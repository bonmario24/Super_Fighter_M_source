package com.eagle.mixsdk.sdk.impl.listeners;

import com.eagle.mixsdk.sdk.verify.EagleToken;

public interface ILoginListener {
    void onLoginFail(String str);

    void onLoginSuccess(EagleToken eagleToken);
}
