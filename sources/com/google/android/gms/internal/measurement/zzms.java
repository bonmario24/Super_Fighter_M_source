package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzms implements zzdb<zzmv> {
    private static zzms zza = new zzms();
    private final zzdb<zzmv> zzb;

    public static boolean zzb() {
        return ((zzmv) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzmv) zza.zza()).zzb();
    }

    private zzms(zzdb<zzmv> zzdb) {
        this.zzb = zzda.zza(zzdb);
    }

    public zzms() {
        this(zzda.zza(new zzmu()));
    }

    public final /* synthetic */ Object zza() {
        return this.zzb.zza();
    }
}
