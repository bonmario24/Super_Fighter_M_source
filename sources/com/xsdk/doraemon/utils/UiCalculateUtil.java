package com.xsdk.doraemon.utils;

import android.content.Context;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

public class UiCalculateUtil {
    public static int calculateTheLayoutWidth(Context context, float ratioLandscape, float ratioPortrait) {
        int screenWidthPixels = context.getResources().getDisplayMetrics().widthPixels;
        if (isLandscape(context)) {
            return (int) (((float) screenWidthPixels) * ratioLandscape);
        }
        return (int) (((float) screenWidthPixels) * ratioPortrait);
    }

    public static int calculateTheLayoutWidth(Context context) {
        return calculateTheLayoutWidth(context, 0.5f, 0.9f);
    }

    public static int calculateTheLayoutHeight(Context context, float ratioLandscape, float ratioPortrait) {
        int screenHeightPixels = context.getResources().getDisplayMetrics().heightPixels;
        if (isLandscape(context)) {
            return (int) (((float) screenHeightPixels) * ratioLandscape);
        }
        return (int) (((float) screenHeightPixels) * ratioPortrait);
    }

    public static int calculateTheLayoutHeight(Context context) {
        return calculateTheLayoutHeight(context, 0.8f, 0.6f);
    }

    public static boolean isLandscape(Context context) {
        if (context.getResources().getConfiguration().orientation == 2) {
            SDKLoggerUtil.getLogger().mo19502e("landscape", new Object[0]);
            return true;
        } else if (context.getResources().getConfiguration().orientation != 1) {
            return false;
        } else {
            SDKLoggerUtil.getLogger().mo19502e("portrait", new Object[0]);
            return false;
        }
    }
}
