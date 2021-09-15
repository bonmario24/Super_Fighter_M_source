package com.xhuyu.component.p036ui.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.base.BaseSupportFragment;
import com.xhuyu.component.mvp.presenter.impl.FeedbackPresenterImpl;
import com.xhuyu.component.mvp.view.FeedbackView;
import com.xsdk.doraemon.utils.UiCalculateUtil;

/* renamed from: com.xhuyu.component.ui.fragment.FeedbackFragment */
public class FeedbackFragment extends BaseSupportFragment implements View.OnClickListener, FeedbackView {
    private static final int MAX_NUM = 500;
    private View btnMkSubmit;
    private EditText etFeedback;
    private View ivClose;
    private FeedbackPresenterImpl mPresenter;
    /* access modifiers changed from: private */
    public TextView tvNum;

    public static FeedbackFragment getInstance() {
        return new FeedbackFragment();
    }

    /* access modifiers changed from: protected */
    public String getLayoutName() {
        return "x_feedback_view";
    }

    /* access modifiers changed from: protected */
    public void initView(View contentView, Bundle bundle) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(UiCalculateUtil.calculateTheLayoutWidth(this._mActivity, 0.55f, 0.9f), UiCalculateUtil.calculateTheLayoutHeight(this._mActivity));
        params.gravity = 17;
        ((RelativeLayout) getViewByName("rl_fd_layout")).setLayoutParams(params);
        this.mPresenter = new FeedbackPresenterImpl(this);
        ((TextView) getViewByName("tv_title")).setText(getStringByName("huyu_title_feedback"));
        getViewByName("iv_back").setVisibility(8);
        this.ivClose = getViewByName("iv_close");
        this.ivClose.setVisibility(0);
        this.btnMkSubmit = getViewByName("btn_mk_submit");
        this.etFeedback = (EditText) getViewByName("et_feedback");
        this.etFeedback.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_NUM)});
        this.etFeedback.requestFocus();
        this.tvNum = (TextView) getViewByName("tv_num");
        this.tvNum.setText("0 / 500");
        setListener();
    }

    private void setListener() {
        this.ivClose.setOnClickListener(this);
        this.btnMkSubmit.setOnClickListener(this);
        this.etFeedback.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                FeedbackFragment.this.tvNum.setText(s.length() + " / " + FeedbackFragment.MAX_NUM);
            }
        });
    }

    public void onClick(View v) {
        if (v.getId() == this.ivClose.getId()) {
            finishActivity();
        } else if (v.getId() == this.btnMkSubmit.getId()) {
            this.mPresenter.doSubmitFeedback(this.etFeedback.getText().toString().trim());
        }
    }

    public void onFinishActivity() {
        finishActivity();
    }

    public void toastMessage(String message, boolean hasFromRes) {
        String msg = message;
        if (!CheckUtils.isNullOrEmpty(msg) && hasFromRes) {
            msg = getStringByName(message);
        }
        showToast(msg);
    }

    public void showDialog() {
        showLoadingDialog();
    }

    public void closeLoadingDialog() {
        dismissDialog();
    }
}
