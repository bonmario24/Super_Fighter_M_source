package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzgj implements Runnable {
    private final /* synthetic */ zzao zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ zzgb zzc;

    zzgj(zzgb zzgb, zzao zzao, zzn zzn) {
        this.zzc = zzgb;
        this.zza = zzao;
        this.zzb = zzn;
    }

    public final void run() {
        zzao zzb2 = this.zzc.zzb(this.zza, this.zzb);
        this.zzc.zza.zzo();
        this.zzc.zza.zza(zzb2, this.zzb);
    }
}
