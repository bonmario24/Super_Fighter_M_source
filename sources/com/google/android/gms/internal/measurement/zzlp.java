package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzlp implements zzlm {
    private static final zzcn<Long> zza;
    private static final zzcn<Boolean> zzb;
    private static final zzcn<Boolean> zzc;
    private static final zzcn<Boolean> zzd;
    private static final zzcn<Long> zze;

    public final boolean zza() {
        return zzb.zzc().booleanValue();
    }

    public final boolean zzb() {
        return zzc.zzc().booleanValue();
    }

    public final boolean zzc() {
        return zzd.zzc().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzct.zza("measurement.id.lifecycle.app_in_background_parameter", 0);
        zzb = zzct.zza("measurement.lifecycle.app_backgrounded_engagement", false);
        zzc = zzct.zza("measurement.lifecycle.app_backgrounded_tracking", true);
        zzd = zzct.zza("measurement.lifecycle.app_in_background_parameter", false);
        zze = zzct.zza("measurement.id.lifecycle.app_backgrounded_tracking", 0);
    }
}
