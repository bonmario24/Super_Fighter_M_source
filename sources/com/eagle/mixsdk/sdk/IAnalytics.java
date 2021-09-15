package com.eagle.mixsdk.sdk;

public interface IAnalytics extends IPlugin {
    public static final int PLUGIN_TYPE = 5;

    void bonus(String str, int i, double d, int i2);

    void buy(String str, int i, double d);

    void failLevel(String str);

    void failTask(String str);

    void finishLevel(String str);

    void finishTask(String str);

    void levelup(int i);

    void login(String str);

    void logout();

    void pay(String str, double d, int i);

    void payRequest(String str, String str2, double d, String str3);

    void startLevel(String str);

    void startTask(String str, String str2);

    void use(String str, int i, double d);
}
