package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzge;
import java.io.IOException;
import java.util.Arrays;

public final class zzha extends zzfu<zzha> implements Cloneable {
    private String tag = "";
    public long zzbjf = 0;
    public long zzbjg = 0;
    private long zzbjh = 0;
    public int zzbji = 0;
    private String zzbjj = "";
    private int zzbjk = 0;
    private boolean zzbjl = false;
    private zzhb[] zzbjm = zzhb.zzge();
    private byte[] zzbjn = zzgb.zzse;
    private zzge.zzd zzbjo = null;
    public byte[] zzbjp = zzgb.zzse;
    private String zzbjq = "";
    private String zzbjr = "";
    private zzgy zzbjs = null;
    private String zzbjt = "";
    public long zzbju = 180000;
    private zzgz zzbjv = null;
    public byte[] zzbjw = zzgb.zzse;
    private String zzbjx = "";
    private int zzbjy = 0;
    private int[] zzbjz = zzgb.zzrx;
    private long zzbka = 0;
    private zzge.zzs zzbkb = null;
    public boolean zzbkc = false;

    public zzha() {
        this.zzrj = null;
        this.zzrs = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzgd */
    public final zzha clone() {
        try {
            zzha zzha = (zzha) super.clone();
            if (this.zzbjm != null && this.zzbjm.length > 0) {
                zzha.zzbjm = new zzhb[this.zzbjm.length];
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= this.zzbjm.length) {
                        break;
                    }
                    if (this.zzbjm[i2] != null) {
                        zzha.zzbjm[i2] = (zzhb) this.zzbjm[i2].clone();
                    }
                    i = i2 + 1;
                }
            }
            if (this.zzbjo != null) {
                zzha.zzbjo = this.zzbjo;
            }
            if (this.zzbjs != null) {
                zzha.zzbjs = (zzgy) this.zzbjs.clone();
            }
            if (this.zzbjv != null) {
                zzha.zzbjv = (zzgz) this.zzbjv.clone();
            }
            if (this.zzbjz != null && this.zzbjz.length > 0) {
                zzha.zzbjz = (int[]) this.zzbjz.clone();
            }
            if (this.zzbkb != null) {
                zzha.zzbkb = this.zzbkb;
            }
            return zzha;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzha)) {
            return false;
        }
        zzha zzha = (zzha) obj;
        if (this.zzbjf != zzha.zzbjf) {
            return false;
        }
        if (this.zzbjg != zzha.zzbjg) {
            return false;
        }
        if (0 != 0) {
            return false;
        }
        if (this.tag == null) {
            if (zzha.tag != null) {
                return false;
            }
        } else if (!this.tag.equals(zzha.tag)) {
            return false;
        }
        if (this.zzbji != zzha.zzbji) {
            return false;
        }
        if (this.zzbjj == null) {
            if (zzha.zzbjj != null) {
                return false;
            }
        } else if (!this.zzbjj.equals(zzha.zzbjj)) {
            return false;
        }
        if (!zzfy.equals((Object[]) this.zzbjm, (Object[]) zzha.zzbjm)) {
            return false;
        }
        if (!Arrays.equals(this.zzbjn, zzha.zzbjn)) {
            return false;
        }
        if (this.zzbjo == null) {
            if (zzha.zzbjo != null) {
                return false;
            }
        } else if (!this.zzbjo.equals(zzha.zzbjo)) {
            return false;
        }
        if (!Arrays.equals(this.zzbjp, zzha.zzbjp)) {
            return false;
        }
        if (this.zzbjq == null) {
            if (zzha.zzbjq != null) {
                return false;
            }
        } else if (!this.zzbjq.equals(zzha.zzbjq)) {
            return false;
        }
        if (this.zzbjr == null) {
            if (zzha.zzbjr != null) {
                return false;
            }
        } else if (!this.zzbjr.equals(zzha.zzbjr)) {
            return false;
        }
        if (this.zzbjs == null) {
            if (zzha.zzbjs != null) {
                return false;
            }
        } else if (!this.zzbjs.equals(zzha.zzbjs)) {
            return false;
        }
        if (this.zzbjt == null) {
            if (zzha.zzbjt != null) {
                return false;
            }
        } else if (!this.zzbjt.equals(zzha.zzbjt)) {
            return false;
        }
        if (this.zzbju != zzha.zzbju) {
            return false;
        }
        if (this.zzbjv == null) {
            if (zzha.zzbjv != null) {
                return false;
            }
        } else if (!this.zzbjv.equals(zzha.zzbjv)) {
            return false;
        }
        if (!Arrays.equals(this.zzbjw, zzha.zzbjw)) {
            return false;
        }
        if (this.zzbjx == null) {
            if (zzha.zzbjx != null) {
                return false;
            }
        } else if (!this.zzbjx.equals(zzha.zzbjx)) {
            return false;
        }
        if (!zzfy.equals(this.zzbjz, zzha.zzbjz)) {
            return false;
        }
        if (0 != 0) {
            return false;
        }
        if (this.zzbkb == null) {
            if (zzha.zzbkb != null) {
                return false;
            }
        } else if (!this.zzbkb.equals(zzha.zzbkb)) {
            return false;
        }
        if (this.zzbkc != zzha.zzbkc) {
            return false;
        }
        return (this.zzrj == null || this.zzrj.isEmpty()) ? zzha.zzrj == null || zzha.zzrj.isEmpty() : this.zzrj.equals(zzha.zzrj);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((this.zzbjj == null ? 0 : this.zzbjj.hashCode()) + (((((this.tag == null ? 0 : this.tag.hashCode()) + ((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.zzbjf ^ (this.zzbjf >>> 32)))) * 31) + ((int) (this.zzbjg ^ (this.zzbjg >>> 32)))) * 31 * 31)) * 31) + this.zzbji) * 31)) * 31 * 31) + 1237) * 31) + zzfy.hashCode((Object[]) this.zzbjm)) * 31) + Arrays.hashCode(this.zzbjn);
        zzge.zzd zzd = this.zzbjo;
        int hashCode2 = (this.zzbjr == null ? 0 : this.zzbjr.hashCode()) + (((this.zzbjq == null ? 0 : this.zzbjq.hashCode()) + (((((zzd == null ? 0 : zzd.hashCode()) + (hashCode * 31)) * 31) + Arrays.hashCode(this.zzbjp)) * 31)) * 31);
        zzgy zzgy = this.zzbjs;
        int hashCode3 = (((this.zzbjt == null ? 0 : this.zzbjt.hashCode()) + (((zzgy == null ? 0 : zzgy.hashCode()) + (hashCode2 * 31)) * 31)) * 31) + ((int) (this.zzbju ^ (this.zzbju >>> 32)));
        zzgz zzgz = this.zzbjv;
        int hashCode4 = ((((zzgz == null ? 0 : zzgz.hashCode()) + (hashCode3 * 31)) * 31) + Arrays.hashCode(this.zzbjw)) * 31;
        int hashCode5 = this.zzbjx == null ? 0 : this.zzbjx.hashCode();
        zzge.zzs zzs = this.zzbkb;
        int hashCode6 = ((this.zzbkc ? 1231 : 1237) + (((zzs == null ? 0 : zzs.hashCode()) + ((((hashCode5 + hashCode4) * 31 * 31) + zzfy.hashCode(this.zzbjz)) * 31 * 31)) * 31)) * 31;
        if (this.zzrj != null && !this.zzrj.isEmpty()) {
            i = this.zzrj.hashCode();
        }
        return hashCode6 + i;
    }

    public final void zza(zzfs zzfs) throws IOException {
        if (this.zzbjf != 0) {
            zzfs.zzi(1, this.zzbjf);
        }
        if (this.tag != null && !this.tag.equals("")) {
            zzfs.zza(2, this.tag);
        }
        if (this.zzbjm != null && this.zzbjm.length > 0) {
            for (zzhb zzhb : this.zzbjm) {
                if (zzhb != null) {
                    zzfs.zza(3, (zzfz) zzhb);
                }
            }
        }
        if (!Arrays.equals(this.zzbjn, zzgb.zzse)) {
            zzfs.zza(4, this.zzbjn);
        }
        if (!Arrays.equals(this.zzbjp, zzgb.zzse)) {
            zzfs.zza(6, this.zzbjp);
        }
        if (this.zzbjs != null) {
            zzfs.zza(7, (zzfz) this.zzbjs);
        }
        if (this.zzbjq != null && !this.zzbjq.equals("")) {
            zzfs.zza(8, this.zzbjq);
        }
        if (this.zzbjo != null) {
            zzfs.zze(9, this.zzbjo);
        }
        if (this.zzbji != 0) {
            zzfs.zzc(11, this.zzbji);
        }
        if (this.zzbjr != null && !this.zzbjr.equals("")) {
            zzfs.zza(13, this.zzbjr);
        }
        if (this.zzbjt != null && !this.zzbjt.equals("")) {
            zzfs.zza(14, this.zzbjt);
        }
        if (this.zzbju != 180000) {
            long j = this.zzbju;
            zzfs.zzb(15, 0);
            zzfs.zzn(zzfs.zzj(j));
        }
        if (this.zzbjv != null) {
            zzfs.zza(16, (zzfz) this.zzbjv);
        }
        if (this.zzbjg != 0) {
            zzfs.zzi(17, this.zzbjg);
        }
        if (!Arrays.equals(this.zzbjw, zzgb.zzse)) {
            zzfs.zza(18, this.zzbjw);
        }
        if (this.zzbjz != null && this.zzbjz.length > 0) {
            for (int zzc : this.zzbjz) {
                zzfs.zzc(20, zzc);
            }
        }
        if (0 != 0) {
            zzfs.zzi(21, 0);
        }
        if (0 != 0) {
            zzfs.zzi(22, 0);
        }
        if (this.zzbkb != null) {
            zzfs.zze(23, this.zzbkb);
        }
        if (this.zzbjx != null && !this.zzbjx.equals("")) {
            zzfs.zza(24, this.zzbjx);
        }
        if (this.zzbkc) {
            zzfs.zzb(25, this.zzbkc);
        }
        if (this.zzbjj != null && !this.zzbjj.equals("")) {
            zzfs.zza(26, this.zzbjj);
        }
        super.zza(zzfs);
    }

    /* access modifiers changed from: protected */
    public final int zzen() {
        int zzen = super.zzen();
        if (this.zzbjf != 0) {
            zzen += zzfs.zzd(1, this.zzbjf);
        }
        if (this.tag != null && !this.tag.equals("")) {
            zzen += zzfs.zzb(2, this.tag);
        }
        if (this.zzbjm != null && this.zzbjm.length > 0) {
            int i = zzen;
            for (zzhb zzhb : this.zzbjm) {
                if (zzhb != null) {
                    i += zzfs.zzb(3, (zzfz) zzhb);
                }
            }
            zzen = i;
        }
        if (!Arrays.equals(this.zzbjn, zzgb.zzse)) {
            zzen += zzfs.zzb(4, this.zzbjn);
        }
        if (!Arrays.equals(this.zzbjp, zzgb.zzse)) {
            zzen += zzfs.zzb(6, this.zzbjp);
        }
        if (this.zzbjs != null) {
            zzen += zzfs.zzb(7, (zzfz) this.zzbjs);
        }
        if (this.zzbjq != null && !this.zzbjq.equals("")) {
            zzen += zzfs.zzb(8, this.zzbjq);
        }
        if (this.zzbjo != null) {
            zzen += zzbn.zzc(9, (zzdo) this.zzbjo);
        }
        if (this.zzbji != 0) {
            zzen += zzfs.zzr(11) + zzfs.zzs(this.zzbji);
        }
        if (this.zzbjr != null && !this.zzbjr.equals("")) {
            zzen += zzfs.zzb(13, this.zzbjr);
        }
        if (this.zzbjt != null && !this.zzbjt.equals("")) {
            zzen += zzfs.zzb(14, this.zzbjt);
        }
        if (this.zzbju != 180000) {
            zzen += zzfs.zzr(15) + zzfs.zzo(zzfs.zzj(this.zzbju));
        }
        if (this.zzbjv != null) {
            zzen += zzfs.zzb(16, (zzfz) this.zzbjv);
        }
        if (this.zzbjg != 0) {
            zzen += zzfs.zzd(17, this.zzbjg);
        }
        if (!Arrays.equals(this.zzbjw, zzgb.zzse)) {
            zzen += zzfs.zzb(18, this.zzbjw);
        }
        if (this.zzbjz != null && this.zzbjz.length > 0) {
            int i2 = 0;
            for (int zzs : this.zzbjz) {
                i2 += zzfs.zzs(zzs);
            }
            zzen = zzen + i2 + (this.zzbjz.length * 2);
        }
        if (0 != 0) {
            zzen += zzfs.zzd(21, 0);
        }
        if (0 != 0) {
            zzen += zzfs.zzd(22, 0);
        }
        if (this.zzbkb != null) {
            zzen += zzbn.zzc(23, (zzdo) this.zzbkb);
        }
        if (this.zzbjx != null && !this.zzbjx.equals("")) {
            zzen += zzfs.zzb(24, this.zzbjx);
        }
        if (this.zzbkc) {
            zzen += zzfs.zzr(25) + 1;
        }
        return (this.zzbjj == null || this.zzbjj.equals("")) ? zzen : zzen + zzfs.zzb(26, this.zzbjj);
    }

    public final /* synthetic */ zzfu zzeo() throws CloneNotSupportedException {
        return (zzha) clone();
    }

    public final /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzha) clone();
    }
}
