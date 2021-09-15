package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzhs implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzhb zzb;

    zzhs(zzhb zzhb, boolean z) {
        this.zzb = zzhb;
        this.zza = z;
    }

    public final void run() {
        this.zzb.zzd(this.zza);
    }
}
