package com.facebook.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.codeless.internal.UnityReflection;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.appevents.internal.Constants;
import com.facebook.appevents.internal.InAppPurchaseActivityLifecycleTracker;
import com.facebook.appevents.internal.RestrictiveDataManager;
import com.facebook.internal.FetchedAppSettings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class FetchedAppSettingsManager {
    private static final String APPLICATION_FIELDS = "fields";
    private static final String APP_SETTINGS_PREFS_KEY_FORMAT = "com.facebook.internal.APP_SETTINGS.%s";
    private static final String APP_SETTINGS_PREFS_STORE = "com.facebook.internal.preferences.APP_SETTINGS";
    private static final String APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES = "android_sdk_error_categories";
    private static final String APP_SETTING_APP_EVENTS_EVENT_BINDINGS = "auto_event_mapping_android";
    private static final String APP_SETTING_APP_EVENTS_FEATURE_BITMASK = "app_events_feature_bitmask";
    private static final String APP_SETTING_APP_EVENTS_SESSION_TIMEOUT = "app_events_session_timeout";
    private static final String APP_SETTING_DIALOG_CONFIGS = "android_dialog_configs";
    private static final String[] APP_SETTING_FIELDS = {APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING, APP_SETTING_NUX_CONTENT, APP_SETTING_NUX_ENABLED, APP_SETTING_DIALOG_CONFIGS, APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES, APP_SETTING_APP_EVENTS_SESSION_TIMEOUT, APP_SETTING_APP_EVENTS_FEATURE_BITMASK, APP_SETTING_APP_EVENTS_EVENT_BINDINGS, APP_SETTING_SMART_LOGIN_OPTIONS, SMART_LOGIN_BOOKMARK_ICON_URL, SMART_LOGIN_MENU_ICON_URL, APP_SETTING_RESTRICTIVE_EVENT_FILTER_FIELD};
    private static final String APP_SETTING_NUX_CONTENT = "gdpv4_nux_content";
    private static final String APP_SETTING_NUX_ENABLED = "gdpv4_nux_enabled";
    private static final String APP_SETTING_RESTRICTIVE_EVENT_FILTER_FIELD = "restrictive_data_filter_params";
    private static final String APP_SETTING_SMART_LOGIN_OPTIONS = "seamless_login";
    private static final String APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING = "supports_implicit_sdk_logging";
    private static final int AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD = 8;
    private static final int CODELESS_EVENTS_ENABLED_BITMASK_FIELD = 32;
    private static final int IAP_AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD = 16;
    private static final String SDK_UPDATE_MESSAGE = "sdk_update_message";
    private static final String SMART_LOGIN_BOOKMARK_ICON_URL = "smart_login_bookmark_icon_url";
    private static final String SMART_LOGIN_MENU_ICON_URL = "smart_login_menu_icon_url";
    /* access modifiers changed from: private */
    public static final String TAG = FetchedAppSettingsManager.class.getSimpleName();
    private static final int TRACK_UNINSTALL_ENABLED_BITMASK_FIELD = 256;
    /* access modifiers changed from: private */
    public static final Map<String, FetchedAppSettings> fetchedAppSettings = new ConcurrentHashMap();
    private static final ConcurrentLinkedQueue<FetchedAppSettingsCallback> fetchedAppSettingsCallbacks = new ConcurrentLinkedQueue<>();
    private static boolean isUnityInit = false;
    /* access modifiers changed from: private */
    public static final AtomicReference<FetchAppSettingState> loadingState = new AtomicReference<>(FetchAppSettingState.NOT_LOADED);
    /* access modifiers changed from: private */
    public static boolean printedSDKUpdatedMessage = false;
    @Nullable
    private static JSONArray unityEventBindings = null;

    enum FetchAppSettingState {
        NOT_LOADED,
        LOADING,
        SUCCESS,
        ERROR
    }

    public interface FetchedAppSettingsCallback {
        void onError();

        void onSuccess(FetchedAppSettings fetchedAppSettings);
    }

    public static void loadAppSettingsAsync() {
        boolean canStartLoading;
        final Context context = FacebookSdk.getApplicationContext();
        final String applicationId = FacebookSdk.getApplicationId();
        if (Utility.isNullOrEmpty(applicationId)) {
            loadingState.set(FetchAppSettingState.ERROR);
            pollCallbacks();
        } else if (fetchedAppSettings.containsKey(applicationId)) {
            loadingState.set(FetchAppSettingState.SUCCESS);
            pollCallbacks();
        } else {
            if (loadingState.compareAndSet(FetchAppSettingState.NOT_LOADED, FetchAppSettingState.LOADING) || loadingState.compareAndSet(FetchAppSettingState.ERROR, FetchAppSettingState.LOADING)) {
                canStartLoading = true;
            } else {
                canStartLoading = false;
            }
            if (!canStartLoading) {
                pollCallbacks();
                return;
            }
            final String settingsKey = String.format(APP_SETTINGS_PREFS_KEY_FORMAT, new Object[]{applicationId});
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    SharedPreferences sharedPrefs = context.getSharedPreferences(FetchedAppSettingsManager.APP_SETTINGS_PREFS_STORE, 0);
                    String settingsJSONString = sharedPrefs.getString(settingsKey, (String) null);
                    FetchedAppSettings appSettings = null;
                    if (!Utility.isNullOrEmpty(settingsJSONString)) {
                        JSONObject settingsJSON = null;
                        try {
                            settingsJSON = new JSONObject(settingsJSONString);
                        } catch (JSONException je) {
                            Utility.logd("FacebookSDK", (Exception) je);
                        }
                        if (settingsJSON != null) {
                            appSettings = FetchedAppSettingsManager.parseAppSettingsFromJSON(applicationId, settingsJSON);
                        }
                    }
                    JSONObject resultJSON = FetchedAppSettingsManager.getAppSettingsQueryResponse(applicationId);
                    if (resultJSON != null) {
                        FetchedAppSettings unused = FetchedAppSettingsManager.parseAppSettingsFromJSON(applicationId, resultJSON);
                        sharedPrefs.edit().putString(settingsKey, resultJSON.toString()).apply();
                    }
                    if (appSettings != null) {
                        String updateMessage = appSettings.getSdkUpdateMessage();
                        if (!FetchedAppSettingsManager.printedSDKUpdatedMessage && updateMessage != null && updateMessage.length() > 0) {
                            boolean unused2 = FetchedAppSettingsManager.printedSDKUpdatedMessage = true;
                            Log.w(FetchedAppSettingsManager.TAG, updateMessage);
                        }
                    }
                    FetchedAppGateKeepersManager.queryAppGateKeepers(applicationId, true);
                    AutomaticAnalyticsLogger.logActivateAppEvent();
                    InAppPurchaseActivityLifecycleTracker.update();
                    FetchedAppSettingsManager.loadingState.set(FetchedAppSettingsManager.fetchedAppSettings.containsKey(applicationId) ? FetchAppSettingState.SUCCESS : FetchAppSettingState.ERROR);
                    FetchedAppSettingsManager.pollCallbacks();
                }
            });
        }
    }

    @Nullable
    public static FetchedAppSettings getAppSettingsWithoutQuery(String applicationId) {
        if (applicationId != null) {
            return fetchedAppSettings.get(applicationId);
        }
        return null;
    }

    public static void getAppSettingsAsync(FetchedAppSettingsCallback callback) {
        fetchedAppSettingsCallbacks.add(callback);
        loadAppSettingsAsync();
    }

    /* access modifiers changed from: private */
    public static synchronized void pollCallbacks() {
        synchronized (FetchedAppSettingsManager.class) {
            FetchAppSettingState currentState = loadingState.get();
            if (!FetchAppSettingState.NOT_LOADED.equals(currentState) && !FetchAppSettingState.LOADING.equals(currentState)) {
                final FetchedAppSettings appSettings = fetchedAppSettings.get(FacebookSdk.getApplicationId());
                Handler handler = new Handler(Looper.getMainLooper());
                if (FetchAppSettingState.ERROR.equals(currentState)) {
                    while (!fetchedAppSettingsCallbacks.isEmpty()) {
                        final FetchedAppSettingsCallback callback = fetchedAppSettingsCallbacks.poll();
                        handler.post(new Runnable() {
                            public void run() {
                                callback.onError();
                            }
                        });
                    }
                } else {
                    while (!fetchedAppSettingsCallbacks.isEmpty()) {
                        final FetchedAppSettingsCallback callback2 = fetchedAppSettingsCallbacks.poll();
                        handler.post(new Runnable() {
                            public void run() {
                                callback2.onSuccess(appSettings);
                            }
                        });
                    }
                }
            }
        }
    }

    @Nullable
    public static FetchedAppSettings queryAppSettings(String applicationId, boolean forceRequery) {
        if (!forceRequery && fetchedAppSettings.containsKey(applicationId)) {
            return fetchedAppSettings.get(applicationId);
        }
        JSONObject response = getAppSettingsQueryResponse(applicationId);
        if (response == null) {
            return null;
        }
        FetchedAppSettings fetchedAppSettings2 = parseAppSettingsFromJSON(applicationId, response);
        if (applicationId.equals(FacebookSdk.getApplicationId())) {
            loadingState.set(FetchAppSettingState.SUCCESS);
            pollCallbacks();
        }
        return fetchedAppSettings2;
    }

    /* access modifiers changed from: private */
    public static FetchedAppSettings parseAppSettingsFromJSON(String applicationId, JSONObject settingsJSON) {
        FacebookRequestErrorClassification errorClassification;
        JSONArray errorClassificationJSON = settingsJSON.optJSONArray(APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES);
        if (errorClassificationJSON == null) {
            errorClassification = FacebookRequestErrorClassification.getDefaultErrorClassification();
        } else {
            errorClassification = FacebookRequestErrorClassification.createFromJSON(errorClassificationJSON);
        }
        int featureBitmask = settingsJSON.optInt(APP_SETTING_APP_EVENTS_FEATURE_BITMASK, 0);
        boolean automaticLoggingEnabled = (featureBitmask & 8) != 0;
        boolean inAppPurchaseAutomaticLoggingEnabled = (featureBitmask & 16) != 0;
        boolean codelessEventsEnabled = (featureBitmask & 32) != 0;
        boolean trackUninstallEnabled = (featureBitmask & 256) != 0;
        JSONArray eventBindings = settingsJSON.optJSONArray(APP_SETTING_APP_EVENTS_EVENT_BINDINGS);
        unityEventBindings = eventBindings;
        if (unityEventBindings != null && InternalSettings.isUnityApp()) {
            UnityReflection.sendEventMapping(eventBindings.toString());
        }
        FetchedAppSettings result = new FetchedAppSettings(settingsJSON.optBoolean(APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING, false), settingsJSON.optString(APP_SETTING_NUX_CONTENT, ""), settingsJSON.optBoolean(APP_SETTING_NUX_ENABLED, false), settingsJSON.optInt(APP_SETTING_APP_EVENTS_SESSION_TIMEOUT, Constants.getDefaultAppEventsSessionTimeoutInSeconds()), SmartLoginOption.parseOptions(settingsJSON.optLong(APP_SETTING_SMART_LOGIN_OPTIONS)), parseDialogConfigurations(settingsJSON.optJSONObject(APP_SETTING_DIALOG_CONFIGS)), automaticLoggingEnabled, errorClassification, settingsJSON.optString(SMART_LOGIN_BOOKMARK_ICON_URL), settingsJSON.optString(SMART_LOGIN_MENU_ICON_URL), inAppPurchaseAutomaticLoggingEnabled, codelessEventsEnabled, eventBindings, settingsJSON.optString(SDK_UPDATE_MESSAGE), trackUninstallEnabled);
        fetchedAppSettings.put(applicationId, result);
        final JSONObject jSONObject = settingsJSON;
        FacebookSdk.getExecutor().execute(new Runnable() {
            public void run() {
                RestrictiveDataManager.updateFromSetting(jSONObject.optString(FetchedAppSettingsManager.APP_SETTING_RESTRICTIVE_EVENT_FILTER_FIELD));
            }
        });
        return result;
    }

    public static void setIsUnityInit(boolean flag) {
        isUnityInit = flag;
        if (unityEventBindings != null && isUnityInit) {
            UnityReflection.sendEventMapping(unityEventBindings.toString());
        }
    }

    /* access modifiers changed from: private */
    public static JSONObject getAppSettingsQueryResponse(String applicationId) {
        Bundle appSettingsParams = new Bundle();
        appSettingsParams.putString("fields", TextUtils.join(",", new ArrayList<>(Arrays.asList(APP_SETTING_FIELDS))));
        GraphRequest request = GraphRequest.newGraphPathRequest((AccessToken) null, applicationId, (GraphRequest.Callback) null);
        request.setSkipClientToken(true);
        request.setParameters(appSettingsParams);
        return request.executeAndWait().getJSONObject();
    }

    private static Map<String, Map<String, FetchedAppSettings.DialogFeatureConfig>> parseDialogConfigurations(JSONObject dialogConfigResponse) {
        JSONArray dialogConfigData;
        HashMap<String, Map<String, FetchedAppSettings.DialogFeatureConfig>> dialogConfigMap = new HashMap<>();
        if (!(dialogConfigResponse == null || (dialogConfigData = dialogConfigResponse.optJSONArray("data")) == null)) {
            for (int i = 0; i < dialogConfigData.length(); i++) {
                FetchedAppSettings.DialogFeatureConfig dialogConfig = FetchedAppSettings.DialogFeatureConfig.parseDialogConfig(dialogConfigData.optJSONObject(i));
                if (dialogConfig != null) {
                    String dialogName = dialogConfig.getDialogName();
                    Map<String, FetchedAppSettings.DialogFeatureConfig> featureMap = dialogConfigMap.get(dialogName);
                    if (featureMap == null) {
                        featureMap = new HashMap<>();
                        dialogConfigMap.put(dialogName, featureMap);
                    }
                    featureMap.put(dialogConfig.getFeatureName(), dialogConfig);
                }
            }
        }
        return dialogConfigMap;
    }
}
