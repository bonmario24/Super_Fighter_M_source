package com.xsdk.doraemon.utils.notch;

import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.DisplayCutout;
import com.xsdk.doraemon.utils.notch.base.BaseNotch;

public class SamsungNotch extends BaseNotch {
    private String TAG = SamsungNotch.class.getSimpleName();
    private Activity activity;
    private DisplayCutout displayCutout;

    public SamsungNotch(Activity activity2, DisplayCutout displayCutout2) {
        this.activity = activity2;
        this.displayCutout = displayCutout2;
    }

    public boolean isSupportNotch() {
        try {
            Resources res = this.activity.getResources();
            int resId = res.getIdentifier("config_mainBuiltInDisplayCutout", "string", "android");
            String spec = resId > 0 ? res.getString(resId) : null;
            if (spec == null || TextUtils.isEmpty(spec)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.e(this.TAG, "getFeature Exception");
            return false;
        }
    }

    @RequiresApi(api = 28)
    public boolean isHideNotch() {
        if (this.activity.getResources().getConfiguration().orientation == 1) {
            int top = this.displayCutout.getSafeInsetTop();
            int bottom = this.displayCutout.getSafeInsetBottom();
            if (top > 0 || bottom > 0) {
                return false;
            }
            return true;
        }
        int left = this.displayCutout.getSafeInsetLeft();
        int right = this.displayCutout.getSafeInsetRight();
        if (left > 0 || right > 0) {
            return false;
        }
        return true;
    }

    @RequiresApi(api = 28)
    public int getNotchHeight() {
        if (!isSupportNotch() || isHideNotch()) {
            return 0;
        }
        if (this.activity.getResources().getConfiguration().orientation == 1) {
            int top = this.displayCutout.getSafeInsetTop();
            int bottom = this.displayCutout.getSafeInsetBottom();
            if (top <= bottom) {
                return bottom;
            }
            return top;
        }
        int left = this.displayCutout.getSafeInsetLeft();
        int right = this.displayCutout.getSafeInsetRight();
        if (left <= right) {
            left = right;
        }
        return left;
    }
}
