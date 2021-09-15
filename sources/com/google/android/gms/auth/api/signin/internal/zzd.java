package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;

public final class zzd implements Runnable {
    private static final Logger zzbd = new Logger("RevokeAccessOperation", new String[0]);
    private final String zzbe;
    private final StatusPendingResult zzbf = new StatusPendingResult((GoogleApiClient) null);

    private zzd(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzbe = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r6 = this;
            r5 = 0
            com.google.android.gms.common.api.Status r1 = com.google.android.gms.common.api.Status.RESULT_INTERNAL_ERROR
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            java.lang.String r0 = "https://accounts.google.com/o/oauth2/revoke?token="
            java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            java.lang.String r0 = r6.zzbe     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            int r4 = r0.length()     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            if (r4 == 0) goto L_0x0058
            java.lang.String r0 = r3.concat(r0)     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
        L_0x001b:
            r2.<init>(r0)     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            java.net.URLConnection r0 = r2.openConnection()     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            java.lang.String r2 = "Content-Type"
            java.lang.String r3 = "application/x-www-form-urlencoded"
            r0.setRequestProperty(r2, r3)     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            int r2 = r0.getResponseCode()     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            r0 = 200(0xc8, float:2.8E-43)
            if (r2 != r0) goto L_0x007d
            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.RESULT_SUCCESS     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
        L_0x0035:
            com.google.android.gms.common.logging.Logger r1 = zzbd     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b4 }
            r3 = 26
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b4 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b4 }
            java.lang.String r3 = "Response Code: "
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b4 }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b4 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b4 }
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b4 }
            r1.mo21222d(r2, r3)     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b4 }
        L_0x0052:
            com.google.android.gms.common.api.internal.StatusPendingResult r1 = r6.zzbf
            r1.setResult(r0)
            return
        L_0x0058:
            java.lang.String r0 = new java.lang.String     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            r0.<init>(r3)     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            goto L_0x001b
        L_0x005e:
            r0 = move-exception
            r2 = r0
        L_0x0060:
            com.google.android.gms.common.logging.Logger r3 = zzbd
            java.lang.String r4 = "IOException when revoking access: "
            java.lang.String r0 = r2.toString()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r2 = r0.length()
            if (r2 == 0) goto L_0x0089
            java.lang.String r0 = r4.concat(r0)
        L_0x0076:
            java.lang.Object[] r2 = new java.lang.Object[r5]
            r3.mo21224e(r0, r2)
            r0 = r1
            goto L_0x0052
        L_0x007d:
            com.google.android.gms.common.logging.Logger r0 = zzbd     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            java.lang.String r3 = "Unable to revoke access!"
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            r0.mo21224e(r3, r4)     // Catch:{ IOException -> 0x005e, Exception -> 0x008f }
            r0 = r1
            goto L_0x0035
        L_0x0089:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4)
            goto L_0x0076
        L_0x008f:
            r0 = move-exception
            r2 = r0
        L_0x0091:
            com.google.android.gms.common.logging.Logger r3 = zzbd
            java.lang.String r4 = "Exception when revoking access: "
            java.lang.String r0 = r2.toString()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r2 = r0.length()
            if (r2 == 0) goto L_0x00ae
            java.lang.String r0 = r4.concat(r0)
        L_0x00a7:
            java.lang.Object[] r2 = new java.lang.Object[r5]
            r3.mo21224e(r0, r2)
            r0 = r1
            goto L_0x0052
        L_0x00ae:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4)
            goto L_0x00a7
        L_0x00b4:
            r2 = move-exception
            r1 = r0
            goto L_0x0091
        L_0x00b7:
            r2 = move-exception
            r1 = r0
            goto L_0x0060
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.internal.zzd.run():void");
    }

    public static PendingResult<Status> zzc(String str) {
        if (str == null) {
            return PendingResults.immediateFailedResult(new Status(4), (GoogleApiClient) null);
        }
        zzd zzd = new zzd(str);
        new Thread(zzd).start();
        return zzd.zzbf;
    }
}
