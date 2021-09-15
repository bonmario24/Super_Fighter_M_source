package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzlc implements zzdb<zzlf> {
    private static zzlc zza = new zzlc();
    private final zzdb<zzlf> zzb;

    public static boolean zzb() {
        return ((zzlf) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzlf) zza.zza()).zzb();
    }

    private zzlc(zzdb<zzlf> zzdb) {
        this.zzb = zzda.zza(zzdb);
    }

    public zzlc() {
        this(zzda.zza(new zzle()));
    }

    public final /* synthetic */ Object zza() {
        return this.zzb.zza();
    }
}
