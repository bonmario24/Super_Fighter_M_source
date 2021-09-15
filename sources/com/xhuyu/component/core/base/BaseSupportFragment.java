package com.xhuyu.component.core.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xhuyu.component.widget.LoadingDialog;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.base.SupportFragment;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

public abstract class BaseSupportFragment extends SupportFragment {
    private View mContentView;
    private LoadingDialog mLoadingDialog;

    /* access modifiers changed from: protected */
    public abstract String getLayoutName();

    /* access modifiers changed from: protected */
    public abstract void initView(View view, Bundle bundle);

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        this.mContentView = ReflectResource.getInstance(this._mActivity).getLayoutView(getLayoutName());
        initView(this.mContentView, getArguments());
        return this.mContentView;
    }

    /* access modifiers changed from: protected */
    public String getStringByName(String strName) {
        return ReflectResource.getInstance(this._mActivity).getString(strName);
    }

    /* access modifiers changed from: protected */
    public View getViewByName(String widgetName) {
        if (this.mContentView == null) {
            return null;
        }
        return ReflectResource.getInstance(this._mActivity).getWidgetView(this.mContentView, widgetName);
    }

    /* access modifiers changed from: protected */
    public int getWidgetViewID(String widgetName) {
        return ReflectResource.getInstance(this._mActivity).getWidgetViewID(widgetName);
    }

    /* access modifiers changed from: protected */
    public void showToast(String msg) {
        try {
            if (!CheckUtil.isEmpty(msg)) {
                Toast.makeText(getActivity(), msg, 0).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void finishActivity() {
        if (this._mActivity != null) {
            this._mActivity.finish();
        }
    }

    /* access modifiers changed from: protected */
    public void showLoadingDialogWithMessage(String messageStringResID) {
        if (this.mLoadingDialog == null) {
            this.mLoadingDialog = new LoadingDialog(getActivity());
        }
        if (this.mLoadingDialog != null && !this.mLoadingDialog.isShowing()) {
            this.mLoadingDialog.setLoadingMessageForRes(messageStringResID);
            this.mLoadingDialog.show();
        }
    }

    /* access modifiers changed from: protected */
    public void showLoadingDialog() {
        showLoadingDialogWithMessage("");
    }

    /* access modifiers changed from: protected */
    public void dismissDialog() {
        if (this.mLoadingDialog != null) {
            this.mLoadingDialog.dismiss();
        }
    }

    public void onPause() {
        super.onPause();
        dismissDialog();
    }

    public boolean onBackPressedSupport() {
        BaseSupportActivity activity;
        if (this._mActivity == null || this._mActivity.isFinishing() || !(this._mActivity instanceof BaseSupportActivity) || (activity = (BaseSupportActivity) this._mActivity) == null || !activity.isShowingDialog()) {
            return super.onBackPressedSupport();
        }
        activity.dismissDialog();
        SDKLoggerUtil.getLogger().mo19504i("dismiss dialog", new Object[0]);
        return true;
    }

    /* access modifiers changed from: protected */
    public HuYuUser getUserInfo() {
        return UserManager.getInstance().getUser();
    }

    /* access modifiers changed from: protected */
    public void saveUserInfo(HuYuUser xUser) {
        UserManager.getInstance().saveUser(xUser);
    }
}
