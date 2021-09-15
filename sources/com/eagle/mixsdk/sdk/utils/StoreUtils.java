package com.eagle.mixsdk.sdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.doraemon.util.SPUtils;
import com.eagle.mixsdk.sdk.EagleSDK;

public class StoreUtils {
    private static SPUtils mSharePreferences;

    /* renamed from: sp */
    private static SharedPreferences f827sp;

    @Deprecated
    public static SharedPreferences getSharedPreferences(Context context) {
        if (f827sp == null) {
            f827sp = context.getSharedPreferences(context.getPackageName() + "_preferences", 0);
        }
        return f827sp;
    }

    public static SPUtils getSharedPreferences() {
        if (mSharePreferences == null) {
            mSharePreferences = SPUtils.getInstance(EagleSDK.getInstance().getApplication().getPackageName() + "_preferences");
        }
        return mSharePreferences;
    }

    public static int getInt(Context context, String key, int defaultVal) {
        return getSharedPreferences().getInt(key, 0);
    }

    @Deprecated
    public static void putInt(Context context, String key, int value) {
        putInt(key, value);
    }

    public static void putInt(String key, int value) {
        if (!TextUtils.isEmpty(key)) {
            getSharedPreferences().put(key, value);
        }
    }

    @Deprecated
    public static boolean getBoolean(Context context, String key, boolean defaultVal) {
        return getBoolean(key, defaultVal);
    }

    public static boolean getBoolean(String key, boolean defaultVal) {
        return getSharedPreferences().getBoolean(key, defaultVal);
    }

    @Deprecated
    public static void putBoolean(Context context, String key, boolean value) {
        putBoolean(key, value);
    }

    public static void putBoolean(String key, boolean value) {
        if (!TextUtils.isEmpty(key)) {
            getSharedPreferences().put(key, value);
        }
    }

    @Deprecated
    public static String getString(Context context, String key) {
        return getString(key);
    }

    public static String getString(String key) {
        return getSharedPreferences().getString(key, "");
    }

    @Deprecated
    public static void putString(Context context, String key, String value) {
        putString(key, value);
    }

    public static void putString(String key, String value) {
        if (!TextUtils.isEmpty(key)) {
            getSharedPreferences().put(key, value);
        }
    }
}
