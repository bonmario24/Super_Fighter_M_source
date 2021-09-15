package com.xhuyu.component.mvp.view;

import org.json.JSONObject;

public interface PhoneView extends PhoneBaseView {
    void onComplete(JSONObject jSONObject, String str);

    void onError(String str, int i);
}
