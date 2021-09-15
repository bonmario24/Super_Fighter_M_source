package com.eagle.mixsdk.sdk.impl.listeners;

import com.eagle.mixsdk.sdk.verify.EagleOrder;

public interface ISDKOrderIDListener {
    void onFailed(int i, String str);

    void onSuccess(EagleOrder eagleOrder);
}
