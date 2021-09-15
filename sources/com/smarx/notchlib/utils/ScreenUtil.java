package com.smarx.notchlib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class ScreenUtil {
    public static Rect calculateNotchRect(Context context, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int[] screenSize = getScreenSize(context);
        int i7 = screenSize[0];
        int i8 = screenSize[1];
        if (isPortrait(context)) {
            i3 = (i7 - i) / 2;
            i4 = 0;
            i5 = i3 + i;
            i6 = i2;
        } else {
            i3 = 0;
            i4 = (i8 - i) / 2;
            i5 = i2;
            i6 = i4 + i;
        }
        return new Rect(i3, i4, i5, i6);
    }

    public static View getContentView(Activity activity) {
        return activity.getWindow().getDecorView().findViewById(16908290);
    }

    public static Rect getContentViewDisplayFrame(Activity activity) {
        View contentView = getContentView(activity);
        Rect rect = new Rect();
        contentView.getWindowVisibleDisplayFrame(rect);
        return rect;
    }

    public static int getContentViewHeight(Activity activity) {
        return getContentView(activity).getHeight();
    }

    private static int getDimensionPixel(Context context, String str) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(str, "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getNavigationBarHeight(Context context) {
        return getDimensionPixel(context, "navigation_bar_height");
    }

    public static int[] getScreenSize(Context context) {
        int[] iArr = new int[2];
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point point = new Point();
                Display.class.getMethod("getRealSize", new Class[]{Point.class}).invoke(defaultDisplay, new Object[]{point});
                i = point.x;
                i2 = point.y;
            } catch (Throwable th) {
            }
        }
        iArr[0] = i;
        iArr[1] = i2;
        return iArr;
    }

    public static int getStatusBarHeight(Context context) {
        return getDimensionPixel(context, "status_bar_height");
    }

    public static boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }
}
