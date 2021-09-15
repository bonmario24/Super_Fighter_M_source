package com.eagle.mixsdk.sdk.okhttp.logger;

import com.doraemon.okhttp3.Connection;
import com.doraemon.okhttp3.Headers;
import com.doraemon.okhttp3.Interceptor;
import com.doraemon.okhttp3.MediaType;
import com.doraemon.okhttp3.Protocol;
import com.doraemon.okhttp3.Request;
import com.doraemon.okhttp3.RequestBody;
import com.doraemon.okhttp3.Response;
import com.doraemon.okhttp3.ResponseBody;
import com.doraemon.okio.Buffer;
import com.eagle.mixsdk.sdk.utils.IOUtil;
import com.lzy.okgo.model.HttpHeaders;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

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
        } catch (Exception var8) {
            printLogStar("HTTP FAILED");
            log("<-- HTTP FAILED: " + var8);
            printLogEnd("HTTP FAILED");
            throw var8;
        }
    }

    private void logForRequest(Request request, Connection connection) throws IOException {
        String str;
        String str2;
        boolean logBody = this.printLevel == Level.BODY;
        boolean logHeaders = this.printLevel == Level.BODY || this.printLevel == Level.HEADERS;
        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        try {
            printLogStar("Request " + request.method());
            log("--> " + request.method() + ' ' + request.url() + ' ' + protocol);
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
        } catch (Exception var16) {
            OkLogger.printStackTrace(var16);
        } finally {
            str = "--> END ";
            log(str + request.method());
            str2 = "Request ";
            printLogEnd(str2 + request.method());
        }
    }

    private Response logForResponse(Response response, long tookMs) {
        String str;
        String str2;
        Response clone = response.newBuilder().build();
        ResponseBody responseBody = clone.body();
        boolean logBody = this.printLevel == Level.BODY;
        boolean logHeaders = this.printLevel == Level.BODY || this.printLevel == Level.HEADERS;
        try {
            printLogStar("Response " + clone.code());
            log("<-- " + clone.code() + ' ' + clone.message() + ' ' + clone.request().url() + " (" + tookMs + "ms）");
            if (logHeaders) {
                Headers headers = clone.headers();
                int count = headers.size();
                for (int i = 0; i < count; i++) {
                    log("\t" + headers.name(i) + ": " + headers.value(i));
                }
                log(" ");
                if (logBody && com.doraemon.okhttp3.internal.http.HttpHeaders.hasBody(clone)) {
                    if (responseBody == null) {
                        return response;
                    } else if (isPlaintext(responseBody.contentType())) {
                        byte[] bytes = IOUtil.toByteArray(responseBody.byteStream());
                        log("\tbody:" + new String(bytes, getCharset(responseBody.contentType())));
                        Response var13 = response.newBuilder().body(ResponseBody.create(responseBody.contentType(), bytes)).build();
                        log("<-- END HTTP");
                        printLogEnd("Response");
                        return var13;
                    } else {
                        log("\tbody: maybe [binary body], omitted!");
                    }
                }
            }
            log("<-- END HTTP");
            printLogEnd("Response");
        } catch (Exception var17) {
            OkLogger.printStackTrace(var17);
        } finally {
            str = "<-- END HTTP";
            log(str);
            str2 = "Response";
            printLogEnd(str2);
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

    private void printLogStar(String tag) {
        log(String.format("┏================================================Eagle OkHttp %s STAR================================================┓", new Object[]{tag}));
    }

    private void printLogEnd(String tag) {
        log(String.format("┗================================================Eagle OkHttp %s END=================================================┛", new Object[]{tag}));
        log("   ");
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
        } catch (Exception var6) {
            OkLogger.printStackTrace(var6);
        }
    }
}
