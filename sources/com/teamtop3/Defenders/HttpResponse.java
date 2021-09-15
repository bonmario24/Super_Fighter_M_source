package com.teamtop3.Defenders;

public class HttpResponse {

    /* renamed from: OK */
    public static final int f88OK = 200;
    protected int code;
    protected int connectTimeout;
    protected String content;
    protected String contentEncoding;
    protected String contentType;
    protected int defaultPort;
    protected String file;
    protected String host;
    protected String message;
    protected String method;
    protected String path;
    protected int port;
    protected String protocol;
    protected String query;
    protected int readTimeout;
    protected String ref;
    protected String userInfo;

    public int getCode() {
        return this.code;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public String getContent() {
        return this.content;
    }

    public String getContentEncoding() {
        return this.contentEncoding;
    }

    public String getContentType() {
        return this.contentType;
    }

    public int getDefaultPort() {
        return this.defaultPort;
    }

    public String getFile() {
        return this.file;
    }

    public String getHost() {
        return this.host;
    }

    public String getMessage() {
        return this.message;
    }

    public String getMethod() {
        return this.method;
    }

    public String getPath() {
        return this.path;
    }

    public int getPort() {
        return this.port;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public String getQuery() {
        return this.query;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public String getRef() {
        return this.ref;
    }

    public String getUserInfo() {
        return this.userInfo;
    }
}
