package com.eagle.mixsdk.sdk.plugin;

import com.eagle.mixsdk.sdk.IAnalytics;
import com.eagle.mixsdk.sdk.base.PluginFactory;

public class EagleAnalytics {
    private static EagleAnalytics instance;
    private IAnalytics analyticsPlugin;

    private EagleAnalytics() {
    }

    public static EagleAnalytics getInstance() {
        if (instance == null) {
            instance = new EagleAnalytics();
        }
        return instance;
    }

    public void init() {
        this.analyticsPlugin = (IAnalytics) PluginFactory.getInstance().initPlugin(5);
    }

    public boolean isSupport(String method) {
        if (this.analyticsPlugin == null) {
            return false;
        }
        return this.analyticsPlugin.isSupportMethod(method);
    }

    public void startLevel(String level) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.startLevel(level);
        }
    }

    public void failLevel(String level) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.failLevel(level);
        }
    }

    public void finishLevel(String level) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.finishLevel(level);
        }
    }

    public void startTask(String task, String type) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.startTask(task, type);
        }
    }

    public void failTask(String task) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.failTask(task);
        }
    }

    public void finishTask(String task) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.finishTask(task);
        }
    }

    public void payRequest(String orderID, String productID, double money, String currency) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.payRequest(orderID, productID, money, currency);
        }
    }

    public void pay(String orderID, double money, int num) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.pay(orderID, money, num);
        }
    }

    public void buy(String item, int num, double price) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.buy(item, num, price);
        }
    }

    public void use(String item, int num, double price) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.use(item, num, price);
        }
    }

    public void bonus(String item, int num, double price, int trigger) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.bonus(item, num, price, trigger);
        }
    }

    public void login(String userID) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.login(userID);
        }
    }

    public void logout() {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.logout();
        }
    }

    public void levelup(int level) {
        if (this.analyticsPlugin != null) {
            this.analyticsPlugin.levelup(level);
        }
    }
}
