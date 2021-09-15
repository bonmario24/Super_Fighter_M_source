package com.lzy.okgo.utils;

import android.util.Log;

public class OkLogger {
    private static boolean isLogEnable = true;
    private static String tag = "OkGo";

    public static void debug(boolean isEnable) {
        debug(tag, isEnable);
    }

    public static void debug(String logTag, boolean isEnable) {
        tag = logTag;
        isLogEnable = isEnable;
    }

    /* renamed from: v */
    public static void m640v(String msg) {
        m641v(tag, msg);
    }

    /* renamed from: v */
    public static void m641v(String tag2, String msg) {
        if (isLogEnable) {
            Log.v(tag2, msg);
        }
    }

    /* renamed from: d */
    public static void m634d(String msg) {
        m635d(tag, msg);
    }

    /* renamed from: d */
    public static void m635d(String tag2, String msg) {
        if (isLogEnable) {
            Log.d(tag2, msg);
        }
    }

    /* renamed from: i */
    public static void m638i(String msg) {
        m639i(tag, msg);
    }

    /* renamed from: i */
    public static void m639i(String tag2, String msg) {
        if (isLogEnable) {
            Log.i(tag2, msg);
        }
    }

    /* renamed from: w */
    public static void m642w(String msg) {
        m643w(tag, msg);
    }

    /* renamed from: w */
    public static void m643w(String tag2, String msg) {
        if (isLogEnable) {
            Log.w(tag2, msg);
        }
    }

    /* renamed from: e */
    public static void m636e(String msg) {
        m637e(tag, msg);
    }

    /* renamed from: e */
    public static void m637e(String tag2, String msg) {
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
