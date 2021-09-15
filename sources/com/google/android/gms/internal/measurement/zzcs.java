package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzcs implements zzcd {
    @GuardedBy("SharedPreferencesLoader.class")
    private static final Map<String, zzcs> zza = new ArrayMap();
    private final SharedPreferences zzb;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzc = new zzcv(this);
    private final Object zzd = new Object();
    private volatile Map<String, ?> zze;
    @GuardedBy("this")
    private final List<zzca> zzf = new ArrayList();

    static zzcs zza(Context context, String str) {
        boolean z;
        zzcs zzcs;
        if (!zzbw.zza() || str.startsWith("direct_boot:")) {
            z = true;
        } else {
            z = zzbw.zza(context);
        }
        if (!z) {
            return null;
        }
        synchronized (zzcs.class) {
            zzcs = zza.get(str);
            if (zzcs == null) {
                zzcs = new zzcs(zzb(context, str));
                zza.put(str, zzcs);
            }
        }
        return zzcs;
    }

    private static SharedPreferences zzb(Context context, String str) {
        SharedPreferences sharedPreferences;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (str.startsWith("direct_boot:")) {
                if (zzbw.zza()) {
                    context = context.createDeviceProtectedStorageContext();
                }
                sharedPreferences = context.getSharedPreferences(str.substring(12), 0);
            } else {
                sharedPreferences = context.getSharedPreferences(str, 0);
                StrictMode.setThreadPolicy(allowThreadDiskReads);
            }
            return sharedPreferences;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    private zzcs(SharedPreferences sharedPreferences) {
        this.zzb = sharedPreferences;
        this.zzb.registerOnSharedPreferenceChangeListener(this.zzc);
    }

    public final Object zza(String str) {
        Map<String, ?> map = this.zze;
        if (map == null) {
            synchronized (this.zzd) {
                map = this.zze;
                if (map == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        map = this.zzb.getAll();
                        this.zze = map;
                    } finally {
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                    }
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    static synchronized void zza() {
        synchronized (zzcs.class) {
            for (zzcs next : zza.values()) {
                next.zzb.unregisterOnSharedPreferenceChangeListener(next.zzc);
            }
            zza.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        synchronized (this.zzd) {
            this.zze = null;
            zzcn.zza();
        }
        synchronized (this) {
            for (zzca zza2 : this.zzf) {
                zza2.zza();
            }
        }
    }
}
