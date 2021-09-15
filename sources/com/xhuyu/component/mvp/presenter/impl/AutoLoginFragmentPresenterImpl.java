package com.xhuyu.component.mvp.presenter.impl;

import android.os.CountDownTimer;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.mvp.model.LoginResult;
import com.xhuyu.component.mvp.presenter.AutoLoginFragmentPresenter;
import com.xhuyu.component.mvp.view.AutoLoginFragmentView;
import com.xhuyu.component.network.NetLoginUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import org.json.JSONObject;

public class AutoLoginFragmentPresenterImpl implements AutoLoginFragmentPresenter {
    private int countDownTime;
    private int delay;
    /* access modifiers changed from: private */
    public AutoLoginFragmentView mAutoLoginView;
    private CountDownTimer mCountDownTimer;

    public AutoLoginFragmentPresenterImpl(AutoLoginFragmentView autoLoginView) {
        this.mAutoLoginView = autoLoginView;
    }

    public void setCountDownTime(int countDownTime2) {
        this.countDownTime = countDownTime2;
    }

    public void setDelay(int delay2) {
        this.delay = delay2;
    }

    public void createCountDownTimer() {
        if (this.mCountDownTimer == null) {
            this.mCountDownTimer = new CountDownTimer((long) this.countDownTime, (long) this.delay) {
                public void onTick(long millisUntilFinished) {
                    AutoLoginFragmentPresenterImpl.this.mAutoLoginView.refreshTick(millisUntilFinished);
                }

                public void onFinish() {
                    AutoLoginFragmentPresenterImpl.this.doNetPostAutoLogin();
                }
            }.start();
        }
    }

    public void dismissCountDown() {
        if (this.mCountDownTimer != null) {
            this.mCountDownTimer.cancel();
            this.mCountDownTimer = null;
        }
    }

    public void postLoginResult(int resultCode, String dataInfo) {
        SDKEventPost.post(3, new LoginResult(10, resultCode, dataInfo));
    }

    /* access modifiers changed from: private */
    public void doNetPostAutoLogin() {
        this.mAutoLoginView.showDialog();
        NetLoginUtil.postAutoLogin(new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                SDKLoggerUtil.getLogger().mo19501d("自动登陆成功", new Object[0]);
                AutoLoginFragmentPresenterImpl.this.dismissCountDown();
                AutoLoginFragmentPresenterImpl.this.postEvent(1, dataJson.toString());
                AutoLoginFragmentPresenterImpl.this.mAutoLoginView.onLoginSuccess();
            }

            public void onFail(String message, int code) {
                AutoLoginFragmentPresenterImpl.this.dismissCountDown();
                AutoLoginFragmentPresenterImpl.this.postEvent(code, message);
                AutoLoginFragmentPresenterImpl.this.mAutoLoginView.onLoginFailed(message, code);
            }
        });
    }

    /* access modifiers changed from: private */
    public void postEvent(int responseCode, String message) {
        this.mAutoLoginView.closeLoadingDialog();
        SDKEventPost.post(3, new LoginResult(10, responseCode, message));
    }
}
