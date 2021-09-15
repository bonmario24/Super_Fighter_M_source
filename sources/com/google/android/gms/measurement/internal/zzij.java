package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzkj;
import com.google.android.gms.internal.measurement.zzkq;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzij implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ zzih zzc;
    private final /* synthetic */ zzih zzd;
    private final /* synthetic */ zzig zze;

    zzij(zzig zzig, boolean z, long j, zzih zzih, zzih zzih2) {
        this.zze = zzig;
        this.zza = z;
        this.zzb = j;
        this.zzc = zzih;
        this.zzd = zzih2;
    }

    public final void run() {
        boolean z;
        long zzb2;
        boolean z2 = false;
        if (this.zze.zzt().zza(zzaq.zzat)) {
            z = this.zza && this.zze.zza != null;
            if (z) {
                this.zze.zza(this.zze.zza, true, this.zzb);
            }
        } else {
            if (this.zza && this.zze.zza != null) {
                this.zze.zza(this.zze.zza, true, this.zzb);
            }
            z = false;
        }
        if (this.zzc == null || this.zzc.zzc != this.zzd.zzc || !zzkm.zzc(this.zzc.zzb, this.zzd.zzb) || !zzkm.zzc(this.zzc.zza, this.zzd.zza)) {
            z2 = true;
        }
        if (z2) {
            Bundle bundle = new Bundle();
            zzig.zza(this.zzd, bundle, true);
            if (this.zzc != null) {
                if (this.zzc.zza != null) {
                    bundle.putString("_pn", this.zzc.zza);
                }
                if (this.zzc.zzb != null) {
                    bundle.putString("_pc", this.zzc.zzb);
                }
                bundle.putLong("_pi", this.zzc.zzc);
            }
            if (this.zze.zzt().zza(zzaq.zzat) && z) {
                if (!zzkq.zzb() || !this.zze.zzt().zza(zzaq.zzav) || !zzkj.zzb() || !this.zze.zzt().zza(zzaq.zzca)) {
                    zzb2 = this.zze.zzk().zzb.zzb();
                } else {
                    zzb2 = this.zze.zzk().zzb.zzc(this.zzb);
                }
                if (zzb2 > 0) {
                    this.zze.zzp().zza(bundle, zzb2);
                }
            }
            this.zze.zzf().zzb("auto", "_vs", bundle);
        }
        this.zze.zza = this.zzd;
        this.zze.zzh().zza(this.zzd);
    }
}
