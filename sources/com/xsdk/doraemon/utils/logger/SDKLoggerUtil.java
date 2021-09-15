package com.xsdk.doraemon.utils.logger;

public class SDKLoggerUtil {
    public static boolean isDebugEnvironment = false;

    public static LoggerCreateUtil getLogger() {
        return LoggerCreateUtil.getLogger().setTag("");
    }

    public static void setLoggerInfo(boolean isDebug, String loggerInfo) {
        isDebugEnvironment = isDebug;
        LoggerCreateUtil.setVersionInfo(loggerInfo);
    }

    public static boolean isDebugEnvironment() {
        return isDebugEnvironment;
    }
}
