package com.xsdk.doraemon.utils.notch;

import android.app.Activity;
import android.graphics.Point;
import android.provider.Settings;
import android.util.Log;
import com.xsdk.doraemon.utils.notch.base.BaseNotch;
import com.xsdk.doraemon.utils.notch.utils.DeviceTools;

public class HuaWeiNotch extends BaseNotch {
    private String TAG = HuaWeiNotch.class.getSimpleName();
    private Activity activity;

    public HuaWeiNotch(Activity activity2) {
        this.activity = activity2;
    }

    public boolean isSupportNotch() {
        try {
            Class<?> HwNotchSizeUtil = this.activity.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return ((Boolean) HwNotchSizeUtil.getMethod("hasNotchInScreen", new Class[0]).invoke(HwNotchSizeUtil, new Object[0])).booleanValue();
        } catch (ClassNotFoundException e) {
            Log.e(this.TAG, "isFeatureSupport ClassNotFoundException");
        } catch (NoSuchMethodException e2) {
            Log.e(this.TAG, "isFeatureSupport NoSuchMethodException");
        } catch (Exception e3) {
            Log.e(this.TAG, "isFeatureSupport Exception");
        }
        return false;
    }

    public boolean isHideNotch() {
        boolean isHide;
        if (Settings.Secure.getInt(this.activity.getContentResolver(), "display_notch_status", 0) == 1) {
            isHide = true;
        } else {
            isHide = false;
        }
        if (isHide) {
            return true;
        }
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
        return getNotchSize()[0];
    }

    public int getNotchHeight() {
        if (!isSupportNotch() || isHideNotch()) {
            return 0;
        }
        return getNotchSize()[1];
    }

    private int[] getNotchSize() {
        int[] ret = {0, 0};
        try {
            Class<?> HwNotchSizeUtil = this.activity.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return (int[]) HwNotchSizeUtil.getMethod("getNotchSize", new Class[0]).invoke(HwNotchSizeUtil, new Object[0]);
        } catch (ClassNotFoundException e) {
            Log.e(this.TAG, "getNotcSize ClassNotFoundException");
            return ret;
        } catch (NoSuchMethodException e2) {
            Log.e(this.TAG, "getNotcSize NoSuchMethodException");
            return ret;
        } catch (Exception e3) {
            Log.e(this.TAG, "getNotcSize Exception");
            return ret;
        }
    }
}
