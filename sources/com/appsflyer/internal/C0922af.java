package com.appsflyer.internal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import com.appsflyer.AFLogger;
import com.appsflyer.AndroidUtils;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.FirebaseMessagingServiceListener;
import com.appsflyer.internal.C0937c;

/* renamed from: com.appsflyer.internal.af */
public final class C0922af {
    C0922af() {
    }

    /* renamed from: ǃ */
    public static boolean m328(Context context) {
        if (AppsFlyerLib.getInstance().isTrackingStopped()) {
            return false;
        }
        try {
            Class.forName("com.google.firebase.messaging.FirebaseMessagingService");
            if (AndroidUtils.m233(context, new Intent("com.google.firebase.MESSAGING_EVENT", (Uri) null, context, FirebaseMessagingServiceListener.class))) {
                return true;
            }
            AFLogger.afWarnLog("Cannot verify existence of our InstanceID Listener Service in the manifest. Please refer to documentation.");
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        } catch (Throwable th) {
            AFLogger.afErrorLog("An error occurred while trying to verify manifest declarations: ", th);
            return false;
        }
    }

    /* renamed from: ı */
    public static void m327(Context context, String str) {
        if (str != null) {
            AFLogger.afInfoLog("updateServerUninstallToken called with: ".concat(String.valueOf(str)));
            C0937c.C0938a.C0939c r0 = C0937c.C0938a.C0939c.m374(AppsFlyerProperties.getInstance().getString("afUninstallToken"));
            SharedPreferences sharedPreferences = AppsFlyerLibCore.getSharedPreferences(context);
            if (!sharedPreferences.getBoolean("sentRegisterRequestToAF", false) || r0.f577 == null || !r0.f577.equals(str)) {
                AppsFlyerProperties.getInstance().set("afUninstallToken", str);
                if (AppsFlyerLibCore.m246(sharedPreferences)) {
                    AppsFlyerLibCore.getInstance().mo14604(context, str);
                }
            }
        }
    }
}
