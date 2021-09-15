package com.eagle.mixsdk.sdk.impl.listeners;

import com.eagle.mixsdk.sdk.verify.EagleToken;

public interface IInnerEvent {
    void onInitFail(String str);

    void onInitSuccess();

    void onLoginFail(String str);

    void onLoginSuccess(EagleToken eagleToken);

    void onLogout();

    void onPayCancel();

    void onPayFail(String str);

    void onPaySuccess();

    void onPayUnknown();

    void onResult(int i, String str);

    void onSwitchAccount(EagleToken eagleToken);
}
