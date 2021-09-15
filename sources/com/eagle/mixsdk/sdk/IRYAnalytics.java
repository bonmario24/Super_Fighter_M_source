package com.eagle.mixsdk.sdk;

import android.content.Context;
import java.util.Map;

public interface IRYAnalytics extends IPlugin {
    public static final int PLUGIN_TYPE = 7;

    void exitSdk();

    void initWithKeyAndChannelId(Context context, String str);

    void setEvent(String str, Map<String, Object> map);

    void setLoginSuccessBusiness(String str);

    void setPayment(String str, String str2, String str3, float f);

    void setPaymentStart(String str, String str2, String str3, float f);

    void setProfile(Map<String, Object> map);

    void setRegisterWithAccountID(String str);
}
