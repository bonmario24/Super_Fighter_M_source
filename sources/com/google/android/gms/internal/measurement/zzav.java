package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzz;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.4.0 */
final class zzav extends zzz.zzb {
    private final /* synthetic */ zzl zzc;
    private final /* synthetic */ zzz zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzav(zzz zzz, zzl zzl) {
        super(zzz);
        this.zzd = zzz;
        this.zzc = zzl;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        this.zzd.zzr.getAppInstanceId(this.zzc);
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzc.zza((Bundle) null);
    }
}
