package com.xsdk.doraemon.okhttp.imp;

import okhttp3.Call;
import okhttp3.Callback;
import org.json.JSONObject;

public interface CallBackListener {
    void onFailure(Callback callback, Call call, String str);

    void onSuccess(JSONObject jSONObject, Object obj);
}
