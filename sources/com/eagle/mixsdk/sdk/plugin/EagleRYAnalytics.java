package com.eagle.mixsdk.sdk.plugin;

import android.content.Context;
import com.eagle.mixsdk.sdk.IRYAnalytics;
import com.eagle.mixsdk.sdk.base.PluginFactory;
import java.util.Map;

public class EagleRYAnalytics {
    private static EagleRYAnalytics instance;
    private IRYAnalytics analyticsPlugin;

    private EagleRYAnalytics() {
    }

    public static EagleRYAnalytics getInstance() {
        if (instance == null) {
            instance = new EagleRYAnalytics();
        }
        return instance;
    }

    public void init() {
        this.analyticsPlugin = (IRYAnalytics) PluginFactory.getInstance().initPlugin(7);
    }

    public boolean isSupport(String method) {
        if (this.analyticsPlugin == null) {
            return false;
        }
        return this.analyticsPlugin.isSupportMethod(method);
    }

    public void initWithKeyAndChannelId(Context context, String channelId) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.initWithKeyAndChannelId(context, channelId);
        }
    }

    public void setRegisterWithAccountID(String accountId) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.setRegisterWithAccountID(accountId);
        }
    }

    public void setLoginSuccessBusiness(String accountId) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.setLoginSuccessBusiness(accountId);
        }
    }

    public void setPaymentStart(String transactionId, String paymentType, String currencyType, float currencyAmount) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.setPaymentStart(transactionId, paymentType, currencyType, currencyAmount);
        }
    }

    public void setPayment(String transactionId, String paymentType, String currencyType, float currencyAmount) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.setPayment(transactionId, paymentType, currencyType, currencyAmount);
        }
    }

    public void setEvent(String eventName, Map<String, Object> extra) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.setEvent(eventName, extra);
        }
    }

    public void setProfile(Map<String, Object> extra) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.setProfile(extra);
        }
    }

    public void exitSdk() {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.exitSdk();
        }
    }
}
