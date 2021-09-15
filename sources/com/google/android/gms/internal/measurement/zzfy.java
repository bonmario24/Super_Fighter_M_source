package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzfy extends zzfw {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzfy() {
        super();
    }

    /* access modifiers changed from: package-private */
    public final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzid.zzf(obj, j);
        if (list instanceof zzfx) {
            unmodifiableList = ((zzfx) list).zze();
        } else if (zza.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if (!(list instanceof zzgy) || !(list instanceof zzfn)) {
                unmodifiableList = Collections.unmodifiableList(list);
            } else if (((zzfn) list).zza()) {
                ((zzfn) list).zzb();
                return;
            } else {
                return;
            }
        }
        zzid.zza(obj, j, unmodifiableList);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        List<L> arrayList;
        List<L> zzc = zzc(obj, j);
        if (zzc.isEmpty()) {
            if (zzc instanceof zzfx) {
                arrayList = new zzfu(i);
            } else if (!(zzc instanceof zzgy) || !(zzc instanceof zzfn)) {
                arrayList = new ArrayList<>(i);
            } else {
                arrayList = ((zzfn) zzc).zza(i);
            }
            zzid.zza(obj, j, (Object) arrayList);
            return arrayList;
        } else if (zza.isAssignableFrom(zzc.getClass())) {
            ArrayList arrayList2 = new ArrayList(zzc.size() + i);
            arrayList2.addAll(zzc);
            zzid.zza(obj, j, (Object) arrayList2);
            return arrayList2;
        } else if (zzc instanceof zzhy) {
            zzfu zzfu = new zzfu(zzc.size() + i);
            zzfu.addAll((zzhy) zzc);
            zzid.zza(obj, j, (Object) zzfu);
            return zzfu;
        } else if (!(zzc instanceof zzgy) || !(zzc instanceof zzfn) || ((zzfn) zzc).zza()) {
            return zzc;
        } else {
            zzfn zza2 = ((zzfn) zzc).zza(zzc.size() + i);
            zzid.zza(obj, j, (Object) zza2);
            return zza2;
        }
    }

    /* access modifiers changed from: package-private */
    public final <E> void zza(Object obj, Object obj2, long j) {
        List zzc = zzc(obj2, j);
        List zza2 = zza(obj, j, zzc.size());
        int size = zza2.size();
        int size2 = zzc.size();
        if (size > 0 && size2 > 0) {
            zza2.addAll(zzc);
        }
        if (size <= 0) {
            zza2 = zzc;
        }
        zzid.zza(obj, j, (Object) zza2);
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzid.zzf(obj, j);
    }
}
