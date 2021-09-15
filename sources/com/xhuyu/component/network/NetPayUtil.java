package com.xhuyu.component.network;

import android.content.Context;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.eagle.mixsdk.sdk.did.ThinkFlyUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseUser;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.core.config.TrackEventKey;
import com.xhuyu.component.core.manager.NetWorkManager;
import com.xhuyu.component.utils.GameUtil;
import com.xhuyu.component.utils.JsonUtil;
import com.xhuyu.component.utils.PhoneInfoUtils;
import com.xhuyu.component.utils.TimeUtil;
import com.xhuyu.component.utils.third.GoogleLoginUtil;
import com.xsdk.doraemon.okhttp.imp.OKHttpCallbackListener;
import com.xsdk.doraemon.okhttp.model.ExtensionInfo;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.utils.ContextUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class NetPayUtil {
    public static void doPaymentToWallet(String tradCode, String url, final GameSDKListener listener) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("trade_code", tradCode);
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(url, hashMap, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, extensionInfo.getExtData());
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    public static void getPayWays(Context context, String roleLevel, final GameSDKListener listener) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("tz", "+8");
        hashMap.put("game_version", GameUtil.getVersionName(context));
        hashMap.put("game_build_version", GameUtil.getVersionCode(context));
        hashMap.put("game_role_level", roleLevel);
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.GET_PAYMENT_LIST, hashMap, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, extensionInfo.getExtData());
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    public static void doVerifyGooglePay(Purchase purchase, boolean isPaySource, final GameSDKListener listener) {
        Map<String, Object> extMap = new HashMap<>();
        extMap.put(FirebaseAnalytics.Event.PURCHASE, purchase);
        extMap.put("isPaySource", Boolean.valueOf(isPaySource));
        ExtensionInfo extensionInfo = new ExtensionInfo();
        extensionInfo.setExtData(extMap);
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("signture", purchase.getSignature());
        dataMap.put("signture_data", purchase.getOriginalJson());
        dataMap.put("order_id", purchase.getDeveloperPayload());
        NetWorkManager.getInstance().doPostAsyncRequest(Urls.GOOGLE_PAY, dataMap, extensionInfo, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                SDKEventPost.postTrack(TrackEventKey.ON_RECEIVER_SERVER_PAY_RESULT, 1, NetPayUtil.transitionData(extensionInfo, FirebaseAnalytics.Event.PURCHASE));
                listener.onSuccess(jsonObject, message, NetPayUtil.transitionData(extensionInfo, FirebaseAnalytics.Event.PURCHASE), NetPayUtil.transitionData(extensionInfo, "isPaySource"));
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                SDKEventPost.postTrack(TrackEventKey.ON_RECEIVER_SERVER_PAY_RESULT, 0, NetPayUtil.transitionData(extensionInfo, FirebaseAnalytics.Event.PURCHASE));
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    /* access modifiers changed from: private */
    public static Object transitionData(ExtensionInfo extInfo, String key) {
        Map<String, Object> extMap;
        if (!(extInfo.getExtData() instanceof Map) || (extMap = (Map) extInfo.getExtData()) == null || extMap.size() <= 0 || !extMap.containsKey(key)) {
            return null;
        }
        return extMap.get(key);
    }

    public static void uniformOrderToGooglePay(int payType, HashMap<String, Object> payInfo, SkuDetails skuDetails, GameSDKListener listener) {
        payInfo.put("pay_type_id", Integer.valueOf(payType));
        double priceAmountMicros = (double) skuDetails.getPriceAmountMicros();
        String priceCurrencyCode = skuDetails.getPriceCurrencyCode();
        try {
            long price = BigDecimal.valueOf(priceAmountMicros).divide(new BigDecimal(1000000)).multiply(new BigDecimal(100)).longValue();
            SDKLoggerUtil.getLogger().mo19502e("获取价格：%s 币种：%s", Long.valueOf(price), priceCurrencyCode);
            payInfo.put("pay_price", Long.valueOf(price));
        } catch (Exception e) {
        }
        payInfo.put("pay_currency", priceCurrencyCode);
        uniformOrder(payInfo, skuDetails, listener);
    }

    public static void uniformOrder(HashMap<String, Object> payInfo, Object extData, final GameSDKListener listener) {
        String phoneNumber;
        FirebaseUser firebaseUser = GoogleLoginUtil.getInstance().getFirebaseUser();
        payInfo.put("device_id", ThinkFlyUtils.getDeviceID(ContextUtil.getInstance().getApplicationContext()));
        payInfo.put("imei_or_idfa", "");
        payInfo.put(ShareConstants.MEDIA_EXTENSION, "");
        payInfo.put("tz", TimeUtil.getTimeZone());
        if (firebaseUser != null) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("country_code", PhoneInfoUtils.getDeviceCountryCode(ContextUtil.getInstance().getApplicationContext()));
            hashMap.put("user_email", firebaseUser.getEmail());
            if (CheckUtil.isEmpty(firebaseUser.getPhoneNumber())) {
                phoneNumber = PhoneInfoUtils.getLine1Number(ContextUtil.getInstance().getApplicationContext());
            } else {
                phoneNumber = firebaseUser.getPhoneNumber();
            }
            hashMap.put("user_mdn", phoneNumber);
            payInfo.put(ShareConstants.MEDIA_EXTENSION, JsonUtil.toJson(hashMap));
        }
        String serverId = payInfo.get("game_server_id").toString();
        SDKEventPost.postTrack(TrackEventKey.ON_START_ORDERS, payInfo.get("game_player_id").toString(), serverId, (String) payInfo.get("product_id"));
        ExtensionInfo extensionInfo = new ExtensionInfo();
        extensionInfo.setExtData(extData);
        NetWorkManager.getInstance().doPostAsyncRequest(Urls.UNIFORM_ORDER, payInfo, extensionInfo, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                if (extensionInfo == null || extensionInfo.getExtData() == null) {
                    listener.onSuccess(jsonObject, message, new Object[0]);
                    return;
                }
                listener.onSuccess(jsonObject, message, extensionInfo.getExtData());
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
                SDKEventPost.postTrack(TrackEventKey.ON_ORDERS_RESULT, 0, "");
            }
        });
    }
}
