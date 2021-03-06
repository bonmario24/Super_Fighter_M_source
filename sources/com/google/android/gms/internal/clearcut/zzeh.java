package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzeh {
    private static final Class<?> zzoh = zzdp();
    private static final zzex<?, ?> zzoi = zzd(false);
    private static final zzex<?, ?> zzoj = zzd(true);
    private static final zzex<?, ?> zzok = new zzez();

    static int zza(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdc) {
            zzdc zzdc = (zzdc) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzbn.zze(zzdc.getLong(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzbn.zze(list.get(i4).longValue());
        }
        return i3;
    }

    private static <UT, UB> UB zza(int i, int i2, UB ub, zzex<UT, UB> zzex) {
        if (ub == null) {
            ub = zzex.zzdz();
        }
        zzex.zza(ub, i, (long) i2);
        return ub;
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzck<?> zzck, UB ub, zzex<UT, UB> zzex) {
        UB ub2;
        int i2;
        if (zzck == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i3 = 0;
            int i4 = 0;
            ub2 = ub;
            while (i3 < size) {
                int intValue = list.get(i3).intValue();
                if (zzck.zzb(intValue) != null) {
                    if (i3 != i4) {
                        list.set(i4, Integer.valueOf(intValue));
                    }
                    i2 = i4 + 1;
                } else {
                    ub2 = zza(i, intValue, ub2, zzex);
                    i2 = i4;
                }
                i3++;
                i4 = i2;
            }
            if (i4 != size) {
                list.subList(i4, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (zzck.zzb(intValue2) == null) {
                    ub = zza(i, intValue2, ub, zzex);
                    it.remove();
                }
            }
            ub2 = ub;
        }
        return ub2;
    }

    public static void zza(int i, List<String> list, zzfr zzfr) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zza(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzfr zzfr, zzef zzef) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zza(i, list, zzef);
        }
    }

    public static void zza(int i, List<Double> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzg(i, list, z);
        }
    }

    static <T, FT extends zzca<FT>> void zza(zzbu<FT> zzbu, T t, T t2) {
        zzby<FT> zza = zzbu.zza((Object) t2);
        if (!zza.isEmpty()) {
            zzbu.zzb(t).zza(zza);
        }
    }

    static <T> void zza(zzdj zzdj, T t, T t2, long j) {
        zzfd.zza((Object) t, j, zzdj.zzb(zzfd.zzo(t, j), zzfd.zzo(t2, j)));
    }

    static <T, UT, UB> void zza(zzex<UT, UB> zzex, T t, T t2) {
        zzex.zze(t, zzex.zzg(zzex.zzq(t), zzex.zzq(t2)));
    }

    static int zzb(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdc) {
            zzdc zzdc = (zzdc) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzbn.zzf(zzdc.getLong(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzbn.zzf(list.get(i4).longValue());
        }
        return i3;
    }

    public static void zzb(int i, List<zzbb> list, zzfr zzfr) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzb(i, list);
        }
    }

    public static void zzb(int i, List<?> list, zzfr zzfr, zzef zzef) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzb(i, list, zzef);
        }
    }

    public static void zzb(int i, List<Float> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzf(i, list, z);
        }
    }

    static int zzc(int i, Object obj, zzef zzef) {
        return obj instanceof zzcv ? zzbn.zza(i, (zzcv) obj) : zzbn.zzb(i, (zzdo) obj, zzef);
    }

    static int zzc(int i, List<?> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = zzbn.zzr(i) * size;
        if (list instanceof zzcx) {
            zzcx zzcx = (zzcx) list;
            int i2 = 0;
            while (i2 < size) {
                Object raw = zzcx.getRaw(i2);
                i2++;
                zzr = (raw instanceof zzbb ? zzbn.zzb((zzbb) raw) : zzbn.zzh((String) raw)) + zzr;
            }
            return zzr;
        }
        int i3 = 0;
        while (i3 < size) {
            Object obj = list.get(i3);
            i3++;
            zzr = (obj instanceof zzbb ? zzbn.zzb((zzbb) obj) : zzbn.zzh((String) obj)) + zzr;
        }
        return zzr;
    }

    static int zzc(int i, List<?> list, zzef zzef) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = zzbn.zzr(i) * size;
        int i2 = 0;
        while (i2 < size) {
            Object obj = list.get(i2);
            i2++;
            zzr = (obj instanceof zzcv ? zzbn.zza((zzcv) obj) : zzbn.zzb((zzdo) obj, zzef)) + zzr;
        }
        return zzr;
    }

    static int zzc(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdc) {
            zzdc zzdc = (zzdc) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzbn.zzg(zzdc.getLong(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzbn.zzg(list.get(i4).longValue());
        }
        return i3;
    }

    public static void zzc(int i, List<Long> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzc(i, list, z);
        }
    }

    public static boolean zzc(int i, int i2, int i3) {
        return i2 < 40 || ((((long) i2) - ((long) i)) + 1) + 9 <= ((2 * ((long) i3)) + 3) + ((((long) i3) + 3) * 3);
    }

    static int zzd(int i, List<zzbb> list) {
        int i2 = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = zzbn.zzr(i) * size;
        while (true) {
            int i3 = i2;
            if (i3 >= list.size()) {
                return zzr;
            }
            zzr += zzbn.zzb(list.get(i3));
            i2 = i3 + 1;
        }
    }

    static int zzd(int i, List<zzdo> list, zzef zzef) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzbn.zzc(i, list.get(i3), zzef);
        }
        return i2;
    }

    static int zzd(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzch) {
            zzch zzch = (zzch) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzbn.zzx(zzch.getInt(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzbn.zzx(list.get(i4).intValue());
        }
        return i3;
    }

    private static zzex<?, ?> zzd(boolean z) {
        try {
            Class<?> zzdq = zzdq();
            if (zzdq == null) {
                return null;
            }
            return (zzex) zzdq.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable th) {
            return null;
        }
    }

    public static void zzd(int i, List<Long> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzd(i, list, z);
        }
    }

    static boolean zzd(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static zzex<?, ?> zzdm() {
        return zzoi;
    }

    public static zzex<?, ?> zzdn() {
        return zzoj;
    }

    public static zzex<?, ?> zzdo() {
        return zzok;
    }

    private static Class<?> zzdp() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzdq() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            return null;
        }
    }

    static int zze(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzch) {
            zzch zzch = (zzch) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzbn.zzs(zzch.getInt(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzbn.zzs(list.get(i4).intValue());
        }
        return i3;
    }

    public static void zze(int i, List<Long> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzn(i, list, z);
        }
    }

    static int zzf(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzch) {
            zzch zzch = (zzch) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzbn.zzt(zzch.getInt(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzbn.zzt(list.get(i4).intValue());
        }
        return i3;
    }

    public static void zzf(int i, List<Long> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zze(i, list, z);
        }
    }

    public static void zzf(Class<?> cls) {
        if (!zzcg.class.isAssignableFrom(cls) && zzoh != null && !zzoh.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    static int zzg(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzch) {
            zzch zzch = (zzch) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzbn.zzu(zzch.getInt(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzbn.zzu(list.get(i4).intValue());
        }
        return i3;
    }

    public static void zzg(int i, List<Long> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzl(i, list, z);
        }
    }

    static int zzh(List<?> list) {
        return list.size() << 2;
    }

    public static void zzh(int i, List<Integer> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zza(i, list, z);
        }
    }

    static int zzi(List<?> list) {
        return list.size() << 3;
    }

    public static void zzi(int i, List<Integer> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzj(i, list, z);
        }
    }

    static int zzj(List<?> list) {
        return list.size();
    }

    public static void zzj(int i, List<Integer> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzfr zzfr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfr.zzi(i, list, z);
        }
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zza(list) + (list.size() * zzbn.zzr(i));
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbn.zzr(i)) + zzb(list);
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbn.zzr(i)) + zzc(list);
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbn.zzr(i)) + zzd(list);
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbn.zzr(i)) + zze(list);
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbn.zzr(i)) + zzf(list);
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzbn.zzr(i)) + zzg(list);
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzbn.zzj(i, 0) * size;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbn.zzg(i, 0);
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbn.zzc(i, true);
    }
}
