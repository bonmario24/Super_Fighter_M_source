package com.xhuyu.component.mvp.presenter.impl;

import android.app.Activity;
import android.content.Intent;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.core.config.TrackEventKey;
import com.xhuyu.component.core.manager.GoogleBillingManager;
import com.xhuyu.component.mvp.model.GoogleBillingConsumeBean;
import com.xhuyu.component.mvp.model.GoogleSkuDetails;
import com.xhuyu.component.mvp.model.PaymentResult;
import com.xhuyu.component.mvp.presenter.PayPresenter;
import com.xhuyu.component.mvp.view.PayView;
import com.xhuyu.component.network.NetPayUtil;
import com.xhuyu.component.utils.JsonUtil;
import com.xhuyu.component.utils.appsflyer.AppsFlyerTrackUtil;
import com.xhuyu.component.widget.LeakageOrderCompleteDialog;
import com.xhuyu.component.widget.TwoButtonsDialog;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

public class GoogleBillingPresenterImpl implements PayPresenter, GoogleBillingManager.BillingUpdatesListener, TwoButtonsDialog.OnButtonCallbackListener {
    /* access modifiers changed from: private */
    public Activity mActivity;
    /* access modifiers changed from: private */
    public GoogleBillingManager mBillingManager;
    private HashMap<String, Object> mPayInfo;
    /* access modifiers changed from: private */
    public int mPayType;
    private HashMap<String, GoogleSkuDetails> mPriceCache = new HashMap<>();
    /* access modifiers changed from: private */
    public String mProductID;
    /* access modifiers changed from: private */
    public PayView mView;
    private LeakageOrderCompleteDialog orderCompleteDialog;

    public GoogleBillingPresenterImpl(PayView view, int payType, String productID, HashMap<String, Object> payInfo) {
        this.mView = view;
        this.mPayInfo = payInfo;
        this.mProductID = productID;
        this.mPayType = payType;
    }

    public void doInitBilling(Activity activity) {
        this.mView.showDialog();
        this.mActivity = activity;
        this.orderCompleteDialog = new LeakageOrderCompleteDialog(activity, this);
        this.orderCompleteDialog.setCanceledOnTouchOutside(false);
        this.orderCompleteDialog.setCancelable(false);
        this.mBillingManager = new GoogleBillingManager(activity, this);
    }

    private void queryPurchases() {
        if (this.mBillingManager != null && this.mBillingManager.getBillingClientResponseCode() == 0) {
            this.mBillingManager.queryPurchases();
        }
    }

    public void onBillingInitInServiceConnectedFinished(int resultCode) {
        if (resultCode != 0) {
            SDKEventPost.postTrack(TrackEventKey.ON_BILLING_INITIALIZE, 0);
            this.mView.onPayFail("google_pay_init_fail", true, new Object[0]);
            return;
        }
        SDKEventPost.postTrack(TrackEventKey.ON_BILLING_INITIALIZE, 1);
    }

    public void onQueryPurchasesFinished(Purchase.PurchasesResult result) {
        if (result.getResponseCode() != 0) {
            SDKEventPost.postTrack(TrackEventKey.ON_BILLING_QUERY_INVENTORY, 0);
            this.mView.onPayFail("google_check_goods_fail", true, Integer.valueOf(result.getResponseCode()));
            return;
        }
        SDKEventPost.postTrack(TrackEventKey.ON_BILLING_QUERY_INVENTORY, 1);
        if (result.getBillingResult().getResponseCode() != 0 || (result.getPurchasesList() != null && result.getPurchasesList().size() > 0)) {
            doCompleteAnOrder(result.getBillingResult(), result.getPurchasesList(), false);
        } else {
            createProductInfo();
        }
    }

    public void onConsumeFinished(BillingResult billingResult, GoogleBillingConsumeBean consumeBean) {
        SDKLoggerUtil.getLogger().mo19501d("Consumption finished. Purchase DeveloperPayload: %s, result: %s", consumeBean.getPurchase().getDeveloperPayload(), Integer.valueOf(billingResult.getResponseCode()));
        if (billingResult.getResponseCode() == 0) {
            SDKLoggerUtil.getLogger().mo19504i("Consumption successful. Provisioning.", new Object[0]);
            SDKEventPost.postTrack(TrackEventKey.ON_BILLING_CONSUME_PRODUCT, 1, consumeBean.getPurchase());
        } else {
            SDKLoggerUtil.getLogger().mo19502e("Error while consuming: %1$s", Integer.valueOf(billingResult.getResponseCode()));
            if (consumeBean.getPurchase() != null) {
                SDKEventPost.postTrack(TrackEventKey.ON_BILLING_CONSUME_PRODUCT, 0, consumeBean.getPurchase());
            }
        }
        SDKLoggerUtil.getLogger().mo19504i("End consumption flow.", new Object[0]);
        if (!consumeBean.isNeedPullPay()) {
            this.mView.payFinish();
        } else if (consumeBean.getPurchase() != null) {
            showLeakageOrderDialog(consumeBean.getPurchase().getOrderId());
        } else {
            createProductInfo();
        }
    }

    public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> purchases) {
        doCompleteAnOrder(billingResult, purchases, true);
    }

    private void createProductInfo() {
        if (!CheckUtil.isEmpty(this.mProductID)) {
            List<String> skuList = new ArrayList<>();
            skuList.add(this.mProductID);
            querySkuDetailsAsync(BillingClient.SkuType.INAPP, skuList);
            return;
        }
        SDKLoggerUtil.getLogger().mo19502e("---product id is null ---", new Object[0]);
        this.mView.onPayFail("product id is null", false, new Object[0]);
    }

    private void querySkuDetailsAsync(final String billingType, List<String> skuList) {
        this.mBillingManager.querySkuDetailsAsync(billingType, skuList, new SkuDetailsResponseListener() {
            public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> skuDetailsList) {
                if (billingResult.getResponseCode() != 0) {
                    String errorMessage = String.format("Unsuccessful query for type: %s . Error code: %s", new Object[]{billingType, Integer.valueOf(billingResult.getResponseCode())});
                    SDKLoggerUtil.getLogger().mo19502e(errorMessage, new Object[0]);
                    SDKEventPost.postTrack(TrackEventKey.ON_BILLING_QUERY_PRODUCT_DETAIL, 0, GoogleBillingPresenterImpl.this.mProductID);
                    GoogleBillingPresenterImpl.this.mView.onPayFail(errorMessage, false, new Object[0]);
                } else if (skuDetailsList == null || skuDetailsList.size() <= 0) {
                    GoogleBillingPresenterImpl.this.mView.onPayFail("cannot find product", false, new Object[0]);
                } else {
                    GoogleBillingPresenterImpl.this.uniformOrder(skuDetailsList);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void uniformOrder(final List<SkuDetails> skuDetailsList) {
        SkuDetails skuDetails = skuDetailsList.get(0);
        try {
            this.mPriceCache.put(skuDetails.getSku(), new GoogleSkuDetails((double) BigDecimal.valueOf((double) skuDetails.getPriceAmountMicros()).divide(new BigDecimal(1000000)).longValue(), skuDetails.getPriceCurrencyCode()));
        } catch (Exception e) {
        }
        SDKEventPost.postTrack(TrackEventKey.ON_BILLING_QUERY_PRODUCT_DETAIL, 1, skuDetails.getSku());
        NetPayUtil.uniformOrderToGooglePay(this.mPayType, this.mPayInfo, skuDetails, new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                GoogleBillingPresenterImpl.this.mView.closeLoadingDialog();
                if (dataJson == null) {
                    GoogleBillingPresenterImpl.this.mView.onPayFail("pay_fail", true, new Object[0]);
                    return;
                }
                SDKLoggerUtil.getLogger().mo19501d("下单成功" + dataJson.toString(), new Object[0]);
                String orderId = JsonUtil.getString(dataJson, "order_id");
                AppsFlyerTrackUtil.trackOrderPayment(GoogleBillingPresenterImpl.this.mActivity, GoogleBillingPresenterImpl.this.mPayType + "");
                SDKEventPost.postTrack(TrackEventKey.ON_ORDERS_RESULT, 1, orderId);
                SkuDetails extSkuDetails = (SkuDetails) skuDetailsList.get(0);
                if (!(args == null || args[0] == null || !(args[0] instanceof SkuDetails))) {
                    extSkuDetails = args[0];
                }
                GoogleBillingPresenterImpl.this.doPayment(extSkuDetails, orderId);
            }

            public void onFail(String message, int code) {
                GoogleBillingPresenterImpl.this.mView.showToastMessage(false, message);
                GoogleBillingPresenterImpl.this.mView.onPayFail(message, false, new Object[0]);
            }
        });
    }

    /* access modifiers changed from: private */
    public void doPayment(SkuDetails skuDetails, String orderID) {
        if (this.mBillingManager != null && this.mBillingManager.getBillingClientResponseCode() > -1 && skuDetails != null) {
            SDKEventPost.postTrack(TrackEventKey.ON_START_PAYMENT, skuDetails, orderID);
            AppsFlyerTrackUtil.trackStartPayment(this.mActivity, this.mPayType + "");
            this.mBillingManager.initiatePurchaseFlow(skuDetails, orderID);
        }
    }

    private void doCompleteAnOrder(BillingResult billingResult, List<Purchase> purchases, boolean isPaySource) {
        if (isPaySource) {
            SDKLoggerUtil.getLogger().mo19504i("doCompleteAnOrder - from Pay  Callback ", new Object[0]);
        } else {
            SDKLoggerUtil.getLogger().mo19504i("doCompleteAnOrder - from query purchase  Callback ", new Object[0]);
        }
        if (billingResult.getResponseCode() == 0 && purchases != null) {
            for (Purchase purchase : purchases) {
                if (purchase.getPurchaseState() == 1) {
                    if (isPaySource) {
                        SDKEventPost.postTrack(TrackEventKey.ON_BILLING_PAY_RESULT, 1, purchase);
                    }
                    doVerifyPayData(purchase, isPaySource);
                }
            }
        } else if (billingResult.getResponseCode() == 1) {
            SDKLoggerUtil.getLogger().mo19502e("onPurchasesUpdated() - user cancelled the purchase flow - skipping", new Object[0]);
            SDKEventPost.postTrack(TrackEventKey.ON_BILLING_PAY_RESULT, 2, null);
            this.mView.onPayFail("pay_fail", true, " user cancelled");
        } else {
            SDKLoggerUtil.getLogger().mo19502e("onPurchasesUpdated() got unknown resultCode: " + billingResult.getResponseCode(), new Object[0]);
            SDKEventPost.postTrack(TrackEventKey.ON_BILLING_PAY_RESULT, 3, null);
            this.mView.onPayFail("pay_fail", true, "- got unknown resultCode: " + billingResult.getResponseCode());
        }
    }

    private void doVerifyPayData(Purchase purchase, boolean isPaySource) {
        GoogleSkuDetails googleSkuDetails;
        this.mView.showDialog();
        if (this.mPriceCache.containsKey(purchase.getSku()) && (googleSkuDetails = this.mPriceCache.get(purchase.getSku())) != null) {
            AppsFlyerTrackUtil.trackCompletePurchase(this.mActivity, purchase.getOrderId(), googleSkuDetails.getPrice(), googleSkuDetails.getCurrency());
            this.mPriceCache.remove(purchase.getSku());
        }
        NetPayUtil.doVerifyGooglePay(purchase, isPaySource, new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                GoogleBillingPresenterImpl.this.mView.closeLoadingDialog();
                boolean needPullPay = true;
                if (args != null && args[1] != null && (args[1] instanceof Boolean) && args[1].booleanValue()) {
                    needPullPay = false;
                    SDKEventPost.post(5, new PaymentResult(1, message));
                    GoogleBillingPresenterImpl.this.mView.showToastMessage(true, "pay_succes");
                }
                if (args != null && args[0] != null && (args[0] instanceof Purchase)) {
                    Purchase pse = args[0];
                    if (!pse.isAcknowledged()) {
                        SDKLoggerUtil.getLogger().mo19504i("We have gas. Consuming it.", new Object[0]);
                        GoogleBillingPresenterImpl.this.mBillingManager.consumeAsync(pse, needPullPay);
                    }
                }
            }

            public void onFail(String message, int code) {
                GoogleBillingPresenterImpl.this.mView.showToastMessage(false, message);
                GoogleBillingPresenterImpl.this.mView.onPayFail(message, false, new Object[0]);
            }
        });
    }

    public void onDestroy() {
        dismissLeakageOrderDialog();
        if (this.mBillingManager != null) {
            SDKLoggerUtil.getLogger().mo19504i("Destroying helper.", new Object[0]);
            this.mBillingManager.destroy();
        }
    }

    public void onPause() {
        dismissLeakageOrderDialog();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    private void showLeakageOrderDialog(String gpOrderID) {
        if (this.orderCompleteDialog != null && !this.orderCompleteDialog.isShowing()) {
            this.orderCompleteDialog.setMessage(gpOrderID);
            this.orderCompleteDialog.show();
        }
    }

    private void dismissLeakageOrderDialog() {
        if (this.orderCompleteDialog != null) {
            this.orderCompleteDialog.dismiss();
        }
    }

    public void onBtnOneCallback() {
        dismissLeakageOrderDialog();
        SDKEventPost.postTrack(TrackEventKey.ON_BILLING_PAY_RESULT, 2, null);
        this.mView.onPayFail("pay_fail", true, " user cancelled");
    }

    public void onBtnTwoCallback() {
        dismissLeakageOrderDialog();
        createProductInfo();
    }
}
