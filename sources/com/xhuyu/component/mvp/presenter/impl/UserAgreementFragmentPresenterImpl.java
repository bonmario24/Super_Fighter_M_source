package com.xhuyu.component.mvp.presenter.impl;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.xhuyu.component.BuildConfig;
import com.xhuyu.component.core.config.Constant;
import com.xhuyu.component.core.config.SDKConfig;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.mvp.presenter.UserAgreementFragmentPresenter;
import com.xhuyu.component.mvp.view.UserAgreementFragmentView;
import com.xhuyu.component.network.Urls;
import com.xhuyu.component.utils.CacheUtils;
import com.xhuyu.component.utils.GameUtil;
import com.xsdk.doraemon.utils.ContextUtil;
import com.xsdk.doraemon.utils.UiCalculateUtil;
import java.util.HashMap;
import java.util.Map;

public class UserAgreementFragmentPresenterImpl implements UserAgreementFragmentPresenter {
    UserAgreementFragmentView mView;
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Nullable
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url, UserAgreementFragmentPresenterImpl.this.getParameterData());
            return true;
        }
    };

    public UserAgreementFragmentPresenterImpl(UserAgreementFragmentView view) {
        this.mView = view;
    }

    public void calculateTheLayout(Context context) {
        this.mView.autoInflaterUI(UiCalculateUtil.calculateTheLayoutWidth(context), UiCalculateUtil.calculateTheLayoutHeight(context));
    }

    public void webViewSettings(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setCacheMode(2);
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webView.setVerticalScrollBarEnabled(true);
        webView.setBackgroundColor(Color.parseColor("#f0f0f0"));
        webView.setScrollBarStyle(33554432);
        String ip = CacheUtils.getCacheString(Constant.HuYuCacheKey.KEY_IP);
        webView.setWebViewClient(this.mWebViewClient);
        webView.loadUrl(ip + Urls.USER_AGREEMENT, getParameterData());
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    /* access modifiers changed from: private */
    public Map<String, String> getParameterData() {
        Map<String, String> params = new HashMap<>();
        params.put("StarPlatform", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        params.put("StarVersion", BuildConfig.VERSION_NAME);
        params.put("StarGameId", CacheUtils.getCacheInteger(Constant.HuYuCacheKey.KEY_GAME_ID, 0) + "");
        params.put("StarToken", UserManager.getInstance().getStarToken());
        params.put("StarLanguage", SDKConfig.getInstance().getConfigValue(Constant.HuYuConfigKey.CURRENT_LANGUAGE_KEY));
        params.put("StarBid", GameUtil.getPackageName(ContextUtil.getInstance().getApplicationContext()));
        return params;
    }
}
