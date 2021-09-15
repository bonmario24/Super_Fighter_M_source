package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzhl implements Runnable {
    private final /* synthetic */ zzgw zza;
    private final /* synthetic */ zzhb zzb;

    zzhl(zzhb zzhb, zzgw zzgw) {
        this.zzb = zzhb;
        this.zza = zzgw;
    }

    public final void run() {
        this.zzb.zza(this.zza);
    }
}
