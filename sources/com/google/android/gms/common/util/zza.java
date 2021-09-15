package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lzy.okgo.OkGo;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public final class zza {
    private static final IntentFilter filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static long zzhl;
    private static float zzhm = Float.NaN;

    @TargetApi(20)
    public static int zzh(Context context) {
        boolean z;
        boolean isScreenOn;
        int i;
        int i2 = 1;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, filter);
        int intExtra = registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0);
        int i3 = 3;
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            i3 = 7;
        }
        if ((intExtra & i3) != 0) {
            z = true;
        } else {
            z = false;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        if (PlatformVersion.isAtLeastKitKatWatch()) {
            isScreenOn = powerManager.isInteractive();
        } else {
            isScreenOn = powerManager.isScreenOn();
        }
        if (isScreenOn) {
            i = 2;
        } else {
            i = 0;
        }
        if (!z) {
            i2 = 0;
        }
        return i | i2;
    }

    public static synchronized float zzi(Context context) {
        float f;
        synchronized (zza.class) {
            if (SystemClock.elapsedRealtime() - zzhl >= OkGo.DEFAULT_MILLISECONDS || Float.isNaN(zzhm)) {
                Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, filter);
                if (registerReceiver != null) {
                    zzhm = ((float) registerReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
                }
                zzhl = SystemClock.elapsedRealtime();
                f = zzhm;
            } else {
                f = zzhm;
            }
        }
        return f;
    }
}
