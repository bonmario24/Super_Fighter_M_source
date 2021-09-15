package com.google.android.gms.measurement.internal;

import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final /* synthetic */ class zzji implements Runnable {
    private final zzjj zza;
    private final int zzb;
    private final zzes zzc;
    private final Intent zzd;

    zzji(zzjj zzjj, int i, zzes zzes, Intent intent) {
        this.zza = zzjj;
        this.zzb = i;
        this.zzc = zzes;
        this.zzd = intent;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd);
    }
}
