package com.lzy.okgo.interceptor;

import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.utils.IOUtils;
import com.lzy.okgo.utils.OkLogger;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private java.util.logging.Level colorLevel;
    private Logger logger;
    private volatile Level printLevel = Level.NONE;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public HttpLoggingInterceptor(String tag) {
        this.logger = Logger.getLogger(tag);
    }

    public void setPrintLevel(Level level) {
        if (this.printLevel == null) {
            throw new NullPointerException("printLevel == null. Use Level.NONE instead.");
        }
        this.printLevel = level;
    }

    public void setColorLevel(java.util.logging.Level level) {
        this.colorLevel = level;
    }

    private void log(String message) {
        this.logger.log(this.colorLevel, message);
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (this.printLevel == Level.NONE) {
            return chain.proceed(request);
        }
        logForRequest(request, chain.connection());
        long startNs = System.nanoTime();
        try {
            return logForResponse(chain.proceed(request), TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs));
        } catch (Exception e) {
            log("<-- HTTP FAILED: " + e);
            throw e;
        }
    }

    private void logForRequest(Request request, Connection connection) throws IOException {
        String str;
        boolean logBody = this.printLevel == Level.BODY;
        boolean logHeaders = this.printLevel == Level.BODY || this.printLevel == Level.HEADERS;
        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;
        try {
            log("--> " + request.method() + ' ' + request.url() + ' ' + (connection != null ? connection.protocol() : Protocol.HTTP_1_1));
            if (logHeaders) {
                if (hasRequestBody) {
                    if (requestBody.contentType() != null) {
                        log("\tContent-Type: " + requestBody.contentType());
                    }
                    if (requestBody.contentLength() != -1) {
                        log("\tContent-Length: " + requestBody.contentLength());
                    }
                }
                Headers headers = request.headers();
                int count = headers.size();
                for (int i = 0; i < count; i++) {
                    String name = headers.name(i);
                    if (!HttpHeaders.HEAD_KEY_CONTENT_TYPE.equalsIgnoreCase(name) && !HttpHeaders.HEAD_KEY_CONTENT_LENGTH.equalsIgnoreCase(name)) {
                        log("\t" + name + ": " + headers.value(i));
                    }
                }
                log(" ");
                if (logBody && hasRequestBody) {
                    if (isPlaintext(requestBody.contentType())) {
                        bodyToString(request);
                    } else {
                        log("\tbody: maybe [binary body], omitted!");
                    }
                }
            }
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
        } finally {
            str = "--> END ";
            log(str + request.method());
        }
    }

    private Response logForResponse(Response response, long tookMs) {
        String str;
        Response clone = response.newBuilder().build();
        ResponseBody responseBody = clone.body();
        boolean logBody = this.printLevel == Level.BODY;
        boolean logHeaders = this.printLevel == Level.BODY || this.printLevel == Level.HEADERS;
        try {
            log("<-- " + clone.code() + ' ' + clone.message() + ' ' + clone.request().url() + " (" + tookMs + "ms）");
            if (logHeaders) {
                Headers headers = clone.headers();
                int count = headers.size();
                for (int i = 0; i < count; i++) {
                    log("\t" + headers.name(i) + ": " + headers.value(i));
                }
                log(" ");
                if (logBody && okhttp3.internal.http.HttpHeaders.hasBody(clone)) {
                    if (responseBody != null) {
                        if (isPlaintext(responseBody.contentType())) {
                            byte[] bytes = IOUtils.toByteArray(responseBody.byteStream());
                            log("\tbody:" + new String(bytes, getCharset(responseBody.contentType())));
                            response = response.newBuilder().body(ResponseBody.create(responseBody.contentType(), bytes)).build();
                            log("<-- END HTTP");
                        } else {
                            log("\tbody: maybe [binary body], omitted!");
                        }
                    }
                    return response;
                }
            }
            log("<-- END HTTP");
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
        } finally {
            str = "<-- END HTTP";
            log(str);
        }
        return response;
    }

    private static Charset getCharset(MediaType contentType) {
        Charset charset = contentType != null ? contentType.charset(UTF8) : UTF8;
        if (charset == null) {
            return UTF8;
        }
        return charset;
    }

    private static boolean isPlaintext(MediaType mediaType) {
        if (mediaType == null) {
            return false;
        }
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        String subtype = mediaType.subtype();
        if (subtype == null) {
            return false;
        }
        String subtype2 = subtype.toLowerCase();
        if (subtype2.contains("x-www-form-urlencoded") || subtype2.contains("json") || subtype2.contains("xml") || subtype2.contains("html")) {
            return true;
        }
        return false;
    }

    private void bodyToString(Request request) {
        try {
            RequestBody body = request.newBuilder().build().body();
            if (body != null) {
                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                log("\tbody:" + buffer.readString(getCharset(body.contentType())));
            }
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
        }
    }
}
