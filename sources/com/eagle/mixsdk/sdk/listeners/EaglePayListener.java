package com.eagle.mixsdk.sdk.listeners;

import com.eagle.mixsdk.sdk.base.BaseListener;

public interface EaglePayListener extends BaseListener {
    void onCancel();

    void onUnknown();
}
