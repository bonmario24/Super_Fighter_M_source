package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzke implements Callable<String> {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzka zzb;

    zzke(zzka zzka, zzn zzn) {
        this.zzb = zzka;
        this.zza = zzn;
    }

    public final /* synthetic */ Object call() throws Exception {
        zzf zzc = this.zzb.zzc(this.zza);
        if (zzc != null) {
            return zzc.zzd();
        }
        this.zzb.zzr().zzi().zza("App info was null when attempting to get app instance id");
        return null;
    }
}
