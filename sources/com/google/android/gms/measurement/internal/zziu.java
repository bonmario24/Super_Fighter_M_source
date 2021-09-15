package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zziu implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzil zzb;

    zziu(zzil zzil, zzn zzn) {
        this.zzb = zzil;
        this.zza = zzn;
    }

    public final void run() {
        zzek zzd = this.zzb.zzb;
        if (zzd == null) {
            this.zzb.zzr().zzf().zza("Failed to send measurementEnabled to service");
            return;
        }
        try {
            zzd.zzb(this.zza);
            this.zzb.zzak();
        } catch (RemoteException e) {
            this.zzb.zzr().zzf().zza("Failed to send measurementEnabled to the service", e);
        }
    }
}
