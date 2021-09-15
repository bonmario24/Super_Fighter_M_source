package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

final class zzds<T> implements zzef<T> {
    private static final Unsafe zzmh = zzfd.zzef();
    private final int[] zzmi;
    private final Object[] zzmj;
    private final int zzmk;
    private final int zzml;
    private final int zzmm;
    private final zzdo zzmn;
    private final boolean zzmo;
    private final boolean zzmp;
    private final boolean zzmq;
    private final boolean zzmr;
    private final int[] zzms;
    private final int[] zzmt;
    private final int[] zzmu;
    private final zzdw zzmv;
    private final zzcy zzmw;
    private final zzex<?, ?> zzmx;
    private final zzbu<?> zzmy;
    private final zzdj zzmz;

    private zzds(int[] iArr, Object[] objArr, int i, int i2, int i3, zzdo zzdo, boolean z, boolean z2, int[] iArr2, int[] iArr3, int[] iArr4, zzdw zzdw, zzcy zzcy, zzex<?, ?> zzex, zzbu<?> zzbu, zzdj zzdj) {
        this.zzmi = iArr;
        this.zzmj = objArr;
        this.zzmk = i;
        this.zzml = i2;
        this.zzmm = i3;
        this.zzmp = zzdo instanceof zzcg;
        this.zzmq = z;
        this.zzmo = zzbu != null && zzbu.zze(zzdo);
        this.zzmr = false;
        this.zzms = iArr2;
        this.zzmt = iArr3;
        this.zzmu = iArr4;
        this.zzmv = zzdw;
        this.zzmw = zzcy;
        this.zzmx = zzex;
        this.zzmy = zzbu;
        this.zzmn = zzdo;
        this.zzmz = zzdj;
    }

    private static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzay zzay) throws IOException {
        return zzax.zza(i, bArr, i2, i3, zzn(obj), zzay);
    }

    private static int zza(zzef<?> zzef, int i, byte[] bArr, int i2, int i3, zzcn<?> zzcn, zzay zzay) throws IOException {
        int zza = zza((zzef) zzef, bArr, i2, i3, zzay);
        zzcn.add(zzay.zzff);
        while (zza < i3) {
            int zza2 = zzax.zza(bArr, zza, zzay);
            if (i != zzay.zzfd) {
                break;
            }
            zza = zza((zzef) zzef, bArr, zza2, i3, zzay);
            zzcn.add(zzay.zzff);
        }
        return zza;
    }

    private static int zza(zzef zzef, byte[] bArr, int i, int i2, int i3, zzay zzay) throws IOException {
        zzds zzds = (zzds) zzef;
        Object newInstance = zzds.newInstance();
        int zza = zzds.zza(newInstance, bArr, i, i2, i3, zzay);
        zzds.zzc(newInstance);
        zzay.zzff = newInstance;
        return zza;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zza(com.google.android.gms.internal.clearcut.zzef r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.clearcut.zzay r11) throws java.io.IOException {
        /*
            int r3 = r9 + 1
            byte r0 = r8[r9]
            if (r0 >= 0) goto L_0x002c
            int r3 = com.google.android.gms.internal.clearcut.zzax.zza((int) r0, (byte[]) r8, (int) r3, (com.google.android.gms.internal.clearcut.zzay) r11)
            int r0 = r11.zzfd
            r6 = r0
        L_0x000d:
            if (r6 < 0) goto L_0x0013
            int r0 = r10 - r3
            if (r6 <= r0) goto L_0x0018
        L_0x0013:
            com.google.android.gms.internal.clearcut.zzco r0 = com.google.android.gms.internal.clearcut.zzco.zzbl()
            throw r0
        L_0x0018:
            java.lang.Object r1 = r7.newInstance()
            int r4 = r3 + r6
            r0 = r7
            r2 = r8
            r5 = r11
            r0.zza(r1, r2, r3, r4, r5)
            r7.zzc(r1)
            r11.zzff = r1
            int r0 = r3 + r6
            return r0
        L_0x002c:
            r6 = r0
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(com.google.android.gms.internal.clearcut.zzef, byte[], int, int, com.google.android.gms.internal.clearcut.zzay):int");
    }

    private static <UT, UB> int zza(zzex<UT, UB> zzex, T t) {
        return zzex.zzm(zzex.zzq(t));
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzay zzay) throws IOException {
        int zza;
        Unsafe unsafe = zzmh;
        long j2 = (long) (this.zzmi[i8 + 2] & 1048575);
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(zzax.zze(bArr, i)));
                    zza = i + 8;
                    break;
                } else {
                    return i;
                }
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(zzax.zzf(bArr, i)));
                    zza = i + 4;
                    break;
                } else {
                    return i;
                }
            case 53:
            case 54:
                if (i5 == 0) {
                    zza = zzax.zzb(bArr, i, zzay);
                    unsafe.putObject(t, j, Long.valueOf(zzay.zzfe));
                    break;
                } else {
                    return i;
                }
            case 55:
            case 62:
                if (i5 == 0) {
                    zza = zzax.zza(bArr, i, zzay);
                    unsafe.putObject(t, j, Integer.valueOf(zzay.zzfd));
                    break;
                } else {
                    return i;
                }
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(zzax.zzd(bArr, i)));
                    zza = i + 8;
                    break;
                } else {
                    return i;
                }
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(zzax.zzc(bArr, i)));
                    zza = i + 4;
                    break;
                } else {
                    return i;
                }
            case 58:
                if (i5 == 0) {
                    int zzb = zzax.zzb(bArr, i, zzay);
                    unsafe.putObject(t, j, Boolean.valueOf(zzay.zzfe != 0));
                    zza = zzb;
                    break;
                } else {
                    return i;
                }
            case 59:
                if (i5 != 2) {
                    return i;
                }
                int zza2 = zzax.zza(bArr, i, zzay);
                int i9 = zzay.zzfd;
                if (i9 == 0) {
                    unsafe.putObject(t, j, "");
                } else if ((536870912 & i6) == 0 || zzff.zze(bArr, zza2, zza2 + i9)) {
                    unsafe.putObject(t, j, new String(bArr, zza2, i9, zzci.UTF_8));
                    zza2 += i9;
                } else {
                    throw zzco.zzbp();
                }
                unsafe.putInt(t, j2, i4);
                return zza2;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                int zza3 = zza(zzad(i8), bArr, i, i2, zzay);
                Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object == null) {
                    unsafe.putObject(t, j, zzay.zzff);
                } else {
                    unsafe.putObject(t, j, zzci.zza(object, zzay.zzff));
                }
                unsafe.putInt(t, j2, i4);
                return zza3;
            case 61:
                if (i5 != 2) {
                    return i;
                }
                int zza4 = zzax.zza(bArr, i, zzay);
                int i10 = zzay.zzfd;
                if (i10 == 0) {
                    unsafe.putObject(t, j, zzbb.zzfi);
                } else {
                    unsafe.putObject(t, j, zzbb.zzb(bArr, zza4, i10));
                    zza4 += i10;
                }
                unsafe.putInt(t, j2, i4);
                return zza4;
            case 63:
                if (i5 != 0) {
                    return i;
                }
                zza = zzax.zza(bArr, i, zzay);
                int i11 = zzay.zzfd;
                zzck<?> zzaf = zzaf(i8);
                if (zzaf == null || zzaf.zzb(i11) != null) {
                    unsafe.putObject(t, j, Integer.valueOf(i11));
                    break;
                } else {
                    zzn(t).zzb(i3, Long.valueOf((long) i11));
                    return zza;
                }
            case 66:
                if (i5 == 0) {
                    zza = zzax.zza(bArr, i, zzay);
                    unsafe.putObject(t, j, Integer.valueOf(zzbk.zzm(zzay.zzfd)));
                    break;
                } else {
                    return i;
                }
            case 67:
                if (i5 == 0) {
                    zza = zzax.zzb(bArr, i, zzay);
                    unsafe.putObject(t, j, Long.valueOf(zzbk.zza(zzay.zzfe)));
                    break;
                } else {
                    return i;
                }
            case 68:
                if (i5 == 3) {
                    zza = zza(zzad(i8), bArr, i, i2, (i3 & -8) | 4, zzay);
                    Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object2 != null) {
                        unsafe.putObject(t, j, zzci.zza(object2, zzay.zzff));
                        break;
                    } else {
                        unsafe.putObject(t, j, zzay.zzff);
                        break;
                    }
                } else {
                    return i;
                }
            default:
                return i;
        }
        unsafe.putInt(t, j2, i4);
        return zza;
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzay zzay) throws IOException {
        zzcn zzcn;
        int zza;
        int i8;
        int i9;
        int i10;
        zzcn zzcn2 = (zzcn) zzmh.getObject(t, j2);
        if (!zzcn2.zzu()) {
            int size = zzcn2.size();
            zzcn = zzcn2.zzi(size == 0 ? 10 : size << 1);
            zzmh.putObject(t, j2, zzcn);
        } else {
            zzcn = zzcn2;
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzbq zzbq = (zzbq) zzcn;
                    int zza2 = zzax.zza(bArr, i, zzay);
                    int i11 = zzay.zzfd + zza2;
                    while (zza2 < i11) {
                        zzbq.zzc(zzax.zze(bArr, zza2));
                        zza2 += 8;
                    }
                    if (zza2 == i11) {
                        return zza2;
                    }
                    throw zzco.zzbl();
                } else if (i5 != 1) {
                    return i;
                } else {
                    zzbq zzbq2 = (zzbq) zzcn;
                    zzbq2.zzc(zzax.zze(bArr, i));
                    int i12 = i + 8;
                    while (i12 < i2) {
                        int zza3 = zzax.zza(bArr, i12, zzay);
                        if (i3 != zzay.zzfd) {
                            return i12;
                        }
                        zzbq2.zzc(zzax.zze(bArr, zza3));
                        i12 = zza3 + 8;
                    }
                    return i12;
                }
            case 19:
            case 36:
                if (i5 == 2) {
                    zzce zzce = (zzce) zzcn;
                    int zza4 = zzax.zza(bArr, i, zzay);
                    int i13 = zzay.zzfd + zza4;
                    while (zza4 < i13) {
                        zzce.zzc(zzax.zzf(bArr, zza4));
                        zza4 += 4;
                    }
                    if (zza4 == i13) {
                        return zza4;
                    }
                    throw zzco.zzbl();
                } else if (i5 != 5) {
                    return i;
                } else {
                    zzce zzce2 = (zzce) zzcn;
                    zzce2.zzc(zzax.zzf(bArr, i));
                    int i14 = i + 4;
                    while (i14 < i2) {
                        int zza5 = zzax.zza(bArr, i14, zzay);
                        if (i3 != zzay.zzfd) {
                            return i14;
                        }
                        zzce2.zzc(zzax.zzf(bArr, zza5));
                        i14 = zza5 + 4;
                    }
                    return i14;
                }
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzdc zzdc = (zzdc) zzcn;
                    int zza6 = zzax.zza(bArr, i, zzay);
                    int i15 = zzay.zzfd + zza6;
                    while (zza6 < i15) {
                        zza6 = zzax.zzb(bArr, zza6, zzay);
                        zzdc.zzm(zzay.zzfe);
                    }
                    if (zza6 == i15) {
                        return zza6;
                    }
                    throw zzco.zzbl();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzdc zzdc2 = (zzdc) zzcn;
                    int zzb = zzax.zzb(bArr, i, zzay);
                    zzdc2.zzm(zzay.zzfe);
                    while (zzb < i2) {
                        int zza7 = zzax.zza(bArr, zzb, zzay);
                        if (i3 != zzay.zzfd) {
                            return zzb;
                        }
                        zzb = zzax.zzb(bArr, zza7, zzay);
                        zzdc2.zzm(zzay.zzfe);
                    }
                    return zzb;
                }
            case 22:
            case 29:
            case 39:
            case 43:
                return i5 == 2 ? zzax.zza(bArr, i, (zzcn<?>) zzcn, zzay) : i5 == 0 ? zzax.zza(i3, bArr, i, i2, (zzcn<?>) zzcn, zzay) : i;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzdc zzdc3 = (zzdc) zzcn;
                    int zza8 = zzax.zza(bArr, i, zzay);
                    int i16 = zzay.zzfd + zza8;
                    while (zza8 < i16) {
                        zzdc3.zzm(zzax.zzd(bArr, zza8));
                        zza8 += 8;
                    }
                    if (zza8 == i16) {
                        return zza8;
                    }
                    throw zzco.zzbl();
                } else if (i5 != 1) {
                    return i;
                } else {
                    zzdc zzdc4 = (zzdc) zzcn;
                    zzdc4.zzm(zzax.zzd(bArr, i));
                    int i17 = i + 8;
                    while (i17 < i2) {
                        int zza9 = zzax.zza(bArr, i17, zzay);
                        if (i3 != zzay.zzfd) {
                            return i17;
                        }
                        zzdc4.zzm(zzax.zzd(bArr, zza9));
                        i17 = zza9 + 8;
                    }
                    return i17;
                }
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzch zzch = (zzch) zzcn;
                    int zza10 = zzax.zza(bArr, i, zzay);
                    int i18 = zzay.zzfd + zza10;
                    while (zza10 < i18) {
                        zzch.zzac(zzax.zzc(bArr, zza10));
                        zza10 += 4;
                    }
                    if (zza10 == i18) {
                        return zza10;
                    }
                    throw zzco.zzbl();
                } else if (i5 != 5) {
                    return i;
                } else {
                    zzch zzch2 = (zzch) zzcn;
                    zzch2.zzac(zzax.zzc(bArr, i));
                    int i19 = i + 4;
                    while (i19 < i2) {
                        int zza11 = zzax.zza(bArr, i19, zzay);
                        if (i3 != zzay.zzfd) {
                            return i19;
                        }
                        zzch2.zzac(zzax.zzc(bArr, zza11));
                        i19 = zza11 + 4;
                    }
                    return i19;
                }
            case 25:
            case 42:
                if (i5 == 2) {
                    zzaz zzaz = (zzaz) zzcn;
                    int zza12 = zzax.zza(bArr, i, zzay);
                    int i20 = zza12 + zzay.zzfd;
                    while (zza12 < i20) {
                        zza12 = zzax.zzb(bArr, zza12, zzay);
                        zzaz.addBoolean(zzay.zzfe != 0);
                    }
                    if (zza12 == i20) {
                        return zza12;
                    }
                    throw zzco.zzbl();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzaz zzaz2 = (zzaz) zzcn;
                    int zzb2 = zzax.zzb(bArr, i, zzay);
                    zzaz2.addBoolean(zzay.zzfe != 0);
                    while (zzb2 < i2) {
                        int zza13 = zzax.zza(bArr, zzb2, zzay);
                        if (i3 != zzay.zzfd) {
                            return zzb2;
                        }
                        zzb2 = zzax.zzb(bArr, zza13, zzay);
                        zzaz2.addBoolean(zzay.zzfe != 0);
                    }
                    return zzb2;
                }
            case 26:
                if (i5 != 2) {
                    return i;
                }
                if ((536870912 & j) == 0) {
                    int zza14 = zzax.zza(bArr, i, zzay);
                    int i21 = zzay.zzfd;
                    if (i21 == 0) {
                        zzcn.add("");
                    } else {
                        zzcn.add(new String(bArr, zza14, i21, zzci.UTF_8));
                        zza14 += i21;
                    }
                    while (i10 < i2) {
                        int zza15 = zzax.zza(bArr, i10, zzay);
                        if (i3 != zzay.zzfd) {
                            return i10;
                        }
                        i10 = zzax.zza(bArr, zza15, zzay);
                        int i22 = zzay.zzfd;
                        if (i22 == 0) {
                            zzcn.add("");
                        } else {
                            zzcn.add(new String(bArr, i10, i22, zzci.UTF_8));
                            i10 += i22;
                        }
                    }
                    return i10;
                }
                int zza16 = zzax.zza(bArr, i, zzay);
                int i23 = zzay.zzfd;
                if (i23 == 0) {
                    zzcn.add("");
                } else {
                    if (!zzff.zze(bArr, zza16, zza16 + i23)) {
                        throw zzco.zzbp();
                    }
                    zzcn.add(new String(bArr, zza16, i23, zzci.UTF_8));
                    zza16 += i23;
                }
                while (i9 < i2) {
                    int zza17 = zzax.zza(bArr, i9, zzay);
                    if (i3 != zzay.zzfd) {
                        return i9;
                    }
                    i9 = zzax.zza(bArr, zza17, zzay);
                    int i24 = zzay.zzfd;
                    if (i24 == 0) {
                        zzcn.add("");
                    } else {
                        if (!zzff.zze(bArr, i9, i9 + i24)) {
                            throw zzco.zzbp();
                        }
                        zzcn.add(new String(bArr, i9, i24, zzci.UTF_8));
                        i9 += i24;
                    }
                }
                return i9;
            case 27:
                return i5 == 2 ? zza((zzef<?>) zzad(i6), i3, bArr, i, i2, (zzcn<?>) zzcn, zzay) : i;
            case 28:
                if (i5 != 2) {
                    return i;
                }
                int zza18 = zzax.zza(bArr, i, zzay);
                int i25 = zzay.zzfd;
                if (i25 == 0) {
                    zzcn.add(zzbb.zzfi);
                } else {
                    zzcn.add(zzbb.zzb(bArr, zza18, i25));
                    zza18 += i25;
                }
                while (i8 < i2) {
                    int zza19 = zzax.zza(bArr, i8, zzay);
                    if (i3 != zzay.zzfd) {
                        return i8;
                    }
                    i8 = zzax.zza(bArr, zza19, zzay);
                    int i26 = zzay.zzfd;
                    if (i26 == 0) {
                        zzcn.add(zzbb.zzfi);
                    } else {
                        zzcn.add(zzbb.zzb(bArr, i8, i26));
                        i8 += i26;
                    }
                }
                return i8;
            case 30:
            case 44:
                if (i5 == 2) {
                    zza = zzax.zza(bArr, i, (zzcn<?>) zzcn, zzay);
                } else if (i5 != 0) {
                    return i;
                } else {
                    zza = zzax.zza(i3, bArr, i, i2, (zzcn<?>) zzcn, zzay);
                }
                zzey zzey = ((zzcg) t).zzjp;
                if (zzey == zzey.zzea()) {
                    zzey = null;
                }
                zzey zzey2 = (zzey) zzeh.zza(i4, zzcn, zzaf(i6), zzey, this.zzmx);
                if (zzey2 == null) {
                    return zza;
                }
                ((zzcg) t).zzjp = zzey2;
                return zza;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzch zzch3 = (zzch) zzcn;
                    int zza20 = zzax.zza(bArr, i, zzay);
                    int i27 = zzay.zzfd + zza20;
                    while (zza20 < i27) {
                        zza20 = zzax.zza(bArr, zza20, zzay);
                        zzch3.zzac(zzbk.zzm(zzay.zzfd));
                    }
                    if (zza20 == i27) {
                        return zza20;
                    }
                    throw zzco.zzbl();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzch zzch4 = (zzch) zzcn;
                    int zza21 = zzax.zza(bArr, i, zzay);
                    zzch4.zzac(zzbk.zzm(zzay.zzfd));
                    while (zza21 < i2) {
                        int zza22 = zzax.zza(bArr, zza21, zzay);
                        if (i3 != zzay.zzfd) {
                            return zza21;
                        }
                        zza21 = zzax.zza(bArr, zza22, zzay);
                        zzch4.zzac(zzbk.zzm(zzay.zzfd));
                    }
                    return zza21;
                }
            case 34:
            case 48:
                if (i5 == 2) {
                    zzdc zzdc5 = (zzdc) zzcn;
                    int zza23 = zzax.zza(bArr, i, zzay);
                    int i28 = zzay.zzfd + zza23;
                    while (zza23 < i28) {
                        zza23 = zzax.zzb(bArr, zza23, zzay);
                        zzdc5.zzm(zzbk.zza(zzay.zzfe));
                    }
                    if (zza23 == i28) {
                        return zza23;
                    }
                    throw zzco.zzbl();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzdc zzdc6 = (zzdc) zzcn;
                    int zzb3 = zzax.zzb(bArr, i, zzay);
                    zzdc6.zzm(zzbk.zza(zzay.zzfe));
                    while (zzb3 < i2) {
                        int zza24 = zzax.zza(bArr, zzb3, zzay);
                        if (i3 != zzay.zzfd) {
                            return zzb3;
                        }
                        zzb3 = zzax.zzb(bArr, zza24, zzay);
                        zzdc6.zzm(zzbk.zza(zzay.zzfe));
                    }
                    return zzb3;
                }
            case 49:
                if (i5 != 3) {
                    return i;
                }
                zzef zzad = zzad(i6);
                int i29 = (i3 & -8) | 4;
                int zza25 = zza(zzad, bArr, i, i2, i29, zzay);
                zzcn.add(zzay.zzff);
                while (zza25 < i2) {
                    int zza26 = zzax.zza(bArr, zza25, zzay);
                    if (i3 != zzay.zzfd) {
                        return zza25;
                    }
                    zza25 = zza(zzad, bArr, zza26, i2, i29, zzay);
                    zzcn.add(zzay.zzff);
                }
                return zza25;
            default:
                return i;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: byte} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <K, V> int zza(T r14, byte[] r15, int r16, int r17, int r18, int r19, long r20, com.google.android.gms.internal.clearcut.zzay r22) throws java.io.IOException {
        /*
            r13 = this;
            sun.misc.Unsafe r4 = zzmh
            r0 = r18
            java.lang.Object r5 = r13.zzae(r0)
            r0 = r20
            java.lang.Object r3 = r4.getObject(r14, r0)
            com.google.android.gms.internal.clearcut.zzdj r2 = r13.zzmz
            boolean r2 = r2.zzi(r3)
            if (r2 == 0) goto L_0x00ba
            com.google.android.gms.internal.clearcut.zzdj r2 = r13.zzmz
            java.lang.Object r2 = r2.zzk(r5)
            com.google.android.gms.internal.clearcut.zzdj r6 = r13.zzmz
            r6.zzb(r2, r3)
            r0 = r20
            r4.putObject(r14, r0, r2)
        L_0x0026:
            com.google.android.gms.internal.clearcut.zzdj r3 = r13.zzmz
            com.google.android.gms.internal.clearcut.zzdh r10 = r3.zzl(r5)
            com.google.android.gms.internal.clearcut.zzdj r3 = r13.zzmz
            java.util.Map r11 = r3.zzg(r2)
            r0 = r16
            r1 = r22
            int r4 = com.google.android.gms.internal.clearcut.zzax.zza(r15, r0, r1)
            r0 = r22
            int r2 = r0.zzfd
            if (r2 < 0) goto L_0x0044
            int r3 = r17 - r4
            if (r2 <= r3) goto L_0x0049
        L_0x0044:
            com.google.android.gms.internal.clearcut.zzco r2 = com.google.android.gms.internal.clearcut.zzco.zzbl()
            throw r2
        L_0x0049:
            int r12 = r4 + r2
            K r3 = r10.zzmc
            V r2 = r10.zzdu
            r8 = r2
            r9 = r3
        L_0x0051:
            if (r4 >= r12) goto L_0x00af
            int r3 = r4 + 1
            byte r2 = r15[r4]
            if (r2 >= 0) goto L_0x0063
            r0 = r22
            int r3 = com.google.android.gms.internal.clearcut.zzax.zza((int) r2, (byte[]) r15, (int) r3, (com.google.android.gms.internal.clearcut.zzay) r0)
            r0 = r22
            int r2 = r0.zzfd
        L_0x0063:
            int r4 = r2 >>> 3
            r5 = r2 & 7
            switch(r4) {
                case 1: goto L_0x0074;
                case 2: goto L_0x008f;
                default: goto L_0x006a;
            }
        L_0x006a:
            r0 = r17
            r1 = r22
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r2, r15, r3, r0, r1)
            r4 = r2
            goto L_0x0051
        L_0x0074:
            com.google.android.gms.internal.clearcut.zzfl r4 = r10.zzmb
            int r4 = r4.zzel()
            if (r5 != r4) goto L_0x006a
            com.google.android.gms.internal.clearcut.zzfl r5 = r10.zzmb
            r6 = 0
            r2 = r15
            r4 = r17
            r7 = r22
            int r3 = zza((byte[]) r2, (int) r3, (int) r4, (com.google.android.gms.internal.clearcut.zzfl) r5, (java.lang.Class<?>) r6, (com.google.android.gms.internal.clearcut.zzay) r7)
            r0 = r22
            java.lang.Object r2 = r0.zzff
            r9 = r2
            r4 = r3
            goto L_0x0051
        L_0x008f:
            com.google.android.gms.internal.clearcut.zzfl r4 = r10.zzmd
            int r4 = r4.zzel()
            if (r5 != r4) goto L_0x006a
            com.google.android.gms.internal.clearcut.zzfl r5 = r10.zzmd
            V r2 = r10.zzdu
            java.lang.Class r6 = r2.getClass()
            r2 = r15
            r4 = r17
            r7 = r22
            int r3 = zza((byte[]) r2, (int) r3, (int) r4, (com.google.android.gms.internal.clearcut.zzfl) r5, (java.lang.Class<?>) r6, (com.google.android.gms.internal.clearcut.zzay) r7)
            r0 = r22
            java.lang.Object r2 = r0.zzff
            r8 = r2
            r4 = r3
            goto L_0x0051
        L_0x00af:
            if (r4 == r12) goto L_0x00b6
            com.google.android.gms.internal.clearcut.zzco r2 = com.google.android.gms.internal.clearcut.zzco.zzbo()
            throw r2
        L_0x00b6:
            r11.put(r9, r8)
            return r12
        L_0x00ba:
            r2 = r3
            goto L_0x0026
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, int, int, long, com.google.android.gms.internal.clearcut.zzay):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v51, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: byte} */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r17v3, types: [byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008d A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(T r41, byte[] r42, int r43, int r44, int r45, com.google.android.gms.internal.clearcut.zzay r46) throws java.io.IOException {
        /*
            r40 = this;
            sun.misc.Unsafe r4 = zzmh
            r39 = -1
            r38 = 0
            r17 = 0
            r19 = r43
        L_0x000a:
            r0 = r19
            r1 = r44
            if (r0 >= r1) goto L_0x032f
            int r10 = r19 + 1
            byte r17 = r42[r19]
            if (r17 >= 0) goto L_0x0026
            r0 = r17
            r1 = r42
            r2 = r46
            int r10 = com.google.android.gms.internal.clearcut.zzax.zza((int) r0, (byte[]) r1, (int) r10, (com.google.android.gms.internal.clearcut.zzay) r2)
            r0 = r46
            int r0 = r0.zzfd
            r17 = r0
        L_0x0026:
            int r18 = r17 >>> 3
            r19 = r17 & 7
            r0 = r40
            r1 = r18
            int r20 = r0.zzai(r1)
            r5 = -1
            r0 = r20
            if (r0 == r5) goto L_0x03b7
            r0 = r40
            int[] r5 = r0.zzmi
            int r6 = r20 + 1
            r32 = r5[r6]
            r5 = 267386880(0xff00000, float:2.3665827E-29)
            r5 = r5 & r32
            int r23 = r5 >>> 20
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r32
            long r6 = (long) r5
            r5 = 17
            r0 = r23
            if (r0 > r5) goto L_0x0275
            r0 = r40
            int[] r5 = r0.zzmi
            int r8 = r20 + 2
            r5 = r5[r8]
            r8 = 1
            int r9 = r5 >>> 20
            int r14 = r8 << r9
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r8
            r0 = r39
            if (r5 == r0) goto L_0x007e
            r8 = -1
            r0 = r39
            if (r0 == r8) goto L_0x0075
            r0 = r39
            long r8 = (long) r0
            r0 = r41
            r1 = r38
            r4.putInt(r0, r8, r1)
        L_0x0075:
            long r8 = (long) r5
            r0 = r41
            int r38 = r4.getInt(r0, r8)
            r39 = r5
        L_0x007e:
            switch(r23) {
                case 0: goto L_0x00a1;
                case 1: goto L_0x00b7;
                case 2: goto L_0x00cd;
                case 3: goto L_0x00cd;
                case 4: goto L_0x00e4;
                case 5: goto L_0x00fb;
                case 6: goto L_0x0111;
                case 7: goto L_0x0127;
                case 8: goto L_0x0147;
                case 9: goto L_0x0172;
                case 10: goto L_0x01ae;
                case 11: goto L_0x00e4;
                case 12: goto L_0x01c8;
                case 13: goto L_0x0111;
                case 14: goto L_0x00fb;
                case 15: goto L_0x01ff;
                case 16: goto L_0x021a;
                case 17: goto L_0x0235;
                default: goto L_0x0081;
            }
        L_0x0081:
            r5 = r38
            r6 = r39
            r19 = r10
        L_0x0087:
            r0 = r17
            r1 = r45
            if (r0 != r1) goto L_0x008f
            if (r45 != 0) goto L_0x0333
        L_0x008f:
            r18 = r42
            r20 = r44
            r21 = r41
            r22 = r46
            int r19 = zza((int) r17, (byte[]) r18, (int) r19, (int) r20, (java.lang.Object) r21, (com.google.android.gms.internal.clearcut.zzay) r22)
            r38 = r5
            r39 = r6
            goto L_0x000a
        L_0x00a1:
            r5 = 1
            r0 = r19
            if (r0 != r5) goto L_0x0081
            r0 = r42
            double r8 = com.google.android.gms.internal.clearcut.zzax.zze(r0, r10)
            r0 = r41
            com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r0, (long) r6, (double) r8)
            int r19 = r10 + 8
            r38 = r38 | r14
            goto L_0x000a
        L_0x00b7:
            r5 = 5
            r0 = r19
            if (r0 != r5) goto L_0x0081
            r0 = r42
            float r5 = com.google.android.gms.internal.clearcut.zzax.zzf(r0, r10)
            r0 = r41
            com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r0, (long) r6, (float) r5)
            int r19 = r10 + 4
            r38 = r38 | r14
            goto L_0x000a
        L_0x00cd:
            if (r19 != 0) goto L_0x0081
            r0 = r42
            r1 = r46
            int r19 = com.google.android.gms.internal.clearcut.zzax.zzb(r0, r10, r1)
            r0 = r46
            long r8 = r0.zzfe
            r5 = r41
            r4.putLong(r5, r6, r8)
            r38 = r38 | r14
            goto L_0x000a
        L_0x00e4:
            if (r19 != 0) goto L_0x0081
            r0 = r42
            r1 = r46
            int r19 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r10, r1)
            r0 = r46
            int r5 = r0.zzfd
            r0 = r41
            r4.putInt(r0, r6, r5)
            r38 = r38 | r14
            goto L_0x000a
        L_0x00fb:
            r5 = 1
            r0 = r19
            if (r0 != r5) goto L_0x0081
            r0 = r42
            long r8 = com.google.android.gms.internal.clearcut.zzax.zzd(r0, r10)
            r5 = r41
            r4.putLong(r5, r6, r8)
            int r19 = r10 + 8
            r38 = r38 | r14
            goto L_0x000a
        L_0x0111:
            r5 = 5
            r0 = r19
            if (r0 != r5) goto L_0x0081
            r0 = r42
            int r5 = com.google.android.gms.internal.clearcut.zzax.zzc(r0, r10)
            r0 = r41
            r4.putInt(r0, r6, r5)
            int r19 = r10 + 4
            r38 = r38 | r14
            goto L_0x000a
        L_0x0127:
            if (r19 != 0) goto L_0x0081
            r0 = r42
            r1 = r46
            int r19 = com.google.android.gms.internal.clearcut.zzax.zzb(r0, r10, r1)
            r0 = r46
            long r8 = r0.zzfe
            r10 = 0
            int r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r5 == 0) goto L_0x0145
            r5 = 1
        L_0x013c:
            r0 = r41
            com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r0, (long) r6, (boolean) r5)
            r38 = r38 | r14
            goto L_0x000a
        L_0x0145:
            r5 = 0
            goto L_0x013c
        L_0x0147:
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x0081
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r5 & r32
            if (r5 != 0) goto L_0x0169
            r0 = r42
            r1 = r46
            int r5 = com.google.android.gms.internal.clearcut.zzax.zzc(r0, r10, r1)
        L_0x015a:
            r0 = r46
            java.lang.Object r8 = r0.zzff
            r0 = r41
            r4.putObject(r0, r6, r8)
            r38 = r38 | r14
            r19 = r5
            goto L_0x000a
        L_0x0169:
            r0 = r42
            r1 = r46
            int r5 = com.google.android.gms.internal.clearcut.zzax.zzd(r0, r10, r1)
            goto L_0x015a
        L_0x0172:
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x0081
            r0 = r40
            r1 = r20
            com.google.android.gms.internal.clearcut.zzef r5 = r0.zzad(r1)
            r0 = r42
            r1 = r44
            r2 = r46
            int r19 = zza((com.google.android.gms.internal.clearcut.zzef) r5, (byte[]) r0, (int) r10, (int) r1, (com.google.android.gms.internal.clearcut.zzay) r2)
            r5 = r38 & r14
            if (r5 != 0) goto L_0x019a
            r0 = r46
            java.lang.Object r5 = r0.zzff
            r0 = r41
            r4.putObject(r0, r6, r5)
        L_0x0196:
            r38 = r38 | r14
            goto L_0x000a
        L_0x019a:
            r0 = r41
            java.lang.Object r5 = r4.getObject(r0, r6)
            r0 = r46
            java.lang.Object r8 = r0.zzff
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzci.zza((java.lang.Object) r5, (java.lang.Object) r8)
            r0 = r41
            r4.putObject(r0, r6, r5)
            goto L_0x0196
        L_0x01ae:
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x0081
            r0 = r42
            r1 = r46
            int r19 = com.google.android.gms.internal.clearcut.zzax.zze(r0, r10, r1)
            r0 = r46
            java.lang.Object r5 = r0.zzff
            r0 = r41
            r4.putObject(r0, r6, r5)
            r38 = r38 | r14
            goto L_0x000a
        L_0x01c8:
            if (r19 != 0) goto L_0x0081
            r0 = r42
            r1 = r46
            int r19 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r10, r1)
            r0 = r46
            int r5 = r0.zzfd
            r0 = r40
            r1 = r20
            com.google.android.gms.internal.clearcut.zzck r8 = r0.zzaf(r1)
            if (r8 == 0) goto L_0x01e6
            com.google.android.gms.internal.clearcut.zzcj r8 = r8.zzb(r5)
            if (r8 == 0) goto L_0x01ef
        L_0x01e6:
            r0 = r41
            r4.putInt(r0, r6, r5)
            r38 = r38 | r14
            goto L_0x000a
        L_0x01ef:
            com.google.android.gms.internal.clearcut.zzey r6 = zzn(r41)
            long r8 = (long) r5
            java.lang.Long r5 = java.lang.Long.valueOf(r8)
            r0 = r17
            r6.zzb(r0, r5)
            goto L_0x000a
        L_0x01ff:
            if (r19 != 0) goto L_0x0081
            r0 = r42
            r1 = r46
            int r19 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r10, r1)
            r0 = r46
            int r5 = r0.zzfd
            int r5 = com.google.android.gms.internal.clearcut.zzbk.zzm(r5)
            r0 = r41
            r4.putInt(r0, r6, r5)
            r38 = r38 | r14
            goto L_0x000a
        L_0x021a:
            if (r19 != 0) goto L_0x0081
            r0 = r42
            r1 = r46
            int r19 = com.google.android.gms.internal.clearcut.zzax.zzb(r0, r10, r1)
            r0 = r46
            long r8 = r0.zzfe
            long r8 = com.google.android.gms.internal.clearcut.zzbk.zza(r8)
            r5 = r41
            r4.putLong(r5, r6, r8)
            r38 = r38 | r14
            goto L_0x000a
        L_0x0235:
            r5 = 3
            r0 = r19
            if (r0 != r5) goto L_0x0081
            int r5 = r18 << 3
            r12 = r5 | 4
            r0 = r40
            r1 = r20
            com.google.android.gms.internal.clearcut.zzef r8 = r0.zzad(r1)
            r9 = r42
            r11 = r44
            r13 = r46
            int r19 = zza((com.google.android.gms.internal.clearcut.zzef) r8, (byte[]) r9, (int) r10, (int) r11, (int) r12, (com.google.android.gms.internal.clearcut.zzay) r13)
            r5 = r38 & r14
            if (r5 != 0) goto L_0x0261
            r0 = r46
            java.lang.Object r5 = r0.zzff
            r0 = r41
            r4.putObject(r0, r6, r5)
        L_0x025d:
            r38 = r38 | r14
            goto L_0x000a
        L_0x0261:
            r0 = r41
            java.lang.Object r5 = r4.getObject(r0, r6)
            r0 = r46
            java.lang.Object r8 = r0.zzff
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzci.zza((java.lang.Object) r5, (java.lang.Object) r8)
            r0 = r41
            r4.putObject(r0, r6, r5)
            goto L_0x025d
        L_0x0275:
            r5 = 27
            r0 = r23
            if (r0 != r5) goto L_0x02b8
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x03b7
            r0 = r41
            java.lang.Object r5 = r4.getObject(r0, r6)
            com.google.android.gms.internal.clearcut.zzcn r5 = (com.google.android.gms.internal.clearcut.zzcn) r5
            boolean r8 = r5.zzu()
            if (r8 != 0) goto L_0x03bf
            int r8 = r5.size()
            if (r8 != 0) goto L_0x02b5
            r8 = 10
        L_0x0296:
            com.google.android.gms.internal.clearcut.zzcn r12 = r5.zzi(r8)
            r0 = r41
            r4.putObject(r0, r6, r12)
        L_0x029f:
            r0 = r40
            r1 = r20
            com.google.android.gms.internal.clearcut.zzef r7 = r0.zzad(r1)
            r8 = r17
            r9 = r42
            r11 = r44
            r13 = r46
            int r19 = zza((com.google.android.gms.internal.clearcut.zzef<?>) r7, (int) r8, (byte[]) r9, (int) r10, (int) r11, (com.google.android.gms.internal.clearcut.zzcn<?>) r12, (com.google.android.gms.internal.clearcut.zzay) r13)
            goto L_0x000a
        L_0x02b5:
            int r8 = r8 << 1
            goto L_0x0296
        L_0x02b8:
            r5 = 49
            r0 = r23
            if (r0 > r5) goto L_0x02de
            r0 = r32
            long r0 = (long) r0
            r21 = r0
            r12 = r40
            r13 = r41
            r14 = r42
            r15 = r10
            r16 = r44
            r24 = r6
            r26 = r46
            int r19 = r12.zza(r13, (byte[]) r14, (int) r15, (int) r16, (int) r17, (int) r18, (int) r19, (int) r20, (long) r21, (int) r23, (long) r24, (com.google.android.gms.internal.clearcut.zzay) r26)
            r0 = r19
            if (r0 != r10) goto L_0x000a
            r5 = r38
            r6 = r39
            goto L_0x0087
        L_0x02de:
            r5 = 50
            r0 = r23
            if (r0 != r5) goto L_0x0309
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x03b7
            r21 = r40
            r22 = r41
            r23 = r42
            r24 = r10
            r25 = r44
            r26 = r20
            r27 = r18
            r28 = r6
            r30 = r46
            int r19 = r21.zza(r22, r23, r24, r25, r26, r27, r28, r30)
            r0 = r19
            if (r0 != r10) goto L_0x000a
            r5 = r38
            r6 = r39
            goto L_0x0087
        L_0x0309:
            r24 = r40
            r25 = r41
            r26 = r42
            r27 = r10
            r28 = r44
            r29 = r17
            r30 = r18
            r31 = r19
            r33 = r23
            r34 = r6
            r36 = r20
            r37 = r46
            int r19 = r24.zza(r25, (byte[]) r26, (int) r27, (int) r28, (int) r29, (int) r30, (int) r31, (int) r32, (int) r33, (long) r34, (int) r36, (com.google.android.gms.internal.clearcut.zzay) r37)
            r0 = r19
            if (r0 != r10) goto L_0x000a
            r5 = r38
            r6 = r39
            goto L_0x0087
        L_0x032f:
            r5 = r38
            r6 = r39
        L_0x0333:
            r7 = -1
            if (r6 == r7) goto L_0x033c
            long r6 = (long) r6
            r0 = r41
            r4.putInt(r0, r6, r5)
        L_0x033c:
            r0 = r40
            int[] r4 = r0.zzmt
            if (r4 == 0) goto L_0x0398
            r9 = 0
            r0 = r40
            int[] r12 = r0.zzmt
            int r13 = r12.length
            r4 = 0
            r11 = r4
        L_0x034a:
            if (r11 >= r13) goto L_0x038d
            r5 = r12[r11]
            r0 = r40
            com.google.android.gms.internal.clearcut.zzex<?, ?> r10 = r0.zzmx
            r0 = r40
            int[] r4 = r0.zzmi
            r6 = r4[r5]
            r0 = r40
            int r4 = r0.zzag(r5)
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r7
            long r14 = (long) r4
            r0 = r41
            java.lang.Object r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r0, r14)
            if (r4 != 0) goto L_0x0373
            r4 = r9
        L_0x036c:
            com.google.android.gms.internal.clearcut.zzey r4 = (com.google.android.gms.internal.clearcut.zzey) r4
            int r5 = r11 + 1
            r11 = r5
            r9 = r4
            goto L_0x034a
        L_0x0373:
            r0 = r40
            com.google.android.gms.internal.clearcut.zzck r8 = r0.zzaf(r5)
            if (r8 != 0) goto L_0x037d
            r4 = r9
            goto L_0x036c
        L_0x037d:
            r0 = r40
            com.google.android.gms.internal.clearcut.zzdj r7 = r0.zzmz
            java.util.Map r7 = r7.zzg(r4)
            r4 = r40
            java.lang.Object r9 = r4.zza((int) r5, (int) r6, r7, (com.google.android.gms.internal.clearcut.zzck<?>) r8, r9, r10)
            r4 = r9
            goto L_0x036c
        L_0x038d:
            if (r9 == 0) goto L_0x0398
            r0 = r40
            com.google.android.gms.internal.clearcut.zzex<?, ?> r4 = r0.zzmx
            r0 = r41
            r4.zzf(r0, r9)
        L_0x0398:
            if (r45 != 0) goto L_0x03a5
            r0 = r19
            r1 = r44
            if (r0 == r1) goto L_0x03b6
            com.google.android.gms.internal.clearcut.zzco r4 = com.google.android.gms.internal.clearcut.zzco.zzbo()
            throw r4
        L_0x03a5:
            r0 = r19
            r1 = r44
            if (r0 > r1) goto L_0x03b1
            r0 = r17
            r1 = r45
            if (r0 == r1) goto L_0x03b6
        L_0x03b1:
            com.google.android.gms.internal.clearcut.zzco r4 = com.google.android.gms.internal.clearcut.zzco.zzbo()
            throw r4
        L_0x03b6:
            return r19
        L_0x03b7:
            r5 = r38
            r6 = r39
            r19 = r10
            goto L_0x0087
        L_0x03bf:
            r12 = r5
            goto L_0x029f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.clearcut.zzay):int");
    }

    private static int zza(byte[] bArr, int i, int i2, zzfl zzfl, Class<?> cls, zzay zzay) throws IOException {
        switch (zzdt.zzgq[zzfl.ordinal()]) {
            case 1:
                int zzb = zzax.zzb(bArr, i, zzay);
                zzay.zzff = Boolean.valueOf(zzay.zzfe != 0);
                return zzb;
            case 2:
                return zzax.zze(bArr, i, zzay);
            case 3:
                zzay.zzff = Double.valueOf(zzax.zze(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzay.zzff = Integer.valueOf(zzax.zzc(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzay.zzff = Long.valueOf(zzax.zzd(bArr, i));
                return i + 8;
            case 8:
                zzay.zzff = Float.valueOf(zzax.zzf(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int zza = zzax.zza(bArr, i, zzay);
                zzay.zzff = Integer.valueOf(zzay.zzfd);
                return zza;
            case 12:
            case 13:
                int zzb2 = zzax.zzb(bArr, i, zzay);
                zzay.zzff = Long.valueOf(zzay.zzfe);
                return zzb2;
            case 14:
                return zza((zzef) zzea.zzcm().zze(cls), bArr, i, i2, zzay);
            case 15:
                int zza2 = zzax.zza(bArr, i, zzay);
                zzay.zzff = Integer.valueOf(zzbk.zzm(zzay.zzfd));
                return zza2;
            case 16:
                int zzb3 = zzax.zzb(bArr, i, zzay);
                zzay.zzff = Long.valueOf(zzbk.zza(zzay.zzfe));
                return zzb3;
            case 17:
                return zzax.zzd(bArr, i, zzay);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    static <T> zzds<T> zza(Class<T> cls, zzdm zzdm, zzdw zzdw, zzcy zzcy, zzex<?, ?> zzex, zzbu<?> zzbu, zzdj zzdj) {
        int zzcp;
        int zzcq;
        int zzcu;
        int zza;
        int i;
        int i2;
        if (zzdm instanceof zzec) {
            zzec zzec = (zzec) zzdm;
            boolean z = zzec.zzcf() == zzcg.zzg.zzkm;
            if (zzec.getFieldCount() == 0) {
                zzcp = 0;
                zzcq = 0;
                zzcu = 0;
            } else {
                zzcp = zzec.zzcp();
                zzcq = zzec.zzcq();
                zzcu = zzec.zzcu();
            }
            int[] iArr = new int[(zzcu << 2)];
            Object[] objArr = new Object[(zzcu << 1)];
            int[] iArr2 = zzec.zzcr() > 0 ? new int[zzec.zzcr()] : null;
            int[] iArr3 = zzec.zzcs() > 0 ? new int[zzec.zzcs()] : null;
            int i3 = 0;
            int i4 = 0;
            zzed zzco = zzec.zzco();
            if (zzco.next()) {
                int zzcx = zzco.zzcx();
                int i5 = 0;
                while (true) {
                    if (zzcx >= zzec.zzcv() || i5 >= ((zzcx - zzcp) << 2)) {
                        if (zzco.zzda()) {
                            zza = (int) zzfd.zza(zzco.zzdb());
                            i = (int) zzfd.zza(zzco.zzdc());
                            i2 = 0;
                        } else {
                            zza = (int) zzfd.zza(zzco.zzdd());
                            if (zzco.zzde()) {
                                i = (int) zzfd.zza(zzco.zzdf());
                                i2 = zzco.zzdg();
                            } else {
                                i = 0;
                                i2 = 0;
                            }
                        }
                        iArr[i5] = zzco.zzcx();
                        iArr[i5 + 1] = zza | (zzco.zzdi() ? 536870912 : 0) | (zzco.zzdh() ? 268435456 : 0) | (zzco.zzcy() << 20);
                        iArr[i5 + 2] = i | (i2 << 20);
                        if (zzco.zzdl() != null) {
                            objArr[(i5 / 4) << 1] = zzco.zzdl();
                            if (zzco.zzdj() != null) {
                                objArr[((i5 / 4) << 1) + 1] = zzco.zzdj();
                            } else if (zzco.zzdk() != null) {
                                objArr[((i5 / 4) << 1) + 1] = zzco.zzdk();
                            }
                        } else if (zzco.zzdj() != null) {
                            objArr[((i5 / 4) << 1) + 1] = zzco.zzdj();
                        } else if (zzco.zzdk() != null) {
                            objArr[((i5 / 4) << 1) + 1] = zzco.zzdk();
                        }
                        int zzcy2 = zzco.zzcy();
                        if (zzcy2 == zzcb.MAP.ordinal()) {
                            iArr2[i3] = i5;
                            i3++;
                        } else if (zzcy2 >= 18 && zzcy2 <= 49) {
                            iArr3[i4] = iArr[i5 + 1] & 1048575;
                            i4++;
                        }
                        if (!zzco.next()) {
                            break;
                        }
                        zzcx = zzco.zzcx();
                    } else {
                        for (int i6 = 0; i6 < 4; i6++) {
                            iArr[i5 + i6] = -1;
                        }
                    }
                    i5 += 4;
                }
            }
            return new zzds<>(iArr, objArr, zzcp, zzcq, zzec.zzcv(), zzec.zzch(), z, false, zzec.zzct(), iArr2, iArr3, zzdw, zzcy, zzex, zzbu, zzdj);
        }
        ((zzes) zzdm).zzcf();
        throw new NoSuchMethodError();
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzck<?> zzck, UB ub, zzex<UT, UB> zzex) {
        zzdh<?, ?> zzl = this.zzmz.zzl(zzae(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (zzck.zzb(((Integer) next.getValue()).intValue()) == null) {
                if (ub == null) {
                    ub = zzex.zzdz();
                }
                zzbg zzk = zzbb.zzk(zzdg.zza(zzl, next.getKey(), next.getValue()));
                try {
                    zzdg.zza(zzk.zzae(), zzl, next.getKey(), next.getValue());
                    zzex.zza(ub, i2, zzk.zzad());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private static void zza(int i, Object obj, zzfr zzfr) throws IOException {
        if (obj instanceof String) {
            zzfr.zza(i, (String) obj);
        } else {
            zzfr.zza(i, (zzbb) obj);
        }
    }

    private static <UT, UB> void zza(zzex<UT, UB> zzex, T t, zzfr zzfr) throws IOException {
        zzex.zza(zzex.zzq(t), zzfr);
    }

    private final <K, V> void zza(zzfr zzfr, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzfr.zza(i, this.zzmz.zzl(zzae(i2)), this.zzmz.zzh(obj));
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzag = (long) (zzag(i) & 1048575);
        if (zza(t2, i)) {
            Object zzo = zzfd.zzo(t, zzag);
            Object zzo2 = zzfd.zzo(t2, zzag);
            if (zzo != null && zzo2 != null) {
                zzfd.zza((Object) t, zzag, zzci.zza(zzo, zzo2));
                zzb(t, i);
            } else if (zzo2 != null) {
                zzfd.zza((Object) t, zzag, zzo2);
                zzb(t, i);
            }
        }
    }

    private final boolean zza(T t, int i) {
        if (this.zzmq) {
            int zzag = zzag(i);
            long j = (long) (zzag & 1048575);
            switch ((zzag & 267386880) >>> 20) {
                case 0:
                    return zzfd.zzn(t, j) != 0.0d;
                case 1:
                    return zzfd.zzm(t, j) != 0.0f;
                case 2:
                    return zzfd.zzk(t, j) != 0;
                case 3:
                    return zzfd.zzk(t, j) != 0;
                case 4:
                    return zzfd.zzj(t, j) != 0;
                case 5:
                    return zzfd.zzk(t, j) != 0;
                case 6:
                    return zzfd.zzj(t, j) != 0;
                case 7:
                    return zzfd.zzl(t, j);
                case 8:
                    Object zzo = zzfd.zzo(t, j);
                    if (zzo instanceof String) {
                        return !((String) zzo).isEmpty();
                    }
                    if (zzo instanceof zzbb) {
                        return !zzbb.zzfi.equals(zzo);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzfd.zzo(t, j) != null;
                case 10:
                    return !zzbb.zzfi.equals(zzfd.zzo(t, j));
                case 11:
                    return zzfd.zzj(t, j) != 0;
                case 12:
                    return zzfd.zzj(t, j) != 0;
                case 13:
                    return zzfd.zzj(t, j) != 0;
                case 14:
                    return zzfd.zzk(t, j) != 0;
                case 15:
                    return zzfd.zzj(t, j) != 0;
                case 16:
                    return zzfd.zzk(t, j) != 0;
                case 17:
                    return zzfd.zzo(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzah = zzah(i);
            return (zzfd.zzj(t, (long) (zzah & 1048575)) & (1 << (zzah >>> 20))) != 0;
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzfd.zzj(t, (long) (zzah(i2) & 1048575)) == i;
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        return this.zzmq ? zza(t, i) : (i2 & i3) != 0;
    }

    private static boolean zza(Object obj, int i, zzef zzef) {
        return zzef.zzo(zzfd.zzo(obj, (long) (1048575 & i)));
    }

    private final zzef zzad(int i) {
        int i2 = (i / 4) << 1;
        zzef zzef = (zzef) this.zzmj[i2];
        if (zzef != null) {
            return zzef;
        }
        zzef zze = zzea.zzcm().zze((Class) this.zzmj[i2 + 1]);
        this.zzmj[i2] = zze;
        return zze;
    }

    private final Object zzae(int i) {
        return this.zzmj[(i / 4) << 1];
    }

    private final zzck<?> zzaf(int i) {
        return (zzck) this.zzmj[((i / 4) << 1) + 1];
    }

    private final int zzag(int i) {
        return this.zzmi[i + 1];
    }

    private final int zzah(int i) {
        return this.zzmi[i + 2];
    }

    private final int zzai(int i) {
        if (i >= this.zzmk) {
            if (i < this.zzmm) {
                int i2 = (i - this.zzmk) << 2;
                if (this.zzmi[i2] == i) {
                    return i2;
                }
                return -1;
            } else if (i <= this.zzml) {
                int i3 = this.zzmm - this.zzmk;
                int length = (this.zzmi.length / 4) - 1;
                while (i3 <= length) {
                    int i4 = (length + i3) >>> 1;
                    int i5 = i4 << 2;
                    int i6 = this.zzmi[i5];
                    if (i == i6) {
                        return i5;
                    }
                    if (i < i6) {
                        length = i4 - 1;
                    } else {
                        i3 = i4 + 1;
                    }
                }
                return -1;
            }
        }
        return -1;
    }

    private final void zzb(T t, int i) {
        if (!this.zzmq) {
            int zzah = zzah(i);
            long j = (long) (zzah & 1048575);
            zzfd.zza((Object) t, j, zzfd.zzj(t, j) | (1 << (zzah >>> 20)));
        }
    }

    private final void zzb(T t, int i, int i2) {
        zzfd.zza((Object) t, (long) (zzah(i2) & 1048575), i);
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 387 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r21, com.google.android.gms.internal.clearcut.zzfr r22) throws java.io.IOException {
        /*
            r20 = this;
            r5 = 0
            r4 = 0
            r0 = r20
            boolean r6 = r0.zzmo
            if (r6 == 0) goto L_0x0022
            r0 = r20
            com.google.android.gms.internal.clearcut.zzbu<?> r6 = r0.zzmy
            r0 = r21
            com.google.android.gms.internal.clearcut.zzby r6 = r6.zza((java.lang.Object) r0)
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L_0x0022
            java.util.Iterator r5 = r6.iterator()
            java.lang.Object r4 = r5.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
        L_0x0022:
            r8 = -1
            r6 = 0
            r0 = r20
            int[] r7 = r0.zzmi
            int r13 = r7.length
            sun.misc.Unsafe r14 = zzmh
            r7 = 0
            r12 = r7
            r9 = r4
        L_0x002e:
            if (r12 >= r13) goto L_0x06fb
            r0 = r20
            int r15 = r0.zzag(r12)
            r0 = r20
            int[] r4 = r0.zzmi
            r16 = r4[r12]
            r4 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = r4 & r15
            int r17 = r4 >>> 20
            r4 = 0
            r0 = r20
            boolean r7 = r0.zzmq
            if (r7 != 0) goto L_0x06f7
            r7 = 17
            r0 = r17
            if (r0 > r7) goto L_0x06f7
            r0 = r20
            int[] r4 = r0.zzmi
            int r7 = r12 + 2
            r10 = r4[r7]
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r7 = r10 & r4
            if (r7 == r8) goto L_0x06f3
            long r0 = (long) r7
            r18 = r0
            r0 = r21
            r1 = r18
            int r4 = r14.getInt(r0, r1)
        L_0x0068:
            r6 = 1
            int r8 = r10 >>> 20
            int r6 = r6 << r8
            r10 = r6
            r11 = r4
            r8 = r7
        L_0x006f:
            if (r9 == 0) goto L_0x0096
            r0 = r20
            com.google.android.gms.internal.clearcut.zzbu<?> r4 = r0.zzmy
            int r4 = r4.zza((java.util.Map.Entry<?, ?>) r9)
            r0 = r16
            if (r4 > r0) goto L_0x0096
            r0 = r20
            com.google.android.gms.internal.clearcut.zzbu<?> r4 = r0.zzmy
            r0 = r22
            r4.zza((com.google.android.gms.internal.clearcut.zzfr) r0, (java.util.Map.Entry<?, ?>) r9)
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L_0x0094
            java.lang.Object r4 = r5.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
        L_0x0092:
            r9 = r4
            goto L_0x006f
        L_0x0094:
            r4 = 0
            goto L_0x0092
        L_0x0096:
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r15
            long r6 = (long) r4
            switch(r17) {
                case 0: goto L_0x00a3;
                case 1: goto L_0x00b5;
                case 2: goto L_0x00c7;
                case 3: goto L_0x00d9;
                case 4: goto L_0x00eb;
                case 5: goto L_0x00fd;
                case 6: goto L_0x010f;
                case 7: goto L_0x0122;
                case 8: goto L_0x0135;
                case 9: goto L_0x0148;
                case 10: goto L_0x0161;
                case 11: goto L_0x0176;
                case 12: goto L_0x0189;
                case 13: goto L_0x019c;
                case 14: goto L_0x01af;
                case 15: goto L_0x01c2;
                case 16: goto L_0x01d5;
                case 17: goto L_0x01e8;
                case 18: goto L_0x0201;
                case 19: goto L_0x0217;
                case 20: goto L_0x022d;
                case 21: goto L_0x0243;
                case 22: goto L_0x0259;
                case 23: goto L_0x026f;
                case 24: goto L_0x0285;
                case 25: goto L_0x029b;
                case 26: goto L_0x02b1;
                case 27: goto L_0x02c6;
                case 28: goto L_0x02e1;
                case 29: goto L_0x02f6;
                case 30: goto L_0x030c;
                case 31: goto L_0x0322;
                case 32: goto L_0x0338;
                case 33: goto L_0x034e;
                case 34: goto L_0x0364;
                case 35: goto L_0x037a;
                case 36: goto L_0x0390;
                case 37: goto L_0x03a6;
                case 38: goto L_0x03bc;
                case 39: goto L_0x03d2;
                case 40: goto L_0x03e8;
                case 41: goto L_0x03fe;
                case 42: goto L_0x0414;
                case 43: goto L_0x042a;
                case 44: goto L_0x0440;
                case 45: goto L_0x0456;
                case 46: goto L_0x046c;
                case 47: goto L_0x0482;
                case 48: goto L_0x0498;
                case 49: goto L_0x04ae;
                case 50: goto L_0x04c9;
                case 51: goto L_0x04da;
                case 52: goto L_0x04f5;
                case 53: goto L_0x0510;
                case 54: goto L_0x052b;
                case 55: goto L_0x0546;
                case 56: goto L_0x0561;
                case 57: goto L_0x057c;
                case 58: goto L_0x0597;
                case 59: goto L_0x05b2;
                case 60: goto L_0x05cd;
                case 61: goto L_0x05ee;
                case 62: goto L_0x060b;
                case 63: goto L_0x0626;
                case 64: goto L_0x0641;
                case 65: goto L_0x065c;
                case 66: goto L_0x0677;
                case 67: goto L_0x0692;
                case 68: goto L_0x06ad;
                default: goto L_0x009e;
            }
        L_0x009e:
            int r4 = r12 + 4
            r12 = r4
            r6 = r11
            goto L_0x002e
        L_0x00a3:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            double r6 = com.google.android.gms.internal.clearcut.zzfd.zzn(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (double) r6)
            goto L_0x009e
        L_0x00b5:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            float r4 = com.google.android.gms.internal.clearcut.zzfd.zzm(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (float) r4)
            goto L_0x009e
        L_0x00c7:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzi(r1, r6)
            goto L_0x009e
        L_0x00d9:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (long) r6)
            goto L_0x009e
        L_0x00eb:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzc((int) r1, (int) r4)
            goto L_0x009e
        L_0x00fd:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzc((int) r1, (long) r6)
            goto L_0x009e
        L_0x010f:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzf(r1, r4)
            goto L_0x009e
        L_0x0122:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            boolean r4 = com.google.android.gms.internal.clearcut.zzfd.zzl(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (boolean) r4)
            goto L_0x009e
        L_0x0135:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r16
            r1 = r22
            zza((int) r0, (java.lang.Object) r4, (com.google.android.gms.internal.clearcut.zzfr) r1)
            goto L_0x009e
        L_0x0148:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.clearcut.zzef r6 = r0.zzad(r12)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.clearcut.zzef) r6)
            goto L_0x009e
        L_0x0161:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            com.google.android.gms.internal.clearcut.zzbb r4 = (com.google.android.gms.internal.clearcut.zzbb) r4
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (com.google.android.gms.internal.clearcut.zzbb) r4)
            goto L_0x009e
        L_0x0176:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzd(r1, r4)
            goto L_0x009e
        L_0x0189:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzn(r1, r4)
            goto L_0x009e
        L_0x019c:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzm(r1, r4)
            goto L_0x009e
        L_0x01af:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzj(r1, r6)
            goto L_0x009e
        L_0x01c2:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zze(r1, r4)
            goto L_0x009e
        L_0x01d5:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (long) r6)
            goto L_0x009e
        L_0x01e8:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.clearcut.zzef r6 = r0.zzad(r12)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.clearcut.zzef) r6)
            goto L_0x009e
        L_0x0201:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zza((int) r10, (java.util.List<java.lang.Double>) r4, (com.google.android.gms.internal.clearcut.zzfr) r0, (boolean) r6)
            goto L_0x009e
        L_0x0217:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzb((int) r10, (java.util.List<java.lang.Float>) r4, (com.google.android.gms.internal.clearcut.zzfr) r0, (boolean) r6)
            goto L_0x009e
        L_0x022d:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzc(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0243:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzd(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0259:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzh(r10, r4, r0, r6)
            goto L_0x009e
        L_0x026f:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzf(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0285:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzk(r10, r4, r0, r6)
            goto L_0x009e
        L_0x029b:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzn(r10, r4, r0, r6)
            goto L_0x009e
        L_0x02b1:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zza((int) r10, (java.util.List<java.lang.String>) r4, (com.google.android.gms.internal.clearcut.zzfr) r0)
            goto L_0x009e
        L_0x02c6:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r20
            com.google.android.gms.internal.clearcut.zzef r6 = r0.zzad(r12)
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zza((int) r10, (java.util.List<?>) r4, (com.google.android.gms.internal.clearcut.zzfr) r0, (com.google.android.gms.internal.clearcut.zzef) r6)
            goto L_0x009e
        L_0x02e1:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzb(r10, r4, r0)
            goto L_0x009e
        L_0x02f6:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzi(r10, r4, r0, r6)
            goto L_0x009e
        L_0x030c:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzm(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0322:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzl(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0338:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzg(r10, r4, r0, r6)
            goto L_0x009e
        L_0x034e:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzj(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0364:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zze(r10, r4, r0, r6)
            goto L_0x009e
        L_0x037a:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zza((int) r10, (java.util.List<java.lang.Double>) r4, (com.google.android.gms.internal.clearcut.zzfr) r0, (boolean) r6)
            goto L_0x009e
        L_0x0390:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzb((int) r10, (java.util.List<java.lang.Float>) r4, (com.google.android.gms.internal.clearcut.zzfr) r0, (boolean) r6)
            goto L_0x009e
        L_0x03a6:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzc(r10, r4, r0, r6)
            goto L_0x009e
        L_0x03bc:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzd(r10, r4, r0, r6)
            goto L_0x009e
        L_0x03d2:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzh(r10, r4, r0, r6)
            goto L_0x009e
        L_0x03e8:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzf(r10, r4, r0, r6)
            goto L_0x009e
        L_0x03fe:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzk(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0414:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzn(r10, r4, r0, r6)
            goto L_0x009e
        L_0x042a:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzi(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0440:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzm(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0456:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzl(r10, r4, r0, r6)
            goto L_0x009e
        L_0x046c:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzg(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0482:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzj(r10, r4, r0, r6)
            goto L_0x009e
        L_0x0498:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zze(r10, r4, r0, r6)
            goto L_0x009e
        L_0x04ae:
            r0 = r20
            int[] r4 = r0.zzmi
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r20
            com.google.android.gms.internal.clearcut.zzef r6 = r0.zzad(r12)
            r0 = r22
            com.google.android.gms.internal.clearcut.zzeh.zzb((int) r10, (java.util.List<?>) r4, (com.google.android.gms.internal.clearcut.zzfr) r0, (com.google.android.gms.internal.clearcut.zzef) r6)
            goto L_0x009e
        L_0x04c9:
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            r1 = r22
            r2 = r16
            r0.zza((com.google.android.gms.internal.clearcut.zzfr) r1, (int) r2, (java.lang.Object) r4, (int) r12)
            goto L_0x009e
        L_0x04da:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            double r6 = zze(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (double) r6)
            goto L_0x009e
        L_0x04f5:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            float r4 = zzf(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (float) r4)
            goto L_0x009e
        L_0x0510:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzi(r1, r6)
            goto L_0x009e
        L_0x052b:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (long) r6)
            goto L_0x009e
        L_0x0546:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = zzg(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzc((int) r1, (int) r4)
            goto L_0x009e
        L_0x0561:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzc((int) r1, (long) r6)
            goto L_0x009e
        L_0x057c:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = zzg(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzf(r1, r4)
            goto L_0x009e
        L_0x0597:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            boolean r4 = zzi(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (boolean) r4)
            goto L_0x009e
        L_0x05b2:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r16
            r1 = r22
            zza((int) r0, (java.lang.Object) r4, (com.google.android.gms.internal.clearcut.zzfr) r1)
            goto L_0x009e
        L_0x05cd:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.clearcut.zzef r6 = r0.zzad(r12)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.clearcut.zzef) r6)
            goto L_0x009e
        L_0x05ee:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            com.google.android.gms.internal.clearcut.zzbb r4 = (com.google.android.gms.internal.clearcut.zzbb) r4
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (com.google.android.gms.internal.clearcut.zzbb) r4)
            goto L_0x009e
        L_0x060b:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = zzg(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzd(r1, r4)
            goto L_0x009e
        L_0x0626:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = zzg(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzn(r1, r4)
            goto L_0x009e
        L_0x0641:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = zzg(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzm(r1, r4)
            goto L_0x009e
        L_0x065c:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzj(r1, r6)
            goto L_0x009e
        L_0x0677:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            int r4 = zzg(r0, r6)
            r0 = r22
            r1 = r16
            r0.zze(r1, r4)
            goto L_0x009e
        L_0x0692:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            long r6 = zzh(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (long) r6)
            goto L_0x009e
        L_0x06ad:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x009e
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.clearcut.zzef r6 = r0.zzad(r12)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.clearcut.zzef) r6)
            goto L_0x009e
        L_0x06ce:
            r4 = 0
        L_0x06cf:
            if (r4 == 0) goto L_0x06e7
            r0 = r20
            com.google.android.gms.internal.clearcut.zzbu<?> r6 = r0.zzmy
            r0 = r22
            r6.zza((com.google.android.gms.internal.clearcut.zzfr) r0, (java.util.Map.Entry<?, ?>) r4)
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L_0x06ce
            java.lang.Object r4 = r5.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            goto L_0x06cf
        L_0x06e7:
            r0 = r20
            com.google.android.gms.internal.clearcut.zzex<?, ?> r4 = r0.zzmx
            r0 = r21
            r1 = r22
            zza(r4, r0, (com.google.android.gms.internal.clearcut.zzfr) r1)
            return
        L_0x06f3:
            r4 = r6
            r7 = r8
            goto L_0x0068
        L_0x06f7:
            r10 = r4
            r11 = r6
            goto L_0x006f
        L_0x06fb:
            r4 = r9
            goto L_0x06cf
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zzb(java.lang.Object, com.google.android.gms.internal.clearcut.zzfr):void");
    }

    private final void zzb(T t, T t2, int i) {
        int zzag = zzag(i);
        int i2 = this.zzmi[i];
        long j = (long) (zzag & 1048575);
        if (zza(t2, i2, i)) {
            Object zzo = zzfd.zzo(t, j);
            Object zzo2 = zzfd.zzo(t2, j);
            if (zzo != null && zzo2 != null) {
                zzfd.zza((Object) t, j, zzci.zza(zzo, zzo2));
                zzb(t, i2, i);
            } else if (zzo2 != null) {
                zzfd.zza((Object) t, j, zzo2);
                zzb(t, i2, i);
            }
        }
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zzfd.zzo(obj, j);
    }

    private static <T> double zze(T t, long j) {
        return ((Double) zzfd.zzo(t, j)).doubleValue();
    }

    private static <T> float zzf(T t, long j) {
        return ((Float) zzfd.zzo(t, j)).floatValue();
    }

    private static <T> int zzg(T t, long j) {
        return ((Integer) zzfd.zzo(t, j)).intValue();
    }

    private static <T> long zzh(T t, long j) {
        return ((Long) zzfd.zzo(t, j)).longValue();
    }

    private static <T> boolean zzi(T t, long j) {
        return ((Boolean) zzfd.zzo(t, j)).booleanValue();
    }

    private static zzey zzn(Object obj) {
        zzey zzey = ((zzcg) obj).zzjp;
        if (zzey != zzey.zzea()) {
            return zzey;
        }
        zzey zzeb = zzey.zzeb();
        ((zzcg) obj).zzjp = zzeb;
        return zzeb;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(T r12, T r13) {
        /*
            r11 = this;
            r1 = 1
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r0 = 0
            int[] r2 = r11.zzmi
            int r4 = r2.length
            r3 = r0
        L_0x0009:
            if (r3 >= r4) goto L_0x01cf
            int r2 = r11.zzag(r3)
            r5 = r2 & r10
            long r6 = (long) r5
            r5 = 267386880(0xff00000, float:2.3665827E-29)
            r2 = r2 & r5
            int r2 = r2 >>> 20
            switch(r2) {
                case 0: goto L_0x001e;
                case 1: goto L_0x0032;
                case 2: goto L_0x0044;
                case 3: goto L_0x0058;
                case 4: goto L_0x006c;
                case 5: goto L_0x007e;
                case 6: goto L_0x0092;
                case 7: goto L_0x00a5;
                case 8: goto L_0x00b8;
                case 9: goto L_0x00cf;
                case 10: goto L_0x00e6;
                case 11: goto L_0x00fd;
                case 12: goto L_0x0110;
                case 13: goto L_0x0123;
                case 14: goto L_0x0136;
                case 15: goto L_0x014b;
                case 16: goto L_0x015e;
                case 17: goto L_0x0173;
                case 18: goto L_0x018a;
                case 19: goto L_0x018a;
                case 20: goto L_0x018a;
                case 21: goto L_0x018a;
                case 22: goto L_0x018a;
                case 23: goto L_0x018a;
                case 24: goto L_0x018a;
                case 25: goto L_0x018a;
                case 26: goto L_0x018a;
                case 27: goto L_0x018a;
                case 28: goto L_0x018a;
                case 29: goto L_0x018a;
                case 30: goto L_0x018a;
                case 31: goto L_0x018a;
                case 32: goto L_0x018a;
                case 33: goto L_0x018a;
                case 34: goto L_0x018a;
                case 35: goto L_0x018a;
                case 36: goto L_0x018a;
                case 37: goto L_0x018a;
                case 38: goto L_0x018a;
                case 39: goto L_0x018a;
                case 40: goto L_0x018a;
                case 41: goto L_0x018a;
                case 42: goto L_0x018a;
                case 43: goto L_0x018a;
                case 44: goto L_0x018a;
                case 45: goto L_0x018a;
                case 46: goto L_0x018a;
                case 47: goto L_0x018a;
                case 48: goto L_0x018a;
                case 49: goto L_0x018a;
                case 50: goto L_0x0198;
                case 51: goto L_0x01a6;
                case 52: goto L_0x01a6;
                case 53: goto L_0x01a6;
                case 54: goto L_0x01a6;
                case 55: goto L_0x01a6;
                case 56: goto L_0x01a6;
                case 57: goto L_0x01a6;
                case 58: goto L_0x01a6;
                case 59: goto L_0x01a6;
                case 60: goto L_0x01a6;
                case 61: goto L_0x01a6;
                case 62: goto L_0x01a6;
                case 63: goto L_0x01a6;
                case 64: goto L_0x01a6;
                case 65: goto L_0x01a6;
                case 66: goto L_0x01a6;
                case 67: goto L_0x01a6;
                case 68: goto L_0x01a6;
                default: goto L_0x001a;
            }
        L_0x001a:
            r2 = r1
        L_0x001b:
            if (r2 != 0) goto L_0x01ca
        L_0x001d:
            return r0
        L_0x001e:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0030
            long r8 = com.google.android.gms.internal.clearcut.zzfd.zzk(r12, r6)
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0030:
            r2 = r0
            goto L_0x001b
        L_0x0032:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0042
            int r2 = com.google.android.gms.internal.clearcut.zzfd.zzj(r12, r6)
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x0042:
            r2 = r0
            goto L_0x001b
        L_0x0044:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0056
            long r8 = com.google.android.gms.internal.clearcut.zzfd.zzk(r12, r6)
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0056:
            r2 = r0
            goto L_0x001b
        L_0x0058:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x006a
            long r8 = com.google.android.gms.internal.clearcut.zzfd.zzk(r12, r6)
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x006a:
            r2 = r0
            goto L_0x001b
        L_0x006c:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x007c
            int r2 = com.google.android.gms.internal.clearcut.zzfd.zzj(r12, r6)
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x007c:
            r2 = r0
            goto L_0x001b
        L_0x007e:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0090
            long r8 = com.google.android.gms.internal.clearcut.zzfd.zzk(r12, r6)
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0090:
            r2 = r0
            goto L_0x001b
        L_0x0092:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00a2
            int r2 = com.google.android.gms.internal.clearcut.zzfd.zzj(r12, r6)
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x00a2:
            r2 = r0
            goto L_0x001b
        L_0x00a5:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00b5
            boolean r2 = com.google.android.gms.internal.clearcut.zzfd.zzl(r12, r6)
            boolean r5 = com.google.android.gms.internal.clearcut.zzfd.zzl(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x00b5:
            r2 = r0
            goto L_0x001b
        L_0x00b8:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00cc
            java.lang.Object r2 = com.google.android.gms.internal.clearcut.zzfd.zzo(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r13, r6)
            boolean r2 = com.google.android.gms.internal.clearcut.zzeh.zzd((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x00cc:
            r2 = r0
            goto L_0x001b
        L_0x00cf:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00e3
            java.lang.Object r2 = com.google.android.gms.internal.clearcut.zzfd.zzo(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r13, r6)
            boolean r2 = com.google.android.gms.internal.clearcut.zzeh.zzd((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x00e3:
            r2 = r0
            goto L_0x001b
        L_0x00e6:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00fa
            java.lang.Object r2 = com.google.android.gms.internal.clearcut.zzfd.zzo(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r13, r6)
            boolean r2 = com.google.android.gms.internal.clearcut.zzeh.zzd((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x00fa:
            r2 = r0
            goto L_0x001b
        L_0x00fd:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x010d
            int r2 = com.google.android.gms.internal.clearcut.zzfd.zzj(r12, r6)
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x010d:
            r2 = r0
            goto L_0x001b
        L_0x0110:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0120
            int r2 = com.google.android.gms.internal.clearcut.zzfd.zzj(r12, r6)
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x0120:
            r2 = r0
            goto L_0x001b
        L_0x0123:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0133
            int r2 = com.google.android.gms.internal.clearcut.zzfd.zzj(r12, r6)
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x0133:
            r2 = r0
            goto L_0x001b
        L_0x0136:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0148
            long r8 = com.google.android.gms.internal.clearcut.zzfd.zzk(r12, r6)
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0148:
            r2 = r0
            goto L_0x001b
        L_0x014b:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x015b
            int r2 = com.google.android.gms.internal.clearcut.zzfd.zzj(r12, r6)
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x015b:
            r2 = r0
            goto L_0x001b
        L_0x015e:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0170
            long r8 = com.google.android.gms.internal.clearcut.zzfd.zzk(r12, r6)
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0170:
            r2 = r0
            goto L_0x001b
        L_0x0173:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0187
            java.lang.Object r2 = com.google.android.gms.internal.clearcut.zzfd.zzo(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r13, r6)
            boolean r2 = com.google.android.gms.internal.clearcut.zzeh.zzd((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x0187:
            r2 = r0
            goto L_0x001b
        L_0x018a:
            java.lang.Object r2 = com.google.android.gms.internal.clearcut.zzfd.zzo(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r13, r6)
            boolean r2 = com.google.android.gms.internal.clearcut.zzeh.zzd((java.lang.Object) r2, (java.lang.Object) r5)
            goto L_0x001b
        L_0x0198:
            java.lang.Object r2 = com.google.android.gms.internal.clearcut.zzfd.zzo(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r13, r6)
            boolean r2 = com.google.android.gms.internal.clearcut.zzeh.zzd((java.lang.Object) r2, (java.lang.Object) r5)
            goto L_0x001b
        L_0x01a6:
            int r2 = r11.zzah(r3)
            r5 = r2 & r10
            long r8 = (long) r5
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r12, r8)
            r2 = r2 & r10
            long r8 = (long) r2
            int r2 = com.google.android.gms.internal.clearcut.zzfd.zzj(r13, r8)
            if (r5 != r2) goto L_0x01c7
            java.lang.Object r2 = com.google.android.gms.internal.clearcut.zzfd.zzo(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r13, r6)
            boolean r2 = com.google.android.gms.internal.clearcut.zzeh.zzd((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x01c7:
            r2 = r0
            goto L_0x001b
        L_0x01ca:
            int r2 = r3 + 4
            r3 = r2
            goto L_0x0009
        L_0x01cf:
            com.google.android.gms.internal.clearcut.zzex<?, ?> r2 = r11.zzmx
            java.lang.Object r2 = r2.zzq(r12)
            com.google.android.gms.internal.clearcut.zzex<?, ?> r3 = r11.zzmx
            java.lang.Object r3 = r3.zzq(r13)
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x001d
            boolean r0 = r11.zzmo
            if (r0 == 0) goto L_0x01f7
            com.google.android.gms.internal.clearcut.zzbu<?> r0 = r11.zzmy
            com.google.android.gms.internal.clearcut.zzby r0 = r0.zza((java.lang.Object) r12)
            com.google.android.gms.internal.clearcut.zzbu<?> r1 = r11.zzmy
            com.google.android.gms.internal.clearcut.zzby r1 = r1.zza((java.lang.Object) r13)
            boolean r0 = r0.equals(r1)
            goto L_0x001d
        L_0x01f7:
            r0 = r1
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int hashCode(T r10) {
        /*
            r9 = this;
            r1 = 37
            r0 = 0
            int[] r2 = r9.zzmi
            int r4 = r2.length
            r3 = r0
            r2 = r0
        L_0x0008:
            if (r3 >= r4) goto L_0x0254
            int r0 = r9.zzag(r3)
            int[] r5 = r9.zzmi
            r5 = r5[r3]
            r6 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r6 & r0
            long r6 = (long) r6
            r8 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r8
            int r0 = r0 >>> 20
            switch(r0) {
                case 0: goto L_0x0024;
                case 1: goto L_0x0034;
                case 2: goto L_0x0040;
                case 3: goto L_0x004c;
                case 4: goto L_0x0058;
                case 5: goto L_0x0060;
                case 6: goto L_0x006c;
                case 7: goto L_0x0074;
                case 8: goto L_0x0080;
                case 9: goto L_0x008e;
                case 10: goto L_0x009c;
                case 11: goto L_0x00a9;
                case 12: goto L_0x00b2;
                case 13: goto L_0x00bb;
                case 14: goto L_0x00c4;
                case 15: goto L_0x00d1;
                case 16: goto L_0x00da;
                case 17: goto L_0x00e7;
                case 18: goto L_0x00f6;
                case 19: goto L_0x00f6;
                case 20: goto L_0x00f6;
                case 21: goto L_0x00f6;
                case 22: goto L_0x00f6;
                case 23: goto L_0x00f6;
                case 24: goto L_0x00f6;
                case 25: goto L_0x00f6;
                case 26: goto L_0x00f6;
                case 27: goto L_0x00f6;
                case 28: goto L_0x00f6;
                case 29: goto L_0x00f6;
                case 30: goto L_0x00f6;
                case 31: goto L_0x00f6;
                case 32: goto L_0x00f6;
                case 33: goto L_0x00f6;
                case 34: goto L_0x00f6;
                case 35: goto L_0x00f6;
                case 36: goto L_0x00f6;
                case 37: goto L_0x00f6;
                case 38: goto L_0x00f6;
                case 39: goto L_0x00f6;
                case 40: goto L_0x00f6;
                case 41: goto L_0x00f6;
                case 42: goto L_0x00f6;
                case 43: goto L_0x00f6;
                case 44: goto L_0x00f6;
                case 45: goto L_0x00f6;
                case 46: goto L_0x00f6;
                case 47: goto L_0x00f6;
                case 48: goto L_0x00f6;
                case 49: goto L_0x00f6;
                case 50: goto L_0x0103;
                case 51: goto L_0x0110;
                case 52: goto L_0x0127;
                case 53: goto L_0x013a;
                case 54: goto L_0x014d;
                case 55: goto L_0x0160;
                case 56: goto L_0x016f;
                case 57: goto L_0x0182;
                case 58: goto L_0x0191;
                case 59: goto L_0x01a4;
                case 60: goto L_0x01b9;
                case 61: goto L_0x01cc;
                case 62: goto L_0x01df;
                case 63: goto L_0x01ee;
                case 64: goto L_0x01fd;
                case 65: goto L_0x020c;
                case 66: goto L_0x021f;
                case 67: goto L_0x022e;
                case 68: goto L_0x0241;
                default: goto L_0x001f;
            }
        L_0x001f:
            r0 = r2
        L_0x0020:
            int r3 = r3 + 4
            r2 = r0
            goto L_0x0008
        L_0x0024:
            int r0 = r2 * 53
            double r6 = com.google.android.gms.internal.clearcut.zzfd.zzn(r10, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r2 = com.google.android.gms.internal.clearcut.zzci.zzl(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0034:
            int r0 = r2 * 53
            float r2 = com.google.android.gms.internal.clearcut.zzfd.zzm(r10, r6)
            int r2 = java.lang.Float.floatToIntBits(r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0040:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6)
            int r2 = com.google.android.gms.internal.clearcut.zzci.zzl(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x004c:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6)
            int r2 = com.google.android.gms.internal.clearcut.zzci.zzl(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0058:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0060:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6)
            int r2 = com.google.android.gms.internal.clearcut.zzci.zzl(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x006c:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0074:
            int r0 = r2 * 53
            boolean r2 = com.google.android.gms.internal.clearcut.zzfd.zzl(r10, r6)
            int r2 = com.google.android.gms.internal.clearcut.zzci.zzc(r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0080:
            int r2 = r2 * 53
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            java.lang.String r0 = (java.lang.String) r0
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x008e:
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            if (r0 == 0) goto L_0x0276
            int r0 = r0.hashCode()
        L_0x0098:
            int r2 = r2 * 53
            int r0 = r0 + r2
            goto L_0x0020
        L_0x009c:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00a9:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00b2:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00bb:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00c4:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6)
            int r2 = com.google.android.gms.internal.clearcut.zzci.zzl(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00d1:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00da:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6)
            int r2 = com.google.android.gms.internal.clearcut.zzci.zzl(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00e7:
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            if (r0 == 0) goto L_0x0273
            int r0 = r0.hashCode()
        L_0x00f1:
            int r2 = r2 * 53
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00f6:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0103:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0110:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            double r6 = zze(r10, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r2 = com.google.android.gms.internal.clearcut.zzci.zzl(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0127:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            float r2 = zzf(r10, r6)
            int r2 = java.lang.Float.floatToIntBits(r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x013a:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zzh(r10, r6)
            int r2 = com.google.android.gms.internal.clearcut.zzci.zzl(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x014d:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zzh(r10, r6)
            int r2 = com.google.android.gms.internal.clearcut.zzci.zzl(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0160:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzg(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x016f:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zzh(r10, r6)
            int r2 = com.google.android.gms.internal.clearcut.zzci.zzl(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0182:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzg(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0191:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            boolean r2 = zzi(r10, r6)
            int r2 = com.google.android.gms.internal.clearcut.zzci.zzc(r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01a4:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r2 = r2 * 53
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            java.lang.String r0 = (java.lang.String) r0
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01b9:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            int r2 = r2 * 53
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01cc:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01df:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzg(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01ee:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzg(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01fd:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzg(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x020c:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zzh(r10, r6)
            int r2 = com.google.android.gms.internal.clearcut.zzci.zzl(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x021f:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzg(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x022e:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zzh(r10, r6)
            int r2 = com.google.android.gms.internal.clearcut.zzci.zzl(r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0241:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            int r2 = r2 * 53
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0254:
            int r0 = r2 * 53
            com.google.android.gms.internal.clearcut.zzex<?, ?> r1 = r9.zzmx
            java.lang.Object r1 = r1.zzq(r10)
            int r1 = r1.hashCode()
            int r0 = r0 + r1
            boolean r1 = r9.zzmo
            if (r1 == 0) goto L_0x0272
            int r0 = r0 * 53
            com.google.android.gms.internal.clearcut.zzbu<?> r1 = r9.zzmy
            com.google.android.gms.internal.clearcut.zzby r1 = r1.zza((java.lang.Object) r10)
            int r1 = r1.hashCode()
            int r0 = r0 + r1
        L_0x0272:
            return r0
        L_0x0273:
            r0 = r1
            goto L_0x00f1
        L_0x0276:
            r0 = r1
            goto L_0x0098
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.hashCode(java.lang.Object):int");
    }

    public final T newInstance() {
        return this.zzmv.newInstance(this.zzmn);
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 547 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r11, com.google.android.gms.internal.clearcut.zzfr r12) throws java.io.IOException {
        /*
            r10 = this;
            int r0 = r12.zzaj()
            int r1 = com.google.android.gms.internal.clearcut.zzcg.zzg.zzkp
            if (r0 != r1) goto L_0x060a
            com.google.android.gms.internal.clearcut.zzex<?, ?> r0 = r10.zzmx
            zza(r0, r11, (com.google.android.gms.internal.clearcut.zzfr) r12)
            r1 = 0
            r0 = 0
            boolean r2 = r10.zzmo
            if (r2 == 0) goto L_0x0029
            com.google.android.gms.internal.clearcut.zzbu<?> r2 = r10.zzmy
            com.google.android.gms.internal.clearcut.zzby r2 = r2.zza((java.lang.Object) r11)
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L_0x0029
            java.util.Iterator r1 = r2.descendingIterator()
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x0029:
            int[] r2 = r10.zzmi
            int r2 = r2.length
            int r2 = r2 + -4
            r3 = r2
        L_0x002f:
            if (r3 < 0) goto L_0x05f6
            int r4 = r10.zzag(r3)
            int[] r2 = r10.zzmi
            r5 = r2[r3]
            r2 = r0
        L_0x003a:
            if (r2 == 0) goto L_0x0059
            com.google.android.gms.internal.clearcut.zzbu<?> r0 = r10.zzmy
            int r0 = r0.zza((java.util.Map.Entry<?, ?>) r2)
            if (r0 <= r5) goto L_0x0059
            com.google.android.gms.internal.clearcut.zzbu<?> r0 = r10.zzmy
            r0.zza((com.google.android.gms.internal.clearcut.zzfr) r12, (java.util.Map.Entry<?, ?>) r2)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x0057
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x0055:
            r2 = r0
            goto L_0x003a
        L_0x0057:
            r0 = 0
            goto L_0x0055
        L_0x0059:
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r4
            int r0 = r0 >>> 20
            switch(r0) {
                case 0: goto L_0x0065;
                case 1: goto L_0x0078;
                case 2: goto L_0x008b;
                case 3: goto L_0x009e;
                case 4: goto L_0x00b1;
                case 5: goto L_0x00c4;
                case 6: goto L_0x00d7;
                case 7: goto L_0x00eb;
                case 8: goto L_0x00ff;
                case 9: goto L_0x0113;
                case 10: goto L_0x012b;
                case 11: goto L_0x0141;
                case 12: goto L_0x0155;
                case 13: goto L_0x0169;
                case 14: goto L_0x017d;
                case 15: goto L_0x0191;
                case 16: goto L_0x01a5;
                case 17: goto L_0x01b9;
                case 18: goto L_0x01d1;
                case 19: goto L_0x01e6;
                case 20: goto L_0x01fb;
                case 21: goto L_0x0210;
                case 22: goto L_0x0225;
                case 23: goto L_0x023a;
                case 24: goto L_0x024f;
                case 25: goto L_0x0264;
                case 26: goto L_0x0279;
                case 27: goto L_0x028d;
                case 28: goto L_0x02a5;
                case 29: goto L_0x02b9;
                case 30: goto L_0x02ce;
                case 31: goto L_0x02e3;
                case 32: goto L_0x02f8;
                case 33: goto L_0x030d;
                case 34: goto L_0x0322;
                case 35: goto L_0x0337;
                case 36: goto L_0x034c;
                case 37: goto L_0x0361;
                case 38: goto L_0x0376;
                case 39: goto L_0x038b;
                case 40: goto L_0x03a0;
                case 41: goto L_0x03b5;
                case 42: goto L_0x03ca;
                case 43: goto L_0x03df;
                case 44: goto L_0x03f4;
                case 45: goto L_0x0409;
                case 46: goto L_0x041e;
                case 47: goto L_0x0433;
                case 48: goto L_0x0448;
                case 49: goto L_0x045d;
                case 50: goto L_0x0475;
                case 51: goto L_0x0483;
                case 52: goto L_0x0497;
                case 53: goto L_0x04ab;
                case 54: goto L_0x04bf;
                case 55: goto L_0x04d3;
                case 56: goto L_0x04e7;
                case 57: goto L_0x04fb;
                case 58: goto L_0x050f;
                case 59: goto L_0x0523;
                case 60: goto L_0x0537;
                case 61: goto L_0x054f;
                case 62: goto L_0x0565;
                case 63: goto L_0x0579;
                case 64: goto L_0x058d;
                case 65: goto L_0x05a1;
                case 66: goto L_0x05b5;
                case 67: goto L_0x05c9;
                case 68: goto L_0x05dd;
                default: goto L_0x0061;
            }
        L_0x0061:
            int r3 = r3 + -4
            r0 = r2
            goto L_0x002f
        L_0x0065:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            double r6 = com.google.android.gms.internal.clearcut.zzfd.zzn(r11, r6)
            r12.zza((int) r5, (double) r6)
            goto L_0x0061
        L_0x0078:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            float r0 = com.google.android.gms.internal.clearcut.zzfd.zzm(r11, r6)
            r12.zza((int) r5, (float) r0)
            goto L_0x0061
        L_0x008b:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)
            r12.zzi(r5, r6)
            goto L_0x0061
        L_0x009e:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)
            r12.zza((int) r5, (long) r6)
            goto L_0x0061
        L_0x00b1:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)
            r12.zzc((int) r5, (int) r0)
            goto L_0x0061
        L_0x00c4:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)
            r12.zzc((int) r5, (long) r6)
            goto L_0x0061
        L_0x00d7:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)
            r12.zzf(r5, r0)
            goto L_0x0061
        L_0x00eb:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            boolean r0 = com.google.android.gms.internal.clearcut.zzfd.zzl(r11, r6)
            r12.zzb((int) r5, (boolean) r0)
            goto L_0x0061
        L_0x00ff:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.clearcut.zzfr) r12)
            goto L_0x0061
        L_0x0113:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            com.google.android.gms.internal.clearcut.zzef r4 = r10.zzad(r3)
            r12.zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.clearcut.zzef) r4)
            goto L_0x0061
        L_0x012b:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            com.google.android.gms.internal.clearcut.zzbb r0 = (com.google.android.gms.internal.clearcut.zzbb) r0
            r12.zza((int) r5, (com.google.android.gms.internal.clearcut.zzbb) r0)
            goto L_0x0061
        L_0x0141:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)
            r12.zzd(r5, r0)
            goto L_0x0061
        L_0x0155:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)
            r12.zzn(r5, r0)
            goto L_0x0061
        L_0x0169:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)
            r12.zzm(r5, r0)
            goto L_0x0061
        L_0x017d:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)
            r12.zzj(r5, r6)
            goto L_0x0061
        L_0x0191:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)
            r12.zze(r5, r0)
            goto L_0x0061
        L_0x01a5:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)
            r12.zzb((int) r5, (long) r6)
            goto L_0x0061
        L_0x01b9:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            com.google.android.gms.internal.clearcut.zzef r4 = r10.zzad(r3)
            r12.zzb((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.clearcut.zzef) r4)
            goto L_0x0061
        L_0x01d1:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.clearcut.zzeh.zza((int) r5, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.clearcut.zzfr) r12, (boolean) r4)
            goto L_0x0061
        L_0x01e6:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzb((int) r5, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.clearcut.zzfr) r12, (boolean) r4)
            goto L_0x0061
        L_0x01fb:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzc(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0210:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzd(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0225:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzh(r5, r0, r12, r4)
            goto L_0x0061
        L_0x023a:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzf(r5, r0, r12, r4)
            goto L_0x0061
        L_0x024f:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzk(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0264:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzn(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0279:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.clearcut.zzeh.zza((int) r5, (java.util.List<java.lang.String>) r0, (com.google.android.gms.internal.clearcut.zzfr) r12)
            goto L_0x0061
        L_0x028d:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.clearcut.zzef r4 = r10.zzad(r3)
            com.google.android.gms.internal.clearcut.zzeh.zza((int) r5, (java.util.List<?>) r0, (com.google.android.gms.internal.clearcut.zzfr) r12, (com.google.android.gms.internal.clearcut.zzef) r4)
            goto L_0x0061
        L_0x02a5:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.clearcut.zzeh.zzb(r5, r0, r12)
            goto L_0x0061
        L_0x02b9:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzi(r5, r0, r12, r4)
            goto L_0x0061
        L_0x02ce:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzm(r5, r0, r12, r4)
            goto L_0x0061
        L_0x02e3:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzl(r5, r0, r12, r4)
            goto L_0x0061
        L_0x02f8:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzg(r5, r0, r12, r4)
            goto L_0x0061
        L_0x030d:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzj(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0322:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.clearcut.zzeh.zze(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0337:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.clearcut.zzeh.zza((int) r5, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.clearcut.zzfr) r12, (boolean) r4)
            goto L_0x0061
        L_0x034c:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzb((int) r5, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.clearcut.zzfr) r12, (boolean) r4)
            goto L_0x0061
        L_0x0361:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzc(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0376:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzd(r5, r0, r12, r4)
            goto L_0x0061
        L_0x038b:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzh(r5, r0, r12, r4)
            goto L_0x0061
        L_0x03a0:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzf(r5, r0, r12, r4)
            goto L_0x0061
        L_0x03b5:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzk(r5, r0, r12, r4)
            goto L_0x0061
        L_0x03ca:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzn(r5, r0, r12, r4)
            goto L_0x0061
        L_0x03df:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzi(r5, r0, r12, r4)
            goto L_0x0061
        L_0x03f4:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzm(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0409:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzl(r5, r0, r12, r4)
            goto L_0x0061
        L_0x041e:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzg(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0433:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzj(r5, r0, r12, r4)
            goto L_0x0061
        L_0x0448:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.clearcut.zzeh.zze(r5, r0, r12, r4)
            goto L_0x0061
        L_0x045d:
            int[] r0 = r10.zzmi
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.clearcut.zzef r4 = r10.zzad(r3)
            com.google.android.gms.internal.clearcut.zzeh.zzb((int) r5, (java.util.List<?>) r0, (com.google.android.gms.internal.clearcut.zzfr) r12, (com.google.android.gms.internal.clearcut.zzef) r4)
            goto L_0x0061
        L_0x0475:
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            r10.zza((com.google.android.gms.internal.clearcut.zzfr) r12, (int) r5, (java.lang.Object) r0, (int) r3)
            goto L_0x0061
        L_0x0483:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            double r6 = zze(r11, r6)
            r12.zza((int) r5, (double) r6)
            goto L_0x0061
        L_0x0497:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            float r0 = zzf(r11, r6)
            r12.zza((int) r5, (float) r0)
            goto L_0x0061
        L_0x04ab:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzh(r11, r6)
            r12.zzi(r5, r6)
            goto L_0x0061
        L_0x04bf:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzh(r11, r6)
            r12.zza((int) r5, (long) r6)
            goto L_0x0061
        L_0x04d3:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzg(r11, r6)
            r12.zzc((int) r5, (int) r0)
            goto L_0x0061
        L_0x04e7:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzh(r11, r6)
            r12.zzc((int) r5, (long) r6)
            goto L_0x0061
        L_0x04fb:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzg(r11, r6)
            r12.zzf(r5, r0)
            goto L_0x0061
        L_0x050f:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            boolean r0 = zzi(r11, r6)
            r12.zzb((int) r5, (boolean) r0)
            goto L_0x0061
        L_0x0523:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.clearcut.zzfr) r12)
            goto L_0x0061
        L_0x0537:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            com.google.android.gms.internal.clearcut.zzef r4 = r10.zzad(r3)
            r12.zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.clearcut.zzef) r4)
            goto L_0x0061
        L_0x054f:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            com.google.android.gms.internal.clearcut.zzbb r0 = (com.google.android.gms.internal.clearcut.zzbb) r0
            r12.zza((int) r5, (com.google.android.gms.internal.clearcut.zzbb) r0)
            goto L_0x0061
        L_0x0565:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzg(r11, r6)
            r12.zzd(r5, r0)
            goto L_0x0061
        L_0x0579:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzg(r11, r6)
            r12.zzn(r5, r0)
            goto L_0x0061
        L_0x058d:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzg(r11, r6)
            r12.zzm(r5, r0)
            goto L_0x0061
        L_0x05a1:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzh(r11, r6)
            r12.zzj(r5, r6)
            goto L_0x0061
        L_0x05b5:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzg(r11, r6)
            r12.zze(r5, r0)
            goto L_0x0061
        L_0x05c9:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zzh(r11, r6)
            r12.zzb((int) r5, (long) r6)
            goto L_0x0061
        L_0x05dd:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0061
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            com.google.android.gms.internal.clearcut.zzef r4 = r10.zzad(r3)
            r12.zzb((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.clearcut.zzef) r4)
            goto L_0x0061
        L_0x05f5:
            r0 = 0
        L_0x05f6:
            if (r0 == 0) goto L_0x0c0f
            com.google.android.gms.internal.clearcut.zzbu<?> r2 = r10.zzmy
            r2.zza((com.google.android.gms.internal.clearcut.zzfr) r12, (java.util.Map.Entry<?, ?>) r0)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x05f5
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            goto L_0x05f6
        L_0x060a:
            boolean r0 = r10.zzmq
            if (r0 == 0) goto L_0x0c10
            r1 = 0
            r0 = 0
            boolean r2 = r10.zzmo
            if (r2 == 0) goto L_0x062a
            com.google.android.gms.internal.clearcut.zzbu<?> r2 = r10.zzmy
            com.google.android.gms.internal.clearcut.zzby r2 = r2.zza((java.lang.Object) r11)
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L_0x062a
            java.util.Iterator r1 = r2.iterator()
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x062a:
            int[] r2 = r10.zzmi
            int r4 = r2.length
            r2 = 0
            r3 = r2
        L_0x062f:
            if (r3 >= r4) goto L_0x0bf6
            int r5 = r10.zzag(r3)
            int[] r2 = r10.zzmi
            r6 = r2[r3]
            r2 = r0
        L_0x063a:
            if (r2 == 0) goto L_0x0659
            com.google.android.gms.internal.clearcut.zzbu<?> r0 = r10.zzmy
            int r0 = r0.zza((java.util.Map.Entry<?, ?>) r2)
            if (r0 > r6) goto L_0x0659
            com.google.android.gms.internal.clearcut.zzbu<?> r0 = r10.zzmy
            r0.zza((com.google.android.gms.internal.clearcut.zzfr) r12, (java.util.Map.Entry<?, ?>) r2)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x0657
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x0655:
            r2 = r0
            goto L_0x063a
        L_0x0657:
            r0 = 0
            goto L_0x0655
        L_0x0659:
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r5
            int r0 = r0 >>> 20
            switch(r0) {
                case 0: goto L_0x0665;
                case 1: goto L_0x0678;
                case 2: goto L_0x068b;
                case 3: goto L_0x069e;
                case 4: goto L_0x06b1;
                case 5: goto L_0x06c4;
                case 6: goto L_0x06d7;
                case 7: goto L_0x06eb;
                case 8: goto L_0x06ff;
                case 9: goto L_0x0713;
                case 10: goto L_0x072b;
                case 11: goto L_0x0741;
                case 12: goto L_0x0755;
                case 13: goto L_0x0769;
                case 14: goto L_0x077d;
                case 15: goto L_0x0791;
                case 16: goto L_0x07a5;
                case 17: goto L_0x07b9;
                case 18: goto L_0x07d1;
                case 19: goto L_0x07e6;
                case 20: goto L_0x07fb;
                case 21: goto L_0x0810;
                case 22: goto L_0x0825;
                case 23: goto L_0x083a;
                case 24: goto L_0x084f;
                case 25: goto L_0x0864;
                case 26: goto L_0x0879;
                case 27: goto L_0x088d;
                case 28: goto L_0x08a5;
                case 29: goto L_0x08b9;
                case 30: goto L_0x08ce;
                case 31: goto L_0x08e3;
                case 32: goto L_0x08f8;
                case 33: goto L_0x090d;
                case 34: goto L_0x0922;
                case 35: goto L_0x0937;
                case 36: goto L_0x094c;
                case 37: goto L_0x0961;
                case 38: goto L_0x0976;
                case 39: goto L_0x098b;
                case 40: goto L_0x09a0;
                case 41: goto L_0x09b5;
                case 42: goto L_0x09ca;
                case 43: goto L_0x09df;
                case 44: goto L_0x09f4;
                case 45: goto L_0x0a09;
                case 46: goto L_0x0a1e;
                case 47: goto L_0x0a33;
                case 48: goto L_0x0a48;
                case 49: goto L_0x0a5d;
                case 50: goto L_0x0a75;
                case 51: goto L_0x0a83;
                case 52: goto L_0x0a97;
                case 53: goto L_0x0aab;
                case 54: goto L_0x0abf;
                case 55: goto L_0x0ad3;
                case 56: goto L_0x0ae7;
                case 57: goto L_0x0afb;
                case 58: goto L_0x0b0f;
                case 59: goto L_0x0b23;
                case 60: goto L_0x0b37;
                case 61: goto L_0x0b4f;
                case 62: goto L_0x0b65;
                case 63: goto L_0x0b79;
                case 64: goto L_0x0b8d;
                case 65: goto L_0x0ba1;
                case 66: goto L_0x0bb5;
                case 67: goto L_0x0bc9;
                case 68: goto L_0x0bdd;
                default: goto L_0x0661;
            }
        L_0x0661:
            int r3 = r3 + 4
            r0 = r2
            goto L_0x062f
        L_0x0665:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            double r8 = com.google.android.gms.internal.clearcut.zzfd.zzn(r11, r8)
            r12.zza((int) r6, (double) r8)
            goto L_0x0661
        L_0x0678:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            float r0 = com.google.android.gms.internal.clearcut.zzfd.zzm(r11, r8)
            r12.zza((int) r6, (float) r0)
            goto L_0x0661
        L_0x068b:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r8)
            r12.zzi(r6, r8)
            goto L_0x0661
        L_0x069e:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r8)
            r12.zza((int) r6, (long) r8)
            goto L_0x0661
        L_0x06b1:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r8)
            r12.zzc((int) r6, (int) r0)
            goto L_0x0661
        L_0x06c4:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r8)
            r12.zzc((int) r6, (long) r8)
            goto L_0x0661
        L_0x06d7:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r8)
            r12.zzf(r6, r0)
            goto L_0x0661
        L_0x06eb:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            boolean r0 = com.google.android.gms.internal.clearcut.zzfd.zzl(r11, r8)
            r12.zzb((int) r6, (boolean) r0)
            goto L_0x0661
        L_0x06ff:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.clearcut.zzfr) r12)
            goto L_0x0661
        L_0x0713:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            com.google.android.gms.internal.clearcut.zzef r5 = r10.zzad(r3)
            r12.zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.clearcut.zzef) r5)
            goto L_0x0661
        L_0x072b:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            com.google.android.gms.internal.clearcut.zzbb r0 = (com.google.android.gms.internal.clearcut.zzbb) r0
            r12.zza((int) r6, (com.google.android.gms.internal.clearcut.zzbb) r0)
            goto L_0x0661
        L_0x0741:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r8)
            r12.zzd(r6, r0)
            goto L_0x0661
        L_0x0755:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r8)
            r12.zzn(r6, r0)
            goto L_0x0661
        L_0x0769:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r8)
            r12.zzm(r6, r0)
            goto L_0x0661
        L_0x077d:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r8)
            r12.zzj(r6, r8)
            goto L_0x0661
        L_0x0791:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r8)
            r12.zze(r6, r0)
            goto L_0x0661
        L_0x07a5:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r8)
            r12.zzb((int) r6, (long) r8)
            goto L_0x0661
        L_0x07b9:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            com.google.android.gms.internal.clearcut.zzef r5 = r10.zzad(r3)
            r12.zzb((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.clearcut.zzef) r5)
            goto L_0x0661
        L_0x07d1:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.clearcut.zzeh.zza((int) r6, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.clearcut.zzfr) r12, (boolean) r5)
            goto L_0x0661
        L_0x07e6:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzb((int) r6, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.clearcut.zzfr) r12, (boolean) r5)
            goto L_0x0661
        L_0x07fb:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzc(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0810:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzd(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0825:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzh(r6, r0, r12, r5)
            goto L_0x0661
        L_0x083a:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzf(r6, r0, r12, r5)
            goto L_0x0661
        L_0x084f:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzk(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0864:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzn(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0879:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.clearcut.zzeh.zza((int) r6, (java.util.List<java.lang.String>) r0, (com.google.android.gms.internal.clearcut.zzfr) r12)
            goto L_0x0661
        L_0x088d:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.clearcut.zzef r5 = r10.zzad(r3)
            com.google.android.gms.internal.clearcut.zzeh.zza((int) r6, (java.util.List<?>) r0, (com.google.android.gms.internal.clearcut.zzfr) r12, (com.google.android.gms.internal.clearcut.zzef) r5)
            goto L_0x0661
        L_0x08a5:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.clearcut.zzeh.zzb(r6, r0, r12)
            goto L_0x0661
        L_0x08b9:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzi(r6, r0, r12, r5)
            goto L_0x0661
        L_0x08ce:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzm(r6, r0, r12, r5)
            goto L_0x0661
        L_0x08e3:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzl(r6, r0, r12, r5)
            goto L_0x0661
        L_0x08f8:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzg(r6, r0, r12, r5)
            goto L_0x0661
        L_0x090d:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzj(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0922:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.clearcut.zzeh.zze(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0937:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.clearcut.zzeh.zza((int) r6, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.clearcut.zzfr) r12, (boolean) r5)
            goto L_0x0661
        L_0x094c:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzb((int) r6, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.clearcut.zzfr) r12, (boolean) r5)
            goto L_0x0661
        L_0x0961:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzc(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0976:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzd(r6, r0, r12, r5)
            goto L_0x0661
        L_0x098b:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzh(r6, r0, r12, r5)
            goto L_0x0661
        L_0x09a0:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzf(r6, r0, r12, r5)
            goto L_0x0661
        L_0x09b5:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzk(r6, r0, r12, r5)
            goto L_0x0661
        L_0x09ca:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzn(r6, r0, r12, r5)
            goto L_0x0661
        L_0x09df:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzi(r6, r0, r12, r5)
            goto L_0x0661
        L_0x09f4:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzm(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0a09:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzl(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0a1e:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzg(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0a33:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.clearcut.zzeh.zzj(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0a48:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.clearcut.zzeh.zze(r6, r0, r12, r5)
            goto L_0x0661
        L_0x0a5d:
            int[] r0 = r10.zzmi
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.clearcut.zzef r5 = r10.zzad(r3)
            com.google.android.gms.internal.clearcut.zzeh.zzb((int) r6, (java.util.List<?>) r0, (com.google.android.gms.internal.clearcut.zzfr) r12, (com.google.android.gms.internal.clearcut.zzef) r5)
            goto L_0x0661
        L_0x0a75:
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            r10.zza((com.google.android.gms.internal.clearcut.zzfr) r12, (int) r6, (java.lang.Object) r0, (int) r3)
            goto L_0x0661
        L_0x0a83:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            double r8 = zze(r11, r8)
            r12.zza((int) r6, (double) r8)
            goto L_0x0661
        L_0x0a97:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            float r0 = zzf(r11, r8)
            r12.zza((int) r6, (float) r0)
            goto L_0x0661
        L_0x0aab:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzh(r11, r8)
            r12.zzi(r6, r8)
            goto L_0x0661
        L_0x0abf:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzh(r11, r8)
            r12.zza((int) r6, (long) r8)
            goto L_0x0661
        L_0x0ad3:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzg(r11, r8)
            r12.zzc((int) r6, (int) r0)
            goto L_0x0661
        L_0x0ae7:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzh(r11, r8)
            r12.zzc((int) r6, (long) r8)
            goto L_0x0661
        L_0x0afb:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzg(r11, r8)
            r12.zzf(r6, r0)
            goto L_0x0661
        L_0x0b0f:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            boolean r0 = zzi(r11, r8)
            r12.zzb((int) r6, (boolean) r0)
            goto L_0x0661
        L_0x0b23:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.clearcut.zzfr) r12)
            goto L_0x0661
        L_0x0b37:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            com.google.android.gms.internal.clearcut.zzef r5 = r10.zzad(r3)
            r12.zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.clearcut.zzef) r5)
            goto L_0x0661
        L_0x0b4f:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            com.google.android.gms.internal.clearcut.zzbb r0 = (com.google.android.gms.internal.clearcut.zzbb) r0
            r12.zza((int) r6, (com.google.android.gms.internal.clearcut.zzbb) r0)
            goto L_0x0661
        L_0x0b65:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzg(r11, r8)
            r12.zzd(r6, r0)
            goto L_0x0661
        L_0x0b79:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzg(r11, r8)
            r12.zzn(r6, r0)
            goto L_0x0661
        L_0x0b8d:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzg(r11, r8)
            r12.zzm(r6, r0)
            goto L_0x0661
        L_0x0ba1:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzh(r11, r8)
            r12.zzj(r6, r8)
            goto L_0x0661
        L_0x0bb5:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzg(r11, r8)
            r12.zze(r6, r0)
            goto L_0x0661
        L_0x0bc9:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zzh(r11, r8)
            r12.zzb((int) r6, (long) r8)
            goto L_0x0661
        L_0x0bdd:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0661
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r8)
            com.google.android.gms.internal.clearcut.zzef r5 = r10.zzad(r3)
            r12.zzb((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.clearcut.zzef) r5)
            goto L_0x0661
        L_0x0bf5:
            r0 = 0
        L_0x0bf6:
            if (r0 == 0) goto L_0x0c0a
            com.google.android.gms.internal.clearcut.zzbu<?> r2 = r10.zzmy
            r2.zza((com.google.android.gms.internal.clearcut.zzfr) r12, (java.util.Map.Entry<?, ?>) r0)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x0bf5
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            goto L_0x0bf6
        L_0x0c0a:
            com.google.android.gms.internal.clearcut.zzex<?, ?> r0 = r10.zzmx
            zza(r0, r11, (com.google.android.gms.internal.clearcut.zzfr) r12)
        L_0x0c0f:
            return
        L_0x0c10:
            r10.zzb(r11, (com.google.android.gms.internal.clearcut.zzfr) r12)
            goto L_0x0c0f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, com.google.android.gms.internal.clearcut.zzfr):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r35, byte[] r36, int r37, int r38, com.google.android.gms.internal.clearcut.zzay r39) throws java.io.IOException {
        /*
            r34 = this;
            r0 = r34
            boolean r4 = r0.zzmq
            if (r4 == 0) goto L_0x024b
            sun.misc.Unsafe r4 = zzmh
            r15 = r37
        L_0x000a:
            r0 = r38
            if (r15 >= r0) goto L_0x0242
            int r11 = r15 + 1
            byte r13 = r36[r15]
            if (r13 >= 0) goto L_0x0020
            r0 = r36
            r1 = r39
            int r11 = com.google.android.gms.internal.clearcut.zzax.zza((int) r13, (byte[]) r0, (int) r11, (com.google.android.gms.internal.clearcut.zzay) r1)
            r0 = r39
            int r13 = r0.zzfd
        L_0x0020:
            int r14 = r13 >>> 3
            r15 = r13 & 7
            r0 = r34
            int r16 = r0.zzai(r14)
            if (r16 < 0) goto L_0x025c
            r0 = r34
            int[] r5 = r0.zzmi
            int r6 = r16 + 1
            r28 = r5[r6]
            r5 = 267386880(0xff00000, float:2.3665827E-29)
            r5 = r5 & r28
            int r19 = r5 >>> 20
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r28
            long r6 = (long) r5
            r5 = 17
            r0 = r19
            if (r0 > r5) goto L_0x019f
            switch(r19) {
                case 0: goto L_0x0057;
                case 1: goto L_0x0068;
                case 2: goto L_0x0079;
                case 3: goto L_0x0079;
                case 4: goto L_0x008e;
                case 5: goto L_0x00a3;
                case 6: goto L_0x00b5;
                case 7: goto L_0x00c7;
                case 8: goto L_0x00e5;
                case 9: goto L_0x010b;
                case 10: goto L_0x0142;
                case 11: goto L_0x008e;
                case 12: goto L_0x0158;
                case 13: goto L_0x00b5;
                case 14: goto L_0x00a3;
                case 15: goto L_0x016d;
                case 16: goto L_0x0186;
                default: goto L_0x0049;
            }
        L_0x0049:
            r15 = r11
        L_0x004a:
            r14 = r36
            r16 = r38
            r17 = r35
            r18 = r39
            int r15 = zza((int) r13, (byte[]) r14, (int) r15, (int) r16, (java.lang.Object) r17, (com.google.android.gms.internal.clearcut.zzay) r18)
            goto L_0x000a
        L_0x0057:
            r5 = 1
            if (r15 != r5) goto L_0x025c
            r0 = r36
            double r8 = com.google.android.gms.internal.clearcut.zzax.zze(r0, r11)
            r0 = r35
            com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r0, (long) r6, (double) r8)
            int r15 = r11 + 8
            goto L_0x000a
        L_0x0068:
            r5 = 5
            if (r15 != r5) goto L_0x025c
            r0 = r36
            float r5 = com.google.android.gms.internal.clearcut.zzax.zzf(r0, r11)
            r0 = r35
            com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r0, (long) r6, (float) r5)
            int r15 = r11 + 4
            goto L_0x000a
        L_0x0079:
            if (r15 != 0) goto L_0x025c
            r0 = r36
            r1 = r39
            int r15 = com.google.android.gms.internal.clearcut.zzax.zzb(r0, r11, r1)
            r0 = r39
            long r8 = r0.zzfe
            r5 = r35
            r4.putLong(r5, r6, r8)
            goto L_0x000a
        L_0x008e:
            if (r15 != 0) goto L_0x025c
            r0 = r36
            r1 = r39
            int r15 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r11, r1)
            r0 = r39
            int r5 = r0.zzfd
            r0 = r35
            r4.putInt(r0, r6, r5)
            goto L_0x000a
        L_0x00a3:
            r5 = 1
            if (r15 != r5) goto L_0x025c
            r0 = r36
            long r8 = com.google.android.gms.internal.clearcut.zzax.zzd(r0, r11)
            r5 = r35
            r4.putLong(r5, r6, r8)
            int r15 = r11 + 8
            goto L_0x000a
        L_0x00b5:
            r5 = 5
            if (r15 != r5) goto L_0x025c
            r0 = r36
            int r5 = com.google.android.gms.internal.clearcut.zzax.zzc(r0, r11)
            r0 = r35
            r4.putInt(r0, r6, r5)
            int r15 = r11 + 4
            goto L_0x000a
        L_0x00c7:
            if (r15 != 0) goto L_0x025c
            r0 = r36
            r1 = r39
            int r15 = com.google.android.gms.internal.clearcut.zzax.zzb(r0, r11, r1)
            r0 = r39
            long r8 = r0.zzfe
            r10 = 0
            int r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r5 == 0) goto L_0x00e3
            r5 = 1
        L_0x00dc:
            r0 = r35
            com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r0, (long) r6, (boolean) r5)
            goto L_0x000a
        L_0x00e3:
            r5 = 0
            goto L_0x00dc
        L_0x00e5:
            r5 = 2
            if (r15 != r5) goto L_0x025c
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r5 & r28
            if (r5 != 0) goto L_0x0102
            r0 = r36
            r1 = r39
            int r5 = com.google.android.gms.internal.clearcut.zzax.zzc(r0, r11, r1)
        L_0x00f6:
            r0 = r39
            java.lang.Object r8 = r0.zzff
            r0 = r35
            r4.putObject(r0, r6, r8)
            r15 = r5
            goto L_0x000a
        L_0x0102:
            r0 = r36
            r1 = r39
            int r5 = com.google.android.gms.internal.clearcut.zzax.zzd(r0, r11, r1)
            goto L_0x00f6
        L_0x010b:
            r5 = 2
            if (r15 != r5) goto L_0x025c
            r0 = r34
            r1 = r16
            com.google.android.gms.internal.clearcut.zzef r5 = r0.zzad(r1)
            r0 = r36
            r1 = r38
            r2 = r39
            int r15 = zza((com.google.android.gms.internal.clearcut.zzef) r5, (byte[]) r0, (int) r11, (int) r1, (com.google.android.gms.internal.clearcut.zzay) r2)
            r0 = r35
            java.lang.Object r5 = r4.getObject(r0, r6)
            if (r5 != 0) goto L_0x0133
            r0 = r39
            java.lang.Object r5 = r0.zzff
            r0 = r35
            r4.putObject(r0, r6, r5)
            goto L_0x000a
        L_0x0133:
            r0 = r39
            java.lang.Object r8 = r0.zzff
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzci.zza((java.lang.Object) r5, (java.lang.Object) r8)
            r0 = r35
            r4.putObject(r0, r6, r5)
            goto L_0x000a
        L_0x0142:
            r5 = 2
            if (r15 != r5) goto L_0x025c
            r0 = r36
            r1 = r39
            int r15 = com.google.android.gms.internal.clearcut.zzax.zze(r0, r11, r1)
            r0 = r39
            java.lang.Object r5 = r0.zzff
            r0 = r35
            r4.putObject(r0, r6, r5)
            goto L_0x000a
        L_0x0158:
            if (r15 != 0) goto L_0x025c
            r0 = r36
            r1 = r39
            int r15 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r11, r1)
            r0 = r39
            int r5 = r0.zzfd
            r0 = r35
            r4.putInt(r0, r6, r5)
            goto L_0x000a
        L_0x016d:
            if (r15 != 0) goto L_0x025c
            r0 = r36
            r1 = r39
            int r15 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r11, r1)
            r0 = r39
            int r5 = r0.zzfd
            int r5 = com.google.android.gms.internal.clearcut.zzbk.zzm(r5)
            r0 = r35
            r4.putInt(r0, r6, r5)
            goto L_0x000a
        L_0x0186:
            if (r15 != 0) goto L_0x025c
            r0 = r36
            r1 = r39
            int r15 = com.google.android.gms.internal.clearcut.zzax.zzb(r0, r11, r1)
            r0 = r39
            long r8 = r0.zzfe
            long r8 = com.google.android.gms.internal.clearcut.zzbk.zza(r8)
            r5 = r35
            r4.putLong(r5, r6, r8)
            goto L_0x000a
        L_0x019f:
            r5 = 27
            r0 = r19
            if (r0 != r5) goto L_0x01e0
            r5 = 2
            if (r15 != r5) goto L_0x025c
            r0 = r35
            java.lang.Object r5 = r4.getObject(r0, r6)
            com.google.android.gms.internal.clearcut.zzcn r5 = (com.google.android.gms.internal.clearcut.zzcn) r5
            boolean r8 = r5.zzu()
            if (r8 != 0) goto L_0x025f
            int r8 = r5.size()
            if (r8 != 0) goto L_0x01dd
            r8 = 10
        L_0x01be:
            com.google.android.gms.internal.clearcut.zzcn r10 = r5.zzi(r8)
            r0 = r35
            r4.putObject(r0, r6, r10)
        L_0x01c7:
            r0 = r34
            r1 = r16
            com.google.android.gms.internal.clearcut.zzef r5 = r0.zzad(r1)
            r6 = r13
            r7 = r36
            r8 = r11
            r9 = r38
            r11 = r39
            int r15 = zza((com.google.android.gms.internal.clearcut.zzef<?>) r5, (int) r6, (byte[]) r7, (int) r8, (int) r9, (com.google.android.gms.internal.clearcut.zzcn<?>) r10, (com.google.android.gms.internal.clearcut.zzay) r11)
            goto L_0x000a
        L_0x01dd:
            int r8 = r8 << 1
            goto L_0x01be
        L_0x01e0:
            r5 = 49
            r0 = r19
            if (r0 > r5) goto L_0x01ff
            r0 = r28
            long r0 = (long) r0
            r17 = r0
            r8 = r34
            r9 = r35
            r10 = r36
            r12 = r38
            r20 = r6
            r22 = r39
            int r15 = r8.zza(r9, (byte[]) r10, (int) r11, (int) r12, (int) r13, (int) r14, (int) r15, (int) r16, (long) r17, (int) r19, (long) r20, (com.google.android.gms.internal.clearcut.zzay) r22)
            if (r15 != r11) goto L_0x000a
            goto L_0x004a
        L_0x01ff:
            r5 = 50
            r0 = r19
            if (r0 != r5) goto L_0x0222
            r5 = 2
            if (r15 != r5) goto L_0x025c
            r17 = r34
            r18 = r35
            r19 = r36
            r20 = r11
            r21 = r38
            r22 = r16
            r23 = r14
            r24 = r6
            r26 = r39
            int r15 = r17.zza(r18, r19, r20, r21, r22, r23, r24, r26)
            if (r15 != r11) goto L_0x000a
            goto L_0x004a
        L_0x0222:
            r20 = r34
            r21 = r35
            r22 = r36
            r23 = r11
            r24 = r38
            r25 = r13
            r26 = r14
            r27 = r15
            r29 = r19
            r30 = r6
            r32 = r16
            r33 = r39
            int r15 = r20.zza(r21, (byte[]) r22, (int) r23, (int) r24, (int) r25, (int) r26, (int) r27, (int) r28, (int) r29, (long) r30, (int) r32, (com.google.android.gms.internal.clearcut.zzay) r33)
            if (r15 != r11) goto L_0x000a
            goto L_0x004a
        L_0x0242:
            r0 = r38
            if (r15 == r0) goto L_0x025b
            com.google.android.gms.internal.clearcut.zzco r4 = com.google.android.gms.internal.clearcut.zzco.zzbo()
            throw r4
        L_0x024b:
            r9 = 0
            r4 = r34
            r5 = r35
            r6 = r36
            r7 = r37
            r8 = r38
            r10 = r39
            r4.zza(r5, (byte[]) r6, (int) r7, (int) r8, (int) r9, (com.google.android.gms.internal.clearcut.zzay) r10)
        L_0x025b:
            return
        L_0x025c:
            r15 = r11
            goto L_0x004a
        L_0x025f:
            r10 = r5
            goto L_0x01c7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.clearcut.zzay):void");
    }

    public final void zzc(T t) {
        if (this.zzmt != null) {
            for (int zzag : this.zzmt) {
                long zzag2 = (long) (zzag(zzag) & 1048575);
                Object zzo = zzfd.zzo(t, zzag2);
                if (zzo != null) {
                    zzfd.zza((Object) t, zzag2, this.zzmz.zzj(zzo));
                }
            }
        }
        if (this.zzmu != null) {
            for (int i : this.zzmu) {
                this.zzmw.zza(t, (long) i);
            }
        }
        this.zzmx.zzc(t);
        if (this.zzmo) {
            this.zzmy.zzc(t);
        }
    }

    public final void zzc(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzmi.length; i += 4) {
            int zzag = zzag(i);
            long j = (long) (1048575 & zzag);
            int i2 = this.zzmi[i];
            switch ((zzag & 267386880) >>> 20) {
                case 0:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzn(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 1:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzm(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 2:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 3:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 4:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 5:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 6:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 7:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzl(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 8:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzo(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 9:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzo(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 11:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 12:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 13:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 14:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 15:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 16:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 17:
                    zza(t, t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzmw.zza(t, t2, j);
                    break;
                case 50:
                    zzeh.zza(this.zzmz, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (!zza(t2, i2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzo(t2, j));
                        zzb(t, i2, i);
                        break;
                    }
                case 60:
                    zzb(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!zza(t2, i2, i)) {
                        break;
                    } else {
                        zzfd.zza((Object) t, j, zzfd.zzo(t2, j));
                        zzb(t, i2, i);
                        break;
                    }
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        if (!this.zzmq) {
            zzeh.zza(this.zzmx, t, t2);
            if (this.zzmo) {
                zzeh.zza(this.zzmy, t, t2);
            }
        }
    }

    public final int zzm(T t) {
        int i;
        if (this.zzmq) {
            Unsafe unsafe = zzmh;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= this.zzmi.length) {
                    return zza(this.zzmx, t) + i2;
                }
                int zzag = zzag(i4);
                int i5 = (267386880 & zzag) >>> 20;
                int i6 = this.zzmi[i4];
                long j = (long) (zzag & 1048575);
                int i7 = (i5 < zzcb.DOUBLE_LIST_PACKED.mo21749id() || i5 > zzcb.SINT64_LIST_PACKED.mo21749id()) ? 0 : this.zzmi[i4 + 2] & 1048575;
                switch (i5) {
                    case 0:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzb(i6, 0.0d);
                            break;
                        }
                    case 1:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzb(i6, 0.0f);
                            break;
                        }
                    case 2:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzd(i6, zzfd.zzk(t, j));
                            break;
                        }
                    case 3:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zze(i6, zzfd.zzk(t, j));
                            break;
                        }
                    case 4:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzg(i6, zzfd.zzj(t, j));
                            break;
                        }
                    case 5:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzg(i6, 0);
                            break;
                        }
                    case 6:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzj(i6, 0);
                            break;
                        }
                    case 7:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzc(i6, true);
                            break;
                        }
                    case 8:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            Object zzo = zzfd.zzo(t, j);
                            if (!(zzo instanceof zzbb)) {
                                i2 += zzbn.zzb(i6, (String) zzo);
                                break;
                            } else {
                                i2 += zzbn.zzc(i6, (zzbb) zzo);
                                break;
                            }
                        }
                    case 9:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzeh.zzc(i6, zzfd.zzo(t, j), zzad(i4));
                            break;
                        }
                    case 10:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzc(i6, (zzbb) zzfd.zzo(t, j));
                            break;
                        }
                    case 11:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzh(i6, zzfd.zzj(t, j));
                            break;
                        }
                    case 12:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzl(i6, zzfd.zzj(t, j));
                            break;
                        }
                    case 13:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzk(i6, 0);
                            break;
                        }
                    case 14:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzh(i6, 0);
                            break;
                        }
                    case 15:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzi(i6, zzfd.zzj(t, j));
                            break;
                        }
                    case 16:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzf(i6, zzfd.zzk(t, j));
                            break;
                        }
                    case 17:
                        if (!zza(t, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzc(i6, (zzdo) zzfd.zzo(t, j), zzad(i4));
                            break;
                        }
                    case 18:
                        i2 += zzeh.zzw(i6, zzd(t, j), false);
                        break;
                    case 19:
                        i2 += zzeh.zzv(i6, zzd(t, j), false);
                        break;
                    case 20:
                        i2 += zzeh.zzo(i6, zzd(t, j), false);
                        break;
                    case 21:
                        i2 += zzeh.zzp(i6, zzd(t, j), false);
                        break;
                    case 22:
                        i2 += zzeh.zzs(i6, zzd(t, j), false);
                        break;
                    case 23:
                        i2 += zzeh.zzw(i6, zzd(t, j), false);
                        break;
                    case 24:
                        i2 += zzeh.zzv(i6, zzd(t, j), false);
                        break;
                    case 25:
                        i2 += zzeh.zzx(i6, zzd(t, j), false);
                        break;
                    case 26:
                        i2 += zzeh.zzc(i6, zzd(t, j));
                        break;
                    case 27:
                        i2 += zzeh.zzc(i6, (List<?>) zzd(t, j), zzad(i4));
                        break;
                    case 28:
                        i2 += zzeh.zzd(i6, (List<zzbb>) zzd(t, j));
                        break;
                    case 29:
                        i2 += zzeh.zzt(i6, zzd(t, j), false);
                        break;
                    case 30:
                        i2 += zzeh.zzr(i6, zzd(t, j), false);
                        break;
                    case 31:
                        i2 += zzeh.zzv(i6, zzd(t, j), false);
                        break;
                    case 32:
                        i2 += zzeh.zzw(i6, zzd(t, j), false);
                        break;
                    case 33:
                        i2 += zzeh.zzu(i6, zzd(t, j), false);
                        break;
                    case 34:
                        i2 += zzeh.zzq(i6, zzd(t, j), false);
                        break;
                    case 35:
                        int zzi = zzeh.zzi((List) unsafe.getObject(t, j));
                        if (zzi > 0) {
                            if (this.zzmr) {
                                unsafe.putInt(t, (long) i7, zzi);
                            }
                            i2 += zzi + zzbn.zzr(i6) + zzbn.zzt(zzi);
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        int zzh = zzeh.zzh((List) unsafe.getObject(t, j));
                        if (zzh > 0) {
                            if (this.zzmr) {
                                unsafe.putInt(t, (long) i7, zzh);
                            }
                            i2 += zzh + zzbn.zzr(i6) + zzbn.zzt(zzh);
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        int zza = zzeh.zza((List) unsafe.getObject(t, j));
                        if (zza > 0) {
                            if (this.zzmr) {
                                unsafe.putInt(t, (long) i7, zza);
                            }
                            i2 += zza + zzbn.zzr(i6) + zzbn.zzt(zza);
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        int zzb = zzeh.zzb((List) unsafe.getObject(t, j));
                        if (zzb > 0) {
                            if (this.zzmr) {
                                unsafe.putInt(t, (long) i7, zzb);
                            }
                            i2 += zzb + zzbn.zzr(i6) + zzbn.zzt(zzb);
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        int zze = zzeh.zze((List) unsafe.getObject(t, j));
                        if (zze > 0) {
                            if (this.zzmr) {
                                unsafe.putInt(t, (long) i7, zze);
                            }
                            i2 += zze + zzbn.zzr(i6) + zzbn.zzt(zze);
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        int zzi2 = zzeh.zzi((List) unsafe.getObject(t, j));
                        if (zzi2 > 0) {
                            if (this.zzmr) {
                                unsafe.putInt(t, (long) i7, zzi2);
                            }
                            i2 += zzi2 + zzbn.zzr(i6) + zzbn.zzt(zzi2);
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        int zzh2 = zzeh.zzh((List) unsafe.getObject(t, j));
                        if (zzh2 > 0) {
                            if (this.zzmr) {
                                unsafe.putInt(t, (long) i7, zzh2);
                            }
                            i2 += zzh2 + zzbn.zzr(i6) + zzbn.zzt(zzh2);
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        int zzj = zzeh.zzj((List) unsafe.getObject(t, j));
                        if (zzj > 0) {
                            if (this.zzmr) {
                                unsafe.putInt(t, (long) i7, zzj);
                            }
                            i2 += zzj + zzbn.zzr(i6) + zzbn.zzt(zzj);
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        int zzf = zzeh.zzf((List<Integer>) (List) unsafe.getObject(t, j));
                        if (zzf > 0) {
                            if (this.zzmr) {
                                unsafe.putInt(t, (long) i7, zzf);
                            }
                            i2 += zzf + zzbn.zzr(i6) + zzbn.zzt(zzf);
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        int zzd = zzeh.zzd((List<Integer>) (List) unsafe.getObject(t, j));
                        if (zzd > 0) {
                            if (this.zzmr) {
                                unsafe.putInt(t, (long) i7, zzd);
                            }
                            i2 += zzd + zzbn.zzr(i6) + zzbn.zzt(zzd);
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        int zzh3 = zzeh.zzh((List) unsafe.getObject(t, j));
                        if (zzh3 > 0) {
                            if (this.zzmr) {
                                unsafe.putInt(t, (long) i7, zzh3);
                            }
                            i2 += zzh3 + zzbn.zzr(i6) + zzbn.zzt(zzh3);
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        int zzi3 = zzeh.zzi((List) unsafe.getObject(t, j));
                        if (zzi3 > 0) {
                            if (this.zzmr) {
                                unsafe.putInt(t, (long) i7, zzi3);
                            }
                            i2 += zzi3 + zzbn.zzr(i6) + zzbn.zzt(zzi3);
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        int zzg = zzeh.zzg((List) unsafe.getObject(t, j));
                        if (zzg > 0) {
                            if (this.zzmr) {
                                unsafe.putInt(t, (long) i7, zzg);
                            }
                            i2 += zzg + zzbn.zzr(i6) + zzbn.zzt(zzg);
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        int zzc = zzeh.zzc((List) unsafe.getObject(t, j));
                        if (zzc > 0) {
                            if (this.zzmr) {
                                unsafe.putInt(t, (long) i7, zzc);
                            }
                            i2 += zzc + zzbn.zzr(i6) + zzbn.zzt(zzc);
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        i2 += zzeh.zzd(i6, zzd(t, j), zzad(i4));
                        break;
                    case 50:
                        i2 += this.zzmz.zzb(i6, zzfd.zzo(t, j), zzae(i4));
                        break;
                    case 51:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzb(i6, 0.0d);
                            break;
                        }
                    case 52:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzb(i6, 0.0f);
                            break;
                        }
                    case 53:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzd(i6, zzh(t, j));
                            break;
                        }
                    case 54:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zze(i6, zzh(t, j));
                            break;
                        }
                    case 55:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzg(i6, zzg(t, j));
                            break;
                        }
                    case 56:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzg(i6, 0);
                            break;
                        }
                    case 57:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzj(i6, 0);
                            break;
                        }
                    case 58:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzc(i6, true);
                            break;
                        }
                    case 59:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            Object zzo2 = zzfd.zzo(t, j);
                            if (!(zzo2 instanceof zzbb)) {
                                i2 += zzbn.zzb(i6, (String) zzo2);
                                break;
                            } else {
                                i2 += zzbn.zzc(i6, (zzbb) zzo2);
                                break;
                            }
                        }
                    case 60:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzeh.zzc(i6, zzfd.zzo(t, j), zzad(i4));
                            break;
                        }
                    case 61:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzc(i6, (zzbb) zzfd.zzo(t, j));
                            break;
                        }
                    case 62:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzh(i6, zzg(t, j));
                            break;
                        }
                    case 63:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzl(i6, zzg(t, j));
                            break;
                        }
                    case 64:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzk(i6, 0);
                            break;
                        }
                    case 65:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzh(i6, 0);
                            break;
                        }
                    case 66:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzi(i6, zzg(t, j));
                            break;
                        }
                    case 67:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzf(i6, zzh(t, j));
                            break;
                        }
                    case 68:
                        if (!zza(t, i6, i4)) {
                            break;
                        } else {
                            i2 += zzbn.zzc(i6, (zzdo) zzfd.zzo(t, j), zzad(i4));
                            break;
                        }
                }
                i3 = i4 + 4;
            }
        } else {
            int i8 = 0;
            Unsafe unsafe2 = zzmh;
            int i9 = -1;
            int i10 = 0;
            int i11 = 0;
            while (true) {
                int i12 = i11;
                if (i12 < this.zzmi.length) {
                    int zzag2 = zzag(i12);
                    int i13 = this.zzmi[i12];
                    int i14 = (267386880 & zzag2) >>> 20;
                    int i15 = 0;
                    if (i14 <= 17) {
                        i = this.zzmi[i12 + 2];
                        int i16 = 1048575 & i;
                        int i17 = 1 << (i >>> 20);
                        if (i16 != i9) {
                            i10 = unsafe2.getInt(t, (long) i16);
                            i9 = i16;
                        }
                        i15 = i17;
                    } else {
                        i = (!this.zzmr || i14 < zzcb.DOUBLE_LIST_PACKED.mo21749id() || i14 > zzcb.SINT64_LIST_PACKED.mo21749id()) ? 0 : this.zzmi[i12 + 2] & 1048575;
                    }
                    long j2 = (long) (1048575 & zzag2);
                    switch (i14) {
                        case 0:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzb(i13, 0.0d);
                                break;
                            }
                        case 1:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzb(i13, 0.0f);
                                break;
                            }
                        case 2:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzd(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 3:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zze(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 4:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzg(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 5:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzg(i13, 0);
                                break;
                            }
                        case 6:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzj(i13, 0);
                                break;
                            }
                        case 7:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzc(i13, true);
                                break;
                            }
                        case 8:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                Object object = unsafe2.getObject(t, j2);
                                if (!(object instanceof zzbb)) {
                                    i8 += zzbn.zzb(i13, (String) object);
                                    break;
                                } else {
                                    i8 += zzbn.zzc(i13, (zzbb) object);
                                    break;
                                }
                            }
                        case 9:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzeh.zzc(i13, unsafe2.getObject(t, j2), zzad(i12));
                                break;
                            }
                        case 10:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzc(i13, (zzbb) unsafe2.getObject(t, j2));
                                break;
                            }
                        case 11:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzh(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 12:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzl(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 13:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzk(i13, 0);
                                break;
                            }
                        case 14:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzh(i13, 0);
                                break;
                            }
                        case 15:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzi(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 16:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzf(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 17:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzbn.zzc(i13, (zzdo) unsafe2.getObject(t, j2), zzad(i12));
                                break;
                            }
                        case 18:
                            i8 += zzeh.zzw(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 19:
                            i8 += zzeh.zzv(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 20:
                            i8 += zzeh.zzo(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 21:
                            i8 += zzeh.zzp(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 22:
                            i8 += zzeh.zzs(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 23:
                            i8 += zzeh.zzw(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 24:
                            i8 += zzeh.zzv(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 25:
                            i8 += zzeh.zzx(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 26:
                            i8 += zzeh.zzc(i13, (List) unsafe2.getObject(t, j2));
                            break;
                        case 27:
                            i8 += zzeh.zzc(i13, (List<?>) (List) unsafe2.getObject(t, j2), zzad(i12));
                            break;
                        case 28:
                            i8 += zzeh.zzd(i13, (List<zzbb>) (List) unsafe2.getObject(t, j2));
                            break;
                        case 29:
                            i8 += zzeh.zzt(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 30:
                            i8 += zzeh.zzr(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 31:
                            i8 += zzeh.zzv(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 32:
                            i8 += zzeh.zzw(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 33:
                            i8 += zzeh.zzu(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 34:
                            i8 += zzeh.zzq(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 35:
                            int zzi4 = zzeh.zzi((List) unsafe2.getObject(t, j2));
                            if (zzi4 > 0) {
                                if (this.zzmr) {
                                    unsafe2.putInt(t, (long) i, zzi4);
                                }
                                i8 += zzi4 + zzbn.zzr(i13) + zzbn.zzt(zzi4);
                                break;
                            } else {
                                break;
                            }
                        case 36:
                            int zzh4 = zzeh.zzh((List) unsafe2.getObject(t, j2));
                            if (zzh4 > 0) {
                                if (this.zzmr) {
                                    unsafe2.putInt(t, (long) i, zzh4);
                                }
                                i8 += zzh4 + zzbn.zzr(i13) + zzbn.zzt(zzh4);
                                break;
                            } else {
                                break;
                            }
                        case 37:
                            int zza2 = zzeh.zza((List) unsafe2.getObject(t, j2));
                            if (zza2 > 0) {
                                if (this.zzmr) {
                                    unsafe2.putInt(t, (long) i, zza2);
                                }
                                i8 += zza2 + zzbn.zzr(i13) + zzbn.zzt(zza2);
                                break;
                            } else {
                                break;
                            }
                        case 38:
                            int zzb2 = zzeh.zzb((List) unsafe2.getObject(t, j2));
                            if (zzb2 > 0) {
                                if (this.zzmr) {
                                    unsafe2.putInt(t, (long) i, zzb2);
                                }
                                i8 += zzb2 + zzbn.zzr(i13) + zzbn.zzt(zzb2);
                                break;
                            } else {
                                break;
                            }
                        case 39:
                            int zze2 = zzeh.zze((List) unsafe2.getObject(t, j2));
                            if (zze2 > 0) {
                                if (this.zzmr) {
                                    unsafe2.putInt(t, (long) i, zze2);
                                }
                                i8 += zze2 + zzbn.zzr(i13) + zzbn.zzt(zze2);
                                break;
                            } else {
                                break;
                            }
                        case 40:
                            int zzi5 = zzeh.zzi((List) unsafe2.getObject(t, j2));
                            if (zzi5 > 0) {
                                if (this.zzmr) {
                                    unsafe2.putInt(t, (long) i, zzi5);
                                }
                                i8 += zzi5 + zzbn.zzr(i13) + zzbn.zzt(zzi5);
                                break;
                            } else {
                                break;
                            }
                        case 41:
                            int zzh5 = zzeh.zzh((List) unsafe2.getObject(t, j2));
                            if (zzh5 > 0) {
                                if (this.zzmr) {
                                    unsafe2.putInt(t, (long) i, zzh5);
                                }
                                i8 += zzh5 + zzbn.zzr(i13) + zzbn.zzt(zzh5);
                                break;
                            } else {
                                break;
                            }
                        case 42:
                            int zzj2 = zzeh.zzj((List) unsafe2.getObject(t, j2));
                            if (zzj2 > 0) {
                                if (this.zzmr) {
                                    unsafe2.putInt(t, (long) i, zzj2);
                                }
                                i8 += zzj2 + zzbn.zzr(i13) + zzbn.zzt(zzj2);
                                break;
                            } else {
                                break;
                            }
                        case 43:
                            int zzf2 = zzeh.zzf((List<Integer>) (List) unsafe2.getObject(t, j2));
                            if (zzf2 > 0) {
                                if (this.zzmr) {
                                    unsafe2.putInt(t, (long) i, zzf2);
                                }
                                i8 += zzf2 + zzbn.zzr(i13) + zzbn.zzt(zzf2);
                                break;
                            } else {
                                break;
                            }
                        case 44:
                            int zzd2 = zzeh.zzd((List<Integer>) (List) unsafe2.getObject(t, j2));
                            if (zzd2 > 0) {
                                if (this.zzmr) {
                                    unsafe2.putInt(t, (long) i, zzd2);
                                }
                                i8 += zzd2 + zzbn.zzr(i13) + zzbn.zzt(zzd2);
                                break;
                            } else {
                                break;
                            }
                        case 45:
                            int zzh6 = zzeh.zzh((List) unsafe2.getObject(t, j2));
                            if (zzh6 > 0) {
                                if (this.zzmr) {
                                    unsafe2.putInt(t, (long) i, zzh6);
                                }
                                i8 += zzh6 + zzbn.zzr(i13) + zzbn.zzt(zzh6);
                                break;
                            } else {
                                break;
                            }
                        case 46:
                            int zzi6 = zzeh.zzi((List) unsafe2.getObject(t, j2));
                            if (zzi6 > 0) {
                                if (this.zzmr) {
                                    unsafe2.putInt(t, (long) i, zzi6);
                                }
                                i8 += zzi6 + zzbn.zzr(i13) + zzbn.zzt(zzi6);
                                break;
                            } else {
                                break;
                            }
                        case 47:
                            int zzg2 = zzeh.zzg((List) unsafe2.getObject(t, j2));
                            if (zzg2 > 0) {
                                if (this.zzmr) {
                                    unsafe2.putInt(t, (long) i, zzg2);
                                }
                                i8 += zzg2 + zzbn.zzr(i13) + zzbn.zzt(zzg2);
                                break;
                            } else {
                                break;
                            }
                        case 48:
                            int zzc2 = zzeh.zzc((List) unsafe2.getObject(t, j2));
                            if (zzc2 > 0) {
                                if (this.zzmr) {
                                    unsafe2.putInt(t, (long) i, zzc2);
                                }
                                i8 += zzc2 + zzbn.zzr(i13) + zzbn.zzt(zzc2);
                                break;
                            } else {
                                break;
                            }
                        case 49:
                            i8 += zzeh.zzd(i13, (List) unsafe2.getObject(t, j2), zzad(i12));
                            break;
                        case 50:
                            i8 += this.zzmz.zzb(i13, unsafe2.getObject(t, j2), zzae(i12));
                            break;
                        case 51:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzb(i13, 0.0d);
                                break;
                            }
                        case 52:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzb(i13, 0.0f);
                                break;
                            }
                        case 53:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzd(i13, zzh(t, j2));
                                break;
                            }
                        case 54:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zze(i13, zzh(t, j2));
                                break;
                            }
                        case 55:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzg(i13, zzg(t, j2));
                                break;
                            }
                        case 56:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzg(i13, 0);
                                break;
                            }
                        case 57:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzj(i13, 0);
                                break;
                            }
                        case 58:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzc(i13, true);
                                break;
                            }
                        case 59:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                Object object2 = unsafe2.getObject(t, j2);
                                if (!(object2 instanceof zzbb)) {
                                    i8 += zzbn.zzb(i13, (String) object2);
                                    break;
                                } else {
                                    i8 += zzbn.zzc(i13, (zzbb) object2);
                                    break;
                                }
                            }
                        case 60:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzeh.zzc(i13, unsafe2.getObject(t, j2), zzad(i12));
                                break;
                            }
                        case 61:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzc(i13, (zzbb) unsafe2.getObject(t, j2));
                                break;
                            }
                        case 62:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzh(i13, zzg(t, j2));
                                break;
                            }
                        case 63:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzl(i13, zzg(t, j2));
                                break;
                            }
                        case 64:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzk(i13, 0);
                                break;
                            }
                        case 65:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzh(i13, 0);
                                break;
                            }
                        case 66:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzi(i13, zzg(t, j2));
                                break;
                            }
                        case 67:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzf(i13, zzh(t, j2));
                                break;
                            }
                        case 68:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzbn.zzc(i13, (zzdo) unsafe2.getObject(t, j2), zzad(i12));
                                break;
                            }
                    }
                    i11 = i12 + 4;
                } else {
                    int zza3 = zza(this.zzmx, t) + i8;
                    return this.zzmo ? zza3 + this.zzmy.zza((Object) t).zzas() : zza3;
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x004b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzo(T r15) {
        /*
            r14 = this;
            int[] r0 = r14.zzms
            if (r0 == 0) goto L_0x0009
            int[] r0 = r14.zzms
            int r0 = r0.length
            if (r0 != 0) goto L_0x000b
        L_0x0009:
            r0 = 1
        L_0x000a:
            return r0
        L_0x000b:
            r4 = -1
            r2 = 0
            int[] r6 = r14.zzms
            int r7 = r6.length
            r0 = 0
            r5 = r0
        L_0x0012:
            if (r5 >= r7) goto L_0x010a
            r8 = r6[r5]
            int r9 = r14.zzai(r8)
            int r10 = r14.zzag(r9)
            r0 = 0
            boolean r1 = r14.zzmq
            if (r1 != 0) goto L_0x0120
            int[] r0 = r14.zzmi
            int r1 = r9 + 2
            r0 = r0[r1]
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r0 & r1
            r1 = 1
            int r0 = r0 >>> 20
            int r0 = r1 << r0
            if (r3 == r4) goto L_0x0120
            sun.misc.Unsafe r1 = zzmh
            long r12 = (long) r3
            int r2 = r1.getInt(r15, r12)
            r1 = r0
        L_0x003d:
            r0 = 268435456(0x10000000, float:2.5243549E-29)
            r0 = r0 & r10
            if (r0 == 0) goto L_0x004d
            r0 = 1
        L_0x0043:
            if (r0 == 0) goto L_0x004f
            boolean r0 = r14.zza(r15, (int) r9, (int) r2, (int) r1)
            if (r0 != 0) goto L_0x004f
            r0 = 0
            goto L_0x000a
        L_0x004d:
            r0 = 0
            goto L_0x0043
        L_0x004f:
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r10
            int r0 = r0 >>> 20
            switch(r0) {
                case 9: goto L_0x005c;
                case 17: goto L_0x005c;
                case 27: goto L_0x006e;
                case 49: goto L_0x006e;
                case 50: goto L_0x00b2;
                case 60: goto L_0x009f;
                case 68: goto L_0x009f;
                default: goto L_0x0057;
            }
        L_0x0057:
            int r0 = r5 + 1
            r5 = r0
            r4 = r3
            goto L_0x0012
        L_0x005c:
            boolean r0 = r14.zza(r15, (int) r9, (int) r2, (int) r1)
            if (r0 == 0) goto L_0x0057
            com.google.android.gms.internal.clearcut.zzef r0 = r14.zzad(r9)
            boolean r0 = zza((java.lang.Object) r15, (int) r10, (com.google.android.gms.internal.clearcut.zzef) r0)
            if (r0 != 0) goto L_0x0057
            r0 = 0
            goto L_0x000a
        L_0x006e:
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r10
            long r0 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r15, r0)
            java.util.List r0 = (java.util.List) r0
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x009d
            com.google.android.gms.internal.clearcut.zzef r4 = r14.zzad(r9)
            r1 = 0
        L_0x0084:
            int r8 = r0.size()
            if (r1 >= r8) goto L_0x009d
            java.lang.Object r8 = r0.get(r1)
            boolean r8 = r4.zzo(r8)
            if (r8 != 0) goto L_0x009a
            r0 = 0
        L_0x0095:
            if (r0 != 0) goto L_0x0057
            r0 = 0
            goto L_0x000a
        L_0x009a:
            int r1 = r1 + 1
            goto L_0x0084
        L_0x009d:
            r0 = 1
            goto L_0x0095
        L_0x009f:
            boolean r0 = r14.zza(r15, (int) r8, (int) r9)
            if (r0 == 0) goto L_0x0057
            com.google.android.gms.internal.clearcut.zzef r0 = r14.zzad(r9)
            boolean r0 = zza((java.lang.Object) r15, (int) r10, (com.google.android.gms.internal.clearcut.zzef) r0)
            if (r0 != 0) goto L_0x0057
            r0 = 0
            goto L_0x000a
        L_0x00b2:
            com.google.android.gms.internal.clearcut.zzdj r0 = r14.zzmz
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r10
            long r10 = (long) r1
            java.lang.Object r1 = com.google.android.gms.internal.clearcut.zzfd.zzo(r15, r10)
            java.util.Map r1 = r0.zzh(r1)
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x0108
            java.lang.Object r0 = r14.zzae(r9)
            com.google.android.gms.internal.clearcut.zzdj r4 = r14.zzmz
            com.google.android.gms.internal.clearcut.zzdh r0 = r4.zzl(r0)
            com.google.android.gms.internal.clearcut.zzfl r0 = r0.zzmd
            com.google.android.gms.internal.clearcut.zzfq r0 = r0.zzek()
            com.google.android.gms.internal.clearcut.zzfq r4 = com.google.android.gms.internal.clearcut.zzfq.MESSAGE
            if (r0 != r4) goto L_0x0108
            r0 = 0
            java.util.Collection r1 = r1.values()
            java.util.Iterator r1 = r1.iterator()
        L_0x00e4:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0108
            java.lang.Object r4 = r1.next()
            if (r0 != 0) goto L_0x00fc
            com.google.android.gms.internal.clearcut.zzea r0 = com.google.android.gms.internal.clearcut.zzea.zzcm()
            java.lang.Class r8 = r4.getClass()
            com.google.android.gms.internal.clearcut.zzef r0 = r0.zze(r8)
        L_0x00fc:
            boolean r4 = r0.zzo(r4)
            if (r4 != 0) goto L_0x00e4
            r0 = 0
        L_0x0103:
            if (r0 != 0) goto L_0x0057
            r0 = 0
            goto L_0x000a
        L_0x0108:
            r0 = 1
            goto L_0x0103
        L_0x010a:
            boolean r0 = r14.zzmo
            if (r0 == 0) goto L_0x011d
            com.google.android.gms.internal.clearcut.zzbu<?> r0 = r14.zzmy
            com.google.android.gms.internal.clearcut.zzby r0 = r0.zza((java.lang.Object) r15)
            boolean r0 = r0.isInitialized()
            if (r0 != 0) goto L_0x011d
            r0 = 0
            goto L_0x000a
        L_0x011d:
            r0 = 1
            goto L_0x000a
        L_0x0120:
            r1 = r0
            r3 = r4
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zzo(java.lang.Object):boolean");
    }
}
