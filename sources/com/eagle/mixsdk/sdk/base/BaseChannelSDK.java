package com.eagle.mixsdk.sdk.base;

import android.text.TextUtils;
import android.util.Log;
import com.eagle.mixsdk.sdk.ActivityCallbackAdapter;
import com.eagle.mixsdk.sdk.EagleSDK;

public class BaseChannelSDK extends ActivityCallbackAdapter {
    /* access modifiers changed from: protected */
    public boolean isEmpty(String str) {
        return TextUtils.isEmpty(str) || "null".equals(str);
    }

    /* access modifiers changed from: protected */
    public void writeLog(String string) {
        Log.e(Constants.TAG, string);
    }

    /* access modifiers changed from: protected */
    public void exitGame() {
        EagleSDK.getInstance().exitGame();
    }

    /* access modifiers changed from: protected */
    public long changeSecondTime(long time) {
        String tTime = time + "";
        if (tTime.length() <= 10) {
            return time;
        }
        try {
            return time / ((long) Math.pow(10.0d, (double) (tTime.length() - 10)));
        } catch (Exception e) {
            e.printStackTrace();
            return time;
        }
    }

    /* access modifiers changed from: protected */
    public void onInitSuc() {
        EagleSDK.getInstance().onResult(1, "init success");
    }

    /* access modifiers changed from: protected */
    public void onInitFail(String msg) {
        EagleSDK.getInstance().onResult(2, "init failed:" + msg);
    }

    /* access modifiers changed from: protected */
    public void onLoginFail(String msg) {
        EagleSDK.getInstance().onResult(5, "login failed:" + msg);
    }

    /* access modifiers changed from: protected */
    public void onLogout() {
        EagleSDK.getInstance().onLogout();
    }

    /* access modifiers changed from: protected */
    public void onPaySuc() {
        EagleSDK.getInstance().onResult(10, "pay success");
    }

    /* access modifiers changed from: protected */
    public void onPayFail(String msg) {
        EagleSDK.getInstance().onResult(11, "pay failed:" + msg);
    }

    /* access modifiers changed from: protected */
    public void onPayCancel() {
        EagleSDK.getInstance().onResult(33, "pay cancel");
    }
}
