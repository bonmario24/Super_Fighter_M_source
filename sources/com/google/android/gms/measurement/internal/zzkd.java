package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzkd implements Runnable {
    private final /* synthetic */ zzkg zza;
    private final /* synthetic */ zzka zzb;

    zzkd(zzka zzka, zzkg zzkg) {
        this.zzb = zzka;
        this.zza = zzkg;
    }

    public final void run() {
        this.zzb.zza(this.zza);
        this.zzb.zza();
    }
}
