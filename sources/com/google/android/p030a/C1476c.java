package com.google.android.p030a;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.google.android.a.c */
/* compiled from: Codecs */
public final class C1476c {
    static {
        C1476c.class.getClassLoader();
    }

    private C1476c() {
    }

    /* renamed from: a */
    public static <T extends Parcelable> T m628a(Parcel parcel, Parcelable.Creator<T> creator) {
        if (parcel.readInt() != 0) {
            return (Parcelable) creator.createFromParcel(parcel);
        }
        return null;
    }

    /* renamed from: a */
    public static void m629a(Parcel parcel, Parcelable parcelable) {
        parcel.writeInt(1);
        parcelable.writeToParcel(parcel, 0);
    }
}
