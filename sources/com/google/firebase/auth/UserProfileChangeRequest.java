package com.google.firebase.auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "UserProfileChangeRequestCreator")
@SafeParcelable.Reserved({1})
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public class UserProfileChangeRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<UserProfileChangeRequest> CREATOR = new zzab();
    @SafeParcelable.Field(getter = "getDisplayName", mo21170id = 2)
    private String zza;
    @SafeParcelable.Field(getter = "getPhotoUrl", mo21170id = 3)
    private String zzb;
    @SafeParcelable.Field(getter = "shouldRemoveDisplayName", mo21170id = 4)
    private boolean zzc;
    @SafeParcelable.Field(getter = "shouldRemovePhotoUri", mo21170id = 5)
    private boolean zzd;
    private Uri zze;

    /* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
    public static class Builder {
        private String zza;
        private Uri zzb;
        private boolean zzc;
        private boolean zzd;

        @KeepForSdk
        @Nullable
        public String getDisplayName() {
            return this.zza;
        }

        @NonNull
        public Builder setDisplayName(@Nullable String str) {
            if (str == null) {
                this.zzc = true;
            } else {
                this.zza = str;
            }
            return this;
        }

        @KeepForSdk
        @Nullable
        public Uri getPhotoUri() {
            return this.zzb;
        }

        @NonNull
        public Builder setPhotoUri(@Nullable Uri uri) {
            if (uri == null) {
                this.zzd = true;
            } else {
                this.zzb = uri;
            }
            return this;
        }

        @NonNull
        public UserProfileChangeRequest build() {
            return new UserProfileChangeRequest(this.zza, this.zzb == null ? null : this.zzb.toString(), this.zzc, this.zzd);
        }
    }

    @SafeParcelable.Constructor
    UserProfileChangeRequest(@SafeParcelable.Param(mo21173id = 2) String str, @SafeParcelable.Param(mo21173id = 3) String str2, @SafeParcelable.Param(mo21173id = 4) boolean z, @SafeParcelable.Param(mo21173id = 5) boolean z2) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = z2;
        this.zze = TextUtils.isEmpty(str2) ? null : Uri.parse(str2);
    }

    @Nullable
    public String getDisplayName() {
        return this.zza;
    }

    public final String zza() {
        return this.zzb;
    }

    @Nullable
    public Uri getPhotoUri() {
        return this.zze;
    }

    public final boolean zzb() {
        return this.zzc;
    }

    public final boolean zzc() {
        return this.zzd;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
