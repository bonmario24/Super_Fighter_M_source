package com.xhuyu.component.utils.appsflyer;

import android.content.Context;
import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AppsFlyerLib;
import com.thinkfly.star.utils.CheckUtils;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.util.HashMap;
import java.util.Map;

public class AppsFlyerTrackUtil {
    public static void trackStartLogin(Context context, String eventType) {
        try {
            AppsFlyerLib.getInstance().trackEvent(context, eventType, (Map<String, Object>) null);
        } catch (Exception e) {
        }
    }

    public static void trackLoginResult(Context context, String loginType) {
        try {
            AppsFlyerLib.getInstance().trackEvent(context, loginType, (Map<String, Object>) null);
        } catch (Exception e) {
        }
    }

    public static void trackOrderPayment(Context context, String payType) {
        try {
            AppsFlyerLib.getInstance().trackEvent(context, "hy_order_payment_" + payType, (Map<String, Object>) null);
        } catch (Exception e) {
        }
    }

    public static void trackStartPayment(Context context, String payType) {
        try {
            AppsFlyerLib.getInstance().trackEvent(context, "hy_start_payment_" + payType, (Map<String, Object>) null);
        } catch (Exception e) {
        }
    }

    public static void trackCompletePurchase(Context context, String googlePayOrderID, double price, String currency) {
        try {
            SDKLoggerUtil.getLogger().mo19502e("进行打点操作：googlePayID=" + googlePayOrderID + "--Price=" + price + "--currency=" + currency, new Object[0]);
            Map<String, Object> eventValue = new HashMap<>();
            eventValue.put(AFInAppEventParameterName.REVENUE, price + "");
            eventValue.put(AFInAppEventParameterName.CONTENT_TYPE, "category_a");
            if (CheckUtils.isNullOrEmpty(googlePayOrderID)) {
                googlePayOrderID = "";
            }
            eventValue.put(AFInAppEventParameterName.CONTENT_ID, googlePayOrderID);
            eventValue.put(AFInAppEventParameterName.CURRENCY, currency);
            AppsFlyerLib.getInstance().trackEvent(context, AFEventType.COMPLETE_PURCHASE, eventValue);
        } catch (Exception e) {
        }
    }

    public static void trackEvent(Context context, String eventName, Map<String, Object> eventValue) {
        try {
            AppsFlyerLib.getInstance().trackEvent(context, eventName, eventValue);
        } catch (Exception e) {
        }
    }
}
