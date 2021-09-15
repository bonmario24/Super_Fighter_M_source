package com.google.android.gms.measurement.internal;

import android.app.ActivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzjx;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzju {
    final /* synthetic */ zzjm zza;

    zzju(zzjm zzjm) {
        this.zza = zzjm;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza() {
        this.zza.zzd();
        if (this.zza.zzs().zza(this.zza.zzm().currentTimeMillis())) {
            this.zza.zzs().zzm.zza(true);
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (runningAppProcessInfo.importance == 100) {
                this.zza.zzr().zzx().zza("Detected application was in foreground");
                zzb(this.zza.zzm().currentTimeMillis(), false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(long j, boolean z) {
        this.zza.zzd();
        this.zza.zzab();
        if (this.zza.zzs().zza(j)) {
            this.zza.zzs().zzm.zza(true);
        }
        this.zza.zzs().zzq.zza(j);
        if (this.zza.zzs().zzm.zza()) {
            zzb(j, z);
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final void zzb(long j, boolean z) {
        this.zza.zzd();
        if (this.zza.zzz.zzab()) {
            this.zza.zzs().zzq.zza(j);
            this.zza.zzr().zzx().zza("Session started, time", Long.valueOf(this.zza.zzm().elapsedRealtime()));
            Long valueOf = Long.valueOf(j / 1000);
            this.zza.zzf().zza("auto", "_sid", (Object) valueOf, j);
            this.zza.zzs().zzm.zza(false);
            Bundle bundle = new Bundle();
            bundle.putLong("_sid", valueOf.longValue());
            if (this.zza.zzt().zza(zzaq.zzbq) && z) {
                bundle.putLong("_aib", 1);
            }
            this.zza.zzf().zza("auto", "_s", j, bundle);
            if (zzjx.zzb() && this.zza.zzt().zza(zzaq.zzbv)) {
                String zza2 = this.zza.zzs().zzv.zza();
                if (!TextUtils.isEmpty(zza2)) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("_ffr", zza2);
                    this.zza.zzf().zza("auto", "_ssr", j, bundle2);
                }
            }
        }
    }
}
