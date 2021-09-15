package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
abstract class zzfw {
    private static final zzfw zza = new zzfy();
    private static final zzfw zzb = new zzgb();

    private zzfw() {
    }

    /* access modifiers changed from: package-private */
    public abstract <L> List<L> zza(Object obj, long j);

    /* access modifiers changed from: package-private */
    public abstract <L> void zza(Object obj, Object obj2, long j);

    /* access modifiers changed from: package-private */
    public abstract void zzb(Object obj, long j);

    static zzfw zza() {
        return zza;
    }

    static zzfw zzb() {
        return zzb;
    }
}
