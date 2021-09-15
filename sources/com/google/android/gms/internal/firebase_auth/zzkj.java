package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
final class zzkj {
    private static final Class<?> zza = zzd();
    private static final zzkz<?, ?> zzb = zza(false);
    private static final zzkz<?, ?> zzc = zza(true);
    private static final zzkz<?, ?> zzd = new zzlb();

    public static void zza(Class<?> cls) {
        if (!zzig.class.isAssignableFrom(cls) && zza != null && !zza.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzlw zzlw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzlw zzlw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzlw zzlw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzlw zzlw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzlw zzlw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzlw zzlw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzlw zzlw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzlw zzlw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzlw zzlw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzlw zzlw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzlw zzlw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzlw zzlw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzlw zzlw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzlw zzlw, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzlw zzlw) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zza(i, list);
        }
    }

    public static void zzb(int i, List<zzgv> list, zzlw zzlw) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zzb(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzlw zzlw, zzkh zzkh) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zza(i, list, zzkh);
        }
    }

    public static void zzb(int i, List<?> list, zzlw zzlw, zzkh zzkh) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlw.zzb(i, list, zzkh);
        }
    }

    static int zza(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjf) {
            zzjf zzjf = (zzjf) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzhq.zzd(zzjf.zzb(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzhq.zzd(list.get(i4).longValue());
        }
        return i3;
    }

    static int zza(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zza(list) + (list.size() * zzhq.zze(i));
    }

    static int zzb(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjf) {
            zzjf zzjf = (zzjf) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzhq.zze(zzjf.zzb(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzhq.zze(list.get(i4).longValue());
        }
        return i3;
    }

    static int zzb(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzhq.zze(i)) + zzb(list);
    }

    static int zzc(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjf) {
            zzjf zzjf = (zzjf) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzhq.zzf(zzjf.zzb(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzhq.zzf(list.get(i4).longValue());
        }
        return i3;
    }

    static int zzc(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzhq.zze(i)) + zzc(list);
    }

    static int zzd(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzij) {
            zzij zzij = (zzij) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzhq.zzk(zzij.zzc(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzhq.zzk(list.get(i4).intValue());
        }
        return i3;
    }

    static int zzd(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzhq.zze(i)) + zzd(list);
    }

    static int zze(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzij) {
            zzij zzij = (zzij) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzhq.zzf(zzij.zzc(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzhq.zzf(list.get(i4).intValue());
        }
        return i3;
    }

    static int zze(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzhq.zze(i)) + zze(list);
    }

    static int zzf(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzij) {
            zzij zzij = (zzij) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzhq.zzg(zzij.zzc(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzhq.zzg(list.get(i4).intValue());
        }
        return i3;
    }

    static int zzf(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzhq.zze(i)) + zzf(list);
    }

    static int zzg(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzij) {
            zzij zzij = (zzij) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzhq.zzh(zzij.zzc(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzhq.zzh(list.get(i4).intValue());
        }
        return i3;
    }

    static int zzg(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzhq.zze(i)) + zzg(list);
    }

    static int zzh(List<?> list) {
        return list.size() << 2;
    }

    static int zzh(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzhq.zzi(i, 0) * size;
    }

    static int zzi(List<?> list) {
        return list.size() << 3;
    }

    static int zzi(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzhq.zzg(i, 0);
    }

    static int zzj(List<?> list) {
        return list.size();
    }

    static int zzj(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzhq.zzb(i, true);
    }

    static int zza(int i, List<?> list) {
        int zzb2;
        int zzb3;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zze = zzhq.zze(i) * size;
        if (list instanceof zziy) {
            zziy zziy = (zziy) list;
            int i2 = 0;
            while (i2 < size) {
                Object zzb4 = zziy.zzb(i2);
                if (zzb4 instanceof zzgv) {
                    zzb3 = zzhq.zzb((zzgv) zzb4);
                } else {
                    zzb3 = zzhq.zzb((String) zzb4);
                }
                i2++;
                zze = zzb3 + zze;
            }
            return zze;
        }
        int i3 = 0;
        while (i3 < size) {
            Object obj = list.get(i3);
            if (obj instanceof zzgv) {
                zzb2 = zzhq.zzb((zzgv) obj);
            } else {
                zzb2 = zzhq.zzb((String) obj);
            }
            i3++;
            zze = zzb2 + zze;
        }
        return zze;
    }

    static int zza(int i, Object obj, zzkh zzkh) {
        if (obj instanceof zziw) {
            return zzhq.zza(i, (zziw) obj);
        }
        return zzhq.zzb(i, (zzjr) obj, zzkh);
    }

    static int zza(int i, List<?> list, zzkh zzkh) {
        int zza2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zze = zzhq.zze(i) * size;
        int i2 = 0;
        while (i2 < size) {
            Object obj = list.get(i2);
            if (obj instanceof zziw) {
                zza2 = zzhq.zza((zziw) obj);
            } else {
                zza2 = zzhq.zza((zzjr) obj, zzkh);
            }
            i2++;
            zze = zza2 + zze;
        }
        return zze;
    }

    static int zzb(int i, List<zzgv> list) {
        int i2 = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zze = zzhq.zze(i) * size;
        while (true) {
            int i3 = i2;
            if (i3 >= list.size()) {
                return zze;
            }
            zze += zzhq.zzb(list.get(i3));
            i2 = i3 + 1;
        }
    }

    static int zzb(int i, List<zzjr> list, zzkh zzkh) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzhq.zzc(i, list.get(i3), zzkh);
        }
        return i2;
    }

    public static zzkz<?, ?> zza() {
        return zzb;
    }

    public static zzkz<?, ?> zzb() {
        return zzc;
    }

    public static zzkz<?, ?> zzc() {
        return zzd;
    }

    private static zzkz<?, ?> zza(boolean z) {
        try {
            Class<?> zze = zze();
            if (zze == null) {
                return null;
            }
            return (zzkz) zze.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzd() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zze() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            return null;
        }
    }

    static boolean zza(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static <T> void zza(zzjk zzjk, T t, T t2, long j) {
        zzlf.zza((Object) t, j, zzjk.zza(zzlf.zzf(t, j), zzlf.zzf(t2, j)));
    }

    static <T, FT extends zzib<FT>> void zza(zzhv<FT> zzhv, T t, T t2) {
        zzhz<FT> zza2 = zzhv.zza((Object) t2);
        if (!zza2.zza.isEmpty()) {
            zzhv.zzb(t).zza(zza2);
        }
    }

    static <T, UT, UB> void zza(zzkz<UT, UB> zzkz, T t, T t2) {
        zzkz.zza((Object) t, zzkz.zzc(zzkz.zzb(t), zzkz.zzb(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzin zzin, UB ub, zzkz<UT, UB> zzkz) {
        UB ub2;
        int i2;
        if (zzin == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i3 = 0;
            int i4 = 0;
            ub2 = ub;
            while (i3 < size) {
                int intValue = list.get(i3).intValue();
                if (zzin.zza(intValue)) {
                    if (i3 != i4) {
                        list.set(i4, Integer.valueOf(intValue));
                    }
                    i2 = i4 + 1;
                } else {
                    ub2 = zza(i, intValue, ub2, zzkz);
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
                if (!zzin.zza(intValue2)) {
                    ub = zza(i, intValue2, ub, zzkz);
                    it.remove();
                }
            }
            ub2 = ub;
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzkz<UT, UB> zzkz) {
        if (ub == null) {
            ub = zzkz.zza();
        }
        zzkz.zza(ub, i, (long) i2);
        return ub;
    }
}
