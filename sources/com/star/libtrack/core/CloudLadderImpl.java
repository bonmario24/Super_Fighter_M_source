package com.star.libtrack.core;

import android.text.TextUtils;
import com.thinkfly.star.CloudLadderSDK;
import com.thinkfly.star.GlobalParams;
import com.thinkfly.star.builder.EventBuilder;
import com.thinkfly.star.builder.InitBuilder;
import com.thinkfly.star.builder.StartupBuilder;
import java.util.Map;

public class CloudLadderImpl implements ICloudLadder {
    private CloudLadderSDK proxy;

    public void init(boolean isDebug, String who, String host, String salt) {
        InitBuilder initBuilder = new InitBuilder.Configure().setAppv(TrackCore.getInstance().getVersion()).setC(TrackCore.getInstance().getChannelID()).setUid(TrackCore.getInstance().getUid()).build();
        GlobalParams globalParams = new GlobalParams();
        globalParams.put("gameid", TrackCore.getInstance().getGameID());
        this.proxy = new CloudLadderSDK(TrackCore.getInstance().getContext(), who, host, salt, initBuilder, isDebug, globalParams);
    }

    public boolean submitCustomEvent(String action, String uid, Map<String, Object> ext) {
        if (TextUtils.isEmpty(action)) {
            return false;
        }
        this.proxy.reportEvent(TrackCore.getInstance().getContext(), new EventBuilder.Configure().setAction(action).setUid(uid).setExtMap(ext).build(), 0);
        if ("oneclick_registration".equals(action) || "initialize".equals(action) || "sdk_login".equals(action)) {
            this.proxy.refreshReport();
        }
        return true;
    }

    public void submitStartupEvent() {
        this.proxy.reportStartup(TrackCore.getInstance().getContext(), new StartupBuilder.Configure().setUid(TrackCore.getInstance().getUid()).build(), 0);
    }
}
