package com.xhuyu.component.mvp.presenter.impl;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.annotation.Nullable;
import com.star.libtrack.core.TrackCore;
import com.star.libtrack.obserable.ClickObserable;
import com.xhuyu.component.core.config.SDKConfig;
import com.xhuyu.component.core.manager.FloatWindowManager;
import com.xhuyu.component.mvp.presenter.BaseActivityPresenter;
import com.xhuyu.component.mvp.view.BaseActivityView;
import com.xsdk.doraemon.base.ActivityCore;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

public class BaseActivityPresenterImpl implements BaseActivityPresenter {
    private static long lastClickTime;
    private ClickObserable clickObserable;
    private Activity mActivity;
    private BaseActivityView mBaseActivityView;

    public BaseActivityPresenterImpl(Activity activity, BaseActivityView baseActivityView) {
        this.mActivity = activity;
        this.mBaseActivityView = baseActivityView;
        if (Build.VERSION.SDK_INT == 26 && ActivityCore.isTranslucentOrFloating(this.mActivity)) {
            SDKLoggerUtil.getLogger().mo19504i("onCreate fixOrientation when Oreo, result =" + ActivityCore.fixOrientation(this.mActivity), new Object[0]);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.mActivity.getWindow().addFlags(67108864);
            this.mActivity.getWindow().addFlags(134217728);
        }
        this.mActivity.requestWindowFeature(1);
        this.mActivity.getWindow().setFlags(1024, 1024);
        this.mActivity.getWindow().setSoftInputMode(18);
        FloatWindowManager.getInstance().destroy();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        initTrack();
        SDKConfig.getInstance().setActivityOrientation(this.mActivity);
    }

    private void initTrack() {
        this.clickObserable = TrackCore.getInstance().trackClick(this.mActivity);
        ViewGroup trackView = this.mBaseActivityView.trackView();
        if (trackView != null) {
            TrackCore.getInstance().trackView(trackView);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        this.clickObserable.dispatchTouchEvent(ev);
        if (ev.getAction() != 0) {
            return false;
        }
        View v = this.mActivity.getCurrentFocus();
        if (isShouldHideKeyboard(v, ev)) {
            hideKeyboard(v.getWindowToken());
        }
        if (!isFastDoubleClick()) {
            return false;
        }
        SDKLoggerUtil.getLogger().mo19502e("click button to fast!", new Object[0]);
        return true;
    }

    private boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v == null || !(v instanceof EditText)) {
            return false;
        }
        int[] l = {0, 0};
        v.getLocationInWindow(l);
        int left = l[0];
        int top = l[1];
        int bottom = top + v.getHeight();
        int right = left + v.getWidth();
        if (event.getX() <= ((float) left) || event.getX() >= ((float) right) || event.getY() <= ((float) top) || event.getY() >= ((float) bottom)) {
            return true;
        }
        return false;
    }

    private void hideKeyboard(IBinder token) {
        if (token != null) {
            ((InputMethodManager) this.mActivity.getSystemService("input_method")).hideSoftInputFromWindow(token, 2);
        }
    }

    public void onDestroy() {
    }
}
