package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzip implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzil zzb;

    zzip(zzil zzil, zzn zzn) {
        this.zzb = zzil;
        this.zza = zzn;
    }

    public final void run() {
        zzek zzd = this.zzb.zzb;
        if (zzd == null) {
            this.zzb.zzr().zzf().zza("Failed to reset data on the service: not connected to service");
            return;
        }
        try {
            zzd.zzd(this.zza);
        } catch (RemoteException e) {
            this.zzb.zzr().zzf().zza("Failed to reset data on the service: remote exception", e);
        }
        this.zzb.zzak();
    }
}
