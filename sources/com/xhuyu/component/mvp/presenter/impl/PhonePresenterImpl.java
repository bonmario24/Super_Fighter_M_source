package com.xhuyu.component.mvp.presenter.impl;

import com.doraemon.p027eg.CheckUtils;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.mvp.presenter.PhonePresenter;
import com.xhuyu.component.mvp.view.PhoneView;
import com.xhuyu.component.network.NetUserUtil;
import org.json.JSONObject;

public class PhonePresenterImpl implements PhonePresenter {
    PhoneView mView;
    /* access modifiers changed from: private */
    public String sendCodePhone = "#";

    public PhonePresenterImpl(PhoneView view) {
        this.mView = view;
    }

    public void requestVerificationCode(final String phoneNumber, String areaCode, String areaAbbreviation) {
        if (checkInfo(phoneNumber, "hint_input_phone")) {
            this.mView.showDialog();
            NetUserUtil.postGetVerifyCodeBind(phoneNumber, areaCode, areaAbbreviation, new GameSDKListener() {
                public void onSuccess(JSONObject dataJson, String message, Object... args) {
                    PhonePresenterImpl.this.mView.showToastMessage(false, message);
                    PhonePresenterImpl.this.mView.closeLoadingDialog();
                    PhonePresenterImpl.this.mView.startTimerToVerificationCode();
                    String unused = PhonePresenterImpl.this.sendCodePhone = phoneNumber;
                }

                public void onFail(String message, int code) {
                    PhonePresenterImpl.this.mView.showToastMessage(false, message);
                    PhonePresenterImpl.this.mView.closeLoadingDialog();
                }
            });
        }
    }

    public void requestBindPhone(String phoneNumber, String verificationCode, String areaCode, String areaAbbreviation) {
        if (checkForBindPhone(phoneNumber, verificationCode)) {
            this.mView.showDialog();
            NetUserUtil.postBindPhone(phoneNumber, verificationCode, areaCode, areaAbbreviation, new GameSDKListener() {
                public void onSuccess(JSONObject dataJson, String message, Object... args) {
                    PhonePresenterImpl.this.mView.closeLoadingDialog();
                    PhonePresenterImpl.this.mView.onComplete(dataJson, message);
                }

                public void onFail(String message, int code) {
                    PhonePresenterImpl.this.mView.closeLoadingDialog();
                    PhonePresenterImpl.this.mView.onError(message, code);
                }
            });
        }
    }

    public boolean checkForBindPhone(String phoneNumber, String verificationCode) {
        if (!checkInfo(phoneNumber, "hint_input_phone") || !checkInfo(verificationCode, "huyu_tip_input_right_code")) {
            return false;
        }
        if (this.sendCodePhone.equals(phoneNumber)) {
            return true;
        }
        this.mView.showToastMessage(true, "huyu_tip_no_send_code");
        return false;
    }

    public boolean checkInfo(String info, String resString) {
        if (!CheckUtils.isNullOrEmpty(info)) {
            return true;
        }
        this.mView.showToastMessage(true, resString);
        return false;
    }
}
