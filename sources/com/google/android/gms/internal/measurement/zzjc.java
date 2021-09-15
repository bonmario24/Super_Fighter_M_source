package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzjc implements zzjd {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Long> zzb;

    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    public final long zzb() {
        return zzb.zzc().longValue();
    }

    static {
        zzct zzct = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzct.zza("measurement.sdk.attribution.cache", true);
        zzb = zzct.zza("measurement.sdk.attribution.cache.ttl", 604800000);
    }
}
