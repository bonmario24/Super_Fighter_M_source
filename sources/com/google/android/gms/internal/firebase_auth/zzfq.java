package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzen;

@SafeParcelable.Class(creator = "ResetPasswordResponseCreator")
@SafeParcelable.Reserved({1})
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzfq extends AbstractSafeParcelable implements zzen<zzfq, zzp.zzk> {
    public static final Parcelable.Creator<zzfq> CREATOR = new zzfs();
    @SafeParcelable.Field(getter = "getEmail", mo21170id = 2)
    private String zza;
    @SafeParcelable.Field(getter = "getNewEmail", mo21170id = 3)
    private String zzb;
    @SafeParcelable.Field(getter = "getRequestType", mo21170id = 4)
    private String zzc;
    @SafeParcelable.Field(getter = "getMfaInfo", mo21170id = 5)
    private zzfh zzd;

    public zzfq() {
    }

    @SafeParcelable.Constructor
    zzfq(@SafeParcelable.Param(mo21173id = 2) String str, @SafeParcelable.Param(mo21173id = 3) String str2, @SafeParcelable.Param(mo21173id = 4) String str3, @SafeParcelable.Param(mo21173id = 5) zzfh zzfh) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzfh;
    }

    public final String zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzb;
    }

    public final String zzd() {
        return this.zzc;
    }

    @Nullable
    public final zzfh zze() {
        return this.zzd;
    }

    public final boolean zzf() {
        return this.zza != null;
    }

    public final boolean zzg() {
        return this.zzb != null;
    }

    public final boolean zzh() {
        return this.zzc != null;
    }

    public final boolean zzi() {
        return this.zzd != null;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzd, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzkb<zzp.zzk> zza() {
        return zzp.zzk.zzf();
    }

    public final /* synthetic */ zzen zza(zzjr zzjr) {
        String str;
        if (!(zzjr instanceof zzp.zzk)) {
            throw new IllegalArgumentException("The passed proto must be an instance of ResetPasswordResponse.");
        }
        zzp.zzk zzk = (zzp.zzk) zzjr;
        this.zza = Strings.emptyToNull(zzk.zza());
        this.zzb = Strings.emptyToNull(zzk.zzb());
        switch (zzfp.zza[zzk.zzc().ordinal()]) {
            case 1:
                str = "VERIFY_EMAIL";
                break;
            case 2:
                str = "PASSWORD_RESET";
                break;
            case 3:
                str = "EMAIL_SIGNIN";
                break;
            case 4:
                str = "VERIFY_BEFORE_UPDATE_EMAIL";
                break;
            case 5:
                str = "RECOVER_EMAIL";
                break;
            case 6:
                str = "REVERT_SECOND_FACTOR_ADDITION";
                break;
            default:
                str = null;
                break;
        }
        this.zzc = str;
        if (zzk.zzd()) {
            this.zzd = zzfh.zza(zzk.zze());
        }
        return this;
    }
}
