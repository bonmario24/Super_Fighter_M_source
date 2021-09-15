package com.xhuyu.component.mvp.presenter.impl;

import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.mvp.presenter.ChangePasswordPresenter;
import com.xhuyu.component.mvp.view.PasswordView;
import com.xhuyu.component.network.NetUserUtil;
import com.xhuyu.component.utils.CheckUtil;
import org.json.JSONObject;

public class ChangePasswordPresenterImpl implements ChangePasswordPresenter {
    /* access modifiers changed from: private */
    public PasswordView mView;

    public ChangePasswordPresenterImpl(PasswordView view) {
        this.mView = view;
    }

    public void doModifyPassword(String oldPassword, String newPassword) {
        if (!CheckUtil.isPasswordValid(oldPassword)) {
            this.mView.showToastMessage(true, "huyu_tip_error_old_pass");
        } else if (!CheckUtil.isPasswordValid(newPassword)) {
            this.mView.showToastMessage(true, "huyu_tip_error_new_pass");
        } else if (oldPassword.equals(newPassword)) {
            this.mView.showToastMessage(true, "huyu_old_pwd_cant_equals");
        } else {
            this.mView.showDialog();
            NetUserUtil.postModifyPassword(oldPassword, newPassword, new GameSDKListener() {
                public void onSuccess(JSONObject dataJson, String message, Object... args) {
                    ChangePasswordPresenterImpl.this.mView.closeLoadingDialog();
                    ChangePasswordPresenterImpl.this.mView.showToastMessage(false, message);
                    ChangePasswordPresenterImpl.this.mView.onComplete();
                }

                public void onFail(String message, int code) {
                    ChangePasswordPresenterImpl.this.mView.closeLoadingDialog();
                    ChangePasswordPresenterImpl.this.mView.showToastMessage(false, message);
                }
            });
        }
    }

    public void doSetPassword(String newPassword) {
        if (!CheckUtil.isPasswordValid(newPassword)) {
            this.mView.showToastMessage(true, "huyu_tip_error_new_pass");
            return;
        }
        this.mView.showDialog();
        NetUserUtil.postSetPassword(newPassword, new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                ChangePasswordPresenterImpl.this.mView.closeLoadingDialog();
                ChangePasswordPresenterImpl.this.mView.showToastMessage(false, message);
                ChangePasswordPresenterImpl.this.mView.onComplete();
            }

            public void onFail(String message, int code) {
                ChangePasswordPresenterImpl.this.mView.closeLoadingDialog();
                ChangePasswordPresenterImpl.this.mView.showToastMessage(false, message);
            }
        });
    }
}
