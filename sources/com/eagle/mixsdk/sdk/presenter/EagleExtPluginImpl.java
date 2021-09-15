package com.eagle.mixsdk.sdk.presenter;

import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.IExtPlugin;
import com.xhuyu.component.core.api.HuYuApi;
import java.util.Map;

public class EagleExtPluginImpl implements IExtPlugin {
    public static final String EXT_PLUGIN_FACEBOOK_SHARE = "doFacebookShare";
    public static final String EXT_PLUGIN_OPEN_MARKETING = "doOpenMarketing";

    public void invoke(String method, Map<String, Object> map) {
        char c = 65535;
        switch (method.hashCode()) {
            case -1737511442:
                if (method.equals(EXT_PLUGIN_FACEBOOK_SHARE)) {
                    c = 0;
                    break;
                }
                break;
            case -157658063:
                if (method.equals(EXT_PLUGIN_OPEN_MARKETING)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                HuYuApi.getInstance().doShare(EagleSDK.getInstance().getContext());
                return;
            case 1:
                HuYuApi.getInstance().doMarketingView(EagleSDK.getInstance().getContext());
                return;
            default:
                return;
        }
    }
}
