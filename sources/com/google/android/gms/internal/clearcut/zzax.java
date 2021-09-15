package com.google.android.gms.internal.clearcut;

import java.io.IOException;

final class zzax {
    static int zza(int i, byte[] bArr, int i2, int i3, zzay zzay) throws zzco {
        if ((i >>> 3) == 0) {
            throw zzco.zzbm();
        }
        switch (i & 7) {
            case 0:
                return zzb(bArr, i2, zzay);
            case 1:
                return i2 + 8;
            case 2:
                return zza(bArr, i2, zzay) + zzay.zzfd;
            case 3:
                int i4 = (i & -8) | 4;
                int i5 = 0;
                int i6 = i2;
                while (i6 < i3) {
                    i6 = zza(bArr, i6, zzay);
                    i5 = zzay.zzfd;
                    if (i5 != i4) {
                        i6 = zza(i5, bArr, i6, i3, zzay);
                    } else if (i6 > i3 && i5 == i4) {
                        return i6;
                    } else {
                        throw zzco.zzbo();
                    }
                }
                if (i6 > i3) {
                }
                throw zzco.zzbo();
            case 5:
                return i2 + 4;
            default:
                throw zzco.zzbm();
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzcn<?> zzcn, zzay zzay) {
        zzch zzch = (zzch) zzcn;
        int zza = zza(bArr, i2, zzay);
        zzch.zzac(zzay.zzfd);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzay);
            if (i != zzay.zzfd) {
                break;
            }
            zza = zza(bArr, zza2, zzay);
            zzch.zzac(zzay.zzfd);
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzey zzey, zzay zzay) throws IOException {
        if ((i >>> 3) == 0) {
            throw zzco.zzbm();
        }
        switch (i & 7) {
            case 0:
                int zzb = zzb(bArr, i2, zzay);
                zzey.zzb(i, Long.valueOf(zzay.zzfe));
                return zzb;
            case 1:
                zzey.zzb(i, Long.valueOf(zzd(bArr, i2)));
                return i2 + 8;
            case 2:
                int zza = zza(bArr, i2, zzay);
                int i4 = zzay.zzfd;
                if (i4 == 0) {
                    zzey.zzb(i, zzbb.zzfi);
                } else {
                    zzey.zzb(i, zzbb.zzb(bArr, zza, i4));
                }
                return zza + i4;
            case 3:
                zzey zzeb = zzey.zzeb();
                int i5 = (i & -8) | 4;
                int i6 = 0;
                int i7 = i2;
                while (true) {
                    if (i7 < i3) {
                        int zza2 = zza(bArr, i7, zzay);
                        i6 = zzay.zzfd;
                        if (i6 != i5) {
                            i7 = zza(i6, bArr, zza2, i3, zzeb, zzay);
                        } else {
                            i7 = zza2;
                        }
                    }
                }
                if (i7 > i3 || i6 != i5) {
                    throw zzco.zzbo();
                }
                zzey.zzb(i, zzeb);
                return i7;
            case 5:
                zzey.zzb(i, Integer.valueOf(zzc(bArr, i2)));
                return i2 + 4;
            default:
                throw zzco.zzbm();
        }
    }

    static int zza(int i, byte[] bArr, int i2, zzay zzay) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzay.zzfd = i3 | (b << 7);
            return i4;
        }
        int i5 = ((b & Byte.MAX_VALUE) << 7) | i3;
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzay.zzfd = (b2 << 14) | i5;
            return i6;
        }
        int i7 = ((b2 & Byte.MAX_VALUE) << 14) | i5;
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzay.zzfd = i7 | (b3 << 21);
            return i8;
        }
        int i9 = ((b3 & Byte.MAX_VALUE) << 21) | i7;
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzay.zzfd = i9 | (b4 << 28);
            return i10;
        }
        int i11 = ((b4 & Byte.MAX_VALUE) << 28) | i9;
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzay.zzfd = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zza(byte[] bArr, int i, zzay zzay) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zza((int) b, bArr, i2, zzay);
        }
        zzay.zzfd = b;
        return i2;
    }

    static int zza(byte[] bArr, int i, zzcn<?> zzcn, zzay zzay) throws IOException {
        zzch zzch = (zzch) zzcn;
        int zza = zza(bArr, i, zzay);
        int i2 = zzay.zzfd + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, zzay);
            zzch.zzac(zzay.zzfd);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzco.zzbl();
    }

    static int zzb(byte[] bArr, int i, zzay zzay) {
        int i2 = 7;
        int i3 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzay.zzfe = j;
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
            zzay.zzfe = j3;
        }
        return i3;
    }

    static int zzc(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }

    static int zzc(byte[] bArr, int i, zzay zzay) {
        int zza = zza(bArr, i, zzay);
        int i2 = zzay.zzfd;
        if (i2 == 0) {
            zzay.zzff = "";
            return zza;
        }
        zzay.zzff = new String(bArr, zza, i2, zzci.UTF_8);
        return zza + i2;
    }

    static int zzd(byte[] bArr, int i, zzay zzay) throws IOException {
        int zza = zza(bArr, i, zzay);
        int i2 = zzay.zzfd;
        if (i2 == 0) {
            zzay.zzff = "";
            return zza;
        } else if (!zzff.zze(bArr, zza, zza + i2)) {
            throw zzco.zzbp();
        } else {
            zzay.zzff = new String(bArr, zza, i2, zzci.UTF_8);
            return zza + i2;
        }
    }

    static long zzd(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    static double zze(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzd(bArr, i));
    }

    static int zze(byte[] bArr, int i, zzay zzay) {
        int zza = zza(bArr, i, zzay);
        int i2 = zzay.zzfd;
        if (i2 == 0) {
            zzay.zzff = zzbb.zzfi;
            return zza;
        }
        zzay.zzff = zzbb.zzb(bArr, zza, i2);
        return zza + i2;
    }

    static float zzf(byte[] bArr, int i) {
        return Float.intBitsToFloat(zzc(bArr, i));
    }
}
