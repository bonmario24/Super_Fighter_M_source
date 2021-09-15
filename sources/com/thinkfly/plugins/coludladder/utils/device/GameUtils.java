package com.thinkfly.plugins.coludladder.utils.device;

import android.content.Context;
import com.doraemon.p027eg.CommonUtil;

public class GameUtils {
    private static GameUtils mGameUtils = null;

    private GameUtils() {
    }

    public static GameUtils getInstance(Context context) {
        if (mGameUtils != null) {
            return mGameUtils;
        }
        GameUtils gameUtils = new GameUtils();
        mGameUtils = gameUtils;
        return gameUtils;
    }

    public String gameName() {
        return CommonUtil.getAppName();
    }

    public String getGameBundle() {
        return CommonUtil.getAppPackageName();
    }

    public String getGameVersion() {
        return CommonUtil.getAppVersionName();
    }

    public String getLocalIPAddress() {
        return "";
    }

    public static String intIP2StringIP(int ip) {
        return (ip & 255) + "." + ((ip >> 8) & 255) + "." + ((ip >> 16) & 255) + "." + ((ip >> 24) & 255);
    }

    public String getWifiName() {
        return "";
    }

    public String getGameBuild() {
        return CommonUtil.getAppVersionCode();
    }
}
