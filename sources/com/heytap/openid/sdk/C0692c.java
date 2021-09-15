package com.heytap.openid.sdk;

import android.content.Context;
import android.content.ServiceConnection;
import android.support.annotation.Keep;
import com.heytap.openid.C0687a;

@Keep
/* renamed from: com.heytap.openid.sdk.c */
public class C0692c {
    @Keep

    /* renamed from: a */
    public C0687a f80a = null;
    @Keep

    /* renamed from: b */
    public String f81b = null;
    @Keep

    /* renamed from: c */
    public String f82c = null;
    @Keep

    /* renamed from: d */
    public final Object f83d = new Object();
    @Keep

    /* renamed from: e */
    public ServiceConnection f84e = new C0691b(this);

    @Keep
    /* renamed from: com.heytap.openid.sdk.c$a */
    private static class C0693a {
        @Keep

        /* renamed from: a */
        public static final C0692c f85a = new C0692c((C0691b) null);
    }

    @Keep
    public /* synthetic */ C0692c(C0691b bVar) {
    }

    @Keep
    /* renamed from: a */
    public native synchronized String mo12187a(Context context, String str);

    @Keep
    /* renamed from: a */
    public native boolean mo12188a(Context context);

    @Keep
    /* renamed from: b */
    public final native String mo12189b(Context context, String str);
}
