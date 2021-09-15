package com.star.libtrack.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

public class Utility {
    public static long parseLong(String str, long defvalue) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return defvalue;
        }
    }

    public static boolean isEmpty(String args) {
        if (TextUtils.isEmpty(args) || "null".equalsIgnoreCase(args)) {
            return true;
        }
        return false;
    }

    public static int packageCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String packageName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
