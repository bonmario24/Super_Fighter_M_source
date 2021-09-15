package com.appsflyer.internal;

import android.content.ContentResolver;
import android.os.Build;
import android.provider.Settings;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.internal.C0963w;

/* renamed from: com.appsflyer.internal.v */
public final class C0961v {
    C0961v() {
    }

    /* renamed from: Ι */
    public static C0963w m402(ContentResolver contentResolver) {
        if (contentResolver == null || AppsFlyerProperties.getInstance().getString("amazon_aid") != null || !"Amazon".equals(Build.MANUFACTURER)) {
            return null;
        }
        int i = Settings.Secure.getInt(contentResolver, "limit_ad_tracking", 2);
        if (i == 0) {
            String string = Settings.Secure.getString(contentResolver, "advertising_id");
            C0963w.C0964e eVar = C0963w.C0964e.AMAZON;
            return new C0963w(string, false);
        } else if (i == 2) {
            return null;
        } else {
            String str = "";
            try {
                str = Settings.Secure.getString(contentResolver, "advertising_id");
            } catch (Throwable th) {
                AFLogger.afErrorLog("Couldn't fetch Amazon Advertising ID (Ad-Tracking is limited!)", th);
            }
            C0963w.C0964e eVar2 = C0963w.C0964e.AMAZON;
            return new C0963w(str, true);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        if (r3.length() == 0) goto L_0x0039;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008b A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00fb A[SYNTHETIC, Splitter:B:39:0x00fb] */
    /* JADX WARNING: Removed duplicated region for block: B:60:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* renamed from: ι */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m403(android.content.Context r9, java.util.Map<java.lang.String, java.lang.Object> r10) {
        /*
            r6 = 0
            r5 = 0
            r0 = 1
            java.lang.String r1 = "Trying to fetch GAID.."
            com.appsflyer.AFLogger.afInfoLog(r1)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r2 = -1
            com.google.android.gms.common.GoogleApiAvailability r1 = com.google.android.gms.common.GoogleApiAvailability.getInstance()     // Catch:{ Throwable -> 0x00b3 }
            int r1 = r1.isGooglePlayServicesAvailable(r9)     // Catch:{ Throwable -> 0x00b3 }
            r7 = r1
        L_0x0017:
            java.lang.String r1 = "com.google.android.gms.ads.identifier.AdvertisingIdClient"
            java.lang.Class.forName(r1)     // Catch:{ Throwable -> 0x00ce }
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r1 = com.google.android.gms.ads.identifier.AdvertisingIdClient.getAdvertisingIdInfo(r9)     // Catch:{ Throwable -> 0x00ce }
            if (r1 == 0) goto L_0x00c1
            java.lang.String r3 = r1.getId()     // Catch:{ Throwable -> 0x00ce }
            boolean r1 = r1.isLimitAdTrackingEnabled()     // Catch:{ Throwable -> 0x0164 }
            if (r1 != 0) goto L_0x00be
            r1 = r0
        L_0x002d:
            java.lang.String r2 = java.lang.Boolean.toString(r1)     // Catch:{ Throwable -> 0x0164 }
            if (r3 == 0) goto L_0x0039
            int r1 = r3.length()     // Catch:{ Throwable -> 0x0169 }
            if (r1 != 0) goto L_0x003e
        L_0x0039:
            java.lang.String r1 = "emptyOrNull |"
            r8.append(r1)     // Catch:{ Throwable -> 0x0169 }
        L_0x003e:
            java.lang.Class r1 = r9.getClass()
            java.lang.String r1 = r1.getName()
            java.lang.String r4 = "android.app.ReceiverRestrictedContext"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x0067
            com.appsflyer.AppsFlyerProperties r1 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r2 = "advertiserId"
            java.lang.String r3 = r1.getString(r2)
            com.appsflyer.AppsFlyerProperties r1 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r2 = "advertiserIdEnabled"
            java.lang.String r2 = r1.getString(r2)
            java.lang.String r1 = "context = android.app.ReceiverRestrictedContext |"
            r8.append(r1)
        L_0x0067:
            int r1 = r8.length()
            if (r1 <= 0) goto L_0x0089
            java.lang.String r1 = "gaidError"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r4 = r4.append(r7)
            java.lang.String r5 = ": "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.String r4 = r4.toString()
            r10.put(r1, r4)
        L_0x0089:
            if (r3 == 0) goto L_0x00b2
            if (r2 == 0) goto L_0x00b2
            java.lang.String r1 = "advertiserId"
            r10.put(r1, r3)
            java.lang.String r1 = "advertiserIdEnabled"
            r10.put(r1, r2)
            com.appsflyer.AppsFlyerProperties r1 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r4 = "advertiserId"
            r1.set((java.lang.String) r4, (java.lang.String) r3)
            com.appsflyer.AppsFlyerProperties r1 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r3 = "advertiserIdEnabled"
            r1.set((java.lang.String) r3, (java.lang.String) r2)
            java.lang.String r1 = "isGaidWithGps"
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r10.put(r1, r0)
        L_0x00b2:
            return
        L_0x00b3:
            r1 = move-exception
            java.lang.String r3 = r1.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r3, r1)
            r7 = r2
            goto L_0x0017
        L_0x00be:
            r1 = r5
            goto L_0x002d
        L_0x00c1:
            java.lang.String r1 = "gpsAdInfo-null |"
            r8.append(r1)     // Catch:{ Throwable -> 0x00ce }
            com.appsflyer.internal.v$a r1 = new com.appsflyer.internal.v$a     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r2 = "GpsAdIndo is null"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x00ce }
            throw r1     // Catch:{ Throwable -> 0x00ce }
        L_0x00ce:
            r4 = move-exception
            r1 = r5
            r2 = r6
            r3 = r6
        L_0x00d2:
            java.lang.String r6 = r4.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r6, r4)
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getSimpleName()
            java.lang.StringBuilder r4 = r8.append(r4)
            java.lang.String r6 = " |"
            r4.append(r6)
            java.lang.String r4 = "WARNING: Google Play Services is missing."
            com.appsflyer.AFLogger.afInfoLog(r4)
            com.appsflyer.AppsFlyerProperties r4 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r6 = "enableGpsFallback"
            boolean r4 = r4.getBoolean(r6, r0)
            if (r4 == 0) goto L_0x0161
            com.appsflyer.internal.u$b r2 = com.appsflyer.internal.C0957u.m398(r9)     // Catch:{ Throwable -> 0x011d }
            java.lang.String r3 = r2.f640     // Catch:{ Throwable -> 0x011d }
            boolean r2 = r2.mo14728()     // Catch:{ Throwable -> 0x011d }
            if (r2 != 0) goto L_0x011b
        L_0x0107:
            java.lang.String r2 = java.lang.Boolean.toString(r0)     // Catch:{ Throwable -> 0x011d }
            if (r3 == 0) goto L_0x0113
            int r0 = r3.length()     // Catch:{ Throwable -> 0x011d }
            if (r0 != 0) goto L_0x0118
        L_0x0113:
            java.lang.String r0 = "emptyOrNull (bypass) |"
            r8.append(r0)     // Catch:{ Throwable -> 0x011d }
        L_0x0118:
            r0 = r1
            goto L_0x003e
        L_0x011b:
            r0 = r5
            goto L_0x0107
        L_0x011d:
            r0 = move-exception
            java.lang.String r2 = r0.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r2, r0)
            java.lang.Class r2 = r0.getClass()
            java.lang.String r2 = r2.getSimpleName()
            java.lang.StringBuilder r2 = r8.append(r2)
            java.lang.String r3 = " |"
            r2.append(r3)
            com.appsflyer.AppsFlyerProperties r2 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r3 = "advertiserId"
            java.lang.String r3 = r2.getString(r3)
            com.appsflyer.AppsFlyerProperties r2 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r4 = "advertiserIdEnabled"
            java.lang.String r2 = r2.getString(r4)
            java.lang.String r4 = r0.getLocalizedMessage()
            if (r4 == 0) goto L_0x015a
            java.lang.String r0 = r0.getLocalizedMessage()
            com.appsflyer.AFLogger.afInfoLog(r0)
            r0 = r1
            goto L_0x003e
        L_0x015a:
            java.lang.String r0 = r0.toString()
            com.appsflyer.AFLogger.afInfoLog(r0)
        L_0x0161:
            r0 = r1
            goto L_0x003e
        L_0x0164:
            r4 = move-exception
            r1 = r5
            r2 = r6
            goto L_0x00d2
        L_0x0169:
            r4 = move-exception
            r1 = r0
            goto L_0x00d2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0961v.m403(android.content.Context, java.util.Map):void");
    }

    /* renamed from: com.appsflyer.internal.v$a */
    static class C0962a extends IllegalStateException {
        C0962a(String str) {
            super(str);
        }
    }
}
