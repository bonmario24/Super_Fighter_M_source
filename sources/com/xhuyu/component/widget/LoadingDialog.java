package com.xhuyu.component.widget;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.utils.CheckUtil;

public class LoadingDialog extends Dialog {
    /* access modifiers changed from: private */
    public boolean cancelable = true;
    private TextView tvLoadingMessage;

    public LoadingDialog(Activity activity) {
        super(activity);
        init();
    }

    private void init() {
        View contentView = ReflectResource.getInstance(getContext()).getLayoutView("x_view_loading");
        ReflectResource.getInstance(getContext()).getWidgetView(contentView, "loading_root").setVisibility(0);
        this.tvLoadingMessage = (TextView) ReflectResource.getInstance(getContext()).getWidgetView(contentView, "tv_loading_tip");
        requestWindowFeature(1);
        Window window = getWindow();
        window.setBackgroundDrawableResource(17170445);
        window.setDimAmount(0.0f);
        setContentView(contentView);
        contentView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (LoadingDialog.this.cancelable) {
                    LoadingDialog.this.dismiss();
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

    public void setLoadingMessageForRes(String messageStringResID) {
        if (!CheckUtil.isEmpty(messageStringResID) && this.tvLoadingMessage != null) {
            this.tvLoadingMessage.setText(ReflectResource.getInstance(getContext()).getString(messageStringResID));
        }
    }

    public void setLoadingMessage(String message) {
        if (!CheckUtil.isEmpty(message) && this.tvLoadingMessage != null) {
            this.tvLoadingMessage.setText(message);
        }
    }

    public void setCancelable(boolean flag) {
        this.cancelable = flag;
        super.setCancelable(flag);
    }
}
