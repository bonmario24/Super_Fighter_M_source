package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzgc;

@SafeParcelable.Class(creator = "DefaultOAuthCredentialCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public class zze extends OAuthCredential {
    public static final Parcelable.Creator<zze> CREATOR = new zzd();
    @SafeParcelable.Field(getter = "getProvider", mo21170id = 1)
    @Nullable
    private final String zza;
    @SafeParcelable.Field(getter = "getIdToken", mo21170id = 2)
    @Nullable
    private final String zzb;
    @SafeParcelable.Field(getter = "getAccessToken", mo21170id = 3)
    @Nullable
    private final String zzc;
    @SafeParcelable.Field(getter = "getWebSignInCredential", mo21170id = 4)
    @Nullable
    private final zzgc zzd;
    @SafeParcelable.Field(getter = "getPendingToken", mo21170id = 5)
    @Nullable
    private final String zze;
    @SafeParcelable.Field(getter = "getSecret", mo21170id = 6)
    @Nullable
    private final String zzf;
    @SafeParcelable.Field(getter = "getRawNonce", mo21170id = 7)
    @Nullable
    private final String zzg;

    @SafeParcelable.Constructor
    zze(@SafeParcelable.Param(mo21173id = 1) @NonNull String str, @SafeParcelable.Param(mo21173id = 2) @Nullable String str2, @SafeParcelable.Param(mo21173id = 3) @Nullable String str3, @SafeParcelable.Param(mo21173id = 4) @Nullable zzgc zzgc, @SafeParcelable.Param(mo21173id = 5) @Nullable String str4, @SafeParcelable.Param(mo21173id = 6) @Nullable String str5, @SafeParcelable.Param(mo21173id = 7) @Nullable String str6) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzgc;
        this.zze = str4;
        this.zzf = str5;
        this.zzg = str6;
    }

    public static zze zza(String str, String str2, String str3) {
        return zza(str, str2, str3, (String) null, (String) null);
    }

    public static zze zza(String str, String str2, String str3, @Nullable String str4, @Nullable String str5) {
        Preconditions.checkNotEmpty(str, "Must specify a non-empty providerId");
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            return new zze(str, str2, str3, (zzgc) null, str4, str5, (String) null);
        }
        throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
    }

    public static zze zza(@NonNull zzgc zzgc) {
        Preconditions.checkNotNull(zzgc, "Must specify a non-null webSignInCredential");
        return new zze((String) null, (String) null, (String) null, zzgc, (String) null, (String) null, (String) null);
    }

    static zze zza(String str, @Nullable String str2, @Nullable String str3, @NonNull String str4) {
        Preconditions.checkNotEmpty(str, "Must specify a non-empty providerId");
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            return new zze(str, str2, str3, (zzgc) null, (String) null, (String) null, str4);
        }
        throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
    }

    public String getProvider() {
        return this.zza;
    }

    public String getSignInMethod() {
        return this.zza;
    }

    @Nullable
    public String getIdToken() {
        return this.zzb;
    }

    @Nullable
    public String getAccessToken() {
        return this.zzc;
    }

    @Nullable
    public String getSecret() {
        return this.zzf;
    }

    public static zzgc zza(@NonNull zze zze2, @Nullable String str) {
        Preconditions.checkNotNull(zze2);
        if (zze2.zzd != null) {
            return zze2.zzd;
        }
        return new zzgc(zze2.getIdToken(), zze2.getAccessToken(), zze2.getProvider(), (String) null, zze2.getSecret(), (String) null, str, zze2.zze, zze2.zzg);
    }

    public final AuthCredential zza() {
        return new zze(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getProvider(), false);
        SafeParcelWriter.writeString(parcel, 2, getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 3, getAccessToken(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, getSecret(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
