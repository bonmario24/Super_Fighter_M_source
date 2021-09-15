package com.eagle.mixsdk.sdk.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.doraemon.util.SizeUtils;

public class CommonDialog extends Dialog {
    static final /* synthetic */ boolean $assertionsDisabled = (!CommonDialog.class.desiredAssertionStatus());
    private static final int COLOR_NEGATIVE_BTN_BG_NORMAL = -10329497;
    private static final int COLOR_NEGATIVE_BTN_BG_PRESSED = -12698040;
    private static final int COLOR_POSITIVE_BTN_BG_NORMAL = -1636844;
    private static final int COLOR_POSITIVE_BTN_BG_PRESSED = -5439467;
    private static final int COLOR_TEXT = -12698040;
    private boolean isLandscape = false;
    private final View.OnClickListener mBtnListener = new View.OnClickListener() {
        public void onClick(View v) {
            CommonDialog.this.dismiss();
            if (v == CommonDialog.this.mPositiveBtn) {
                if (CommonDialog.this.mBuilder.mPositiveClickListener != null) {
                    CommonDialog.this.mBuilder.mPositiveClickListener.onClick(v);
                }
            } else if (v == CommonDialog.this.mNegativeBtn && CommonDialog.this.mBuilder.mNegativeClickListener != null) {
                CommonDialog.this.mBuilder.mNegativeClickListener.onClick(v);
            }
        }
    };
    /* access modifiers changed from: private */
    public Builder mBuilder;
    private TextView mMessageTv;
    /* access modifiers changed from: private */
    public TextView mNegativeBtn;
    /* access modifiers changed from: private */
    public TextView mPositiveBtn;
    private TextView mTitleTv;

    public CommonDialog(Builder builder) {
        super(builder.mContext);
        this.mBuilder = builder;
        requestWindowFeature(1);
        setContentView(initDialogView());
    }

    public void initData() {
        setupTitle();
        setupMessage();
        setPositiveButton();
        setNegativeButton();
        windowDeploy();
    }

    public int getDialogWith() {
        return SizeUtils.dp2px(300.0f);
    }

    public int getDialogHeight() {
        return -2;
    }

    public View initDialogView() {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(1);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        int padding = SizeUtils.dp2px(20.0f);
        layout.setPadding(padding, padding, padding, padding);
        layout.setLayoutParams(lp);
        layout.setBackgroundDrawable(DrawableUtils.getBackgroundDrawable(-1, SizeUtils.dp2px(6.0f)));
        this.mTitleTv = new TextView(getContext());
        LinearLayout.LayoutParams tvLp = new LinearLayout.LayoutParams(-2, -2);
        tvLp.gravity = 1;
        this.mTitleTv.setTextColor(-12698040);
        tvLp.setMargins(0, 0, 0, SizeUtils.dp2px(20.0f));
        layout.addView(this.mTitleTv, tvLp);
        this.mMessageTv = new TextView(getContext());
        LinearLayout.LayoutParams msgLp = new LinearLayout.LayoutParams(-2, 0, 1.0f);
        msgLp.gravity = 1;
        msgLp.setMargins(0, 0, 0, SizeUtils.dp2px(30.0f));
        this.mMessageTv.setTextColor(-12698040);
        layout.addView(this.mMessageTv, msgLp);
        RelativeLayout btnBgView = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams btnBgLp = new RelativeLayout.LayoutParams(-1, -2);
        this.mPositiveBtn = getButtonView();
        this.mPositiveBtn.setBackgroundDrawable(DrawableUtils.getStateListDrawable(COLOR_POSITIVE_BTN_BG_NORMAL, COLOR_POSITIVE_BTN_BG_PRESSED, SizeUtils.dp2px(4.0f)));
        RelativeLayout.LayoutParams positiveLP = new RelativeLayout.LayoutParams(SizeUtils.dp2px(96.0f), SizeUtils.dp2px(42.0f));
        positiveLP.addRule(this.mBuilder.isSingleBtn ? 13 : 9);
        this.mPositiveBtn.setLayoutParams(positiveLP);
        btnBgView.addView(this.mPositiveBtn);
        if (!this.mBuilder.isSingleBtn) {
            this.mNegativeBtn = getButtonView();
            this.mNegativeBtn.setBackgroundDrawable(DrawableUtils.getStateListDrawable(COLOR_NEGATIVE_BTN_BG_NORMAL, -12698040, SizeUtils.dp2px(4.0f)));
            RelativeLayout.LayoutParams negativeLP = new RelativeLayout.LayoutParams(SizeUtils.dp2px(96.0f), SizeUtils.dp2px(42.0f));
            negativeLP.addRule(TextUtils.isEmpty(this.mBuilder.mPositiveBtnText) ? 13 : 11);
            this.mNegativeBtn.setLayoutParams(negativeLP);
            btnBgView.addView(this.mNegativeBtn);
        }
        layout.addView(btnBgView, btnBgLp);
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        relativeLayout.setPadding(padding, padding, padding, padding);
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setBackgroundDrawable(DrawableUtils.getBackgroundDrawable(0, SizeUtils.dp2px(6.0f)));
        RelativeLayout.LayoutParams rlLP = new RelativeLayout.LayoutParams(getDialogWith(), getDialogHeight());
        rlLP.addRule(13);
        if (this.mBuilder.mCancelable) {
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CommonDialog.this.dismiss();
                }
            });
            layout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                }
            });
        }
        layout.setLayoutParams(rlLP);
        relativeLayout.addView(layout);
        return relativeLayout;
    }

    private TextView getButtonView() {
        TextView btn = new TextView(getContext());
        btn.setPadding(0, SizeUtils.dp2px(10.0f), 0, SizeUtils.dp2px(10.0f));
        btn.setGravity(17);
        btn.setTextColor(-1);
        return btn;
    }

    private TextView getNegativeButtonView() {
        TextView btn = new TextView(getContext());
        btn.setPadding(0, SizeUtils.dp2px(10.0f), 0, SizeUtils.dp2px(10.0f));
        btn.setGravity(17);
        btn.setTextColor(-1);
        btn.setBackgroundDrawable(DrawableUtils.getStateListDrawable(COLOR_NEGATIVE_BTN_BG_NORMAL, -12698040, SizeUtils.dp2px(4.0f)));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, -2, 1.0f);
        lp.gravity = 1;
        lp.setMargins(SizeUtils.dp2px(5.0f), 0, 0, 0);
        btn.setLayoutParams(lp);
        return btn;
    }

    private void windowDeploy() {
        Window mWindow = getWindow();
        if ($assertionsDisabled || mWindow != null) {
            mWindow.setBackgroundDrawable(DrawableUtils.getBackgroundDrawable(0, SizeUtils.dp2px(6.0f)));
            WindowManager.LayoutParams wl = mWindow.getAttributes();
            if (Build.VERSION.SDK_INT >= 19) {
                mWindow.setFlags(67108864, 67108864);
                mWindow.setFlags(134217728, 134217728);
            }
            mWindow.setAttributes(wl);
            return;
        }
        throw new AssertionError();
    }

    public void dismiss() {
        InputMethodManager manager;
        View view = getCurrentFocus();
        if ((view instanceof TextView) && (manager = (InputMethodManager) getContext().getSystemService("input_method")) != null) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        super.dismiss();
    }

    public void show() {
        initData();
        super.show();
    }

    public void setTitle(@Nullable CharSequence text) {
        this.mTitleTv.setText(text);
    }

    private void setupTitle() {
        if (this.mTitleTv != null) {
            if (TextUtils.isEmpty(this.mBuilder.mTitle)) {
                this.mTitleTv.setVisibility(8);
                return;
            }
            this.mTitleTv.setVisibility(0);
            this.mTitleTv.setText(this.mBuilder.mTitle);
            this.mTitleTv.setTextSize(1, 18.0f);
        }
    }

    private void setupMessage() {
        if (this.mMessageTv != null) {
            if (TextUtils.isEmpty(this.mBuilder.mMessage)) {
                this.mMessageTv.setVisibility(8);
                return;
            }
            this.mMessageTv.setVisibility(0);
            this.mMessageTv.setText(this.mBuilder.mMessage);
            this.mMessageTv.setTextSize(1, 14.0f);
            this.mMessageTv.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }

    private void setPositiveButton() {
        if (this.mPositiveBtn != null) {
            if (TextUtils.isEmpty(this.mBuilder.mPositiveBtnText)) {
                this.mPositiveBtn.setVisibility(8);
                return;
            }
            this.mPositiveBtn.setVisibility(0);
            setButton(this.mPositiveBtn, this.mBuilder.mPositiveBtnText);
        }
    }

    private void setNegativeButton() {
        if (this.mNegativeBtn != null) {
            if (TextUtils.isEmpty(this.mBuilder.mNegativeBtnText)) {
                this.mNegativeBtn.setVisibility(8);
                return;
            }
            this.mNegativeBtn.setVisibility(0);
            setButton(this.mNegativeBtn, this.mBuilder.mNegativeBtnText);
        }
    }

    private void setButton(TextView view, @Nullable CharSequence text) {
        view.setText(text);
        view.setTextSize(1, 14.0f);
        view.setOnClickListener(this.mBtnListener);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public boolean isSingleBtn = false;
        /* access modifiers changed from: private */
        public boolean mCancelable = true;
        /* access modifiers changed from: private */
        public Context mContext;
        /* access modifiers changed from: private */
        public CharSequence mMessage;
        private float mMessageSize = -1.0f;
        /* access modifiers changed from: private */
        public CharSequence mNegativeBtnText;
        /* access modifiers changed from: private */
        public View.OnClickListener mNegativeClickListener;
        /* access modifiers changed from: private */
        public CharSequence mPositiveBtnText;
        /* access modifiers changed from: private */
        public View.OnClickListener mPositiveClickListener;
        /* access modifiers changed from: private */
        public CharSequence mTitle;

        public Builder(@NonNull Context context) {
            this.mContext = context;
        }

        public Context getContext() {
            return this.mContext;
        }

        public Builder setTitle(@StringRes int titleId) {
            this.mTitle = this.mContext.getText(titleId);
            return this;
        }

        public Builder setTitle(@Nullable CharSequence title) {
            this.mTitle = title;
            return this;
        }

        public Builder setMessage(@StringRes int messageId) {
            this.mMessage = this.mContext.getText(messageId);
            return this;
        }

        public Builder setMessage(@Nullable CharSequence message) {
            this.mMessage = message;
            return this;
        }

        public Builder setPositiveButton(@StringRes int textId, View.OnClickListener listener) {
            this.mPositiveBtnText = this.mContext.getText(textId);
            this.mPositiveClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence positiveButton, View.OnClickListener listener) {
            this.mPositiveBtnText = positiveButton;
            this.mPositiveClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(@StringRes int textId, View.OnClickListener listener) {
            this.mNegativeBtnText = this.mContext.getText(textId);
            this.mNegativeClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence negativeButton, View.OnClickListener listener) {
            this.mNegativeClickListener = listener;
            this.mNegativeBtnText = negativeButton;
            return this;
        }

        public Builder isSingleBtn(boolean isSingle) {
            this.isSingleBtn = isSingle;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.mCancelable = cancelable;
            return this;
        }

        public CommonDialog builder() {
            CommonDialog dialog = new CommonDialog(this);
            dialog.setCancelable(this.mCancelable);
            if (this.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            return dialog;
        }
    }
}
