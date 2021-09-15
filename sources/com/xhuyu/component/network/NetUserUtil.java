package com.xhuyu.component.network;

import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.core.manager.NetWorkManager;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.utils.RSAUtil;
import com.xhuyu.component.utils.ResourceUtil;
import com.xsdk.doraemon.okhttp.imp.OKHttpCallbackListener;
import com.xsdk.doraemon.okhttp.model.ExtensionInfo;
import java.util.HashMap;
import org.json.JSONObject;

public class NetUserUtil {
    public static void postBindEmail(String email, String verifyCode, final GameSDKListener listener) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("email_code", verifyCode);
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.URL_EMAIL_BIND, hashMap, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, new Object[0]);
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    public static void postGetEmailVerifyCode(String email, final GameSDKListener listener) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", email);
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.URL_GET_EMAIL_VERIFY_CODE, hashMap, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, new Object[0]);
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    public static void postGetPhoneCodeWithInLogin(final GameSDKListener listener) {
        if (UserManager.getInstance().getUser() == null) {
            listener.onFail(ResourceUtil.getString("huyu_no_login_user"), -1);
        } else {
            NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.URL_PHONE_CODE_WITH_LOGIN, new OKHttpCallbackListener() {
                public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                    listener.onSuccess(jsonObject, message, new Object[0]);
                }

                public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                    listener.onFail(errorThrows, errorCode);
                }
            });
        }
    }

    public static void postVerifyChangePhone(String phone, String verifyCode, int type, String areaCode, String areaAbbreviation, final GameSDKListener listener) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("step", type + "");
        if (type != 1) {
            hashMap.put("phone", phone);
        }
        hashMap.put("sms_code", verifyCode);
        hashMap.put("area_code", areaCode);
        hashMap.put("area_abbreviation", areaAbbreviation);
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.URL_VERIFY_CHANGE_PHONE, hashMap, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, new Object[0]);
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    public static void postBindPhone(String phone, String verifyCode, String areaCode, String areaAbbreviation, final GameSDKListener listener) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("phone", phone);
        hashMap.put("sms_code", verifyCode);
        hashMap.put("area_code", areaCode);
        hashMap.put("area_abbreviation", areaAbbreviation);
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.URL_BIND_PHONE, hashMap, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, new Object[0]);
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    public static void postGetVerifyCodeBind(String phone, String areaCode, String areaAbbreviation, final GameSDKListener listener) {
        if (UserManager.getInstance().getUser() == null) {
            listener.onFail(ResourceUtil.getString("huyu_no_login_user"), -1);
            return;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("phone", phone);
        hashMap.put("area_code", areaCode);
        hashMap.put("area_abbreviation", areaAbbreviation);
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.URL_GET_SMS_VERIFY_CODE, hashMap, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, new Object[0]);
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    public static void postSetPassword(String password, final GameSDKListener listener) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("password", RSAUtil.publicEncrypt(password));
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.URL_SET_PASSWORD, hashMap, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, new Object[0]);
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    public static void postModifyPassword(String oldpass, String newPass, final GameSDKListener listener) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("password", RSAUtil.publicEncrypt(newPass));
        hashMap.put("old_password", RSAUtil.publicEncrypt(oldpass));
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.URL_MODIFY_PASSWORD, hashMap, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, new Object[0]);
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    public static void postGetUserInfo(final GameSDKListener listener) {
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.URL_GET_USER_INFO, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, new Object[0]);
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }
}
