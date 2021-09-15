package com.unity3d.player;

import java.util.concurrent.atomic.AtomicReference;

public class GoogleVrApi {

    /* renamed from: a */
    private static AtomicReference f96a = new AtomicReference();

    private GoogleVrApi() {
    }

    /* renamed from: a */
    static void m41a() {
        f96a.set((Object) null);
    }

    /* renamed from: a */
    static void m42a(C0770d dVar) {
        f96a.compareAndSet((Object) null, new GoogleVrProxy(dVar));
    }

    /* renamed from: b */
    static GoogleVrProxy m43b() {
        return (GoogleVrProxy) f96a.get();
    }

    public static GoogleVrVideo getGoogleVrVideo() {
        return (GoogleVrVideo) f96a.get();
    }
}
