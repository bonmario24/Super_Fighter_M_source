package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzlk implements zzll {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Boolean> zzb;
    private static final zzcn<Boolean> zzc;
    private static final zzcn<Boolean> zzd;
    private static final zzcn<Long> zze;

    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    public final boolean zzb() {
        return zzb.zzc().booleanValue();
    }

    public final boolean zzc() {
        return zzc.zzc().booleanValue();
    }

    public final boolean zzd() {
        return zzd.zzc().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzct.zza("measurement.sdk.collection.enable_extend_user_property_size", true);
        zzb = zzct.zza("measurement.sdk.collection.last_deep_link_referrer2", true);
        zzc = zzct.zza("measurement.sdk.collection.last_deep_link_referrer_campaign2", false);
        zzd = zzct.zza("measurement.sdk.collection.last_gclid_from_referrer2", false);
        zze = zzct.zza("measurement.id.sdk.collection.last_deep_link_referrer2", 0);
    }
}
