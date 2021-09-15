package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "EmailAuthCredentialCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public class EmailAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<EmailAuthCredential> CREATOR = new zzf();
    @SafeParcelable.Field(getter = "getEmail", mo21170id = 1)
    private String zza;
    @SafeParcelable.Field(getter = "getPassword", mo21170id = 2)
    private String zzb;
    @SafeParcelable.Field(getter = "getSignInLink", mo21170id = 3)
    private final String zzc;
    @SafeParcelable.Field(getter = "getCachedState", mo21170id = 4)
    private String zzd;
    @SafeParcelable.Field(getter = "isForLinking", mo21170id = 5)
    private boolean zze;

    @SafeParcelable.Constructor
    EmailAuthCredential(@SafeParcelable.Param(mo21173id = 1) @NonNull String str, @SafeParcelable.Param(mo21173id = 2) @NonNull String str2, @SafeParcelable.Param(mo21173id = 3) @NonNull String str3, @SafeParcelable.Param(mo21173id = 4) @NonNull String str4, @SafeParcelable.Param(mo21173id = 5) @NonNull boolean z) {
        this.zza = Preconditions.checkNotEmpty(str);
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            this.zzb = str2;
            this.zzc = str3;
            this.zzd = str4;
            this.zze = z;
            return;
        }
        throw new IllegalArgumentException("Cannot create an EmailAuthCredential without a password or emailLink.");
    }

    EmailAuthCredential(String str, String str2) {
        this(str, str2, (String) null, (String) null, false);
    }

    @NonNull
    public final String zzb() {
        return this.zza;
    }

    @NonNull
    public final String zzc() {
        return this.zzb;
    }

    @NonNull
    public final String zzd() {
        return this.zzc;
    }

    @Nullable
    public final String zze() {
        return this.zzd;
    }

    public final boolean zzf() {
        return this.zze;
    }

    public final EmailAuthCredential zza(@Nullable FirebaseUser firebaseUser) {
        this.zzd = firebaseUser.zzf();
        this.zze = true;
        return this;
    }

    @NonNull
    public String getProvider() {
        return "password";
    }

    @NonNull
    public String getSignInMethod() {
        if (!TextUtils.isEmpty(this.zzb)) {
            return "password";
        }
        return EmailAuthProvider.EMAIL_LINK_SIGN_IN_METHOD;
    }

    public final boolean zzg() {
        return !TextUtils.isEmpty(this.zzc);
    }

    public static boolean zza(@NonNull String str) {
        ActionCodeUrl parseLink;
        if (!TextUtils.isEmpty(str) && (parseLink = ActionCodeUrl.parseLink(str)) != null && parseLink.getOperation() == 4) {
            return true;
        }
        return false;
    }

    public final AuthCredential zza() {
        return new EmailAuthCredential(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
