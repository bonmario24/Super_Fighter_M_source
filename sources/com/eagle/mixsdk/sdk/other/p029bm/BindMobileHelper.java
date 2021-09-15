package com.eagle.mixsdk.sdk.other.p029bm;

import android.text.TextUtils;
import com.doraemon.p027eg.CommonUtil;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.did.ThinkFlyUtils;
import com.eagle.mixsdk.sdk.listeners.EagleBindMobileListener;
import com.eagle.mixsdk.sdk.log.Log;
import com.eagle.mixsdk.sdk.okhttp.model.ExtensionInfo;
import com.eagle.mixsdk.sdk.utils.EagleHttpUtils;
import com.eagle.mixsdk.sdk.utils.HttpUtil;
import com.eagle.mixsdk.sdk.utils.ResPluginUtil;
import com.facebook.internal.ServerProtocol;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.eagle.mixsdk.sdk.other.bm.BindMobileHelper */
public class BindMobileHelper {
    private static final String TAG = "Eagle BindMobileHelper";
    private static final String bindPath = "/user/bind/binding?sign=";
    private static final String captchaPath = "/user/bind/captcha?sign=";
    private static final String h5Path = "/user/bind/index?";
    private static BindMobileHelper instance;

    /* renamed from: com.eagle.mixsdk.sdk.other.bm.BindMobileHelper$IJsCallBack */
    public interface IJsCallBack {
        void onResult(int i, String str);
    }

    private BindMobileHelper() {
    }

    public static BindMobileHelper getInstance() {
        if (instance == null) {
            instance = new BindMobileHelper();
        }
        return instance;
    }

    public void sendVerifyCode(String phoneNum, final EagleBindMobileListener listener) {
        if (isPhoneNum(phoneNum)) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("phoneNum", phoneNum);
                jsonObject.put("ts", System.currentTimeMillis() + "");
                EagleHttpUtils.getCommonJson(jsonObject);
                EagleHttpUtils.doPostAsyncRequestWithLoop(captchaPath + HttpUtil.requestParamsSign(jsonObject.toString()), jsonObject.toString(), new EagleHttpUtils.EagleHttpResultListener() {
                    public void onMainThreadSuccess(JSONObject jsonObject, ExtensionInfo extensionInfo) {
                        if (jsonObject != null) {
                            Log.m599e(BindMobileHelper.TAG, "sendVerifyCode is success " + jsonObject.toString());
                            int code = -1;
                            String msg = "";
                            if (jsonObject.has(ServerProtocol.DIALOG_PARAM_STATE)) {
                                code = jsonObject.optInt(ServerProtocol.DIALOG_PARAM_STATE);
                            }
                            if (jsonObject.has("msg")) {
                                msg = jsonObject.optString("msg");
                            }
                            if (listener != null) {
                                listener.onResult(code, msg);
                            }
                        }
                    }

                    public void onMainThreadFail(String s, ExtensionInfo extensionInfo) {
                        if (listener != null) {
                            listener.onResult(-1, ResPluginUtil.getStringByName("xeagle_failed_verification_code"));
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
                if (listener != null) {
                    listener.onResult(-2, ResPluginUtil.getStringByName("xeagle_failed_verification_code"));
                }
            }
        } else if (listener != null) {
            listener.onResult(-1, ResPluginUtil.getStringByName("xeagle_correct_cell_phone_number"));
        }
    }

    public void bindingMobile(String captcha, String phoneNum, String roleData, final EagleBindMobileListener listener) {
        if (TextUtils.isEmpty(captcha)) {
            Log.m599e(TAG, "captcha is empty");
            if (listener != null) {
                listener.onResult(-1, ResPluginUtil.getStringByName("xeagle_correct_verification_code"));
            }
        } else if (!isPhoneNum(phoneNum)) {
            if (listener != null) {
                listener.onResult(-1, ResPluginUtil.getStringByName("xeagle_correct_cell_phone_number"));
            }
        } else if (TextUtils.isEmpty(roleData)) {
            Log.m599e(TAG, "roleData is empty");
            if (listener != null) {
                listener.onResult(-1, ResPluginUtil.getStringByName("xeagle_roledata_not_null"));
            }
        } else {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("idfa", "");
                jsonObject.put("imei", CommonUtil.getIMEI(EagleSDK.getInstance().getContext()));
                jsonObject.put("did", ThinkFlyUtils.getDeviceID(EagleSDK.getInstance().getContext()));
                jsonObject.put("phoneNum", phoneNum);
                jsonObject.put("captcha", captcha);
                jsonObject.put("uid", EagleSDK.getInstance().getUToken().getUserID() + "");
                JSONObject roleJson = new JSONObject(roleData);
                jsonObject.put("serverId", roleJson.optString("serverId", ""));
                jsonObject.put("serverName", roleJson.optString("serverName", ""));
                jsonObject.put("roleId", roleJson.optString("roleId", ""));
                jsonObject.put("roleName", roleJson.optString("roleName", ""));
                jsonObject.put("roleLevel", roleJson.optString("roleLevel", ""));
                jsonObject.put("vipLevel", roleJson.optString("vipLevel", ""));
                EagleHttpUtils.getCommonJson(jsonObject);
                EagleHttpUtils.doPostAsyncRequestWithLoop(bindPath + HttpUtil.requestParamsSign(jsonObject.toString()), jsonObject.toString(), new EagleHttpUtils.EagleHttpResultListener() {
                    public void onMainThreadSuccess(JSONObject jsonObject, ExtensionInfo extensionInfo) {
                        if (jsonObject != null) {
                            Log.m599e(BindMobileHelper.TAG, "bindingMobile is success " + jsonObject.toString());
                            int code = -1;
                            String msg = "";
                            if (jsonObject.has(ServerProtocol.DIALOG_PARAM_STATE)) {
                                code = jsonObject.optInt(ServerProtocol.DIALOG_PARAM_STATE);
                            }
                            if (code == 1) {
                                try {
                                    msg = jsonObject.getJSONObject("data").optString("msg", "");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else if (jsonObject.has("msg")) {
                                msg = jsonObject.optString("msg");
                            }
                            if (listener != null) {
                                listener.onResult(code, msg);
                            }
                        }
                    }

                    public void onMainThreadFail(String s, ExtensionInfo extensionInfo) {
                        if (listener != null) {
                            listener.onResult(-1, ResPluginUtil.getStringByName("xeagle_get_the_failure"));
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getH5Url(HashMap<String, String> params) {
        String _params = "";
        try {
            EagleHttpUtils.getCommonParams(params);
            _params = EagleHttpUtils.urlParamsFormat(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = EagleSDK.getInstance().getEagleServerURL() + h5Path + _params;
        Log.m602w(TAG, "h5Url  " + url);
        return url;
    }

    public boolean isPhoneNum(String mobile) {
        return Pattern.matches("^1(3[0-9]|4[0-9]|5[0-9]|6[0-9]|7[0-9]|8[0-9]|9[0-9])\\d{8}", mobile);
    }
}
