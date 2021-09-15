package com.xsdk.doraemon.utils.notch.base;

import android.app.Activity;
import android.graphics.Point;
import com.xsdk.doraemon.utils.notch.utils.DeviceTools;

public abstract class BaseNotch {
    public int getNotchWidth() {
        return 0;
    }

    public int getNotchHeight() {
        return 0;
    }

    public int getBottomDangerHeight() {
        return 0;
    }

    public boolean isHideNotch() {
        return false;
    }

    public boolean isSupportNotch() {
        return false;
    }

    public Point getResolution(Activity activity) {
        return DeviceTools.getScreenRealSize(activity);
    }
}
