package com.eagle.mixsdk.sdk.plugin;

import com.eagle.mixsdk.sdk.DMPPlugin;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.base.PluginFactory;
import com.eagle.mixsdk.sdk.log.Log;
import org.json.JSONObject;

public class EagleDMP {
    private static EagleDMP mEagleDMP = null;
    private DMPPlugin mDMPPlugin = null;

    private EagleDMP() {
    }

    public static EagleDMP getInstance() {
        if (mEagleDMP != null) {
            return mEagleDMP;
        }
        EagleDMP eagleDMP = new EagleDMP();
        mEagleDMP = eagleDMP;
        return eagleDMP;
    }

    public void init() {
        this.mDMPPlugin = (DMPPlugin) PluginFactory.getInstance().initPlugin(12);
    }

    public void logActionParams(String actionType, JSONObject actionParam) {
        if (isPluginInited()) {
            this.mDMPPlugin.logActionParams(actionType, actionParam);
        }
    }

    public void logActionRegister() {
        if (isPluginInited()) {
            this.mDMPPlugin.logActionRegister();
        }
    }

    public void logActionOrder() {
        if (isPluginInited()) {
            this.mDMPPlugin.logActionOrder();
        }
    }

    public void logActionPurchase(JSONObject actionParam) {
        if (isPluginInited()) {
            this.mDMPPlugin.logActionPurchase(actionParam);
        }
    }

    public void logActionNoParams(String actionType) {
        if (isPluginInited()) {
            this.mDMPPlugin.logActionNoParams(actionType);
        }
    }

    public boolean isPluginInited() {
        if (this.mDMPPlugin != null) {
            return true;
        }
        Log.m599e(Constants.TAG, "The dmp plugin is not inited or inited failed.");
        return false;
    }
}
