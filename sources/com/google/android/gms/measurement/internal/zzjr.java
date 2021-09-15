package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzjr {
    final /* synthetic */ zzjm zza;
    private zzjq zzb;

    zzjr(zzjm zzjm) {
        this.zza = zzjm;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza() {
        this.zza.zzd();
        if (this.zza.zzt().zza(zzaq.zzbp) && this.zzb != null) {
            this.zza.zzc.removeCallbacks(this.zzb);
        }
        if (this.zza.zzt().zza(zzaq.zzcd)) {
            this.zza.zzs().zzs.zza(false);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(long j) {
        if (this.zza.zzt().zza(zzaq.zzbp)) {
            this.zzb = new zzjq(this, this.zza.zzm().currentTimeMillis(), j);
            this.zza.zzc.postDelayed(this.zzb, 2000);
        }
    }
}
