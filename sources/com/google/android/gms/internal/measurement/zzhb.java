package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzhb {
    private static final zzhb zza = new zzhb();
    private final zzhe zzb = new zzgd();
    private final ConcurrentMap<Class<?>, zzhf<?>> zzc = new ConcurrentHashMap();

    public static zzhb zza() {
        return zza;
    }

    public final <T> zzhf<T> zza(Class<T> cls) {
        zzfh.zza(cls, "messageType");
        zzhf<T> zzhf = (zzhf) this.zzc.get(cls);
        if (zzhf != null) {
            return zzhf;
        }
        zzhf<T> zza2 = this.zzb.zza(cls);
        zzfh.zza(cls, "messageType");
        zzfh.zza(zza2, "schema");
        zzhf<T> putIfAbsent = this.zzc.putIfAbsent(cls, zza2);
        return putIfAbsent != null ? putIfAbsent : zza2;
    }

    public final <T> zzhf<T> zza(T t) {
        return zza(t.getClass());
    }

    private zzhb() {
    }
}
