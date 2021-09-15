package com.smarx.notchlib.impl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Rect;
import android.view.Window;
import android.view.WindowManager;
import com.smarx.notchlib.INotchScreen;
import com.smarx.notchlib.utils.ScreenUtil;
import java.util.ArrayList;
import java.util.List;

@TargetApi(26)
public class HuaweiNotchScreen implements INotchScreen {
    public static final int FLAG_NOTCH_SUPPORT = 65536;

    public static void setNotDisplayInNotch(Activity activity) {
        try {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            Class<?> cls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            Object newInstance = cls.getConstructor(new Class[]{WindowManager.LayoutParams.class}).newInstance(new Object[]{attributes});
            cls.getMethod("clearHwFlags", new Class[]{Integer.TYPE}).invoke(newInstance, new Object[]{65536});
            window.getWindowManager().updateViewLayout(window.getDecorView(), window.getDecorView().getLayoutParams());
        } catch (Throwable th) {
        }
    }

    public void getNotchRect(Activity activity, INotchScreen.NotchSizeCallback notchSizeCallback) {
        try {
            Class<?> loadClass = activity.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            int[] iArr = (int[]) loadClass.getMethod("getNotchSize", new Class[0]).invoke(loadClass, new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(ScreenUtil.calculateNotchRect(activity, iArr[0], iArr[1]));
            notchSizeCallback.onResult(arrayList);
        } catch (Throwable th) {
            notchSizeCallback.onResult((List<Rect>) null);
        }
    }

    public boolean hasNotch(Activity activity) {
        try {
            Class<?> loadClass = activity.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return ((Boolean) loadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(loadClass, new Object[0])).booleanValue();
        } catch (Throwable th) {
            return false;
        }
    }

    public void setDisplayInNotch(Activity activity) {
        try {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            Class<?> cls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            Object newInstance = cls.getConstructor(new Class[]{WindowManager.LayoutParams.class}).newInstance(new Object[]{attributes});
            cls.getMethod("addHwFlags", new Class[]{Integer.TYPE}).invoke(newInstance, new Object[]{65536});
            window.getWindowManager().updateViewLayout(window.getDecorView(), window.getDecorView().getLayoutParams());
        } catch (Throwable th) {
        }
    }
}
