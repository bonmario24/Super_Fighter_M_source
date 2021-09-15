package com.facebook.appevents.internal;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.facebook.FacebookSdk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class InAppPurchaseActivityLifecycleTracker {
    private static final String BILLING_ACTIVITY_NAME = "com.android.billingclient.api.ProxyBillingActivity";
    private static final String SERVICE_INTERFACE_NAME = "com.android.vending.billing.IInAppBillingService$Stub";
    private static final String TAG = InAppPurchaseActivityLifecycleTracker.class.getCanonicalName();
    private static Application.ActivityLifecycleCallbacks callbacks;
    /* access modifiers changed from: private */
    public static Boolean hasBiillingActivity = null;
    private static Boolean hasBillingService = null;
    /* access modifiers changed from: private */
    public static Object inAppBillingObj;
    private static Intent intent;
    private static final AtomicBoolean isTracking = new AtomicBoolean(false);
    private static ServiceConnection serviceConnection;

    public static void update() {
        initializeIfNotInitialized();
        if (hasBillingService.booleanValue() && AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
            startTracking();
        }
    }

    private static void initializeIfNotInitialized() {
        if (hasBillingService == null) {
            try {
                Class.forName(SERVICE_INTERFACE_NAME);
                hasBillingService = true;
                try {
                    Class.forName(BILLING_ACTIVITY_NAME);
                    hasBiillingActivity = true;
                } catch (ClassNotFoundException e) {
                    hasBiillingActivity = false;
                }
                InAppPurchaseEventManager.clearSkuDetailsCache();
                intent = new Intent("com.android.vending.billing.InAppBillingService.BIND").setPackage("com.android.vending");
                serviceConnection = new ServiceConnection() {
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Object unused = InAppPurchaseActivityLifecycleTracker.inAppBillingObj = InAppPurchaseEventManager.asInterface(FacebookSdk.getApplicationContext(), service);
                    }

                    public void onServiceDisconnected(ComponentName name) {
                    }
                };
                callbacks = new Application.ActivityLifecycleCallbacks() {
                    public void onActivityResumed(Activity activity) {
                        FacebookSdk.getExecutor().execute(new Runnable() {
                            public void run() {
                                Context context = FacebookSdk.getApplicationContext();
                                InAppPurchaseActivityLifecycleTracker.logPurchase(context, InAppPurchaseEventManager.getPurchasesInapp(context, InAppPurchaseActivityLifecycleTracker.inAppBillingObj), false);
                                InAppPurchaseActivityLifecycleTracker.logPurchase(context, InAppPurchaseEventManager.getPurchasesSubs(context, InAppPurchaseActivityLifecycleTracker.inAppBillingObj), true);
                            }
                        });
                    }

                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    }

                    public void onActivityStarted(Activity activity) {
                    }

                    public void onActivityPaused(Activity activity) {
                    }

                    public void onActivityStopped(Activity activity) {
                        if (InAppPurchaseActivityLifecycleTracker.hasBiillingActivity.booleanValue() && activity.getLocalClassName().equals(InAppPurchaseActivityLifecycleTracker.BILLING_ACTIVITY_NAME)) {
                            FacebookSdk.getExecutor().execute(new Runnable() {
                                public void run() {
                                    Context context = FacebookSdk.getApplicationContext();
                                    ArrayList<String> purchases = InAppPurchaseEventManager.getPurchasesInapp(context, InAppPurchaseActivityLifecycleTracker.inAppBillingObj);
                                    if (purchases.isEmpty()) {
                                        purchases = InAppPurchaseEventManager.getPurchaseHistoryInapp(context, InAppPurchaseActivityLifecycleTracker.inAppBillingObj);
                                    }
                                    InAppPurchaseActivityLifecycleTracker.logPurchase(context, purchases, false);
                                }
                            });
                        }
                    }

                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                    }

                    public void onActivityDestroyed(Activity activity) {
                    }
                };
            } catch (ClassNotFoundException e2) {
                hasBillingService = false;
            }
        }
    }

    private static void startTracking() {
        if (isTracking.compareAndSet(false, true)) {
            Context context = FacebookSdk.getApplicationContext();
            if (context instanceof Application) {
                ((Application) context).registerActivityLifecycleCallbacks(callbacks);
                context.bindService(intent, serviceConnection, 1);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void logPurchase(Context context, ArrayList<String> purchases, boolean isSubscription) {
        if (!purchases.isEmpty()) {
            Map<String, String> purchaseMap = new HashMap<>();
            ArrayList<String> skuList = new ArrayList<>();
            Iterator<String> it = purchases.iterator();
            while (it.hasNext()) {
                String purchase = it.next();
                try {
                    String sku = new JSONObject(purchase).getString("productId");
                    purchaseMap.put(sku, purchase);
                    skuList.add(sku);
                } catch (JSONException e) {
                    Log.e(TAG, "Error parsing in-app purchase data.", e);
                }
            }
            for (Map.Entry<String, String> pair : InAppPurchaseEventManager.getSkuDetails(context, skuList, inAppBillingObj, isSubscription).entrySet()) {
                AutomaticAnalyticsLogger.logPurchase(purchaseMap.get(pair.getKey()), pair.getValue(), isSubscription);
            }
        }
    }
}
