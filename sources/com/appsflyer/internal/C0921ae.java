package com.appsflyer.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.AppsFlyerProperties;
import java.io.File;
import java.lang.ref.WeakReference;
import java.security.SecureRandom;

/* renamed from: com.appsflyer.internal.ae */
public final class C0921ae {

    /* renamed from: ɩ */
    private static String f510 = null;

    C0921ae() {
    }

    /* renamed from: Ι */
    public static synchronized String m326(WeakReference<Context> weakReference) {
        String str;
        String str2 = null;
        synchronized (C0921ae.class) {
            if (weakReference.get() == null) {
                str = f510;
            } else {
                if (f510 == null) {
                    if (weakReference.get() != null) {
                        str2 = AppsFlyerLibCore.getSharedPreferences(weakReference.get()).getString("AF_INSTALLATION", (String) null);
                    }
                    if (str2 != null) {
                        f510 = str2;
                    } else {
                        try {
                            File file = new File(weakReference.get().getFilesDir(), "AF_INSTALLATION");
                            if (!file.exists()) {
                                f510 = new StringBuilder().append(System.currentTimeMillis()).append("-").append(Math.abs(new SecureRandom().nextLong())).toString();
                            } else {
                                f510 = m325(file);
                                file.delete();
                            }
                            String str3 = f510;
                            SharedPreferences.Editor edit = AppsFlyerLibCore.getSharedPreferences(weakReference.get()).edit();
                            edit.putString("AF_INSTALLATION", str3);
                            edit.apply();
                        } catch (Exception e) {
                            AFLogger.afErrorLog("Error getting AF unique ID", e);
                        }
                    }
                    if (f510 != null) {
                        AppsFlyerProperties.getInstance().set("uid", f510);
                    }
                }
                str = f510;
            }
        }
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0031 A[SYNTHETIC, Splitter:B:21:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0040 A[SYNTHETIC, Splitter:B:28:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x004b  */
    /* renamed from: ǃ */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m325(java.io.File r4) {
        /*
            r2 = 0
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x0027, all -> 0x003c }
            java.lang.String r0 = "r"
            r3.<init>(r4, r0)     // Catch:{ IOException -> 0x0027, all -> 0x003c }
            long r0 = r3.length()     // Catch:{ IOException -> 0x0051 }
            int r0 = (int) r0     // Catch:{ IOException -> 0x0051 }
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x0051 }
            r3.readFully(r0)     // Catch:{ IOException -> 0x0054 }
            r3.close()     // Catch:{ IOException -> 0x0054 }
            r3.close()     // Catch:{ IOException -> 0x0020 }
        L_0x0018:
            java.lang.String r1 = new java.lang.String
            if (r0 == 0) goto L_0x004b
        L_0x001c:
            r1.<init>(r0)
            return r1
        L_0x0020:
            r1 = move-exception
            java.lang.String r2 = "Exception while trying to close the InstallationFile"
            com.appsflyer.AFLogger.afErrorLog(r2, r1)
            goto L_0x0018
        L_0x0027:
            r1 = move-exception
            r0 = r2
            r3 = r2
        L_0x002a:
            java.lang.String r2 = "Exception while reading InstallationFile: "
            com.appsflyer.AFLogger.afErrorLog(r2, r1)     // Catch:{ all -> 0x004f }
            if (r3 == 0) goto L_0x0018
            r3.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0018
        L_0x0035:
            r1 = move-exception
            java.lang.String r2 = "Exception while trying to close the InstallationFile"
            com.appsflyer.AFLogger.afErrorLog(r2, r1)
            goto L_0x0018
        L_0x003c:
            r0 = move-exception
            r3 = r2
        L_0x003e:
            if (r3 == 0) goto L_0x0043
            r3.close()     // Catch:{ IOException -> 0x0044 }
        L_0x0043:
            throw r0
        L_0x0044:
            r1 = move-exception
            java.lang.String r2 = "Exception while trying to close the InstallationFile"
            com.appsflyer.AFLogger.afErrorLog(r2, r1)
            goto L_0x0043
        L_0x004b:
            r0 = 0
            byte[] r0 = new byte[r0]
            goto L_0x001c
        L_0x004f:
            r0 = move-exception
            goto L_0x003e
        L_0x0051:
            r1 = move-exception
            r0 = r2
            goto L_0x002a
        L_0x0054:
            r1 = move-exception
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0921ae.m325(java.io.File):java.lang.String");
    }
}
