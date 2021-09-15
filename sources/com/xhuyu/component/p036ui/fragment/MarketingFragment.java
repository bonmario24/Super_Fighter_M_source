package com.xhuyu.component.p036ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.doraemon.util.SizeUtils;
import com.xhuyu.component.core.base.BaseSupportFragment;
import com.xhuyu.component.utils.GameUtil;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.utils.UiCalculateUtil;

/* renamed from: com.xhuyu.component.ui.fragment.MarketingFragment */
public class MarketingFragment extends BaseSupportFragment implements View.OnClickListener {
    /* access modifiers changed from: private */
    public LinearLayout btnContains;
    /* access modifiers changed from: private */
    public LinearLayout llContainsMk;

    public static MarketingFragment getInstance() {
        return new MarketingFragment();
    }

    /* access modifiers changed from: protected */
    public String getLayoutName() {
        return "x_marketing_view";
    }

    /* access modifiers changed from: protected */
    public void initView(View contentView, Bundle bundle) {
        this.llContainsMk = (LinearLayout) getViewByName("ll_contains_mk");
        getViewByName("btn_mk_like").setOnClickListener(this);
        getViewByName("btn_mk_dislike").setOnClickListener(this);
        getViewByName("btn_mk_later").setOnClickListener(this);
        this.btnContains = (LinearLayout) getViewByName("ll_btn_contains");
        initBtnViews();
        calculateTheLayout();
    }

    private void initBtnViews() {
        boolean landscape = isLandscape();
        if (landscape) {
            this.btnContains.setOrientation(0);
        } else {
            this.btnContains.setOrientation(1);
        }
        for (int i = 0; i < this.btnContains.getChildCount(); i++) {
            View childAt = this.btnContains.getChildAt(i);
            if (landscape) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
                if (i < this.btnContains.getChildCount() - 1) {
                    params.rightMargin = SizeUtils.dp2px(20.0f);
                }
                params.gravity = 17;
                childAt.setLayoutParams(params);
                childAt.setPadding(SizeUtils.dp2px(20.0f), SizeUtils.dp2px(2.0f), SizeUtils.dp2px(20.0f), SizeUtils.dp2px(2.0f));
            } else {
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(-1, -2);
                params2.bottomMargin = SizeUtils.dp2px(20.0f);
                childAt.setLayoutParams(params2);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean isLandscape() {
        if (getResources().getConfiguration().orientation == 2) {
            return true;
        }
        return false;
    }

    private void calculateTheLayout() {
        this.llContainsMk.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int containsWidth;
                View child = MarketingFragment.this.btnContains.getChildAt(0);
                LinearLayout.LayoutParams childParams = (LinearLayout.LayoutParams) child.getLayoutParams();
                if (MarketingFragment.this.isLandscape()) {
                    containsWidth = ((child.getWidth() + childParams.rightMargin) * MarketingFragment.this.btnContains.getChildCount()) + MarketingFragment.this.llContainsMk.getPaddingLeft() + MarketingFragment.this.llContainsMk.getPaddingRight();
                } else {
                    containsWidth = UiCalculateUtil.calculateTheLayoutWidth(MarketingFragment.this._mActivity);
                }
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(containsWidth, -2);
                params.gravity = 17;
                MarketingFragment.this.llContainsMk.setLayoutParams(params);
                if (Build.VERSION.SDK_INT >= 16) {
                    MarketingFragment.this.llContainsMk.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    MarketingFragment.this.llContainsMk.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    public void onClick(View v) {
        if (v.getId() == getViewByName("btn_mk_like").getId()) {
            if (!CheckUtil.checkPackageApp(this._mActivity, "com.android.vending")) {
                showToast(getStringByName("huyu_please_install_google_store"));
                return;
            }
            try {
                GameUtil.launchMarketAppDetail(this._mActivity, GameUtil.getPackageName(this._mActivity), "com.android.vending");
                finishActivity();
            } catch (Exception e) {
                e.printStackTrace();
                showToast(getStringByName("huyu_please_install_google_store"));
            }
        } else if (v.getId() == getViewByName("btn_mk_dislike").getId()) {
            startWithPop(FeedbackFragment.getInstance());
        } else if (v.getId() == getViewByName("btn_mk_later").getId()) {
            finishActivity();
        }
    }
}
