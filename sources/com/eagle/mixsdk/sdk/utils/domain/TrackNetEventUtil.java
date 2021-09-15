package com.eagle.mixsdk.sdk.utils.domain;

import com.appsflyer.AppsFlyerProperties;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.utils.StoreUtils;
import com.eagle.mixsdk.track.EagleTrackReport;
import com.thinkfly.plugins.coludladder.CloudLadder;
import com.thinkfly.star.builder.EventBuilder;

public class TrackNetEventUtil {
    public static void reportEvent(String[] eagleArgs, String[] checkArgs) {
        EventBuilder.Configure configure = new EventBuilder.Configure();
        configure.setAction("netcheck");
        configure.putNumber("time", CloudLadder.fixTimeStamp(System.currentTimeMillis()));
        configure.putString("fc", String.valueOf(EagleSDK.getInstance().getCurrChannel()));
        configure.setUid(StoreUtils.getString(EagleSDK.USERID_KEY));
        configure.putString(AppsFlyerProperties.APP_ID, EagleSDK.getInstance().getAppID() + "");
        configure.putString("chnuid", EagleSDK.getInstance().getUToken() == null ? "" : EagleSDK.getInstance().getUToken().getSdkUserID());
        configure.putString("eagle_host", eagleArgs[0]);
        configure.putString("eagle_result", eagleArgs[2]);
        configure.putString("check_host", checkArgs[0]);
        configure.putString("check_result", checkArgs[2]);
        configure.putNumber("eagle_rt", Long.parseLong(eagleArgs[1]));
        configure.putNumber("check_rt", Long.parseLong(checkArgs[1]));
        EagleTrackReport.getInstance().reportEvent(EagleSDK.getInstance().getContext(), configure.build());
    }
}
