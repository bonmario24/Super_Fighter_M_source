package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzhp implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zzhb zze;

    zzhp(zzhb zzhb, AtomicReference atomicReference, String str, String str2, String str3) {
        this.zze = zzhb;
        this.zza = atomicReference;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
    }

    public final void run() {
        this.zze.zzz.zzw().zza((AtomicReference<List<zzw>>) this.zza, this.zzb, this.zzc, this.zzd);
    }
}
