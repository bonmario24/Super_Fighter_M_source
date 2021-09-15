package com.xsdk.doraemon.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

public class LabelViewPager extends ViewPager {
    public LabelViewPager(@NonNull Context context) {
        super(context);
    }

    public LabelViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        SDKLoggerUtil.getLogger().mo19502e("getChildCount()=" + getChildCount() + "", new Object[0]);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, 0));
            int h = child.getMeasuredHeight();
            if (h > height) {
                height = h;
            }
        }
        super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(height, 1073741824));
    }
}
