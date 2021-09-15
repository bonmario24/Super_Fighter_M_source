package com.xhuyu.component.p036ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xhuyu.component.core.base.BaseSupportFragment;
import com.xhuyu.component.mvp.presenter.impl.UserAgreementFragmentPresenterImpl;
import com.xhuyu.component.mvp.view.UserAgreementFragmentView;

/* renamed from: com.xhuyu.component.ui.fragment.UserAgreementFragment */
public class UserAgreementFragment extends BaseSupportFragment implements View.OnClickListener, UserAgreementFragmentView {
    private UserAgreementFragmentPresenterImpl mPresenter;
    private WebView mWebView;

    /* access modifiers changed from: protected */
    public String getLayoutName() {
        return "layout_web_view";
    }

    public static UserAgreementFragment getInstance() {
        Bundle args = new Bundle();
        UserAgreementFragment fragment = new UserAgreementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /* access modifiers changed from: protected */
    public void initView(View contentView, Bundle bundle) {
        this.mPresenter = new UserAgreementFragmentPresenterImpl(this);
        setupUI();
        this.mPresenter.calculateTheLayout(getActivity());
        this.mPresenter.webViewSettings(this.mWebView);
    }

    private void setupUI() {
        ((TextView) getViewByName("tv_title")).setText(getStringByName("xf_user_pro"));
        getViewByName("iv_back").setOnClickListener(this);
        this.mWebView = (WebView) getViewByName("webview_agreement");
    }

    public void onClick(View v) {
        pop();
    }

    public void autoInflaterUI(int width, int height) {
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(width, height);
        rlp.addRule(13);
        ((LinearLayout) getViewByName("ll_contains_user")).setLayoutParams(rlp);
    }

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
}
