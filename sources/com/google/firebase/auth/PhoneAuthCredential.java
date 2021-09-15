package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "PhoneAuthCredentialCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public class PhoneAuthCredential extends AuthCredential implements Cloneable {
    public static final Parcelable.Creator<PhoneAuthCredential> CREATOR = new zzw();
    @SafeParcelable.Field(getter = "getSessionInfo", mo21170id = 1)
    private String zza;
    @SafeParcelable.Field(getter = "getSmsCode", mo21170id = 2)
    private String zzb;
    @SafeParcelable.Field(getter = "getHasVerificationProof", mo21170id = 3)
    private boolean zzc;
    @SafeParcelable.Field(getter = "getPhoneNumber", mo21170id = 4)
    private String zzd;
    @SafeParcelable.Field(getter = "getAutoCreate", mo21170id = 5)
    private boolean zze;
    @SafeParcelable.Field(getter = "getTemporaryProof", mo21170id = 6)
    private String zzf;
    @SafeParcelable.Field(getter = "getMfaEnrollmentId", mo21170id = 7)
    @Nullable
    private String zzg;

    @SafeParcelable.Constructor
    PhoneAuthCredential(@SafeParcelable.Param(mo21173id = 1) @Nullable String str, @SafeParcelable.Param(mo21173id = 2) @Nullable String str2, @SafeParcelable.Param(mo21173id = 3) boolean z, @SafeParcelable.Param(mo21173id = 4) @Nullable String str3, @SafeParcelable.Param(mo21173id = 5) boolean z2, @SafeParcelable.Param(mo21173id = 6) @Nullable String str4, @SafeParcelable.Param(mo21173id = 7) @Nullable String str5) {
        Preconditions.checkArgument((z && !TextUtils.isEmpty(str3) && TextUtils.isEmpty(str5)) || (z && TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str5)) || ((!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) || (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4))), "Cannot create PhoneAuthCredential without either verificationProof, sessionInfo, temporary proof, or enrollment ID.");
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = str3;
        this.zze = z2;
        this.zzf = str4;
        this.zzg = str5;
    }

    public static PhoneAuthCredential zza(String str, String str2) {
        return new PhoneAuthCredential(str, str2, false, (String) null, true, (String) null, (String) null);
    }

    public static PhoneAuthCredential zzb(@NonNull String str, @NonNull String str2) {
        return new PhoneAuthCredential((String) null, (String) null, false, str, true, str2, (String) null);
    }

    public final String zzb() {
        return this.zza;
    }

    @Nullable
    public String getSmsCode() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzd;
    }

    @NonNull
    public String getProvider() {
        return "phone";
    }

    @NonNull
    public String getSignInMethod() {
        return "phone";
    }

    public final PhoneAuthCredential zza(boolean z) {
        this.zze = false;
        return this;
    }

    public final boolean zzd() {
        return this.zze;
    }

    public final String zze() {
        return this.zzf;
    }

    public final AuthCredential zza() {
        return (PhoneAuthCredential) clone();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, getSmsCode(), false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.writeString(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new PhoneAuthCredential(this.zza, getSmsCode(), this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
    }
}
