package com.eagle.mixsdk.sdk.plugin;

import android.util.Log;
import com.eagle.mixsdk.sdk.IPush;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.base.PluginFactory;

public class EaglePush {
    private static EaglePush instance;
    private IPush pushPlugin;

    private EaglePush() {
    }

    public void init() {
        this.pushPlugin = (IPush) PluginFactory.getInstance().initPlugin(3);
    }

    public static EaglePush getInstance() {
        if (instance == null) {
            instance = new EaglePush();
        }
        return instance;
    }

    public boolean isSupport(String method) {
        if (isPluginInited()) {
            return this.pushPlugin.isSupportMethod(method);
        }
        return false;
    }

    public void scheduleNotification(String msgs) {
        if (isPluginInited()) {
            this.pushPlugin.scheduleNotification(msgs);
        }
    }

    public void startPush() {
        if (isPluginInited()) {
            this.pushPlugin.startPush();
        }
    }

    public void stopPush() {
        if (isPluginInited()) {
            this.pushPlugin.stopPush();
        }
    }

    public void addTags(String... tags) {
        if (isPluginInited()) {
            this.pushPlugin.addTags(tags);
        }
    }

    public void removeTags(String... tags) {
        if (isPluginInited()) {
            this.pushPlugin.removeTags(tags);
        }
    }

    public void addAlias(String alias) {
        if (isPluginInited()) {
            this.pushPlugin.addAlias(alias);
        }
    }

    public void removeAlias(String alias) {
        if (isPluginInited()) {
            this.pushPlugin.removeAlias(alias);
        }
    }

    private boolean isPluginInited() {
        if (this.pushPlugin != null) {
            return true;
        }
        Log.e(Constants.TAG, "The push plugin is not inited or inited failed.");
        return false;
    }
}
