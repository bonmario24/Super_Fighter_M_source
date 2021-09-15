package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.appevents.AppEventsLogger;

public final class CampaignTrackingReceiver extends BroadcastReceiver {
    static final String INSTALL_REFERRER = "referrer";

    public void onReceive(Context context, Intent intent) {
        String referrer = intent.getStringExtra("referrer");
        if (referrer != null && referrer.startsWith("fb")) {
            AppEventsLogger.setInstallReferrer(intent.getStringExtra("referrer"));
        }
    }
}
