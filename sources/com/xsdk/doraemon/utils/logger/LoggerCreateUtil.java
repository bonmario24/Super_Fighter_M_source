package com.xsdk.doraemon.utils.logger;

import com.xsdk.doraemon.utils.CheckUtil;

public class LoggerCreateUtil {

    /* renamed from: D */
    public static final int f895D = 2;

    /* renamed from: E */
    public static final int f896E = 1;

    /* renamed from: I */
    public static final int f897I = 3;
    public static final int JSON = 6;

    /* renamed from: V */
    public static final int f898V = 5;

    /* renamed from: W */
    public static final int f899W = 4;
    private static LoggerCreateUtil instance;
    private static String tag;
    private static String versionInfo;

    public static LoggerCreateUtil getLogger() {
        if (instance == null) {
            synchronized (LoggerCreateUtil.class) {
                if (instance == null) {
                    instance = new LoggerCreateUtil();
                }
            }
        }
        return instance;
    }

    private LoggerCreateUtil() {
        tag = versionInfo;
    }

    public LoggerCreateUtil setTag(String tag2) {
        if (CheckUtil.isEmpty(tag2)) {
            tag = versionInfo;
        } else {
            tag = versionInfo + "/" + tag2;
        }
        return instance;
    }

    public static void setVersionInfo(String verInfo) {
        versionInfo = verInfo;
    }

    private void printLogger(int type, String formatCharacter, Object... formatValue) {
        String newLoggerCharacter = getFormatData(formatCharacter, formatValue);
        switch (type) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                PrintLog.printDefault(type, tag, newLoggerCharacter);
                return;
            case 6:
                JsonLog.printJson(tag, newLoggerCharacter);
                return;
            default:
                return;
        }
    }

    public void printJson(String json) {
        printLogger(6, json, new Object[0]);
    }

    public void printJson(String innerTag, String json) {
        printLogger(6, innerTag, json);
    }

    /* renamed from: v */
    public void mo19508v(String verboseInfo, Object... args) {
        printLogger(5, verboseInfo, args);
    }

    /* renamed from: w */
    public void mo19509w(String warnInfo, Object... args) {
        printLogger(4, warnInfo, args);
    }

    /* renamed from: i */
    public void mo19504i(String info, Object... args) {
        printLogger(3, info, args);
    }

    /* renamed from: d */
    public void mo19501d(String debugInfo, Object... args) {
        printLogger(2, debugInfo, args);
    }

    /* renamed from: e */
    public void mo19502e(String errorInfo, Object... args) {
        printLogger(1, errorInfo, args);
    }

    public String getFormatData(String formatInfo, Object... args) {
        if (args == null || args.length == 0) {
            return formatInfo;
        }
        return String.format(formatInfo, args);
    }
}
