package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzgm;

@SafeParcelable.Class(creator = "ActionCodeSettingsCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public class ActionCodeSettings extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ActionCodeSettings> CREATOR = new zzb();
    @SafeParcelable.Field(getter = "getUrl", mo21170id = 1)
    private final String zza;
    @SafeParcelable.Field(getter = "getIOSBundle", mo21170id = 2)
    private final String zzb;
    @SafeParcelable.Field(getter = "getIOSAppStoreId", mo21170id = 3)
    private final String zzc;
    @SafeParcelable.Field(getter = "getAndroidPackageName", mo21170id = 4)
    private final String zzd;
    @SafeParcelable.Field(getter = "getAndroidInstallApp", mo21170id = 5)
    private final boolean zze;
    @SafeParcelable.Field(getter = "getAndroidMinimumVersion", mo21170id = 6)
    private final String zzf;
    @SafeParcelable.Field(getter = "canHandleCodeInApp", mo21170id = 7)
    private final boolean zzg;
    @SafeParcelable.Field(getter = "getLocaleHeader", mo21170id = 8)
    private String zzh;
    @SafeParcelable.Field(getter = "getRequestType", mo21170id = 9)
    private int zzi;
    @SafeParcelable.Field(getter = "getDynamicLinkDomain", mo21170id = 10)
    private String zzj;

    @SafeParcelable.Constructor
    ActionCodeSettings(@SafeParcelable.Param(mo21173id = 1) String str, @SafeParcelable.Param(mo21173id = 2) String str2, @SafeParcelable.Param(mo21173id = 3) String str3, @SafeParcelable.Param(mo21173id = 4) String str4, @SafeParcelable.Param(mo21173id = 5) boolean z, @SafeParcelable.Param(mo21173id = 6) String str5, @SafeParcelable.Param(mo21173id = 7) boolean z2, @SafeParcelable.Param(mo21173id = 8) String str6, @SafeParcelable.Param(mo21173id = 9) int i, @SafeParcelable.Param(mo21173id = 10) String str7) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = z;
        this.zzf = str5;
        this.zzg = z2;
        this.zzh = str6;
        this.zzi = i;
        this.zzj = str7;
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
    public static class Builder {
        /* access modifiers changed from: private */
        public String zza;
        /* access modifiers changed from: private */
        public String zzb;
        /* access modifiers changed from: private */
        public String zzc;
        /* access modifiers changed from: private */
        public boolean zzd;
        /* access modifiers changed from: private */
        public String zze;
        /* access modifiers changed from: private */
        public boolean zzf;
        /* access modifiers changed from: private */
        public String zzg;

        private Builder() {
            this.zzf = false;
        }

        @KeepForSdk
        public String getUrl() {
            return this.zza;
        }

        @NonNull
        public Builder setUrl(@NonNull String str) {
            this.zza = str;
            return this;
        }

        @KeepForSdk
        public String getIOSBundleId() {
            return this.zzb;
        }

        @NonNull
        public Builder setIOSBundleId(@NonNull String str) {
            this.zzb = str;
            return this;
        }

        @NonNull
        public Builder setAndroidPackageName(@NonNull String str, boolean z, @Nullable String str2) {
            this.zzc = str;
            this.zzd = z;
            this.zze = str2;
            return this;
        }

        @KeepForSdk
        public boolean getHandleCodeInApp() {
            return this.zzf;
        }

        @NonNull
        public Builder setHandleCodeInApp(boolean z) {
            this.zzf = z;
            return this;
        }

        @KeepForSdk
        public String getDynamicLinkDomain() {
            return this.zzg;
        }

        @NonNull
        public Builder setDynamicLinkDomain(@NonNull String str) {
            this.zzg = str;
            return this;
        }

        @NonNull
        public ActionCodeSettings build() {
            if (this.zza != null) {
                return new ActionCodeSettings(this);
            }
            throw new IllegalArgumentException("Cannot build ActionCodeSettings with null URL. Call #setUrl(String) before calling build()");
        }
    }

    private ActionCodeSettings(Builder builder) {
        this.zza = builder.zza;
        this.zzb = builder.zzb;
        this.zzc = null;
        this.zzd = builder.zzc;
        this.zze = builder.zzd;
        this.zzf = builder.zze;
        this.zzg = builder.zzf;
        this.zzj = builder.zzg;
    }

    public static ActionCodeSettings zza() {
        return new ActionCodeSettings(new Builder());
    }

    @NonNull
    public String getUrl() {
        return this.zza;
    }

    @Nullable
    public String getIOSBundle() {
        return this.zzb;
    }

    public final String zzb() {
        return this.zzc;
    }

    @Nullable
    public String getAndroidPackageName() {
        return this.zzd;
    }

    public boolean getAndroidInstallApp() {
        return this.zze;
    }

    @Nullable
    public String getAndroidMinimumVersion() {
        return this.zzf;
    }

    public boolean canHandleCodeInApp() {
        return this.zzg;
    }

    public final void zza(@NonNull String str) {
        this.zzh = str;
    }

    public final String zzc() {
        return this.zzh;
    }

    public final void zza(@NonNull zzgm zzgm) {
        this.zzi = zzgm.zza();
    }

    public final int zzd() {
        return this.zzi;
    }

    public final String zze() {
        return this.zzj;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getUrl(), false);
        SafeParcelWriter.writeString(parcel, 2, getIOSBundle(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, getAndroidPackageName(), false);
        SafeParcelWriter.writeBoolean(parcel, 5, getAndroidInstallApp());
        SafeParcelWriter.writeString(parcel, 6, getAndroidMinimumVersion(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, canHandleCodeInApp());
        SafeParcelWriter.writeString(parcel, 8, this.zzh, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzi);
        SafeParcelWriter.writeString(parcel, 10, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
