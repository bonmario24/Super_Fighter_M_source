package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzew;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzeu<T extends zzew<T>> {
    private static final zzeu zzd = new zzeu(true);
    final zzhg<T, Object> zza;
    private boolean zzb;
    private boolean zzc;

    private zzeu() {
        this.zza = zzhg.zza(16);
    }

    private zzeu(boolean z) {
        this(zzhg.zza(0));
        zzb();
    }

    private zzeu(zzhg<T, Object> zzhg) {
        this.zza = zzhg;
        zzb();
    }

    public static <T extends zzew<T>> zzeu<T> zza() {
        return zzd;
    }

    public final void zzb() {
        if (!this.zzb) {
            this.zza.zza();
            this.zzb = true;
        }
    }

    public final boolean zzc() {
        return this.zzb;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzeu)) {
            return false;
        }
        return this.zza.equals(((zzeu) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        if (this.zzc) {
            return new zzfs(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<T, Object>> zze() {
        if (this.zzc) {
            return new zzfs(this.zza.zze().iterator());
        }
        return this.zza.zze().iterator();
    }

    private final Object zza(T t) {
        Object obj = this.zza.get(t);
        if (!(obj instanceof zzfr)) {
            return obj;
        }
        zzfr zzfr = (zzfr) obj;
        return zzfr.zza();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.util.ArrayList} */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r7, java.lang.Object r8) {
        /*
            r6 = this;
            boolean r0 = r7.zzd()
            if (r0 == 0) goto L_0x0034
            boolean r0 = r8 instanceof java.util.List
            if (r0 != 0) goto L_0x0012
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Wrong object type used with protocol message reflection."
            r0.<init>(r1)
            throw r0
        L_0x0012:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.List r8 = (java.util.List) r8
            r1.addAll(r8)
            r0 = r1
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r3 = r0.size()
            r2 = 0
        L_0x0024:
            if (r2 >= r3) goto L_0x003c
            java.lang.Object r4 = r0.get(r2)
            int r2 = r2 + 1
            com.google.android.gms.internal.measurement.zzik r5 = r7.zzb()
            zza((com.google.android.gms.internal.measurement.zzik) r5, (java.lang.Object) r4)
            goto L_0x0024
        L_0x0034:
            com.google.android.gms.internal.measurement.zzik r0 = r7.zzb()
            zza((com.google.android.gms.internal.measurement.zzik) r0, (java.lang.Object) r8)
            r1 = r8
        L_0x003c:
            boolean r0 = r1 instanceof com.google.android.gms.internal.measurement.zzfr
            if (r0 == 0) goto L_0x0043
            r0 = 1
            r6.zzc = r0
        L_0x0043:
            com.google.android.gms.internal.measurement.zzhg<T, java.lang.Object> r0 = r6.zza
            r0.put(r7, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzeu.zzb(com.google.android.gms.internal.measurement.zzew, java.lang.Object):void");
    }

    private static void zza(zzik zzik, Object obj) {
        boolean z = false;
        zzfh.zza(obj);
        switch (zzex.zza[zzik.zza().ordinal()]) {
            case 1:
                z = obj instanceof Integer;
                break;
            case 2:
                z = obj instanceof Long;
                break;
            case 3:
                z = obj instanceof Float;
                break;
            case 4:
                z = obj instanceof Double;
                break;
            case 5:
                z = obj instanceof Boolean;
                break;
            case 6:
                z = obj instanceof String;
                break;
            case 7:
                if ((obj instanceof zzdw) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzfg)) {
                    z = true;
                    break;
                }
            case 9:
                if ((obj instanceof zzgm) || (obj instanceof zzfr)) {
                    z = true;
                    break;
                }
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public final boolean zzf() {
        for (int i = 0; i < this.zza.zzc(); i++) {
            if (!zza(this.zza.zzb(i))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> zza2 : this.zza.zzd()) {
            if (!zza(zza2)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends zzew<T>> boolean zza(Map.Entry<T, Object> entry) {
        zzew zzew = (zzew) entry.getKey();
        if (zzew.zzc() == zzir.MESSAGE) {
            if (zzew.zzd()) {
                for (zzgm g_ : (List) entry.getValue()) {
                    if (!g_.mo23725g_()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzgm) {
                    if (!((zzgm) value).mo23725g_()) {
                        return false;
                    }
                } else if (value instanceof zzfr) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzeu<T> zzeu) {
        for (int i = 0; i < zzeu.zza.zzc(); i++) {
            zzb(zzeu.zza.zzb(i));
        }
        for (Map.Entry<T, Object> zzb2 : zzeu.zza.zzd()) {
            zzb(zzb2);
        }
    }

    private static Object zza(Object obj) {
        if (obj instanceof zzgv) {
            return ((zzgv) obj).zza();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzb(Map.Entry<T, Object> entry) {
        Object zzv;
        zzew zzew = (zzew) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzfr) {
            zzfr zzfr = (zzfr) value;
            value = zzfr.zza();
        }
        if (zzew.zzd()) {
            Object zza2 = zza(zzew);
            if (zza2 == null) {
                zza2 = new ArrayList();
            }
            for (Object zza3 : (List) value) {
                ((List) zza2).add(zza(zza3));
            }
            this.zza.put(zzew, zza2);
        } else if (zzew.zzc() == zzir.MESSAGE) {
            Object zza4 = zza(zzew);
            if (zza4 == null) {
                this.zza.put(zzew, zza(value));
                return;
            }
            if (zza4 instanceof zzgv) {
                zzv = zzew.zza((zzgv) zza4, (zzgv) value);
            } else {
                zzv = zzew.zza(((zzgm) zza4).zzbq(), (zzgm) value).zzv();
            }
            this.zza.put(zzew, zzv);
        } else {
            this.zza.put(zzew, zza(value));
        }
    }

    static void zza(zzel zzel, zzik zzik, int i, Object obj) throws IOException {
        if (zzik == zzik.GROUP) {
            zzfh.zza((zzgm) obj);
            zzel.zza(i, 3);
            ((zzgm) obj).zza(zzel);
            zzel.zza(i, 4);
            return;
        }
        zzel.zza(i, zzik.zzb());
        switch (zzex.zzb[zzik.ordinal()]) {
            case 1:
                zzel.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzel.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzel.zza(((Long) obj).longValue());
                return;
            case 4:
                zzel.zza(((Long) obj).longValue());
                return;
            case 5:
                zzel.zza(((Integer) obj).intValue());
                return;
            case 6:
                zzel.zzc(((Long) obj).longValue());
                return;
            case 7:
                zzel.zzd(((Integer) obj).intValue());
                return;
            case 8:
                zzel.zza(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzgm) obj).zza(zzel);
                return;
            case 10:
                zzel.zza((zzgm) obj);
                return;
            case 11:
                if (obj instanceof zzdw) {
                    zzel.zza((zzdw) obj);
                    return;
                } else {
                    zzel.zza((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzdw) {
                    zzel.zza((zzdw) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzel.zzb(bArr, 0, bArr.length);
                return;
            case 13:
                zzel.zzb(((Integer) obj).intValue());
                return;
            case 14:
                zzel.zzd(((Integer) obj).intValue());
                return;
            case 15:
                zzel.zzc(((Long) obj).longValue());
                return;
            case 16:
                zzel.zzc(((Integer) obj).intValue());
                return;
            case 17:
                zzel.zzb(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzfg) {
                    zzel.zza(((zzfg) obj).zza());
                    return;
                } else {
                    zzel.zza(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public final int zzg() {
        int i;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = i2;
            if (i3 >= this.zza.zzc()) {
                break;
            }
            i2 = zzc(this.zza.zzb(i3)) + i;
            i3++;
        }
        for (Map.Entry<T, Object> zzc2 : this.zza.zzd()) {
            i += zzc(zzc2);
        }
        return i;
    }

    private static int zzc(Map.Entry<T, Object> entry) {
        zzew zzew = (zzew) entry.getKey();
        Object value = entry.getValue();
        if (zzew.zzc() != zzir.MESSAGE || zzew.zzd() || zzew.zze()) {
            return zza((zzew<?>) zzew, value);
        }
        if (value instanceof zzfr) {
            return zzel.zzb(((zzew) entry.getKey()).zza(), (zzfv) (zzfr) value);
        }
        return zzel.zzb(((zzew) entry.getKey()).zza(), (zzgm) value);
    }

    static int zza(zzik zzik, int i, Object obj) {
        int i2;
        int zze = zzel.zze(i);
        if (zzik == zzik.GROUP) {
            zzfh.zza((zzgm) obj);
            i2 = zze << 1;
        } else {
            i2 = zze;
        }
        return i2 + zzb(zzik, obj);
    }

    private static int zzb(zzik zzik, Object obj) {
        switch (zzex.zzb[zzik.ordinal()]) {
            case 1:
                return zzel.zzb(((Double) obj).doubleValue());
            case 2:
                return zzel.zzb(((Float) obj).floatValue());
            case 3:
                return zzel.zzd(((Long) obj).longValue());
            case 4:
                return zzel.zze(((Long) obj).longValue());
            case 5:
                return zzel.zzf(((Integer) obj).intValue());
            case 6:
                return zzel.zzg(((Long) obj).longValue());
            case 7:
                return zzel.zzi(((Integer) obj).intValue());
            case 8:
                return zzel.zzb(((Boolean) obj).booleanValue());
            case 9:
                return zzel.zzc((zzgm) obj);
            case 10:
                if (obj instanceof zzfr) {
                    return zzel.zza((zzfv) (zzfr) obj);
                }
                return zzel.zzb((zzgm) obj);
            case 11:
                if (obj instanceof zzdw) {
                    return zzel.zzb((zzdw) obj);
                }
                return zzel.zzb((String) obj);
            case 12:
                if (obj instanceof zzdw) {
                    return zzel.zzb((zzdw) obj);
                }
                return zzel.zzb((byte[]) obj);
            case 13:
                return zzel.zzg(((Integer) obj).intValue());
            case 14:
                return zzel.zzj(((Integer) obj).intValue());
            case 15:
                return zzel.zzh(((Long) obj).longValue());
            case 16:
                return zzel.zzh(((Integer) obj).intValue());
            case 17:
                return zzel.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzfg) {
                    return zzel.zzk(((zzfg) obj).zza());
                }
                return zzel.zzk(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zza(zzew<?> zzew, Object obj) {
        int i = 0;
        zzik zzb2 = zzew.zzb();
        int zza2 = zzew.zza();
        if (!zzew.zzd()) {
            return zza(zzb2, zza2, obj);
        }
        if (zzew.zze()) {
            for (Object zzb3 : (List) obj) {
                i += zzb(zzb2, zzb3);
            }
            return zzel.zzl(i) + zzel.zze(zza2) + i;
        }
        for (Object zza3 : (List) obj) {
            i += zza(zzb2, zza2, zza3);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzeu zzeu = new zzeu();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.zza.zzc()) {
                break;
            }
            Map.Entry<T, Object> zzb2 = this.zza.zzb(i2);
            zzeu.zzb((zzew) zzb2.getKey(), zzb2.getValue());
            i = i2 + 1;
        }
        for (Map.Entry next : this.zza.zzd()) {
            zzeu.zzb((zzew) next.getKey(), next.getValue());
        }
        zzeu.zzc = this.zzc;
        return zzeu;
    }
}
