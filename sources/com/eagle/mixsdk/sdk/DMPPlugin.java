package com.eagle.mixsdk.sdk;

import org.json.JSONObject;

public interface DMPPlugin extends IPlugin {
    public static final int PLUGIN_TYPE = 12;

    void init();

    void logActionNoParams(String str);

    void logActionOrder();

    void logActionParams(String str, JSONObject jSONObject);

    void logActionPurchase(JSONObject jSONObject);

    void logActionRegister();
}
