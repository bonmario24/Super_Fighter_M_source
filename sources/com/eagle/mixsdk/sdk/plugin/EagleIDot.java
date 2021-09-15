package com.eagle.mixsdk.sdk.plugin;

import android.content.Context;
import com.eagle.mixsdk.sdk.IDotPlugin;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.base.PluginFactory;
import com.eagle.mixsdk.sdk.log.Log;
import com.thinkfly.star.builder.LoginBuilder;
import com.thinkfly.star.builder.LogoutBuilder;
import com.thinkfly.star.builder.RegisterBuilder;
import com.thinkfly.star.builder.StartupBuilder;
import java.util.Map;

public class EagleIDot {
    private static EagleIDot mEagleIDot = null;
    private IDotPlugin mIDotPlugin = null;

    private EagleIDot() {
        init();
    }

    public static EagleIDot getInstance() {
        if (mEagleIDot != null) {
            return mEagleIDot;
        }
        EagleIDot eagleIDot = new EagleIDot();
        mEagleIDot = eagleIDot;
        return eagleIDot;
    }

    public void init() {
        if (this.mIDotPlugin == null) {
            this.mIDotPlugin = (IDotPlugin) PluginFactory.getInstance().initPlugin(10);
        }
    }

    public void reportEvent(Context context, String action, Map<String, Object> extmap) {
        if (this.mIDotPlugin != null) {
            this.mIDotPlugin.reportEvent(context, action, extmap);
        }
    }

    public void reportStartup(Context context, StartupBuilder builder) {
        if (this.mIDotPlugin != null) {
            this.mIDotPlugin.reportStartup(context, builder);
        }
    }

    public void reportLogin(Context context, LoginBuilder builder) {
        if (this.mIDotPlugin != null) {
            this.mIDotPlugin.reportLogin(context, builder);
        }
    }

    public void reportRegister(Context context, RegisterBuilder builder) {
        if (this.mIDotPlugin != null) {
            this.mIDotPlugin.reportRegister(context, builder);
        }
    }

    public void reportLogout(Context context, LogoutBuilder builder) {
        if (this.mIDotPlugin != null) {
            this.mIDotPlugin.reportLogout(context, builder);
        }
    }

    public boolean isSupport(String method) {
        if (isPluginInited()) {
            return true;
        }
        return false;
    }

    private boolean isPluginInited() {
        if (this.mIDotPlugin != null) {
            return true;
        }
        Log.m599e(Constants.TAG, "The idot plugin is not inited or inited failed.");
        return false;
    }
}
