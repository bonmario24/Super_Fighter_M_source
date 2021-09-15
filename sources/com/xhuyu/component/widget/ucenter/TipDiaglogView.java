package com.xhuyu.component.widget.ucenter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.xhuyu.component.callback.OnMultiClickListener;
import com.xhuyu.component.widget.TitleBar;

public class TipDiaglogView extends IViewWrapper {
    private Button btnCancel;
    private Button btnConfirm;
    private boolean isShowing = false;
    /* access modifiers changed from: private */
    public IDialogViewListener listener;
    private TitleBar titleBar;
    private TextView tvContent;

    public interface IDialogViewListener {
        void onCancel(TipDiaglogView tipDiaglogView);

        void onComfir(TipDiaglogView tipDiaglogView);
    }

    public TipDiaglogView(Context context) {
        super(context);
    }

    public void initView() {
        this.btnCancel = (Button) findViewByName("btn_cancel");
        this.btnConfirm = (Button) findViewByName("btn_confirm");
        this.tvContent = (TextView) findViewByName("tv_content_title");
        this.titleBar = new TitleBar(findViewByName("rl_title_contains"));
        this.titleBar.setTitle(getString("huyu_tip_wxtip"));
        this.titleBar.setBackIconVisiable(false);
        getBaseView().setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        getBaseView().setOnClickListener(new OnMultiClickListener() {
            public void onMultiClick(View view) {
            }
        });
        this.btnCancel.setOnClickListener(new OnMultiClickListener() {
            public void onMultiClick(View view) {
                if (TipDiaglogView.this.listener != null) {
                    TipDiaglogView.this.listener.onCancel(TipDiaglogView.this);
                }
            }
        });
        this.btnConfirm.setOnClickListener(new OnMultiClickListener() {
            public void onMultiClick(View view) {
                if (TipDiaglogView.this.listener != null) {
                    TipDiaglogView.this.listener.onComfir(TipDiaglogView.this);
                }
            }
        });
    }

    public void show(ViewGroup vp) {
        vp.addView(getBaseView());
        this.isShowing = true;
    }

    public void dismiss() {
        ViewGroup vp = (ViewGroup) getBaseView().getParent();
        if (vp != null) {
            vp.removeView(getBaseView());
        }
        this.isShowing = false;
    }

    public boolean isShowing() {
        return this.isShowing;
    }

    public void setContentText(String contentText) {
        this.tvContent.setText(contentText);
    }

    public String getLayoutName() {
        return "diglog_tip";
    }

    public void setListener(IDialogViewListener listener2) {
        this.listener = listener2;
    }
}
