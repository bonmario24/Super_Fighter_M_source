package com.eagle.mixsdk.track;

import android.content.Context;
import android.text.TextUtils;
import com.appsflyer.AppsFlyerProperties;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.did.ThinkFlyUtils;
import com.eagle.mixsdk.sdk.utils.StoreUtils;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import com.thinkfly.star.CloudLadderProxyApplication;
import com.thinkfly.star.CloudLadderSDK;
import com.thinkfly.star.GlobalParams;
import com.thinkfly.star.builder.EventBuilder;
import com.thinkfly.star.builder.InitBuilder;
import com.thinkfly.star.builder.LoginBuilder;
import com.thinkfly.star.builder.LogoutBuilder;
import com.thinkfly.star.builder.RegisterBuilder;
import com.thinkfly.star.builder.StartupBuilder;
import java.util.Date;
import java.util.HashMap;

public class EagleTrackReport extends BaseReport {
    private static final String appKey = "eaglesdk";
    private static final String appSec = "450rsjsxa2nvOMxsw";
    private CloudLadderSDK sdk;

    private static class SingletonHolder {
        public static EagleTrackReport instance = new EagleTrackReport();

        private SingletonHolder() {
        }
    }

    private EagleTrackReport() {
    }

    public static EagleTrackReport getInstance() {
        return SingletonHolder.instance;
    }

    public void init(Context context) {
        CloudLadderProxyApplication.init(EagleSDK.getInstance().getApplication());
        initCloudLadderSDK(context);
        reportStartup(context);
    }

    private void initCloudLadderSDK(Context context) {
        InitBuilder builder = new InitBuilder.Configure().setIsOpenHeartbeat(false).setC(String.valueOf(EagleSDK.getInstance().getCurrChannel())).setAppv(Constants.VERSIONNAME).setsc("" + EagleSDK.getInstance().getSubChannel()).setExtc(EagleSDK.getInstance().getExtChannel()).setUid(StoreUtils.getString(EagleSDK.USERID_KEY)).build();
        GlobalParams globalParams = new GlobalParams();
        globalParams.put(AppsFlyerProperties.APP_ID, EagleSDK.getInstance().getAppID() + "");
        globalParams.put("chnuid", "");
        this.sdk = new CloudLadderSDK(context, appKey, getHost(), appSec, builder, isDebug(), globalParams);
    }

    public void reportStartup(Context context) {
        if (this.sdk != null) {
            this.sdk.reportStartup(context, new StartupBuilder.Configure().build(), 0);
        }
    }

    public void reportHeartbeatEvent(EventBuilder builder) {
        if (this.sdk != null && builder != null) {
            builder.getExtMap().put("event", 5);
            this.sdk.reportExtendHeartbeat(builder);
        }
    }

    public void stopHeartbeat() {
        if (this.sdk != null) {
            this.sdk.stopHeartbeat();
        }
    }

    public void reportEvent(Context context, EventBuilder build) {
        if (this.sdk != null) {
            String eagleUid = EagleSDK.getInstance().getEagleUid();
            if (!TextUtils.isEmpty(eagleUid)) {
                build.setUid(eagleUid);
            }
            this.sdk.getGlobalParams().put("chnuid", EagleSDK.getInstance().getChannelUid());
            this.sdk.reportEvent(context, build, 0);
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

    public void reportActive(String code, String link, String event, String guid) {
        reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("activation").put("event", event).put("guid", guid).put("code", code).put("link", link).build());
    }

    public void reportEmulatorEvent(HashMap<String, Object> map) {
        reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setExtMap(map).setAction("simulator").setUid(EagleSDK.getInstance().getEagleUid()).putString("chnuid", "").build());
    }

    public void reportAppsInfo(String pkgs, String names) {
        if (!TextUtils.isEmpty(pkgs) && !TextUtils.isEmpty(names)) {
            reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("applist").putString("package", pkgs).putString("appname", names).build());
        }
    }

    public void reportDidEvent() {
        reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("apk_did").put("didv3", "").put("didv3_from", "").put("didv4", ThinkFlyUtils.getDeviceID(EagleSDK.getInstance().getApplication())).put("didv4_from", ThinkFlyUtils.getDIDFeature()).build());
    }

    public void refresh() {
        if (this.sdk != null) {
            this.sdk.refreshReport();
        }
    }
}
