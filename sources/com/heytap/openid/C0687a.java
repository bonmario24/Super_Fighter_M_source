package com.heytap.openid;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.support.annotation.Keep;

@Keep
/* renamed from: com.heytap.openid.a */
public interface C0687a extends IInterface {

    @Keep
    /* renamed from: com.heytap.openid.a$a */
    public static abstract class C0688a extends Binder implements C0687a {

        @Keep
        /* renamed from: com.heytap.openid.a$a$a */
        private static class C0689a implements C0687a {
            @Keep

            /* renamed from: a */
            public IBinder f76a;

            @Keep
            public C0689a(IBinder iBinder) {
                this.f76a = iBinder;
            }

            @Keep
            /* renamed from: a */
            public native String mo12183a(String str, String str2, String str3);

            @Keep
            public native IBinder asBinder();
        }

        @Keep
        /* renamed from: a */
        public static native C0687a m23a(IBinder iBinder);
    }
}
