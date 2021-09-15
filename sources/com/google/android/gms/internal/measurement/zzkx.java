package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzkx implements zzku {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Boolean> zzb;

    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    public final boolean zzb() {
        return zzb.zzc().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzct.zza("measurement.collection.efficient_engagement_reporting_enabled_2", true);
        zzb = zzct.zza("measurement.collection.redundant_engagement_removal_enabled", false);
    }
}
