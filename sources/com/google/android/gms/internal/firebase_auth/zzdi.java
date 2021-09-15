package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SetFirebaseUiVersionAidlRequestCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzdi extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdi> CREATOR = new zzdh();
    @SafeParcelable.Field(getter = "getFirebaseUiVersion", mo21170id = 1)
    private final String zza;

    @SafeParcelable.Constructor
    public zzdi(@SafeParcelable.Param(mo21173id = 1) String str) {
        this.zza = str;
    }

    public final String zza() {
        return this.zza;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
