package com.xhuyu.component.widget;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.thinkfly.star.utils.CheckUtils;
import com.xsdk.doraemon.apkreflect.ReflectResource;

public class TwoButtonsDialog extends Dialog implements View.OnClickListener {
    private Button btnOne;
    private Button btnTwo;
    /* access modifiers changed from: private */
    public boolean cancelable = true;
    private View containsLayout;
    private OnButtonCallbackListener mListener;
    private TextView tvTipDetail;
    private TextView tvTitle;

    public interface OnButtonCallbackListener {
        void onBtnOneCallback();

        void onBtnTwoCallback();
    }

    public TwoButtonsDialog(Activity activity, OnButtonCallbackListener listener) {
        super(activity);
        this.mListener = listener;
        init();
    }

    private void init() {
        View contentView = ReflectResource.getInstance(getContext()).getLayoutView("x_layout_two_buttons_dialog");
        this.containsLayout = ReflectResource.getInstance(getContext()).getWidgetView(contentView, "rel_contains");
        this.btnOne = (Button) ReflectResource.getInstance(getContext()).getWidgetView(contentView, "btn_one");
        this.btnTwo = (Button) ReflectResource.getInstance(getContext()).getWidgetView(contentView, "btn_two");
        this.tvTitle = (TextView) ReflectResource.getInstance(getContext()).getWidgetView(contentView, "title");
        this.tvTipDetail = (TextView) ReflectResource.getInstance(getContext()).getWidgetView(contentView, "tv_tip");
        this.btnOne.setOnClickListener(this);
        this.btnTwo.setOnClickListener(this);
        requestWindowFeature(1);
        Window window = getWindow();
        window.setBackgroundDrawableResource(17170445);
        window.setDimAmount(0.0f);
        setContentView(contentView);
        contentView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (TwoButtonsDialog.this.cancelable) {
                    TwoButtonsDialog.this.dismiss();
                }
            }
        });
    }

    public void show() {
        super.show();
    }

    public void dismiss() {
        super.dismiss();
    }

    public void setButtonOneText(String textStringRes) {
        try {
            if (!CheckUtils.isNullOrEmpty(textStringRes)) {
                this.btnOne.setText(ReflectResource.getInstance(getContext()).getString(textStringRes));
            }
        } catch (Exception e) {
        }
    }

    public void setContainsBackgroundColor(int color) {
        if (this.containsLayout != null) {
            this.containsLayout.setBackgroundColor(color);
        }
    }

    public void setButtonTwoText(String textStringRes) {
        try {
            if (!CheckUtils.isNullOrEmpty(textStringRes)) {
                this.btnTwo.setText(ReflectResource.getInstance(getContext()).getString(textStringRes));
            }
        } catch (Exception e) {
        }
    }

    public void setBtnTwoBackgroundResource(int resId) {
        if (this.btnTwo != null) {
            this.btnTwo.setBackgroundResource(resId);
        }
    }

    public void setBtnOneBackgroundResource(int resId) {
        if (this.btnOne != null) {
            this.btnOne.setBackgroundResource(resId);
        }
    }

    public void seTitleText(String textStringRes) {
        try {
            if (!CheckUtils.isNullOrEmpty(textStringRes)) {
                this.tvTitle.setText(ReflectResource.getInstance(getContext()).getString(textStringRes));
            }
        } catch (Exception e) {
        }
    }

    public void seTipDetailText(String textStringRes) {
        try {
            if (!CheckUtils.isNullOrEmpty(textStringRes)) {
                this.tvTipDetail.setText(ReflectResource.getInstance(getContext()).getString(textStringRes));
            }
        } catch (Exception e) {
        }
    }

    public void seTipDetailText(String textStringRes, Object... formatArgs) {
        try {
            if (!CheckUtils.isNullOrEmpty(textStringRes)) {
                this.tvTipDetail.setText(ReflectResource.getInstance(getContext()).getProxyContext().getResources().getString(ReflectResource.getInstance(getContext()).getStringId(textStringRes), formatArgs));
            }
        } catch (Exception e) {
        }
    }

    public void setCancelable(boolean flag) {
        this.cancelable = flag;
        super.setCancelable(flag);
    }

    public void onClick(View v) {
        if (v.getId() == this.btnOne.getId()) {
            this.mListener.onBtnOneCallback();
        } else if (v.getId() == this.btnTwo.getId()) {
            this.mListener.onBtnTwoCallback();
        }
    }
}
