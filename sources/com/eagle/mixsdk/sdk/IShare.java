package com.eagle.mixsdk.sdk;

import com.eagle.mixsdk.sdk.impl.listeners.ISDKShareListener;

public interface IShare extends IPlugin {
    public static final int PLUGIN_TYPE = 4;

    void share(ShareParams shareParams, ISDKShareListener iSDKShareListener);
}
