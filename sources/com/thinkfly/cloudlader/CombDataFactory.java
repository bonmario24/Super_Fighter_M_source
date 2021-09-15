package com.thinkfly.cloudlader;

import android.content.Context;
import com.appsflyer.share.Constants;
import com.thinkfly.cloudlader.utils.DeviceUtil;
import com.thinkfly.plugins.coludladder.config.Version;
import com.thinkfly.plugins.coludladder.utils.FixTimeUtils;
import com.thinkfly.plugins.coludladder.utils.device.GameUtils;
import com.thinkfly.star.GlobalParams;
import com.thinkfly.star.builder.Builder;
import com.thinkfly.star.builder.EventBuilder;
import com.thinkfly.star.builder.LoginBuilder;
import com.thinkfly.star.builder.LogoutBuilder;
import com.thinkfly.star.builder.RegisterBuilder;
import com.thinkfly.star.builder.StartupBuilder;
import org.json.JSONException;
import org.json.JSONObject;

public class CombDataFactory {
    private String appKey;
    private String appv;

    /* renamed from: c */
    private String f869c;
    private String extc;

    /* renamed from: fc */
    private String f870fc;
    private GlobalParams globalParams;

    /* renamed from: sc */
    private String f871sc;

    private CombDataFactory(Configure configure) {
        this.appKey = configure.appKey;
        this.appv = configure.appv;
        this.f869c = configure.f872c;
        this.f870fc = configure.f873fc;
        this.f871sc = configure.f874sc;
        this.extc = configure.extc;
        this.globalParams = configure.globalParams;
    }

    private JSONObject createDeviceJSON(Context context) throws JSONException {
        return DeviceUtil.createDeviceJSON(context);
    }

    private JSONObject createDataJSON(Context context, Builder builder) throws JSONException {
        JSONObject dataJson = new JSONObject();
        builder.makeData(dataJson, this.globalParams);
        dataJson.put("time", FixTimeUtils.getInstance().fixTimeStamp(System.currentTimeMillis()));
        dataJson.put(Constants.URL_CAMPAIGN, this.f869c);
        dataJson.put("fc", this.f869c);
        dataJson.put("sc", this.f871sc);
        dataJson.put("extc", this.extc);
        dataJson.put("appv", this.appv);
        dataJson.put("uid", builder.getUid());
        dataJson.put("sdkv", Version.VERSION_NAME);
        dataJson.put("pkg", GameUtils.getInstance(context).getGameBundle());
        return dataJson;
    }

    private JSONObject createSendJSON(Context context, String what, Builder builder) throws JSONException {
        JSONObject sendJson = new JSONObject();
        sendJson.put("when", FixTimeUtils.getInstance().getCurrentTimeMillisWithFix());
        sendJson.put("who", this.appKey);
        sendJson.put("platform", "app");
        sendJson.put("what", what);
        JSONObject contextJson = new JSONObject();
        contextJson.put("device", createDeviceJSON(context));
        contextJson.put("data", createDataJSON(context, builder));
        sendJson.put("context", contextJson);
        return sendJson;
    }

    public JSONObject createStartupJson(Context context, StartupBuilder builder) throws JSONException {
        return createSendJSON(context, "startup", builder);
    }

    public JSONObject createEventJson(Context context, EventBuilder builder) throws JSONException {
        return createSendJSON(context, "event", builder);
    }

    public JSONObject createLoginJson(Context context, LoginBuilder builder) throws JSONException {
        return createSendJSON(context, "login", builder);
    }

    public JSONObject createRegisterJson(Context context, RegisterBuilder builder) throws JSONException {
        return createSendJSON(context, "login", builder);
    }

    public JSONObject createLogoutJson(Context context, LogoutBuilder builder) throws JSONException {
        return createSendJSON(context, "logout", builder);
    }

    public JSONObject createHeartbeatJson(Context context, Builder builder) throws JSONException {
        return createSendJSON(context, "heartbeat", builder);
    }

    public void setGlobalParams(GlobalParams globalParams2) {
        this.globalParams = globalParams2;
    }

    public GlobalParams getGlobalParams() {
        if (this.globalParams == null) {
            this.globalParams = new GlobalParams();
        }
        return this.globalParams;
    }

    public static class Configure {
        /* access modifiers changed from: private */
        public String appKey = "";
        /* access modifiers changed from: private */
        public String appv = "";
        /* access modifiers changed from: private */

        /* renamed from: c */
        public String f872c;
        /* access modifiers changed from: private */
        public String extc;
        /* access modifiers changed from: private */

        /* renamed from: fc */
        public String f873fc;
        /* access modifiers changed from: private */
        public GlobalParams globalParams;
        /* access modifiers changed from: private */

        /* renamed from: sc */
        public String f874sc;

        public Configure setAppKey(String appKey2) {
            this.appKey = appKey2;
            return this;
        }

        public Configure setAppv(String appv2) {
            this.appv = appv2;
            return this;
        }

        public Configure setC(String c) {
            this.f872c = c;
            return this;
        }

        public Configure setFc(String fc) {
            this.f873fc = fc;
            return this;
        }

        public Configure setSc(String sc) {
            this.f874sc = sc;
            return this;
        }

        public Configure setExtc(String extc2) {
            this.extc = extc2;
            return this;
        }

        public Configure setGlobalParams(GlobalParams globalParams2) {
            this.globalParams = globalParams2;
            return this;
        }

        public CombDataFactory config() {
            return new CombDataFactory(this);
        }
    }
}
