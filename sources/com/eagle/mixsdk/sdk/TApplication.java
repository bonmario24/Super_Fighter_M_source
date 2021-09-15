package com.eagle.mixsdk.sdk;

import android.content.Context;
import android.content.res.Configuration;
import com.doraemon.util.language.LanguageUtil;
import com.xhuyu.component.core.api.SDKApplication;

public class TApplication extends SDKApplication {
    public void onCreate() {
        super.onCreate();
        EagleSDK.getInstance().onAppCreate(this);
    }

    public void attachBaseContext(Context base) {
        EagleSDK.getInstance().loadLanguageConfig(base);
        Context base1 = LanguageUtil.getInstance().attachBaseContext(base);
        super.attachBaseContext(base1);
        EagleSDK.getInstance().onAppAttachBaseContext(this, base1);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        EagleSDK.getInstance().onAppConfigurationChanged(this, newConfig);
    }

    public void onTerminate() {
        super.onTerminate();
        EagleSDK.getInstance().onTerminate();
    }
}
