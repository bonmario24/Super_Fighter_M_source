package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "StringListCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzga extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzga> CREATOR = new zzfz();
    @SafeParcelable.VersionField(mo21176id = 1)
    private final int zza;
    @SafeParcelable.Field(getter = "getValues", mo21170id = 2)
    private List<String> zzb;

    public final List<String> zza() {
        return this.zzb;
    }

    public zzga() {
        this((List<String>) null);
    }

    private zzga(@Nullable List<String> list) {
        this.zza = 1;
        this.zzb = new ArrayList();
        if (list != null && !list.isEmpty()) {
            this.zzb.addAll(list);
        }
    }

    @SafeParcelable.Constructor
    zzga(@SafeParcelable.Param(mo21173id = 1) int i, @SafeParcelable.Param(mo21173id = 2) List<String> list) {
        this.zza = i;
        if (list == null || list.isEmpty()) {
            this.zzb = Collections.emptyList();
            return;
        }
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < list.size()) {
                list.set(i3, Strings.emptyToNull(list.get(i3)));
                i2 = i3 + 1;
            } else {
                this.zzb = Collections.unmodifiableList(list);
                return;
            }
        }
    }

    public static zzga zzb() {
        return new zzga((List<String>) null);
    }

    public static zzga zza(zzga zzga) {
        List<String> list;
        if (zzga != null) {
            list = zzga.zzb;
        } else {
            list = null;
        }
        return new zzga(list);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeStringList(parcel, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
