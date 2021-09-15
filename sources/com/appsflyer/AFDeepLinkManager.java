package com.appsflyer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.VisibleForTesting;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AFDeepLinkManager {
    @VisibleForTesting
    public static AFDeepLinkManager instance;
    @VisibleForTesting
    public static Uri trampolineUri;

    /* renamed from: ı */
    public static String[] f342;

    /* renamed from: ǃ */
    static String[] f343;

    /* renamed from: ɩ */
    static volatile boolean f344;

    /* renamed from: Ι */
    static final int f345 = ((int) TimeUnit.SECONDS.toMillis(2));

    private AFDeepLinkManager() {
    }

    public static AFDeepLinkManager getInstance() {
        if (instance == null) {
            instance = new AFDeepLinkManager();
        }
        return instance;
    }

    /* renamed from: ǃ */
    private static boolean m200(String str) {
        if (f343 == null || str.contains("af_tranid=")) {
            return false;
        }
        AFLogger.afRDLog(new StringBuilder("Validate ESP URLs :").append(Arrays.asList(f343)).toString());
        for (String str2 : f343) {
            if (str.contains("://".concat(String.valueOf(str2)))) {
                AFLogger.afRDLog("Deeplink matches ESP domain: ".concat(String.valueOf(str2)));
                return true;
            }
        }
        return false;
    }

    /* renamed from: Ι */
    static void m202(final Context context, final Map<String, Object> map, final Uri uri) {
        if (m200(uri.toString())) {
            f344 = true;
            AFExecutor instance2 = AFExecutor.getInstance();
            if (instance2.f367 == null) {
                instance2.f367 = Executors.newSingleThreadScheduledExecutor(instance2.f366);
            }
            instance2.f367.execute(new Runnable() {
                /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
                    java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
                    	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
                    	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
                    	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
                    	at java.base/java.util.Objects.checkIndex(Objects.java:372)
                    	at java.base/java.util.ArrayList.get(ArrayList.java:458)
                    	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
                    	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
                    	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                    	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                    	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                    	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
                    	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
                    	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
                    	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
                    	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
                    	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
                    */
                /* JADX WARNING: Removed duplicated region for block: B:13:0x008d  */
                /* JADX WARNING: Removed duplicated region for block: B:26:0x00de  */
                public final void run() {
                    /*
                        r9 = this;
                        r8 = 0
                        java.util.HashMap r5 = new java.util.HashMap
                        r5.<init>()
                        long r6 = java.lang.System.currentTimeMillis()
                        r2 = 0
                        java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00be }
                        java.lang.String r1 = "ESP deeplink resolving is started: "
                        r0.<init>(r1)     // Catch:{ Throwable -> 0x00be }
                        android.net.Uri r1 = r4     // Catch:{ Throwable -> 0x00be }
                        java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x00be }
                        java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Throwable -> 0x00be }
                        java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00be }
                        com.appsflyer.AFLogger.afDebugLog(r0)     // Catch:{ Throwable -> 0x00be }
                        java.net.URL r0 = new java.net.URL     // Catch:{ Throwable -> 0x00be }
                        android.net.Uri r1 = r4     // Catch:{ Throwable -> 0x00be }
                        java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x00be }
                        r0.<init>(r1)     // Catch:{ Throwable -> 0x00be }
                        java.net.URLConnection r0 = r0.openConnection()     // Catch:{ Throwable -> 0x00be }
                        java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Throwable -> 0x00be }
                        r1 = 0
                        r0.setInstanceFollowRedirects(r1)     // Catch:{ Throwable -> 0x00be }
                        int r1 = com.appsflyer.AFDeepLinkManager.f345     // Catch:{ Throwable -> 0x00be }
                        r0.setReadTimeout(r1)     // Catch:{ Throwable -> 0x00be }
                        int r1 = com.appsflyer.AFDeepLinkManager.f345     // Catch:{ Throwable -> 0x00be }
                        r0.setConnectTimeout(r1)     // Catch:{ Throwable -> 0x00be }
                        java.lang.String r1 = "User-agent"
                        java.lang.String r3 = "Dalvik/2.1.0 (Linux; U; Android 6.0.1; Nexus 5 Build/M4B30Z)"
                        r0.setRequestProperty(r1, r3)     // Catch:{ Throwable -> 0x00be }
                        r0.connect()     // Catch:{ Throwable -> 0x00be }
                        java.lang.String r1 = "ESP deeplink resolving is finished"
                        com.appsflyer.AFLogger.afDebugLog(r1)     // Catch:{ Throwable -> 0x00be }
                        java.lang.String r1 = "status"
                        int r3 = r0.getResponseCode()     // Catch:{ Throwable -> 0x00be }
                        java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x00be }
                        r5.put(r1, r3)     // Catch:{ Throwable -> 0x00be }
                        int r1 = r0.getResponseCode()     // Catch:{ Throwable -> 0x00be }
                        r3 = 300(0x12c, float:4.2E-43)
                        if (r1 < r3) goto L_0x00f0
                        int r1 = r0.getResponseCode()     // Catch:{ Throwable -> 0x00be }
                        r3 = 305(0x131, float:4.27E-43)
                        if (r1 > r3) goto L_0x00f0
                        java.lang.String r1 = "Location"
                        java.lang.String r1 = r0.getHeaderField(r1)     // Catch:{ Throwable -> 0x00be }
                        android.net.Uri r2 = android.net.Uri.parse(r1)     // Catch:{ Throwable -> 0x00be }
                        r1 = r2
                    L_0x0079:
                        long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00ec }
                        long r2 = r2 - r6
                        r0.disconnect()     // Catch:{ Throwable -> 0x00ec }
                        r0 = r1
                    L_0x0082:
                        java.lang.String r1 = "latency"
                        java.lang.String r2 = java.lang.Long.toString(r2)
                        r5.put(r1, r2)
                        if (r0 == 0) goto L_0x00de
                        java.lang.String r1 = "res"
                        java.lang.String r2 = r0.toString()
                        r5.put(r1, r2)
                    L_0x0096:
                        java.util.Map r1 = r3
                        monitor-enter(r1)
                        java.util.Map r2 = r3     // Catch:{ all -> 0x00e6 }
                        java.lang.String r3 = "af_deeplink_r"
                        r2.put(r3, r5)     // Catch:{ all -> 0x00e6 }
                        java.util.Map r2 = r3     // Catch:{ all -> 0x00e6 }
                        java.lang.String r3 = "af_deeplink"
                        android.net.Uri r4 = r4     // Catch:{ all -> 0x00e6 }
                        java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00e6 }
                        r2.put(r3, r4)     // Catch:{ all -> 0x00e6 }
                        monitor-exit(r1)     // Catch:{ all -> 0x00e6 }
                        com.appsflyer.AFDeepLinkManager.f344 = r8
                        if (r0 == 0) goto L_0x00e9
                    L_0x00b2:
                        com.appsflyer.AppsFlyerLibCore r1 = com.appsflyer.AppsFlyerLibCore.getInstance()
                        android.content.Context r2 = r2
                        java.util.Map r3 = r3
                        r1.handleDeepLinkCallback(r2, r3, r0)
                        return
                    L_0x00be:
                        r1 = move-exception
                        r4 = r1
                        r0 = r2
                    L_0x00c1:
                        java.lang.String r1 = "error"
                        java.lang.String r2 = r4.getLocalizedMessage()
                        r5.put(r1, r2)
                        java.lang.String r1 = "status"
                        java.lang.String r2 = "-1"
                        r5.put(r1, r2)
                        long r2 = java.lang.System.currentTimeMillis()
                        long r2 = r2 - r6
                        java.lang.String r1 = r4.getMessage()
                        com.appsflyer.AFLogger.afErrorLog(r1, r4)
                        goto L_0x0082
                    L_0x00de:
                        java.lang.String r1 = "res"
                        java.lang.String r2 = ""
                        r5.put(r1, r2)
                        goto L_0x0096
                    L_0x00e6:
                        r0 = move-exception
                        monitor-exit(r1)
                        throw r0
                    L_0x00e9:
                        android.net.Uri r0 = r4
                        goto L_0x00b2
                    L_0x00ec:
                        r2 = move-exception
                        r4 = r2
                        r0 = r1
                        goto L_0x00c1
                    L_0x00f0:
                        r1 = r2
                        goto L_0x0079
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AFDeepLinkManager.C08895.run():void");
                }
            });
        } else {
            AppsFlyerLibCore.getInstance().handleDeepLinkCallback(context, map, uri);
        }
        trampolineUri = null;
    }

    /* renamed from: ɩ */
    static void m201(Intent intent, Context context, Map<String, Object> map) {
        Uri uri = null;
        if (intent != null && "android.intent.action.VIEW".equals(intent.getAction())) {
            uri = intent.getData();
        }
        if (uri != null) {
            if (!intent.hasExtra("af_consumed")) {
                intent.putExtra("af_consumed", System.currentTimeMillis());
                m202(context, map, uri);
                return;
            }
            AFLogger.afInfoLog(new StringBuilder("skipping re-use of previously consumed deep link: ").append(uri.toString()).append(" w/af_consumed").toString());
        } else if (trampolineUri != null) {
            AFLogger.afInfoLog(new StringBuilder("using trampoline Intent fallback with URI: ").append(trampolineUri).toString());
            m202(context, map, trampolineUri);
        } else if (AppsFlyerLibCore.getInstance().latestDeepLink != null) {
            AFLogger.afInfoLog(new StringBuilder("using Unity/plugin Intent fallback with URI: ").append(AppsFlyerLibCore.getInstance().latestDeepLink.toString()).toString());
            m202(context, map, AppsFlyerLibCore.getInstance().latestDeepLink);
        } else {
            AFLogger.afDebugLog("No deep link detected");
        }
    }

    /* access modifiers changed from: protected */
    public void collectIntentsFromActivities(Intent intent) {
        Uri uri = null;
        if (intent != null && "android.intent.action.VIEW".equals(intent.getAction())) {
            uri = intent.getData();
        }
        if (uri != null && intent.getData() != trampolineUri) {
            trampolineUri = intent.getData();
        }
    }
}
