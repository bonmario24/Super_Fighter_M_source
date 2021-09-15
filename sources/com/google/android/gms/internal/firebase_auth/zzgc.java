package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzgb;

@SafeParcelable.Class(creator = "VerifyAssertionRequestCreator")
@SafeParcelable.Reserved({1})
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzgc extends AbstractSafeParcelable implements zzgb<zzp.zzr> {
    public static final Parcelable.Creator<zzgc> CREATOR = new zzgb();
    @SafeParcelable.Field(getter = "getRequestUri", mo21170id = 2)
    private String zza;
    @SafeParcelable.Field(getter = "getCurrentIdToken", mo21170id = 3)
    private String zzb;
    @SafeParcelable.Field(getter = "getIdToken", mo21170id = 4)
    private String zzc;
    @SafeParcelable.Field(getter = "getAccessToken", mo21170id = 5)
    private String zzd;
    @SafeParcelable.Field(getter = "getProviderId", mo21170id = 6)
    private String zze;
    @SafeParcelable.Field(getter = "getEmail", mo21170id = 7)
    @Nullable
    private String zzf;
    @SafeParcelable.Field(getter = "getPostBody", mo21170id = 8)
    private String zzg;
    @SafeParcelable.Field(getter = "getOauthTokenSecret", mo21170id = 9)
    private String zzh;
    @SafeParcelable.Field(getter = "getReturnSecureToken", mo21170id = 10)
    private boolean zzi;
    @SafeParcelable.Field(getter = "getAutoCreate", mo21170id = 11)
    private boolean zzj;
    @SafeParcelable.Field(getter = "getAuthCode", mo21170id = 12)
    private String zzk;
    @SafeParcelable.Field(getter = "getSessionId", mo21170id = 13)
    private String zzl;
    @SafeParcelable.Field(getter = "getIdpResponseUrl", mo21170id = 14)
    private String zzm;
    @SafeParcelable.Field(getter = "getTenantId", mo21170id = 15)
    private String zzn;
    @SafeParcelable.Field(getter = "getReturnIdpCredential", mo21170id = 16)
    private boolean zzo;
    @SafeParcelable.Field(getter = "getPendingToken", mo21170id = 17)
    @Nullable
    private String zzp;

    public zzgc() {
        this.zzi = true;
        this.zzj = true;
    }

    @SafeParcelable.Constructor
    zzgc(@SafeParcelable.Param(mo21173id = 2) String str, @SafeParcelable.Param(mo21173id = 3) String str2, @SafeParcelable.Param(mo21173id = 4) String str3, @SafeParcelable.Param(mo21173id = 5) String str4, @SafeParcelable.Param(mo21173id = 6) String str5, @SafeParcelable.Param(mo21173id = 7) String str6, @SafeParcelable.Param(mo21173id = 8) String str7, @SafeParcelable.Param(mo21173id = 9) String str8, @SafeParcelable.Param(mo21173id = 10) boolean z, @SafeParcelable.Param(mo21173id = 11) boolean z2, @SafeParcelable.Param(mo21173id = 12) String str9, @SafeParcelable.Param(mo21173id = 13) String str10, @SafeParcelable.Param(mo21173id = 14) String str11, @SafeParcelable.Param(mo21173id = 15) String str12, @SafeParcelable.Param(mo21173id = 16) boolean z3, @SafeParcelable.Param(mo21173id = 17) String str13) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = str5;
        this.zzf = str6;
        this.zzg = str7;
        this.zzh = str8;
        this.zzi = z;
        this.zzj = z2;
        this.zzk = str9;
        this.zzl = str10;
        this.zzm = str11;
        this.zzn = str12;
        this.zzo = z3;
        this.zzp = str13;
    }

    public zzgc(@Nullable String str, @Nullable String str2, String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9) {
        this.zza = "http://localhost";
        this.zzc = str;
        this.zzd = str2;
        this.zzh = str5;
        this.zzk = str6;
        this.zzn = str7;
        this.zzp = str8;
        this.zzi = true;
        if (!TextUtils.isEmpty(this.zzc) || !TextUtils.isEmpty(this.zzd) || !TextUtils.isEmpty(this.zzk)) {
            this.zze = Preconditions.checkNotEmpty(str3);
            this.zzf = null;
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(this.zzc)) {
                sb.append("id_token=").append(this.zzc).append("&");
            }
            if (!TextUtils.isEmpty(this.zzd)) {
                sb.append("access_token=").append(this.zzd).append("&");
            }
            if (!TextUtils.isEmpty(this.zzf)) {
                sb.append("identifier=").append(this.zzf).append("&");
            }
            if (!TextUtils.isEmpty(this.zzh)) {
                sb.append("oauth_token_secret=").append(this.zzh).append("&");
            }
            if (!TextUtils.isEmpty(this.zzk)) {
                sb.append("code=").append(this.zzk).append("&");
            }
            if (!TextUtils.isEmpty(str9)) {
                sb.append("nonce=").append(str9).append("&");
            }
            sb.append("providerId=").append(this.zze);
            this.zzg = sb.toString();
            this.zzj = true;
            return;
        }
        throw new IllegalArgumentException("idToken, accessToken and authCode cannot all be null");
    }

    public final zzgc zza(String str) {
        this.zzb = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzgc zza(boolean z) {
        this.zzj = false;
        return this;
    }

    public final zzgc zzb(@Nullable String str) {
        this.zzn = str;
        return this;
    }

    public final zzgc zzb(boolean z) {
        this.zzi = true;
        return this;
    }

    public final zzgc zzc(boolean z) {
        this.zzo = true;
        return this;
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
        SafeParcelWriter.writeString(parcel, 9, this.zzh, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzi);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzj);
        SafeParcelWriter.writeString(parcel, 12, this.zzk, false);
        SafeParcelWriter.writeString(parcel, 13, this.zzl, false);
        SafeParcelWriter.writeString(parcel, 14, this.zzm, false);
        SafeParcelWriter.writeString(parcel, 15, this.zzn, false);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzo);
        SafeParcelWriter.writeString(parcel, 17, this.zzp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* synthetic */ zzjr zza() {
        zzp.zzr.zza zzc2 = zzp.zzr.zza().zza(this.zzi).zzc(this.zzj);
        if (this.zzb != null) {
            zzc2.zzd(this.zzb);
        }
        if (this.zza != null) {
            zzc2.zza(this.zza);
        }
        if (this.zzg != null) {
            zzc2.zzb(this.zzg);
        }
        if (this.zzn != null) {
            zzc2.zze(this.zzn);
        }
        if (this.zzp != null) {
            zzc2.zzf(this.zzp);
        }
        if (!TextUtils.isEmpty(this.zzl)) {
            zzc2.zzc(this.zzl);
        }
        if (!TextUtils.isEmpty(this.zzm)) {
            zzc2.zza(this.zzm);
        }
        return (zzp.zzr) ((zzig) zzc2.zzb(this.zzo).zzf());
    }
}
