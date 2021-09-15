package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzjo implements zzjp {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Long> zzb;
    private static final zzcn<Boolean> zzc;
    private static final zzcn<Boolean> zzd;
    private static final zzcn<Boolean> zze;
    private static final zzcn<Boolean> zzf;
    private static final zzcn<Boolean> zzg;

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    public final boolean zzc() {
        return zzc.zzc().booleanValue();
    }

    public final boolean zzd() {
        return zzd.zzc().booleanValue();
    }

    public final boolean zze() {
        return zze.zzc().booleanValue();
    }

    public final boolean zzf() {
        return zzf.zzc().booleanValue();
    }

    public final boolean zzg() {
        return zzg.zzc().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzct.zza("measurement.gold.enhanced_ecommerce.format_logs", false);
        zzb = zzct.zza("measurement.id.gold.enhanced_ecommerce.service", 0);
        zzc = zzct.zza("measurement.gold.enhanced_ecommerce.log_nested_complex_events", true);
        zzd = zzct.zza("measurement.gold.enhanced_ecommerce.nested_param_daily_event_count", false);
        zze = zzct.zza("measurement.gold.enhanced_ecommerce.updated_schema.client", true);
        zzf = zzct.zza("measurement.gold.enhanced_ecommerce.updated_schema.service", false);
        zzg = zzct.zza("measurement.gold.enhanced_ecommerce.upload_nested_complex_events", false);
    }
}
