package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzjh implements Runnable {
    private final /* synthetic */ zzjd zza;

    zzjh(zzjd zzjd) {
        this.zza = zzjd;
    }

    public final void run() {
        zzil zzil = this.zza.zza;
        Context zzn = this.zza.zza.zzn();
        this.zza.zza.zzu();
        zzil.zza(new ComponentName(zzn, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
