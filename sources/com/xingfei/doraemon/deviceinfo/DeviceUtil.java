package com.xingfei.doraemon.deviceinfo;

import android.content.Context;
import android.os.Build;
import com.doraemon.p027eg.CommonUtil;
import com.eagle.mixsdk.sdk.did.ThinkFlyUtils;
import com.eagle.mixsdk.sdk.did.presenter.MsnIdentifierPresenter;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceUtil {
    private static boolean isFirst = true;

    public static JSONObject createDeviceJSON(Context context) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("carrier", CommonUtil.getSimOperatorByMnc());
        jSONObject.put("jbk", CommonUtil.getDeviceRootState());
        jSONObject.put("did", ThinkFlyUtils.getDeviceID(context));
        jSONObject.put("tz", CommonUtil.getTimeZone());
        jSONObject.put("lang", CommonUtil.getLanguage());
        jSONObject.put("width", getWidth(context));
        jSONObject.put("height", getHeight(context));
        jSONObject.put("mac", CommonUtil.getMacAddress());
        jSONObject.put("net", CommonUtil.getNetworkType());
        jSONObject.put("model", CommonUtil.getModel());
        jSONObject.put("os", CommonUtil.getOS());
        jSONObject.put("osver", CommonUtil.getOSVersion());
        jSONObject.put("apil", Build.VERSION.SDK_INT);
        jSONObject.put("idfa", AdvertisingIdHelper.getInstance().getAdvertisingId());
        jSONObject.put("idfv", getOAID(context));
        jSONObject.put("imei", CommonUtil.getIMEI(context));
        jSONObject.put("ip", CommonUtil.getLocalIPAddress());
        jSONObject.put("wifi", CommonUtil.getWifiName());
        jSONObject.put("brand", CommonUtil.getBrand());
        jSONObject.put("factory", CommonUtil.getManufacturer());
        return jSONObject;
    }

    public static JSONObject getDeviceInfo(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device", createDeviceJSON(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static int getHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static String getOAID(Context context) {
        if (isFirst) {
            isFirst = false;
            MsnIdentifierPresenter.getInstance().getIdentifier(context);
        }
        return MsnIdentifierPresenter.getInstance().getIdentifier().getOAID();
    }

    public static int getWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
