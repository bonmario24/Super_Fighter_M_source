package com.eagle.mixsdk.track;

import android.content.Context;
import android.text.TextUtils;
import com.appsflyer.AppsFlyerProperties;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.utils.StoreUtils;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import com.thinkfly.star.CloudLadderSDK;
import com.thinkfly.star.GlobalParams;
import com.thinkfly.star.builder.EventBuilder;
import com.thinkfly.star.builder.InitBuilder;
import com.thinkfly.star.builder.LoginBuilder;
import com.thinkfly.star.builder.LogoutBuilder;
import com.thinkfly.star.builder.RegisterBuilder;
import com.thinkfly.star.builder.StartupBuilder;
import java.util.Date;

public class TrackReport extends BaseTrack {
    private static final String appKey = "eaglesdk";
    private static final String appSec = "450rsjsxa2nvOMxsw";
    private CloudLadderSDK sdk;

    private static class SingletonHolder {
        public static TrackReport instance = new TrackReport();

        private SingletonHolder() {
        }
    }

    private TrackReport() {
    }

    public static TrackReport getInstance() {
        return SingletonHolder.instance;
    }

    public void init(Context context) {
        InitBuilder build = new InitBuilder.Configure().setIsOpenHeartbeat(false).setC(String.valueOf(EagleSDK.getInstance().getCurrChannel())).setAppv("2.8.0").setsc("" + EagleSDK.getInstance().getSubChannel()).setExtc(EagleSDK.getInstance().getExtChannel()).setUid(StoreUtils.getString(context, EagleSDK.USERID_KEY)).build();
        GlobalParams globalParams = new GlobalParams();
        globalParams.put(AppsFlyerProperties.APP_ID, EagleSDK.getInstance().getAppID() + "");
        globalParams.put("chnuid", "");
        this.sdk = new CloudLadderSDK(context, appKey, getHost(), appSec, build, isDebug(), globalParams);
    }

    public void reportActive(String str, String str2, String str3, String str4) {
        reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("activation").put("event", str3).put("guid", str4).put("code", str).put("link", str2).build());
    }

    public void reportEvent(Context context, EventBuilder eventBuilder) {
        if (this.sdk != null) {
            String eagleUid = EagleSDK.getInstance().getEagleUid();
            if (!TextUtils.isEmpty(eagleUid)) {
                eventBuilder.setUid(eagleUid);
            }
            this.sdk.getGlobalParams().put("chnuid", EagleSDK.getInstance().getChannelUid());
            this.sdk.reportEvent(context, eventBuilder, 0);
        }
    }

    public void reportHeartbeatEvent(EventBuilder eventBuilder) {
        if (this.sdk != null && eventBuilder != null) {
            eventBuilder.getExtMap().put("event", 5);
            this.sdk.reportExtendHeartbeat(eventBuilder);
        }
    }

    public void reportLogin(Context context) {
        EagleToken uToken;
        if (this.sdk != null && (uToken = EagleSDK.getInstance().getUToken()) != null) {
            this.sdk.getGlobalParams().put("chnuid", EagleSDK.getInstance().getChannelUid());
            if (uToken.getIsOld() == 0) {
                this.sdk.reportRegister(context, new RegisterBuilder.Configure().setRtime(new Date(uToken.getRegisterTime())).setUid(EagleSDK.getInstance().getEagleUid()).setLway(LoginBuilder.System).build(), 0);
                return;
            }
            this.sdk.reportLogin(context, new LoginBuilder.Configure().setRtime(new Date(uToken.getRegisterTime())).setUid(EagleSDK.getInstance().getEagleUid()).setLway(LoginBuilder.System).build(), 0);
        }
    }

    public void reportLogout(Context context) {
        if (this.sdk != null) {
            LogoutBuilder build = new LogoutBuilder.Configure().build();
            build.setUid(EagleSDK.getInstance().getEagleUid());
            this.sdk.reportLogout(context, build, 0);
            this.sdk.getGlobalParams().put("chnuid", "");
        }
    }

    public void reportStartup(Context context) {
        if (this.sdk != null) {
            this.sdk.reportStartup(context, new StartupBuilder.Configure().build(), 0);
        }
    }

    public void stopHeartbeat() {
        if (this.sdk != null) {
            this.sdk.stopHeartbeat();
        }
    }
}
