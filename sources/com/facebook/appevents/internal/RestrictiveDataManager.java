package com.facebook.appevents.internal;

import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.Log;
import com.facebook.appevents.AppEvent;
import com.facebook.internal.Utility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class RestrictiveDataManager {
    private static final String TAG = RestrictiveDataManager.class.getCanonicalName();
    private static boolean enabled = false;
    private static Set<String> restrictiveEvents = new HashSet();
    private static List<RestrictiveParam> restrictiveParams = new ArrayList();

    public static void enable() {
        enabled = true;
    }

    public static synchronized void updateFromSetting(String eventFilterResponse) {
        synchronized (RestrictiveDataManager.class) {
            if (enabled) {
                try {
                    if (!eventFilterResponse.isEmpty()) {
                        JSONObject jsonObject = new JSONObject(eventFilterResponse);
                        restrictiveParams.clear();
                        restrictiveEvents.clear();
                        Iterator<String> keys = jsonObject.keys();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            JSONObject json = jsonObject.getJSONObject(key);
                            if (json != null) {
                                if (json.optBoolean("is_deprecated_event")) {
                                    restrictiveEvents.add(key);
                                } else {
                                    JSONObject paramJson = jsonObject.getJSONObject(key).optJSONObject("restrictive_param");
                                    if (paramJson != null) {
                                        restrictiveParams.add(new RestrictiveParam(key, Utility.convertJSONObjectToStringMap(paramJson)));
                                    }
                                }
                            }
                        }
                    }
                } catch (JSONException je) {
                    Log.w(TAG, "updateRulesFromSetting failed", je);
                } catch (Exception e) {
                    Log.w(TAG, "updateFromSetting failed", e);
                }
            }
        }
        return;
    }

    public static void processEvents(List<AppEvent> events) {
        if (enabled) {
            Iterator<AppEvent> iterator = events.iterator();
            while (iterator.hasNext()) {
                if (isDeprecatedEvent(iterator.next().getName())) {
                    iterator.remove();
                }
            }
        }
    }

    public static void processParameters(Map<String, String> parameters, String eventName) {
        if (enabled) {
            Map<String, String> restrictedParams = new HashMap<>();
            Iterator<String> it = new ArrayList<>(parameters.keySet()).iterator();
            while (it.hasNext()) {
                String key = it.next();
                String type = getMatchedRuleType(eventName, key);
                if (type != null) {
                    restrictedParams.put(key, type);
                    parameters.remove(key);
                }
            }
            if (restrictedParams.size() > 0) {
                try {
                    JSONObject restrictedJSON = new JSONObject();
                    for (Map.Entry<String, String> entry : restrictedParams.entrySet()) {
                        restrictedJSON.put(entry.getKey(), entry.getValue());
                    }
                    parameters.put("_restrictedParams", restrictedJSON.toString());
                } catch (JSONException e) {
                    Log.w(TAG, "processParameters failed", e);
                }
            }
        }
    }

    private static boolean isDeprecatedEvent(String eventName) {
        return restrictiveEvents.contains(eventName);
    }

    @Nullable
    private static String getMatchedRuleType(String eventName, String paramKey) {
        try {
            Iterator<RestrictiveParam> it = new ArrayList<>(restrictiveParams).iterator();
            while (it.hasNext()) {
                RestrictiveParam filter = it.next();
                if (filter != null && eventName.equals(filter.eventName)) {
                    for (String param : filter.params.keySet()) {
                        if (paramKey.equals(param)) {
                            return filter.params.get(param);
                        }
                    }
                    continue;
                }
            }
        } catch (Exception e) {
            Log.w(TAG, "getMatchedRuleType failed", e);
        }
        return null;
    }

    static class RestrictiveParam {
        String eventName;
        Map<String, String> params;

        RestrictiveParam(String eventName2, Map<String, String> params2) {
            this.eventName = eventName2;
            this.params = params2;
        }
    }
}
