package com.doraemon.p027eg;

import android.support.annotation.NonNull;

/* renamed from: com.doraemon.eg.SPUtils */
public class SPUtils {
    public static void put(@NonNull String key, String value) {
        getSharePreferences().put(key, value);
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        return getSharePreferences().getString(key, defaultValue);
    }

    public static void put(@NonNull String key, boolean value) {
        getSharePreferences().put(key, value);
    }

    private static boolean getBoolean(@NonNull String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return getSharePreferences().getBoolean(key, defaultValue);
    }

    public static void put(@NonNull String key, int value) {
        getSharePreferences().put(key, value);
    }

    public static int getInt(@NonNull String key) {
        return getInt(key, -1);
    }

    public static int getInt(@NonNull String key, int defaultValue) {
        return getSharePreferences().getInt(key, defaultValue);
    }

    public static void put(@NonNull String key, long value) {
        getSharePreferences().put(key, value);
    }

    public static long getLong(@NonNull String key) {
        return getLong(key, -1);
    }

    public static long getLong(@NonNull String key, long defaultValue) {
        return getSharePreferences().getLong(key, defaultValue);
    }

    private static com.doraemon.util.SPUtils getSharePreferences() {
        return com.doraemon.util.SPUtils.getInstance("EagleMixSD_SP");
    }
}
