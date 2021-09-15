package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzjf implements Runnable {
    private final /* synthetic */ ComponentName zza;
    private final /* synthetic */ zzjd zzb;

    zzjf(zzjd zzjd, ComponentName componentName) {
        this.zzb = zzjd;
        this.zza = componentName;
    }

    public final void run() {
        this.zzb.zza.zza(this.zza);
    }
}
