package com.appsflyer;

import android.app.Activity;
import android.app.Application;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import com.appsflyer.AFFacebookDeferredDeeplink;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.Foreground;
import com.appsflyer.OneLinkHttpTask;
import com.appsflyer.internal.C0916aa;
import com.appsflyer.internal.C0917ab;
import com.appsflyer.internal.C0919ac;
import com.appsflyer.internal.C0920ad;
import com.appsflyer.internal.C0921ae;
import com.appsflyer.internal.C0922af;
import com.appsflyer.internal.C0923ag;
import com.appsflyer.internal.C0927ah;
import com.appsflyer.internal.C0928ai;
import com.appsflyer.internal.C0934b;
import com.appsflyer.internal.C0937c;
import com.appsflyer.internal.C0941g;
import com.appsflyer.internal.C0945j;
import com.appsflyer.internal.C0946p;
import com.appsflyer.internal.C0948q;
import com.appsflyer.internal.C0951s;
import com.appsflyer.internal.C0954t;
import com.appsflyer.internal.C0961v;
import com.appsflyer.internal.C0963w;
import com.appsflyer.internal.C0965x;
import com.appsflyer.internal.C0967y;
import com.appsflyer.internal.C0968z;
import com.appsflyer.internal.EventDataCollector;
import com.appsflyer.internal.instant.AFInstantApps;
import com.appsflyer.internal.model.event.AdRevenue;
import com.appsflyer.internal.model.event.Attr;
import com.appsflyer.internal.model.event.BackgroundEvent;
import com.appsflyer.internal.model.event.BackgroundReferrerLaunch;
import com.appsflyer.internal.model.event.CachedEvent;
import com.appsflyer.internal.model.event.InAppEvent;
import com.appsflyer.internal.model.event.Launch;
import com.appsflyer.internal.model.event.ProxyEvent;
import com.appsflyer.internal.model.event.Stats;
import com.appsflyer.internal.model.event.UninstallTokenEvent;
import com.appsflyer.internal.referrer.GoogleReferrer;
import com.appsflyer.internal.referrer.HuaweiReferrer;
import com.appsflyer.internal.referrer.Payload;
import com.appsflyer.oaid.OaidClient;
import com.appsflyer.share.Constants;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.UserDataStore;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.xhuyu.component.core.config.Constant;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.NetworkInterface;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppsFlyerLibCore extends AppsFlyerLib {
    public static final String AF_PRE_INSTALL_PATH = "AF_PRE_INSTALL_PATH";
    public static String FIRST_LAUNCHES_URL = new StringBuilder("https://%sconversions.%s/api/v").append(f408).toString();
    @VisibleForTesting
    public static final String INSTALL_REFERRER_PREF = "rfr";
    public static final String IS_STOP_TRACKING_USED = "is_stop_tracking_used";
    public static final String LOG_TAG = "AppsFlyer_5.4.0";
    public static final String PRE_INSTALL_SYSTEM_DEFAULT = "/data/local/tmp/pre_install.appsflyer";
    public static final String PRE_INSTALL_SYSTEM_DEFAULT_ETC = "/etc/pre_install.appsflyer";
    public static final String PRE_INSTALL_SYSTEM_RO_PROP = "ro.appsflyer.preinstall.path";
    @VisibleForTesting
    public static String REFERRER_TRACKING_URL = new StringBuilder("https://%sattr.%s/api/v").append(f408).toString();
    public static String REGISTER_URL = new StringBuilder("https://%sregister.%s/api/v").append(f401).toString();
    @VisibleForTesting
    public static AppsFlyerLibCore instance = new AppsFlyerLibCore();

    /* renamed from: ı */
    public static final String f400 = BuildConfig.VERSION_NAME.substring(0, BuildConfig.VERSION_NAME.lastIndexOf("."));

    /* renamed from: Ɩ */
    private static final String f401 = new StringBuilder().append(f400).append("/androidevent?buildnumber=5.4.0&app_id=").toString();

    /* renamed from: ǃ */
    public static AppsFlyerInAppPurchaseValidatorListener f402 = null;

    /* renamed from: ɩ */
    public static final String f403 = "38";
    /* access modifiers changed from: private */

    /* renamed from: ɪ */
    public static AppsFlyerConversionListener f404 = null;

    /* renamed from: ɹ */
    private static String f405 = new StringBuilder("https://%slaunches.%s/api/v").append(f408).toString();

    /* renamed from: ɾ */
    private static String f406 = new StringBuilder("https://%sinapps.%s/api/v").append(f408).toString();

    /* renamed from: І */
    private static String f407 = new StringBuilder("https://%sadrevenue.%s/api/v").append(f400).append("/android?buildnumber=5.4.0&app_id=").toString();

    /* renamed from: і */
    private static final String f408 = new StringBuilder().append(f400).append("/androidevent?app_id=").toString();

    /* renamed from: ӏ */
    private static final List<String> f409 = Arrays.asList(new String[]{"is_cache"});
    protected Uri latestDeepLink = null;

    /* renamed from: ŀ */
    private long f410 = -1;
    /* access modifiers changed from: private */

    /* renamed from: ł */
    public ScheduledExecutorService f411 = null;
    /* access modifiers changed from: private */

    /* renamed from: ſ */
    public long f412;

    /* renamed from: Ɨ */
    private String f413;

    /* renamed from: ƚ */
    private Map<Long, String> f414;

    /* renamed from: ǀ */
    private boolean f415;

    /* renamed from: ȷ */
    private long f416 = -1;

    /* renamed from: ɍ */
    private boolean f417 = false;

    /* renamed from: ɔ */
    private boolean f418 = false;

    /* renamed from: ɟ */
    private C0923ag f419 = new C0923ag();

    /* renamed from: ɨ */
    private String f420;

    /* renamed from: ɺ */
    private boolean f421;

    /* renamed from: ɼ */
    private boolean f422 = false;

    /* renamed from: ɿ */
    private long f423 = TimeUnit.SECONDS.toMillis(5);
    /* access modifiers changed from: private */

    /* renamed from: ʅ */
    public long f424;
    /* access modifiers changed from: private */
    @Nullable

    /* renamed from: ʟ */
    public GoogleReferrer f425;
    /* access modifiers changed from: private */

    /* renamed from: ͻ */
    public Map<String, Object> f426;

    /* renamed from: Ι */
    public String f427;

    /* renamed from: ι */
    public String f428;
    /* access modifiers changed from: private */

    /* renamed from: ϲ */
    public Map<String, Object> f429;

    /* renamed from: ϳ */
    private Application f430;

    /* renamed from: Ј */
    private boolean f431 = false;
    /* access modifiers changed from: private */

    /* renamed from: г */
    public boolean f432 = false;
    /* access modifiers changed from: private */

    /* renamed from: с */
    public boolean f433 = false;
    /* access modifiers changed from: private */

    /* renamed from: т */
    public HuaweiReferrer f434;

    /* renamed from: х */
    private String f435;
    @VisibleForTesting

    /* renamed from: Ӏ */
    long f436;

    /* renamed from: ı */
    static /* synthetic */ void m243(AppsFlyerLibCore appsFlyerLibCore, AFEvent aFEvent) throws IOException {
        String jSONObject;
        AFLogger.afInfoLog(new StringBuilder("url: ").append(aFEvent.urlString()).toString());
        if (aFEvent.f356 != null) {
            jSONObject = Base64.encodeToString(aFEvent.mo14503(), 2);
            AFLogger.afInfoLog("cached data: ".concat(String.valueOf(jSONObject)));
        } else {
            jSONObject = new JSONObject(aFEvent.params()).toString();
            C0927ah.m336("data: ".concat(String.valueOf(jSONObject)));
        }
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("server_request", aFEvent.urlString(), jSONObject);
        try {
            appsFlyerLibCore.m286(aFEvent);
        } catch (IOException e) {
            AFLogger.afErrorLog("Exception in sendRequestToServer. ", e);
            if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.USE_HTTP_FALLBACK, false)) {
                appsFlyerLibCore.m286(aFEvent.urlString(aFEvent.urlString().replace("https:", "http:")));
            } else {
                AFLogger.afInfoLog(new StringBuilder("failed to send requeset to server. ").append(e.getLocalizedMessage()).toString());
                throw e;
            }
        }
    }

    @VisibleForTesting
    public AppsFlyerLibCore() {
        AFVersionDeclaration.init();
    }

    public static AppsFlyerLibCore getInstance() {
        return instance;
    }

    public void performOnAppAttribution(@NonNull Context context, @NonNull URI uri) {
        if (uri == null || uri.toString().isEmpty()) {
            if (f404 != null) {
                f404.onAttributionFailure(new StringBuilder("Link is \"").append(uri).append("\"").toString());
            }
        } else if (context != null) {
            AFDeepLinkManager.getInstance();
            AFDeepLinkManager.m202(context, new HashMap(), Uri.parse(uri.toString()));
        } else if (f404 != null) {
            f404.onAttributionFailure(new StringBuilder("Context is \"").append(context).append("\"").toString());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ǃ */
    public final void mo14601(Context context, Intent intent) {
        if (intent.getStringExtra("appsflyer_preinstall") != null) {
            getInstance();
            String stringExtra = intent.getStringExtra("appsflyer_preinstall");
            try {
                if (new JSONObject(stringExtra).has(Constants.URL_MEDIA_SOURCE)) {
                    AppsFlyerProperties.getInstance().set("preInstallName", stringExtra);
                } else {
                    AFLogger.afWarnLog("Cannot set preinstall attribution data without a media source");
                }
            } catch (JSONException e) {
                AFLogger.afErrorLog("Error parsing JSON for preinstall", e);
            }
        }
        AFLogger.afInfoLog("****** onReceive called *******");
        AppsFlyerProperties.getInstance().setOnReceiveCalled();
        String stringExtra2 = intent.getStringExtra(Payload.REFERRER);
        AFLogger.afInfoLog("Play store referrer: ".concat(String.valueOf(stringExtra2)));
        if (stringExtra2 != null) {
            SharedPreferences.Editor edit = getSharedPreferences(context).edit();
            edit.putString(Payload.REFERRER, stringExtra2);
            edit.apply();
            AppsFlyerProperties.getInstance().setReferrer(stringExtra2);
            if (AppsFlyerProperties.getInstance().isFirstLaunchCalled()) {
                AFLogger.afInfoLog("onReceive: isLaunchCalled");
                AFEvent weakContext = new BackgroundReferrerLaunch().context(context).mo14501().weakContext();
                weakContext.f350 = stringExtra2;
                weakContext.f351 = intent;
                if (stringExtra2 != null && stringExtra2.length() > 5 && m247(weakContext, getSharedPreferences(context))) {
                    m267(AFExecutor.getInstance().mo14505(), new C0909e(this, weakContext, (byte) 0), 5, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    /* renamed from: ǃ */
    private static void m261(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                JSONArray jSONArray = new JSONArray((String) jSONObject.get(keys.next()));
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(Long.valueOf(jSONArray.getLong(i)));
                }
            } catch (JSONException e) {
            }
        }
        Collections.sort(arrayList);
        Iterator<String> keys2 = jSONObject.keys();
        String str = null;
        while (keys2.hasNext() && str == null) {
            String next = keys2.next();
            try {
                JSONArray jSONArray2 = new JSONArray((String) jSONObject.get(next));
                int i2 = 0;
                while (true) {
                    if (i2 >= jSONArray2.length()) {
                        break;
                    } else if (jSONArray2.getLong(i2) == ((Long) arrayList.get(0)).longValue() || jSONArray2.getLong(i2) == ((Long) arrayList.get(1)).longValue() || jSONArray2.getLong(i2) == ((Long) arrayList.get(arrayList.size() - 1)).longValue()) {
                        str = null;
                    } else {
                        i2++;
                        str = next;
                    }
                }
            } catch (JSONException e2) {
                str = str;
            }
        }
        if (str != null) {
            jSONObject.remove(str);
        }
    }

    /* renamed from: ı */
    static void m240(Context context, String str) {
        JSONObject jSONObject;
        JSONArray jSONArray;
        AFLogger.afDebugLog("received a new (extra) referrer: ".concat(String.valueOf(str)));
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String string = getSharedPreferences(context).getString("extraReferrers", (String) null);
            if (string == null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONArray = new JSONArray();
                jSONObject = jSONObject2;
            } else {
                jSONObject = new JSONObject(string);
                if (jSONObject.has(str)) {
                    jSONArray = new JSONArray((String) jSONObject.get(str));
                } else {
                    jSONArray = new JSONArray();
                }
            }
            if (((long) jSONArray.length()) < 5) {
                jSONArray.put(currentTimeMillis);
            }
            if (((long) jSONObject.length()) >= 4) {
                m261(jSONObject);
            }
            jSONObject.put(str, jSONArray.toString());
            String jSONObject3 = jSONObject.toString();
            SharedPreferences.Editor edit = getSharedPreferences(context).edit();
            edit.putString("extraReferrers", jSONObject3);
            edit.apply();
        } catch (JSONException e) {
        } catch (Throwable th) {
            AFLogger.afErrorLog(new StringBuilder("Couldn't save referrer - ").append(str).append(": ").toString(), th);
        }
    }

    public void stopTracking(boolean z, Context context) {
        this.f422 = z;
        C0916aa.m315();
        try {
            File r0 = C0916aa.m318(context);
            if (!r0.exists()) {
                r0.mkdir();
            } else {
                for (File file : r0.listFiles()) {
                    Log.i(LOG_TAG, new StringBuilder("Found cached request").append(file.getName()).toString());
                    C0916aa.m317(C0916aa.m314(file).f607, context);
                }
            }
        } catch (Exception e) {
            Log.i(LOG_TAG, "Could not cache request");
        }
        if (this.f422) {
            SharedPreferences.Editor edit = getSharedPreferences(context).edit();
            edit.putBoolean(IS_STOP_TRACKING_USED, true);
            edit.apply();
        }
    }

    public void onPause(Context context) {
        if (Foreground.listener != null) {
            Foreground.listener.onBecameBackground(context);
        }
    }

    public void updateServerUninstallToken(Context context, String str) {
        C0922af.m327(context, str);
    }

    public void setDebugLog(boolean z) {
        setLogLevel(z ? AFLogger.LogLevel.DEBUG : AFLogger.LogLevel.NONE);
    }

    public AppsFlyerLib enableLocationCollection(boolean z) {
        this.f417 = z;
        return this;
    }

    /* access modifiers changed from: private */
    /* renamed from: ι */
    public static void m284(Context context, String str, long j) {
        SharedPreferences.Editor edit = getSharedPreferences(context).edit();
        edit.putLong(str, j);
        edit.apply();
    }

    /* renamed from: ι */
    private static boolean m290(String str) {
        return AppsFlyerProperties.getInstance().getBoolean(str, false);
    }

    /* renamed from: ι */
    private static boolean m288() {
        return m290(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID) && AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID) == null;
    }

    public void waitForCustomerUserId(boolean z) {
        AFLogger.afInfoLog("initAfterCustomerUserID: ".concat(String.valueOf(z)), true);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, z);
    }

    public void setCustomerIdAndTrack(String str, @NonNull Context context) {
        if (context == null) {
            return;
        }
        if (m288()) {
            setCustomerUserId(str);
            AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false);
            AFLogger.afInfoLog(new StringBuilder("CustomerUserId set: ").append(str).append(" - Initializing AppsFlyer Tacking").toString(), true);
            String referrer = AppsFlyerProperties.getInstance().getReferrer(context);
            m241(context, AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY), referrer == null ? "" : referrer, context instanceof Activity ? ((Activity) context).getIntent() : null);
            if (AppsFlyerProperties.getInstance().getString("afUninstallToken") != null) {
                mo14604(context, AppsFlyerProperties.getInstance().getString("afUninstallToken"));
                return;
            }
            return;
        }
        setCustomerUserId(str);
        AFLogger.afInfoLog("waitForCustomerUserId is false; setting CustomerUserID: ".concat(String.valueOf(str)), true);
    }

    public String getOutOfStore(Context context) {
        String r0;
        String string = AppsFlyerProperties.getInstance().getString("api_store_value");
        if (string != null) {
            return string;
        }
        if (context == null) {
            r0 = null;
        } else {
            r0 = m263("AF_STORE", context.getPackageManager(), context.getPackageName());
        }
        if (r0 != null) {
            return r0;
        }
        AFLogger.afInfoLog("No out-of-store value set");
        return null;
    }

    public void setOutOfStore(String str) {
        if (str != null) {
            String lowerCase = str.toLowerCase();
            AppsFlyerProperties.getInstance().set("api_store_value", lowerCase);
            AFLogger.afInfoLog("Store API set with value: ".concat(String.valueOf(lowerCase)), true);
            return;
        }
        AFLogger.m221("Cannot set setOutOfStore with null");
    }

    public void setAdditionalData(HashMap<String, Object> hashMap) {
        if (hashMap != null) {
            if (C0928ai.f525 == null) {
                C0928ai.f525 = new C0928ai();
            }
            C0928ai.f525.mo14690("public_api_call", "setAdditionalData", hashMap.toString());
            AppsFlyerProperties.getInstance().setCustomData(new JSONObject(hashMap).toString());
        }
    }

    @Deprecated
    public void sendDeepLinkData(Activity activity) {
        if (activity != null && activity.getIntent() != null) {
            if (C0928ai.f525 == null) {
                C0928ai.f525 = new C0928ai();
            }
            C0928ai.f525.mo14690("public_api_call", "sendDeepLinkData", activity.getLocalClassName(), new StringBuilder("activity_intent_").append(activity.getIntent().toString()).toString());
        } else if (activity != null) {
            if (C0928ai.f525 == null) {
                C0928ai.f525 = new C0928ai();
            }
            C0928ai.f525.mo14690("public_api_call", "sendDeepLinkData", activity.getLocalClassName(), "activity_intent_null");
        } else {
            if (C0928ai.f525 == null) {
                C0928ai.f525 = new C0928ai();
            }
            C0928ai.f525.mo14690("public_api_call", "sendDeepLinkData", "activity_null");
        }
        try {
            startTracking(activity);
            AFLogger.afInfoLog(new StringBuilder("getDeepLinkData with activity ").append(activity.getIntent().getDataString()).toString());
        } catch (Exception e) {
            AFLogger.afInfoLog("getDeepLinkData Exception: ".concat(String.valueOf(e)));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendPushNotificationData(android.app.Activity r14) {
        /*
            r13 = this;
            r1 = 0
            r12 = 2
            r6 = 1
            r5 = 0
            if (r14 == 0) goto L_0x00d6
            android.content.Intent r0 = r14.getIntent()
            if (r0 == 0) goto L_0x00d6
            com.appsflyer.internal.ai r0 = com.appsflyer.internal.C0928ai.f525
            if (r0 != 0) goto L_0x0017
            com.appsflyer.internal.ai r0 = new com.appsflyer.internal.ai
            r0.<init>()
            com.appsflyer.internal.C0928ai.f525 = r0
        L_0x0017:
            com.appsflyer.internal.ai r0 = com.appsflyer.internal.C0928ai.f525
            java.lang.String r2 = "sendPushNotificationData"
            java.lang.String[] r3 = new java.lang.String[r12]
            java.lang.String r4 = r14.getLocalClassName()
            r3[r5] = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "activity_intent_"
            r4.<init>(r5)
            android.content.Intent r5 = r14.getIntent()
            java.lang.String r5 = r5.toString()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3[r6] = r4
            java.lang.String r4 = "public_api_call"
            r0.mo14690(r4, r2, r3)
        L_0x0041:
            boolean r0 = r14 instanceof android.app.Activity
            if (r0 == 0) goto L_0x01d9
            r0 = r14
            android.app.Activity r0 = (android.app.Activity) r0
            android.content.Intent r2 = r0.getIntent()
            if (r2 == 0) goto L_0x01d9
            android.os.Bundle r3 = r2.getExtras()
            if (r3 == 0) goto L_0x01d9
            java.lang.String r0 = "af"
            java.lang.String r1 = r3.getString(r0)
            if (r1 == 0) goto L_0x0078
            java.lang.String r0 = "Push Notification received af payload = "
            java.lang.String r4 = java.lang.String.valueOf(r1)
            java.lang.String r0 = r0.concat(r4)
            com.appsflyer.AFLogger.afInfoLog(r0)
            java.lang.String r0 = "af"
            r3.remove(r0)
            r0 = r14
            android.app.Activity r0 = (android.app.Activity) r0
            android.content.Intent r2 = r2.putExtras(r3)
            r0.setIntent(r2)
        L_0x0078:
            r0 = r1
        L_0x0079:
            r13.f413 = r0
            java.lang.String r0 = r13.f413
            if (r0 == 0) goto L_0x00d5
            long r4 = java.lang.System.currentTimeMillis()
            java.util.Map<java.lang.Long, java.lang.String> r0 = r13.f414
            if (r0 != 0) goto L_0x0116
            java.lang.String r0 = "pushes: initializing pushes history.."
            com.appsflyer.AFLogger.afInfoLog(r0)
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            r0.<init>()
            r13.f414 = r0
            r2 = r4
        L_0x0094:
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r1 = "pushPayloadHistorySize"
            int r0 = r0.getInt(r1, r12)
            java.util.Map<java.lang.Long, java.lang.String> r1 = r13.f414
            int r1 = r1.size()
            if (r1 != r0) goto L_0x00c7
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "pushes: removing oldest overflowing push (oldest push:"
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r1 = ")"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.appsflyer.AFLogger.afInfoLog(r0)
            java.util.Map<java.lang.Long, java.lang.String> r0 = r13.f414
            java.lang.Long r1 = java.lang.Long.valueOf(r2)
            r0.remove(r1)
        L_0x00c7:
            java.util.Map<java.lang.Long, java.lang.String> r0 = r13.f414
            java.lang.Long r1 = java.lang.Long.valueOf(r4)
            java.lang.String r2 = r13.f413
            r0.put(r1, r2)
            r13.startTracking(r14)
        L_0x00d5:
            return
        L_0x00d6:
            if (r14 == 0) goto L_0x00fa
            com.appsflyer.internal.ai r0 = com.appsflyer.internal.C0928ai.f525
            if (r0 != 0) goto L_0x00e3
            com.appsflyer.internal.ai r0 = new com.appsflyer.internal.ai
            r0.<init>()
            com.appsflyer.internal.C0928ai.f525 = r0
        L_0x00e3:
            com.appsflyer.internal.ai r0 = com.appsflyer.internal.C0928ai.f525
            java.lang.String r2 = "sendPushNotificationData"
            java.lang.String[] r3 = new java.lang.String[r12]
            java.lang.String r4 = r14.getLocalClassName()
            r3[r5] = r4
            java.lang.String r4 = "activity_intent_null"
            r3[r6] = r4
            java.lang.String r4 = "public_api_call"
            r0.mo14690(r4, r2, r3)
            goto L_0x0041
        L_0x00fa:
            com.appsflyer.internal.ai r0 = com.appsflyer.internal.C0928ai.f525
            if (r0 != 0) goto L_0x0105
            com.appsflyer.internal.ai r0 = new com.appsflyer.internal.ai
            r0.<init>()
            com.appsflyer.internal.C0928ai.f525 = r0
        L_0x0105:
            com.appsflyer.internal.ai r0 = com.appsflyer.internal.C0928ai.f525
            java.lang.String r2 = "sendPushNotificationData"
            java.lang.String[] r3 = new java.lang.String[r6]
            java.lang.String r4 = "activity_null"
            r3[r5] = r4
            java.lang.String r4 = "public_api_call"
            r0.mo14690(r4, r2, r3)
            goto L_0x0041
        L_0x0116:
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()     // Catch:{ Throwable -> 0x01d4 }
            java.lang.String r1 = "pushPayloadMaxAging"
            r2 = 1800000(0x1b7740, double:8.89318E-318)
            long r6 = r0.getLong(r1, r2)     // Catch:{ Throwable -> 0x01d4 }
            java.util.Map<java.lang.Long, java.lang.String> r0 = r13.f414     // Catch:{ Throwable -> 0x01d4 }
            java.util.Set r0 = r0.keySet()     // Catch:{ Throwable -> 0x01d4 }
            java.util.Iterator r8 = r0.iterator()     // Catch:{ Throwable -> 0x01d4 }
            r2 = r4
        L_0x012e:
            boolean r0 = r8.hasNext()     // Catch:{ Throwable -> 0x0199 }
            if (r0 == 0) goto L_0x0094
            java.lang.Object r0 = r8.next()     // Catch:{ Throwable -> 0x0199 }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ Throwable -> 0x0199 }
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0199 }
            java.lang.String r1 = r13.f413     // Catch:{ Throwable -> 0x0199 }
            r9.<init>(r1)     // Catch:{ Throwable -> 0x0199 }
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0199 }
            java.util.Map<java.lang.Long, java.lang.String> r1 = r13.f414     // Catch:{ Throwable -> 0x0199 }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ Throwable -> 0x0199 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Throwable -> 0x0199 }
            r10.<init>(r1)     // Catch:{ Throwable -> 0x0199 }
            java.lang.String r1 = "pid"
            java.lang.Object r1 = r9.get(r1)     // Catch:{ Throwable -> 0x0199 }
            java.lang.String r11 = "pid"
            java.lang.Object r11 = r10.get(r11)     // Catch:{ Throwable -> 0x0199 }
            boolean r1 = r1.equals(r11)     // Catch:{ Throwable -> 0x0199 }
            if (r1 == 0) goto L_0x01b6
            java.lang.String r1 = "c"
            java.lang.Object r1 = r9.get(r1)     // Catch:{ Throwable -> 0x0199 }
            java.lang.String r11 = "c"
            java.lang.Object r11 = r10.get(r11)     // Catch:{ Throwable -> 0x0199 }
            boolean r1 = r1.equals(r11)     // Catch:{ Throwable -> 0x0199 }
            if (r1 == 0) goto L_0x01b6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0199 }
            java.lang.String r1 = "PushNotificationMeasurement: A previous payload with same PID and campaign was already acknowledged! (old: "
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0199 }
            java.lang.StringBuilder r0 = r0.append(r10)     // Catch:{ Throwable -> 0x0199 }
            java.lang.String r1 = ", new: "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Throwable -> 0x0199 }
            java.lang.StringBuilder r0 = r0.append(r9)     // Catch:{ Throwable -> 0x0199 }
            java.lang.String r1 = ")"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Throwable -> 0x0199 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0199 }
            com.appsflyer.AFLogger.afInfoLog(r0)     // Catch:{ Throwable -> 0x0199 }
            r0 = 0
            r13.f413 = r0     // Catch:{ Throwable -> 0x0199 }
            goto L_0x00d5
        L_0x0199:
            r0 = move-exception
        L_0x019a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r6 = "Error while handling push notification measurement: "
            r1.<init>(r6)
            java.lang.Class r6 = r0.getClass()
            java.lang.String r6 = r6.getSimpleName()
            java.lang.StringBuilder r1 = r1.append(r6)
            java.lang.String r1 = r1.toString()
            com.appsflyer.AFLogger.afErrorLog(r1, r0)
            goto L_0x0094
        L_0x01b6:
            long r10 = r0.longValue()     // Catch:{ Throwable -> 0x0199 }
            long r10 = r4 - r10
            int r1 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r1 <= 0) goto L_0x01c5
            java.util.Map<java.lang.Long, java.lang.String> r1 = r13.f414     // Catch:{ Throwable -> 0x0199 }
            r1.remove(r0)     // Catch:{ Throwable -> 0x0199 }
        L_0x01c5:
            long r10 = r0.longValue()     // Catch:{ Throwable -> 0x0199 }
            int r1 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r1 > 0) goto L_0x01d7
            long r0 = r0.longValue()     // Catch:{ Throwable -> 0x0199 }
        L_0x01d1:
            r2 = r0
            goto L_0x012e
        L_0x01d4:
            r0 = move-exception
            r2 = r4
            goto L_0x019a
        L_0x01d7:
            r0 = r2
            goto L_0x01d1
        L_0x01d9:
            r0 = r1
            goto L_0x0079
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLibCore.sendPushNotificationData(android.app.Activity):void");
    }

    public void setUserEmails(AppsFlyerProperties.EmailsCryptType emailsCryptType, String... strArr) {
        String str;
        ArrayList arrayList = new ArrayList(strArr.length + 1);
        arrayList.add(emailsCryptType.toString());
        arrayList.addAll(Arrays.asList(strArr));
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setUserEmails", (String[]) arrayList.toArray(new String[(strArr.length + 1)]));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.EMAIL_CRYPT_TYPE, emailsCryptType.getValue());
        HashMap hashMap = new HashMap();
        String str2 = null;
        ArrayList arrayList2 = new ArrayList();
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str3 = strArr[i];
            switch (C089810.f439[emailsCryptType.ordinal()]) {
                case 2:
                    str = "plain_el_arr";
                    arrayList2.add(str3);
                    break;
                default:
                    str = "sha256_el_arr";
                    arrayList2.add(C0968z.m411(str3));
                    break;
            }
            i++;
            str2 = str;
        }
        hashMap.put(str2, arrayList2);
        AppsFlyerProperties.getInstance().setUserEmails(new JSONObject(hashMap).toString());
    }

    /* renamed from: com.appsflyer.AppsFlyerLibCore$10 */
    static /* synthetic */ class C089810 {

        /* renamed from: ι */
        static final /* synthetic */ int[] f439 = new int[AppsFlyerProperties.EmailsCryptType.values().length];

        static {
            try {
                f439[AppsFlyerProperties.EmailsCryptType.SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f439[AppsFlyerProperties.EmailsCryptType.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public void setResolveDeepLinkURLs(String... strArr) {
        AFLogger.afDebugLog(String.format("setResolveDeepLinkURLs %s", new Object[]{Arrays.toString(strArr)}));
        AFDeepLinkManager.f343 = strArr;
    }

    public void setOneLinkCustomDomain(String... strArr) {
        AFLogger.afDebugLog(String.format("setOneLinkCustomDomain %s", new Object[]{Arrays.toString(strArr)}));
        AFDeepLinkManager.f342 = strArr;
    }

    public AppsFlyerLib init(String str, AppsFlyerConversionListener appsFlyerConversionListener, final Context context) {
        if (context != null) {
            this.f430 = (Application) context.getApplicationContext();
            if (GoogleReferrer.allow(this, context)) {
                if (this.f425 == null) {
                    this.f425 = new GoogleReferrer();
                    AFLogger.afDebugLog("Connecting to Install Referrer Library...");
                    this.f425.start(context, new Runnable() {
                        public final void run() {
                            try {
                                AFLogger.afDebugLog("Install Referrer collected locally");
                                AppsFlyerLibCore.m276(AppsFlyerLibCore.this);
                            } catch (Throwable th) {
                                AFLogger.afErrorLog(th.getMessage(), th);
                            }
                        }
                    });
                } else {
                    AFLogger.afWarnLog("GoogleReferrer instance already created");
                }
            }
            final SharedPreferences sharedPreferences = getSharedPreferences(context);
            if (getLaunchCounter(sharedPreferences, false) < 2) {
                this.f434 = new HuaweiReferrer(new Runnable() {
                    public final void run() {
                        if (AppsFlyerLibCore.this.getLaunchCounter(sharedPreferences, false) != 1) {
                            return;
                        }
                        if (!GoogleReferrer.allow(AppsFlyerLibCore.this, context) || sharedPreferences.getBoolean(AppsFlyerProperties.NEW_REFERRER_SENT, false)) {
                            AppsFlyerLibCore.this.m242(new Attr().context(context));
                        }
                    }
                }, context);
                this.f434.start();
            }
            this.f433 = m279(context);
        } else {
            AFLogger.afWarnLog("init :: context is null, Google Install Referrer will be not initialized!");
        }
        return init(str, appsFlyerConversionListener);
    }

    /* renamed from: Ι */
    private boolean m279(Context context) {
        try {
            Class.forName("com.appsflyer.lvl.AppsFlyerLVL");
            final long currentTimeMillis = System.currentTimeMillis();
            this.f429 = new ConcurrentHashMap();
            C09014 r4 = new C0954t.C0956d() {
                /* renamed from: ı */
                public final void mo14609(@NonNull String str, @NonNull String str2) {
                    AppsFlyerLibCore.this.f429.put("signedData", str);
                    AppsFlyerLibCore.this.f429.put("signature", str2);
                    AppsFlyerLibCore.this.f429.put("ttr", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    AFLogger.afInfoLog("Successfully retrieved Google LVL data.");
                }

                /* renamed from: ǃ */
                public final void mo14610(String str, Exception exc) {
                    String message = exc.getMessage();
                    if (message == null) {
                        message = "unknown";
                    }
                    AppsFlyerLibCore.this.f429.put("error", message);
                    AFLogger.afErrorLog(str, exc, true);
                }
            };
            try {
                Class<?> cls = Class.forName("com.appsflyer.lvl.AppsFlyerLVL");
                Class<?> cls2 = Class.forName("com.appsflyer.lvl.AppsFlyerLVL$resultListener");
                Method method = cls.getMethod("checkLicense", new Class[]{Long.TYPE, Context.class, cls2});
                C0954t.C09553 r7 = new InvocationHandler(r4) {

                    /* renamed from: ι */
                    private /* synthetic */ C0956d f638;

                    public final java.lang.Object invoke(
/*
Method generation error in method: com.appsflyer.internal.t.3.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object, dex: classes.dex
                    jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.appsflyer.internal.t.3.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object, class status: UNLOADED
                    	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:311)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:68)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:311)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:68)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    
*/
                };
                method.invoke((Object) null, new Object[]{Long.valueOf(currentTimeMillis), context, Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, r7)});
                return true;
            } catch (ClassNotFoundException e) {
                r4.mo14610(e.getClass().getSimpleName(), e);
                return true;
            } catch (NoSuchMethodException e2) {
                r4.mo14610(e2.getClass().getSimpleName(), e2);
                return true;
            } catch (IllegalAccessException e3) {
                r4.mo14610(e3.getClass().getSimpleName(), e3);
                return true;
            } catch (InvocationTargetException e4) {
                r4.mo14610(e4.getClass().getSimpleName(), e4);
                return true;
            }
        } catch (ClassNotFoundException e5) {
            return false;
        }
    }

    public void enableFacebookDeferredApplinks(boolean z) {
        this.f431 = z;
    }

    public void startTracking(Context context) {
        startTracking(context, (String) null);
    }

    public void startTracking(Context context, String str) {
        startTracking(context, str, (AppsFlyerTrackingRequestListener) null);
    }

    public void startTracking(Context context, final String str, final AppsFlyerTrackingRequestListener appsFlyerTrackingRequestListener) {
        if (!this.f421) {
            AFLogger.afWarnLog("ERROR: AppsFlyer SDK is not initialized! The API call 'startTracking()' must be called after the 'init(String, AppsFlyerConversionListener)' API method, which should be called on the Application's onCreate.");
            if (str == null) {
                return;
            }
        }
        if (Foreground.listener == null) {
            this.f430 = (Application) context.getApplicationContext();
            if (C0928ai.f525 == null) {
                C0928ai.f525 = new C0928ai();
            }
            C0928ai.f525.mo14690("public_api_call", "startTracking", str);
            AFLogger.afInfoLog(String.format("Starting AppsFlyer Tracking: (v%s.%s)", new Object[]{BuildConfig.VERSION_NAME, f403}));
            AFLogger.afInfoLog(new StringBuilder("Build Number: ").append(f403).toString());
            AppsFlyerProperties.getInstance().loadProperties(this.f430.getApplicationContext());
            if (!TextUtils.isEmpty(str)) {
                AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_KEY, str);
                C0927ah.m335(str);
            } else if (TextUtils.isEmpty(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY))) {
                AFLogger.afWarnLog("ERROR: AppsFlyer SDK is not initialized! You must provide AppsFlyer Dev-Key either in the 'init' API method (should be called on Application's onCreate),or in the startTracking API method (should be called on Activity's onCreate).");
                return;
            }
            Context baseContext = this.f430.getBaseContext();
            try {
                if ((baseContext.getPackageManager().getPackageInfo(baseContext.getPackageName(), 0).applicationInfo.flags & 32768) != 0) {
                    if (baseContext.getResources().getIdentifier("appsflyer_backup_rules", "xml", baseContext.getPackageName()) != 0) {
                        AFLogger.afInfoLog("appsflyer_backup_rules.xml detected, using AppsFlyer defined backup rules for AppsFlyer SDK data", true);
                    } else {
                        AFLogger.m221("'allowBackup' is set to true; appsflyer_backup_rules.xml not detected.\nAppsFlyer shared preferences should be excluded from auto backup by adding: <exclude domain=\"sharedpref\" path=\"appsflyer-data\"/> to the Application's <full-backup-content> rules");
                    }
                }
            } catch (Exception e) {
                AFLogger.afRDLog(new StringBuilder("checkBackupRules Exception: ").append(e.toString()).toString());
            }
            if (this.f431) {
                Context applicationContext = this.f430.getApplicationContext();
                this.f426 = new HashMap();
                final long currentTimeMillis = System.currentTimeMillis();
                C08971 r1 = new AFFacebookDeferredDeeplink.AppLinkFetchEvents() {
                    public final void onAppLinkFetchFinished(String str, String str2, String str3) {
                        if (str != null) {
                            AFLogger.afInfoLog("Facebook Deferred AppLink data received: ".concat(String.valueOf(str)));
                            AppsFlyerLibCore.this.f426.put("link", str);
                            if (str2 != null) {
                                AppsFlyerLibCore.this.f426.put("target_url", str2);
                            }
                            if (str3 != null) {
                                HashMap hashMap = new HashMap();
                                HashMap hashMap2 = new HashMap();
                                hashMap2.put(ShareConstants.PROMO_CODE, str3);
                                hashMap.put(ShareConstants.DEEPLINK_CONTEXT, hashMap2);
                                AppsFlyerLibCore.this.f426.put("extras", hashMap);
                            }
                        } else {
                            AppsFlyerLibCore.this.f426.put("link", "");
                        }
                        AppsFlyerLibCore.this.f426.put("ttr", String.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    }

                    public final void onAppLinkFetchFailed(String str) {
                        AppsFlyerLibCore.this.f426.put("error", str);
                    }
                };
                try {
                    Class.forName("com.facebook.FacebookSdk").getMethod("sdkInitialize", new Class[]{Context.class}).invoke((Object) null, new Object[]{applicationContext});
                    Class<?> cls = Class.forName("com.facebook.applinks.AppLinkData");
                    Class<?> cls2 = Class.forName("com.facebook.applinks.AppLinkData$CompletionHandler");
                    Method method = cls.getMethod("fetchDeferredAppLinkData", new Class[]{Context.class, String.class, cls2});
                    AFFacebookDeferredDeeplink.C08925 r5 = new InvocationHandler(cls, r1) {

                        /* renamed from: ı */
                        private /* synthetic */ Class f371;

                        /* renamed from: ɩ */
                        private /* synthetic */ AppLinkFetchEvents f372;

                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public final java.lang.Object invoke(
/*
Method generation error in method: com.appsflyer.AFFacebookDeferredDeeplink.5.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object, dex: classes.dex
                        jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.appsflyer.AFFacebookDeferredDeeplink.5.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object, class status: UNLOADED
                        	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:311)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:68)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                        
*/
                    };
                    Object newProxyInstance = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, r5);
                    String string = applicationContext.getString(applicationContext.getResources().getIdentifier("facebook_app_id", "string", applicationContext.getPackageName()));
                    if (TextUtils.isEmpty(string)) {
                        r1.onAppLinkFetchFailed("Facebook app id not defined in resources");
                    } else {
                        method.invoke((Object) null, new Object[]{applicationContext, string, newProxyInstance});
                    }
                } catch (NoSuchMethodException e2) {
                    r1.onAppLinkFetchFailed(e2.toString());
                } catch (InvocationTargetException e3) {
                    r1.onAppLinkFetchFailed(e3.toString());
                } catch (ClassNotFoundException e4) {
                    r1.onAppLinkFetchFailed(e4.toString());
                } catch (IllegalAccessException e5) {
                    r1.onAppLinkFetchFailed(e5.toString());
                }
            }
            Foreground.m311(context, new Foreground.Listener() {
                public final void onBecameForeground(Activity activity) {
                    if (AppsFlyerLibCore.this.getLaunchCounter(AppsFlyerLibCore.getSharedPreferences(activity), false) < 2) {
                        AFSensorManager r0 = AFSensorManager.m226((Context) activity);
                        r0.f383.post(r0.f392);
                        r0.f383.post(r0.f388);
                    }
                    AFLogger.afInfoLog("onBecameForeground");
                    long unused = AppsFlyerLibCore.this.f424 = System.currentTimeMillis();
                    AppsFlyerLibCore appsFlyerLibCore = AppsFlyerLibCore.this;
                    AFEvent key = new Launch().context(activity).key(str);
                    key.f354 = appsFlyerTrackingRequestListener;
                    appsFlyerLibCore.mo14602(key);
                    AFLogger.resetDeltaTime();
                }

                public final void onBecameBackground(Context context) {
                    AFLogger.afInfoLog("onBecameBackground");
                    long unused = AppsFlyerLibCore.this.f412 = System.currentTimeMillis();
                    AFLogger.afInfoLog("callStatsBackground background call");
                    AppsFlyerLibCore.this.mo14605((WeakReference<Context>) new WeakReference(context));
                    if (C0928ai.f525 == null) {
                        C0928ai.f525 = new C0928ai();
                    }
                    C0928ai aiVar = C0928ai.f525;
                    if (aiVar.mo14697()) {
                        aiVar.mo14696();
                        if (context != null) {
                            String packageName = context.getPackageName();
                            PackageManager packageManager = context.getPackageManager();
                            try {
                                if (C0928ai.f525 == null) {
                                    C0928ai.f525 = new C0928ai();
                                }
                                C0928ai.f525.mo14695(packageName, packageManager);
                                if (C0928ai.f525 == null) {
                                    C0928ai.f525 = new C0928ai();
                                }
                                new Thread(new C0920ad((BackgroundEvent) new ProxyEvent().body(C0928ai.f525.mo14694()).trackingStopped(AppsFlyerLib.getInstance().isTrackingStopped()).urlString(new StringBuilder().append(ServerConfigHandler.getUrl("https://%smonitorsdk.%s/remote-debug?app_id=")).append(packageName).toString()))).start();
                            } catch (Throwable th) {
                            }
                        }
                        aiVar.mo14692();
                    } else {
                        AFLogger.afDebugLog("RD status is OFF");
                    }
                    AFExecutor instance = AFExecutor.getInstance();
                    try {
                        AFExecutor.m206(instance.f368);
                        if (instance.f369 instanceof ThreadPoolExecutor) {
                            AFExecutor.m206((ThreadPoolExecutor) instance.f369);
                        }
                    } catch (Throwable th2) {
                        AFLogger.afErrorLog("failed to stop Executors", th2);
                    }
                    AFSensorManager r0 = AFSensorManager.m226(context);
                    r0.f383.post(r0.f392);
                }
            });
        }
    }

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
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* renamed from: ɩ */
    private static void m266(android.content.Context r5) {
        /*
            r0 = 18
            boolean r1 = com.appsflyer.AndroidUtils.m232()
            if (r1 == 0) goto L_0x000f
            r0 = 23
            java.lang.String r1 = "OPPO device found"
            com.appsflyer.AFLogger.afRDLog(r1)
        L_0x000f:
            int r1 = android.os.Build.VERSION.SDK_INT
            if (r1 < r0) goto L_0x00c4
            java.lang.String r0 = "keyPropDisableAFKeystore"
            boolean r0 = m290((java.lang.String) r0)
            if (r0 != 0) goto L_0x00c4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "OS SDK is="
            r0.<init>(r1)
            int r1 = android.os.Build.VERSION.SDK_INT
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "; use KeyStore"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.appsflyer.AFLogger.afRDLog(r0)
            com.appsflyer.AFKeystoreWrapper r1 = new com.appsflyer.AFKeystoreWrapper
            r1.<init>(r5)
            boolean r0 = r1.mo14512()
            if (r0 != 0) goto L_0x0074
            java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference
            r0.<init>(r5)
            java.lang.String r0 = com.appsflyer.internal.C0921ae.m326(r0)
            r1.f377 = r0
            r0 = 0
            r1.f375 = r0
            java.lang.String r0 = r1.mo14513()
            r1.mo14514(r0)
        L_0x0055:
            java.lang.String r0 = "KSAppsFlyerId"
            java.lang.String r2 = r1.mo14511()
            com.appsflyer.AppsFlyerProperties r3 = com.appsflyer.AppsFlyerProperties.getInstance()
            r3.set((java.lang.String) r0, (java.lang.String) r2)
            java.lang.String r0 = "KSAppsFlyerRICounter"
            int r1 = r1.mo14515()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            com.appsflyer.AppsFlyerProperties r2 = com.appsflyer.AppsFlyerProperties.getInstance()
            r2.set((java.lang.String) r0, (java.lang.String) r1)
        L_0x0073:
            return
        L_0x0074:
            java.lang.String r0 = r1.mo14513()
            java.lang.Object r2 = r1.f373
            monitor-enter(r2)
            int r3 = r1.f375     // Catch:{ all -> 0x00c1 }
            int r3 = r3 + 1
            r1.f375 = r3     // Catch:{ all -> 0x00c1 }
            java.lang.String r3 = "Deleting key with alias: "
            java.lang.String r4 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x00c1 }
            java.lang.String r3 = r3.concat(r4)     // Catch:{ all -> 0x00c1 }
            com.appsflyer.AFLogger.afInfoLog(r3)     // Catch:{ all -> 0x00c1 }
            java.lang.Object r3 = r1.f373     // Catch:{ KeyStoreException -> 0x00a3 }
            monitor-enter(r3)     // Catch:{ KeyStoreException -> 0x00a3 }
            java.security.KeyStore r4 = r1.f376     // Catch:{ all -> 0x00a0 }
            r4.deleteEntry(r0)     // Catch:{ all -> 0x00a0 }
            monitor-exit(r3)     // Catch:{ all -> 0x00a0 }
        L_0x0097:
            monitor-exit(r2)     // Catch:{ all -> 0x00c1 }
            java.lang.String r0 = r1.mo14513()
            r1.mo14514(r0)
            goto L_0x0055
        L_0x00a0:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ KeyStoreException -> 0x00a3 }
            throw r0     // Catch:{ KeyStoreException -> 0x00a3 }
        L_0x00a3:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c1 }
            java.lang.String r4 = "Exception "
            r3.<init>(r4)     // Catch:{ all -> 0x00c1 }
            java.lang.String r4 = r0.getMessage()     // Catch:{ all -> 0x00c1 }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x00c1 }
            java.lang.String r4 = " occurred"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x00c1 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00c1 }
            com.appsflyer.AFLogger.afErrorLog(r3, r0)     // Catch:{ all -> 0x00c1 }
            goto L_0x0097
        L_0x00c1:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x00c4:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "OS SDK is="
            r0.<init>(r1)
            int r1 = android.os.Build.VERSION.SDK_INT
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "; no KeyStore usage"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.appsflyer.AFLogger.afRDLog(r0)
            goto L_0x0073
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLibCore.m266(android.content.Context):void");
    }

    public void setPhoneNumber(String str) {
        this.f435 = C0968z.m411(str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ɩ */
    public final void mo14605(WeakReference<Context> weakReference) {
        if (weakReference.get() != null) {
            AFLogger.afInfoLog("app went to background");
            SharedPreferences sharedPreferences = getSharedPreferences(weakReference.get());
            AppsFlyerProperties.getInstance().saveProperties(sharedPreferences);
            long j = this.f412 - this.f424;
            HashMap hashMap = new HashMap();
            String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
            if (string == null) {
                AFLogger.afWarnLog("[callStats] AppsFlyer's SDK cannot send any event without providing DevKey.");
                return;
            }
            String string2 = AppsFlyerProperties.getInstance().getString("KSAppsFlyerId");
            if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, false)) {
                hashMap.put(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, "true");
            }
            C0963w r0 = C0961v.m402(weakReference.get().getContentResolver());
            if (r0 != null) {
                hashMap.put("amazon_aid", r0.f645);
                hashMap.put("amazon_aid_limit", String.valueOf(r0.mo14735()));
            }
            String string3 = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
            if (string3 != null) {
                hashMap.put(ServerParameters.ADVERTISING_ID_PARAM, string3);
            }
            hashMap.put("app_id", weakReference.get().getPackageName());
            hashMap.put("devkey", string);
            hashMap.put("uid", C0921ae.m326(weakReference));
            hashMap.put("time_in_app", String.valueOf(j / 1000));
            hashMap.put("statType", "user_closed_app");
            hashMap.put("platform", "Android");
            hashMap.put("launch_counter", Integer.toString(getLaunchCounter(sharedPreferences, false)));
            hashMap.put(AppsFlyerProperties.CHANNEL, getConfiguredChannel(weakReference.get()));
            hashMap.put("originalAppsflyerId", string2 != null ? string2 : "");
            if (this.f418) {
                try {
                    AFLogger.afDebugLog("Running callStats task");
                    new Thread(new C0920ad((BackgroundEvent) new Stats().trackingStopped(isTrackingStopped()).params(hashMap).urlString(ServerConfigHandler.getUrl("https://%sstats.%s/stats")))).start();
                } catch (Throwable th) {
                    AFLogger.afErrorLog("Could not send callStats request", th);
                }
            } else {
                AFLogger.afDebugLog("Stats call is disabled, ignore ...");
            }
        }
    }

    @Deprecated
    public void trackAppLaunch(Context context, String str) {
        if (GoogleReferrer.allow(this, context)) {
            if (this.f425 == null) {
                this.f425 = new GoogleReferrer();
                AFLogger.afDebugLog("Connecting to Install Referrer Library...");
                this.f425.start(context, new Runnable() {
                    public final void run() {
                        try {
                            AFLogger.afDebugLog("Install Referrer collected locally");
                            AppsFlyerLibCore.m276(AppsFlyerLibCore.this);
                        } catch (Throwable th) {
                            AFLogger.afErrorLog(th.getMessage(), th);
                        }
                    }
                });
            } else {
                AFLogger.afWarnLog("GoogleReferrer instance already created");
            }
        }
        m241(context, str, "", (Intent) null);
    }

    /* access modifiers changed from: protected */
    public void setDeepLinkData(Intent intent) {
        if (intent != null) {
            try {
                if ("android.intent.action.VIEW".equals(intent.getAction())) {
                    this.latestDeepLink = intent.getData();
                    AFLogger.afDebugLog(new StringBuilder("Unity setDeepLinkData = ").append(this.latestDeepLink).toString());
                }
            } catch (Throwable th) {
                AFLogger.afErrorLog("Exception while setting deeplink data (unity). ", th);
            }
        }
    }

    public void setPluginDeepLinkData(Intent intent) {
        setDeepLinkData(intent);
    }

    public void trackEvent(Context context, String str, Map<String, Object> map, AppsFlyerTrackingRequestListener appsFlyerTrackingRequestListener) {
        HashMap hashMap;
        Map map2;
        AFEvent context2 = new InAppEvent().context(context);
        context2.f363 = str;
        if (map == null) {
            hashMap = null;
        } else {
            hashMap = new HashMap(map);
        }
        context2.f359 = hashMap;
        context2.f354 = appsFlyerTrackingRequestListener;
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai aiVar = C0928ai.f525;
        String[] strArr = new String[2];
        strArr[0] = str;
        if (context2.f359 == null) {
            map2 = new HashMap();
        } else {
            map2 = context2.f359;
        }
        strArr[1] = new JSONObject(map2).toString();
        aiVar.mo14690("public_api_call", "trackEvent", strArr);
        if (str != null) {
            AFSensorManager r0 = AFSensorManager.m226(context);
            long currentTimeMillis = System.currentTimeMillis();
            if (r0.f389 != 0) {
                r0.f384++;
                if (r0.f389 - currentTimeMillis < 500) {
                    r0.f383.removeCallbacks(r0.f393);
                    r0.f383.post(r0.f388);
                }
            } else {
                r0.f383.post(r0.f392);
                r0.f383.post(r0.f388);
            }
            r0.f389 = currentTimeMillis;
        }
        mo14602(context2);
    }

    public void sendAdRevenue(Context context, Map<String, Object> map) {
        AFEvent context2 = new AdRevenue().context(context);
        context2.f359 = map;
        Context context3 = context2.context();
        String obj = new StringBuilder().append(ServerConfigHandler.getUrl(f407)).append(context3.getPackageName()).toString();
        SharedPreferences sharedPreferences = getSharedPreferences(context3);
        int launchCounter = getLaunchCounter(sharedPreferences, false);
        int r5 = m250(sharedPreferences, "appsFlyerAdRevenueCount", true);
        HashMap hashMap = new HashMap();
        hashMap.put("ad_network", context2.f359);
        hashMap.put("adrevenue_counter", Integer.valueOf(r5));
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
        hashMap.put("af_key", string);
        hashMap.put("launch_counter", Integer.valueOf(launchCounter));
        hashMap.put("af_timestamp", Long.toString(new Date().getTime()));
        hashMap.put("uid", C0921ae.m326(new WeakReference(context3)));
        String string2 = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
        hashMap.put("advertiserIdEnabled", AppsFlyerProperties.getInstance().getString("advertiserIdEnabled"));
        if (string2 != null) {
            hashMap.put(ServerParameters.ADVERTISING_ID_PARAM, string2);
        }
        hashMap.put("device", Build.DEVICE);
        m259(context3, (Map<String, Object>) hashMap);
        try {
            PackageInfo packageInfo = context3.getPackageManager().getPackageInfo(context3.getPackageName(), 0);
            hashMap.put("app_version_code", Integer.toString(packageInfo.versionCode));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
            long j = packageInfo.firstInstallTime;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            hashMap.put("install_date", simpleDateFormat.format(new Date(j)));
            String string3 = sharedPreferences.getString("appsFlyerFirstInstall", (String) null);
            if (string3 == null) {
                string3 = m283(simpleDateFormat, context3);
            }
            hashMap.put("first_launch_date", string3);
        } catch (Throwable th) {
            AFLogger.afErrorLog("AdRevenue - Exception while collecting app version data ", th);
        }
        AFEvent r1 = context2.urlString(obj).params(hashMap).mo14501();
        r1.f353 = launchCounter;
        m267(AFExecutor.getInstance().mo14505(), new C0906a(this, r1.key(string), (byte) 0), 1, TimeUnit.MILLISECONDS);
    }

    public void trackEvent(Context context, String str, Map<String, Object> map) {
        trackEvent(context, str, map, (AppsFlyerTrackingRequestListener) null);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    @android.support.annotation.VisibleForTesting
    /* renamed from: ǃ */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo14602(@android.support.annotation.NonNull com.appsflyer.AFEvent r6) {
        /*
            r5 = this;
            android.content.Context r1 = r6.context()
            r3 = 0
            java.lang.String r2 = ""
            boolean r0 = r1 instanceof android.app.Activity
            if (r0 == 0) goto L_0x0044
            r0 = r1
            android.app.Activity r0 = (android.app.Activity) r0
            android.content.Intent r3 = r0.getIntent()
            android.net.Uri r0 = com.appsflyer.internal.ActivityCompat.getReferrer(r0)
            if (r0 == 0) goto L_0x0044
            java.lang.String r0 = r0.toString()
        L_0x001c:
            java.lang.String r2 = "AppsFlyerKey"
            com.appsflyer.AppsFlyerProperties r4 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r2 = r4.getString(r2)
            if (r2 != 0) goto L_0x002e
            java.lang.String r0 = "[TrackEvent/Launch] AppsFlyer's SDK cannot send any event without providing DevKey."
            com.appsflyer.AFLogger.afWarnLog(r0)
        L_0x002d:
            return
        L_0x002e:
            com.appsflyer.AppsFlyerProperties r2 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r1 = r2.getReferrer(r1)
            if (r1 != 0) goto L_0x003a
            java.lang.String r1 = ""
        L_0x003a:
            r6.f350 = r1
            r6.f351 = r3
            r6.f361 = r0
            r5.m275((com.appsflyer.AFEvent) r6)
            goto L_0x002d
        L_0x0044:
            r0 = r2
            goto L_0x001c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLibCore.mo14602(com.appsflyer.AFEvent):void");
    }

    /* renamed from: ɩ */
    public final void mo14604(Context context, String str) {
        if (m288()) {
            AFLogger.afInfoLog("CustomerUserId not set, Tracking is disabled", true);
            return;
        }
        HashMap hashMap = new HashMap();
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
        if (string == null) {
            AFLogger.afWarnLog("[registerUninstall] AppsFlyer's SDK cannot send any event without providing DevKey.");
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            hashMap.put("app_version_code", Integer.toString(packageInfo.versionCode));
            hashMap.put("app_version_name", packageInfo.versionName);
            hashMap.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, packageManager.getApplicationLabel(packageInfo.applicationInfo).toString());
            long j = packageInfo.firstInstallTime;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            hashMap.put("installDate", simpleDateFormat.format(new Date(j)));
        } catch (Throwable th) {
            AFLogger.afErrorLog("Exception while collecting application version info.", th);
        }
        m285(context, (Map<String, ? super String>) hashMap);
        String string2 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID);
        if (string2 != null) {
            hashMap.put("appUserId", string2);
        }
        try {
            hashMap.put("model", Build.MODEL);
            hashMap.put("brand", Build.BRAND);
        } catch (Throwable th2) {
            AFLogger.afErrorLog("Exception while collecting device brand and model.", th2);
        }
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, false)) {
            hashMap.put(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, "true");
        }
        C0963w r0 = C0961v.m402(context.getContentResolver());
        if (r0 != null) {
            hashMap.put("amazon_aid", r0.f645);
            hashMap.put("amazon_aid_limit", String.valueOf(r0.mo14735()));
        }
        String string3 = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
        if (string3 != null) {
            hashMap.put(ServerParameters.ADVERTISING_ID_PARAM, string3);
        }
        hashMap.put("devkey", string);
        hashMap.put("uid", C0921ae.m326(new WeakReference(context)));
        hashMap.put("af_gcm_token", str);
        hashMap.put("launch_counter", Integer.toString(getLaunchCounter(getSharedPreferences(context), false)));
        hashMap.put(ServerProtocol.DIALOG_PARAM_SDK_VERSION, Integer.toString(Build.VERSION.SDK_INT));
        String configuredChannel = getConfiguredChannel(context);
        if (configuredChannel != null) {
            hashMap.put(AppsFlyerProperties.CHANNEL, configuredChannel);
        }
        try {
            new Thread(new C0920ad((BackgroundEvent) new UninstallTokenEvent().trackingStopped(isTrackingStopped()).params(hashMap).context(context).urlString(new StringBuilder().append(ServerConfigHandler.getUrl(REGISTER_URL)).append(packageName).toString()))).start();
        } catch (Throwable th3) {
            AFLogger.afErrorLog(th3.getMessage(), th3);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: ı */
    public static Map<String, Object> m238(Context context) throws C0967y {
        String string = getSharedPreferences(context).getString("attributionId", (String) null);
        if (string != null && string.length() > 0) {
            return m265(string);
        }
        throw new C0967y();
    }

    /* access modifiers changed from: protected */
    public void getConversionData(Context context, final ConversionDataListener conversionDataListener) {
        f404 = new AppsFlyerConversionListener() {
            public final void onConversionDataSuccess(Map<String, Object> map) {
                AFLogger.afDebugLog(new StringBuilder("Calling onConversionDataLoaded with:\n").append(map.toString()).toString());
                ConversionDataListener.this.onConversionDataLoaded(map);
            }

            public final void onConversionDataFail(String str) {
                AFLogger.afDebugLog("Calling onConversionFailure with:\n".concat(String.valueOf(str)));
                ConversionDataListener.this.onConversionFailure(str);
            }

            public final void onAppOpenAttribution(Map<String, String> map) {
            }

            public final void onAttributionFailure(String str) {
            }
        };
    }

    /* access modifiers changed from: private */
    /* renamed from: ɩ */
    public static Map<String, Object> m265(String str) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!f409.contains(next)) {
                    hashMap.put(next, jSONObject.isNull(next) ? null : jSONObject.get(next));
                }
            }
            return hashMap;
        } catch (JSONException e) {
            AFLogger.afErrorLog(e.getMessage(), e);
            return null;
        }
    }

    /* renamed from: ı */
    private void m241(Context context, String str, String str2, Intent intent) {
        AFEvent context2 = new Launch().context(context);
        context2.f363 = null;
        AFEvent key = context2.key(str);
        key.f359 = null;
        key.f350 = str2;
        key.f351 = intent;
        key.f361 = null;
        m275(key);
    }

    /* renamed from: Ι */
    private void m275(AFEvent aFEvent) {
        aFEvent.mo14501();
        boolean z = aFEvent.f363 == null;
        if (m288()) {
            AFLogger.afInfoLog("CustomerUserId not set, Tracking is disabled", true);
            return;
        }
        if (z) {
            if (!AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.LAUNCH_PROTECT_ENABLED, true)) {
                AFLogger.afInfoLog("Allowing multiple launches within a 5 second time window.");
            } else if (m278()) {
                return;
            }
            this.f416 = System.currentTimeMillis();
        }
        m267(AFExecutor.getInstance().mo14505(), new C0909e(this, aFEvent.weakContext(), (byte) 0), 150, TimeUnit.MILLISECONDS);
    }

    /* renamed from: Ι */
    private boolean m278() {
        if (this.f416 > 0) {
            long currentTimeMillis = System.currentTimeMillis() - this.f416;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS Z", Locale.US);
            long j = this.f416;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String format = simpleDateFormat.format(new Date(j));
            long j2 = this.f410;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String format2 = simpleDateFormat.format(new Date(j2));
            if (currentTimeMillis < this.f423 && !isTrackingStopped()) {
                AFLogger.afInfoLog(String.format(Locale.US, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nThis launch is blocked: %s ms < %s ms", new Object[]{format, format2, Long.valueOf(currentTimeMillis), Long.valueOf(this.f423)}));
                return true;
            } else if (!isTrackingStopped()) {
                AFLogger.afInfoLog(String.format(Locale.US, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nSending launch (+%s ms)", new Object[]{format, format2, Long.valueOf(currentTimeMillis)}));
            }
        } else if (!isTrackingStopped()) {
            AFLogger.afInfoLog("Sending first launch for this session!");
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: ı */
    public void m242(AFEvent aFEvent) {
        String str;
        ScheduledExecutorService r2;
        long j;
        boolean z = false;
        Context context = aFEvent.context();
        String str2 = aFEvent.f363;
        if (context == null) {
            AFLogger.afDebugLog("sendTrackingWithEvent - got null context. skipping event/launch.");
            return;
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        AppsFlyerProperties.getInstance().saveProperties(sharedPreferences);
        if (!isTrackingStopped()) {
            AFLogger.afInfoLog(new StringBuilder("sendTrackingWithEvent from activity: ").append(context.getClass().getName()).toString());
        }
        boolean z2 = str2 == null;
        boolean z3 = aFEvent instanceof BackgroundReferrerLaunch;
        boolean z4 = aFEvent instanceof Attr;
        aFEvent.f352 = z2;
        Map<String, Object> r8 = mo14603(aFEvent);
        String str3 = (String) r8.get("appsflyerKey");
        if (str3 == null || str3.length() == 0) {
            AFLogger.afDebugLog("Not sending data yet, waiting for dev key");
            return;
        }
        if (!isTrackingStopped()) {
            AFLogger.afInfoLog("AppsFlyerLib.sendTrackingWithEvent");
        }
        int launchCounter = getLaunchCounter(sharedPreferences, false);
        if (z4 || z3) {
            str = ServerConfigHandler.getUrl(REFERRER_TRACKING_URL);
        } else if (!z2) {
            str = ServerConfigHandler.getUrl(f406);
        } else if (launchCounter < 2) {
            str = ServerConfigHandler.getUrl(FIRST_LAUNCHES_URL);
        } else {
            str = ServerConfigHandler.getUrl(f405);
        }
        String obj = new StringBuilder().append(new StringBuilder().append(str).append(context.getPackageName()).toString()).append("&buildnumber=5.4.0").toString();
        String configuredChannel = getConfiguredChannel(context);
        if (configuredChannel != null) {
            obj = new StringBuilder().append(obj).append("&channel=").append(configuredChannel).toString();
        }
        if (!(AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, false) || AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, false)) && r8.get(ServerParameters.ADVERTISING_ID_PARAM) != null) {
            try {
                if (TextUtils.isEmpty(this.f428) && r8.remove("android_id") != null) {
                    AFLogger.afInfoLog("validateGaidAndIMEI :: removing: android_id");
                }
                if (TextUtils.isEmpty(this.f427) && r8.remove("imei") != null) {
                    AFLogger.afInfoLog("validateGaidAndIMEI :: removing: imei");
                }
            } catch (Exception e) {
                AFLogger.afErrorLog("failed to remove IMEI or AndroidID key from params; ", e);
            }
        }
        AFEvent r0 = aFEvent.urlString(obj).params(r8).mo14501();
        r0.f353 = launchCounter;
        C0906a aVar = new C0906a(this, r0, (byte) 0);
        if (z2) {
            if (GoogleReferrer.allow(this, context) && !m268()) {
                AFLogger.afDebugLog("Failed to get new referrer, wait ...");
                z = true;
            }
            if (this.f434 != null && this.f434.valid()) {
                z = true;
            }
            if (this.f431 && !m245()) {
                AFLogger.afDebugLog("fetching Facebook deferred AppLink data, wait ...");
                z = true;
            }
            if (this.f433 && !m294()) {
                z = true;
            }
        }
        if (AFDeepLinkManager.f344) {
            AFLogger.afRDLog("ESP deeplink: execute launch on SerialExecutor");
            AFExecutor instance2 = AFExecutor.getInstance();
            if (instance2.f367 == null) {
                instance2.f367 = Executors.newSingleThreadScheduledExecutor(instance2.f366);
            }
            r2 = instance2.f367;
        } else {
            r2 = AFExecutor.getInstance().mo14505();
        }
        if (z) {
            j = 500;
        } else {
            j = 0;
        }
        m267(r2, aVar, j, TimeUnit.MILLISECONDS);
    }

    /* renamed from: ı */
    private boolean m247(AFEvent aFEvent, SharedPreferences sharedPreferences) {
        boolean z;
        int launchCounter = getLaunchCounter(sharedPreferences, false);
        boolean z2 = launchCounter == 1 && !(aFEvent instanceof Attr);
        if (sharedPreferences.getBoolean(AppsFlyerProperties.NEW_REFERRER_SENT, false) || launchCounter != 1) {
            z = false;
        } else {
            z = true;
        }
        if (z || z2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: ɩ */
    public boolean m268() {
        return this.f425 != null && this.f425.oldMap.size() > 0;
    }

    /* renamed from: ı */
    private boolean m245() {
        return this.f426 != null && !this.f426.isEmpty();
    }

    /* access modifiers changed from: private */
    /* renamed from: і */
    public boolean m294() {
        return this.f429 != null && !this.f429.isEmpty();
    }

    /* renamed from: ɩ */
    public final Map<String, Object> mo14603(AFEvent aFEvent) {
        Map map;
        String str;
        String str2;
        boolean z;
        boolean z2;
        String str3;
        long j;
        UiModeManager uiModeManager;
        Context context = aFEvent.context();
        String key = aFEvent.key();
        String str4 = aFEvent.f363;
        if (aFEvent.f359 == null) {
            map = new HashMap();
        } else {
            map = aFEvent.f359;
        }
        String jSONObject = new JSONObject(map).toString();
        String str5 = aFEvent.f350;
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        boolean r13 = aFEvent.mo14502();
        Intent intent = aFEvent.intent();
        String str6 = aFEvent.f361;
        HashMap hashMap = new HashMap();
        C0961v.m403(context, hashMap);
        long time = new Date().getTime();
        hashMap.put("af_timestamp", Long.toString(time));
        String r4 = C0937c.m360(context, time);
        if (r4 != null) {
            hashMap.put("cksm_v1", r4);
        }
        try {
            if (!isTrackingStopped()) {
                AFLogger.afInfoLog(new StringBuilder("******* sendTrackingWithEvent: ").append(r13 ? "Launch" : str4).toString());
            } else {
                AFLogger.afInfoLog("SDK tracking has been stopped");
            }
            C0916aa.m315();
            if (!C0916aa.m318(context).exists()) {
                C0916aa.m318(context).mkdir();
            }
        } catch (Exception e) {
            Log.i(LOG_TAG, "Could not create cache directory");
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getLocalizedMessage(), th);
        }
        try {
            List asList = Arrays.asList(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions);
            if (!asList.contains("android.permission.INTERNET")) {
                AFLogger.afWarnLog("Permission android.permission.INTERNET is missing in the AndroidManifest.xml");
            }
            if (!asList.contains("android.permission.ACCESS_NETWORK_STATE")) {
                AFLogger.afWarnLog("Permission android.permission.ACCESS_NETWORK_STATE is missing in the AndroidManifest.xml");
            }
            if (!asList.contains("android.permission.ACCESS_WIFI_STATE")) {
                AFLogger.afWarnLog("Permission android.permission.ACCESS_WIFI_STATE is missing in the AndroidManifest.xml");
            }
        } catch (Exception e2) {
            AFLogger.afErrorLog("Exception while validation permissions. ", e2);
        }
        hashMap.put("af_events_api", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("brand", Build.BRAND);
        hashMap.put("device", Build.DEVICE);
        hashMap.put("product", Build.PRODUCT);
        hashMap.put(ServerProtocol.DIALOG_PARAM_SDK_VERSION, Integer.toString(Build.VERSION.SDK_INT));
        hashMap.put("model", Build.MODEL);
        hashMap.put("deviceType", Build.TYPE);
        m259(context, (Map<String, Object>) hashMap);
        AppsFlyerProperties instance2 = AppsFlyerProperties.getInstance();
        if (r13) {
            if (m295(context)) {
                if (!instance2.isOtherSdkStringDisabled()) {
                    hashMap.put("batteryLevel", String.valueOf(m292(context)));
                }
                m266(context);
                if (Build.VERSION.SDK_INT >= 23) {
                    uiModeManager = (UiModeManager) context.getSystemService(UiModeManager.class);
                } else {
                    uiModeManager = (UiModeManager) context.getSystemService("uimode");
                }
                if (uiModeManager != null && uiModeManager.getCurrentModeType() == 4) {
                    hashMap.put("tv", Boolean.TRUE);
                }
                if (AFInstantApps.isInstantApp(context)) {
                    hashMap.put("inst_app", Boolean.TRUE);
                }
            }
            long j2 = getSharedPreferences(context).getLong("AppsFlyerTimePassedSincePrevLaunch", 0);
            long currentTimeMillis = System.currentTimeMillis();
            m284(context, "AppsFlyerTimePassedSincePrevLaunch", currentTimeMillis);
            if (j2 > 0) {
                j = (currentTimeMillis - j2) / 1000;
            } else {
                j = -1;
            }
            hashMap.put("timepassedsincelastlaunch", Long.toString(j));
            String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_ID);
            String string2 = AppsFlyerProperties.getInstance().getString("onelinkVersion");
            if (string != null) {
                hashMap.put("onelink_id", string);
            }
            if (string2 != null) {
                hashMap.put("onelink_ver", string2);
            }
            long j3 = sharedPreferences.getLong("appsflyerGetConversionDataTiming", 0);
            if (j3 > 0) {
                hashMap.put("gcd_timing", Long.toString(j3));
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putLong("appsflyerGetConversionDataTiming", 0);
                edit.apply();
            }
            if (this.f435 != null) {
                hashMap.put("phone", this.f435);
            }
            if (!TextUtils.isEmpty(str5)) {
                hashMap.put(Payload.REFERRER, str5);
            }
            String string3 = sharedPreferences.getString("extraReferrers", (String) null);
            if (string3 != null) {
                hashMap.put("extraReferrers", string3);
            }
            String referrer = instance2.getReferrer(context);
            if (!TextUtils.isEmpty(referrer) && hashMap.get(Payload.REFERRER) == null) {
                hashMap.put(Payload.REFERRER, referrer);
            }
        } else {
            SharedPreferences sharedPreferences2 = getSharedPreferences(context);
            SharedPreferences.Editor edit2 = sharedPreferences2.edit();
            try {
                String string4 = sharedPreferences2.getString("prev_event_name", (String) null);
                if (string4 != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("prev_event_timestamp", new StringBuilder().append(sharedPreferences2.getLong("prev_event_timestamp", -1)).toString());
                    jSONObject2.put("prev_event_value", sharedPreferences2.getString("prev_event_value", (String) null));
                    jSONObject2.put("prev_event_name", string4);
                    hashMap.put("prev_event", jSONObject2.toString());
                }
                edit2.putString("prev_event_name", str4);
                edit2.putString("prev_event_value", jSONObject);
                edit2.putLong("prev_event_timestamp", System.currentTimeMillis());
                edit2.apply();
            } catch (Exception e3) {
                AFLogger.afErrorLog("Error while processing previous event.", e3);
            }
        }
        String string5 = AppsFlyerProperties.getInstance().getString("KSAppsFlyerId");
        String string6 = AppsFlyerProperties.getInstance().getString("KSAppsFlyerRICounter");
        if (!(string5 == null || string6 == null || Integer.valueOf(string6).intValue() <= 0)) {
            hashMap.put("reinstallCounter", string6);
            hashMap.put("originalAppsflyerId", string5);
        }
        String string7 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ADDITIONAL_CUSTOM_DATA);
        if (string7 != null) {
            hashMap.put("customData", string7);
        }
        try {
            String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            if (installerPackageName != null) {
                hashMap.put("installer_package", installerPackageName);
            }
        } catch (Exception e4) {
            AFLogger.afErrorLog("Exception while getting the app's installer package. ", e4);
        }
        String string8 = instance2.getString(AppsFlyerProperties.EXTENSION);
        if (string8 != null && string8.length() > 0) {
            hashMap.put(AppsFlyerProperties.EXTENSION, string8);
        }
        String configuredChannel = getConfiguredChannel(context);
        String r5 = m252(context, configuredChannel);
        if ((r5 != null && !r5.equals(configuredChannel)) || (r5 == null && configuredChannel != null)) {
            hashMap.put("af_latestchannel", configuredChannel);
        }
        SharedPreferences sharedPreferences3 = getSharedPreferences(context);
        if (sharedPreferences3.contains("INSTALL_STORE")) {
            str = sharedPreferences3.getString("INSTALL_STORE", (String) null);
        } else {
            if (m295(context)) {
                str = AppsFlyerProperties.getInstance().getString("api_store_value");
                if (str == null) {
                    if (context != null) {
                        str = m263("AF_STORE", context.getPackageManager(), context.getPackageName());
                    }
                }
                SharedPreferences.Editor edit3 = getSharedPreferences(context).edit();
                edit3.putString("INSTALL_STORE", str);
                edit3.apply();
            }
            str = null;
            SharedPreferences.Editor edit32 = getSharedPreferences(context).edit();
            edit32.putString("INSTALL_STORE", str);
            edit32.apply();
        }
        if (str != null) {
            hashMap.put("af_installstore", str.toLowerCase());
        }
        SharedPreferences sharedPreferences4 = getSharedPreferences(context);
        String string9 = AppsFlyerProperties.getInstance().getString("preInstallName");
        if (string9 == null) {
            if (sharedPreferences4.contains("preInstallName")) {
                string9 = sharedPreferences4.getString("preInstallName", (String) null);
            } else {
                if (m295(context)) {
                    File r42 = m270(m237(PRE_INSTALL_SYSTEM_RO_PROP));
                    if (m269(r42)) {
                        r42 = m270(m263(AF_PRE_INSTALL_PATH, context.getPackageManager(), context.getPackageName()));
                    }
                    if (m269(r42)) {
                        r42 = m270(PRE_INSTALL_SYSTEM_DEFAULT);
                    }
                    if (m269(r42)) {
                        r42 = m270(PRE_INSTALL_SYSTEM_DEFAULT_ETC);
                    }
                    if (m269(r42) || (string9 = m253(r42, context.getPackageName())) == null) {
                        string9 = null;
                    }
                    if (string9 == null) {
                        if (context == null) {
                            string9 = null;
                        } else {
                            string9 = m263("AF_PRE_INSTALL_NAME", context.getPackageManager(), context.getPackageName());
                        }
                    }
                }
                if (string9 != null) {
                    SharedPreferences.Editor edit4 = getSharedPreferences(context).edit();
                    edit4.putString("preInstallName", string9);
                    edit4.apply();
                }
            }
            if (string9 != null) {
                AppsFlyerProperties.getInstance().set("preInstallName", string9);
            }
        }
        if (string9 != null) {
            hashMap.put("af_preinstall_name", string9.toLowerCase());
        }
        String string10 = AppsFlyerProperties.getInstance().getString("api_store_value");
        if (string10 == null) {
            if (context == null) {
                string10 = null;
            } else {
                string10 = m263("AF_STORE", context.getPackageManager(), context.getPackageName());
            }
        }
        if (string10 != null) {
            hashMap.put("af_currentstore", string10.toLowerCase());
        }
        if (key == null || key.length() <= 0) {
            String string11 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
            if (string11 == null || string11.length() <= 0) {
                AFLogger.afInfoLog("AppsFlyer dev key is missing!!! Please use  AppsFlyerLib.getInstance().setAppsFlyerKey(...) to set it. ");
                AFLogger.afInfoLog("AppsFlyer will not track this event.");
                return null;
            }
            hashMap.put("appsflyerKey", string11);
        } else {
            hashMap.put("appsflyerKey", key);
        }
        String string12 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID);
        if (string12 != null) {
            hashMap.put("appUserId", string12);
        }
        String string13 = instance2.getString(AppsFlyerProperties.USER_EMAILS);
        if (string13 != null) {
            hashMap.put("user_emails", string13);
        } else {
            String string14 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.USER_EMAIL);
            if (string14 != null) {
                hashMap.put("sha1_el", C0968z.m412(string14));
            }
        }
        if (str4 != null) {
            hashMap.put("eventName", str4);
            if (jSONObject != null) {
                hashMap.put("eventValue", jSONObject);
            }
        }
        if (AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_ID) != null) {
            hashMap.put(AppsFlyerProperties.APP_ID, AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_ID));
        }
        String string15 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.CURRENCY_CODE);
        if (string15 != null) {
            if (string15.length() != 3) {
                AFLogger.afWarnLog(new StringBuilder("WARNING: currency code should be 3 characters!!! '").append(string15).append("' is not a legal value.").toString());
            }
            hashMap.put(FirebaseAnalytics.Param.CURRENCY, string15);
        }
        String string16 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.IS_UPDATE);
        if (string16 != null) {
            hashMap.put("isUpdate", string16);
        }
        hashMap.put("af_preinstalled", Boolean.toString(isPreInstalledApp(context)));
        if (instance2.getBoolean(AppsFlyerProperties.COLLECT_FACEBOOK_ATTR_ID, true)) {
            try {
                context.getPackageManager().getApplicationInfo(Constant.PAK_FACEBOOK, 0);
                str3 = getAttributionId(context);
            } catch (PackageManager.NameNotFoundException e5) {
                str3 = null;
                AFLogger.afWarnLog("Exception while collecting facebook's attribution ID. ");
            } catch (Throwable th2) {
                AFLogger.afErrorLog("Exception while collecting facebook's attribution ID. ", th2);
                str3 = null;
            }
            if (str3 != null) {
                hashMap.put("fb", str3);
            }
        }
        AppsFlyerProperties instance3 = AppsFlyerProperties.getInstance();
        if (instance3.getBoolean(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, false)) {
            hashMap.put(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, "true");
        } else {
            SharedPreferences sharedPreferences5 = getSharedPreferences(context);
            boolean z3 = instance3.getBoolean(AppsFlyerProperties.COLLECT_IMEI, true);
            String string17 = sharedPreferences5.getString("imeiCached", (String) null);
            String str7 = null;
            if (!z3 || !TextUtils.isEmpty(this.f427)) {
                if (this.f427 != null) {
                    str2 = this.f427;
                }
                str2 = null;
            } else {
                if (m248(context)) {
                    try {
                        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                        str2 = (String) telephonyManager.getClass().getMethod("getDeviceId", new Class[0]).invoke(telephonyManager, new Object[0]);
                        if (str2 == null) {
                            if (string17 != null) {
                                AFLogger.afDebugLog("use cached IMEI: ".concat(String.valueOf(string17)));
                                str2 = string17;
                            }
                        }
                    } catch (InvocationTargetException e6) {
                        if (string17 != null) {
                            AFLogger.afDebugLog("use cached IMEI: ".concat(String.valueOf(string17)));
                            str2 = string17;
                        } else {
                            str2 = null;
                        }
                        AFLogger.afWarnLog("WARNING: READ_PHONE_STATE is missing.");
                    } catch (Exception e7) {
                        if (string17 != null) {
                            AFLogger.afDebugLog("use cached IMEI: ".concat(String.valueOf(string17)));
                            str7 = string17;
                        }
                        AFLogger.afErrorLog("WARNING: other reason: ", e7);
                        str2 = str7;
                    }
                }
                str2 = null;
            }
            if (str2 != null) {
                SharedPreferences.Editor edit5 = getSharedPreferences(context).edit();
                edit5.putString("imeiCached", str2);
                edit5.apply();
                hashMap.put("imei", str2);
            } else {
                AFLogger.afInfoLog("IMEI was not collected.");
            }
            boolean z4 = instance3.getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID, true);
            String string18 = sharedPreferences5.getString("androidIdCached", (String) null);
            String str8 = null;
            if (!z4 || !TextUtils.isEmpty(this.f428)) {
                if (this.f428 != null) {
                    str8 = this.f428;
                }
            } else if (m248(context)) {
                try {
                    String string19 = Settings.Secure.getString(context.getContentResolver(), "android_id");
                    if (string19 != null) {
                        str8 = string19;
                    } else {
                        if (string18 != null) {
                            AFLogger.afDebugLog("use cached AndroidId: ".concat(String.valueOf(string18)));
                        } else {
                            string18 = null;
                        }
                        str8 = string18;
                    }
                } catch (Exception e8) {
                    if (string18 != null) {
                        AFLogger.afDebugLog("use cached AndroidId: ".concat(String.valueOf(string18)));
                        str8 = string18;
                    }
                    AFLogger.afErrorLog(e8.getMessage(), e8);
                }
            }
            if (str8 != null) {
                SharedPreferences.Editor edit6 = getSharedPreferences(context).edit();
                edit6.putString("androidIdCached", str8);
                edit6.apply();
                hashMap.put("android_id", str8);
            } else {
                AFLogger.afInfoLog("Android ID was not collected.");
            }
            HashMap hashMap2 = new HashMap();
            String str9 = null;
            boolean z5 = this.f420 != null;
            if (z5) {
                str9 = this.f420;
            } else if (instance3.getBoolean(AppsFlyerProperties.COLLECT_OAID, true)) {
                try {
                    OaidClient oaidClient = new OaidClient(context);
                    oaidClient.setLogging(instance3.isEnableLog());
                    OaidClient.Info fetch = oaidClient.fetch();
                    if (fetch != null) {
                        str9 = fetch.getId();
                        Boolean lat = fetch.getLat();
                        if (lat != null) {
                            hashMap2.put("isLat", lat);
                        }
                    }
                } catch (Throwable th3) {
                    AFLogger.afDebugLog("No OAID library");
                }
            }
            if (str9 != null) {
                hashMap2.put("isManual", Boolean.valueOf(z5));
                hashMap2.put("val", str9);
                hashMap.put("oaid", hashMap2);
            }
        }
        try {
            String r43 = C0921ae.m326(new WeakReference(context));
            if (r43 != null) {
                hashMap.put("uid", r43);
            }
        } catch (Exception e9) {
            AFLogger.afErrorLog(new StringBuilder("ERROR: could not get uid ").append(e9.getMessage()).toString(), e9);
        }
        try {
            hashMap.put("lang", Locale.getDefault().getDisplayLanguage());
        } catch (Exception e10) {
            AFLogger.afErrorLog("Exception while collecting display language name. ", e10);
        }
        try {
            hashMap.put("lang_code", Locale.getDefault().getLanguage());
        } catch (Exception e11) {
            AFLogger.afErrorLog("Exception while collecting display language code. ", e11);
        }
        try {
            hashMap.put(UserDataStore.COUNTRY, Locale.getDefault().getCountry());
        } catch (Exception e12) {
            AFLogger.afErrorLog("Exception while collecting country name. ", e12);
        }
        hashMap.put("platformextension", this.f419.mo14687());
        m285(context, (Map<String, ? super String>) hashMap);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
        try {
            long j4 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            hashMap.put("installDate", simpleDateFormat.format(new Date(j4)));
        } catch (Exception e13) {
            AFLogger.afErrorLog("Exception while collecting install date. ", e13);
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo.versionCode > sharedPreferences.getInt("versionCode", 0)) {
                int i = packageInfo.versionCode;
                SharedPreferences.Editor edit7 = getSharedPreferences(context).edit();
                edit7.putInt("versionCode", i);
                edit7.apply();
            }
            hashMap.put("app_version_code", Integer.toString(packageInfo.versionCode));
            hashMap.put("app_version_name", packageInfo.versionName);
            long j5 = packageInfo.firstInstallTime;
            long j6 = packageInfo.lastUpdateTime;
            hashMap.put("date1", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US).format(new Date(j5)));
            hashMap.put("date2", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US).format(new Date(j6)));
            hashMap.put("firstLaunchDate", m283(simpleDateFormat, context));
        } catch (Throwable th4) {
            AFLogger.afErrorLog("Exception while collecting app version data ", th4);
        }
        this.f415 = C0922af.m328(context);
        AFLogger.afDebugLog(new StringBuilder("didConfigureTokenRefreshService=").append(this.f415).toString());
        if (!this.f415) {
            hashMap.put("tokenRefreshConfigured", Boolean.FALSE);
        }
        if (r13) {
            AFDeepLinkManager.getInstance();
            AFDeepLinkManager.m201(intent, context, hashMap);
            if (this.f413 != null) {
                JSONObject jSONObject3 = new JSONObject(this.f413);
                jSONObject3.put("isPush", "true");
                hashMap.put("af_deeplink", jSONObject3.toString());
            }
            this.f413 = null;
            hashMap.put("open_referrer", str6);
        }
        if (!r13) {
            try {
                AFSensorManager r44 = AFSensorManager.m226(context);
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                List<Map<String, Object>> r45 = r44.mo14517();
                if (!r45.isEmpty()) {
                    concurrentHashMap.put("sensors", C0941g.m382(r45));
                } else {
                    concurrentHashMap.put("sensors", "na");
                }
                hashMap.putAll(concurrentHashMap);
            } catch (Exception e14) {
                AFLogger.afRDLog(new StringBuilder("Unexpected exception from AFSensorManager: ").append(e14.getMessage()).toString());
            }
        }
        if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) == null) {
            C0961v.m403(context, hashMap);
            if (AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM) != null) {
                hashMap.put("GAID_retry", "true");
            } else {
                hashMap.put("GAID_retry", "false");
            }
        }
        C0963w r46 = C0961v.m402(context.getContentResolver());
        if (r46 != null) {
            hashMap.put("amazon_aid", r46.f645);
            hashMap.put("amazon_aid_limit", String.valueOf(r46.mo14735()));
        }
        hashMap.put("registeredUninstall", Boolean.valueOf(sharedPreferences.getBoolean("sentRegisterRequestToAF", false)));
        int launchCounter = getLaunchCounter(sharedPreferences, r13);
        hashMap.put("counter", Integer.toString(launchCounter));
        if (str4 != null) {
            z = true;
        } else {
            z = false;
        }
        hashMap.put("iaecounter", Integer.toString(m250(sharedPreferences, "appsFlyerInAppEventCount", z)));
        if (r13 && launchCounter == 1) {
            instance2.setFirstLaunchCalled();
            if (m290(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID)) {
                hashMap.put("wait_cid", Boolean.toString(true));
            }
        }
        if (!m246(sharedPreferences)) {
            z2 = true;
        } else {
            z2 = false;
        }
        hashMap.put("isFirstCall", Boolean.toString(z2));
        HashMap hashMap3 = new HashMap();
        hashMap3.put("cpu_abi", m237("ro.product.cpu.abi"));
        hashMap3.put("cpu_abi2", m237("ro.product.cpu.abi2"));
        hashMap3.put("arch", m237("os.arch"));
        hashMap3.put("build_display_id", m237("ro.build.display.id"));
        if (r13) {
            if (this.f417) {
                C0946p pVar = C0946p.C0947a.f621;
                Location r6 = C0946p.m384(context);
                HashMap hashMap4 = new HashMap(3);
                if (r6 != null) {
                    hashMap4.put("lat", String.valueOf(r6.getLatitude()));
                    hashMap4.put("lon", String.valueOf(r6.getLongitude()));
                    hashMap4.put("ts", String.valueOf(r6.getTime()));
                }
                if (!hashMap4.isEmpty()) {
                    hashMap3.put("loc", hashMap4);
                }
            }
            C0934b.C0935d r62 = C0934b.C0936e.f565.mo14708(context);
            hashMap3.put("btl", Float.toString(r62.f563));
            if (r62.f564 != null) {
                hashMap3.put("btch", r62.f564);
            }
            if (launchCounter <= 2) {
                AFSensorManager r52 = AFSensorManager.m226(context);
                ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
                List<Map<String, Object>> r8 = r52.mo14519();
                if (!r8.isEmpty()) {
                    concurrentHashMap2.put("sensors", r8);
                } else {
                    List<Map<String, Object>> r53 = r52.mo14517();
                    if (!r53.isEmpty()) {
                        concurrentHashMap2.put("sensors", r53);
                    }
                }
                hashMap3.putAll(concurrentHashMap2);
            }
        }
        hashMap3.put("dim", C0948q.m386(context));
        hashMap.put("deviceData", hashMap3);
        String str10 = (String) hashMap.get("af_timestamp");
        hashMap.put("af_v", C0968z.m412(new StringBuilder().append(((String) hashMap.get("appsflyerKey")).substring(0, 7)).append(((String) hashMap.get("uid")).substring(0, 7)).append(str10.substring(str10.length() - 7)).toString()));
        hashMap.put("af_v2", C0968z.m412(C0968z.m410(new StringBuilder().append(new StringBuilder().append(new StringBuilder().append(new StringBuilder().append(new StringBuilder().append((String) hashMap.get("appsflyerKey")).append(hashMap.get("af_timestamp")).toString()).append(hashMap.get("uid")).toString()).append(hashMap.get("installDate")).toString()).append(hashMap.get("counter")).toString()).append(hashMap.get("iaecounter")).toString())));
        hashMap.put("ivc", Boolean.valueOf(m272(context)));
        if (sharedPreferences.contains(IS_STOP_TRACKING_USED)) {
            hashMap.put("istu", String.valueOf(sharedPreferences.getBoolean(IS_STOP_TRACKING_USED, false)));
        }
        if (instance2.getObject("consumeAfDeepLink") != null) {
            hashMap.put("is_dp_api", String.valueOf(instance2.getBoolean("consumeAfDeepLink", false)));
        }
        HashMap hashMap5 = new HashMap();
        hashMap5.put("mcc", Integer.valueOf(context.getResources().getConfiguration().mcc));
        hashMap5.put("mnc", Integer.valueOf(context.getResources().getConfiguration().mnc));
        hashMap.put("cell", hashMap5);
        EventDataCollector eventDataCollector = new EventDataCollector(context);
        hashMap.put("sig", eventDataCollector.signature());
        hashMap.put("last_boot_time", Long.valueOf(eventDataCollector.bootTime()));
        hashMap.put("disk", eventDataCollector.disk());
        return hashMap;
    }

    /* renamed from: ı */
    public static boolean m246(@NonNull SharedPreferences sharedPreferences) {
        return Boolean.parseBoolean(sharedPreferences.getString("sentSuccessfully", (String) null));
    }

    /* renamed from: ǃ */
    private static void m259(Context context, Map<String, Object> map) {
        String str;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            switch (windowManager.getDefaultDisplay().getRotation()) {
                case 0:
                    str = "p";
                    break;
                case 1:
                    str = "l";
                    break;
                case 2:
                    str = "pr";
                    break;
                case 3:
                    str = "lr";
                    break;
                default:
                    str = "";
                    break;
            }
            map.put("sc_o", str);
        }
    }

    public void setConsumeAFDeepLinks(boolean z) {
        AppsFlyerProperties.getInstance().set("consumeAfDeepLink", z);
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setConsumeAFDeepLinks: ".concat(String.valueOf(z)), new String[0]);
    }

    /* access modifiers changed from: protected */
    public void handleDeepLinkCallback(Context context, Map<String, Object> map, Uri uri) {
        String str;
        String substring;
        String obj = uri.toString();
        if (obj == null) {
            str = null;
        } else {
            if (obj.matches("fb\\d*?://authorize.*") && obj.contains("access_token")) {
                int indexOf = obj.indexOf(63);
                if (indexOf == -1) {
                    substring = "";
                } else {
                    substring = obj.substring(indexOf);
                }
                if (substring.length() != 0) {
                    ArrayList arrayList = new ArrayList();
                    if (substring.contains("&")) {
                        arrayList = new ArrayList(Arrays.asList(substring.split("&")));
                    } else {
                        arrayList.add(substring);
                    }
                    StringBuilder sb = new StringBuilder();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        String str2 = (String) it.next();
                        if (str2.contains("access_token")) {
                            it.remove();
                        } else {
                            if (sb.length() != 0) {
                                sb.append("&");
                            } else if (!str2.startsWith("?")) {
                                sb.append("?");
                            }
                            sb.append(str2);
                        }
                    }
                    str = obj.replace(substring, sb.toString());
                }
            }
            str = obj;
        }
        if (!map.containsKey("af_deeplink")) {
            map.put("af_deeplink", str);
        }
        final HashMap hashMap = new HashMap();
        hashMap.put("link", uri.toString());
        final WeakReference weakReference = new WeakReference(context);
        C0917ab abVar = new C0917ab(uri, this);
        abVar.setConnProvider(new OneLinkHttpTask.HttpsUrlConnectionProvider());
        if (abVar.mo14683()) {
            abVar.f508 = new C0917ab.C0918b() {
                /* renamed from: ı */
                public final void mo14612(String str) {
                    if (AppsFlyerLibCore.f404 != null) {
                        m306(hashMap);
                        AFLogger.afDebugLog("Calling onAttributionFailure with:\n".concat(String.valueOf(str)));
                        AppsFlyerLibCore.f404.onAttributionFailure(str);
                    }
                }

                /* renamed from: ι */
                private void m306(Map<String, String> map) {
                    if (weakReference.get() != null) {
                        AppsFlyerLibCore.m258((Context) weakReference.get(), "deeplinkAttribution", new JSONObject(map).toString());
                    }
                }

                /* renamed from: ɩ */
                public final void mo14613(Map<String, String> map) {
                    for (String next : map.keySet()) {
                        hashMap.put(next, map.get(next));
                    }
                    m306(hashMap);
                    AppsFlyerLibCore.m277((Map<String, String>) hashMap);
                }
            };
            AFExecutor.getInstance().getThreadPoolExecutor().execute(abVar);
            return;
        }
        m277(AndroidUtils.m234(context, hashMap, uri));
    }

    /* access modifiers changed from: private */
    /* renamed from: Ι */
    public static void m277(Map<String, String> map) {
        if (f404 != null) {
            try {
                AFLogger.afDebugLog(new StringBuilder("Calling onAppOpenAttribution with:\n").append(map.toString()).toString());
                f404.onAppOpenAttribution(map);
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getLocalizedMessage(), th);
            }
        }
    }

    /* renamed from: ι */
    private static boolean m289(Context context) {
        try {
            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0) {
                return true;
            }
        } catch (Throwable th) {
            AFLogger.afErrorLog("WARNING:  Google play services is unavailable. ", th);
        }
        try {
            context.getPackageManager().getPackageInfo("com.google.android.gms", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            AFLogger.afErrorLog("WARNING:  Google Play Services is unavailable. ", e);
            return false;
        }
    }

    /* renamed from: Ɩ */
    private static boolean m248(Context context) {
        boolean z;
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, false) || AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, false)) {
            z = true;
        } else {
            z = false;
        }
        if (z || !m289(context)) {
            return true;
        }
        return false;
    }

    /* renamed from: Ӏ */
    private static boolean m295(Context context) {
        return !getSharedPreferences(context).contains("appsFlyerCount");
    }

    /* renamed from: ı */
    private static String m237(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class}).invoke((Object) null, new Object[]{str});
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return null;
        }
    }

    @Nullable
    /* renamed from: ɩ */
    private static String m263(String str, PackageManager packageManager, String str2) {
        Object obj;
        try {
            Bundle bundle = packageManager.getApplicationInfo(str2, 128).metaData;
            if (!(bundle == null || (obj = bundle.get(str)) == null)) {
                String obj2 = obj.toString();
                if (obj2.replaceAll("\\p{C}", "").equals(obj2)) {
                    return obj2;
                }
                AFLogger.afWarnLog(new StringBuilder("Manifest meta-data ").append(str).append(": ").append(obj2).append(" contains non-printing characters").toString());
                return obj2;
            }
        } catch (Throwable th) {
            AFLogger.afErrorLog(new StringBuilder("Could not find ").append(str).append(" value in the manifest").toString(), th);
        }
        return null;
    }

    public void setPreinstallAttribution(String str, String str2, String str3) {
        AFLogger.afDebugLog("setPreinstallAttribution API called");
        JSONObject jSONObject = new JSONObject();
        if (str != null) {
            try {
                jSONObject.put(Constants.URL_MEDIA_SOURCE, str);
            } catch (JSONException e) {
                AFLogger.afErrorLog(e.getMessage(), e);
            }
        }
        if (str2 != null) {
            jSONObject.put(Constants.URL_CAMPAIGN, str2);
        }
        if (str3 != null) {
            jSONObject.put(Constants.URL_SITE_ID, str3);
        }
        if (jSONObject.has(Constants.URL_MEDIA_SOURCE)) {
            AppsFlyerProperties.getInstance().set("preInstallName", jSONObject.toString());
            return;
        }
        AFLogger.afWarnLog("Cannot set preinstall attribution data without a media source");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057 A[SYNTHETIC, Splitter:B:24:0x0057] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0069 A[SYNTHETIC, Splitter:B:31:0x0069] */
    /* renamed from: ǃ */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m253(java.io.File r4, java.lang.String r5) {
        /*
            r0 = 0
            java.util.Properties r2 = new java.util.Properties     // Catch:{ FileNotFoundException -> 0x0024, Throwable -> 0x004b, all -> 0x0064 }
            r2.<init>()     // Catch:{ FileNotFoundException -> 0x0024, Throwable -> 0x004b, all -> 0x0064 }
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x0024, Throwable -> 0x004b, all -> 0x0064 }
            r1.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0024, Throwable -> 0x004b, all -> 0x0064 }
            r2.load(r1)     // Catch:{ FileNotFoundException -> 0x0080, Throwable -> 0x007d }
            java.lang.String r3 = "Found PreInstall property!"
            com.appsflyer.AFLogger.afInfoLog(r3)     // Catch:{ FileNotFoundException -> 0x0080, Throwable -> 0x007d }
            java.lang.String r0 = r2.getProperty(r5)     // Catch:{ FileNotFoundException -> 0x0080, Throwable -> 0x007d }
            r1.close()     // Catch:{ Throwable -> 0x001b }
        L_0x001a:
            return r0
        L_0x001b:
            r1 = move-exception
            java.lang.String r2 = r1.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r2, r1)
            goto L_0x001a
        L_0x0024:
            r1 = move-exception
            r1 = r0
        L_0x0026:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0076 }
            java.lang.String r3 = "PreInstall file wasn't found: "
            r2.<init>(r3)     // Catch:{ all -> 0x0076 }
            java.lang.String r3 = r4.getAbsolutePath()     // Catch:{ all -> 0x0076 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0076 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0076 }
            com.appsflyer.AFLogger.afDebugLog(r2)     // Catch:{ all -> 0x0076 }
            if (r1 == 0) goto L_0x001a
            r1.close()     // Catch:{ Throwable -> 0x0042 }
            goto L_0x001a
        L_0x0042:
            r1 = move-exception
            java.lang.String r2 = r1.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r2, r1)
            goto L_0x001a
        L_0x004b:
            r1 = move-exception
            r2 = r1
            r3 = r0
        L_0x004e:
            java.lang.String r1 = r2.getMessage()     // Catch:{ all -> 0x007a }
            com.appsflyer.AFLogger.afErrorLog(r1, r2)     // Catch:{ all -> 0x007a }
            if (r3 == 0) goto L_0x001a
            r3.close()     // Catch:{ Throwable -> 0x005b }
            goto L_0x001a
        L_0x005b:
            r1 = move-exception
            java.lang.String r2 = r1.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r2, r1)
            goto L_0x001a
        L_0x0064:
            r1 = move-exception
            r2 = r1
            r3 = r0
        L_0x0067:
            if (r3 == 0) goto L_0x006c
            r3.close()     // Catch:{ Throwable -> 0x006d }
        L_0x006c:
            throw r2
        L_0x006d:
            r0 = move-exception
            java.lang.String r1 = r0.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r1, r0)
            goto L_0x006c
        L_0x0076:
            r0 = move-exception
            r2 = r0
            r3 = r1
            goto L_0x0067
        L_0x007a:
            r0 = move-exception
            r2 = r0
            goto L_0x0067
        L_0x007d:
            r2 = move-exception
            r3 = r1
            goto L_0x004e
        L_0x0080:
            r2 = move-exception
            goto L_0x0026
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLibCore.m253(java.io.File, java.lang.String):java.lang.String");
    }

    /* renamed from: ɩ */
    private static boolean m269(File file) {
        return file == null || !file.exists();
    }

    /* renamed from: ɹ */
    private static File m270(String str) {
        if (str != null) {
            try {
                if (str.trim().length() > 0) {
                    return new File(str.trim());
                }
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getMessage(), th);
            }
        }
        return null;
    }

    @Nullable
    public String getConfiguredChannel(Context context) {
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.CHANNEL);
        if (string == null) {
            if (context == null) {
                string = null;
            } else {
                string = m263("CHANNEL", context.getPackageManager(), context.getPackageName());
            }
        }
        if (string == null || !string.equals("")) {
            return string;
        }
        return null;
    }

    public boolean isPreInstalledApp(Context context) {
        try {
            if ((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 1) != 0) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            AFLogger.afErrorLog("Could not check if app is pre installed", e);
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: ǃ */
    public static String m252(Context context, String str) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences.contains("CACHED_CHANNEL")) {
            return sharedPreferences.getString("CACHED_CHANNEL", (String) null);
        }
        SharedPreferences.Editor edit = getSharedPreferences(context).edit();
        edit.putString("CACHED_CHANNEL", str);
        edit.apply();
        return str;
    }

    /* renamed from: ι */
    private static String m283(SimpleDateFormat simpleDateFormat, Context context) {
        String string = getSharedPreferences(context).getString("appsFlyerFirstInstall", (String) null);
        if (string == null) {
            if (m295(context)) {
                AFLogger.afDebugLog("AppsFlyer: first launch detected");
                string = simpleDateFormat.format(new Date());
            } else {
                string = "";
            }
            SharedPreferences.Editor edit = getSharedPreferences(context).edit();
            edit.putString("appsFlyerFirstInstall", string);
            edit.apply();
        }
        AFLogger.afInfoLog("AppsFlyer: first launch date: ".concat(String.valueOf(string)));
        return string;
    }

    public String getAttributionId(Context context) {
        try {
            return new C0919ac(context).mo14684();
        } catch (Throwable th) {
            AFLogger.afErrorLog("Could not collect facebook attribution id. ", th);
            return null;
        }
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getApplicationContext().getSharedPreferences("appsflyer-data", 0);
    }

    public final int getLaunchCounter(SharedPreferences sharedPreferences, boolean z) {
        return m250(sharedPreferences, "appsFlyerCount", z);
    }

    /* renamed from: ǃ */
    private static int m250(SharedPreferences sharedPreferences, String str, boolean z) {
        int i = sharedPreferences.getInt(str, 0);
        if (z) {
            i++;
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(str, i);
            edit.apply();
        }
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        if (C0928ai.f525.mo14697()) {
            if (C0928ai.f525 == null) {
                C0928ai.f525 = new C0928ai();
            }
            C0928ai.f525.mo14693(String.valueOf(i));
        }
        return i;
    }

    /* access modifiers changed from: private */
    /* renamed from: ɩ */
    public static void m267(ScheduledExecutorService scheduledExecutorService, Runnable runnable, long j, TimeUnit timeUnit) {
        if (scheduledExecutorService != null) {
            try {
                if (!scheduledExecutorService.isShutdown() && !scheduledExecutorService.isTerminated()) {
                    scheduledExecutorService.schedule(runnable, j, timeUnit);
                    return;
                }
            } catch (RejectedExecutionException e) {
                AFLogger.afErrorLog("scheduleJob failed with RejectedExecutionException Exception", e);
                return;
            } catch (Throwable th) {
                AFLogger.afErrorLog("scheduleJob failed with Exception", th);
                return;
            }
        }
        AFLogger.afWarnLog("scheduler is null, shut downed or terminated");
    }

    public boolean isTrackingStopped() {
        return this.f422;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0047 A[SYNTHETIC, Splitter:B:16:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004c A[Catch:{ Throwable -> 0x0092 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0067 A[SYNTHETIC, Splitter:B:29:0x0067] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006c A[Catch:{ Throwable -> 0x008b }] */
    @android.support.annotation.NonNull
    /* renamed from: ǃ */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String mo14600(java.net.HttpURLConnection r7) {
        /*
            r6 = this;
            r2 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.InputStream r0 = r7.getErrorStream()     // Catch:{ Throwable -> 0x0094, all -> 0x0062 }
            if (r0 != 0) goto L_0x0010
            java.io.InputStream r0 = r7.getInputStream()     // Catch:{ Throwable -> 0x0094, all -> 0x0062 }
        L_0x0010:
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0094, all -> 0x0062 }
            r1.<init>(r0)     // Catch:{ Throwable -> 0x0094, all -> 0x0062 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0098, all -> 0x008d }
            r3.<init>(r1)     // Catch:{ Throwable -> 0x0098, all -> 0x008d }
        L_0x001a:
            java.lang.String r0 = r3.readLine()     // Catch:{ Throwable -> 0x002a }
            if (r0 == 0) goto L_0x0059
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ Throwable -> 0x002a }
            r2 = 10
            r0.append(r2)     // Catch:{ Throwable -> 0x002a }
            goto L_0x001a
        L_0x002a:
            r0 = move-exception
        L_0x002b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0090 }
            java.lang.String r5 = "Could not read connection response from: "
            r2.<init>(r5)     // Catch:{ all -> 0x0090 }
            java.net.URL r5 = r7.getURL()     // Catch:{ all -> 0x0090 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0090 }
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0090 }
            com.appsflyer.AFLogger.afErrorLog(r2, r0)     // Catch:{ all -> 0x0090 }
            if (r3 == 0) goto L_0x004a
            r3.close()     // Catch:{ Throwable -> 0x0092 }
        L_0x004a:
            if (r1 == 0) goto L_0x004f
            r1.close()     // Catch:{ Throwable -> 0x0092 }
        L_0x004f:
            java.lang.String r0 = r4.toString()
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0070 }
            r1.<init>(r0)     // Catch:{ JSONException -> 0x0070 }
        L_0x0058:
            return r0
        L_0x0059:
            r3.close()     // Catch:{ Throwable -> 0x0060 }
            r1.close()     // Catch:{ Throwable -> 0x0060 }
            goto L_0x004f
        L_0x0060:
            r0 = move-exception
            goto L_0x004f
        L_0x0062:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L_0x0065:
            if (r3 == 0) goto L_0x006a
            r3.close()     // Catch:{ Throwable -> 0x008b }
        L_0x006a:
            if (r1 == 0) goto L_0x006f
            r1.close()     // Catch:{ Throwable -> 0x008b }
        L_0x006f:
            throw r0
        L_0x0070:
            r1 = move-exception
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            java.lang.String r2 = "string_response"
            r1.put(r2, r0)     // Catch:{ JSONException -> 0x0080 }
            java.lang.String r0 = r1.toString()     // Catch:{ JSONException -> 0x0080 }
            goto L_0x0058
        L_0x0080:
            r0 = move-exception
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r0 = r0.toString()
            goto L_0x0058
        L_0x008b:
            r1 = move-exception
            goto L_0x006f
        L_0x008d:
            r0 = move-exception
            r3 = r2
            goto L_0x0065
        L_0x0090:
            r0 = move-exception
            goto L_0x0065
        L_0x0092:
            r0 = move-exception
            goto L_0x004f
        L_0x0094:
            r0 = move-exception
            r1 = r2
            r3 = r2
            goto L_0x002b
        L_0x0098:
            r0 = move-exception
            r3 = r2
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLibCore.mo14600(java.net.HttpURLConnection):java.lang.String");
    }

    /* renamed from: і */
    private static float m292(Context context) {
        try {
            Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1);
            int intExtra2 = registerReceiver.getIntExtra("scale", -1);
            if (intExtra == -1 || intExtra2 == -1) {
                return 50.0f;
            }
            return (((float) intExtra) / ((float) intExtra2)) * 100.0f;
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return 1.0f;
        }
    }

    /* renamed from: ɹ */
    private static boolean m272(Context context) {
        if (context != null) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                    for (Network networkCapabilities : connectivityManager.getAllNetworks()) {
                        NetworkCapabilities networkCapabilities2 = connectivityManager.getNetworkCapabilities(networkCapabilities);
                        if (networkCapabilities2.hasTransport(4) && !networkCapabilities2.hasCapability(15)) {
                            return true;
                        }
                    }
                    return false;
                } catch (Exception e) {
                    AFLogger.afErrorLog("Failed collecting ivc data", e);
                }
            } else if (Build.VERSION.SDK_INT >= 16) {
                ArrayList arrayList = new ArrayList();
                try {
                    Iterator<T> it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
                    while (it.hasNext()) {
                        NetworkInterface networkInterface = (NetworkInterface) it.next();
                        if (networkInterface.isUp()) {
                            arrayList.add(networkInterface.getName());
                        }
                    }
                    return arrayList.contains("tun0");
                } catch (Exception e2) {
                    AFLogger.afErrorLog("Failed collecting ivc data", e2);
                }
            }
        }
        return false;
    }

    public void setLogLevel(@NonNull AFLogger.LogLevel logLevel) {
        boolean z;
        if (logLevel.getLevel() > AFLogger.LogLevel.NONE.getLevel()) {
            z = true;
        } else {
            z = false;
        }
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "log", String.valueOf(z));
        AppsFlyerProperties.getInstance().set("shouldLog", z);
        AppsFlyerProperties.getInstance().set("logLevel", logLevel.getLevel());
    }

    public void setHost(String str, String str2) {
        if (str != null) {
            AppsFlyerProperties.getInstance().set("custom_host_prefix", str);
        }
        if (str2 == null || str2.isEmpty()) {
            AFLogger.afWarnLog("hostName cannot be null or empty");
        } else {
            AppsFlyerProperties.getInstance().set("custom_host", str2);
        }
    }

    public String getHostName() {
        String string = AppsFlyerProperties.getInstance().getString("custom_host");
        return string != null ? string : ServerParameters.DEFAULT_HOST;
    }

    @Deprecated
    public void setHostName(String str) {
        AppsFlyerProperties.getInstance().set("custom_host", str);
    }

    public String getHostPrefix() {
        String string = AppsFlyerProperties.getInstance().getString("custom_host_prefix");
        return string != null ? string : "";
    }

    public void setMinTimeBetweenSessions(int i) {
        this.f423 = TimeUnit.SECONDS.toMillis((long) i);
    }

    /* renamed from: com.appsflyer.AppsFlyerLibCore$e */
    class C0909e implements Runnable {

        /* renamed from: ı */
        private AFEvent f465;

        /* synthetic */ C0909e(AppsFlyerLibCore appsFlyerLibCore, AFEvent aFEvent, byte b) {
            this(aFEvent);
        }

        private C0909e(AFEvent aFEvent) {
            this.f465 = aFEvent;
        }

        public final void run() {
            AppsFlyerLibCore appsFlyerLibCore = AppsFlyerLibCore.this;
            AFEvent aFEvent = this.f465;
            aFEvent.f360 = aFEvent.f349.get();
            appsFlyerLibCore.m242(aFEvent);
        }
    }

    /* renamed from: com.appsflyer.AppsFlyerLibCore$a */
    class C0906a implements Runnable {

        /* renamed from: ɩ */
        private final AFEvent f453;

        /* synthetic */ C0906a(AppsFlyerLibCore appsFlyerLibCore, AFEvent aFEvent, byte b) {
            this(aFEvent);
        }

        private C0906a(AFEvent aFEvent) {
            this.f453 = aFEvent.weakContext();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:107:0x0239, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:108:0x023a, code lost:
            com.appsflyer.AFLogger.afErrorLog(r0.getMessage(), r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:120:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0104, code lost:
            r1 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x0105, code lost:
            r2 = r0;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:104:0x0235 A[SYNTHETIC, Splitter:B:104:0x0235] */
        /* JADX WARNING: Removed duplicated region for block: B:107:0x0239 A[ExcHandler: Throwable (r0v5 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:25:0x00b9] */
        /* JADX WARNING: Removed duplicated region for block: B:89:0x01c0 A[SYNTHETIC, Splitter:B:89:0x01c0] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r11 = this;
                r3 = 0
                r10 = 2
                r7 = 0
                r9 = 1
                com.appsflyer.AFEvent r0 = r11.f453
                java.util.Map r4 = r0.params()
                com.appsflyer.AFEvent r0 = r11.f453
                boolean r0 = r0.mo14502()
                com.appsflyer.AFEvent r1 = r11.f453
                java.lang.String r5 = r1.urlString()
                com.appsflyer.AFEvent r1 = r11.f453
                int r1 = r1.f353
                com.appsflyer.AFEvent r2 = r11.f453
                android.content.Context r6 = r2.context()
                com.appsflyer.AppsFlyerLibCore r2 = com.appsflyer.AppsFlyerLibCore.this
                boolean r2 = r2.isTrackingStopped()
                if (r2 == 0) goto L_0x0029
            L_0x0028:
                return
            L_0x0029:
                byte[] r2 = new byte[r7]
                if (r0 == 0) goto L_0x00ab
                if (r1 > r10) goto L_0x00ab
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                com.appsflyer.AppsFlyerLibCore r7 = com.appsflyer.AppsFlyerLibCore.this
                boolean r7 = r7.m268()
                if (r7 == 0) goto L_0x0065
                java.lang.String r7 = "rfr"
                com.appsflyer.AppsFlyerLibCore r8 = com.appsflyer.AppsFlyerLibCore.this
                com.appsflyer.internal.referrer.GoogleReferrer r8 = r8.f425
                java.util.Map<java.lang.String, java.lang.Object> r8 = r8.oldMap
                r4.put(r7, r8)
                android.content.SharedPreferences r7 = com.appsflyer.AppsFlyerLibCore.getSharedPreferences(r6)
                android.content.SharedPreferences$Editor r7 = r7.edit()
                java.lang.String r8 = "newGPReferrerSent"
                android.content.SharedPreferences$Editor r7 = r7.putBoolean(r8, r9)
                r7.apply()
                com.appsflyer.AppsFlyerLibCore r7 = com.appsflyer.AppsFlyerLibCore.this
                com.appsflyer.internal.referrer.GoogleReferrer r7 = r7.f425
                java.util.Map<java.lang.String, java.lang.Object> r7 = r7.newMap
                r0.add(r7)
            L_0x0065:
                com.appsflyer.AppsFlyerLibCore r7 = com.appsflyer.AppsFlyerLibCore.this
                com.appsflyer.internal.referrer.HuaweiReferrer r7 = r7.f434
                if (r7 == 0) goto L_0x007a
                com.appsflyer.AppsFlyerLibCore r7 = com.appsflyer.AppsFlyerLibCore.this
                com.appsflyer.internal.referrer.HuaweiReferrer r7 = r7.f434
                java.util.Map<java.lang.String, java.lang.Object> r7 = r7.map
                if (r7 == 0) goto L_0x0137
                r0.add(r7)
            L_0x007a:
                boolean r1 = r0.isEmpty()
                if (r1 != 0) goto L_0x0085
                java.lang.String r1 = "referrers"
                r4.put(r1, r0)
            L_0x0085:
                com.appsflyer.AppsFlyerLibCore r0 = com.appsflyer.AppsFlyerLibCore.this
                java.util.Map r0 = r0.f426
                if (r0 == 0) goto L_0x0098
                java.lang.String r0 = "fb_ddl"
                com.appsflyer.AppsFlyerLibCore r1 = com.appsflyer.AppsFlyerLibCore.this
                java.util.Map r1 = r1.f426
                r4.put(r0, r1)
            L_0x0098:
                com.appsflyer.AppsFlyerLibCore r0 = com.appsflyer.AppsFlyerLibCore.this
                boolean r0 = r0.m294()
                if (r0 == 0) goto L_0x0165
                java.lang.String r0 = "lvl"
                com.appsflyer.AppsFlyerLibCore r1 = com.appsflyer.AppsFlyerLibCore.this
                java.util.Map r1 = r1.f429
                r4.put(r0, r1)
            L_0x00ab:
                com.appsflyer.AFEvent r0 = r11.f453
                boolean r0 = r0 instanceof com.appsflyer.internal.model.event.AdRevenue
                if (r0 != 0) goto L_0x00b9
                com.appsflyer.internal.c$a r0 = new com.appsflyer.internal.c$a
                r0.<init>(r4, r6)
                r4.putAll(r0)
            L_0x00b9:
                com.appsflyer.AFEvent r1 = r11.f453     // Catch:{ IOException -> 0x019c, Throwable -> 0x0239 }
                com.appsflyer.AFEvent r0 = r11.f453     // Catch:{ IOException -> 0x019c, Throwable -> 0x0239 }
                boolean r0 = r0 instanceof com.appsflyer.internal.model.event.AdRevenue     // Catch:{ IOException -> 0x019c, Throwable -> 0x0239 }
                if (r0 == 0) goto L_0x0186
                java.lang.String r0 = "af_key"
                java.lang.Object r0 = r4.get(r0)     // Catch:{ IOException -> 0x019c, Throwable -> 0x0239 }
                java.lang.String r0 = (java.lang.String) r0     // Catch:{ IOException -> 0x019c, Throwable -> 0x0239 }
            L_0x00c9:
                r1.key(r0)     // Catch:{ IOException -> 0x019c, Throwable -> 0x0239 }
                monitor-enter(r4)     // Catch:{ IOException -> 0x019c, Throwable -> 0x0239 }
                com.appsflyer.AFEvent r0 = r11.f453     // Catch:{ all -> 0x0198 }
                r1 = 1
                java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0190 }
                r7 = 0
                r1[r7] = r0     // Catch:{ all -> 0x0190 }
                r0 = 48
                r7 = 11758(0x2dee, float:1.6476E-41)
                r8 = 24
                java.lang.Object r0 = com.appsflyer.internal.C0940d.m378(r0, r7, r8)     // Catch:{ all -> 0x0190 }
                java.lang.Class r0 = (java.lang.Class) r0     // Catch:{ all -> 0x0190 }
                java.lang.String r7 = "ɩ"
                r8 = 1
                java.lang.Class[] r8 = new java.lang.Class[r8]     // Catch:{ all -> 0x0190 }
                r9 = 0
                java.lang.Class<com.appsflyer.AFEvent> r10 = com.appsflyer.AFEvent.class
                r8[r9] = r10     // Catch:{ all -> 0x0190 }
                java.lang.reflect.Method r0 = r0.getMethod(r7, r8)     // Catch:{ all -> 0x0190 }
                r7 = 0
                java.lang.Object r0 = r0.invoke(r7, r1)     // Catch:{ all -> 0x0190 }
                byte[] r0 = (byte[]) r0     // Catch:{ all -> 0x0190 }
                monitor-exit(r4)     // Catch:{ all -> 0x024b }
                com.appsflyer.AppsFlyerLibCore r1 = com.appsflyer.AppsFlyerLibCore.this     // Catch:{ IOException -> 0x0104, Throwable -> 0x0239 }
                com.appsflyer.AFEvent r2 = r11.f453     // Catch:{ IOException -> 0x0104, Throwable -> 0x0239 }
                com.appsflyer.AFEvent r2 = r2.post(r0)     // Catch:{ IOException -> 0x0104, Throwable -> 0x0239 }
                com.appsflyer.AppsFlyerLibCore.m243((com.appsflyer.AppsFlyerLibCore) r1, (com.appsflyer.AFEvent) r2)     // Catch:{ IOException -> 0x0104, Throwable -> 0x0239 }
                goto L_0x0028
            L_0x0104:
                r1 = move-exception
                r2 = r0
            L_0x0106:
                java.lang.String r0 = "Exception while sending request to server. "
                com.appsflyer.AFLogger.afErrorLog(r0, r1)
                if (r2 == 0) goto L_0x0028
                if (r6 == 0) goto L_0x0028
                java.lang.String r0 = "&isCachedRequest=true&timeincache="
                boolean r0 = r5.contains(r0)
                if (r0 != 0) goto L_0x0028
                com.appsflyer.internal.C0916aa.m315()
                com.appsflyer.internal.j r4 = new com.appsflyer.internal.j
                java.lang.String r0 = "5.4.0"
                r4.<init>(r5, r2, r0)
                java.io.File r0 = com.appsflyer.internal.C0916aa.m318(r6)     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                boolean r2 = r0.exists()     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                if (r2 != 0) goto L_0x01a1
                r0.mkdir()     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
            L_0x012e:
                java.lang.String r0 = r1.getMessage()
                com.appsflyer.AFLogger.afErrorLog(r0, r1)
                goto L_0x0028
            L_0x0137:
                com.appsflyer.AppsFlyerLibCore r7 = com.appsflyer.AppsFlyerLibCore.this
                com.appsflyer.internal.referrer.HuaweiReferrer r7 = r7.f434
                boolean r7 = r7.valid()
                if (r7 == 0) goto L_0x007a
                if (r1 != r10) goto L_0x007a
                java.util.HashMap r1 = new java.util.HashMap
                r1.<init>()
                java.lang.String r7 = "source"
                java.lang.String r8 = "huawei"
                r1.put(r7, r8)
                java.lang.String r7 = "response"
                java.lang.String r8 = "TIMEOUT"
                r1.put(r7, r8)
                com.appsflyer.internal.referrer.MandatoryFields r7 = new com.appsflyer.internal.referrer.MandatoryFields
                r7.<init>()
                r1.putAll(r7)
                r0.add(r1)
                goto L_0x007a
            L_0x0165:
                com.appsflyer.AppsFlyerLibCore r0 = com.appsflyer.AppsFlyerLibCore.this
                boolean r0 = r0.f433
                if (r0 == 0) goto L_0x00ab
                com.appsflyer.AppsFlyerLibCore r0 = com.appsflyer.AppsFlyerLibCore.this
                java.util.HashMap r1 = new java.util.HashMap
                r1.<init>()
                java.util.Map unused = r0.f429 = r1
                com.appsflyer.AppsFlyerLibCore r0 = com.appsflyer.AppsFlyerLibCore.this
                java.util.Map r0 = r0.f429
                java.lang.String r1 = "error"
                java.lang.String r7 = "operation timed out."
                r0.put(r1, r7)
                goto L_0x00ab
            L_0x0186:
                java.lang.String r0 = "appsflyerKey"
                java.lang.Object r0 = r4.get(r0)     // Catch:{ IOException -> 0x019c, Throwable -> 0x0239 }
                java.lang.String r0 = (java.lang.String) r0     // Catch:{ IOException -> 0x019c, Throwable -> 0x0239 }
                goto L_0x00c9
            L_0x0190:
                r0 = move-exception
                java.lang.Throwable r1 = r0.getCause()     // Catch:{ all -> 0x0198 }
                if (r1 == 0) goto L_0x01a0
                throw r1     // Catch:{ all -> 0x0198 }
            L_0x0198:
                r0 = move-exception
                r1 = r0
            L_0x019a:
                monitor-exit(r4)     // Catch:{ IOException -> 0x019c, Throwable -> 0x0239 }
                throw r1     // Catch:{ IOException -> 0x019c, Throwable -> 0x0239 }
            L_0x019c:
                r0 = move-exception
                r1 = r0
                goto L_0x0106
            L_0x01a0:
                throw r0     // Catch:{ all -> 0x0198 }
            L_0x01a1:
                java.io.File[] r0 = r0.listFiles()     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                if (r0 == 0) goto L_0x01c8
                int r0 = r0.length     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                r2 = 40
                if (r0 <= r2) goto L_0x01c8
                java.lang.String r0 = "AppsFlyer_5.4.0"
                java.lang.String r2 = "reached cache limit, not caching request"
                android.util.Log.i(r0, r2)     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                goto L_0x012e
            L_0x01b5:
                r0 = move-exception
                r0 = r3
            L_0x01b7:
                java.lang.String r2 = "AppsFlyer_5.4.0"
                java.lang.String r3 = "Could not cache request"
                android.util.Log.i(r2, r3)     // Catch:{ all -> 0x0245 }
                if (r0 == 0) goto L_0x012e
                r0.close()     // Catch:{ IOException -> 0x01c5 }
                goto L_0x012e
            L_0x01c5:
                r0 = move-exception
                goto L_0x012e
            L_0x01c8:
                java.lang.String r0 = "AppsFlyer_5.4.0"
                java.lang.String r2 = "caching request..."
                android.util.Log.i(r0, r2)     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                java.io.File r0 = com.appsflyer.internal.C0916aa.m318(r6)     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                java.lang.String r5 = java.lang.Long.toString(r6)     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                r2.<init>(r0, r5)     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                r2.createNewFile()     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                java.lang.String r2 = r2.getPath()     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                r6 = 1
                r5.<init>(r2, r6)     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                r0.<init>(r5)     // Catch:{ Exception -> 0x01b5, all -> 0x0231 }
                java.lang.String r2 = "version="
                r0.write(r2)     // Catch:{ Exception -> 0x0248 }
                java.lang.String r2 = r4.f608     // Catch:{ Exception -> 0x0248 }
                r0.write(r2)     // Catch:{ Exception -> 0x0248 }
                r2 = 10
                r0.write(r2)     // Catch:{ Exception -> 0x0248 }
                java.lang.String r2 = "url="
                r0.write(r2)     // Catch:{ Exception -> 0x0248 }
                java.lang.String r2 = r4.f606     // Catch:{ Exception -> 0x0248 }
                r0.write(r2)     // Catch:{ Exception -> 0x0248 }
                r2 = 10
                r0.write(r2)     // Catch:{ Exception -> 0x0248 }
                java.lang.String r2 = "data="
                r0.write(r2)     // Catch:{ Exception -> 0x0248 }
                byte[] r2 = r4.mo14711()     // Catch:{ Exception -> 0x0248 }
                r3 = 2
                java.lang.String r2 = android.util.Base64.encodeToString(r2, r3)     // Catch:{ Exception -> 0x0248 }
                r0.write(r2)     // Catch:{ Exception -> 0x0248 }
                r2 = 10
                r0.write(r2)     // Catch:{ Exception -> 0x0248 }
                r0.flush()     // Catch:{ Exception -> 0x0248 }
                r0.close()     // Catch:{ IOException -> 0x022e }
                goto L_0x012e
            L_0x022e:
                r0 = move-exception
                goto L_0x012e
            L_0x0231:
                r0 = move-exception
                r1 = r0
            L_0x0233:
                if (r3 == 0) goto L_0x0238
                r3.close()     // Catch:{ IOException -> 0x0243 }
            L_0x0238:
                throw r1
            L_0x0239:
                r0 = move-exception
                java.lang.String r1 = r0.getMessage()
                com.appsflyer.AFLogger.afErrorLog(r1, r0)
                goto L_0x0028
            L_0x0243:
                r0 = move-exception
                goto L_0x0238
            L_0x0245:
                r1 = move-exception
                r3 = r0
                goto L_0x0233
            L_0x0248:
                r2 = move-exception
                goto L_0x01b7
            L_0x024b:
                r1 = move-exception
                r2 = r0
                goto L_0x019a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLibCore.C0906a.run():void");
        }
    }

    @VisibleForTesting
    /* renamed from: com.appsflyer.AppsFlyerLibCore$b */
    static class C0907b implements Runnable {
        @VisibleForTesting

        /* renamed from: ı */
        private static String f455 = "https://%sgcdsdk.%s/install_data/v4.0/";

        /* renamed from: ι */
        private static final List<String> f456 = Arrays.asList(new String[]{"googleplay", "playstore", "googleplaystore"});

        /* renamed from: ǃ */
        private final String f457;

        /* renamed from: ɩ */
        private final Application f458;

        /* renamed from: ɹ */
        private AppsFlyerLibCore f459;

        /* renamed from: Ι */
        final ScheduledExecutorService f460;

        /* renamed from: І */
        private final AtomicInteger f461;

        /* renamed from: і */
        private final int f462;

        /* synthetic */ C0907b(AppsFlyerLibCore appsFlyerLibCore, Application application, String str, byte b) {
            this(appsFlyerLibCore, application, str);
        }

        private C0907b(AppsFlyerLibCore appsFlyerLibCore, Application application, String str) {
            this.f460 = AFExecutor.getInstance().mo14505();
            this.f461 = new AtomicInteger(0);
            this.f459 = appsFlyerLibCore;
            this.f458 = application;
            this.f457 = str;
            this.f462 = 0;
        }

        private C0907b(C0907b bVar) {
            this.f460 = AFExecutor.getInstance().mo14505();
            this.f461 = new AtomicInteger(0);
            this.f459 = bVar.f459;
            this.f458 = bVar.f458;
            this.f457 = bVar.f457;
            this.f462 = bVar.f462 + 1;
        }

        /* renamed from: Ι */
        private void m309(String str, int i) {
            if (500 > i || i >= 600) {
                AFLogger.afDebugLog("Calling onConversionFailure with:\n".concat(String.valueOf(str)));
                AppsFlyerLibCore.f404.onConversionDataFail(str);
            } else if (this.f462 == 2) {
                AFLogger.afDebugLog("Calling onConversionFailure with:\n".concat(String.valueOf(str)));
                AppsFlyerLibCore.f404.onConversionDataFail(str);
            } else {
                C0907b bVar = new C0907b(this);
                AppsFlyerLibCore.m267(bVar.f460, bVar, 10, TimeUnit.MILLISECONDS);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:86:0x0294  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r13 = this;
                r12 = 404(0x194, float:5.66E-43)
                r11 = 1
                java.lang.String r0 = r13.f457
                if (r0 == 0) goto L_0x000f
                java.lang.String r0 = r13.f457
                int r0 = r0.length()
                if (r0 != 0) goto L_0x0010
            L_0x000f:
                return
            L_0x0010:
                com.appsflyer.AppsFlyerLibCore r0 = r13.f459
                boolean r0 = r0.isTrackingStopped()
                if (r0 != 0) goto L_0x000f
                java.util.concurrent.atomic.AtomicInteger r0 = r13.f461
                r0.incrementAndGet()
                r2 = 0
                android.app.Application r0 = r13.f458     // Catch:{ Throwable -> 0x0246 }
                if (r0 != 0) goto L_0x0028
                java.util.concurrent.atomic.AtomicInteger r0 = r13.f461
                r0.decrementAndGet()
                goto L_0x000f
            L_0x0028:
                long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0246 }
                android.app.Application r0 = r13.f458     // Catch:{ Throwable -> 0x0246 }
                com.appsflyer.AppsFlyerLibCore r1 = r13.f459     // Catch:{ Throwable -> 0x0246 }
                android.app.Application r3 = r13.f458     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r1 = r1.getConfiguredChannel(r3)     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r1 = com.appsflyer.AppsFlyerLibCore.m252((android.content.Context) r0, (java.lang.String) r1)     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r0 = ""
                if (r1 == 0) goto L_0x0054
                java.util.List<java.lang.String> r3 = f456     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r6 = r1.toLowerCase()     // Catch:{ Throwable -> 0x0246 }
                boolean r3 = r3.contains(r6)     // Catch:{ Throwable -> 0x0246 }
                if (r3 != 0) goto L_0x0235
                java.lang.String r0 = "-"
                java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r0 = r0.concat(r1)     // Catch:{ Throwable -> 0x0246 }
            L_0x0054:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0246 }
                r1.<init>()     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r3 = f455     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r3 = com.appsflyer.ServerConfigHandler.getUrl(r3)     // Catch:{ Throwable -> 0x0246 }
                java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ Throwable -> 0x0246 }
                android.app.Application r3 = r13.f458     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r3 = r3.getPackageName()     // Catch:{ Throwable -> 0x0246 }
                java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ Throwable -> 0x0246 }
                java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r1 = "?devkey="
                java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r1 = r13.f457     // Catch:{ Throwable -> 0x0246 }
                java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r1 = "&device_id="
                java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Throwable -> 0x0246 }
                java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference     // Catch:{ Throwable -> 0x0246 }
                android.app.Application r3 = r13.f458     // Catch:{ Throwable -> 0x0246 }
                r1.<init>(r3)     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r1 = com.appsflyer.internal.C0921ae.m326(r1)     // Catch:{ Throwable -> 0x0246 }
                java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r1 = r0.toString()     // Catch:{ Throwable -> 0x0246 }
                com.appsflyer.internal.ai r0 = com.appsflyer.internal.C0928ai.f525     // Catch:{ Throwable -> 0x0246 }
                if (r0 != 0) goto L_0x00a1
                com.appsflyer.internal.ai r0 = new com.appsflyer.internal.ai     // Catch:{ Throwable -> 0x0246 }
                r0.<init>()     // Catch:{ Throwable -> 0x0246 }
                com.appsflyer.internal.C0928ai.f525 = r0     // Catch:{ Throwable -> 0x0246 }
            L_0x00a1:
                com.appsflyer.internal.ai r0 = com.appsflyer.internal.C0928ai.f525     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r3 = ""
                java.lang.String r6 = "server_request"
                r7 = 1
                java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ Throwable -> 0x0246 }
                r8 = 0
                r7[r8] = r3     // Catch:{ Throwable -> 0x0246 }
                r0.mo14690(r6, r1, r7)     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r0 = "Calling server for attribution url: "
                java.lang.String r3 = java.lang.String.valueOf(r1)     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r0 = r0.concat(r3)     // Catch:{ Throwable -> 0x0246 }
                com.appsflyer.internal.C0927ah.m336(r0)     // Catch:{ Throwable -> 0x0246 }
                java.net.URL r0 = new java.net.URL     // Catch:{ Throwable -> 0x0246 }
                r0.<init>(r1)     // Catch:{ Throwable -> 0x0246 }
                java.net.URLConnection r0 = r0.openConnection()     // Catch:{ Throwable -> 0x0246 }
                java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r2 = "GET"
                r0.setRequestMethod(r2)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r2 = 10000(0x2710, float:1.4013E-41)
                r0.setConnectTimeout(r2)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r2 = "Connection"
                java.lang.String r3 = "close"
                r0.setRequestProperty(r2, r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r0.connect()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                int r3 = r0.getResponseCode()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                com.appsflyer.AppsFlyerLibCore r2 = r13.f459     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r6 = r2.mo14600((java.net.HttpURLConnection) r0)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                com.appsflyer.internal.ai r2 = com.appsflyer.internal.C0928ai.f525     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                if (r2 != 0) goto L_0x00f1
                com.appsflyer.internal.ai r2 = new com.appsflyer.internal.ai     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r2.<init>()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                com.appsflyer.internal.C0928ai.f525 = r2     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
            L_0x00f1:
                com.appsflyer.internal.ai r2 = com.appsflyer.internal.C0928ai.f525     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r7 = "server_response"
                r8 = 2
                java.lang.String[] r8 = new java.lang.String[r8]     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r9 = 0
                java.lang.String r10 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r8[r9] = r10     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r9 = 1
                r8[r9] = r6     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r2.mo14690(r7, r1, r8)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r2 = 200(0xc8, float:2.8E-43)
                if (r3 == r2) goto L_0x010b
                if (r3 != r12) goto L_0x02a1
            L_0x010b:
                long r8 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                android.app.Application r1 = r13.f458     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r2 = "appsflyerGetConversionDataTiming"
                long r4 = r8 - r4
                com.appsflyer.AppsFlyerLibCore.m284(r1, r2, r4)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r1 = "Attribution data: "
                java.lang.String r2 = java.lang.String.valueOf(r6)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r1 = r1.concat(r2)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                com.appsflyer.internal.C0927ah.m336(r1)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                int r1 = r6.length()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                if (r1 <= 0) goto L_0x0224
                java.util.Map r2 = com.appsflyer.AppsFlyerLibCore.m265((java.lang.String) r6)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r1 = "iscache"
                java.lang.Object r1 = r2.get(r1)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                if (r3 != r12) goto L_0x0151
                java.lang.String r3 = "error_reason"
                r2.remove(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r3 = "status_code"
                r2.remove(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r3 = "af_status"
                java.lang.String r4 = "Organic"
                r2.put(r3, r4)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r3 = "af_message"
                java.lang.String r4 = "organic install"
                r2.put(r3, r4)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
            L_0x0151:
                if (r1 == 0) goto L_0x0164
                boolean r3 = r1.booleanValue()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                if (r3 != 0) goto L_0x0164
                android.app.Application r3 = r13.f458     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r4 = "appsflyerConversionDataCacheExpiration"
                long r8 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                com.appsflyer.AppsFlyerLibCore.m284(r3, r4, r8)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
            L_0x0164:
                java.lang.String r3 = "af_siteid"
                boolean r3 = r2.containsKey(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                if (r3 == 0) goto L_0x018c
                java.lang.String r3 = "af_channel"
                boolean r3 = r2.containsKey(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                if (r3 == 0) goto L_0x0268
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r4 = "[Invite] Detected App-Invite via channel: "
                r3.<init>(r4)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r4 = "af_channel"
                java.lang.Object r4 = r2.get(r4)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                com.appsflyer.AFLogger.afDebugLog(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
            L_0x018c:
                java.lang.String r3 = "af_siteid"
                boolean r3 = r2.containsKey(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                if (r3 == 0) goto L_0x01ac
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r4 = "[Invite] Detected App-Invite via channel: "
                r3.<init>(r4)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r4 = "af_channel"
                java.lang.Object r4 = r2.get(r4)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                com.appsflyer.AFLogger.afDebugLog(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
            L_0x01ac:
                java.lang.String r3 = "is_first_launch"
                java.lang.Boolean r4 = java.lang.Boolean.FALSE     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r2.put(r3, r4)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r3.<init>(r2)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                if (r3 == 0) goto L_0x0282
                android.app.Application r4 = r13.f458     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r5 = "attributionId"
                com.appsflyer.AppsFlyerLibCore.m258((android.content.Context) r4, (java.lang.String) r5, (java.lang.String) r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
            L_0x01c5:
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r4 = "iscache="
                r3.<init>(r4)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.StringBuilder r1 = r3.append(r1)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r3 = " caching conversion data"
                java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                com.appsflyer.AFLogger.afDebugLog(r1)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                com.appsflyer.AppsFlyerConversionListener r1 = com.appsflyer.AppsFlyerLibCore.f404     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                if (r1 == 0) goto L_0x0224
                java.util.concurrent.atomic.AtomicInteger r1 = r13.f461     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                int r1 = r1.intValue()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                if (r1 > r11) goto L_0x0224
                android.app.Application r1 = r13.f458     // Catch:{ y -> 0x0298 }
                java.util.Map r1 = com.appsflyer.AppsFlyerLibCore.m238((android.content.Context) r1)     // Catch:{ y -> 0x0298 }
                android.app.Application r3 = r13.f458     // Catch:{ y -> 0x0298 }
                android.content.SharedPreferences r3 = com.appsflyer.AppsFlyerLibCore.getSharedPreferences(r3)     // Catch:{ y -> 0x0298 }
                java.lang.String r4 = "sixtyDayConversionData"
                r5 = 0
                boolean r3 = r3.getBoolean(r4, r5)     // Catch:{ y -> 0x0298 }
                if (r3 != 0) goto L_0x0207
                java.lang.String r3 = "is_first_launch"
                java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch:{ y -> 0x0298 }
                r1.put(r3, r4)     // Catch:{ y -> 0x0298 }
            L_0x0207:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r3 = "Calling onConversionDataSuccess with:\n"
                r2.<init>(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r3 = r1.toString()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                com.appsflyer.AFLogger.afDebugLog(r2)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                com.appsflyer.AppsFlyerConversionListener r2 = com.appsflyer.AppsFlyerLibCore.f404     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r2.onConversionDataSuccess(r1)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
            L_0x0224:
                java.util.concurrent.atomic.AtomicInteger r1 = r13.f461
                r1.decrementAndGet()
                if (r0 == 0) goto L_0x022e
                r0.disconnect()
            L_0x022e:
                java.util.concurrent.ScheduledExecutorService r0 = r13.f460
                r0.shutdown()
                goto L_0x000f
            L_0x0235:
                java.lang.String r3 = "AF detected using redundant Google-Play channel for attribution - %s. Using without channel postfix."
                r6 = 1
                java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Throwable -> 0x0246 }
                r7 = 0
                r6[r7] = r1     // Catch:{ Throwable -> 0x0246 }
                java.lang.String r1 = java.lang.String.format(r3, r6)     // Catch:{ Throwable -> 0x0246 }
                com.appsflyer.AFLogger.afWarnLog(r1)     // Catch:{ Throwable -> 0x0246 }
                goto L_0x0054
            L_0x0246:
                r0 = move-exception
                r1 = r0
            L_0x0248:
                com.appsflyer.AppsFlyerConversionListener r0 = com.appsflyer.AppsFlyerLibCore.f404     // Catch:{ all -> 0x02d2 }
                if (r0 == 0) goto L_0x0256
                java.lang.String r0 = r1.getMessage()     // Catch:{ all -> 0x02d2 }
                r3 = 0
                r13.m309(r0, r3)     // Catch:{ all -> 0x02d2 }
            L_0x0256:
                java.lang.String r0 = r1.getMessage()     // Catch:{ all -> 0x02d2 }
                com.appsflyer.AFLogger.afErrorLog(r0, r1)     // Catch:{ all -> 0x02d2 }
                java.util.concurrent.atomic.AtomicInteger r0 = r13.f461
                r0.decrementAndGet()
                if (r2 == 0) goto L_0x022e
                r2.disconnect()
                goto L_0x022e
            L_0x0268:
                java.lang.String r3 = "[CrossPromotion] App was installed via %s's Cross Promotion"
                r4 = 1
                java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r5 = 0
                java.lang.String r7 = "af_siteid"
                java.lang.Object r7 = r2.get(r7)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r4[r5] = r7     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                com.appsflyer.AFLogger.afDebugLog(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                goto L_0x018c
            L_0x027f:
                r1 = move-exception
                r2 = r0
                goto L_0x0248
            L_0x0282:
                android.app.Application r3 = r13.f458     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r4 = "attributionId"
                com.appsflyer.AppsFlyerLibCore.m258((android.content.Context) r3, (java.lang.String) r4, (java.lang.String) r6)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                goto L_0x01c5
            L_0x028b:
                r1 = move-exception
                r2 = r0
            L_0x028d:
                java.util.concurrent.atomic.AtomicInteger r0 = r13.f461
                r0.decrementAndGet()
                if (r2 == 0) goto L_0x0297
                r2.disconnect()
            L_0x0297:
                throw r1
            L_0x0298:
                r1 = move-exception
                java.lang.String r3 = "Exception while trying to fetch attribution data. "
                com.appsflyer.AFLogger.afErrorLog(r3, r1)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r1 = r2
                goto L_0x0207
            L_0x02a1:
                com.appsflyer.AppsFlyerConversionListener r2 = com.appsflyer.AppsFlyerLibCore.f404     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                if (r2 == 0) goto L_0x02b4
                java.lang.String r2 = "Error connection to server: "
                java.lang.String r4 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r2 = r2.concat(r4)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                r13.m309(r2, r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
            L_0x02b4:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r4 = "AttributionIdFetcher response code: "
                r2.<init>(r4)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r3 = "  url: "
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                com.appsflyer.internal.C0927ah.m336(r1)     // Catch:{ Throwable -> 0x027f, all -> 0x028b }
                goto L_0x0224
            L_0x02d2:
                r0 = move-exception
                r1 = r0
                goto L_0x028d
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLibCore.C0907b.run():void");
        }
    }

    /* renamed from: com.appsflyer.AppsFlyerLibCore$c */
    class C0908c implements Runnable {

        /* renamed from: ι */
        private WeakReference<Context> f464 = null;

        public C0908c(Context context) {
            this.f464 = new WeakReference<>(context);
        }

        public final void run() {
            if (!AppsFlyerLibCore.this.f432) {
                AppsFlyerLibCore.this.f436 = System.currentTimeMillis();
                if (this.f464 != null) {
                    boolean unused = AppsFlyerLibCore.this.f432 = true;
                    try {
                        String r1 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
                        synchronized (this.f464) {
                            C0916aa.m315();
                            for (C0945j next : C0916aa.m316(this.f464.get())) {
                                AFLogger.afInfoLog(new StringBuilder("resending request: ").append(next.f606).toString());
                                try {
                                    long currentTimeMillis = System.currentTimeMillis();
                                    long parseLong = Long.parseLong(next.f607, 10);
                                    AppsFlyerLibCore appsFlyerLibCore = AppsFlyerLibCore.this;
                                    AFEvent key = new CachedEvent().urlString(new StringBuilder().append(next.f606).append("&isCachedRequest=true&timeincache=").append((currentTimeMillis - parseLong) / 1000).toString()).post(next.mo14711()).key(r1);
                                    key.f349 = this.f464;
                                    key.f356 = next.f607;
                                    key.f352 = false;
                                    AppsFlyerLibCore.m243(appsFlyerLibCore, key);
                                } catch (Exception e) {
                                    AFLogger.afErrorLog("Failed to resend cached request", e);
                                }
                            }
                        }
                        boolean unused2 = AppsFlyerLibCore.this.f432 = false;
                    } catch (Exception e2) {
                        try {
                            AFLogger.afErrorLog("failed to check cache. ", e2);
                        } finally {
                            boolean unused3 = AppsFlyerLibCore.this.f432 = false;
                        }
                    }
                    AppsFlyerLibCore.this.f411.shutdown();
                    ScheduledExecutorService unused4 = AppsFlyerLibCore.this.f411 = null;
                }
            }
        }
    }

    public String getSdkVersion() {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "getSdkVersion", new String[0]);
        return new StringBuilder("version: 5.4.0 (build ").append(f403).append(")").toString();
    }

    public void setImeiData(String str) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setImeiData", str);
        this.f427 = str;
    }

    public void setOaidData(String str) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setOaidData", str);
        this.f420 = str;
    }

    public void setAndroidIdData(String str) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setAndroidIdData", str);
        this.f428 = str;
    }

    public void setAppInviteOneLink(String str) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setAppInviteOneLink", str);
        AFLogger.afInfoLog("setAppInviteOneLink = ".concat(String.valueOf(str)));
        if (str == null || !str.equals(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_ID))) {
            AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_DOMAIN);
            AppsFlyerProperties.getInstance().remove("onelinkVersion");
            AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_SCHEME);
        }
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.ONELINK_ID, str);
    }

    public void setUserEmails(String... strArr) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setUserEmails", strArr);
        setUserEmails(AppsFlyerProperties.EmailsCryptType.NONE, strArr);
    }

    public void setCollectAndroidID(boolean z) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setCollectAndroidID", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_ANDROID_ID, Boolean.toString(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, Boolean.toString(z));
    }

    public void setCollectIMEI(boolean z) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setCollectIMEI", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_IMEI, Boolean.toString(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, Boolean.toString(z));
    }

    public void setCollectOaid(boolean z) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setCollectOaid", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_OAID, Boolean.toString(z));
    }

    @Deprecated
    public AppsFlyerLib init(String str, AppsFlyerConversionListener appsFlyerConversionListener) {
        String str2;
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai aiVar = C0928ai.f525;
        String[] strArr = new String[2];
        strArr[0] = str;
        if (appsFlyerConversionListener == null) {
            str2 = "null";
        } else {
            str2 = "conversionDataListener";
        }
        strArr[1] = str2;
        aiVar.mo14690("public_api_call", "init", strArr);
        AFLogger.m218(String.format("Initializing AppsFlyer SDK: (v%s.%s)", new Object[]{BuildConfig.VERSION_NAME, f403}));
        this.f421 = true;
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_KEY, str);
        C0927ah.m335(str);
        f404 = appsFlyerConversionListener;
        return this;
    }

    public void setCustomerUserId(String str) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setCustomerUserId", str);
        AFLogger.afInfoLog("setCustomerUserId = ".concat(String.valueOf(str)));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.APP_USER_ID, str);
    }

    public void setAppId(String str) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setAppId", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.APP_ID, str);
    }

    public void setExtension(String str) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setExtension", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.EXTENSION, str);
    }

    public void setIsUpdate(boolean z) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setIsUpdate", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.IS_UPDATE, z);
    }

    public void setCurrencyCode(String str) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setCurrencyCode", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.CURRENCY_CODE, str);
    }

    public void trackLocation(Context context, double d, double d2) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "trackLocation", String.valueOf(d), String.valueOf(d2));
        HashMap hashMap = new HashMap();
        hashMap.put(AFInAppEventParameterName.LONGTITUDE, Double.toString(d2));
        hashMap.put(AFInAppEventParameterName.LATITUDE, Double.toString(d));
        AFEvent context2 = new InAppEvent().context(context);
        context2.f363 = AFInAppEventType.LOCATION_COORDINATES;
        context2.f359 = hashMap;
        mo14602(context2);
    }

    public void reportTrackSession(Context context) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "reportTrackSession", new String[0]);
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.f530 = false;
        AFEvent context2 = new InAppEvent().context(context);
        context2.f363 = null;
        context2.f359 = null;
        mo14602(context2);
    }

    public void setDeviceTrackingDisabled(boolean z) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "setDeviceTrackingDisabled", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, z);
    }

    public void registerConversionListener(Context context, AppsFlyerConversionListener appsFlyerConversionListener) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "registerConversionListener", new String[0]);
        if (appsFlyerConversionListener != null) {
            f404 = appsFlyerConversionListener;
        }
    }

    public void unregisterConversionListener() {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "unregisterConversionListener", new String[0]);
        f404 = null;
    }

    public void registerValidatorListener(Context context, AppsFlyerInAppPurchaseValidatorListener appsFlyerInAppPurchaseValidatorListener) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "registerValidatorListener", new String[0]);
        AFLogger.afDebugLog("registerValidatorListener called");
        if (appsFlyerInAppPurchaseValidatorListener == null) {
            AFLogger.afDebugLog("registerValidatorListener null listener");
        } else {
            f402 = appsFlyerInAppPurchaseValidatorListener;
        }
    }

    /* renamed from: ι */
    private static void m285(Context context, Map<String, ? super String> map) {
        C0951s sVar = C0951s.C0953d.f637;
        C0951s.C0952a r0 = C0951s.m394(context);
        map.put("network", r0.f634);
        if (r0.f636 != null) {
            map.put("operator", r0.f636);
        }
        if (r0.f635 != null) {
            map.put("carrier", r0.f635);
        }
    }

    public String getAppsFlyerUID(Context context) {
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai.f525.mo14690("public_api_call", "getAppsFlyerUID", new String[0]);
        return C0921ae.m326(new WeakReference(context));
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x0188 A[SYNTHETIC, Splitter:B:58:0x0188] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0194  */
    /* renamed from: ι */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m286(com.appsflyer.AFEvent r18) throws java.io.IOException {
        /*
            r17 = this;
            java.net.URL r6 = new java.net.URL
            r0 = r18
            java.lang.String r2 = r0.f362
            r6.<init>(r2)
            byte[] r7 = r18.mo14503()
            java.lang.String r8 = r18.key()
            r0 = r18
            java.lang.String r9 = r0.f356
            boolean r10 = r18.mo14502()
            android.content.Context r11 = r18.context()
            r0 = r18
            com.appsflyer.AppsFlyerTrackingRequestListener r12 = r0.f354
            if (r10 == 0) goto L_0x017c
            com.appsflyer.AppsFlyerConversionListener r2 = f404
            if (r2 == 0) goto L_0x017c
            r2 = 1
            r3 = r2
        L_0x0029:
            r4 = 0
            java.net.URLConnection r2 = r6.openConnection()     // Catch:{ all -> 0x025e }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ all -> 0x025e }
            java.lang.String r4 = "POST"
            r2.setRequestMethod(r4)     // Catch:{ all -> 0x018c }
            int r4 = r7.length     // Catch:{ all -> 0x018c }
            java.lang.String r5 = "Content-Length"
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x018c }
            r2.setRequestProperty(r5, r4)     // Catch:{ all -> 0x018c }
            java.lang.String r5 = "Content-Type"
            boolean r4 = r18.isEncrypt()     // Catch:{ all -> 0x018c }
            if (r4 == 0) goto L_0x0180
            java.lang.String r4 = "application/octet-stream"
        L_0x0049:
            r2.setRequestProperty(r5, r4)     // Catch:{ all -> 0x018c }
            r4 = 10000(0x2710, float:1.4013E-41)
            r2.setConnectTimeout(r4)     // Catch:{ all -> 0x018c }
            r4 = 1
            r2.setDoOutput(r4)     // Catch:{ all -> 0x018c }
            r5 = 0
            java.io.DataOutputStream r4 = new java.io.DataOutputStream     // Catch:{ all -> 0x0184 }
            java.io.OutputStream r13 = r2.getOutputStream()     // Catch:{ all -> 0x0184 }
            r4.<init>(r13)     // Catch:{ all -> 0x0184 }
            r4.write(r7)     // Catch:{ all -> 0x0262 }
            r4.close()     // Catch:{ all -> 0x018c }
            int r4 = r2.getResponseCode()     // Catch:{ all -> 0x018c }
            r0 = r17
            java.lang.String r5 = r0.mo14600((java.net.HttpURLConnection) r2)     // Catch:{ all -> 0x018c }
            com.appsflyer.internal.ai r7 = com.appsflyer.internal.C0928ai.f525     // Catch:{ all -> 0x018c }
            if (r7 != 0) goto L_0x007a
            com.appsflyer.internal.ai r7 = new com.appsflyer.internal.ai     // Catch:{ all -> 0x018c }
            r7.<init>()     // Catch:{ all -> 0x018c }
            com.appsflyer.internal.C0928ai.f525 = r7     // Catch:{ all -> 0x018c }
        L_0x007a:
            com.appsflyer.internal.ai r7 = com.appsflyer.internal.C0928ai.f525     // Catch:{ all -> 0x018c }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x018c }
            java.lang.String r13 = "server_response"
            r14 = 2
            java.lang.String[] r14 = new java.lang.String[r14]     // Catch:{ all -> 0x018c }
            r15 = 0
            java.lang.String r16 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x018c }
            r14[r15] = r16     // Catch:{ all -> 0x018c }
            r15 = 1
            r14[r15] = r5     // Catch:{ all -> 0x018c }
            r7.mo14690(r13, r6, r14)     // Catch:{ all -> 0x018c }
            java.lang.String r6 = "response code: "
            java.lang.String r7 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x018c }
            java.lang.String r6 = r6.concat(r7)     // Catch:{ all -> 0x018c }
            com.appsflyer.AFLogger.afInfoLog(r6)     // Catch:{ all -> 0x018c }
            android.content.SharedPreferences r6 = getSharedPreferences(r11)     // Catch:{ all -> 0x018c }
            r7 = 200(0xc8, float:2.8E-43)
            if (r4 != r7) goto L_0x01e9
            if (r11 == 0) goto L_0x00b3
            if (r10 == 0) goto L_0x00b3
            long r14 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x018c }
            r0 = r17
            r0.f410 = r14     // Catch:{ all -> 0x018c }
        L_0x00b3:
            if (r12 == 0) goto L_0x00b8
            r12.onTrackingRequestSuccess()     // Catch:{ all -> 0x018c }
        L_0x00b8:
            if (r9 == 0) goto L_0x019c
            com.appsflyer.internal.C0916aa.m315()     // Catch:{ all -> 0x018c }
            com.appsflyer.internal.C0916aa.m317(r9, r11)     // Catch:{ all -> 0x018c }
        L_0x00c0:
            java.lang.String r4 = "afUninstallToken"
            com.appsflyer.AppsFlyerProperties r7 = com.appsflyer.AppsFlyerProperties.getInstance()     // Catch:{ all -> 0x018c }
            java.lang.String r4 = r7.getString(r4)     // Catch:{ all -> 0x018c }
            if (r4 == 0) goto L_0x00f2
            java.lang.String r7 = "Uninstall Token exists: "
            java.lang.String r9 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x018c }
            java.lang.String r7 = r7.concat(r9)     // Catch:{ all -> 0x018c }
            com.appsflyer.AFLogger.afDebugLog(r7)     // Catch:{ all -> 0x018c }
            java.lang.String r7 = "sentRegisterRequestToAF"
            r9 = 0
            boolean r7 = r6.getBoolean(r7, r9)     // Catch:{ all -> 0x018c }
            if (r7 != 0) goto L_0x00f2
            java.lang.String r7 = "Resending Uninstall token to AF servers: "
            java.lang.String r9 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x018c }
            java.lang.String r7 = r7.concat(r9)     // Catch:{ all -> 0x018c }
            com.appsflyer.AFLogger.afDebugLog(r7)     // Catch:{ all -> 0x018c }
            com.appsflyer.internal.C0922af.m327(r11, r4)     // Catch:{ all -> 0x018c }
        L_0x00f2:
            r0 = r17
            android.net.Uri r4 = r0.latestDeepLink     // Catch:{ all -> 0x018c }
            if (r4 == 0) goto L_0x00fd
            r4 = 0
            r0 = r17
            r0.latestDeepLink = r4     // Catch:{ all -> 0x018c }
        L_0x00fd:
            org.json.JSONObject r4 = com.appsflyer.ServerConfigHandler.m313(r5)     // Catch:{ all -> 0x018c }
            java.lang.String r5 = "send_background"
            r7 = 0
            boolean r4 = r4.optBoolean(r5, r7)     // Catch:{ all -> 0x018c }
            r0 = r17
            r0.f418 = r4     // Catch:{ all -> 0x018c }
        L_0x010c:
            java.lang.String r4 = "appsflyerConversionDataCacheExpiration"
            r12 = 0
            long r4 = r6.getLong(r4, r12)     // Catch:{ all -> 0x018c }
            r12 = 0
            int r7 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r7 == 0) goto L_0x0152
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x018c }
            long r4 = r12 - r4
            r12 = 5184000000(0x134fd9000, double:2.561236308E-314)
            int r4 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r4 <= 0) goto L_0x0152
            java.lang.String r4 = "sixtyDayConversionData"
            android.content.SharedPreferences r5 = getSharedPreferences(r11)     // Catch:{ all -> 0x018c }
            android.content.SharedPreferences$Editor r5 = r5.edit()     // Catch:{ all -> 0x018c }
            r7 = 1
            r5.putBoolean(r4, r7)     // Catch:{ all -> 0x018c }
            r5.apply()     // Catch:{ all -> 0x018c }
            java.lang.String r4 = "attributionId"
            android.content.SharedPreferences r5 = getSharedPreferences(r11)     // Catch:{ all -> 0x018c }
            android.content.SharedPreferences$Editor r5 = r5.edit()     // Catch:{ all -> 0x018c }
            r7 = 0
            r5.putString(r4, r7)     // Catch:{ all -> 0x018c }
            r5.apply()     // Catch:{ all -> 0x018c }
            java.lang.String r4 = "appsflyerConversionDataCacheExpiration"
            r12 = 0
            m284(r11, r4, r12)     // Catch:{ all -> 0x018c }
        L_0x0152:
            java.lang.String r4 = "attributionId"
            r5 = 0
            java.lang.String r4 = r6.getString(r4, r5)     // Catch:{ all -> 0x018c }
            if (r4 != 0) goto L_0x01fa
            if (r8 == 0) goto L_0x01fa
            if (r3 == 0) goto L_0x01fa
            com.appsflyer.AppsFlyerLibCore$b r4 = new com.appsflyer.AppsFlyerLibCore$b     // Catch:{ all -> 0x018c }
            android.content.Context r3 = r11.getApplicationContext()     // Catch:{ all -> 0x018c }
            android.app.Application r3 = (android.app.Application) r3     // Catch:{ all -> 0x018c }
            r5 = 0
            r0 = r17
            r4.<init>(r0, r3, r8, r5)     // Catch:{ all -> 0x018c }
            java.util.concurrent.ScheduledExecutorService r3 = r4.f460     // Catch:{ all -> 0x018c }
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x018c }
            r6 = 10
            m267(r3, r4, r6, r5)     // Catch:{ all -> 0x018c }
        L_0x0176:
            if (r2 == 0) goto L_0x017b
            r2.disconnect()
        L_0x017b:
            return
        L_0x017c:
            r2 = 0
            r3 = r2
            goto L_0x0029
        L_0x0180:
            java.lang.String r4 = "application/json"
            goto L_0x0049
        L_0x0184:
            r3 = move-exception
            r4 = r5
        L_0x0186:
            if (r4 == 0) goto L_0x0194
            r4.close()     // Catch:{ all -> 0x018c }
        L_0x018b:
            throw r3     // Catch:{ all -> 0x018c }
        L_0x018c:
            r3 = move-exception
            r4 = r2
        L_0x018e:
            if (r4 == 0) goto L_0x0193
            r4.disconnect()
        L_0x0193:
            throw r3
        L_0x0194:
            if (r12 == 0) goto L_0x018b
            java.lang.String r4 = "No Connectivity"
            r12.onTrackingRequestFailure(r4)     // Catch:{ all -> 0x018c }
            goto L_0x018b
        L_0x019c:
            java.lang.String r4 = "sentSuccessfully"
            java.lang.String r7 = "true"
            android.content.SharedPreferences r9 = getSharedPreferences(r11)     // Catch:{ all -> 0x018c }
            android.content.SharedPreferences$Editor r9 = r9.edit()     // Catch:{ all -> 0x018c }
            r9.putString(r4, r7)     // Catch:{ all -> 0x018c }
            r9.apply()     // Catch:{ all -> 0x018c }
            r0 = r17
            boolean r4 = r0.f432     // Catch:{ all -> 0x018c }
            if (r4 != 0) goto L_0x00c0
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x018c }
            r0 = r17
            long r14 = r0.f436     // Catch:{ all -> 0x018c }
            long r12 = r12 - r14
            r14 = 15000(0x3a98, double:7.411E-320)
            int r4 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r4 < 0) goto L_0x00c0
            r0 = r17
            java.util.concurrent.ScheduledExecutorService r4 = r0.f411     // Catch:{ all -> 0x018c }
            if (r4 != 0) goto L_0x00c0
            com.appsflyer.AFExecutor r4 = com.appsflyer.AFExecutor.getInstance()     // Catch:{ all -> 0x018c }
            java.util.concurrent.ScheduledThreadPoolExecutor r4 = r4.mo14505()     // Catch:{ all -> 0x018c }
            r0 = r17
            r0.f411 = r4     // Catch:{ all -> 0x018c }
            com.appsflyer.AppsFlyerLibCore$c r4 = new com.appsflyer.AppsFlyerLibCore$c     // Catch:{ all -> 0x018c }
            r0 = r17
            r4.<init>(r11)     // Catch:{ all -> 0x018c }
            r0 = r17
            java.util.concurrent.ScheduledExecutorService r7 = r0.f411     // Catch:{ all -> 0x018c }
            r12 = 1
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ all -> 0x018c }
            m267(r7, r4, r12, r9)     // Catch:{ all -> 0x018c }
            goto L_0x00c0
        L_0x01e9:
            if (r12 == 0) goto L_0x010c
            java.lang.String r5 = "Failure: "
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x018c }
            java.lang.String r4 = r5.concat(r4)     // Catch:{ all -> 0x018c }
            r12.onTrackingRequestFailure(r4)     // Catch:{ all -> 0x018c }
            goto L_0x010c
        L_0x01fa:
            if (r8 != 0) goto L_0x0203
            java.lang.String r3 = "AppsFlyer dev key is missing."
            com.appsflyer.AFLogger.afWarnLog(r3)     // Catch:{ all -> 0x018c }
            goto L_0x0176
        L_0x0203:
            if (r3 == 0) goto L_0x0176
            java.lang.String r3 = "attributionId"
            r4 = 0
            java.lang.String r3 = r6.getString(r3, r4)     // Catch:{ all -> 0x018c }
            if (r3 == 0) goto L_0x0176
            r3 = 0
            r0 = r17
            int r3 = r0.getLaunchCounter(r6, r3)     // Catch:{ all -> 0x018c }
            r4 = 1
            if (r3 <= r4) goto L_0x0176
            java.util.Map r3 = m238((android.content.Context) r11)     // Catch:{ y -> 0x0254 }
            if (r3 == 0) goto L_0x0176
            java.lang.String r4 = "is_first_launch"
            boolean r4 = r3.containsKey(r4)     // Catch:{ Throwable -> 0x024a }
            if (r4 != 0) goto L_0x022d
            java.lang.String r4 = "is_first_launch"
            java.lang.Boolean r5 = java.lang.Boolean.FALSE     // Catch:{ Throwable -> 0x024a }
            r3.put(r4, r5)     // Catch:{ Throwable -> 0x024a }
        L_0x022d:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x024a }
            java.lang.String r5 = "Calling onConversionDataSuccess with:\n"
            r4.<init>(r5)     // Catch:{ Throwable -> 0x024a }
            java.lang.String r5 = r3.toString()     // Catch:{ Throwable -> 0x024a }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x024a }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x024a }
            com.appsflyer.AFLogger.afDebugLog(r4)     // Catch:{ Throwable -> 0x024a }
            com.appsflyer.AppsFlyerConversionListener r4 = f404     // Catch:{ Throwable -> 0x024a }
            r4.onConversionDataSuccess(r3)     // Catch:{ Throwable -> 0x024a }
            goto L_0x0176
        L_0x024a:
            r3 = move-exception
            java.lang.String r4 = r3.getLocalizedMessage()     // Catch:{ y -> 0x0254 }
            com.appsflyer.AFLogger.afErrorLog(r4, r3)     // Catch:{ y -> 0x0254 }
            goto L_0x0176
        L_0x0254:
            r3 = move-exception
            java.lang.String r4 = r3.getMessage()     // Catch:{ all -> 0x018c }
            com.appsflyer.AFLogger.afErrorLog(r4, r3)     // Catch:{ all -> 0x018c }
            goto L_0x0176
        L_0x025e:
            r2 = move-exception
            r3 = r2
            goto L_0x018e
        L_0x0262:
            r3 = move-exception
            goto L_0x0186
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLibCore.m286(com.appsflyer.AFEvent):void");
    }

    public void validateAndTrackInAppPurchase(Context context, String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        String obj;
        if (C0928ai.f525 == null) {
            C0928ai.f525 = new C0928ai();
        }
        C0928ai aiVar = C0928ai.f525;
        String[] strArr = new String[6];
        strArr[0] = str;
        strArr[1] = str2;
        strArr[2] = str3;
        strArr[3] = str4;
        strArr[4] = str5;
        if (map == null) {
            obj = "";
        } else {
            obj = map.toString();
        }
        strArr[5] = obj;
        aiVar.mo14690("public_api_call", "validateAndTrackInAppPurchase", strArr);
        if (!isTrackingStopped()) {
            AFLogger.afInfoLog(new StringBuilder("Validate in app called with parameters: ").append(str3).append(" ").append(str4).append(" ").append(str5).toString());
        }
        if (str != null && str4 != null && str2 != null && str5 != null && str3 != null) {
            new Thread(new C0965x(context.getApplicationContext(), AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY), str, str2, str3, str4, str5, map, context instanceof Activity ? ((Activity) context).getIntent() : null)).start();
        } else if (f402 != null) {
            f402.onValidateInAppFailure("Please provide purchase parameters");
        }
    }

    /* renamed from: Ι */
    static /* synthetic */ void m276(AppsFlyerLibCore appsFlyerLibCore) {
        AFEvent context = new Attr().context(appsFlyerLibCore.f430);
        if (appsFlyerLibCore.m268() && appsFlyerLibCore.m247(context, getSharedPreferences(appsFlyerLibCore.f430))) {
            appsFlyerLibCore.m242(context);
        }
    }

    /* renamed from: ǃ */
    static /* synthetic */ void m258(Context context, String str, String str2) {
        SharedPreferences.Editor edit = getSharedPreferences(context).edit();
        edit.putString(str, str2);
        edit.apply();
    }
}
