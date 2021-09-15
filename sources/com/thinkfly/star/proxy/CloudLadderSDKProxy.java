package com.thinkfly.star.proxy;

import android.content.Context;
import com.thinkfly.star.CloudLadderSDK;
import com.thinkfly.star.builder.EventBuilder;
import com.thinkfly.star.builder.InitBuilder;

public class CloudLadderSDKProxy {
    private InitBuilder mInitBuilder = null;
    private CloudLadderSDK sdk;

    public CloudLadderSDKProxy(Context context, String who, String host, String salt, boolean debug, InitBuilder initBuilder) {
        this.mInitBuilder = initBuilder;
        this.sdk = new CloudLadderSDK(context, who, host, salt, initBuilder, debug);
    }

    public void reportEvent(Context context, EventBuilder builder, int priority) {
        if (this.mInitBuilder != null) {
            builder.setC(this.mInitBuilder.getC());
            builder.setSc(this.mInitBuilder.getSc());
            builder.setExtc(this.mInitBuilder.getExtc());
            builder.setAppv(this.mInitBuilder.getAppv());
        }
        this.sdk.reportEvent(context, builder, priority);
    }
}
