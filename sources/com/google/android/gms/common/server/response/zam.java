package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.Map;

@ShowFirstParty
@SafeParcelable.Class(creator = "FieldMappingDictionaryEntryCreator")
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public final class zam extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zam> CREATOR = new zan();
    @SafeParcelable.Field(mo21170id = 2)
    final String className;
    @SafeParcelable.VersionField(mo21176id = 1)
    private final int versionCode;
    @SafeParcelable.Field(mo21170id = 3)
    final ArrayList<zal> zaro;

    @SafeParcelable.Constructor
    zam(@SafeParcelable.Param(mo21173id = 1) int i, @SafeParcelable.Param(mo21173id = 2) String str, @SafeParcelable.Param(mo21173id = 3) ArrayList<zal> arrayList) {
        this.versionCode = i;
        this.className = str;
        this.zaro = arrayList;
    }

    zam(String str, Map<String, FastJsonResponse.Field<?, ?>> map) {
        ArrayList<zal> arrayList;
        this.versionCode = 1;
        this.className = str;
        if (map == null) {
            arrayList = null;
        } else {
            ArrayList<zal> arrayList2 = new ArrayList<>();
            for (String next : map.keySet()) {
                arrayList2.add(new zal(next, map.get(next)));
            }
            arrayList = arrayList2;
        }
        this.zaro = arrayList;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.className, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zaro, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
