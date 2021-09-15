package com.google.android.gms.internal.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import androidx.annotation.GuardedBy;
import androidx.annotation.RequiresApi;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public final class zzl {
    private static volatile boolean zzjs;
    @GuardedBy("DirectBootUtils.class")
    private static boolean zzjt = false;

    public static boolean zzan() {
        return Build.VERSION.SDK_INT >= 24;
    }

    @RequiresApi(24)
    @TargetApi(24)
    public static Context getDeviceProtectedStorageContext(Context context) {
        return context.isDeviceProtectedStorage() ? context : context.createDeviceProtectedStorageContext();
    }

    static {
        boolean z;
        if (!zzan()) {
            z = true;
        } else {
            z = false;
        }
        zzjs = z;
    }
}
