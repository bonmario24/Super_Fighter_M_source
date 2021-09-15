package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzgb extends zzfw {
    private zzgb() {
        super();
    }

    /* access modifiers changed from: package-private */
    public final <L> List<L> zza(Object obj, long j) {
        zzfn zzc = zzc(obj, j);
        if (zzc.zza()) {
            return zzc;
        }
        int size = zzc.size();
        zzfn zza = zzc.zza(size == 0 ? 10 : size << 1);
        zzid.zza(obj, j, (Object) zza);
        return zza;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        zzc(obj, j).zzb();
    }

    /* access modifiers changed from: package-private */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzfn zzc = zzc(obj, j);
        zzfn zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzc.zza()) {
                zzc = zzc.zza(size2 + size);
            }
            zzc.addAll(zzc2);
        }
        if (size <= 0) {
            zzc = zzc2;
        }
        zzid.zza(obj, j, (Object) zzc);
    }

    private static <E> zzfn<E> zzc(Object obj, long j) {
        return (zzfn) zzid.zzf(obj, j);
    }
}
