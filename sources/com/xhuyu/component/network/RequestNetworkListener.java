package com.xhuyu.component.network;

import com.xsdk.doraemon.okhttp.model.ExtensionInfo;
import org.json.JSONObject;

public interface RequestNetworkListener {
    void onFail(String str, int i);

    void onSuccess(JSONObject jSONObject, String str, ExtensionInfo extensionInfo);
}
