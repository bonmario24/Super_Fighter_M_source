package com.xsdk.doraemon.utils.logger;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonLog {
    public static final int JSON_INDENT = 4;
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    JsonLog() {
    }

    public static void printJson(String tag, String msg) {
        String message;
        try {
            if (msg.startsWith("{")) {
                message = new JSONObject(msg).toString(4);
            } else if (msg.startsWith("[")) {
                message = new JSONArray(msg).toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }
        printLine(tag, true);
        PrintLog.printDefault(1, tag, LINE_SEPARATOR + message);
        printLine(tag, false);
    }

    private static void printLine(String tag, boolean isTop) {
        if (!SDKLoggerUtil.isDebugEnvironment()) {
            return;
        }
        if (isTop) {
            Log.e(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.e(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }
}
