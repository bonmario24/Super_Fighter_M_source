package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.Arrays;

public final class zzgz extends zzfu<zzgz> implements Cloneable {
    private byte[] zzbjb = zzgb.zzse;
    private String zzbjc = "";
    private byte[][] zzbjd = zzgb.zzsd;
    private boolean zzbje = false;

    public zzgz() {
        this.zzrj = null;
        this.zzrs = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzgc */
    public final zzgz clone() {
        try {
            zzgz zzgz = (zzgz) super.clone();
            if (this.zzbjd != null && this.zzbjd.length > 0) {
                zzgz.zzbjd = (byte[][]) this.zzbjd.clone();
            }
            return zzgz;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgz)) {
            return false;
        }
        zzgz zzgz = (zzgz) obj;
        if (!Arrays.equals(this.zzbjb, zzgz.zzbjb)) {
            return false;
        }
        if (this.zzbjc == null) {
            if (zzgz.zzbjc != null) {
                return false;
            }
        } else if (!this.zzbjc.equals(zzgz.zzbjc)) {
            return false;
        }
        if (!zzfy.zza(this.zzbjd, zzgz.zzbjd)) {
            return false;
        }
        return (this.zzrj == null || this.zzrj.isEmpty()) ? zzgz.zzrj == null || zzgz.zzrj.isEmpty() : this.zzrj.equals(zzgz.zzrj);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((this.zzbjc == null ? 0 : this.zzbjc.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.zzbjb)) * 31)) * 31) + zzfy.zza(this.zzbjd)) * 31) + 1237) * 31;
        if (this.zzrj != null && !this.zzrj.isEmpty()) {
            i = this.zzrj.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzfs zzfs) throws IOException {
        if (!Arrays.equals(this.zzbjb, zzgb.zzse)) {
            zzfs.zza(1, this.zzbjb);
        }
        if (this.zzbjd != null && this.zzbjd.length > 0) {
            for (byte[] bArr : this.zzbjd) {
                if (bArr != null) {
                    zzfs.zza(2, bArr);
                }
            }
        }
        if (this.zzbjc != null && !this.zzbjc.equals("")) {
            zzfs.zza(4, this.zzbjc);
        }
        super.zza(zzfs);
    }

    /* access modifiers changed from: protected */
    public final int zzen() {
        int i;
        int zzen = super.zzen();
        if (!Arrays.equals(this.zzbjb, zzgb.zzse)) {
            zzen += zzfs.zzb(1, this.zzbjb);
        }
        if (this.zzbjd != null && this.zzbjd.length > 0) {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i2 < this.zzbjd.length) {
                byte[] bArr = this.zzbjd[i2];
                if (bArr != null) {
                    i4++;
                    i = zzfs.zzh(bArr) + i3;
                } else {
                    i = i3;
                }
                i2++;
                i3 = i;
            }
            zzen = zzen + i3 + (i4 * 1);
        }
        return (this.zzbjc == null || this.zzbjc.equals("")) ? zzen : zzen + zzfs.zzb(4, this.zzbjc);
    }

    public final /* synthetic */ zzfu zzeo() throws CloneNotSupportedException {
        return (zzgz) clone();
    }

    public final /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzgz) clone();
    }
}
