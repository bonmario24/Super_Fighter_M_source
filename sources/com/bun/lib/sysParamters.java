package com.bun.lib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.support.annotation.Keep;

@Keep
public class sysParamters {
    @Keep

    /* renamed from: d */
    private static volatile sysParamters f690d;
    @Keep

    /* renamed from: a */
    private String f691a;
    @Keep

    /* renamed from: b */
    private String f692b;
    @Keep

    /* renamed from: c */
    private String f693c;
    @Keep
    private String sdk_version = "10012";
    @Keep
    private String sdk_vname = "1.0.12";

    @Keep
    private sysParamters() {
    }

    @Keep
    /* renamed from: a */
    private static native PackageInfo m438a(Context context, String str);

    @Keep
    /* renamed from: a */
    public static native String m439a(Context context);

    @Keep
    /* renamed from: a */
    public static native String m440a(String str, String str2);

    @Keep
    /* renamed from: e */
    public static native String m441e();

    @Keep
    /* renamed from: f */
    public static native sysParamters m442f();

    @Keep
    /* renamed from: g */
    public static native String m443g();

    @Keep
    /* renamed from: h */
    private static native String m444h();

    @Keep
    /* renamed from: a */
    public native String mo14784a();

    @Keep
    /* renamed from: b */
    public native String mo14785b();

    @Keep
    /* renamed from: c */
    public native String mo14786c();

    @Keep
    /* renamed from: d */
    public native String mo14787d();
}
