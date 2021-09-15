package com.appsflyer.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.firebase.analytics.FirebaseAnalytics;

/* renamed from: com.appsflyer.internal.b */
public final class C0934b {

    /* renamed from: ı */
    private IntentFilter f562 = new IntentFilter("android.intent.action.BATTERY_CHANGED");

    /* renamed from: com.appsflyer.internal.b$e */
    public static final class C0936e {

        /* renamed from: ı */
        public static final C0934b f565 = new C0934b();
    }

    C0934b() {
    }

    @NonNull
    /* renamed from: Ι */
    public final C0935d mo14708(Context context) {
        String str = null;
        float f = 0.0f;
        try {
            Intent registerReceiver = context.registerReceiver((BroadcastReceiver) null, this.f562);
            if (registerReceiver != null) {
                if (2 == registerReceiver.getIntExtra("status", -1)) {
                    switch (registerReceiver.getIntExtra("plugged", -1)) {
                        case 1:
                            str = "ac";
                            break;
                        case 2:
                            str = "usb";
                            break;
                        case 4:
                            str = "wireless";
                            break;
                        default:
                            str = FacebookRequestErrorClassification.KEY_OTHER;
                            break;
                    }
                } else {
                    str = "no";
                }
                int intExtra = registerReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1);
                int intExtra2 = registerReceiver.getIntExtra("scale", -1);
                if (!(-1 == intExtra || -1 == intExtra2)) {
                    f = (100.0f * ((float) intExtra)) / ((float) intExtra2);
                }
            }
        } catch (Throwable th) {
        }
        return new C0935d(f, str);
    }

    /* renamed from: com.appsflyer.internal.b$d */
    public static final class C0935d {

        /* renamed from: ı */
        public final float f563;

        /* renamed from: Ι */
        public final String f564;

        C0935d(float f, String str) {
            this.f563 = f;
            this.f564 = str;
        }
    }
}
