package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final /* synthetic */ class zzjt implements Runnable {
    private final zzjq zza;

    zzjt(zzjq zzjq) {
        this.zza = zzjq;
    }

    public final void run() {
        zzjq zzjq = this.zza;
        zzjr zzjr = zzjq.zzc;
        long j = zzjq.zza;
        long j2 = zzjq.zzb;
        zzjr.zza.zzd();
        zzjr.zza.zzr().zzw().zza("Application going to the background");
        if (zzjr.zza.zzt().zza(zzaq.zzcd)) {
            zzjr.zza.zzs().zzs.zza(true);
        }
        if (!zzjr.zza.zzt().zzj().booleanValue()) {
            zzjr.zza.zzb.zzb(j2);
            zzjr.zza.zza(false, false, j2);
        }
        zzjr.zza.zzf().zza("auto", "_ab", j, new Bundle());
    }
}
