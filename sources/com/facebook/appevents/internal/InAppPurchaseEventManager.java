package com.facebook.appevents.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.FacebookSdk;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class InAppPurchaseEventManager {
    private static final String AS_INTERFACE = "asInterface";
    private static final int CACHE_CLEAR_TIME_LIMIT_SEC = 604800;
    private static final String DETAILS_LIST = "DETAILS_LIST";
    private static final String GET_PURCHASES = "getPurchases";
    private static final String GET_PURCHASE_HISTORY = "getPurchaseHistory";
    private static final String GET_SKU_DETAILS = "getSkuDetails";
    private static final String INAPP = "inapp";
    private static final String INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
    private static final String INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
    private static final String IN_APP_BILLING_SERVICE = "com.android.vending.billing.IInAppBillingService";
    private static final String IN_APP_BILLING_SERVICE_STUB = "com.android.vending.billing.IInAppBillingService$Stub";
    private static final String IS_BILLING_SUPPORTED = "isBillingSupported";
    private static final String ITEM_ID_LIST = "ITEM_ID_LIST";
    private static final String LAST_CLEARED_TIME = "LAST_CLEARED_TIME";
    private static final int MAX_QUERY_PURCHASE_NUM = 30;
    private static final String PACKAGE_NAME = FacebookSdk.getApplicationContext().getPackageName();
    private static final int PURCHASE_EXPIRE_TIME_SEC = 86400;
    private static final String PURCHASE_INAPP_STORE = "com.facebook.internal.PURCHASE";
    private static final int PURCHASE_STOP_QUERY_TIME_SEC = 1200;
    private static final String RESPONSE_CODE = "RESPONSE_CODE";
    private static final String SKU_DETAILS_STORE = "com.facebook.internal.SKU_DETAILS";
    private static final int SKU_DETAIL_EXPIRE_TIME_SEC = 43200;
    private static final String SUBSCRIPTION = "subs";
    private static final String TAG = InAppPurchaseEventManager.class.getCanonicalName();
    private static final HashMap<String, Class<?>> classMap = new HashMap<>();
    private static final HashMap<String, Method> methodMap = new HashMap<>();
    private static final SharedPreferences purchaseInappSharedPrefs = FacebookSdk.getApplicationContext().getSharedPreferences(PURCHASE_INAPP_STORE, 0);
    private static final SharedPreferences skuDetailSharedPrefs = FacebookSdk.getApplicationContext().getSharedPreferences(SKU_DETAILS_STORE, 0);

    InAppPurchaseEventManager() {
    }

    @Nullable
    static Object asInterface(Context context, IBinder service) {
        return invokeMethod(context, IN_APP_BILLING_SERVICE_STUB, AS_INTERFACE, (Object) null, new Object[]{service});
    }

    static Map<String, String> getSkuDetails(Context context, ArrayList<String> skuList, Object inAppBillingObj, boolean isSubscription) {
        Map<String, String> skuDetailsMap = readSkuDetailsFromCache(skuList);
        ArrayList<String> unresolvedSkuList = new ArrayList<>();
        Iterator<String> it = skuList.iterator();
        while (it.hasNext()) {
            String sku = it.next();
            if (!skuDetailsMap.containsKey(sku)) {
                unresolvedSkuList.add(sku);
            }
        }
        skuDetailsMap.putAll(getSkuDetailsFromGoogle(context, unresolvedSkuList, inAppBillingObj, isSubscription));
        return skuDetailsMap;
    }

    private static Map<String, String> getSkuDetailsFromGoogle(Context context, ArrayList<String> skuList, Object inAppBillingObj, boolean isSubscription) {
        Map<String, String> skuDetailsMap = new HashMap<>();
        if (inAppBillingObj != null && !skuList.isEmpty()) {
            Bundle querySkus = new Bundle();
            querySkus.putStringArrayList(ITEM_ID_LIST, skuList);
            Object[] args = new Object[4];
            args[0] = 3;
            args[1] = PACKAGE_NAME;
            args[2] = isSubscription ? "subs" : "inapp";
            args[3] = querySkus;
            Object result = invokeMethod(context, IN_APP_BILLING_SERVICE, GET_SKU_DETAILS, inAppBillingObj, args);
            if (result != null) {
                Bundle bundle = (Bundle) result;
                if (bundle.getInt("RESPONSE_CODE") == 0) {
                    ArrayList<String> skuDetailsList = bundle.getStringArrayList("DETAILS_LIST");
                    if (skuDetailsList != null && skuList.size() == skuDetailsList.size()) {
                        for (int i = 0; i < skuList.size(); i++) {
                            skuDetailsMap.put(skuList.get(i), skuDetailsList.get(i));
                        }
                    }
                    writeSkuDetailsToCache(skuDetailsMap);
                }
            }
        }
        return skuDetailsMap;
    }

    private static Map<String, String> readSkuDetailsFromCache(ArrayList<String> skuList) {
        Map<String, String> skuDetailsMap = new HashMap<>();
        long nowSec = System.currentTimeMillis() / 1000;
        Iterator<String> it = skuList.iterator();
        while (it.hasNext()) {
            String sku = it.next();
            String rawString = skuDetailSharedPrefs.getString(sku, (String) null);
            if (rawString != null) {
                String[] splitted = rawString.split(";", 2);
                if (nowSec - Long.parseLong(splitted[0]) < 43200) {
                    skuDetailsMap.put(sku, splitted[1]);
                }
            }
        }
        return skuDetailsMap;
    }

    private static void writeSkuDetailsToCache(Map<String, String> skuDetailsMap) {
        long nowSec = System.currentTimeMillis() / 1000;
        SharedPreferences.Editor editor = skuDetailSharedPrefs.edit();
        for (Map.Entry<String, String> pair : skuDetailsMap.entrySet()) {
            editor.putString(pair.getKey(), nowSec + ";" + pair.getValue());
        }
        editor.apply();
    }

    private static Boolean isBillingSupported(Context context, Object inAppBillingObj, String type) {
        boolean z = true;
        if (inAppBillingObj == null) {
            return false;
        }
        Object result = invokeMethod(context, IN_APP_BILLING_SERVICE, IS_BILLING_SUPPORTED, inAppBillingObj, new Object[]{3, PACKAGE_NAME, type});
        if (result == null || ((Integer) result).intValue() != 0) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    static ArrayList<String> getPurchasesInapp(Context context, Object inAppBillingObj) {
        return filterPurchases(getPurchases(context, inAppBillingObj, "inapp"));
    }

    static ArrayList<String> getPurchasesSubs(Context context, Object inAppBillingObj) {
        return filterPurchases(getPurchases(context, inAppBillingObj, "subs"));
    }

    private static ArrayList<String> getPurchases(Context context, Object inAppBillingObj, String type) {
        ArrayList<String> purchases = new ArrayList<>();
        if (inAppBillingObj != null && isBillingSupported(context, inAppBillingObj, type).booleanValue()) {
            String continuationToken = null;
            int queriedPurchaseNum = 0;
            do {
                Object result = invokeMethod(context, IN_APP_BILLING_SERVICE, GET_PURCHASES, inAppBillingObj, new Object[]{3, PACKAGE_NAME, type, continuationToken});
                continuationToken = null;
                if (result != null) {
                    Bundle purchaseDetails = (Bundle) result;
                    if (purchaseDetails.getInt("RESPONSE_CODE") == 0) {
                        ArrayList<String> details = purchaseDetails.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                        if (details == null) {
                            break;
                        }
                        queriedPurchaseNum += details.size();
                        purchases.addAll(details);
                        continuationToken = purchaseDetails.getString("INAPP_CONTINUATION_TOKEN");
                    }
                }
                if (queriedPurchaseNum >= 30) {
                    break;
                }
            } while (continuationToken != null);
        }
        return purchases;
    }

    static boolean hasFreeTrialPeirod(String skuDetail) {
        try {
            String freeTrialPeriod = new JSONObject(skuDetail).optString("freeTrialPeriod");
            if (freeTrialPeriod == null || freeTrialPeriod.isEmpty()) {
                return false;
            }
            return true;
        } catch (JSONException e) {
            Log.e(TAG, "parsing sku detail failure: ", e);
            return false;
        }
    }

    static ArrayList<String> getPurchaseHistoryInapp(Context context, Object inAppBillingObj) {
        ArrayList<String> purchases = new ArrayList<>();
        if (inAppBillingObj == null) {
            ArrayList<String> arrayList = purchases;
            return purchases;
        }
        Class<?> iapClass = getClass(context, IN_APP_BILLING_SERVICE);
        if (iapClass == null) {
            ArrayList<String> arrayList2 = purchases;
            return purchases;
        } else if (getMethod(iapClass, GET_PURCHASE_HISTORY) == null) {
            ArrayList<String> arrayList3 = purchases;
            return purchases;
        } else {
            ArrayList<String> purchases2 = getPurchaseHistory(context, inAppBillingObj, "inapp");
            ArrayList<String> arrayList4 = purchases2;
            return filterPurchases(purchases2);
        }
    }

    private static ArrayList<String> getPurchaseHistory(Context context, Object inAppBillingObj, String type) {
        ArrayList<String> details;
        ArrayList<String> purchases = new ArrayList<>();
        if (isBillingSupported(context, inAppBillingObj, type).booleanValue()) {
            String continuationToken = null;
            int queriedPurchaseNum = 0;
            boolean reachTimeLimit = false;
            do {
                Object[] args = {6, PACKAGE_NAME, type, continuationToken, new Bundle()};
                continuationToken = null;
                Object result = invokeMethod(context, IN_APP_BILLING_SERVICE, GET_PURCHASE_HISTORY, inAppBillingObj, args);
                if (result != null) {
                    long nowSec = System.currentTimeMillis() / 1000;
                    Bundle purchaseDetails = (Bundle) result;
                    if (purchaseDetails.getInt("RESPONSE_CODE") == 0 && (details = purchaseDetails.getStringArrayList("INAPP_PURCHASE_DATA_LIST")) != null) {
                        Iterator<String> it = details.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            String detail = it.next();
                            try {
                                if (nowSec - (new JSONObject(detail).getLong("purchaseTime") / 1000) > 1200) {
                                    reachTimeLimit = true;
                                    break;
                                }
                                purchases.add(detail);
                                queriedPurchaseNum++;
                            } catch (JSONException e) {
                                Log.e(TAG, "parsing purchase failure: ", e);
                            }
                        }
                        continuationToken = purchaseDetails.getString("INAPP_CONTINUATION_TOKEN");
                    }
                }
                if (queriedPurchaseNum >= 30 || continuationToken == null) {
                    break;
                }
            } while (!reachTimeLimit);
        }
        return purchases;
    }

    private static ArrayList<String> filterPurchases(ArrayList<String> purchases) {
        ArrayList<String> filteredPurchase = new ArrayList<>();
        SharedPreferences.Editor editor = purchaseInappSharedPrefs.edit();
        long nowSec = System.currentTimeMillis() / 1000;
        Iterator<String> it = purchases.iterator();
        while (it.hasNext()) {
            String purchase = it.next();
            try {
                JSONObject purchaseJson = new JSONObject(purchase);
                String sku = purchaseJson.getString("productId");
                long purchaseTimeMillis = purchaseJson.getLong("purchaseTime");
                String purchaseToken = purchaseJson.getString("purchaseToken");
                if (nowSec - (purchaseTimeMillis / 1000) <= 86400 && !purchaseInappSharedPrefs.getString(sku, "").equals(purchaseToken)) {
                    editor.putString(sku, purchaseToken);
                    filteredPurchase.add(purchase);
                }
            } catch (JSONException e) {
                Log.e(TAG, "parsing purchase failure: ", e);
            }
        }
        editor.apply();
        return filteredPurchase;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.reflect.Method getMethod(java.lang.Class<?> r13, java.lang.String r14) {
        /*
            r11 = 4
            r10 = 3
            r9 = 2
            r8 = 1
            r6 = 0
            java.util.HashMap<java.lang.String, java.lang.reflect.Method> r7 = methodMap
            java.lang.Object r1 = r7.get(r14)
            java.lang.reflect.Method r1 = (java.lang.reflect.Method) r1
            if (r1 == 0) goto L_0x0012
            r2 = r1
            r3 = r1
        L_0x0011:
            return r3
        L_0x0012:
            r4 = 0
            r7 = -1
            int r12 = r14.hashCode()     // Catch:{ NoSuchMethodException -> 0x00cc }
            switch(r12) {
                case -1801122596: goto L_0x0048;
                case -1450694211: goto L_0x003e;
                case -1123215065: goto L_0x002b;
                case -594356707: goto L_0x0052;
                case -573310373: goto L_0x0034;
                default: goto L_0x001b;
            }     // Catch:{ NoSuchMethodException -> 0x00cc }
        L_0x001b:
            r6 = r7
        L_0x001c:
            switch(r6) {
                case 0: goto L_0x005c;
                case 1: goto L_0x0066;
                case 2: goto L_0x007f;
                case 3: goto L_0x0093;
                case 4: goto L_0x00ad;
                default: goto L_0x001f;
            }     // Catch:{ NoSuchMethodException -> 0x00cc }
        L_0x001f:
            java.lang.reflect.Method r1 = r13.getDeclaredMethod(r14, r4)     // Catch:{ NoSuchMethodException -> 0x00cc }
            java.util.HashMap<java.lang.String, java.lang.reflect.Method> r6 = methodMap     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6.put(r14, r1)     // Catch:{ NoSuchMethodException -> 0x00cc }
        L_0x0028:
            r2 = r1
            r3 = r1
            goto L_0x0011
        L_0x002b:
            java.lang.String r8 = "asInterface"
            boolean r8 = r14.equals(r8)     // Catch:{ NoSuchMethodException -> 0x00cc }
            if (r8 == 0) goto L_0x001b
            goto L_0x001c
        L_0x0034:
            java.lang.String r6 = "getSkuDetails"
            boolean r6 = r14.equals(r6)     // Catch:{ NoSuchMethodException -> 0x00cc }
            if (r6 == 0) goto L_0x001b
            r6 = r8
            goto L_0x001c
        L_0x003e:
            java.lang.String r6 = "isBillingSupported"
            boolean r6 = r14.equals(r6)     // Catch:{ NoSuchMethodException -> 0x00cc }
            if (r6 == 0) goto L_0x001b
            r6 = r9
            goto L_0x001c
        L_0x0048:
            java.lang.String r6 = "getPurchases"
            boolean r6 = r14.equals(r6)     // Catch:{ NoSuchMethodException -> 0x00cc }
            if (r6 == 0) goto L_0x001b
            r6 = r10
            goto L_0x001c
        L_0x0052:
            java.lang.String r6 = "getPurchaseHistory"
            boolean r6 = r14.equals(r6)     // Catch:{ NoSuchMethodException -> 0x00cc }
            if (r6 == 0) goto L_0x001b
            r6 = r11
            goto L_0x001c
        L_0x005c:
            r6 = 1
            java.lang.Class[] r5 = new java.lang.Class[r6]     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 0
            java.lang.Class<android.os.IBinder> r7 = android.os.IBinder.class
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r4 = r5
            goto L_0x001f
        L_0x0066:
            r6 = 4
            java.lang.Class[] r5 = new java.lang.Class[r6]     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 0
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ NoSuchMethodException -> 0x00cc }
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 1
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 2
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 3
            java.lang.Class<android.os.Bundle> r7 = android.os.Bundle.class
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r4 = r5
            goto L_0x001f
        L_0x007f:
            r6 = 3
            java.lang.Class[] r5 = new java.lang.Class[r6]     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 0
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ NoSuchMethodException -> 0x00cc }
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 1
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 2
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r4 = r5
            goto L_0x001f
        L_0x0093:
            r6 = 4
            java.lang.Class[] r5 = new java.lang.Class[r6]     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 0
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ NoSuchMethodException -> 0x00cc }
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 1
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 2
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 3
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r4 = r5
            goto L_0x001f
        L_0x00ad:
            r6 = 5
            java.lang.Class[] r5 = new java.lang.Class[r6]     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 0
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ NoSuchMethodException -> 0x00cc }
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 1
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 2
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 3
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r6 = 4
            java.lang.Class<android.os.Bundle> r7 = android.os.Bundle.class
            r5[r6] = r7     // Catch:{ NoSuchMethodException -> 0x00cc }
            r4 = r5
            goto L_0x001f
        L_0x00cc:
            r0 = move-exception
            java.lang.String r6 = TAG
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = r13.getName()
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = "."
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r14)
            java.lang.String r8 = " method not found"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            android.util.Log.e(r6, r7, r0)
            goto L_0x0028
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.internal.InAppPurchaseEventManager.getMethod(java.lang.Class, java.lang.String):java.lang.reflect.Method");
    }

    @Nullable
    private static Class<?> getClass(Context context, String className) {
        Class<?> classObj = classMap.get(className);
        if (classObj != null) {
            Class<?> cls = classObj;
            return classObj;
        }
        try {
            classObj = context.getClassLoader().loadClass(className);
            classMap.put(className, classObj);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, className + " is not available, please add " + className + " to the project.", e);
        }
        Class<?> cls2 = classObj;
        return classObj;
    }

    @Nullable
    private static Object invokeMethod(Context context, String className, String methodName, Object obj, Object[] args) {
        Method methodObj;
        Class<?> classObj = getClass(context, className);
        if (classObj == null || (methodObj = getMethod(classObj, methodName)) == null) {
            return null;
        }
        if (obj != null) {
            obj = classObj.cast(obj);
        }
        try {
            return methodObj.invoke(obj, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    static void clearSkuDetailsCache() {
        long nowSec = System.currentTimeMillis() / 1000;
        long lastClearedTimeSec = skuDetailSharedPrefs.getLong(LAST_CLEARED_TIME, 0);
        if (lastClearedTimeSec == 0) {
            skuDetailSharedPrefs.edit().putLong(LAST_CLEARED_TIME, nowSec).apply();
        } else if (nowSec - lastClearedTimeSec > 604800) {
            skuDetailSharedPrefs.edit().clear().putLong(LAST_CLEARED_TIME, nowSec).apply();
        }
    }
}
