package com.xsdk.doraemon.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ActivityCore {
    public static boolean isTranslucentOrFloating(Activity activity) {
        boolean isTranslucentOrFloating = false;
        try {
            TypedArray ta = activity.obtainStyledAttributes((int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get((Object) null));
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", new Class[]{TypedArray.class});
            m.setAccessible(true);
            isTranslucentOrFloating = ((Boolean) m.invoke((Object) null, new Object[]{ta})).booleanValue();
            m.setAccessible(false);
            return isTranslucentOrFloating;
        } catch (Exception e) {
            e.printStackTrace();
            return isTranslucentOrFloating;
        }
    }

    public static boolean fixOrientation(Activity activity) {
        try {
            Field field = Activity.class.getDeclaredField("mActivityInfo");
            field.setAccessible(true);
            ((ActivityInfo) field.get(activity)).screenOrientation = -1;
            field.setAccessible(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
