package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzkj;
import com.google.android.gms.internal.measurement.zzkp;
import com.google.android.gms.internal.measurement.zzkq;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzjs {
    @VisibleForTesting
    private long zza;
    @VisibleForTesting
    private long zzb;
    private final zzag zzc = new zzjv(this, this.zzd.zzz);
    private final /* synthetic */ zzjm zzd;

    public zzjs(zzjm zzjm) {
        this.zzd = zzjm;
        this.zza = zzjm.zzm().elapsedRealtime();
        this.zzb = this.zza;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(long j) {
        this.zzd.zzd();
        this.zzc.zzc();
        this.zza = j;
        this.zzb = this.zza;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(long j) {
        this.zzc.zzc();
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zzc.zzc();
        this.zza = 0;
        this.zzb = this.zza;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzc() {
        this.zzd.zzd();
        zza(false, false, this.zzd.zzm().elapsedRealtime());
        this.zzd.zze().zza(this.zzd.zzm().elapsedRealtime());
    }

    @WorkerThread
    public final boolean zza(boolean z, boolean z2, long j) {
        this.zzd.zzd();
        this.zzd.zzw();
        if (!zzkj.zzb() || !this.zzd.zzt().zza(zzaq.zzca)) {
            j = this.zzd.zzm().elapsedRealtime();
        }
        if (!zzkp.zzb() || !this.zzd.zzt().zza(zzaq.zzbw) || this.zzd.zzz.zzab()) {
            this.zzd.zzs().zzq.zza(this.zzd.zzm().currentTimeMillis());
        }
        long j2 = j - this.zza;
        if (z || j2 >= 1000) {
            if (this.zzd.zzt().zza(zzaq.zzat) && !z2) {
                j2 = (!zzkq.zzb() || !this.zzd.zzt().zza(zzaq.zzav) || !zzkj.zzb() || !this.zzd.zzt().zza(zzaq.zzca)) ? zzb() : zzc(j);
            }
            this.zzd.zzr().zzx().zza("Recording user engagement, ms", Long.valueOf(j2));
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j2);
            zzig.zza(this.zzd.zzi().zzab(), bundle, true);
            if (this.zzd.zzt().zza(zzaq.zzat) && !this.zzd.zzt().zza(zzaq.zzau) && z2) {
                bundle.putLong("_fr", 1);
            }
            if (!this.zzd.zzt().zza(zzaq.zzau) || !z2) {
                this.zzd.zzf().zza("auto", "_e", bundle);
            }
            this.zza = j;
            this.zzc.zzc();
            this.zzc.zza(3600000);
            return true;
        }
        this.zzd.zzr().zzx().zza("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j2));
        return false;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final long zzb() {
        long elapsedRealtime = this.zzd.zzm().elapsedRealtime();
        long j = elapsedRealtime - this.zzb;
        this.zzb = elapsedRealtime;
        return j;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final long zzc(long j) {
        long j2 = j - this.zzb;
        this.zzb = j;
        return j2;
    }
}
