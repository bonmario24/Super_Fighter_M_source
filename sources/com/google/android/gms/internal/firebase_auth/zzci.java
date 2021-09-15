package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "CreateUserWithEmailAndPasswordAidlRequestCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzci extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzci> CREATOR = new zzch();
    @SafeParcelable.Field(getter = "getEmail", mo21170id = 1)
    private final String zza;
    @SafeParcelable.Field(getter = "getPassword", mo21170id = 2)
    private final String zzb;
    @SafeParcelable.Field(getter = "getTenantId", mo21170id = 3)
    @Nullable
    private final String zzc;

    @SafeParcelable.Constructor
    public zzci(@SafeParcelable.Param(mo21173id = 1) String str, @SafeParcelable.Param(mo21173id = 2) String str2, @SafeParcelable.Param(mo21173id = 3) @Nullable String str3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
    }

    public final String zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    @Nullable
    public final String zzc() {
        return this.zzc;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}