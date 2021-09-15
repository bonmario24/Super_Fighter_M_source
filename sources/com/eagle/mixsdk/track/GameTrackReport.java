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

public class GameTrackReport extends BaseReport {
    private static GameTrackReport instance;
    private CloudLadderSDK sdk;

    private GameTrackReport() {
    }

    public static GameTrackReport get(Context context) {
        if (instance == null) {
            instance = new GameTrackReport();
            instance.init(context);
        }
        return instance;
    }

    private void init(Context context) {
        InitBuilder initBuilder = new InitBuilder.Configure().setAppv(CommonUtil.getAppVersionName()).setC("" + EagleSDK.getInstance().getCurrChannel()).setsc("" + EagleSDK.getInstance().getSubChannel()).setExtc(EagleSDK.getInstance().getExtChannel()).build();
        String ladder_appKey = EagleSDK.getInstance().getSDKParams().getString("CloudLadder_AppKey");
        String ladder_appSec = EagleSDK.getInstance().getSDKParams().getString("CloudLadder_AppSec");
        if (TextUtils.isEmpty(ladder_appKey) || TextUtils.isEmpty(ladder_appSec)) {
            Log.m599e(Constants.TAG, "GameTrack params isEmpty " + ladder_appKey + "--" + ladder_appSec);
            return;
        }
        try {
            this.sdk = new CloudLadderSDK(context, ladder_appKey, getHost(), ladder_appSec, initBuilder, isDebug());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reportEvent(Context context, String action, Map<String, Object> map) {
        if (this.sdk == null) {
            Log.m599e(Constants.TAG, "GameTrack init fail!!!!");
        } else if (TextUtils.isEmpty(action)) {
            Log.m599e(Constants.TAG, "action isEmpty " + action);
        } else {
            EventBuilder builder = new EventBuilder.Configure().setAction(action).setExtMap(map).put("chnuid", EagleSDK.getInstance().getEagleUid()).put(AppsFlyerProperties.APP_ID, "" + EagleSDK.getInstance().getAppID()).build();
            Object uid = map.get("uid");
            if (uid != null && (uid instanceof String)) {
                builder.setUid((String) uid);
            }
            this.sdk.reportEvent(context, builder, 0);
            if (action.equals("checkpoint")) {
                this.sdk.refreshReport();
            }
        }
    }
}
