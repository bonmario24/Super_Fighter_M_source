package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzin implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzkh zzb;
    private final /* synthetic */ zzn zzc;
    private final /* synthetic */ zzil zzd;

    zzin(zzil zzil, boolean z, zzkh zzkh, zzn zzn) {
        this.zzd = zzil;
        this.zza = z;
        this.zzb = zzkh;
        this.zzc = zzn;
    }

    public final void run() {
        zzek zzd2 = this.zzd.zzb;
        if (zzd2 == null) {
            this.zzd.zzr().zzf().zza("Discarding data. Failed to set user property");
            return;
        }
        this.zzd.zza(zzd2, (AbstractSafeParcelable) this.zza ? null : this.zzb, this.zzc);
        this.zzd.zzak();
    }
}
