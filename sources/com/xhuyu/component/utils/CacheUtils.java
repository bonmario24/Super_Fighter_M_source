package com.xhuyu.component.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

public class CacheUtils {
    public static final int VALUE_INT_DEFAULT = -1;
    public static final long VALUE_LONG_DEFAULT = -1;
    private static Context mContext;
    private static String sAppCachePath = "SDKConfig";

    public static void init(Context context) {
        if (mContext == null) {
            mContext = context;
        }
    }

    public static String getCacheString(String name) {
        return getCacheString(sAppCachePath, name);
    }

    public static String getCacheString(String cacheName, String name) {
        if (TextUtils.isEmpty(cacheName)) {
            return "";
        }
        return mContext.getSharedPreferences(cacheName, 0).getString(name, "");
    }

    public static void putCacheString(String name, String value) {
        putCacheString(sAppCachePath, name, value);
    }

    public static void putCacheString(String cacheName, String name, String value) {
        if (!TextUtils.isEmpty(cacheName)) {
            SharedPreferences.Editor editor = mContext.getSharedPreferences(cacheName, 0).edit();
            editor.putString(name, value);
            editor.commit();
        }
    }

    public static int getCacheInteger(String name) {
        return getCacheInteger(name, -1);
    }

    public static int getCacheInteger(String name, int def) {
        return mContext == null ? def : mContext.getSharedPreferences(sAppCachePath, 0).getInt(name, def);
    }

    public static void putCacheInteger(String name, int value) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(sAppCachePath, 0).edit();
        editor.putInt(name, value);
        editor.apply();
    }

    public static int getCacheInteger(String cacheName, String name, int def) {
        return mContext.getSharedPreferences(cacheName, 0).getInt(name, def);
    }

    public static void putCacheInteger(String cacheName, String name, int value) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(cacheName, 0).edit();
        editor.putInt(name, value);
        editor.apply();
    }

    public static long getCacheLong(String name) {
        return mContext.getSharedPreferences(sAppCachePath, 0).getLong(name, -1);
    }

    public static long getCacheLong(String cacheName, String name) {
        return mContext.getSharedPreferences(cacheName, 0).getLong(name, -1);
    }

    public static void putCacheBoolean(String name, boolean value) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(sAppCachePath, 0).edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    public static boolean getCacheBoolean(String name, boolean defaultValue) {
        return mContext.getSharedPreferences(sAppCachePath, 0).getBoolean(name, defaultValue);
    }

    public static void putCacheBoolean(String cacheName, String name, boolean value) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(cacheName, 0).edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    public static boolean getCacheBoolean(String cacheName, String name, boolean defaultValue) {
        return mContext.getSharedPreferences(cacheName, 0).getBoolean(name, defaultValue);
    }

    public static void putCacheLong(String name, long value) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(sAppCachePath, 0).edit();
        editor.putLong(name, value);
        editor.apply();
    }

    public static void putCacheLong(String cacheName, String name, long value) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(cacheName, 0).edit();
        editor.putLong(name, value);
        editor.apply();
    }

    public static void putCacheArrayList(Context ctx, String key, ArrayList<String> list) {
        putCacheArrayList(ctx.getApplicationContext(), sAppCachePath, key, list);
    }

    public static void putCacheArrayList(Context ctx, String cacheName, String key, ArrayList<String> list) {
        if (!TextUtils.isEmpty(cacheName)) {
            putCacheString(cacheName, key, new JSONArray(list).toString());
        }
    }

    public static ArrayList<String> getCacheArrayList(Context ctx, String key) {
        return getCacheArrayList(ctx.getApplicationContext(), sAppCachePath, key);
    }

    public static ArrayList<String> getCacheArrayList(Context ctx, String cacheName, String key) {
        ArrayList<String> ret = new ArrayList<>();
        if (!TextUtils.isEmpty(cacheName)) {
            String listStr = getCacheString(cacheName, key);
            if (TextUtils.isEmpty(listStr)) {
                listStr = "{}";
            }
            try {
                JSONArray listJson = new JSONArray(listStr);
                if (listJson != null) {
                    for (int i = 0; i < listJson.length(); i++) {
                        ret.add(listJson.optString(i));
                    }
                }
            } catch (Exception e) {
            }
        }
        return ret;
    }

    public static void putCacheLinkedList(String key, LinkedList<String> list) {
        putCacheString(key, new JSONArray(list).toString());
    }

    public static LinkedList<String> getCacheLinkedList(String key) {
        LinkedList<String> ret = new LinkedList<>();
        String listStr = getCacheString(key);
        if (TextUtils.isEmpty(listStr)) {
            listStr = "{}";
        }
        try {
            JSONArray listJson = new JSONArray(listStr);
            if (listJson != null) {
                for (int i = 0; i < listJson.length(); i++) {
                    ret.add(listJson.optString(i));
                }
            }
        } catch (Exception e) {
        }
        return ret;
    }

    public static void putCacheHashMap(String key, HashMap<String, String> map) {
        putCacheString(key, new JSONObject(map).toString());
    }

    public static HashMap<String, String> getCacheHashMap(String key) {
        return getCacheHashMapByKey(key);
    }

    public static HashMap<String, String> getCacheHashMapByKey(String key) {
        HashMap<String, String> ret = new HashMap<>();
        String mapStr = getCacheString(key);
        if (TextUtils.isEmpty(mapStr)) {
            mapStr = "{}";
        }
        try {
            JSONObject mapJson = new JSONObject(mapStr);
            if (mapJson != null) {
                Iterator<String> it = mapJson.keys();
                while (it.hasNext()) {
                    String theKey = it.next();
                    ret.put(theKey, mapJson.optString(theKey));
                }
            }
        } catch (Exception e) {
        }
        return ret;
    }

    public static void removeByKey(String key) {
        removeByKey(mContext.getApplicationContext(), sAppCachePath, key);
    }

    public static void removeByKey(Context ctx, String cacheName, String key) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(cacheName, 0).edit();
        editor.remove(key);
        editor.apply();
    }

    public static void clearCacheByName(String cacheName) {
        if (!TextUtils.isEmpty(cacheName)) {
            SharedPreferences.Editor editor = mContext.getSharedPreferences(cacheName, 0).edit();
            editor.clear();
            editor.apply();
        }
    }
}
