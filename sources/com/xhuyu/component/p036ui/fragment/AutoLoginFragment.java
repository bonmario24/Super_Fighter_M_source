package com.xhuyu.component.p036ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.xhuyu.component.core.base.BaseSupportFragment;
import com.xhuyu.component.mvp.presenter.impl.AutoLoginFragmentPresenterImpl;
import com.xhuyu.component.mvp.view.AutoLoginFragmentView;
import com.xhuyu.component.utils.ResourceUtil;

/* renamed from: com.xhuyu.component.ui.fragment.AutoLoginFragment */
public class AutoLoginFragment extends BaseSupportFragment implements View.OnClickListener, AutoLoginFragmentView {
    private AutoLoginFragmentPresenterImpl mPresenter;
    private TextView tvTitle;

    public static AutoLoginFragment getInstance() {
        return new AutoLoginFragment();
    }

    /* access modifiers changed from: protected */
    public String getLayoutName() {
        return "layout_auto_login";
    }

    /* access modifiers changed from: protected */
    public void initView(View contentView, Bundle bundle) {
        this.tvTitle = (TextView) getViewByName("tv_title");
        getViewByName("btn_switch").setOnClickListener(this);
        if (getUserInfo() == null) {
            toNormalLogin();
            return;
        }
        this.mPresenter = new AutoLoginFragmentPresenterImpl(this);
        this.mPresenter.setCountDownTime(3000);
        this.mPresenter.setDelay(500);
        this.mPresenter.createCountDownTimer();
    }

    public void onClick(View v) {
        cancelLogin();
        toNormalLogin();
    }

    public void refreshTick(long millisUntilFinished) {
        try {
            this.tvTitle.setText(getUserInfo().getUsername() + "," + ResourceUtil.getStringContainFormat("logining", Long.valueOf((millisUntilFinished / 1000) + 1)));
        } catch (Exception e) {
        }
    }

    public void onLoginSuccess() {
        finishActivity();
    }

    public void onLoginFailed(String message, int code) {
        showToast(message);
        toNormalLogin();
    }

    public void showDialog() {
        showLoadingDialog();
    }

    public void closeLoadingDialog() {
        dismissDialog();
    }

    private void toNormalLogin() {
        startWithPop(LoginByAccountFragment.getInstance());
    }

    private void cancelLogin() {
        this.mPresenter.dismissCountDown();
    }

    public boolean onBackPressedSupport() {
        cancelLogin();
        toNormalLogin();
        this.mPresenter.postLoginResult(2, getStringByName("login_cancel"));
        return super.onBackPressedSupport();
    }
}
