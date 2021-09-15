package com.google.android.gms.internal.firebase_auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ProviderUserInfoCreator")
@SafeParcelable.Reserved({1})
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzfj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfj> CREATOR = new zzfm();
    @SafeParcelable.Field(getter = "getFederatedId", mo21170id = 2)
    private String zza;
    @SafeParcelable.Field(getter = "getDisplayName", mo21170id = 3)
    private String zzb;
    @SafeParcelable.Field(getter = "getPhotoUrl", mo21170id = 4)
    private String zzc;
    @SafeParcelable.Field(getter = "getProviderId", mo21170id = 5)
    private String zzd;
    @SafeParcelable.Field(getter = "getRawUserInfo", mo21170id = 6)
    private String zze;
    @SafeParcelable.Field(getter = "getPhoneNumber", mo21170id = 7)
    private String zzf;
    @SafeParcelable.Field(getter = "getEmail", mo21170id = 8)
    private String zzg;

    public zzfj() {
    }

    @SafeParcelable.Constructor
    zzfj(@SafeParcelable.Param(mo21173id = 2) String str, @SafeParcelable.Param(mo21173id = 3) String str2, @SafeParcelable.Param(mo21173id = 4) String str3, @SafeParcelable.Param(mo21173id = 5) String str4, @SafeParcelable.Param(mo21173id = 6) String str5, @SafeParcelable.Param(mo21173id = 7) String str6, @SafeParcelable.Param(mo21173id = 8) String str7) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = str5;
        this.zzf = str6;
        this.zzg = str7;
    }

    public final String zza() {
        return this.zza;
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }

    @Nullable
    public final Uri zzc() {
        if (!TextUtils.isEmpty(this.zzc)) {
            return Uri.parse(this.zzc);
        }
        return null;
    }

    public final String zzd() {
        return this.zzd;
    }

    public final String zze() {
        return this.zzf;
    }

    public final void zza(String str) {
        this.zze = str;
    }

    @Nullable
    public final String zzf() {
        return this.zze;
    }

    @Nullable
    public final String zzg() {
        return this.zzg;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 6, this.zze, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
