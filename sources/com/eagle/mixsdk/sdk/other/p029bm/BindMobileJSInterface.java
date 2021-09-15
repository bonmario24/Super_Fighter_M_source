package com.eagle.mixsdk.sdk.other.p029bm;

import android.util.Log;
import android.webkit.JavascriptInterface;
import com.doraemon.util.ShellAdbUtil;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.listeners.EagleBindMobileListener;

/* renamed from: com.eagle.mixsdk.sdk.other.bm.BindMobileJSInterface */
public class BindMobileJSInterface {
    public static final int ACTION_BIND_MOBILE = 12;
    public static final int ACTION_CLOSE = 13;
    public static final int ACTION_VERIFY_CODE = 11;
    /* access modifiers changed from: private */
    public final IJsCallBack mJsCallback;

    /* renamed from: com.eagle.mixsdk.sdk.other.bm.BindMobileJSInterface$IJsCallBack */
    public interface IJsCallBack {
        void onResult(int i, int i2, String str);
    }

    public BindMobileJSInterface(IJsCallBack iJsCallBack) {
        this.mJsCallback = iJsCallBack;
    }

    @JavascriptInterface
    public String _sendCaptcha(String phoneNum) {
        Log.w(Constants.TAG, "_sendCaptcha " + phoneNum);
        BindMobileHelper.getInstance().sendVerifyCode(phoneNum, new EagleBindMobileListener() {
            public void onResult(int code, String msg) {
                if (BindMobileJSInterface.this.mJsCallback != null) {
                    BindMobileJSInterface.this.mJsCallback.onResult(11, code, msg);
                }
            }
        });
        return "";
    }

    @JavascriptInterface
    public String _bindingMobile(String captcha, String phoneNum, String roleData) {
        Log.w(Constants.TAG, "_bindingMobile " + captcha + " " + phoneNum + ShellAdbUtil.COMMAND_LINE_END + roleData);
        BindMobileHelper.getInstance().bindingMobile(captcha, phoneNum, roleData, new EagleBindMobileListener() {
            public void onResult(int code, String msg) {
                if (BindMobileJSInterface.this.mJsCallback != null) {
                    BindMobileJSInterface.this.mJsCallback.onResult(12, code, msg);
                }
            }
        });
        return "";
    }

    @JavascriptInterface
    public String _sendGift(final String tips) {
        Log.w(Constants.TAG, "tips " + tips);
        EagleSDK.getInstance().runOnMainThread(new Runnable() {
            public void run() {
                if (BindMobileJSInterface.this.mJsCallback != null) {
                    BindMobileJSInterface.this.mJsCallback.onResult(12, 1, tips);
                }
            }
        });
        return "";
    }

    @JavascriptInterface
    public void _close() {
        EagleSDK.getInstance().runOnMainThread(new Runnable() {
            public void run() {
                if (BindMobileJSInterface.this.mJsCallback != null) {
                    BindMobileJSInterface.this.mJsCallback.onResult(13, 1, "");
                }
            }
        });
    }
}
