package com.doraemon.p027eg;

import android.util.Log;

/* renamed from: com.doraemon.eg.SimpleLogUtil */
public class SimpleLogUtil {
    public static final int DEBUG = 2;
    public static final int ERROR = 5;
    public static final int INFO = 3;
    public static int LEVEL = 1;
    public static final int NOTHING = 6;
    private static String TAG = "xsdk";
    public static final int VERBOSE = 1;
    public static final int WARN = 4;

    /* renamed from: d */
    public static void m570d(String str) {
        if (LEVEL <= 2) {
            Log.d(TAG, str);
        }
    }

    /* renamed from: d */
    public static void m571d(String str, String str2) {
        if (LEVEL <= 1) {
            Log.v(str, str2);
        }
    }

    /* renamed from: e */
    public static void m572e(String str) {
        if (LEVEL <= 5) {
            Log.e(TAG, str);
        }
    }

    /* renamed from: e */
    public static void m573e(String str, String str2) {
        if (LEVEL <= 1) {
            Log.v(str, str2);
        }
    }

    /* renamed from: i */
    public static void m574i(String str) {
        if (LEVEL <= 3) {
            Log.i(TAG, str);
        }
    }

    /* renamed from: i */
    public static void m575i(String str, String str2) {
        if (LEVEL <= 1) {
            Log.v(str, str2);
        }
    }

    public static void isRelease(boolean z) {
        if (z) {
            LEVEL = 5;
        } else {
            LEVEL = 1;
        }
    }

    public static void setGlobalTag(String str) {
        TAG = str;
    }

    /* renamed from: v */
    public static void m576v(String str) {
        if (LEVEL <= 1) {
            Log.v(TAG, str);
        }
    }

    /* renamed from: v */
    public static void m577v(String str, String str2) {
        if (LEVEL <= 1) {
            Log.v(str, str2);
        }
    }

    /* renamed from: w */
    public static void m578w(String str) {
        if (LEVEL <= 4) {
            Log.w(TAG, str);
        }
    }
}
