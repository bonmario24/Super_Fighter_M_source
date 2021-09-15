package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.nio.charset.Charset;

class zzbi extends zzbh {
    protected final byte[] zzfp;

    zzbi(byte[] bArr) {
        this.zzfp = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbb)) {
            return false;
        }
        if (size() != ((zzbb) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzbi)) {
            return obj.equals(this);
        }
        int zzab = zzab();
        int zzab2 = ((zzbi) obj).zzab();
        if (zzab == 0 || zzab2 == 0 || zzab == zzab2) {
            return zza((zzbi) obj, 0, size());
        }
        return false;
    }

    public int size() {
        return this.zzfp.length;
    }

    /* access modifiers changed from: protected */
    public final int zza(int i, int i2, int i3) {
        return zzci.zza(i, this.zzfp, zzac(), i3);
    }

    public final zzbb zza(int i, int i2) {
        int zzb = zzb(0, i2, size());
        return zzb == 0 ? zzbb.zzfi : new zzbe(this.zzfp, zzac(), zzb);
    }

    /* access modifiers changed from: protected */
    public final String zza(Charset charset) {
        return new String(this.zzfp, zzac(), size(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzba zzba) throws IOException {
        zzba.zza(this.zzfp, zzac(), size());
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzbb zzbb, int i, int i2) {
        if (i2 > zzbb.size()) {
            throw new IllegalArgumentException(new StringBuilder(40).append("Length too large: ").append(i2).append(size()).toString());
        } else if (i2 > zzbb.size()) {
            throw new IllegalArgumentException(new StringBuilder(59).append("Ran off end of other: 0, ").append(i2).append(", ").append(zzbb.size()).toString());
        } else if (!(zzbb instanceof zzbi)) {
            return zzbb.zza(0, i2).equals(zza(0, i2));
        } else {
            zzbi zzbi = (zzbi) zzbb;
            byte[] bArr = this.zzfp;
            byte[] bArr2 = zzbi.zzfp;
            int zzac = zzac() + i2;
            int zzac2 = zzac();
            int zzac3 = zzbi.zzac();
            while (zzac2 < zzac) {
                if (bArr[zzac2] != bArr2[zzac3]) {
                    return false;
                }
                zzac2++;
                zzac3++;
            }
            return true;
        }
    }

    public final boolean zzaa() {
        int zzac = zzac();
        return zzff.zze(this.zzfp, zzac, size() + zzac);
    }

    /* access modifiers changed from: protected */
    public int zzac() {
        return 0;
    }

    public byte zzj(int i) {
        return this.zzfp[i];
    }
}
