package com.eagle.mixsdk.support.event;

import com.eagle.mixsdk.sdk.base.IEagleSDKListener;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import java.util.ArrayList;
import java.util.List;

public class EagleEvent extends com.eagle.mixsdk.sdk.other.EagleEvent {
    private static EagleEvent instance;
    private List<IEagleSDKListener> mSDKListener = new ArrayList();

    private EagleEvent() {
    }

    public static EagleEvent create(IEagleSDKListener listener) {
        if (instance == null) {
            instance = new EagleEvent();
        }
        instance.addSDKListener(listener);
        return instance;
    }

    public void onResult(int code, String msg) {
        for (IEagleSDKListener sdkListener : this.mSDKListener) {
            sdkListener.onResult(code, msg);
        }
    }

    public void onInitSuccess() {
        for (IEagleSDKListener sdkListener : this.mSDKListener) {
            sdkListener.onResult(1, "init success");
        }
    }

    public void onInitFail(String msg) {
        for (IEagleSDKListener sdkListener : this.mSDKListener) {
            sdkListener.onResult(2, msg);
        }
    }

    public void onLoginSuccess(EagleToken token) {
        for (IEagleSDKListener sdkListener : this.mSDKListener) {
            sdkListener.onLoginResult("");
            sdkListener.onAuthResult(token);
        }
    }

    public void onLoginFail(String msg) {
        for (IEagleSDKListener sdkListener : this.mSDKListener) {
            sdkListener.onResult(5, msg);
        }
    }

    public void onLogout() {
        for (IEagleSDKListener sdkListener : this.mSDKListener) {
            sdkListener.onLogout();
        }
    }

    public void onSwitchAccount(EagleToken token) {
        for (IEagleSDKListener sdkListener : this.mSDKListener) {
            sdkListener.onSwitchAccount("");
            sdkListener.onAuthResult(token);
        }
    }

    public void onPaySuccess() {
        for (IEagleSDKListener sdkListener : this.mSDKListener) {
            sdkListener.onResult(10, "pay success");
        }
    }

    public void onPayFail(String msg) {
        for (IEagleSDKListener sdkListener : this.mSDKListener) {
            sdkListener.onResult(11, msg);
        }
    }

    public void onPayCancel() {
        for (IEagleSDKListener sdkListener : this.mSDKListener) {
            sdkListener.onResult(33, "pay cancel");
        }
    }

    public void onPayUnknown() {
        for (IEagleSDKListener sdkListener : this.mSDKListener) {
            sdkListener.onResult(10, "pay unknown");
        }
    }

    public void addSDKListener(IEagleSDKListener listener) {
        if (listener != null && !this.mSDKListener.contains(listener)) {
            this.mSDKListener.add(listener);
        }
    }

    public List<IEagleSDKListener> getSDKListener() {
        return this.mSDKListener;
    }
}
