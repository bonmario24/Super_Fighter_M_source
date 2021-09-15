package com.eagle.mixsdk.sdk.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.doraemon.p027eg.ReflectResource;

public class LoadingDialog extends Dialog {
    private static final int CHANGE_TITLE_WHAT = 1;
    private static final int CHNAGE_TITLE_DELAYMILLIS = 300;
    private static final int MAX_SUFFIX_NUMBER = 3;
    private static final char SUFFIX = '·';
    /* access modifiers changed from: private */
    public boolean cancelable = true;

    /* renamed from: tv */
    private TextView f826tv;
    private TextView tv_point;

    public LoadingDialog(Activity activity) {
        super(activity);
        init();
    }

    private void init() {
        View contentView = ReflectResource.getInstance(getContext()).getLayoutView("eaglesdk_loding_dialog_layout");
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(17170445);
            window.setDimAmount(0.0f);
        }
        setContentView(contentView);
        contentView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (LoadingDialog.this.cancelable) {
                    LoadingDialog.this.dismiss();
                }
            }
        });
        this.f826tv = (TextView) contentView.findViewById(ReflectResource.getInstance(getContext()).getWidgetViewID("tv"));
    }

    public void show() {
        super.show();
    }

    public void dismiss() {
        super.dismiss();
    }

    public void setCancelable(boolean flag) {
        this.cancelable = flag;
        super.setCancelable(flag);
    }

    public void setTitle(CharSequence title) {
        this.f826tv.setText(title);
        if (this.f826tv.getVisibility() != 0) {
            this.f826tv.setVisibility(0);
        }
    }

    public void setTitle(int titleId) {
        setTitle((CharSequence) getContext().getString(titleId));
    }
}
