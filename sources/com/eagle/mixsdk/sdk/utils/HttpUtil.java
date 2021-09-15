package com.eagle.mixsdk.sdk.utils;

import com.doraemon.okhttp3.Call;
import com.doraemon.okhttp3.Callback;
import com.doraemon.p027eg.CommonUtil;
import com.doraemon.util.language.Language;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.log.Log;
import com.eagle.mixsdk.sdk.okhttp.imp.CallBackListener;
import com.eagle.mixsdk.sdk.okhttp.util.OKHttpUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpUtil {
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String PARAMETER_SEPARATOR = "&";

    public interface IHttpResult {
        void onMainThreadFail(String str);

        void onMainThreadSuccess(JSONObject jSONObject);
    }

    public static JSONObject getCommonJson(JSONObject jsonObject) {
        if (jsonObject == null) {
            jsonObject = new JSONObject();
        }
        try {
            jsonObject.put("appID", EagleSDK.getInstance().getAppID());
            jsonObject.put("channelID", EagleSDK.getInstance().getCurrChannel());
            jsonObject.put("subChannelID", EagleSDK.getInstance().getSubChannel());
            jsonObject.put("extChannel", EagleSDK.getInstance().getExtChannel());
            jsonObject.put(Language.f817pt, "android");
            jsonObject.put("v", Constants.VERSIONNAME);
            jsonObject.put("lang", CommonUtil.getLanguage());
            jsonObject.put("language", getLanguage());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.m602w(Constants.TAG, " params " + jsonObject.toString());
        return jsonObject;
    }

    public static void getCommonParams(Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        try {
            params.put("appID", EagleSDK.getInstance().getAppID() + "");
            params.put("channelID", "" + EagleSDK.getInstance().getCurrChannel());
            params.put("subChannelID", "" + EagleSDK.getInstance().getSubChannel());
            params.put("extChannel", EagleSDK.getInstance().getExtChannel());
            params.put("lang", CommonUtil.getLanguage());
            params.put("language", getLanguage());
            params.put(Language.f817pt, "android");
            params.put("v", Constants.VERSIONNAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.m602w(Constants.TAG, " params " + params.toString());
    }

    public static String getLanguage() {
        try {
            return ResPluginUtil.getStringByName("eagle_lan");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String urlParamsFormat(Map<String, String> params, String charset) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            String val = params.get(key);
            if (val == null) {
                val = "";
            }
            String encodedName = URLEncoder.encode(key, charset);
            String encodedValue = URLEncoder.encode(val, charset);
            if (sb.length() > 0) {
                sb.append(PARAMETER_SEPARATOR);
            }
            sb.append(encodedName).append(NAME_VALUE_SEPARATOR).append(encodedValue);
        }
        return sb.toString();
    }

    public static void getAsync(String url, final IHttpResult listener) {
        OKHttpUtil.getInstance().doGetAsyncRequest(url, new CallBackListener() {
            public void onSuccess(final JSONObject jsonObject, Object args) {
                EagleSDK.getInstance().runOnMainThread(new Runnable() {
                    public void run() {
                        if (listener != null) {
                            listener.onMainThreadSuccess(jsonObject);
                        }
                    }
                });
            }

            public void onFailure(Callback var1, Call call, final String exceptionMessage) {
                EagleSDK.getInstance().runOnMainThread(new Runnable() {
                    public void run() {
                        if (listener != null) {
                            listener.onMainThreadFail(exceptionMessage);
                        }
                    }
                });
            }
        });
    }

    public static void postAsync(String url, String params, final IHttpResult listener) {
        OKHttpUtil.getInstance().doPostAsyncRequest(url, params, new CallBackListener() {
            public void onSuccess(final JSONObject jsonObject, Object args) {
                EagleSDK.getInstance().runOnMainThread(new Runnable() {
                    public void run() {
                        if (listener != null) {
                            listener.onMainThreadSuccess(jsonObject);
                        }
                    }
                });
            }

            public void onFailure(Callback var1, Call call, final String exceptionMessage) {
                EagleSDK.getInstance().runOnMainThread(new Runnable() {
                    public void run() {
                        if (listener != null) {
                            listener.onMainThreadFail(exceptionMessage);
                        }
                    }
                });
            }
        });
    }

    public static void postByForm(String url, String params, final IHttpResult listener) {
        OKHttpUtil.getInstance().doPostAsyncByFormRequest(url, params, new CallBackListener() {
            public void onSuccess(final JSONObject jsonObject, Object args) {
                EagleSDK.getInstance().runOnMainThread(new Runnable() {
                    public void run() {
                        if (listener != null) {
                            listener.onMainThreadSuccess(jsonObject);
                        }
                    }
                });
            }

            public void onFailure(Callback var1, Call call, final String exceptionMessage) {
                EagleSDK.getInstance().runOnMainThread(new Runnable() {
                    public void run() {
                        if (listener != null) {
                            listener.onMainThreadFail(exceptionMessage);
                        }
                    }
                });
            }
        });
    }

    public static String requestParamsSign(String jsonData) {
        return EncryptUtils.md5(EncryptUtils.md5(jsonData) + EagleSDK.getInstance().getAppKey());
    }
}
