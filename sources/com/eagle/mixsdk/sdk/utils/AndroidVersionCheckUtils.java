package com.eagle.mixsdk.sdk.utils;

import android.os.Build;

public class AndroidVersionCheckUtils {
    private AndroidVersionCheckUtils() {
    }

    public static boolean hasDonut() {
        return Build.VERSION.SDK_INT >= 4;
    }

    public static boolean hasEclair() {
        return Build.VERSION.SDK_INT >= 5;
    }

    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= 8;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= 9;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= 11;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= 12;
    }

    public static boolean hasIcecreamsandwich() {
        return Build.VERSION.SDK_INT >= 14;
    }
}
