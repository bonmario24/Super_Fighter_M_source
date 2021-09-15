package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzgq<T> implements zzhf<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzid.zzc();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzgm zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final boolean zzk;
    private final int[] zzl;
    private final int zzm;
    private final int zzn;
    private final zzgu zzo;
    private final zzfw zzp;
    private final zzhx<?, ?> zzq;
    private final zzet<?> zzr;
    private final zzgj zzs;

    private zzgq(int[] iArr, Object[] objArr, int i, int i2, zzgm zzgm, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzgu zzgu, zzfw zzfw, zzhx<?, ?> zzhx, zzet<?> zzet, zzgj zzgj) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzgm instanceof zzfe;
        this.zzj = z;
        this.zzh = zzet != null && zzet.zza(zzgm);
        this.zzk = false;
        this.zzl = iArr2;
        this.zzm = i3;
        this.zzn = i4;
        this.zzo = zzgu;
        this.zzp = zzfw;
        this.zzq = zzhx;
        this.zzr = zzet;
        this.zzg = zzgm;
        this.zzs = zzgj;
    }

    static <T> zzgq<T> zza(Class<T> cls, zzgk zzgk, zzgu zzgu, zzfw zzfw, zzhx<?, ?> zzhx, zzet<?> zzet, zzgj zzgj) {
        int i;
        int i2;
        int i3;
        int i4;
        char c;
        char c2;
        char c3;
        char c4;
        int[] iArr;
        int i5;
        char c5;
        char c6;
        char c7;
        char charAt;
        char charAt2;
        char charAt3;
        char charAt4;
        char charAt5;
        char charAt6;
        char charAt7;
        char charAt8;
        int i6;
        int i7;
        char c8;
        int i8;
        int i9;
        char c9;
        int i10;
        int i11;
        int i12;
        char c10;
        int i13;
        char c11;
        int i14;
        Field zza2;
        char charAt9;
        char c12;
        int i15;
        int i16;
        Field zza3;
        Field zza4;
        int i17;
        char charAt10;
        char charAt11;
        char charAt12;
        int i18;
        char charAt13;
        if (zzgk instanceof zzhd) {
            zzhd zzhd = (zzhd) zzgk;
            boolean z = zzhd.zza() == zzfe.zzf.zzi;
            String zzd2 = zzhd.zzd();
            int length = zzd2.length();
            int i19 = 1;
            char charAt14 = zzd2.charAt(0);
            if (charAt14 >= 55296) {
                char c13 = charAt14 & 8191;
                int i20 = 13;
                while (true) {
                    i = i19 + 1;
                    char charAt15 = zzd2.charAt(i19);
                    if (charAt15 < 55296) {
                        break;
                    }
                    c13 |= (charAt15 & 8191) << i20;
                    i20 += 13;
                    i19 = i;
                }
            } else {
                i = 1;
            }
            int i21 = i + 1;
            char charAt16 = zzd2.charAt(i);
            if (charAt16 >= 55296) {
                char c14 = charAt16 & 8191;
                int i22 = 13;
                while (true) {
                    i18 = i21 + 1;
                    charAt13 = zzd2.charAt(i21);
                    if (charAt13 < 55296) {
                        break;
                    }
                    c14 |= (charAt13 & 8191) << i22;
                    i22 += 13;
                    i21 = i18;
                }
                charAt16 = (charAt13 << i22) | c14;
                i2 = i18;
            } else {
                i2 = i21;
            }
            if (charAt16 == 0) {
                c6 = 0;
                c = 0;
                c2 = 0;
                c4 = 0;
                iArr = zza;
                i5 = 0;
                c5 = 0;
                c7 = 0;
            } else {
                int i23 = i3 + 1;
                char charAt17 = zzd2.charAt(i3);
                if (charAt17 >= 55296) {
                    char c15 = charAt17 & 8191;
                    int i24 = 13;
                    while (true) {
                        i4 = i23 + 1;
                        charAt8 = zzd2.charAt(i23);
                        if (charAt8 < 55296) {
                            break;
                        }
                        c15 |= (charAt8 & 8191) << i24;
                        i24 += 13;
                        i23 = i4;
                    }
                    charAt17 = (charAt8 << i24) | c15;
                } else {
                    i4 = i23;
                }
                int i25 = i4 + 1;
                char charAt18 = zzd2.charAt(i4);
                if (charAt18 >= 55296) {
                    char c16 = charAt18 & 8191;
                    int i26 = 13;
                    while (true) {
                        int i27 = i25;
                        i25 = i27 + 1;
                        charAt7 = zzd2.charAt(i27);
                        if (charAt7 < 55296) {
                            break;
                        }
                        c16 |= (charAt7 & 8191) << i26;
                        i26 += 13;
                    }
                    charAt18 = (charAt7 << i26) | c16;
                }
                int i28 = i25 + 1;
                char charAt19 = zzd2.charAt(i25);
                if (charAt19 >= 55296) {
                    char c17 = charAt19 & 8191;
                    int i29 = 13;
                    while (true) {
                        int i30 = i28;
                        i28 = i30 + 1;
                        charAt6 = zzd2.charAt(i30);
                        if (charAt6 < 55296) {
                            break;
                        }
                        c17 |= (charAt6 & 8191) << i29;
                        i29 += 13;
                    }
                    charAt19 = (charAt6 << i29) | c17;
                }
                int i31 = i28 + 1;
                char charAt20 = zzd2.charAt(i28);
                if (charAt20 >= 55296) {
                    char c18 = charAt20 & 8191;
                    int i32 = 13;
                    while (true) {
                        int i33 = i31;
                        i31 = i33 + 1;
                        charAt5 = zzd2.charAt(i33);
                        if (charAt5 < 55296) {
                            break;
                        }
                        c18 |= (charAt5 & 8191) << i32;
                        i32 += 13;
                    }
                    c = (charAt5 << i32) | c18;
                } else {
                    c = charAt20;
                }
                int i34 = i31 + 1;
                char charAt21 = zzd2.charAt(i31);
                if (charAt21 >= 55296) {
                    char c19 = charAt21 & 8191;
                    int i35 = 13;
                    while (true) {
                        int i36 = i34;
                        i34 = i36 + 1;
                        charAt4 = zzd2.charAt(i36);
                        if (charAt4 < 55296) {
                            break;
                        }
                        c19 |= (charAt4 & 8191) << i35;
                        i35 += 13;
                    }
                    c2 = (charAt4 << i35) | c19;
                } else {
                    c2 = charAt21;
                }
                int i37 = i34 + 1;
                char charAt22 = zzd2.charAt(i34);
                if (charAt22 >= 55296) {
                    char c20 = charAt22 & 8191;
                    int i38 = 13;
                    while (true) {
                        int i39 = i37;
                        i37 = i39 + 1;
                        charAt3 = zzd2.charAt(i39);
                        if (charAt3 < 55296) {
                            break;
                        }
                        c20 |= (charAt3 & 8191) << i38;
                        i38 += 13;
                    }
                    c3 = (charAt3 << i38) | c20;
                } else {
                    c3 = charAt22;
                }
                int i40 = i37 + 1;
                char charAt23 = zzd2.charAt(i37);
                if (charAt23 >= 55296) {
                    char c21 = charAt23 & 8191;
                    int i41 = 13;
                    while (true) {
                        int i42 = i40;
                        i40 = i42 + 1;
                        charAt2 = zzd2.charAt(i42);
                        if (charAt2 < 55296) {
                            break;
                        }
                        c21 |= (charAt2 & 8191) << i41;
                        i41 += 13;
                    }
                    charAt23 = (charAt2 << i41) | c21;
                }
                i3 = i40 + 1;
                char charAt24 = zzd2.charAt(i40);
                if (charAt24 >= 55296) {
                    char c22 = charAt24 & 8191;
                    int i43 = 13;
                    while (true) {
                        int i44 = i3;
                        i3 = i44 + 1;
                        charAt = zzd2.charAt(i44);
                        if (charAt < 55296) {
                            break;
                        }
                        c22 |= (charAt & 8191) << i43;
                        i43 += 13;
                    }
                    c4 = (charAt << i43) | c22;
                } else {
                    c4 = charAt24;
                }
                iArr = new int[(charAt23 + c4 + c3)];
                i5 = charAt18 + (charAt17 << 1);
                c5 = c3;
                c6 = charAt19;
                c7 = charAt17;
            }
            Unsafe unsafe = zzb;
            Object[] zze2 = zzhd.zze();
            int i45 = 0;
            Class<?> cls2 = zzhd.zzc().getClass();
            int[] iArr2 = new int[(c2 * 3)];
            Object[] objArr = new Object[(c2 << 1)];
            int i46 = c4 + c5;
            int i47 = 0;
            char c23 = c4;
            int i48 = i5;
            while (i3 < length) {
                int i49 = i3 + 1;
                char charAt25 = zzd2.charAt(i3);
                if (charAt25 >= 55296) {
                    char c24 = charAt25 & 8191;
                    int i50 = 13;
                    while (true) {
                        i7 = i49 + 1;
                        charAt12 = zzd2.charAt(i49);
                        if (charAt12 < 55296) {
                            break;
                        }
                        c24 |= (charAt12 & 8191) << i50;
                        i50 += 13;
                        i49 = i7;
                    }
                    i6 = (charAt12 << i50) | c24;
                } else {
                    i6 = charAt25;
                    i7 = i49;
                }
                int i51 = i7 + 1;
                char charAt26 = zzd2.charAt(i7);
                if (charAt26 >= 55296) {
                    char c25 = charAt26 & 8191;
                    int i52 = 13;
                    while (true) {
                        int i53 = i51;
                        i51 = i53 + 1;
                        charAt11 = zzd2.charAt(i53);
                        if (charAt11 < 55296) {
                            break;
                        }
                        c25 |= (charAt11 & 8191) << i52;
                        i52 += 13;
                    }
                    c8 = (charAt11 << i52) | c25;
                    i8 = i51;
                } else {
                    c8 = charAt26;
                    i8 = i51;
                }
                char c26 = c8 & 255;
                if ((c8 & 1024) != 0) {
                    iArr[i45] = i47;
                    i9 = i45 + 1;
                } else {
                    i9 = i45;
                }
                if (c26 >= '3') {
                    int i54 = i8 + 1;
                    char charAt27 = zzd2.charAt(i8);
                    if (charAt27 >= 55296) {
                        char c27 = charAt27 & 8191;
                        int i55 = 13;
                        while (true) {
                            i17 = i54 + 1;
                            charAt10 = zzd2.charAt(i54);
                            if (charAt10 < 55296) {
                                break;
                            }
                            c27 |= (charAt10 & 8191) << i55;
                            i55 += 13;
                            i54 = i17;
                        }
                        c12 = (charAt10 << i55) | c27;
                        i15 = i17;
                    } else {
                        c12 = charAt27;
                        i15 = i54;
                    }
                    int i56 = c26 - '3';
                    if (i56 == 9 || i56 == 17) {
                        objArr[((i47 / 3) << 1) + 1] = zze2[i48];
                        i16 = i48 + 1;
                    } else if (i56 != 12 || z) {
                        i16 = i48;
                    } else {
                        objArr[((i47 / 3) << 1) + 1] = zze2[i48];
                        i16 = i48 + 1;
                    }
                    int i57 = c12 << 1;
                    Object obj = zze2[i57];
                    if (obj instanceof Field) {
                        zza3 = (Field) obj;
                    } else {
                        zza3 = zza(cls2, (String) obj);
                        zze2[i57] = zza3;
                    }
                    i12 = (int) unsafe.objectFieldOffset(zza3);
                    int i58 = i57 + 1;
                    Object obj2 = zze2[i58];
                    if (obj2 instanceof Field) {
                        zza4 = (Field) obj2;
                    } else {
                        zza4 = zza(cls2, (String) obj2);
                        zze2[i58] = zza4;
                    }
                    i10 = (int) unsafe.objectFieldOffset(zza4);
                    i11 = 0;
                    c10 = c23;
                    i13 = i16;
                    i8 = i15;
                } else {
                    int i59 = i48 + 1;
                    Field zza5 = zza(cls2, (String) zze2[i48]);
                    if (c26 == 9 || c26 == 17) {
                        objArr[((i47 / 3) << 1) + 1] = zza5.getType();
                        c9 = c23;
                    } else if (c26 == 27 || c26 == '1') {
                        objArr[((i47 / 3) << 1) + 1] = zze2[i59];
                        c9 = c23;
                        i59++;
                    } else {
                        if (c26 == 12 || c26 == 30 || c26 == ',') {
                            if (!z) {
                                objArr[((i47 / 3) << 1) + 1] = zze2[i59];
                                c9 = c23;
                                i59++;
                            }
                        } else if (c26 == '2') {
                            int i60 = c23 + 1;
                            iArr[c23] = i47;
                            int i61 = i59 + 1;
                            objArr[(i47 / 3) << 1] = zze2[i59];
                            if ((c8 & 2048) != 0) {
                                i59 = i61 + 1;
                                objArr[((i47 / 3) << 1) + 1] = zze2[i61];
                                c9 = i60;
                            } else {
                                c9 = i60;
                                i59 = i61;
                            }
                        }
                        c9 = c23;
                    }
                    int objectFieldOffset = (int) unsafe.objectFieldOffset(zza5);
                    if ((c8 & 4096) != 4096 || c26 > 17) {
                        i10 = 1048575;
                        i11 = 0;
                    } else {
                        int i62 = i8 + 1;
                        char charAt28 = zzd2.charAt(i8);
                        if (charAt28 >= 55296) {
                            char c28 = charAt28 & 8191;
                            int i63 = 13;
                            while (true) {
                                i14 = i62 + 1;
                                charAt9 = zzd2.charAt(i62);
                                if (charAt9 < 55296) {
                                    break;
                                }
                                c28 |= (charAt9 & 8191) << i63;
                                i63 += 13;
                                i62 = i14;
                            }
                            c11 = (charAt9 << i63) | c28;
                        } else {
                            c11 = charAt28;
                            i14 = i62;
                        }
                        int i64 = (c11 / ' ') + (c7 << 1);
                        Object obj3 = zze2[i64];
                        if (obj3 instanceof Field) {
                            zza2 = (Field) obj3;
                        } else {
                            zza2 = zza(cls2, (String) obj3);
                            zze2[i64] = zza2;
                        }
                        i10 = (int) unsafe.objectFieldOffset(zza2);
                        i11 = c11 % ' ';
                        i8 = i14;
                    }
                    if (c26 < 18 || c26 > '1') {
                        i12 = objectFieldOffset;
                        c10 = c9;
                        i13 = i59;
                    } else {
                        iArr[i46] = objectFieldOffset;
                        i12 = objectFieldOffset;
                        i46++;
                        c10 = c9;
                        i13 = i59;
                    }
                }
                int i65 = i47 + 1;
                iArr2[i47] = i6;
                int i66 = i65 + 1;
                iArr2[i65] = ((c8 & 256) != 0 ? 268435456 : 0) | ((c8 & 512) != 0 ? 536870912 : 0) | (c26 << 20) | i12;
                iArr2[i66] = i10 | (i11 << 20);
                i47 = i66 + 1;
                c23 = c10;
                i45 = i9;
                i48 = i13;
                i3 = i8;
            }
            return new zzgq<>(iArr2, objArr, c6, c, zzhd.zzc(), z, false, iArr, c4, c4 + c5, zzgu, zzfw, zzhx, zzet, zzgj);
        }
        if (((zzhq) zzgk).zza() == zzfe.zzf.zzi) {
        }
        throw new NoSuchMethodError();
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            throw new RuntimeException(new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length()).append("Field ").append(str).append(" for ").append(name).append(" not found. Known fields are ").append(arrays).toString());
        }
    }

    public final T zza() {
        return this.zzo.zza(this.zzg);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(T r12, T r13) {
        /*
            r11 = this;
            r1 = 1
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r0 = 0
            int[] r2 = r11.zzc
            int r4 = r2.length
            r3 = r0
        L_0x0009:
            if (r3 >= r4) goto L_0x01e0
            int r2 = r11.zzd((int) r3)
            r5 = r2 & r10
            long r6 = (long) r5
            r5 = 267386880(0xff00000, float:2.3665827E-29)
            r2 = r2 & r5
            int r2 = r2 >>> 20
            switch(r2) {
                case 0: goto L_0x001e;
                case 1: goto L_0x003a;
                case 2: goto L_0x0054;
                case 3: goto L_0x0068;
                case 4: goto L_0x007c;
                case 5: goto L_0x008e;
                case 6: goto L_0x00a3;
                case 7: goto L_0x00b6;
                case 8: goto L_0x00c9;
                case 9: goto L_0x00e0;
                case 10: goto L_0x00f7;
                case 11: goto L_0x010e;
                case 12: goto L_0x0121;
                case 13: goto L_0x0134;
                case 14: goto L_0x0147;
                case 15: goto L_0x015c;
                case 16: goto L_0x016f;
                case 17: goto L_0x0184;
                case 18: goto L_0x019b;
                case 19: goto L_0x019b;
                case 20: goto L_0x019b;
                case 21: goto L_0x019b;
                case 22: goto L_0x019b;
                case 23: goto L_0x019b;
                case 24: goto L_0x019b;
                case 25: goto L_0x019b;
                case 26: goto L_0x019b;
                case 27: goto L_0x019b;
                case 28: goto L_0x019b;
                case 29: goto L_0x019b;
                case 30: goto L_0x019b;
                case 31: goto L_0x019b;
                case 32: goto L_0x019b;
                case 33: goto L_0x019b;
                case 34: goto L_0x019b;
                case 35: goto L_0x019b;
                case 36: goto L_0x019b;
                case 37: goto L_0x019b;
                case 38: goto L_0x019b;
                case 39: goto L_0x019b;
                case 40: goto L_0x019b;
                case 41: goto L_0x019b;
                case 42: goto L_0x019b;
                case 43: goto L_0x019b;
                case 44: goto L_0x019b;
                case 45: goto L_0x019b;
                case 46: goto L_0x019b;
                case 47: goto L_0x019b;
                case 48: goto L_0x019b;
                case 49: goto L_0x019b;
                case 50: goto L_0x01a9;
                case 51: goto L_0x01b7;
                case 52: goto L_0x01b7;
                case 53: goto L_0x01b7;
                case 54: goto L_0x01b7;
                case 55: goto L_0x01b7;
                case 56: goto L_0x01b7;
                case 57: goto L_0x01b7;
                case 58: goto L_0x01b7;
                case 59: goto L_0x01b7;
                case 60: goto L_0x01b7;
                case 61: goto L_0x01b7;
                case 62: goto L_0x01b7;
                case 63: goto L_0x01b7;
                case 64: goto L_0x01b7;
                case 65: goto L_0x01b7;
                case 66: goto L_0x01b7;
                case 67: goto L_0x01b7;
                case 68: goto L_0x01b7;
                default: goto L_0x001a;
            }
        L_0x001a:
            r2 = r1
        L_0x001b:
            if (r2 != 0) goto L_0x01db
        L_0x001d:
            return r0
        L_0x001e:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0038
            double r8 = com.google.android.gms.internal.measurement.zzid.zze(r12, r6)
            long r8 = java.lang.Double.doubleToLongBits(r8)
            double r6 = com.google.android.gms.internal.measurement.zzid.zze(r13, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0038:
            r2 = r0
            goto L_0x001b
        L_0x003a:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0052
            float r2 = com.google.android.gms.internal.measurement.zzid.zzd(r12, r6)
            int r2 = java.lang.Float.floatToIntBits(r2)
            float r5 = com.google.android.gms.internal.measurement.zzid.zzd(r13, r6)
            int r5 = java.lang.Float.floatToIntBits(r5)
            if (r2 == r5) goto L_0x001a
        L_0x0052:
            r2 = r0
            goto L_0x001b
        L_0x0054:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0066
            long r8 = com.google.android.gms.internal.measurement.zzid.zzb(r12, r6)
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0066:
            r2 = r0
            goto L_0x001b
        L_0x0068:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x007a
            long r8 = com.google.android.gms.internal.measurement.zzid.zzb(r12, r6)
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x007a:
            r2 = r0
            goto L_0x001b
        L_0x007c:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x008c
            int r2 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r12, (long) r6)
            int r5 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r13, (long) r6)
            if (r2 == r5) goto L_0x001a
        L_0x008c:
            r2 = r0
            goto L_0x001b
        L_0x008e:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00a0
            long r8 = com.google.android.gms.internal.measurement.zzid.zzb(r12, r6)
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x00a0:
            r2 = r0
            goto L_0x001b
        L_0x00a3:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00b3
            int r2 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r12, (long) r6)
            int r5 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r13, (long) r6)
            if (r2 == r5) goto L_0x001a
        L_0x00b3:
            r2 = r0
            goto L_0x001b
        L_0x00b6:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00c6
            boolean r2 = com.google.android.gms.internal.measurement.zzid.zzc(r12, r6)
            boolean r5 = com.google.android.gms.internal.measurement.zzid.zzc(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x00c6:
            r2 = r0
            goto L_0x001b
        L_0x00c9:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00dd
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzid.zzf(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzid.zzf(r13, r6)
            boolean r2 = com.google.android.gms.internal.measurement.zzhh.zza((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x00dd:
            r2 = r0
            goto L_0x001b
        L_0x00e0:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00f4
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzid.zzf(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzid.zzf(r13, r6)
            boolean r2 = com.google.android.gms.internal.measurement.zzhh.zza((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x00f4:
            r2 = r0
            goto L_0x001b
        L_0x00f7:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x010b
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzid.zzf(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzid.zzf(r13, r6)
            boolean r2 = com.google.android.gms.internal.measurement.zzhh.zza((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x010b:
            r2 = r0
            goto L_0x001b
        L_0x010e:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x011e
            int r2 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r12, (long) r6)
            int r5 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r13, (long) r6)
            if (r2 == r5) goto L_0x001a
        L_0x011e:
            r2 = r0
            goto L_0x001b
        L_0x0121:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0131
            int r2 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r12, (long) r6)
            int r5 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r13, (long) r6)
            if (r2 == r5) goto L_0x001a
        L_0x0131:
            r2 = r0
            goto L_0x001b
        L_0x0134:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0144
            int r2 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r12, (long) r6)
            int r5 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r13, (long) r6)
            if (r2 == r5) goto L_0x001a
        L_0x0144:
            r2 = r0
            goto L_0x001b
        L_0x0147:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0159
            long r8 = com.google.android.gms.internal.measurement.zzid.zzb(r12, r6)
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0159:
            r2 = r0
            goto L_0x001b
        L_0x015c:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x016c
            int r2 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r12, (long) r6)
            int r5 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r13, (long) r6)
            if (r2 == r5) goto L_0x001a
        L_0x016c:
            r2 = r0
            goto L_0x001b
        L_0x016f:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0181
            long r8 = com.google.android.gms.internal.measurement.zzid.zzb(r12, r6)
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0181:
            r2 = r0
            goto L_0x001b
        L_0x0184:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0198
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzid.zzf(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzid.zzf(r13, r6)
            boolean r2 = com.google.android.gms.internal.measurement.zzhh.zza((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x0198:
            r2 = r0
            goto L_0x001b
        L_0x019b:
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzid.zzf(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzid.zzf(r13, r6)
            boolean r2 = com.google.android.gms.internal.measurement.zzhh.zza((java.lang.Object) r2, (java.lang.Object) r5)
            goto L_0x001b
        L_0x01a9:
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzid.zzf(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzid.zzf(r13, r6)
            boolean r2 = com.google.android.gms.internal.measurement.zzhh.zza((java.lang.Object) r2, (java.lang.Object) r5)
            goto L_0x001b
        L_0x01b7:
            int r2 = r11.zze((int) r3)
            r5 = r2 & r10
            long r8 = (long) r5
            int r5 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r12, (long) r8)
            r2 = r2 & r10
            long r8 = (long) r2
            int r2 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r13, (long) r8)
            if (r5 != r2) goto L_0x01d8
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzid.zzf(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzid.zzf(r13, r6)
            boolean r2 = com.google.android.gms.internal.measurement.zzhh.zza((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x01d8:
            r2 = r0
            goto L_0x001b
        L_0x01db:
            int r2 = r3 + 3
            r3 = r2
            goto L_0x0009
        L_0x01e0:
            com.google.android.gms.internal.measurement.zzhx<?, ?> r2 = r11.zzq
            java.lang.Object r2 = r2.zzb(r12)
            com.google.android.gms.internal.measurement.zzhx<?, ?> r3 = r11.zzq
            java.lang.Object r3 = r3.zzb(r13)
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x001d
            boolean r0 = r11.zzh
            if (r0 == 0) goto L_0x0208
            com.google.android.gms.internal.measurement.zzet<?> r0 = r11.zzr
            com.google.android.gms.internal.measurement.zzeu r0 = r0.zza((java.lang.Object) r12)
            com.google.android.gms.internal.measurement.zzet<?> r1 = r11.zzr
            com.google.android.gms.internal.measurement.zzeu r1 = r1.zza((java.lang.Object) r13)
            boolean r0 = r0.equals(r1)
            goto L_0x001d
        L_0x0208:
            r0 = r1
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgq.zza(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(T r10) {
        /*
            r9 = this;
            r1 = 37
            r0 = 0
            int[] r2 = r9.zzc
            int r4 = r2.length
            r3 = r0
            r2 = r0
        L_0x0008:
            if (r3 >= r4) goto L_0x0254
            int r0 = r9.zzd((int) r3)
            int[] r5 = r9.zzc
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
            int r3 = r3 + 3
            r2 = r0
            goto L_0x0008
        L_0x0024:
            int r0 = r2 * 53
            double r6 = com.google.android.gms.internal.measurement.zzid.zze(r10, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r2 = com.google.android.gms.internal.measurement.zzfh.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0034:
            int r0 = r2 * 53
            float r2 = com.google.android.gms.internal.measurement.zzid.zzd(r10, r6)
            int r2 = java.lang.Float.floatToIntBits(r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0040:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r10, r6)
            int r2 = com.google.android.gms.internal.measurement.zzfh.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x004c:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r10, r6)
            int r2 = com.google.android.gms.internal.measurement.zzfh.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0058:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r10, (long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0060:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r10, r6)
            int r2 = com.google.android.gms.internal.measurement.zzfh.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x006c:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r10, (long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0074:
            int r0 = r2 * 53
            boolean r2 = com.google.android.gms.internal.measurement.zzid.zzc(r10, r6)
            int r2 = com.google.android.gms.internal.measurement.zzfh.zza((boolean) r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0080:
            int r2 = r2 * 53
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r10, r6)
            java.lang.String r0 = (java.lang.String) r0
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x008e:
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r10, r6)
            if (r0 == 0) goto L_0x0276
            int r0 = r0.hashCode()
        L_0x0098:
            int r2 = r2 * 53
            int r0 = r0 + r2
            goto L_0x0020
        L_0x009c:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzid.zzf(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00a9:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r10, (long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00b2:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r10, (long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00bb:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r10, (long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00c4:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r10, r6)
            int r2 = com.google.android.gms.internal.measurement.zzfh.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00d1:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r10, (long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00da:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r10, r6)
            int r2 = com.google.android.gms.internal.measurement.zzfh.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00e7:
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r10, r6)
            if (r0 == 0) goto L_0x0273
            int r0 = r0.hashCode()
        L_0x00f1:
            int r2 = r2 * 53
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00f6:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzid.zzf(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0103:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzid.zzf(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0110:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            double r6 = zzb(r10, (long) r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r2 = com.google.android.gms.internal.measurement.zzfh.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0127:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            float r2 = zzc(r10, r6)
            int r2 = java.lang.Float.floatToIntBits(r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x013a:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zze(r10, r6)
            int r2 = com.google.android.gms.internal.measurement.zzfh.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x014d:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zze(r10, r6)
            int r2 = com.google.android.gms.internal.measurement.zzfh.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0160:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzd(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x016f:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zze(r10, r6)
            int r2 = com.google.android.gms.internal.measurement.zzfh.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0182:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzd(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0191:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            boolean r2 = zzf(r10, r6)
            int r2 = com.google.android.gms.internal.measurement.zzfh.zza((boolean) r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01a4:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r2 = r2 * 53
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r10, r6)
            java.lang.String r0 = (java.lang.String) r0
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01b9:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r10, r6)
            int r2 = r2 * 53
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01cc:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzid.zzf(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01df:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzd(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01ee:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzd(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01fd:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzd(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x020c:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zze(r10, r6)
            int r2 = com.google.android.gms.internal.measurement.zzfh.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x021f:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            int r2 = zzd(r10, r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x022e:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zze(r10, r6)
            int r2 = com.google.android.gms.internal.measurement.zzfh.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0241:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r10, r6)
            int r2 = r2 * 53
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0254:
            int r0 = r2 * 53
            com.google.android.gms.internal.measurement.zzhx<?, ?> r1 = r9.zzq
            java.lang.Object r1 = r1.zzb(r10)
            int r1 = r1.hashCode()
            int r0 = r0 + r1
            boolean r1 = r9.zzh
            if (r1 == 0) goto L_0x0272
            int r0 = r0 * 53
            com.google.android.gms.internal.measurement.zzet<?> r1 = r9.zzr
            com.google.android.gms.internal.measurement.zzeu r1 = r1.zza((java.lang.Object) r10)
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgq.zza(java.lang.Object):int");
    }

    public final void zzb(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzd2 = zzd(i);
            long j = (long) (1048575 & zzd2);
            int i2 = this.zzc[i];
            switch ((zzd2 & 267386880) >>> 20) {
                case 0:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zze(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 1:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zzd(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 2:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zzb(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 3:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zzb(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 4:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zza((Object) t2, j));
                        zzb(t, i);
                        break;
                    }
                case 5:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zzb(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 6:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zza((Object) t2, j));
                        zzb(t, i);
                        break;
                    }
                case 7:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zzc(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 8:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zzf(t2, j));
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
                        zzid.zza((Object) t, j, zzid.zzf(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 11:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zza((Object) t2, j));
                        zzb(t, i);
                        break;
                    }
                case 12:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zza((Object) t2, j));
                        zzb(t, i);
                        break;
                    }
                case 13:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zza((Object) t2, j));
                        zzb(t, i);
                        break;
                    }
                case 14:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zzb(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 15:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zza((Object) t2, j));
                        zzb(t, i);
                        break;
                    }
                case 16:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzid.zza((Object) t, j, zzid.zzb(t2, j));
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
                    this.zzp.zza(t, t2, j);
                    break;
                case 50:
                    zzhh.zza(this.zzs, t, t2, j);
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
                        zzid.zza((Object) t, j, zzid.zzf(t2, j));
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
                        zzid.zza((Object) t, j, zzid.zzf(t2, j));
                        zzb(t, i2, i);
                        break;
                    }
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        zzhh.zza(this.zzq, t, t2);
        if (this.zzh) {
            zzhh.zza(this.zzr, t, t2);
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzd2 = (long) (zzd(i) & 1048575);
        if (zza(t2, i)) {
            Object zzf2 = zzid.zzf(t, zzd2);
            Object zzf3 = zzid.zzf(t2, zzd2);
            if (zzf2 != null && zzf3 != null) {
                zzid.zza((Object) t, zzd2, zzfh.zza(zzf2, zzf3));
                zzb(t, i);
            } else if (zzf3 != null) {
                zzid.zza((Object) t, zzd2, zzf3);
                zzb(t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzd2 = zzd(i);
        int i2 = this.zzc[i];
        long j = (long) (zzd2 & 1048575);
        if (zza(t2, i2, i)) {
            Object zzf2 = zzid.zzf(t, j);
            Object zzf3 = zzid.zzf(t2, j);
            if (zzf2 != null && zzf3 != null) {
                zzid.zza((Object) t, j, zzfh.zza(zzf2, zzf3));
                zzb(t, i2, i);
            } else if (zzf3 != null) {
                zzid.zza((Object) t, j, zzf3);
                zzb(t, i2, i);
            }
        }
    }

    public final int zzb(T t) {
        int i;
        int i2;
        if (this.zzj) {
            Unsafe unsafe = zzb;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                int i5 = i4;
                if (i5 >= this.zzc.length) {
                    return zza(this.zzq, t) + i3;
                }
                int zzd2 = zzd(i5);
                int i6 = (267386880 & zzd2) >>> 20;
                int i7 = this.zzc[i5];
                long j = (long) (zzd2 & 1048575);
                if (i6 < zzez.DOUBLE_LIST_PACKED.zza() || i6 > zzez.SINT64_LIST_PACKED.zza()) {
                    i2 = 0;
                } else {
                    i2 = this.zzc[i5 + 2] & 1048575;
                }
                switch (i6) {
                    case 0:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzb(i7, 0.0d);
                            break;
                        }
                    case 1:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzb(i7, 0.0f);
                            break;
                        }
                    case 2:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzd(i7, zzid.zzb(t, j));
                            break;
                        }
                    case 3:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zze(i7, zzid.zzb(t, j));
                            break;
                        }
                    case 4:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzf(i7, zzid.zza((Object) t, j));
                            break;
                        }
                    case 5:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzg(i7, 0);
                            break;
                        }
                    case 6:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzi(i7, 0);
                            break;
                        }
                    case 7:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzb(i7, true);
                            break;
                        }
                    case 8:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            Object zzf2 = zzid.zzf(t, j);
                            if (!(zzf2 instanceof zzdw)) {
                                i3 += zzel.zzb(i7, (String) zzf2);
                                break;
                            } else {
                                i3 += zzel.zzc(i7, (zzdw) zzf2);
                                break;
                            }
                        }
                    case 9:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhh.zza(i7, zzid.zzf(t, j), zza(i5));
                            break;
                        }
                    case 10:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzc(i7, (zzdw) zzid.zzf(t, j));
                            break;
                        }
                    case 11:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzg(i7, zzid.zza((Object) t, j));
                            break;
                        }
                    case 12:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzk(i7, zzid.zza((Object) t, j));
                            break;
                        }
                    case 13:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzj(i7, 0);
                            break;
                        }
                    case 14:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzh(i7, 0);
                            break;
                        }
                    case 15:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzh(i7, zzid.zza((Object) t, j));
                            break;
                        }
                    case 16:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzf(i7, zzid.zzb(t, j));
                            break;
                        }
                    case 17:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzc(i7, (zzgm) zzid.zzf(t, j), zza(i5));
                            break;
                        }
                    case 18:
                        i3 += zzhh.zzi(i7, zza((Object) t, j), false);
                        break;
                    case 19:
                        i3 += zzhh.zzh(i7, zza((Object) t, j), false);
                        break;
                    case 20:
                        i3 += zzhh.zza(i7, (List<Long>) zza((Object) t, j), false);
                        break;
                    case 21:
                        i3 += zzhh.zzb(i7, (List<Long>) zza((Object) t, j), false);
                        break;
                    case 22:
                        i3 += zzhh.zze(i7, zza((Object) t, j), false);
                        break;
                    case 23:
                        i3 += zzhh.zzi(i7, zza((Object) t, j), false);
                        break;
                    case 24:
                        i3 += zzhh.zzh(i7, zza((Object) t, j), false);
                        break;
                    case 25:
                        i3 += zzhh.zzj(i7, zza((Object) t, j), false);
                        break;
                    case 26:
                        i3 += zzhh.zza(i7, zza((Object) t, j));
                        break;
                    case 27:
                        i3 += zzhh.zza(i7, zza((Object) t, j), zza(i5));
                        break;
                    case 28:
                        i3 += zzhh.zzb(i7, zza((Object) t, j));
                        break;
                    case 29:
                        i3 += zzhh.zzf(i7, zza((Object) t, j), false);
                        break;
                    case 30:
                        i3 += zzhh.zzd(i7, zza((Object) t, j), false);
                        break;
                    case 31:
                        i3 += zzhh.zzh(i7, zza((Object) t, j), false);
                        break;
                    case 32:
                        i3 += zzhh.zzi(i7, zza((Object) t, j), false);
                        break;
                    case 33:
                        i3 += zzhh.zzg(i7, zza((Object) t, j), false);
                        break;
                    case 34:
                        i3 += zzhh.zzc(i7, zza((Object) t, j), false);
                        break;
                    case 35:
                        int zzi2 = zzhh.zzi((List) unsafe.getObject(t, j));
                        if (zzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzi2);
                            }
                            i3 += zzi2 + zzel.zze(i7) + zzel.zzg(zzi2);
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        int zzh2 = zzhh.zzh((List) unsafe.getObject(t, j));
                        if (zzh2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzh2);
                            }
                            i3 += zzh2 + zzel.zze(i7) + zzel.zzg(zzh2);
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        int zza2 = zzhh.zza((List<Long>) (List) unsafe.getObject(t, j));
                        if (zza2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zza2);
                            }
                            i3 += zza2 + zzel.zze(i7) + zzel.zzg(zza2);
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        int zzb2 = zzhh.zzb((List) unsafe.getObject(t, j));
                        if (zzb2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzb2);
                            }
                            i3 += zzb2 + zzel.zze(i7) + zzel.zzg(zzb2);
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        int zze2 = zzhh.zze((List) unsafe.getObject(t, j));
                        if (zze2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zze2);
                            }
                            i3 += zze2 + zzel.zze(i7) + zzel.zzg(zze2);
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        int zzi3 = zzhh.zzi((List) unsafe.getObject(t, j));
                        if (zzi3 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzi3);
                            }
                            i3 += zzi3 + zzel.zze(i7) + zzel.zzg(zzi3);
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        int zzh3 = zzhh.zzh((List) unsafe.getObject(t, j));
                        if (zzh3 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzh3);
                            }
                            i3 += zzh3 + zzel.zze(i7) + zzel.zzg(zzh3);
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        int zzj2 = zzhh.zzj((List) unsafe.getObject(t, j));
                        if (zzj2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzj2);
                            }
                            i3 += zzj2 + zzel.zze(i7) + zzel.zzg(zzj2);
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        int zzf3 = zzhh.zzf((List) unsafe.getObject(t, j));
                        if (zzf3 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzf3);
                            }
                            i3 += zzf3 + zzel.zze(i7) + zzel.zzg(zzf3);
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        int zzd3 = zzhh.zzd((List) unsafe.getObject(t, j));
                        if (zzd3 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzd3);
                            }
                            i3 += zzd3 + zzel.zze(i7) + zzel.zzg(zzd3);
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        int zzh4 = zzhh.zzh((List) unsafe.getObject(t, j));
                        if (zzh4 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzh4);
                            }
                            i3 += zzh4 + zzel.zze(i7) + zzel.zzg(zzh4);
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        int zzi4 = zzhh.zzi((List) unsafe.getObject(t, j));
                        if (zzi4 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzi4);
                            }
                            i3 += zzi4 + zzel.zze(i7) + zzel.zzg(zzi4);
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        int zzg2 = zzhh.zzg((List) unsafe.getObject(t, j));
                        if (zzg2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzg2);
                            }
                            i3 += zzg2 + zzel.zze(i7) + zzel.zzg(zzg2);
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        int zzc2 = zzhh.zzc((List) unsafe.getObject(t, j));
                        if (zzc2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzc2);
                            }
                            i3 += zzc2 + zzel.zze(i7) + zzel.zzg(zzc2);
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        i3 += zzhh.zzb(i7, (List<zzgm>) zza((Object) t, j), zza(i5));
                        break;
                    case 50:
                        i3 += this.zzs.zza(i7, zzid.zzf(t, j), zzb(i5));
                        break;
                    case 51:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzb(i7, 0.0d);
                            break;
                        }
                    case 52:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzb(i7, 0.0f);
                            break;
                        }
                    case 53:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzd(i7, zze(t, j));
                            break;
                        }
                    case 54:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zze(i7, zze(t, j));
                            break;
                        }
                    case 55:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzf(i7, zzd(t, j));
                            break;
                        }
                    case 56:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzg(i7, 0);
                            break;
                        }
                    case 57:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzi(i7, 0);
                            break;
                        }
                    case 58:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzb(i7, true);
                            break;
                        }
                    case 59:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            Object zzf4 = zzid.zzf(t, j);
                            if (!(zzf4 instanceof zzdw)) {
                                i3 += zzel.zzb(i7, (String) zzf4);
                                break;
                            } else {
                                i3 += zzel.zzc(i7, (zzdw) zzf4);
                                break;
                            }
                        }
                    case 60:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhh.zza(i7, zzid.zzf(t, j), zza(i5));
                            break;
                        }
                    case 61:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzc(i7, (zzdw) zzid.zzf(t, j));
                            break;
                        }
                    case 62:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzg(i7, zzd(t, j));
                            break;
                        }
                    case 63:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzk(i7, zzd(t, j));
                            break;
                        }
                    case 64:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzj(i7, 0);
                            break;
                        }
                    case 65:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzh(i7, 0);
                            break;
                        }
                    case 66:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzh(i7, zzd(t, j));
                            break;
                        }
                    case 67:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzf(i7, zze(t, j));
                            break;
                        }
                    case 68:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzel.zzc(i7, (zzgm) zzid.zzf(t, j), zza(i5));
                            break;
                        }
                }
                i4 = i5 + 3;
            }
        } else {
            int i8 = 0;
            Unsafe unsafe2 = zzb;
            int i9 = 1048575;
            int i10 = 0;
            int i11 = 0;
            while (true) {
                int i12 = i11;
                if (i12 < this.zzc.length) {
                    int zzd4 = zzd(i12);
                    int i13 = this.zzc[i12];
                    int i14 = (267386880 & zzd4) >>> 20;
                    int i15 = 0;
                    if (i14 <= 17) {
                        i = this.zzc[i12 + 2];
                        int i16 = 1048575 & i;
                        int i17 = 1 << (i >>> 20);
                        if (i16 != i9) {
                            i10 = unsafe2.getInt(t, (long) i16);
                            i9 = i16;
                        }
                        i15 = i17;
                    } else if (!this.zzk || i14 < zzez.DOUBLE_LIST_PACKED.zza() || i14 > zzez.SINT64_LIST_PACKED.zza()) {
                        i = 0;
                    } else {
                        i = this.zzc[i12 + 2] & 1048575;
                    }
                    long j2 = (long) (1048575 & zzd4);
                    switch (i14) {
                        case 0:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzb(i13, 0.0d);
                                break;
                            }
                        case 1:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzb(i13, 0.0f);
                                break;
                            }
                        case 2:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzd(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 3:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zze(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 4:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzf(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 5:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzg(i13, 0);
                                break;
                            }
                        case 6:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzi(i13, 0);
                                break;
                            }
                        case 7:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzb(i13, true);
                                break;
                            }
                        case 8:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                Object object = unsafe2.getObject(t, j2);
                                if (!(object instanceof zzdw)) {
                                    i8 += zzel.zzb(i13, (String) object);
                                    break;
                                } else {
                                    i8 += zzel.zzc(i13, (zzdw) object);
                                    break;
                                }
                            }
                        case 9:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhh.zza(i13, unsafe2.getObject(t, j2), zza(i12));
                                break;
                            }
                        case 10:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzc(i13, (zzdw) unsafe2.getObject(t, j2));
                                break;
                            }
                        case 11:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzg(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 12:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzk(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 13:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzj(i13, 0);
                                break;
                            }
                        case 14:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzh(i13, 0);
                                break;
                            }
                        case 15:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzh(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 16:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzf(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 17:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzel.zzc(i13, (zzgm) unsafe2.getObject(t, j2), zza(i12));
                                break;
                            }
                        case 18:
                            i8 += zzhh.zzi(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 19:
                            i8 += zzhh.zzh(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 20:
                            i8 += zzhh.zza(i13, (List<Long>) (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 21:
                            i8 += zzhh.zzb(i13, (List<Long>) (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 22:
                            i8 += zzhh.zze(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 23:
                            i8 += zzhh.zzi(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 24:
                            i8 += zzhh.zzh(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 25:
                            i8 += zzhh.zzj(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 26:
                            i8 += zzhh.zza(i13, (List<?>) (List) unsafe2.getObject(t, j2));
                            break;
                        case 27:
                            i8 += zzhh.zza(i13, (List<?>) (List) unsafe2.getObject(t, j2), zza(i12));
                            break;
                        case 28:
                            i8 += zzhh.zzb(i13, (List) unsafe2.getObject(t, j2));
                            break;
                        case 29:
                            i8 += zzhh.zzf(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 30:
                            i8 += zzhh.zzd(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 31:
                            i8 += zzhh.zzh(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 32:
                            i8 += zzhh.zzi(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 33:
                            i8 += zzhh.zzg(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 34:
                            i8 += zzhh.zzc(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 35:
                            int zzi5 = zzhh.zzi((List) unsafe2.getObject(t, j2));
                            if (zzi5 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzi5);
                                }
                                i8 += zzi5 + zzel.zze(i13) + zzel.zzg(zzi5);
                                break;
                            } else {
                                break;
                            }
                        case 36:
                            int zzh5 = zzhh.zzh((List) unsafe2.getObject(t, j2));
                            if (zzh5 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzh5);
                                }
                                i8 += zzh5 + zzel.zze(i13) + zzel.zzg(zzh5);
                                break;
                            } else {
                                break;
                            }
                        case 37:
                            int zza3 = zzhh.zza((List<Long>) (List) unsafe2.getObject(t, j2));
                            if (zza3 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zza3);
                                }
                                i8 += zza3 + zzel.zze(i13) + zzel.zzg(zza3);
                                break;
                            } else {
                                break;
                            }
                        case 38:
                            int zzb3 = zzhh.zzb((List) unsafe2.getObject(t, j2));
                            if (zzb3 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzb3);
                                }
                                i8 += zzb3 + zzel.zze(i13) + zzel.zzg(zzb3);
                                break;
                            } else {
                                break;
                            }
                        case 39:
                            int zze3 = zzhh.zze((List) unsafe2.getObject(t, j2));
                            if (zze3 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zze3);
                                }
                                i8 += zze3 + zzel.zze(i13) + zzel.zzg(zze3);
                                break;
                            } else {
                                break;
                            }
                        case 40:
                            int zzi6 = zzhh.zzi((List) unsafe2.getObject(t, j2));
                            if (zzi6 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzi6);
                                }
                                i8 += zzi6 + zzel.zze(i13) + zzel.zzg(zzi6);
                                break;
                            } else {
                                break;
                            }
                        case 41:
                            int zzh6 = zzhh.zzh((List) unsafe2.getObject(t, j2));
                            if (zzh6 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzh6);
                                }
                                i8 += zzh6 + zzel.zze(i13) + zzel.zzg(zzh6);
                                break;
                            } else {
                                break;
                            }
                        case 42:
                            int zzj3 = zzhh.zzj((List) unsafe2.getObject(t, j2));
                            if (zzj3 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzj3);
                                }
                                i8 += zzj3 + zzel.zze(i13) + zzel.zzg(zzj3);
                                break;
                            } else {
                                break;
                            }
                        case 43:
                            int zzf5 = zzhh.zzf((List) unsafe2.getObject(t, j2));
                            if (zzf5 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzf5);
                                }
                                i8 += zzf5 + zzel.zze(i13) + zzel.zzg(zzf5);
                                break;
                            } else {
                                break;
                            }
                        case 44:
                            int zzd5 = zzhh.zzd((List) unsafe2.getObject(t, j2));
                            if (zzd5 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzd5);
                                }
                                i8 += zzd5 + zzel.zze(i13) + zzel.zzg(zzd5);
                                break;
                            } else {
                                break;
                            }
                        case 45:
                            int zzh7 = zzhh.zzh((List) unsafe2.getObject(t, j2));
                            if (zzh7 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzh7);
                                }
                                i8 += zzh7 + zzel.zze(i13) + zzel.zzg(zzh7);
                                break;
                            } else {
                                break;
                            }
                        case 46:
                            int zzi7 = zzhh.zzi((List) unsafe2.getObject(t, j2));
                            if (zzi7 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzi7);
                                }
                                i8 += zzi7 + zzel.zze(i13) + zzel.zzg(zzi7);
                                break;
                            } else {
                                break;
                            }
                        case 47:
                            int zzg3 = zzhh.zzg((List) unsafe2.getObject(t, j2));
                            if (zzg3 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzg3);
                                }
                                i8 += zzg3 + zzel.zze(i13) + zzel.zzg(zzg3);
                                break;
                            } else {
                                break;
                            }
                        case 48:
                            int zzc3 = zzhh.zzc((List) unsafe2.getObject(t, j2));
                            if (zzc3 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzc3);
                                }
                                i8 += zzc3 + zzel.zze(i13) + zzel.zzg(zzc3);
                                break;
                            } else {
                                break;
                            }
                        case 49:
                            i8 += zzhh.zzb(i13, (List<zzgm>) (List) unsafe2.getObject(t, j2), zza(i12));
                            break;
                        case 50:
                            i8 += this.zzs.zza(i13, unsafe2.getObject(t, j2), zzb(i12));
                            break;
                        case 51:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzb(i13, 0.0d);
                                break;
                            }
                        case 52:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzb(i13, 0.0f);
                                break;
                            }
                        case 53:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzd(i13, zze(t, j2));
                                break;
                            }
                        case 54:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zze(i13, zze(t, j2));
                                break;
                            }
                        case 55:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzf(i13, zzd(t, j2));
                                break;
                            }
                        case 56:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzg(i13, 0);
                                break;
                            }
                        case 57:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzi(i13, 0);
                                break;
                            }
                        case 58:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzb(i13, true);
                                break;
                            }
                        case 59:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                Object object2 = unsafe2.getObject(t, j2);
                                if (!(object2 instanceof zzdw)) {
                                    i8 += zzel.zzb(i13, (String) object2);
                                    break;
                                } else {
                                    i8 += zzel.zzc(i13, (zzdw) object2);
                                    break;
                                }
                            }
                        case 60:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhh.zza(i13, unsafe2.getObject(t, j2), zza(i12));
                                break;
                            }
                        case 61:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzc(i13, (zzdw) unsafe2.getObject(t, j2));
                                break;
                            }
                        case 62:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzg(i13, zzd(t, j2));
                                break;
                            }
                        case 63:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzk(i13, zzd(t, j2));
                                break;
                            }
                        case 64:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzj(i13, 0);
                                break;
                            }
                        case 65:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzh(i13, 0);
                                break;
                            }
                        case 66:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzh(i13, zzd(t, j2));
                                break;
                            }
                        case 67:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzf(i13, zze(t, j2));
                                break;
                            }
                        case 68:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzel.zzc(i13, (zzgm) unsafe2.getObject(t, j2), zza(i12));
                                break;
                            }
                    }
                    i11 = i12 + 3;
                } else {
                    int zza4 = i8 + zza(this.zzq, t);
                    if (!this.zzh) {
                        return zza4;
                    }
                    zzeu<?> zza5 = this.zzr.zza((Object) t);
                    int i18 = 0;
                    int i19 = 0;
                    while (true) {
                        int i20 = i19;
                        if (i20 < zza5.zza.zzc()) {
                            Map.Entry<T, Object> zzb4 = zza5.zza.zzb(i20);
                            i18 += zzeu.zza((zzew<?>) (zzew) zzb4.getKey(), zzb4.getValue());
                            i19 = i20 + 1;
                        } else {
                            for (Map.Entry next : zza5.zza.zzd()) {
                                i18 += zzeu.zza((zzew<?>) (zzew) next.getKey(), next.getValue());
                            }
                            return zza4 + i18;
                        }
                    }
                }
            }
        }
    }

    private static <UT, UB> int zza(zzhx<UT, UB> zzhx, T t) {
        return zzhx.zzf(zzhx.zzb(t));
    }

    private static List<?> zza(Object obj, long j) {
        return (List) zzid.zzf(obj, j);
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 547 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r11, com.google.android.gms.internal.measurement.zziq r12) throws java.io.IOException {
        /*
            r10 = this;
            int r0 = r12.zza()
            int r1 = com.google.android.gms.internal.measurement.zzfe.zzf.zzk
            if (r0 != r1) goto L_0x060c
            com.google.android.gms.internal.measurement.zzhx<?, ?> r0 = r10.zzq
            zza(r0, r11, (com.google.android.gms.internal.measurement.zziq) r12)
            r1 = 0
            r0 = 0
            boolean r2 = r10.zzh
            if (r2 == 0) goto L_0x002b
            com.google.android.gms.internal.measurement.zzet<?> r2 = r10.zzr
            com.google.android.gms.internal.measurement.zzeu r2 = r2.zza((java.lang.Object) r11)
            com.google.android.gms.internal.measurement.zzhg<T, java.lang.Object> r3 = r2.zza
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x002b
            java.util.Iterator r1 = r2.zze()
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x002b:
            int[] r2 = r10.zzc
            int r2 = r2.length
            int r2 = r2 + -3
            r3 = r2
        L_0x0031:
            if (r3 < 0) goto L_0x05f8
            int r4 = r10.zzd((int) r3)
            int[] r2 = r10.zzc
            r5 = r2[r3]
            r2 = r0
        L_0x003c:
            if (r2 == 0) goto L_0x005b
            com.google.android.gms.internal.measurement.zzet<?> r0 = r10.zzr
            int r0 = r0.zza((java.util.Map.Entry<?, ?>) r2)
            if (r0 <= r5) goto L_0x005b
            com.google.android.gms.internal.measurement.zzet<?> r0 = r10.zzr
            r0.zza(r12, r2)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x0059
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x0057:
            r2 = r0
            goto L_0x003c
        L_0x0059:
            r0 = 0
            goto L_0x0057
        L_0x005b:
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r4
            int r0 = r0 >>> 20
            switch(r0) {
                case 0: goto L_0x0067;
                case 1: goto L_0x007a;
                case 2: goto L_0x008d;
                case 3: goto L_0x00a0;
                case 4: goto L_0x00b3;
                case 5: goto L_0x00c6;
                case 6: goto L_0x00d9;
                case 7: goto L_0x00ed;
                case 8: goto L_0x0101;
                case 9: goto L_0x0115;
                case 10: goto L_0x012d;
                case 11: goto L_0x0143;
                case 12: goto L_0x0157;
                case 13: goto L_0x016b;
                case 14: goto L_0x017f;
                case 15: goto L_0x0193;
                case 16: goto L_0x01a7;
                case 17: goto L_0x01bb;
                case 18: goto L_0x01d3;
                case 19: goto L_0x01e8;
                case 20: goto L_0x01fd;
                case 21: goto L_0x0212;
                case 22: goto L_0x0227;
                case 23: goto L_0x023c;
                case 24: goto L_0x0251;
                case 25: goto L_0x0266;
                case 26: goto L_0x027b;
                case 27: goto L_0x028f;
                case 28: goto L_0x02a7;
                case 29: goto L_0x02bb;
                case 30: goto L_0x02d0;
                case 31: goto L_0x02e5;
                case 32: goto L_0x02fa;
                case 33: goto L_0x030f;
                case 34: goto L_0x0324;
                case 35: goto L_0x0339;
                case 36: goto L_0x034e;
                case 37: goto L_0x0363;
                case 38: goto L_0x0378;
                case 39: goto L_0x038d;
                case 40: goto L_0x03a2;
                case 41: goto L_0x03b7;
                case 42: goto L_0x03cc;
                case 43: goto L_0x03e1;
                case 44: goto L_0x03f6;
                case 45: goto L_0x040b;
                case 46: goto L_0x0420;
                case 47: goto L_0x0435;
                case 48: goto L_0x044a;
                case 49: goto L_0x045f;
                case 50: goto L_0x0477;
                case 51: goto L_0x0485;
                case 52: goto L_0x0499;
                case 53: goto L_0x04ad;
                case 54: goto L_0x04c1;
                case 55: goto L_0x04d5;
                case 56: goto L_0x04e9;
                case 57: goto L_0x04fd;
                case 58: goto L_0x0511;
                case 59: goto L_0x0525;
                case 60: goto L_0x0539;
                case 61: goto L_0x0551;
                case 62: goto L_0x0567;
                case 63: goto L_0x057b;
                case 64: goto L_0x058f;
                case 65: goto L_0x05a3;
                case 66: goto L_0x05b7;
                case 67: goto L_0x05cb;
                case 68: goto L_0x05df;
                default: goto L_0x0063;
            }
        L_0x0063:
            int r3 = r3 + -3
            r0 = r2
            goto L_0x0031
        L_0x0067:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            double r6 = com.google.android.gms.internal.measurement.zzid.zze(r11, r6)
            r12.zza((int) r5, (double) r6)
            goto L_0x0063
        L_0x007a:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            float r0 = com.google.android.gms.internal.measurement.zzid.zzd(r11, r6)
            r12.zza((int) r5, (float) r0)
            goto L_0x0063
        L_0x008d:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r11, r6)
            r12.zza((int) r5, (long) r6)
            goto L_0x0063
        L_0x00a0:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r11, r6)
            r12.zzc((int) r5, (long) r6)
            goto L_0x0063
        L_0x00b3:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r11, (long) r6)
            r12.zzc((int) r5, (int) r0)
            goto L_0x0063
        L_0x00c6:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r11, r6)
            r12.zzd((int) r5, (long) r6)
            goto L_0x0063
        L_0x00d9:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r11, (long) r6)
            r12.zzd((int) r5, (int) r0)
            goto L_0x0063
        L_0x00ed:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            boolean r0 = com.google.android.gms.internal.measurement.zzid.zzc(r11, r6)
            r12.zza((int) r5, (boolean) r0)
            goto L_0x0063
        L_0x0101:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.measurement.zziq) r12)
            goto L_0x0063
        L_0x0115:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            com.google.android.gms.internal.measurement.zzhf r4 = r10.zza((int) r3)
            r12.zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.measurement.zzhf) r4)
            goto L_0x0063
        L_0x012d:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            com.google.android.gms.internal.measurement.zzdw r0 = (com.google.android.gms.internal.measurement.zzdw) r0
            r12.zza((int) r5, (com.google.android.gms.internal.measurement.zzdw) r0)
            goto L_0x0063
        L_0x0143:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r11, (long) r6)
            r12.zze((int) r5, (int) r0)
            goto L_0x0063
        L_0x0157:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r11, (long) r6)
            r12.zzb((int) r5, (int) r0)
            goto L_0x0063
        L_0x016b:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r11, (long) r6)
            r12.zza((int) r5, (int) r0)
            goto L_0x0063
        L_0x017f:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r11, r6)
            r12.zzb((int) r5, (long) r6)
            goto L_0x0063
        L_0x0193:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r11, (long) r6)
            r12.zzf(r5, r0)
            goto L_0x0063
        L_0x01a7:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.measurement.zzid.zzb(r11, r6)
            r12.zze((int) r5, (long) r6)
            goto L_0x0063
        L_0x01bb:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            com.google.android.gms.internal.measurement.zzhf r4 = r10.zza((int) r3)
            r12.zzb((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.measurement.zzhf) r4)
            goto L_0x0063
        L_0x01d3:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.measurement.zzhh.zza((int) r5, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.measurement.zziq) r12, (boolean) r4)
            goto L_0x0063
        L_0x01e8:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.measurement.zzhh.zzb((int) r5, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.measurement.zziq) r12, (boolean) r4)
            goto L_0x0063
        L_0x01fd:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.measurement.zzhh.zzc(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0212:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.measurement.zzhh.zzd(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0227:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.measurement.zzhh.zzh(r5, r0, r12, r4)
            goto L_0x0063
        L_0x023c:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.measurement.zzhh.zzf(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0251:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.measurement.zzhh.zzk(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0266:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.measurement.zzhh.zzn(r5, r0, r12, r4)
            goto L_0x0063
        L_0x027b:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.measurement.zzhh.zza((int) r5, (java.util.List<java.lang.String>) r0, (com.google.android.gms.internal.measurement.zziq) r12)
            goto L_0x0063
        L_0x028f:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.measurement.zzhf r4 = r10.zza((int) r3)
            com.google.android.gms.internal.measurement.zzhh.zza((int) r5, (java.util.List<?>) r0, (com.google.android.gms.internal.measurement.zziq) r12, (com.google.android.gms.internal.measurement.zzhf) r4)
            goto L_0x0063
        L_0x02a7:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.measurement.zzhh.zzb((int) r5, (java.util.List<com.google.android.gms.internal.measurement.zzdw>) r0, (com.google.android.gms.internal.measurement.zziq) r12)
            goto L_0x0063
        L_0x02bb:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.measurement.zzhh.zzi(r5, r0, r12, r4)
            goto L_0x0063
        L_0x02d0:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.measurement.zzhh.zzm(r5, r0, r12, r4)
            goto L_0x0063
        L_0x02e5:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.measurement.zzhh.zzl(r5, r0, r12, r4)
            goto L_0x0063
        L_0x02fa:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.measurement.zzhh.zzg(r5, r0, r12, r4)
            goto L_0x0063
        L_0x030f:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.measurement.zzhh.zzj(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0324:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.measurement.zzhh.zze(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0339:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.measurement.zzhh.zza((int) r5, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.measurement.zziq) r12, (boolean) r4)
            goto L_0x0063
        L_0x034e:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.measurement.zzhh.zzb((int) r5, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.measurement.zziq) r12, (boolean) r4)
            goto L_0x0063
        L_0x0363:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.measurement.zzhh.zzc(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0378:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.measurement.zzhh.zzd(r5, r0, r12, r4)
            goto L_0x0063
        L_0x038d:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.measurement.zzhh.zzh(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03a2:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.measurement.zzhh.zzf(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03b7:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.measurement.zzhh.zzk(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03cc:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.measurement.zzhh.zzn(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03e1:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.measurement.zzhh.zzi(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03f6:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.measurement.zzhh.zzm(r5, r0, r12, r4)
            goto L_0x0063
        L_0x040b:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.measurement.zzhh.zzl(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0420:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.measurement.zzhh.zzg(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0435:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.measurement.zzhh.zzj(r5, r0, r12, r4)
            goto L_0x0063
        L_0x044a:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.measurement.zzhh.zze(r5, r0, r12, r4)
            goto L_0x0063
        L_0x045f:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.measurement.zzhf r4 = r10.zza((int) r3)
            com.google.android.gms.internal.measurement.zzhh.zzb((int) r5, (java.util.List<?>) r0, (com.google.android.gms.internal.measurement.zziq) r12, (com.google.android.gms.internal.measurement.zzhf) r4)
            goto L_0x0063
        L_0x0477:
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            r10.zza((com.google.android.gms.internal.measurement.zziq) r12, (int) r5, (java.lang.Object) r0, (int) r3)
            goto L_0x0063
        L_0x0485:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            double r6 = zzb(r11, (long) r6)
            r12.zza((int) r5, (double) r6)
            goto L_0x0063
        L_0x0499:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            float r0 = zzc(r11, r6)
            r12.zza((int) r5, (float) r0)
            goto L_0x0063
        L_0x04ad:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zze(r11, r6)
            r12.zza((int) r5, (long) r6)
            goto L_0x0063
        L_0x04c1:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zze(r11, r6)
            r12.zzc((int) r5, (long) r6)
            goto L_0x0063
        L_0x04d5:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzd(r11, r6)
            r12.zzc((int) r5, (int) r0)
            goto L_0x0063
        L_0x04e9:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zze(r11, r6)
            r12.zzd((int) r5, (long) r6)
            goto L_0x0063
        L_0x04fd:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzd(r11, r6)
            r12.zzd((int) r5, (int) r0)
            goto L_0x0063
        L_0x0511:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            boolean r0 = zzf(r11, r6)
            r12.zza((int) r5, (boolean) r0)
            goto L_0x0063
        L_0x0525:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.measurement.zziq) r12)
            goto L_0x0063
        L_0x0539:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            com.google.android.gms.internal.measurement.zzhf r4 = r10.zza((int) r3)
            r12.zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.measurement.zzhf) r4)
            goto L_0x0063
        L_0x0551:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            com.google.android.gms.internal.measurement.zzdw r0 = (com.google.android.gms.internal.measurement.zzdw) r0
            r12.zza((int) r5, (com.google.android.gms.internal.measurement.zzdw) r0)
            goto L_0x0063
        L_0x0567:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzd(r11, r6)
            r12.zze((int) r5, (int) r0)
            goto L_0x0063
        L_0x057b:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzd(r11, r6)
            r12.zzb((int) r5, (int) r0)
            goto L_0x0063
        L_0x058f:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzd(r11, r6)
            r12.zza((int) r5, (int) r0)
            goto L_0x0063
        L_0x05a3:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zze(r11, r6)
            r12.zzb((int) r5, (long) r6)
            goto L_0x0063
        L_0x05b7:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = zzd(r11, r6)
            r12.zzf(r5, r0)
            goto L_0x0063
        L_0x05cb:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = zze(r11, r6)
            r12.zze((int) r5, (long) r6)
            goto L_0x0063
        L_0x05df:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r6)
            com.google.android.gms.internal.measurement.zzhf r4 = r10.zza((int) r3)
            r12.zzb((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.measurement.zzhf) r4)
            goto L_0x0063
        L_0x05f7:
            r0 = 0
        L_0x05f8:
            if (r0 == 0) goto L_0x0c13
            com.google.android.gms.internal.measurement.zzet<?> r2 = r10.zzr
            r2.zza(r12, r0)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x05f7
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            goto L_0x05f8
        L_0x060c:
            boolean r0 = r10.zzj
            if (r0 == 0) goto L_0x0c14
            r1 = 0
            r0 = 0
            boolean r2 = r10.zzh
            if (r2 == 0) goto L_0x062e
            com.google.android.gms.internal.measurement.zzet<?> r2 = r10.zzr
            com.google.android.gms.internal.measurement.zzeu r2 = r2.zza((java.lang.Object) r11)
            com.google.android.gms.internal.measurement.zzhg<T, java.lang.Object> r3 = r2.zza
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x062e
            java.util.Iterator r1 = r2.zzd()
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x062e:
            int[] r2 = r10.zzc
            int r4 = r2.length
            r2 = 0
            r3 = r2
        L_0x0633:
            if (r3 >= r4) goto L_0x0bfa
            int r5 = r10.zzd((int) r3)
            int[] r2 = r10.zzc
            r6 = r2[r3]
            r2 = r0
        L_0x063e:
            if (r2 == 0) goto L_0x065d
            com.google.android.gms.internal.measurement.zzet<?> r0 = r10.zzr
            int r0 = r0.zza((java.util.Map.Entry<?, ?>) r2)
            if (r0 > r6) goto L_0x065d
            com.google.android.gms.internal.measurement.zzet<?> r0 = r10.zzr
            r0.zza(r12, r2)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x065b
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
        L_0x0659:
            r2 = r0
            goto L_0x063e
        L_0x065b:
            r0 = 0
            goto L_0x0659
        L_0x065d:
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r5
            int r0 = r0 >>> 20
            switch(r0) {
                case 0: goto L_0x0669;
                case 1: goto L_0x067c;
                case 2: goto L_0x068f;
                case 3: goto L_0x06a2;
                case 4: goto L_0x06b5;
                case 5: goto L_0x06c8;
                case 6: goto L_0x06db;
                case 7: goto L_0x06ef;
                case 8: goto L_0x0703;
                case 9: goto L_0x0717;
                case 10: goto L_0x072f;
                case 11: goto L_0x0745;
                case 12: goto L_0x0759;
                case 13: goto L_0x076d;
                case 14: goto L_0x0781;
                case 15: goto L_0x0795;
                case 16: goto L_0x07a9;
                case 17: goto L_0x07bd;
                case 18: goto L_0x07d5;
                case 19: goto L_0x07ea;
                case 20: goto L_0x07ff;
                case 21: goto L_0x0814;
                case 22: goto L_0x0829;
                case 23: goto L_0x083e;
                case 24: goto L_0x0853;
                case 25: goto L_0x0868;
                case 26: goto L_0x087d;
                case 27: goto L_0x0891;
                case 28: goto L_0x08a9;
                case 29: goto L_0x08bd;
                case 30: goto L_0x08d2;
                case 31: goto L_0x08e7;
                case 32: goto L_0x08fc;
                case 33: goto L_0x0911;
                case 34: goto L_0x0926;
                case 35: goto L_0x093b;
                case 36: goto L_0x0950;
                case 37: goto L_0x0965;
                case 38: goto L_0x097a;
                case 39: goto L_0x098f;
                case 40: goto L_0x09a4;
                case 41: goto L_0x09b9;
                case 42: goto L_0x09ce;
                case 43: goto L_0x09e3;
                case 44: goto L_0x09f8;
                case 45: goto L_0x0a0d;
                case 46: goto L_0x0a22;
                case 47: goto L_0x0a37;
                case 48: goto L_0x0a4c;
                case 49: goto L_0x0a61;
                case 50: goto L_0x0a79;
                case 51: goto L_0x0a87;
                case 52: goto L_0x0a9b;
                case 53: goto L_0x0aaf;
                case 54: goto L_0x0ac3;
                case 55: goto L_0x0ad7;
                case 56: goto L_0x0aeb;
                case 57: goto L_0x0aff;
                case 58: goto L_0x0b13;
                case 59: goto L_0x0b27;
                case 60: goto L_0x0b3b;
                case 61: goto L_0x0b53;
                case 62: goto L_0x0b69;
                case 63: goto L_0x0b7d;
                case 64: goto L_0x0b91;
                case 65: goto L_0x0ba5;
                case 66: goto L_0x0bb9;
                case 67: goto L_0x0bcd;
                case 68: goto L_0x0be1;
                default: goto L_0x0665;
            }
        L_0x0665:
            int r3 = r3 + 3
            r0 = r2
            goto L_0x0633
        L_0x0669:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            double r8 = com.google.android.gms.internal.measurement.zzid.zze(r11, r8)
            r12.zza((int) r6, (double) r8)
            goto L_0x0665
        L_0x067c:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            float r0 = com.google.android.gms.internal.measurement.zzid.zzd(r11, r8)
            r12.zza((int) r6, (float) r0)
            goto L_0x0665
        L_0x068f:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.measurement.zzid.zzb(r11, r8)
            r12.zza((int) r6, (long) r8)
            goto L_0x0665
        L_0x06a2:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.measurement.zzid.zzb(r11, r8)
            r12.zzc((int) r6, (long) r8)
            goto L_0x0665
        L_0x06b5:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r11, (long) r8)
            r12.zzc((int) r6, (int) r0)
            goto L_0x0665
        L_0x06c8:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.measurement.zzid.zzb(r11, r8)
            r12.zzd((int) r6, (long) r8)
            goto L_0x0665
        L_0x06db:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r11, (long) r8)
            r12.zzd((int) r6, (int) r0)
            goto L_0x0665
        L_0x06ef:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            boolean r0 = com.google.android.gms.internal.measurement.zzid.zzc(r11, r8)
            r12.zza((int) r6, (boolean) r0)
            goto L_0x0665
        L_0x0703:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.measurement.zziq) r12)
            goto L_0x0665
        L_0x0717:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            com.google.android.gms.internal.measurement.zzhf r5 = r10.zza((int) r3)
            r12.zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.measurement.zzhf) r5)
            goto L_0x0665
        L_0x072f:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            com.google.android.gms.internal.measurement.zzdw r0 = (com.google.android.gms.internal.measurement.zzdw) r0
            r12.zza((int) r6, (com.google.android.gms.internal.measurement.zzdw) r0)
            goto L_0x0665
        L_0x0745:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r11, (long) r8)
            r12.zze((int) r6, (int) r0)
            goto L_0x0665
        L_0x0759:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r11, (long) r8)
            r12.zzb((int) r6, (int) r0)
            goto L_0x0665
        L_0x076d:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r11, (long) r8)
            r12.zza((int) r6, (int) r0)
            goto L_0x0665
        L_0x0781:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.measurement.zzid.zzb(r11, r8)
            r12.zzb((int) r6, (long) r8)
            goto L_0x0665
        L_0x0795:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r11, (long) r8)
            r12.zzf(r6, r0)
            goto L_0x0665
        L_0x07a9:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.measurement.zzid.zzb(r11, r8)
            r12.zze((int) r6, (long) r8)
            goto L_0x0665
        L_0x07bd:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            com.google.android.gms.internal.measurement.zzhf r5 = r10.zza((int) r3)
            r12.zzb((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.measurement.zzhf) r5)
            goto L_0x0665
        L_0x07d5:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.measurement.zzhh.zza((int) r6, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.measurement.zziq) r12, (boolean) r5)
            goto L_0x0665
        L_0x07ea:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.measurement.zzhh.zzb((int) r6, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.measurement.zziq) r12, (boolean) r5)
            goto L_0x0665
        L_0x07ff:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.measurement.zzhh.zzc(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0814:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.measurement.zzhh.zzd(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0829:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.measurement.zzhh.zzh(r6, r0, r12, r5)
            goto L_0x0665
        L_0x083e:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.measurement.zzhh.zzf(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0853:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.measurement.zzhh.zzk(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0868:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.measurement.zzhh.zzn(r6, r0, r12, r5)
            goto L_0x0665
        L_0x087d:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.measurement.zzhh.zza((int) r6, (java.util.List<java.lang.String>) r0, (com.google.android.gms.internal.measurement.zziq) r12)
            goto L_0x0665
        L_0x0891:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.measurement.zzhf r5 = r10.zza((int) r3)
            com.google.android.gms.internal.measurement.zzhh.zza((int) r6, (java.util.List<?>) r0, (com.google.android.gms.internal.measurement.zziq) r12, (com.google.android.gms.internal.measurement.zzhf) r5)
            goto L_0x0665
        L_0x08a9:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.measurement.zzhh.zzb((int) r6, (java.util.List<com.google.android.gms.internal.measurement.zzdw>) r0, (com.google.android.gms.internal.measurement.zziq) r12)
            goto L_0x0665
        L_0x08bd:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.measurement.zzhh.zzi(r6, r0, r12, r5)
            goto L_0x0665
        L_0x08d2:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.measurement.zzhh.zzm(r6, r0, r12, r5)
            goto L_0x0665
        L_0x08e7:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.measurement.zzhh.zzl(r6, r0, r12, r5)
            goto L_0x0665
        L_0x08fc:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.measurement.zzhh.zzg(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0911:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.measurement.zzhh.zzj(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0926:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.measurement.zzhh.zze(r6, r0, r12, r5)
            goto L_0x0665
        L_0x093b:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.measurement.zzhh.zza((int) r6, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.measurement.zziq) r12, (boolean) r5)
            goto L_0x0665
        L_0x0950:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.measurement.zzhh.zzb((int) r6, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.measurement.zziq) r12, (boolean) r5)
            goto L_0x0665
        L_0x0965:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.measurement.zzhh.zzc(r6, r0, r12, r5)
            goto L_0x0665
        L_0x097a:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.measurement.zzhh.zzd(r6, r0, r12, r5)
            goto L_0x0665
        L_0x098f:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.measurement.zzhh.zzh(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09a4:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.measurement.zzhh.zzf(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09b9:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.measurement.zzhh.zzk(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09ce:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.measurement.zzhh.zzn(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09e3:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.measurement.zzhh.zzi(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09f8:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.measurement.zzhh.zzm(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a0d:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.measurement.zzhh.zzl(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a22:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.measurement.zzhh.zzg(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a37:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.measurement.zzhh.zzj(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a4c:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.measurement.zzhh.zze(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a61:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.measurement.zzhf r5 = r10.zza((int) r3)
            com.google.android.gms.internal.measurement.zzhh.zzb((int) r6, (java.util.List<?>) r0, (com.google.android.gms.internal.measurement.zziq) r12, (com.google.android.gms.internal.measurement.zzhf) r5)
            goto L_0x0665
        L_0x0a79:
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            r10.zza((com.google.android.gms.internal.measurement.zziq) r12, (int) r6, (java.lang.Object) r0, (int) r3)
            goto L_0x0665
        L_0x0a87:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            double r8 = zzb(r11, (long) r8)
            r12.zza((int) r6, (double) r8)
            goto L_0x0665
        L_0x0a9b:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            float r0 = zzc(r11, r8)
            r12.zza((int) r6, (float) r0)
            goto L_0x0665
        L_0x0aaf:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zze(r11, r8)
            r12.zza((int) r6, (long) r8)
            goto L_0x0665
        L_0x0ac3:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zze(r11, r8)
            r12.zzc((int) r6, (long) r8)
            goto L_0x0665
        L_0x0ad7:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzd(r11, r8)
            r12.zzc((int) r6, (int) r0)
            goto L_0x0665
        L_0x0aeb:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zze(r11, r8)
            r12.zzd((int) r6, (long) r8)
            goto L_0x0665
        L_0x0aff:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzd(r11, r8)
            r12.zzd((int) r6, (int) r0)
            goto L_0x0665
        L_0x0b13:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            boolean r0 = zzf(r11, r8)
            r12.zza((int) r6, (boolean) r0)
            goto L_0x0665
        L_0x0b27:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.measurement.zziq) r12)
            goto L_0x0665
        L_0x0b3b:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            com.google.android.gms.internal.measurement.zzhf r5 = r10.zza((int) r3)
            r12.zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.measurement.zzhf) r5)
            goto L_0x0665
        L_0x0b53:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            com.google.android.gms.internal.measurement.zzdw r0 = (com.google.android.gms.internal.measurement.zzdw) r0
            r12.zza((int) r6, (com.google.android.gms.internal.measurement.zzdw) r0)
            goto L_0x0665
        L_0x0b69:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzd(r11, r8)
            r12.zze((int) r6, (int) r0)
            goto L_0x0665
        L_0x0b7d:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzd(r11, r8)
            r12.zzb((int) r6, (int) r0)
            goto L_0x0665
        L_0x0b91:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzd(r11, r8)
            r12.zza((int) r6, (int) r0)
            goto L_0x0665
        L_0x0ba5:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zze(r11, r8)
            r12.zzb((int) r6, (long) r8)
            goto L_0x0665
        L_0x0bb9:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = zzd(r11, r8)
            r12.zzf(r6, r0)
            goto L_0x0665
        L_0x0bcd:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = zze(r11, r8)
            r12.zze((int) r6, (long) r8)
            goto L_0x0665
        L_0x0be1:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r11, r8)
            com.google.android.gms.internal.measurement.zzhf r5 = r10.zza((int) r3)
            r12.zzb((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.measurement.zzhf) r5)
            goto L_0x0665
        L_0x0bf9:
            r0 = 0
        L_0x0bfa:
            if (r0 == 0) goto L_0x0c0e
            com.google.android.gms.internal.measurement.zzet<?> r2 = r10.zzr
            r2.zza(r12, r0)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x0bf9
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            goto L_0x0bfa
        L_0x0c0e:
            com.google.android.gms.internal.measurement.zzhx<?, ?> r0 = r10.zzq
            zza(r0, r11, (com.google.android.gms.internal.measurement.zziq) r12)
        L_0x0c13:
            return
        L_0x0c14:
            r10.zzb(r11, (com.google.android.gms.internal.measurement.zziq) r12)
            goto L_0x0c13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgq.zza(java.lang.Object, com.google.android.gms.internal.measurement.zziq):void");
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 387 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r21, com.google.android.gms.internal.measurement.zziq r22) throws java.io.IOException {
        /*
            r20 = this;
            r5 = 0
            r4 = 0
            r0 = r20
            boolean r6 = r0.zzh
            if (r6 == 0) goto L_0x0024
            r0 = r20
            com.google.android.gms.internal.measurement.zzet<?> r6 = r0.zzr
            r0 = r21
            com.google.android.gms.internal.measurement.zzeu r6 = r6.zza((java.lang.Object) r0)
            com.google.android.gms.internal.measurement.zzhg<T, java.lang.Object> r7 = r6.zza
            boolean r7 = r7.isEmpty()
            if (r7 != 0) goto L_0x0024
            java.util.Iterator r5 = r6.zzd()
            java.lang.Object r4 = r5.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
        L_0x0024:
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r6 = 0
            r0 = r20
            int[] r7 = r0.zzc
            int r13 = r7.length
            sun.misc.Unsafe r14 = zzb
            r7 = 0
            r12 = r7
            r9 = r4
        L_0x0032:
            if (r12 >= r13) goto L_0x06ff
            r0 = r20
            int r15 = r0.zzd((int) r12)
            r0 = r20
            int[] r4 = r0.zzc
            r16 = r4[r12]
            r4 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = r4 & r15
            int r17 = r4 >>> 20
            r4 = 0
            r0 = r20
            boolean r7 = r0.zzj
            if (r7 != 0) goto L_0x06fb
            r7 = 17
            r0 = r17
            if (r0 > r7) goto L_0x06fb
            r0 = r20
            int[] r4 = r0.zzc
            int r7 = r12 + 2
            r10 = r4[r7]
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r7 = r10 & r4
            if (r7 == r8) goto L_0x06f7
            long r0 = (long) r7
            r18 = r0
            r0 = r21
            r1 = r18
            int r4 = r14.getInt(r0, r1)
        L_0x006c:
            r6 = 1
            int r8 = r10 >>> 20
            int r6 = r6 << r8
            r10 = r6
            r11 = r4
            r8 = r7
        L_0x0073:
            if (r9 == 0) goto L_0x009a
            r0 = r20
            com.google.android.gms.internal.measurement.zzet<?> r4 = r0.zzr
            int r4 = r4.zza((java.util.Map.Entry<?, ?>) r9)
            r0 = r16
            if (r4 > r0) goto L_0x009a
            r0 = r20
            com.google.android.gms.internal.measurement.zzet<?> r4 = r0.zzr
            r0 = r22
            r4.zza(r0, r9)
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L_0x0098
            java.lang.Object r4 = r5.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
        L_0x0096:
            r9 = r4
            goto L_0x0073
        L_0x0098:
            r4 = 0
            goto L_0x0096
        L_0x009a:
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r15
            long r6 = (long) r4
            switch(r17) {
                case 0: goto L_0x00a7;
                case 1: goto L_0x00b9;
                case 2: goto L_0x00cb;
                case 3: goto L_0x00dd;
                case 4: goto L_0x00ef;
                case 5: goto L_0x0101;
                case 6: goto L_0x0113;
                case 7: goto L_0x0126;
                case 8: goto L_0x0139;
                case 9: goto L_0x014c;
                case 10: goto L_0x0165;
                case 11: goto L_0x017a;
                case 12: goto L_0x018d;
                case 13: goto L_0x01a0;
                case 14: goto L_0x01b3;
                case 15: goto L_0x01c6;
                case 16: goto L_0x01d9;
                case 17: goto L_0x01ec;
                case 18: goto L_0x0205;
                case 19: goto L_0x021b;
                case 20: goto L_0x0231;
                case 21: goto L_0x0247;
                case 22: goto L_0x025d;
                case 23: goto L_0x0273;
                case 24: goto L_0x0289;
                case 25: goto L_0x029f;
                case 26: goto L_0x02b5;
                case 27: goto L_0x02ca;
                case 28: goto L_0x02e5;
                case 29: goto L_0x02fa;
                case 30: goto L_0x0310;
                case 31: goto L_0x0326;
                case 32: goto L_0x033c;
                case 33: goto L_0x0352;
                case 34: goto L_0x0368;
                case 35: goto L_0x037e;
                case 36: goto L_0x0394;
                case 37: goto L_0x03aa;
                case 38: goto L_0x03c0;
                case 39: goto L_0x03d6;
                case 40: goto L_0x03ec;
                case 41: goto L_0x0402;
                case 42: goto L_0x0418;
                case 43: goto L_0x042e;
                case 44: goto L_0x0444;
                case 45: goto L_0x045a;
                case 46: goto L_0x0470;
                case 47: goto L_0x0486;
                case 48: goto L_0x049c;
                case 49: goto L_0x04b2;
                case 50: goto L_0x04cd;
                case 51: goto L_0x04de;
                case 52: goto L_0x04f9;
                case 53: goto L_0x0514;
                case 54: goto L_0x052f;
                case 55: goto L_0x054a;
                case 56: goto L_0x0565;
                case 57: goto L_0x0580;
                case 58: goto L_0x059b;
                case 59: goto L_0x05b6;
                case 60: goto L_0x05d1;
                case 61: goto L_0x05f2;
                case 62: goto L_0x060f;
                case 63: goto L_0x062a;
                case 64: goto L_0x0645;
                case 65: goto L_0x0660;
                case 66: goto L_0x067b;
                case 67: goto L_0x0696;
                case 68: goto L_0x06b1;
                default: goto L_0x00a2;
            }
        L_0x00a2:
            int r4 = r12 + 3
            r12 = r4
            r6 = r11
            goto L_0x0032
        L_0x00a7:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            double r6 = com.google.android.gms.internal.measurement.zzid.zze(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (double) r6)
            goto L_0x00a2
        L_0x00b9:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            float r4 = com.google.android.gms.internal.measurement.zzid.zzd(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (float) r4)
            goto L_0x00a2
        L_0x00cb:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (long) r6)
            goto L_0x00a2
        L_0x00dd:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzc((int) r1, (long) r6)
            goto L_0x00a2
        L_0x00ef:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzc((int) r1, (int) r4)
            goto L_0x00a2
        L_0x0101:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzd((int) r1, (long) r6)
            goto L_0x00a2
        L_0x0113:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzd((int) r1, (int) r4)
            goto L_0x00a2
        L_0x0126:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            boolean r4 = com.google.android.gms.internal.measurement.zzid.zzc(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (boolean) r4)
            goto L_0x00a2
        L_0x0139:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r16
            r1 = r22
            zza((int) r0, (java.lang.Object) r4, (com.google.android.gms.internal.measurement.zziq) r1)
            goto L_0x00a2
        L_0x014c:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.measurement.zzhf r6 = r0.zza((int) r12)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.measurement.zzhf) r6)
            goto L_0x00a2
        L_0x0165:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            com.google.android.gms.internal.measurement.zzdw r4 = (com.google.android.gms.internal.measurement.zzdw) r4
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (com.google.android.gms.internal.measurement.zzdw) r4)
            goto L_0x00a2
        L_0x017a:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zze((int) r1, (int) r4)
            goto L_0x00a2
        L_0x018d:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (int) r4)
            goto L_0x00a2
        L_0x01a0:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (int) r4)
            goto L_0x00a2
        L_0x01b3:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (long) r6)
            goto L_0x00a2
        L_0x01c6:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            int r4 = r14.getInt(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzf(r1, r4)
            goto L_0x00a2
        L_0x01d9:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            long r6 = r14.getLong(r0, r6)
            r0 = r22
            r1 = r16
            r0.zze((int) r1, (long) r6)
            goto L_0x00a2
        L_0x01ec:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.measurement.zzhf r6 = r0.zza((int) r12)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.measurement.zzhf) r6)
            goto L_0x00a2
        L_0x0205:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zza((int) r10, (java.util.List<java.lang.Double>) r4, (com.google.android.gms.internal.measurement.zziq) r0, (boolean) r6)
            goto L_0x00a2
        L_0x021b:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzb((int) r10, (java.util.List<java.lang.Float>) r4, (com.google.android.gms.internal.measurement.zziq) r0, (boolean) r6)
            goto L_0x00a2
        L_0x0231:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzc(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x0247:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzd(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x025d:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzh(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x0273:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzf(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x0289:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzk(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x029f:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzn(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x02b5:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zza((int) r10, (java.util.List<java.lang.String>) r4, (com.google.android.gms.internal.measurement.zziq) r0)
            goto L_0x00a2
        L_0x02ca:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r20
            com.google.android.gms.internal.measurement.zzhf r6 = r0.zza((int) r12)
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zza((int) r10, (java.util.List<?>) r4, (com.google.android.gms.internal.measurement.zziq) r0, (com.google.android.gms.internal.measurement.zzhf) r6)
            goto L_0x00a2
        L_0x02e5:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzb((int) r10, (java.util.List<com.google.android.gms.internal.measurement.zzdw>) r4, (com.google.android.gms.internal.measurement.zziq) r0)
            goto L_0x00a2
        L_0x02fa:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzi(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x0310:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzm(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x0326:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzl(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x033c:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzg(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x0352:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzj(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x0368:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 0
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zze(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x037e:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zza((int) r10, (java.util.List<java.lang.Double>) r4, (com.google.android.gms.internal.measurement.zziq) r0, (boolean) r6)
            goto L_0x00a2
        L_0x0394:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzb((int) r10, (java.util.List<java.lang.Float>) r4, (com.google.android.gms.internal.measurement.zziq) r0, (boolean) r6)
            goto L_0x00a2
        L_0x03aa:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzc(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x03c0:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzd(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x03d6:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzh(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x03ec:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzf(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x0402:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzk(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x0418:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzn(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x042e:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzi(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x0444:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzm(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x045a:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzl(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x0470:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzg(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x0486:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzj(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x049c:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r6 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zze(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x04b2:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r20
            com.google.android.gms.internal.measurement.zzhf r6 = r0.zza((int) r12)
            r0 = r22
            com.google.android.gms.internal.measurement.zzhh.zzb((int) r10, (java.util.List<?>) r4, (com.google.android.gms.internal.measurement.zziq) r0, (com.google.android.gms.internal.measurement.zzhf) r6)
            goto L_0x00a2
        L_0x04cd:
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            r1 = r22
            r2 = r16
            r0.zza((com.google.android.gms.internal.measurement.zziq) r1, (int) r2, (java.lang.Object) r4, (int) r12)
            goto L_0x00a2
        L_0x04de:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            double r6 = zzb(r0, (long) r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (double) r6)
            goto L_0x00a2
        L_0x04f9:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            float r4 = zzc(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (float) r4)
            goto L_0x00a2
        L_0x0514:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            long r6 = zze(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (long) r6)
            goto L_0x00a2
        L_0x052f:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            long r6 = zze(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzc((int) r1, (long) r6)
            goto L_0x00a2
        L_0x054a:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            int r4 = zzd(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzc((int) r1, (int) r4)
            goto L_0x00a2
        L_0x0565:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            long r6 = zze(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzd((int) r1, (long) r6)
            goto L_0x00a2
        L_0x0580:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            int r4 = zzd(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzd((int) r1, (int) r4)
            goto L_0x00a2
        L_0x059b:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            boolean r4 = zzf(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (boolean) r4)
            goto L_0x00a2
        L_0x05b6:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r16
            r1 = r22
            zza((int) r0, (java.lang.Object) r4, (com.google.android.gms.internal.measurement.zziq) r1)
            goto L_0x00a2
        L_0x05d1:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.measurement.zzhf r6 = r0.zza((int) r12)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.measurement.zzhf) r6)
            goto L_0x00a2
        L_0x05f2:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            com.google.android.gms.internal.measurement.zzdw r4 = (com.google.android.gms.internal.measurement.zzdw) r4
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (com.google.android.gms.internal.measurement.zzdw) r4)
            goto L_0x00a2
        L_0x060f:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            int r4 = zzd(r0, r6)
            r0 = r22
            r1 = r16
            r0.zze((int) r1, (int) r4)
            goto L_0x00a2
        L_0x062a:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            int r4 = zzd(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (int) r4)
            goto L_0x00a2
        L_0x0645:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            int r4 = zzd(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (int) r4)
            goto L_0x00a2
        L_0x0660:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            long r6 = zze(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (long) r6)
            goto L_0x00a2
        L_0x067b:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            int r4 = zzd(r0, r6)
            r0 = r22
            r1 = r16
            r0.zzf(r1, r4)
            goto L_0x00a2
        L_0x0696:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            long r6 = zze(r0, r6)
            r0 = r22
            r1 = r16
            r0.zze((int) r1, (long) r6)
            goto L_0x00a2
        L_0x06b1:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.measurement.zzhf r6 = r0.zza((int) r12)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.measurement.zzhf) r6)
            goto L_0x00a2
        L_0x06d2:
            r4 = 0
        L_0x06d3:
            if (r4 == 0) goto L_0x06eb
            r0 = r20
            com.google.android.gms.internal.measurement.zzet<?> r6 = r0.zzr
            r0 = r22
            r6.zza(r0, r4)
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L_0x06d2
            java.lang.Object r4 = r5.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            goto L_0x06d3
        L_0x06eb:
            r0 = r20
            com.google.android.gms.internal.measurement.zzhx<?, ?> r4 = r0.zzq
            r0 = r21
            r1 = r22
            zza(r4, r0, (com.google.android.gms.internal.measurement.zziq) r1)
            return
        L_0x06f7:
            r4 = r6
            r7 = r8
            goto L_0x006c
        L_0x06fb:
            r10 = r4
            r11 = r6
            goto L_0x0073
        L_0x06ff:
            r4 = r9
            goto L_0x06d3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgq.zzb(java.lang.Object, com.google.android.gms.internal.measurement.zziq):void");
    }

    private final <K, V> void zza(zziq zziq, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zziq.zza(i, this.zzs.zzb(zzb(i2)), this.zzs.zzc(obj));
        }
    }

    private static <UT, UB> void zza(zzhx<UT, UB> zzhx, T t, zziq zziq) throws IOException {
        zzhx.zza(zzhx.zzb(t), zziq);
    }

    public final void zza(T t, zzhc zzhc, zzer zzer) throws IOException {
        zzhx<?, ?> zzhx;
        Throwable th;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object zza2;
        if (zzer == null) {
            throw new NullPointerException();
        }
        zzhx = this.zzq;
        zzet<?> zzet = this.zzr;
        Object obj5 = null;
        zzeu<?> zzeu = null;
        while (true) {
            int zza3 = zzhc.zza();
            int zzg2 = zzg(zza3);
            if (zzg2 >= 0) {
                int zzd2 = zzd(zzg2);
                switch ((267386880 & zzd2) >>> 20) {
                    case 0:
                        zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zzd());
                        zzb(t, zzg2);
                        continue;
                    case 1:
                        zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zze());
                        zzb(t, zzg2);
                        continue;
                    case 2:
                        zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zzg());
                        zzb(t, zzg2);
                        continue;
                    case 3:
                        zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zzf());
                        zzb(t, zzg2);
                        continue;
                    case 4:
                        zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zzh());
                        zzb(t, zzg2);
                        continue;
                    case 5:
                        zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zzi());
                        zzb(t, zzg2);
                        continue;
                    case 6:
                        zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zzj());
                        zzb(t, zzg2);
                        continue;
                    case 7:
                        zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zzk());
                        zzb(t, zzg2);
                        continue;
                    case 8:
                        zza((Object) t, zzd2, zzhc);
                        zzb(t, zzg2);
                        continue;
                    case 9:
                        if (!zza(t, zzg2)) {
                            zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zza(zza(zzg2), zzer));
                            zzb(t, zzg2);
                            break;
                        } else {
                            zzid.zza((Object) t, (long) (1048575 & zzd2), zzfh.zza(zzid.zzf(t, (long) (1048575 & zzd2)), zzhc.zza(zza(zzg2), zzer)));
                            continue;
                        }
                    case 10:
                        zzid.zza((Object) t, (long) (1048575 & zzd2), (Object) zzhc.zzn());
                        zzb(t, zzg2);
                        continue;
                    case 11:
                        zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zzo());
                        zzb(t, zzg2);
                        continue;
                    case 12:
                        int zzp2 = zzhc.zzp();
                        zzfi zzc2 = zzc(zzg2);
                        if (zzc2 != null && !zzc2.zza(zzp2)) {
                            obj5 = zzhh.zza(zza3, zzp2, obj5, zzhx);
                            break;
                        } else {
                            zzid.zza((Object) t, (long) (1048575 & zzd2), zzp2);
                            zzb(t, zzg2);
                            continue;
                        }
                        break;
                    case 13:
                        zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zzq());
                        zzb(t, zzg2);
                        continue;
                    case 14:
                        zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zzr());
                        zzb(t, zzg2);
                        continue;
                    case 15:
                        zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zzs());
                        zzb(t, zzg2);
                        continue;
                    case 16:
                        zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zzt());
                        zzb(t, zzg2);
                        continue;
                    case 17:
                        if (!zza(t, zzg2)) {
                            zzid.zza((Object) t, (long) (1048575 & zzd2), zzhc.zzb(zza(zzg2), zzer));
                            zzb(t, zzg2);
                            break;
                        } else {
                            zzid.zza((Object) t, (long) (1048575 & zzd2), zzfh.zza(zzid.zzf(t, (long) (1048575 & zzd2)), zzhc.zzb(zza(zzg2), zzer)));
                            continue;
                        }
                    case 18:
                        zzhc.zza(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 19:
                        zzhc.zzb(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 20:
                        zzhc.zzd(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 21:
                        zzhc.zzc(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 22:
                        zzhc.zze(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 23:
                        zzhc.zzf(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 24:
                        zzhc.zzg(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 25:
                        zzhc.zzh(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 26:
                        if (!zzf(zzd2)) {
                            zzhc.zzi(this.zzp.zza(t, (long) (1048575 & zzd2)));
                            break;
                        } else {
                            zzhc.zzj(this.zzp.zza(t, (long) (1048575 & zzd2)));
                            continue;
                        }
                    case 27:
                        zzhc.zza(this.zzp.zza(t, (long) (1048575 & zzd2)), zza(zzg2), zzer);
                        continue;
                    case 28:
                        zzhc.zzk(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 29:
                        zzhc.zzl(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 30:
                        List zza4 = this.zzp.zza(t, (long) (zzd2 & 1048575));
                        zzhc.zzm(zza4);
                        obj5 = zzhh.zza(zza3, zza4, zzc(zzg2), obj5, zzhx);
                        continue;
                    case 31:
                        zzhc.zzn(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 32:
                        zzhc.zzo(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 33:
                        zzhc.zzp(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 34:
                        zzhc.zzq(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 35:
                        zzhc.zza(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 36:
                        zzhc.zzb(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 37:
                        zzhc.zzd(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 38:
                        zzhc.zzc(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 39:
                        zzhc.zze(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 40:
                        zzhc.zzf(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 41:
                        zzhc.zzg(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 42:
                        zzhc.zzh(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 43:
                        zzhc.zzl(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 44:
                        List zza5 = this.zzp.zza(t, (long) (zzd2 & 1048575));
                        zzhc.zzm(zza5);
                        obj5 = zzhh.zza(zza3, zza5, zzc(zzg2), obj5, zzhx);
                        continue;
                    case 45:
                        zzhc.zzn(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 46:
                        zzhc.zzo(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 47:
                        zzhc.zzp(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 48:
                        zzhc.zzq(this.zzp.zza(t, (long) (1048575 & zzd2)));
                        continue;
                    case 49:
                        zzhf zza6 = zza(zzg2);
                        zzhc.zzb(this.zzp.zza(t, (long) (1048575 & zzd2)), zza6, zzer);
                        continue;
                    case 50:
                        Object zzb2 = zzb(zzg2);
                        long zzd3 = (long) (zzd(zzg2) & 1048575);
                        Object zzf2 = zzid.zzf(t, zzd3);
                        if (zzf2 == null) {
                            obj2 = this.zzs.zzf(zzb2);
                            zzid.zza((Object) t, zzd3, obj2);
                        } else if (this.zzs.zzd(zzf2)) {
                            obj2 = this.zzs.zzf(zzb2);
                            this.zzs.zza(obj2, zzf2);
                            zzid.zza((Object) t, zzd3, obj2);
                        } else {
                            obj2 = zzf2;
                        }
                        zzhc.zza(this.zzs.zza(obj2), this.zzs.zzb(zzb2), zzer);
                        continue;
                    case 51:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) Double.valueOf(zzhc.zzd()));
                        zzb(t, zza3, zzg2);
                        continue;
                    case 52:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) Float.valueOf(zzhc.zze()));
                        zzb(t, zza3, zzg2);
                        continue;
                    case 53:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzhc.zzg()));
                        zzb(t, zza3, zzg2);
                        continue;
                    case 54:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzhc.zzf()));
                        zzb(t, zza3, zzg2);
                        continue;
                    case 55:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzhc.zzh()));
                        zzb(t, zza3, zzg2);
                        continue;
                    case 56:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzhc.zzi()));
                        zzb(t, zza3, zzg2);
                        continue;
                    case 57:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzhc.zzj()));
                        zzb(t, zza3, zzg2);
                        continue;
                    case 58:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) Boolean.valueOf(zzhc.zzk()));
                        zzb(t, zza3, zzg2);
                        continue;
                    case 59:
                        zza((Object) t, zzd2, zzhc);
                        zzb(t, zza3, zzg2);
                        continue;
                    case 60:
                        if (zza(t, zza3, zzg2)) {
                            zzid.zza((Object) t, (long) (zzd2 & 1048575), zzfh.zza(zzid.zzf(t, (long) (1048575 & zzd2)), zzhc.zza(zza(zzg2), zzer)));
                        } else {
                            zzid.zza((Object) t, (long) (zzd2 & 1048575), zzhc.zza(zza(zzg2), zzer));
                            zzb(t, zzg2);
                        }
                        zzb(t, zza3, zzg2);
                        continue;
                    case 61:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) zzhc.zzn());
                        zzb(t, zza3, zzg2);
                        continue;
                    case 62:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzhc.zzo()));
                        zzb(t, zza3, zzg2);
                        continue;
                    case 63:
                        int zzp3 = zzhc.zzp();
                        zzfi zzc3 = zzc(zzg2);
                        if (zzc3 != null && !zzc3.zza(zzp3)) {
                            obj5 = zzhh.zza(zza3, zzp3, obj5, zzhx);
                            break;
                        } else {
                            zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzp3));
                            zzb(t, zza3, zzg2);
                            continue;
                        }
                        break;
                    case 64:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzhc.zzq()));
                        zzb(t, zza3, zzg2);
                        continue;
                    case 65:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzhc.zzr()));
                        zzb(t, zza3, zzg2);
                        continue;
                    case 66:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzhc.zzs()));
                        zzb(t, zza3, zzg2);
                        continue;
                    case 67:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzhc.zzt()));
                        zzb(t, zza3, zzg2);
                        continue;
                    case 68:
                        zzid.zza((Object) t, (long) (zzd2 & 1048575), zzhc.zzb(zza(zzg2), zzer));
                        zzb(t, zza3, zzg2);
                        continue;
                    default:
                        if (obj5 == null) {
                            try {
                                obj4 = zzhx.zza();
                            } catch (zzfp e) {
                                break;
                            }
                        } else {
                            obj4 = obj5;
                        }
                        try {
                            if (!zzhx.zza(obj4, zzhc)) {
                                for (int i = this.zzm; i < this.zzn; i++) {
                                    obj4 = zza((Object) t, this.zzl[i], obj4, zzhx);
                                }
                                if (obj4 != null) {
                                    zzhx.zzb((Object) t, obj4);
                                    return;
                                }
                                return;
                            }
                            obj5 = obj4;
                            continue;
                        } catch (zzfp e2) {
                            obj5 = obj4;
                            break;
                        }
                }
                try {
                    zzhx.zza(zzhc);
                    if (obj5 == null) {
                        obj3 = zzhx.zzc(t);
                    } else {
                        obj3 = obj5;
                    }
                    if (!zzhx.zza(obj, zzhc)) {
                        for (int i2 = this.zzm; i2 < this.zzn; i2++) {
                            obj = zza((Object) t, this.zzl[i2], obj, zzhx);
                        }
                        if (obj != null) {
                            zzhx.zzb((Object) t, obj);
                            return;
                        }
                        return;
                    }
                    obj5 = obj;
                } catch (Throwable th2) {
                    th = th2;
                    obj = obj5;
                }
            } else if (zza3 == Integer.MAX_VALUE) {
                for (int i3 = this.zzm; i3 < this.zzn; i3++) {
                    obj5 = zza((Object) t, this.zzl[i3], obj5, zzhx);
                }
                if (obj5 != null) {
                    zzhx.zzb((Object) t, obj5);
                    return;
                }
                return;
            } else {
                if (!this.zzh) {
                    zza2 = null;
                } else {
                    zza2 = zzet.zza(zzer, this.zzg, zza3);
                }
                if (zza2 != null) {
                    if (zzeu == null) {
                        zzeu = zzet.zzb(t);
                    }
                    obj5 = zzet.zza(zzhc, zza2, zzer, zzeu, obj5, zzhx);
                } else {
                    zzhx.zza(zzhc);
                    if (obj5 == null) {
                        obj = zzhx.zzc(t);
                    } else {
                        obj = obj5;
                    }
                    try {
                        if (!zzhx.zza(obj, zzhc)) {
                            for (int i4 = this.zzm; i4 < this.zzn; i4++) {
                                obj = zza((Object) t, this.zzl[i4], obj, zzhx);
                            }
                            if (obj != null) {
                                zzhx.zzb((Object) t, obj);
                                return;
                            }
                            return;
                        }
                        obj5 = obj;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
            }
        }
        for (int i5 = this.zzm; i5 < this.zzn; i5++) {
            obj = zza((Object) t, this.zzl[i5], obj, zzhx);
        }
        if (obj != null) {
            zzhx.zzb((Object) t, obj);
        }
        throw th;
    }

    private static zzhw zze(Object obj) {
        zzhw zzhw = ((zzfe) obj).zzb;
        if (zzhw != zzhw.zza()) {
            return zzhw;
        }
        zzhw zzb2 = zzhw.zzb();
        ((zzfe) obj).zzb = zzb2;
        return zzb2;
    }

    private static int zza(byte[] bArr, int i, int i2, zzik zzik, Class<?> cls, zzdr zzdr) throws IOException {
        boolean z;
        switch (zzgt.zza[zzik.ordinal()]) {
            case 1:
                int zzb2 = zzds.zzb(bArr, i, zzdr);
                if (zzdr.zzb != 0) {
                    z = true;
                } else {
                    z = false;
                }
                zzdr.zzc = Boolean.valueOf(z);
                return zzb2;
            case 2:
                return zzds.zze(bArr, i, zzdr);
            case 3:
                zzdr.zzc = Double.valueOf(zzds.zzc(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzdr.zzc = Integer.valueOf(zzds.zza(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzdr.zzc = Long.valueOf(zzds.zzb(bArr, i));
                return i + 8;
            case 8:
                zzdr.zzc = Float.valueOf(zzds.zzd(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int zza2 = zzds.zza(bArr, i, zzdr);
                zzdr.zzc = Integer.valueOf(zzdr.zza);
                return zza2;
            case 12:
            case 13:
                int zzb3 = zzds.zzb(bArr, i, zzdr);
                zzdr.zzc = Long.valueOf(zzdr.zzb);
                return zzb3;
            case 14:
                return zzds.zza((zzhf) zzhb.zza().zza(cls), bArr, i, i2, zzdr);
            case 15:
                int zza3 = zzds.zza(bArr, i, zzdr);
                zzdr.zzc = Integer.valueOf(zzei.zze(zzdr.zza));
                return zza3;
            case 16:
                int zzb4 = zzds.zzb(bArr, i, zzdr);
                zzdr.zzc = Long.valueOf(zzei.zza(zzdr.zzb));
                return zzb4;
            case 17:
                return zzds.zzd(bArr, i, zzdr);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzdr zzdr) throws IOException {
        zzfn zzfn;
        int zza2;
        int i8;
        int i9;
        int i10;
        boolean z;
        zzfn zzfn2 = (zzfn) zzb.getObject(t, j2);
        if (!zzfn2.zza()) {
            int size = zzfn2.size();
            zzfn = zzfn2.zza(size == 0 ? 10 : size << 1);
            zzb.putObject(t, j2, zzfn);
        } else {
            zzfn = zzfn2;
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzeq zzeq = (zzeq) zzfn;
                    int zza3 = zzds.zza(bArr, i, zzdr);
                    int i11 = zzdr.zza + zza3;
                    while (zza3 < i11) {
                        zzeq.zza(zzds.zzc(bArr, zza3));
                        zza3 += 8;
                    }
                    if (zza3 == i11) {
                        return zza3;
                    }
                    throw zzfm.zza();
                } else if (i5 != 1) {
                    return i;
                } else {
                    zzeq zzeq2 = (zzeq) zzfn;
                    zzeq2.zza(zzds.zzc(bArr, i));
                    int i12 = i + 8;
                    while (i12 < i2) {
                        int zza4 = zzds.zza(bArr, i12, zzdr);
                        if (i3 != zzdr.zza) {
                            return i12;
                        }
                        zzeq2.zza(zzds.zzc(bArr, zza4));
                        i12 = zza4 + 8;
                    }
                    return i12;
                }
            case 19:
            case 36:
                if (i5 == 2) {
                    zzfa zzfa = (zzfa) zzfn;
                    int zza5 = zzds.zza(bArr, i, zzdr);
                    int i13 = zzdr.zza + zza5;
                    while (zza5 < i13) {
                        zzfa.zza(zzds.zzd(bArr, zza5));
                        zza5 += 4;
                    }
                    if (zza5 == i13) {
                        return zza5;
                    }
                    throw zzfm.zza();
                } else if (i5 != 5) {
                    return i;
                } else {
                    zzfa zzfa2 = (zzfa) zzfn;
                    zzfa2.zza(zzds.zzd(bArr, i));
                    int i14 = i + 4;
                    while (i14 < i2) {
                        int zza6 = zzds.zza(bArr, i14, zzdr);
                        if (i3 != zzdr.zza) {
                            return i14;
                        }
                        zzfa2.zza(zzds.zzd(bArr, zza6));
                        i14 = zza6 + 4;
                    }
                    return i14;
                }
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzga zzga = (zzga) zzfn;
                    int zza7 = zzds.zza(bArr, i, zzdr);
                    int i15 = zzdr.zza + zza7;
                    while (zza7 < i15) {
                        zza7 = zzds.zzb(bArr, zza7, zzdr);
                        zzga.zza(zzdr.zzb);
                    }
                    if (zza7 == i15) {
                        return zza7;
                    }
                    throw zzfm.zza();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzga zzga2 = (zzga) zzfn;
                    int zzb2 = zzds.zzb(bArr, i, zzdr);
                    zzga2.zza(zzdr.zzb);
                    while (zzb2 < i2) {
                        int zza8 = zzds.zza(bArr, zzb2, zzdr);
                        if (i3 != zzdr.zza) {
                            return zzb2;
                        }
                        zzb2 = zzds.zzb(bArr, zza8, zzdr);
                        zzga2.zza(zzdr.zzb);
                    }
                    return zzb2;
                }
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzds.zza(bArr, i, (zzfn<?>) zzfn, zzdr);
                }
                if (i5 == 0) {
                    return zzds.zza(i3, bArr, i, i2, (zzfn<?>) zzfn, zzdr);
                }
                return i;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzga zzga3 = (zzga) zzfn;
                    int zza9 = zzds.zza(bArr, i, zzdr);
                    int i16 = zzdr.zza + zza9;
                    while (zza9 < i16) {
                        zzga3.zza(zzds.zzb(bArr, zza9));
                        zza9 += 8;
                    }
                    if (zza9 == i16) {
                        return zza9;
                    }
                    throw zzfm.zza();
                } else if (i5 != 1) {
                    return i;
                } else {
                    zzga zzga4 = (zzga) zzfn;
                    zzga4.zza(zzds.zzb(bArr, i));
                    int i17 = i + 8;
                    while (i17 < i2) {
                        int zza10 = zzds.zza(bArr, i17, zzdr);
                        if (i3 != zzdr.zza) {
                            return i17;
                        }
                        zzga4.zza(zzds.zzb(bArr, zza10));
                        i17 = zza10 + 8;
                    }
                    return i17;
                }
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzff zzff = (zzff) zzfn;
                    int zza11 = zzds.zza(bArr, i, zzdr);
                    int i18 = zzdr.zza + zza11;
                    while (zza11 < i18) {
                        zzff.zzd(zzds.zza(bArr, zza11));
                        zza11 += 4;
                    }
                    if (zza11 == i18) {
                        return zza11;
                    }
                    throw zzfm.zza();
                } else if (i5 != 5) {
                    return i;
                } else {
                    zzff zzff2 = (zzff) zzfn;
                    zzff2.zzd(zzds.zza(bArr, i));
                    int i19 = i + 4;
                    while (i19 < i2) {
                        int zza12 = zzds.zza(bArr, i19, zzdr);
                        if (i3 != zzdr.zza) {
                            return i19;
                        }
                        zzff2.zzd(zzds.zza(bArr, zza12));
                        i19 = zza12 + 4;
                    }
                    return i19;
                }
            case 25:
            case 42:
                if (i5 == 2) {
                    zzdu zzdu = (zzdu) zzfn;
                    int zza13 = zzds.zza(bArr, i, zzdr);
                    int i20 = zza13 + zzdr.zza;
                    while (zza13 < i20) {
                        zza13 = zzds.zzb(bArr, zza13, zzdr);
                        zzdu.zza(zzdr.zzb != 0);
                    }
                    if (zza13 == i20) {
                        return zza13;
                    }
                    throw zzfm.zza();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzdu zzdu2 = (zzdu) zzfn;
                    int zzb3 = zzds.zzb(bArr, i, zzdr);
                    zzdu2.zza(zzdr.zzb != 0);
                    while (zzb3 < i2) {
                        int zza14 = zzds.zza(bArr, zzb3, zzdr);
                        if (i3 != zzdr.zza) {
                            return zzb3;
                        }
                        zzb3 = zzds.zzb(bArr, zza14, zzdr);
                        if (zzdr.zzb != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        zzdu2.zza(z);
                    }
                    return zzb3;
                }
            case 26:
                if (i5 != 2) {
                    return i;
                }
                if ((536870912 & j) == 0) {
                    int zza15 = zzds.zza(bArr, i, zzdr);
                    int i21 = zzdr.zza;
                    if (i21 < 0) {
                        throw zzfm.zzb();
                    }
                    if (i21 == 0) {
                        zzfn.add("");
                    } else {
                        zzfn.add(new String(bArr, zza15, i21, zzfh.zza));
                        zza15 += i21;
                    }
                    while (i10 < i2) {
                        int zza16 = zzds.zza(bArr, i10, zzdr);
                        if (i3 != zzdr.zza) {
                            return i10;
                        }
                        i10 = zzds.zza(bArr, zza16, zzdr);
                        int i22 = zzdr.zza;
                        if (i22 < 0) {
                            throw zzfm.zzb();
                        } else if (i22 == 0) {
                            zzfn.add("");
                        } else {
                            zzfn.add(new String(bArr, i10, i22, zzfh.zza));
                            i10 += i22;
                        }
                    }
                    return i10;
                }
                int zza17 = zzds.zza(bArr, i, zzdr);
                int i23 = zzdr.zza;
                if (i23 < 0) {
                    throw zzfm.zzb();
                }
                if (i23 == 0) {
                    zzfn.add("");
                } else {
                    if (!zzif.zza(bArr, zza17, zza17 + i23)) {
                        throw zzfm.zzh();
                    }
                    zzfn.add(new String(bArr, zza17, i23, zzfh.zza));
                    zza17 += i23;
                }
                while (i9 < i2) {
                    int zza18 = zzds.zza(bArr, i9, zzdr);
                    if (i3 != zzdr.zza) {
                        return i9;
                    }
                    i9 = zzds.zza(bArr, zza18, zzdr);
                    int i24 = zzdr.zza;
                    if (i24 < 0) {
                        throw zzfm.zzb();
                    } else if (i24 == 0) {
                        zzfn.add("");
                    } else {
                        if (!zzif.zza(bArr, i9, i9 + i24)) {
                            throw zzfm.zzh();
                        }
                        zzfn.add(new String(bArr, i9, i24, zzfh.zza));
                        i9 += i24;
                    }
                }
                return i9;
            case 27:
                if (i5 == 2) {
                    return zzds.zza(zza(i6), i3, bArr, i, i2, zzfn, zzdr);
                }
                return i;
            case 28:
                if (i5 != 2) {
                    return i;
                }
                int zza19 = zzds.zza(bArr, i, zzdr);
                int i25 = zzdr.zza;
                if (i25 < 0) {
                    throw zzfm.zzb();
                } else if (i25 > bArr.length - zza19) {
                    throw zzfm.zza();
                } else {
                    if (i25 == 0) {
                        zzfn.add(zzdw.zza);
                    } else {
                        zzfn.add(zzdw.zza(bArr, zza19, i25));
                        zza19 += i25;
                    }
                    while (i8 < i2) {
                        int zza20 = zzds.zza(bArr, i8, zzdr);
                        if (i3 != zzdr.zza) {
                            return i8;
                        }
                        i8 = zzds.zza(bArr, zza20, zzdr);
                        int i26 = zzdr.zza;
                        if (i26 < 0) {
                            throw zzfm.zzb();
                        } else if (i26 > bArr.length - i8) {
                            throw zzfm.zza();
                        } else if (i26 == 0) {
                            zzfn.add(zzdw.zza);
                        } else {
                            zzfn.add(zzdw.zza(bArr, i8, i26));
                            i8 += i26;
                        }
                    }
                    return i8;
                }
            case 30:
            case 44:
                if (i5 == 2) {
                    zza2 = zzds.zza(bArr, i, (zzfn<?>) zzfn, zzdr);
                } else if (i5 != 0) {
                    return i;
                } else {
                    zza2 = zzds.zza(i3, bArr, i, i2, (zzfn<?>) zzfn, zzdr);
                }
                zzhw zzhw = ((zzfe) t).zzb;
                if (zzhw == zzhw.zza()) {
                    zzhw = null;
                }
                zzhw zzhw2 = (zzhw) zzhh.zza(i4, zzfn, zzc(i6), zzhw, this.zzq);
                if (zzhw2 == null) {
                    return zza2;
                }
                ((zzfe) t).zzb = zzhw2;
                return zza2;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzff zzff3 = (zzff) zzfn;
                    int zza21 = zzds.zza(bArr, i, zzdr);
                    int i27 = zzdr.zza + zza21;
                    while (zza21 < i27) {
                        zza21 = zzds.zza(bArr, zza21, zzdr);
                        zzff3.zzd(zzei.zze(zzdr.zza));
                    }
                    if (zza21 == i27) {
                        return zza21;
                    }
                    throw zzfm.zza();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzff zzff4 = (zzff) zzfn;
                    int zza22 = zzds.zza(bArr, i, zzdr);
                    zzff4.zzd(zzei.zze(zzdr.zza));
                    while (zza22 < i2) {
                        int zza23 = zzds.zza(bArr, zza22, zzdr);
                        if (i3 != zzdr.zza) {
                            return zza22;
                        }
                        zza22 = zzds.zza(bArr, zza23, zzdr);
                        zzff4.zzd(zzei.zze(zzdr.zza));
                    }
                    return zza22;
                }
            case 34:
            case 48:
                if (i5 == 2) {
                    zzga zzga5 = (zzga) zzfn;
                    int zza24 = zzds.zza(bArr, i, zzdr);
                    int i28 = zzdr.zza + zza24;
                    while (zza24 < i28) {
                        zza24 = zzds.zzb(bArr, zza24, zzdr);
                        zzga5.zza(zzei.zza(zzdr.zzb));
                    }
                    if (zza24 == i28) {
                        return zza24;
                    }
                    throw zzfm.zza();
                } else if (i5 != 0) {
                    return i;
                } else {
                    zzga zzga6 = (zzga) zzfn;
                    int zzb4 = zzds.zzb(bArr, i, zzdr);
                    zzga6.zza(zzei.zza(zzdr.zzb));
                    while (zzb4 < i2) {
                        int zza25 = zzds.zza(bArr, zzb4, zzdr);
                        if (i3 != zzdr.zza) {
                            return zzb4;
                        }
                        zzb4 = zzds.zzb(bArr, zza25, zzdr);
                        zzga6.zza(zzei.zza(zzdr.zzb));
                    }
                    return zzb4;
                }
            case 49:
                if (i5 != 3) {
                    return i;
                }
                zzhf zza26 = zza(i6);
                int i29 = (i3 & -8) | 4;
                int zza27 = zzds.zza(zza26, bArr, i, i2, i29, zzdr);
                zzfn.add(zzdr.zzc);
                while (zza27 < i2) {
                    int zza28 = zzds.zza(bArr, zza27, zzdr);
                    if (i3 != zzdr.zza) {
                        return zza27;
                    }
                    zza27 = zzds.zza(zza26, bArr, zza28, i2, i29, zzdr);
                    zzfn.add(zzdr.zzc);
                }
                return zza27;
            default:
                return i;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: byte} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <K, V> int zza(T r17, byte[] r18, int r19, int r20, int r21, long r22, com.google.android.gms.internal.measurement.zzdr r24) throws java.io.IOException {
        /*
            r16 = this;
            sun.misc.Unsafe r5 = zzb
            r0 = r16
            r1 = r21
            java.lang.Object r6 = r0.zzb((int) r1)
            r0 = r17
            r1 = r22
            java.lang.Object r4 = r5.getObject(r0, r1)
            r0 = r16
            com.google.android.gms.internal.measurement.zzgj r3 = r0.zzs
            boolean r3 = r3.zzd(r4)
            if (r3 == 0) goto L_0x00d2
            r0 = r16
            com.google.android.gms.internal.measurement.zzgj r3 = r0.zzs
            java.lang.Object r3 = r3.zzf(r6)
            r0 = r16
            com.google.android.gms.internal.measurement.zzgj r7 = r0.zzs
            r7.zza(r3, r4)
            r0 = r17
            r1 = r22
            r5.putObject(r0, r1, r3)
        L_0x0032:
            r0 = r16
            com.google.android.gms.internal.measurement.zzgj r4 = r0.zzs
            com.google.android.gms.internal.measurement.zzgh r11 = r4.zzb(r6)
            r0 = r16
            com.google.android.gms.internal.measurement.zzgj r4 = r0.zzs
            java.util.Map r12 = r4.zza(r3)
            r0 = r18
            r1 = r19
            r2 = r24
            int r5 = com.google.android.gms.internal.measurement.zzds.zza(r0, r1, r2)
            r0 = r24
            int r3 = r0.zza
            if (r3 < 0) goto L_0x0056
            int r4 = r20 - r5
            if (r3 <= r4) goto L_0x005b
        L_0x0056:
            com.google.android.gms.internal.measurement.zzfm r3 = com.google.android.gms.internal.measurement.zzfm.zza()
            throw r3
        L_0x005b:
            int r13 = r5 + r3
            K r4 = r11.zzb
            V r3 = r11.zzd
            r9 = r3
            r10 = r4
        L_0x0063:
            if (r5 >= r13) goto L_0x00c7
            int r4 = r5 + 1
            byte r3 = r18[r5]
            if (r3 >= 0) goto L_0x0077
            r0 = r18
            r1 = r24
            int r4 = com.google.android.gms.internal.measurement.zzds.zza((int) r3, (byte[]) r0, (int) r4, (com.google.android.gms.internal.measurement.zzdr) r1)
            r0 = r24
            int r3 = r0.zza
        L_0x0077:
            int r5 = r3 >>> 3
            r6 = r3 & 7
            switch(r5) {
                case 1: goto L_0x008a;
                case 2: goto L_0x00a6;
                default: goto L_0x007e;
            }
        L_0x007e:
            r0 = r18
            r1 = r20
            r2 = r24
            int r3 = com.google.android.gms.internal.measurement.zzds.zza((int) r3, (byte[]) r0, (int) r4, (int) r1, (com.google.android.gms.internal.measurement.zzdr) r2)
            r5 = r3
            goto L_0x0063
        L_0x008a:
            com.google.android.gms.internal.measurement.zzik r5 = r11.zza
            int r5 = r5.zzb()
            if (r6 != r5) goto L_0x007e
            com.google.android.gms.internal.measurement.zzik r6 = r11.zza
            r7 = 0
            r3 = r18
            r5 = r20
            r8 = r24
            int r4 = zza((byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.measurement.zzik) r6, (java.lang.Class<?>) r7, (com.google.android.gms.internal.measurement.zzdr) r8)
            r0 = r24
            java.lang.Object r3 = r0.zzc
            r10 = r3
            r5 = r4
            goto L_0x0063
        L_0x00a6:
            com.google.android.gms.internal.measurement.zzik r5 = r11.zzc
            int r5 = r5.zzb()
            if (r6 != r5) goto L_0x007e
            com.google.android.gms.internal.measurement.zzik r6 = r11.zzc
            V r3 = r11.zzd
            java.lang.Class r7 = r3.getClass()
            r3 = r18
            r5 = r20
            r8 = r24
            int r4 = zza((byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.measurement.zzik) r6, (java.lang.Class<?>) r7, (com.google.android.gms.internal.measurement.zzdr) r8)
            r0 = r24
            java.lang.Object r3 = r0.zzc
            r9 = r3
            r5 = r4
            goto L_0x0063
        L_0x00c7:
            if (r5 == r13) goto L_0x00ce
            com.google.android.gms.internal.measurement.zzfm r3 = com.google.android.gms.internal.measurement.zzfm.zzg()
            throw r3
        L_0x00ce:
            r12.put(r10, r9)
            return r13
        L_0x00d2:
            r3 = r4
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgq.zza(java.lang.Object, byte[], int, int, int, long, com.google.android.gms.internal.measurement.zzdr):int");
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzdr zzdr) throws IOException {
        int zza2;
        Object obj;
        Object obj2;
        Unsafe unsafe = zzb;
        long j2 = (long) (this.zzc[i8 + 2] & 1048575);
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(zzds.zzc(bArr, i)));
                    zza2 = i + 8;
                    break;
                } else {
                    return i;
                }
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(zzds.zzd(bArr, i)));
                    zza2 = i + 4;
                    break;
                } else {
                    return i;
                }
            case 53:
            case 54:
                if (i5 == 0) {
                    zza2 = zzds.zzb(bArr, i, zzdr);
                    unsafe.putObject(t, j, Long.valueOf(zzdr.zzb));
                    break;
                } else {
                    return i;
                }
            case 55:
            case 62:
                if (i5 == 0) {
                    zza2 = zzds.zza(bArr, i, zzdr);
                    unsafe.putObject(t, j, Integer.valueOf(zzdr.zza));
                    break;
                } else {
                    return i;
                }
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(zzds.zzb(bArr, i)));
                    zza2 = i + 8;
                    break;
                } else {
                    return i;
                }
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(zzds.zza(bArr, i)));
                    zza2 = i + 4;
                    break;
                } else {
                    return i;
                }
            case 58:
                if (i5 == 0) {
                    int zzb2 = zzds.zzb(bArr, i, zzdr);
                    unsafe.putObject(t, j, Boolean.valueOf(zzdr.zzb != 0));
                    zza2 = zzb2;
                    break;
                } else {
                    return i;
                }
            case 59:
                if (i5 != 2) {
                    return i;
                }
                int zza3 = zzds.zza(bArr, i, zzdr);
                int i9 = zzdr.zza;
                if (i9 == 0) {
                    unsafe.putObject(t, j, "");
                } else if ((536870912 & i6) == 0 || zzif.zza(bArr, zza3, zza3 + i9)) {
                    unsafe.putObject(t, j, new String(bArr, zza3, i9, zzfh.zza));
                    zza3 += i9;
                } else {
                    throw zzfm.zzh();
                }
                unsafe.putInt(t, j2, i4);
                return zza3;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                int zza4 = zzds.zza(zza(i8), bArr, i, i2, zzdr);
                if (unsafe.getInt(t, j2) == i4) {
                    obj2 = unsafe.getObject(t, j);
                } else {
                    obj2 = null;
                }
                if (obj2 == null) {
                    unsafe.putObject(t, j, zzdr.zzc);
                } else {
                    unsafe.putObject(t, j, zzfh.zza(obj2, zzdr.zzc));
                }
                unsafe.putInt(t, j2, i4);
                return zza4;
            case 61:
                if (i5 == 2) {
                    zza2 = zzds.zze(bArr, i, zzdr);
                    unsafe.putObject(t, j, zzdr.zzc);
                    break;
                } else {
                    return i;
                }
            case 63:
                if (i5 != 0) {
                    return i;
                }
                zza2 = zzds.zza(bArr, i, zzdr);
                int i10 = zzdr.zza;
                zzfi zzc2 = zzc(i8);
                if (zzc2 == null || zzc2.zza(i10)) {
                    unsafe.putObject(t, j, Integer.valueOf(i10));
                    break;
                } else {
                    zze((Object) t).zza(i3, (Object) Long.valueOf((long) i10));
                    return zza2;
                }
                break;
            case 66:
                if (i5 == 0) {
                    zza2 = zzds.zza(bArr, i, zzdr);
                    unsafe.putObject(t, j, Integer.valueOf(zzei.zze(zzdr.zza)));
                    break;
                } else {
                    return i;
                }
            case 67:
                if (i5 == 0) {
                    zza2 = zzds.zzb(bArr, i, zzdr);
                    unsafe.putObject(t, j, Long.valueOf(zzei.zza(zzdr.zzb)));
                    break;
                } else {
                    return i;
                }
            case 68:
                if (i5 == 3) {
                    zza2 = zzds.zza(zza(i8), bArr, i, i2, (i3 & -8) | 4, zzdr);
                    if (unsafe.getInt(t, j2) == i4) {
                        obj = unsafe.getObject(t, j);
                    } else {
                        obj = null;
                    }
                    if (obj != null) {
                        unsafe.putObject(t, j, zzfh.zza(obj, zzdr.zzc));
                        break;
                    } else {
                        unsafe.putObject(t, j, zzdr.zzc);
                        break;
                    }
                } else {
                    return i;
                }
            default:
                return i;
        }
        unsafe.putInt(t, j2, i4);
        return zza2;
    }

    private final zzhf zza(int i) {
        int i2 = (i / 3) << 1;
        zzhf zzhf = (zzhf) this.zzd[i2];
        if (zzhf != null) {
            return zzhf;
        }
        zzhf zza2 = zzhb.zza().zza((Class) this.zzd[i2 + 1]);
        this.zzd[i2] = zza2;
        return zza2;
    }

    private final Object zzb(int i) {
        return this.zzd[(i / 3) << 1];
    }

    private final zzfi zzc(int i) {
        return (zzfi) this.zzd[((i / 3) << 1) + 1];
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v45, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v86, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v61, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v65, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r17v3, types: [byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(T r41, byte[] r42, int r43, int r44, int r45, com.google.android.gms.internal.measurement.zzdr r46) throws java.io.IOException {
        /*
            r40 = this;
            sun.misc.Unsafe r4 = zzb
            r39 = 1048575(0xfffff, float:1.469367E-39)
            r38 = 0
            r17 = 0
            r18 = -1
            r20 = 0
            r6 = r18
            r7 = r43
        L_0x0011:
            r0 = r44
            if (r7 >= r0) goto L_0x042d
            int r10 = r7 + 1
            byte r17 = r42[r7]
            if (r17 >= 0) goto L_0x002b
            r0 = r17
            r1 = r42
            r2 = r46
            int r10 = com.google.android.gms.internal.measurement.zzds.zza((int) r0, (byte[]) r1, (int) r10, (com.google.android.gms.internal.measurement.zzdr) r2)
            r0 = r46
            int r0 = r0.zza
            r17 = r0
        L_0x002b:
            int r18 = r17 >>> 3
            r19 = r17 & 7
            r0 = r18
            if (r0 <= r6) goto L_0x0088
            int r5 = r20 / 3
            r0 = r40
            r1 = r18
            int r20 = r0.zza((int) r1, (int) r5)
        L_0x003d:
            r5 = -1
            r0 = r20
            if (r0 != r5) goto L_0x0091
            r20 = 0
            r11 = r38
            r12 = r39
            r7 = r10
        L_0x0049:
            r0 = r17
            r1 = r45
            if (r0 != r1) goto L_0x0051
            if (r45 != 0) goto L_0x03c8
        L_0x0051:
            r0 = r40
            boolean r5 = r0.zzh
            if (r5 == 0) goto L_0x03b0
            r0 = r46
            com.google.android.gms.internal.measurement.zzer r5 = r0.zzd
            com.google.android.gms.internal.measurement.zzer r6 = com.google.android.gms.internal.measurement.zzer.zza()
            if (r5 == r6) goto L_0x03b0
            r0 = r40
            com.google.android.gms.internal.measurement.zzgm r5 = r0.zzg
            int r6 = r17 >>> 3
            r0 = r46
            com.google.android.gms.internal.measurement.zzer r8 = r0.zzd
            com.google.android.gms.internal.measurement.zzfe$zzd r5 = r8.zza(r5, r6)
            if (r5 != 0) goto L_0x039d
            com.google.android.gms.internal.measurement.zzhw r9 = zze((java.lang.Object) r41)
            r5 = r17
            r6 = r42
            r8 = r44
            r10 = r46
            int r7 = com.google.android.gms.internal.measurement.zzds.zza((int) r5, (byte[]) r6, (int) r7, (int) r8, (com.google.android.gms.internal.measurement.zzhw) r9, (com.google.android.gms.internal.measurement.zzdr) r10)
            r6 = r18
            r38 = r11
            r39 = r12
            goto L_0x0011
        L_0x0088:
            r0 = r40
            r1 = r18
            int r20 = r0.zzg(r1)
            goto L_0x003d
        L_0x0091:
            r0 = r40
            int[] r5 = r0.zzc
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
            if (r0 > r5) goto L_0x02e9
            r0 = r40
            int[] r5 = r0.zzc
            int r8 = r20 + 2
            r5 = r5[r8]
            r8 = 1
            int r9 = r5 >>> 20
            int r14 = r8 << r9
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r8
            r0 = r39
            if (r5 == r0) goto L_0x00da
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r39
            if (r0 == r8) goto L_0x00d1
            r0 = r39
            long r8 = (long) r0
            r0 = r41
            r1 = r38
            r4.putInt(r0, r8, r1)
        L_0x00d1:
            long r8 = (long) r5
            r0 = r41
            int r38 = r4.getInt(r0, r8)
            r39 = r5
        L_0x00da:
            switch(r23) {
                case 0: goto L_0x00e4;
                case 1: goto L_0x00fc;
                case 2: goto L_0x0114;
                case 3: goto L_0x0114;
                case 4: goto L_0x012f;
                case 5: goto L_0x014a;
                case 6: goto L_0x0162;
                case 7: goto L_0x017a;
                case 8: goto L_0x019e;
                case 9: goto L_0x01ca;
                case 10: goto L_0x020a;
                case 11: goto L_0x012f;
                case 12: goto L_0x0228;
                case 13: goto L_0x0162;
                case 14: goto L_0x014a;
                case 15: goto L_0x0267;
                case 16: goto L_0x0286;
                case 17: goto L_0x02a5;
                default: goto L_0x00dd;
            }
        L_0x00dd:
            r11 = r38
            r12 = r39
            r7 = r10
            goto L_0x0049
        L_0x00e4:
            r5 = 1
            r0 = r19
            if (r0 != r5) goto L_0x00dd
            r0 = r42
            double r8 = com.google.android.gms.internal.measurement.zzds.zzc(r0, r10)
            r0 = r41
            com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r0, (long) r6, (double) r8)
            int r7 = r10 + 8
            r38 = r38 | r14
            r6 = r18
            goto L_0x0011
        L_0x00fc:
            r5 = 5
            r0 = r19
            if (r0 != r5) goto L_0x00dd
            r0 = r42
            float r5 = com.google.android.gms.internal.measurement.zzds.zzd(r0, r10)
            r0 = r41
            com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r0, (long) r6, (float) r5)
            int r7 = r10 + 4
            r38 = r38 | r14
            r6 = r18
            goto L_0x0011
        L_0x0114:
            if (r19 != 0) goto L_0x00dd
            r0 = r42
            r1 = r46
            int r43 = com.google.android.gms.internal.measurement.zzds.zzb(r0, r10, r1)
            r0 = r46
            long r8 = r0.zzb
            r5 = r41
            r4.putLong(r5, r6, r8)
            r38 = r38 | r14
            r6 = r18
            r7 = r43
            goto L_0x0011
        L_0x012f:
            if (r19 != 0) goto L_0x00dd
            r0 = r42
            r1 = r46
            int r43 = com.google.android.gms.internal.measurement.zzds.zza(r0, r10, r1)
            r0 = r46
            int r5 = r0.zza
            r0 = r41
            r4.putInt(r0, r6, r5)
            r38 = r38 | r14
            r6 = r18
            r7 = r43
            goto L_0x0011
        L_0x014a:
            r5 = 1
            r0 = r19
            if (r0 != r5) goto L_0x00dd
            r0 = r42
            long r8 = com.google.android.gms.internal.measurement.zzds.zzb(r0, r10)
            r5 = r41
            r4.putLong(r5, r6, r8)
            int r7 = r10 + 8
            r38 = r38 | r14
            r6 = r18
            goto L_0x0011
        L_0x0162:
            r5 = 5
            r0 = r19
            if (r0 != r5) goto L_0x00dd
            r0 = r42
            int r5 = com.google.android.gms.internal.measurement.zzds.zza(r0, r10)
            r0 = r41
            r4.putInt(r0, r6, r5)
            int r7 = r10 + 4
            r38 = r38 | r14
            r6 = r18
            goto L_0x0011
        L_0x017a:
            if (r19 != 0) goto L_0x00dd
            r0 = r42
            r1 = r46
            int r43 = com.google.android.gms.internal.measurement.zzds.zzb(r0, r10, r1)
            r0 = r46
            long r8 = r0.zzb
            r10 = 0
            int r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r5 == 0) goto L_0x019c
            r5 = 1
        L_0x018f:
            r0 = r41
            com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r0, (long) r6, (boolean) r5)
            r38 = r38 | r14
            r6 = r18
            r7 = r43
            goto L_0x0011
        L_0x019c:
            r5 = 0
            goto L_0x018f
        L_0x019e:
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x00dd
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r5 & r32
            if (r5 != 0) goto L_0x01c1
            r0 = r42
            r1 = r46
            int r5 = com.google.android.gms.internal.measurement.zzds.zzc(r0, r10, r1)
        L_0x01b1:
            r0 = r46
            java.lang.Object r8 = r0.zzc
            r0 = r41
            r4.putObject(r0, r6, r8)
            r38 = r38 | r14
            r6 = r18
            r7 = r5
            goto L_0x0011
        L_0x01c1:
            r0 = r42
            r1 = r46
            int r5 = com.google.android.gms.internal.measurement.zzds.zzd(r0, r10, r1)
            goto L_0x01b1
        L_0x01ca:
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x00dd
            r0 = r40
            r1 = r20
            com.google.android.gms.internal.measurement.zzhf r5 = r0.zza((int) r1)
            r0 = r42
            r1 = r44
            r2 = r46
            int r43 = com.google.android.gms.internal.measurement.zzds.zza((com.google.android.gms.internal.measurement.zzhf) r5, (byte[]) r0, (int) r10, (int) r1, (com.google.android.gms.internal.measurement.zzdr) r2)
            r5 = r38 & r14
            if (r5 != 0) goto L_0x01f6
            r0 = r46
            java.lang.Object r5 = r0.zzc
            r0 = r41
            r4.putObject(r0, r6, r5)
        L_0x01ee:
            r38 = r38 | r14
            r6 = r18
            r7 = r43
            goto L_0x0011
        L_0x01f6:
            r0 = r41
            java.lang.Object r5 = r4.getObject(r0, r6)
            r0 = r46
            java.lang.Object r8 = r0.zzc
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfh.zza((java.lang.Object) r5, (java.lang.Object) r8)
            r0 = r41
            r4.putObject(r0, r6, r5)
            goto L_0x01ee
        L_0x020a:
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x00dd
            r0 = r42
            r1 = r46
            int r43 = com.google.android.gms.internal.measurement.zzds.zze(r0, r10, r1)
            r0 = r46
            java.lang.Object r5 = r0.zzc
            r0 = r41
            r4.putObject(r0, r6, r5)
            r38 = r38 | r14
            r6 = r18
            r7 = r43
            goto L_0x0011
        L_0x0228:
            if (r19 != 0) goto L_0x00dd
            r0 = r42
            r1 = r46
            int r43 = com.google.android.gms.internal.measurement.zzds.zza(r0, r10, r1)
            r0 = r46
            int r5 = r0.zza
            r0 = r40
            r1 = r20
            com.google.android.gms.internal.measurement.zzfi r8 = r0.zzc((int) r1)
            if (r8 == 0) goto L_0x0246
            boolean r8 = r8.zza(r5)
            if (r8 == 0) goto L_0x0253
        L_0x0246:
            r0 = r41
            r4.putInt(r0, r6, r5)
            r38 = r38 | r14
            r6 = r18
            r7 = r43
            goto L_0x0011
        L_0x0253:
            com.google.android.gms.internal.measurement.zzhw r6 = zze((java.lang.Object) r41)
            long r8 = (long) r5
            java.lang.Long r5 = java.lang.Long.valueOf(r8)
            r0 = r17
            r6.zza((int) r0, (java.lang.Object) r5)
            r6 = r18
            r7 = r43
            goto L_0x0011
        L_0x0267:
            if (r19 != 0) goto L_0x00dd
            r0 = r42
            r1 = r46
            int r43 = com.google.android.gms.internal.measurement.zzds.zza(r0, r10, r1)
            r0 = r46
            int r5 = r0.zza
            int r5 = com.google.android.gms.internal.measurement.zzei.zze(r5)
            r0 = r41
            r4.putInt(r0, r6, r5)
            r38 = r38 | r14
            r6 = r18
            r7 = r43
            goto L_0x0011
        L_0x0286:
            if (r19 != 0) goto L_0x00dd
            r0 = r42
            r1 = r46
            int r43 = com.google.android.gms.internal.measurement.zzds.zzb(r0, r10, r1)
            r0 = r46
            long r8 = r0.zzb
            long r8 = com.google.android.gms.internal.measurement.zzei.zza((long) r8)
            r5 = r41
            r4.putLong(r5, r6, r8)
            r38 = r38 | r14
            r6 = r18
            r7 = r43
            goto L_0x0011
        L_0x02a5:
            r5 = 3
            r0 = r19
            if (r0 != r5) goto L_0x00dd
            int r5 = r18 << 3
            r12 = r5 | 4
            r0 = r40
            r1 = r20
            com.google.android.gms.internal.measurement.zzhf r8 = r0.zza((int) r1)
            r9 = r42
            r11 = r44
            r13 = r46
            int r43 = com.google.android.gms.internal.measurement.zzds.zza((com.google.android.gms.internal.measurement.zzhf) r8, (byte[]) r9, (int) r10, (int) r11, (int) r12, (com.google.android.gms.internal.measurement.zzdr) r13)
            r5 = r38 & r14
            if (r5 != 0) goto L_0x02d5
            r0 = r46
            java.lang.Object r5 = r0.zzc
            r0 = r41
            r4.putObject(r0, r6, r5)
        L_0x02cd:
            r38 = r38 | r14
            r6 = r18
            r7 = r43
            goto L_0x0011
        L_0x02d5:
            r0 = r41
            java.lang.Object r5 = r4.getObject(r0, r6)
            r0 = r46
            java.lang.Object r8 = r0.zzc
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfh.zza((java.lang.Object) r5, (java.lang.Object) r8)
            r0 = r41
            r4.putObject(r0, r6, r5)
            goto L_0x02cd
        L_0x02e9:
            r5 = 27
            r0 = r23
            if (r0 != r5) goto L_0x032e
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x0423
            r0 = r41
            java.lang.Object r5 = r4.getObject(r0, r6)
            com.google.android.gms.internal.measurement.zzfn r5 = (com.google.android.gms.internal.measurement.zzfn) r5
            boolean r8 = r5.zza()
            if (r8 != 0) goto L_0x042a
            int r8 = r5.size()
            if (r8 != 0) goto L_0x032b
            r8 = 10
        L_0x030a:
            com.google.android.gms.internal.measurement.zzfn r12 = r5.zza(r8)
            r0 = r41
            r4.putObject(r0, r6, r12)
        L_0x0313:
            r0 = r40
            r1 = r20
            com.google.android.gms.internal.measurement.zzhf r7 = r0.zza((int) r1)
            r8 = r17
            r9 = r42
            r11 = r44
            r13 = r46
            int r7 = com.google.android.gms.internal.measurement.zzds.zza(r7, r8, r9, r10, r11, r12, r13)
            r6 = r18
            goto L_0x0011
        L_0x032b:
            int r8 = r8 << 1
            goto L_0x030a
        L_0x032e:
            r5 = 49
            r0 = r23
            if (r0 > r5) goto L_0x0352
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
            int r7 = r12.zza(r13, (byte[]) r14, (int) r15, (int) r16, (int) r17, (int) r18, (int) r19, (int) r20, (long) r21, (int) r23, (long) r24, (com.google.android.gms.internal.measurement.zzdr) r26)
            if (r7 != r10) goto L_0x041f
            r11 = r38
            r12 = r39
            goto L_0x0049
        L_0x0352:
            r5 = 50
            r0 = r23
            if (r0 != r5) goto L_0x0379
            r5 = 2
            r0 = r19
            if (r0 != r5) goto L_0x0423
            r22 = r40
            r23 = r41
            r24 = r42
            r25 = r10
            r26 = r44
            r27 = r20
            r28 = r6
            r30 = r46
            int r7 = r22.zza(r23, (byte[]) r24, (int) r25, (int) r26, (int) r27, (long) r28, (com.google.android.gms.internal.measurement.zzdr) r30)
            if (r7 != r10) goto L_0x041f
            r11 = r38
            r12 = r39
            goto L_0x0049
        L_0x0379:
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
            int r7 = r24.zza(r25, (byte[]) r26, (int) r27, (int) r28, (int) r29, (int) r30, (int) r31, (int) r32, (int) r33, (long) r34, (int) r36, (com.google.android.gms.internal.measurement.zzdr) r37)
            if (r7 != r10) goto L_0x041f
            r11 = r38
            r12 = r39
            goto L_0x0049
        L_0x039d:
            r4 = r41
            com.google.android.gms.internal.measurement.zzfe$zzb r4 = (com.google.android.gms.internal.measurement.zzfe.zzb) r4
            r4.zza()
            com.google.android.gms.internal.measurement.zzfe$zzb r41 = (com.google.android.gms.internal.measurement.zzfe.zzb) r41
            r0 = r41
            com.google.android.gms.internal.measurement.zzeu<com.google.android.gms.internal.measurement.zzfe$zze> r4 = r0.zzc
            java.lang.NoSuchMethodError r4 = new java.lang.NoSuchMethodError
            r4.<init>()
            throw r4
        L_0x03b0:
            com.google.android.gms.internal.measurement.zzhw r9 = zze((java.lang.Object) r41)
            r5 = r17
            r6 = r42
            r8 = r44
            r10 = r46
            int r7 = com.google.android.gms.internal.measurement.zzds.zza((int) r5, (byte[]) r6, (int) r7, (int) r8, (com.google.android.gms.internal.measurement.zzhw) r9, (com.google.android.gms.internal.measurement.zzdr) r10)
            r6 = r18
            r38 = r11
            r39 = r12
            goto L_0x0011
        L_0x03c8:
            r5 = r11
            r6 = r12
        L_0x03ca:
            r8 = 1048575(0xfffff, float:1.469367E-39)
            if (r6 == r8) goto L_0x03d5
            long r8 = (long) r6
            r0 = r41
            r4.putInt(r0, r8, r5)
        L_0x03d5:
            r6 = 0
            r0 = r40
            int r4 = r0.zzm
            r5 = r4
        L_0x03db:
            r0 = r40
            int r4 = r0.zzn
            if (r5 >= r4) goto L_0x03f9
            r0 = r40
            int[] r4 = r0.zzl
            r4 = r4[r5]
            r0 = r40
            com.google.android.gms.internal.measurement.zzhx<?, ?> r8 = r0.zzq
            r0 = r40
            r1 = r41
            java.lang.Object r4 = r0.zza((java.lang.Object) r1, (int) r4, r6, r8)
            com.google.android.gms.internal.measurement.zzhw r4 = (com.google.android.gms.internal.measurement.zzhw) r4
            int r5 = r5 + 1
            r6 = r4
            goto L_0x03db
        L_0x03f9:
            if (r6 == 0) goto L_0x0404
            r0 = r40
            com.google.android.gms.internal.measurement.zzhx<?, ?> r4 = r0.zzq
            r0 = r41
            r4.zzb((java.lang.Object) r0, r6)
        L_0x0404:
            if (r45 != 0) goto L_0x040f
            r0 = r44
            if (r7 == r0) goto L_0x041e
            com.google.android.gms.internal.measurement.zzfm r4 = com.google.android.gms.internal.measurement.zzfm.zzg()
            throw r4
        L_0x040f:
            r0 = r44
            if (r7 > r0) goto L_0x0419
            r0 = r17
            r1 = r45
            if (r0 == r1) goto L_0x041e
        L_0x0419:
            com.google.android.gms.internal.measurement.zzfm r4 = com.google.android.gms.internal.measurement.zzfm.zzg()
            throw r4
        L_0x041e:
            return r7
        L_0x041f:
            r6 = r18
            goto L_0x0011
        L_0x0423:
            r11 = r38
            r12 = r39
            r7 = r10
            goto L_0x0049
        L_0x042a:
            r12 = r5
            goto L_0x0313
        L_0x042d:
            r5 = r38
            r6 = r39
            goto L_0x03ca
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgq.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.zzdr):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v54, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r37, byte[] r38, int r39, int r40, com.google.android.gms.internal.measurement.zzdr r41) throws java.io.IOException {
        /*
            r36 = this;
            r0 = r36
            boolean r4 = r0.zzj
            if (r4 == 0) goto L_0x030e
            sun.misc.Unsafe r4 = zzb
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r34 = 0
            r14 = -1
            r16 = 0
            r6 = r14
            r35 = r10
            r7 = r39
        L_0x0015:
            r0 = r40
            if (r7 >= r0) goto L_0x02f4
            int r11 = r7 + 1
            byte r13 = r38[r7]
            if (r13 >= 0) goto L_0x002b
            r0 = r38
            r1 = r41
            int r11 = com.google.android.gms.internal.measurement.zzds.zza((int) r13, (byte[]) r0, (int) r11, (com.google.android.gms.internal.measurement.zzdr) r1)
            r0 = r41
            int r13 = r0.zza
        L_0x002b:
            int r14 = r13 >>> 3
            r15 = r13 & 7
            if (r14 <= r6) goto L_0x0056
            int r5 = r16 / 3
            r0 = r36
            int r16 = r0.zza((int) r14, (int) r5)
        L_0x0039:
            r5 = -1
            r0 = r16
            if (r0 != r5) goto L_0x005d
            r16 = 0
            r12 = r35
            r7 = r11
        L_0x0043:
            com.google.android.gms.internal.measurement.zzhw r9 = zze((java.lang.Object) r37)
            r5 = r13
            r6 = r38
            r8 = r40
            r10 = r41
            int r7 = com.google.android.gms.internal.measurement.zzds.zza((int) r5, (byte[]) r6, (int) r7, (int) r8, (com.google.android.gms.internal.measurement.zzhw) r9, (com.google.android.gms.internal.measurement.zzdr) r10)
            r6 = r14
            r35 = r12
            goto L_0x0015
        L_0x0056:
            r0 = r36
            int r16 = r0.zzg(r14)
            goto L_0x0039
        L_0x005d:
            r0 = r36
            int[] r5 = r0.zzc
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
            if (r0 > r5) goto L_0x024c
            r0 = r36
            int[] r5 = r0.zzc
            int r8 = r16 + 2
            r5 = r5[r8]
            r8 = 1
            int r9 = r5 >>> 20
            int r12 = r8 << r9
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r10 = r5 & r8
            r0 = r35
            if (r10 == r0) goto L_0x032e
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r35
            if (r0 == r5) goto L_0x009e
            r0 = r35
            long r8 = (long) r0
            r0 = r37
            r1 = r34
            r4.putInt(r0, r8, r1)
        L_0x009e:
            r5 = 1048575(0xfffff, float:1.469367E-39)
            if (r10 == r5) goto L_0x032a
            long r8 = (long) r10
            r0 = r37
            int r5 = r4.getInt(r0, r8)
        L_0x00aa:
            r34 = r5
        L_0x00ac:
            switch(r19) {
                case 0: goto L_0x00b2;
                case 1: goto L_0x00c9;
                case 2: goto L_0x00e0;
                case 3: goto L_0x00e0;
                case 4: goto L_0x00fc;
                case 5: goto L_0x0118;
                case 6: goto L_0x012f;
                case 7: goto L_0x0146;
                case 8: goto L_0x016b;
                case 9: goto L_0x0196;
                case 10: goto L_0x01d3;
                case 11: goto L_0x00fc;
                case 12: goto L_0x01f0;
                case 13: goto L_0x012f;
                case 14: goto L_0x0118;
                case 15: goto L_0x020c;
                case 16: goto L_0x022c;
                default: goto L_0x00af;
            }
        L_0x00af:
            r12 = r10
            r7 = r11
            goto L_0x0043
        L_0x00b2:
            r5 = 1
            if (r15 != r5) goto L_0x00af
            r0 = r38
            double r8 = com.google.android.gms.internal.measurement.zzds.zzc(r0, r11)
            r0 = r37
            com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r0, (long) r6, (double) r8)
            int r7 = r11 + 8
            r34 = r34 | r12
            r6 = r14
            r35 = r10
            goto L_0x0015
        L_0x00c9:
            r5 = 5
            if (r15 != r5) goto L_0x00af
            r0 = r38
            float r5 = com.google.android.gms.internal.measurement.zzds.zzd(r0, r11)
            r0 = r37
            com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r0, (long) r6, (float) r5)
            int r7 = r11 + 4
            r34 = r34 | r12
            r6 = r14
            r35 = r10
            goto L_0x0015
        L_0x00e0:
            if (r15 != 0) goto L_0x00af
            r0 = r38
            r1 = r41
            int r39 = com.google.android.gms.internal.measurement.zzds.zzb(r0, r11, r1)
            r0 = r41
            long r8 = r0.zzb
            r5 = r37
            r4.putLong(r5, r6, r8)
            r34 = r34 | r12
            r6 = r14
            r35 = r10
            r7 = r39
            goto L_0x0015
        L_0x00fc:
            if (r15 != 0) goto L_0x00af
            r0 = r38
            r1 = r41
            int r39 = com.google.android.gms.internal.measurement.zzds.zza(r0, r11, r1)
            r0 = r41
            int r5 = r0.zza
            r0 = r37
            r4.putInt(r0, r6, r5)
            r34 = r34 | r12
            r6 = r14
            r35 = r10
            r7 = r39
            goto L_0x0015
        L_0x0118:
            r5 = 1
            if (r15 != r5) goto L_0x00af
            r0 = r38
            long r8 = com.google.android.gms.internal.measurement.zzds.zzb(r0, r11)
            r5 = r37
            r4.putLong(r5, r6, r8)
            int r7 = r11 + 8
            r34 = r34 | r12
            r6 = r14
            r35 = r10
            goto L_0x0015
        L_0x012f:
            r5 = 5
            if (r15 != r5) goto L_0x00af
            r0 = r38
            int r5 = com.google.android.gms.internal.measurement.zzds.zza(r0, r11)
            r0 = r37
            r4.putInt(r0, r6, r5)
            int r7 = r11 + 4
            r34 = r34 | r12
            r6 = r14
            r35 = r10
            goto L_0x0015
        L_0x0146:
            if (r15 != 0) goto L_0x00af
            r0 = r38
            r1 = r41
            int r39 = com.google.android.gms.internal.measurement.zzds.zzb(r0, r11, r1)
            r0 = r41
            long r8 = r0.zzb
            r18 = 0
            int r5 = (r8 > r18 ? 1 : (r8 == r18 ? 0 : -1))
            if (r5 == 0) goto L_0x0169
            r5 = 1
        L_0x015b:
            r0 = r37
            com.google.android.gms.internal.measurement.zzid.zza((java.lang.Object) r0, (long) r6, (boolean) r5)
            r34 = r34 | r12
            r6 = r14
            r35 = r10
            r7 = r39
            goto L_0x0015
        L_0x0169:
            r5 = 0
            goto L_0x015b
        L_0x016b:
            r5 = 2
            if (r15 != r5) goto L_0x00af
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r5 & r28
            if (r5 != 0) goto L_0x018d
            r0 = r38
            r1 = r41
            int r5 = com.google.android.gms.internal.measurement.zzds.zzc(r0, r11, r1)
        L_0x017c:
            r0 = r41
            java.lang.Object r8 = r0.zzc
            r0 = r37
            r4.putObject(r0, r6, r8)
            r34 = r34 | r12
            r6 = r14
            r35 = r10
            r7 = r5
            goto L_0x0015
        L_0x018d:
            r0 = r38
            r1 = r41
            int r5 = com.google.android.gms.internal.measurement.zzds.zzd(r0, r11, r1)
            goto L_0x017c
        L_0x0196:
            r5 = 2
            if (r15 != r5) goto L_0x00af
            r0 = r36
            r1 = r16
            com.google.android.gms.internal.measurement.zzhf r5 = r0.zza((int) r1)
            r0 = r38
            r1 = r40
            r2 = r41
            int r39 = com.google.android.gms.internal.measurement.zzds.zza((com.google.android.gms.internal.measurement.zzhf) r5, (byte[]) r0, (int) r11, (int) r1, (com.google.android.gms.internal.measurement.zzdr) r2)
            r0 = r37
            java.lang.Object r5 = r4.getObject(r0, r6)
            if (r5 != 0) goto L_0x01c5
            r0 = r41
            java.lang.Object r5 = r0.zzc
            r0 = r37
            r4.putObject(r0, r6, r5)
        L_0x01bc:
            r34 = r34 | r12
            r6 = r14
            r35 = r10
            r7 = r39
            goto L_0x0015
        L_0x01c5:
            r0 = r41
            java.lang.Object r8 = r0.zzc
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfh.zza((java.lang.Object) r5, (java.lang.Object) r8)
            r0 = r37
            r4.putObject(r0, r6, r5)
            goto L_0x01bc
        L_0x01d3:
            r5 = 2
            if (r15 != r5) goto L_0x00af
            r0 = r38
            r1 = r41
            int r39 = com.google.android.gms.internal.measurement.zzds.zze(r0, r11, r1)
            r0 = r41
            java.lang.Object r5 = r0.zzc
            r0 = r37
            r4.putObject(r0, r6, r5)
            r34 = r34 | r12
            r6 = r14
            r35 = r10
            r7 = r39
            goto L_0x0015
        L_0x01f0:
            if (r15 != 0) goto L_0x00af
            r0 = r38
            r1 = r41
            int r39 = com.google.android.gms.internal.measurement.zzds.zza(r0, r11, r1)
            r0 = r41
            int r5 = r0.zza
            r0 = r37
            r4.putInt(r0, r6, r5)
            r34 = r34 | r12
            r6 = r14
            r35 = r10
            r7 = r39
            goto L_0x0015
        L_0x020c:
            if (r15 != 0) goto L_0x00af
            r0 = r38
            r1 = r41
            int r39 = com.google.android.gms.internal.measurement.zzds.zza(r0, r11, r1)
            r0 = r41
            int r5 = r0.zza
            int r5 = com.google.android.gms.internal.measurement.zzei.zze(r5)
            r0 = r37
            r4.putInt(r0, r6, r5)
            r34 = r34 | r12
            r6 = r14
            r35 = r10
            r7 = r39
            goto L_0x0015
        L_0x022c:
            if (r15 != 0) goto L_0x00af
            r0 = r38
            r1 = r41
            int r39 = com.google.android.gms.internal.measurement.zzds.zzb(r0, r11, r1)
            r0 = r41
            long r8 = r0.zzb
            long r8 = com.google.android.gms.internal.measurement.zzei.zza((long) r8)
            r5 = r37
            r4.putLong(r5, r6, r8)
            r34 = r34 | r12
            r6 = r14
            r35 = r10
            r7 = r39
            goto L_0x0015
        L_0x024c:
            r5 = 27
            r0 = r19
            if (r0 != r5) goto L_0x028e
            r5 = 2
            if (r15 != r5) goto L_0x0322
            r0 = r37
            java.lang.Object r5 = r4.getObject(r0, r6)
            com.google.android.gms.internal.measurement.zzfn r5 = (com.google.android.gms.internal.measurement.zzfn) r5
            boolean r8 = r5.zza()
            if (r8 != 0) goto L_0x0327
            int r8 = r5.size()
            if (r8 != 0) goto L_0x028b
            r8 = 10
        L_0x026b:
            com.google.android.gms.internal.measurement.zzfn r10 = r5.zza(r8)
            r0 = r37
            r4.putObject(r0, r6, r10)
        L_0x0274:
            r0 = r36
            r1 = r16
            com.google.android.gms.internal.measurement.zzhf r5 = r0.zza((int) r1)
            r6 = r13
            r7 = r38
            r8 = r11
            r9 = r40
            r11 = r41
            int r7 = com.google.android.gms.internal.measurement.zzds.zza(r5, r6, r7, r8, r9, r10, r11)
            r6 = r14
            goto L_0x0015
        L_0x028b:
            int r8 = r8 << 1
            goto L_0x026b
        L_0x028e:
            r5 = 49
            r0 = r19
            if (r0 > r5) goto L_0x02af
            r0 = r28
            long r0 = (long) r0
            r17 = r0
            r8 = r36
            r9 = r37
            r10 = r38
            r12 = r40
            r20 = r6
            r22 = r41
            int r7 = r8.zza(r9, (byte[]) r10, (int) r11, (int) r12, (int) r13, (int) r14, (int) r15, (int) r16, (long) r17, (int) r19, (long) r20, (com.google.android.gms.internal.measurement.zzdr) r22)
            if (r7 != r11) goto L_0x031f
            r12 = r35
            goto L_0x0043
        L_0x02af:
            r5 = 50
            r0 = r19
            if (r0 != r5) goto L_0x02d2
            r5 = 2
            if (r15 != r5) goto L_0x0322
            r18 = r36
            r19 = r37
            r20 = r38
            r21 = r11
            r22 = r40
            r23 = r16
            r24 = r6
            r26 = r41
            int r7 = r18.zza(r19, (byte[]) r20, (int) r21, (int) r22, (int) r23, (long) r24, (com.google.android.gms.internal.measurement.zzdr) r26)
            if (r7 != r11) goto L_0x031f
            r12 = r35
            goto L_0x0043
        L_0x02d2:
            r20 = r36
            r21 = r37
            r22 = r38
            r23 = r11
            r24 = r40
            r25 = r13
            r26 = r14
            r27 = r15
            r29 = r19
            r30 = r6
            r32 = r16
            r33 = r41
            int r7 = r20.zza(r21, (byte[]) r22, (int) r23, (int) r24, (int) r25, (int) r26, (int) r27, (int) r28, (int) r29, (long) r30, (int) r32, (com.google.android.gms.internal.measurement.zzdr) r33)
            if (r7 != r11) goto L_0x031f
            r12 = r35
            goto L_0x0043
        L_0x02f4:
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r35
            if (r0 == r5) goto L_0x0305
            r0 = r35
            long r8 = (long) r0
            r0 = r37
            r1 = r34
            r4.putInt(r0, r8, r1)
        L_0x0305:
            r0 = r40
            if (r7 == r0) goto L_0x031e
            com.google.android.gms.internal.measurement.zzfm r4 = com.google.android.gms.internal.measurement.zzfm.zzg()
            throw r4
        L_0x030e:
            r9 = 0
            r4 = r36
            r5 = r37
            r6 = r38
            r7 = r39
            r8 = r40
            r10 = r41
            r4.zza(r5, (byte[]) r6, (int) r7, (int) r8, (int) r9, (com.google.android.gms.internal.measurement.zzdr) r10)
        L_0x031e:
            return
        L_0x031f:
            r6 = r14
            goto L_0x0015
        L_0x0322:
            r12 = r35
            r7 = r11
            goto L_0x0043
        L_0x0327:
            r10 = r5
            goto L_0x0274
        L_0x032a:
            r5 = r34
            goto L_0x00aa
        L_0x032e:
            r10 = r35
            goto L_0x00ac
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgq.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzdr):void");
    }

    public final void zzc(T t) {
        for (int i = this.zzm; i < this.zzn; i++) {
            long zzd2 = (long) (zzd(this.zzl[i]) & 1048575);
            Object zzf2 = zzid.zzf(t, zzd2);
            if (zzf2 != null) {
                zzid.zza((Object) t, zzd2, this.zzs.zze(zzf2));
            }
        }
        int length = this.zzl.length;
        for (int i2 = this.zzn; i2 < length; i2++) {
            this.zzp.zzb(t, (long) this.zzl[i2]);
        }
        this.zzq.zzd(t);
        if (this.zzh) {
            this.zzr.zzc(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzhx<UT, UB> zzhx) {
        zzfi zzc2;
        int i2 = this.zzc[i];
        Object zzf2 = zzid.zzf(obj, (long) (zzd(i) & 1048575));
        if (zzf2 == null || (zzc2 = zzc(i)) == null) {
            return ub;
        }
        return zza(i, i2, this.zzs.zza(zzf2), zzc2, ub, zzhx);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzfi zzfi, UB ub, zzhx<UT, UB> zzhx) {
        zzgh<?, ?> zzb2 = this.zzs.zzb(zzb(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (!zzfi.zza(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzhx.zza();
                }
                zzee zzc2 = zzdw.zzc(zzge.zza(zzb2, next.getKey(), next.getValue()));
                try {
                    zzge.zza(zzc2.zzb(), zzb2, next.getKey(), next.getValue());
                    zzhx.zza(ub, i2, zzc2.zza());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x0040 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x004b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzd(T r15) {
        /*
            r14 = this;
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r9 = 1
            r7 = 0
            r6 = r7
            r4 = r7
            r3 = r8
        L_0x0008:
            int r0 = r14.zzm
            if (r6 >= r0) goto L_0x00f5
            int[] r0 = r14.zzl
            r2 = r0[r6]
            int[] r0 = r14.zzc
            r10 = r0[r2]
            int r11 = r14.zzd((int) r2)
            int[] r0 = r14.zzc
            int r1 = r2 + 2
            r1 = r0[r1]
            r0 = r1 & r8
            int r1 = r1 >>> 20
            int r5 = r9 << r1
            if (r0 == r3) goto L_0x0030
            if (r0 == r8) goto L_0x0108
            sun.misc.Unsafe r1 = zzb
            long r12 = (long) r0
            int r4 = r1.getInt(r15, r12)
            r3 = r0
        L_0x0030:
            r0 = 268435456(0x10000000, float:2.5243549E-29)
            r0 = r0 & r11
            if (r0 == 0) goto L_0x0041
            r0 = r9
        L_0x0036:
            if (r0 == 0) goto L_0x0043
            r0 = r14
            r1 = r15
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 != 0) goto L_0x0043
        L_0x0040:
            return r7
        L_0x0041:
            r0 = r7
            goto L_0x0036
        L_0x0043:
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r11
            int r0 = r0 >>> 20
            switch(r0) {
                case 9: goto L_0x004f;
                case 17: goto L_0x004f;
                case 27: goto L_0x0062;
                case 49: goto L_0x0062;
                case 50: goto L_0x00a0;
                case 60: goto L_0x008f;
                case 68: goto L_0x008f;
                default: goto L_0x004b;
            }
        L_0x004b:
            int r0 = r6 + 1
            r6 = r0
            goto L_0x0008
        L_0x004f:
            r0 = r14
            r1 = r15
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x004b
            com.google.android.gms.internal.measurement.zzhf r0 = r14.zza((int) r2)
            boolean r0 = zza((java.lang.Object) r15, (int) r11, (com.google.android.gms.internal.measurement.zzhf) r0)
            if (r0 != 0) goto L_0x004b
            goto L_0x0040
        L_0x0062:
            r0 = r11 & r8
            long r0 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzid.zzf(r15, r0)
            java.util.List r0 = (java.util.List) r0
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x008d
            com.google.android.gms.internal.measurement.zzhf r2 = r14.zza((int) r2)
            r1 = r7
        L_0x0076:
            int r5 = r0.size()
            if (r1 >= r5) goto L_0x008d
            java.lang.Object r5 = r0.get(r1)
            boolean r5 = r2.zzd(r5)
            if (r5 != 0) goto L_0x008a
            r0 = r7
        L_0x0087:
            if (r0 != 0) goto L_0x004b
            goto L_0x0040
        L_0x008a:
            int r1 = r1 + 1
            goto L_0x0076
        L_0x008d:
            r0 = r9
            goto L_0x0087
        L_0x008f:
            boolean r0 = r14.zza(r15, (int) r10, (int) r2)
            if (r0 == 0) goto L_0x004b
            com.google.android.gms.internal.measurement.zzhf r0 = r14.zza((int) r2)
            boolean r0 = zza((java.lang.Object) r15, (int) r11, (com.google.android.gms.internal.measurement.zzhf) r0)
            if (r0 != 0) goto L_0x004b
            goto L_0x0040
        L_0x00a0:
            com.google.android.gms.internal.measurement.zzgj r0 = r14.zzs
            r1 = r11 & r8
            long r10 = (long) r1
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzid.zzf(r15, r10)
            java.util.Map r1 = r0.zzc(r1)
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x00f3
            java.lang.Object r0 = r14.zzb((int) r2)
            com.google.android.gms.internal.measurement.zzgj r2 = r14.zzs
            com.google.android.gms.internal.measurement.zzgh r0 = r2.zzb(r0)
            com.google.android.gms.internal.measurement.zzik r0 = r0.zzc
            com.google.android.gms.internal.measurement.zzir r0 = r0.zza()
            com.google.android.gms.internal.measurement.zzir r2 = com.google.android.gms.internal.measurement.zzir.MESSAGE
            if (r0 != r2) goto L_0x00f3
            r0 = 0
            java.util.Collection r1 = r1.values()
            java.util.Iterator r1 = r1.iterator()
        L_0x00d0:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00f3
            java.lang.Object r2 = r1.next()
            if (r0 != 0) goto L_0x00e8
            com.google.android.gms.internal.measurement.zzhb r0 = com.google.android.gms.internal.measurement.zzhb.zza()
            java.lang.Class r5 = r2.getClass()
            com.google.android.gms.internal.measurement.zzhf r0 = r0.zza(r5)
        L_0x00e8:
            boolean r2 = r0.zzd(r2)
            if (r2 != 0) goto L_0x00d0
            r0 = r7
        L_0x00ef:
            if (r0 != 0) goto L_0x004b
            goto L_0x0040
        L_0x00f3:
            r0 = r9
            goto L_0x00ef
        L_0x00f5:
            boolean r0 = r14.zzh
            if (r0 == 0) goto L_0x0105
            com.google.android.gms.internal.measurement.zzet<?> r0 = r14.zzr
            com.google.android.gms.internal.measurement.zzeu r0 = r0.zza((java.lang.Object) r15)
            boolean r0 = r0.zzf()
            if (r0 == 0) goto L_0x0040
        L_0x0105:
            r7 = r9
            goto L_0x0040
        L_0x0108:
            r3 = r0
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgq.zzd(java.lang.Object):boolean");
    }

    private static boolean zza(Object obj, int i, zzhf zzhf) {
        return zzhf.zzd(zzid.zzf(obj, (long) (1048575 & i)));
    }

    private static void zza(int i, Object obj, zziq zziq) throws IOException {
        if (obj instanceof String) {
            zziq.zza(i, (String) obj);
        } else {
            zziq.zza(i, (zzdw) obj);
        }
    }

    private final void zza(Object obj, int i, zzhc zzhc) throws IOException {
        if (zzf(i)) {
            zzid.zza(obj, (long) (i & 1048575), (Object) zzhc.zzm());
        } else if (this.zzi) {
            zzid.zza(obj, (long) (i & 1048575), (Object) zzhc.zzl());
        } else {
            zzid.zza(obj, (long) (i & 1048575), (Object) zzhc.zzn());
        }
    }

    private final int zzd(int i) {
        return this.zzc[i + 1];
    }

    private final int zze(int i) {
        return this.zzc[i + 2];
    }

    private static boolean zzf(int i) {
        return (536870912 & i) != 0;
    }

    private static <T> double zzb(T t, long j) {
        return ((Double) zzid.zzf(t, j)).doubleValue();
    }

    private static <T> float zzc(T t, long j) {
        return ((Float) zzid.zzf(t, j)).floatValue();
    }

    private static <T> int zzd(T t, long j) {
        return ((Integer) zzid.zzf(t, j)).intValue();
    }

    private static <T> long zze(T t, long j) {
        return ((Long) zzid.zzf(t, j)).longValue();
    }

    private static <T> boolean zzf(T t, long j) {
        return ((Boolean) zzid.zzf(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zza(t, i);
        }
        return (i3 & i4) != 0;
    }

    private final boolean zza(T t, int i) {
        int zze2 = zze(i);
        if (((long) (zze2 & 1048575)) == 1048575) {
            int zzd2 = zzd(i);
            long j = (long) (zzd2 & 1048575);
            switch ((zzd2 & 267386880) >>> 20) {
                case 0:
                    if (zzid.zze(t, j) != 0.0d) {
                        return true;
                    }
                    return false;
                case 1:
                    return zzid.zzd(t, j) != 0.0f;
                case 2:
                    return zzid.zzb(t, j) != 0;
                case 3:
                    return zzid.zzb(t, j) != 0;
                case 4:
                    return zzid.zza((Object) t, j) != 0;
                case 5:
                    return zzid.zzb(t, j) != 0;
                case 6:
                    return zzid.zza((Object) t, j) != 0;
                case 7:
                    return zzid.zzc(t, j);
                case 8:
                    Object zzf2 = zzid.zzf(t, j);
                    if (zzf2 instanceof String) {
                        return !((String) zzf2).isEmpty();
                    }
                    if (zzf2 instanceof zzdw) {
                        return !zzdw.zza.equals(zzf2);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzid.zzf(t, j) != null;
                case 10:
                    return !zzdw.zza.equals(zzid.zzf(t, j));
                case 11:
                    return zzid.zza((Object) t, j) != 0;
                case 12:
                    return zzid.zza((Object) t, j) != 0;
                case 13:
                    return zzid.zza((Object) t, j) != 0;
                case 14:
                    return zzid.zzb(t, j) != 0;
                case 15:
                    return zzid.zza((Object) t, j) != 0;
                case 16:
                    return zzid.zzb(t, j) != 0;
                case 17:
                    return zzid.zzf(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            return (zzid.zza((Object) t, (long) (zze2 & 1048575)) & (1 << (zze2 >>> 20))) != 0;
        }
    }

    private final void zzb(T t, int i) {
        int zze2 = zze(i);
        long j = (long) (1048575 & zze2);
        if (j != 1048575) {
            zzid.zza((Object) t, j, (1 << (zze2 >>> 20)) | zzid.zza((Object) t, j));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzid.zza((Object) t, (long) (zze(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzid.zza((Object) t, (long) (zze(i2) & 1048575), i);
    }

    private final int zzg(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzb(i, 0);
    }

    private final int zza(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzb(i, i2);
    }

    private final int zzb(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }
}
