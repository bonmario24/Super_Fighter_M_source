package com.google.android.gms.internal.firebase_auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.zze;
import java.util.List;

@SafeParcelable.Class(creator = "GetAccountInfoUserCreator")
@SafeParcelable.Reserved({1})
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzfa extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfa> CREATOR = new zzez();
    @SafeParcelable.Field(getter = "getLocalId", mo21170id = 2)
    private String zza;
    @SafeParcelable.Field(getter = "getEmail", mo21170id = 3)
    private String zzb;
    @SafeParcelable.Field(getter = "isEmailVerified", mo21170id = 4)
    private boolean zzc;
    @SafeParcelable.Field(getter = "getDisplayName", mo21170id = 5)
    private String zzd;
    @SafeParcelable.Field(getter = "getPhotoUrl", mo21170id = 6)
    private String zze;
    @SafeParcelable.Field(getter = "getProviderInfoList", mo21170id = 7)
    private zzfl zzf;
    @SafeParcelable.Field(getter = "getPassword", mo21170id = 8)
    private String zzg;
    @SafeParcelable.Field(getter = "getPhoneNumber", mo21170id = 9)
    private String zzh;
    @SafeParcelable.Field(getter = "getCreationTimestamp", mo21170id = 10)
    private long zzi;
    @SafeParcelable.Field(getter = "getLastSignInTimestamp", mo21170id = 11)
    private long zzj;
    @SafeParcelable.Field(getter = "isNewUser", mo21170id = 12)
    private boolean zzk;
    @SafeParcelable.Field(getter = "getDefaultOAuthCredential", mo21170id = 13)
    private zze zzl;
    @SafeParcelable.Field(getter = "getMfaInfoList", mo21170id = 14)
    private List<zzfh> zzm;

    public zzfa() {
        this.zzf = new zzfl();
    }

    @SafeParcelable.Constructor
    public zzfa(@SafeParcelable.Param(mo21173id = 2) String str, @SafeParcelable.Param(mo21173id = 3) String str2, @SafeParcelable.Param(mo21173id = 4) boolean z, @SafeParcelable.Param(mo21173id = 5) String str3, @SafeParcelable.Param(mo21173id = 6) String str4, @SafeParcelable.Param(mo21173id = 7) zzfl zzfl, @SafeParcelable.Param(mo21173id = 8) String str5, @SafeParcelable.Param(mo21173id = 9) String str6, @SafeParcelable.Param(mo21173id = 10) long j, @SafeParcelable.Param(mo21173id = 11) long j2, @SafeParcelable.Param(mo21173id = 12) boolean z2, @SafeParcelable.Param(mo21173id = 13) zze zze2, @SafeParcelable.Param(mo21173id = 14) List<zzfh> list) {
        zzfl zza2;
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = str3;
        this.zze = str4;
        if (zzfl == null) {
            zza2 = new zzfl();
        } else {
            zza2 = zzfl.zza(zzfl);
        }
        this.zzf = zza2;
        this.zzg = str5;
        this.zzh = str6;
        this.zzi = j;
        this.zzj = j2;
        this.zzk = z2;
        this.zzl = zze2;
        this.zzm = list == null ? zzbj.zzf() : list;
    }

    @Nullable
    public final String zza() {
        return this.zzb;
    }

    public final boolean zzb() {
        return this.zzc;
    }

    @NonNull
    public final String zzc() {
        return this.zza;
    }

    @Nullable
    public final String zzd() {
        return this.zzd;
    }

    @Nullable
    public final Uri zze() {
        if (!TextUtils.isEmpty(this.zze)) {
            return Uri.parse(this.zze);
        }
        return null;
    }

    @Nullable
    public final String zzf() {
        return this.zzh;
    }

    public final long zzg() {
        return this.zzi;
    }

    public final long zzh() {
        return this.zzj;
    }

    public final boolean zzi() {
        return this.zzk;
    }

    @NonNull
    public final zzfa zza(@Nullable String str) {
        this.zzb = str;
        return this;
    }

    @NonNull
    public final zzfa zzb(@Nullable String str) {
        this.zzd = str;
        return this;
    }

    @NonNull
    public final zzfa zzc(@Nullable String str) {
        this.zze = str;
        return this;
    }

    @NonNull
    public final zzfa zzd(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzg = str;
        return this;
    }

    @NonNull
    public final zzfa zza(List<zzfj> list) {
        Preconditions.checkNotNull(list);
        this.zzf = new zzfl();
        this.zzf.zza().addAll(list);
        return this;
    }

    public final zzfa zza(boolean z) {
        this.zzk = z;
        return this;
    }

    @NonNull
    public final List<zzfj> zzj() {
        return this.zzf.zza();
    }

    public final zzfl zzk() {
        return this.zzf;
    }

    @Nullable
    public final zze zzl() {
        return this.zzl;
    }

    @NonNull
    public final zzfa zza(zze zze2) {
        this.zzl = zze2;
        return this;
    }

    @NonNull
    public final List<zzfh> zzm() {
        return this.zzm;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzc);
        SafeParcelWriter.writeString(parcel, 5, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 6, this.zze, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzf, i, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzh, false);
        SafeParcelWriter.writeLong(parcel, 10, this.zzi);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzk);
        SafeParcelWriter.writeParcelable(parcel, 13, this.zzl, i, false);
        SafeParcelWriter.writeTypedList(parcel, 14, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
