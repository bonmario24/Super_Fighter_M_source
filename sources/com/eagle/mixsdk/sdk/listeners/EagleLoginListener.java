package com.eagle.mixsdk.sdk.listeners;

import com.eagle.mixsdk.sdk.impl.listeners.ILoginListener;
import com.eagle.mixsdk.sdk.verify.EagleToken;

public interface EagleLoginListener extends ILoginListener {
    void onLogout();

    void onSwitchAccount(EagleToken eagleToken);
}
