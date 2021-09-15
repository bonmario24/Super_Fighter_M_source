package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
public class zzfv {
    private static final zzer zza = zzer.zza();
    private zzdw zzb;
    private volatile zzgm zzc;
    private volatile zzdw zzd;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfv)) {
            return false;
        }
        zzfv zzfv = (zzfv) obj;
        zzgm zzgm = this.zzc;
        zzgm zzgm2 = zzfv.zzc;
        if (zzgm == null && zzgm2 == null) {
            return zzc().equals(zzfv.zzc());
        }
        if (zzgm != null && zzgm2 != null) {
            return zzgm.equals(zzgm2);
        }
        if (zzgm != null) {
            return zzgm.equals(zzfv.zzb(zzgm.mo23726h_()));
        }
        return zzb(zzgm2.mo23726h_()).equals(zzgm2);
    }

    public int hashCode() {
        return 1;
    }

    private final zzgm zzb(zzgm zzgm) {
        if (this.zzc == null) {
            synchronized (this) {
                if (this.zzc == null) {
                    try {
                        this.zzc = zzgm;
                        this.zzd = zzdw.zza;
                    } catch (zzfm e) {
                        this.zzc = zzgm;
                        this.zzd = zzdw.zza;
                    }
                }
            }
        }
        return this.zzc;
    }

    public final zzgm zza(zzgm zzgm) {
        zzgm zzgm2 = this.zzc;
        this.zzb = null;
        this.zzd = null;
        this.zzc = zzgm;
        return zzgm2;
    }

    public final int zzb() {
        if (this.zzd != null) {
            return this.zzd.zza();
        }
        if (this.zzc != null) {
            return this.zzc.zzbm();
        }
        return 0;
    }

    public final zzdw zzc() {
        if (this.zzd != null) {
            return this.zzd;
        }
        synchronized (this) {
            if (this.zzd != null) {
                zzdw zzdw = this.zzd;
                return zzdw;
            }
            if (this.zzc == null) {
                this.zzd = zzdw.zza;
            } else {
                this.zzd = this.zzc.zzbh();
            }
            zzdw zzdw2 = this.zzd;
            return zzdw2;
        }
    }
}
