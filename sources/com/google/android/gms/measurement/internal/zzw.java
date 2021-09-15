package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ConditionalUserPropertyParcelCreator")
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzw> CREATOR = new zzz();
    @SafeParcelable.Field(mo21170id = 2)
    public String zza;
    @SafeParcelable.Field(mo21170id = 3)
    public String zzb;
    @SafeParcelable.Field(mo21170id = 4)
    public zzkh zzc;
    @SafeParcelable.Field(mo21170id = 5)
    public long zzd;
    @SafeParcelable.Field(mo21170id = 6)
    public boolean zze;
    @SafeParcelable.Field(mo21170id = 7)
    public String zzf;
    @SafeParcelable.Field(mo21170id = 8)
    public zzao zzg;
    @SafeParcelable.Field(mo21170id = 9)
    public long zzh;
    @SafeParcelable.Field(mo21170id = 10)
    public zzao zzi;
    @SafeParcelable.Field(mo21170id = 11)
    public long zzj;
    @SafeParcelable.Field(mo21170id = 12)
    public zzao zzk;

    zzw(zzw zzw) {
        Preconditions.checkNotNull(zzw);
        this.zza = zzw.zza;
        this.zzb = zzw.zzb;
        this.zzc = zzw.zzc;
        this.zzd = zzw.zzd;
        this.zze = zzw.zze;
        this.zzf = zzw.zzf;
        this.zzg = zzw.zzg;
        this.zzh = zzw.zzh;
        this.zzi = zzw.zzi;
        this.zzj = zzw.zzj;
        this.zzk = zzw.zzk;
    }

    @SafeParcelable.Constructor
    zzw(@SafeParcelable.Param(mo21173id = 2) String str, @SafeParcelable.Param(mo21173id = 3) String str2, @SafeParcelable.Param(mo21173id = 4) zzkh zzkh, @SafeParcelable.Param(mo21173id = 5) long j, @SafeParcelable.Param(mo21173id = 6) boolean z, @SafeParcelable.Param(mo21173id = 7) String str3, @SafeParcelable.Param(mo21173id = 8) zzao zzao, @SafeParcelable.Param(mo21173id = 9) long j2, @SafeParcelable.Param(mo21173id = 10) zzao zzao2, @SafeParcelable.Param(mo21173id = 11) long j3, @SafeParcelable.Param(mo21173id = 12) zzao zzao3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzkh;
        this.zzd = j;
        this.zze = z;
        this.zzf = str3;
        this.zzg = zzao;
        this.zzh = j2;
        this.zzi = zzao2;
        this.zzj = j3;
        this.zzk = zzao3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzc, i, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zze);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzg, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzh);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzi, i, false);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
