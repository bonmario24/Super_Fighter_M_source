package com.appsflyer.internal.referrer;

import android.content.Context;
import com.appsflyer.internal.ContentFetcher;
import java.util.Map;

public class HuaweiReferrer extends ContentFetcher<Map<String, Object>> {
    public Map<String, Object> map;

    /* renamed from: ɩ */
    private Runnable f633;

    public HuaweiReferrer(Runnable runnable, Context context) {
        super(context, "com.huawei.appmarket.commondata", "ffe391e0ea186d0734ed601e4e70e3224b7309d48e2075bac46d8c667eae7212", 0);
        this.f633 = runnable;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00af  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, java.lang.Object> query() {
        /*
            r9 = this;
            r6 = 0
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            java.lang.String r0 = "source"
            java.lang.String r1 = "huawei"
            r7.put(r0, r1)
            com.appsflyer.internal.referrer.MandatoryFields r0 = new com.appsflyer.internal.referrer.MandatoryFields
            r0.<init>()
            r7.putAll(r0)
            android.content.Context r0 = r9.context     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            java.lang.String r2 = "content://"
            r1.<init>(r2)     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            java.lang.String r2 = r9.authority     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            java.lang.String r2 = "/item/5"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            r2 = 0
            r3 = 0
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            r5 = 0
            android.content.Context r8 = r9.context     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            java.lang.String r8 = r8.getPackageName()     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            r4[r5] = r8     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            r5 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            if (r1 == 0) goto L_0x00a4
            boolean r0 = r1.moveToFirst()     // Catch:{ Exception -> 0x0093 }
            if (r0 == 0) goto L_0x008b
            java.lang.String r0 = "response"
            java.lang.String r2 = "OK"
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r0 = "referrer"
            r2 = 0
            java.lang.String r2 = r1.getString(r2)     // Catch:{ Exception -> 0x0093 }
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r0 = "install_begin_ts"
            r2 = 1
            long r2 = r1.getLong(r2)     // Catch:{ Exception -> 0x0093 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Exception -> 0x0093 }
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r0 = "install_end_ts"
            r2 = 2
            long r2 = r1.getLong(r2)     // Catch:{ Exception -> 0x0093 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Exception -> 0x0093 }
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0093 }
        L_0x007e:
            if (r1 == 0) goto L_0x0083
            r1.close()
        L_0x0083:
            r9.map = r7
            java.lang.Runnable r0 = r9.f633
            r0.run()
            return r7
        L_0x008b:
            java.lang.String r0 = "response"
            java.lang.String r2 = "FEATURE_NOT_SUPPORTED"
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0093 }
            goto L_0x007e
        L_0x0093:
            r0 = move-exception
        L_0x0094:
            java.lang.String r2 = "response"
            java.lang.String r3 = "FEATURE_NOT_SUPPORTED"
            r7.put(r2, r3)     // Catch:{ all -> 0x00ac }
            r9.onError(r0)     // Catch:{ all -> 0x00ac }
            if (r1 == 0) goto L_0x0083
            r1.close()
            goto L_0x0083
        L_0x00a4:
            java.lang.String r0 = "response"
            java.lang.String r2 = "SERVICE_UNAVAILABLE"
            r7.put(r0, r2)     // Catch:{ Exception -> 0x0093 }
            goto L_0x007e
        L_0x00ac:
            r0 = move-exception
        L_0x00ad:
            if (r1 == 0) goto L_0x00b2
            r1.close()
        L_0x00b2:
            throw r0
        L_0x00b3:
            r0 = move-exception
            r1 = r6
            goto L_0x00ad
        L_0x00b6:
            r0 = move-exception
            r1 = r6
            goto L_0x0094
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.referrer.HuaweiReferrer.query():java.util.Map");
    }
}
