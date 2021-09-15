package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.zze;
import java.util.List;

@SafeParcelable.Class(creator = "DefaultAuthResultCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzj implements AuthResult {
    public static final Parcelable.Creator<zzj> CREATOR = new zzi();
    @SafeParcelable.Field(getter = "getUser", mo21170id = 1)
    @NonNull
    private zzp zza;
    @SafeParcelable.Field(getter = "getAdditionalUserInfo", mo21170id = 2)
    @Nullable
    private zzh zzb;
    @SafeParcelable.Field(getter = "getOAuthCredential", mo21170id = 3)
    @Nullable
    private zze zzc;

    @SafeParcelable.Constructor
    zzj(@SafeParcelable.Param(mo21173id = 1) @NonNull zzp zzp, @SafeParcelable.Param(mo21173id = 2) @Nullable zzh zzh, @SafeParcelable.Param(mo21173id = 3) @Nullable zze zze) {
        this.zza = zzp;
        this.zzb = zzh;
        this.zzc = zze;
    }

    public zzj(zzp zzp) {
        this.zza = (zzp) Preconditions.checkNotNull(zzp);
        List<zzl> zzh = this.zza.zzh();
        this.zzb = null;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= zzh.size()) {
                break;
            }
            if (!TextUtils.isEmpty(zzh.get(i2).zza())) {
                this.zzb = new zzh(zzh.get(i2).getProviderId(), zzh.get(i2).zza(), zzp.zzi());
            }
            i = i2 + 1;
        }
        if (this.zzb == null) {
            this.zzb = new zzh(zzp.zzi());
        }
        this.zzc = zzp.zzj();
    }

    @Nullable
    public final FirebaseUser getUser() {
        return this.zza;
    }

    @Nullable
    public final AdditionalUserInfo getAdditionalUserInfo() {
        return this.zzb;
    }

    @Nullable
    public final AuthCredential getCredential() {
        return this.zzc;
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getUser(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getAdditionalUserInfo(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int describeContents() {
        return 0;
    }
}
