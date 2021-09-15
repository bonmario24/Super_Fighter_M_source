package com.xsdk.doraemon.utils.notch.utils;

import android.app.Activity;
import android.util.Log;
import com.xsdk.doraemon.utils.notch.HuaWeiNotch;
import com.xsdk.doraemon.utils.notch.LenovoNotch;
import com.xsdk.doraemon.utils.notch.MeizuNotch;
import com.xsdk.doraemon.utils.notch.NubiaNotch;
import com.xsdk.doraemon.utils.notch.OnePlusNotch;
import com.xsdk.doraemon.utils.notch.OppoNotch;
import com.xsdk.doraemon.utils.notch.VivoNotch;
import com.xsdk.doraemon.utils.notch.XiaomiNotch;

public class NotchUtil {
    public static int[] getNotchAtSize(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            Log.e("NotchUtil", "activity is finishing or is null");
            return null;
        }
        int[] notchSize = getHuaWeiNotch(activity);
        if (notchSize == null) {
            notchSize = getXiaomiNotch(activity);
        }
        if (notchSize == null) {
            notchSize = getVivoNotch(activity);
        }
        if (notchSize == null) {
            notchSize = getOppoNotch(activity);
        }
        if (notchSize == null) {
            notchSize = getLenovoNotch(activity);
        }
        if (notchSize == null) {
            notchSize = getMeizuNotch(activity);
        }
        if (notchSize == null) {
            notchSize = getNubiaNotch(activity);
        }
        if (notchSize == null) {
            return getOnePlusNotch(activity);
        }
        return notchSize;
    }

    private static int[] getHuaWeiNotch(Activity activity) {
        HuaWeiNotch huaWeiNotch = new HuaWeiNotch(activity);
        int notchWidth = huaWeiNotch.getNotchWidth();
        int notchHeight = huaWeiNotch.getNotchHeight();
        if (notchWidth <= 0 || notchHeight <= 0) {
            return null;
        }
        return new int[]{notchWidth, notchHeight};
    }

    private static int[] getLenovoNotch(Activity activity) {
        LenovoNotch lenovoNotch = new LenovoNotch(activity);
        int notchWidth = lenovoNotch.getNotchWidth();
        int notchHeight = lenovoNotch.getNotchHeight();
        if (notchWidth <= 0 || notchHeight <= 0) {
            return null;
        }
        return new int[]{notchWidth, notchHeight};
    }

    private static int[] getMeizuNotch(Activity activity) {
        int notchHeight = new MeizuNotch(activity).getNotchHeight();
        if (notchHeight <= 0) {
            return null;
        }
        return new int[]{0, notchHeight};
    }

    private static int[] getNubiaNotch(Activity activity) {
        int notchHeight = new NubiaNotch(activity).getNotchHeight();
        if (notchHeight <= 0) {
            return null;
        }
        return new int[]{0, notchHeight};
    }

    private static int[] getOnePlusNotch(Activity activity) {
        int notchHeight = new OnePlusNotch(activity).getNotchHeight();
        if (notchHeight <= 0) {
            return null;
        }
        return new int[]{0, notchHeight};
    }

    private static int[] getOppoNotch(Activity activity) {
        OppoNotch oppoNotch = new OppoNotch(activity);
        int notchWidth = oppoNotch.getNotchWidth();
        int notchHeight = oppoNotch.getNotchHeight();
        if (notchWidth <= 0 || notchHeight <= 0) {
            return null;
        }
        return new int[]{notchWidth, notchHeight};
    }

    private static int[] getVivoNotch(Activity activity) {
        VivoNotch vivoNotch = new VivoNotch(activity);
        int notchWidth = vivoNotch.getNotchWidth();
        int notchHeight = vivoNotch.getNotchHeight();
        if (notchWidth <= 0 || notchHeight <= 0) {
            return null;
        }
        return new int[]{notchWidth, notchHeight};
    }

    private static int[] getXiaomiNotch(Activity activity) {
        XiaomiNotch xiaomiNotch = new XiaomiNotch(activity);
        int notchWidth = xiaomiNotch.getNotchWidth();
        int notchHeight = xiaomiNotch.getNotchHeight();
        if (notchWidth <= 0 || notchHeight <= 0) {
            return null;
        }
        return new int[]{notchWidth, notchHeight};
    }
}
