package com.facebook.appevents;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.concurrent.Executor;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class InternalAppEventsLogger {
    private AppEventsLoggerImpl loggerImpl;

    public InternalAppEventsLogger(Context context) {
        this.loggerImpl = new AppEventsLoggerImpl(context, (String) null, (AccessToken) null);
    }

    public InternalAppEventsLogger(Context context, String applicationId) {
        this.loggerImpl = new AppEventsLoggerImpl(context, applicationId, (AccessToken) null);
    }

    public InternalAppEventsLogger(String activityName, String applicationId, AccessToken accessToken) {
        this.loggerImpl = new AppEventsLoggerImpl(activityName, applicationId, accessToken);
    }

    public void logEvent(String eventName, Bundle parameters) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logEvent(eventName, parameters);
        }
    }

    public void logEvent(String eventName, double valueToSum, Bundle parameters) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logEvent(eventName, valueToSum, parameters);
        }
    }

    public void logPurchaseImplicitly(BigDecimal purchaseAmount, Currency currency, Bundle parameters) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logPurchaseImplicitly(purchaseAmount, currency, parameters);
        }
    }

    public void logEventImplicitly(String eventName, BigDecimal purchaseAmount, Currency currency, Bundle parameters) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logEventImplicitly(eventName, purchaseAmount, currency, parameters);
        }
    }

    public void logEventImplicitly(String eventName) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logEventImplicitly(eventName, (Double) null, (Bundle) null);
        }
    }

    public void logEventImplicitly(String eventName, Double valueToSum, Bundle parameters) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logEventImplicitly(eventName, valueToSum, parameters);
        }
    }

    public void logEventImplicitly(String eventName, Bundle parameters) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logEventImplicitly(eventName, (Double) null, parameters);
        }
    }

    public static AppEventsLogger.FlushBehavior getFlushBehavior() {
        return AppEventsLoggerImpl.getFlushBehavior();
    }

    public void flush() {
        this.loggerImpl.flush();
    }

    static Executor getAnalyticsExecutor() {
        return AppEventsLoggerImpl.getAnalyticsExecutor();
    }

    static String getPushNotificationsRegistrationId() {
        return AppEventsLoggerImpl.getPushNotificationsRegistrationId();
    }

    public static void setUserData(Bundle userData) {
        UserDataStore.setUserDataAndHash(userData);
    }
}
