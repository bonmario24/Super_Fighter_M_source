package com.doraemon.okhttp3.internal.http2;

import com.doraemon.okhttp3.Headers;
import com.doraemon.okhttp3.Interceptor;
import com.doraemon.okhttp3.OkHttpClient;
import com.doraemon.okhttp3.Protocol;
import com.doraemon.okhttp3.Request;
import com.doraemon.okhttp3.Response;
import com.doraemon.okhttp3.ResponseBody;
import com.doraemon.okhttp3.internal.Internal;
import com.doraemon.okhttp3.internal.Util;
import com.doraemon.okhttp3.internal.connection.StreamAllocation;
import com.doraemon.okhttp3.internal.http.HttpCodec;
import com.doraemon.okhttp3.internal.http.RealResponseBody;
import com.doraemon.okhttp3.internal.http.RequestLine;
import com.doraemon.okhttp3.internal.http.StatusLine;
import com.doraemon.okio.Buffer;
import com.doraemon.okio.ByteString;
import com.doraemon.okio.ForwardingSource;
import com.doraemon.okio.Okio;
import com.doraemon.okio.Sink;
import com.doraemon.okio.Source;
import com.lzy.okgo.cookie.SerializableCookie;
import com.lzy.okgo.model.HttpHeaders;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class Http2Codec implements HttpCodec {
    private static final ByteString CONNECTION = ByteString.encodeUtf8("connection");
    private static final ByteString ENCODING = ByteString.encodeUtf8("encoding");
    private static final ByteString HOST = ByteString.encodeUtf8(SerializableCookie.HOST);
    private static final List<ByteString> HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableList((T[]) new ByteString[]{CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, f73TE, TRANSFER_ENCODING, ENCODING, UPGRADE, Header.TARGET_METHOD, Header.TARGET_PATH, Header.TARGET_SCHEME, Header.TARGET_AUTHORITY});
    private static final List<ByteString> HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableList((T[]) new ByteString[]{CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, f73TE, TRANSFER_ENCODING, ENCODING, UPGRADE});
    private static final ByteString KEEP_ALIVE = ByteString.encodeUtf8(HttpHeaders.HEAD_VALUE_CONNECTION_KEEP_ALIVE);
    private static final ByteString PROXY_CONNECTION = ByteString.encodeUtf8("proxy-connection");

    /* renamed from: TE */
    private static final ByteString f73TE = ByteString.encodeUtf8("te");
    private static final ByteString TRANSFER_ENCODING = ByteString.encodeUtf8("transfer-encoding");
    private static final ByteString UPGRADE = ByteString.encodeUtf8("upgrade");
    private final Interceptor.Chain chain;
    private final Http2Connection connection;
    private final Protocol protocol;
    private Http2Stream stream;
    final StreamAllocation streamAllocation;

    public Http2Codec(OkHttpClient client, Interceptor.Chain chain2, StreamAllocation streamAllocation2, Http2Connection connection2) {
        this.chain = chain2;
        this.streamAllocation = streamAllocation2;
        this.connection = connection2;
        this.protocol = client.protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE) ? Protocol.H2_PRIOR_KNOWLEDGE : Protocol.HTTP_2;
    }

    public Sink createRequestBody(Request request, long contentLength) {
        return this.stream.getSink();
    }

    public void writeRequestHeaders(Request request) throws IOException {
        if (this.stream == null) {
            this.stream = this.connection.newStream(http2HeadersList(request), request.body() != null);
            this.stream.readTimeout().timeout((long) this.chain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
            this.stream.writeTimeout().timeout((long) this.chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        }
    }

    public void flushRequest() throws IOException {
        this.connection.flush();
    }

    public void finishRequest() throws IOException {
        this.stream.getSink().close();
    }

    public Response.Builder readResponseHeaders(boolean expectContinue) throws IOException {
        Response.Builder responseBuilder = readHttp2HeadersList(this.stream.takeResponseHeaders(), this.protocol);
        if (!expectContinue || Internal.instance.code(responseBuilder) != 100) {
            return responseBuilder;
        }
        return null;
    }

    public static List<Header> http2HeadersList(Request request) {
        Headers headers = request.headers();
        List<Header> result = new ArrayList<>(headers.size() + 4);
        result.add(new Header(Header.TARGET_METHOD, request.method()));
        result.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.url())));
        String host = request.header("Host");
        if (host != null) {
            result.add(new Header(Header.TARGET_AUTHORITY, host));
        }
        result.add(new Header(Header.TARGET_SCHEME, request.url().scheme()));
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            ByteString name = ByteString.encodeUtf8(headers.name(i).toLowerCase(Locale.US));
            if (!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(name)) {
                result.add(new Header(name, headers.value(i)));
            }
        }
        return result;
    }

    public static Response.Builder readHttp2HeadersList(List<Header> headerBlock, Protocol protocol2) throws IOException {
        StatusLine statusLine = null;
        Headers.Builder headersBuilder = new Headers.Builder();
        int size = headerBlock.size();
        for (int i = 0; i < size; i++) {
            Header header = headerBlock.get(i);
            if (header != null) {
                ByteString name = header.name;
                String value = header.value.utf8();
                if (name.equals(Header.RESPONSE_STATUS)) {
                    statusLine = StatusLine.parse("HTTP/1.1 " + value);
                } else if (!HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(name)) {
                    Internal.instance.addLenient(headersBuilder, name.utf8(), value);
                }
            } else if (statusLine != null && statusLine.code == 100) {
                statusLine = null;
                headersBuilder = new Headers.Builder();
            }
        }
        if (statusLine != null) {
            return new Response.Builder().protocol(protocol2).code(statusLine.code).message(statusLine.message).headers(headersBuilder.build());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    public ResponseBody openResponseBody(Response response) throws IOException {
        this.streamAllocation.eventListener.responseBodyStart(this.streamAllocation.call);
        return new RealResponseBody(response.header(HttpHeaders.HEAD_KEY_CONTENT_TYPE), com.doraemon.okhttp3.internal.http.HttpHeaders.contentLength(response), Okio.buffer(new StreamFinishingSource(this.stream.getSource())));
    }

    public void cancel() {
        if (this.stream != null) {
            this.stream.closeLater(ErrorCode.CANCEL);
        }
    }

    class StreamFinishingSource extends ForwardingSource {
        long bytesRead = 0;
        boolean completed = false;

        StreamFinishingSource(Source delegate) {
            super(delegate);
        }

        public long read(Buffer sink, long byteCount) throws IOException {
            try {
                long read = delegate().read(sink, byteCount);
                if (read > 0) {
                    this.bytesRead += read;
                }
                return read;
            } catch (IOException e) {
                endOfInput(e);
                throw e;
            }
        }

        public void close() throws IOException {
            super.close();
            endOfInput((IOException) null);
        }

        private void endOfInput(IOException e) {
            if (!this.completed) {
                this.completed = true;
                Http2Codec.this.streamAllocation.streamFinished(false, Http2Codec.this, this.bytesRead, e);
            }
        }
    }
}
