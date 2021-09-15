package com.xhuyu.component.mvp.presenter;

import android.content.Context;
import android.webkit.WebView;

public interface UserAgreementFragmentPresenter {
    void calculateTheLayout(Context context);

    void webViewSettings(WebView webView);
}
