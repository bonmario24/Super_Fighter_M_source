package com.smarx.notchlib.impl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.Window;
import com.smarx.notchlib.INotchScreen;
import com.smarx.notchlib.utils.ScreenUtil;
import java.util.ArrayList;

@TargetApi(26)
public class MiNotchScreen implements INotchScreen {
    public static int getNotchHeight(Context context) {
        int identifier = context.getResources().getIdentifier("notch_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getNotchWidth(Context context) {
        int identifier = context.getResources().getIdentifier("notch_width", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private static boolean isNotch() {
        try {
            return ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", new Class[]{String.class, Integer.TYPE}).invoke((Object) null, new Object[]{"ro.miui.notch", 0})).intValue() == 1;
        } catch (Throwable th) {
            return false;
        }
    }

    public void getNotchRect(Activity activity, INotchScreen.NotchSizeCallback notchSizeCallback) {
        Rect calculateNotchRect = ScreenUtil.calculateNotchRect(activity, getNotchWidth(activity), getNotchHeight(activity));
        ArrayList arrayList = new ArrayList();
        arrayList.add(calculateNotchRect);
        notchSizeCallback.onResult(arrayList);
    }

    public boolean hasNotch(Activity activity) {
        return isNotch();
    }

    public void setDisplayInNotch(Activity activity) {
        Class<Window> cls = Window.class;
        try {
            cls.getMethod("addExtraFlags", new Class[]{Integer.TYPE}).invoke(activity.getWindow(), new Object[]{1792});
        } catch (Exception e) {
        }
    }
}
