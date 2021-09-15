package com.xhuyu.component.mvp.presenter.impl;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Message;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xhuyu.component.mvp.presenter.WebActivityPresenter;
import com.xhuyu.component.mvp.view.WebActivityView;

public class WebActivityPresenterImpl implements WebActivityPresenter {
    /* access modifiers changed from: private */
    public WebActivityView mView;
    private WebViewClient mWebViewClient = new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intent intent;
            if (url.startsWith("http:") || url.startsWith("https:")) {
                return false;
            }
            try {
                if (url.startsWith("android-app://")) {
                    intent = Intent.parseUri(url, 2);
                } else if (url.startsWith("intent://")) {
                    intent = Intent.parseUri(url, 1);
                } else {
                    intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                }
                WebActivityPresenterImpl.this.mView.onStartActivity(intent);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
        }
    };

    public WebActivityPresenterImpl(WebActivityView view) {
        this.mView = view;
    }

    public void initWebViewSettings(WebView webView) {
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
        webSettings.setSupportMultipleWindows(true);
        webView.setBackgroundColor(Color.parseColor("#f0f0f0"));
        webView.setScrollBarStyle(33554432);
        this.mView.loadWebView(webView);
        webView.setWebViewClient(this.mWebViewClient);
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg) {
                WebView newWebView = new WebView(view.getContext());
                view.addView(newWebView);
                newWebView.setWebViewClient(new WebViewClient());
                newWebView.setWebChromeClient(this);
                ((WebView.WebViewTransport) resultMsg.obj).setWebView(newWebView);
                resultMsg.sendToTarget();
                return true;
            }

            public void onProgressChanged(WebView view, int newProgress) {
                WebActivityPresenterImpl.this.mView.showDialog();
                if (newProgress == 100) {
                    WebActivityPresenterImpl.this.mView.closeLoadingDialog();
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }
}
