package com.google.android.gms.internal.firebase_auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
final class zzjd extends zzjb {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzjd() {
        super();
    }

    /* access modifiers changed from: package-private */
    public final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzlf.zzf(obj, j);
        if (list instanceof zziy) {
            unmodifiableList = ((zziy) list).mo22762b_();
        } else if (zza.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if (!(list instanceof zzka) || !(list instanceof zzio)) {
                unmodifiableList = Collections.unmodifiableList(list);
            } else if (((zzio) list).zza()) {
                ((zzio) list).mo22488c_();
                return;
            } else {
                return;
            }
        }
        zzlf.zza(obj, j, unmodifiableList);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        List<L> arrayList;
        List<L> zzc = zzc(obj, j);
        if (zzc.isEmpty()) {
            if (zzc instanceof zziy) {
                arrayList = new zziz(i);
            } else if (!(zzc instanceof zzka) || !(zzc instanceof zzio)) {
                arrayList = new ArrayList<>(i);
            } else {
                arrayList = ((zzio) zzc).zza(i);
            }
            zzlf.zza(obj, j, (Object) arrayList);
            return arrayList;
        } else if (zza.isAssignableFrom(zzc.getClass())) {
            ArrayList arrayList2 = new ArrayList(zzc.size() + i);
            arrayList2.addAll(zzc);
            zzlf.zza(obj, j, (Object) arrayList2);
            return arrayList2;
        } else if (zzc instanceof zzle) {
            zziz zziz = new zziz(zzc.size() + i);
            zziz.addAll((zzle) zzc);
            zzlf.zza(obj, j, (Object) zziz);
            return zziz;
        } else if (!(zzc instanceof zzka) || !(zzc instanceof zzio) || ((zzio) zzc).zza()) {
            return zzc;
        } else {
            zzio zza2 = ((zzio) zzc).zza(zzc.size() + i);
            zzlf.zza(obj, j, (Object) zza2);
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
        zzlf.zza(obj, j, (Object) zza2);
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzlf.zzf(obj, j);
    }
}
