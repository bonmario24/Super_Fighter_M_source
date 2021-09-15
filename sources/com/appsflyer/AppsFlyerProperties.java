package com.appsflyer;

import android.content.Context;
import android.content.SharedPreferences;
import com.appsflyer.internal.referrer.Payload;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class AppsFlyerProperties {
    public static final String ADDITIONAL_CUSTOM_DATA = "additionalCustomData";
    public static final String AF_KEY = "AppsFlyerKey";
    public static final String AF_WAITFOR_CUSTOMERID = "waitForCustomerId";
    public static final String APP_ID = "appid";
    public static final String APP_USER_ID = "AppUserId";
    public static final String CHANNEL = "channel";
    public static final String COLLECT_ANDROID_ID = "collectAndroidId";
    public static final String COLLECT_ANDROID_ID_FORCE_BY_USER = "collectAndroidIdForceByUser";
    public static final String COLLECT_FACEBOOK_ATTR_ID = "collectFacebookAttrId";
    public static final String COLLECT_FINGER_PRINT = "collectFingerPrint";
    public static final String COLLECT_IMEI = "collectIMEI";
    public static final String COLLECT_IMEI_FORCE_BY_USER = "collectIMEIForceByUser";
    public static final String COLLECT_MAC = "collectMAC";
    public static final String COLLECT_OAID = "collectOAID";
    public static final String CURRENCY_CODE = "currencyCode";
    public static final String DEVICE_TRACKING_DISABLED = "deviceTrackingDisabled";
    public static final String DISABLE_KEYSTORE = "keyPropDisableAFKeystore";
    public static final String DISABLE_LOGS_COMPLETELY = "disableLogs";
    public static final String DISABLE_OTHER_SDK = "disableOtherSdk";
    public static final String DPM = "disableProxy";
    public static final String EMAIL_CRYPT_TYPE = "userEmailsCryptType";
    public static final String ENABLE_GPS_FALLBACK = "enableGpsFallback";
    public static final String EXTENSION = "sdkExtension";
    public static final String IS_UPDATE = "IS_UPDATE";
    public static final String LAUNCH_PROTECT_ENABLED = "launchProtectEnabled";
    public static final String NEW_REFERRER_SENT = "newGPReferrerSent";
    public static final String ONELINK_DOMAIN = "onelinkDomain";
    public static final String ONELINK_ID = "oneLinkSlug";
    public static final String ONELINK_SCHEME = "onelinkScheme";
    public static final String USER_EMAIL = "userEmail";
    public static final String USER_EMAILS = "userEmails";
    public static final String USE_HTTP_FALLBACK = "useHttpFallback";

    /* renamed from: ǃ */
    private static AppsFlyerProperties f467 = new AppsFlyerProperties();

    /* renamed from: ı */
    private boolean f468;

    /* renamed from: Ɩ */
    private boolean f469 = false;

    /* renamed from: ɩ */
    private String f470;

    /* renamed from: Ι */
    private Map<String, Object> f471 = new HashMap();

    /* renamed from: ι */
    private boolean f472;

    private AppsFlyerProperties() {
    }

    public static AppsFlyerProperties getInstance() {
        return f467;
    }

    public synchronized void remove(String str) {
        this.f471.remove(str);
    }

    public synchronized void set(String str, String str2) {
        this.f471.put(str, str2);
    }

    public synchronized void set(String str, String[] strArr) {
        this.f471.put(str, strArr);
    }

    public synchronized void set(String str, int i) {
        this.f471.put(str, Integer.toString(i));
    }

    public synchronized void set(String str, long j) {
        this.f471.put(str, Long.toString(j));
    }

    public synchronized void set(String str, boolean z) {
        this.f471.put(str, Boolean.toString(z));
    }

    public synchronized void setCustomData(String str) {
        this.f471.put(ADDITIONAL_CUSTOM_DATA, str);
    }

    public synchronized void setUserEmails(String str) {
        this.f471.put(USER_EMAILS, str);
    }

    public synchronized String getString(String str) {
        return (String) this.f471.get(str);
    }

    public boolean getBoolean(String str, boolean z) {
        String string = getString(str);
        return string == null ? z : Boolean.valueOf(string).booleanValue();
    }

    public int getInt(String str, int i) {
        String string = getString(str);
        return string == null ? i : Integer.valueOf(string).intValue();
    }

    public long getLong(String str, long j) {
        String string = getString(str);
        return string == null ? j : Long.valueOf(string).longValue();
    }

    public synchronized Object getObject(String str) {
        return this.f471.get(str);
    }

    /* access modifiers changed from: protected */
    public boolean isOnReceiveCalled() {
        return this.f468;
    }

    /* access modifiers changed from: protected */
    public void setOnReceiveCalled() {
        this.f468 = true;
    }

    /* access modifiers changed from: protected */
    public boolean isFirstLaunchCalled() {
        return this.f472;
    }

    /* access modifiers changed from: protected */
    public void setFirstLaunchCalled(boolean z) {
        this.f472 = z;
    }

    /* access modifiers changed from: protected */
    public void setFirstLaunchCalled() {
        this.f472 = true;
    }

    /* access modifiers changed from: protected */
    public void setReferrer(String str) {
        set("AF_REFERRER", str);
        this.f470 = str;
    }

    public String getReferrer(Context context) {
        if (this.f470 != null) {
            return this.f470;
        }
        if (getString("AF_REFERRER") != null) {
            return getString("AF_REFERRER");
        }
        if (context != null) {
            return AppsFlyerLibCore.getSharedPreferences(context).getString(Payload.REFERRER, (String) null);
        }
        return null;
    }

    public boolean isEnableLog() {
        return getBoolean("shouldLog", true);
    }

    public boolean isLogsDisabledCompletely() {
        return getBoolean(DISABLE_LOGS_COMPLETELY, false);
    }

    public boolean isOtherSdkStringDisabled() {
        return getBoolean(DISABLE_OTHER_SDK, false);
    }

    public synchronized void saveProperties(SharedPreferences sharedPreferences) {
        sharedPreferences.edit().putString("savedProperties", new JSONObject(this.f471).toString()).apply();
    }

    public synchronized void loadProperties(Context context) {
        if (!m310()) {
            String string = AppsFlyerLibCore.getSharedPreferences(context).getString("savedProperties", (String) null);
            if (string != null) {
                AFLogger.afDebugLog("Loading properties..");
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (this.f471.get(next) == null) {
                            this.f471.put(next, jSONObject.getString(next));
                        }
                    }
                    this.f469 = true;
                } catch (JSONException e) {
                    AFLogger.afErrorLog("Failed loading properties", e);
                }
                AFLogger.afDebugLog(new StringBuilder("Done loading properties: ").append(this.f469).toString());
            }
        }
    }

    /* renamed from: Ι */
    private boolean m310() {
        return this.f469;
    }

    public enum EmailsCryptType {
        NONE(0),
        SHA256(3);
        

        /* renamed from: ɩ */
        private final int f474;

        private EmailsCryptType(int i) {
            this.f474 = i;
        }

        public final int getValue() {
            return this.f474;
        }
    }
}
