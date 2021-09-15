package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzjq implements Runnable {
    long zza;
    long zzb;
    final /* synthetic */ zzjr zzc;

    zzjq(zzjr zzjr, long j, long j2) {
        this.zzc = zzjr;
        this.zza = j;
        this.zzb = j2;
    }

    public final void run() {
        this.zzc.zza.zzq().zza((Runnable) new zzjt(this));
    }
}
