package com.appsflyer.internal;

import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* renamed from: com.appsflyer.internal.s */
public final class C0951s {

    /* renamed from: com.appsflyer.internal.s$d */
    public static final class C0953d {

        /* renamed from: ι */
        public static final C0951s f637 = new C0951s();
    }

    C0951s() {
    }

    /* renamed from: ɩ */
    private static boolean m395(NetworkInfo networkInfo) {
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002c, code lost:
        if (1 != r6.getType()) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        r3 = "WIFI";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        if (r1.isEmpty() != false) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005c, code lost:
        if (r6.getType() != 0) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005e, code lost:
        r3 = "MOBILE";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0061, code lost:
        r3 = "unknown";
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042 A[SYNTHETIC, Splitter:B:17:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004f A[Catch:{ Throwable -> 0x00b4 }] */
    /* renamed from: ǃ */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.appsflyer.internal.C0951s.C0952a m394(@android.support.annotation.NonNull android.content.Context r9) {
        /*
            r5 = 0
            r1 = 0
            r8 = 1
            java.lang.String r3 = "unknown"
            java.lang.String r0 = "connectivity"
            java.lang.Object r0 = r9.getSystemService(r0)     // Catch:{ Throwable -> 0x00a5 }
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0     // Catch:{ Throwable -> 0x00a5 }
            if (r0 == 0) goto L_0x00a2
            r2 = 21
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00a5 }
            if (r2 > r4) goto L_0x006a
            android.net.Network[] r2 = r0.getAllNetworks()     // Catch:{ Throwable -> 0x00a5 }
            int r4 = r2.length     // Catch:{ Throwable -> 0x00a5 }
        L_0x001a:
            if (r1 >= r4) goto L_0x0067
            r6 = r2[r1]     // Catch:{ Throwable -> 0x00a5 }
            android.net.NetworkInfo r6 = r0.getNetworkInfo(r6)     // Catch:{ Throwable -> 0x00a5 }
            boolean r7 = m395(r6)     // Catch:{ Throwable -> 0x00a5 }
            if (r7 == 0) goto L_0x0064
            int r0 = r6.getType()     // Catch:{ Throwable -> 0x00a5 }
            if (r8 != r0) goto L_0x0058
            java.lang.String r3 = "WIFI"
        L_0x0030:
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r9.getSystemService(r0)     // Catch:{ Throwable -> 0x00a5 }
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r2 = r0.getSimOperatorName()     // Catch:{ Throwable -> 0x00a5 }
            java.lang.String r1 = r0.getNetworkOperatorName()     // Catch:{ Throwable -> 0x00b0 }
            if (r1 == 0) goto L_0x0048
            boolean r4 = r1.isEmpty()     // Catch:{ Throwable -> 0x00b4 }
            if (r4 == 0) goto L_0x00b7
        L_0x0048:
            r4 = 2
            int r0 = r0.getPhoneType()     // Catch:{ Throwable -> 0x00b4 }
            if (r4 != r0) goto L_0x00b7
            java.lang.String r0 = "CDMA"
        L_0x0051:
            r1 = r2
        L_0x0052:
            com.appsflyer.internal.s$a r2 = new com.appsflyer.internal.s$a
            r2.<init>(r3, r0, r1)
            return r2
        L_0x0058:
            int r0 = r6.getType()     // Catch:{ Throwable -> 0x00a5 }
            if (r0 != 0) goto L_0x0061
            java.lang.String r3 = "MOBILE"
            goto L_0x0030
        L_0x0061:
            java.lang.String r3 = "unknown"
            goto L_0x0030
        L_0x0064:
            int r1 = r1 + 1
            goto L_0x001a
        L_0x0067:
            java.lang.String r3 = "unknown"
            goto L_0x0030
        L_0x006a:
            r1 = 1
            android.net.NetworkInfo r1 = r0.getNetworkInfo(r1)     // Catch:{ Throwable -> 0x00a5 }
            boolean r1 = m395(r1)     // Catch:{ Throwable -> 0x00a5 }
            if (r1 == 0) goto L_0x0078
            java.lang.String r3 = "WIFI"
            goto L_0x0030
        L_0x0078:
            r1 = 0
            android.net.NetworkInfo r1 = r0.getNetworkInfo(r1)     // Catch:{ Throwable -> 0x00a5 }
            boolean r1 = m395(r1)     // Catch:{ Throwable -> 0x00a5 }
            if (r1 == 0) goto L_0x0086
            java.lang.String r3 = "MOBILE"
            goto L_0x0030
        L_0x0086:
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()     // Catch:{ Throwable -> 0x00a5 }
            boolean r1 = m395(r0)     // Catch:{ Throwable -> 0x00a5 }
            if (r1 == 0) goto L_0x00a2
            int r1 = r0.getType()     // Catch:{ Throwable -> 0x00a5 }
            if (r8 != r1) goto L_0x0099
            java.lang.String r3 = "WIFI"
            goto L_0x0030
        L_0x0099:
            int r0 = r0.getType()     // Catch:{ Throwable -> 0x00a5 }
            if (r0 != 0) goto L_0x00a2
            java.lang.String r3 = "MOBILE"
            goto L_0x0030
        L_0x00a2:
            java.lang.String r3 = "unknown"
            goto L_0x0030
        L_0x00a5:
            r1 = move-exception
            r4 = r1
            r0 = r5
            r2 = r5
        L_0x00a9:
            java.lang.String r1 = "Exception while collecting network info. "
            com.appsflyer.AFLogger.afErrorLog(r1, r4)
            r1 = r2
            goto L_0x0052
        L_0x00b0:
            r1 = move-exception
            r4 = r1
            r0 = r5
            goto L_0x00a9
        L_0x00b4:
            r4 = move-exception
            r0 = r1
            goto L_0x00a9
        L_0x00b7:
            r0 = r1
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0951s.m394(android.content.Context):com.appsflyer.internal.s$a");
    }

    /* renamed from: com.appsflyer.internal.s$a */
    public static final class C0952a {

        /* renamed from: ı */
        public final String f634;

        /* renamed from: ɩ */
        public final String f635;

        /* renamed from: ι */
        public final String f636;

        C0952a(@NonNull String str, @Nullable String str2, @Nullable String str3) {
            this.f634 = str;
            this.f635 = str2;
            this.f636 = str3;
        }
    }
}
