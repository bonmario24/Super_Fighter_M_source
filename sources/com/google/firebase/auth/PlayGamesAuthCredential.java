package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzgc;

@SafeParcelable.Class(creator = "PlayGamesAuthCredentialCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public class PlayGamesAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<PlayGamesAuthCredential> CREATOR = new zzz();
    @SafeParcelable.Field(getter = "getServerAuthCode", mo21170id = 1)
    private final String zza;

    @SafeParcelable.Constructor
    PlayGamesAuthCredential(@SafeParcelable.Param(mo21173id = 1) @NonNull String str) {
        this.zza = Preconditions.checkNotEmpty(str);
    }

    @NonNull
    public String getProvider() {
        return "playgames.google.com";
    }

    @NonNull
    public String getSignInMethod() {
        return "playgames.google.com";
    }

    public static zzgc zza(@NonNull PlayGamesAuthCredential playGamesAuthCredential, @Nullable String str) {
        Preconditions.checkNotNull(playGamesAuthCredential);
        return new zzgc((String) null, (String) null, playGamesAuthCredential.getProvider(), (String) null, (String) null, playGamesAuthCredential.zza, str, (String) null, (String) null);
    }

    public final AuthCredential zza() {
        return new PlayGamesAuthCredential(this.zza);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
