package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzhh {
    private static final Class<?> zza = zzd();
    private static final zzhx<?, ?> zzb = zza(false);
    private static final zzhx<?, ?> zzc = zza(true);
    private static final zzhx<?, ?> zzd = new zzhz();

    public static void zza(Class<?> cls) {
        if (!zzfe.class.isAssignableFrom(cls) && zza != null && !zza.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zziq zziq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zziq zziq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zziq zziq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zziq zziq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zziq zziq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zziq zziq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zziq zziq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zziq zziq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zziq zziq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zziq zziq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zziq zziq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zziq zziq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zziq zziq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zziq zziq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zziq zziq) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zza(i, list);
        }
    }

    public static void zzb(int i, List<zzdw> list, zziq zziq) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zzb(i, list);
        }
    }

    public static void zza(int i, List<?> list, zziq zziq, zzhf zzhf) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zza(i, list, zzhf);
        }
    }

    public static void zzb(int i, List<?> list, zziq zziq, zzhf zzhf) throws IOException {
        if (list != null && !list.isEmpty()) {
            zziq.zzb(i, list, zzhf);
        }
    }

    static int zza(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzga) {
            zzga zzga = (zzga) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzel.zzd(zzga.zzb(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzel.zzd(list.get(i4).longValue());
        }
        return i3;
    }

    static int zza(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zza(list) + (list.size() * zzel.zze(i));
    }

    static int zzb(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzga) {
            zzga zzga = (zzga) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzel.zze(zzga.zzb(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzel.zze(list.get(i4).longValue());
        }
        return i3;
    }

    static int zzb(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzel.zze(i)) + zzb(list);
    }

    static int zzc(List<Long> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzga) {
            zzga zzga = (zzga) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzel.zzf(zzga.zzb(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzel.zzf(list.get(i4).longValue());
        }
        return i3;
    }

    static int zzc(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzel.zze(i)) + zzc(list);
    }

    static int zzd(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzff) {
            zzff zzff = (zzff) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzel.zzk(zzff.zzc(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzel.zzk(list.get(i4).intValue());
        }
        return i3;
    }

    static int zzd(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzel.zze(i)) + zzd(list);
    }

    static int zze(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzff) {
            zzff zzff = (zzff) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzel.zzf(zzff.zzc(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzel.zzf(list.get(i4).intValue());
        }
        return i3;
    }

    static int zze(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzel.zze(i)) + zze(list);
    }

    static int zzf(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzff) {
            zzff zzff = (zzff) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzel.zzg(zzff.zzc(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzel.zzg(list.get(i4).intValue());
        }
        return i3;
    }

    static int zzf(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzel.zze(i)) + zzf(list);
    }

    static int zzg(List<Integer> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzff) {
            zzff zzff = (zzff) list;
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += zzel.zzh(zzff.zzc(i2));
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzel.zzh(list.get(i4).intValue());
        }
        return i3;
    }

    static int zzg(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (size * zzel.zze(i)) + zzg(list);
    }

    static int zzh(List<?> list) {
        return list.size() << 2;
    }

    static int zzh(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzel.zzi(i, 0) * size;
    }

    static int zzi(List<?> list) {
        return list.size() << 3;
    }

    static int zzi(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzel.zzg(i, 0);
    }

    static int zzj(List<?> list) {
        return list.size();
    }

    static int zzj(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzel.zzb(i, true);
    }

    static int zza(int i, List<?> list) {
        int zzb2;
        int zzb3;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zze = zzel.zze(i) * size;
        if (list instanceof zzfx) {
            zzfx zzfx = (zzfx) list;
            int i2 = 0;
            while (i2 < size) {
                Object zzb4 = zzfx.zzb(i2);
                if (zzb4 instanceof zzdw) {
                    zzb3 = zzel.zzb((zzdw) zzb4);
                } else {
                    zzb3 = zzel.zzb((String) zzb4);
                }
                i2++;
                zze = zzb3 + zze;
            }
            return zze;
        }
        int i3 = 0;
        while (i3 < size) {
            Object obj = list.get(i3);
            if (obj instanceof zzdw) {
                zzb2 = zzel.zzb((zzdw) obj);
            } else {
                zzb2 = zzel.zzb((String) obj);
            }
            i3++;
            zze = zzb2 + zze;
        }
        return zze;
    }

    static int zza(int i, Object obj, zzhf zzhf) {
        if (obj instanceof zzfv) {
            return zzel.zza(i, (zzfv) obj);
        }
        return zzel.zzb(i, (zzgm) obj, zzhf);
    }

    static int zza(int i, List<?> list, zzhf zzhf) {
        int zza2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zze = zzel.zze(i) * size;
        int i2 = 0;
        while (i2 < size) {
            Object obj = list.get(i2);
            if (obj instanceof zzfv) {
                zza2 = zzel.zza((zzfv) obj);
            } else {
                zza2 = zzel.zza((zzgm) obj, zzhf);
            }
            i2++;
            zze = zza2 + zze;
        }
        return zze;
    }

    static int zzb(int i, List<zzdw> list) {
        int i2 = 0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zze = zzel.zze(i) * size;
        while (true) {
            int i3 = i2;
            if (i3 >= list.size()) {
                return zze;
            }
            zze += zzel.zzb(list.get(i3));
            i2 = i3 + 1;
        }
    }

    static int zzb(int i, List<zzgm> list, zzhf zzhf) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzel.zzc(i, list.get(i3), zzhf);
        }
        return i2;
    }

    public static zzhx<?, ?> zza() {
        return zzb;
    }

    public static zzhx<?, ?> zzb() {
        return zzc;
    }

    public static zzhx<?, ?> zzc() {
        return zzd;
    }

    private static zzhx<?, ?> zza(boolean z) {
        try {
            Class<?> zze = zze();
            if (zze == null) {
                return null;
            }
            return (zzhx) zze.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
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

    static <T> void zza(zzgj zzgj, T t, T t2, long j) {
        zzid.zza((Object) t, j, zzgj.zza(zzid.zzf(t, j), zzid.zzf(t2, j)));
    }

    static <T, FT extends zzew<FT>> void zza(zzet<FT> zzet, T t, T t2) {
        zzeu<FT> zza2 = zzet.zza((Object) t2);
        if (!zza2.zza.isEmpty()) {
            zzet.zzb(t).zza(zza2);
        }
    }

    static <T, UT, UB> void zza(zzhx<UT, UB> zzhx, T t, T t2) {
        zzhx.zza((Object) t, zzhx.zzc(zzhx.zzb(t), zzhx.zzb(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzfi zzfi, UB ub, zzhx<UT, UB> zzhx) {
        UB ub2;
        int i2;
        if (zzfi == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i3 = 0;
            int i4 = 0;
            ub2 = ub;
            while (i3 < size) {
                int intValue = list.get(i3).intValue();
                if (zzfi.zza(intValue)) {
                    if (i3 != i4) {
                        list.set(i4, Integer.valueOf(intValue));
                    }
                    i2 = i4 + 1;
                } else {
                    ub2 = zza(i, intValue, ub2, zzhx);
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
                if (!zzfi.zza(intValue2)) {
                    ub = zza(i, intValue2, ub, zzhx);
                    it.remove();
                }
            }
            ub2 = ub;
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzhx<UT, UB> zzhx) {
        if (ub == null) {
            ub = zzhx.zza();
        }
        zzhx.zza(ub, i, (long) i2);
        return ub;
    }
}
