package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzjc implements Runnable {
    private final /* synthetic */ zzek zza;
    private final /* synthetic */ zzjd zzb;

    zzjc(zzjd zzjd, zzek zzek) {
        this.zzb = zzjd;
        this.zza = zzek;
    }

    public final void run() {
        synchronized (this.zzb) {
            boolean unused = this.zzb.zzb = false;
            if (!this.zzb.zza.zzab()) {
                this.zzb.zza.zzr().zzx().zza("Connected to service");
                this.zzb.zza.zza(this.zza);
            }
        }
    }
}
