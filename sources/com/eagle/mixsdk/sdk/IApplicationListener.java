package com.eagle.mixsdk.sdk;

import android.content.Context;
import android.content.res.Configuration;

public interface IApplicationListener {
    void onProxyAttachBaseContext(Context context);

    void onProxyConfigurationChanged(Configuration configuration);

    void onProxyCreate();

    void onProxyTerminate();
}
