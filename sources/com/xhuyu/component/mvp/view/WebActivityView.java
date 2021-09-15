package com.xhuyu.component.mvp.view;

import android.content.Intent;
import android.webkit.WebView;
import com.xhuyu.component.core.base.BaseView;

public interface WebActivityView extends BaseView {
    void loadWebView(WebView webView);

    void onStartActivity(Intent intent);
}
