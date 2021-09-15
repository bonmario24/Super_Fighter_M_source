package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public final class DeviceProperties {
    private static Boolean zzhb;
    private static Boolean zzhc;
    private static Boolean zzhd;
    private static Boolean zzhe;
    private static Boolean zzhf;
    private static Boolean zzhg;
    private static Boolean zzhh;
    private static Boolean zzhi;
    private static Boolean zzhj;

    private DeviceProperties() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        if (zzhc.booleanValue() != false) goto L_0x003a;
     */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isTablet(android.content.res.Resources r5) {
        /*
            r4 = 3
            r1 = 1
            r2 = 0
            if (r5 != 0) goto L_0x0006
        L_0x0005:
            return r2
        L_0x0006:
            java.lang.Boolean r0 = zzhb
            if (r0 != 0) goto L_0x0041
            android.content.res.Configuration r0 = r5.getConfiguration()
            int r0 = r0.screenLayout
            r0 = r0 & 15
            if (r0 <= r4) goto L_0x0048
            r0 = r1
        L_0x0015:
            if (r0 != 0) goto L_0x003a
            java.lang.Boolean r0 = zzhc
            if (r0 != 0) goto L_0x0032
            android.content.res.Configuration r0 = r5.getConfiguration()
            int r3 = r0.screenLayout
            r3 = r3 & 15
            if (r3 > r4) goto L_0x004a
            int r0 = r0.smallestScreenWidthDp
            r3 = 600(0x258, float:8.41E-43)
            if (r0 < r3) goto L_0x004a
            r0 = r1
        L_0x002c:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            zzhc = r0
        L_0x0032:
            java.lang.Boolean r0 = zzhc
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x003b
        L_0x003a:
            r2 = r1
        L_0x003b:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)
            zzhb = r0
        L_0x0041:
            java.lang.Boolean r0 = zzhb
            boolean r2 = r0.booleanValue()
            goto L_0x0005
        L_0x0048:
            r0 = r2
            goto L_0x0015
        L_0x004a:
            r0 = r2
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.DeviceProperties.isTablet(android.content.res.Resources):boolean");
    }

    @TargetApi(20)
    @KeepForSdk
    public static boolean isWearable(Context context) {
        if (zzhd == null) {
            zzhd = Boolean.valueOf(PlatformVersion.isAtLeastKitKatWatch() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return zzhd.booleanValue();
    }

    @TargetApi(26)
    @KeepForSdk
    public static boolean isWearableWithoutPlayStore(Context context) {
        return isWearable(context) && (!PlatformVersion.isAtLeastN() || (zzf(context) && !PlatformVersion.isAtLeastO()));
    }

    @TargetApi(21)
    @KeepForSdk
    public static boolean isSidewinder(Context context) {
        return zzf(context);
    }

    @TargetApi(21)
    private static boolean zzf(Context context) {
        if (zzhe == null) {
            zzhe = Boolean.valueOf(PlatformVersion.isAtLeastLollipop() && context.getPackageManager().hasSystemFeature("cn.google"));
        }
        return zzhe.booleanValue();
    }

    @KeepForSdk
    public static boolean isLatchsky(Context context) {
        if (zzhf == null) {
            PackageManager packageManager = context.getPackageManager();
            zzhf = Boolean.valueOf(packageManager.hasSystemFeature("com.google.android.feature.services_updater") && packageManager.hasSystemFeature("cn.google.services"));
        }
        return zzhf.booleanValue();
    }

    public static boolean zzg(Context context) {
        if (zzhg == null) {
            zzhg = Boolean.valueOf(context.getPackageManager().hasSystemFeature("android.hardware.type.iot") || context.getPackageManager().hasSystemFeature("android.hardware.type.embedded"));
        }
        return zzhg.booleanValue();
    }

    @KeepForSdk
    public static boolean isFeaturePhone(Context context) {
        if (zzhj == null) {
            zzhj = Boolean.valueOf(context.getPackageManager().hasSystemFeature("android.hardware.type.featurephone"));
        }
        return zzhj.booleanValue();
    }

    @KeepForSdk
    public static boolean isAuto(Context context) {
        if (zzhh == null) {
            zzhh = Boolean.valueOf(PlatformVersion.isAtLeastO() && context.getPackageManager().hasSystemFeature("android.hardware.type.automotive"));
        }
        return zzhh.booleanValue();
    }

    @KeepForSdk
    public static boolean isTv(Context context) {
        if (zzhi == null) {
            PackageManager packageManager = context.getPackageManager();
            zzhi = Boolean.valueOf(packageManager.hasSystemFeature("com.google.android.tv") || packageManager.hasSystemFeature("android.hardware.type.television") || packageManager.hasSystemFeature("android.software.leanback"));
        }
        return zzhi.booleanValue();
    }

    @KeepForSdk
    public static boolean isUserBuild() {
        return "user".equals(Build.TYPE);
    }
}
