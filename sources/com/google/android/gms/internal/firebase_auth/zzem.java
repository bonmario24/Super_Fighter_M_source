package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.internal.zzar;
import com.google.firebase.auth.zze;
import java.util.List;

@SafeParcelable.Class(creator = "OnFailedMfaSignInAidlResponseCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzem> CREATOR = new zzel();
    @SafeParcelable.Field(getter = "getMfaPendingCredential", mo21170id = 1)
    private String zza;
    @SafeParcelable.Field(getter = "getMfaInfoList", mo21170id = 2)
    private List<zzfh> zzb;
    @SafeParcelable.Field(getter = "getDefaultOAuthCredential", mo21170id = 3)
    private zze zzc;

    @SafeParcelable.Constructor
    public zzem(@SafeParcelable.Param(mo21173id = 1) String str, @SafeParcelable.Param(mo21173id = 2) List<zzfh> list, @SafeParcelable.Param(mo21173id = 3) @Nullable zze zze) {
        this.zza = str;
        this.zzb = list;
        this.zzc = zze;
    }

    public final String zza() {
        return this.zza;
    }

    public final zze zzb() {
        return this.zzc;
    }

    public final List<MultiFactorInfo> zzc() {
        return zzar.zza(this.zzb);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
