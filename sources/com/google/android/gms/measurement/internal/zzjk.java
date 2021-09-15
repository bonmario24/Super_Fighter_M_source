package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzjk implements Runnable {
    private final /* synthetic */ zzka zza;
    private final /* synthetic */ Runnable zzb;

    zzjk(zzjj zzjj, zzka zzka, Runnable runnable) {
        this.zza = zzka;
        this.zzb = runnable;
    }

    public final void run() {
        this.zza.zzo();
        this.zza.zza(this.zzb);
        this.zza.zzl();
    }
}
