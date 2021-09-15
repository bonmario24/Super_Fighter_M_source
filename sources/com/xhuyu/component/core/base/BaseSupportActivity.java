package com.xhuyu.component.core.base;

import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.xhuyu.component.core.manager.FloatWindowManager;
import com.xhuyu.component.mvp.presenter.impl.BaseActivityPresenterImpl;
import com.xhuyu.component.mvp.view.BaseActivityView;
import com.xhuyu.component.widget.LoadingDialog;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.base.ActivityCore;
import com.xsdk.doraemon.base.SupportActivity;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

public abstract class BaseSupportActivity extends SupportActivity implements BaseActivityView {
    private BaseActivityPresenterImpl mBaseActivityPresenter;
    private View mContentView;
    protected LoadingDialog mLoadingDialog;

    /* access modifiers changed from: protected */
    public abstract String getLayoutName();

    /* access modifiers changed from: protected */
    public abstract ViewGroup needTrackContainsView();

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.mBaseActivityPresenter = new BaseActivityPresenterImpl(this, this);
        super.onCreate(savedInstanceState);
        this.mContentView = ReflectResource.getInstance(this).getLayoutView(CheckUtil.isEmpty(getLayoutName()) ? getDefaultLayoutName() : getLayoutName());
        if (this.mContentView != null) {
            setContentView(this.mContentView);
        }
        this.mBaseActivityPresenter.onCreate(savedInstanceState);
    }

    public String getDefaultLayoutName() {
        return "x_common_activity";
    }

    /* access modifiers changed from: protected */
    public String getStringByName(String strName) {
        return ReflectResource.getInstance(this).getString(strName);
    }

    public View getWidgetView(String widgetName) {
        if (this.mContentView == null) {
            return null;
        }
        return ReflectResource.getInstance(this).getWidgetView(this.mContentView, widgetName);
    }

    /* access modifiers changed from: protected */
    public void showToast(String msg) {
        try {
            if (!CheckUtil.isEmpty(msg)) {
                Toast.makeText(this, msg, 0).show();
            }
        } catch (Exception e) {
        }
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (this.mBaseActivityPresenter == null || !this.mBaseActivityPresenter.dispatchTouchEvent(event)) {
            return super.dispatchTouchEvent(event);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void showLoadingDialog() {
        if (this.mLoadingDialog == null) {
            this.mLoadingDialog = new LoadingDialog(this);
        }
        if (this != null && !isFinishing() && this.mLoadingDialog != null && !this.mLoadingDialog.isShowing()) {
            this.mLoadingDialog.show();
        }
    }

    /* access modifiers changed from: protected */
    public void dismissDialog() {
        if (this.mLoadingDialog != null) {
            this.mLoadingDialog.dismiss();
        }
    }

    public boolean isShowingDialog() {
        if (this.mLoadingDialog != null) {
            return this.mLoadingDialog.isShowing();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        dismissDialog();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mBaseActivityPresenter != null) {
            this.mBaseActivityPresenter.onDestroy();
        }
        super.onDestroy();
        FloatWindowManager.getInstance().show();
    }

    public ViewGroup trackView() {
        ViewGroup needTrackContainsView = needTrackContainsView();
        return needTrackContainsView == null ? (ViewGroup) getWidgetView("f_fragment") : needTrackContainsView;
    }

    public void setRequestedOrientation(int requestedOrientation) {
        if (Build.VERSION.SDK_INT != 26 || !ActivityCore.isTranslucentOrFloating(this)) {
            super.setRequestedOrientation(requestedOrientation);
        } else {
            SDKLoggerUtil.getLogger().mo19504i("avoid calling setRequestedOrientation when Oreo.", new Object[0]);
        }
    }
}
