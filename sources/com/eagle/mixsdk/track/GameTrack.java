package com.eagle.mixsdk.track;

import android.content.Context;
import android.text.TextUtils;
import com.appsflyer.AppsFlyerProperties;
import com.doraemon.p027eg.CommonUtil;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.log.Log;
import com.thinkfly.star.CloudLadderSDK;
import com.thinkfly.star.builder.EventBuilder;
import com.thinkfly.star.builder.InitBuilder;
import java.util.Map;

public class GameTrack extends BaseTrack {
    private static GameTrack instance;
    private CloudLadderSDK sdk;

    private GameTrack() {
    }

    public static GameTrack get(Context context) {
        if (instance == null) {
            instance = new GameTrack();
            instance.init(context);
        }
        return instance;
    }

    private void init(Context context) {
        InitBuilder build = new InitBuilder.Configure().setAppv(CommonUtil.getAppVersionName()).setC("" + EagleSDK.getInstance().getCurrChannel()).setsc("" + EagleSDK.getInstance().getSubChannel()).setExtc(EagleSDK.getInstance().getExtChannel()).build();
        String string = EagleSDK.getInstance().getSDKParams().getString("CloudLadder_AppKey");
        String string2 = EagleSDK.getInstance().getSDKParams().getString("CloudLadder_AppSec");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            Log.m599e(Constants.TAG, "GameTrack params isEmpty " + string + "--" + string2);
            return;
        }
        try {
            this.sdk = new CloudLadderSDK(context, string, getHost(), string2, build, isDebug());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reportEvent(Context context, String str, Map<String, Object> map) {
        if (this.sdk == null) {
            Log.m599e(Constants.TAG, "GameTrack init fail!!!!");
        } else if (TextUtils.isEmpty(str)) {
            Log.m599e(Constants.TAG, "action isEmpty " + str);
        } else {
            EventBuilder build = new EventBuilder.Configure().setAction(str).setExtMap(map).put("chnuid", EagleSDK.getInstance().getEagleUid()).put(AppsFlyerProperties.APP_ID, "" + EagleSDK.getInstance().getAppID()).build();
            Object obj = map.get("uid");
            if (obj != null && (obj instanceof String)) {
                build.setUid((String) obj);
            }
            this.sdk.reportEvent(context, build, 0);
            if (str.equals("checkpoint")) {
                this.sdk.refreshReport();
            }
        }
    }
}
