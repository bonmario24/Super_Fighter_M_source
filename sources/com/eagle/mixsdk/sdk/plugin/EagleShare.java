package com.eagle.mixsdk.sdk.plugin;

import android.util.Log;
import android.widget.Toast;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.IShare;
import com.eagle.mixsdk.sdk.ShareParams;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.base.PluginFactory;
import com.eagle.mixsdk.sdk.impl.listeners.ISDKShareListener;

public class EagleShare {
    private static EagleShare instance;
    private IShare sharePlugin = ((IShare) PluginFactory.getInstance().initPlugin(4));

    private EagleShare() {
    }

    public static EagleShare getInstance() {
        if (instance == null) {
            instance = new EagleShare();
        }
        return instance;
    }

    public boolean isSupport(String method) {
        if (isPluginInited()) {
            return this.sharePlugin.isSupportMethod(method);
        }
        return false;
    }

    public void share(ShareParams params, ISDKShareListener listener) {
        if (isPluginInited()) {
            this.sharePlugin.share(params, listener);
        } else {
            Toast.makeText(EagleSDK.getInstance().getContext(), "调用[分享接口]接口成功，这个默认的实现，还需要经过打包工具来打出最终的渠道包", 1).show();
        }
    }

    private boolean isPluginInited() {
        if (this.sharePlugin != null) {
            return true;
        }
        Log.e(Constants.TAG, "The share plugin is not inited or inited failed.");
        return false;
    }
}
