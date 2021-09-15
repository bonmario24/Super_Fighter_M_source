package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzjp implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzjm zzb;

    zzjp(zzjm zzjm, long j) {
        this.zzb = zzjm;
        this.zza = j;
    }

    public final void run() {
        this.zzb.zza(this.zza);
    }
}
