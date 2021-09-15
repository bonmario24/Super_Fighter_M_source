package com.xhuyu.component.p036ui.activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.api.HuYuApi;
import com.xhuyu.component.core.base.BaseWebActivity;
import com.xhuyu.component.mvp.presenter.impl.SplashPresenterImpl;
import com.xhuyu.component.mvp.view.SplashView;
import com.xhuyu.component.widget.NetErrorDialog;
import com.xhuyu.component.widget.TwoButtonsDialog;
import com.xsdk.doraemon.event.SDKEventBus;
import com.xsdk.doraemon.event.Subscribe;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

/* renamed from: com.xhuyu.component.ui.activity.SplashActivity */
public class SplashActivity extends BaseWebActivity implements SplashView, TwoButtonsDialog.OnButtonCallbackListener {
    private static final String GAME_MAIN_NAME = "HuYu_Game_Main_Activity";
    private boolean isNeedShowErrorDialog = false;
    private LinearLayout llContains;
    private RelativeLayout mContainsLayout;
    private NetErrorDialog mNetErrorDialog;
    private SplashPresenterImpl presenter;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showStatusBar();
        SDKEventBus.getDefault().register(this);
        this.mNetErrorDialog = new NetErrorDialog(this, this);
        this.mNetErrorDialog.setCanceledOnTouchOutside(false);
        this.mNetErrorDialog.setCancelable(false);
        setupUI();
        this.presenter = new SplashPresenterImpl(this);
        this.presenter.doRequestDuplicate();
    }

    @Subscribe(event = {7})
    public void onExitApp() {
        SDKLoggerUtil.getLogger().mo19504i("退出应用", new Object[0]);
        finish();
    }

    private void setupUI() {
        this.mContainsLayout = (RelativeLayout) getWidgetView("rl_web_layout");
        this.mContainsLayout.setBackgroundColor(-1);
        getWidgetView("rl_title_contains").setVisibility(8);
        this.llContains = (LinearLayout) getWidgetView("ll_contains_user");
        this.llContains.setVisibility(4);
    }

    public void loadWebView(WebView webView) {
    }

    public void showToastMessage(String message) {
        if (!CheckUtils.isNullOrEmpty(message)) {
            Toast.makeText(this, message, 0).show();
        }
    }

    public void loadUrl(String url) {
        this.llContains.setVisibility(0);
        this.mWebView.loadUrl(url);
    }

    public void jumpGame() {
        try {
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), 128);
            if (appInfo.metaData != null) {
                String mainActivity = appInfo.metaData.getString(GAME_MAIN_NAME);
                if (!CheckUtils.isNullOrEmpty(mainActivity)) {
                    startActivity(new Intent(this, Class.forName(mainActivity)));
                    overridePendingTransition(0, 0);
                    finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openFailDialog(String messageRes) {
        showNetErrorDialog();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        SDKEventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onBackPressedSupport() {
        if (this.mNetErrorDialog == null || !this.mNetErrorDialog.isShowing()) {
            this.isNeedShowErrorDialog = false;
            HuYuApi.getInstance().doExitGame(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (this.isNeedShowErrorDialog) {
            showNetErrorDialog();
        }
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        dismissNetErrorDialog();
        super.onPause();
    }

    public void onBtnOneCallback() {
        this.isNeedShowErrorDialog = false;
        dismissNetErrorDialog();
        this.presenter.doRequestDuplicate();
    }

    private void dismissNetErrorDialog() {
        if (this.mNetErrorDialog != null) {
            this.mNetErrorDialog.dismiss();
        }
    }

    private void showNetErrorDialog() {
        if (this.mNetErrorDialog != null && !this.mNetErrorDialog.isShowing()) {
            this.isNeedShowErrorDialog = true;
            this.mNetErrorDialog.show();
        }
    }

    public void onBtnTwoCallback() {
        finish();
    }
}
