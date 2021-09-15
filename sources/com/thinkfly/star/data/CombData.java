package com.thinkfly.star.data;

import android.content.Context;
import com.appsflyer.share.Constants;
import com.thinkfly.plugins.coludladder.config.Version;
import com.thinkfly.plugins.coludladder.utils.FixTimeUtils;
import com.thinkfly.plugins.coludladder.utils.device.GameUtils;
import com.thinkfly.star.CloudLadderSDK;
import com.thinkfly.star.builder.Builder;
import com.thinkfly.star.builder.EventBuilder;
import com.thinkfly.star.builder.LoginBuilder;
import com.thinkfly.star.builder.LogoutBuilder;
import com.thinkfly.star.builder.RegisterBuilder;
import com.thinkfly.star.builder.StartupBuilder;
import com.xingfei.doraemon.deviceinfo.DeviceUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class CombData {
    private CloudLadderSDK sdk;

    public CombData(CloudLadderSDK cloudLadderSDK) {
        this.sdk = cloudLadderSDK;
    }

    private JSONObject createDataJSON(Context context, Builder builder, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        jSONObject.put("time", FixTimeUtils.getInstance().fixTimeStamp(System.currentTimeMillis()));
        if (this.sdk.getInitBuilder() != null) {
            jSONObject.put("fc", this.sdk.getInitBuilder().getC());
            jSONObject.put(Constants.URL_CAMPAIGN, this.sdk.getInitBuilder().getC());
            jSONObject.put("sc", this.sdk.getInitBuilder().getSC());
            jSONObject.put("extc", this.sdk.getInitBuilder().getExtc());
            jSONObject.put("appv", this.sdk.getInitBuilder().getAppv());
        }
        jSONObject.put("uid", builder.getUid());
        jSONObject.put("sdkv", Version.VERSION_NAME);
        jSONObject.put("pkg", GameUtils.getInstance(context).getGameBundle());
        return jSONObject;
    }

    private JSONObject createDeviceJSON(Context context) throws JSONException {
        return DeviceUtil.createDeviceJSON(context);
    }

    private JSONObject createSendJSON(Context context, String str, Builder builder) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("when", System.currentTimeMillis());
        jSONObject.put("who", this.sdk.getAppKey());
        jSONObject.put("platform", "app");
        jSONObject.put("what", str);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("device", createDeviceJSON(context));
        JSONObject jSONObject3 = new JSONObject();
        builder.makeData(jSONObject3, this.sdk.getGlobalParams());
        jSONObject2.put("data", createDataJSON(context, builder, jSONObject3));
        jSONObject.put("context", jSONObject2);
        return jSONObject;
    }

    public JSONObject createEventJson(Context context, EventBuilder eventBuilder) throws JSONException {
        return createSendJSON(context, "event", eventBuilder);
    }

    public JSONObject createHeartbeatJson(Context context, Builder builder) throws JSONException {
        return createSendJSON(context, "heartbeat", builder);
    }

    public JSONObject createLoginJson(Context context, LoginBuilder loginBuilder) throws JSONException {
        return createSendJSON(context, "login", loginBuilder);
    }

    public JSONObject createLogoutJson(Context context, LogoutBuilder logoutBuilder) throws JSONException {
        return createSendJSON(context, "logout", logoutBuilder);
    }

    public JSONObject createRegisterJson(Context context, RegisterBuilder registerBuilder) throws JSONException {
        return createSendJSON(context, "login", registerBuilder);
    }

    public JSONObject createStartupJson(Context context, StartupBuilder startupBuilder) throws JSONException {
        return createSendJSON(context, "startup", startupBuilder);
    }
}
