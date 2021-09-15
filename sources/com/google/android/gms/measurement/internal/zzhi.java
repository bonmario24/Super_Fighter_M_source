package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzkp;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzhi implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzhb zzb;

    zzhi(zzhb zzhb, long j) {
        this.zzb = zzhb;
        this.zza = j;
    }

    public final void run() {
        boolean z = true;
        zzhb zzhb = this.zzb;
        long j = this.zza;
        zzhb.zzd();
        zzhb.zzb();
        zzhb.zzw();
        zzhb.zzr().zzw().zza("Resetting analytics data (FE)");
        zzjm zzk = zzhb.zzk();
        zzk.zzd();
        zzk.zzb.zza();
        boolean zzab = zzhb.zzz.zzab();
        zzfe zzs = zzhb.zzs();
        zzs.zzh.zza(j);
        if (!TextUtils.isEmpty(zzs.zzs().zzv.zza())) {
            zzs.zzv.zza((String) null);
        }
        if (zzkp.zzb() && zzs.zzt().zza(zzaq.zzbw)) {
            zzs.zzq.zza(0);
        }
        if (!zzs.zzt().zzh()) {
            zzs.zzc(!zzab);
        }
        zzs.zzw.zza((String) null);
        zzs.zzx.zza(0);
        zzs.zzy.zza((Bundle) null);
        zzhb.zzh().zzad();
        if (zzkp.zzb() && zzhb.zzt().zza(zzaq.zzbw)) {
            zzhb.zzk().zza.zza();
        }
        if (zzab) {
            z = false;
        }
        zzhb.zzc = z;
        this.zzb.zzh().zza((AtomicReference<String>) new AtomicReference());
    }
}
