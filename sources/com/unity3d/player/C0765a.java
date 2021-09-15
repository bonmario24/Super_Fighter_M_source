package com.unity3d.player;

import android.os.Build;
import java.net.InetAddress;
import java.net.Socket;
import java.security.SecureRandom;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* renamed from: com.unity3d.player.a */
public final class C0765a extends SSLSocketFactory {

    /* renamed from: c */
    private static volatile SSLSocketFactory f225c;

    /* renamed from: d */
    private static final Object[] f226d = new Object[0];

    /* renamed from: e */
    private static final boolean f227e;

    /* renamed from: a */
    private final SSLSocketFactory f228a;

    /* renamed from: b */
    private final C0766a f229b = new C0766a();

    /* renamed from: com.unity3d.player.a$a */
    class C0766a implements HandshakeCompletedListener {
        C0766a() {
        }

        public final void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
            SSLSession session = handshakeCompletedEvent.getSession();
            session.getCipherSuite();
            session.getProtocol();
            try {
                session.getPeerPrincipal().getName();
            } catch (SSLPeerUnverifiedException e) {
            }
        }
    }

    static {
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 20) {
            z = true;
        }
        f227e = z;
    }

    private C0765a() {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
        this.f228a = instance.getSocketFactory();
    }

    /* renamed from: a */
    private static Socket m130a(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket) && f227e) {
            ((SSLSocket) socket).setEnabledProtocols(((SSLSocket) socket).getSupportedProtocols());
        }
        return socket;
    }

    /* renamed from: a */
    public static SSLSocketFactory m131a() {
        synchronized (f226d) {
            if (f225c != null) {
                SSLSocketFactory sSLSocketFactory = f225c;
                return sSLSocketFactory;
            }
            try {
                C0765a aVar = new C0765a();
                f225c = aVar;
                return aVar;
            } catch (Exception e) {
                C0771e.Log(5, "CustomSSLSocketFactory: Failed to create SSLSocketFactory (" + e.getMessage() + ")");
                return null;
            }
        }
    }

    public final Socket createSocket() {
        return m130a(this.f228a.createSocket());
    }

    public final Socket createSocket(String str, int i) {
        return m130a(this.f228a.createSocket(str, i));
    }

    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return m130a(this.f228a.createSocket(str, i, inetAddress, i2));
    }

    public final Socket createSocket(InetAddress inetAddress, int i) {
        return m130a(this.f228a.createSocket(inetAddress, i));
    }

    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return m130a(this.f228a.createSocket(inetAddress, i, inetAddress2, i2));
    }

    public final Socket createSocket(Socket socket, String str, int i, boolean z) {
        return m130a(this.f228a.createSocket(socket, str, i, z));
    }

    public final String[] getDefaultCipherSuites() {
        return this.f228a.getDefaultCipherSuites();
    }

    public final String[] getSupportedCipherSuites() {
        return this.f228a.getSupportedCipherSuites();
    }
}
