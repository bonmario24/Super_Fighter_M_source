package com.thinkfly.plugins.coludladder.log;

import com.doraemon.util.SimpleLogUtil;

public class Log {
    public static String TAG = "CloudLadder";
    private static boolean isDebug = false;

    /* renamed from: d */
    public static void m662d(String tag, String msg) {
        if (isDebug) {
            SimpleLogUtil.m580d(tag, msg);
        }
    }

    /* renamed from: i */
    public static void m664i(String tag, String msg) {
        if (isDebug) {
            SimpleLogUtil.m584i(tag, msg);
        }
    }

    /* renamed from: w */
    public static void m665w(String tag, String msg) {
        if (isDebug) {
            SimpleLogUtil.m580d(tag, msg);
        }
    }

    /* renamed from: e */
    public static void m663e(String tag, String msg) {
        if (isDebug) {
            SimpleLogUtil.m582e(tag, msg);
        }
    }

    public static void init(boolean d) {
        isDebug = d;
        if (!isDebug) {
            isDebug = android.util.Log.isLoggable(TAG, 3);
        }
        System.out.println(TAG + " " + isDebug);
    }
}
