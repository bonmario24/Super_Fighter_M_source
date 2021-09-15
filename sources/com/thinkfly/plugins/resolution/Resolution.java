package com.thinkfly.plugins.resolution;

import android.text.TextUtils;
import com.doraemon.p027eg.CommonUtil;
import com.eagle.mixsdk.sdk.did.ThinkFlyUtils;
import com.thinkfly.cloudlader.utils.DeviceUtil;
import com.thinkfly.plugins.coludladder.config.Config;
import com.thinkfly.plugins.coludladder.log.Log;
import com.thinkfly.plugins.coludladder.utils.FixTimeUtils;
import com.thinkfly.star.CloudLadderApplication;
import org.json.JSONException;
import org.json.JSONObject;

public class Resolution {
    @Deprecated
    public static JSONObject resolution(JSONObject jsonObject, String timestamp, String intervalTime) throws NumberFormatException, JSONException {
        return resolution(jsonObject, timestamp);
    }

    public static void fixTimeStamp(JSONObject jsonObject, String timestamp) {
        if (jsonObject != null) {
            String intervalTime = FixTimeUtils.getInstance().getIntervalTime();
            Log.m662d(Log.TAG, "RepeatThread intervalTime : " + intervalTime);
            if (FixTimeUtils.getInstance().isNeedFixTime()) {
                try {
                    if (jsonObject.has("when")) {
                        jsonObject.put("when", System.currentTimeMillis() + Long.parseLong(intervalTime));
                    }
                    Log.m662d(Log.TAG, "resolution timestamp : " + timestamp);
                    if (Config.UNKNOWN.equals(timestamp)) {
                        Log.m662d(Log.TAG, "start fix times ");
                        if (jsonObject.has("context")) {
                            JSONObject contextJson = jsonObject.optJSONObject("context");
                            if (contextJson.has("data")) {
                                JSONObject dataJson = contextJson.optJSONObject("data");
                                if (dataJson.has("time")) {
                                    Log.m662d(Log.TAG, "start fix data/time ");
                                    dataJson.put("time", dataJson.optLong("time") + Long.parseLong(intervalTime));
                                }
                                if ("login".equals(jsonObject.optString("what"))) {
                                    long rtime = dataJson.optLong("rtime");
                                    if (rtime != 0) {
                                        Log.m662d(Log.TAG, "start fix data/rtime ");
                                        dataJson.put("rtime", Long.parseLong(intervalTime) + rtime);
                                    }
                                }
                            }
                        } else if (jsonObject.has("data")) {
                            JSONObject dataJson2 = jsonObject.optJSONObject("data");
                            if (dataJson2.has("timestamp")) {
                                dataJson2.put("timestamp", "" + (Long.parseLong(dataJson2.optString("timestamp")) + Long.parseLong(intervalTime)));
                            }
                        }
                    } else {
                        return;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            Log.m662d(Log.TAG, "fixSendData : " + jsonObject);
        }
    }

    public static JSONObject resolution(JSONObject jsonObject, String timestamp) {
        fixDeviceData(jsonObject);
        fixTimeStamp(jsonObject, timestamp);
        return jsonObject;
    }

    public static void fixDeviceData(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                if (jsonObject.has("context")) {
                    JSONObject contextJson = jsonObject.optJSONObject("context");
                    if (contextJson.has("data")) {
                        JSONObject deviceJson = contextJson.optJSONObject("device");
                        checkAndFixValue(deviceJson, "idfa", DeviceUtil.getGAID());
                        checkAndFixValue(deviceJson, "idfv", DeviceUtil.getOAID());
                        checkAndFixValue(deviceJson, "imei", CommonUtil.getIMEI(CloudLadderApplication.getInstance().getApplication()));
                        String did = deviceJson.optString("did", "");
                        if (TextUtils.isEmpty(did) || "00000000000000000000000000000000".equals(did)) {
                            String deviceID = ThinkFlyUtils.getDeviceID(CloudLadderApplication.getInstance().getApplication());
                            Log.m662d(Log.TAG, "fix did " + deviceID);
                            deviceJson.put("did", deviceID);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkAndFixValue(JSONObject jsonObject, String key, String compareValue) throws JSONException {
        if (jsonObject != null && !TextUtils.isEmpty(compareValue) && TextUtils.isEmpty(jsonObject.optString(key))) {
            Log.m662d(Log.TAG, "fix " + key + ": " + compareValue);
            jsonObject.put(key, compareValue);
        }
    }
}
