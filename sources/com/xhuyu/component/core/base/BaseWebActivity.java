package com.xhuyu.component.core.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.doraemon.util.SizeUtils;
import com.xhuyu.component.mvp.presenter.impl.WebActivityPresenterImpl;
import com.xhuyu.component.mvp.view.WebActivityView;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.utils.UiCalculateUtil;
import com.xsdk.doraemon.utils.notch.utils.DeviceTools;

public abstract class BaseWebActivity extends BaseSupportActivity implements WebActivityView, View.OnClickListener {
    public static final String EXTRA_TITLE = "extra_title";
    private WebActivityPresenterImpl mPresenter;
    protected WebView mWebView;

    public abstract void loadWebView(WebView webView);

    /* access modifiers changed from: protected */
    public String getLayoutName() {
        return "layout_web_view";
    }

    /* access modifiers changed from: protected */
    public ViewGroup needTrackContainsView() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPresenter = new WebActivityPresenterImpl(this);
        setupUI();
        this.mPresenter.initWebViewSettings(this.mWebView);
    }

    private void setupUI() {
        TextView tvTitle = (TextView) getWidgetView("tv_title");
        if (getIntent() != null) {
            String title = getIntent().getStringExtra(EXTRA_TITLE);
            if (!CheckUtil.isEmpty(title)) {
                tvTitle.setText(title);
            }
        }
        getWidgetView("iv_back").setOnClickListener(this);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getWidgetView("iv_back").getLayoutParams();
        layoutParams.rightMargin = SizeUtils.dp2px(12.0f);
        layoutParams.leftMargin = SizeUtils.dp2px(12.0f);
        getWidgetView("iv_back").setLayoutParams(layoutParams);
        this.mWebView = (WebView) getWidgetView("webview_agreement");
        getWidgetView("ll_contains_user").setPadding(0, 0, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void showStatusBar() {
        if (!UiCalculateUtil.isLandscape(this)) {
            getWindow().clearFlags(1024);
            final View viewStatus = getWidgetView("view_status");
            viewStatus.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    int height = DeviceTools.getStatusBarHeight(BaseWebActivity.this);
                    ViewGroup.LayoutParams params = viewStatus.getLayoutParams();
                    params.height = height;
                    viewStatus.setLayoutParams(params);
                    viewStatus.setVisibility(0);
                    if (Build.VERSION.SDK_INT >= 16) {
                        viewStatus.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        viewStatus.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                }
            });
        }
    }

    public void showDialog() {
        showLoadingDialog();
    }

    public void closeLoadingDialog() {
        dismissDialog();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mWebView != null) {
            this.mWebView.loadDataWithBaseURL((String) null, "", "text/html", "utf-8", (String) null);
            this.mWebView.clearHistory();
            ((ViewGroup) this.mWebView.getParent()).removeView(this.mWebView);
            this.mWebView.destroy();
            this.mWebView = null;
        }
        super.onDestroy();
    }

    public void onClick(View v) {
        if (v.getId() == getWidgetView("iv_back").getId()) {
            finish();
        }
    }

    public void onStartActivity(Intent intent) {
        startActivity(intent);
    }
}
