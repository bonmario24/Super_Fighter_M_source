package com.thinkfly.plugins.coludladder.okhttp;

import com.doraemon.p027eg.CheckUtils;
import com.thinkfly.plugins.coludladder.log.Log;
import com.thinkfly.plugins.coludladder.okhttp.OKHttpCenter;
import org.json.JSONObject;

public class OKHttpUtil {
    public static JSONObject getRequest(String url) {
        if (CheckUtils.isNullOrEmpty(url)) {
            return null;
        }
        Log.m662d(Log.TAG, "get url : " + url);
        return OKHttpCenter.getSync(url);
    }

    public static JSONObject postRequest(String url, String params, String sign, int c) {
        if (CheckUtils.isNullOrEmpty(url) || CheckUtils.isNullOrEmpty(params) || CheckUtils.isNullOrEmpty(sign)) {
            return null;
        }
        String url2 = getAbsoluteUrl(url, params, sign, c);
        Log.m662d(Log.TAG, "post url : " + url2);
        return OKHttpCenter.postSync(url2, params);
    }

    public static void getAsync(String url, OKHttpCenter.OKHttpCallbackListener listener) {
        OKHttpCenter.getAsync(url, listener);
    }

    private static String getAbsoluteUrl(String url, String params, String sign, int c) {
        return url + "?sign=" + sign + "&c=" + c;
    }
}
