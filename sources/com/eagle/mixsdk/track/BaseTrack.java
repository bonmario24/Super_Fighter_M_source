package com.eagle.mixsdk.track;

import android.text.TextUtils;
import com.eagle.mixsdk.sdk.EagleSDK;

public class BaseTrack {
    private static final String host = "http://applog.test.dc.hoho666.com/app";

    /* access modifiers changed from: protected */
    public String getHost() {
        String iDotUrl = EagleSDK.getInstance().getIDotUrl();
        return TextUtils.isEmpty(iDotUrl) ? host : iDotUrl;
    }

    /* access modifiers changed from: protected */
    public boolean isDebug() {
        return !TextUtils.isEmpty(EagleSDK.getInstance().getSDKParams().getString("Track_Log"));
    }
}
