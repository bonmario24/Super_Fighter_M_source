package com.eagle.mixsdk.sdk.listeners;

import com.eagle.mixsdk.sdk.verify.EagleInitConfig;

public interface EagleConfigResultListener {
    void onResult(int i, EagleInitConfig eagleInitConfig);
}
