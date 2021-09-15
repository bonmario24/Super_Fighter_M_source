package com.doraemon.util;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public final class SizeUtils {

    public interface onGetSizeListener {
        void onGetSize(View view);
    }

    private SizeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int dp2px(float dpValue) {
        return (int) ((dpValue * Utils.getApp().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dp(float pxValue) {
        return (int) ((pxValue / Utils.getApp().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int sp2px(float spValue) {
        return (int) ((spValue * Utils.getApp().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int px2sp(float pxValue) {
        return (int) ((pxValue / Utils.getApp().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static float applyDimension(float value, int unit) {
        DisplayMetrics metrics = Utils.getApp().getResources().getDisplayMetrics();
        switch (unit) {
            case 0:
                return value;
            case 1:
                return value * metrics.density;
            case 2:
                return value * metrics.scaledDensity;
            case 3:
                return metrics.xdpi * value * 0.013888889f;
            case 4:
                return value * metrics.xdpi;
            case 5:
                return metrics.xdpi * value * 0.03937008f;
            default:
                return 0.0f;
        }
    }

    public static void forceGetViewSize(final View view, final onGetSizeListener listener) {
        view.post(new Runnable() {
            public void run() {
                if (listener != null) {
                    listener.onGetSize(view);
                }
            }
        });
    }

    public static int getMeasuredWidth(View view) {
        return measureView(view)[0];
    }

    public static int getMeasuredHeight(View view) {
        return measureView(view)[1];
    }

    public static int[] measureView(View view) {
        int heightSpec;
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(-1, -2);
        }
        int widthSpec = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
        int lpHeight = lp.height;
        if (lpHeight > 0) {
            heightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, 1073741824);
        } else {
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(widthSpec, heightSpec);
        return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
    }
}
