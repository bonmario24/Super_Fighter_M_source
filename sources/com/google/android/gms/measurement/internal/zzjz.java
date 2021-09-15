package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzjz extends zzag {
    private final /* synthetic */ zzka zza;
    private final /* synthetic */ zzjw zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjz(zzjw zzjw, zzgt zzgt, zzka zzka) {
        super(zzgt);
        this.zzb = zzjw;
        this.zza = zzka;
    }

    public final void zza() {
        this.zzb.zzf();
        this.zzb.zzr().zzx().zza("Starting upload from DelayedRunnable");
        this.zza.zzl();
    }
}
