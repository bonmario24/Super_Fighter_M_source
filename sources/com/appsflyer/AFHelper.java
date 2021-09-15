package com.appsflyer;

import android.os.Build;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AFHelper {
    public static JSONObject convertToJsonObject(Map<String, ?> map) {
        if (Build.VERSION.SDK_INT >= 19) {
            return new JSONObject(map);
        }
        return m209(map);
    }

    /* renamed from: ι */
    private static JSONObject m209(Map<String, ?> map) {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry next : map.entrySet()) {
            try {
                jSONObject.put((String) next.getKey(), m208(next.getValue()));
            } catch (JSONException e) {
            }
        }
        return jSONObject;
    }

    /* renamed from: ı */
    private static Object m208(Object obj) {
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject) || obj.equals(JSONObject.NULL)) {
            return obj;
        }
        try {
            if (obj instanceof Collection) {
                JSONArray jSONArray = new JSONArray();
                for (Object r2 : (Collection) obj) {
                    jSONArray.put(m208(r2));
                }
                return jSONArray;
            } else if (obj.getClass().isArray()) {
                int length = Array.getLength(obj);
                JSONArray jSONArray2 = new JSONArray();
                for (int i = 0; i < length; i++) {
                    jSONArray2.put(m208(Array.get(obj, i)));
                }
                return jSONArray2;
            } else if (obj instanceof Map) {
                return m209((Map) obj);
            } else {
                if ((obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Short) || (obj instanceof String)) {
                    return obj;
                }
                return obj.toString();
            }
        } catch (Exception e) {
            return JSONObject.NULL;
        }
    }
}
