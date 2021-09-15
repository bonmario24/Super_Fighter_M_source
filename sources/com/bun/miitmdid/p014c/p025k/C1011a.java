package com.bun.miitmdid.p014c.p025k;

import android.content.Context;
import android.support.annotation.Keep;
import com.bun.lib.C0971a;
import java.lang.reflect.Method;

@Keep
/* renamed from: com.bun.miitmdid.c.k.a */
public class C1011a {
    @Keep

    /* renamed from: a */
    private static Object f786a;
    @Keep

    /* renamed from: b */
    private static Class<?> f787b;
    @Keep

    /* renamed from: c */
    private static Method f788c;
    @Keep

    /* renamed from: d */
    private static Method f789d;
    @Keep

    /* renamed from: e */
    private static Method f790e;

    static {
        try {
            f787b = Class.forName("com.android.id.impl.IdProviderImpl");
            f786a = f787b.newInstance();
        } catch (Exception e) {
            C0971a.m426a("IdentifierManager", "reflect exception!", e);
        }
        try {
            if (f787b != null) {
                f788c = f787b.getMethod("getOAID", new Class[]{Context.class});
            }
        } catch (Exception e2) {
            C0971a.m426a("IdentifierManager", "reflect exception!", e2);
        }
        try {
            if (f787b != null) {
                f789d = f787b.getMethod("getVAID", new Class[]{Context.class});
            }
        } catch (Exception e3) {
            C0971a.m426a("IdentifierManager", "reflect exception!", e3);
        }
        try {
            if (f787b != null) {
                f790e = f787b.getMethod("getAAID", new Class[]{Context.class});
            }
        } catch (Exception e4) {
            C0971a.m426a("IdentifierManager", "reflect exception!", e4);
        }
    }

    @Keep
    /* renamed from: a */
    public static native String m544a(Context context);

    @Keep
    /* renamed from: a */
    private static native String m545a(Context context, Method method);

    @Keep
    /* renamed from: a */
    public static native boolean m546a();

    @Keep
    /* renamed from: b */
    public static native String m547b(Context context);

    @Keep
    /* renamed from: c */
    public static native String m548c(Context context);
}
