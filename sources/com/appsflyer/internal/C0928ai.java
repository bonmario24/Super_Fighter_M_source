package com.appsflyer.internal;

import android.content.pm.PackageManager;
import android.os.Build;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.ServerParameters;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.appsflyer.internal.ai */
public final class C0928ai {

    /* renamed from: ɩ */
    public static C0928ai f525;

    /* renamed from: ı */
    private JSONArray f526;

    /* renamed from: ǃ */
    private JSONObject f527;

    /* renamed from: ɹ */
    private int f528 = 0;

    /* renamed from: Ι */
    private boolean f529 = true;

    /* renamed from: ι */
    public boolean f530;

    /* renamed from: І */
    private boolean f531 = AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DPM, false);

    /* renamed from: і */
    private boolean f532;

    /* renamed from: Ӏ */
    private String f533 = "-1";

    public C0928ai() {
        boolean z = true;
        this.f530 = this.f531 ? false : z;
        this.f526 = new JSONArray();
        this.f528 = 0;
        this.f532 = false;
    }

    /* renamed from: ɩ */
    public final synchronized void mo14693(String str) {
        this.f533 = str;
    }

    /* renamed from: ǃ */
    public final synchronized void mo14691() {
        this.f532 = true;
        mo14690("r_debugging_on", new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis())), new String[0]);
    }

    /* renamed from: ι */
    public final synchronized void mo14696() {
        mo14690("r_debugging_off", new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis())), new String[0]);
        this.f532 = false;
        this.f529 = false;
    }

    /* renamed from: ɩ */
    public final synchronized void mo14692() {
        this.f527 = null;
        this.f526 = null;
        f525 = null;
    }

    /* renamed from: ɹ */
    private boolean m339() {
        return this.f530 && (this.f529 || this.f532);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: ι */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m340(java.lang.String r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            r3 = this;
            monitor-enter(r3)
            org.json.JSONObject r0 = r3.f527     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
            java.lang.String r1 = "brand"
            r0.put(r1, r4)     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
            org.json.JSONObject r0 = r3.f527     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
            java.lang.String r1 = "model"
            r0.put(r1, r5)     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
            org.json.JSONObject r0 = r3.f527     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
            java.lang.String r1 = "platform"
            java.lang.String r2 = "Android"
            r0.put(r1, r2)     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
            org.json.JSONObject r0 = r3.f527     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
            java.lang.String r1 = "platform_version"
            r0.put(r1, r6)     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
            if (r7 == 0) goto L_0x002e
            int r0 = r7.length()     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
            if (r0 <= 0) goto L_0x002e
            org.json.JSONObject r0 = r3.f527     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
            java.lang.String r1 = "advertiserId"
            r0.put(r1, r7)     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
        L_0x002e:
            if (r8 == 0) goto L_0x003d
            int r0 = r8.length()     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
            if (r0 <= 0) goto L_0x003d
            org.json.JSONObject r0 = r3.f527     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
            java.lang.String r1 = "imei"
            r0.put(r1, r8)     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
        L_0x003d:
            if (r9 == 0) goto L_0x004c
            int r0 = r9.length()     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
            if (r0 <= 0) goto L_0x004c
            org.json.JSONObject r0 = r3.f527     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
            java.lang.String r1 = "android_id"
            r0.put(r1, r9)     // Catch:{ Throwable -> 0x0051, all -> 0x004e }
        L_0x004c:
            monitor-exit(r3)
            return
        L_0x004e:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x0051:
            r0 = move-exception
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0928ai.m340(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: ǃ */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m337(java.lang.String r3, java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            org.json.JSONObject r0 = r2.f527     // Catch:{ Throwable -> 0x003a, all -> 0x0037 }
            java.lang.String r1 = "sdk_version"
            r0.put(r1, r3)     // Catch:{ Throwable -> 0x003a, all -> 0x0037 }
            if (r4 == 0) goto L_0x0017
            int r0 = r4.length()     // Catch:{ Throwable -> 0x003a, all -> 0x0037 }
            if (r0 <= 0) goto L_0x0017
            org.json.JSONObject r0 = r2.f527     // Catch:{ Throwable -> 0x003a, all -> 0x0037 }
            java.lang.String r1 = "devkey"
            r0.put(r1, r4)     // Catch:{ Throwable -> 0x003a, all -> 0x0037 }
        L_0x0017:
            if (r5 == 0) goto L_0x0026
            int r0 = r5.length()     // Catch:{ Throwable -> 0x003a, all -> 0x0037 }
            if (r0 <= 0) goto L_0x0026
            org.json.JSONObject r0 = r2.f527     // Catch:{ Throwable -> 0x003a, all -> 0x0037 }
            java.lang.String r1 = "originalAppsFlyerId"
            r0.put(r1, r5)     // Catch:{ Throwable -> 0x003a, all -> 0x0037 }
        L_0x0026:
            if (r6 == 0) goto L_0x0035
            int r0 = r6.length()     // Catch:{ Throwable -> 0x003a, all -> 0x0037 }
            if (r0 <= 0) goto L_0x0035
            org.json.JSONObject r0 = r2.f527     // Catch:{ Throwable -> 0x003a, all -> 0x0037 }
            java.lang.String r1 = "uid"
            r0.put(r1, r6)     // Catch:{ Throwable -> 0x003a, all -> 0x0037 }
        L_0x0035:
            monitor-exit(r2)
            return
        L_0x0037:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x003a:
            r0 = move-exception
            goto L_0x0035
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0928ai.m337(java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: ɩ */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m338(java.lang.String r3, java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 == 0) goto L_0x0010
            int r0 = r3.length()     // Catch:{ Throwable -> 0x0042, all -> 0x003f }
            if (r0 <= 0) goto L_0x0010
            org.json.JSONObject r0 = r2.f527     // Catch:{ Throwable -> 0x0042, all -> 0x003f }
            java.lang.String r1 = "app_id"
            r0.put(r1, r3)     // Catch:{ Throwable -> 0x0042, all -> 0x003f }
        L_0x0010:
            if (r4 == 0) goto L_0x001f
            int r0 = r4.length()     // Catch:{ Throwable -> 0x0042, all -> 0x003f }
            if (r0 <= 0) goto L_0x001f
            org.json.JSONObject r0 = r2.f527     // Catch:{ Throwable -> 0x0042, all -> 0x003f }
            java.lang.String r1 = "app_version"
            r0.put(r1, r4)     // Catch:{ Throwable -> 0x0042, all -> 0x003f }
        L_0x001f:
            if (r5 == 0) goto L_0x002e
            int r0 = r5.length()     // Catch:{ Throwable -> 0x0042, all -> 0x003f }
            if (r0 <= 0) goto L_0x002e
            org.json.JSONObject r0 = r2.f527     // Catch:{ Throwable -> 0x0042, all -> 0x003f }
            java.lang.String r1 = "channel"
            r0.put(r1, r5)     // Catch:{ Throwable -> 0x0042, all -> 0x003f }
        L_0x002e:
            if (r6 == 0) goto L_0x003d
            int r0 = r6.length()     // Catch:{ Throwable -> 0x0042, all -> 0x003f }
            if (r0 <= 0) goto L_0x003d
            org.json.JSONObject r0 = r2.f527     // Catch:{ Throwable -> 0x0042, all -> 0x003f }
            java.lang.String r1 = "preInstall"
            r0.put(r1, r6)     // Catch:{ Throwable -> 0x0042, all -> 0x003f }
        L_0x003d:
            monitor-exit(r2)
            return
        L_0x003f:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x0042:
            r0 = move-exception
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0928ai.m338(java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* renamed from: ı */
    public final synchronized void mo14690(String str, String str2, String... strArr) {
        String format;
        if (m339() && this.f528 < 98304) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                String str3 = "";
                if (strArr.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int length = strArr.length - 1; length > 0; length--) {
                        sb.append(strArr[length]).append(", ");
                    }
                    sb.append(strArr[0]);
                    str3 = sb.toString();
                }
                String format2 = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.ENGLISH).format(Long.valueOf(currentTimeMillis));
                if (str != null) {
                    format = String.format("%18s %5s _/%s [%s] %s %s", new Object[]{format2, Long.valueOf(Thread.currentThread().getId()), AppsFlyerLibCore.LOG_TAG, str, str2, str3});
                } else {
                    format = String.format("%18s %5s %s/%s %s", new Object[]{format2, Long.valueOf(Thread.currentThread().getId()), str2, AppsFlyerLibCore.LOG_TAG, str3});
                }
                this.f526.put(format);
                this.f528 = format.getBytes().length + this.f528;
            } catch (Throwable th) {
            }
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: Ι */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.String mo14694() {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            org.json.JSONObject r1 = r4.f527     // Catch:{ JSONException -> 0x0019, all -> 0x0016 }
            java.lang.String r2 = "data"
            org.json.JSONArray r3 = r4.f526     // Catch:{ JSONException -> 0x0019, all -> 0x0016 }
            r1.put(r2, r3)     // Catch:{ JSONException -> 0x0019, all -> 0x0016 }
            org.json.JSONObject r1 = r4.f527     // Catch:{ JSONException -> 0x0019, all -> 0x0016 }
            java.lang.String r0 = r1.toString()     // Catch:{ JSONException -> 0x0019, all -> 0x0016 }
            r4.m341()     // Catch:{ JSONException -> 0x0019, all -> 0x0016 }
        L_0x0014:
            monitor-exit(r4)
            return r0
        L_0x0016:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x0019:
            r1 = move-exception
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0928ai.mo14694():java.lang.String");
    }

    /* renamed from: Ι */
    public final synchronized void mo14695(String str, PackageManager packageManager) {
        AppsFlyerProperties instance = AppsFlyerProperties.getInstance();
        AppsFlyerLibCore instance2 = AppsFlyerLibCore.getInstance();
        String string = instance.getString("remote_debug_static_data");
        if (string != null) {
            try {
                this.f527 = new JSONObject(string);
            } catch (Throwable th) {
            }
        } else {
            this.f527 = new JSONObject();
            m340(Build.BRAND, Build.MODEL, Build.VERSION.RELEASE, instance.getString(ServerParameters.ADVERTISING_ID_PARAM), instance2.f427, instance2.f428);
            m337(new StringBuilder("5.4.0.").append(AppsFlyerLibCore.f403).toString(), instance.getString(AppsFlyerProperties.AF_KEY), instance.getString("KSAppsFlyerId"), instance.getString("uid"));
            try {
                int i = packageManager.getPackageInfo(str, 0).versionCode;
                m338(str, String.valueOf(i), instance.getString(AppsFlyerProperties.CHANNEL), instance.getString("preInstallName"));
            } catch (Throwable th2) {
            }
            instance.set("remote_debug_static_data", this.f527.toString());
        }
        try {
            this.f527.put("launch_counter", this.f533);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return;
    }

    /* renamed from: і */
    private synchronized void m341() {
        this.f526 = null;
        this.f526 = new JSONArray();
        this.f528 = 0;
    }

    /* renamed from: ı */
    public final synchronized void mo14689() {
        this.f529 = false;
        m341();
    }

    /* renamed from: І */
    public final boolean mo14697() {
        return this.f532;
    }
}
