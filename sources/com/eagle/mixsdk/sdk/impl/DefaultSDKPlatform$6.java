package com.eagle.mixsdk.sdk.impl;

import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.PayParams;
import com.eagle.mixsdk.sdk.test.listeners.ISDKPayListener;
import com.eagle.mixsdk.sdk.test.services.TestSdkManager;
import com.eagle.mixsdk.sdk.test.widgets.ExtendDialog;

class DefaultSDKPlatform$6 implements Runnable {
    final /* synthetic */ DefaultSDKPlatform this$0;
    final /* synthetic */ PayParams val$data;
    final /* synthetic */ String val$msg;

    DefaultSDKPlatform$6(DefaultSDKPlatform defaultSDKPlatform, String str, PayParams payParams) {
        this.this$0 = defaultSDKPlatform;
        this.val$msg = str;
        this.val$data = payParams;
    }

    public void run() {
        ExtendDialog.createDialog(EagleSDK.getInstance().getContext(), this.val$msg, new ExtendDialog.ExtendDialogListener() {
            public void onResult() {
                TestSdkManager.getInstance().pay(EagleSDK.getInstance().getContext(), DefaultSDKPlatform$6.this.val$data, new ISDKPayListener() {
                    public void onFailed(int i) {
                        EagleSDK.getInstance().onResult(11, "pay failed.");
                    }

                    public void onSuccess(String str) {
                        EagleSDK.getInstance().onResult(10, "pay success");
                    }
                });
            }
        });
    }
}
