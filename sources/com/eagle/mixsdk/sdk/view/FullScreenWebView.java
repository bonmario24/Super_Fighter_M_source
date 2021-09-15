package com.eagle.mixsdk.sdk.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.eagle.mixsdk.sdk.SDKTools;
import com.eagle.mixsdk.sdk.base.Constants;

public class FullScreenWebView extends WebView {
    private static long FRIST_TIMESTAMP = 0;
    private static long TIMESTAMP = 500;
    private WebSettings mWebSettings = getSettings();

    @SuppressLint({"SetJavaScriptEnabled"})
    public FullScreenWebView(Context context) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mWebSettings.setJavaScriptEnabled(true);
        this.mWebSettings.setUseWideViewPort(true);
        this.mWebSettings.setLoadWithOverviewMode(true);
        this.mWebSettings.setSupportZoom(false);
        this.mWebSettings.setBuiltInZoomControls(false);
        this.mWebSettings.setDisplayZoomControls(false);
        setScrollContainer(false);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        if (mDensity == 240) {
            this.mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == 160) {
            this.mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if (mDensity == 120) {
            this.mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        } else if (mDensity == 320) {
            this.mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == 213) {
            this.mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else {
            this.mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        }
        if (SDKTools.isNetworkAvailable(context)) {
            this.mWebSettings.setCacheMode(-1);
        } else {
            this.mWebSettings.setCacheMode(1);
        }
        this.mWebSettings.setDomStorageEnabled(true);
        this.mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        this.mWebSettings.setAllowFileAccess(true);
        this.mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        this.mWebSettings.setLoadsImagesAutomatically(true);
        this.mWebSettings.setDefaultTextEncodingName("UTF-8");
        setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.w(Constants.TAG, "url " + url);
                return false;
            }

            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });
    }

    public void setTransparent() {
        setBackgroundColor(Color.parseColor("#20000000"));
    }

    public void toLoadUrl(String url) {
        if (url != null && url.length() > 0) {
            loadUrl(url);
        }
    }

    public boolean isCanGoBack() {
        return canGoBack();
    }

    public void toGoBack() {
        goBack();
    }

    public boolean isCanGoForward() {
        return canGoForward();
    }

    public void toGoForward() {
        goForward();
    }

    public void toClearCache() {
        clearCache(true);
    }

    public void toClearHistory() {
        clearHistory();
    }

    public void toClearForData() {
        clearFormData();
    }

    public void onWebViewResume() {
        onResume();
    }

    public void onWebViewPause() {
        onPause();
    }

    public void onWebViewDestory() {
        ((ViewGroup) getParent()).removeView(this);
        destroy();
    }

    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    public boolean performClick() {
        return super.performClick();
    }
}
