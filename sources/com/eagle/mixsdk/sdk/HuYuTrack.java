package com.eagle.mixsdk.sdk;

import android.content.Context;
import android.util.Log;
import com.eagle.mixsdk.sdk.base.Constants;
import com.thinkfly.star.builder.InitBuilder;
import com.thinkfly.star.builder.LoginBuilder;
import com.thinkfly.star.builder.LogoutBuilder;
import com.thinkfly.star.builder.RegisterBuilder;
import com.thinkfly.star.builder.StartupBuilder;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.api.HuYuApi;
import java.util.Map;

public class HuYuTrack implements IDotPlugin {
    public void init(Context context, String s, String s1, String s2, InitBuilder initBuilder, boolean b) {
    }

    public void reportEvent(Context context, String action, Map<String, Object> map) {
        Map<String, Object> eventValue;
        Log.e(Constants.TAG, "reportEvent: action is " + action);
        if (CheckUtils.isNullOrEmpty(action) || !"doTrackEvent".equals(action) || map == null || !map.containsKey("eventName")) {
            return;
        }
        if (!map.containsKey("eventValue") || (eventValue = (Map) map.get("eventValue")) == null) {
            HuYuApi.getInstance().trackEvent(EagleSDK.getInstance().getContext(), (String) map.get("eventName"), (Map<String, Object>) null);
        } else {
            HuYuApi.getInstance().trackEvent(EagleSDK.getInstance().getContext(), (String) map.get("eventName"), eventValue);
        }
    }

    public void reportStartup(Context context, StartupBuilder startupBuilder) {
    }

    public void reportLogin(Context context, LoginBuilder loginBuilder) {
    }

    public void reportRegister(Context context, RegisterBuilder registerBuilder) {
    }

    public void reportLogout(Context context, LogoutBuilder logoutBuilder) {
    }

    public boolean isSupportMethod(String s) {
        return true;
    }
}
