package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzhd implements zzgk {
    private final zzgm zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    zzhd(zzgm zzgm, String str, Object[] objArr) {
        this.zza = zzgm;
        this.zzb = str;
        this.zzc = objArr;
        int i = 1;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.zzd = charAt;
            return;
        }
        char c = charAt & 8191;
        int i2 = 13;
        while (true) {
            int i3 = i + 1;
            char charAt2 = str.charAt(i);
            if (charAt2 >= 55296) {
                c |= (charAt2 & 8191) << i2;
                i2 += 13;
                i = i3;
            } else {
                this.zzd = (charAt2 << i2) | c;
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzd() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zze() {
        return this.zzc;
    }

    public final zzgm zzc() {
        return this.zza;
    }

    public final int zza() {
        return (this.zzd & 1) == 1 ? zzfe.zzf.zzh : zzfe.zzf.zzi;
    }

    public final boolean zzb() {
        return (this.zzd & 2) == 2;
    }
}
