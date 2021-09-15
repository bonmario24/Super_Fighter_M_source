package com.doraemon.okhttp3.internal.http;

import com.doraemon.okhttp3.Interceptor;
import com.doraemon.okhttp3.Request;
import com.doraemon.okhttp3.Response;
import com.doraemon.okhttp3.internal.Util;
import com.doraemon.okhttp3.internal.connection.RealConnection;
import com.doraemon.okhttp3.internal.connection.StreamAllocation;
import com.doraemon.okio.Buffer;
import com.doraemon.okio.BufferedSink;
import com.doraemon.okio.ForwardingSink;
import com.doraemon.okio.Okio;
import com.doraemon.okio.Sink;
import com.lzy.okgo.model.HttpHeaders;
import java.io.IOException;
import java.net.ProtocolException;

public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean forWebSocket2) {
        this.forWebSocket = forWebSocket2;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response response;
        RealInterceptorChain realChain = (RealInterceptorChain) chain;
        HttpCodec httpCodec = realChain.httpStream();
        StreamAllocation streamAllocation = realChain.streamAllocation();
        RealConnection connection = (RealConnection) realChain.connection();
        Request request = realChain.request();
        long sentRequestMillis = System.currentTimeMillis();
        realChain.eventListener().requestHeadersStart(realChain.call());
        httpCodec.writeRequestHeaders(request);
        realChain.eventListener().requestHeadersEnd(realChain.call(), request);
        Response.Builder responseBuilder = null;
        if (HttpMethod.permitsRequestBody(request.method()) && request.body() != null) {
            if ("100-continue".equalsIgnoreCase(request.header("Expect"))) {
                httpCodec.flushRequest();
                realChain.eventListener().responseHeadersStart(realChain.call());
                responseBuilder = httpCodec.readResponseHeaders(true);
            }
            if (responseBuilder == null) {
                realChain.eventListener().requestBodyStart(realChain.call());
                CountingSink requestBodyOut = new CountingSink(httpCodec.createRequestBody(request, request.body().contentLength()));
                BufferedSink bufferedRequestBody = Okio.buffer((Sink) requestBodyOut);
                request.body().writeTo(bufferedRequestBody);
                bufferedRequestBody.close();
                realChain.eventListener().requestBodyEnd(realChain.call(), requestBodyOut.successfulCount);
            } else if (!connection.isMultiplexed()) {
                streamAllocation.noNewStreams();
            }
        }
        httpCodec.finishRequest();
        if (responseBuilder == null) {
            realChain.eventListener().responseHeadersStart(realChain.call());
            responseBuilder = httpCodec.readResponseHeaders(false);
        }
        Response response2 = responseBuilder.request(request).handshake(streamAllocation.connection().handshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        int code = response2.code();
        if (code == 100) {
            response2 = httpCodec.readResponseHeaders(false).request(request).handshake(streamAllocation.connection().handshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
            code = response2.code();
        }
        realChain.eventListener().responseHeadersEnd(realChain.call(), response2);
        if (!this.forWebSocket || code != 101) {
            response = response2.newBuilder().body(httpCodec.openResponseBody(response2)).build();
        } else {
            response = response2.newBuilder().body(Util.EMPTY_RESPONSE).build();
        }
        if (HttpHeaders.HEAD_VALUE_CONNECTION_CLOSE.equalsIgnoreCase(response.request().header(HttpHeaders.HEAD_KEY_CONNECTION)) || HttpHeaders.HEAD_VALUE_CONNECTION_CLOSE.equalsIgnoreCase(response.header(HttpHeaders.HEAD_KEY_CONNECTION))) {
            streamAllocation.noNewStreams();
        }
        if ((code != 204 && code != 205) || response.body().contentLength() <= 0) {
            return response;
        }
        throw new ProtocolException("HTTP " + code + " had non-zero Content-Length: " + response.body().contentLength());
    }

    static final class CountingSink extends ForwardingSink {
        long successfulCount;

        CountingSink(Sink delegate) {
            super(delegate);
        }

        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            this.successfulCount += byteCount;
        }
    }
}
