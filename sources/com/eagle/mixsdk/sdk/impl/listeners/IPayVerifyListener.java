package com.eagle.mixsdk.sdk.impl.listeners;

public interface IPayVerifyListener {
    void onVerifyFail(String str, Object obj);

    void onVerifySuccess(Object obj);
}
