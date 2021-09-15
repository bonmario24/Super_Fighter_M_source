package com.xhuyu.component.mvp.presenter.impl;

import com.doraemon.p027eg.CheckUtils;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.mvp.presenter.EmailPresenter;
import com.xhuyu.component.mvp.view.EmailView;
import com.xhuyu.component.network.NetUserUtil;
import org.json.JSONObject;

public class EmailPresenterImpl implements EmailPresenter {
    /* access modifiers changed from: private */
    public EmailView mView;
    /* access modifiers changed from: private */
    public String sendCodeEmail = "#";

    public EmailPresenterImpl(EmailView view) {
        this.mView = view;
    }

    public void requestVerificationCode(final String email) {
        if (CheckUtils.isNullOrEmpty(email)) {
            this.mView.showToastMessage(true, "huyu_hint_input_email");
            return;
        }
        this.mView.showDialog();
        NetUserUtil.postGetEmailVerifyCode(email, new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                EmailPresenterImpl.this.mView.showToastMessage(false, message);
                EmailPresenterImpl.this.mView.closeLoadingDialog();
                EmailPresenterImpl.this.mView.startTimerToVerificationCode();
                String unused = EmailPresenterImpl.this.sendCodeEmail = email;
            }

            public void onFail(String message, int code) {
                EmailPresenterImpl.this.mView.showToastMessage(false, message);
                EmailPresenterImpl.this.mView.closeLoadingDialog();
            }
        });
    }

    public void requestBindEmail(String email, String verifyCode) {
        if (CheckUtils.isNullOrEmpty(email)) {
            this.mView.showToastMessage(true, "huyu_hint_input_email");
        } else if (CheckUtils.isNullOrEmpty(verifyCode)) {
            this.mView.showToastMessage(true, "huyu_tip_msm_code");
        } else if (!this.sendCodeEmail.equals(email)) {
            this.mView.showToastMessage(true, "huyu_tip_no_send_code");
        } else {
            this.mView.showDialog();
            NetUserUtil.postBindEmail(email, verifyCode, new GameSDKListener() {
                public void onSuccess(JSONObject dataJson, String message, Object... args) {
                    EmailPresenterImpl.this.mView.showToastMessage(false, message);
                    EmailPresenterImpl.this.mView.closeLoadingDialog();
                    EmailPresenterImpl.this.mView.onComplete(dataJson, message);
                }

                public void onFail(String message, int code) {
                    EmailPresenterImpl.this.mView.showToastMessage(false, message);
                    EmailPresenterImpl.this.mView.closeLoadingDialog();
                    EmailPresenterImpl.this.mView.onError(message, code);
                }
            });
        }
    }
}
