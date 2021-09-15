package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzgn implements Callable<List<zzkj>> {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzgb zzb;

    zzgn(zzgb zzgb, zzn zzn) {
        this.zzb = zzgb;
        this.zza = zzn;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzb.zza.zzo();
        return this.zzb.zza.zze().zza(this.zza.zza);
    }
}
