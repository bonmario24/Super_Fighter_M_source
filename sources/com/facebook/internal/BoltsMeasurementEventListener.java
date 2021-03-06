package com.facebook.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.p000v4.content.LocalBroadcastManager;
import com.facebook.appevents.InternalAppEventsLogger;

public class BoltsMeasurementEventListener extends BroadcastReceiver {
    private static final String BOLTS_MEASUREMENT_EVENT_PREFIX = "bf_";
    private static final String MEASUREMENT_EVENT_ARGS_KEY = "event_args";
    private static final String MEASUREMENT_EVENT_NAME_KEY = "event_name";
    private static final String MEASUREMENT_EVENT_NOTIFICATION_NAME = "com.parse.bolts.measurement_event";
    private static BoltsMeasurementEventListener _instance;
    private Context applicationContext;

    private BoltsMeasurementEventListener(Context context) {
        this.applicationContext = context.getApplicationContext();
    }

    private void open() {
        LocalBroadcastManager.getInstance(this.applicationContext).registerReceiver(this, new IntentFilter("com.parse.bolts.measurement_event"));
    }

    private void close() {
        LocalBroadcastManager.getInstance(this.applicationContext).unregisterReceiver(this);
    }

    public static BoltsMeasurementEventListener getInstance(Context context) {
        if (_instance != null) {
            return _instance;
        }
        _instance = new BoltsMeasurementEventListener(context);
        _instance.open();
        return _instance;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public void onReceive(Context context, Intent intent) {
        InternalAppEventsLogger logger = new InternalAppEventsLogger(context);
        String eventName = BOLTS_MEASUREMENT_EVENT_PREFIX + intent.getStringExtra("event_name");
        Bundle eventArgs = intent.getBundleExtra("event_args");
        Bundle logData = new Bundle();
        for (String key : eventArgs.keySet()) {
            logData.putString(key.replaceAll("[^0-9a-zA-Z _-]", "-").replaceAll("^[ -]*", "").replaceAll("[ -]*$", ""), (String) eventArgs.get(key));
        }
        logger.logEvent(eventName, logData);
    }
}
