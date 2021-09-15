package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "EventParcelCreator")
@SafeParcelable.Reserved({1})
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzao extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzao> CREATOR = new zzar();
    @SafeParcelable.Field(mo21170id = 2)
    public final String zza;
    @SafeParcelable.Field(mo21170id = 3)
    public final zzan zzb;
    @SafeParcelable.Field(mo21170id = 4)
    public final String zzc;
    @SafeParcelable.Field(mo21170id = 5)
    public final long zzd;

    @SafeParcelable.Constructor
    public zzao(@SafeParcelable.Param(mo21173id = 2) String str, @SafeParcelable.Param(mo21173id = 3) zzan zzan, @SafeParcelable.Param(mo21173id = 4) String str2, @SafeParcelable.Param(mo21173id = 5) long j) {
        this.zza = str;
        this.zzb = zzan;
        this.zzc = str2;
        this.zzd = j;
    }

    zzao(zzao zzao, long j) {
        Preconditions.checkNotNull(zzao);
        this.zza = zzao.zza;
        this.zzb = zzao.zzb;
        this.zzc = zzao.zzc;
        this.zzd = j;
    }

    public final String toString() {
        String str = this.zzc;
        String str2 = this.zza;
        String valueOf = String.valueOf(this.zzb);
        return new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length() + String.valueOf(valueOf).length()).append("origin=").append(str).append(",name=").append(str2).append(",params=").append(valueOf).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
