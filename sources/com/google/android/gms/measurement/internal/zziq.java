package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zziq implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzil zzb;

    zziq(zzil zzil, zzn zzn) {
        this.zzb = zzil;
        this.zza = zzn;
    }

    public final void run() {
        zzek zzd = this.zzb.zzb;
        if (zzd == null) {
            this.zzb.zzr().zzf().zza("Discarding data. Failed to send app launch");
            return;
        }
        try {
            zzd.zza(this.zza);
            this.zzb.zzj().zzad();
            this.zzb.zza(zzd, (AbstractSafeParcelable) null, this.zza);
            this.zzb.zzak();
        } catch (RemoteException e) {
            this.zzb.zzr().zzf().zza("Failed to send app launch to the service", e);
        }
    }
}
