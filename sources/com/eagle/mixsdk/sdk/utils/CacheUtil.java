package com.eagle.mixsdk.sdk.utils;

import android.content.SharedPreferences;
import com.eagle.mixsdk.sdk.EagleSDK;

public class CacheUtil {
    private static String SP_NAME = "eagle_info";
    public static CacheUtil sInstance;
    private SharedPreferences mSharedPreferences = EagleSDK.getInstance().getApplication().getSharedPreferences(SP_NAME, 0);

    private CacheUtil() {
    }

    public static CacheUtil getInstance() {
        if (sInstance == null) {
            synchronized (CacheUtil.class) {
                if (sInstance == null) {
                    sInstance = new CacheUtil();
                }
            }
        }
        return sInstance;
    }

    public void setIntValue(String key, int value) {
        SharedPreferences.Editor editor = this.mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getIntValue(String key, int defaultValue) {
        return this.mSharedPreferences.getInt(key, defaultValue);
    }

    public void setStringValue(String key, String value) {
        SharedPreferences.Editor editor = this.mSharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getStringValue(String key, String defaultValue) {
        return this.mSharedPreferences.getString(key, defaultValue);
    }

    public void setLongValue(String key, long value) {
        SharedPreferences.Editor editor = this.mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public long getLongValue(String key, long defaultValue) {
        return this.mSharedPreferences.getLong(key, defaultValue);
    }
}
