package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzga implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzgb zzb;

    zzga(zzgb zzgb, zzn zzn) {
        this.zzb = zzgb;
        this.zza = zzn;
    }

    public final void run() {
        this.zzb.zza.zzo();
        zzka zza2 = this.zzb.zza;
        zzn zzn = this.zza;
        zza2.zzq().zzd();
        zza2.zzk();
        Preconditions.checkNotEmpty(zzn.zza);
        zza2.zzc(zzn);
    }
}
