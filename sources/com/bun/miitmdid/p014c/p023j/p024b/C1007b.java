package com.bun.miitmdid.p014c.p023j.p024b;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Keep;

@Keep
/* renamed from: com.bun.miitmdid.c.j.b.b */
public class C1007b {
    @Keep

    /* renamed from: a */
    private static Context f769a;
    @Keep

    /* renamed from: b */
    private static boolean f770b;
    @Keep

    /* renamed from: c */
    private static C1007b f771c;
    @Keep

    /* renamed from: d */
    private static C1006a f772d;
    @Keep

    /* renamed from: e */
    private static C1009c f773e;
    @Keep

    /* renamed from: f */
    private static C1009c f774f;
    @Keep

    /* renamed from: g */
    private static C1009c f775g;
    @Keep

    /* renamed from: h */
    private static Object f776h = new Object();
    @Keep

    /* renamed from: i */
    private static HandlerThread f777i;
    @Keep

    /* renamed from: j */
    private static Handler f778j;
    @Keep

    /* renamed from: k */
    private static String f779k;
    @Keep

    /* renamed from: l */
    private static String f780l;
    @Keep

    /* renamed from: m */
    private static String f781m;
    @Keep

    /* renamed from: n */
    private static String f782n;

    @Keep
    /* renamed from: com.bun.miitmdid.c.j.b.b$a */
    static final class C1008a extends Handler {
        @Keep
        C1008a(Looper looper) {
            super(looper);
        }

        @Keep
        public native void handleMessage(Message message);
    }

    @Keep
    private C1007b() {
    }

    @Keep
    /* renamed from: a */
    public static native C1007b m526a(Context context);

    @Keep
    /* renamed from: a */
    public static native String m527a(String str, String str2);

    @Keep
    /* renamed from: a */
    private static native void m528a(Context context, int i, String str);

    @Keep
    /* renamed from: b */
    private native void m529b(int i, String str);

    @Keep
    /* renamed from: c */
    static native /* synthetic */ C1006a m530c();

    @Keep
    /* renamed from: c */
    static native /* synthetic */ String m531c(String str);

    @Keep
    /* renamed from: d */
    static native /* synthetic */ Object m532d();

    @Keep
    /* renamed from: e */
    public static native void m533e();

    @Keep
    /* renamed from: f */
    private static native void m534f();

    @Keep
    /* renamed from: a */
    public native String mo14826a();

    @Keep
    /* renamed from: a */
    public native String mo14827a(String str);

    @Keep
    /* renamed from: a */
    public native void mo14828a(int i, String str);

    @Keep
    /* renamed from: b */
    public native String mo14829b(String str);

    @Keep
    /* renamed from: b */
    public native boolean mo14830b();
}
