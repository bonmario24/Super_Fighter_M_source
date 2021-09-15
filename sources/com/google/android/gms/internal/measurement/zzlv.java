package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzlv implements zzls {
    private static final zzcn<Long> zza;
    private static final zzcn<Long> zzb;

    public final long zza() {
        return zzb.zzc().longValue();
    }

    static {
        zzct zzct = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzct.zza("measurement.id.max_bundles_per_iteration", 0);
        zzb = zzct.zza("measurement.max_bundles_per_iteration", 2);
    }
}
