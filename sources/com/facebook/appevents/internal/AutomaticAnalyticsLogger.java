package com.facebook.appevents.internal;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.android.billingclient.api.BillingClient;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.internal.FetchedAppGateKeepersManager;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Validate;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class AutomaticAnalyticsLogger {
    private static final String APP_EVENTS_IF_AUTO_LOG_SUBS = "app_events_if_auto_log_subs";
    private static final String TAG = AutomaticAnalyticsLogger.class.getCanonicalName();
    private static final InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(FacebookSdk.getApplicationContext());

    public static void logActivateAppEvent() {
        Context context = FacebookSdk.getApplicationContext();
        String appId = FacebookSdk.getApplicationId();
        boolean autoLogAppEvents = FacebookSdk.getAutoLogAppEventsEnabled();
        Validate.notNull(context, "context");
        if (!autoLogAppEvents) {
            return;
        }
        if (context instanceof Application) {
            AppEventsLogger.activateApp((Application) context, appId);
        } else {
            Log.w(TAG, "Automatic logging of basic events will not happen, because FacebookSdk.getApplicationContext() returns object that is not instance of android.app.Application. Make sure you call FacebookSdk.sdkInitialize() from Application class and pass application context.");
        }
    }

    public static void logActivityTimeSpentEvent(String activityName, long timeSpentInSeconds) {
        Context context = FacebookSdk.getApplicationContext();
        String appId = FacebookSdk.getApplicationId();
        Validate.notNull(context, "context");
        FetchedAppSettings settings = FetchedAppSettingsManager.queryAppSettings(appId, false);
        if (settings != null && settings.getAutomaticLoggingEnabled() && timeSpentInSeconds > 0) {
            InternalAppEventsLogger logger = new InternalAppEventsLogger(context);
            Bundle params = new Bundle(1);
            params.putCharSequence(Constants.AA_TIME_SPENT_SCREEN_PARAMETER_NAME, activityName);
            logger.logEvent(Constants.AA_TIME_SPENT_EVENT_NAME, (double) timeSpentInSeconds, params);
        }
    }

    static void logPurchase(String purchase, String skuDetails, boolean isSubscription) {
        PurchaseLoggingParameters loggingParameters;
        boolean logAsSubs = false;
        if (isImplicitPurchaseLoggingEnabled() && (loggingParameters = getPurchaseLoggingParameters(purchase, skuDetails)) != null) {
            if (isSubscription && FetchedAppGateKeepersManager.getGateKeeperForKey(APP_EVENTS_IF_AUTO_LOG_SUBS, FacebookSdk.getApplicationId(), false)) {
                logAsSubs = true;
            }
            if (logAsSubs) {
                internalAppEventsLogger.logEventImplicitly(InAppPurchaseEventManager.hasFreeTrialPeirod(skuDetails) ? AppEventsConstants.EVENT_NAME_START_TRIAL : AppEventsConstants.EVENT_NAME_SUBSCRIBE, loggingParameters.purchaseAmount, loggingParameters.currency, loggingParameters.param);
            } else {
                internalAppEventsLogger.logPurchaseImplicitly(loggingParameters.purchaseAmount, loggingParameters.currency, loggingParameters.param);
            }
        }
    }

    public static boolean isImplicitPurchaseLoggingEnabled() {
        FetchedAppSettings settings = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        return settings != null && FacebookSdk.getAutoLogAppEventsEnabled() && settings.getIAPAutomaticLoggingEnabled();
    }

    @Nullable
    private static PurchaseLoggingParameters getPurchaseLoggingParameters(String purchase, String skuDetails) {
        return getPurchaseLoggingParameters(purchase, skuDetails, new HashMap());
    }

    @Nullable
    private static PurchaseLoggingParameters getPurchaseLoggingParameters(String purchase, String skuDetails, Map<String, String> extraParameter) {
        try {
            JSONObject purchaseJSON = new JSONObject(purchase);
            JSONObject skuDetailsJSON = new JSONObject(skuDetails);
            Bundle params = new Bundle(1);
            params.putCharSequence(Constants.IAP_PRODUCT_ID, purchaseJSON.getString("productId"));
            params.putCharSequence(Constants.IAP_PURCHASE_TIME, purchaseJSON.getString("purchaseTime"));
            params.putCharSequence(Constants.IAP_PURCHASE_TOKEN, purchaseJSON.getString("purchaseToken"));
            params.putCharSequence(Constants.IAP_PACKAGE_NAME, purchaseJSON.optString("packageName"));
            params.putCharSequence(Constants.IAP_PRODUCT_TITLE, skuDetailsJSON.optString("title"));
            params.putCharSequence(Constants.IAP_PRODUCT_DESCRIPTION, skuDetailsJSON.optString("description"));
            String type = skuDetailsJSON.optString("type");
            params.putCharSequence(Constants.IAP_PRODUCT_TYPE, type);
            if (type.equals(BillingClient.SkuType.SUBS)) {
                params.putCharSequence(Constants.IAP_SUBSCRIPTION_AUTORENEWING, Boolean.toString(purchaseJSON.optBoolean("autoRenewing", false)));
                params.putCharSequence(Constants.IAP_SUBSCRIPTION_PERIOD, skuDetailsJSON.optString("subscriptionPeriod"));
                params.putCharSequence(Constants.IAP_FREE_TRIAL_PERIOD, skuDetailsJSON.optString("freeTrialPeriod"));
                String introductoryPriceCycles = skuDetailsJSON.optString("introductoryPriceCycles");
                if (!introductoryPriceCycles.isEmpty()) {
                    params.putCharSequence(Constants.IAP_INTRO_PRICE_AMOUNT_MICROS, skuDetailsJSON.optString("introductoryPriceAmountMicros"));
                    params.putCharSequence(Constants.IAP_INTRO_PRICE_CYCLES, introductoryPriceCycles);
                }
            }
            for (String key : extraParameter.keySet()) {
                params.putCharSequence(key, extraParameter.get(key));
            }
            return new PurchaseLoggingParameters(new BigDecimal(((double) skuDetailsJSON.getLong("price_amount_micros")) / 1000000.0d), Currency.getInstance(skuDetailsJSON.getString("price_currency_code")), params);
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing in-app subscription data.", e);
            return null;
        }
    }

    private static class PurchaseLoggingParameters {
        Currency currency;
        Bundle param;
        BigDecimal purchaseAmount;

        PurchaseLoggingParameters(BigDecimal purchaseAmount2, Currency currency2, Bundle param2) {
            this.purchaseAmount = purchaseAmount2;
            this.currency = currency2;
            this.param = param2;
        }
    }
}
