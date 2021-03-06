package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.ActionCodeSettings;

@SafeParcelable.Class(creator = "SendEmailVerificationWithSettingsAidlRequestCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzdc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdc> CREATOR = new zzdb();
    @SafeParcelable.Field(getter = "getToken", mo21170id = 1)
    private final String zza;
    @SafeParcelable.Field(getter = "getActionCodeSettings", mo21170id = 2)
    @Nullable
    private final ActionCodeSettings zzb;

    @SafeParcelable.Constructor
    public zzdc(@SafeParcelable.Param(mo21173id = 1) String str, @SafeParcelable.Param(mo21173id = 2) @Nullable ActionCodeSettings actionCodeSettings) {
        this.zza = str;
        this.zzb = actionCodeSettings;
    }

    public final String zza() {
        return this.zza;
    }

    @Nullable
    public final ActionCodeSettings zzb() {
        return this.zzb;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
