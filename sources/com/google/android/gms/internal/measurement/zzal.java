package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzz;
import com.google.android.gms.measurement.internal.zzgw;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.4.0 */
final class zzal extends zzz.zzb {
    private final /* synthetic */ zzgw zzc;
    private final /* synthetic */ zzz zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzal(zzz zzz, zzgw zzgw) {
        super(zzz);
        this.zzd = zzz;
        this.zzc = zzgw;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        this.zzd.zzr.setEventInterceptor(new zzz.zza(this.zzc));
    }
}
