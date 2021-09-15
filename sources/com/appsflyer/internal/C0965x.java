package com.appsflyer.internal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.appsflyer.AFEvent;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerInAppPurchaseValidatorListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.ServerConfigHandler;
import com.appsflyer.ServerParameters;
import com.appsflyer.internal.model.event.Purchase;
import com.appsflyer.internal.model.event.SdkServices;
import com.appsflyer.internal.model.event.Validate;
import com.appsflyer.internal.referrer.Payload;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.appsflyer.internal.x */
public final class C0965x implements Runnable {

    /* renamed from: ǃ */
    private static String f650 = new StringBuilder("https://%svalidate.%s/api/v").append(AppsFlyerLibCore.f400).append("/androidevent?buildnumber=5.4.0&app_id=").toString();

    /* renamed from: ι */
    private static String f651 = "https://%ssdk-services.%s/validate-android-signature";
    /* access modifiers changed from: private */

    /* renamed from: ı */
    public WeakReference<Context> f652;

    /* renamed from: Ɩ */
    private String f653;
    /* access modifiers changed from: private */

    /* renamed from: ȷ */
    public Map<String, String> f654;

    /* renamed from: ɩ */
    private String f655;

    /* renamed from: ɹ */
    private String f656;

    /* renamed from: Ι */
    private final Intent f657;

    /* renamed from: І */
    private String f658;

    /* renamed from: і */
    private String f659;

    /* renamed from: Ӏ */
    private String f660;

    /* renamed from: Ι */
    static /* synthetic */ void m407(C0965x xVar, Map map, Map map2, WeakReference weakReference) {
        if (weakReference.get() != null) {
            String obj = new StringBuilder().append(ServerConfigHandler.getUrl(f650)).append(((Context) weakReference.get()).getPackageName()).toString();
            String string = AppsFlyerLibCore.getSharedPreferences((Context) weakReference.get()).getString(Payload.REFERRER, "");
            AFEvent key = new Validate((Context) weakReference.get()).key(xVar.f655);
            key.f350 = string;
            key.f351 = xVar.f657;
            Validate validate = (Validate) key;
            Map<String, Object> r3 = AppsFlyerLibCore.getInstance().mo14603((AFEvent) validate);
            r3.put(FirebaseAnalytics.Param.PRICE, xVar.f653);
            r3.put(FirebaseAnalytics.Param.CURRENCY, xVar.f656);
            r3.put("receipt_data", map);
            if (map2 != null) {
                r3.put("extra_prms", map2);
            }
            String jSONObject = new JSONObject(r3).toString();
            if (C0928ai.f525 == null) {
                C0928ai.f525 = new C0928ai();
            }
            C0928ai.f525.mo14690("server_request", obj, jSONObject);
            HttpURLConnection httpURLConnection = null;
            try {
                HttpURLConnection r1 = m406((Purchase) validate.params(r3).urlString(obj));
                int i = -1;
                if (r1 != null) {
                    i = r1.getResponseCode();
                }
                String r32 = AppsFlyerLibCore.getInstance().mo14600(r1);
                if (C0928ai.f525 == null) {
                    C0928ai.f525 = new C0928ai();
                }
                C0928ai.f525.mo14690("server_response", obj, String.valueOf(i), r32);
                AFLogger.afInfoLog(new StringBuilder("Validate-WH response - ").append(i).append(": ").append(new JSONObject(r32).toString()).toString());
                if (r1 != null) {
                    r1.disconnect();
                }
            } catch (Throwable th) {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        }
    }

    public C0965x(Context context, String str, String str2, String str3, String str4, String str5, String str6, Map<String, String> map, @Nullable Intent intent) {
        this.f652 = new WeakReference<>(context);
        this.f655 = str;
        this.f658 = str2;
        this.f660 = str4;
        this.f653 = str5;
        this.f656 = str6;
        this.f654 = map;
        this.f659 = str3;
        this.f657 = intent;
    }

    public final void run() {
        if (this.f655 != null && this.f655.length() != 0 && !AppsFlyerLib.getInstance().isTrackingStopped()) {
            HttpURLConnection httpURLConnection = null;
            try {
                Context context = this.f652.get();
                if (context != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("public-key", this.f658);
                    hashMap.put("sig-data", this.f660);
                    hashMap.put("signature", this.f659);
                    final HashMap hashMap2 = new HashMap(hashMap);
                    new Thread(new Runnable() {
                        public final void run() {
                            C0965x.m407(C0965x.this, hashMap2, C0965x.this.f654, C0965x.this.f652);
                        }
                    }).start();
                    hashMap.put("dev_key", this.f655);
                    hashMap.put("app_id", context.getPackageName());
                    hashMap.put("uid", AppsFlyerLib.getInstance().getAppsFlyerUID(context));
                    hashMap.put(ServerParameters.ADVERTISING_ID_PARAM, AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM));
                    String jSONObject = new JSONObject(hashMap).toString();
                    String url = ServerConfigHandler.getUrl(f651);
                    if (C0928ai.f525 == null) {
                        C0928ai.f525 = new C0928ai();
                    }
                    C0928ai.f525.mo14690("server_request", url, jSONObject);
                    httpURLConnection = m406((Purchase) new SdkServices().params(hashMap).urlString(url));
                    int i = -1;
                    if (httpURLConnection != null) {
                        i = httpURLConnection.getResponseCode();
                    }
                    String r2 = AppsFlyerLibCore.getInstance().mo14600(httpURLConnection);
                    if (C0928ai.f525 == null) {
                        C0928ai.f525 = new C0928ai();
                    }
                    C0928ai.f525.mo14690("server_response", url, String.valueOf(i), r2);
                    JSONObject jSONObject2 = new JSONObject(r2);
                    jSONObject2.put("code", i);
                    if (i == 200) {
                        AFLogger.afInfoLog(new StringBuilder("Validate response 200 ok: ").append(jSONObject2.toString()).toString());
                        m409(jSONObject2.optBoolean("result"), this.f660, this.f653, this.f656, jSONObject2.toString());
                    } else {
                        AFLogger.afInfoLog("Failed Validate request");
                        m409(false, this.f660, this.f653, this.f656, jSONObject2.toString());
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            } catch (Throwable th) {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        }
    }

    /* renamed from: ǃ */
    private static HttpURLConnection m406(@NonNull Purchase purchase) {
        AFLogger.afDebugLog(new StringBuilder("Calling ").append(purchase.urlString()).toString());
        return new C0920ad(purchase.trackingStopped(AppsFlyerLib.getInstance().isTrackingStopped())).mo14686();
    }

    /* renamed from: ι */
    private static void m409(boolean z, String str, String str2, String str3, String str4) {
        if (AppsFlyerLibCore.f402 != null) {
            AFLogger.afDebugLog(new StringBuilder("Validate callback parameters: ").append(str).append(" ").append(str2).append(" ").append(str3).toString());
            if (z) {
                AFLogger.afDebugLog("Validate in app purchase success: ".concat(String.valueOf(str4)));
                AppsFlyerLibCore.f402.onValidateInApp();
                return;
            }
            AFLogger.afDebugLog("Validate in app purchase failed: ".concat(String.valueOf(str4)));
            AppsFlyerInAppPurchaseValidatorListener appsFlyerInAppPurchaseValidatorListener = AppsFlyerLibCore.f402;
            if (str4 == null) {
                str4 = "Failed validating";
            }
            appsFlyerInAppPurchaseValidatorListener.onValidateInAppFailure(str4);
        }
    }
}
