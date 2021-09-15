package com.xsdk.doraemon.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

public class LabelGridView extends GridView {
    public LabelGridView(Context context) {
        super(context);
    }

    public LabelGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LabelGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
