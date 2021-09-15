package com.smarx.notchlib.impl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.lzy.okgo.model.Progress;
import com.smarx.notchlib.INotchScreen;
import com.smarx.notchlib.utils.ScreenUtil;
import java.util.ArrayList;

@TargetApi(26)
public class VivoNotchScreen implements INotchScreen {
    private static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int getNotchHeight(Context context) {
        return (int) (27.0f * getDensity(context));
    }

    public static int getNotchWidth(Context context) {
        return (int) (100.0f * getDensity(context));
    }

    public static boolean isNotch() {
        try {
            Class<?> cls = Class.forName("android.util.FtFeature");
            return ((Boolean) cls.getMethod("isFtFeatureSupport", new Class[]{Integer.TYPE}).invoke(cls.newInstance(), new Object[]{32})).booleanValue();
        } catch (Exception e) {
            Log.e(Progress.TAG, "get error() ", e);
            return false;
        }
    }

    public void getNotchRect(Activity activity, INotchScreen.NotchSizeCallback notchSizeCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ScreenUtil.calculateNotchRect(activity, getNotchWidth(activity), getNotchHeight(activity)));
        notchSizeCallback.onResult(arrayList);
    }

    public boolean hasNotch(Activity activity) {
        return isNotch();
    }

    @Deprecated
    public void setDisplayInNotch(Activity activity) {
    }
}
