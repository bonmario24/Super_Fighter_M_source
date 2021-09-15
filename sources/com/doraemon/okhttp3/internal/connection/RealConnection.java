package com.doraemon.okhttp3.internal.connection;

import com.doraemon.ext.Nullable;
import com.doraemon.okhttp3.Address;
import com.doraemon.okhttp3.Call;
import com.doraemon.okhttp3.Connection;
import com.doraemon.okhttp3.ConnectionPool;
import com.doraemon.okhttp3.EventListener;
import com.doraemon.okhttp3.Handshake;
import com.doraemon.okhttp3.HttpUrl;
import com.doraemon.okhttp3.Interceptor;
import com.doraemon.okhttp3.OkHttpClient;
import com.doraemon.okhttp3.Protocol;
import com.doraemon.okhttp3.Request;
import com.doraemon.okhttp3.Response;
import com.doraemon.okhttp3.Route;
import com.doraemon.okhttp3.internal.Internal;
import com.doraemon.okhttp3.internal.Util;
import com.doraemon.okhttp3.internal.Version;
import com.doraemon.okhttp3.internal.http.HttpCodec;
import com.doraemon.okhttp3.internal.http.HttpHeaders;
import com.doraemon.okhttp3.internal.http1.Http1Codec;
import com.doraemon.okhttp3.internal.http2.ErrorCode;
import com.doraemon.okhttp3.internal.http2.Http2Codec;
import com.doraemon.okhttp3.internal.http2.Http2Connection;
import com.doraemon.okhttp3.internal.http2.Http2Stream;
import com.doraemon.okhttp3.internal.p008ws.RealWebSocket;
import com.doraemon.okhttp3.internal.platform.Platform;
import com.doraemon.okhttp3.internal.tls.OkHostnameVerifier;
import com.doraemon.okio.BufferedSink;
import com.doraemon.okio.BufferedSource;
import com.doraemon.okio.Okio;
import com.doraemon.okio.Source;
import com.teamtop3.Defenders.HttpResponse;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;

public final class RealConnection extends Http2Connection.Listener implements Connection {
    private static final int MAX_TUNNEL_ATTEMPTS = 21;
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    public int allocationLimit = 1;
    public final List<Reference<StreamAllocation>> allocations = new ArrayList();
    private final ConnectionPool connectionPool;
    private Handshake handshake;
    private Http2Connection http2Connection;
    public long idleAtNanos = Long.MAX_VALUE;
    public boolean noNewStreams;
    private Protocol protocol;
    private Socket rawSocket;
    private final Route route;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    public int successCount;

    public RealConnection(ConnectionPool connectionPool2, Route route2) {
        this.connectionPool = connectionPool2;
        this.route = route2;
    }

    public static RealConnection testConnection(ConnectionPool connectionPool2, Route route2, Socket socket2, long idleAtNanos2) {
        RealConnection result = new RealConnection(connectionPool2, route2);
        result.socket = socket2;
        result.idleAtNanos = idleAtNanos2;
        return result;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00af, code lost:
        if (r14.rawSocket == null) goto L_0x00b1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a0 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(int r15, int r16, int r17, int r18, boolean r19, com.doraemon.okhttp3.Call r20, com.doraemon.okhttp3.EventListener r21) {
        /*
            r14 = this;
            com.doraemon.okhttp3.Protocol r3 = r14.protocol
            if (r3 == 0) goto L_0x000c
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "already connected"
            r3.<init>(r4)
            throw r3
        L_0x000c:
            r13 = 0
            com.doraemon.okhttp3.Route r3 = r14.route
            com.doraemon.okhttp3.Address r3 = r3.address()
            java.util.List r10 = r3.connectionSpecs()
            com.doraemon.okhttp3.internal.connection.ConnectionSpecSelector r9 = new com.doraemon.okhttp3.internal.connection.ConnectionSpecSelector
            r9.<init>(r10)
            com.doraemon.okhttp3.Route r3 = r14.route
            com.doraemon.okhttp3.Address r3 = r3.address()
            javax.net.ssl.SSLSocketFactory r3 = r3.sslSocketFactory()
            if (r3 != 0) goto L_0x0079
            com.doraemon.okhttp3.ConnectionSpec r3 = com.doraemon.okhttp3.ConnectionSpec.CLEARTEXT
            boolean r3 = r10.contains(r3)
            if (r3 != 0) goto L_0x003d
            com.doraemon.okhttp3.internal.connection.RouteException r3 = new com.doraemon.okhttp3.internal.connection.RouteException
            java.net.UnknownServiceException r4 = new java.net.UnknownServiceException
            java.lang.String r5 = "CLEARTEXT communication not enabled for client"
            r4.<init>(r5)
            r3.<init>(r4)
            throw r3
        L_0x003d:
            com.doraemon.okhttp3.Route r3 = r14.route
            com.doraemon.okhttp3.Address r3 = r3.address()
            com.doraemon.okhttp3.HttpUrl r3 = r3.url()
            java.lang.String r12 = r3.host()
            com.doraemon.okhttp3.internal.platform.Platform r3 = com.doraemon.okhttp3.internal.platform.Platform.get()
            boolean r3 = r3.isCleartextTrafficPermitted(r12)
            if (r3 != 0) goto L_0x0098
            com.doraemon.okhttp3.internal.connection.RouteException r3 = new com.doraemon.okhttp3.internal.connection.RouteException
            java.net.UnknownServiceException r4 = new java.net.UnknownServiceException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "CLEARTEXT communication to "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r12)
            java.lang.String r6 = " not permitted by network security policy"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            r3.<init>(r4)
            throw r3
        L_0x0079:
            com.doraemon.okhttp3.Route r3 = r14.route
            com.doraemon.okhttp3.Address r3 = r3.address()
            java.util.List r3 = r3.protocols()
            com.doraemon.okhttp3.Protocol r4 = com.doraemon.okhttp3.Protocol.H2_PRIOR_KNOWLEDGE
            boolean r3 = r3.contains(r4)
            if (r3 == 0) goto L_0x0098
            com.doraemon.okhttp3.internal.connection.RouteException r3 = new com.doraemon.okhttp3.internal.connection.RouteException
            java.net.UnknownServiceException r4 = new java.net.UnknownServiceException
            java.lang.String r5 = "H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"
            r4.<init>(r5)
            r3.<init>(r4)
            throw r3
        L_0x0098:
            com.doraemon.okhttp3.Route r3 = r14.route     // Catch:{ IOException -> 0x00f2 }
            boolean r3 = r3.requiresTunnel()     // Catch:{ IOException -> 0x00f2 }
            if (r3 == 0) goto L_0x00ca
            r3 = r14
            r4 = r15
            r5 = r16
            r6 = r17
            r7 = r20
            r8 = r21
            r3.connectTunnel(r4, r5, r6, r7, r8)     // Catch:{ IOException -> 0x00f2 }
            java.net.Socket r3 = r14.rawSocket     // Catch:{ IOException -> 0x00f2 }
            if (r3 != 0) goto L_0x00d3
        L_0x00b1:
            com.doraemon.okhttp3.Route r3 = r14.route
            boolean r3 = r3.requiresTunnel()
            if (r3 == 0) goto L_0x013a
            java.net.Socket r3 = r14.rawSocket
            if (r3 != 0) goto L_0x013a
            java.net.ProtocolException r11 = new java.net.ProtocolException
            java.lang.String r3 = "Too many tunnel connections attempted: 21"
            r11.<init>(r3)
            com.doraemon.okhttp3.internal.connection.RouteException r3 = new com.doraemon.okhttp3.internal.connection.RouteException
            r3.<init>(r11)
            throw r3
        L_0x00ca:
            r0 = r16
            r1 = r20
            r2 = r21
            r14.connectSocket(r15, r0, r1, r2)     // Catch:{ IOException -> 0x00f2 }
        L_0x00d3:
            r0 = r18
            r1 = r20
            r2 = r21
            r14.establishProtocol(r9, r0, r1, r2)     // Catch:{ IOException -> 0x00f2 }
            com.doraemon.okhttp3.Route r3 = r14.route     // Catch:{ IOException -> 0x00f2 }
            java.net.InetSocketAddress r3 = r3.socketAddress()     // Catch:{ IOException -> 0x00f2 }
            com.doraemon.okhttp3.Route r4 = r14.route     // Catch:{ IOException -> 0x00f2 }
            java.net.Proxy r4 = r4.proxy()     // Catch:{ IOException -> 0x00f2 }
            com.doraemon.okhttp3.Protocol r5 = r14.protocol     // Catch:{ IOException -> 0x00f2 }
            r0 = r21
            r1 = r20
            r0.connectEnd(r1, r3, r4, r5)     // Catch:{ IOException -> 0x00f2 }
            goto L_0x00b1
        L_0x00f2:
            r8 = move-exception
            java.net.Socket r3 = r14.socket
            com.doraemon.okhttp3.internal.Util.closeQuietly((java.net.Socket) r3)
            java.net.Socket r3 = r14.rawSocket
            com.doraemon.okhttp3.internal.Util.closeQuietly((java.net.Socket) r3)
            r3 = 0
            r14.socket = r3
            r3 = 0
            r14.rawSocket = r3
            r3 = 0
            r14.source = r3
            r3 = 0
            r14.sink = r3
            r3 = 0
            r14.handshake = r3
            r3 = 0
            r14.protocol = r3
            r3 = 0
            r14.http2Connection = r3
            com.doraemon.okhttp3.Route r3 = r14.route
            java.net.InetSocketAddress r5 = r3.socketAddress()
            com.doraemon.okhttp3.Route r3 = r14.route
            java.net.Proxy r6 = r3.proxy()
            r7 = 0
            r3 = r21
            r4 = r20
            r3.connectFailed(r4, r5, r6, r7, r8)
            if (r13 != 0) goto L_0x0136
            com.doraemon.okhttp3.internal.connection.RouteException r13 = new com.doraemon.okhttp3.internal.connection.RouteException
            r13.<init>(r8)
        L_0x012d:
            if (r19 == 0) goto L_0x0135
            boolean r3 = r9.connectionFailed(r8)
            if (r3 != 0) goto L_0x0098
        L_0x0135:
            throw r13
        L_0x0136:
            r13.addConnectException(r8)
            goto L_0x012d
        L_0x013a:
            com.doraemon.okhttp3.internal.http2.Http2Connection r3 = r14.http2Connection
            if (r3 == 0) goto L_0x014a
            com.doraemon.okhttp3.ConnectionPool r4 = r14.connectionPool
            monitor-enter(r4)
            com.doraemon.okhttp3.internal.http2.Http2Connection r3 = r14.http2Connection     // Catch:{ all -> 0x014b }
            int r3 = r3.maxConcurrentStreams()     // Catch:{ all -> 0x014b }
            r14.allocationLimit = r3     // Catch:{ all -> 0x014b }
            monitor-exit(r4)     // Catch:{ all -> 0x014b }
        L_0x014a:
            return
        L_0x014b:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x014b }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.okhttp3.internal.connection.RealConnection.connect(int, int, int, int, boolean, com.doraemon.okhttp3.Call, com.doraemon.okhttp3.EventListener):void");
    }

    private void connectTunnel(int connectTimeout, int readTimeout, int writeTimeout, Call call, EventListener eventListener) throws IOException {
        Request tunnelRequest = createTunnelRequest();
        HttpUrl url = tunnelRequest.url();
        int i = 0;
        while (i < 21) {
            connectSocket(connectTimeout, readTimeout, call, eventListener);
            tunnelRequest = createTunnel(readTimeout, writeTimeout, tunnelRequest, url);
            if (tunnelRequest != null) {
                Util.closeQuietly(this.rawSocket);
                this.rawSocket = null;
                this.sink = null;
                this.source = null;
                eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), (Protocol) null);
                i++;
            } else {
                return;
            }
        }
    }

    private void connectSocket(int connectTimeout, int readTimeout, Call call, EventListener eventListener) throws IOException {
        Proxy proxy = this.route.proxy();
        this.rawSocket = (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.HTTP) ? this.route.address().socketFactory().createSocket() : new Socket(proxy);
        eventListener.connectStart(call, this.route.socketAddress(), proxy);
        this.rawSocket.setSoTimeout(readTimeout);
        try {
            Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), connectTimeout);
            try {
                this.source = Okio.buffer(Okio.source(this.rawSocket));
                this.sink = Okio.buffer(Okio.sink(this.rawSocket));
            } catch (NullPointerException npe) {
                if (NPE_THROW_WITH_NULL.equals(npe.getMessage())) {
                    throw new IOException(npe);
                }
            }
        } catch (ConnectException e) {
            ConnectException ce = new ConnectException("Failed to connect to " + this.route.socketAddress());
            ce.initCause(e);
            throw ce;
        }
    }

    private void establishProtocol(ConnectionSpecSelector connectionSpecSelector, int pingIntervalMillis, Call call, EventListener eventListener) throws IOException {
        if (this.route.address().sslSocketFactory() != null) {
            eventListener.secureConnectStart(call);
            connectTls(connectionSpecSelector);
            eventListener.secureConnectEnd(call, this.handshake);
            if (this.protocol == Protocol.HTTP_2) {
                startHttp2(pingIntervalMillis);
            }
        } else if (this.route.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
            this.socket = this.rawSocket;
            this.protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            startHttp2(pingIntervalMillis);
        } else {
            this.socket = this.rawSocket;
            this.protocol = Protocol.HTTP_1_1;
        }
    }

    private void startHttp2(int pingIntervalMillis) throws IOException {
        this.socket.setSoTimeout(0);
        this.http2Connection = new Http2Connection.Builder(true).socket(this.socket, this.route.address().url().host(), this.source, this.sink).listener(this).pingIntervalMillis(pingIntervalMillis).build();
        this.http2Connection.start();
    }

    /* JADX WARNING: type inference failed for: r11v5, types: [java.net.Socket] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void connectTls(com.doraemon.okhttp3.internal.connection.ConnectionSpecSelector r16) throws java.io.IOException {
        /*
            r15 = this;
            com.doraemon.okhttp3.Route r11 = r15.route
            com.doraemon.okhttp3.Address r1 = r11.address()
            javax.net.ssl.SSLSocketFactory r7 = r1.sslSocketFactory()
            r9 = 0
            r6 = 0
            java.net.Socket r11 = r15.rawSocket     // Catch:{ AssertionError -> 0x00bd }
            com.doraemon.okhttp3.HttpUrl r12 = r1.url()     // Catch:{ AssertionError -> 0x00bd }
            java.lang.String r12 = r12.host()     // Catch:{ AssertionError -> 0x00bd }
            com.doraemon.okhttp3.HttpUrl r13 = r1.url()     // Catch:{ AssertionError -> 0x00bd }
            int r13 = r13.port()     // Catch:{ AssertionError -> 0x00bd }
            r14 = 1
            java.net.Socket r11 = r7.createSocket(r11, r12, r13, r14)     // Catch:{ AssertionError -> 0x00bd }
            r0 = r11
            javax.net.ssl.SSLSocket r0 = (javax.net.ssl.SSLSocket) r0     // Catch:{ AssertionError -> 0x00bd }
            r6 = r0
            r0 = r16
            com.doraemon.okhttp3.ConnectionSpec r3 = r0.configureSecureSocket(r6)     // Catch:{ AssertionError -> 0x00bd }
            boolean r11 = r3.supportsTlsExtensions()     // Catch:{ AssertionError -> 0x00bd }
            if (r11 == 0) goto L_0x0046
            com.doraemon.okhttp3.internal.platform.Platform r11 = com.doraemon.okhttp3.internal.platform.Platform.get()     // Catch:{ AssertionError -> 0x00bd }
            com.doraemon.okhttp3.HttpUrl r12 = r1.url()     // Catch:{ AssertionError -> 0x00bd }
            java.lang.String r12 = r12.host()     // Catch:{ AssertionError -> 0x00bd }
            java.util.List r13 = r1.protocols()     // Catch:{ AssertionError -> 0x00bd }
            r11.configureTlsExtensions(r6, r12, r13)     // Catch:{ AssertionError -> 0x00bd }
        L_0x0046:
            r6.startHandshake()     // Catch:{ AssertionError -> 0x00bd }
            javax.net.ssl.SSLSession r8 = r6.getSession()     // Catch:{ AssertionError -> 0x00bd }
            com.doraemon.okhttp3.Handshake r10 = com.doraemon.okhttp3.Handshake.get(r8)     // Catch:{ AssertionError -> 0x00bd }
            javax.net.ssl.HostnameVerifier r11 = r1.hostnameVerifier()     // Catch:{ AssertionError -> 0x00bd }
            com.doraemon.okhttp3.HttpUrl r12 = r1.url()     // Catch:{ AssertionError -> 0x00bd }
            java.lang.String r12 = r12.host()     // Catch:{ AssertionError -> 0x00bd }
            boolean r11 = r11.verify(r12, r8)     // Catch:{ AssertionError -> 0x00bd }
            if (r11 != 0) goto L_0x00da
            java.util.List r11 = r10.peerCertificates()     // Catch:{ AssertionError -> 0x00bd }
            r12 = 0
            java.lang.Object r2 = r11.get(r12)     // Catch:{ AssertionError -> 0x00bd }
            java.security.cert.X509Certificate r2 = (java.security.cert.X509Certificate) r2     // Catch:{ AssertionError -> 0x00bd }
            javax.net.ssl.SSLPeerUnverifiedException r11 = new javax.net.ssl.SSLPeerUnverifiedException     // Catch:{ AssertionError -> 0x00bd }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x00bd }
            r12.<init>()     // Catch:{ AssertionError -> 0x00bd }
            java.lang.String r13 = "Hostname "
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ AssertionError -> 0x00bd }
            com.doraemon.okhttp3.HttpUrl r13 = r1.url()     // Catch:{ AssertionError -> 0x00bd }
            java.lang.String r13 = r13.host()     // Catch:{ AssertionError -> 0x00bd }
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ AssertionError -> 0x00bd }
            java.lang.String r13 = " not verified:\n    certificate: "
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ AssertionError -> 0x00bd }
            java.lang.String r13 = com.doraemon.okhttp3.CertificatePinner.pin(r2)     // Catch:{ AssertionError -> 0x00bd }
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ AssertionError -> 0x00bd }
            java.lang.String r13 = "\n    DN: "
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ AssertionError -> 0x00bd }
            java.security.Principal r13 = r2.getSubjectDN()     // Catch:{ AssertionError -> 0x00bd }
            java.lang.String r13 = r13.getName()     // Catch:{ AssertionError -> 0x00bd }
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ AssertionError -> 0x00bd }
            java.lang.String r13 = "\n    subjectAltNames: "
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ AssertionError -> 0x00bd }
            java.util.List r13 = com.doraemon.okhttp3.internal.tls.OkHostnameVerifier.allSubjectAltNames(r2)     // Catch:{ AssertionError -> 0x00bd }
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ AssertionError -> 0x00bd }
            java.lang.String r12 = r12.toString()     // Catch:{ AssertionError -> 0x00bd }
            r11.<init>(r12)     // Catch:{ AssertionError -> 0x00bd }
            throw r11     // Catch:{ AssertionError -> 0x00bd }
        L_0x00bd:
            r4 = move-exception
            boolean r11 = com.doraemon.okhttp3.internal.Util.isAndroidGetsocknameError(r4)     // Catch:{ all -> 0x00ca }
            if (r11 == 0) goto L_0x0134
            java.io.IOException r11 = new java.io.IOException     // Catch:{ all -> 0x00ca }
            r11.<init>(r4)     // Catch:{ all -> 0x00ca }
            throw r11     // Catch:{ all -> 0x00ca }
        L_0x00ca:
            r11 = move-exception
            if (r6 == 0) goto L_0x00d4
            com.doraemon.okhttp3.internal.platform.Platform r12 = com.doraemon.okhttp3.internal.platform.Platform.get()
            r12.afterHandshake(r6)
        L_0x00d4:
            if (r9 != 0) goto L_0x00d9
            com.doraemon.okhttp3.internal.Util.closeQuietly((java.net.Socket) r6)
        L_0x00d9:
            throw r11
        L_0x00da:
            com.doraemon.okhttp3.CertificatePinner r11 = r1.certificatePinner()     // Catch:{ AssertionError -> 0x00bd }
            com.doraemon.okhttp3.HttpUrl r12 = r1.url()     // Catch:{ AssertionError -> 0x00bd }
            java.lang.String r12 = r12.host()     // Catch:{ AssertionError -> 0x00bd }
            java.util.List r13 = r10.peerCertificates()     // Catch:{ AssertionError -> 0x00bd }
            r11.check((java.lang.String) r12, (java.util.List<java.security.cert.Certificate>) r13)     // Catch:{ AssertionError -> 0x00bd }
            boolean r11 = r3.supportsTlsExtensions()     // Catch:{ AssertionError -> 0x00bd }
            if (r11 == 0) goto L_0x012f
            com.doraemon.okhttp3.internal.platform.Platform r11 = com.doraemon.okhttp3.internal.platform.Platform.get()     // Catch:{ AssertionError -> 0x00bd }
            java.lang.String r5 = r11.getSelectedProtocol(r6)     // Catch:{ AssertionError -> 0x00bd }
        L_0x00fb:
            r15.socket = r6     // Catch:{ AssertionError -> 0x00bd }
            java.net.Socket r11 = r15.socket     // Catch:{ AssertionError -> 0x00bd }
            com.doraemon.okio.Source r11 = com.doraemon.okio.Okio.source((java.net.Socket) r11)     // Catch:{ AssertionError -> 0x00bd }
            com.doraemon.okio.BufferedSource r11 = com.doraemon.okio.Okio.buffer((com.doraemon.okio.Source) r11)     // Catch:{ AssertionError -> 0x00bd }
            r15.source = r11     // Catch:{ AssertionError -> 0x00bd }
            java.net.Socket r11 = r15.socket     // Catch:{ AssertionError -> 0x00bd }
            com.doraemon.okio.Sink r11 = com.doraemon.okio.Okio.sink((java.net.Socket) r11)     // Catch:{ AssertionError -> 0x00bd }
            com.doraemon.okio.BufferedSink r11 = com.doraemon.okio.Okio.buffer((com.doraemon.okio.Sink) r11)     // Catch:{ AssertionError -> 0x00bd }
            r15.sink = r11     // Catch:{ AssertionError -> 0x00bd }
            r15.handshake = r10     // Catch:{ AssertionError -> 0x00bd }
            if (r5 == 0) goto L_0x0131
            com.doraemon.okhttp3.Protocol r11 = com.doraemon.okhttp3.Protocol.get(r5)     // Catch:{ AssertionError -> 0x00bd }
        L_0x011d:
            r15.protocol = r11     // Catch:{ AssertionError -> 0x00bd }
            r9 = 1
            if (r6 == 0) goto L_0x0129
            com.doraemon.okhttp3.internal.platform.Platform r11 = com.doraemon.okhttp3.internal.platform.Platform.get()
            r11.afterHandshake(r6)
        L_0x0129:
            if (r9 != 0) goto L_0x012e
            com.doraemon.okhttp3.internal.Util.closeQuietly((java.net.Socket) r6)
        L_0x012e:
            return
        L_0x012f:
            r5 = 0
            goto L_0x00fb
        L_0x0131:
            com.doraemon.okhttp3.Protocol r11 = com.doraemon.okhttp3.Protocol.HTTP_1_1     // Catch:{ AssertionError -> 0x00bd }
            goto L_0x011d
        L_0x0134:
            throw r4     // Catch:{ all -> 0x00ca }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.okhttp3.internal.connection.RealConnection.connectTls(com.doraemon.okhttp3.internal.connection.ConnectionSpecSelector):void");
    }

    private Request createTunnel(int readTimeout, int writeTimeout, Request tunnelRequest, HttpUrl url) throws IOException {
        Response response;
        String requestLine = "CONNECT " + Util.hostHeader(url, true) + " HTTP/1.1";
        do {
            Http1Codec tunnelConnection = new Http1Codec((OkHttpClient) null, (StreamAllocation) null, this.source, this.sink);
            this.source.timeout().timeout((long) readTimeout, TimeUnit.MILLISECONDS);
            this.sink.timeout().timeout((long) writeTimeout, TimeUnit.MILLISECONDS);
            tunnelConnection.writeRequest(tunnelRequest.headers(), requestLine);
            tunnelConnection.finishRequest();
            response = tunnelConnection.readResponseHeaders(false).request(tunnelRequest).build();
            long contentLength = HttpHeaders.contentLength(response);
            if (contentLength == -1) {
                contentLength = 0;
            }
            Source body = tunnelConnection.newFixedLengthSource(contentLength);
            Util.skipAll(body, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            body.close();
            switch (response.code()) {
                case HttpResponse.f88OK /*200*/:
                    if (this.source.buffer().exhausted() && this.sink.buffer().exhausted()) {
                        return null;
                    }
                    throw new IOException("TLS tunnel buffered too many bytes!");
                case 407:
                    tunnelRequest = this.route.address().proxyAuthenticator().authenticate(this.route, response);
                    if (tunnelRequest != null) {
                        break;
                    } else {
                        throw new IOException("Failed to authenticate with proxy");
                    }
                default:
                    throw new IOException("Unexpected response code for CONNECT: " + response.code());
            }
        } while (!com.lzy.okgo.model.HttpHeaders.HEAD_VALUE_CONNECTION_CLOSE.equalsIgnoreCase(response.header(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_CONNECTION)));
        return tunnelRequest;
    }

    private Request createTunnelRequest() {
        return new Request.Builder().url(this.route.address().url()).header("Host", Util.hostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_USER_AGENT, Version.userAgent()).build();
    }

    public boolean isEligible(Address address, @Nullable Route route2) {
        if (this.allocations.size() >= this.allocationLimit || this.noNewStreams || !Internal.instance.equalsNonHost(this.route.address(), address)) {
            return false;
        }
        if (address.url().host().equals(route().address().url().host())) {
            return true;
        }
        if (this.http2Connection == null || route2 == null || route2.proxy().type() != Proxy.Type.DIRECT || this.route.proxy().type() != Proxy.Type.DIRECT || !this.route.socketAddress().equals(route2.socketAddress()) || route2.address().hostnameVerifier() != OkHostnameVerifier.INSTANCE || !supportsUrl(address.url())) {
            return false;
        }
        try {
            address.certificatePinner().check(address.url().host(), handshake().peerCertificates());
            return true;
        } catch (SSLPeerUnverifiedException e) {
            return false;
        }
    }

    public boolean supportsUrl(HttpUrl url) {
        if (url.port() != this.route.address().url().port()) {
            return false;
        }
        if (url.host().equals(this.route.address().url().host())) {
            return true;
        }
        return this.handshake != null && OkHostnameVerifier.INSTANCE.verify(url.host(), (X509Certificate) this.handshake.peerCertificates().get(0));
    }

    public HttpCodec newCodec(OkHttpClient client, Interceptor.Chain chain, StreamAllocation streamAllocation) throws SocketException {
        if (this.http2Connection != null) {
            return new Http2Codec(client, chain, streamAllocation, this.http2Connection);
        }
        this.socket.setSoTimeout(chain.readTimeoutMillis());
        this.source.timeout().timeout((long) chain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.sink.timeout().timeout((long) chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        return new Http1Codec(client, streamAllocation, this.source, this.sink);
    }

    public RealWebSocket.Streams newWebSocketStreams(StreamAllocation streamAllocation) {
        final StreamAllocation streamAllocation2 = streamAllocation;
        return new RealWebSocket.Streams(true, this.source, this.sink) {
            public void close() throws IOException {
                streamAllocation2.streamFinished(true, streamAllocation2.codec(), -1, (IOException) null);
            }
        };
    }

    public Route route() {
        return this.route;
    }

    public void cancel() {
        Util.closeQuietly(this.rawSocket);
    }

    public Socket socket() {
        return this.socket;
    }

    public boolean isHealthy(boolean doExtensiveChecks) {
        int readTimeout;
        if (this.socket.isClosed() || this.socket.isInputShutdown() || this.socket.isOutputShutdown()) {
            return false;
        }
        if (this.http2Connection != null) {
            if (this.http2Connection.isShutdown()) {
                return false;
            }
            return true;
        } else if (!doExtensiveChecks) {
            return true;
        } else {
            try {
                readTimeout = this.socket.getSoTimeout();
                this.socket.setSoTimeout(1);
                if (this.source.exhausted()) {
                    this.socket.setSoTimeout(readTimeout);
                    return false;
                }
                this.socket.setSoTimeout(readTimeout);
                return true;
            } catch (SocketTimeoutException e) {
                return true;
            } catch (IOException e2) {
                return false;
            } catch (Throwable th) {
                this.socket.setSoTimeout(readTimeout);
                throw th;
            }
        }
    }

    public void onStream(Http2Stream stream) throws IOException {
        stream.close(ErrorCode.REFUSED_STREAM);
    }

    public void onSettings(Http2Connection connection) {
        synchronized (this.connectionPool) {
            this.allocationLimit = connection.maxConcurrentStreams();
        }
    }

    public Handshake handshake() {
        return this.handshake;
    }

    public boolean isMultiplexed() {
        return this.http2Connection != null;
    }

    public Protocol protocol() {
        return this.protocol;
    }

    public String toString() {
        return "Connection{" + this.route.address().url().host() + ":" + this.route.address().url().port() + ", proxy=" + this.route.proxy() + " hostAddress=" + this.route.socketAddress() + " cipherSuite=" + (this.handshake != null ? this.handshake.cipherSuite() : "none") + " protocol=" + this.protocol + '}';
    }
}
