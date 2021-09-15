package com.eagle.mixsdk.sdk;

import android.app.Activity;

public class HuYuPay implements IPay {
    public HuYuPay(Activity context) {
    }

    public boolean isSupportMethod(String methodName) {
        return true;
    }

    public void pay(PayParams data) {
        HuYuSDK.getInstance().pay(data);
    }
}
