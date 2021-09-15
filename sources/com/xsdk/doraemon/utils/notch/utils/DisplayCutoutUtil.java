package com.xsdk.doraemon.utils.notch.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.xsdk.doraemon.utils.notch.callback.OnDisplayCutoutListener;
import java.util.List;

public class DisplayCutoutUtil {
    /* access modifiers changed from: private */
    public static final String TAG = DisplayCutoutUtil.class.getSimpleName();

    public static void openFullScreenModel(Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            activity.requestWindowFeature(1);
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = 1;
            activity.getWindow().setAttributes(lp);
            activity.getWindow().getDecorView().setSystemUiVisibility(activity.getWindow().getDecorView().getSystemUiVisibility() | 3846);
        }
    }

    @RequiresApi(api = 28)
    public static void controlView(Activity activity, final OnDisplayCutoutListener listener) {
        activity.getWindow().getDecorView().setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                DisplayCutout cutout = windowInsets.getDisplayCutout();
                if (cutout == null) {
                    listener.onDisplayCutout((DisplayCutout) null, false);
                    Log.e(DisplayCutoutUtil.TAG, "cutout==null, is not notch screen");
                } else {
                    listener.onDisplayCutout(cutout, true);
                    List<Rect> rects = cutout.getBoundingRects();
                    if (rects.size() == 0) {
                        Log.e(DisplayCutoutUtil.TAG, "rects==null || rects.size()==0, is not notch screen");
                    } else {
                        Log.d(DisplayCutoutUtil.TAG, "rect size:" + rects.size());
                        for (Rect rect : rects) {
                            Log.d(DisplayCutoutUtil.TAG, "rect left:" + rect.left);
                            Log.d(DisplayCutoutUtil.TAG, "rect top:" + rect.top);
                            Log.d(DisplayCutoutUtil.TAG, "rect right:" + rect.right);
                            Log.d(DisplayCutoutUtil.TAG, "rect bottom:" + rect.bottom);
                        }
                    }
                    if (Build.VERSION.SDK_INT >= 29) {
                        Log.d(DisplayCutoutUtil.TAG, "----getBoundingRectTop:" + cutout.getBoundingRectTop());
                        Log.d(DisplayCutoutUtil.TAG, "----getBoundingRectBottom:" + cutout.getBoundingRectBottom());
                        Log.d(DisplayCutoutUtil.TAG, "----getBoundingRectLeft:" + cutout.getBoundingRectLeft());
                        Log.d(DisplayCutoutUtil.TAG, "----getBoundingRectLeft:" + cutout.getBoundingRectRight());
                    }
                    Log.d(DisplayCutoutUtil.TAG, "getSafeInsetTop:" + cutout.getSafeInsetTop());
                    Log.d(DisplayCutoutUtil.TAG, "getSafeInsetBottom:" + cutout.getSafeInsetBottom());
                    Log.d(DisplayCutoutUtil.TAG, "getSafeInsetLeft:" + cutout.getSafeInsetLeft());
                    Log.d(DisplayCutoutUtil.TAG, "getSafeInsetRight:" + cutout.getSafeInsetRight());
                }
                return windowInsets;
            }
        });
    }
}
