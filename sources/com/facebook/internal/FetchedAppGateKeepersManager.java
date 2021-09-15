package com.facebook.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.lzy.okgo.cache.CacheEntity;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class FetchedAppGateKeepersManager {
    private static final String APPLICATION_FIELDS = "fields";
    private static final long APPLICATION_GATEKEEPER_CACHE_TIMEOUT = 3600000;
    private static final String APPLICATION_GATEKEEPER_EDGE = "mobile_sdk_gk";
    private static final String APPLICATION_GATEKEEPER_FIELD = "gatekeepers";
    private static final String APPLICATION_GRAPH_DATA = "data";
    private static final String APPLICATION_PLATFORM = "platform";
    private static final String APPLICATION_SDK_VERSION = "sdk_version";
    private static final String APP_GATEKEEPERS_PREFS_KEY_FORMAT = "com.facebook.internal.APP_GATEKEEPERS.%s";
    private static final String APP_GATEKEEPERS_PREFS_STORE = "com.facebook.internal.preferences.APP_GATEKEEPERS";
    private static final String APP_PLATFORM = "android";
    private static final String TAG = FetchedAppGateKeepersManager.class.getCanonicalName();
    private static final ConcurrentLinkedQueue<Callback> callbacks = new ConcurrentLinkedQueue<>();
    private static final Map<String, JSONObject> fetchedAppGateKeepers = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public static final AtomicBoolean isLoading = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    @Nullable
    public static Long timestamp;

    public interface Callback {
        void onCompleted();
    }

    static void loadAppGateKeepersAsync() {
        loadAppGateKeepersAsync((Callback) null);
    }

    static synchronized void loadAppGateKeepersAsync(@Nullable Callback callback) {
        synchronized (FetchedAppGateKeepersManager.class) {
            if (callback != null) {
                callbacks.add(callback);
            }
            if (isTimestampValid(timestamp)) {
                pollCallbacks();
            } else {
                final Context context = FacebookSdk.getApplicationContext();
                final String applicationId = FacebookSdk.getApplicationId();
                final String gateKeepersKey = String.format(APP_GATEKEEPERS_PREFS_KEY_FORMAT, new Object[]{applicationId});
                if (context != null) {
                    String gateKeepersJSONString = context.getSharedPreferences(APP_GATEKEEPERS_PREFS_STORE, 0).getString(gateKeepersKey, (String) null);
                    if (!Utility.isNullOrEmpty(gateKeepersJSONString)) {
                        JSONObject gateKeepersJSON = null;
                        try {
                            gateKeepersJSON = new JSONObject(gateKeepersJSONString);
                        } catch (JSONException je) {
                            Utility.logd("FacebookSDK", (Exception) je);
                        }
                        if (gateKeepersJSON != null) {
                            parseAppGateKeepersFromJSON(applicationId, gateKeepersJSON);
                        }
                    }
                    Executor executor = FacebookSdk.getExecutor();
                    if (executor != null && isLoading.compareAndSet(false, true)) {
                        executor.execute(new Runnable() {
                            public void run() {
                                JSONObject gateKeepersResultJSON = FetchedAppGateKeepersManager.getAppGateKeepersQueryResponse(applicationId);
                                if (gateKeepersResultJSON != null) {
                                    JSONObject unused = FetchedAppGateKeepersManager.parseAppGateKeepersFromJSON(applicationId, gateKeepersResultJSON);
                                    context.getSharedPreferences(FetchedAppGateKeepersManager.APP_GATEKEEPERS_PREFS_STORE, 0).edit().putString(gateKeepersKey, gateKeepersResultJSON.toString()).apply();
                                    Long unused2 = FetchedAppGateKeepersManager.timestamp = Long.valueOf(System.currentTimeMillis());
                                }
                                FetchedAppGateKeepersManager.pollCallbacks();
                                FetchedAppGateKeepersManager.isLoading.set(false);
                            }
                        });
                    }
                }
            }
        }
        return;
    }

    /* access modifiers changed from: private */
    public static void pollCallbacks() {
        Handler handler = new Handler(Looper.getMainLooper());
        while (!callbacks.isEmpty()) {
            final Callback callback = callbacks.poll();
            if (callback != null) {
                handler.post(new Runnable() {
                    public void run() {
                        callback.onCompleted();
                    }
                });
            }
        }
    }

    @Nullable
    static JSONObject queryAppGateKeepers(String applicationId, boolean forceRequery) {
        if (!forceRequery && fetchedAppGateKeepers.containsKey(applicationId)) {
            return fetchedAppGateKeepers.get(applicationId);
        }
        JSONObject response = getAppGateKeepersQueryResponse(applicationId);
        if (response == null) {
            return null;
        }
        Context context = FacebookSdk.getApplicationContext();
        context.getSharedPreferences(APP_GATEKEEPERS_PREFS_STORE, 0).edit().putString(String.format(APP_GATEKEEPERS_PREFS_KEY_FORMAT, new Object[]{applicationId}), response.toString()).apply();
        return parseAppGateKeepersFromJSON(applicationId, response);
    }

    public static boolean getGateKeeperForKey(String name, String applicationId, boolean defaultValue) {
        loadAppGateKeepersAsync();
        return (applicationId == null || !fetchedAppGateKeepers.containsKey(applicationId)) ? defaultValue : fetchedAppGateKeepers.get(applicationId).optBoolean(name, defaultValue);
    }

    /* access modifiers changed from: private */
    @Nullable
    public static JSONObject getAppGateKeepersQueryResponse(String applicationId) {
        Bundle appGateKeepersParams = new Bundle();
        appGateKeepersParams.putString(APPLICATION_PLATFORM, "android");
        appGateKeepersParams.putString(APPLICATION_SDK_VERSION, FacebookSdk.getSdkVersion());
        appGateKeepersParams.putString("fields", APPLICATION_GATEKEEPER_FIELD);
        GraphRequest request = GraphRequest.newGraphPathRequest((AccessToken) null, String.format("%s/%s", new Object[]{applicationId, APPLICATION_GATEKEEPER_EDGE}), (GraphRequest.Callback) null);
        request.setSkipClientToken(true);
        request.setParameters(appGateKeepersParams);
        return request.executeAndWait().getJSONObject();
    }

    /* access modifiers changed from: private */
    public static synchronized JSONObject parseAppGateKeepersFromJSON(String applicationId, JSONObject gateKeepersJSON) {
        JSONObject result;
        synchronized (FetchedAppGateKeepersManager.class) {
            if (fetchedAppGateKeepers.containsKey(applicationId)) {
                result = fetchedAppGateKeepers.get(applicationId);
            } else {
                result = new JSONObject();
            }
            JSONArray arr = gateKeepersJSON.optJSONArray("data");
            JSONObject gateKeepers = null;
            if (arr != null) {
                gateKeepers = arr.optJSONObject(0);
            }
            if (!(gateKeepers == null || gateKeepers.optJSONArray(APPLICATION_GATEKEEPER_FIELD) == null)) {
                JSONArray data = gateKeepers.optJSONArray(APPLICATION_GATEKEEPER_FIELD);
                for (int i = 0; i < data.length(); i++) {
                    try {
                        JSONObject gk = data.getJSONObject(i);
                        result.put(gk.getString(CacheEntity.KEY), gk.getBoolean("value"));
                    } catch (JSONException je) {
                        Utility.logd("FacebookSDK", (Exception) je);
                    }
                }
            }
            fetchedAppGateKeepers.put(applicationId, result);
        }
        return result;
    }

    private static boolean isTimestampValid(@Nullable Long timestamp2) {
        if (timestamp2 != null && System.currentTimeMillis() - timestamp2.longValue() < APPLICATION_GATEKEEPER_CACHE_TIMEOUT) {
            return true;
        }
        return false;
    }
}
