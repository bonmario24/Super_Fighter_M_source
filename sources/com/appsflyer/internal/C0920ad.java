package com.appsflyer.internal;

import com.appsflyer.internal.model.event.BackgroundEvent;

/* renamed from: com.appsflyer.internal.ad */
public final class C0920ad implements Runnable {

    /* renamed from: Ι */
    private final BackgroundEvent f509;

    public C0920ad(BackgroundEvent backgroundEvent) {
        this.f509 = (BackgroundEvent) backgroundEvent.weakContext();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: java.lang.Object[]} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0190  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01d9  */
    /* renamed from: ɩ */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.net.HttpURLConnection mo14686() {
        /*
            r15 = this;
            com.appsflyer.internal.model.event.BackgroundEvent r0 = r15.f509
            java.lang.String r6 = r0.urlString()
            com.appsflyer.internal.model.event.BackgroundEvent r0 = r15.f509
            java.lang.String r1 = r0.body()
            com.appsflyer.internal.model.event.BackgroundEvent r0 = r15.f509
            boolean r0 = r0.trackingStopped()
            com.appsflyer.internal.model.event.BackgroundEvent r2 = r15.f509
            boolean r7 = r2.readResponse()
            com.appsflyer.internal.model.event.BackgroundEvent r2 = r15.f509
            boolean r8 = r2.proxyMode()
            com.appsflyer.internal.model.event.BackgroundEvent r2 = r15.f509
            boolean r9 = r2.isEncrypt()
            r2 = 0
            java.lang.String r4 = ""
            r3 = 0
            byte[] r5 = r1.getBytes()
            if (r0 == 0) goto L_0x0030
            r0 = 0
        L_0x002f:
            return r0
        L_0x0030:
            java.net.URL r10 = new java.net.URL     // Catch:{ Throwable -> 0x01dc }
            r10.<init>(r6)     // Catch:{ Throwable -> 0x01dc }
            if (r8 == 0) goto L_0x008f
            com.appsflyer.internal.ai r0 = com.appsflyer.internal.C0928ai.f525     // Catch:{ Throwable -> 0x01dc }
            if (r0 != 0) goto L_0x0042
            com.appsflyer.internal.ai r0 = new com.appsflyer.internal.ai     // Catch:{ Throwable -> 0x01dc }
            r0.<init>()     // Catch:{ Throwable -> 0x01dc }
            com.appsflyer.internal.C0928ai.f525 = r0     // Catch:{ Throwable -> 0x01dc }
        L_0x0042:
            com.appsflyer.internal.ai r0 = com.appsflyer.internal.C0928ai.f525     // Catch:{ Throwable -> 0x01dc }
            java.lang.String r11 = r10.toString()     // Catch:{ Throwable -> 0x01dc }
            java.lang.String r12 = "server_request"
            r13 = 1
            java.lang.String[] r13 = new java.lang.String[r13]     // Catch:{ Throwable -> 0x01dc }
            r14 = 0
            r13[r14] = r1     // Catch:{ Throwable -> 0x01dc }
            r0.mo14690(r12, r11, r13)     // Catch:{ Throwable -> 0x01dc }
            java.lang.String r0 = "UTF-8"
            byte[] r0 = r1.getBytes(r0)     // Catch:{ Throwable -> 0x01dc }
            int r0 = r0.length     // Catch:{ Throwable -> 0x01dc }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01dc }
            java.lang.String r12 = "call = "
            r11.<init>(r12)     // Catch:{ Throwable -> 0x01dc }
            java.lang.StringBuilder r11 = r11.append(r10)     // Catch:{ Throwable -> 0x01dc }
            java.lang.String r12 = "; size = "
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ Throwable -> 0x01dc }
            java.lang.StringBuilder r11 = r11.append(r0)     // Catch:{ Throwable -> 0x01dc }
            java.lang.String r12 = " byte"
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ Throwable -> 0x01dc }
            r12 = 1
            if (r0 <= r12) goto L_0x01a9
            java.lang.String r0 = "s"
        L_0x007a:
            java.lang.StringBuilder r0 = r11.append(r0)     // Catch:{ Throwable -> 0x01dc }
            java.lang.String r11 = "; body = "
            java.lang.StringBuilder r0 = r0.append(r11)     // Catch:{ Throwable -> 0x01dc }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Throwable -> 0x01dc }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x01dc }
            com.appsflyer.internal.C0927ah.m336(r0)     // Catch:{ Throwable -> 0x01dc }
        L_0x008f:
            java.lang.String r0 = "AppsFlyer"
            int r0 = r0.hashCode()     // Catch:{ Throwable -> 0x01dc }
            android.net.TrafficStats.setThreadStatsTag(r0)     // Catch:{ Throwable -> 0x01dc }
            java.net.URLConnection r0 = r10.openConnection()     // Catch:{ Throwable -> 0x01dc }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Throwable -> 0x01dc }
            r1 = 30000(0x7530, float:4.2039E-41)
            r0.setReadTimeout(r1)     // Catch:{ Throwable -> 0x01b9 }
            r1 = 30000(0x7530, float:4.2039E-41)
            r0.setConnectTimeout(r1)     // Catch:{ Throwable -> 0x01b9 }
            java.lang.String r1 = "POST"
            r0.setRequestMethod(r1)     // Catch:{ Throwable -> 0x01b9 }
            r1 = 1
            r0.setDoInput(r1)     // Catch:{ Throwable -> 0x01b9 }
            r1 = 1
            r0.setDoOutput(r1)     // Catch:{ Throwable -> 0x01b9 }
            java.lang.String r3 = "Content-Type"
            if (r9 == 0) goto L_0x01ad
            java.lang.String r1 = "application/octet-stream"
        L_0x00bb:
            r0.setRequestProperty(r3, r1)     // Catch:{ Throwable -> 0x01b9 }
            java.io.OutputStream r3 = r0.getOutputStream()     // Catch:{ Throwable -> 0x01b9 }
            if (r9 == 0) goto L_0x01d4
            com.appsflyer.internal.model.event.BackgroundEvent r1 = r15.f509     // Catch:{ Throwable -> 0x01b9 }
            java.lang.String r1 = r1.key()     // Catch:{ Throwable -> 0x01b9 }
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ all -> 0x01b1 }
            r11 = 0
            r9[r11] = r1     // Catch:{ all -> 0x01b1 }
            r1 = 24
            r11 = 0
            r12 = 24
            java.lang.Object r1 = com.appsflyer.internal.C0940d.m378(r1, r11, r12)     // Catch:{ all -> 0x01b1 }
            java.lang.Class r1 = (java.lang.Class) r1     // Catch:{ all -> 0x01b1 }
            java.lang.String r11 = "ǃ"
            r12 = 1
            java.lang.Class[] r12 = new java.lang.Class[r12]     // Catch:{ all -> 0x01b1 }
            r13 = 0
            java.lang.Class<java.lang.String> r14 = java.lang.String.class
            r12[r13] = r14     // Catch:{ all -> 0x01b1 }
            java.lang.reflect.Method r1 = r1.getMethod(r11, r12)     // Catch:{ all -> 0x01b1 }
            r11 = 0
            java.lang.Object r9 = r1.invoke(r11, r9)     // Catch:{ all -> 0x01b1 }
            r1 = 1
            java.lang.Object[] r11 = new java.lang.Object[r1]     // Catch:{ all -> 0x01cb }
            r1 = 0
            r11[r1] = r5     // Catch:{ all -> 0x01cb }
            r1 = 24
            r5 = 0
            r12 = 24
            java.lang.Object r1 = com.appsflyer.internal.C0940d.m378(r1, r5, r12)     // Catch:{ all -> 0x01cb }
            java.lang.Class r1 = (java.lang.Class) r1     // Catch:{ all -> 0x01cb }
            java.lang.String r5 = "ı"
            r12 = 1
            java.lang.Class[] r12 = new java.lang.Class[r12]     // Catch:{ all -> 0x01cb }
            r13 = 0
            java.lang.Class<byte[]> r14 = byte[].class
            r12[r13] = r14     // Catch:{ all -> 0x01cb }
            java.lang.reflect.Method r1 = r1.getDeclaredMethod(r5, r12)     // Catch:{ all -> 0x01cb }
            java.lang.Object r1 = r1.invoke(r9, r11)     // Catch:{ all -> 0x01cb }
            byte[] r1 = (byte[]) r1     // Catch:{ all -> 0x01cb }
        L_0x0113:
            r3.write(r1)     // Catch:{ Throwable -> 0x01b9 }
            r3.close()     // Catch:{ Throwable -> 0x01b9 }
            r0.connect()     // Catch:{ Throwable -> 0x01b9 }
            int r3 = r0.getResponseCode()     // Catch:{ Throwable -> 0x01b9 }
            if (r7 == 0) goto L_0x01e2
            com.appsflyer.AppsFlyerLibCore r1 = com.appsflyer.AppsFlyerLibCore.getInstance()     // Catch:{ Throwable -> 0x01b9 }
            java.lang.String r1 = r1.mo14600((java.net.HttpURLConnection) r0)     // Catch:{ Throwable -> 0x01b9 }
        L_0x012a:
            if (r8 == 0) goto L_0x014f
            com.appsflyer.internal.ai r4 = com.appsflyer.internal.C0928ai.f525     // Catch:{ Throwable -> 0x01e0 }
            if (r4 != 0) goto L_0x0137
            com.appsflyer.internal.ai r4 = new com.appsflyer.internal.ai     // Catch:{ Throwable -> 0x01e0 }
            r4.<init>()     // Catch:{ Throwable -> 0x01e0 }
            com.appsflyer.internal.C0928ai.f525 = r4     // Catch:{ Throwable -> 0x01e0 }
        L_0x0137:
            com.appsflyer.internal.ai r4 = com.appsflyer.internal.C0928ai.f525     // Catch:{ Throwable -> 0x01e0 }
            java.lang.String r5 = r10.toString()     // Catch:{ Throwable -> 0x01e0 }
            java.lang.String r7 = "server_response"
            r8 = 2
            java.lang.String[] r8 = new java.lang.String[r8]     // Catch:{ Throwable -> 0x01e0 }
            r9 = 0
            java.lang.String r11 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x01e0 }
            r8[r9] = r11     // Catch:{ Throwable -> 0x01e0 }
            r9 = 1
            r8[r9] = r1     // Catch:{ Throwable -> 0x01e0 }
            r4.mo14690(r7, r5, r8)     // Catch:{ Throwable -> 0x01e0 }
        L_0x014f:
            r4 = 200(0xc8, float:2.8E-43)
            if (r3 != r4) goto L_0x01d7
            java.lang.String r3 = "Status 200 ok"
            com.appsflyer.AFLogger.afInfoLog(r3)     // Catch:{ Throwable -> 0x01e0 }
            com.appsflyer.internal.model.event.BackgroundEvent r3 = r15.f509     // Catch:{ Throwable -> 0x01e0 }
            android.content.Context r3 = r3.context()     // Catch:{ Throwable -> 0x01e0 }
            java.lang.String r4 = r10.toString()     // Catch:{ Throwable -> 0x01e0 }
            java.lang.String r5 = com.appsflyer.AppsFlyerLibCore.REGISTER_URL     // Catch:{ Throwable -> 0x01e0 }
            java.lang.String r5 = com.appsflyer.ServerConfigHandler.getUrl(r5)     // Catch:{ Throwable -> 0x01e0 }
            boolean r4 = r4.startsWith(r5)     // Catch:{ Throwable -> 0x01e0 }
            if (r4 == 0) goto L_0x0187
            if (r3 == 0) goto L_0x0187
            android.content.SharedPreferences r3 = com.appsflyer.AppsFlyerLibCore.getSharedPreferences(r3)     // Catch:{ Throwable -> 0x01e0 }
            android.content.SharedPreferences$Editor r3 = r3.edit()     // Catch:{ Throwable -> 0x01e0 }
            java.lang.String r4 = "sentRegisterRequestToAF"
            r5 = 1
            android.content.SharedPreferences$Editor r3 = r3.putBoolean(r4, r5)     // Catch:{ Throwable -> 0x01e0 }
            r3.apply()     // Catch:{ Throwable -> 0x01e0 }
            java.lang.String r3 = "Successfully registered for Uninstall Tracking"
            com.appsflyer.AFLogger.afDebugLog(r3)     // Catch:{ Throwable -> 0x01e0 }
        L_0x0187:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Connection "
            r3.<init>(r4)
            if (r2 == 0) goto L_0x01d9
            java.lang.String r2 = "error"
        L_0x0192:
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r3 = ": "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            com.appsflyer.AFLogger.afInfoLog(r1)
            goto L_0x002f
        L_0x01a9:
            java.lang.String r0 = ""
            goto L_0x007a
        L_0x01ad:
            java.lang.String r1 = "application/json"
            goto L_0x00bb
        L_0x01b1:
            r1 = move-exception
            java.lang.Throwable r2 = r1.getCause()     // Catch:{ Throwable -> 0x01b9 }
            if (r2 == 0) goto L_0x01ca
            throw r2     // Catch:{ Throwable -> 0x01b9 }
        L_0x01b9:
            r2 = move-exception
            r1 = r4
        L_0x01bb:
            java.lang.String r3 = "Error while calling "
            java.lang.String r4 = java.lang.String.valueOf(r6)
            java.lang.String r3 = r3.concat(r4)
            com.appsflyer.AFLogger.afErrorLog(r3, r2)
            r2 = 1
            goto L_0x0187
        L_0x01ca:
            throw r1     // Catch:{ Throwable -> 0x01b9 }
        L_0x01cb:
            r1 = move-exception
            java.lang.Throwable r2 = r1.getCause()     // Catch:{ Throwable -> 0x01b9 }
            if (r2 == 0) goto L_0x01d3
            throw r2     // Catch:{ Throwable -> 0x01b9 }
        L_0x01d3:
            throw r1     // Catch:{ Throwable -> 0x01b9 }
        L_0x01d4:
            r1 = r5
            goto L_0x0113
        L_0x01d7:
            r2 = 1
            goto L_0x0187
        L_0x01d9:
            java.lang.String r2 = "call succeeded"
            goto L_0x0192
        L_0x01dc:
            r2 = move-exception
            r0 = r3
            r1 = r4
            goto L_0x01bb
        L_0x01e0:
            r2 = move-exception
            goto L_0x01bb
        L_0x01e2:
            r1 = r4
            goto L_0x012a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0920ad.mo14686():java.net.HttpURLConnection");
    }

    public final void run() {
        mo14686();
    }
}
