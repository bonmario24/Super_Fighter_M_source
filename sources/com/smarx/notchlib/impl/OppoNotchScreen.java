package com.smarx.notchlib.impl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Rect;
import android.text.TextUtils;
import com.smarx.notchlib.INotchScreen;
import com.smarx.notchlib.utils.ScreenUtil;
import java.util.ArrayList;
import java.util.List;

@TargetApi(26)
public class OppoNotchScreen implements INotchScreen {
    private static String getScreenValue() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls.newInstance(), new Object[]{"ro.oppo.screen.heteromorphism"});
        } catch (Throwable th) {
            return "";
        }
    }

    public void getNotchRect(Activity activity, INotchScreen.NotchSizeCallback notchSizeCallback) {
        int intValue;
        int intValue2;
        int intValue3;
        int intValue4;
        try {
            String screenValue = getScreenValue();
            if (!TextUtils.isEmpty(screenValue)) {
                String[] split = screenValue.split(":");
                String[] split2 = split[0].split(",");
                String[] split3 = split[1].split(",");
                if (ScreenUtil.isPortrait(activity)) {
                    intValue = Integer.valueOf(split2[0]).intValue();
                    intValue2 = Integer.valueOf(split2[1]).intValue();
                    intValue3 = Integer.valueOf(split3[0]).intValue();
                    intValue4 = Integer.valueOf(split3[1]).intValue();
                } else {
                    intValue = Integer.valueOf(split2[1]).intValue();
                    intValue2 = Integer.valueOf(split2[0]).intValue();
                    intValue3 = Integer.valueOf(split3[1]).intValue();
                    intValue4 = Integer.valueOf(split3[0]).intValue();
                }
                Rect rect = new Rect(intValue, intValue2, intValue3, intValue4);
                ArrayList arrayList = new ArrayList();
                arrayList.add(rect);
                notchSizeCallback.onResult(arrayList);
            }
        } catch (Throwable th) {
            notchSizeCallback.onResult((List<Rect>) null);
        }
    }

    public boolean hasNotch(Activity activity) {
        try {
            return activity.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
        } catch (Throwable th) {
            return false;
        }
    }

    @Deprecated
    public void setDisplayInNotch(Activity activity) {
    }
}
