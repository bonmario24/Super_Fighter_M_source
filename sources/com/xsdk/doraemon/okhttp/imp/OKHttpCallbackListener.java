package com.xsdk.doraemon.okhttp.imp;

import com.xsdk.doraemon.okhttp.model.ExtensionInfo;
import org.json.JSONObject;

public interface OKHttpCallbackListener {
    void onFailure(int i, String str, ExtensionInfo extensionInfo);

    void onSuccess(JSONObject jSONObject, String str, ExtensionInfo extensionInfo);
}
