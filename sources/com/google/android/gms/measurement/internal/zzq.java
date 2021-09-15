package com.google.android.gms.measurement.internal;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzfe;
import com.google.android.gms.internal.measurement.zzjy;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzq {
    private String zza;
    private boolean zzb;
    private zzbs.zzi zzc;
    /* access modifiers changed from: private */
    public BitSet zzd;
    private BitSet zze;
    private Map<Integer, Long> zzf;
    private Map<Integer, List<Long>> zzg;
    private final /* synthetic */ zzo zzh;

    private zzq(zzo zzo, String str) {
        this.zzh = zzo;
        this.zza = str;
        this.zzb = true;
        this.zzd = new BitSet();
        this.zze = new BitSet();
        this.zzf = new ArrayMap();
        this.zzg = new ArrayMap();
    }

    private zzq(zzo zzo, String str, zzbs.zzi zzi, BitSet bitSet, BitSet bitSet2, Map<Integer, Long> map, Map<Integer, Long> map2) {
        this.zzh = zzo;
        this.zza = str;
        this.zzd = bitSet;
        this.zze = bitSet2;
        this.zzf = map;
        this.zzg = new ArrayMap();
        if (map2 != null) {
            for (Integer next : map2.keySet()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(map2.get(next));
                this.zzg.put(next, arrayList);
            }
        }
        this.zzb = false;
        this.zzc = zzi;
    }

    /* access modifiers changed from: package-private */
    public final void zza(@NonNull zzv zzv) {
        int zza2 = zzv.zza();
        if (zzv.zzc != null) {
            this.zze.set(zza2, zzv.zzc.booleanValue());
        }
        if (zzv.zzd != null) {
            this.zzd.set(zza2, zzv.zzd.booleanValue());
        }
        if (zzv.zze != null) {
            Long l = this.zzf.get(Integer.valueOf(zza2));
            long longValue = zzv.zze.longValue() / 1000;
            if (l == null || longValue > l.longValue()) {
                this.zzf.put(Integer.valueOf(zza2), Long.valueOf(longValue));
            }
        }
        if (zzv.zzf != null) {
            List list = this.zzg.get(Integer.valueOf(zza2));
            if (list == null) {
                list = new ArrayList();
                this.zzg.put(Integer.valueOf(zza2), list);
            }
            if (zzv.zzb()) {
                list.clear();
            }
            if (zzjy.zzb() && this.zzh.zzt().zzd(this.zza, zzaq.zzbg) && zzv.zzc()) {
                list.clear();
            }
            if (!zzjy.zzb() || !this.zzh.zzt().zzd(this.zza, zzaq.zzbg)) {
                list.add(Long.valueOf(zzv.zzf.longValue() / 1000));
                return;
            }
            long longValue2 = zzv.zzf.longValue() / 1000;
            if (!list.contains(Long.valueOf(longValue2))) {
                list.add(Long.valueOf(longValue2));
            }
        }
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public final zzbs.zza zza(int i) {
        ArrayList arrayList;
        List list;
        zzbs.zza.C1800zza zzh2 = zzbs.zza.zzh();
        zzh2.zza(i);
        zzh2.zza(this.zzb);
        if (this.zzc != null) {
            zzh2.zza(this.zzc);
        }
        zzbs.zzi.zza zza2 = zzbs.zzi.zzi().zzb((Iterable<? extends Long>) zzki.zza(this.zzd)).zza((Iterable<? extends Long>) zzki.zza(this.zze));
        if (this.zzf == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(this.zzf.size());
            for (Integer intValue : this.zzf.keySet()) {
                int intValue2 = intValue.intValue();
                arrayList2.add((zzbs.zzb) ((zzfe) zzbs.zzb.zze().zza(intValue2).zza(this.zzf.get(Integer.valueOf(intValue2)).longValue()).zzv()));
            }
            arrayList = arrayList2;
        }
        zza2.zzc(arrayList);
        if (this.zzg == null) {
            list = Collections.emptyList();
        } else {
            ArrayList arrayList3 = new ArrayList(this.zzg.size());
            for (Integer next : this.zzg.keySet()) {
                zzbs.zzj.zza zza3 = zzbs.zzj.zze().zza(next.intValue());
                List list2 = this.zzg.get(next);
                if (list2 != null) {
                    Collections.sort(list2);
                    zza3.zza((Iterable<? extends Long>) list2);
                }
                arrayList3.add((zzbs.zzj) ((zzfe) zza3.zzv()));
            }
            list = arrayList3;
        }
        zza2.zzd(list);
        zzh2.zza(zza2);
        return (zzbs.zza) ((zzfe) zzh2.zzv());
    }

    /* synthetic */ zzq(zzo zzo, String str, zzbs.zzi zzi, BitSet bitSet, BitSet bitSet2, Map map, Map map2, zzr zzr) {
        this(zzo, str, zzi, bitSet, bitSet2, map, map2);
    }

    /* synthetic */ zzq(zzo zzo, String str, zzr zzr) {
        this(zzo, str);
    }
}
