package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzj;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzjm extends zzg {
    protected final zzju zza = new zzju(this);
    protected final zzjs zzb = new zzjs(this);
    /* access modifiers changed from: private */
    public Handler zzc;
    private final zzjr zzd = new zzjr(this);

    zzjm(zzfw zzfw) {
        super(zzfw);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzab() {
        zzd();
        if (this.zzc == null) {
            this.zzc = new zzj(Looper.getMainLooper());
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(long j) {
        zzd();
        zzab();
        zzr().zzx().zza("Activity resumed, time", Long.valueOf(j));
        this.zzd.zza();
        if (zzt().zzj().booleanValue()) {
            this.zzb.zza(j);
        }
        zzju zzju = this.zza;
        zzju.zza.zzd();
        if (zzju.zza.zzz.zzab()) {
            if (!zzju.zza.zzt().zza(zzaq.zzcd)) {
                zzju.zza.zzs().zzs.zza(false);
            }
            zzju.zza(zzju.zza.zzm().currentTimeMillis(), false);
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzb(long j) {
        zzd();
        zzab();
        zzr().zzx().zza("Activity paused, time", Long.valueOf(j));
        this.zzd.zza(j);
        if (zzt().zzj().booleanValue()) {
            this.zzb.zzb(j);
        }
        zzju zzju = this.zza;
        if (!zzju.zza.zzt().zza(zzaq.zzcd)) {
            zzju.zza.zzs().zzs.zza(true);
        }
    }

    public final boolean zza(boolean z, boolean z2, long j) {
        return this.zzb.zza(z, z2, j);
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    public final /* bridge */ /* synthetic */ zza zze() {
        return super.zze();
    }

    public final /* bridge */ /* synthetic */ zzhb zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzep zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzil zzh() {
        return super.zzh();
    }

    public final /* bridge */ /* synthetic */ zzig zzi() {
        return super.zzi();
    }

    public final /* bridge */ /* synthetic */ zzeo zzj() {
        return super.zzj();
    }

    public final /* bridge */ /* synthetic */ zzjm zzk() {
        return super.zzk();
    }

    public final /* bridge */ /* synthetic */ zzai zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzeq zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzkm zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzft zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzes zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzfe zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzy zzt() {
        return super.zzt();
    }

    public final /* bridge */ /* synthetic */ zzx zzu() {
        return super.zzu();
    }
}
