package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzc;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public abstract class zzen extends zzc implements zzek {
    public zzen() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza((zzao) zzb.zza(parcel, zzao.CREATOR), (zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                break;
            case 2:
                zza((zzkh) zzb.zza(parcel, zzkh.CREATOR), (zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                break;
            case 4:
                zza((zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                break;
            case 5:
                zza((zzao) zzb.zza(parcel, zzao.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 6:
                zzb((zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                break;
            case 7:
                List<zzkh> zza = zza((zzn) zzb.zza(parcel, zzn.CREATOR), zzb.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            case 9:
                byte[] zza2 = zza((zzao) zzb.zza(parcel, zzao.CREATOR), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeByteArray(zza2);
                break;
            case 10:
                zza(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 11:
                String zzc = zzc((zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                parcel2.writeString(zzc);
                break;
            case 12:
                zza((zzw) zzb.zza(parcel, zzw.CREATOR), (zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                break;
            case 13:
                zza((zzw) zzb.zza(parcel, zzw.CREATOR));
                parcel2.writeNoException();
                break;
            case 14:
                List<zzkh> zza3 = zza(parcel.readString(), parcel.readString(), zzb.zza(parcel), (zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza3);
                break;
            case 15:
                List<zzkh> zza4 = zza(parcel.readString(), parcel.readString(), parcel.readString(), zzb.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza4);
                break;
            case 16:
                List<zzw> zza5 = zza(parcel.readString(), parcel.readString(), (zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza5);
                break;
            case 17:
                List<zzw> zza6 = zza(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeTypedList(zza6);
                break;
            case 18:
                zzd((zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
