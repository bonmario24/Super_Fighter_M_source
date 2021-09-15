package com.xhuyu.component.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import com.thinkfly.star.utils.CheckUtils;

public class GameUtil {
    public static String getGameName(Context context) {
        try {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    public static String getGameBundle(Context context) {
        return context.getPackageName();
    }

    public static String getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static synchronized String getPackageName(Context context) {
        String str;
        synchronized (GameUtil.class) {
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
            } catch (Exception e) {
                e.printStackTrace();
                str = "";
            }
        }
        return str;
    }

    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    public static void launchMarketAppDetail(Activity activity, String appPkg, String marketPkg) throws Exception {
        if (!CheckUtils.isNullOrEmpty(appPkg)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + appPkg));
            if (!CheckUtils.isNullOrEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(268435456);
            activity.startActivity(intent);
        }
    }
}
