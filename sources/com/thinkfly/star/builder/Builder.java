package com.thinkfly.star.builder;

import com.doraemon.p027eg.CheckUtils;
import com.thinkfly.plugins.coludladder.log.Log;
import com.thinkfly.star.GlobalParams;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Builder {
    protected String appv = "";

    /* renamed from: c */
    protected String f875c = "";
    protected String extc = "";

    /* renamed from: sc */
    protected String f876sc = "";
    protected String sdkv = "";
    protected String uid = "";

    public interface DictFunction {
        void appendIntegerToMap(String str, int i);

        void appendLongToMap(String str, long j);

        void appendStringToMap(String str, String str2);
    }

    public String getC() {
        return this.f875c;
    }

    public void setC(String c) {
        this.f875c = c;
    }

    public String getSc() {
        return this.f876sc;
    }

    public void setSc(String sc) {
        this.f876sc = sc;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid2) {
        this.uid = uid2;
    }

    public void setSdkv(String sdkv2) {
        this.sdkv = sdkv2;
    }

    public String getSdkv() {
        return this.sdkv;
    }

    public String getAppv() {
        return this.appv;
    }

    public void setAppv(String appv2) {
        this.appv = appv2;
    }

    public String getExtc() {
        return this.extc;
    }

    public void setExtc(String extc2) {
        this.extc = extc2;
    }

    public JSONObject makeData(JSONObject jsonObject, GlobalParams globalParams) throws JSONException {
        if (jsonObject == null) {
            Log.m665w(Log.TAG, "makeData-- JSONObject is null");
            return null;
        } else if (globalParams == null || globalParams.isEmpty()) {
            Log.m665w(Log.TAG, "makeData-- GlobalParams is Empty");
            return jsonObject;
        } else {
            for (String key : globalParams.keySet()) {
                if (!CheckUtils.isNumeric(key)) {
                    jsonObject.put(key, globalParams.get(key));
                }
            }
            return jsonObject;
        }
    }
}
