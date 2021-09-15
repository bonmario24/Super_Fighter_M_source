package com.xsdk.doraemon.utils.notch;

import android.app.Activity;
import android.graphics.Point;
import com.xsdk.doraemon.utils.notch.base.BaseNotch;
import com.xsdk.doraemon.utils.notch.utils.DeviceTools;

public class OppoNotch extends BaseNotch {
    private String TAG = OppoNotch.class.getSimpleName();
    private Activity activity;

    public OppoNotch(Activity activity2) {
        this.activity = activity2;
    }

    public boolean isSupportNotch() {
        return this.activity.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    public boolean isHideNotch() {
        Point pReal = getResolution(this.activity);
        Point pShot = DeviceTools.getScreenShotSize(this.activity);
        int realSize = pReal.x > pReal.y ? pReal.x : pReal.y;
        int shotSize = pShot.x > pShot.y ? pShot.x : pShot.y;
        int statusBarHeigth = DeviceTools.getStatusBarHeight(this.activity);
        int off = realSize - shotSize;
        if (off == statusBarHeigth || off == statusBarHeigth + 1 || off == statusBarHeigth - 1) {
            return true;
        }
        return false;
    }

    public int getNotchWidth() {
        if (!isSupportNotch() || isHideNotch()) {
            return 0;
        }
        return 324;
    }

    public int getNotchHeight() {
        if (!isSupportNotch() || isHideNotch()) {
            return 0;
        }
        return 80;
    }
}
