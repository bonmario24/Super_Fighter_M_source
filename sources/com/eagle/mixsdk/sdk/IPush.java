package com.eagle.mixsdk.sdk;

public interface IPush extends IPlugin {
    public static final int PLUGIN_TYPE = 3;

    void addAlias(String str);

    void addTags(String... strArr);

    void removeAlias(String str);

    void removeTags(String... strArr);

    void scheduleNotification(String str);

    void startPush();

    void stopPush();
}
