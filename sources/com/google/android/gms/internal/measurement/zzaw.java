package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzz;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.4.0 */
final class zzaw extends zzz.zzb {
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzz zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaw(zzz zzz, boolean z) {
        super(zzz);
        this.zzd = zzz;
        this.zzc = z;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        this.zzd.zzr.setDataCollectionEnabled(this.zzc);
    }
}
