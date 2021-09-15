package com.eagle.mixsdk.sdk.plugin;

import com.eagle.mixsdk.sdk.IServicePlugin;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.base.PluginFactory;
import com.eagle.mixsdk.sdk.log.Log;

public class EagleServicePlugin {
    private static EagleServicePlugin instance;
    private IServicePlugin servicePlugin = ((IServicePlugin) PluginFactory.getInstance().initPlugin(9));

    private EagleServicePlugin() {
    }

    public static EagleServicePlugin getInstance() {
        if (instance == null) {
            instance = new EagleServicePlugin();
        }
        return instance;
    }

    public void openCustomerService(String appId) {
        if (!isPluginInited()) {
            Log.m598d(Constants.TAG, "servicePlugin 不能为空");
        } else {
            this.servicePlugin.openCustomerService(appId);
        }
    }

    public boolean isSupport(String method) {
        if (isPluginInited()) {
            return true;
        }
        return false;
    }

    private boolean isPluginInited() {
        if (this.servicePlugin != null) {
            return true;
        }
        Log.m599e(Constants.TAG, "The service plugin is not inited or inited failed.");
        return false;
    }
}
