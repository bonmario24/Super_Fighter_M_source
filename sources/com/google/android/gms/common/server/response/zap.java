package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public final class zap implements Parcelable.Creator<SafeParcelResponse> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new SafeParcelResponse[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        zaj zaj = null;
        Parcel parcel2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    parcel2 = SafeParcelReader.createParcel(parcel, readHeader);
                    break;
                case 3:
                    zaj = (zaj) SafeParcelReader.createParcelable(parcel, readHeader, zaj.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new SafeParcelResponse(i, parcel2, zaj);
    }
}
