package com.xhuyu.component.p036ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.xhuyu.component.core.base.BaseLoginComponentView;
import com.xhuyu.component.core.config.SDKConfig;
import com.xhuyu.component.mvp.presenter.impl.NormalLoginFragmentPresenterImpl;
import com.xhuyu.component.utils.BaseButtonUtil;
import com.xhuyu.component.utils.ResourceUtil;
import com.xhuyu.component.utils.appsflyer.AFEventType;
import com.xhuyu.component.utils.appsflyer.AppsFlyerTrackUtil;
import com.xhuyu.component.utils.third.FacebookLoginUtil;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xhuyu.component.ui.fragment.NormalLoginFragment */
public class NormalLoginFragment extends BaseLoginComponentView implements View.OnClickListener {
    private BaseButtonUtil btnFacebook;
    private BaseButtonUtil btnGoogle;
    private List<BaseButtonUtil> btnList;
    private BaseButtonUtil btnVisitor;
    /* access modifiers changed from: private */
    public LinearLayout llContains;
    /* access modifiers changed from: private */
    public NormalLoginFragmentPresenterImpl mPresenter;
    ScrollView slContains;
    private TextView tvAgreement;

    public static NormalLoginFragment getInstance() {
        return new NormalLoginFragment();
    }

    /* access modifiers changed from: protected */
    public String getLayoutName() {
        return "layout_login";
    }

    /* access modifiers changed from: protected */
    public void initView(View contentView, Bundle bundle) {
        this.mPresenter = new NormalLoginFragmentPresenterImpl(this);
        this.mPresenter.initThreadSDK(getActivity());
        setupUI();
    }

    private void setupUI() {
        int i;
        int i2 = 0;
        this.btnList = new ArrayList();
        this.btnFacebook = new BaseButtonUtil(getViewByName("rl_facebook_contains"));
        this.btnFacebook.setText(getStringByName("facebook_login"));
        this.btnFacebook.setImageIco("login_fb");
        this.btnFacebook.setOnClickListener(this);
        this.btnFacebook.setVisibility(SDKConfig.getInstance().isShowFacebookLogin() ? 0 : 8);
        this.btnList.add(this.btnFacebook);
        this.btnGoogle = new BaseButtonUtil(getViewByName("rl_google_contains"));
        this.btnGoogle.setText(getStringByName("google_login"));
        this.btnGoogle.setImageIco("login_google");
        this.btnGoogle.setOnClickListener(this);
        BaseButtonUtil baseButtonUtil = this.btnGoogle;
        if (SDKConfig.getInstance().isShowGoogleLogin()) {
            i = 0;
        } else {
            i = 8;
        }
        baseButtonUtil.setVisibility(i);
        this.btnList.add(this.btnGoogle);
        this.btnVisitor = new BaseButtonUtil(getViewByName("rl_visitor_contains"));
        this.btnVisitor.setText(getStringByName("visitor_login"));
        this.btnVisitor.setImageIco("visitor");
        this.btnVisitor.setOnClickListener(this);
        BaseButtonUtil baseButtonUtil2 = this.btnVisitor;
        if (!SDKConfig.getInstance().isShowVisitorLogin()) {
            i2 = 8;
        }
        baseButtonUtil2.setVisibility(i2);
        this.btnList.add(this.btnVisitor);
        this.tvAgreement = (TextView) getViewByName("tv_xf_agreement");
        this.tvAgreement.setOnClickListener(this);
        this.slContains = (ScrollView) getViewByName("sl_contains");
        this.llContains = (LinearLayout) getViewByName("ll_contains");
        getViewByName("iv_log").setVisibility(8);
        setUiListener();
    }

    private View getVisibleView() {
        if (this.btnList != null) {
            for (BaseButtonUtil buttonUtil : this.btnList) {
                if (buttonUtil.getVisibility() == 0) {
                    return buttonUtil.getTextView();
                }
            }
        }
        return null;
    }

    private void setUiListener() {
        this.slContains.setVisibility(4);
        this.llContains.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                NormalLoginFragment.this.mPresenter.calculateTheLayout(NormalLoginFragment.this.getActivity(), NormalLoginFragment.this.slContains.getPaddingTop() + NormalLoginFragment.this.llContains.getHeight() + NormalLoginFragment.this.slContains.getPaddingBottom());
                if (Build.VERSION.SDK_INT >= 16) {
                    NormalLoginFragment.this.llContains.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    NormalLoginFragment.this.llContains.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    public void autoInflaterUI(int width, int height) {
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(width, height);
        rlp.addRule(13);
        this.slContains.setLayoutParams(rlp);
        aligningButton();
    }

    private void aligningButton() {
        TextView textView = this.btnFacebook.getTextView();
        final TextView visitorTv = this.btnVisitor.getTextView();
        final TextView googleTextView = this.btnGoogle.getTextView();
        final View visibleView = getVisibleView();
        if (visibleView != null) {
            visibleView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(visibleView.getWidth(), -2);
                    params.addRule(13);
                    visitorTv.setLayoutParams(params);
                    visitorTv.setGravity(3);
                    googleTextView.setLayoutParams(params);
                    googleTextView.setGravity(3);
                    SDKConfig.getInstance().setDialogViewHeight(NormalLoginFragment.this.slContains.getHeight());
                    if (Build.VERSION.SDK_INT >= 16) {
                        visibleView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        visibleView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                    NormalLoginFragment.this.slContains.setVisibility(0);
                }
            });
        }
    }

    public void onClick(View v) {
        if (v.getId() == ResourceUtil.getWidgetViewID("rl_facebook_contains")) {
            AppsFlyerTrackUtil.trackStartLogin(this._mActivity, AFEventType.FACEBOOK_LOGIN);
            FacebookLoginUtil.getInstance().doFacebookLogin();
        } else if (v.getId() == ResourceUtil.getWidgetViewID("rl_visitor_contains")) {
            AppsFlyerTrackUtil.trackStartLogin(this._mActivity, AFEventType.VISITOR_LOGIN);
            this.mPresenter.doVisitorLogin();
        } else if (v.getId() == ResourceUtil.getWidgetViewID("rl_google_contains")) {
            AppsFlyerTrackUtil.trackStartLogin(this._mActivity, AFEventType.GOOGLE_LOGIN);
            this.mPresenter.doGoogleLogin(this._mActivity);
        } else if (v.getId() == ResourceUtil.getWidgetViewID("tv_xf_agreement")) {
            start(UserAgreementFragment.getInstance());
        }
    }

    private void cancelLogin() {
        this.mPresenter.postLoginResult(13, 2, getStringByName("login_cancel"));
        finishActivity();
    }

    public boolean onBackPressedSupport() {
        if (super.onBackPressedSupport()) {
            return true;
        }
        cancelLogin();
        return super.onBackPressedSupport();
    }
}
