package com.thinkfly.plugins.coludladder.utils;

public class CommUtils {
    public static long getServiceTimeAndLocalTimeInterval(long j) {
        long currentTimeMillis = j - System.currentTimeMillis();
        if (Math.abs(currentTimeMillis) <= 30000) {
            return 0;
        }
        return currentTimeMillis;
    }

    public static long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
}
