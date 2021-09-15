package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzma implements zzdb<zzmd> {
    private static zzma zza = new zzma();
    private final zzdb<zzmd> zzb;

    public static boolean zzb() {
        return ((zzmd) zza.zza()).zza();
    }

    public static double zzc() {
        return ((zzmd) zza.zza()).zzb();
    }

    public static long zzd() {
        return ((zzmd) zza.zza()).zzc();
    }

    public static long zze() {
        return ((zzmd) zza.zza()).zzd();
    }

    public static String zzf() {
        return ((zzmd) zza.zza()).zze();
    }

    private zzma(zzdb<zzmd> zzdb) {
        this.zzb = zzda.zza(zzdb);
    }

    public zzma() {
        this(zzda.zza(new zzmc()));
    }

    public final /* synthetic */ Object zza() {
        return this.zzb.zza();
    }
}
