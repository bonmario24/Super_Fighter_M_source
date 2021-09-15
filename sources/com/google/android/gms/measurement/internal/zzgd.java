package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzgd implements Runnable {
    private final /* synthetic */ zzw zza;
    private final /* synthetic */ zzgb zzb;

    zzgd(zzgb zzgb, zzw zzw) {
        this.zzb = zzgb;
        this.zza = zzw;
    }

    public final void run() {
        this.zzb.zza.zzo();
        if (this.zza.zzc.zza() == null) {
            this.zzb.zza.zzb(this.zza);
        } else {
            this.zzb.zza.zza(this.zza);
        }
    }
}
