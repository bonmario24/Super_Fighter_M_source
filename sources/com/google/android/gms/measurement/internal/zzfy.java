package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzfy implements Runnable {
    private final /* synthetic */ zzgy zza;
    private final /* synthetic */ zzfw zzb;

    zzfy(zzfw zzfw, zzgy zzgy) {
        this.zzb = zzfw;
        this.zza = zzgy;
    }

    public final void run() {
        this.zzb.zza(this.zza);
        this.zzb.zza();
    }
}
