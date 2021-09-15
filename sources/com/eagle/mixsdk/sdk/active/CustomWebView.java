package com.eagle.mixsdk.sdk.active;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CustomWebView extends WebView {
    private Activity context;

    @SuppressLint({"SetJavaScriptEnabled"})
    public CustomWebView(Context context2) {
        super(context2);
        this.context = (Activity) context2;
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        requestFocus();
        getSettings().setJavaScriptEnabled(true);
        getSettings().setBuiltInZoomControls(false);
        getSettings().setSavePassword(false);
        getSettings().setUseWideViewPort(true);
        getSettings().setUserAgentString("mac os");
        getSettings().setDefaultTextEncodingName("UTF-8");
        setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                CustomAlertDialog.getInstance().getImageView().setVisibility(0);
                view.loadUrl(url);
                return false;
            }
        });
        setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return false;
            }
        });
    }

    public void toLoadUrl(String url) {
        if (url != null && !TextUtils.isEmpty(url)) {
            loadUrl(url);
        }
    }

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    public void installJSInterface(Object JSObject, String control) {
        if (JSObject != null) {
            addJavascriptInterface(JSObject, control);
        }
    }
}
