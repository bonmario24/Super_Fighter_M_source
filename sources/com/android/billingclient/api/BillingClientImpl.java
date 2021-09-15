package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.android.billingclient.BuildConfig;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.util.BillingHelper;
import com.android.vending.billing.IInAppBillingService;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;

class BillingClientImpl extends BillingClient {
    private static final long ASYNCHRONOUS_TIMEOUT_IN_MILLISECONDS = 30000;
    private static final String GET_SKU_DETAILS_ITEM_LIST = "ITEM_ID_LIST";
    private static final int MAX_IAP_VERSION = 10;
    private static final int MAX_SKU_DETAILS_ITEMS_PER_REQUEST = 20;
    private static final int MIN_IAP_VERSION = 3;
    private static final long SYNCHRONOUS_TIMEOUT_IN_MILLISECONDS = 5000;
    private static final String TAG = "BillingClient";
    /* access modifiers changed from: private */
    public final Context mApplicationContext;
    /* access modifiers changed from: private */
    public final BillingBroadcastManager mBroadcastManager;
    /* access modifiers changed from: private */
    public final int mChildDirected;
    /* access modifiers changed from: private */
    public int mClientState;
    private final boolean mEnablePendingPurchases;
    private ExecutorService mExecutorService;
    /* access modifiers changed from: private */
    public boolean mIABv10Supported;
    /* access modifiers changed from: private */
    public boolean mIABv6Supported;
    /* access modifiers changed from: private */
    public boolean mIABv8Supported;
    /* access modifiers changed from: private */
    public boolean mIABv9Supported;
    /* access modifiers changed from: private */
    public final String mQualifiedVersionNumber;
    /* access modifiers changed from: private */
    public IInAppBillingService mService;
    private BillingServiceConnection mServiceConnection;
    /* access modifiers changed from: private */
    public boolean mSubscriptionUpdateSupported;
    /* access modifiers changed from: private */
    public boolean mSubscriptionsSupported;
    private final Handler mUiThreadHandler;
    /* access modifiers changed from: private */
    public final int mUnderAgeOfConsent;
    private final ResultReceiver onPurchaseFinishedReceiver;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ClientState {
        public static final int CLOSED = 3;
        public static final int CONNECTED = 2;
        public static final int CONNECTING = 1;
        public static final int DISCONNECTED = 0;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setExecutorService(ExecutorService executorService) {
        this.mExecutorService = executorService;
    }

    @UiThread
    BillingClientImpl(@NonNull Context context, int childDirected, int underAgeOfConsent, boolean enablePendingPurchases, @NonNull PurchasesUpdatedListener listener) {
        this(context, childDirected, underAgeOfConsent, enablePendingPurchases, listener, BuildConfig.VERSION_NAME);
    }

    private BillingClientImpl(Activity activity, int childDirected, int underAgeOfConsent, boolean enablePendingPurchases, String versionOverride) {
        this(activity.getApplicationContext(), childDirected, underAgeOfConsent, enablePendingPurchases, new BillingClientNativeCallback(), versionOverride);
    }

    private BillingClientImpl(@NonNull Context context, int childDirected, int underAgeOfConsent, boolean enablePendingPurchases, @NonNull PurchasesUpdatedListener listener, String versionNumber) {
        this.mClientState = 0;
        this.mUiThreadHandler = new Handler(Looper.getMainLooper());
        this.onPurchaseFinishedReceiver = new ResultReceiver(this.mUiThreadHandler) {
            public void onReceiveResult(int resultCode, Bundle resultData) {
                PurchasesUpdatedListener purchasesUpdatedListener = BillingClientImpl.this.mBroadcastManager.getListener();
                if (purchasesUpdatedListener == null) {
                    BillingHelper.logWarn(BillingClientImpl.TAG, "PurchasesUpdatedListener is null - no way to return the response.");
                    return;
                }
                purchasesUpdatedListener.onPurchasesUpdated(BillingResult.newBuilder().setResponseCode(resultCode).setDebugMessage(BillingHelper.getDebugMessageFromBundle(resultData, BillingClientImpl.TAG)).build(), BillingHelper.extractPurchases(resultData));
            }
        };
        this.mApplicationContext = context.getApplicationContext();
        this.mChildDirected = childDirected;
        this.mUnderAgeOfConsent = underAgeOfConsent;
        this.mEnablePendingPurchases = enablePendingPurchases;
        this.mBroadcastManager = new BillingBroadcastManager(this.mApplicationContext, listener);
        this.mQualifiedVersionNumber = versionNumber;
    }

    public BillingResult isFeatureSupported(String feature) {
        if (!isReady()) {
            return BillingResults.SERVICE_DISCONNECTED;
        }
        char c = 65535;
        switch (feature.hashCode()) {
            case -422092961:
                if (feature.equals(BillingClient.FeatureType.SUBSCRIPTIONS_UPDATE)) {
                    c = 1;
                    break;
                }
                break;
            case 207616302:
                if (feature.equals(BillingClient.FeatureType.PRICE_CHANGE_CONFIRMATION)) {
                    c = 4;
                    break;
                }
                break;
            case 292218239:
                if (feature.equals(BillingClient.FeatureType.IN_APP_ITEMS_ON_VR)) {
                    c = 2;
                    break;
                }
                break;
            case 1219490065:
                if (feature.equals(BillingClient.FeatureType.SUBSCRIPTIONS_ON_VR)) {
                    c = 3;
                    break;
                }
                break;
            case 1987365622:
                if (feature.equals(BillingClient.FeatureType.SUBSCRIPTIONS)) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return this.mSubscriptionsSupported ? BillingResults.f71OK : BillingResults.FEATURE_NOT_SUPPORTED;
            case 1:
                return this.mSubscriptionUpdateSupported ? BillingResults.f71OK : BillingResults.FEATURE_NOT_SUPPORTED;
            case 2:
                return isBillingSupportedOnVr(BillingClient.SkuType.INAPP);
            case 3:
                return isBillingSupportedOnVr(BillingClient.SkuType.SUBS);
            case 4:
                return this.mIABv8Supported ? BillingResults.f71OK : BillingResults.FEATURE_NOT_SUPPORTED;
            default:
                BillingHelper.logWarn(TAG, "Unsupported feature: " + feature);
                return BillingResults.UNKNOWN_FEATURE;
        }
    }

    public boolean isReady() {
        return (this.mClientState != 2 || this.mService == null || this.mServiceConnection == null) ? false : true;
    }

    public void startConnection(@NonNull BillingClientStateListener listener) {
        if (isReady()) {
            BillingHelper.logVerbose(TAG, "Service connection is valid. No need to re-initialize.");
            listener.onBillingSetupFinished(BillingResults.f71OK);
        } else if (this.mClientState == 1) {
            BillingHelper.logWarn(TAG, "Client is already in the process of connecting to billing service.");
            listener.onBillingSetupFinished(BillingResults.CLIENT_CONNECTING);
        } else if (this.mClientState == 3) {
            BillingHelper.logWarn(TAG, "Client was already closed and can't be reused. Please create another instance.");
            listener.onBillingSetupFinished(BillingResults.SERVICE_DISCONNECTED);
        } else {
            this.mClientState = 1;
            this.mBroadcastManager.registerReceiver();
            BillingHelper.logVerbose(TAG, "Starting in-app billing setup.");
            this.mServiceConnection = new BillingServiceConnection(listener);
            Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            serviceIntent.setPackage("com.android.vending");
            List<ResolveInfo> intentServices = this.mApplicationContext.getPackageManager().queryIntentServices(serviceIntent, 0);
            if (intentServices != null && !intentServices.isEmpty()) {
                ResolveInfo resolveInfo = intentServices.get(0);
                if (resolveInfo.serviceInfo != null) {
                    String packageName = resolveInfo.serviceInfo.packageName;
                    String className = resolveInfo.serviceInfo.name;
                    if (!"com.android.vending".equals(packageName) || className == null) {
                        BillingHelper.logWarn(TAG, "The device doesn't have valid Play Store.");
                    } else {
                        ComponentName component = new ComponentName(packageName, className);
                        Intent explicitServiceIntent = new Intent(serviceIntent);
                        explicitServiceIntent.setComponent(component);
                        explicitServiceIntent.putExtra(BillingHelper.LIBRARY_VERSION_KEY, this.mQualifiedVersionNumber);
                        if (this.mApplicationContext.bindService(explicitServiceIntent, this.mServiceConnection, 1)) {
                            BillingHelper.logVerbose(TAG, "Service was bonded successfully.");
                            return;
                        }
                        BillingHelper.logWarn(TAG, "Connection to Billing service is blocked.");
                    }
                }
            }
            this.mClientState = 0;
            BillingHelper.logVerbose(TAG, "Billing service unavailable on device.");
            listener.onBillingSetupFinished(BillingResults.BILLING_UNAVAILABLE);
        }
    }

    private void startConnection(long futureHandle) {
        startConnection((BillingClientStateListener) new BillingClientNativeCallback(futureHandle));
    }

    public void endConnection() {
        try {
            this.mBroadcastManager.destroy();
            if (this.mServiceConnection != null) {
                this.mServiceConnection.markDisconnectedAndCleanUp();
            }
            if (!(this.mServiceConnection == null || this.mService == null)) {
                BillingHelper.logVerbose(TAG, "Unbinding from service.");
                this.mApplicationContext.unbindService(this.mServiceConnection);
                this.mServiceConnection = null;
            }
            this.mService = null;
            if (this.mExecutorService != null) {
                this.mExecutorService.shutdownNow();
                this.mExecutorService = null;
            }
        } catch (Exception ex) {
            BillingHelper.logWarn(TAG, "There was an exception while ending connection: " + ex);
        } finally {
            this.mClientState = 3;
        }
    }

    private void launchPriceChangeConfirmationFlow(Activity activity, PriceChangeFlowParams priceChangeFlowParams, long futureHandle) {
        launchPriceChangeConfirmationFlow(activity, priceChangeFlowParams, (PriceChangeConfirmationListener) new BillingClientNativeCallback(futureHandle));
    }

    public void launchPriceChangeConfirmationFlow(Activity activity, PriceChangeFlowParams priceChangeFlowParams, @NonNull PriceChangeConfirmationListener listener) {
        if (!isReady()) {
            listener.onPriceChangeConfirmationResult(BillingResults.SERVICE_DISCONNECTED);
        } else if (priceChangeFlowParams == null || priceChangeFlowParams.getSkuDetails() == null) {
            BillingHelper.logWarn(TAG, "Please fix the input params. priceChangeFlowParams must contain valid sku.");
            listener.onPriceChangeConfirmationResult(BillingResults.NULL_SKU);
        } else {
            String sku = priceChangeFlowParams.getSkuDetails().getSku();
            if (sku == null) {
                BillingHelper.logWarn(TAG, "Please fix the input params. priceChangeFlowParams must contain valid sku.");
                listener.onPriceChangeConfirmationResult(BillingResults.NULL_SKU);
            } else if (!this.mIABv8Supported) {
                BillingHelper.logWarn(TAG, "Current client doesn't support price change confirmation flow.");
                listener.onPriceChangeConfirmationResult(BillingResults.FEATURE_NOT_SUPPORTED);
            } else {
                Bundle extraParams = new Bundle();
                extraParams.putString(BillingHelper.LIBRARY_VERSION_KEY, this.mQualifiedVersionNumber);
                extraParams.putBoolean(BillingHelper.EXTRA_PARAM_KEY_SUBS_PRICE_CHANGE, true);
                final Bundle extraParamsFinal = extraParams;
                final String str = sku;
                try {
                    Bundle priceChangeIntentBundle = executeAsync(new Callable<Bundle>() {
                        public Bundle call() throws Exception {
                            return BillingClientImpl.this.mService.getSubscriptionManagementIntent(8, BillingClientImpl.this.mApplicationContext.getPackageName(), str, BillingClient.SkuType.SUBS, extraParamsFinal);
                        }
                    }, SYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, (Runnable) null).get(SYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS);
                    int responseCode = BillingHelper.getResponseCodeFromBundle(priceChangeIntentBundle, TAG);
                    BillingResult billingResult = BillingResult.newBuilder().setResponseCode(responseCode).setDebugMessage(BillingHelper.getDebugMessageFromBundle(priceChangeIntentBundle, TAG)).build();
                    if (responseCode != 0) {
                        BillingHelper.logWarn(TAG, "Unable to launch price change flow, error response code: " + responseCode);
                        listener.onPriceChangeConfirmationResult(billingResult);
                        return;
                    }
                    final PriceChangeConfirmationListener priceChangeConfirmationListener = listener;
                    ResultReceiver onPriceChangeConfirmationReceiver = new ResultReceiver(this.mUiThreadHandler) {
                        public void onReceiveResult(int resultCode, Bundle resultData) {
                            priceChangeConfirmationListener.onPriceChangeConfirmationResult(BillingResult.newBuilder().setResponseCode(resultCode).setDebugMessage(BillingHelper.getDebugMessageFromBundle(resultData, BillingClientImpl.TAG)).build());
                        }
                    };
                    Intent intent = new Intent(activity, ProxyBillingActivity.class);
                    intent.putExtra(BillingHelper.RESPONSE_SUBS_MANAGEMENT_INTENT_KEY, (PendingIntent) priceChangeIntentBundle.getParcelable(BillingHelper.RESPONSE_SUBS_MANAGEMENT_INTENT_KEY));
                    intent.putExtra("result_receiver", onPriceChangeConfirmationReceiver);
                    activity.startActivity(intent);
                } catch (CancellationException | TimeoutException e) {
                    BillingHelper.logWarn(TAG, "Time out while launching Price Change Flow for sku: " + sku + "; try to reconnect");
                    listener.onPriceChangeConfirmationResult(BillingResults.SERVICE_TIMEOUT);
                } catch (Exception e2) {
                    BillingHelper.logWarn(TAG, "Exception caught while launching Price Change Flow for sku: " + sku + "; try to reconnect");
                    listener.onPriceChangeConfirmationResult(BillingResults.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    public BillingResult launchBillingFlow(Activity activity, BillingFlowParams params) {
        Future<Bundle> futureBuyIntentBundle;
        if (!isReady()) {
            return broadcastFailureAndReturnBillingResponse(BillingResults.SERVICE_DISCONNECTED);
        }
        final String skuType = params.getSkuType();
        final String newSku = params.getSku();
        SkuDetails skuDetails = params.getSkuDetails();
        boolean rewardedSku = skuDetails != null && skuDetails.isRewarded();
        if (newSku == null) {
            BillingHelper.logWarn(TAG, "Please fix the input params. SKU can't be null.");
            return broadcastFailureAndReturnBillingResponse(BillingResults.NULL_SKU);
        } else if (skuType == null) {
            BillingHelper.logWarn(TAG, "Please fix the input params. SkuType can't be null.");
            return broadcastFailureAndReturnBillingResponse(BillingResults.NULL_SKU_TYPE);
        } else if (!skuType.equals(BillingClient.SkuType.SUBS) || this.mSubscriptionsSupported) {
            boolean isSubscriptionUpdate = params.getOldSku() != null;
            if (isSubscriptionUpdate && !this.mSubscriptionUpdateSupported) {
                BillingHelper.logWarn(TAG, "Current client doesn't support subscriptions update.");
                return broadcastFailureAndReturnBillingResponse(BillingResults.SUBSCRIPTIONS_UPDATE_NOT_SUPPORTED);
            } else if (params.hasExtraParams() && !this.mIABv6Supported) {
                BillingHelper.logWarn(TAG, "Current client doesn't support extra params for buy intent.");
                return broadcastFailureAndReturnBillingResponse(BillingResults.EXTRA_PARAMS_NOT_SUPPORTED);
            } else if (!rewardedSku || this.mIABv6Supported) {
                BillingHelper.logVerbose(TAG, "Constructing buy intent for " + newSku + ", item type: " + skuType);
                if (this.mIABv6Supported) {
                    Bundle extraParams = BillingHelper.constructExtraParamsForLaunchBillingFlow(params, this.mIABv9Supported, this.mEnablePendingPurchases, this.mQualifiedVersionNumber);
                    if (!skuDetails.getSkuDetailsToken().isEmpty()) {
                        extraParams.putString(BillingHelper.EXTRA_PARAM_KEY_SKU_DETAILS_TOKEN, skuDetails.getSkuDetailsToken());
                    }
                    if (rewardedSku) {
                        extraParams.putString(BillingFlowParams.EXTRA_PARAM_KEY_RSKU, skuDetails.rewardToken());
                        if (this.mChildDirected != 0) {
                            extraParams.putInt(BillingFlowParams.EXTRA_PARAM_CHILD_DIRECTED, this.mChildDirected);
                        }
                        if (this.mUnderAgeOfConsent != 0) {
                            extraParams.putInt(BillingFlowParams.EXTRA_PARAM_UNDER_AGE_OF_CONSENT, this.mUnderAgeOfConsent);
                        }
                    }
                    final Bundle extraParamsFinal = extraParams;
                    int apiVersion = 6;
                    if (this.mIABv9Supported) {
                        apiVersion = 9;
                    } else if (params.getVrPurchaseFlow()) {
                        apiVersion = 7;
                    }
                    final int finalApiVersion = apiVersion;
                    futureBuyIntentBundle = executeAsync(new Callable<Bundle>() {
                        public Bundle call() throws Exception {
                            return BillingClientImpl.this.mService.getBuyIntentExtraParams(finalApiVersion, BillingClientImpl.this.mApplicationContext.getPackageName(), newSku, skuType, (String) null, extraParamsFinal);
                        }
                    }, SYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, (Runnable) null);
                } else if (isSubscriptionUpdate) {
                    final BillingFlowParams billingFlowParams = params;
                    futureBuyIntentBundle = executeAsync(new Callable<Bundle>() {
                        public Bundle call() throws Exception {
                            return BillingClientImpl.this.mService.getBuyIntentToReplaceSkus(5, BillingClientImpl.this.mApplicationContext.getPackageName(), Arrays.asList(new String[]{billingFlowParams.getOldSku()}), newSku, BillingClient.SkuType.SUBS, (String) null);
                        }
                    }, SYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, (Runnable) null);
                } else {
                    futureBuyIntentBundle = executeAsync(new Callable<Bundle>() {
                        public Bundle call() throws Exception {
                            return BillingClientImpl.this.mService.getBuyIntent(3, BillingClientImpl.this.mApplicationContext.getPackageName(), newSku, skuType, (String) null);
                        }
                    }, SYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, (Runnable) null);
                }
                try {
                    Bundle buyIntentBundle = futureBuyIntentBundle.get(SYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS);
                    int responseCode = BillingHelper.getResponseCodeFromBundle(buyIntentBundle, TAG);
                    String debugMessage = BillingHelper.getDebugMessageFromBundle(buyIntentBundle, TAG);
                    if (responseCode != 0) {
                        BillingHelper.logWarn(TAG, "Unable to buy item, Error response code: " + responseCode);
                        return broadcastFailureAndReturnBillingResponse(BillingResult.newBuilder().setResponseCode(responseCode).setDebugMessage(debugMessage).build());
                    }
                    Intent intent = new Intent(activity, ProxyBillingActivity.class);
                    intent.putExtra("result_receiver", this.onPurchaseFinishedReceiver);
                    intent.putExtra(BillingHelper.RESPONSE_BUY_INTENT_KEY, (PendingIntent) buyIntentBundle.getParcelable(BillingHelper.RESPONSE_BUY_INTENT_KEY));
                    activity.startActivity(intent);
                    return BillingResults.f71OK;
                } catch (CancellationException | TimeoutException e) {
                    BillingHelper.logWarn(TAG, "Time out while launching billing flow: ; for sku: " + newSku + "; try to reconnect");
                    return broadcastFailureAndReturnBillingResponse(BillingResults.SERVICE_TIMEOUT);
                } catch (Exception e2) {
                    BillingHelper.logWarn(TAG, "Exception while launching billing flow: ; for sku: " + newSku + "; try to reconnect");
                    return broadcastFailureAndReturnBillingResponse(BillingResults.SERVICE_DISCONNECTED);
                }
            } else {
                BillingHelper.logWarn(TAG, "Current client doesn't support extra params for buy intent.");
                return broadcastFailureAndReturnBillingResponse(BillingResults.EXTRA_PARAMS_NOT_SUPPORTED);
            }
        } else {
            BillingHelper.logWarn(TAG, "Current client doesn't support subscriptions.");
            return broadcastFailureAndReturnBillingResponse(BillingResults.SUBSCRIPTIONS_NOT_SUPPORTED);
        }
    }

    private BillingResult broadcastFailureAndReturnBillingResponse(BillingResult billingResult) {
        this.mBroadcastManager.getListener().onPurchasesUpdated(billingResult, (List<Purchase>) null);
        return billingResult;
    }

    private int launchBillingFlowCpp(Activity activity, BillingFlowParams params) {
        return launchBillingFlow(activity, params).getResponseCode();
    }

    public Purchase.PurchasesResult queryPurchases(final String skuType) {
        if (!isReady()) {
            return new Purchase.PurchasesResult(BillingResults.SERVICE_DISCONNECTED, (List<Purchase>) null);
        }
        if (TextUtils.isEmpty(skuType)) {
            BillingHelper.logWarn(TAG, "Please provide a valid SKU type.");
            return new Purchase.PurchasesResult(BillingResults.EMPTY_SKU_TYPE, (List<Purchase>) null);
        }
        try {
            return executeAsync(new Callable<Purchase.PurchasesResult>() {
                public Purchase.PurchasesResult call() throws Exception {
                    return BillingClientImpl.this.queryPurchasesInternal(skuType);
                }
            }, SYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, (Runnable) null).get(SYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS);
        } catch (CancellationException | TimeoutException e) {
            return new Purchase.PurchasesResult(BillingResults.SERVICE_TIMEOUT, (List<Purchase>) null);
        } catch (Exception e2) {
            return new Purchase.PurchasesResult(BillingResults.INTERNAL_ERROR, (List<Purchase>) null);
        }
    }

    private void queryPurchases(final String skuType, long futureHandle) {
        final BillingClientNativeCallback callback = new BillingClientNativeCallback(futureHandle);
        if (!isReady()) {
            callback.onQueryPurchasesResponse(BillingResults.SERVICE_DISCONNECTED, (List<Purchase>) null);
        }
        if (executeAsync(new Callable<Void>() {
            public Void call() {
                final Purchase.PurchasesResult result = BillingClientImpl.this.queryPurchasesInternal(skuType);
                BillingClientImpl.this.postToUiThread(new Runnable() {
                    public void run() {
                        callback.onQueryPurchasesResponse(result.getBillingResult(), result.getPurchasesList());
                    }
                });
                return null;
            }
        }, ASYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, new Runnable() {
            public void run() {
                callback.onQueryPurchasesResponse(BillingResults.SERVICE_TIMEOUT, (List<Purchase>) null);
            }
        }) == null) {
            callback.onQueryPurchasesResponse(getBillingResultForNullFutureResult(), (List<Purchase>) null);
        }
    }

    public void querySkuDetailsAsync(SkuDetailsParams params, final SkuDetailsResponseListener listener) {
        if (!isReady()) {
            listener.onSkuDetailsResponse(BillingResults.SERVICE_DISCONNECTED, (List<SkuDetails>) null);
            return;
        }
        final String skuType = params.getSkuType();
        final List<String> skusList = params.getSkusList();
        if (TextUtils.isEmpty(skuType)) {
            BillingHelper.logWarn(TAG, "Please fix the input params. SKU type can't be empty.");
            listener.onSkuDetailsResponse(BillingResults.EMPTY_SKU_TYPE, (List<SkuDetails>) null);
        } else if (skusList == null) {
            BillingHelper.logWarn(TAG, "Please fix the input params. The list of SKUs can't be empty.");
            listener.onSkuDetailsResponse(BillingResults.EMPTY_SKU_LIST, (List<SkuDetails>) null);
        } else if (executeAsync(new Callable<Void>() {
            public Void call() {
                final SkuDetails.SkuDetailsResult result = BillingClientImpl.this.querySkuDetailsInternal(skuType, skusList);
                BillingClientImpl.this.postToUiThread(new Runnable() {
                    public void run() {
                        listener.onSkuDetailsResponse(BillingResult.newBuilder().setResponseCode(result.getResponseCode()).setDebugMessage(result.getDebugMessage()).build(), result.getSkuDetailsList());
                    }
                });
                return null;
            }
        }, ASYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, new Runnable() {
            public void run() {
                listener.onSkuDetailsResponse(BillingResults.SERVICE_TIMEOUT, (List<SkuDetails>) null);
            }
        }) == null) {
            listener.onSkuDetailsResponse(getBillingResultForNullFutureResult(), (List<SkuDetails>) null);
        }
    }

    private void querySkuDetailsAsync(String skuType, String[] skusList, long futureHandle) {
        querySkuDetailsAsync(SkuDetailsParams.newBuilder().setType(skuType).setSkusList(Arrays.asList(skusList)).build(), new BillingClientNativeCallback(futureHandle));
    }

    public void consumeAsync(final ConsumeParams consumeParams, final ConsumeResponseListener listener) {
        if (!isReady()) {
            listener.onConsumeResponse(BillingResults.SERVICE_DISCONNECTED, (String) null);
        } else if (executeAsync(new Callable<Void>() {
            public Void call() {
                BillingClientImpl.this.consumeInternal(consumeParams, listener);
                return null;
            }
        }, ASYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, new Runnable() {
            public void run() {
                listener.onConsumeResponse(BillingResults.SERVICE_TIMEOUT, (String) null);
            }
        }) == null) {
            listener.onConsumeResponse(getBillingResultForNullFutureResult(), (String) null);
        }
    }

    private void consumeAsync(ConsumeParams consumeParams, long futureHandle) {
        consumeAsync(consumeParams, (ConsumeResponseListener) new BillingClientNativeCallback(futureHandle));
    }

    public void queryPurchaseHistoryAsync(final String skuType, final PurchaseHistoryResponseListener listener) {
        if (!isReady()) {
            listener.onPurchaseHistoryResponse(BillingResults.SERVICE_DISCONNECTED, (List<PurchaseHistoryRecord>) null);
        } else if (executeAsync(new Callable<Void>() {
            public Void call() {
                final PurchaseHistoryResult result = BillingClientImpl.this.queryPurchaseHistoryInternal(skuType);
                BillingClientImpl.this.postToUiThread(new Runnable() {
                    public void run() {
                        listener.onPurchaseHistoryResponse(result.getBillingResult(), result.getPurchaseHistoryRecordList());
                    }
                });
                return null;
            }
        }, ASYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, new Runnable() {
            public void run() {
                listener.onPurchaseHistoryResponse(BillingResults.SERVICE_TIMEOUT, (List<PurchaseHistoryRecord>) null);
            }
        }) == null) {
            listener.onPurchaseHistoryResponse(getBillingResultForNullFutureResult(), (List<PurchaseHistoryRecord>) null);
        }
    }

    /* access modifiers changed from: private */
    public PurchaseHistoryResult queryPurchaseHistoryInternal(String skuType) {
        BillingHelper.logVerbose(TAG, "Querying purchase history, item type: " + skuType);
        String continueToken = null;
        List<PurchaseHistoryRecord> resultList = new ArrayList<>();
        Bundle extraParams = BillingHelper.constructExtraParamsForQueryPurchases(this.mIABv9Supported, this.mEnablePendingPurchases, this.mQualifiedVersionNumber);
        do {
            try {
                if (!this.mIABv6Supported) {
                    BillingHelper.logWarn(TAG, "getPurchaseHistory is not supported on current device");
                    return new PurchaseHistoryResult(BillingResults.GET_PURCHASE_HISTORY_NOT_SUPPORTED, (List<PurchaseHistoryRecord>) null);
                }
                Bundle purchasedItems = this.mService.getPurchaseHistory(6, this.mApplicationContext.getPackageName(), skuType, continueToken, extraParams);
                BillingResult billingResult = PurchaseApiResponseChecker.checkPurchasesBundleValidity(purchasedItems, TAG, "getPurchaseHistory()");
                if (billingResult != BillingResults.f71OK) {
                    return new PurchaseHistoryResult(billingResult, (List<PurchaseHistoryRecord>) null);
                }
                ArrayList<String> ownedSkus = purchasedItems.getStringArrayList(BillingHelper.RESPONSE_INAPP_ITEM_LIST);
                ArrayList<String> purchaseDataList = purchasedItems.getStringArrayList(BillingHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST);
                ArrayList<String> signatureList = purchasedItems.getStringArrayList(BillingHelper.RESPONSE_INAPP_SIGNATURE_LIST);
                int i = 0;
                while (i < purchaseDataList.size()) {
                    String purchaseData = purchaseDataList.get(i);
                    String signature = signatureList.get(i);
                    BillingHelper.logVerbose(TAG, "Purchase record found for sku : " + ownedSkus.get(i));
                    try {
                        PurchaseHistoryRecord purchaseHistoryRecord = new PurchaseHistoryRecord(purchaseData, signature);
                        if (TextUtils.isEmpty(purchaseHistoryRecord.getPurchaseToken())) {
                            BillingHelper.logWarn(TAG, "BUG: empty/null token!");
                        }
                        resultList.add(purchaseHistoryRecord);
                        i++;
                    } catch (JSONException e) {
                        BillingHelper.logWarn(TAG, "Got an exception trying to decode the purchase: " + e);
                        return new PurchaseHistoryResult(BillingResults.INTERNAL_ERROR, (List<PurchaseHistoryRecord>) null);
                    }
                }
                continueToken = purchasedItems.getString(BillingHelper.INAPP_CONTINUATION_TOKEN);
                BillingHelper.logVerbose(TAG, "Continuation token: " + continueToken);
            } catch (RemoteException e2) {
                BillingHelper.logWarn(TAG, "Got exception trying to get purchase history: " + e2 + "; try to reconnect");
                return new PurchaseHistoryResult(BillingResults.SERVICE_DISCONNECTED, (List<PurchaseHistoryRecord>) null);
            }
        } while (!TextUtils.isEmpty(continueToken));
        return new PurchaseHistoryResult(BillingResults.f71OK, resultList);
    }

    private void queryPurchaseHistoryAsync(@NonNull String skuType, long futureHandle) {
        queryPurchaseHistoryAsync(skuType, (PurchaseHistoryResponseListener) new BillingClientNativeCallback(futureHandle));
    }

    public void loadRewardedSku(final RewardLoadParams params, final RewardResponseListener listener) {
        if (!this.mIABv6Supported) {
            listener.onRewardResponse(BillingResults.ITEM_UNAVAILABLE);
        } else if (executeAsync(new Callable<Void>() {
            public Void call() {
                try {
                    Bundle buyIntentBundle = BillingClientImpl.this.mService.getBuyIntentExtraParams(6, BillingClientImpl.this.mApplicationContext.getPackageName(), params.getSkuDetails().getSku(), params.getSkuDetails().getType(), (String) null, BillingHelper.constructExtraParamsForLoadRewardedSku(params.getSkuDetails().rewardToken(), BillingClientImpl.this.mChildDirected, BillingClientImpl.this.mUnderAgeOfConsent, BillingClientImpl.this.mQualifiedVersionNumber));
                    final BillingResult billingResult = BillingResult.newBuilder().setResponseCode(BillingHelper.getResponseCodeFromBundle(buyIntentBundle, BillingClientImpl.TAG)).setDebugMessage(BillingHelper.getDebugMessageFromBundle(buyIntentBundle, BillingClientImpl.TAG)).build();
                    BillingClientImpl.this.postToUiThread(new Runnable() {
                        public void run() {
                            listener.onRewardResponse(billingResult);
                        }
                    });
                } catch (Exception e) {
                    BillingClientImpl.this.postToUiThread(new Runnable() {
                        public void run() {
                            listener.onRewardResponse(BillingResults.INTERNAL_ERROR);
                        }
                    });
                }
                return null;
            }
        }, ASYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, new Runnable() {
            public void run() {
                listener.onRewardResponse(BillingResults.SERVICE_TIMEOUT);
            }
        }) == null) {
            listener.onRewardResponse(getBillingResultForNullFutureResult());
        }
    }

    private void loadRewardedSku(RewardLoadParams params, long futureHandle) {
        loadRewardedSku(params, (RewardResponseListener) new BillingClientNativeCallback(futureHandle));
    }

    public void acknowledgePurchase(final AcknowledgePurchaseParams acknowledgePurchaseParams, final AcknowledgePurchaseResponseListener listener) {
        if (!isReady()) {
            listener.onAcknowledgePurchaseResponse(BillingResults.SERVICE_DISCONNECTED);
        } else if (TextUtils.isEmpty(acknowledgePurchaseParams.getPurchaseToken())) {
            BillingHelper.logWarn(TAG, "Please provide a valid purchase token.");
            listener.onAcknowledgePurchaseResponse(BillingResults.INVALID_PURCHASE_TOKEN);
        } else if (!this.mIABv9Supported) {
            listener.onAcknowledgePurchaseResponse(BillingResults.API_VERSION_NOT_V9);
        } else if (executeAsync(new Callable<Void>() {
            public Void call() {
                try {
                    Bundle responseBundle = BillingClientImpl.this.mService.acknowledgePurchaseExtraParams(9, BillingClientImpl.this.mApplicationContext.getPackageName(), acknowledgePurchaseParams.getPurchaseToken(), BillingHelper.constructExtraParamsForAcknowledgePurchase(acknowledgePurchaseParams, BillingClientImpl.this.mQualifiedVersionNumber));
                    final int responseCode = BillingHelper.getResponseCodeFromBundle(responseBundle, BillingClientImpl.TAG);
                    final String debugMessage = BillingHelper.getDebugMessageFromBundle(responseBundle, BillingClientImpl.TAG);
                    BillingClientImpl.this.postToUiThread(new Runnable() {
                        public void run() {
                            listener.onAcknowledgePurchaseResponse(BillingResult.newBuilder().setResponseCode(responseCode).setDebugMessage(debugMessage).build());
                        }
                    });
                } catch (Exception e) {
                    BillingClientImpl.this.postToUiThread(new Runnable() {
                        public void run() {
                            BillingHelper.logWarn(BillingClientImpl.TAG, "Error acknowledge purchase; ex: " + e);
                            listener.onAcknowledgePurchaseResponse(BillingResults.SERVICE_DISCONNECTED);
                        }
                    });
                }
                return null;
            }
        }, ASYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, new Runnable() {
            public void run() {
                listener.onAcknowledgePurchaseResponse(BillingResults.SERVICE_TIMEOUT);
            }
        }) == null) {
            listener.onAcknowledgePurchaseResponse(getBillingResultForNullFutureResult());
        }
    }

    private void acknowledgePurchase(AcknowledgePurchaseParams acknowledgePurchaseParams, long futureHandle) {
        acknowledgePurchase(acknowledgePurchaseParams, (AcknowledgePurchaseResponseListener) new BillingClientNativeCallback(futureHandle));
    }

    /* access modifiers changed from: private */
    @Nullable
    public <T> Future<T> executeAsync(@NonNull Callable<T> callable, long maxTimeout, @Nullable final Runnable onTimeout) {
        long actualTimeout = (long) (0.95d * ((double) maxTimeout));
        if (this.mExecutorService == null) {
            this.mExecutorService = Executors.newFixedThreadPool(BillingHelper.NUMBER_OF_CORES);
        }
        try {
            final Future<T> task = this.mExecutorService.submit(callable);
            this.mUiThreadHandler.postDelayed(new Runnable() {
                public void run() {
                    if (!task.isDone() && !task.isCancelled()) {
                        task.cancel(true);
                        BillingHelper.logWarn(BillingClientImpl.TAG, "Async task is taking too long, cancel it!");
                        if (onTimeout != null) {
                            onTimeout.run();
                        }
                    }
                }
            }, actualTimeout);
            return task;
        } catch (Exception e) {
            BillingHelper.logWarn(TAG, "Async task throws exception " + e);
            return null;
        }
    }

    private BillingResult isBillingSupportedOnVr(final String skuType) {
        try {
            return executeAsync(new Callable<Integer>() {
                public Integer call() throws Exception {
                    return Integer.valueOf(BillingClientImpl.this.mService.isBillingSupportedExtraParams(7, BillingClientImpl.this.mApplicationContext.getPackageName(), skuType, BillingClientImpl.this.generateVrBundle()));
                }
            }, SYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, (Runnable) null).get(SYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS).intValue() == 0 ? BillingResults.f71OK : BillingResults.FEATURE_NOT_SUPPORTED;
        } catch (Exception e) {
            BillingHelper.logWarn(TAG, "Exception while checking if billing is supported; try to reconnect");
            return BillingResults.SERVICE_DISCONNECTED;
        }
    }

    /* access modifiers changed from: private */
    public Bundle generateVrBundle() {
        Bundle result = new Bundle();
        result.putBoolean(BillingFlowParams.EXTRA_PARAM_KEY_VR, true);
        return result;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public SkuDetails.SkuDetailsResult querySkuDetailsInternal(String skuType, List<String> skuList) {
        Bundle skuDetails;
        List<SkuDetails> resultList = new ArrayList<>();
        int startIndex = 0;
        int listSize = skuList.size();
        while (startIndex < listSize) {
            int endIndex = startIndex + 20;
            if (endIndex > listSize) {
                endIndex = listSize;
            }
            ArrayList<String> curSkuList = new ArrayList<>(skuList.subList(startIndex, endIndex));
            Bundle querySkus = new Bundle();
            querySkus.putStringArrayList(GET_SKU_DETAILS_ITEM_LIST, curSkuList);
            querySkus.putString(BillingHelper.LIBRARY_VERSION_KEY, this.mQualifiedVersionNumber);
            try {
                if (this.mIABv10Supported) {
                    skuDetails = this.mService.getSkuDetailsExtraParams(10, this.mApplicationContext.getPackageName(), skuType, querySkus, BillingHelper.constructExtraParamsForGetSkuDetails(this.mIABv9Supported, this.mEnablePendingPurchases, this.mQualifiedVersionNumber));
                } else {
                    skuDetails = this.mService.getSkuDetails(3, this.mApplicationContext.getPackageName(), skuType, querySkus);
                }
                if (skuDetails == null) {
                    BillingHelper.logWarn(TAG, "querySkuDetailsAsync got null sku details list");
                    return new SkuDetails.SkuDetailsResult(4, "Null sku details list", (List<SkuDetails>) null);
                } else if (!skuDetails.containsKey(BillingHelper.RESPONSE_GET_SKU_DETAILS_LIST)) {
                    int responseCode = BillingHelper.getResponseCodeFromBundle(skuDetails, TAG);
                    String debugMessage = BillingHelper.getDebugMessageFromBundle(skuDetails, TAG);
                    if (responseCode != 0) {
                        BillingHelper.logWarn(TAG, "getSkuDetails() failed. Response code: " + responseCode);
                        return new SkuDetails.SkuDetailsResult(responseCode, debugMessage, resultList);
                    }
                    BillingHelper.logWarn(TAG, "getSkuDetails() returned a bundle with neither an error nor a detail list.");
                    return new SkuDetails.SkuDetailsResult(6, debugMessage, resultList);
                } else {
                    ArrayList<String> skuDetailsJsonList = skuDetails.getStringArrayList(BillingHelper.RESPONSE_GET_SKU_DETAILS_LIST);
                    if (skuDetailsJsonList == null) {
                        BillingHelper.logWarn(TAG, "querySkuDetailsAsync got null response list");
                        return new SkuDetails.SkuDetailsResult(4, "querySkuDetailsAsync got null response list", (List<SkuDetails>) null);
                    }
                    int i = 0;
                    while (i < skuDetailsJsonList.size()) {
                        try {
                            SkuDetails currentSkuDetails = new SkuDetails(skuDetailsJsonList.get(i));
                            BillingHelper.logVerbose(TAG, "Got sku details: " + currentSkuDetails);
                            resultList.add(currentSkuDetails);
                            i++;
                        } catch (JSONException e) {
                            BillingHelper.logWarn(TAG, "Got a JSON exception trying to decode SkuDetails.");
                            return new SkuDetails.SkuDetailsResult(6, "Error trying to decode SkuDetails.", (List<SkuDetails>) null);
                        }
                    }
                    startIndex += 20;
                }
            } catch (Exception e2) {
                BillingHelper.logWarn(TAG, "querySkuDetailsAsync got a remote exception (try to reconnect)." + e2);
                return new SkuDetails.SkuDetailsResult(-1, "Service connection is disconnected.", (List<SkuDetails>) null);
            }
        }
        return new SkuDetails.SkuDetailsResult(0, "", resultList);
    }

    /* access modifiers changed from: private */
    public Purchase.PurchasesResult queryPurchasesInternal(String skuType) {
        Bundle ownedItems;
        BillingHelper.logVerbose(TAG, "Querying owned items, item type: " + skuType);
        String continueToken = null;
        List<Purchase> resultList = new ArrayList<>();
        Bundle extraParams = BillingHelper.constructExtraParamsForQueryPurchases(this.mIABv9Supported, this.mEnablePendingPurchases, this.mQualifiedVersionNumber);
        do {
            try {
                if (this.mIABv9Supported) {
                    ownedItems = this.mService.getPurchasesExtraParams(9, this.mApplicationContext.getPackageName(), skuType, continueToken, extraParams);
                } else {
                    ownedItems = this.mService.getPurchases(3, this.mApplicationContext.getPackageName(), skuType, continueToken);
                }
                BillingResult billingResult = PurchaseApiResponseChecker.checkPurchasesBundleValidity(ownedItems, TAG, "getPurchase()");
                if (billingResult != BillingResults.f71OK) {
                    return new Purchase.PurchasesResult(billingResult, (List<Purchase>) null);
                }
                ArrayList<String> ownedSkus = ownedItems.getStringArrayList(BillingHelper.RESPONSE_INAPP_ITEM_LIST);
                ArrayList<String> purchaseDataList = ownedItems.getStringArrayList(BillingHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST);
                ArrayList<String> signatureList = ownedItems.getStringArrayList(BillingHelper.RESPONSE_INAPP_SIGNATURE_LIST);
                int i = 0;
                while (i < purchaseDataList.size()) {
                    String purchaseData = purchaseDataList.get(i);
                    String signature = signatureList.get(i);
                    BillingHelper.logVerbose(TAG, "Sku is owned: " + ownedSkus.get(i));
                    try {
                        Purchase purchase = new Purchase(purchaseData, signature);
                        if (TextUtils.isEmpty(purchase.getPurchaseToken())) {
                            BillingHelper.logWarn(TAG, "BUG: empty/null token!");
                        }
                        resultList.add(purchase);
                        i++;
                    } catch (JSONException e) {
                        BillingHelper.logWarn(TAG, "Got an exception trying to decode the purchase: " + e);
                        return new Purchase.PurchasesResult(BillingResults.INTERNAL_ERROR, (List<Purchase>) null);
                    }
                }
                continueToken = ownedItems.getString(BillingHelper.INAPP_CONTINUATION_TOKEN);
                BillingHelper.logVerbose(TAG, "Continuation token: " + continueToken);
            } catch (Exception e2) {
                BillingHelper.logWarn(TAG, "Got exception trying to get purchases: " + e2 + "; try to reconnect");
                return new Purchase.PurchasesResult(BillingResults.SERVICE_DISCONNECTED, (List<Purchase>) null);
            }
        } while (!TextUtils.isEmpty(continueToken));
        return new Purchase.PurchasesResult(BillingResults.f71OK, resultList);
    }

    /* access modifiers changed from: private */
    public void postToUiThread(Runnable runnable) {
        if (!Thread.interrupted()) {
            this.mUiThreadHandler.post(runnable);
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void consumeInternal(ConsumeParams consumeParams, final ConsumeResponseListener listener) {
        final int responseCode;
        String debugMessage;
        final String purchaseToken = consumeParams.getPurchaseToken();
        try {
            BillingHelper.logVerbose(TAG, "Consuming purchase with token: " + purchaseToken);
            if (this.mIABv9Supported) {
                Bundle responseBundle = this.mService.consumePurchaseExtraParams(9, this.mApplicationContext.getPackageName(), purchaseToken, BillingHelper.constructExtraParamsForConsume(consumeParams, this.mIABv9Supported, this.mQualifiedVersionNumber));
                responseCode = responseBundle.getInt(BillingHelper.RESPONSE_CODE);
                debugMessage = BillingHelper.getDebugMessageFromBundle(responseBundle, TAG);
            } else {
                responseCode = this.mService.consumePurchase(3, this.mApplicationContext.getPackageName(), purchaseToken);
                debugMessage = "";
            }
            final BillingResult billingResult = BillingResult.newBuilder().setResponseCode(responseCode).setDebugMessage(debugMessage).build();
            if (responseCode == 0) {
                postToUiThread(new Runnable() {
                    public void run() {
                        BillingHelper.logVerbose(BillingClientImpl.TAG, "Successfully consumed purchase.");
                        listener.onConsumeResponse(billingResult, purchaseToken);
                    }
                });
                return;
            }
            final ConsumeResponseListener consumeResponseListener = listener;
            postToUiThread(new Runnable() {
                public void run() {
                    BillingHelper.logWarn(BillingClientImpl.TAG, "Error consuming purchase with token. Response code: " + responseCode);
                    consumeResponseListener.onConsumeResponse(billingResult, purchaseToken);
                }
            });
        } catch (Exception e) {
            postToUiThread(new Runnable() {
                public void run() {
                    BillingHelper.logWarn(BillingClientImpl.TAG, "Error consuming purchase; ex: " + e);
                    listener.onConsumeResponse(BillingResults.SERVICE_DISCONNECTED, purchaseToken);
                }
            });
        }
    }

    private final class BillingServiceConnection implements ServiceConnection {
        /* access modifiers changed from: private */
        public boolean disconnected;
        /* access modifiers changed from: private */
        public final Object lock;
        /* access modifiers changed from: private */
        public BillingClientStateListener mListener;

        private BillingServiceConnection(@NonNull BillingClientStateListener listener) {
            this.lock = new Object();
            this.disconnected = false;
            this.mListener = listener;
        }

        public void onServiceDisconnected(ComponentName name) {
            BillingHelper.logWarn(BillingClientImpl.TAG, "Billing service disconnected.");
            IInAppBillingService unused = BillingClientImpl.this.mService = null;
            int unused2 = BillingClientImpl.this.mClientState = 0;
            synchronized (this.lock) {
                if (this.mListener != null) {
                    this.mListener.onBillingServiceDisconnected();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void markDisconnectedAndCleanUp() {
            synchronized (this.lock) {
                this.mListener = null;
                this.disconnected = true;
            }
        }

        /* access modifiers changed from: private */
        public void notifySetupResult(final BillingResult result) {
            BillingClientImpl.this.postToUiThread(new Runnable() {
                public void run() {
                    synchronized (BillingServiceConnection.this.lock) {
                        if (BillingServiceConnection.this.mListener != null) {
                            BillingServiceConnection.this.mListener.onBillingSetupFinished(result);
                        }
                    }
                }
            });
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            BillingHelper.logVerbose(BillingClientImpl.TAG, "Billing service connected.");
            IInAppBillingService unused = BillingClientImpl.this.mService = IInAppBillingService.Stub.asInterface(service);
            if (BillingClientImpl.this.executeAsync(new Callable<Void>() {
                public Void call() {
                    boolean z;
                    boolean z2;
                    boolean z3;
                    boolean z4;
                    boolean z5;
                    boolean z6 = true;
                    synchronized (BillingServiceConnection.this.lock) {
                        if (!BillingServiceConnection.this.disconnected) {
                            int setupResponse = 3;
                            try {
                                String packageName = BillingClientImpl.this.mApplicationContext.getPackageName();
                                int highestLevelSupportedForSubs = 0;
                                int apiVersion = 10;
                                while (true) {
                                    if (apiVersion < 3) {
                                        break;
                                    }
                                    setupResponse = BillingClientImpl.this.mService.isBillingSupported(apiVersion, packageName, BillingClient.SkuType.SUBS);
                                    if (setupResponse == 0) {
                                        highestLevelSupportedForSubs = apiVersion;
                                        break;
                                    }
                                    apiVersion--;
                                }
                                BillingClientImpl billingClientImpl = BillingClientImpl.this;
                                if (highestLevelSupportedForSubs >= 5) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                boolean unused = billingClientImpl.mSubscriptionUpdateSupported = z;
                                BillingClientImpl billingClientImpl2 = BillingClientImpl.this;
                                if (highestLevelSupportedForSubs >= 3) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                boolean unused2 = billingClientImpl2.mSubscriptionsSupported = z2;
                                if (highestLevelSupportedForSubs < 3) {
                                    BillingHelper.logVerbose(BillingClientImpl.TAG, "In-app billing API does not support subscription on this device.");
                                }
                                int highestLevelSupportedForInApp = 0;
                                int apiVersion2 = 10;
                                while (true) {
                                    if (apiVersion2 < 3) {
                                        break;
                                    }
                                    setupResponse = BillingClientImpl.this.mService.isBillingSupported(apiVersion2, packageName, BillingClient.SkuType.INAPP);
                                    if (setupResponse == 0) {
                                        highestLevelSupportedForInApp = apiVersion2;
                                        break;
                                    }
                                    apiVersion2--;
                                }
                                BillingClientImpl billingClientImpl3 = BillingClientImpl.this;
                                if (highestLevelSupportedForInApp >= 10) {
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                                boolean unused3 = billingClientImpl3.mIABv10Supported = z3;
                                BillingClientImpl billingClientImpl4 = BillingClientImpl.this;
                                if (highestLevelSupportedForInApp >= 9) {
                                    z4 = true;
                                } else {
                                    z4 = false;
                                }
                                boolean unused4 = billingClientImpl4.mIABv9Supported = z4;
                                BillingClientImpl billingClientImpl5 = BillingClientImpl.this;
                                if (highestLevelSupportedForInApp >= 8) {
                                    z5 = true;
                                } else {
                                    z5 = false;
                                }
                                boolean unused5 = billingClientImpl5.mIABv8Supported = z5;
                                BillingClientImpl billingClientImpl6 = BillingClientImpl.this;
                                if (highestLevelSupportedForInApp < 6) {
                                    z6 = false;
                                }
                                boolean unused6 = billingClientImpl6.mIABv6Supported = z6;
                                if (highestLevelSupportedForInApp < 3) {
                                    BillingHelper.logWarn(BillingClientImpl.TAG, "In-app billing API version 3 is not supported on this device.");
                                }
                                if (setupResponse == 0) {
                                    int unused7 = BillingClientImpl.this.mClientState = 2;
                                } else {
                                    int unused8 = BillingClientImpl.this.mClientState = 0;
                                    IInAppBillingService unused9 = BillingClientImpl.this.mService = null;
                                }
                            } catch (Exception e) {
                                BillingHelper.logWarn(BillingClientImpl.TAG, "Exception while checking if billing is supported; try to reconnect");
                                int unused10 = BillingClientImpl.this.mClientState = 0;
                                IInAppBillingService unused11 = BillingClientImpl.this.mService = null;
                            }
                            if (setupResponse == 0) {
                                BillingServiceConnection.this.notifySetupResult(BillingResults.f71OK);
                            } else {
                                BillingServiceConnection.this.notifySetupResult(BillingResults.API_VERSION_NOT_V3);
                            }
                        }
                    }
                    return null;
                }
            }, BillingClientImpl.ASYNCHRONOUS_TIMEOUT_IN_MILLISECONDS, new Runnable() {
                public void run() {
                    int unused = BillingClientImpl.this.mClientState = 0;
                    IInAppBillingService unused2 = BillingClientImpl.this.mService = null;
                    BillingServiceConnection.this.notifySetupResult(BillingResults.SERVICE_TIMEOUT);
                }
            }) == null) {
                notifySetupResult(BillingClientImpl.this.getBillingResultForNullFutureResult());
            }
        }
    }

    /* access modifiers changed from: private */
    public BillingResult getBillingResultForNullFutureResult() {
        return (this.mClientState == 0 || this.mClientState == 3) ? BillingResults.SERVICE_DISCONNECTED : BillingResults.INTERNAL_ERROR;
    }

    private static class PurchaseHistoryResult {
        private BillingResult mBillingResult;
        private List<PurchaseHistoryRecord> mPurchaseHistoryRecordList;

        PurchaseHistoryResult(BillingResult mBillingResult2, List<PurchaseHistoryRecord> purchaseHistoryRecordList) {
            this.mPurchaseHistoryRecordList = purchaseHistoryRecordList;
            this.mBillingResult = mBillingResult2;
        }

        /* access modifiers changed from: package-private */
        public BillingResult getBillingResult() {
            return this.mBillingResult;
        }

        /* access modifiers changed from: package-private */
        public List<PurchaseHistoryRecord> getPurchaseHistoryRecordList() {
            return this.mPurchaseHistoryRecordList;
        }
    }
}
