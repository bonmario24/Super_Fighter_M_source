package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzmc implements zzmd {
    private static final zzcn<Boolean> zza;
    private static final zzcn<Double> zzb;
    private static final zzcn<Long> zzc;
    private static final zzcn<Long> zzd;
    private static final zzcn<String> zze;

    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    public final double zzb() {
        return zzb.zzc().doubleValue();
    }

    public final long zzc() {
        return zzc.zzc().longValue();
    }

    public final long zzd() {
        return zzd.zzc().longValue();
    }

    public final String zze() {
        return zze.zzc();
    }

    static {
        zzct zzct = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzct.zza("measurement.test.boolean_flag", false);
        zzb = zzct.zza("measurement.test.double_flag", -3.0d);
        zzc = zzct.zza("measurement.test.int_flag", -2);
        zzd = zzct.zza("measurement.test.long_flag", -1);
        zze = zzct.zza("measurement.test.string_flag", "---");
    }
}
