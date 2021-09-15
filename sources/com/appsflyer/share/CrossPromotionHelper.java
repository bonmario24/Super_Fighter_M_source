package com.appsflyer.share;

import android.content.Context;
import android.support.annotation.NonNull;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.ServerConfigHandler;
import com.appsflyer.ServerParameters;
import com.appsflyer.internal.C0932am;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class CrossPromotionHelper {

    /* renamed from: ɩ */
    private static String f664 = "https://%simpression.%s";

    public static void trackAndOpenStore(@NonNull Context context, String str, String str2) {
        trackAndOpenStore(context, str, str2, (Map<String, String>) null);
    }

    public static void trackAndOpenStore(@NonNull Context context, String str, String str2, Map<String, String> map) {
        LinkGenerator r0 = m415(context, str, str2, map, ServerConfigHandler.getUrl(Constants.f663));
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false)) {
            AFLogger.afInfoLog("CustomerUserId not set, track And Open Store is disabled", true);
            return;
        }
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("af_campaign", str2);
        AppsFlyerLib.getInstance().trackEvent(context, "af_cross_promotion", map);
        new Thread(new C0969a(r0.generateLink(), new C0932am(), context, AppsFlyerLib.getInstance().isTrackingStopped())).start();
    }

    public static void trackCrossPromoteImpression(@NonNull Context context, String str, String str2) {
        trackCrossPromoteImpression(context, str, str2, (Map<String, String>) null);
    }

    public static void trackCrossPromoteImpression(@NonNull Context context, String str, String str2, Map<String, String> map) {
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false)) {
            AFLogger.afInfoLog("CustomerUserId not set, Promote Impression is disabled", true);
        } else {
            new Thread(new C0969a(m415(context, str, str2, map, ServerConfigHandler.getUrl(f664)).generateLink(), (C0932am) null, (Context) null, AppsFlyerLib.getInstance().isTrackingStopped())).start();
        }
    }

    @NonNull
    /* renamed from: ı */
    private static LinkGenerator m415(@NonNull Context context, String str, String str2, Map<String, String> map, String str3) {
        LinkGenerator linkGenerator = new LinkGenerator("af_cross_promotion");
        linkGenerator.f674 = str3;
        linkGenerator.f679 = str;
        linkGenerator.addParameter(Constants.URL_SITE_ID, context.getPackageName());
        if (str2 != null) {
            linkGenerator.setCampaign(str2);
        }
        if (map != null) {
            linkGenerator.addParameters(map);
        }
        String string = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
        if (string != null) {
            linkGenerator.addParameter("advertising_id", string);
        }
        return linkGenerator;
    }

    /* renamed from: com.appsflyer.share.CrossPromotionHelper$a */
    static class C0969a implements Runnable {

        /* renamed from: ı */
        private final WeakReference<Context> f665;

        /* renamed from: ǃ */
        private final String f666;

        /* renamed from: ɩ */
        private final boolean f667;

        /* renamed from: Ι */
        private final C0932am f668;

        C0969a(String str, C0932am amVar, Context context, boolean z) {
            this.f666 = str;
            this.f668 = amVar;
            this.f665 = new WeakReference<>(context);
            this.f667 = z;
        }

        /* JADX WARNING: Removed duplicated region for block: B:31:0x00c1  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r5 = this;
                boolean r0 = r5.f667
                if (r0 == 0) goto L_0x0005
            L_0x0004:
                return
            L_0x0005:
                r2 = 0
                java.net.URL r0 = new java.net.URL     // Catch:{ Throwable -> 0x00c8 }
                java.lang.String r1 = r5.f666     // Catch:{ Throwable -> 0x00c8 }
                r0.<init>(r1)     // Catch:{ Throwable -> 0x00c8 }
                java.net.URLConnection r0 = r0.openConnection()     // Catch:{ Throwable -> 0x00c8 }
                java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Throwable -> 0x00c8 }
                r1 = 10000(0x2710, float:1.4013E-41)
                r0.setConnectTimeout(r1)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                r1 = 0
                r0.setInstanceFollowRedirects(r1)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                int r1 = r0.getResponseCode()     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                switch(r1) {
                    case 200: goto L_0x0047;
                    case 301: goto L_0x006d;
                    case 302: goto L_0x006d;
                    default: goto L_0x0023;
                }     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
            L_0x0023:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.String r3 = "call to "
                r2.<init>(r3)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.String r3 = r5.f666     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.String r3 = " failed: "
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                com.appsflyer.AFLogger.afInfoLog(r1)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
            L_0x0041:
                if (r0 == 0) goto L_0x0004
                r0.disconnect()
                goto L_0x0004
            L_0x0047:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.String r2 = "Cross promotion impressions success: "
                r1.<init>(r2)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.String r2 = r5.f666     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                r2 = 0
                com.appsflyer.AFLogger.afInfoLog(r1, r2)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                goto L_0x0041
            L_0x005d:
                r1 = move-exception
                r2 = r0
            L_0x005f:
                java.lang.String r0 = r1.getMessage()     // Catch:{ all -> 0x00c5 }
                r3 = 1
                com.appsflyer.AFLogger.afErrorLog(r0, r1, r3)     // Catch:{ all -> 0x00c5 }
                if (r2 == 0) goto L_0x0004
                r2.disconnect()
                goto L_0x0004
            L_0x006d:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.String r2 = "Cross promotion redirection success: "
                r1.<init>(r2)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.String r2 = r5.f666     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                r2 = 0
                com.appsflyer.AFLogger.afInfoLog(r1, r2)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                com.appsflyer.internal.am r1 = r5.f668     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                if (r1 == 0) goto L_0x0041
                java.lang.ref.WeakReference<android.content.Context> r1 = r5.f665     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.Object r1 = r1.get()     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                if (r1 == 0) goto L_0x0041
                com.appsflyer.internal.am r1 = r5.f668     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.String r2 = "Location"
                java.lang.String r2 = r0.getHeaderField(r2)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                r1.f551 = r2     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                com.appsflyer.internal.am r2 = r5.f668     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.ref.WeakReference<android.content.Context> r1 = r5.f665     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.Object r1 = r1.get()     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                android.content.Context r1 = (android.content.Context) r1     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.String r3 = r2.f551     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                if (r3 == 0) goto L_0x0041
                android.content.Intent r3 = new android.content.Intent     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                java.lang.String r4 = "android.intent.action.VIEW"
                java.lang.String r2 = r2.f551     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                r3.<init>(r4, r2)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                r2 = 268435456(0x10000000, float:2.5243549E-29)
                android.content.Intent r2 = r3.setFlags(r2)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                r1.startActivity(r2)     // Catch:{ Throwable -> 0x005d, all -> 0x00bd }
                goto L_0x0041
            L_0x00bd:
                r1 = move-exception
                r2 = r0
            L_0x00bf:
                if (r2 == 0) goto L_0x00c4
                r2.disconnect()
            L_0x00c4:
                throw r1
            L_0x00c5:
                r0 = move-exception
                r1 = r0
                goto L_0x00bf
            L_0x00c8:
                r0 = move-exception
                r1 = r0
                goto L_0x005f
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.share.CrossPromotionHelper.C0969a.run():void");
        }
    }
}
