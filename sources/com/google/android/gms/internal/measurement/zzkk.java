package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzkk implements zzdb<zzkn> {
    private static zzkk zza = new zzkk();
    private final zzdb<zzkn> zzb;

    public static boolean zzb() {
        return ((zzkn) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzkn) zza.zza()).zzb();
    }

    private zzkk(zzdb<zzkn> zzdb) {
        this.zzb = zzda.zza(zzdb);
    }

    public zzkk() {
        this(zzda.zza(new zzkm()));
    }

    public final /* synthetic */ Object zza() {
        return this.zzb.zza();
    }
}
