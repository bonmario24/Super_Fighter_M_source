package com.eagle.mixsdk.sdk;

import android.app.Activity;
import com.eagle.mixsdk.sdk.utils.Arrays;

public class HuYuUser extends EagleUserAdapter {
    private String[] supportedMethods = {"login", "logout", "submitExtraData", "showAccountCenter", "exit"};

    public HuYuUser(Activity context) {
        HuYuSDK.getInstance().initSDK();
    }

    public boolean isSupportMethod(String methodName) {
        return Arrays.contain(this.supportedMethods, methodName);
    }

    public void login() {
        HuYuSDK.getInstance().login();
    }

    public void logout() {
        HuYuSDK.getInstance().logout();
    }

    public void submitExtraData(UserExtraData extraData) {
        HuYuSDK.getInstance().submitExtraData(extraData);
    }

    public boolean showAccountCenter() {
        return HuYuSDK.getInstance().showAccountCenter();
    }

    public void exit() {
        HuYuSDK.getInstance().exitSDK();
    }
}
