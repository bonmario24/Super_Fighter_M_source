package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.MultiFactorSession;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "DefaultMultiFactorSessionCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzw extends MultiFactorSession {
    public static final Parcelable.Creator<zzw> CREATOR = new zzz();
    @SafeParcelable.Field(getter = "getIdToken", mo21170id = 1)
    @Nullable
    private String zza;
    @SafeParcelable.Field(getter = "getPendingCredential", mo21170id = 2)
    @Nullable
    private String zzb;
    @SafeParcelable.Field(getter = "getPhoneMultiFactorInfoList", mo21170id = 3)
    @Nullable
    private List<PhoneMultiFactorInfo> zzc;

    private zzw() {
    }

    @SafeParcelable.Constructor
    zzw(@SafeParcelable.Param(mo21173id = 1) String str, @SafeParcelable.Param(mo21173id = 2) String str2, @SafeParcelable.Param(mo21173id = 3) List<PhoneMultiFactorInfo> list) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = list;
    }

    public static zzw zza(String str) {
        Preconditions.checkNotEmpty(str);
        zzw zzw = new zzw();
        zzw.zza = str;
        return zzw;
    }

    public static zzw zza(List<MultiFactorInfo> list, String str) {
        Preconditions.checkNotNull(list);
        Preconditions.checkNotEmpty(str);
        zzw zzw = new zzw();
        zzw.zzc = new ArrayList();
        for (MultiFactorInfo next : list) {
            if (next instanceof PhoneMultiFactorInfo) {
                zzw.zzc.add((PhoneMultiFactorInfo) next);
            }
        }
        zzw.zzb = str;
        return zzw;
    }

    @Nullable
    public final String zza() {
        return this.zza;
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }

    public final boolean zzc() {
        return this.zza != null;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
