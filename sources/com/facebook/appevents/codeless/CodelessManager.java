package com.facebook.appevents.codeless;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.codeless.ViewIndexingTrigger;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class CodelessManager {
    /* access modifiers changed from: private */
    @Nullable
    public static String deviceSessionID = null;
    /* access modifiers changed from: private */
    public static Boolean isAppIndexingEnabled = false;
    /* access modifiers changed from: private */
    public static volatile Boolean isCheckingSession = false;
    private static CodelessMatcher matcher;
    /* access modifiers changed from: private */
    public static SensorManager sensorManager;
    /* access modifiers changed from: private */
    public static ViewIndexer viewIndexer;
    /* access modifiers changed from: private */
    public static final ViewIndexingTrigger viewIndexingTrigger = new ViewIndexingTrigger();

    public static void onActivityResumed(final Activity activity) {
        FeatureManager.checkFeature(FeatureManager.Feature.CodelessEvents, new FeatureManager.Callback() {
            public void onCompleted(boolean enabled) {
                if (enabled) {
                    CodelessManager.getMatcher().add(activity);
                    Context applicationContext = activity.getApplicationContext();
                    final String appId = FacebookSdk.getApplicationId();
                    final FetchedAppSettings appSettings = FetchedAppSettingsManager.getAppSettingsWithoutQuery(appId);
                    if (appSettings != null && appSettings.getCodelessEventsEnabled()) {
                        SensorManager unused = CodelessManager.sensorManager = (SensorManager) applicationContext.getSystemService("sensor");
                        if (CodelessManager.sensorManager != null) {
                            Sensor accelerometer = CodelessManager.sensorManager.getDefaultSensor(1);
                            ViewIndexer unused2 = CodelessManager.viewIndexer = new ViewIndexer(activity);
                            CodelessManager.viewIndexingTrigger.setOnShakeListener(new ViewIndexingTrigger.OnShakeListener() {
                                public void onShake() {
                                    boolean codelessEventsEnabled;
                                    boolean codelessSetupEnabled;
                                    if (appSettings == null || !appSettings.getCodelessEventsEnabled()) {
                                        codelessEventsEnabled = false;
                                    } else {
                                        codelessEventsEnabled = true;
                                    }
                                    if (!FacebookSdk.getCodelessSetupEnabled()) {
                                        codelessSetupEnabled = false;
                                    } else {
                                        codelessSetupEnabled = true;
                                    }
                                    if (codelessEventsEnabled && codelessSetupEnabled) {
                                        CodelessManager.checkCodelessSession(appId);
                                    }
                                }
                            });
                            CodelessManager.sensorManager.registerListener(CodelessManager.viewIndexingTrigger, accelerometer, 2);
                            if (appSettings != null && appSettings.getCodelessEventsEnabled()) {
                                CodelessManager.viewIndexer.schedule();
                            }
                        }
                    }
                }
            }
        });
    }

    public static void onActivityPaused(final Activity activity) {
        FeatureManager.checkFeature(FeatureManager.Feature.CodelessEvents, new FeatureManager.Callback() {
            public void onCompleted(boolean enabled) {
                if (enabled) {
                    CodelessManager.getMatcher().remove(activity);
                    if (CodelessManager.viewIndexer != null) {
                        CodelessManager.viewIndexer.unschedule();
                    }
                    if (CodelessManager.sensorManager != null) {
                        CodelessManager.sensorManager.unregisterListener(CodelessManager.viewIndexingTrigger);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static synchronized CodelessMatcher getMatcher() {
        CodelessMatcher codelessMatcher;
        synchronized (CodelessManager.class) {
            if (matcher == null) {
                matcher = new CodelessMatcher();
            }
            codelessMatcher = matcher;
        }
        return codelessMatcher;
    }

    public static void checkCodelessSession(final String applicationId) {
        if (!isCheckingSession.booleanValue()) {
            isCheckingSession = true;
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    GraphRequest request = GraphRequest.newPostRequest((AccessToken) null, String.format(Locale.US, "%s/app_indexing_session", new Object[]{applicationId}), (JSONObject) null, (GraphRequest.Callback) null);
                    Bundle requestParameters = request.getParameters();
                    if (requestParameters == null) {
                        requestParameters = new Bundle();
                    }
                    AttributionIdentifiers identifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
                    JSONArray extInfoArray = new JSONArray();
                    extInfoArray.put(Build.MODEL != null ? Build.MODEL : "");
                    if (identifiers == null || identifiers.getAndroidAdvertiserId() == null) {
                        extInfoArray.put("");
                    } else {
                        extInfoArray.put(identifiers.getAndroidAdvertiserId());
                    }
                    extInfoArray.put(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    extInfoArray.put(AppEventUtility.isEmulator() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    Locale locale = Utility.getCurrentLocale();
                    extInfoArray.put(locale.getLanguage() + "_" + locale.getCountry());
                    String extInfo = extInfoArray.toString();
                    requestParameters.putString(Constants.DEVICE_SESSION_ID, CodelessManager.getCurrentDeviceSessionID());
                    requestParameters.putString(Constants.EXTINFO, extInfo);
                    request.setParameters(requestParameters);
                    if (request != null) {
                        JSONObject jsonRes = request.executeAndWait().getJSONObject();
                        Boolean unused = CodelessManager.isAppIndexingEnabled = Boolean.valueOf(jsonRes != null && jsonRes.optBoolean(Constants.APP_INDEXING_ENABLED, false));
                        if (!CodelessManager.isAppIndexingEnabled.booleanValue()) {
                            String unused2 = CodelessManager.deviceSessionID = null;
                        } else {
                            CodelessManager.viewIndexer.schedule();
                        }
                    }
                    Boolean unused3 = CodelessManager.isCheckingSession = false;
                }
            });
        }
    }

    public static String getCurrentDeviceSessionID() {
        if (deviceSessionID == null) {
            deviceSessionID = UUID.randomUUID().toString();
        }
        return deviceSessionID;
    }

    public static boolean getIsAppIndexingEnabled() {
        return isAppIndexingEnabled.booleanValue();
    }

    public static void updateAppIndexing(Boolean appIndexingEnalbed) {
        isAppIndexingEnabled = appIndexingEnalbed;
    }
}
