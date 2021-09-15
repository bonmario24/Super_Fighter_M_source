package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final /* synthetic */ class zzhd implements Runnable {
    private final zzhb zza;

    zzhd(zzhb zzhb) {
        this.zza = zzhb;
    }

    public final void run() {
        zzhb zzhb = this.zza;
        zzhb.zzd();
        if (zzhb.zzs().zzt.zza()) {
            zzhb.zzr().zzw().zza("Deferred Deep Link already retrieved. Not fetching again.");
            return;
        }
        long zza2 = zzhb.zzs().zzu.zza();
        zzhb.zzs().zzu.zza(1 + zza2);
        if (zza2 >= 5) {
            zzhb.zzr().zzi().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
            zzhb.zzs().zzt.zza(true);
            return;
        }
        zzhb.zzz.zzah();
    }
}
