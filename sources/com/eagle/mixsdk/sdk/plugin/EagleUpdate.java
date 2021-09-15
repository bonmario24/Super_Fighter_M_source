package com.eagle.mixsdk.sdk.plugin;

import android.app.Activity;
import com.eagle.mixsdk.sdk.IUpdatePlugin;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.base.PluginFactory;
import com.eagle.mixsdk.sdk.impl.listeners.IEagleUpdateListener;
import com.eagle.mixsdk.sdk.log.Log;

public class EagleUpdate {
    private static EagleUpdate mEagleUpdate = null;
    private IUpdatePlugin mIUpdatePlugin = null;

    private EagleUpdate() {
    }

    public static EagleUpdate getInstance() {
        if (mEagleUpdate == null) {
            mEagleUpdate = new EagleUpdate();
        }
        return mEagleUpdate;
    }

    public void init(Activity context) {
        this.mIUpdatePlugin = (IUpdatePlugin) PluginFactory.getInstance().initPlugin(13);
        if (isPluginInited()) {
            this.mIUpdatePlugin.init(context);
        }
    }

    public void isNeedToUpdate(Activity context, IEagleUpdateListener eagleUpdateListener) {
        if (isPluginInited()) {
            this.mIUpdatePlugin.isNeedToUpdate(context, eagleUpdateListener);
        } else if (eagleUpdateListener != null) {
            eagleUpdateListener.onNotToUpdate();
        }
    }

    private boolean isPluginInited() {
        if (this.mIUpdatePlugin != null) {
            return true;
        }
        Log.m599e(Constants.TAG, "The update plugin is not inited or inited failed.");
        return false;
    }
}
