package com.xsdk.doraemon.utils.notch;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import com.xsdk.doraemon.utils.notch.base.BaseNotch;
import com.xsdk.doraemon.utils.notch.utils.DeviceTools;
import java.lang.reflect.Method;

public class VivoNotch extends BaseNotch {
    private String TAG = VivoNotch.class.getSimpleName();
    private Activity activity;

    public VivoNotch(Activity activity2) {
        this.activity = activity2;
    }

    public Point getResolution(Activity activity2) {
        int statusBarHeight = DeviceTools.getStatusBarHeight(activity2);
        int navigationBarHeight = DeviceTools.getNavigationBarHeight(activity2);
        Point pReal = DeviceTools.getScreenRealSize(activity2);
        Point pShot = DeviceTools.getScreenShotSize(activity2);
        if ((pReal.x > pReal.y ? pReal.x : pReal.y) - (pShot.x > pShot.y ? pShot.x : pShot.y) == statusBarHeight + navigationBarHeight) {
            return DeviceTools.getScreenDisplaySize(activity2);
        }
        return pReal;
    }

    public boolean isSupportNotch() {
        try {
            Method[] methods = Class.forName("android.util.FtFeature").getDeclaredMethods();
            Method method = null;
            int length = methods.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Method m = methods[i];
                if (m.getName().equalsIgnoreCase("isFeatureSupport")) {
                    method = m;
                    break;
                }
                i++;
            }
            return ((Boolean) method.invoke((Object) null, new Object[]{32})).booleanValue();
        } catch (ClassNotFoundException e) {
            Log.e(this.TAG, "getFeature ClassNotFoundException");
            return false;
        } catch (Exception e2) {
            Log.e(this.TAG, "getFeature Exception");
            return false;
        }
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
        if (this.activity.getResources().getConfiguration().orientation == 2 && Build.MODEL.contains("V1824") && realSize - shotSize == 73) {
            return true;
        }
        return false;
    }

    public int getNotchWidth() {
        if (!isSupportNotch() || isHideNotch()) {
            return 0;
        }
        return (DeviceTools.getStatusBarHeight(this.activity) * 100) / 32;
    }

    public int getNotchHeight() {
        if (!isSupportNotch() || isHideNotch()) {
            return 0;
        }
        return (DeviceTools.getStatusBarHeight(this.activity) * 27) / 32;
    }

    public int getBottomDangerHeight() {
        if (!isSupportNotch() || isHideNotch()) {
            return 0;
        }
        return (DeviceTools.getStatusBarHeight(this.activity) * 24) / 32;
    }
}
