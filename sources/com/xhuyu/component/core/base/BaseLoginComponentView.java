package com.xhuyu.component.core.base;

import android.app.Activity;
import android.view.View;
import com.xhuyu.component.mvp.view.NormalLoginFragmentView;

public abstract class BaseLoginComponentView extends BaseSupportFragment implements NormalLoginFragmentView {
    public void showToastMessage(boolean fromRes, String resStringName) {
        String message = resStringName;
        if (fromRes) {
            try {
                message = getStringByName(resStringName);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        showToast(message);
    }

    public void showAccountInfo(boolean isNewCreate, String account, String pwd) {
    }

    public void changePwdEditViewType(int inputType) {
    }

    public void showDialog() {
        showLoadingDialog();
    }

    public void onLoginFailed(String message, int code) {
        dismissDialog();
        try {
            showToast(getStringByName("login_fail") + ": " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeLoadingDialog() {
        dismissDialog();
    }

    public void onLoginSuccess() {
        finishActivity();
    }

    public View getContentView() {
        return getView();
    }

    public Activity getViewActivity() {
        return this._mActivity;
    }
}
