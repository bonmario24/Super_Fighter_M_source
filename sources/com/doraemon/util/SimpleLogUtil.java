package com.doraemon.util;

import android.util.Log;

public class SimpleLogUtil {
    public static final int DEBUG = 2;
    public static final int ERROR = 5;
    public static final int INFO = 3;
    public static int LEVEL = 1;
    public static final int NOTHING = 6;
    private static String TAG = "[doraemon]";
    public static final int VERBOSE = 1;
    public static final int WARN = 4;

    /* renamed from: v */
    public static void m585v(String msg) {
        if (LEVEL <= 1) {
            Log.v(TAG, msg);
        }
    }

    /* renamed from: v */
    public static void m586v(String tag, String msg) {
        if (LEVEL <= 1) {
            Log.v(tag, msg);
        }
    }

    /* renamed from: d */
    public static void m579d(String msg) {
        if (LEVEL <= 2) {
            Log.d(TAG, msg);
        }
    }

    /* renamed from: d */
    public static void m580d(String tag, String msg) {
        if (LEVEL <= 1) {
            Log.v(tag, msg);
        }
    }

    /* renamed from: i */
    public static void m583i(String msg) {
        if (LEVEL <= 3) {
            Log.i(TAG, msg);
        }
    }

    /* renamed from: i */
    public static void m584i(String tag, String msg) {
        if (LEVEL <= 1) {
            Log.v(tag, msg);
        }
    }

    /* renamed from: w */
    public static void m587w(String msg) {
        if (LEVEL <= 4) {
            Log.w(TAG, msg);
        }
    }

    /* renamed from: e */
    public static void m581e(String msg) {
        if (LEVEL <= 5) {
            Log.e(TAG, msg);
        }
    }

    /* renamed from: e */
    public static void m582e(String tag, String msg) {
        if (LEVEL <= 1) {
            Log.v(tag, msg);
        }
    }

    public static void setGlobalTag(String TAG2) {
        TAG = TAG2;
    }

    public static void isRelease(boolean isRelease) {
        if (isRelease) {
            LEVEL = 5;
        } else {
            LEVEL = 1;
        }
    }
}
