package com.appsflyer;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import com.eagle.mixsdk.sdk.utils.RSAUtils;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Calendar;
import javax.security.auth.x500.X500Principal;

class AFKeystoreWrapper {

    /* renamed from: ı */
    final Object f373 = new Object();

    /* renamed from: ǃ */
    private Context f374;

    /* renamed from: ɩ */
    int f375;

    /* renamed from: Ι */
    KeyStore f376;

    /* renamed from: ι */
    String f377;

    public AFKeystoreWrapper(Context context) {
        this.f374 = context;
        this.f377 = "";
        this.f375 = 0;
        AFLogger.afInfoLog("Initialising KeyStore..");
        try {
            this.f376 = KeyStore.getInstance("AndroidKeyStore");
            this.f376.load((KeyStore.LoadStoreParameter) null);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            AFLogger.afErrorLog("Couldn't load keystore instance of type: AndroidKeyStore", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        r4 = r0.split(",");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        if (r4.length != 3) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        com.appsflyer.AFLogger.afInfoLog("Found a matching AF key with alias:\n".concat(java.lang.String.valueOf(r0)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0 = r4[1].trim().split("=");
        r2 = r4[2].trim().split("=");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        if (r0.length != 2) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0059, code lost:
        if (r2.length != 2) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005b, code lost:
        r8.f377 = r0[1].trim();
        r8.f375 = java.lang.Integer.parseInt(r2[1].trim());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0095, code lost:
        r0 = th;
     */
    /* renamed from: ǃ */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo14512() {
        /*
            r8 = this;
            r7 = 2
            r1 = 1
            r2 = 0
            java.lang.Object r3 = r8.f373
            monitor-enter(r3)
            java.security.KeyStore r0 = r8.f376     // Catch:{ all -> 0x0092 }
            if (r0 == 0) goto L_0x0097
            java.security.KeyStore r0 = r8.f376     // Catch:{ Throwable -> 0x0075 }
            java.util.Enumeration r4 = r0.aliases()     // Catch:{ Throwable -> 0x0075 }
        L_0x0010:
            boolean r0 = r4.hasMoreElements()     // Catch:{ Throwable -> 0x0075 }
            if (r0 == 0) goto L_0x0073
            java.lang.Object r0 = r4.nextElement()     // Catch:{ Throwable -> 0x0075 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0075 }
            if (r0 == 0) goto L_0x0010
            boolean r5 = m210(r0)     // Catch:{ Throwable -> 0x0075 }
            if (r5 == 0) goto L_0x0010
            java.lang.String r4 = ","
            java.lang.String[] r4 = r0.split(r4)     // Catch:{ Throwable -> 0x0075 }
            int r5 = r4.length     // Catch:{ Throwable -> 0x0075 }
            r6 = 3
            if (r5 != r6) goto L_0x0073
            java.lang.String r5 = "Found a matching AF key with alias:\n"
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x0075 }
            java.lang.String r0 = r5.concat(r0)     // Catch:{ Throwable -> 0x0075 }
            com.appsflyer.AFLogger.afInfoLog(r0)     // Catch:{ Throwable -> 0x0075 }
            r0 = 1
            r0 = r4[r0]     // Catch:{ Throwable -> 0x0095 }
            java.lang.String r0 = r0.trim()     // Catch:{ Throwable -> 0x0095 }
            java.lang.String r2 = "="
            java.lang.String[] r0 = r0.split(r2)     // Catch:{ Throwable -> 0x0095 }
            r2 = 2
            r2 = r4[r2]     // Catch:{ Throwable -> 0x0095 }
            java.lang.String r2 = r2.trim()     // Catch:{ Throwable -> 0x0095 }
            java.lang.String r4 = "="
            java.lang.String[] r2 = r2.split(r4)     // Catch:{ Throwable -> 0x0095 }
            int r4 = r0.length     // Catch:{ Throwable -> 0x0095 }
            if (r4 != r7) goto L_0x0071
            int r4 = r2.length     // Catch:{ Throwable -> 0x0095 }
            if (r4 != r7) goto L_0x0071
            r4 = 1
            r0 = r0[r4]     // Catch:{ Throwable -> 0x0095 }
            java.lang.String r0 = r0.trim()     // Catch:{ Throwable -> 0x0095 }
            r8.f377 = r0     // Catch:{ Throwable -> 0x0095 }
            r0 = 1
            r0 = r2[r0]     // Catch:{ Throwable -> 0x0095 }
            java.lang.String r0 = r0.trim()     // Catch:{ Throwable -> 0x0095 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Throwable -> 0x0095 }
            r8.f375 = r0     // Catch:{ Throwable -> 0x0095 }
        L_0x0071:
            monitor-exit(r3)     // Catch:{ all -> 0x0092 }
            return r1
        L_0x0073:
            r1 = r2
            goto L_0x0071
        L_0x0075:
            r0 = move-exception
            r1 = r2
        L_0x0077:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0092 }
            java.lang.String r4 = "Couldn't list KeyStore Aliases: "
            r2.<init>(r4)     // Catch:{ all -> 0x0092 }
            java.lang.Class r4 = r0.getClass()     // Catch:{ all -> 0x0092 }
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x0092 }
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x0092 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0092 }
            com.appsflyer.AFLogger.afErrorLog(r2, r0)     // Catch:{ all -> 0x0092 }
            goto L_0x0071
        L_0x0092:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x0095:
            r0 = move-exception
            goto L_0x0077
        L_0x0097:
            r1 = r2
            goto L_0x0071
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AFKeystoreWrapper.mo14512():boolean");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ɩ */
    public final void mo14514(String str) {
        AFLogger.afInfoLog("Creating a new key with alias: ".concat(String.valueOf(str)));
        try {
            Calendar instance = Calendar.getInstance();
            Calendar instance2 = Calendar.getInstance();
            instance2.add(1, 5);
            AlgorithmParameterSpec algorithmParameterSpec = null;
            synchronized (this.f373) {
                if (!this.f376.containsAlias(str)) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        algorithmParameterSpec = new KeyGenParameterSpec.Builder(str, 3).setCertificateSubject(new X500Principal("CN=AndroidSDK, O=AppsFlyer")).setCertificateSerialNumber(BigInteger.ONE).setCertificateNotBefore(instance.getTime()).setCertificateNotAfter(instance2.getTime()).build();
                    } else if (Build.VERSION.SDK_INT >= 18 && !AndroidUtils.m232()) {
                        algorithmParameterSpec = new KeyPairGeneratorSpec.Builder(this.f374).setAlias(str).setSubject(new X500Principal("CN=AndroidSDK, O=AppsFlyer")).setSerialNumber(BigInteger.ONE).setStartDate(instance.getTime()).setEndDate(instance2.getTime()).build();
                    }
                    KeyPairGenerator instance3 = KeyPairGenerator.getInstance(RSAUtils.KEY_ALGORITHM, "AndroidKeyStore");
                    instance3.initialize(algorithmParameterSpec);
                    instance3.generateKeyPair();
                } else {
                    AFLogger.afInfoLog("Alias already exists: ".concat(String.valueOf(str)));
                }
            }
        } catch (Throwable th) {
            AFLogger.afErrorLog(new StringBuilder("Exception ").append(th.getMessage()).append(" occurred").toString(), th);
        }
    }

    /* renamed from: Ι */
    private static boolean m210(String str) {
        return str.startsWith("com.appsflyer");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ɩ */
    public final String mo14513() {
        StringBuilder sb = new StringBuilder();
        sb.append("com.appsflyer,");
        synchronized (this.f373) {
            sb.append("KSAppsFlyerId=").append(this.f377).append(",");
            sb.append("KSAppsFlyerRICounter=").append(this.f375);
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ı */
    public final String mo14511() {
        String str;
        synchronized (this.f373) {
            str = this.f377;
        }
        return str;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ι */
    public final int mo14515() {
        int i;
        synchronized (this.f373) {
            i = this.f375;
        }
        return i;
    }
}
