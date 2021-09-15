package com.xsdk.doraemon.utils.notch.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import com.facebook.appevents.AppEventsConstants;
import java.lang.reflect.Method;

public class DeviceTools {
    public static boolean isSupportAndroidVersion() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static Point getScreenRealSize(Context context) {
        Point screenSize = null;
        try {
            Point screenSize2 = new Point();
            try {
                Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                if (Build.VERSION.SDK_INT >= 17) {
                    defaultDisplay.getRealSize(screenSize2);
                } else {
                    try {
                        screenSize2.set(((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue(), ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue());
                    } catch (Exception e) {
                        screenSize2.set(defaultDisplay.getWidth(), defaultDisplay.getHeight());
                        e.printStackTrace();
                    }
                }
                return screenSize2;
            } catch (Exception e2) {
                e = e2;
                screenSize = screenSize2;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return screenSize;
        }
    }

    public static Point getScreenDisplaySize(Activity activity) {
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        Point screenSize = new Point();
        screenSize.set(dm.widthPixels, dm.heightPixels);
        return screenSize;
    }

    public static Point getScreenShotSize(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Point screenSize = new Point();
        if (bmp != null) {
            screenSize.set(bmp.getWidth(), bmp.getHeight());
        }
        return screenSize;
    }

    public static int getStatusBarHeight(Context context) {
        int resourceId;
        if (context != null && (resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android")) > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static int getNavigationBarHeight(Context context) {
        if (context.getResources().getIdentifier("config_showNavigationBar", "bool", "android") == 0) {
            return 0;
        }
        return context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    @TargetApi(14)
    public static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            try {
                String sNavBarOverride = getNavBarOverride();
                if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(sNavBarOverride)) {
                    return false;
                }
                if (AppEventsConstants.EVENT_PARAM_VALUE_NO.equals(sNavBarOverride)) {
                    return true;
                }
                return hasNav;
            } catch (Exception e) {
                e.printStackTrace();
                return hasNav;
            }
        } else {
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    private static String getNavBarOverride() {
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        try {
            Method m = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class});
            m.setAccessible(true);
            return (String) m.invoke((Object) null, new Object[]{"qemu.hw.mainkeys"});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void hideBottomUIMenu(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            if (Build.VERSION.SDK_INT < 19) {
                activity.getWindow().getDecorView().setSystemUiVisibility(8);
            } else {
                activity.getWindow().getDecorView().setSystemUiVisibility(5894);
            }
        }
    }
}
