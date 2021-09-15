package com.star.libtrack.core;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrackConfigManager {
    private static String LOG_TAG = "xsdk";
    private static final String NAME_TRACK = "track.json";
    private static final String TYPE_CLICK = "click";
    private static final String TYPE_KEYBOARD = "keyboard";
    private static final String TYPE_OKHTTP = "okhttp";
    private static final String TYPE_PAGE_ENTER = "page_enter";
    private static final String TYPE_PAGE_EXIT = "page_exit";
    private static Map<String, JSONObject> event_click = new HashMap();
    private static Map<String, JSONObject> event_http = new HashMap();
    private static Map<String, JSONObject> event_keyboard = new HashMap();
    private static Map<String, JSONObject> event_page_enter = new HashMap();
    private static Map<String, JSONObject> event_page_exit = new HashMap();

    public static boolean loadConfig(Context context) {
        if (context == null) {
            debugLog("loadConfig error Context is null");
            return false;
        }
        try {
            JSONObject json = readConfig(context);
            if (json == null) {
                return false;
            }
            readEventConfig(json);
            clearTmp();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean readEventConfig(JSONObject json) {
        try {
            JSONArray events = json.getJSONArray("events");
            if (events.length() == 0) {
                return true;
            }
            List<String> ids_tmp = new ArrayList<>();
            int i = 0;
            while (i < events.length()) {
                JSONObject event = events.getJSONObject(i);
                String type = event.getString("type");
                String id = event.getString("id");
                if (TextUtils.isEmpty(type) || TextUtils.isEmpty(id)) {
                    throw new RuntimeException("track.json配置错误，缺少type/id");
                } else if (ids_tmp.contains(id)) {
                    throw new RuntimeException("配置错误，存在重复id=" + id);
                } else {
                    putEvent(type, id, event);
                    ids_tmp.add(id);
                    i++;
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private static void putEvent(String type, String id, JSONObject event) {
        char c = 65535;
        switch (type.hashCode()) {
            case -1015101340:
                if (type.equals(TYPE_OKHTTP)) {
                    c = 4;
                    break;
                }
                break;
            case 94750088:
                if (type.equals(TYPE_CLICK)) {
                    c = 0;
                    break;
                }
                break;
            case 503739367:
                if (type.equals(TYPE_KEYBOARD)) {
                    c = 1;
                    break;
                }
                break;
            case 883445966:
                if (type.equals(TYPE_PAGE_EXIT)) {
                    c = 3;
                    break;
                }
                break;
            case 1616733480:
                if (type.equals(TYPE_PAGE_ENTER)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                event_click.put(id, event);
                return;
            case 1:
                event_keyboard.put(id, event);
                return;
            case 2:
                event_page_enter.put(id, event);
                return;
            case 3:
                event_page_exit.put(id, event);
                return;
            case 4:
                event_http.put(id, event);
                return;
            default:
                return;
        }
    }

    private static JSONObject readConfig(Context context) {
        if (context == null) {
            return null;
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(NAME_TRACK)));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = reader.readLine();
                if (line != null) {
                    sb.append(line);
                } else {
                    reader.close();
                    return new JSONObject(sb.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject queryClickEvent(String page, String id) {
        JSONObject event = event_click.get(id);
        if (event == null) {
            return event_click.get(page + ":" + id);
        }
        return event;
    }

    public static JSONObject queryPageEvent(String id, boolean isEnter) {
        if (!isEnter) {
            id = id + "_exit";
        }
        return isEnter ? event_page_enter.get(id) : event_page_exit.get(id);
    }

    public static JSONObject queryOkHttpEvent(String id, boolean isUrl) {
        if (!isUrl) {
            return event_http.get(id);
        }
        for (String key : event_http.keySet()) {
            if (key.contains(id)) {
                return event_http.get(key);
            }
        }
        return null;
    }

    public static Map<String, Object> getExtMap(JSONObject obj) {
        try {
            Map<String, Object> result = new HashMap<>();
            result.put("page", obj.getString("page"));
            if (!obj.has("submit")) {
                return result;
            }
            JSONObject submit = obj.getJSONObject("submit");
            Iterator<String> it = submit.keys();
            while (it.hasNext()) {
                String key = it.next();
                result.put(key, submit.get(key));
            }
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void clearTmp() {
        Runtime.getRuntime().gc();
    }

    private static void debugLog(String msg) {
        Log.d(LOG_TAG, msg);
    }
}
