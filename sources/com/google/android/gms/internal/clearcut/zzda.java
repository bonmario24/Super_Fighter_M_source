package com.google.android.gms.internal.clearcut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzda extends zzcy {
    private static final Class<?> zzlv = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzda() {
        super();
    }

    private static <E> List<E> zzb(Object obj, long j) {
        return (List) zzfd.zzo(obj, j);
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzfd.zzo(obj, j);
        if (list instanceof zzcx) {
            unmodifiableList = ((zzcx) list).zzbu();
        } else if (!zzlv.isAssignableFrom(list.getClass())) {
            unmodifiableList = Collections.unmodifiableList(list);
        } else {
            return;
        }
        zzfd.zza(obj, j, unmodifiableList);
    }

    /* access modifiers changed from: package-private */
    public final <E> void zza(Object obj, Object obj2, long j) {
        List zzb = zzb(obj2, j);
        int size = zzb.size();
        List zzb2 = zzb(obj, j);
        if (zzb2.isEmpty()) {
            zzb2 = zzb2 instanceof zzcx ? new zzcw(size) : new ArrayList(size);
            zzfd.zza(obj, j, (Object) zzb2);
        } else if (zzlv.isAssignableFrom(zzb2.getClass())) {
            ArrayList arrayList = new ArrayList(size + zzb2.size());
            arrayList.addAll(zzb2);
            zzfd.zza(obj, j, (Object) arrayList);
            zzb2 = arrayList;
        } else if (zzb2 instanceof zzfa) {
            zzcw zzcw = new zzcw(size + zzb2.size());
            zzcw.addAll((zzfa) zzb2);
            zzfd.zza(obj, j, (Object) zzcw);
            zzb2 = zzcw;
        }
        int size2 = zzb2.size();
        int size3 = zzb.size();
        if (size2 > 0 && size3 > 0) {
            zzb2.addAll(zzb);
        }
        if (size2 <= 0) {
            zzb2 = zzb;
        }
        zzfd.zza(obj, j, (Object) zzb2);
    }
}
