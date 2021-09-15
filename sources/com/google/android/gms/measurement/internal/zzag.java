package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzj;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
abstract class zzag {
    private static volatile Handler zzb;
    private final zzgt zza;
    private final Runnable zzc;
    /* access modifiers changed from: private */
    public volatile long zzd;

    zzag(zzgt zzgt) {
        Preconditions.checkNotNull(zzgt);
        this.zza = zzgt;
        this.zzc = new zzaj(this, zzgt);
    }

    public abstract void zza();

    public final void zza(long j) {
        zzc();
        if (j >= 0) {
            this.zzd = this.zza.zzm().currentTimeMillis();
            if (!zzd().postDelayed(this.zzc, j)) {
                this.zza.zzr().zzf().zza("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public final boolean zzb() {
        return this.zzd != 0;
    }

    /* access modifiers changed from: package-private */
    public final void zzc() {
        this.zzd = 0;
        zzd().removeCallbacks(this.zzc);
    }

    private final Handler zzd() {
        Handler handler;
        if (zzb != null) {
            return zzb;
        }
        synchronized (zzag.class) {
            if (zzb == null) {
                zzb = new zzj(this.zza.zzn().getMainLooper());
            }
            handler = zzb;
        }
        return handler;
    }
}
