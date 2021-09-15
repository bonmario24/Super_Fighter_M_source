package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzjn implements zzjk {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Boolean> zzb;
    private static final zzcn<Boolean> zzc;
    private static final zzcn<Long> zzd;

    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    public final boolean zzb() {
        return zzc.zzc().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzct.zza("measurement.sdk.dynamite.allow_remote_dynamite", false);
        zzb = zzct.zza("measurement.collection.init_params_control_enabled", true);
        zzc = zzct.zza("measurement.sdk.dynamite.use_dynamite3", true);
        zzd = zzct.zza("measurement.id.sdk.dynamite.use_dynamite", 0);
    }
}
