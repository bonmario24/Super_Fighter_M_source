package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzig;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
final class zzjv<T> implements zzkh<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzlf.zzc();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzjr zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final boolean zzk;
    private final int[] zzl;
    private final int zzm;
    private final int zzn;
    private final zzjw zzo;
    private final zzjb zzp;
    private final zzkz<?, ?> zzq;
    private final zzhv<?> zzr;
    private final zzjk zzs;

    private zzjv(int[] iArr, Object[] objArr, int i, int i2, zzjr zzjr, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzjw zzjw, zzjb zzjb, zzkz<?, ?> zzkz, zzhv<?> zzhv, zzjk zzjk) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzjr instanceof zzig;
        this.zzj = z;
        this.zzh = zzhv != null && zzhv.zza(zzjr);
        this.zzk = false;
        this.zzl = iArr2;
        this.zzm = i3;
        this.zzn = i4;
        this.zzo = zzjw;
        this.zzp = zzjb;
        this.zzq = zzkz;
        this.zzr = zzhv;
        this.zzg = zzjr;
        this.zzs = zzjk;
    }

    static <T> zzjv<T> zza(Class<T> cls, zzjp zzjp, zzjw zzjw, zzjb zzjb, zzkz<?, ?> zzkz, zzhv<?> zzhv, zzjk zzjk) {
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
        if (zzjp instanceof zzkf) {
            zzkf zzkf = (zzkf) zzjp;
            boolean z = zzkf.zza() == zzig.zze.zzi;
            String zzd2 = zzkf.zzd();
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
            Object[] zze2 = zzkf.zze();
            int i45 = 0;
            Class<?> cls2 = zzkf.zzc().getClass();
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
            return new zzjv<>(iArr2, objArr, c6, c, zzkf.zzc(), z, false, iArr, c4, c4 + c5, zzjw, zzjb, zzkz, zzhv, zzjk);
        }
        if (((zzks) zzjp).zza() == zzig.zze.zzi) {
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
            double r8 = com.google.android.gms.internal.firebase_auth.zzlf.zze(r12, r6)
            long r8 = java.lang.Double.doubleToLongBits(r8)
            double r6 = com.google.android.gms.internal.firebase_auth.zzlf.zze(r13, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0038:
            r2 = r0
            goto L_0x001b
        L_0x003a:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0052
            float r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzd(r12, r6)
            int r2 = java.lang.Float.floatToIntBits(r2)
            float r5 = com.google.android.gms.internal.firebase_auth.zzlf.zzd(r13, r6)
            int r5 = java.lang.Float.floatToIntBits(r5)
            if (r2 == r5) goto L_0x001a
        L_0x0052:
            r2 = r0
            goto L_0x001b
        L_0x0054:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0066
            long r8 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r12, r6)
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0066:
            r2 = r0
            goto L_0x001b
        L_0x0068:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x007a
            long r8 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r12, r6)
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x007a:
            r2 = r0
            goto L_0x001b
        L_0x007c:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x008c
            int r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r12, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r13, (long) r6)
            if (r2 == r5) goto L_0x001a
        L_0x008c:
            r2 = r0
            goto L_0x001b
        L_0x008e:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00a0
            long r8 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r12, r6)
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x00a0:
            r2 = r0
            goto L_0x001b
        L_0x00a3:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00b3
            int r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r12, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r13, (long) r6)
            if (r2 == r5) goto L_0x001a
        L_0x00b3:
            r2 = r0
            goto L_0x001b
        L_0x00b6:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00c6
            boolean r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzc(r12, r6)
            boolean r5 = com.google.android.gms.internal.firebase_auth.zzlf.zzc(r13, r6)
            if (r2 == r5) goto L_0x001a
        L_0x00c6:
            r2 = r0
            goto L_0x001b
        L_0x00c9:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00dd
            java.lang.Object r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r13, r6)
            boolean r2 = com.google.android.gms.internal.firebase_auth.zzkj.zza((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x00dd:
            r2 = r0
            goto L_0x001b
        L_0x00e0:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x00f4
            java.lang.Object r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r13, r6)
            boolean r2 = com.google.android.gms.internal.firebase_auth.zzkj.zza((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x00f4:
            r2 = r0
            goto L_0x001b
        L_0x00f7:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x010b
            java.lang.Object r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r13, r6)
            boolean r2 = com.google.android.gms.internal.firebase_auth.zzkj.zza((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x010b:
            r2 = r0
            goto L_0x001b
        L_0x010e:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x011e
            int r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r12, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r13, (long) r6)
            if (r2 == r5) goto L_0x001a
        L_0x011e:
            r2 = r0
            goto L_0x001b
        L_0x0121:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0131
            int r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r12, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r13, (long) r6)
            if (r2 == r5) goto L_0x001a
        L_0x0131:
            r2 = r0
            goto L_0x001b
        L_0x0134:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0144
            int r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r12, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r13, (long) r6)
            if (r2 == r5) goto L_0x001a
        L_0x0144:
            r2 = r0
            goto L_0x001b
        L_0x0147:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0159
            long r8 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r12, r6)
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0159:
            r2 = r0
            goto L_0x001b
        L_0x015c:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x016c
            int r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r12, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r13, (long) r6)
            if (r2 == r5) goto L_0x001a
        L_0x016c:
            r2 = r0
            goto L_0x001b
        L_0x016f:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0181
            long r8 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r12, r6)
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r13, r6)
            int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x001a
        L_0x0181:
            r2 = r0
            goto L_0x001b
        L_0x0184:
            boolean r2 = r11.zzc(r12, r13, r3)
            if (r2 == 0) goto L_0x0198
            java.lang.Object r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r13, r6)
            boolean r2 = com.google.android.gms.internal.firebase_auth.zzkj.zza((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x0198:
            r2 = r0
            goto L_0x001b
        L_0x019b:
            java.lang.Object r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r13, r6)
            boolean r2 = com.google.android.gms.internal.firebase_auth.zzkj.zza((java.lang.Object) r2, (java.lang.Object) r5)
            goto L_0x001b
        L_0x01a9:
            java.lang.Object r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r13, r6)
            boolean r2 = com.google.android.gms.internal.firebase_auth.zzkj.zza((java.lang.Object) r2, (java.lang.Object) r5)
            goto L_0x001b
        L_0x01b7:
            int r2 = r11.zze(r3)
            r5 = r2 & r10
            long r8 = (long) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r12, (long) r8)
            r2 = r2 & r10
            long r8 = (long) r2
            int r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r13, (long) r8)
            if (r5 != r2) goto L_0x01d8
            java.lang.Object r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r12, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r13, r6)
            boolean r2 = com.google.android.gms.internal.firebase_auth.zzkj.zza((java.lang.Object) r2, (java.lang.Object) r5)
            if (r2 != 0) goto L_0x001a
        L_0x01d8:
            r2 = r0
            goto L_0x001b
        L_0x01db:
            int r2 = r3 + 3
            r3 = r2
            goto L_0x0009
        L_0x01e0:
            com.google.android.gms.internal.firebase_auth.zzkz<?, ?> r2 = r11.zzq
            java.lang.Object r2 = r2.zzb(r12)
            com.google.android.gms.internal.firebase_auth.zzkz<?, ?> r3 = r11.zzq
            java.lang.Object r3 = r3.zzb(r13)
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x001d
            boolean r0 = r11.zzh
            if (r0 == 0) goto L_0x0208
            com.google.android.gms.internal.firebase_auth.zzhv<?> r0 = r11.zzr
            com.google.android.gms.internal.firebase_auth.zzhz r0 = r0.zza((java.lang.Object) r12)
            com.google.android.gms.internal.firebase_auth.zzhv<?> r1 = r11.zzr
            com.google.android.gms.internal.firebase_auth.zzhz r1 = r1.zza((java.lang.Object) r13)
            boolean r0 = r0.equals(r1)
            goto L_0x001d
        L_0x0208:
            r0 = r1
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjv.zza(java.lang.Object, java.lang.Object):boolean");
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
            double r6 = com.google.android.gms.internal.firebase_auth.zzlf.zze(r10, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r2 = com.google.android.gms.internal.firebase_auth.zzii.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0034:
            int r0 = r2 * 53
            float r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzd(r10, r6)
            int r2 = java.lang.Float.floatToIntBits(r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0040:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r10, r6)
            int r2 = com.google.android.gms.internal.firebase_auth.zzii.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x004c:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r10, r6)
            int r2 = com.google.android.gms.internal.firebase_auth.zzii.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0058:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r10, (long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0060:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r10, r6)
            int r2 = com.google.android.gms.internal.firebase_auth.zzii.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x006c:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r10, (long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0074:
            int r0 = r2 * 53
            boolean r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzc(r10, r6)
            int r2 = com.google.android.gms.internal.firebase_auth.zzii.zza((boolean) r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0080:
            int r2 = r2 * 53
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r10, r6)
            java.lang.String r0 = (java.lang.String) r0
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x008e:
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r10, r6)
            if (r0 == 0) goto L_0x0276
            int r0 = r0.hashCode()
        L_0x0098:
            int r2 = r2 * 53
            int r0 = r0 + r2
            goto L_0x0020
        L_0x009c:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00a9:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r10, (long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00b2:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r10, (long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00bb:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r10, (long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00c4:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r10, r6)
            int r2 = com.google.android.gms.internal.firebase_auth.zzii.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00d1:
            int r0 = r2 * 53
            int r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r10, (long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00da:
            int r0 = r2 * 53
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r10, r6)
            int r2 = com.google.android.gms.internal.firebase_auth.zzii.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00e7:
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r10, r6)
            if (r0 == 0) goto L_0x0273
            int r0 = r0.hashCode()
        L_0x00f1:
            int r2 = r2 * 53
            int r0 = r0 + r2
            goto L_0x0020
        L_0x00f6:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0103:
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r10, r6)
            int r2 = r2.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0110:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            double r6 = zzb(r10, (long) r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r2 = com.google.android.gms.internal.firebase_auth.zzii.zza((long) r6)
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
            int r2 = com.google.android.gms.internal.firebase_auth.zzii.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x014d:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            long r6 = zze(r10, r6)
            int r2 = com.google.android.gms.internal.firebase_auth.zzii.zza((long) r6)
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
            int r2 = com.google.android.gms.internal.firebase_auth.zzii.zza((long) r6)
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
            int r2 = com.google.android.gms.internal.firebase_auth.zzii.zza((boolean) r2)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01a4:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r2 = r2 * 53
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r10, r6)
            java.lang.String r0 = (java.lang.String) r0
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01b9:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r10, r6)
            int r2 = r2 * 53
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x01cc:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            int r0 = r2 * 53
            java.lang.Object r2 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r10, r6)
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
            int r2 = com.google.android.gms.internal.firebase_auth.zzii.zza((long) r6)
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
            int r2 = com.google.android.gms.internal.firebase_auth.zzii.zza((long) r6)
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0241:
            boolean r0 = r9.zza(r10, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x001f
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r10, r6)
            int r2 = r2 * 53
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            goto L_0x0020
        L_0x0254:
            int r0 = r2 * 53
            com.google.android.gms.internal.firebase_auth.zzkz<?, ?> r1 = r9.zzq
            java.lang.Object r1 = r1.zzb(r10)
            int r1 = r1.hashCode()
            int r0 = r0 + r1
            boolean r1 = r9.zzh
            if (r1 == 0) goto L_0x0272
            int r0 = r0 * 53
            com.google.android.gms.internal.firebase_auth.zzhv<?> r1 = r9.zzr
            com.google.android.gms.internal.firebase_auth.zzhz r1 = r1.zza((java.lang.Object) r10)
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjv.zza(java.lang.Object):int");
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
                        zzlf.zza((Object) t, j, zzlf.zze(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 1:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzlf.zza((Object) t, j, zzlf.zzd(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 2:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzlf.zza((Object) t, j, zzlf.zzb(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 3:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzlf.zza((Object) t, j, zzlf.zzb(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 4:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzlf.zza((Object) t, j, zzlf.zza((Object) t2, j));
                        zzb(t, i);
                        break;
                    }
                case 5:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzlf.zza((Object) t, j, zzlf.zzb(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 6:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzlf.zza((Object) t, j, zzlf.zza((Object) t2, j));
                        zzb(t, i);
                        break;
                    }
                case 7:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzlf.zza((Object) t, j, zzlf.zzc(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 8:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzlf.zza((Object) t, j, zzlf.zzf(t2, j));
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
                        zzlf.zza((Object) t, j, zzlf.zzf(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 11:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzlf.zza((Object) t, j, zzlf.zza((Object) t2, j));
                        zzb(t, i);
                        break;
                    }
                case 12:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzlf.zza((Object) t, j, zzlf.zza((Object) t2, j));
                        zzb(t, i);
                        break;
                    }
                case 13:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzlf.zza((Object) t, j, zzlf.zza((Object) t2, j));
                        zzb(t, i);
                        break;
                    }
                case 14:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzlf.zza((Object) t, j, zzlf.zzb(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 15:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzlf.zza((Object) t, j, zzlf.zza((Object) t2, j));
                        zzb(t, i);
                        break;
                    }
                case 16:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzlf.zza((Object) t, j, zzlf.zzb(t2, j));
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
                    zzkj.zza(this.zzs, t, t2, j);
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
                        zzlf.zza((Object) t, j, zzlf.zzf(t2, j));
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
                        zzlf.zza((Object) t, j, zzlf.zzf(t2, j));
                        zzb(t, i2, i);
                        break;
                    }
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        zzkj.zza(this.zzq, t, t2);
        if (this.zzh) {
            zzkj.zza(this.zzr, t, t2);
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzd2 = (long) (zzd(i) & 1048575);
        if (zza(t2, i)) {
            Object zzf2 = zzlf.zzf(t, zzd2);
            Object zzf3 = zzlf.zzf(t2, zzd2);
            if (zzf2 != null && zzf3 != null) {
                zzlf.zza((Object) t, zzd2, zzii.zza(zzf2, zzf3));
                zzb(t, i);
            } else if (zzf3 != null) {
                zzlf.zza((Object) t, zzd2, zzf3);
                zzb(t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzd2 = zzd(i);
        int i2 = this.zzc[i];
        long j = (long) (zzd2 & 1048575);
        if (zza(t2, i2, i)) {
            Object zzf2 = zzlf.zzf(t, j);
            Object zzf3 = zzlf.zzf(t2, j);
            if (zzf2 != null && zzf3 != null) {
                zzlf.zza((Object) t, j, zzii.zza(zzf2, zzf3));
                zzb(t, i2, i);
            } else if (zzf3 != null) {
                zzlf.zza((Object) t, j, zzf3);
                zzb(t, i2, i);
            }
        }
    }

    public final int zzd(T t) {
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
                if (i6 < zzia.DOUBLE_LIST_PACKED.zza() || i6 > zzia.SINT64_LIST_PACKED.zza()) {
                    i2 = 0;
                } else {
                    i2 = this.zzc[i5 + 2] & 1048575;
                }
                switch (i6) {
                    case 0:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzb(i7, 0.0d);
                            break;
                        }
                    case 1:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzb(i7, 0.0f);
                            break;
                        }
                    case 2:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzd(i7, zzlf.zzb(t, j));
                            break;
                        }
                    case 3:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zze(i7, zzlf.zzb(t, j));
                            break;
                        }
                    case 4:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzf(i7, zzlf.zza((Object) t, j));
                            break;
                        }
                    case 5:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzg(i7, 0);
                            break;
                        }
                    case 6:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzi(i7, 0);
                            break;
                        }
                    case 7:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzb(i7, true);
                            break;
                        }
                    case 8:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            Object zzf2 = zzlf.zzf(t, j);
                            if (!(zzf2 instanceof zzgv)) {
                                i3 += zzhq.zzb(i7, (String) zzf2);
                                break;
                            } else {
                                i3 += zzhq.zzc(i7, (zzgv) zzf2);
                                break;
                            }
                        }
                    case 9:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzkj.zza(i7, zzlf.zzf(t, j), zza(i5));
                            break;
                        }
                    case 10:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzc(i7, (zzgv) zzlf.zzf(t, j));
                            break;
                        }
                    case 11:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzg(i7, zzlf.zza((Object) t, j));
                            break;
                        }
                    case 12:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzk(i7, zzlf.zza((Object) t, j));
                            break;
                        }
                    case 13:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzj(i7, 0);
                            break;
                        }
                    case 14:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzh(i7, 0);
                            break;
                        }
                    case 15:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzh(i7, zzlf.zza((Object) t, j));
                            break;
                        }
                    case 16:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzf(i7, zzlf.zzb(t, j));
                            break;
                        }
                    case 17:
                        if (!zza(t, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzc(i7, (zzjr) zzlf.zzf(t, j), zza(i5));
                            break;
                        }
                    case 18:
                        i3 += zzkj.zzi(i7, zza((Object) t, j), false);
                        break;
                    case 19:
                        i3 += zzkj.zzh(i7, zza((Object) t, j), false);
                        break;
                    case 20:
                        i3 += zzkj.zza(i7, (List<Long>) zza((Object) t, j), false);
                        break;
                    case 21:
                        i3 += zzkj.zzb(i7, (List<Long>) zza((Object) t, j), false);
                        break;
                    case 22:
                        i3 += zzkj.zze(i7, zza((Object) t, j), false);
                        break;
                    case 23:
                        i3 += zzkj.zzi(i7, zza((Object) t, j), false);
                        break;
                    case 24:
                        i3 += zzkj.zzh(i7, zza((Object) t, j), false);
                        break;
                    case 25:
                        i3 += zzkj.zzj(i7, zza((Object) t, j), false);
                        break;
                    case 26:
                        i3 += zzkj.zza(i7, zza((Object) t, j));
                        break;
                    case 27:
                        i3 += zzkj.zza(i7, zza((Object) t, j), zza(i5));
                        break;
                    case 28:
                        i3 += zzkj.zzb(i7, zza((Object) t, j));
                        break;
                    case 29:
                        i3 += zzkj.zzf(i7, zza((Object) t, j), false);
                        break;
                    case 30:
                        i3 += zzkj.zzd(i7, zza((Object) t, j), false);
                        break;
                    case 31:
                        i3 += zzkj.zzh(i7, zza((Object) t, j), false);
                        break;
                    case 32:
                        i3 += zzkj.zzi(i7, zza((Object) t, j), false);
                        break;
                    case 33:
                        i3 += zzkj.zzg(i7, zza((Object) t, j), false);
                        break;
                    case 34:
                        i3 += zzkj.zzc(i7, zza((Object) t, j), false);
                        break;
                    case 35:
                        int zzi2 = zzkj.zzi((List) unsafe.getObject(t, j));
                        if (zzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzi2);
                            }
                            i3 += zzi2 + zzhq.zze(i7) + zzhq.zzg(zzi2);
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        int zzh2 = zzkj.zzh((List) unsafe.getObject(t, j));
                        if (zzh2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzh2);
                            }
                            i3 += zzh2 + zzhq.zze(i7) + zzhq.zzg(zzh2);
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        int zza2 = zzkj.zza((List<Long>) (List) unsafe.getObject(t, j));
                        if (zza2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zza2);
                            }
                            i3 += zza2 + zzhq.zze(i7) + zzhq.zzg(zza2);
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        int zzb2 = zzkj.zzb((List) unsafe.getObject(t, j));
                        if (zzb2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzb2);
                            }
                            i3 += zzb2 + zzhq.zze(i7) + zzhq.zzg(zzb2);
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        int zze2 = zzkj.zze((List) unsafe.getObject(t, j));
                        if (zze2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zze2);
                            }
                            i3 += zze2 + zzhq.zze(i7) + zzhq.zzg(zze2);
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        int zzi3 = zzkj.zzi((List) unsafe.getObject(t, j));
                        if (zzi3 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzi3);
                            }
                            i3 += zzi3 + zzhq.zze(i7) + zzhq.zzg(zzi3);
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        int zzh3 = zzkj.zzh((List) unsafe.getObject(t, j));
                        if (zzh3 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzh3);
                            }
                            i3 += zzh3 + zzhq.zze(i7) + zzhq.zzg(zzh3);
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        int zzj2 = zzkj.zzj((List) unsafe.getObject(t, j));
                        if (zzj2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzj2);
                            }
                            i3 += zzj2 + zzhq.zze(i7) + zzhq.zzg(zzj2);
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        int zzf3 = zzkj.zzf((List) unsafe.getObject(t, j));
                        if (zzf3 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzf3);
                            }
                            i3 += zzf3 + zzhq.zze(i7) + zzhq.zzg(zzf3);
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        int zzd3 = zzkj.zzd((List) unsafe.getObject(t, j));
                        if (zzd3 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzd3);
                            }
                            i3 += zzd3 + zzhq.zze(i7) + zzhq.zzg(zzd3);
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        int zzh4 = zzkj.zzh((List) unsafe.getObject(t, j));
                        if (zzh4 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzh4);
                            }
                            i3 += zzh4 + zzhq.zze(i7) + zzhq.zzg(zzh4);
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        int zzi4 = zzkj.zzi((List) unsafe.getObject(t, j));
                        if (zzi4 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzi4);
                            }
                            i3 += zzi4 + zzhq.zze(i7) + zzhq.zzg(zzi4);
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        int zzg2 = zzkj.zzg((List) unsafe.getObject(t, j));
                        if (zzg2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzg2);
                            }
                            i3 += zzg2 + zzhq.zze(i7) + zzhq.zzg(zzg2);
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        int zzc2 = zzkj.zzc((List) unsafe.getObject(t, j));
                        if (zzc2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, (long) i2, zzc2);
                            }
                            i3 += zzc2 + zzhq.zze(i7) + zzhq.zzg(zzc2);
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        i3 += zzkj.zzb(i7, (List<zzjr>) zza((Object) t, j), zza(i5));
                        break;
                    case 50:
                        i3 += this.zzs.zza(i7, zzlf.zzf(t, j), zzb(i5));
                        break;
                    case 51:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzb(i7, 0.0d);
                            break;
                        }
                    case 52:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzb(i7, 0.0f);
                            break;
                        }
                    case 53:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzd(i7, zze(t, j));
                            break;
                        }
                    case 54:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zze(i7, zze(t, j));
                            break;
                        }
                    case 55:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzf(i7, zzd(t, j));
                            break;
                        }
                    case 56:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzg(i7, 0);
                            break;
                        }
                    case 57:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzi(i7, 0);
                            break;
                        }
                    case 58:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzb(i7, true);
                            break;
                        }
                    case 59:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            Object zzf4 = zzlf.zzf(t, j);
                            if (!(zzf4 instanceof zzgv)) {
                                i3 += zzhq.zzb(i7, (String) zzf4);
                                break;
                            } else {
                                i3 += zzhq.zzc(i7, (zzgv) zzf4);
                                break;
                            }
                        }
                    case 60:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzkj.zza(i7, zzlf.zzf(t, j), zza(i5));
                            break;
                        }
                    case 61:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzc(i7, (zzgv) zzlf.zzf(t, j));
                            break;
                        }
                    case 62:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzg(i7, zzd(t, j));
                            break;
                        }
                    case 63:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzk(i7, zzd(t, j));
                            break;
                        }
                    case 64:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzj(i7, 0);
                            break;
                        }
                    case 65:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzh(i7, 0);
                            break;
                        }
                    case 66:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzh(i7, zzd(t, j));
                            break;
                        }
                    case 67:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzf(i7, zze(t, j));
                            break;
                        }
                    case 68:
                        if (!zza(t, i7, i5)) {
                            break;
                        } else {
                            i3 += zzhq.zzc(i7, (zzjr) zzlf.zzf(t, j), zza(i5));
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
                    } else if (!this.zzk || i14 < zzia.DOUBLE_LIST_PACKED.zza() || i14 > zzia.SINT64_LIST_PACKED.zza()) {
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
                                i8 += zzhq.zzb(i13, 0.0d);
                                break;
                            }
                        case 1:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zzb(i13, 0.0f);
                                break;
                            }
                        case 2:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zzd(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 3:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zze(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 4:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zzf(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 5:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zzg(i13, 0);
                                break;
                            }
                        case 6:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zzi(i13, 0);
                                break;
                            }
                        case 7:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zzb(i13, true);
                                break;
                            }
                        case 8:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                Object object = unsafe2.getObject(t, j2);
                                if (!(object instanceof zzgv)) {
                                    i8 += zzhq.zzb(i13, (String) object);
                                    break;
                                } else {
                                    i8 += zzhq.zzc(i13, (zzgv) object);
                                    break;
                                }
                            }
                        case 9:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzkj.zza(i13, unsafe2.getObject(t, j2), zza(i12));
                                break;
                            }
                        case 10:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zzc(i13, (zzgv) unsafe2.getObject(t, j2));
                                break;
                            }
                        case 11:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zzg(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 12:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zzk(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 13:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zzj(i13, 0);
                                break;
                            }
                        case 14:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zzh(i13, 0);
                                break;
                            }
                        case 15:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zzh(i13, unsafe2.getInt(t, j2));
                                break;
                            }
                        case 16:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zzf(i13, unsafe2.getLong(t, j2));
                                break;
                            }
                        case 17:
                            if ((i15 & i10) == 0) {
                                break;
                            } else {
                                i8 += zzhq.zzc(i13, (zzjr) unsafe2.getObject(t, j2), zza(i12));
                                break;
                            }
                        case 18:
                            i8 += zzkj.zzi(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 19:
                            i8 += zzkj.zzh(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 20:
                            i8 += zzkj.zza(i13, (List<Long>) (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 21:
                            i8 += zzkj.zzb(i13, (List<Long>) (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 22:
                            i8 += zzkj.zze(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 23:
                            i8 += zzkj.zzi(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 24:
                            i8 += zzkj.zzh(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 25:
                            i8 += zzkj.zzj(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 26:
                            i8 += zzkj.zza(i13, (List<?>) (List) unsafe2.getObject(t, j2));
                            break;
                        case 27:
                            i8 += zzkj.zza(i13, (List<?>) (List) unsafe2.getObject(t, j2), zza(i12));
                            break;
                        case 28:
                            i8 += zzkj.zzb(i13, (List) unsafe2.getObject(t, j2));
                            break;
                        case 29:
                            i8 += zzkj.zzf(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 30:
                            i8 += zzkj.zzd(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 31:
                            i8 += zzkj.zzh(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 32:
                            i8 += zzkj.zzi(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 33:
                            i8 += zzkj.zzg(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 34:
                            i8 += zzkj.zzc(i13, (List) unsafe2.getObject(t, j2), false);
                            break;
                        case 35:
                            int zzi5 = zzkj.zzi((List) unsafe2.getObject(t, j2));
                            if (zzi5 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzi5);
                                }
                                i8 += zzi5 + zzhq.zze(i13) + zzhq.zzg(zzi5);
                                break;
                            } else {
                                break;
                            }
                        case 36:
                            int zzh5 = zzkj.zzh((List) unsafe2.getObject(t, j2));
                            if (zzh5 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzh5);
                                }
                                i8 += zzh5 + zzhq.zze(i13) + zzhq.zzg(zzh5);
                                break;
                            } else {
                                break;
                            }
                        case 37:
                            int zza3 = zzkj.zza((List<Long>) (List) unsafe2.getObject(t, j2));
                            if (zza3 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zza3);
                                }
                                i8 += zza3 + zzhq.zze(i13) + zzhq.zzg(zza3);
                                break;
                            } else {
                                break;
                            }
                        case 38:
                            int zzb3 = zzkj.zzb((List) unsafe2.getObject(t, j2));
                            if (zzb3 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzb3);
                                }
                                i8 += zzb3 + zzhq.zze(i13) + zzhq.zzg(zzb3);
                                break;
                            } else {
                                break;
                            }
                        case 39:
                            int zze3 = zzkj.zze((List) unsafe2.getObject(t, j2));
                            if (zze3 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zze3);
                                }
                                i8 += zze3 + zzhq.zze(i13) + zzhq.zzg(zze3);
                                break;
                            } else {
                                break;
                            }
                        case 40:
                            int zzi6 = zzkj.zzi((List) unsafe2.getObject(t, j2));
                            if (zzi6 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzi6);
                                }
                                i8 += zzi6 + zzhq.zze(i13) + zzhq.zzg(zzi6);
                                break;
                            } else {
                                break;
                            }
                        case 41:
                            int zzh6 = zzkj.zzh((List) unsafe2.getObject(t, j2));
                            if (zzh6 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzh6);
                                }
                                i8 += zzh6 + zzhq.zze(i13) + zzhq.zzg(zzh6);
                                break;
                            } else {
                                break;
                            }
                        case 42:
                            int zzj3 = zzkj.zzj((List) unsafe2.getObject(t, j2));
                            if (zzj3 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzj3);
                                }
                                i8 += zzj3 + zzhq.zze(i13) + zzhq.zzg(zzj3);
                                break;
                            } else {
                                break;
                            }
                        case 43:
                            int zzf5 = zzkj.zzf((List) unsafe2.getObject(t, j2));
                            if (zzf5 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzf5);
                                }
                                i8 += zzf5 + zzhq.zze(i13) + zzhq.zzg(zzf5);
                                break;
                            } else {
                                break;
                            }
                        case 44:
                            int zzd5 = zzkj.zzd((List) unsafe2.getObject(t, j2));
                            if (zzd5 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzd5);
                                }
                                i8 += zzd5 + zzhq.zze(i13) + zzhq.zzg(zzd5);
                                break;
                            } else {
                                break;
                            }
                        case 45:
                            int zzh7 = zzkj.zzh((List) unsafe2.getObject(t, j2));
                            if (zzh7 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzh7);
                                }
                                i8 += zzh7 + zzhq.zze(i13) + zzhq.zzg(zzh7);
                                break;
                            } else {
                                break;
                            }
                        case 46:
                            int zzi7 = zzkj.zzi((List) unsafe2.getObject(t, j2));
                            if (zzi7 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzi7);
                                }
                                i8 += zzi7 + zzhq.zze(i13) + zzhq.zzg(zzi7);
                                break;
                            } else {
                                break;
                            }
                        case 47:
                            int zzg3 = zzkj.zzg((List) unsafe2.getObject(t, j2));
                            if (zzg3 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzg3);
                                }
                                i8 += zzg3 + zzhq.zze(i13) + zzhq.zzg(zzg3);
                                break;
                            } else {
                                break;
                            }
                        case 48:
                            int zzc3 = zzkj.zzc((List) unsafe2.getObject(t, j2));
                            if (zzc3 > 0) {
                                if (this.zzk) {
                                    unsafe2.putInt(t, (long) i, zzc3);
                                }
                                i8 += zzc3 + zzhq.zze(i13) + zzhq.zzg(zzc3);
                                break;
                            } else {
                                break;
                            }
                        case 49:
                            i8 += zzkj.zzb(i13, (List<zzjr>) (List) unsafe2.getObject(t, j2), zza(i12));
                            break;
                        case 50:
                            i8 += this.zzs.zza(i13, unsafe2.getObject(t, j2), zzb(i12));
                            break;
                        case 51:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzb(i13, 0.0d);
                                break;
                            }
                        case 52:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzb(i13, 0.0f);
                                break;
                            }
                        case 53:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzd(i13, zze(t, j2));
                                break;
                            }
                        case 54:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zze(i13, zze(t, j2));
                                break;
                            }
                        case 55:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzf(i13, zzd(t, j2));
                                break;
                            }
                        case 56:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzg(i13, 0);
                                break;
                            }
                        case 57:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzi(i13, 0);
                                break;
                            }
                        case 58:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzb(i13, true);
                                break;
                            }
                        case 59:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                Object object2 = unsafe2.getObject(t, j2);
                                if (!(object2 instanceof zzgv)) {
                                    i8 += zzhq.zzb(i13, (String) object2);
                                    break;
                                } else {
                                    i8 += zzhq.zzc(i13, (zzgv) object2);
                                    break;
                                }
                            }
                        case 60:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzkj.zza(i13, unsafe2.getObject(t, j2), zza(i12));
                                break;
                            }
                        case 61:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzc(i13, (zzgv) unsafe2.getObject(t, j2));
                                break;
                            }
                        case 62:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzg(i13, zzd(t, j2));
                                break;
                            }
                        case 63:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzk(i13, zzd(t, j2));
                                break;
                            }
                        case 64:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzj(i13, 0);
                                break;
                            }
                        case 65:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzh(i13, 0);
                                break;
                            }
                        case 66:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzh(i13, zzd(t, j2));
                                break;
                            }
                        case 67:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzf(i13, zze(t, j2));
                                break;
                            }
                        case 68:
                            if (!zza(t, i13, i12)) {
                                break;
                            } else {
                                i8 += zzhq.zzc(i13, (zzjr) unsafe2.getObject(t, j2), zza(i12));
                                break;
                            }
                    }
                    i11 = i12 + 3;
                } else {
                    int zza4 = i8 + zza(this.zzq, t);
                    if (!this.zzh) {
                        return zza4;
                    }
                    zzhz<?> zza5 = this.zzr.zza((Object) t);
                    int i18 = 0;
                    int i19 = 0;
                    while (true) {
                        int i20 = i19;
                        if (i20 < zza5.zza.zzc()) {
                            Map.Entry<T, Object> zzb4 = zza5.zza.zzb(i20);
                            i18 += zzhz.zza((zzib<?>) (zzib) zzb4.getKey(), zzb4.getValue());
                            i19 = i20 + 1;
                        } else {
                            for (Map.Entry next : zza5.zza.zzd()) {
                                i18 += zzhz.zza((zzib<?>) (zzib) next.getKey(), next.getValue());
                            }
                            return zza4 + i18;
                        }
                    }
                }
            }
        }
    }

    private static <UT, UB> int zza(zzkz<UT, UB> zzkz, T t) {
        return zzkz.zzf(zzkz.zzb(t));
    }

    private static List<?> zza(Object obj, long j) {
        return (List) zzlf.zzf(obj, j);
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 547 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r11, com.google.android.gms.internal.firebase_auth.zzlw r12) throws java.io.IOException {
        /*
            r10 = this;
            int r0 = r12.zza()
            int r1 = com.google.android.gms.internal.firebase_auth.zzig.zze.zzk
            if (r0 != r1) goto L_0x060c
            com.google.android.gms.internal.firebase_auth.zzkz<?, ?> r0 = r10.zzq
            zza(r0, r11, (com.google.android.gms.internal.firebase_auth.zzlw) r12)
            r1 = 0
            r0 = 0
            boolean r2 = r10.zzh
            if (r2 == 0) goto L_0x002b
            com.google.android.gms.internal.firebase_auth.zzhv<?> r2 = r10.zzr
            com.google.android.gms.internal.firebase_auth.zzhz r2 = r2.zza((java.lang.Object) r11)
            com.google.android.gms.internal.firebase_auth.zzki<T, java.lang.Object> r3 = r2.zza
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
            com.google.android.gms.internal.firebase_auth.zzhv<?> r0 = r10.zzr
            int r0 = r0.zza((java.util.Map.Entry<?, ?>) r2)
            if (r0 <= r5) goto L_0x005b
            com.google.android.gms.internal.firebase_auth.zzhv<?> r0 = r10.zzr
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
            double r6 = com.google.android.gms.internal.firebase_auth.zzlf.zze(r11, r6)
            r12.zza((int) r5, (double) r6)
            goto L_0x0063
        L_0x007a:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            float r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzd(r11, r6)
            r12.zza((int) r5, (float) r0)
            goto L_0x0063
        L_0x008d:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r11, r6)
            r12.zza((int) r5, (long) r6)
            goto L_0x0063
        L_0x00a0:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r11, r6)
            r12.zzc((int) r5, (long) r6)
            goto L_0x0063
        L_0x00b3:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r11, (long) r6)
            r12.zzc((int) r5, (int) r0)
            goto L_0x0063
        L_0x00c6:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r11, r6)
            r12.zzd((int) r5, (long) r6)
            goto L_0x0063
        L_0x00d9:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r11, (long) r6)
            r12.zzd((int) r5, (int) r0)
            goto L_0x0063
        L_0x00ed:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            boolean r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzc(r11, r6)
            r12.zza((int) r5, (boolean) r0)
            goto L_0x0063
        L_0x0101:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12)
            goto L_0x0063
        L_0x0115:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            com.google.android.gms.internal.firebase_auth.zzkh r4 = r10.zza((int) r3)
            r12.zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.firebase_auth.zzkh) r4)
            goto L_0x0063
        L_0x012d:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            com.google.android.gms.internal.firebase_auth.zzgv r0 = (com.google.android.gms.internal.firebase_auth.zzgv) r0
            r12.zza((int) r5, (com.google.android.gms.internal.firebase_auth.zzgv) r0)
            goto L_0x0063
        L_0x0143:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r11, (long) r6)
            r12.zze((int) r5, (int) r0)
            goto L_0x0063
        L_0x0157:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r11, (long) r6)
            r12.zzb((int) r5, (int) r0)
            goto L_0x0063
        L_0x016b:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r11, (long) r6)
            r12.zza((int) r5, (int) r0)
            goto L_0x0063
        L_0x017f:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r11, r6)
            r12.zzb((int) r5, (long) r6)
            goto L_0x0063
        L_0x0193:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            int r0 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r11, (long) r6)
            r12.zzf(r5, r0)
            goto L_0x0063
        L_0x01a7:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            long r6 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r11, r6)
            r12.zze((int) r5, (long) r6)
            goto L_0x0063
        L_0x01bb:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            com.google.android.gms.internal.firebase_auth.zzkh r4 = r10.zza((int) r3)
            r12.zzb((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.firebase_auth.zzkh) r4)
            goto L_0x0063
        L_0x01d3:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zza((int) r5, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12, (boolean) r4)
            goto L_0x0063
        L_0x01e8:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzb((int) r5, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12, (boolean) r4)
            goto L_0x0063
        L_0x01fd:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzc(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0212:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzd(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0227:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzh(r5, r0, r12, r4)
            goto L_0x0063
        L_0x023c:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzf(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0251:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzk(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0266:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzn(r5, r0, r12, r4)
            goto L_0x0063
        L_0x027b:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.firebase_auth.zzkj.zza((int) r5, (java.util.List<java.lang.String>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12)
            goto L_0x0063
        L_0x028f:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.firebase_auth.zzkh r4 = r10.zza((int) r3)
            com.google.android.gms.internal.firebase_auth.zzkj.zza((int) r5, (java.util.List<?>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12, (com.google.android.gms.internal.firebase_auth.zzkh) r4)
            goto L_0x0063
        L_0x02a7:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.firebase_auth.zzkj.zzb((int) r5, (java.util.List<com.google.android.gms.internal.firebase_auth.zzgv>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12)
            goto L_0x0063
        L_0x02bb:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzi(r5, r0, r12, r4)
            goto L_0x0063
        L_0x02d0:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzm(r5, r0, r12, r4)
            goto L_0x0063
        L_0x02e5:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzl(r5, r0, r12, r4)
            goto L_0x0063
        L_0x02fa:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzg(r5, r0, r12, r4)
            goto L_0x0063
        L_0x030f:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzj(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0324:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zze(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0339:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zza((int) r5, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12, (boolean) r4)
            goto L_0x0063
        L_0x034e:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzb((int) r5, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12, (boolean) r4)
            goto L_0x0063
        L_0x0363:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzc(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0378:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzd(r5, r0, r12, r4)
            goto L_0x0063
        L_0x038d:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzh(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03a2:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzf(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03b7:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzk(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03cc:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzn(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03e1:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzi(r5, r0, r12, r4)
            goto L_0x0063
        L_0x03f6:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzm(r5, r0, r12, r4)
            goto L_0x0063
        L_0x040b:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzl(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0420:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzg(r5, r0, r12, r4)
            goto L_0x0063
        L_0x0435:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzj(r5, r0, r12, r4)
            goto L_0x0063
        L_0x044a:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            r4 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zze(r5, r0, r12, r4)
            goto L_0x0063
        L_0x045f:
            int[] r0 = r10.zzc
            r5 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.firebase_auth.zzkh r4 = r10.zza((int) r3)
            com.google.android.gms.internal.firebase_auth.zzkj.zzb((int) r5, (java.util.List<?>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12, (com.google.android.gms.internal.firebase_auth.zzkh) r4)
            goto L_0x0063
        L_0x0477:
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            r10.zza((com.google.android.gms.internal.firebase_auth.zzlw) r12, (int) r5, (java.lang.Object) r0, (int) r3)
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
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12)
            goto L_0x0063
        L_0x0539:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            com.google.android.gms.internal.firebase_auth.zzkh r4 = r10.zza((int) r3)
            r12.zza((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.firebase_auth.zzkh) r4)
            goto L_0x0063
        L_0x0551:
            boolean r0 = r10.zza(r11, (int) r5, (int) r3)
            if (r0 == 0) goto L_0x0063
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r4
            long r6 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            com.google.android.gms.internal.firebase_auth.zzgv r0 = (com.google.android.gms.internal.firebase_auth.zzgv) r0
            r12.zza((int) r5, (com.google.android.gms.internal.firebase_auth.zzgv) r0)
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
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r6)
            com.google.android.gms.internal.firebase_auth.zzkh r4 = r10.zza((int) r3)
            r12.zzb((int) r5, (java.lang.Object) r0, (com.google.android.gms.internal.firebase_auth.zzkh) r4)
            goto L_0x0063
        L_0x05f7:
            r0 = 0
        L_0x05f8:
            if (r0 == 0) goto L_0x0c13
            com.google.android.gms.internal.firebase_auth.zzhv<?> r2 = r10.zzr
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
            com.google.android.gms.internal.firebase_auth.zzhv<?> r2 = r10.zzr
            com.google.android.gms.internal.firebase_auth.zzhz r2 = r2.zza((java.lang.Object) r11)
            com.google.android.gms.internal.firebase_auth.zzki<T, java.lang.Object> r3 = r2.zza
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
            com.google.android.gms.internal.firebase_auth.zzhv<?> r0 = r10.zzr
            int r0 = r0.zza((java.util.Map.Entry<?, ?>) r2)
            if (r0 > r6) goto L_0x065d
            com.google.android.gms.internal.firebase_auth.zzhv<?> r0 = r10.zzr
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
            double r8 = com.google.android.gms.internal.firebase_auth.zzlf.zze(r11, r8)
            r12.zza((int) r6, (double) r8)
            goto L_0x0665
        L_0x067c:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            float r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzd(r11, r8)
            r12.zza((int) r6, (float) r0)
            goto L_0x0665
        L_0x068f:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r11, r8)
            r12.zza((int) r6, (long) r8)
            goto L_0x0665
        L_0x06a2:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r11, r8)
            r12.zzc((int) r6, (long) r8)
            goto L_0x0665
        L_0x06b5:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r11, (long) r8)
            r12.zzc((int) r6, (int) r0)
            goto L_0x0665
        L_0x06c8:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r11, r8)
            r12.zzd((int) r6, (long) r8)
            goto L_0x0665
        L_0x06db:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r11, (long) r8)
            r12.zzd((int) r6, (int) r0)
            goto L_0x0665
        L_0x06ef:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            boolean r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzc(r11, r8)
            r12.zza((int) r6, (boolean) r0)
            goto L_0x0665
        L_0x0703:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12)
            goto L_0x0665
        L_0x0717:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            com.google.android.gms.internal.firebase_auth.zzkh r5 = r10.zza((int) r3)
            r12.zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.firebase_auth.zzkh) r5)
            goto L_0x0665
        L_0x072f:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            com.google.android.gms.internal.firebase_auth.zzgv r0 = (com.google.android.gms.internal.firebase_auth.zzgv) r0
            r12.zza((int) r6, (com.google.android.gms.internal.firebase_auth.zzgv) r0)
            goto L_0x0665
        L_0x0745:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r11, (long) r8)
            r12.zze((int) r6, (int) r0)
            goto L_0x0665
        L_0x0759:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r11, (long) r8)
            r12.zzb((int) r6, (int) r0)
            goto L_0x0665
        L_0x076d:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r11, (long) r8)
            r12.zza((int) r6, (int) r0)
            goto L_0x0665
        L_0x0781:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r11, r8)
            r12.zzb((int) r6, (long) r8)
            goto L_0x0665
        L_0x0795:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            int r0 = com.google.android.gms.internal.firebase_auth.zzlf.zza((java.lang.Object) r11, (long) r8)
            r12.zzf(r6, r0)
            goto L_0x0665
        L_0x07a9:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            long r8 = com.google.android.gms.internal.firebase_auth.zzlf.zzb(r11, r8)
            r12.zze((int) r6, (long) r8)
            goto L_0x0665
        L_0x07bd:
            boolean r0 = r10.zza(r11, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            com.google.android.gms.internal.firebase_auth.zzkh r5 = r10.zza((int) r3)
            r12.zzb((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.firebase_auth.zzkh) r5)
            goto L_0x0665
        L_0x07d5:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zza((int) r6, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12, (boolean) r5)
            goto L_0x0665
        L_0x07ea:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzb((int) r6, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12, (boolean) r5)
            goto L_0x0665
        L_0x07ff:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzc(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0814:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzd(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0829:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzh(r6, r0, r12, r5)
            goto L_0x0665
        L_0x083e:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzf(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0853:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzk(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0868:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzn(r6, r0, r12, r5)
            goto L_0x0665
        L_0x087d:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.firebase_auth.zzkj.zza((int) r6, (java.util.List<java.lang.String>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12)
            goto L_0x0665
        L_0x0891:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.firebase_auth.zzkh r5 = r10.zza((int) r3)
            com.google.android.gms.internal.firebase_auth.zzkj.zza((int) r6, (java.util.List<?>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12, (com.google.android.gms.internal.firebase_auth.zzkh) r5)
            goto L_0x0665
        L_0x08a9:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.firebase_auth.zzkj.zzb((int) r6, (java.util.List<com.google.android.gms.internal.firebase_auth.zzgv>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12)
            goto L_0x0665
        L_0x08bd:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzi(r6, r0, r12, r5)
            goto L_0x0665
        L_0x08d2:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzm(r6, r0, r12, r5)
            goto L_0x0665
        L_0x08e7:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzl(r6, r0, r12, r5)
            goto L_0x0665
        L_0x08fc:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzg(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0911:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zzj(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0926:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 0
            com.google.android.gms.internal.firebase_auth.zzkj.zze(r6, r0, r12, r5)
            goto L_0x0665
        L_0x093b:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zza((int) r6, (java.util.List<java.lang.Double>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12, (boolean) r5)
            goto L_0x0665
        L_0x0950:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzb((int) r6, (java.util.List<java.lang.Float>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12, (boolean) r5)
            goto L_0x0665
        L_0x0965:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzc(r6, r0, r12, r5)
            goto L_0x0665
        L_0x097a:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzd(r6, r0, r12, r5)
            goto L_0x0665
        L_0x098f:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzh(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09a4:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzf(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09b9:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzk(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09ce:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzn(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09e3:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzi(r6, r0, r12, r5)
            goto L_0x0665
        L_0x09f8:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzm(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a0d:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzl(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a22:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzg(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a37:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zzj(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a4c:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            r5 = 1
            com.google.android.gms.internal.firebase_auth.zzkj.zze(r6, r0, r12, r5)
            goto L_0x0665
        L_0x0a61:
            int[] r0 = r10.zzc
            r6 = r0[r3]
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.firebase_auth.zzkh r5 = r10.zza((int) r3)
            com.google.android.gms.internal.firebase_auth.zzkj.zzb((int) r6, (java.util.List<?>) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12, (com.google.android.gms.internal.firebase_auth.zzkh) r5)
            goto L_0x0665
        L_0x0a79:
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            r10.zza((com.google.android.gms.internal.firebase_auth.zzlw) r12, (int) r6, (java.lang.Object) r0, (int) r3)
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
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.firebase_auth.zzlw) r12)
            goto L_0x0665
        L_0x0b3b:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            com.google.android.gms.internal.firebase_auth.zzkh r5 = r10.zza((int) r3)
            r12.zza((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.firebase_auth.zzkh) r5)
            goto L_0x0665
        L_0x0b53:
            boolean r0 = r10.zza(r11, (int) r6, (int) r3)
            if (r0 == 0) goto L_0x0665
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r8 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            com.google.android.gms.internal.firebase_auth.zzgv r0 = (com.google.android.gms.internal.firebase_auth.zzgv) r0
            r12.zza((int) r6, (com.google.android.gms.internal.firebase_auth.zzgv) r0)
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
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r11, r8)
            com.google.android.gms.internal.firebase_auth.zzkh r5 = r10.zza((int) r3)
            r12.zzb((int) r6, (java.lang.Object) r0, (com.google.android.gms.internal.firebase_auth.zzkh) r5)
            goto L_0x0665
        L_0x0bf9:
            r0 = 0
        L_0x0bfa:
            if (r0 == 0) goto L_0x0c0e
            com.google.android.gms.internal.firebase_auth.zzhv<?> r2 = r10.zzr
            r2.zza(r12, r0)
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x0bf9
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            goto L_0x0bfa
        L_0x0c0e:
            com.google.android.gms.internal.firebase_auth.zzkz<?, ?> r0 = r10.zzq
            zza(r0, r11, (com.google.android.gms.internal.firebase_auth.zzlw) r12)
        L_0x0c13:
            return
        L_0x0c14:
            r10.zzb(r11, (com.google.android.gms.internal.firebase_auth.zzlw) r12)
            goto L_0x0c13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjv.zza(java.lang.Object, com.google.android.gms.internal.firebase_auth.zzlw):void");
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 387 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r21, com.google.android.gms.internal.firebase_auth.zzlw r22) throws java.io.IOException {
        /*
            r20 = this;
            r5 = 0
            r4 = 0
            r0 = r20
            boolean r6 = r0.zzh
            if (r6 == 0) goto L_0x0024
            r0 = r20
            com.google.android.gms.internal.firebase_auth.zzhv<?> r6 = r0.zzr
            r0 = r21
            com.google.android.gms.internal.firebase_auth.zzhz r6 = r6.zza((java.lang.Object) r0)
            com.google.android.gms.internal.firebase_auth.zzki<T, java.lang.Object> r7 = r6.zza
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
            com.google.android.gms.internal.firebase_auth.zzhv<?> r4 = r0.zzr
            int r4 = r4.zza((java.util.Map.Entry<?, ?>) r9)
            r0 = r16
            if (r4 > r0) goto L_0x009a
            r0 = r20
            com.google.android.gms.internal.firebase_auth.zzhv<?> r4 = r0.zzr
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
            double r6 = com.google.android.gms.internal.firebase_auth.zzlf.zze(r0, r6)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (double) r6)
            goto L_0x00a2
        L_0x00b9:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            float r4 = com.google.android.gms.internal.firebase_auth.zzlf.zzd(r0, r6)
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
            boolean r4 = com.google.android.gms.internal.firebase_auth.zzlf.zzc(r0, r6)
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
            zza((int) r0, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_auth.zzlw) r1)
            goto L_0x00a2
        L_0x014c:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            com.google.android.gms.internal.firebase_auth.zzkh r6 = r0.zza((int) r12)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_auth.zzkh) r6)
            goto L_0x00a2
        L_0x0165:
            r4 = r11 & r10
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            com.google.android.gms.internal.firebase_auth.zzgv r4 = (com.google.android.gms.internal.firebase_auth.zzgv) r4
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (com.google.android.gms.internal.firebase_auth.zzgv) r4)
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
            com.google.android.gms.internal.firebase_auth.zzkh r6 = r0.zza((int) r12)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_auth.zzkh) r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zza((int) r10, (java.util.List<java.lang.Double>) r4, (com.google.android.gms.internal.firebase_auth.zzlw) r0, (boolean) r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzb((int) r10, (java.util.List<java.lang.Float>) r4, (com.google.android.gms.internal.firebase_auth.zzlw) r0, (boolean) r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzc(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzd(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzh(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzf(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzk(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzn(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x02b5:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r22
            com.google.android.gms.internal.firebase_auth.zzkj.zza((int) r10, (java.util.List<java.lang.String>) r4, (com.google.android.gms.internal.firebase_auth.zzlw) r0)
            goto L_0x00a2
        L_0x02ca:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r20
            com.google.android.gms.internal.firebase_auth.zzkh r6 = r0.zza((int) r12)
            r0 = r22
            com.google.android.gms.internal.firebase_auth.zzkj.zza((int) r10, (java.util.List<?>) r4, (com.google.android.gms.internal.firebase_auth.zzlw) r0, (com.google.android.gms.internal.firebase_auth.zzkh) r6)
            goto L_0x00a2
        L_0x02e5:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r22
            com.google.android.gms.internal.firebase_auth.zzkj.zzb((int) r10, (java.util.List<com.google.android.gms.internal.firebase_auth.zzgv>) r4, (com.google.android.gms.internal.firebase_auth.zzlw) r0)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzi(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzm(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzl(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzg(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzj(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zze(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zza((int) r10, (java.util.List<java.lang.Double>) r4, (com.google.android.gms.internal.firebase_auth.zzlw) r0, (boolean) r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzb((int) r10, (java.util.List<java.lang.Float>) r4, (com.google.android.gms.internal.firebase_auth.zzlw) r0, (boolean) r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzc(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzd(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzh(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzf(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzk(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzn(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzi(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzm(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzl(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzg(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zzj(r10, r4, r0, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkj.zze(r10, r4, r0, r6)
            goto L_0x00a2
        L_0x04b2:
            r0 = r20
            int[] r4 = r0.zzc
            r10 = r4[r12]
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            java.util.List r4 = (java.util.List) r4
            r0 = r20
            com.google.android.gms.internal.firebase_auth.zzkh r6 = r0.zza((int) r12)
            r0 = r22
            com.google.android.gms.internal.firebase_auth.zzkj.zzb((int) r10, (java.util.List<?>) r4, (com.google.android.gms.internal.firebase_auth.zzlw) r0, (com.google.android.gms.internal.firebase_auth.zzkh) r6)
            goto L_0x00a2
        L_0x04cd:
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            r0 = r20
            r1 = r22
            r2 = r16
            r0.zza((com.google.android.gms.internal.firebase_auth.zzlw) r1, (int) r2, (java.lang.Object) r4, (int) r12)
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
            zza((int) r0, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_auth.zzlw) r1)
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
            com.google.android.gms.internal.firebase_auth.zzkh r6 = r0.zza((int) r12)
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_auth.zzkh) r6)
            goto L_0x00a2
        L_0x05f2:
            r0 = r20
            r1 = r21
            r2 = r16
            boolean r4 = r0.zza(r1, (int) r2, (int) r12)
            if (r4 == 0) goto L_0x00a2
            r0 = r21
            java.lang.Object r4 = r14.getObject(r0, r6)
            com.google.android.gms.internal.firebase_auth.zzgv r4 = (com.google.android.gms.internal.firebase_auth.zzgv) r4
            r0 = r22
            r1 = r16
            r0.zza((int) r1, (com.google.android.gms.internal.firebase_auth.zzgv) r4)
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
            com.google.android.gms.internal.firebase_auth.zzkh r6 = r0.zza((int) r12)
            r0 = r22
            r1 = r16
            r0.zzb((int) r1, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_auth.zzkh) r6)
            goto L_0x00a2
        L_0x06d2:
            r4 = 0
        L_0x06d3:
            if (r4 == 0) goto L_0x06eb
            r0 = r20
            com.google.android.gms.internal.firebase_auth.zzhv<?> r6 = r0.zzr
            r0 = r22
            r6.zza(r0, r4)
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L_0x06d2
            java.lang.Object r4 = r5.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            goto L_0x06d3
        L_0x06eb:
            r0 = r20
            com.google.android.gms.internal.firebase_auth.zzkz<?, ?> r4 = r0.zzq
            r0 = r21
            r1 = r22
            zza(r4, r0, (com.google.android.gms.internal.firebase_auth.zzlw) r1)
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjv.zzb(java.lang.Object, com.google.android.gms.internal.firebase_auth.zzlw):void");
    }

    private final <K, V> void zza(zzlw zzlw, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzlw.zza(i, this.zzs.zzf(zzb(i2)), this.zzs.zzb(obj));
        }
    }

    private static <UT, UB> void zza(zzkz<UT, UB> zzkz, T t, zzlw zzlw) throws IOException {
        zzkz.zza(zzkz.zzb(t), zzlw);
    }

    public final void zza(T t, zzke zzke, zzht zzht) throws IOException {
        Throwable th;
        Object obj;
        int i;
        Object obj2;
        Object obj3;
        Object obj4;
        Object zza2;
        if (zzht == null) {
            throw new NullPointerException();
        }
        zzkz<?, ?> zzkz = this.zzq;
        zzhv<?> zzhv = this.zzr;
        Object obj5 = null;
        zzhz<?> zzhz = null;
        while (true) {
            int zza3 = zzke.zza();
            if (zza3 < this.zze || zza3 > this.zzf) {
                i = -1;
            } else {
                int i2 = 0;
                int length = (this.zzc.length / 3) - 1;
                while (true) {
                    if (i2 > length) {
                        i = -1;
                    } else {
                        int i3 = (length + i2) >>> 1;
                        i = i3 * 3;
                        int i4 = this.zzc[i];
                        if (zza3 != i4) {
                            if (zza3 < i4) {
                                length = i3 - 1;
                            } else {
                                i2 = i3 + 1;
                            }
                        }
                    }
                }
            }
            if (i >= 0) {
                int zzd2 = zzd(i);
                switch ((267386880 & zzd2) >>> 20) {
                    case 0:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zzd());
                        zzb(t, i);
                        continue;
                    case 1:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zze());
                        zzb(t, i);
                        continue;
                    case 2:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zzg());
                        zzb(t, i);
                        continue;
                    case 3:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zzf());
                        zzb(t, i);
                        continue;
                    case 4:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zzh());
                        zzb(t, i);
                        continue;
                    case 5:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zzi());
                        zzb(t, i);
                        continue;
                    case 6:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zzj());
                        zzb(t, i);
                        continue;
                    case 7:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zzk());
                        zzb(t, i);
                        continue;
                    case 8:
                        zza((Object) t, zzd2, zzke);
                        zzb(t, i);
                        continue;
                    case 9:
                        if (!zza(t, i)) {
                            zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zza(zza(i), zzht));
                            zzb(t, i);
                            break;
                        } else {
                            zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzii.zza(zzlf.zzf(t, (long) (1048575 & zzd2)), zzke.zza(zza(i), zzht)));
                            continue;
                        }
                    case 10:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) zzke.zzn());
                        zzb(t, i);
                        continue;
                    case 11:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zzo());
                        zzb(t, i);
                        continue;
                    case 12:
                        int zzp2 = zzke.zzp();
                        zzin zzc2 = zzc(i);
                        if (zzc2 != null && !zzc2.zza(zzp2)) {
                            obj5 = zzkj.zza(zza3, zzp2, obj5, zzkz);
                            break;
                        } else {
                            zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzp2);
                            zzb(t, i);
                            continue;
                        }
                        break;
                    case 13:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zzq());
                        zzb(t, i);
                        continue;
                    case 14:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zzr());
                        zzb(t, i);
                        continue;
                    case 15:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zzs());
                        zzb(t, i);
                        continue;
                    case 16:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zzt());
                        zzb(t, i);
                        continue;
                    case 17:
                        if (!zza(t, i)) {
                            zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zzb(zza(i), zzht));
                            zzb(t, i);
                            break;
                        } else {
                            zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzii.zza(zzlf.zzf(t, (long) (1048575 & zzd2)), zzke.zzb(zza(i), zzht)));
                            continue;
                        }
                    case 18:
                        zzke.zza(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 19:
                        zzke.zzb(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 20:
                        zzke.zzd(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 21:
                        zzke.zzc(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 22:
                        zzke.zze(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 23:
                        zzke.zzf(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 24:
                        zzke.zzg(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 25:
                        zzke.zzh(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 26:
                        if (!zzf(zzd2)) {
                            zzke.zzi(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                            break;
                        } else {
                            zzke.zzj(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                            continue;
                        }
                    case 27:
                        zzke.zza(this.zzp.zza(t, (long) (zzd2 & 1048575)), zza(i), zzht);
                        continue;
                    case 28:
                        zzke.zzk(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 29:
                        zzke.zzl(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 30:
                        List zza4 = this.zzp.zza(t, (long) (zzd2 & 1048575));
                        zzke.zzm(zza4);
                        obj5 = zzkj.zza(zza3, zza4, zzc(i), obj5, zzkz);
                        continue;
                    case 31:
                        zzke.zzn(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 32:
                        zzke.zzo(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 33:
                        zzke.zzp(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 34:
                        zzke.zzq(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 35:
                        zzke.zza(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 36:
                        zzke.zzb(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 37:
                        zzke.zzd(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 38:
                        zzke.zzc(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 39:
                        zzke.zze(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 40:
                        zzke.zzf(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 41:
                        zzke.zzg(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 42:
                        zzke.zzh(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 43:
                        zzke.zzl(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 44:
                        List zza5 = this.zzp.zza(t, (long) (zzd2 & 1048575));
                        zzke.zzm(zza5);
                        obj5 = zzkj.zza(zza3, zza5, zzc(i), obj5, zzkz);
                        continue;
                    case 45:
                        zzke.zzn(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 46:
                        zzke.zzo(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 47:
                        zzke.zzp(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 48:
                        zzke.zzq(this.zzp.zza(t, (long) (zzd2 & 1048575)));
                        continue;
                    case 49:
                        zzke.zzb(this.zzp.zza(t, (long) (zzd2 & 1048575)), zza(i), zzht);
                        continue;
                    case 50:
                        Object zzb2 = zzb(i);
                        long zzd3 = (long) (zzd(i) & 1048575);
                        Object zzf2 = zzlf.zzf(t, zzd3);
                        if (zzf2 == null) {
                            obj2 = this.zzs.zze(zzb2);
                            zzlf.zza((Object) t, zzd3, obj2);
                        } else if (this.zzs.zzc(zzf2)) {
                            obj2 = this.zzs.zze(zzb2);
                            this.zzs.zza(obj2, zzf2);
                            zzlf.zza((Object) t, zzd3, obj2);
                        } else {
                            obj2 = zzf2;
                        }
                        zzke.zza(this.zzs.zza(obj2), this.zzs.zzf(zzb2), zzht);
                        continue;
                    case 51:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) Double.valueOf(zzke.zzd()));
                        zzb(t, zza3, i);
                        continue;
                    case 52:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) Float.valueOf(zzke.zze()));
                        zzb(t, zza3, i);
                        continue;
                    case 53:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzke.zzg()));
                        zzb(t, zza3, i);
                        continue;
                    case 54:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzke.zzf()));
                        zzb(t, zza3, i);
                        continue;
                    case 55:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzke.zzh()));
                        zzb(t, zza3, i);
                        continue;
                    case 56:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzke.zzi()));
                        zzb(t, zza3, i);
                        continue;
                    case 57:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzke.zzj()));
                        zzb(t, zza3, i);
                        continue;
                    case 58:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) Boolean.valueOf(zzke.zzk()));
                        zzb(t, zza3, i);
                        continue;
                    case 59:
                        zza((Object) t, zzd2, zzke);
                        zzb(t, zza3, i);
                        continue;
                    case 60:
                        if (zza(t, zza3, i)) {
                            zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzii.zza(zzlf.zzf(t, (long) (1048575 & zzd2)), zzke.zza(zza(i), zzht)));
                        } else {
                            zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zza(zza(i), zzht));
                            zzb(t, i);
                        }
                        zzb(t, zza3, i);
                        continue;
                    case 61:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) zzke.zzn());
                        zzb(t, zza3, i);
                        continue;
                    case 62:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzke.zzo()));
                        zzb(t, zza3, i);
                        continue;
                    case 63:
                        int zzp3 = zzke.zzp();
                        zzin zzc3 = zzc(i);
                        if (zzc3 != null && !zzc3.zza(zzp3)) {
                            obj5 = zzkj.zza(zza3, zzp3, obj5, zzkz);
                            break;
                        } else {
                            zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzp3));
                            zzb(t, zza3, i);
                            continue;
                        }
                        break;
                    case 64:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzke.zzq()));
                        zzb(t, zza3, i);
                        continue;
                    case 65:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzke.zzr()));
                        zzb(t, zza3, i);
                        continue;
                    case 66:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) Integer.valueOf(zzke.zzs()));
                        zzb(t, zza3, i);
                        continue;
                    case 67:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), (Object) Long.valueOf(zzke.zzt()));
                        zzb(t, zza3, i);
                        continue;
                    case 68:
                        zzlf.zza((Object) t, (long) (zzd2 & 1048575), zzke.zzb(zza(i), zzht));
                        zzb(t, zza3, i);
                        continue;
                    default:
                        if (obj5 == null) {
                            try {
                                obj4 = zzkz.zza();
                            } catch (zziq e) {
                                break;
                            }
                        } else {
                            obj4 = obj5;
                        }
                        try {
                            if (!zzkz.zza(obj4, zzke)) {
                                for (int i5 = this.zzm; i5 < this.zzn; i5++) {
                                    obj4 = zza((Object) t, this.zzl[i5], obj4, zzkz);
                                }
                                if (obj4 != null) {
                                    zzkz.zzb((Object) t, obj4);
                                    return;
                                }
                                return;
                            }
                            obj5 = obj4;
                            continue;
                        } catch (zziq e2) {
                            obj5 = obj4;
                            break;
                        }
                }
                try {
                    zzkz.zza(zzke);
                    if (obj5 == null) {
                        obj3 = zzkz.zzc(t);
                    } else {
                        obj3 = obj5;
                    }
                    if (!zzkz.zza(obj, zzke)) {
                        for (int i6 = this.zzm; i6 < this.zzn; i6++) {
                            obj = zza((Object) t, this.zzl[i6], obj, zzkz);
                        }
                        if (obj != null) {
                            zzkz.zzb((Object) t, obj);
                            return;
                        }
                        return;
                    }
                    obj5 = obj;
                } catch (Throwable th2) {
                    th = th2;
                    obj = obj5;
                    for (int i7 = this.zzm; i7 < this.zzn; i7++) {
                        obj = zza((Object) t, this.zzl[i7], obj, zzkz);
                    }
                    if (obj != null) {
                        zzkz.zzb((Object) t, obj);
                    }
                    throw th;
                }
            } else if (zza3 == Integer.MAX_VALUE) {
                for (int i8 = this.zzm; i8 < this.zzn; i8++) {
                    obj5 = zza((Object) t, this.zzl[i8], obj5, zzkz);
                }
                if (obj5 != null) {
                    zzkz.zzb((Object) t, obj5);
                    return;
                }
                return;
            } else {
                if (!this.zzh) {
                    zza2 = null;
                } else {
                    zza2 = zzhv.zza(zzht, this.zzg, zza3);
                }
                if (zza2 != null) {
                    if (zzhz == null) {
                        zzhz = zzhv.zzb(t);
                    }
                    obj5 = zzhv.zza(zzke, zza2, zzht, zzhz, obj5, zzkz);
                } else {
                    zzkz.zza(zzke);
                    if (obj5 == null) {
                        obj = zzkz.zzc(t);
                    } else {
                        obj = obj5;
                    }
                    try {
                        if (!zzkz.zza(obj, zzke)) {
                            for (int i9 = this.zzm; i9 < this.zzn; i9++) {
                                obj = zza((Object) t, this.zzl[i9], obj, zzkz);
                            }
                            if (obj != null) {
                                zzkz.zzb((Object) t, obj);
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
    }

    private final zzkh zza(int i) {
        int i2 = (i / 3) << 1;
        zzkh zzkh = (zzkh) this.zzd[i2];
        if (zzkh != null) {
            return zzkh;
        }
        zzkh zza2 = zzkd.zza().zza((Class) this.zzd[i2 + 1]);
        this.zzd[i2] = zza2;
        return zza2;
    }

    private final Object zzb(int i) {
        return this.zzd[(i / 3) << 1];
    }

    private final zzin zzc(int i) {
        return (zzin) this.zzd[((i / 3) << 1) + 1];
    }

    public final void zzb(T t) {
        for (int i = this.zzm; i < this.zzn; i++) {
            long zzd2 = (long) (zzd(this.zzl[i]) & 1048575);
            Object zzf2 = zzlf.zzf(t, zzd2);
            if (zzf2 != null) {
                zzlf.zza((Object) t, zzd2, this.zzs.zzd(zzf2));
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

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzkz<UT, UB> zzkz) {
        zzin zzc2;
        int i2 = this.zzc[i];
        Object zzf2 = zzlf.zzf(obj, (long) (zzd(i) & 1048575));
        if (zzf2 == null || (zzc2 = zzc(i)) == null) {
            return ub;
        }
        return zza(i, i2, this.zzs.zza(zzf2), zzc2, ub, zzkz);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzin zzin, UB ub, zzkz<UT, UB> zzkz) {
        zzji<?, ?> zzf2 = this.zzs.zzf(zzb(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (!zzin.zza(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzkz.zza();
                }
                zzhd zzc2 = zzgv.zzc(zzjj.zza(zzf2, next.getKey(), next.getValue()));
                try {
                    zzjj.zza(zzc2.zzb(), zzf2, next.getKey(), next.getValue());
                    zzkz.zza(ub, i2, zzc2.zza());
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
    public final boolean zzc(T r15) {
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
            boolean r0 = r0.zza(r1, r2, r3, r4, r5)
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
            boolean r0 = r0.zza(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x004b
            com.google.android.gms.internal.firebase_auth.zzkh r0 = r14.zza((int) r2)
            boolean r0 = zza((java.lang.Object) r15, (int) r11, (com.google.android.gms.internal.firebase_auth.zzkh) r0)
            if (r0 != 0) goto L_0x004b
            goto L_0x0040
        L_0x0062:
            r0 = r11 & r8
            long r0 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r15, r0)
            java.util.List r0 = (java.util.List) r0
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x008d
            com.google.android.gms.internal.firebase_auth.zzkh r2 = r14.zza((int) r2)
            r1 = r7
        L_0x0076:
            int r5 = r0.size()
            if (r1 >= r5) goto L_0x008d
            java.lang.Object r5 = r0.get(r1)
            boolean r5 = r2.zzc(r5)
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
            com.google.android.gms.internal.firebase_auth.zzkh r0 = r14.zza((int) r2)
            boolean r0 = zza((java.lang.Object) r15, (int) r11, (com.google.android.gms.internal.firebase_auth.zzkh) r0)
            if (r0 != 0) goto L_0x004b
            goto L_0x0040
        L_0x00a0:
            com.google.android.gms.internal.firebase_auth.zzjk r0 = r14.zzs
            r1 = r11 & r8
            long r10 = (long) r1
            java.lang.Object r1 = com.google.android.gms.internal.firebase_auth.zzlf.zzf(r15, r10)
            java.util.Map r1 = r0.zzb(r1)
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x00f3
            java.lang.Object r0 = r14.zzb((int) r2)
            com.google.android.gms.internal.firebase_auth.zzjk r2 = r14.zzs
            com.google.android.gms.internal.firebase_auth.zzji r0 = r2.zzf(r0)
            com.google.android.gms.internal.firebase_auth.zzlq r0 = r0.zzc
            com.google.android.gms.internal.firebase_auth.zzlt r0 = r0.zza()
            com.google.android.gms.internal.firebase_auth.zzlt r2 = com.google.android.gms.internal.firebase_auth.zzlt.MESSAGE
            if (r0 != r2) goto L_0x00f3
            r0 = 0
            java.util.Collection r1 = r1.values()
            java.util.Iterator r1 = r1.iterator()
        L_0x00d0:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00f3
            java.lang.Object r2 = r1.next()
            if (r0 != 0) goto L_0x00e8
            com.google.android.gms.internal.firebase_auth.zzkd r0 = com.google.android.gms.internal.firebase_auth.zzkd.zza()
            java.lang.Class r5 = r2.getClass()
            com.google.android.gms.internal.firebase_auth.zzkh r0 = r0.zza(r5)
        L_0x00e8:
            boolean r2 = r0.zzc(r2)
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
            com.google.android.gms.internal.firebase_auth.zzhv<?> r0 = r14.zzr
            com.google.android.gms.internal.firebase_auth.zzhz r0 = r0.zza((java.lang.Object) r15)
            boolean r0 = r0.zzf()
            if (r0 == 0) goto L_0x0040
        L_0x0105:
            r7 = r9
            goto L_0x0040
        L_0x0108:
            r3 = r0
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjv.zzc(java.lang.Object):boolean");
    }

    private static boolean zza(Object obj, int i, zzkh zzkh) {
        return zzkh.zzc(zzlf.zzf(obj, (long) (1048575 & i)));
    }

    private static void zza(int i, Object obj, zzlw zzlw) throws IOException {
        if (obj instanceof String) {
            zzlw.zza(i, (String) obj);
        } else {
            zzlw.zza(i, (zzgv) obj);
        }
    }

    private final void zza(Object obj, int i, zzke zzke) throws IOException {
        if (zzf(i)) {
            zzlf.zza(obj, (long) (i & 1048575), (Object) zzke.zzm());
        } else if (this.zzi) {
            zzlf.zza(obj, (long) (i & 1048575), (Object) zzke.zzl());
        } else {
            zzlf.zza(obj, (long) (i & 1048575), (Object) zzke.zzn());
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
        return ((Double) zzlf.zzf(t, j)).doubleValue();
    }

    private static <T> float zzc(T t, long j) {
        return ((Float) zzlf.zzf(t, j)).floatValue();
    }

    private static <T> int zzd(T t, long j) {
        return ((Integer) zzlf.zzf(t, j)).intValue();
    }

    private static <T> long zze(T t, long j) {
        return ((Long) zzlf.zzf(t, j)).longValue();
    }

    private static <T> boolean zzf(T t, long j) {
        return ((Boolean) zzlf.zzf(t, j)).booleanValue();
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
                    if (zzlf.zze(t, j) != 0.0d) {
                        return true;
                    }
                    return false;
                case 1:
                    return zzlf.zzd(t, j) != 0.0f;
                case 2:
                    return zzlf.zzb(t, j) != 0;
                case 3:
                    return zzlf.zzb(t, j) != 0;
                case 4:
                    return zzlf.zza((Object) t, j) != 0;
                case 5:
                    return zzlf.zzb(t, j) != 0;
                case 6:
                    return zzlf.zza((Object) t, j) != 0;
                case 7:
                    return zzlf.zzc(t, j);
                case 8:
                    Object zzf2 = zzlf.zzf(t, j);
                    if (zzf2 instanceof String) {
                        return !((String) zzf2).isEmpty();
                    }
                    if (zzf2 instanceof zzgv) {
                        return !zzgv.zza.equals(zzf2);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzlf.zzf(t, j) != null;
                case 10:
                    return !zzgv.zza.equals(zzlf.zzf(t, j));
                case 11:
                    return zzlf.zza((Object) t, j) != 0;
                case 12:
                    return zzlf.zza((Object) t, j) != 0;
                case 13:
                    return zzlf.zza((Object) t, j) != 0;
                case 14:
                    return zzlf.zzb(t, j) != 0;
                case 15:
                    return zzlf.zza((Object) t, j) != 0;
                case 16:
                    return zzlf.zzb(t, j) != 0;
                case 17:
                    return zzlf.zzf(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            return (zzlf.zza((Object) t, (long) (zze2 & 1048575)) & (1 << (zze2 >>> 20))) != 0;
        }
    }

    private final void zzb(T t, int i) {
        int zze2 = zze(i);
        long j = (long) (1048575 & zze2);
        if (j != 1048575) {
            zzlf.zza((Object) t, j, (1 << (zze2 >>> 20)) | zzlf.zza((Object) t, j));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzlf.zza((Object) t, (long) (zze(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzlf.zza((Object) t, (long) (zze(i2) & 1048575), i);
    }
}
