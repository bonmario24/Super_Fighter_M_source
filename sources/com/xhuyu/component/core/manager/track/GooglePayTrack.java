package com.xhuyu.component.core.manager.track;

import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.star.libtrack.core.TrackCore;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.manager.TrackEventManager;
import com.xsdk.doraemon.event.SDKEventBus;
import com.xsdk.doraemon.event.Subscribe;
import com.xsdk.doraemon.utils.MD5Util;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GooglePayTrack {
    public void init() {
        SDKEventBus.getDefault().register(this);
    }

    @Subscribe(event = {8198})
    public void trackBillingInitialize(int result) {
        SDKLoggerUtil.getLogger().mo19502e("receiver--doTrackBillingInitialize result:%s", Integer.valueOf(result));
        Map<String, Object> ext = new HashMap<>();
        ext.put("result", Integer.valueOf(result));
        ext.put("page", "googlepay");
        TrackCore.getInstance().submitCustomEvent("gppay_initialize", ext);
    }

    @Subscribe(event = {8199})
    public void trackBillingQueryInventory(int result) {
        SDKLoggerUtil.getLogger().mo19502e("receiver--trackBillingQueryInventory result:%s", Integer.valueOf(result));
        Map<String, Object> ext = new HashMap<>();
        ext.put("result", Integer.valueOf(result));
        ext.put("page", "googlepay");
        TrackCore.getInstance().submitCustomEvent("inventory", ext);
    }

    @Subscribe(event = {8208})
    public void trackBillingQueryProductDetail(int result, String productID) {
        Map<String, Object> ext = new HashMap<>();
        ext.put("result", Integer.valueOf(result));
        ext.put("product_id", productID);
        ext.put("page", "googlepay");
        TrackCore.getInstance().submitCustomEvent("product_detail", ext);
    }

    @Subscribe(event = {8196})
    public void trackStartGooglePayment(SkuDetails skuDetails, String orderID) {
        try {
            TrackEventManager.getInstance().trackPayment(3, BigDecimal.valueOf((double) skuDetails.getPriceAmountMicros()).divide(new BigDecimal(1000000)).multiply(new BigDecimal(100)).longValue(), orderID);
        } catch (Exception e) {
        }
    }

    @Subscribe(event = {8200})
    public void trackBillingConsumeProduct(int result, Purchase purchase) {
        Map<String, Object> ext = new HashMap<>();
        ext.put("result", Integer.valueOf(result));
        ext.put("gp_order_id", (purchase == null || CheckUtils.isNullOrEmpty(purchase.getOrderId())) ? "" : purchase.getOrderId());
        ext.put("selfsdk_order_id", (purchase == null || CheckUtils.isNullOrEmpty(purchase.getDeveloperPayload())) ? "" : purchase.getDeveloperPayload());
        ext.put("product_id", (purchase == null || CheckUtils.isNullOrEmpty(purchase.getSku())) ? "" : purchase.getSku());
        ext.put("page", "googlepay");
        TrackCore.getInstance().submitCustomEvent("consume_product", ext);
    }

    @Subscribe(event = {8201})
    public void trackBillingPayResult(int result, Purchase purchase) {
        Map<String, Object> ext = new HashMap<>();
        ext.put("result", Integer.valueOf(result));
        ext.put("gp_order_id", (purchase == null || CheckUtils.isNullOrEmpty(purchase.getOrderId())) ? "" : purchase.getOrderId());
        ext.put("receipt_md5", (purchase == null || CheckUtils.isNullOrEmpty(purchase.getOriginalJson())) ? "" : MD5Util.getMD5(purchase.getOriginalJson()));
        ext.put("product_id", (purchase == null || CheckUtils.isNullOrEmpty(purchase.getSku())) ? "" : purchase.getSku());
        ext.put("page", "googlepay");
        TrackCore.getInstance().submitCustomEvent("gpay_callback", ext);
    }

    @Subscribe(event = {8214})
    public void trackServerPaymentResult(int result, Purchase purchase) {
        Map<String, Object> ext = new HashMap<>();
        ext.put("result", Integer.valueOf(result));
        ext.put("gp_order_id", (purchase == null || CheckUtils.isNullOrEmpty(purchase.getOrderId())) ? "" : purchase.getOrderId());
        ext.put("selfsdk_order_id", (purchase == null || CheckUtils.isNullOrEmpty(purchase.getDeveloperPayload())) ? "" : purchase.getDeveloperPayload());
        ext.put("product_id", (purchase == null || CheckUtils.isNullOrEmpty(purchase.getSku())) ? "" : purchase.getSku());
        ext.put("page", "googlepay");
        TrackCore.getInstance().submitCustomEvent("gp_pay_result", ext);
    }
}
