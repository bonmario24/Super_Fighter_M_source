package com.xhuyu.component.p036ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import com.facebook.share.internal.ShareConstants;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.core.base.BaseWebActivity;
import com.xhuyu.component.mvp.model.PaymentResult;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

/* renamed from: com.xhuyu.component.ui.activity.WebBillingActivity */
public class WebBillingActivity extends BaseWebActivity {
    private static final String EXTRA_URL = "extra_url";
    private String mUrl;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showStatusBar();
        setRequestedOrientation(1);
        this.mWebView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void on_result(final int result) {
                WebBillingActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if (result == 1) {
                            SDKEventPost.post(5, new PaymentResult(1, "pay success"));
                            return;
                        }
                        SDKEventPost.post(5, new PaymentResult(0, "pay fail"));
                    }
                });
                WebBillingActivity.this.finish();
            }
        }, "pay");
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && intent.getData() != null) {
            String message = intent.getData().getQueryParameter(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
            if (!CheckUtils.isNullOrEmpty(message)) {
                SDKLoggerUtil.getLogger().mo19504i("receiver pay result message:" + message, new Object[0]);
            }
        }
    }

    public void onStartActivity(Intent intent) {
        startActivity(intent);
    }

    public void loadWebView(WebView webView) {
        this.mUrl = getIntent().getStringExtra(EXTRA_URL);
        webView.loadUrl(this.mUrl);
    }

    public static void start(Context context, String title, String url) {
        Intent starter = new Intent(context, WebBillingActivity.class);
        starter.putExtra(BaseWebActivity.EXTRA_TITLE, title);
        starter.putExtra(EXTRA_URL, url);
        context.startActivity(starter);
    }
}
