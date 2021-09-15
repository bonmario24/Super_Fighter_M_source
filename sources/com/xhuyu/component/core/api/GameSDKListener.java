package com.xhuyu.component.core.api;

import org.json.JSONObject;

public interface GameSDKListener {
    void onFail(String str, int i);

    void onSuccess(JSONObject jSONObject, String str, Object... objArr);
}
