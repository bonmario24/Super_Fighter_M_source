package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzja implements zzdb<zzjd> {
    private static zzja zza = new zzja();
    private final zzdb<zzjd> zzb;

    public static boolean zzb() {
        return ((zzjd) zza.zza()).zza();
    }

    public static long zzc() {
        return ((zzjd) zza.zza()).zzb();
    }

    private zzja(zzdb<zzjd> zzdb) {
        this.zzb = zzda.zza(zzdb);
    }

    public zzja() {
        this(zzda.zza(new zzjc()));
    }

    public final /* synthetic */ Object zza() {
        return this.zzb.zza();
    }
}
