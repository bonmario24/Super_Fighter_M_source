package com.eagle.mixsdk.sdk;

public interface IPay extends IPlugin {
    public static final int PLUGIN_TYPE = 2;

    void pay(PayParams payParams);
}
