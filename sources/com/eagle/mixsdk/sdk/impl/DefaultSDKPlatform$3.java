package com.eagle.mixsdk.sdk.impl;

import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.test.listeners.ISDKLogoutListener;
import org.json.JSONObject;

class DefaultSDKPlatform$3 implements ISDKLogoutListener {
    final /* synthetic */ DefaultSDKPlatform this$0;

    DefaultSDKPlatform$3(DefaultSDKPlatform defaultSDKPlatform) {
        this.this$0 = defaultSDKPlatform;
    }

    public void onFail() {
    }

    public void onLogout() {
        EagleSDK.getInstance().onLogout();
    }

    public void onSwitch(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("userId", str);
            jSONObject.put("username", str2);
            EagleSDK.getInstance().onSwitchAccount(jSONObject.toString());
        } catch (Exception e) {
            EagleSDK.getInstance().onResult(5, e.getMessage());
            e.printStackTrace();
        }
    }
}
