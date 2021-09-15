package com.xsdk.doraemon.utils;

public class FastClickUtil {
    private static final int MIN_CLICK_DELAY_TIME = 800;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if (curClickTime - lastClickTime <= 800) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    public static void reset() {
        lastClickTime = 0;
    }
}
