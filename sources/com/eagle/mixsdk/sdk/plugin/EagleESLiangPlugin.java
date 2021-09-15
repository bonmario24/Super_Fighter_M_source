package com.eagle.mixsdk.sdk.plugin;

import com.eagle.mixsdk.sdk.IESLiangPlugin;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.base.PluginFactory;
import com.eagle.mixsdk.sdk.log.Log;

public class EagleESLiangPlugin {
    private static EagleESLiangPlugin mMouseESLiangPlugin = null;
    private IESLiangPlugin mESLiangPlugin = null;

    private EagleESLiangPlugin() {
        if (!PluginFactory.getInstance().getPlugin(11).isSupportMethod("")) {
            Log.m602w(Constants.TAG, "eslp is not inited ");
        }
    }

    public static EagleESLiangPlugin getInstance() {
        if (mMouseESLiangPlugin != null) {
            return mMouseESLiangPlugin;
        }
        EagleESLiangPlugin eagleESLiangPlugin = new EagleESLiangPlugin();
        mMouseESLiangPlugin = eagleESLiangPlugin;
        return eagleESLiangPlugin;
    }

    public void init() {
    }
}
