package com.eagle.mixsdk.sdk.okhttp.imp;

import com.doraemon.okhttp3.Call;
import com.doraemon.okhttp3.Callback;
import org.json.JSONObject;

public interface CallBackListener {
    void onFailure(Callback callback, Call call, String str);

    void onSuccess(JSONObject jSONObject, Object obj);
}
