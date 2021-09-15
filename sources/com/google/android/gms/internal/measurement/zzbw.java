package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.RequiresApi;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public class zzbw {
    @GuardedBy("DirectBootUtils.class")
    private static UserManager zza;
    private static volatile boolean zzb;
    @GuardedBy("DirectBootUtils.class")
    private static boolean zzc = false;

    private zzbw() {
    }

    public static boolean zza() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean zza(Context context) {
        return !zza() || zzc(context);
    }

    @RequiresApi(24)
    @TargetApi(24)
    @GuardedBy("DirectBootUtils.class")
    private static boolean zzb(Context context) {
        boolean z = true;
        int i = 1;
        while (true) {
            if (i > 2) {
                z = false;
                break;
            }
            if (zza == null) {
                zza = (UserManager) context.getSystemService(UserManager.class);
            }
            UserManager userManager = zza;
            if (userManager == null) {
                break;
            }
            try {
                z = userManager.isUserUnlocked() || !userManager.isUserRunning(Process.myUserHandle());
            } catch (NullPointerException e) {
                Log.w("DirectBootUtils", "Failed to check if user is unlocked.", e);
                zza = null;
                i++;
            }
        }
        if (z) {
            zza = null;
        }
        return z;
    }

    @RequiresApi(24)
    @TargetApi(24)
    private static boolean zzc(Context context) {
        boolean z = true;
        if (!zzb) {
            synchronized (zzbw.class) {
                if (!zzb) {
                    z = zzb(context);
                    if (z) {
                        zzb = z;
                    }
                }
            }
        }
        return z;
    }

    static {
        boolean z;
        if (!zza()) {
            z = true;
        } else {
            z = false;
        }
        zzb = z;
    }
}
