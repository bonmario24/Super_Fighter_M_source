package com.eagle.mixsdk.track;

import android.content.Context;
import android.content.res.Configuration;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.IApplicationListener;
import com.thinkfly.star.CloudLadderProxyApplication;

public class TrackProxyApplicationListener implements IApplicationListener {
    public void onProxyAttachBaseContext(Context context) {
    }

    public void onProxyConfigurationChanged(Configuration configuration) {
    }

    public void onProxyCreate() {
        CloudLadderProxyApplication.init(EagleSDK.getInstance().getApplication());
        TrackReport.getInstance().init(EagleSDK.getInstance().getApplication());
        TrackReport.getInstance().reportStartup(EagleSDK.getInstance().getApplication());
        EagleTrackManager.getInstance().addTracks(TrackEvent.getInstance());
    }

    public void onProxyTerminate() {
    }
}
