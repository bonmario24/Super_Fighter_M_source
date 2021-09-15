package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.facebook.GraphRequest;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

final class UserSettingsManager {
    private static final String ADVERTISERID_COLLECTION_FALSE_WARNING = "The value for AdvertiserIDCollectionEnabled is currently set to FALSE so you're sending app events without collecting Advertiser ID. This can affect the quality of your advertising and analytics results.";
    private static final String ADVERTISERID_COLLECTION_NOT_SET_WARNING = "You haven't set a value for AdvertiserIDCollectionEnabled. Set the flag to TRUE if you want to collect Advertiser ID for better advertising and analytics results. To request user consent before collecting data, set the flag value to FALSE, then change to TRUE once user consent is received. Learn more: https://developers.facebook.com/docs/app-events/getting-started-app-events-android#disable-auto-events.";
    private static final String ADVERTISER_ID_KEY = "advertiser_id";
    private static final String APPLICATION_FIELDS = "fields";
    private static final String AUTOLOG_APPEVENT_NOT_SET_WARNING = "Please set a value for AutoLogAppEventsEnabled. Set the flag to TRUE if you want to collect app install, app launch and in-app purchase events automatically. To request user consent before collecting data, set the flag value to FALSE, then change to TRUE once user consent is received. Learn more: https://developers.facebook.com/docs/app-events/getting-started-app-events-android#disable-auto-events.";
    private static final String EVENTS_CODELESS_SETUP_ENABLED = "auto_event_setup_enabled";
    private static final String LAST_TIMESTAMP = "last_timestamp";
    private static final String TAG = UserSettingsManager.class.getName();
    private static final long TIMEOUT_7D = 604800000;
    private static final String USER_SETTINGS = "com.facebook.sdk.USER_SETTINGS";
    private static final String USER_SETTINGS_BITMASK = "com.facebook.sdk.USER_SETTINGS_BITMASK";
    private static final String VALUE = "value";
    /* access modifiers changed from: private */
    public static UserSetting advertiserIDCollectionEnabled = new UserSetting(true, FacebookSdk.ADVERTISER_ID_COLLECTION_ENABLED_PROPERTY);
    private static UserSetting autoInitEnabled = new UserSetting(true, FacebookSdk.AUTO_INIT_ENABLED_PROPERTY);
    private static UserSetting autoLogAppEventsEnabled = new UserSetting(true, FacebookSdk.AUTO_LOG_APP_EVENTS_ENABLED_PROPERTY);
    /* access modifiers changed from: private */
    public static UserSetting codelessSetupEnabled = new UserSetting(false, EVENTS_CODELESS_SETUP_ENABLED);
    /* access modifiers changed from: private */
    public static AtomicBoolean isFetchingCodelessStatus = new AtomicBoolean(false);
    private static AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static SharedPreferences userSettingPref;
    private static SharedPreferences.Editor userSettingPrefEditor;

    UserSettingsManager() {
    }

    public static void initializeIfNotInitialized() {
        if (FacebookSdk.isInitialized() && isInitialized.compareAndSet(false, true)) {
            userSettingPref = FacebookSdk.getApplicationContext().getSharedPreferences(USER_SETTINGS, 0);
            userSettingPrefEditor = userSettingPref.edit();
            initializeUserSetting(autoLogAppEventsEnabled, advertiserIDCollectionEnabled, autoInitEnabled);
            initializeCodelessSetupEnabledAsync();
            logWarnings();
            logIfSDKSettingsChanged();
        }
    }

    private static void initializeUserSetting(UserSetting... userSettings) {
        for (UserSetting userSetting : userSettings) {
            if (userSetting == codelessSetupEnabled) {
                initializeCodelessSetupEnabledAsync();
            } else if (userSetting.value == null) {
                readSettingFromCache(userSetting);
                if (userSetting.value == null) {
                    loadSettingFromManifest(userSetting);
                }
            } else {
                writeSettingToCache(userSetting);
            }
        }
    }

    private static void initializeCodelessSetupEnabledAsync() {
        readSettingFromCache(codelessSetupEnabled);
        final long currTime = System.currentTimeMillis();
        if (codelessSetupEnabled.value == null || currTime - codelessSetupEnabled.lastTS >= TIMEOUT_7D) {
            codelessSetupEnabled.value = null;
            codelessSetupEnabled.lastTS = 0;
            if (isFetchingCodelessStatus.compareAndSet(false, true)) {
                FacebookSdk.getExecutor().execute(new Runnable() {
                    public void run() {
                        FetchedAppSettings appSettings;
                        if (UserSettingsManager.advertiserIDCollectionEnabled.getValue() && (appSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false)) != null && appSettings.getCodelessEventsEnabled()) {
                            String advertiser_id = null;
                            AttributionIdentifiers identifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
                            if (!(identifiers == null || identifiers.getAndroidAdvertiserId() == null)) {
                                advertiser_id = identifiers.getAndroidAdvertiserId();
                            }
                            if (advertiser_id != null) {
                                Bundle codelessSettingsParams = new Bundle();
                                codelessSettingsParams.putString(UserSettingsManager.ADVERTISER_ID_KEY, identifiers.getAndroidAdvertiserId());
                                codelessSettingsParams.putString("fields", UserSettingsManager.EVENTS_CODELESS_SETUP_ENABLED);
                                GraphRequest codelessRequest = GraphRequest.newGraphPathRequest((AccessToken) null, FacebookSdk.getApplicationId(), (GraphRequest.Callback) null);
                                codelessRequest.setSkipClientToken(true);
                                codelessRequest.setParameters(codelessSettingsParams);
                                JSONObject response = codelessRequest.executeAndWait().getJSONObject();
                                if (response != null) {
                                    UserSettingsManager.codelessSetupEnabled.value = Boolean.valueOf(response.optBoolean(UserSettingsManager.EVENTS_CODELESS_SETUP_ENABLED, false));
                                    UserSettingsManager.codelessSetupEnabled.lastTS = currTime;
                                    UserSettingsManager.writeSettingToCache(UserSettingsManager.codelessSetupEnabled);
                                }
                            }
                        }
                        UserSettingsManager.isFetchingCodelessStatus.set(false);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static void writeSettingToCache(UserSetting userSetting) {
        validateInitialized();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value", userSetting.value);
            jsonObject.put(LAST_TIMESTAMP, userSetting.lastTS);
            userSettingPrefEditor.putString(userSetting.key, jsonObject.toString()).commit();
            logIfSDKSettingsChanged();
        } catch (JSONException je) {
            Utility.logd(TAG, (Exception) je);
        }
    }

    private static void readSettingFromCache(UserSetting userSetting) {
        validateInitialized();
        try {
            String settingStr = userSettingPref.getString(userSetting.key, "");
            if (!settingStr.isEmpty()) {
                JSONObject setting = new JSONObject(settingStr);
                userSetting.value = Boolean.valueOf(setting.getBoolean("value"));
                userSetting.lastTS = setting.getLong(LAST_TIMESTAMP);
            }
        } catch (JSONException je) {
            Utility.logd(TAG, (Exception) je);
        }
    }

    private static void loadSettingFromManifest(UserSetting userSetting) {
        validateInitialized();
        try {
            Context ctx = FacebookSdk.getApplicationContext();
            ApplicationInfo ai = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128);
            if (ai != null && ai.metaData != null && ai.metaData.containsKey(userSetting.key)) {
                userSetting.value = Boolean.valueOf(ai.metaData.getBoolean(userSetting.key, userSetting.defaultVal));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Utility.logd(TAG, (Exception) e);
        }
    }

    private static void logWarnings() {
        try {
            Context ctx = FacebookSdk.getApplicationContext();
            ApplicationInfo ai = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128);
            if (ai != null && ai.metaData != null) {
                if (!ai.metaData.containsKey(FacebookSdk.AUTO_LOG_APP_EVENTS_ENABLED_PROPERTY)) {
                    Log.w(TAG, AUTOLOG_APPEVENT_NOT_SET_WARNING);
                }
                if (!ai.metaData.containsKey(FacebookSdk.ADVERTISER_ID_COLLECTION_ENABLED_PROPERTY)) {
                    Log.w(TAG, ADVERTISERID_COLLECTION_NOT_SET_WARNING);
                }
                if (!getAdvertiserIDCollectionEnabled()) {
                    Log.w(TAG, ADVERTISERID_COLLECTION_FALSE_WARNING);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
    }

    private static void logIfSDKSettingsChanged() {
        if (isInitialized.get() && FacebookSdk.isInitialized()) {
            Context ctx = FacebookSdk.getApplicationContext();
            int bit = 0 + 1 + 1 + 1;
            int bitmask = 0 | ((autoInitEnabled.getValue() ? 1 : 0) << 0) | ((autoLogAppEventsEnabled.getValue() ? 1 : 0) << 1) | ((advertiserIDCollectionEnabled.getValue() ? 1 : 0) << 2);
            int previousBitmask = userSettingPref.getInt(USER_SETTINGS_BITMASK, 0);
            if (previousBitmask != bitmask) {
                userSettingPrefEditor.putInt(USER_SETTINGS_BITMASK, bitmask).commit();
                int initialBitmask = 0;
                int usageBitmask = 0;
                try {
                    ApplicationInfo ai = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128);
                    if (!(ai == null || ai.metaData == null)) {
                        String[] keys = {FacebookSdk.AUTO_INIT_ENABLED_PROPERTY, FacebookSdk.AUTO_LOG_APP_EVENTS_ENABLED_PROPERTY, FacebookSdk.ADVERTISER_ID_COLLECTION_ENABLED_PROPERTY};
                        boolean[] defaultValues = {true, true, true};
                        for (int i = 0; i < keys.length; i++) {
                            usageBitmask |= (ai.metaData.containsKey(keys[i]) ? 1 : 0) << i;
                            initialBitmask |= (ai.metaData.getBoolean(keys[i], defaultValues[i]) ? 1 : 0) << i;
                        }
                    }
                } catch (PackageManager.NameNotFoundException e) {
                }
                InternalAppEventsLogger logger = new InternalAppEventsLogger(ctx);
                Bundle parameters = new Bundle();
                parameters.putInt("usage", usageBitmask);
                parameters.putInt("initial", initialBitmask);
                parameters.putInt("previous", previousBitmask);
                parameters.putInt("current", bitmask);
                logger.logEventImplicitly("fb_sdk_settings_changed", parameters);
            }
        }
    }

    private static void validateInitialized() {
        if (!isInitialized.get()) {
            throw new FacebookSdkNotInitializedException("The UserSettingManager has not been initialized successfully");
        }
    }

    public static void setAutoInitEnabled(boolean flag) {
        autoInitEnabled.value = Boolean.valueOf(flag);
        autoInitEnabled.lastTS = System.currentTimeMillis();
        if (isInitialized.get()) {
            writeSettingToCache(autoInitEnabled);
        } else {
            initializeIfNotInitialized();
        }
    }

    public static boolean getAutoInitEnabled() {
        initializeIfNotInitialized();
        return autoInitEnabled.getValue();
    }

    public static void setAutoLogAppEventsEnabled(boolean flag) {
        autoLogAppEventsEnabled.value = Boolean.valueOf(flag);
        autoLogAppEventsEnabled.lastTS = System.currentTimeMillis();
        if (isInitialized.get()) {
            writeSettingToCache(autoLogAppEventsEnabled);
        } else {
            initializeIfNotInitialized();
        }
    }

    public static boolean getAutoLogAppEventsEnabled() {
        initializeIfNotInitialized();
        return autoLogAppEventsEnabled.getValue();
    }

    public static void setAdvertiserIDCollectionEnabled(boolean flag) {
        advertiserIDCollectionEnabled.value = Boolean.valueOf(flag);
        advertiserIDCollectionEnabled.lastTS = System.currentTimeMillis();
        if (isInitialized.get()) {
            writeSettingToCache(advertiserIDCollectionEnabled);
        } else {
            initializeIfNotInitialized();
        }
    }

    public static boolean getAdvertiserIDCollectionEnabled() {
        initializeIfNotInitialized();
        return advertiserIDCollectionEnabled.getValue();
    }

    public static boolean getCodelessSetupEnabled() {
        initializeIfNotInitialized();
        return codelessSetupEnabled.getValue();
    }

    private static class UserSetting {
        boolean defaultVal;
        String key;
        long lastTS;
        Boolean value;

        UserSetting(boolean defaultVal2, String key2) {
            this.defaultVal = defaultVal2;
            this.key = key2;
        }

        /* access modifiers changed from: package-private */
        public boolean getValue() {
            return this.value == null ? this.defaultVal : this.value.booleanValue();
        }
    }
}
