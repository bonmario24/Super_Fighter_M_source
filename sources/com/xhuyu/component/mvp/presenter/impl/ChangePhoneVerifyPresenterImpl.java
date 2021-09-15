package com.xhuyu.component.mvp.presenter.impl;

import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.mvp.presenter.ChangePhoneVerifyPresenter;
import com.xhuyu.component.mvp.view.PhoneView;
import com.xhuyu.component.network.NetUserUtil;
import org.json.JSONObject;

public class ChangePhoneVerifyPresenterImpl extends PhonePresenterImpl implements ChangePhoneVerifyPresenter {
    public ChangePhoneVerifyPresenterImpl(PhoneView view) {
        super(view);
    }

    public void doGetPhoneCodeInLogin() {
        this.mView.showDialog();
        NetUserUtil.postGetPhoneCodeWithInLogin(new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                ChangePhoneVerifyPresenterImpl.this.mView.closeLoadingDialog();
                ChangePhoneVerifyPresenterImpl.this.mView.startTimerToVerificationCode();
                ChangePhoneVerifyPresenterImpl.this.mView.showToastMessage(false, message);
            }

            public void onFail(String message, int code) {
                ChangePhoneVerifyPresenterImpl.this.mView.closeLoadingDialog();
                ChangePhoneVerifyPresenterImpl.this.mView.showToastMessage(false, message);
            }
        });
    }

    public void doChangePhoneVerify(String phoneNumber, String verificationCode, int type, String areaCode, String areaAbbreviation) {
        if (checkInfo(phoneNumber, "hint_input_phone") && checkInfo(verificationCode, "huyu_tip_input_right_code")) {
            this.mView.showDialog();
            NetUserUtil.postVerifyChangePhone(phoneNumber, verificationCode, type, areaCode, areaAbbreviation, new GameSDKListener() {
                public void onSuccess(JSONObject dataJson, String message, Object... args) {
                    ChangePhoneVerifyPresenterImpl.this.mView.closeLoadingDialog();
                    ChangePhoneVerifyPresenterImpl.this.mView.onComplete(dataJson, message);
                }

                public void onFail(String message, int code) {
                    ChangePhoneVerifyPresenterImpl.this.mView.closeLoadingDialog();
                    ChangePhoneVerifyPresenterImpl.this.mView.showToastMessage(false, message);
                    ChangePhoneVerifyPresenterImpl.this.mView.onError(message, code);
                }
            });
        }
    }
}
