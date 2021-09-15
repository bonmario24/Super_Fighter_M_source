package com.xsdk.doraemon.utils.notch;

import android.app.Activity;
import android.graphics.Point;
import com.xsdk.doraemon.utils.notch.base.BaseNotch;
import com.xsdk.doraemon.utils.notch.utils.DeviceTools;

public class LenovoNotch extends BaseNotch {
    private String TAG = LenovoNotch.class.getSimpleName();
    private Activity activity;

    public LenovoNotch(Activity activity2) {
        this.activity = activity2;
    }

    public boolean isSupportNotch() {
        int resourceId = this.activity.getResources().getIdentifier("config_screen_has_notch", "bool", "android");
        if (resourceId > 0) {
            return this.activity.getResources().getBoolean(resourceId);
        }
        return false;
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

    public int getNotchHeight() {
        int resourceId;
        if (!isSupportNotch() || isHideNotch() || (resourceId = this.activity.getResources().getIdentifier("notch_h", "integer", "android")) <= 0) {
            return 0;
        }
        return this.activity.getResources().getInteger(resourceId);
    }

    public int getNotchWidth() {
        int resourceId;
        if (!isSupportNotch() || isHideNotch() || (resourceId = this.activity.getResources().getIdentifier("notch_w", "integer", "android")) <= 0) {
            return 0;
        }
        return this.activity.getResources().getInteger(resourceId);
    }
}
