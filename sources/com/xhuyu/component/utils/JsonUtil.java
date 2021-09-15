package com.xhuyu.component.utils;

import android.text.TextUtils;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
    public static Gson gson = new Gson();

    public static int getInt(JSONObject json, String key, int def) {
        try {
            return json.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return def;
        }
    }

    public static String getString(JSONObject json, String key) {
        try {
            return json.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getJsonObject(JSONObject json, String key) {
        try {
            if (!json.has(key)) {
                return null;
            }
            return json.getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject newJsonObject(String text, JSONObject defvalue) {
        try {
            return new JSONObject(text);
        } catch (JSONException e) {
            e.printStackTrace();
            return defvalue;
        }
    }

    public static JSONObject newJsonObject(Object object, JSONObject defvalue) {
        return object == null ? defvalue : newJsonObject(gson.toJson(object), defvalue);
    }

    public static <T> T parseObject(JSONObject object, Class<T> tClass) {
        if (object == null) {
            return null;
        }
        return parseObject(object.toString(), tClass);
    }

    public static <T> T parseObject(String json, Class<T> tClass) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            return gson.fromJson(json, tClass);
        } catch (Exception e) {
            return null;
        }
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return "";
        }
        return gson.toJson(obj);
    }
}
