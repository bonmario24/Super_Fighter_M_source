package com.appsflyer;

import com.appsflyer.internal.C0922af;
import com.appsflyer.internal.C0937c;
import com.google.firebase.messaging.FirebaseMessagingService;

public class FirebaseMessagingServiceListener extends FirebaseMessagingService {
    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.google.firebase.messaging.FirebaseMessagingService, com.appsflyer.FirebaseMessagingServiceListener] */
    public void onNewToken(String str) {
        FirebaseMessagingServiceListener.super.onNewToken(str);
        long currentTimeMillis = System.currentTimeMillis();
        if (str != null) {
            AFLogger.afInfoLog("Firebase Refreshed Token = ".concat(String.valueOf(str)));
            C0937c.C0938a.C0939c r2 = C0937c.C0938a.C0939c.m374(AppsFlyerProperties.getInstance().getString("afUninstallToken"));
            C0937c.C0938a.C0939c cVar = new C0937c.C0938a.C0939c(currentTimeMillis, str);
            if (r2.mo14710(cVar)) {
                C0922af.m327(getApplicationContext(), cVar.f577);
            }
        }
    }
}
