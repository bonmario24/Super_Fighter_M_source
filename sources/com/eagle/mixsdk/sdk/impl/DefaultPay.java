package com.eagle.mixsdk.sdk.impl;

import android.widget.Toast;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.IPay;
import com.eagle.mixsdk.sdk.PayParams;

public class DefaultPay implements IPay {
    public boolean isSupportMethod(String methodName) {
        return true;
    }

    public void pay(PayParams data) {
        Toast.makeText(EagleSDK.getInstance().getContext(), "调用[支付接口]接口成功，PayParams中的参数，除了extension，其他的请都赋值，最后还需要经过打包工具来打出最终的渠道包", 1).show();
    }
}
