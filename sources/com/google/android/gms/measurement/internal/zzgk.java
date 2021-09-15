package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzgk implements Runnable {
    private final /* synthetic */ zzkh zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ zzgb zzc;

    zzgk(zzgb zzgb, zzkh zzkh, zzn zzn) {
        this.zzc = zzgb;
        this.zza = zzkh;
        this.zzb = zzn;
    }

    public final void run() {
        this.zzc.zza.zzo();
        if (this.zza.zza() == null) {
            this.zzc.zza.zzb(this.zza, this.zzb);
        } else {
            this.zzc.zza.zza(this.zza, this.zzb);
        }
    }
}
