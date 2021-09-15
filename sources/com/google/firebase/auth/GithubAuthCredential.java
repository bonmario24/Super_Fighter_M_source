package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzgc;

@SafeParcelable.Class(creator = "GithubAuthCredentialCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public class GithubAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<GithubAuthCredential> CREATOR = new zzt();
    @SafeParcelable.Field(getter = "getToken", mo21170id = 1)
    private String zza;

    @SafeParcelable.Constructor
    GithubAuthCredential(@SafeParcelable.Param(mo21173id = 1) @NonNull String str) {
        this.zza = Preconditions.checkNotEmpty(str);
    }

    @NonNull
    public String getProvider() {
        return "github.com";
    }

    @NonNull
    public String getSignInMethod() {
        return "github.com";
    }

    public static zzgc zza(@NonNull GithubAuthCredential githubAuthCredential, @Nullable String str) {
        Preconditions.checkNotNull(githubAuthCredential);
        return new zzgc((String) null, githubAuthCredential.zza, githubAuthCredential.getProvider(), (String) null, (String) null, (String) null, str, (String) null, (String) null);
    }

    public final AuthCredential zza() {
        return new GithubAuthCredential(this.zza);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
