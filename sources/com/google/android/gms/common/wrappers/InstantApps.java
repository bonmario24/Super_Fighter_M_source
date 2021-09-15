package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public class InstantApps {
    private static Context zzim;
    private static Boolean zzin;

    @KeepForSdk
    public static synchronized boolean isInstantApp(Context context) {
        boolean booleanValue;
        synchronized (InstantApps.class) {
            Context applicationContext = context.getApplicationContext();
            if (zzim == null || zzin == null || zzim != applicationContext) {
                zzin = null;
                if (PlatformVersion.isAtLeastO()) {
                    zzin = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
                } else {
                    try {
                        context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                        zzin = true;
                    } catch (ClassNotFoundException e) {
                        zzin = false;
                    }
                }
                zzim = applicationContext;
                booleanValue = zzin.booleanValue();
            } else {
                booleanValue = zzin.booleanValue();
            }
        }
        return booleanValue;
    }
}
