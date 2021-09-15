package com.eagle.mixsdk.sdk.utils;

import com.doraemon.p027eg.ReflectResource;
import com.eagle.mixsdk.sdk.EagleSDK;

public class ResPluginUtil {
    public static String getStringByName(String resName) {
        try {
            return ReflectResource.getInstance(EagleSDK.getInstance().getApplication()).getString(resName);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
