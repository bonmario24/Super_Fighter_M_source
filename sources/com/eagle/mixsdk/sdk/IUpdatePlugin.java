package com.eagle.mixsdk.sdk;

import android.app.Activity;
import com.eagle.mixsdk.sdk.impl.listeners.IEagleUpdateListener;

public interface IUpdatePlugin extends IPlugin {
    public static final int PLUGIN_TYPE = 13;

    void init(Activity activity);

    void isNeedToUpdate(Activity activity, IEagleUpdateListener iEagleUpdateListener);
}
