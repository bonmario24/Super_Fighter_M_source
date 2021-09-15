package com.eagle.mixsdk.sdk.utils;

import com.doraemon.p027eg.CheckUtils;
import com.doraemon.util.language.LanConfig;

public class OverseasUtil {
    public static boolean isOverseasChannel() {
        String eagleIsOverseas = LanConfig.getInstance().getLanConfig("Eagle_IsOverseas");
        return !CheckUtils.isNullOrEmpty(eagleIsOverseas) && eagleIsOverseas.equalsIgnoreCase("true");
    }
}
