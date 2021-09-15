package com.xhuyu.component.core.manager;

import android.app.Activity;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.xhuyu.component.mvp.model.GoogleBillingConsumeBean;
import com.xhuyu.component.utils.HookUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.util.HashMap;
import java.util.List;

public class GoogleBillingManager implements PurchasesUpdatedListener {
    public static final int BILLING_MANAGER_NOT_INITIALIZED = -1;
    /* access modifiers changed from: private */
    public HashMap<String, GoogleBillingConsumeBean> cachePurchaseHashMap = new HashMap<>();
    /* access modifiers changed from: private */
    public final Activity mActivity;
    /* access modifiers changed from: private */
    public BillingClient mBillingClient;
    /* access modifiers changed from: private */
    public int mBillingClientResponseCode = -1;
    /* access modifiers changed from: private */
    public final BillingUpdatesListener mBillingUpdatesListener;
    /* access modifiers changed from: private */
    public boolean mIsServiceConnected;

    public interface BillingUpdatesListener {
        void onBillingInitInServiceConnectedFinished(int i);

        void onConsumeFinished(BillingResult billingResult, GoogleBillingConsumeBean googleBillingConsumeBean);

        void onPurchasesUpdated(BillingResult billingResult, List<Purchase> list);

        void onQueryPurchasesFinished(Purchase.PurchasesResult purchasesResult);
    }

    public GoogleBillingManager(Activity activity, BillingUpdatesListener updatesListener) {
        printDebugLog("Creating Billing client.", new Object[0]);
        this.mActivity = activity;
        this.mBillingUpdatesListener = updatesListener;
        this.mBillingClient = BillingClient.newBuilder(this.mActivity).setListener(this).enablePendingPurchases().build();
        printDebugLog("Starting setup.", new Object[0]);
        startServiceConnection(new Runnable() {
            public void run() {
                GoogleBillingManager.this.printDebugLog("Setup successful. Querying inventory.", new Object[0]);
                GoogleBillingManager.this.queryPurchases();
            }
        });
    }

    public void queryPurchases() {
        executeServiceRequest(new Runnable() {
            public void run() {
                long time = System.currentTimeMillis();
                Purchase.PurchasesResult purchasesResult = GoogleBillingManager.this.mBillingClient.queryPurchases(BillingClient.SkuType.INAPP);
                GoogleBillingManager.this.printDebugLog("Querying purchases elapsed time:%s ms", Long.valueOf(System.currentTimeMillis() - time));
                if (GoogleBillingManager.this.areSubscriptionsSupported()) {
                    Purchase.PurchasesResult subscriptionResult = GoogleBillingManager.this.mBillingClient.queryPurchases(BillingClient.SkuType.SUBS);
                    GoogleBillingManager.this.printDebugLog("Querying purchases and subscriptions elapsed time: %s ms", Long.valueOf(System.currentTimeMillis() - time));
                    GoogleBillingManager.this.printDebugLog("Querying subscriptions result code: %s Purchases size: %s", Integer.valueOf(subscriptionResult.getResponseCode()), Integer.valueOf(subscriptionResult.getPurchasesList().size()));
                    if (subscriptionResult.getResponseCode() == 0) {
                        purchasesResult.getPurchasesList().addAll(subscriptionResult.getPurchasesList());
                    } else {
                        GoogleBillingManager.this.printErrorLog("Got an error response trying to query subscription purchases", new Object[0]);
                    }
                } else if (purchasesResult.getResponseCode() == 0) {
                    GoogleBillingManager.this.printDebugLog("Skipped subscription purchases query since they are not supported", new Object[0]);
                } else {
                    GoogleBillingManager.this.printDebugLog("queryPurchasesAsync() got an error response code: " + purchasesResult.getResponseCode(), new Object[0]);
                }
                GoogleBillingManager.this.onQueryPurchasesFinished(purchasesResult);
            }
        });
    }

    /* access modifiers changed from: private */
    public void onQueryPurchasesFinished(Purchase.PurchasesResult result) {
        this.mBillingUpdatesListener.onQueryPurchasesFinished(result);
        if (this.mBillingClient == null || result.getResponseCode() != 0) {
            printErrorLog("Billing client was null or result code (%s)was bad - quitting", Integer.valueOf(result.getResponseCode()));
            return;
        }
        printDebugLog("Query inventory was successful.", new Object[0]);
    }

    public boolean areSubscriptionsSupported() {
        BillingResult featureSupported = this.mBillingClient.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS);
        if (featureSupported.getResponseCode() != 0) {
            printErrorLog("areSubscriptionsSupported() got an error response: " + featureSupported.getResponseCode(), new Object[0]);
        }
        if (featureSupported.getResponseCode() == 0) {
            return true;
        }
        return false;
    }

    public void startServiceConnection(final Runnable executeOnSuccess) {
        this.mBillingClient.startConnection(new BillingClientStateListener() {
            public void onBillingSetupFinished(BillingResult billingResult) {
                GoogleBillingManager.this.printDebugLog("Setup finished. Response code: " + billingResult.getResponseCode(), new Object[0]);
                if (billingResult.getResponseCode() == 0) {
                    boolean unused = GoogleBillingManager.this.mIsServiceConnected = true;
                    if (executeOnSuccess != null) {
                        executeOnSuccess.run();
                    }
                }
                int unused2 = GoogleBillingManager.this.mBillingClientResponseCode = billingResult.getResponseCode();
                if (GoogleBillingManager.this.mBillingUpdatesListener != null) {
                    GoogleBillingManager.this.mBillingUpdatesListener.onBillingInitInServiceConnectedFinished(GoogleBillingManager.this.mBillingClientResponseCode);
                }
            }

            public void onBillingServiceDisconnected() {
                boolean unused = GoogleBillingManager.this.mIsServiceConnected = false;
                if (GoogleBillingManager.this.mBillingUpdatesListener != null) {
                    GoogleBillingManager.this.mBillingUpdatesListener.onBillingInitInServiceConnectedFinished(-1);
                }
            }
        });
    }

    public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> purchases) {
        printDebugLog("onPurchasesUpdated code = %s ,  msg = %s", Integer.valueOf(billingResult.getResponseCode()), billingResult.getDebugMessage());
        this.mBillingUpdatesListener.onPurchasesUpdated(billingResult, purchases);
    }

    public void initiatePurchaseFlow(final SkuDetails skuDetails, final String developerPayload) {
        executeServiceRequest(new Runnable() {
            public void run() {
                HookUtil.doSetPayloadToBillingClient(GoogleBillingManager.this.mBillingClient, developerPayload);
                BillingFlowParams.Builder builder = BillingFlowParams.newBuilder();
                builder.setSkuDetails(skuDetails);
                GoogleBillingManager.this.mBillingClient.launchBillingFlow(GoogleBillingManager.this.mActivity, builder.build());
            }
        });
    }

    public void querySkuDetailsAsync(final String itemType, final List<String> skuList, final SkuDetailsResponseListener listener) {
        executeServiceRequest(new Runnable() {
            public void run() {
                SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                params.setSkusList(skuList).setType(itemType);
                GoogleBillingManager.this.mBillingClient.querySkuDetailsAsync(params.build(), new SkuDetailsResponseListener() {
                    public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
                        listener.onSkuDetailsResponse(billingResult, list);
                    }
                });
            }
        });
    }

    public void consumeAsync(final Purchase purchase, boolean needPullPay) {
        if (this.cachePurchaseHashMap.containsKey(purchase.getPurchaseToken())) {
            printErrorLog("Token was already scheduled to be consumed - skipping...", new Object[0]);
            return;
        }
        this.cachePurchaseHashMap.put(purchase.getPurchaseToken(), new GoogleBillingConsumeBean(purchase, needPullPay));
        final ConsumeResponseListener onConsumeListener = new ConsumeResponseListener() {
            public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                GoogleBillingManager.this.printErrorLog("onConsumeResponse code = %s  ,msg = %s ", Integer.valueOf(billingResult.getResponseCode()), billingResult.getDebugMessage());
                if (billingResult.getResponseCode() == 0) {
                }
                if (GoogleBillingManager.this.cachePurchaseHashMap.containsKey(purchaseToken)) {
                    GoogleBillingManager.this.mBillingUpdatesListener.onConsumeFinished(billingResult, (GoogleBillingConsumeBean) GoogleBillingManager.this.cachePurchaseHashMap.get(purchaseToken));
                    GoogleBillingManager.this.cachePurchaseHashMap.remove(purchaseToken);
                }
            }
        };
        executeServiceRequest(new Runnable() {
            public void run() {
                GoogleBillingManager.this.mBillingClient.consumeAsync(ConsumeParams.newBuilder().setDeveloperPayload(purchase.getDeveloperPayload()).setPurchaseToken(purchase.getPurchaseToken()).build(), onConsumeListener);
            }
        });
    }

    public int getBillingClientResponseCode() {
        return this.mBillingClientResponseCode;
    }

    private void executeServiceRequest(Runnable runnable) {
        if (this.mIsServiceConnected) {
            runnable.run();
        } else {
            startServiceConnection(runnable);
        }
    }

    public void destroy() {
        printDebugLog("Destroying the manager.", new Object[0]);
        if (this.mBillingClient != null && this.mBillingClient.isReady()) {
            this.mBillingClient.endConnection();
            this.mBillingClient = null;
        }
    }

    /* access modifiers changed from: private */
    public void printDebugLog(String message, Object... args) {
        SDKLoggerUtil.getLogger().mo19504i(message, args);
    }

    /* access modifiers changed from: private */
    public void printErrorLog(String message, Object... args) {
        SDKLoggerUtil.getLogger().mo19504i(message, args);
    }
}
