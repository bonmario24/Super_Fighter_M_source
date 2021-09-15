package com.google.android.gms.internal.p037authapiphone;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zze */
/* compiled from: com.google.android.gms:play-services-auth-api-phone@@17.1.0 */
public final class zze extends zzb implements zzf {
    zze(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.phone.internal.ISmsRetrieverApiService");
    }

    public final void zza(zzh zzh) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) zzh);
        zza(1, zza);
    }

    public final void zza(String str, zzh zzh) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, (IInterface) zzh);
        zza(2, zza);
    }
}
