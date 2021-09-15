package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzbk;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzjy;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
final class zzu extends zzv {
    private zzbk.zze zzg;
    private final /* synthetic */ zzo zzh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzu(zzo zzo, String str, int i, zzbk.zze zze) {
        super(str, i);
        this.zzh = zzo;
        this.zzg = zze;
    }

    /* access modifiers changed from: package-private */
    public final int zza() {
        return this.zzg.zzb();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(Long l, Long l2, zzbs.zzk zzk, boolean z) {
        boolean z2;
        Object obj;
        Integer num;
        Boolean bool = null;
        boolean z3 = zzjy.zzb() && this.zzh.zzt().zzd(this.zza, zzaq.zzbe);
        boolean zze = this.zzg.zze();
        boolean zzf = this.zzg.zzf();
        boolean zzh2 = this.zzg.zzh();
        if (zze || zzf || zzh2) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z || z2) {
            zzbk.zzc zzd = this.zzg.zzd();
            boolean zzf2 = zzd.zzf();
            if (zzk.zzf()) {
                if (!zzd.zzc()) {
                    this.zzh.zzr().zzi().zza("No number filter for long property. property", this.zzh.zzo().zzc(zzk.zzc()));
                } else {
                    bool = zza(zza(zzk.zzg(), zzd.zzd()), zzf2);
                }
            } else if (zzk.zzh()) {
                if (!zzd.zzc()) {
                    this.zzh.zzr().zzi().zza("No number filter for double property. property", this.zzh.zzo().zzc(zzk.zzc()));
                } else {
                    bool = zza(zza(zzk.zzi(), zzd.zzd()), zzf2);
                }
            } else if (!zzk.zzd()) {
                this.zzh.zzr().zzi().zza("User property has no value, property", this.zzh.zzo().zzc(zzk.zzc()));
            } else if (zzd.zza()) {
                bool = zza(zza(zzk.zze(), zzd.zzb(), this.zzh.zzr()), zzf2);
            } else if (!zzd.zzc()) {
                this.zzh.zzr().zzi().zza("No string or number filter defined. property", this.zzh.zzo().zzc(zzk.zzc()));
            } else if (zzki.zza(zzk.zze())) {
                bool = zza(zza(zzk.zze(), zzd.zzd()), zzf2);
            } else {
                this.zzh.zzr().zzi().zza("Invalid user property value for Numeric number filter. property, value", this.zzh.zzo().zzc(zzk.zzc()), zzk.zze());
            }
            zzeu zzx = this.zzh.zzr().zzx();
            if (bool == null) {
                obj = "null";
            } else {
                obj = bool;
            }
            zzx.zza("Property filter result", obj);
            if (bool == null) {
                return false;
            }
            this.zzc = true;
            if (zzh2 && !bool.booleanValue()) {
                return true;
            }
            if (!z || this.zzg.zze()) {
                this.zzd = bool;
            }
            if (!bool.booleanValue() || !z2 || !zzk.zza()) {
                return true;
            }
            long zzb = zzk.zzb();
            if (l != null) {
                zzb = l.longValue();
            }
            if (z3 && this.zzg.zze() && !this.zzg.zzf() && l2 != null) {
                zzb = l2.longValue();
            }
            if (this.zzg.zzf()) {
                this.zzf = Long.valueOf(zzb);
                return true;
            }
            this.zze = Long.valueOf(zzb);
            return true;
        }
        zzeu zzx2 = this.zzh.zzr().zzx();
        Integer valueOf = Integer.valueOf(this.zzb);
        if (this.zzg.zza()) {
            num = Integer.valueOf(this.zzg.zzb());
        } else {
            num = null;
        }
        zzx2.zza("Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", valueOf, num);
        return true;
    }
}
