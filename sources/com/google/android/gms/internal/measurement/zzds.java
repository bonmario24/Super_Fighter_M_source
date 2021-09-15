package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzds {
    static int zza(byte[] bArr, int i, zzdr zzdr) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zza((int) b, bArr, i2, zzdr);
        }
        zzdr.zza = b;
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, zzdr zzdr) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzdr.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = ((b & Byte.MAX_VALUE) << 7) | i3;
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzdr.zza = (b2 << 14) | i5;
            return i6;
        }
        int i7 = ((b2 & Byte.MAX_VALUE) << 14) | i5;
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzdr.zza = i7 | (b3 << 21);
            return i8;
        }
        int i9 = ((b3 & Byte.MAX_VALUE) << 21) | i7;
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzdr.zza = i9 | (b4 << 28);
            return i10;
        }
        int i11 = ((b4 & Byte.MAX_VALUE) << 28) | i9;
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzdr.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzb(byte[] bArr, int i, zzdr zzdr) {
        int i2 = 7;
        int i3 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzdr.zzb = j;
        } else {
            long j2 = 127 & j;
            byte b = bArr[i3];
            long j3 = j2 | (((long) (b & Byte.MAX_VALUE)) << 7);
            i3++;
            while (b < 0) {
                b = bArr[i3];
                i2 += 7;
                j3 |= ((long) (b & Byte.MAX_VALUE)) << i2;
                i3++;
            }
            zzdr.zzb = j3;
        }
        return i3;
    }

    static int zza(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }

    static long zzb(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    static double zzc(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzb(bArr, i));
    }

    static float zzd(byte[] bArr, int i) {
        return Float.intBitsToFloat(zza(bArr, i));
    }

    static int zzc(byte[] bArr, int i, zzdr zzdr) throws zzfm {
        int zza = zza(bArr, i, zzdr);
        int i2 = zzdr.zza;
        if (i2 < 0) {
            throw zzfm.zzb();
        } else if (i2 == 0) {
            zzdr.zzc = "";
            return zza;
        } else {
            zzdr.zzc = new String(bArr, zza, i2, zzfh.zza);
            return zza + i2;
        }
    }

    static int zzd(byte[] bArr, int i, zzdr zzdr) throws zzfm {
        int zza = zza(bArr, i, zzdr);
        int i2 = zzdr.zza;
        if (i2 < 0) {
            throw zzfm.zzb();
        } else if (i2 == 0) {
            zzdr.zzc = "";
            return zza;
        } else {
            zzdr.zzc = zzif.zzb(bArr, zza, i2);
            return zza + i2;
        }
    }

    static int zze(byte[] bArr, int i, zzdr zzdr) throws zzfm {
        int zza = zza(bArr, i, zzdr);
        int i2 = zzdr.zza;
        if (i2 < 0) {
            throw zzfm.zzb();
        } else if (i2 > bArr.length - zza) {
            throw zzfm.zza();
        } else if (i2 == 0) {
            zzdr.zzc = zzdw.zza;
            return zza;
        } else {
            zzdr.zzc = zzdw.zza(bArr, zza, i2);
            return zza + i2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zza(com.google.android.gms.internal.measurement.zzhf r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.measurement.zzdr r11) throws java.io.IOException {
        /*
            int r3 = r9 + 1
            byte r0 = r8[r9]
            if (r0 >= 0) goto L_0x002c
            int r3 = zza((int) r0, (byte[]) r8, (int) r3, (com.google.android.gms.internal.measurement.zzdr) r11)
            int r0 = r11.zza
            r6 = r0
        L_0x000d:
            if (r6 < 0) goto L_0x0013
            int r0 = r10 - r3
            if (r6 <= r0) goto L_0x0018
        L_0x0013:
            com.google.android.gms.internal.measurement.zzfm r0 = com.google.android.gms.internal.measurement.zzfm.zza()
            throw r0
        L_0x0018:
            java.lang.Object r1 = r7.zza()
            int r4 = r3 + r6
            r0 = r7
            r2 = r8
            r5 = r11
            r0.zza(r1, r2, r3, r4, r5)
            r7.zzc(r1)
            r11.zzc = r1
            int r0 = r3 + r6
            return r0
        L_0x002c:
            r6 = r0
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzds.zza(com.google.android.gms.internal.measurement.zzhf, byte[], int, int, com.google.android.gms.internal.measurement.zzdr):int");
    }

    static int zza(zzhf zzhf, byte[] bArr, int i, int i2, int i3, zzdr zzdr) throws IOException {
        zzgq zzgq = (zzgq) zzhf;
        Object zza = zzgq.zza();
        int zza2 = zzgq.zza(zza, bArr, i, i2, i3, zzdr);
        zzgq.zzc(zza);
        zzdr.zzc = zza;
        return zza2;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzfn<?> zzfn, zzdr zzdr) {
        zzff zzff = (zzff) zzfn;
        int zza = zza(bArr, i2, zzdr);
        zzff.zzd(zzdr.zza);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzdr);
            if (i != zzdr.zza) {
                break;
            }
            zza = zza(bArr, zza2, zzdr);
            zzff.zzd(zzdr.zza);
        }
        return zza;
    }

    static int zza(byte[] bArr, int i, zzfn<?> zzfn, zzdr zzdr) throws IOException {
        zzff zzff = (zzff) zzfn;
        int zza = zza(bArr, i, zzdr);
        int i2 = zzdr.zza + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, zzdr);
            zzff.zzd(zzdr.zza);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzfm.zza();
    }

    static int zza(zzhf<?> zzhf, int i, byte[] bArr, int i2, int i3, zzfn<?> zzfn, zzdr zzdr) throws IOException {
        int zza = zza((zzhf) zzhf, bArr, i2, i3, zzdr);
        zzfn.add(zzdr.zzc);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzdr);
            if (i != zzdr.zza) {
                break;
            }
            zza = zza((zzhf) zzhf, bArr, zza2, i3, zzdr);
            zzfn.add(zzdr.zzc);
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzhw zzhw, zzdr zzdr) throws zzfm {
        if ((i >>> 3) == 0) {
            throw zzfm.zzd();
        }
        switch (i & 7) {
            case 0:
                int zzb = zzb(bArr, i2, zzdr);
                zzhw.zza(i, (Object) Long.valueOf(zzdr.zzb));
                return zzb;
            case 1:
                zzhw.zza(i, (Object) Long.valueOf(zzb(bArr, i2)));
                return i2 + 8;
            case 2:
                int zza = zza(bArr, i2, zzdr);
                int i4 = zzdr.zza;
                if (i4 < 0) {
                    throw zzfm.zzb();
                } else if (i4 > bArr.length - zza) {
                    throw zzfm.zza();
                } else {
                    if (i4 == 0) {
                        zzhw.zza(i, (Object) zzdw.zza);
                    } else {
                        zzhw.zza(i, (Object) zzdw.zza(bArr, zza, i4));
                    }
                    return zza + i4;
                }
            case 3:
                zzhw zzb2 = zzhw.zzb();
                int i5 = (i & -8) | 4;
                int i6 = 0;
                int i7 = i2;
                while (true) {
                    if (i7 < i3) {
                        int zza2 = zza(bArr, i7, zzdr);
                        i6 = zzdr.zza;
                        if (i6 != i5) {
                            i7 = zza(i6, bArr, zza2, i3, zzb2, zzdr);
                        } else {
                            i7 = zza2;
                        }
                    }
                }
                if (i7 > i3 || i6 != i5) {
                    throw zzfm.zzg();
                }
                zzhw.zza(i, (Object) zzb2);
                return i7;
            case 5:
                zzhw.zza(i, (Object) Integer.valueOf(zza(bArr, i2)));
                return i2 + 4;
            default:
                throw zzfm.zzd();
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzdr zzdr) throws zzfm {
        if ((i >>> 3) == 0) {
            throw zzfm.zzd();
        }
        switch (i & 7) {
            case 0:
                return zzb(bArr, i2, zzdr);
            case 1:
                return i2 + 8;
            case 2:
                return zza(bArr, i2, zzdr) + zzdr.zza;
            case 3:
                int i4 = (i & -8) | 4;
                int i5 = 0;
                int i6 = i2;
                while (i6 < i3) {
                    i6 = zza(bArr, i6, zzdr);
                    i5 = zzdr.zza;
                    if (i5 != i4) {
                        i6 = zza(i5, bArr, i6, i3, zzdr);
                    } else if (i6 > i3 && i5 == i4) {
                        return i6;
                    } else {
                        throw zzfm.zzg();
                    }
                }
                if (i6 > i3) {
                }
                throw zzfm.zzg();
            case 5:
                return i2 + 4;
            default:
                throw zzfm.zzd();
        }
    }
}
