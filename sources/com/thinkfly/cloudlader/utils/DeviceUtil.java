package com.thinkfly.cloudlader.utils;

import android.content.Context;
import android.os.Build;
import com.doraemon.devices.AdvertisingIdHelper;
import com.doraemon.devices.MsaSdkHelper;
import com.doraemon.p027eg.CommonUtil;
import com.doraemon.p027eg.FilterUtils;
import com.eagle.mixsdk.sdk.did.ThinkFlyUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceUtil {
    private static volatile boolean isFirst = true;

    public static int getWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static JSONObject getDeviceInfo(Context context) {
        JSONObject contextJson = new JSONObject();
        try {
            contextJson.put("device", createDeviceJSON(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contextJson;
    }

    public static JSONObject createDeviceJSON(Context context) throws JSONException {
        JSONObject deviceJson = new JSONObject();
        deviceJson.put("carrier", CommonUtil.getSimOperatorByMnc());
        deviceJson.put("jbk", CommonUtil.getDeviceRootState());
        deviceJson.put("did", ThinkFlyUtils.getDeviceID(context));
        deviceJson.put("tz", CommonUtil.getTimeZone());
        deviceJson.put("lang", CommonUtil.getLanguage());
        deviceJson.put("width", getWidth(context));
        deviceJson.put("height", getHeight(context));
        deviceJson.put("mac", CommonUtil.getMacAddress());
        deviceJson.put("net", CommonUtil.getNetworkType());
        deviceJson.put("model", CommonUtil.getModel());
        deviceJson.put("os", CommonUtil.getOS());
        deviceJson.put("osver", CommonUtil.getOSVersion());
        deviceJson.put("apil", Build.VERSION.SDK_INT);
        deviceJson.put("idfa", getGAID());
        deviceJson.put("idfv", getOAID());
        deviceJson.put("imei", CommonUtil.getIMEI(context));
        deviceJson.put("ip", CommonUtil.getLocalIPAddress());
        deviceJson.put("wifi", CommonUtil.getWifiName());
        deviceJson.put("brand", CommonUtil.getBrand());
        deviceJson.put("factory", CommonUtil.getManufacturer());
        return deviceJson;
    }

    public static String getOAID() {
        return MsaSdkHelper.getInstance().getIdentifier().getOAID();
    }

    public static String getGAID() {
        return FilterUtils.filterString(AdvertisingIdHelper.getInstance().getAdvertisingId());
    }
}
