package com.xhuyu.component.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xsdk.doraemon.apkreflect.ReflectResource;

public class TitleBar {
    private View baseView;
    private ImageView ivBack;
    private ImageView ivClose;
    private TextView tvTitle;

    public TitleBar(View baseView2) {
        this.baseView = baseView2;
        initView();
    }

    private void initView() {
        if (this.baseView == null) {
            throw new RuntimeException("TitleBar baseView is null");
        }
        this.tvTitle = (TextView) findViewByName("tv_title");
        this.ivBack = (ImageView) findViewByName("iv_back");
        this.ivClose = (ImageView) findViewByName("iv_close");
        if (this.tvTitle == null || this.ivBack == null || this.ivClose == null) {
            throw new RuntimeException("illegality view set for TitleBar");
        }
    }

    private View findViewByName(String resName) {
        if (this.baseView != null) {
            return ReflectResource.getInstance(this.baseView.getContext().getApplicationContext()).getWidgetView(this.baseView, resName);
        }
        throw new RuntimeException("TitleBar baseView is null");
    }

    public void setTitle(String title) {
        if (this.tvTitle != null) {
            this.tvTitle.setText(title);
        }
    }

    public void setBackIcon(String resName) {
        if (this.ivBack != null) {
            this.ivBack.setImageDrawable(ReflectResource.getInstance(this.ivBack.getContext().getApplicationContext()).getDrawable(resName));
        }
    }

    public void setCloseIcon(String resName) {
        if (this.ivClose != null) {
            this.ivClose.setImageDrawable(ReflectResource.getInstance(this.ivClose.getContext().getApplicationContext()).getDrawable(resName));
        }
    }

    public void setBackIconVisiable(boolean visiable) {
        if (this.ivBack != null) {
            this.ivBack.setVisibility(visiable ? 0 : 8);
        }
    }

    public void setCloseIconVisiable(boolean visiable) {
        if (this.ivClose != null) {
            this.ivClose.setVisibility(visiable ? 0 : 8);
        }
    }

    public void setBackClickListener(View.OnClickListener listener) {
        if (this.ivBack != null) {
            this.ivBack.setOnClickListener(listener);
        }
    }

    public void setCloseClickListener(View.OnClickListener listener) {
        if (this.ivClose != null) {
            this.ivClose.setOnClickListener(listener);
        }
    }
}
