package com.eagle.mixsdk.sdk.other;

import com.eagle.mixsdk.sdk.impl.listeners.IInnerEvent;
import com.eagle.mixsdk.sdk.verify.EagleToken;

public class EagleEvent implements IInnerEvent {
    public void onEagleInitSuc() {
    }

    public void onEagleInitFail(String msg) {
    }

    public void onResult(int code, String msg) {
    }

    public void onInitSuccess() {
    }

    public void onInitFail(String msg) {
    }

    public void onLoginSuccess(EagleToken token) {
    }

    public void onLoginFail(String msg) {
    }

    public void onLogout() {
    }

    public void onSwitchAccount(EagleToken token) {
    }

    public void onPaySuccess() {
    }

    public void onPayFail(String msg) {
    }

    public void onPayCancel() {
    }

    public void onPayUnknown() {
    }
}
