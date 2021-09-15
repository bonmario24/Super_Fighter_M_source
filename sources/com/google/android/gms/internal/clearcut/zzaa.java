package com.google.android.gms.internal.clearcut;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.UserManager;

public class zzaa {
    private static volatile UserManager zzdc;
    private static volatile boolean zzdd = (!zzf());

    private zzaa() {
    }

    public static boolean zze(Context context) {
        return zzf() && !zzf(context);
    }

    private static boolean zzf() {
        return Build.VERSION.SDK_INT >= 24;
    }

    @TargetApi(24)
    private static boolean zzf(Context context) {
        boolean z = zzdd;
        if (z) {
            return z;
        }
        UserManager userManager = zzdc;
        if (userManager == null) {
            synchronized (zzaa.class) {
                userManager = zzdc;
                if (userManager == null) {
                    userManager = (UserManager) context.getSystemService(UserManager.class);
                    zzdc = userManager;
                    if (userManager == null) {
                        zzdd = true;
                        return true;
                    }
                }
            }
        }
        boolean isUserUnlocked = userManager.isUserUnlocked();
        zzdd = isUserUnlocked;
        if (!isUserUnlocked) {
            return isUserUnlocked;
        }
        zzdc = null;
        return isUserUnlocked;
    }
}
