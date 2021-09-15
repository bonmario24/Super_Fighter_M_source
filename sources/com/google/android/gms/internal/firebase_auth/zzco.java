package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.PhoneAuthCredential;

@SafeParcelable.Class(creator = "FinalizeMfaSignInAidlRequestCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzco extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzco> CREATOR = new zzcn();
    @SafeParcelable.Field(getter = "getPhoneAuthCredential", mo21170id = 1)
    private final PhoneAuthCredential zza;
    @SafeParcelable.Field(getter = "getPendingCredential", mo21170id = 2)
    private final String zzb;

    @SafeParcelable.Constructor
    public zzco(@SafeParcelable.Param(mo21173id = 1) PhoneAuthCredential phoneAuthCredential, @SafeParcelable.Param(mo21173id = 2) String str) {
        this.zza = phoneAuthCredential;
        this.zzb = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
