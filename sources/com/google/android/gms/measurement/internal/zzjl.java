package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final /* synthetic */ class zzjl implements Runnable {
    private final zzjj zza;
    private final zzes zzb;
    private final JobParameters zzc;

    zzjl(zzjj zzjj, zzes zzes, JobParameters jobParameters) {
        this.zza = zzjj;
        this.zzb = zzes;
        this.zzc = jobParameters;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}
