package com.bun.lib;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.support.annotation.Keep;

@Keep
/* renamed from: com.bun.lib.c */
public interface C0973c extends IInterface {

    @Keep
    /* renamed from: com.bun.lib.c$a */
    public static abstract class C0974a extends Binder implements C0973c {

        @Keep
        /* renamed from: com.bun.lib.c$a$a */
        private static class C0975a implements C0973c {
            @Keep

            /* renamed from: a */
            private IBinder f689a;

            @Keep
            C0975a(IBinder iBinder) {
                this.f689a = iBinder;
            }

            @Keep
            public native IBinder asBinder();

            @Keep
            /* renamed from: c */
            public native boolean mo14777c();

            @Keep
            public native String getAAID();

            @Keep
            public native String getOAID();

            @Keep
            public native String getVAID();

            @Keep
            public native boolean isSupported();

            @Keep
            public native void shutDown();
        }

        @Keep
        /* renamed from: a */
        public static native C0973c m436a(IBinder iBinder);
    }

    @Keep
    /* renamed from: c */
    boolean mo14777c();

    @Keep
    String getAAID();

    @Keep
    String getOAID();

    @Keep
    String getVAID();

    @Keep
    boolean isSupported();

    @Keep
    void shutDown();
}
