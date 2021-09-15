package com.eagle.mixsdk.sdk.okhttp.imp;

import com.eagle.mixsdk.sdk.okhttp.model.ExtensionInfo;
import org.json.JSONObject;

public interface OKHttpCallbackListener {
    void onFailure(String str, ExtensionInfo extensionInfo);

    void onSuccess(JSONObject jSONObject, ExtensionInfo extensionInfo);
}
