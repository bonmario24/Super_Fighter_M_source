package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzgb;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzfy implements zzgb<zzp.C1797zzp> {
    private String zza;
    private String zzb;
    @Nullable
    private String zzc;
    @Nullable
    private String zzd;

    public zzfy(@Nullable String str) {
        this.zzd = str;
    }

    public zzfy(String str, String str2, @Nullable String str3, @Nullable String str4) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = Preconditions.checkNotEmpty(str2);
        this.zzc = null;
        this.zzd = str4;
    }

    public final /* synthetic */ zzjr zza() {
        zzp.C1797zzp.zza zza2 = zzp.C1797zzp.zza();
        if (this.zza != null) {
            zza2.zza(this.zza);
        }
        if (this.zzb != null) {
            zza2.zzb(this.zzb);
        }
        if (this.zzd != null) {
            zza2.zzc(this.zzd);
        }
        return (zzp.C1797zzp) ((zzig) zza2.zzf());
    }
}
