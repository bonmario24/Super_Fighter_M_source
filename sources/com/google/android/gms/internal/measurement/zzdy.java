package com.google.android.gms.internal.measurement;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzdy implements Comparator<zzdw> {
    zzdy() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzdw zzdw = (zzdw) obj;
        zzdw zzdw2 = (zzdw) obj2;
        zzeb zzeb = (zzeb) zzdw.iterator();
        zzeb zzeb2 = (zzeb) zzdw2.iterator();
        while (zzeb.hasNext() && zzeb2.hasNext()) {
            int compare = Integer.compare(zzdw.zzb(zzeb.zza()), zzdw.zzb(zzeb2.zza()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzdw.zza(), zzdw2.zza());
    }
}
