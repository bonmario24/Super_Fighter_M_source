package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzgc;

@SafeParcelable.Class(creator = "TwitterAuthCredentialCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public class TwitterAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<TwitterAuthCredential> CREATOR = new zzaa();
    @SafeParcelable.Field(getter = "getToken", mo21170id = 1)
    private String zza;
    @SafeParcelable.Field(getter = "getSecret", mo21170id = 2)
    private String zzb;

    @SafeParcelable.Constructor
    TwitterAuthCredential(@SafeParcelable.Param(mo21173id = 1) @NonNull String str, @SafeParcelable.Param(mo21173id = 2) @NonNull String str2) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = Preconditions.checkNotEmpty(str2);
    }

    @NonNull
    public String getProvider() {
        return "twitter.com";
    }

    @NonNull
    public String getSignInMethod() {
        return "twitter.com";
    }

    public static zzgc zza(@NonNull TwitterAuthCredential twitterAuthCredential, @Nullable String str) {
        Preconditions.checkNotNull(twitterAuthCredential);
        return new zzgc((String) null, twitterAuthCredential.zza, twitterAuthCredential.getProvider(), (String) null, twitterAuthCredential.zzb, (String) null, str, (String) null, (String) null);
    }

    public final AuthCredential zza() {
        return new TwitterAuthCredential(this.zza, this.zzb);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
