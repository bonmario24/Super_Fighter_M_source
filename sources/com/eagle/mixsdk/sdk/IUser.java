package com.eagle.mixsdk.sdk;

import com.eagle.mixsdk.sdk.listeners.EagleCertificationInfoListener;
import com.eagle.mixsdk.sdk.listeners.EagleGetUserInfoListener;
import com.eagle.mixsdk.sdk.platform.EagleBindMobileListener;

public interface IUser extends IPlugin {
    public static final int PLUGIN_TYPE = 1;

    void bindMobile(EagleBindMobileListener eagleBindMobileListener);

    void exit();

    String getJsonExt();

    void getPlayerCertificationInfo(EagleCertificationInfoListener eagleCertificationInfoListener);

    void getUserInfo(String str, EagleGetUserInfoListener eagleGetUserInfoListener);

    void login();

    void loginCustom(String str);

    void logout();

    void postGiftCode(String str);

    @Deprecated
    void queryAntiAddiction();

    void queryProducts();

    @Deprecated
    void realNameRegister();

    boolean showAccountCenter();

    void submitExtraData(UserExtraData userExtraData);

    void switchLogin();
}
