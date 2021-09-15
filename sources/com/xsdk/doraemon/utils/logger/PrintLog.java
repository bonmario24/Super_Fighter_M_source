package com.xsdk.doraemon.utils.logger;

import android.util.Log;

class PrintLog {
    PrintLog() {
    }

    public static void printDefault(int type, String tag, String msg) {
        printSub(type, tag, msg);
    }

    private static void printSub(int type, String tag, String sub) {
        switch (type) {
            case 1:
                if (SDKLoggerUtil.isDebugEnvironment()) {
                    Log.e(tag, sub);
                    return;
                }
                return;
            case 2:
                if (SDKLoggerUtil.isDebugEnvironment()) {
                    Log.d(tag, sub);
                    return;
                }
                return;
            case 3:
                if (SDKLoggerUtil.isDebugEnvironment()) {
                    Log.i(tag, sub);
                    return;
                }
                return;
            case 4:
                if (SDKLoggerUtil.isDebugEnvironment()) {
                    Log.w(tag, sub);
                    return;
                }
                return;
            case 5:
                if (SDKLoggerUtil.isDebugEnvironment()) {
                    Log.v(tag, sub);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
