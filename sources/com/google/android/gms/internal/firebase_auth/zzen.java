package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzgb;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzen implements zzgb<zzp.zza> {
    private String zza;
    private String zzb = "http://localhost";
    @Nullable
    private final String zzc;

    public zzen(String str, @Nullable String str2) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzc = str2;
    }

    public final /* synthetic */ zzjr zza() {
        zzp.zza.C1794zza zzb2 = zzp.zza.zza().zza(this.zza).zzb(this.zzb);
        if (this.zzc != null) {
            zzb2.zzc(this.zzc);
        }
        return (zzp.zza) ((zzig) zzb2.zzf());
    }
}
