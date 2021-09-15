package com.eagle.mixsdk.sdk.impl;

import android.util.Log;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.test.listeners.ISDKListener;

class DefaultSDKPlatform$4 implements ISDKListener {
    final /* synthetic */ DefaultSDKPlatform this$0;

    DefaultSDKPlatform$4(DefaultSDKPlatform defaultSDKPlatform) {
        this.this$0 = defaultSDKPlatform;
    }

    public void onFailed(int i) {
        Log.e(Constants.TAG, "default sdk inti failed.");
        EagleSDK.getInstance().onResult(2, "init failed");
    }

    public void onSuccess() {
        Log.d(Constants.TAG, "default sdk init success");
        EagleSDK.getInstance().onResult(1, "init success");
    }
}
