package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.AdditionalUserInfo;
import java.util.Map;
import javax.annotation.Nullable;

@SafeParcelable.Class(creator = "DefaultAdditionalUserInfoCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzh implements AdditionalUserInfo {
    public static final Parcelable.Creator<zzh> CREATOR = new zzg();
    @SafeParcelable.Field(getter = "getProviderId", mo21170id = 1)
    private final String zza;
    @SafeParcelable.Field(getter = "getRawUserInfo", mo21170id = 2)
    private final String zzb;
    private Map<String, Object> zzc;
    @SafeParcelable.Field(getter = "isNewUser", mo21170id = 3)
    private boolean zzd;

    @SafeParcelable.Constructor
    public zzh(@SafeParcelable.Param(mo21173id = 1) String str, @SafeParcelable.Param(mo21173id = 2) String str2, @SafeParcelable.Param(mo21173id = 3) boolean z) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzas.zzb(str2);
        this.zzd = z;
    }

    public zzh(boolean z) {
        this.zzd = z;
        this.zzb = null;
        this.zza = null;
        this.zzc = null;
    }

    @Nullable
    public final String getProviderId() {
        return this.zza;
    }

    @Nullable
    public final Map<String, Object> getProfile() {
        return this.zzc;
    }

    @Nullable
    public final String getUsername() {
        if ("github.com".equals(this.zza)) {
            return (String) this.zzc.get("login");
        }
        if ("twitter.com".equals(this.zza)) {
            return (String) this.zzc.get("screen_name");
        }
        return null;
    }

    public final boolean isNewUser() {
        return this.zzd;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getProviderId(), false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeBoolean(parcel, 3, isNewUser());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int describeContents() {
        return 0;
    }
}
