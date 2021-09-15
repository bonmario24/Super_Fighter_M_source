package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
final class zzhm extends zzhh {
    private final InputStream zze;
    private final byte[] zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private zzhl zzm;

    private zzhm(InputStream inputStream, int i) {
        super();
        this.zzl = Integer.MAX_VALUE;
        this.zzm = null;
        zzii.zza(inputStream, "input");
        this.zze = inputStream;
        this.zzf = new byte[i];
        this.zzg = 0;
        this.zzi = 0;
        this.zzk = 0;
    }

    public final int zza() throws IOException {
        if (zzt()) {
            this.zzj = 0;
            return 0;
        }
        this.zzj = zzv();
        if ((this.zzj >>> 3) != 0) {
            return this.zzj;
        }
        throw zzir.zzd();
    }

    public final void zza(int i) throws zzir {
        if (this.zzj != i) {
            throw zzir.zze();
        }
    }

    public final boolean zzb(int i) throws IOException {
        int zza;
        int i2 = 0;
        switch (i & 7) {
            case 0:
                if (this.zzg - this.zzi >= 10) {
                    while (i2 < 10) {
                        byte[] bArr = this.zzf;
                        int i3 = this.zzi;
                        this.zzi = i3 + 1;
                        if (bArr[i3] >= 0) {
                            return true;
                        }
                        i2++;
                    }
                    throw zzir.zzc();
                }
                while (i2 < 10) {
                    if (zzaa() >= 0) {
                        return true;
                    }
                    i2++;
                }
                throw zzir.zzc();
            case 1:
                zzj(8);
                return true;
            case 2:
                zzj(zzv());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzj(4);
                return true;
            default:
                throw zzir.zzf();
        }
        do {
            zza = zza();
            if (zza == 0 || !zzb(zza)) {
                zza(((i >>> 3) << 3) | 4);
                return true;
            }
            zza = zza();
            zza(((i >>> 3) << 3) | 4);
            return true;
        } while (!zzb(zza));
        zza(((i >>> 3) << 3) | 4);
        return true;
    }

    public final double zzb() throws IOException {
        return Double.longBitsToDouble(zzy());
    }

    public final float zzc() throws IOException {
        return Float.intBitsToFloat(zzx());
    }

    public final long zzd() throws IOException {
        return zzw();
    }

    public final long zze() throws IOException {
        return zzw();
    }

    public final int zzf() throws IOException {
        return zzv();
    }

    public final long zzg() throws IOException {
        return zzy();
    }

    public final int zzh() throws IOException {
        return zzx();
    }

    public final boolean zzi() throws IOException {
        return zzw() != 0;
    }

    public final String zzj() throws IOException {
        int zzv = zzv();
        if (zzv > 0 && zzv <= this.zzg - this.zzi) {
            String str = new String(this.zzf, this.zzi, zzv, zzii.zza);
            this.zzi = zzv + this.zzi;
            return str;
        } else if (zzv == 0) {
            return "";
        } else {
            if (zzv > this.zzg) {
                return new String(zza(zzv, false), zzii.zza);
            }
            zzf(zzv);
            String str2 = new String(this.zzf, this.zzi, zzv, zzii.zza);
            this.zzi = zzv + this.zzi;
            return str2;
        }
    }

    public final String zzk() throws IOException {
        byte[] zza;
        int zzv = zzv();
        int i = this.zzi;
        if (zzv <= this.zzg - i && zzv > 0) {
            byte[] bArr = this.zzf;
            this.zzi = i + zzv;
            zza = bArr;
        } else if (zzv == 0) {
            return "";
        } else {
            if (zzv <= this.zzg) {
                zzf(zzv);
                zza = this.zzf;
                this.zzi = zzv;
                i = 0;
            } else {
                zza = zza(zzv, false);
                i = 0;
            }
        }
        return zzli.zzb(zza, i, zzv);
    }

    public final zzgv zzl() throws IOException {
        int zzv = zzv();
        if (zzv <= this.zzg - this.zzi && zzv > 0) {
            zzgv zza = zzgv.zza(this.zzf, this.zzi, zzv);
            this.zzi = zzv + this.zzi;
            return zza;
        } else if (zzv == 0) {
            return zzgv.zza;
        } else {
            byte[] zzh2 = zzh(zzv);
            if (zzh2 != null) {
                return zzgv.zza(zzh2);
            }
            int i = this.zzi;
            int i2 = this.zzg - this.zzi;
            this.zzk += this.zzg;
            this.zzi = 0;
            this.zzg = 0;
            List<byte[]> zzi2 = zzi(zzv - i2);
            byte[] bArr = new byte[zzv];
            System.arraycopy(this.zzf, i, bArr, 0, i2);
            Iterator<byte[]> it = zzi2.iterator();
            while (true) {
                int i3 = i2;
                if (!it.hasNext()) {
                    return zzgv.zzb(bArr);
                }
                byte[] next = it.next();
                System.arraycopy(next, 0, bArr, i3, next.length);
                i2 = next.length + i3;
            }
        }
    }

    public final int zzm() throws IOException {
        return zzv();
    }

    public final int zzn() throws IOException {
        return zzv();
    }

    public final int zzo() throws IOException {
        return zzx();
    }

    public final long zzp() throws IOException {
        return zzy();
    }

    public final int zzq() throws IOException {
        return zze(zzv());
    }

    public final long zzr() throws IOException {
        return zza(zzw());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006a, code lost:
        if (r3[r2] < 0) goto L_0x006c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzv() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzi
            int r1 = r5.zzg
            if (r1 == r0) goto L_0x006c
            byte[] r3 = r5.zzf
            int r2 = r0 + 1
            byte r0 = r3[r0]
            if (r0 < 0) goto L_0x0011
            r5.zzi = r2
        L_0x0010:
            return r0
        L_0x0011:
            int r1 = r5.zzg
            int r1 = r1 - r2
            r4 = 9
            if (r1 < r4) goto L_0x006c
            int r1 = r2 + 1
            byte r2 = r3[r2]
            int r2 = r2 << 7
            r0 = r0 ^ r2
            if (r0 >= 0) goto L_0x0026
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
        L_0x0023:
            r5.zzi = r1
            goto L_0x0010
        L_0x0026:
            int r2 = r1 + 1
            byte r1 = r3[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x0033
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            r1 = r2
            goto L_0x0023
        L_0x0033:
            int r1 = r2 + 1
            byte r2 = r3[r2]
            int r2 = r2 << 21
            r0 = r0 ^ r2
            if (r0 >= 0) goto L_0x0041
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x0023
        L_0x0041:
            int r2 = r1 + 1
            byte r1 = r3[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L_0x0072
            int r1 = r2 + 1
            byte r2 = r3[r2]
            if (r2 >= 0) goto L_0x0023
            int r2 = r1 + 1
            byte r1 = r3[r1]
            if (r1 >= 0) goto L_0x0072
            int r1 = r2 + 1
            byte r2 = r3[r2]
            if (r2 >= 0) goto L_0x0023
            int r2 = r1 + 1
            byte r1 = r3[r1]
            if (r1 >= 0) goto L_0x0072
            int r1 = r2 + 1
            byte r2 = r3[r2]
            if (r2 >= 0) goto L_0x0023
        L_0x006c:
            long r0 = r5.zzs()
            int r0 = (int) r0
            goto L_0x0010
        L_0x0072:
            r1 = r2
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzhm.zzv():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b2, code lost:
        if (((long) r4[r3]) < 0) goto L_0x00b4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zzw() throws java.io.IOException {
        /*
            r10 = this;
            r8 = 0
            int r0 = r10.zzi
            int r1 = r10.zzg
            if (r1 == r0) goto L_0x00b4
            byte[] r4 = r10.zzf
            int r1 = r0 + 1
            byte r0 = r4[r0]
            if (r0 < 0) goto L_0x0014
            r10.zzi = r1
            long r0 = (long) r0
        L_0x0013:
            return r0
        L_0x0014:
            int r2 = r10.zzg
            int r2 = r2 - r1
            r3 = 9
            if (r2 < r3) goto L_0x00b4
            int r2 = r1 + 1
            byte r1 = r4[r1]
            int r1 = r1 << 7
            r0 = r0 ^ r1
            if (r0 >= 0) goto L_0x002a
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            long r0 = (long) r0
        L_0x0027:
            r10.zzi = r2
            goto L_0x0013
        L_0x002a:
            int r3 = r2 + 1
            byte r1 = r4[r2]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x0038
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            long r0 = (long) r0
            r2 = r3
            goto L_0x0027
        L_0x0038:
            int r2 = r3 + 1
            byte r1 = r4[r3]
            int r1 = r1 << 21
            r0 = r0 ^ r1
            if (r0 >= 0) goto L_0x0047
            r1 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r1
            long r0 = (long) r0
            goto L_0x0027
        L_0x0047:
            long r0 = (long) r0
            int r3 = r2 + 1
            byte r2 = r4[r2]
            long r6 = (long) r2
            r2 = 28
            long r6 = r6 << r2
            long r0 = r0 ^ r6
            int r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r2 < 0) goto L_0x005b
            r4 = 266354560(0xfe03f80, double:1.315966377E-315)
            long r0 = r0 ^ r4
            r2 = r3
            goto L_0x0027
        L_0x005b:
            int r2 = r3 + 1
            byte r3 = r4[r3]
            long r6 = (long) r3
            r3 = 35
            long r6 = r6 << r3
            long r0 = r0 ^ r6
            int r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x006f
            r4 = -34093383808(0xfffffff80fe03f80, double:NaN)
            long r0 = r0 ^ r4
            goto L_0x0027
        L_0x006f:
            int r3 = r2 + 1
            byte r2 = r4[r2]
            long r6 = (long) r2
            r2 = 42
            long r6 = r6 << r2
            long r0 = r0 ^ r6
            int r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r2 < 0) goto L_0x0084
            r4 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            long r0 = r0 ^ r4
            r2 = r3
            goto L_0x0027
        L_0x0084:
            int r2 = r3 + 1
            byte r3 = r4[r3]
            long r6 = (long) r3
            r3 = 49
            long r6 = r6 << r3
            long r0 = r0 ^ r6
            int r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x0098
            r4 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            long r0 = r0 ^ r4
            goto L_0x0027
        L_0x0098:
            int r3 = r2 + 1
            byte r2 = r4[r2]
            long r6 = (long) r2
            r2 = 56
            long r6 = r6 << r2
            long r0 = r0 ^ r6
            r6 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r0 = r0 ^ r6
            int r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x00ba
            int r2 = r3 + 1
            byte r3 = r4[r3]
            long r4 = (long) r3
            int r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x0027
        L_0x00b4:
            long r0 = r10.zzs()
            goto L_0x0013
        L_0x00ba:
            r2 = r3
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzhm.zzw():long");
    }

    /* access modifiers changed from: package-private */
    public final long zzs() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzaa = zzaa();
            j |= ((long) (zzaa & Byte.MAX_VALUE)) << i;
            if ((zzaa & 128) == 0) {
                return j;
            }
        }
        throw zzir.zzc();
    }

    private final int zzx() throws IOException {
        int i = this.zzi;
        if (this.zzg - i < 4) {
            zzf(4);
            i = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private final long zzy() throws IOException {
        int i = this.zzi;
        if (this.zzg - i < 8) {
            zzf(8);
            i = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    public final int zzc(int i) throws zzir {
        if (i < 0) {
            throw zzir.zzb();
        }
        int i2 = this.zzk + this.zzi + i;
        int i3 = this.zzl;
        if (i2 > i3) {
            throw zzir.zza();
        }
        this.zzl = i2;
        zzz();
        return i3;
    }

    private final void zzz() {
        this.zzg += this.zzh;
        int i = this.zzk + this.zzg;
        if (i > this.zzl) {
            this.zzh = i - this.zzl;
            this.zzg -= this.zzh;
            return;
        }
        this.zzh = 0;
    }

    public final void zzd(int i) {
        this.zzl = i;
        zzz();
    }

    public final boolean zzt() throws IOException {
        return this.zzi == this.zzg && !zzg(1);
    }

    public final int zzu() {
        return this.zzk + this.zzi;
    }

    private final void zzf(int i) throws IOException {
        if (zzg(i)) {
            return;
        }
        if (i > (this.zzc - this.zzk) - this.zzi) {
            throw zzir.zzg();
        }
        throw zzir.zza();
    }

    private final boolean zzg(int i) throws IOException {
        while (this.zzi + i > this.zzg) {
            if (i > (this.zzc - this.zzk) - this.zzi || this.zzk + this.zzi + i > this.zzl) {
                return false;
            }
            int i2 = this.zzi;
            if (i2 > 0) {
                if (this.zzg > i2) {
                    System.arraycopy(this.zzf, i2, this.zzf, 0, this.zzg - i2);
                }
                this.zzk += i2;
                this.zzg -= i2;
                this.zzi = 0;
            }
            int read = this.zze.read(this.zzf, this.zzg, Math.min(this.zzf.length - this.zzg, (this.zzc - this.zzk) - this.zzg));
            if (read == 0 || read < -1 || read > this.zzf.length) {
                String valueOf = String.valueOf(this.zze.getClass());
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 91).append(valueOf).append("#read(byte[]) returned invalid result: ").append(read).append("\nThe InputStream implementation is buggy.").toString());
            } else if (read <= 0) {
                return false;
            } else {
                this.zzg = read + this.zzg;
                zzz();
                if (this.zzg >= i) {
                    return true;
                }
            }
        }
        throw new IllegalStateException(new StringBuilder(77).append("refillBuffer() called when ").append(i).append(" bytes were already available in buffer").toString());
    }

    private final byte zzaa() throws IOException {
        if (this.zzi == this.zzg) {
            zzf(1);
        }
        byte[] bArr = this.zzf;
        int i = this.zzi;
        this.zzi = i + 1;
        return bArr[i];
    }

    private final byte[] zza(int i, boolean z) throws IOException {
        byte[] zzh2 = zzh(i);
        if (zzh2 != null) {
            return zzh2;
        }
        int i2 = this.zzi;
        int i3 = this.zzg - this.zzi;
        this.zzk += this.zzg;
        this.zzi = 0;
        this.zzg = 0;
        List<byte[]> zzi2 = zzi(i - i3);
        byte[] bArr = new byte[i];
        System.arraycopy(this.zzf, i2, bArr, 0, i3);
        Iterator<byte[]> it = zzi2.iterator();
        while (true) {
            int i4 = i3;
            if (!it.hasNext()) {
                return bArr;
            }
            byte[] next = it.next();
            System.arraycopy(next, 0, bArr, i4, next.length);
            i3 = next.length + i4;
        }
    }

    private final byte[] zzh(int i) throws IOException {
        if (i == 0) {
            return zzii.zzb;
        }
        if (i < 0) {
            throw zzir.zzb();
        }
        int i2 = this.zzk + this.zzi + i;
        if (i2 - this.zzc > 0) {
            throw zzir.zzg();
        } else if (i2 > this.zzl) {
            zzj((this.zzl - this.zzk) - this.zzi);
            throw zzir.zza();
        } else {
            int i3 = this.zzg - this.zzi;
            int i4 = i - i3;
            if (i4 >= 4096 && i4 > this.zze.available()) {
                return null;
            }
            byte[] bArr = new byte[i];
            System.arraycopy(this.zzf, this.zzi, bArr, 0, i3);
            this.zzk += this.zzg;
            this.zzi = 0;
            this.zzg = 0;
            while (i3 < bArr.length) {
                int read = this.zze.read(bArr, i3, i - i3);
                if (read == -1) {
                    throw zzir.zza();
                }
                this.zzk += read;
                i3 += read;
            }
            return bArr;
        }
    }

    private final List<byte[]> zzi(int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (i > 0) {
            byte[] bArr = new byte[Math.min(i, 4096)];
            int i2 = 0;
            while (i2 < bArr.length) {
                int read = this.zze.read(bArr, i2, bArr.length - i2);
                if (read == -1) {
                    throw zzir.zza();
                }
                this.zzk += read;
                i2 += read;
            }
            i -= bArr.length;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    private final void zzj(int i) throws IOException {
        if (i <= this.zzg - this.zzi && i >= 0) {
            this.zzi += i;
        } else if (i < 0) {
            throw zzir.zzb();
        } else if (this.zzk + this.zzi + i > this.zzl) {
            zzj((this.zzl - this.zzk) - this.zzi);
            throw zzir.zza();
        } else {
            this.zzk += this.zzi;
            this.zzg = 0;
            this.zzi = 0;
            int i2 = this.zzg - this.zzi;
            while (i2 < i) {
                int i3 = i - i2;
                try {
                    long skip = this.zze.skip((long) i3);
                    if (skip >= 0 && skip <= ((long) i3)) {
                        if (skip == 0) {
                            break;
                        }
                        i2 = ((int) skip) + i2;
                    } else {
                        String valueOf = String.valueOf(this.zze.getClass());
                        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 92).append(valueOf).append("#skip returned invalid result: ").append(skip).append("\nThe InputStream implementation is buggy.").toString());
                    }
                } catch (Throwable th) {
                    this.zzk = i2 + this.zzk;
                    zzz();
                    throw th;
                }
            }
            this.zzk += i2;
            zzz();
            if (i2 < i) {
                int i4 = this.zzg - this.zzi;
                this.zzi = this.zzg;
                zzf(1);
                while (i - i4 > this.zzg) {
                    i4 += this.zzg;
                    this.zzi = this.zzg;
                    zzf(1);
                }
                this.zzi = i - i4;
            }
        }
    }
}
