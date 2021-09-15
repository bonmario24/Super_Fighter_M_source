package com.xsdk.doraemon.okhttp.logger;

import android.util.Log;

public class OkLogger {
    private static boolean isLogEnable = true;
    private static String tag = "ThinkFlyOkHttp";

    public static void debug(boolean isEnable) {
        debug(tag, isEnable);
    }

    public static void debug(String logTag, boolean isEnable) {
        tag = logTag;
        isLogEnable = isEnable;
    }

    /* renamed from: v */
    public static void m672v(String msg) {
        m673v(tag, msg);
    }

    /* renamed from: v */
    public static void m673v(String tag2, String msg) {
        if (isLogEnable) {
            Log.v(tag2, msg);
        }
    }

    /* renamed from: d */
    public static void m666d(String msg) {
        m667d(tag, msg);
    }

    /* renamed from: d */
    public static void m667d(String tag2, String msg) {
        if (isLogEnable) {
            Log.d(tag2, msg);
        }
    }

    /* renamed from: i */
    public static void m670i(String msg) {
        m671i(tag, msg);
    }

    /* renamed from: i */
    public static void m671i(String tag2, String msg) {
        if (isLogEnable) {
            Log.i(tag2, msg);
        }
    }

    /* renamed from: w */
    public static void m674w(String msg) {
        m675w(tag, msg);
    }

    /* renamed from: w */
    public static void m675w(String tag2, String msg) {
        if (isLogEnable) {
            Log.w(tag2, msg);
        }
    }

    /* renamed from: e */
    public static void m668e(String msg) {
        m669e(tag, msg);
    }

    /* renamed from: e */
    public static void m669e(String tag2, String msg) {
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
