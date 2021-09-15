package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
public abstract class zzel extends zzdt {
    private static final Logger zzb = Logger.getLogger(zzel.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzc = zzid.zza();
    zzeo zza;

    public static zzel zza(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public abstract int zza();

    public abstract void zza(byte b) throws IOException;

    public abstract void zza(int i) throws IOException;

    public abstract void zza(int i, int i2) throws IOException;

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzdw zzdw) throws IOException;

    public abstract void zza(int i, zzgm zzgm) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zza(int i, zzgm zzgm, zzhf zzhf) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(int i, boolean z) throws IOException;

    public abstract void zza(long j) throws IOException;

    public abstract void zza(zzdw zzdw) throws IOException;

    public abstract void zza(zzgm zzgm) throws IOException;

    public abstract void zza(String str) throws IOException;

    public abstract void zzb(int i) throws IOException;

    public abstract void zzb(int i, int i2) throws IOException;

    public abstract void zzb(int i, zzdw zzdw) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zzb(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzc(int i, int i2) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public abstract void zzc(long j) throws IOException;

    public abstract void zzd(int i) throws IOException;

    public abstract void zze(int i, int i2) throws IOException;

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        zzb(java.lang.String r4, java.lang.Throwable r5) {
            /*
                r3 = this;
                java.lang.String r0 = "CodedOutputStream was writing to a flat byte array and ran out of space.: "
                java.lang.String r1 = java.lang.String.valueOf(r0)
                java.lang.String r0 = java.lang.String.valueOf(r4)
                int r2 = r0.length()
                if (r2 == 0) goto L_0x0018
                java.lang.String r0 = r1.concat(r0)
            L_0x0014:
                r3.<init>(r0, r5)
                return
            L_0x0018:
                java.lang.String r0 = new java.lang.String
                r0.<init>(r1)
                goto L_0x0014
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzel.zzb.<init>(java.lang.String, java.lang.Throwable):void");
        }
    }

    private zzel() {
    }

    public final void zzd(int i, int i2) throws IOException {
        zzc(i, zzm(i2));
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzi(j));
    }

    public final void zza(int i, float f) throws IOException {
        zze(i, Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
    static class zza extends zzel {
        private final byte[] zzb;
        private final int zzc;
        private final int zzd;
        private int zze;

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            } else if ((i2 | 0 | (bArr.length - (i2 + 0))) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), 0, Integer.valueOf(i2)}));
            } else {
                this.zzb = bArr;
                this.zzc = 0;
                this.zze = 0;
                this.zzd = i2 + 0;
            }
        }

        public final void zza(int i, int i2) throws IOException {
            zzb((i << 3) | i2);
        }

        public final void zzb(int i, int i2) throws IOException {
            zza(i, 0);
            zza(i2);
        }

        public final void zzc(int i, int i2) throws IOException {
            zza(i, 0);
            zzb(i2);
        }

        public final void zze(int i, int i2) throws IOException {
            zza(i, 5);
            zzd(i2);
        }

        public final void zza(int i, long j) throws IOException {
            zza(i, 0);
            zza(j);
        }

        public final void zzc(int i, long j) throws IOException {
            zza(i, 1);
            zzc(j);
        }

        public final void zza(int i, boolean z) throws IOException {
            int i2 = 0;
            zza(i, 0);
            if (z) {
                i2 = 1;
            }
            zza((byte) i2);
        }

        public final void zza(int i, String str) throws IOException {
            zza(i, 2);
            zza(str);
        }

        public final void zza(int i, zzdw zzdw) throws IOException {
            zza(i, 2);
            zza(zzdw);
        }

        public final void zza(zzdw zzdw) throws IOException {
            zzb(zzdw.zza());
            zzdw.zza((zzdt) this);
        }

        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            zzb(i2);
            zzc(bArr, 0, i2);
        }

        /* access modifiers changed from: package-private */
        public final void zza(int i, zzgm zzgm, zzhf zzhf) throws IOException {
            zza(i, 2);
            zzdm zzdm = (zzdm) zzgm;
            int zzbj = zzdm.zzbj();
            if (zzbj == -1) {
                zzbj = zzhf.zzb(zzdm);
                zzdm.zzc(zzbj);
            }
            zzb(zzbj);
            zzhf.zza(zzgm, (zziq) this.zza);
        }

        public final void zza(int i, zzgm zzgm) throws IOException {
            zza(1, 3);
            zzc(2, i);
            zza(3, 2);
            zza(zzgm);
            zza(1, 4);
        }

        public final void zzb(int i, zzdw zzdw) throws IOException {
            zza(1, 3);
            zzc(2, i);
            zza(3, zzdw);
            zza(1, 4);
        }

        public final void zza(zzgm zzgm) throws IOException {
            zzb(zzgm.zzbm());
            zzgm.zza(this);
        }

        public final void zza(byte b) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i = this.zze;
                this.zze = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e);
            }
        }

        public final void zza(int i) throws IOException {
            if (i >= 0) {
                zzb(i);
            } else {
                zza((long) i);
            }
        }

        public final void zzb(int i) throws IOException {
            if (!zzel.zzc || zzdp.zza() || zza() < 5) {
                while ((i & -128) != 0) {
                    try {
                        byte[] bArr = this.zzb;
                        int i2 = this.zze;
                        this.zze = i2 + 1;
                        bArr[i2] = (byte) ((i & 127) | 128);
                        i >>>= 7;
                    } catch (IndexOutOfBoundsException e) {
                        throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e);
                    }
                }
                byte[] bArr2 = this.zzb;
                int i3 = this.zze;
                this.zze = i3 + 1;
                bArr2[i3] = (byte) i;
            } else if ((i & -128) == 0) {
                byte[] bArr3 = this.zzb;
                int i4 = this.zze;
                this.zze = i4 + 1;
                zzid.zza(bArr3, (long) i4, (byte) i);
            } else {
                byte[] bArr4 = this.zzb;
                int i5 = this.zze;
                this.zze = i5 + 1;
                zzid.zza(bArr4, (long) i5, (byte) (i | 128));
                int i6 = i >>> 7;
                if ((i6 & -128) == 0) {
                    byte[] bArr5 = this.zzb;
                    int i7 = this.zze;
                    this.zze = i7 + 1;
                    zzid.zza(bArr5, (long) i7, (byte) i6);
                    return;
                }
                byte[] bArr6 = this.zzb;
                int i8 = this.zze;
                this.zze = i8 + 1;
                zzid.zza(bArr6, (long) i8, (byte) (i6 | 128));
                int i9 = i6 >>> 7;
                if ((i9 & -128) == 0) {
                    byte[] bArr7 = this.zzb;
                    int i10 = this.zze;
                    this.zze = i10 + 1;
                    zzid.zza(bArr7, (long) i10, (byte) i9);
                    return;
                }
                byte[] bArr8 = this.zzb;
                int i11 = this.zze;
                this.zze = i11 + 1;
                zzid.zza(bArr8, (long) i11, (byte) (i9 | 128));
                int i12 = i9 >>> 7;
                if ((i12 & -128) == 0) {
                    byte[] bArr9 = this.zzb;
                    int i13 = this.zze;
                    this.zze = i13 + 1;
                    zzid.zza(bArr9, (long) i13, (byte) i12);
                    return;
                }
                byte[] bArr10 = this.zzb;
                int i14 = this.zze;
                this.zze = i14 + 1;
                zzid.zza(bArr10, (long) i14, (byte) (i12 | 128));
                byte[] bArr11 = this.zzb;
                int i15 = this.zze;
                this.zze = i15 + 1;
                zzid.zza(bArr11, (long) i15, (byte) (i12 >>> 7));
            }
        }

        public final void zzd(int i) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i2 = this.zze;
                this.zze = i2 + 1;
                bArr[i2] = (byte) i;
                byte[] bArr2 = this.zzb;
                int i3 = this.zze;
                this.zze = i3 + 1;
                bArr2[i3] = (byte) (i >> 8);
                byte[] bArr3 = this.zzb;
                int i4 = this.zze;
                this.zze = i4 + 1;
                bArr3[i4] = (byte) (i >> 16);
                byte[] bArr4 = this.zzb;
                int i5 = this.zze;
                this.zze = i5 + 1;
                bArr4[i5] = (byte) (i >>> 24);
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e);
            }
        }

        public final void zza(long j) throws IOException {
            if (!zzel.zzc || zza() < 10) {
                while ((j & -128) != 0) {
                    try {
                        byte[] bArr = this.zzb;
                        int i = this.zze;
                        this.zze = i + 1;
                        bArr[i] = (byte) ((((int) j) & 127) | 128);
                        j >>>= 7;
                    } catch (IndexOutOfBoundsException e) {
                        throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e);
                    }
                }
                byte[] bArr2 = this.zzb;
                int i2 = this.zze;
                this.zze = i2 + 1;
                bArr2[i2] = (byte) ((int) j);
                return;
            }
            while ((j & -128) != 0) {
                byte[] bArr3 = this.zzb;
                int i3 = this.zze;
                this.zze = i3 + 1;
                zzid.zza(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            byte[] bArr4 = this.zzb;
            int i4 = this.zze;
            this.zze = i4 + 1;
            zzid.zza(bArr4, (long) i4, (byte) ((int) j));
        }

        public final void zzc(long j) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i = this.zze;
                this.zze = i + 1;
                bArr[i] = (byte) ((int) j);
                byte[] bArr2 = this.zzb;
                int i2 = this.zze;
                this.zze = i2 + 1;
                bArr2[i2] = (byte) ((int) (j >> 8));
                byte[] bArr3 = this.zzb;
                int i3 = this.zze;
                this.zze = i3 + 1;
                bArr3[i3] = (byte) ((int) (j >> 16));
                byte[] bArr4 = this.zzb;
                int i4 = this.zze;
                this.zze = i4 + 1;
                bArr4[i4] = (byte) ((int) (j >> 24));
                byte[] bArr5 = this.zzb;
                int i5 = this.zze;
                this.zze = i5 + 1;
                bArr5[i5] = (byte) ((int) (j >> 32));
                byte[] bArr6 = this.zzb;
                int i6 = this.zze;
                this.zze = i6 + 1;
                bArr6[i6] = (byte) ((int) (j >> 40));
                byte[] bArr7 = this.zzb;
                int i7 = this.zze;
                this.zze = i7 + 1;
                bArr7[i7] = (byte) ((int) (j >> 48));
                byte[] bArr8 = this.zzb;
                int i8 = this.zze;
                this.zze = i8 + 1;
                bArr8[i8] = (byte) ((int) (j >> 56));
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e);
            }
        }

        private final void zzc(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.zzb, this.zze, i2);
                this.zze += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), Integer.valueOf(i2)}), e);
            }
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            zzc(bArr, i, i2);
        }

        public final void zza(String str) throws IOException {
            int i = this.zze;
            try {
                int zzg = zzg(str.length() * 3);
                int zzg2 = zzg(str.length());
                if (zzg2 == zzg) {
                    this.zze = i + zzg2;
                    int zza = zzif.zza(str, this.zzb, this.zze, zza());
                    this.zze = i;
                    zzb((zza - i) - zzg2);
                    this.zze = zza;
                    return;
                }
                zzb(zzif.zza((CharSequence) str));
                this.zze = zzif.zza(str, this.zzb, this.zze, zza());
            } catch (zzij e) {
                this.zze = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(e2);
            }
        }

        public final int zza() {
            return this.zzd - this.zze;
        }
    }

    public final void zzc(int i) throws IOException {
        zzb(zzm(i));
    }

    public final void zzb(long j) throws IOException {
        zza(zzi(j));
    }

    public final void zza(float f) throws IOException {
        zzd(Float.floatToRawIntBits(f));
    }

    public final void zza(double d) throws IOException {
        zzc(Double.doubleToRawLongBits(d));
    }

    public final void zza(boolean z) throws IOException {
        zza((byte) (z ? 1 : 0));
    }

    public static int zzf(int i, int i2) {
        return zze(i) + zzf(i2);
    }

    public static int zzg(int i, int i2) {
        return zze(i) + zzg(i2);
    }

    public static int zzh(int i, int i2) {
        return zze(i) + zzg(zzm(i2));
    }

    public static int zzi(int i, int i2) {
        return zze(i) + 4;
    }

    public static int zzj(int i, int i2) {
        return zze(i) + 4;
    }

    public static int zzd(int i, long j) {
        return zze(i) + zze(j);
    }

    public static int zze(int i, long j) {
        return zze(i) + zze(j);
    }

    public static int zzf(int i, long j) {
        return zze(i) + zze(zzi(j));
    }

    public static int zzg(int i, long j) {
        return zze(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zze(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zze(i) + 4;
    }

    public static int zzb(int i, double d) {
        return zze(i) + 8;
    }

    public static int zzb(int i, boolean z) {
        return zze(i) + 1;
    }

    public static int zzk(int i, int i2) {
        return zze(i) + zzf(i2);
    }

    public static int zzb(int i, String str) {
        return zze(i) + zzb(str);
    }

    public static int zzc(int i, zzdw zzdw) {
        int zze = zze(i);
        int zza2 = zzdw.zza();
        return zze + zza2 + zzg(zza2);
    }

    public static int zza(int i, zzfv zzfv) {
        int zze = zze(i);
        int zzb2 = zzfv.zzb();
        return zze + zzb2 + zzg(zzb2);
    }

    static int zzb(int i, zzgm zzgm, zzhf zzhf) {
        return zze(i) + zza(zzgm, zzhf);
    }

    public static int zzb(int i, zzgm zzgm) {
        return (zze(1) << 1) + zzg(2, i) + zze(3) + zzb(zzgm);
    }

    public static int zzd(int i, zzdw zzdw) {
        return (zze(1) << 1) + zzg(2, i) + zzc(3, zzdw);
    }

    public static int zzb(int i, zzfv zzfv) {
        return (zze(1) << 1) + zzg(2, i) + zza(3, zzfv);
    }

    public static int zze(int i) {
        return zzg(i << 3);
    }

    public static int zzf(int i) {
        if (i >= 0) {
            return zzg(i);
        }
        return 10;
    }

    public static int zzg(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        if ((-268435456 & i) == 0) {
            return 4;
        }
        return 5;
    }

    public static int zzh(int i) {
        return zzg(zzm(i));
    }

    public static int zzi(int i) {
        return 4;
    }

    public static int zzj(int i) {
        return 4;
    }

    public static int zzd(long j) {
        return zze(j);
    }

    public static int zze(long j) {
        long j2;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        int i = 2;
        if ((-34359738368L & j) != 0) {
            i = 6;
            j2 = j >>> 28;
        } else {
            j2 = j;
        }
        if ((-2097152 & j2) != 0) {
            i += 2;
            j2 >>>= 14;
        }
        if ((j2 & -16384) != 0) {
            return i + 1;
        }
        return i;
    }

    public static int zzf(long j) {
        return zze(zzi(j));
    }

    public static int zzg(long j) {
        return 8;
    }

    public static int zzh(long j) {
        return 8;
    }

    public static int zzb(float f) {
        return 4;
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzb(boolean z) {
        return 1;
    }

    public static int zzk(int i) {
        return zzf(i);
    }

    public static int zzb(String str) {
        int length;
        try {
            length = zzif.zza((CharSequence) str);
        } catch (zzij e) {
            length = str.getBytes(zzfh.zza).length;
        }
        return length + zzg(length);
    }

    public static int zza(zzfv zzfv) {
        int zzb2 = zzfv.zzb();
        return zzb2 + zzg(zzb2);
    }

    public static int zzb(zzdw zzdw) {
        int zza2 = zzdw.zza();
        return zza2 + zzg(zza2);
    }

    public static int zzb(byte[] bArr) {
        int length = bArr.length;
        return length + zzg(length);
    }

    public static int zzb(zzgm zzgm) {
        int zzbm = zzgm.zzbm();
        return zzbm + zzg(zzbm);
    }

    static int zza(zzgm zzgm, zzhf zzhf) {
        zzdm zzdm = (zzdm) zzgm;
        int zzbj = zzdm.zzbj();
        if (zzbj == -1) {
            zzbj = zzhf.zzb(zzdm);
            zzdm.zzc(zzbj);
        }
        return zzbj + zzg(zzbj);
    }

    private static int zzm(int i) {
        return (i << 1) ^ (i >> 31);
    }

    private static long zzi(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public final void zzb() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, zzij zzij) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzij);
        byte[] bytes = str.getBytes(zzfh.zza);
        try {
            zzb(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzb(e);
        } catch (zzb e2) {
            throw e2;
        }
    }

    @Deprecated
    static int zzc(int i, zzgm zzgm, zzhf zzhf) {
        int zze = zze(i) << 1;
        zzdm zzdm = (zzdm) zzgm;
        int zzbj = zzdm.zzbj();
        if (zzbj == -1) {
            zzbj = zzhf.zzb(zzdm);
            zzdm.zzc(zzbj);
        }
        return zzbj + zze;
    }

    @Deprecated
    public static int zzc(zzgm zzgm) {
        return zzgm.zzbm();
    }

    @Deprecated
    public static int zzl(int i) {
        return zzg(i);
    }
}
