package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.zze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "GetAccountInfoUserListCreator")
@SafeParcelable.Reserved({1})
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzfc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfc> CREATOR = new zzfb();
    @SafeParcelable.Field(getter = "getUsers", mo21170id = 2)
    private List<zzfa> zza;

    public zzfc() {
        this.zza = new ArrayList();
    }

    @SafeParcelable.Constructor
    zzfc(@SafeParcelable.Param(mo21173id = 2) List<zzfa> list) {
        List<zzfa> unmodifiableList;
        if (list == null) {
            unmodifiableList = Collections.emptyList();
        } else {
            unmodifiableList = Collections.unmodifiableList(list);
        }
        this.zza = unmodifiableList;
    }

    public final List<zzfa> zza() {
        return this.zza;
    }

    public static zzfc zza(zzfc zzfc) {
        Preconditions.checkNotNull(zzfc);
        List<zzfa> list = zzfc.zza;
        zzfc zzfc2 = new zzfc();
        if (list != null && !list.isEmpty()) {
            zzfc2.zza.addAll(list);
        }
        return zzfc2;
    }

    public static zzfc zza(zzp.zzg zzg) {
        ArrayList arrayList = new ArrayList(zzg.zza());
        for (int i = 0; i < zzg.zza(); i++) {
            zzz zza2 = zzg.zza(i);
            arrayList.add(new zzfa(Strings.emptyToNull(zza2.zza()), Strings.emptyToNull(zza2.zzb()), zza2.zze(), Strings.emptyToNull(zza2.zzc()), Strings.emptyToNull(zza2.zzd()), zzfl.zza(zza2.zzf()), Strings.emptyToNull(zza2.zzi()), Strings.emptyToNull(zza2.zzj()), zza2.zzh(), zza2.zzg(), false, (zze) null, zzfh.zza(zza2.zzk())));
        }
        return new zzfc(arrayList);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
