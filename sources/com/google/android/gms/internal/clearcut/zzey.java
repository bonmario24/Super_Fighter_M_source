package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import java.io.IOException;
import java.util.Arrays;

public final class zzey {
    private static final zzey zzoz = new zzey(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzfa;
    private int zzjq;
    private Object[] zzmj;
    private int[] zzpa;

    private zzey() {
        this(0, new int[8], new Object[8], true);
    }

    private zzey(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzjq = -1;
        this.count = i;
        this.zzpa = iArr;
        this.zzmj = objArr;
        this.zzfa = z;
    }

    static zzey zza(zzey zzey, zzey zzey2) {
        int i = zzey.count + zzey2.count;
        int[] copyOf = Arrays.copyOf(zzey.zzpa, i);
        System.arraycopy(zzey2.zzpa, 0, copyOf, zzey.count, zzey2.count);
        Object[] copyOf2 = Arrays.copyOf(zzey.zzmj, i);
        System.arraycopy(zzey2.zzmj, 0, copyOf2, zzey.count, zzey2.count);
        return new zzey(i, copyOf, copyOf2, true);
    }

    private static void zzb(int i, Object obj, zzfr zzfr) throws IOException {
        int i2 = i >>> 3;
        switch (i & 7) {
            case 0:
                zzfr.zzi(i2, ((Long) obj).longValue());
                return;
            case 1:
                zzfr.zzc(i2, ((Long) obj).longValue());
                return;
            case 2:
                zzfr.zza(i2, (zzbb) obj);
                return;
            case 3:
                if (zzfr.zzaj() == zzcg.zzg.zzko) {
                    zzfr.zzaa(i2);
                    ((zzey) obj).zzb(zzfr);
                    zzfr.zzab(i2);
                    return;
                }
                zzfr.zzab(i2);
                ((zzey) obj).zzb(zzfr);
                zzfr.zzaa(i2);
                return;
            case 5:
                zzfr.zzf(i2, ((Integer) obj).intValue());
                return;
            default:
                throw new RuntimeException(zzco.zzbn());
        }
    }

    public static zzey zzea() {
        return zzoz;
    }

    static zzey zzeb() {
        return new zzey();
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
        if (!(obj instanceof zzey)) {
            return false;
        }
        zzey zzey = (zzey) obj;
        if (this.count == zzey.count) {
            int[] iArr = this.zzpa;
            int[] iArr2 = zzey.zzpa;
            int i = this.count;
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
                Object[] objArr = this.zzmj;
                Object[] objArr2 = zzey.zzmj;
                int i3 = this.count;
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
        int i2 = (this.count + 527) * 31;
        int[] iArr = this.zzpa;
        int i3 = this.count;
        int i4 = 17;
        for (int i5 = 0; i5 < i3; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzmj;
        int i7 = this.count;
        for (int i8 = 0; i8 < i7; i8++) {
            i = (i * 31) + objArr[i8].hashCode();
        }
        return i6 + i;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzfr zzfr) throws IOException {
        if (zzfr.zzaj() == zzcg.zzg.zzkp) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzfr.zza(this.zzpa[i] >>> 3, this.zzmj[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzfr.zza(this.zzpa[i2] >>> 3, this.zzmj[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzdr.zza(sb, i, String.valueOf(this.zzpa[i2] >>> 3), this.zzmj[i2]);
        }
    }

    public final int zzas() {
        int zzas;
        int i = this.zzjq;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.count; i2++) {
                int i3 = this.zzpa[i2];
                int i4 = i3 >>> 3;
                switch (i3 & 7) {
                    case 0:
                        zzas = zzbn.zze(i4, ((Long) this.zzmj[i2]).longValue());
                        break;
                    case 1:
                        zzas = zzbn.zzg(i4, ((Long) this.zzmj[i2]).longValue());
                        break;
                    case 2:
                        zzas = zzbn.zzc(i4, (zzbb) this.zzmj[i2]);
                        break;
                    case 3:
                        zzas = ((zzey) this.zzmj[i2]).zzas() + (zzbn.zzr(i4) << 1);
                        break;
                    case 5:
                        zzas = zzbn.zzj(i4, ((Integer) this.zzmj[i2]).intValue());
                        break;
                    default:
                        throw new IllegalStateException(zzco.zzbn());
                }
                i += zzas;
            }
            this.zzjq = i;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(int i, Object obj) {
        if (!this.zzfa) {
            throw new UnsupportedOperationException();
        }
        if (this.count == this.zzpa.length) {
            int i2 = (this.count < 4 ? 8 : this.count >> 1) + this.count;
            this.zzpa = Arrays.copyOf(this.zzpa, i2);
            this.zzmj = Arrays.copyOf(this.zzmj, i2);
        }
        this.zzpa[this.count] = i;
        this.zzmj[this.count] = obj;
        this.count++;
    }

    public final void zzb(zzfr zzfr) throws IOException {
        if (this.count != 0) {
            if (zzfr.zzaj() == zzcg.zzg.zzko) {
                for (int i = 0; i < this.count; i++) {
                    zzb(this.zzpa[i], this.zzmj[i], zzfr);
                }
                return;
            }
            for (int i2 = this.count - 1; i2 >= 0; i2--) {
                zzb(this.zzpa[i2], this.zzmj[i2], zzfr);
            }
        }
    }

    public final int zzec() {
        int i = this.zzjq;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.count; i2++) {
                i += zzbn.zzd(this.zzpa[i2] >>> 3, (zzbb) this.zzmj[i2]);
            }
            this.zzjq = i;
        }
        return i;
    }

    public final void zzv() {
        this.zzfa = false;
    }
}
