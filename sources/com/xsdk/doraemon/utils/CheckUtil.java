package com.xsdk.doraemon.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CheckUtil {
    public static boolean isInteger(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return Pattern.compile("^[-\\+]?[\\d]*$").matcher(str).matches();
    }

    public static boolean checkTextEmpty(String... content) {
        for (String isEmpty : content) {
            if (isEmpty(isEmpty)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(String args) {
        if (TextUtils.isEmpty(args) || "null".equalsIgnoreCase(args)) {
            return true;
        }
        return false;
    }

    public static boolean isNumericzidai(String str) {
        if (!isEmpty(str) && Pattern.compile("-?[0-9]+.?[0-9]+").matcher(str).matches()) {
            return true;
        }
        return false;
    }

    public static boolean checkPackageApp(Context context, String packageName) {
        List<PackageInfo> pInfo = context.getApplicationContext().getPackageManager().getInstalledPackages(0);
        List<String> pName = new ArrayList<>();
        if (pInfo != null) {
            for (int i = 0; i < pInfo.size(); i++) {
                pName.add(pInfo.get(i).packageName);
            }
        }
        return pName.contains(packageName);
    }
}
