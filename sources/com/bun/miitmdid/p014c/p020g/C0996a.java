package com.bun.miitmdid.p014c.p020g;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Keep;
import com.bun.miitmdid.p014c.p018e.C0994a;
import com.zui.deviceidservice.IDeviceidInterface;

@Keep
/* renamed from: com.bun.miitmdid.c.g.a */
public class C0996a {
    @Keep

    /* renamed from: e */
    private static String f748e = "OpenDeviceId library";
    @Keep

    /* renamed from: f */
    private static boolean f749f;
    @Keep

    /* renamed from: a */
    private Context f750a = null;
    @Keep

    /* renamed from: b */
    private IDeviceidInterface f751b;
    @Keep

    /* renamed from: c */
    private ServiceConnection f752c;
    @Keep

    /* renamed from: d */
    private C0994a f753d;

    @Keep
    /* renamed from: com.bun.miitmdid.c.g.a$a */
    class C0997a implements ServiceConnection {
        @Keep
        C0997a() {
        }

        @Keep
        public native synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder);

        @Keep
        public native void onServiceDisconnected(ComponentName componentName);
    }

    @Keep
    public C0996a(Context context, C0994a aVar) {
        if (context != null) {
            this.f750a = context;
            this.f753d = aVar;
            this.f752c = new C0997a();
            Intent intent = new Intent();
            intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
            if (this.f750a.bindService(intent, this.f752c, 1)) {
                m503b("bindService Successful!");
                return;
            }
            m503b("bindService Failed!");
            C0994a aVar2 = this.f753d;
            if (aVar2 != null) {
                aVar2.mo14812b();
                return;
            }
            return;
        }
        throw new NullPointerException("Context can not be null.");
    }

    @Keep
    /* renamed from: a */
    static native /* synthetic */ C0994a m499a(C0996a aVar);

    @Keep
    /* renamed from: a */
    static native /* synthetic */ IDeviceidInterface m500a(C0996a aVar, IDeviceidInterface iDeviceidInterface);

    @Keep
    /* renamed from: a */
    static native /* synthetic */ void m501a(C0996a aVar, String str);

    @Keep
    /* renamed from: a */
    private native void m502a(String str);

    @Keep
    /* renamed from: b */
    private native void m503b(String str);

    @Keep
    /* renamed from: a */
    public native String mo14813a();

    @Keep
    /* renamed from: b */
    public native String mo14814b();

    @Keep
    /* renamed from: c */
    public native String mo14815c();

    @Keep
    /* renamed from: d */
    public native String mo14816d();

    @Keep
    /* renamed from: e */
    public native boolean mo14817e();

    @Keep
    /* renamed from: f */
    public native void mo14818f();
}
