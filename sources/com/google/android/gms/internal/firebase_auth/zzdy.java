package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.PhoneMultiFactorInfo;

@SafeParcelable.Class(creator = "StartMfaPhoneNumberSignInAidlRequestCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzdy extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdy> CREATOR = new zzdx();
    @SafeParcelable.Field(getter = "getPhoneMultiFactorInfo", mo21170id = 1)
    private PhoneMultiFactorInfo zza;
    @SafeParcelable.Field(getter = "getPendingCredential", mo21170id = 2)
    private final String zzb;
    @SafeParcelable.Field(getter = "getLocaleHeader", mo21170id = 3)
    @Nullable
    private final String zzc;
    @SafeParcelable.Field(getter = "getTimeoutInSeconds", mo21170id = 4)
    private final long zzd;
    @SafeParcelable.Field(getter = "getForceNewSmsVerificationSession", mo21170id = 5)
    private final boolean zze;
    @SafeParcelable.Field(getter = "getRequireSmsVerification", mo21170id = 6)
    private final boolean zzf;

    @SafeParcelable.Constructor
    public zzdy(@SafeParcelable.Param(mo21173id = 1) PhoneMultiFactorInfo phoneMultiFactorInfo, @SafeParcelable.Param(mo21173id = 2) String str, @SafeParcelable.Param(mo21173id = 3) @Nullable String str2, @SafeParcelable.Param(mo21173id = 4) long j, @SafeParcelable.Param(mo21173id = 5) boolean z, @SafeParcelable.Param(mo21173id = 6) boolean z2) {
        this.zza = phoneMultiFactorInfo;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = j;
        this.zze = z;
        this.zzf = z2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeLong(parcel, 4, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
