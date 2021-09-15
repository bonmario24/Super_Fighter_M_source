package com.eagle.mixsdk.sdk.plugin;

import android.util.Log;
import com.eagle.mixsdk.sdk.IDownload;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.base.PluginFactory;

public class EagleDownload {
    private static EagleDownload instance;
    private IDownload downloadPlugin;

    private EagleDownload() {
    }

    public static EagleDownload getInstance() {
        if (instance == null) {
            instance = new EagleDownload();
        }
        return instance;
    }

    public void init() {
        this.downloadPlugin = (IDownload) PluginFactory.getInstance().initPlugin(6);
    }

    public boolean isSupport(String method) {
        if (isPluginInited()) {
            return this.downloadPlugin.isSupportMethod(method);
        }
        return false;
    }

    public void download(String url, boolean showConfirm, boolean force) {
        if (isPluginInited()) {
            this.downloadPlugin.download(url, showConfirm, force);
        }
    }

    private boolean isPluginInited() {
        if (this.downloadPlugin != null) {
            return true;
        }
        Log.e(Constants.TAG, "The download plugin is not inited or inited failed.");
        return false;
    }
}
