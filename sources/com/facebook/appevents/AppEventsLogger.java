package com.facebook.appevents;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.Log;
import android.webkit.WebView;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import java.math.BigDecimal;
import java.util.Currency;

public class AppEventsLogger {
    public static final String ACTION_APP_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_FLUSHED";
    public static final String APP_EVENTS_EXTRA_FLUSH_RESULT = "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT";
    public static final String APP_EVENTS_EXTRA_NUM_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED";
    private static final String TAG = AppEventsLogger.class.getCanonicalName();
    private AppEventsLoggerImpl loggerImpl;

    public enum FlushBehavior {
        AUTO,
        EXPLICIT_ONLY
    }

    public enum ProductAvailability {
        IN_STOCK,
        OUT_OF_STOCK,
        PREORDER,
        AVALIABLE_FOR_ORDER,
        DISCONTINUED
    }

    public enum ProductCondition {
        NEW,
        REFURBISHED,
        USED
    }

    public static void activateApp(Application application) {
        AppEventsLoggerImpl.activateApp(application, (String) null);
    }

    public static void activateApp(Application application, String applicationId) {
        AppEventsLoggerImpl.activateApp(application, applicationId);
    }

    @Deprecated
    public static void activateApp(Context _context) {
        activateApp((Application) null, (String) null);
    }

    @Deprecated
    public static void activateApp(Context context, String _str) {
        AppEventsLoggerImpl.functionDEPRECATED("Please use activateApp(Application) or activateApp(Application, String)");
    }

    @Deprecated
    public static void deactivateApp(Context _context) {
        deactivateApp((Context) null, (String) null);
    }

    @Deprecated
    public static void deactivateApp(Context _context, String _str) {
        AppEventsLoggerImpl.functionDEPRECATED("deactivate app will be logged automatically");
    }

    public static void initializeLib(Context context, String applicationId) {
        AppEventsLoggerImpl.initializeLib(context, applicationId);
    }

    public static AppEventsLogger newLogger(Context context) {
        return new AppEventsLogger(context, (String) null, (AccessToken) null);
    }

    public static AppEventsLogger newLogger(Context context, AccessToken accessToken) {
        return new AppEventsLogger(context, (String) null, accessToken);
    }

    public static AppEventsLogger newLogger(Context context, String applicationId, AccessToken accessToken) {
        return new AppEventsLogger(context, applicationId, accessToken);
    }

    public static AppEventsLogger newLogger(Context context, String applicationId) {
        return new AppEventsLogger(context, applicationId, (AccessToken) null);
    }

    private AppEventsLogger(Context context, String applicationId, AccessToken accessToken) {
        this.loggerImpl = new AppEventsLoggerImpl(context, applicationId, accessToken);
    }

    public static FlushBehavior getFlushBehavior() {
        return AppEventsLoggerImpl.getFlushBehavior();
    }

    public static void setFlushBehavior(FlushBehavior flushBehavior) {
        AppEventsLoggerImpl.setFlushBehavior(flushBehavior);
    }

    public void logEvent(String eventName) {
        this.loggerImpl.logEvent(eventName);
    }

    public void logEvent(String eventName, double valueToSum) {
        this.loggerImpl.logEvent(eventName, valueToSum);
    }

    public void logEvent(String eventName, Bundle parameters) {
        this.loggerImpl.logEvent(eventName, parameters);
    }

    public void logEvent(String eventName, double valueToSum, Bundle parameters) {
        this.loggerImpl.logEvent(eventName, valueToSum, parameters);
    }

    public void logPurchase(BigDecimal purchaseAmount, Currency currency) {
        this.loggerImpl.logPurchase(purchaseAmount, currency);
    }

    public void logPurchase(BigDecimal purchaseAmount, Currency currency, Bundle parameters) {
        this.loggerImpl.logPurchase(purchaseAmount, currency, parameters);
    }

    @Deprecated
    public void logPurchaseImplicitly(BigDecimal purchaseAmount, Currency currency, Bundle parameters) {
        String errMsg;
        if (AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
            errMsg = "Function logPurchaseImplicitly() is deprecated and your purchase events cannot be logged with this function. " + "Auto-logging of in-app purchase has been enabled in the SDK, so you don't have to manually log purchases";
        } else {
            errMsg = "Function logPurchaseImplicitly() is deprecated and your purchase events cannot be logged with this function. " + "Please use logPurchase() function instead.";
        }
        Log.e(TAG, errMsg);
    }

    public void logPushNotificationOpen(Bundle payload) {
        this.loggerImpl.logPushNotificationOpen(payload, (String) null);
    }

    public void logPushNotificationOpen(Bundle payload, String action) {
        this.loggerImpl.logPushNotificationOpen(payload, action);
    }

    public void logProductItem(String itemID, ProductAvailability availability, ProductCondition condition, String description, String imageLink, String link, String title, BigDecimal priceAmount, Currency currency, String gtin, String mpn, String brand, Bundle parameters) {
        this.loggerImpl.logProductItem(itemID, availability, condition, description, imageLink, link, title, priceAmount, currency, gtin, mpn, brand, parameters);
    }

    public void flush() {
        this.loggerImpl.flush();
    }

    public static void onContextStop() {
        AppEventsLoggerImpl.onContextStop();
    }

    public boolean isValidForAccessToken(AccessToken accessToken) {
        return this.loggerImpl.isValidForAccessToken(accessToken);
    }

    public static void setPushNotificationsRegistrationId(String registrationId) {
        AppEventsLoggerImpl.setPushNotificationsRegistrationId(registrationId);
    }

    public static void augmentWebView(WebView webView, Context context) {
        AppEventsLoggerImpl.augmentWebView(webView, context);
    }

    public static void setUserID(String userID) {
        AnalyticsUserIDStore.setUserID(userID);
    }

    public static String getUserID() {
        return AnalyticsUserIDStore.getUserID();
    }

    public static void clearUserID() {
        AnalyticsUserIDStore.setUserID((String) null);
    }

    @Deprecated
    public static void setUserData(Bundle userData) {
        UserDataStore.setUserDataAndHash(userData);
    }

    public static void setUserData(@Nullable String email, @Nullable String firstName, @Nullable String lastName, @Nullable String phone, @Nullable String dateOfBirth, @Nullable String gender, @Nullable String city, @Nullable String state, @Nullable String zip, @Nullable String country) {
        UserDataStore.setUserDataAndHash(email, firstName, lastName, phone, dateOfBirth, gender, city, state, zip, country);
    }

    public static String getUserData() {
        return UserDataStore.getHashedUserData();
    }

    public static void clearUserData() {
        UserDataStore.clear();
    }

    public static void updateUserProperties(Bundle parameters, GraphRequest.Callback callback) {
        updateUserProperties(parameters, FacebookSdk.getApplicationId(), callback);
    }

    public static void updateUserProperties(Bundle parameters, String applicationID, GraphRequest.Callback callback) {
        AppEventsLoggerImpl.updateUserProperties(parameters, applicationID, callback);
    }

    @Deprecated
    public void logSdkEvent(String eventName, Double valueToSum, Bundle parameters) {
        this.loggerImpl.logSdkEvent(eventName, valueToSum, parameters);
    }

    public String getApplicationId() {
        return this.loggerImpl.getApplicationId();
    }

    public static String getAnonymousAppDeviceGUID(Context context) {
        return AppEventsLoggerImpl.getAnonymousAppDeviceGUID(context);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void setInstallReferrer(String referrer) {
        AppEventsLoggerImpl.setInstallReferrer(referrer);
    }
}
