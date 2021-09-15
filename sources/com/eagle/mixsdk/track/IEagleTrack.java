package com.eagle.mixsdk.track;

public interface IEagleTrack {
    void onEagleLogin(int i, String str);

    void onGameEvent(Object obj);

    void onInitEagle(int i, String str);

    void onInitSDK(int i, String str);

    void onLogout();

    void onNotifyLogin();

    void onOrderResult(int i, Object obj);

    void onPayResult(int i, Object obj);

    void onPayVerifyResult(int i, Object obj);

    void onPlayerAntiAddictionInfoResult();

    void onSDKLogin(int i, String str);

    void onShowLoginPage();

    void onStartLogin();

    void onStartOrder(Object obj);

    void onStartPay(Object obj);

    void onStartPayVerify(Object obj);

    void onStartPlayerAntiAddictionInfo();
}
