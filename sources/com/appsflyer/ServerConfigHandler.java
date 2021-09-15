package com.appsflyer;

public class ServerConfigHandler {
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00cb  */
    @android.support.annotation.Nullable
    /* renamed from: ı */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static org.json.JSONObject m313(java.lang.String r6) {
        /*
            r1 = 0
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00db, Throwable -> 0x00ae }
            r0.<init>(r6)     // Catch:{ JSONException -> 0x00db, Throwable -> 0x00ae }
            com.appsflyer.AppsFlyerProperties r1 = com.appsflyer.AppsFlyerProperties.getInstance()     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            java.lang.String r2 = "disableProxy"
            r3 = 0
            boolean r1 = r1.getBoolean(r2, r3)     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            java.lang.String r2 = "monitor"
            r3 = 0
            boolean r2 = r0.optBoolean(r2, r3)     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            if (r2 == 0) goto L_0x006b
            if (r1 != 0) goto L_0x006b
            com.appsflyer.internal.ai r1 = com.appsflyer.internal.C0928ai.f525     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            if (r1 != 0) goto L_0x0027
            com.appsflyer.internal.ai r1 = new com.appsflyer.internal.ai     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            r1.<init>()     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            com.appsflyer.internal.C0928ai.f525 = r1     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
        L_0x0027:
            com.appsflyer.internal.ai r1 = com.appsflyer.internal.C0928ai.f525     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            r1.mo14691()     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
        L_0x002c:
            java.lang.String r1 = "ol_id"
            boolean r1 = r0.has(r1)     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            if (r1 == 0) goto L_0x006a
            java.lang.String r1 = "ol_scheme"
            r2 = 0
            java.lang.String r1 = r0.optString(r1, r2)     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            java.lang.String r2 = "ol_domain"
            r3 = 0
            java.lang.String r2 = r0.optString(r2, r3)     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            java.lang.String r3 = "ol_ver"
            r4 = 0
            java.lang.String r3 = r0.optString(r3, r4)     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            if (r1 == 0) goto L_0x0054
            com.appsflyer.AppsFlyerProperties r4 = com.appsflyer.AppsFlyerProperties.getInstance()     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            java.lang.String r5 = "onelinkScheme"
            r4.set((java.lang.String) r5, (java.lang.String) r1)     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
        L_0x0054:
            if (r2 == 0) goto L_0x005f
            com.appsflyer.AppsFlyerProperties r1 = com.appsflyer.AppsFlyerProperties.getInstance()     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            java.lang.String r4 = "onelinkDomain"
            r1.set((java.lang.String) r4, (java.lang.String) r2)     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
        L_0x005f:
            if (r3 == 0) goto L_0x006a
            com.appsflyer.AppsFlyerProperties r1 = com.appsflyer.AppsFlyerProperties.getInstance()     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            java.lang.String r2 = "onelinkVersion"
            r1.set((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
        L_0x006a:
            return r0
        L_0x006b:
            com.appsflyer.internal.ai r1 = com.appsflyer.internal.C0928ai.f525     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            if (r1 != 0) goto L_0x0076
            com.appsflyer.internal.ai r1 = new com.appsflyer.internal.ai     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            r1.<init>()     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            com.appsflyer.internal.C0928ai.f525 = r1     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
        L_0x0076:
            com.appsflyer.internal.ai r1 = com.appsflyer.internal.C0928ai.f525     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            r1.mo14689()     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            com.appsflyer.internal.ai r1 = com.appsflyer.internal.C0928ai.f525     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            if (r1 != 0) goto L_0x0086
            com.appsflyer.internal.ai r1 = new com.appsflyer.internal.ai     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            r1.<init>()     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            com.appsflyer.internal.C0928ai.f525 = r1     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
        L_0x0086:
            com.appsflyer.internal.ai r1 = com.appsflyer.internal.C0928ai.f525     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            r1.mo14696()     // Catch:{ JSONException -> 0x008c, Throwable -> 0x00d8 }
            goto L_0x002c
        L_0x008c:
            r1 = move-exception
        L_0x008d:
            com.appsflyer.internal.ai r1 = com.appsflyer.internal.C0928ai.f525
            if (r1 != 0) goto L_0x0098
            com.appsflyer.internal.ai r1 = new com.appsflyer.internal.ai
            r1.<init>()
            com.appsflyer.internal.C0928ai.f525 = r1
        L_0x0098:
            com.appsflyer.internal.ai r1 = com.appsflyer.internal.C0928ai.f525
            r1.mo14689()
            com.appsflyer.internal.ai r1 = com.appsflyer.internal.C0928ai.f525
            if (r1 != 0) goto L_0x00a8
            com.appsflyer.internal.ai r1 = new com.appsflyer.internal.ai
            r1.<init>()
            com.appsflyer.internal.C0928ai.f525 = r1
        L_0x00a8:
            com.appsflyer.internal.ai r1 = com.appsflyer.internal.C0928ai.f525
            r1.mo14696()
            goto L_0x006a
        L_0x00ae:
            r2 = move-exception
            r0 = r1
        L_0x00b0:
            java.lang.String r1 = r2.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r1, r2)
            com.appsflyer.internal.ai r1 = com.appsflyer.internal.C0928ai.f525
            if (r1 != 0) goto L_0x00c2
            com.appsflyer.internal.ai r1 = new com.appsflyer.internal.ai
            r1.<init>()
            com.appsflyer.internal.C0928ai.f525 = r1
        L_0x00c2:
            com.appsflyer.internal.ai r1 = com.appsflyer.internal.C0928ai.f525
            r1.mo14689()
            com.appsflyer.internal.ai r1 = com.appsflyer.internal.C0928ai.f525
            if (r1 != 0) goto L_0x00d2
            com.appsflyer.internal.ai r1 = new com.appsflyer.internal.ai
            r1.<init>()
            com.appsflyer.internal.C0928ai.f525 = r1
        L_0x00d2:
            com.appsflyer.internal.ai r1 = com.appsflyer.internal.C0928ai.f525
            r1.mo14696()
            goto L_0x006a
        L_0x00d8:
            r1 = move-exception
            r2 = r1
            goto L_0x00b0
        L_0x00db:
            r0 = move-exception
            r0 = r1
            goto L_0x008d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.ServerConfigHandler.m313(java.lang.String):org.json.JSONObject");
    }

    public static String getUrl(String str) {
        return String.format(str, new Object[]{AppsFlyerLib.getInstance().getHostPrefix(), AppsFlyerLibCore.getInstance().getHostName()});
    }
}
