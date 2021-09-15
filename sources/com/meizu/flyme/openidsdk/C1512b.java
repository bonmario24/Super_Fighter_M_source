package com.meizu.flyme.openidsdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Keep;

@Keep
/* renamed from: com.meizu.flyme.openidsdk.b */
public class C1512b {
    @Keep

    /* renamed from: g */
    public static volatile C1512b f856g;
    @Keep

    /* renamed from: h */
    public static boolean f857h;
    @Keep

    /* renamed from: a */
    public OpenId f858a = new OpenId("udid");
    @Keep

    /* renamed from: b */
    public OpenId f859b = new OpenId("oaid");
    @Keep

    /* renamed from: c */
    public OpenId f860c = new OpenId("aaid");
    @Keep

    /* renamed from: d */
    public OpenId f861d = new OpenId("vaid");
    @Keep

    /* renamed from: e */
    public Boolean f862e;
    @Keep

    /* renamed from: f */
    public BroadcastReceiver f863f;

    @Keep
    /* renamed from: a */
    public static native ValueData m654a(Cursor cursor);

    @Keep
    /* renamed from: a */
    public static final native C1512b m655a();

    @Keep
    /* renamed from: b */
    public static native void m656b(String str);

    @Keep
    /* renamed from: a */
    public native OpenId mo17991a(String str);

    @Keep
    /* renamed from: a */
    public final native String mo17992a(Context context, OpenId openId);

    @Keep
    /* renamed from: a */
    public final native synchronized void mo17993a(Context context);

    @Keep
    /* renamed from: a */
    public final native boolean mo17994a(Context context, boolean z);
}
