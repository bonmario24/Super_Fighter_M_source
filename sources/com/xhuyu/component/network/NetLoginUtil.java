package com.xhuyu.component.network;

import android.text.TextUtils;
import com.star.libtrack.core.TrackCore;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.core.manager.NetWorkManager;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xhuyu.component.utils.ResourceUtil;
import com.xhuyu.component.utils.appsflyer.AFEventType;
import com.xhuyu.component.utils.appsflyer.AppsFlyerTrackUtil;
import com.xsdk.doraemon.okhttp.imp.OKHttpCallbackListener;
import com.xsdk.doraemon.okhttp.model.ExtensionInfo;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.utils.ContextUtil;
import java.util.HashMap;
import org.json.JSONObject;

public class NetLoginUtil {
    public static void postGetRandAccount(final GameSDKListener listener) {
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.URL_GET_RAND_ACCOUNT, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, new Object[0]);
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    public static void postQuickLogin(String account, String password, boolean change, GameSDKListener listener) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("username", account);
        hashMap.put("password", password);
        hashMap.put("change_pwd", Integer.valueOf(change ? 1 : 0));
        loginRequest(Urls.URL_QUICK_LOGIN, hashMap, listener);
    }

    public static void postVisitorLogin(String account, String password, GameSDKListener listener) {
        HashMap<String, Object> data = new HashMap<>();
        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(password)) {
            data.put("username", account);
            data.put("password", password);
        }
        loginRequest(Urls.VISITOR_LOGIN, data, listener);
    }

    public static void postFacebookLogin(String accessToken, GameSDKListener listener) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("access_token", accessToken);
        loginRequest(Urls.FACEBOOK_LOGIN, hashMap, listener);
    }

    public static void postGoogleLogin(String idToken, GameSDKListener listener) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id_token", idToken);
        loginRequest(Urls.GOOGLE_LOGIN, hashMap, listener);
    }

    public static void postAutoLogin(GameSDKListener listener) {
        SDKEventPost.postTrack(8194, 10);
        AppsFlyerTrackUtil.trackStartLogin(ContextUtil.getInstance().getApplicationContext(), AFEventType.AUTO_LOGIN);
        HuYuUser user = UserManager.getInstance().getUserCache();
        if (user == null) {
            listener.onFail(ResourceUtil.getString("auto_login_out_date"), -1);
            return;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("star_token", user.getStar_token());
        loginRequest(Urls.AUTO_LOGIN, hashMap, listener);
    }

    private static void loginRequest(String url, HashMap<String, Object> dataMap, final GameSDKListener listener) {
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(url, dataMap, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                NetLoginUtil.updateUserInfo(jsonObject);
                listener.onSuccess(jsonObject, message, new Object[0]);
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    /* access modifiers changed from: private */
    public static void updateUserInfo(JSONObject dataJson) {
        UserManager.getInstance().setLoginStatus(true);
        if (dataJson == null) {
            dataJson = new JSONObject();
        }
        try {
            String uid = dataJson.optString("uid");
            if (!CheckUtil.isEmpty(uid)) {
                TrackCore.getInstance().setUid(uid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
