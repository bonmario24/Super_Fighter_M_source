package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zziw implements zzix {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Boolean> zzb;

    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzct.zza("measurement.androidId.delete_feature", true);
        zzb = zzct.zza("measurement.log_androidId_enabled", false);
    }
}
