package com.xhuyu.component.utils;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xsdk.doraemon.apkreflect.ReflectResource;

public class BaseButtonUtil {
    private View base;

    /* renamed from: iv */
    private ImageView f888iv;
    private TextView tvTitle;

    public BaseButtonUtil(View base2) {
        this.base = base2;
        this.tvTitle = (TextView) ReflectResource.getInstance(base2.getContext()).getWidgetView(base2, "tv_title");
        this.f888iv = (ImageView) ReflectResource.getInstance(base2.getContext()).getWidgetView(base2, "iv");
    }

    public void setText(String text) {
        this.tvTitle.setText(text);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.base.setOnClickListener(listener);
    }

    public void setImageIco(String resName) {
        if (TextUtils.isEmpty(resName)) {
            this.f888iv.setVisibility(8);
            return;
        }
        Drawable drawable = ReflectResource.getInstance(this.base.getContext()).getDrawable(resName);
        this.f888iv.setImageDrawable(drawable);
        drawable.setBounds(0, 0, (int) (((double) drawable.getMinimumWidth()) * 0.8d), (int) (((double) drawable.getMinimumHeight()) * 0.8d));
        this.tvTitle.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    public View getBaseView() {
        return this.base;
    }

    public TextView getTextView() {
        return this.tvTitle;
    }

    public void setTextColor(int color) {
        this.tvTitle.setTextColor(color);
    }

    public void setVisibility(int visibility) {
        this.base.setVisibility(visibility);
    }

    public int getVisibility() {
        return this.base.getVisibility();
    }
}
