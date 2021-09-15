package com.facebook.appevents;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebView;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.appevents.internal.Constants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.BundleJSONConverter;
import com.facebook.internal.FetchedAppGateKeepersManager;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class AppEventsLoggerImpl {
    private static final String ACCOUNT_KIT_EVENT_NAME_PREFIX = "fb_ak";
    private static final String APP_EVENTS_KILLSWITCH = "app_events_killswitch";
    private static final String APP_EVENT_NAME_PUSH_OPENED = "fb_mobile_push_opened";
    private static final String APP_EVENT_PREFERENCES = "com.facebook.sdk.appEventPreferences";
    private static final String APP_EVENT_PUSH_PARAMETER_ACTION = "fb_push_action";
    private static final String APP_EVENT_PUSH_PARAMETER_CAMPAIGN = "fb_push_campaign";
    private static final int APP_SUPPORTS_ATTRIBUTION_ID_RECHECK_PERIOD_IN_SECONDS = 86400;
    private static final String PUSH_PAYLOAD_CAMPAIGN_KEY = "campaign";
    private static final String PUSH_PAYLOAD_KEY = "fb_push_payload";
    /* access modifiers changed from: private */
    public static final String TAG = AppEventsLoggerImpl.class.getCanonicalName();
    private static String anonymousAppDeviceGUID;
    private static ScheduledThreadPoolExecutor backgroundExecutor;
    private static AppEventsLogger.FlushBehavior flushBehavior = AppEventsLogger.FlushBehavior.AUTO;
    private static boolean isActivateAppEventRequested;
    private static String pushNotificationsRegistrationId;
    private static final Object staticLock = new Object();
    private final AccessTokenAppIdPair accessTokenAppId;
    private final String contextName;

    static void activateApp(Application application, String applicationId) {
        if (!FacebookSdk.isInitialized()) {
            throw new FacebookException("The Facebook sdk must be initialized before calling activateApp");
        }
        AnalyticsUserIDStore.initStore();
        UserDataStore.initStore();
        if (applicationId == null) {
            applicationId = FacebookSdk.getApplicationId();
        }
        FacebookSdk.publishInstallAsync(application, applicationId);
        ActivityLifecycleTracker.startTracking(application, applicationId);
    }

    static void functionDEPRECATED(String extraMsg) {
        Log.w(TAG, "This function is deprecated. " + extraMsg);
    }

    static void initializeLib(final Context context, String applicationId) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            final AppEventsLoggerImpl logger = new AppEventsLoggerImpl(context, applicationId, (AccessToken) null);
            backgroundExecutor.execute(new Runnable() {
                public void run() {
                    Bundle params = new Bundle();
                    String[] classes = {"com.facebook.core.Core", "com.facebook.login.Login", "com.facebook.share.Share", "com.facebook.places.Places", "com.facebook.messenger.Messenger", "com.facebook.applinks.AppLinks", "com.facebook.marketing.Marketing", "com.facebook.all.All", "com.android.billingclient.api.BillingClient", "com.android.vending.billing.IInAppBillingService"};
                    String[] keys = {"core_lib_included", "login_lib_included", "share_lib_included", "places_lib_included", "messenger_lib_included", "applinks_lib_included", "marketing_lib_included", "all_lib_included", "billing_client_lib_included", "billing_service_lib_included"};
                    if (classes.length != keys.length) {
                        throw new FacebookException("Number of class names and key names should match");
                    }
                    int bitmask = 0;
                    for (int i = 0; i < classes.length; i++) {
                        String className = classes[i];
                        String keyName = keys[i];
                        try {
                            Class.forName(className);
                            params.putInt(keyName, 1);
                            bitmask |= 1 << i;
                        } catch (ClassNotFoundException e) {
                        }
                    }
                    SharedPreferences preferences = context.getSharedPreferences(AppEventsLoggerImpl.APP_EVENT_PREFERENCES, 0);
                    if (preferences.getInt("kitsBitmask", 0) != bitmask) {
                        preferences.edit().putInt("kitsBitmask", bitmask).apply();
                        logger.logEventImplicitly(AnalyticsEvents.EVENT_SDK_INITIALIZE, (Double) null, params);
                    }
                }
            });
        }
    }

    static AppEventsLogger.FlushBehavior getFlushBehavior() {
        AppEventsLogger.FlushBehavior flushBehavior2;
        synchronized (staticLock) {
            flushBehavior2 = flushBehavior;
        }
        return flushBehavior2;
    }

    static void setFlushBehavior(AppEventsLogger.FlushBehavior flushBehavior2) {
        synchronized (staticLock) {
            flushBehavior = flushBehavior2;
        }
    }

    /* access modifiers changed from: package-private */
    public void logEvent(String eventName) {
        logEvent(eventName, (Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public void logEvent(String eventName, double valueToSum) {
        logEvent(eventName, valueToSum, (Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public void logEvent(String eventName, Bundle parameters) {
        logEvent(eventName, (Double) null, parameters, false, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    /* access modifiers changed from: package-private */
    public void logEvent(String eventName, double valueToSum, Bundle parameters) {
        logEvent(eventName, Double.valueOf(valueToSum), parameters, false, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    /* access modifiers changed from: package-private */
    public void logPurchase(BigDecimal purchaseAmount, Currency currency) {
        logPurchase(purchaseAmount, currency, (Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public void logPurchase(BigDecimal purchaseAmount, Currency currency, Bundle parameters) {
        if (AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
            Log.w(TAG, "You are logging purchase events while auto-logging of in-app purchase is enabled in the SDK. Make sure you don't log duplicate events");
        }
        logPurchase(purchaseAmount, currency, parameters, false);
    }

    /* access modifiers changed from: package-private */
    public void logPurchaseImplicitly(BigDecimal purchaseAmount, Currency currency, Bundle parameters) {
        logPurchase(purchaseAmount, currency, parameters, true);
    }

    /* access modifiers changed from: package-private */
    public void logPurchase(BigDecimal purchaseAmount, Currency currency, Bundle parameters, boolean isImplicitlyLogged) {
        if (purchaseAmount == null) {
            notifyDeveloperError("purchaseAmount cannot be null");
        } else if (currency == null) {
            notifyDeveloperError("currency cannot be null");
        } else {
            if (parameters == null) {
                parameters = new Bundle();
            }
            parameters.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, currency.getCurrencyCode());
            logEvent(AppEventsConstants.EVENT_NAME_PURCHASED, Double.valueOf(purchaseAmount.doubleValue()), parameters, isImplicitlyLogged, ActivityLifecycleTracker.getCurrentSessionGuid());
            eagerFlush();
        }
    }

    /* access modifiers changed from: package-private */
    public void logPushNotificationOpen(Bundle payload, String action) {
        String campaignId = null;
        try {
            String payloadString = payload.getString(PUSH_PAYLOAD_KEY);
            if (!Utility.isNullOrEmpty(payloadString)) {
                campaignId = new JSONObject(payloadString).getString("campaign");
                if (campaignId == null) {
                    Logger.log(LoggingBehavior.DEVELOPER_ERRORS, TAG, "Malformed payload specified for logging a push notification open.");
                    return;
                }
                Bundle parameters = new Bundle();
                parameters.putString(APP_EVENT_PUSH_PARAMETER_CAMPAIGN, campaignId);
                if (action != null) {
                    parameters.putString(APP_EVENT_PUSH_PARAMETER_ACTION, action);
                }
                logEvent(APP_EVENT_NAME_PUSH_OPENED, parameters);
            }
        } catch (JSONException e) {
        }
    }

    /* access modifiers changed from: package-private */
    public void logProductItem(String itemID, AppEventsLogger.ProductAvailability availability, AppEventsLogger.ProductCondition condition, String description, String imageLink, String link, String title, BigDecimal priceAmount, Currency currency, String gtin, String mpn, String brand, Bundle parameters) {
        if (itemID == null) {
            notifyDeveloperError("itemID cannot be null");
        } else if (availability == null) {
            notifyDeveloperError("availability cannot be null");
        } else if (condition == null) {
            notifyDeveloperError("condition cannot be null");
        } else if (description == null) {
            notifyDeveloperError("description cannot be null");
        } else if (imageLink == null) {
            notifyDeveloperError("imageLink cannot be null");
        } else if (link == null) {
            notifyDeveloperError("link cannot be null");
        } else if (title == null) {
            notifyDeveloperError("title cannot be null");
        } else if (priceAmount == null) {
            notifyDeveloperError("priceAmount cannot be null");
        } else if (currency == null) {
            notifyDeveloperError("currency cannot be null");
        } else if (gtin == null && mpn == null && brand == null) {
            notifyDeveloperError("Either gtin, mpn or brand is required");
        } else {
            if (parameters == null) {
                parameters = new Bundle();
            }
            parameters.putString(Constants.EVENT_PARAM_PRODUCT_ITEM_ID, itemID);
            parameters.putString(Constants.EVENT_PARAM_PRODUCT_AVAILABILITY, availability.name());
            parameters.putString(Constants.EVENT_PARAM_PRODUCT_CONDITION, condition.name());
            parameters.putString(Constants.EVENT_PARAM_PRODUCT_DESCRIPTION, description);
            parameters.putString(Constants.EVENT_PARAM_PRODUCT_IMAGE_LINK, imageLink);
            parameters.putString(Constants.EVENT_PARAM_PRODUCT_LINK, link);
            parameters.putString(Constants.EVENT_PARAM_PRODUCT_TITLE, title);
            parameters.putString(Constants.EVENT_PARAM_PRODUCT_PRICE_AMOUNT, priceAmount.setScale(3, 4).toString());
            parameters.putString(Constants.EVENT_PARAM_PRODUCT_PRICE_CURRENCY, currency.getCurrencyCode());
            if (gtin != null) {
                parameters.putString(Constants.EVENT_PARAM_PRODUCT_GTIN, gtin);
            }
            if (mpn != null) {
                parameters.putString(Constants.EVENT_PARAM_PRODUCT_MPN, mpn);
            }
            if (brand != null) {
                parameters.putString(Constants.EVENT_PARAM_PRODUCT_BRAND, brand);
            }
            logEvent(AppEventsConstants.EVENT_NAME_PRODUCT_CATALOG_UPDATE, parameters);
            eagerFlush();
        }
    }

    /* access modifiers changed from: package-private */
    public void flush() {
        AppEventQueue.flush(FlushReason.EXPLICIT);
    }

    static void onContextStop() {
        AppEventQueue.persistToDisk();
    }

    /* access modifiers changed from: package-private */
    public boolean isValidForAccessToken(AccessToken accessToken) {
        return this.accessTokenAppId.equals(new AccessTokenAppIdPair(accessToken));
    }

    static void setPushNotificationsRegistrationId(String registrationId) {
        synchronized (staticLock) {
            if (!Utility.stringsEqualOrEmpty(pushNotificationsRegistrationId, registrationId)) {
                pushNotificationsRegistrationId = registrationId;
                AppEventsLoggerImpl logger = new AppEventsLoggerImpl(FacebookSdk.getApplicationContext(), (String) null, (AccessToken) null);
                logger.logEvent(AppEventsConstants.EVENT_NAME_PUSH_TOKEN_OBTAINED);
                if (getFlushBehavior() != AppEventsLogger.FlushBehavior.EXPLICIT_ONLY) {
                    logger.flush();
                }
            }
        }
    }

    static String getPushNotificationsRegistrationId() {
        String str;
        synchronized (staticLock) {
            str = pushNotificationsRegistrationId;
        }
        return str;
    }

    static void setInstallReferrer(String referrer) {
        SharedPreferences preferences = FacebookSdk.getApplicationContext().getSharedPreferences(APP_EVENT_PREFERENCES, 0);
        if (referrer != null) {
            preferences.edit().putString("install_referrer", referrer).apply();
        }
    }

    @Nullable
    static String getInstallReferrer() {
        return FacebookSdk.getApplicationContext().getSharedPreferences(APP_EVENT_PREFERENCES, 0).getString("install_referrer", (String) null);
    }

    static void augmentWebView(WebView webView, Context context) {
        int majorRelease;
        int minorRelease;
        String[] parts = Build.VERSION.RELEASE.split("\\.");
        if (parts.length > 0) {
            majorRelease = Integer.parseInt(parts[0]);
        } else {
            majorRelease = 0;
        }
        if (parts.length > 1) {
            minorRelease = Integer.parseInt(parts[1]);
        } else {
            minorRelease = 0;
        }
        if (Build.VERSION.SDK_INT < 17 || majorRelease < 4 || (majorRelease == 4 && minorRelease <= 1)) {
            Logger.log(LoggingBehavior.DEVELOPER_ERRORS, TAG, "augmentWebView is only available for Android SDK version >= 17 on devices running Android >= 4.2");
        } else {
            webView.addJavascriptInterface(new FacebookSDKJSInterface(context), "fbmq_" + FacebookSdk.getApplicationId());
        }
    }

    static void updateUserProperties(final Bundle parameters, final String applicationID, final GraphRequest.Callback callback) {
        getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                String userID = AnalyticsUserIDStore.getUserID();
                if (userID == null || userID.isEmpty()) {
                    Logger.log(LoggingBehavior.APP_EVENTS, AppEventsLoggerImpl.TAG, "AppEventsLogger userID cannot be null or empty");
                    return;
                }
                Bundle userPropertiesParams = new Bundle();
                userPropertiesParams.putString("user_unique_id", userID);
                userPropertiesParams.putBundle("custom_data", parameters);
                AttributionIdentifiers identifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
                if (!(identifiers == null || identifiers.getAndroidAdvertiserId() == null)) {
                    userPropertiesParams.putString("advertiser_id", identifiers.getAndroidAdvertiserId());
                }
                Bundle data = new Bundle();
                try {
                    JSONObject userData = BundleJSONConverter.convertToJSON(userPropertiesParams);
                    JSONArray dataArray = new JSONArray();
                    dataArray.put(userData);
                    data.putString("data", dataArray.toString());
                    GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(), String.format(Locale.US, "%s/user_properties", new Object[]{applicationID}), data, HttpMethod.POST, callback);
                    request.setSkipClientToken(true);
                    request.executeAsync();
                } catch (JSONException ex) {
                    throw new FacebookException("Failed to construct request", (Throwable) ex);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void logSdkEvent(String eventName, Double valueToSum, Bundle parameters) {
        if (!eventName.startsWith(ACCOUNT_KIT_EVENT_NAME_PREFIX)) {
            Log.e(TAG, "logSdkEvent is deprecated and only supports account kit for legacy, please use logEvent instead");
        } else if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            logEvent(eventName, valueToSum, parameters, true, ActivityLifecycleTracker.getCurrentSessionGuid());
        }
    }

    public String getApplicationId() {
        return this.accessTokenAppId.getApplicationId();
    }

    AppEventsLoggerImpl(Context context, String applicationId, AccessToken accessToken) {
        this(Utility.getActivityName(context), applicationId, accessToken);
    }

    AppEventsLoggerImpl(String activityName, String applicationId, AccessToken accessToken) {
        Validate.sdkInitialized();
        this.contextName = activityName;
        accessToken = accessToken == null ? AccessToken.getCurrentAccessToken() : accessToken;
        if (!AccessToken.isCurrentAccessTokenActive() || (applicationId != null && !applicationId.equals(accessToken.getApplicationId()))) {
            this.accessTokenAppId = new AccessTokenAppIdPair((String) null, applicationId == null ? Utility.getMetadataApplicationId(FacebookSdk.getApplicationContext()) : applicationId);
        } else {
            this.accessTokenAppId = new AccessTokenAppIdPair(accessToken);
        }
        initializeTimersIfNeeded();
    }

    private static void initializeTimersIfNeeded() {
        synchronized (staticLock) {
            if (backgroundExecutor == null) {
                backgroundExecutor = new ScheduledThreadPoolExecutor(1);
                backgroundExecutor.scheduleAtFixedRate(new Runnable() {
                    public void run() {
                        Set<String> applicationIds = new HashSet<>();
                        for (AccessTokenAppIdPair accessTokenAppId : AppEventQueue.getKeySet()) {
                            applicationIds.add(accessTokenAppId.getApplicationId());
                        }
                        for (String applicationId : applicationIds) {
                            FetchedAppSettingsManager.queryAppSettings(applicationId, true);
                        }
                    }
                }, 0, 86400, TimeUnit.SECONDS);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void logEventImplicitly(String eventName, Double valueToSum, Bundle parameters) {
        logEvent(eventName, valueToSum, parameters, true, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    /* access modifiers changed from: package-private */
    public void logEventImplicitly(String eventName, BigDecimal purchaseAmount, Currency currency, Bundle parameters) {
        if (purchaseAmount == null || currency == null) {
            Utility.logd(TAG, "purchaseAmount and currency cannot be null");
            return;
        }
        if (parameters == null) {
            parameters = new Bundle();
        }
        parameters.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, currency.getCurrencyCode());
        logEvent(eventName, Double.valueOf(purchaseAmount.doubleValue()), parameters, true, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    /* access modifiers changed from: package-private */
    public void logEvent(String eventName, Double valueToSum, Bundle parameters, boolean isImplicitlyLogged, @Nullable UUID currentSessionId) {
        if (eventName != null && !eventName.isEmpty()) {
            if (FetchedAppGateKeepersManager.getGateKeeperForKey(APP_EVENTS_KILLSWITCH, FacebookSdk.getApplicationId(), false)) {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "KillSwitch is enabled and fail to log app event: %s", eventName);
                return;
            }
            try {
                logEvent(new AppEvent(this.contextName, eventName, valueToSum, parameters, isImplicitlyLogged, ActivityLifecycleTracker.isInBackground(), currentSessionId), this.accessTokenAppId);
            } catch (JSONException jsonException) {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", jsonException.toString());
            } catch (FacebookException e) {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event: %s", e.toString());
            }
        }
    }

    private static void logEvent(AppEvent event, AccessTokenAppIdPair accessTokenAppId2) {
        AppEventQueue.add(accessTokenAppId2, event);
        if (!event.getIsImplicit() && !isActivateAppEventRequested) {
            if (event.getName().equals(AppEventsConstants.EVENT_NAME_ACTIVATED_APP)) {
                isActivateAppEventRequested = true;
            } else {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
            }
        }
    }

    static void eagerFlush() {
        if (getFlushBehavior() != AppEventsLogger.FlushBehavior.EXPLICIT_ONLY) {
            AppEventQueue.flush(FlushReason.EAGER_FLUSHING_EVENT);
        }
    }

    private static void notifyDeveloperError(String message) {
        Logger.log(LoggingBehavior.DEVELOPER_ERRORS, "AppEvents", message);
    }

    static Executor getAnalyticsExecutor() {
        if (backgroundExecutor == null) {
            initializeTimersIfNeeded();
        }
        return backgroundExecutor;
    }

    static String getAnonymousAppDeviceGUID(Context context) {
        if (anonymousAppDeviceGUID == null) {
            synchronized (staticLock) {
                if (anonymousAppDeviceGUID == null) {
                    anonymousAppDeviceGUID = context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).getString("anonymousAppDeviceGUID", (String) null);
                    if (anonymousAppDeviceGUID == null) {
                        anonymousAppDeviceGUID = "XZ" + UUID.randomUUID().toString();
                        context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).edit().putString("anonymousAppDeviceGUID", anonymousAppDeviceGUID).apply();
                    }
                }
            }
        }
        return anonymousAppDeviceGUID;
    }
}
