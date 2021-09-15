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
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "CreateAuthUriResponseCreator")
@SafeParcelable.Reserved({1})
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzeq extends AbstractSafeParcelable implements zzen<zzeq, zzp.zzb> {
    public static final Parcelable.Creator<zzeq> CREATOR = new zzep();
    @SafeParcelable.Field(getter = "getAuthUri", mo21170id = 2)
    private String zza;
    @SafeParcelable.Field(getter = "isRegistered", mo21170id = 3)
    private boolean zzb;
    @SafeParcelable.Field(getter = "getProviderId", mo21170id = 4)
    private String zzc;
    @SafeParcelable.Field(getter = "isForExistingProvider", mo21170id = 5)
    private boolean zzd;
    @SafeParcelable.Field(getter = "getStringList", mo21170id = 6)
    private zzga zze;
    @SafeParcelable.Field(getter = "getSignInMethods", mo21170id = 7)
    private List<String> zzf;

    public zzeq() {
        this.zze = zzga.zzb();
    }

    @SafeParcelable.Constructor
    public zzeq(@SafeParcelable.Param(mo21173id = 2) String str, @SafeParcelable.Param(mo21173id = 3) boolean z, @SafeParcelable.Param(mo21173id = 4) String str2, @SafeParcelable.Param(mo21173id = 5) boolean z2, @SafeParcelable.Param(mo21173id = 6) zzga zzga, @SafeParcelable.Param(mo21173id = 7) List<String> list) {
        this.zza = str;
        this.zzb = z;
        this.zzc = str2;
        this.zzd = z2;
        this.zze = zzga == null ? zzga.zzb() : zzga.zza(zzga);
        this.zzf = list;
    }

    @Nullable
    public final List<String> zzb() {
        return this.zzf;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzb);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzd);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zze, i, false);
        SafeParcelWriter.writeStringList(parcel, 7, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzkb<zzp.zzb> zza() {
        return zzp.zzb.zzi();
    }

    public final /* synthetic */ zzen zza(zzjr zzjr) {
        zzga zzga;
        if (!(zzjr instanceof zzp.zzb)) {
            throw new IllegalArgumentException("The passed proto must be an instance of CreateAuthUriResponse.");
        }
        zzp.zzb zzb2 = (zzp.zzb) zzjr;
        this.zza = Strings.emptyToNull(zzb2.zza());
        this.zzb = zzb2.zzd();
        this.zzc = Strings.emptyToNull(zzb2.zze());
        this.zzd = zzb2.zzf();
        if (zzb2.zzc() == 0) {
            zzga = zzga.zzb();
        } else {
            zzga = new zzga(1, new ArrayList(zzb2.zzb()));
        }
        this.zze = zzga;
        this.zzf = zzb2.zzh() == 0 ? new ArrayList<>(0) : zzb2.zzg();
        return this;
    }
}
