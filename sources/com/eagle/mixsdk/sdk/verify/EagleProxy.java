package com.eagle.mixsdk.sdk.verify;

import android.text.TextUtils;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.p027eg.CommonUtil;
import com.doraemon.util.Utils;
import com.doraemon.util.language.Language;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.PayParams;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.base.Urls;
import com.eagle.mixsdk.sdk.did.ThinkFlyUtils;
import com.eagle.mixsdk.sdk.impl.listeners.ILoginListener;
import com.eagle.mixsdk.sdk.impl.listeners.IPayVerifyListener;
import com.eagle.mixsdk.sdk.impl.listeners.ISDKOrderIDListener;
import com.eagle.mixsdk.sdk.listeners.EagleCertificationInfoListener;
import com.eagle.mixsdk.sdk.listeners.EagleConfigResultListener;
import com.eagle.mixsdk.sdk.listeners.EagleGetUserInfoListener;
import com.eagle.mixsdk.sdk.log.Log;
import com.eagle.mixsdk.sdk.manger.EagleLoopDomainManager;
import com.eagle.mixsdk.sdk.okhttp.imp.OKHttpCallbackListener;
import com.eagle.mixsdk.sdk.okhttp.model.ExtensionInfo;
import com.eagle.mixsdk.sdk.plugin.EagleUser;
import com.eagle.mixsdk.sdk.utils.EagleHttpUtils;
import com.eagle.mixsdk.sdk.utils.EncryptUtils;
import com.eagle.mixsdk.sdk.utils.HttpUtil;
import com.eagle.mixsdk.sdk.utils.StoreUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class EagleProxy {
    public static void initConfig(final EagleConfigResultListener listener) {
        JSONObject initJson = new JSONObject();
        try {
            EagleHttpUtils.getCommonJson(initJson);
            initJson.put("deviceID", ThinkFlyUtils.getDeviceID(EagleSDK.getInstance().getApplication()));
        } catch (JSONException e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onResult(-1, new EagleInitConfig());
            }
        }
        EagleHttpUtils.doPostAsyncRequestWithLoop("/game/init?sign=" + HttpUtil.requestParamsSign(initJson.toString()), initJson.toString(), new EagleHttpUtils.EagleHttpResultListener() {
            public void onMainThreadSuccess(JSONObject jsonObject, ExtensionInfo extensionInfo) {
                EagleInitConfig initConfig = new EagleInitConfig();
                if (jsonObject != null) {
                    initConfig.parseConfig(jsonObject);
                } else {
                    initConfig.setErrorMsg("jsonObject is null");
                }
                if (listener != null) {
                    listener.onResult(initConfig.getState(), initConfig);
                }
            }

            public void onMainThreadFail(String s, ExtensionInfo extensionInfo) {
                Log.m599e(Constants.TAG, "result:" + s);
                if (listener != null) {
                    listener.onResult(-1, new EagleInitConfig().setErrorMsg(s));
                }
            }
        });
    }

    public static void doGetUserInfo(String extInfo, final EagleGetUserInfoListener userInfoListener) {
        HashMap<String, String> params = new HashMap<>();
        try {
            EagleHttpUtils.getCommonParams(params);
            EagleToken tokenInfo = EagleSDK.getInstance().getUToken();
            if (tokenInfo == null) {
                Log.m599e(Constants.TAG, "The user not logined. the token is null");
                userInfoListener.onResult((JSONObject) null);
                return;
            }
            params.put("userId", "" + tokenInfo.getUserID());
            if (CheckUtils.isNullOrEmpty(extInfo)) {
                extInfo = "";
            }
            params.put("extInfo", extInfo);
            if (params.size() <= 0) {
                Log.m599e(Constants.TAG, "doGetUserInfo fail");
                userInfoListener.onResult((JSONObject) null);
                return;
            }
            String paramsJson = new JSONObject(params).toString();
            EagleHttpUtils.doPostAsyncRequestWithLoop("/user/info/get?sign=" + HttpUtil.requestParamsSign(paramsJson), paramsJson, new EagleHttpUtils.EagleHttpResultListener() {
                public void onMainThreadSuccess(JSONObject jsonObject, ExtensionInfo extensionInfo) {
                    Log.m598d(Constants.TAG, "get user info result is " + jsonObject);
                    if (jsonObject == null) {
                        Log.m599e(Constants.TAG, "doGetUserInfo fail");
                        userInfoListener.onResult((JSONObject) null);
                        return;
                    }
                    try {
                        int state = jsonObject.optInt(ServerProtocol.DIALOG_PARAM_STATE);
                        String msg = jsonObject.optString("msg");
                        if (state != 1) {
                            Log.m599e(Constants.TAG, "doGetUserInfo fail ：" + msg);
                            userInfoListener.onResult((JSONObject) null);
                            return;
                        }
                        JSONObject jsonData = jsonObject.optJSONObject("data");
                        if (jsonData == null) {
                            Log.m599e(Constants.TAG, "doGetUserInfo fail");
                            userInfoListener.onResult((JSONObject) null);
                            return;
                        }
                        userInfoListener.onResult(jsonData);
                    } catch (Exception e) {
                        Log.m599e(Constants.TAG, "doGetUserInfo fail");
                        e.printStackTrace();
                        userInfoListener.onResult((JSONObject) null);
                    }
                }

                public void onMainThreadFail(String s, ExtensionInfo extensionInfo) {
                    Log.m599e(Constants.TAG, "doGetUserInfo fail:" + s);
                    userInfoListener.onResult((JSONObject) null);
                }
            });
        } catch (Exception e) {
            Log.m599e(Constants.TAG, "doGetUserInfo fail");
            e.printStackTrace();
        }
    }

    public static void doGetPlayerCertificationInfo(final EagleCertificationInfoListener listener) {
        HashMap<String, String> params = new HashMap<>();
        try {
            EagleHttpUtils.getCommonParams(params);
            EagleToken tokenInfo = EagleSDK.getInstance().getUToken();
            if (tokenInfo == null) {
                Log.m599e(Constants.TAG, "The user not logined. the token is null");
                listener.onResult(createDefaultCertificationInfo());
                return;
            }
            params.put("userId", "" + tokenInfo.getUserID());
            params.put("extInfo", "");
            if (params.size() <= 0) {
                Log.m599e(Constants.TAG, "getPlayerCertificationInfo fail");
                listener.onResult(createDefaultCertificationInfo());
                return;
            }
            String paramsJson = new JSONObject(params).toString();
            EagleHttpUtils.doPostAsyncRequestWithLoop("/user/certification/get?sign=" + HttpUtil.requestParamsSign(paramsJson), paramsJson, new EagleHttpUtils.EagleHttpResultListener() {
                public void onMainThreadSuccess(JSONObject jsonObject, ExtensionInfo extensionInfo) {
                    Log.m598d(Constants.TAG, "get Player Adult Info result is " + jsonObject);
                    if (jsonObject == null) {
                        Log.m599e(Constants.TAG, "getPlayerCertificationInfo fail");
                        listener.onResult(EagleProxy.createDefaultCertificationInfo());
                        return;
                    }
                    try {
                        int state = jsonObject.optInt(ServerProtocol.DIALOG_PARAM_STATE);
                        String msg = jsonObject.optString("msg");
                        if (state != 1) {
                            Log.m599e(Constants.TAG, "getPlayerCertificationInfo fail ：" + msg);
                            listener.onResult(EagleProxy.createDefaultCertificationInfo());
                            return;
                        }
                        JSONObject jsonData = jsonObject.optJSONObject("data");
                        if (jsonData == null) {
                            Log.m599e(Constants.TAG, "getPlayerCertificationInfo fail");
                            listener.onResult(EagleProxy.createDefaultCertificationInfo());
                            return;
                        }
                        listener.onResult(new EagleCertificationInfo(jsonData.optInt("certification", -1), jsonData.optInt("age", -1), jsonData.optString("identity"), jsonData.optString("extInfo")));
                    } catch (Exception e) {
                        Log.m599e(Constants.TAG, "getPlayerCertificationInfo fail");
                        e.printStackTrace();
                        listener.onResult(EagleProxy.createDefaultCertificationInfo());
                    }
                }

                public void onMainThreadFail(String s, ExtensionInfo extensionInfo) {
                    Log.m599e(Constants.TAG, "getPlayerCertificationInfo fail:" + s);
                    listener.onResult(EagleProxy.createDefaultCertificationInfo());
                }
            });
        } catch (Exception e) {
            Log.m599e(Constants.TAG, "getPlayerCertificationInfo fail");
            e.printStackTrace();
        }
    }

    public static EagleCertificationInfo createDefaultCertificationInfo() {
        return new EagleCertificationInfo();
    }

    public static int calculateCertificationInfo(int certificationState, int playerAge) {
        if (certificationState == 0) {
            return -1;
        }
        if (certificationState != 1 || playerAge == -1 || playerAge >= 18) {
            return 0;
        }
        return 1;
    }

    public static void getToken(String result, final ILoginListener listener) {
        HashMap<String, String> params = new HashMap<>();
        EagleHttpUtils.getCommonParams(params);
        params.put("deviceID", ThinkFlyUtils.getDeviceID(EagleSDK.getInstance().getContext()));
        params.put("carrier", CommonUtil.getSimOperatorByMnc());
        params.put("jbk", CommonUtil.getDeviceRootState() + "");
        params.put("tz", CommonUtil.getTimeZone());
        params.put("resolution", CommonUtil.getResolution());
        params.put("mac", CommonUtil.getMacAddress());
        params.put("networkType", CommonUtil.getNetworkType() + "");
        params.put("model", CommonUtil.getModel());
        params.put("osVersion", CommonUtil.getOSVersion());
        params.put("gameName", CommonUtil.getAppName());
        params.put("gameBundle", CommonUtil.getAppPackageName());
        params.put("gameVersion", CommonUtil.getAppVersionName());
        params.put("gameBuild", CommonUtil.getAppVersionCode());
        params.put("idfa", CommonUtil.getIMEI(EagleSDK.getInstance().getContext()));
        params.put("idfv", "");
        params.put("ip", CommonUtil.getLocalIPAddress());
        params.put("wifi", CommonUtil.getWifiName());
        params.put("suid", "");
        params.put("manufacturer", CommonUtil.getManufacturer());
        params.put(ShareConstants.MEDIA_EXTENSION, result);
        StringBuilder sb = new StringBuilder();
        sb.append("appID=").append(EagleSDK.getInstance().getAppID()).append("channelID=").append(EagleSDK.getInstance().getCurrChannel()).append("extension=").append(result).append(EagleSDK.getInstance().getAppKey());
        params.put("sign", EncryptUtils.md5(sb.toString()).toLowerCase());
        params.put("pkg", Utils.getApp().getPackageName());
        String paramStr = "";
        try {
            paramStr = EagleHttpUtils.urlParamsFormat(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(paramStr)) {
            listener.onLoginFail("get token fail");
        } else {
            EagleHttpUtils.doGetAsyncRequestWithLoop("/user/getToken?" + paramStr, new EagleHttpUtils.EagleHttpResultListener() {
                public void onMainThreadSuccess(JSONObject jsonObject, ExtensionInfo extensionInfo) {
                    JSONObject activityJson;
                    String str;
                    Log.m598d(Constants.TAG, "The token result is " + jsonObject);
                    if (jsonObject == null) {
                        listener.onLoginFail("get token fail");
                        return;
                    }
                    try {
                        int state = jsonObject.optInt(ServerProtocol.DIALOG_PARAM_STATE);
                        String msg = jsonObject.optString("msg");
                        if (state == 17) {
                            ILoginListener iLoginListener = listener;
                            EagleToken eagleToken = new EagleToken();
                            if (TextUtils.isEmpty(msg)) {
                                str = "注册已关闭";
                            } else {
                                str = msg;
                            }
                            EagleToken gameNoticeSwitch = eagleToken.setGameNoticeContent(str).setGameNoticeSwitch(1);
                            if (TextUtils.isEmpty(msg)) {
                                msg = "注册已关闭";
                            }
                            iLoginListener.onLoginSuccess(gameNoticeSwitch.setMsg(msg));
                        } else if (state != 1) {
                            ILoginListener iLoginListener2 = listener;
                            if (TextUtils.isEmpty(msg)) {
                                msg = "get token fail";
                            }
                            iLoginListener2.onLoginFail(msg);
                        } else {
                            JSONObject jsonData = jsonObject.optJSONObject("data");
                            if (jsonData == null) {
                                listener.onLoginFail("get token fail");
                                return;
                            }
                            long userID = jsonData.optLong("userID");
                            StoreUtils.putString(EagleSDK.USERID_KEY, userID + "");
                            EagleToken eagleToken2 = new EagleToken(userID, jsonData.optString("sdkUserID"), jsonData.optString("username"), jsonData.optString("sdkUserName"), jsonData.optString("token"), jsonData.optString(ShareConstants.MEDIA_EXTENSION), msg, jsonData.optInt("isOld"), jsonData.optInt("isActivated"));
                            if (jsonData.has("isOpenServer")) {
                                eagleToken2.setIsOpenServer(jsonData.optInt("isOpenServer"));
                            }
                            if (jsonData.has("serverPrompt")) {
                                eagleToken2.setGameNoticeContent(jsonData.optString("serverPrompt"));
                            }
                            if (jsonData.has("serverPromptSwitch")) {
                                eagleToken2.setGameNoticeSwitch(jsonData.optInt("serverPromptSwitch"));
                            }
                            int antiAddictionSwitch = jsonData.optInt("antiAddictionSw");
                            eagleToken2.setAntiAddictionSwitch(antiAddictionSwitch);
                            if (antiAddictionSwitch == 1) {
                                String identity = jsonData.optString("identity");
                                int certificationState = jsonData.optInt("certification", -1);
                                int playerAge = jsonData.optInt("age", -1);
                                eagleToken2.setAgeLimit(jsonData.optInt("ageLimit", 0));
                                eagleToken2.setCertificationInfo(new EagleCertificationInfo(certificationState, playerAge, identity, ""));
                            }
                            eagleToken2.setRegisterTime(jsonData.optLong("rtime", 0));
                            eagleToken2.setHasBindMobile(jsonData.optInt("isBindMobile", 0) == 1);
                            if (jsonData.has("activity") && (activityJson = jsonData.optJSONObject("activity")) != null) {
                                eagleToken2.setOpenBindMobile(activityJson.optInt("bindMobile", 0) == 1);
                            }
                            listener.onLoginSuccess(eagleToken2);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        listener.onLoginFail("get token fail");
                    }
                }

                public void onMainThreadFail(String s, ExtensionInfo extensionInfo) {
                    listener.onLoginFail("get token fail " + s);
                }
            });
        }
    }

    public static void getOrder(PayParams data, final ISDKOrderIDListener listener) {
        if (listener != null) {
            HashMap<String, String> params = new HashMap<>();
            EagleHttpUtils.getCommonParams(params);
            try {
                EagleToken tokenInfo = EagleSDK.getInstance().getUToken();
                if (tokenInfo == null) {
                    Log.m599e(Constants.TAG, "The user not logined. the token is null");
                    listener.onFailed(-1, "The user not logined. the token is null");
                } else if (AppEventsConstants.EVENT_PARAM_VALUE_NO.equals(data.getMoney())) {
                    listener.onFailed(-1, "The price or money is 0 ?");
                } else {
                    try {
                        Long.parseLong(data.getMoney());
                        params.put("userID", "" + tokenInfo.getUserID());
                        params.put("appID", EagleSDK.getInstance().getAppID() + "");
                        params.put("channelID", "" + EagleSDK.getInstance().getCurrChannel());
                        params.put("productID", data.getProductId());
                        params.put("productName", data.getProductName());
                        params.put("productDesc", data.getProductDesc());
                        params.put("money", data.getMoney());
                        params.put(FirebaseAnalytics.Param.CURRENCY, data.getCurrency());
                        params.put("roleID", "" + data.getRoleId());
                        params.put("roleName", data.getRoleName());
                        params.put("roleLevel", data.getRoleLevel() + "");
                        params.put("serverID", data.getServerId());
                        params.put("serverName", data.getServerName());
                        params.put(ShareConstants.MEDIA_EXTENSION, data.getExtension());
                        params.put("notifyUrl", data.getPayNotifyUrl());
                        params.put("gameOrderID", data.getGameOrderID());
                        params.put("jsonExt", EagleUser.getInstance().getIUser().getJsonExt());
                        params.put("v", Constants.VERSIONNAME);
                        params.put(Language.f817pt, "android");
                        params.put("subChannelID", "" + EagleSDK.getInstance().getSubChannel());
                        params.put("extChannel", EagleSDK.getInstance().getExtChannel());
                        params.put("suid", "");
                        params.put("deviceID", ThinkFlyUtils.getDeviceID(EagleSDK.getInstance().getContext()));
                        params.put("idfa", CommonUtil.getIMEI(EagleSDK.getInstance().getContext()));
                        params.put("signType", "md5");
                        params.put("sign", generateSign(tokenInfo, data));
                        params.put("pkg", Utils.getApp().getPackageName());
                        String paramStr = "";
                        try {
                            paramStr = EagleHttpUtils.urlParamsFormat(params, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        if (TextUtils.isEmpty(paramStr)) {
                            listener.onFailed(-1, "get order fail");
                        } else {
                            EagleHttpUtils.doPostAsyncByFormRequestWithLoop(Urls.ORDERS_URL, paramStr, new EagleHttpUtils.EagleHttpResultListener() {
                                public void onMainThreadSuccess(JSONObject jsonObject, ExtensionInfo extensionInfo) {
                                    Log.m598d(Constants.TAG, "The order result is " + jsonObject);
                                    if (jsonObject == null) {
                                        listener.onFailed(-1, "get order fail");
                                        return;
                                    }
                                    try {
                                        int state = jsonObject.optInt(ServerProtocol.DIALOG_PARAM_STATE);
                                        String msg = "get order success";
                                        if (jsonObject.has("msg")) {
                                            msg = jsonObject.optString("msg");
                                        }
                                        JSONObject jsonData = jsonObject.optJSONObject("data");
                                        if (jsonData == null) {
                                            listener.onFailed(-1, "get order fail");
                                        } else {
                                            listener.onSuccess(new EagleOrder(state, jsonData.optString("orderID"), jsonData.optString(ShareConstants.MEDIA_EXTENSION), jsonData.optString("notifyUrl"), msg));
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        listener.onFailed(-1, "get order fail");
                                    }
                                }

                                public void onMainThreadFail(String s, ExtensionInfo extensionInfo) {
                                    listener.onFailed(-1, "get order fail" + s);
                                }
                            });
                        }
                    } catch (NumberFormatException e2) {
                        e2.printStackTrace();
                        listener.onFailed(-1, "The price or money cannot have decimals ");
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public static void payVerify(JSONObject data, final IPayVerifyListener listener) {
        if (listener != null && data != null) {
            if (checkDataInValid(data, "orderID") || checkDataInValid(data, "channelOrderID") || checkDataInValid(data, "environment") || checkDataInValid(data, "jsonExt")) {
                listener.onVerifyFail(" some value is empty!", data);
                return;
            }
            EagleHttpUtils.getCommonJson(data);
            EagleLoopDomainManager.getInstance().doPostAsyncRequest("/pay/verify?sign=" + HttpUtil.requestParamsSign(data.toString()), data.toString(), data, new OKHttpCallbackListener() {
                public void onSuccess(JSONObject jsonObject, ExtensionInfo extensionInfo) {
                    String msg = "verify fail";
                    Object args = extensionInfo != null ? extensionInfo.getExtData() : null;
                    if (jsonObject != null) {
                        if (jsonObject.optInt(ServerProtocol.DIALOG_PARAM_STATE) == 1) {
                            listener.onVerifySuccess(args);
                            return;
                        }
                        msg = jsonObject.optString("msg", msg);
                    }
                    listener.onVerifyFail(msg, args);
                }

                public void onFailure(String msg, ExtensionInfo extensionInfo) {
                    Log.m599e(Constants.TAG, "result:" + msg);
                    listener.onVerifyFail(msg, extensionInfo != null ? extensionInfo.getExtData() : null);
                }
            });
        }
    }

    private static String generateSign(EagleToken token, PayParams data) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        sb.append("userID=").append(token.getUserID()).append("&").append("productID=").append(data.getProductId() == null ? "" : data.getProductId()).append("&").append("productName=").append(data.getProductName() == null ? "" : data.getProductName()).append("&").append("productDesc=").append(data.getProductDesc() == null ? "" : data.getProductDesc()).append("&").append("money=").append(data.getMoney()).append("&").append("roleID=").append(data.getRoleId() == null ? "" : data.getRoleId()).append("&").append("roleName=").append(data.getRoleName() == null ? "" : data.getRoleName()).append("&").append("roleLevel=").append(data.getRoleLevel()).append("&").append("serverID=").append(data.getServerId() == null ? "" : data.getServerId()).append("&").append("serverName=").append(data.getServerName() == null ? "" : data.getServerName()).append("&").append("extension=").append(data.getExtension() == null ? "" : data.getExtension());
        if (!TextUtils.isEmpty(data.getPayNotifyUrl())) {
            sb.append("&notifyUrl=").append(data.getPayNotifyUrl());
        }
        sb.append(EagleSDK.getInstance().getAppKey());
        String encoded = URLEncoder.encode(sb.toString(), "UTF-8");
        Log.m598d(Constants.TAG, "The encoded getOrderID sign is " + encoded);
        String sign = EncryptUtils.md5(encoded).toLowerCase();
        Log.m598d(Constants.TAG, "The getOrderID sign is " + sign);
        return sign;
    }

    private static boolean checkDataInValid(JSONObject data, String key) {
        return TextUtils.isEmpty(data.optString(key, ""));
    }
}
