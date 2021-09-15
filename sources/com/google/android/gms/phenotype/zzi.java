package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.Comparator;

@SafeParcelable.Class(creator = "FlagCreator")
@SafeParcelable.Reserved({1})
public final class zzi extends AbstractSafeParcelable implements Comparable<zzi> {
    public static final Parcelable.Creator<zzi> CREATOR = new zzk();
    private static final Comparator<zzi> zzai = new zzj();
    @SafeParcelable.Field(mo21170id = 2)
    public final String name;
    @SafeParcelable.Field(mo21170id = 3)
    private final long zzab;
    @SafeParcelable.Field(mo21170id = 4)
    private final boolean zzac;
    @SafeParcelable.Field(mo21170id = 5)
    private final double zzad;
    @SafeParcelable.Field(mo21170id = 6)
    private final String zzae;
    @SafeParcelable.Field(mo21170id = 7)
    private final byte[] zzaf;
    @SafeParcelable.Field(mo21170id = 8)
    private final int zzag;
    @SafeParcelable.Field(mo21170id = 9)
    public final int zzah;

    @SafeParcelable.Constructor
    public zzi(@SafeParcelable.Param(mo21173id = 2) String str, @SafeParcelable.Param(mo21173id = 3) long j, @SafeParcelable.Param(mo21173id = 4) boolean z, @SafeParcelable.Param(mo21173id = 5) double d, @SafeParcelable.Param(mo21173id = 6) String str2, @SafeParcelable.Param(mo21173id = 7) byte[] bArr, @SafeParcelable.Param(mo21173id = 8) int i, @SafeParcelable.Param(mo21173id = 9) int i2) {
        this.name = str;
        this.zzab = j;
        this.zzac = z;
        this.zzad = d;
        this.zzae = str2;
        this.zzaf = bArr;
        this.zzag = i;
        this.zzah = i2;
    }

    private static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        zzi zzi = (zzi) obj;
        int compareTo = this.name.compareTo(zzi.name);
        if (compareTo != 0) {
            return compareTo;
        }
        int compare = compare(this.zzag, zzi.zzag);
        if (compare != 0) {
            return compare;
        }
        switch (this.zzag) {
            case 1:
                long j = this.zzab;
                long j2 = zzi.zzab;
                if (j < j2) {
                    return -1;
                }
                return j != j2 ? 1 : 0;
            case 2:
                boolean z = this.zzac;
                if (z != zzi.zzac) {
                    return z ? 1 : -1;
                }
                return 0;
            case 3:
                return Double.compare(this.zzad, zzi.zzad);
            case 4:
                String str = this.zzae;
                String str2 = zzi.zzae;
                if (str == str2) {
                    return 0;
                }
                if (str == null) {
                    return -1;
                }
                if (str2 == null) {
                    return 1;
                }
                return str.compareTo(str2);
            case 5:
                if (this.zzaf == zzi.zzaf) {
                    return 0;
                }
                if (this.zzaf == null) {
                    return -1;
                }
                if (zzi.zzaf == null) {
                    return 1;
                }
                for (int i = 0; i < Math.min(this.zzaf.length, zzi.zzaf.length); i++) {
                    int i2 = this.zzaf[i] - zzi.zzaf[i];
                    if (i2 != 0) {
                        return i2;
                    }
                }
                return compare(this.zzaf.length, zzi.zzaf.length);
            default:
                throw new AssertionError(new StringBuilder(31).append("Invalid enum value: ").append(this.zzag).toString());
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzi)) {
            return false;
        }
        zzi zzi = (zzi) obj;
        if (!zzn.equals(this.name, zzi.name) || this.zzag != zzi.zzag || this.zzah != zzi.zzah) {
            return false;
        }
        switch (this.zzag) {
            case 1:
                return this.zzab == zzi.zzab;
            case 2:
                return this.zzac == zzi.zzac;
            case 3:
                return this.zzad == zzi.zzad;
            case 4:
                return zzn.equals(this.zzae, zzi.zzae);
            case 5:
                return Arrays.equals(this.zzaf, zzi.zzaf);
            default:
                throw new AssertionError(new StringBuilder(31).append("Invalid enum value: ").append(this.zzag).toString());
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Flag(");
        sb.append(this.name);
        sb.append(", ");
        switch (this.zzag) {
            case 1:
                sb.append(this.zzab);
                break;
            case 2:
                sb.append(this.zzac);
                break;
            case 3:
                sb.append(this.zzad);
                break;
            case 4:
                sb.append("'");
                sb.append(this.zzae);
                sb.append("'");
                break;
            case 5:
                if (this.zzaf != null) {
                    sb.append("'");
                    sb.append(Base64.encodeToString(this.zzaf, 3));
                    sb.append("'");
                    break;
                } else {
                    sb.append("null");
                    break;
                }
            default:
                String str = this.name;
                throw new AssertionError(new StringBuilder(String.valueOf(str).length() + 27).append("Invalid type: ").append(str).append(", ").append(this.zzag).toString());
        }
        sb.append(", ");
        sb.append(this.zzag);
        sb.append(", ");
        sb.append(this.zzah);
        sb.append(")");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzab);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzac);
        SafeParcelWriter.writeDouble(parcel, 5, this.zzad);
        SafeParcelWriter.writeString(parcel, 6, this.zzae, false);
        SafeParcelWriter.writeByteArray(parcel, 7, this.zzaf, false);
        SafeParcelWriter.writeInt(parcel, 8, this.zzag);
        SafeParcelWriter.writeInt(parcel, 9, this.zzah);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
