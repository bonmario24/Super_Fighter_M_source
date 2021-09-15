package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzgi implements zzgj {
    zzgi() {
    }

    public final Map<?, ?> zza(Object obj) {
        return (zzgg) obj;
    }

    public final zzgh<?, ?> zzb(Object obj) {
        zzge zzge = (zzge) obj;
        throw new NoSuchMethodError();
    }

    public final Map<?, ?> zzc(Object obj) {
        return (zzgg) obj;
    }

    public final boolean zzd(Object obj) {
        return !((zzgg) obj).zzd();
    }

    public final Object zze(Object obj) {
        ((zzgg) obj).zzc();
        return obj;
    }

    public final Object zzf(Object obj) {
        return zzgg.zza().zzb();
    }

    public final Object zza(Object obj, Object obj2) {
        zzgg zzgg = (zzgg) obj;
        zzgg zzgg2 = (zzgg) obj2;
        if (!zzgg2.isEmpty()) {
            if (!zzgg.zzd()) {
                zzgg = zzgg.zzb();
            }
            zzgg.zza(zzgg2);
        }
        return zzgg;
    }

    public final int zza(int i, Object obj, Object obj2) {
        zzgg zzgg = (zzgg) obj;
        zzge zzge = (zzge) obj2;
        if (!zzgg.isEmpty()) {
            Iterator it = zzgg.entrySet().iterator();
            if (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                entry.getKey();
                entry.getValue();
                throw new NoSuchMethodError();
            }
        }
        return 0;
    }
}
