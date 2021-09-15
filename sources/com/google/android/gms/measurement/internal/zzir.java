package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzp;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzir implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzp zzb;
    private final /* synthetic */ zzil zzc;

    zzir(zzil zzil, zzn zzn, zzp zzp) {
        this.zzc = zzil;
        this.zza = zzn;
        this.zzb = zzp;
    }

    public final void run() {
        try {
            zzek zzd = this.zzc.zzb;
            if (zzd == null) {
                this.zzc.zzr().zzf().zza("Failed to get app instance id");
                return;
            }
            String zzc2 = zzd.zzc(this.zza);
            if (zzc2 != null) {
                this.zzc.zzf().zza(zzc2);
                this.zzc.zzs().zzj.zza(zzc2);
            }
            this.zzc.zzak();
            this.zzc.zzp().zza(this.zzb, zzc2);
        } catch (RemoteException e) {
            this.zzc.zzr().zzf().zza("Failed to get app instance id", e);
        } finally {
            this.zzc.zzp().zza(this.zzb, (String) null);
        }
    }
}
