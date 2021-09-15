package com.eagle.mixsdk.sdk.listeners;

import java.util.Map;

public interface EagleInvokeListener {

    public interface CallbackListener {
        void onCallback(Object... objArr);
    }

    void onFailed(String str, String str2);

    void onSuccess(String str, Map<String, Object> map, CallbackListener callbackListener);
}
