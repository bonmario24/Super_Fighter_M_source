package com.google.android.gms.internal.firebase_auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
abstract class zzba extends zzae<String> {
    final CharSequence zza;
    private final zzaf zzb;
    private final boolean zzc;
    private int zzd = 0;
    private int zze;

    protected zzba(zzax zzax, CharSequence charSequence) {
        this.zzb = zzax.zza;
        this.zzc = false;
        this.zze = zzax.zzd;
        this.zza = charSequence;
    }

    /* access modifiers changed from: package-private */
    public abstract int zza(int i);

    /* access modifiers changed from: package-private */
    public abstract int zzb(int i);

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zza() {
        int i;
        int i2 = this.zzd;
        while (this.zzd != -1) {
            int zza2 = zza(this.zzd);
            if (zza2 == -1) {
                zza2 = this.zza.length();
                this.zzd = -1;
            } else {
                this.zzd = zzb(zza2);
            }
            if (this.zzd == i2) {
                this.zzd++;
                if (this.zzd > this.zza.length()) {
                    this.zzd = -1;
                }
            } else {
                int i3 = i2;
                while (i3 < zza2 && this.zzb.zza(this.zza.charAt(i3))) {
                    i3++;
                }
                int i4 = zza2;
                while (i > i3 && this.zzb.zza(this.zza.charAt(i - 1))) {
                    i4 = i - 1;
                }
                if (!this.zzc || i3 != i) {
                    if (this.zze == 1) {
                        i = this.zza.length();
                        this.zzd = -1;
                        while (i > i3 && this.zzb.zza(this.zza.charAt(i - 1))) {
                            i--;
                        }
                    } else {
                        this.zze--;
                    }
                    return this.zza.subSequence(i3, i).toString();
                }
                i2 = this.zzd;
            }
        }
        zzb();
        return null;
    }
}
