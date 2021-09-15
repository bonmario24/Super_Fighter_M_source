package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzgb;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzfn implements zzgb<zzp.zzj> {
    private final String zza;
    @Nullable
    private final String zzb;
    @Nullable
    private final String zzc;

    public zzfn(String str, @Nullable String str2, @Nullable String str3) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = str2;
        this.zzc = str3;
    }

    public final /* synthetic */ zzjr zza() {
        zzp.zzj.zza zza2 = zzp.zzj.zza().zza(this.zza);
        if (this.zzb != null) {
            zza2.zzb(this.zzb);
        }
        if (this.zzc != null) {
            zza2.zzc(this.zzc);
        }
        return (zzp.zzj) ((zzig) zza2.zzf());
    }
}
