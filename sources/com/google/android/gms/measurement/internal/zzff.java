package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzff implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzfc zzb;

    zzff(zzfc zzfc, boolean z) {
        this.zzb = zzfc;
        this.zza = z;
    }

    public final void run() {
        this.zzb.zzb.zza(this.zza);
    }
}
