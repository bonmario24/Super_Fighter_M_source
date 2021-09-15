package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzim implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzil zzd;

    zzim(zzil zzil, AtomicReference atomicReference, zzn zzn, boolean z) {
        this.zzd = zzil;
        this.zza = atomicReference;
        this.zzb = zzn;
        this.zzc = z;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                zzek zzd2 = this.zzd.zzb;
                if (zzd2 == null) {
                    this.zzd.zzr().zzf().zza("Failed to get all user properties; not connected to service");
                    this.zza.notify();
                    return;
                }
                this.zza.set(zzd2.zza(this.zzb, this.zzc));
                this.zzd.zzak();
                this.zza.notify();
            } catch (RemoteException e) {
                this.zzd.zzr().zzf().zza("Failed to get all user properties; remote exception", e);
                this.zza.notify();
            } catch (Throwable th) {
                this.zza.notify();
                throw th;
            }
        }
    }
}
