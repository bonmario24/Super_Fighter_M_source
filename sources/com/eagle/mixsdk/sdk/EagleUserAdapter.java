package com.eagle.mixsdk.sdk;

import com.eagle.mixsdk.sdk.listeners.EagleCertificationInfoListener;
import com.eagle.mixsdk.sdk.listeners.EagleGetUserInfoListener;
import com.eagle.mixsdk.sdk.platform.EagleBindMobileListener;
import com.eagle.mixsdk.sdk.plugin.EagleUser;
import com.eagle.mixsdk.sdk.verify.EagleCertificationInfo;
import com.eagle.mixsdk.sdk.verify.EagleProxy;

public abstract class EagleUserAdapter implements IUser {
    public abstract boolean isSupportMethod(String str);

    public void login() {
    }

    public void loginCustom(String customData) {
    }

    public void switchLogin() {
    }

    public boolean showAccountCenter() {
        return false;
    }

    public void logout() {
    }

    public void submitExtraData(UserExtraData extraData) {
    }

    public void exit() {
    }

    public void postGiftCode(String code) {
    }

    public void realNameRegister() {
    }

    public void queryAntiAddiction() {
    }

    public void queryProducts() {
    }

    public String getJsonExt() {
        return "";
    }

    public void bindMobile(EagleBindMobileListener listener) {
    }

    public void getPlayerCertificationInfo(EagleCertificationInfoListener listener) {
        EagleCertificationInfo certificationInfo;
        if (EagleUser.getInstance().getEagleToken().getAntiAddictionSwitch() != 1 || (certificationInfo = EagleUser.getInstance().getEagleToken().getCertificationInfo()) == null) {
            listener.onResult(EagleProxy.createDefaultCertificationInfo());
        } else {
            listener.onResult(certificationInfo);
        }
    }

    public void doGetPlayerCertificationInfo(EagleCertificationInfoListener listener) {
        EagleProxy.doGetPlayerCertificationInfo(listener);
    }

    public void getUserInfo(String extInfo, EagleGetUserInfoListener userInfoListener) {
        EagleProxy.doGetUserInfo(extInfo, userInfoListener);
    }
}
