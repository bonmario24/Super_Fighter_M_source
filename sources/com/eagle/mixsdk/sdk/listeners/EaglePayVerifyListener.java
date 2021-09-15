package com.eagle.mixsdk.sdk.listeners;

import org.json.JSONObject;

public interface EaglePayVerifyListener {
    void onVerifyFail(String str);

    void onVerifySuccess(JSONObject jSONObject);
}
