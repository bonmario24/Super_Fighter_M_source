package com.xhuyu.component.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import com.doraemon.util.DeviceUtils;
import com.doraemon.util.EncryptUtils;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

public class AppUtil {
    public static String getStringMetaData(Context context, String metaName) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (appInfo != null) {
                return appInfo.metaData.getString(metaName);
            }
            return "";
        } catch (Exception paramContext) {
            paramContext.printStackTrace();
            return "";
        }
    }

    public static String get16UniquId(Context context) {
        if (Build.VERSION.SDK_INT >= 26) {
            return get16DeviceId();
        }
        return get16EncodeKey(context);
    }

    private static String get16EncodeKey(Context context) {
        String key = DeviceUtils.getAndroidID();
        if (key.length() < 16) {
            return key.concat("0000000000000000").substring(0, 16);
        }
        return key;
    }

    private static String get16DeviceId() {
        String dst = EncryptUtils.encryptMD5ToLowerString(Build.PRODUCT + Build.DISPLAY + Build.BOARD + Build.MANUFACTURER);
        if (TextUtils.isEmpty(dst)) {
            return "0000000000000000";
        }
        return dst.substring(8, 24);
    }

    public static String getAppName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return String.valueOf(context.getPackageManager().getApplicationLabel(context.getApplicationInfo()));
        } catch (Throwable e) {
            SDKLoggerUtil.getLogger().mo19504i("getAppName >> e:" + e.toString(), new Object[0]);
            return "HuYuSDK";
        }
    }

    public static String getIMEI(Context context) {
        return DeviceUtils.getIMEI(context);
    }
}
