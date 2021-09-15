package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzgb;

@SafeParcelable.Class(creator = "SendVerificationCodeRequestCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzfr extends AbstractSafeParcelable implements zzgb<zzp.zzl> {
    public static final Parcelable.Creator<zzfr> CREATOR = new zzfu();
    @SafeParcelable.Field(getter = "getPhoneNumber", mo21170id = 1)
    private final String zza;
    @SafeParcelable.Field(getter = "getTimeoutInSeconds", mo21170id = 2)
    private final long zzb;
    @SafeParcelable.Field(getter = "getForceNewSmsVerificationSession", mo21170id = 3)
    private final boolean zzc;
    @SafeParcelable.Field(getter = "getLanguageHeader", mo21170id = 4)
    private final String zzd;
    @SafeParcelable.Field(getter = "getTenantId", mo21170id = 5)
    @Nullable
    private final String zze;
    @SafeParcelable.Field(getter = "getRecaptchaToken", mo21170id = 6)
    @Nullable
    private final String zzf;
    @Nullable
    private zzeo zzg;

    @SafeParcelable.Constructor
    public zzfr(@SafeParcelable.Param(mo21173id = 1) String str, @SafeParcelable.Param(mo21173id = 2) long j, @SafeParcelable.Param(mo21173id = 3) boolean z, @SafeParcelable.Param(mo21173id = 4) String str2, @SafeParcelable.Param(mo21173id = 5) @Nullable String str3, @SafeParcelable.Param(mo21173id = 6) @Nullable String str4) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = j;
        this.zzc = z;
        this.zzd = str2;
        this.zze = str3;
        this.zzf = str4;
    }

    public final String zzb() {
        return this.zza;
    }

    public final void zza(zzeo zzeo) {
        this.zzg = zzeo;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* synthetic */ zzjr zza() {
        zzp.zzl.zzb zza2 = zzp.zzl.zza().zza(this.zza);
        if (this.zze != null) {
            zza2.zzc(this.zze);
        }
        if (this.zzf != null) {
            zza2.zzb(this.zzf);
        }
        if (this.zzg != null) {
            zza2.zza((zzp.zzl.zza) this.zzg.zza());
        }
        return (zzp.zzl) ((zzig) zza2.zzf());
    }
}
