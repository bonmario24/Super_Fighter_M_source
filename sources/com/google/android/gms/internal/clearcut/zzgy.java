package com.google.android.gms.internal.clearcut;

import java.io.IOException;

public final class zzgy extends zzfu<zzgy> implements Cloneable {
    private String[] zzbiw = zzgb.zzsc;
    private String[] zzbix = zzgb.zzsc;
    private int[] zzbiy = zzgb.zzrx;
    private long[] zzbiz = zzgb.zzry;
    private long[] zzbja = zzgb.zzry;

    public zzgy() {
        this.zzrj = null;
        this.zzrs = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzgb */
    public final zzgy clone() {
        try {
            zzgy zzgy = (zzgy) super.clone();
            if (this.zzbiw != null && this.zzbiw.length > 0) {
                zzgy.zzbiw = (String[]) this.zzbiw.clone();
            }
            if (this.zzbix != null && this.zzbix.length > 0) {
                zzgy.zzbix = (String[]) this.zzbix.clone();
            }
            if (this.zzbiy != null && this.zzbiy.length > 0) {
                zzgy.zzbiy = (int[]) this.zzbiy.clone();
            }
            if (this.zzbiz != null && this.zzbiz.length > 0) {
                zzgy.zzbiz = (long[]) this.zzbiz.clone();
            }
            if (this.zzbja != null && this.zzbja.length > 0) {
                zzgy.zzbja = (long[]) this.zzbja.clone();
            }
            return zzgy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgy)) {
            return false;
        }
        zzgy zzgy = (zzgy) obj;
        if (!zzfy.equals((Object[]) this.zzbiw, (Object[]) zzgy.zzbiw)) {
            return false;
        }
        if (!zzfy.equals((Object[]) this.zzbix, (Object[]) zzgy.zzbix)) {
            return false;
        }
        if (!zzfy.equals(this.zzbiy, zzgy.zzbiy)) {
            return false;
        }
        if (!zzfy.equals(this.zzbiz, zzgy.zzbiz)) {
            return false;
        }
        if (!zzfy.equals(this.zzbja, zzgy.zzbja)) {
            return false;
        }
        return (this.zzrj == null || this.zzrj.isEmpty()) ? zzgy.zzrj == null || zzgy.zzrj.isEmpty() : this.zzrj.equals(zzgy.zzrj);
    }

    public final int hashCode() {
        return ((this.zzrj == null || this.zzrj.isEmpty()) ? 0 : this.zzrj.hashCode()) + ((((((((((((getClass().getName().hashCode() + 527) * 31) + zzfy.hashCode((Object[]) this.zzbiw)) * 31) + zzfy.hashCode((Object[]) this.zzbix)) * 31) + zzfy.hashCode(this.zzbiy)) * 31) + zzfy.hashCode(this.zzbiz)) * 31) + zzfy.hashCode(this.zzbja)) * 31);
    }

    public final void zza(zzfs zzfs) throws IOException {
        if (this.zzbiw != null && this.zzbiw.length > 0) {
            for (String str : this.zzbiw) {
                if (str != null) {
                    zzfs.zza(1, str);
                }
            }
        }
        if (this.zzbix != null && this.zzbix.length > 0) {
            for (String str2 : this.zzbix) {
                if (str2 != null) {
                    zzfs.zza(2, str2);
                }
            }
        }
        if (this.zzbiy != null && this.zzbiy.length > 0) {
            for (int zzc : this.zzbiy) {
                zzfs.zzc(3, zzc);
            }
        }
        if (this.zzbiz != null && this.zzbiz.length > 0) {
            for (long zzi : this.zzbiz) {
                zzfs.zzi(4, zzi);
            }
        }
        if (this.zzbja != null && this.zzbja.length > 0) {
            for (long zzi2 : this.zzbja) {
                zzfs.zzi(5, zzi2);
            }
        }
        super.zza(zzfs);
    }

    /* access modifiers changed from: protected */
    public final int zzen() {
        int i;
        int zzen = super.zzen();
        if (this.zzbiw == null || this.zzbiw.length <= 0) {
            i = zzen;
        } else {
            int i2 = 0;
            int i3 = 0;
            for (String str : this.zzbiw) {
                if (str != null) {
                    i3++;
                    i2 += zzfs.zzh(str);
                }
            }
            i = zzen + i2 + (i3 * 1);
        }
        if (this.zzbix != null && this.zzbix.length > 0) {
            int i4 = 0;
            int i5 = 0;
            for (String str2 : this.zzbix) {
                if (str2 != null) {
                    i5++;
                    i4 += zzfs.zzh(str2);
                }
            }
            i = i + i4 + (i5 * 1);
        }
        if (this.zzbiy != null && this.zzbiy.length > 0) {
            int i6 = 0;
            for (int zzs : this.zzbiy) {
                i6 += zzfs.zzs(zzs);
            }
            i = i + i6 + (this.zzbiy.length * 1);
        }
        if (this.zzbiz != null && this.zzbiz.length > 0) {
            int i7 = 0;
            for (long zzo : this.zzbiz) {
                i7 += zzfs.zzo(zzo);
            }
            i = i + i7 + (this.zzbiz.length * 1);
        }
        if (this.zzbja == null || this.zzbja.length <= 0) {
            return i;
        }
        int i8 = 0;
        for (long zzo2 : this.zzbja) {
            i8 += zzfs.zzo(zzo2);
        }
        return i + i8 + (this.zzbja.length * 1);
    }

    public final /* synthetic */ zzfu zzeo() throws CloneNotSupportedException {
        return (zzgy) clone();
    }

    public final /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzgy) clone();
    }
}
