package com.eagle.mixsdk.sdk.okhttp.logger;

import android.util.Log;

public class OkLogger {
    private static boolean isLogEnable = true;
    private static String tag = "EagleOkHttp";

    public static void debug(boolean isEnable) {
        debug(tag, isEnable);
    }

    public static void debug(String logTag, boolean isEnable) {
        tag = logTag;
        isLogEnable = isEnable;
    }

    /* renamed from: v */
    public static void m622v(String msg) {
        m623v(tag, msg);
    }

    /* renamed from: v */
    public static void m623v(String tag2, String msg) {
        if (isLogEnable) {
            Log.v(tag2, msg);
        }
    }

    /* renamed from: d */
    public static void m616d(String msg) {
        m617d(tag, msg);
    }

    /* renamed from: d */
    public static void m617d(String tag2, String msg) {
        if (isLogEnable) {
            Log.d(tag2, msg);
        }
    }

    /* renamed from: i */
    public static void m620i(String msg) {
        m621i(tag, msg);
    }

    /* renamed from: i */
    public static void m621i(String tag2, String msg) {
        if (isLogEnable) {
            Log.i(tag2, msg);
        }
    }

    /* renamed from: w */
    public static void m624w(String msg) {
        m625w(tag, msg);
    }

    /* renamed from: w */
    public static void m625w(String tag2, String msg) {
        if (isLogEnable) {
            Log.w(tag2, msg);
        }
    }

    /* renamed from: e */
    public static void m618e(String msg) {
        m619e(tag, msg);
    }

    /* renamed from: e */
    public static void m619e(String tag2, String msg) {
        if (isLogEnable) {
            Log.e(tag2, msg);
        }
    }

    public static void printStackTrace(Throwable t) {
        if (isLogEnable && t != null) {
            t.printStackTrace();
        }
    }
}
