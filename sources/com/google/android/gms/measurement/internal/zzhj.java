package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzhj implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzhb zzb;

    zzhj(zzhb zzhb, AtomicReference atomicReference) {
        this.zzb = zzhb;
        this.zza = atomicReference;
    }

    public final void run() {
        this.zzb.zzh().zza((AtomicReference<String>) this.zza);
    }
}
