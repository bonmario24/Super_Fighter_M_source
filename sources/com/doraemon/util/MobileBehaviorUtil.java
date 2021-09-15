package com.doraemon.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import java.util.List;

public class MobileBehaviorUtil {
    public static String getInputMethod(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService("input_method");
        if (imm != null) {
            List<InputMethodInfo> methodList = imm.getInputMethodList();
            if (methodList.size() > 0) {
                return methodList.get(0).loadLabel(context.getPackageManager()).toString();
            }
        }
        return "";
    }

    public static String getBrowserApp(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setDataAndType(Uri.parse("http://"), (String) null);
        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent, 32);
        if (resolveInfoList.size() > 0) {
            return resolveInfoList.get(0).activityInfo.packageName;
        }
        return "";
    }

    public static boolean isSupportMagnetic(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.sensor.compass");
    }

    public static boolean isSupportGyroscope(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.sensor.gyroscope");
    }

    public static boolean isSupportMicrophone(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.microphone");
    }

    public static boolean isRoot() {
        return ShellAdbUtil.isRoot();
    }
}
