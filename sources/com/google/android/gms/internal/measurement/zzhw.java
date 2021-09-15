package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
public final class zzhw {
    private static final zzhw zza = new zzhw(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    public static zzhw zza() {
        return zza;
    }

    static zzhw zzb() {
        return new zzhw();
    }

    static zzhw zza(zzhw zzhw, zzhw zzhw2) {
        int i = zzhw.zzb + zzhw2.zzb;
        int[] copyOf = Arrays.copyOf(zzhw.zzc, i);
        System.arraycopy(zzhw2.zzc, 0, copyOf, zzhw.zzb, zzhw2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzhw.zzd, i);
        System.arraycopy(zzhw2.zzd, 0, copyOf2, zzhw.zzb, zzhw2.zzb);
        return new zzhw(i, copyOf, copyOf2, true);
    }

    private zzhw() {
        this(0, new int[8], new Object[8], true);
    }

    private zzhw(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public final void zzc() {
        this.zzf = false;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zziq zziq) throws IOException {
        if (zziq.zza() == zzfe.zzf.zzk) {
            for (int i = this.zzb - 1; i >= 0; i--) {
                zziq.zza(this.zzc[i] >>> 3, this.zzd[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zziq.zza(this.zzc[i2] >>> 3, this.zzd[i2]);
        }
    }

    public final void zzb(zziq zziq) throws IOException {
        if (this.zzb != 0) {
            if (zziq.zza() == zzfe.zzf.zzj) {
                for (int i = 0; i < this.zzb; i++) {
                    zza(this.zzc[i], this.zzd[i], zziq);
                }
                return;
            }
            for (int i2 = this.zzb - 1; i2 >= 0; i2--) {
                zza(this.zzc[i2], this.zzd[i2], zziq);
            }
        }
    }

    private static void zza(int i, Object obj, zziq zziq) throws IOException {
        int i2 = i >>> 3;
        switch (i & 7) {
            case 0:
                zziq.zza(i2, ((Long) obj).longValue());
                return;
            case 1:
                zziq.zzd(i2, ((Long) obj).longValue());
                return;
            case 2:
                zziq.zza(i2, (zzdw) obj);
                return;
            case 3:
                if (zziq.zza() == zzfe.zzf.zzj) {
                    zziq.zza(i2);
                    ((zzhw) obj).zzb(zziq);
                    zziq.zzb(i2);
                    return;
                }
                zziq.zzb(i2);
                ((zzhw) obj).zzb(zziq);
                zziq.zza(i2);
                return;
            case 5:
                zziq.zzd(i2, ((Integer) obj).intValue());
                return;
            default:
                throw new RuntimeException(zzfm.zzf());
        }
    }

    public final int zzd() {
        int i = this.zze;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.zzb; i2++) {
                i += zzel.zzd(this.zzc[i2] >>> 3, (zzdw) this.zzd[i2]);
            }
            this.zze = i;
        }
        return i;
    }

    public final int zze() {
        int zze2;
        int i = this.zze;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.zzb; i2++) {
                int i3 = this.zzc[i2];
                int i4 = i3 >>> 3;
                switch (i3 & 7) {
                    case 0:
                        zze2 = zzel.zze(i4, ((Long) this.zzd[i2]).longValue());
                        break;
                    case 1:
                        zze2 = zzel.zzg(i4, ((Long) this.zzd[i2]).longValue());
                        break;
                    case 2:
                        zze2 = zzel.zzc(i4, (zzdw) this.zzd[i2]);
                        break;
                    case 3:
                        zze2 = ((zzhw) this.zzd[i2]).zze() + (zzel.zze(i4) << 1);
                        break;
                    case 5:
                        zze2 = zzel.zzi(i4, ((Integer) this.zzd[i2]).intValue());
                        break;
                    default:
                        throw new IllegalStateException(zzfm.zzf());
                }
                i += zze2;
            }
            this.zze = i;
        }
        return i;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof zzhw)) {
            return false;
        }
        zzhw zzhw = (zzhw) obj;
        if (this.zzb == zzhw.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzhw.zzc;
            int i = this.zzb;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                } else if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                Object[] objArr = this.zzd;
                Object[] objArr2 = zzhw.zzd;
                int i3 = this.zzb;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    } else if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    } else {
                        i4++;
                    }
                }
                if (z2) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        int i2 = (this.zzb + 527) * 31;
        int[] iArr = this.zzc;
        int i3 = this.zzb;
        int i4 = 17;
        for (int i5 = 0; i5 < i3; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i = (i * 31) + objArr[i8].hashCode();
        }
        return i6 + i;
    }

    /* access modifiers changed from: package-private */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzgr.zza(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(int i, Object obj) {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
        if (this.zzb == this.zzc.length) {
            int i2 = (this.zzb < 4 ? 8 : this.zzb >> 1) + this.zzb;
            this.zzc = Arrays.copyOf(this.zzc, i2);
            this.zzd = Arrays.copyOf(this.zzd, i2);
        }
        this.zzc[this.zzb] = i;
        this.zzd[this.zzb] = obj;
        this.zzb++;
    }
}
