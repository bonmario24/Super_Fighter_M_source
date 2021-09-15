package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzz;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.4.0 */
final class zzbi extends zzz.zzb {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ zzz.zzc zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbi(zzz.zzc zzc2, Activity activity) {
        super(zzz.this);
        this.zzd = zzc2;
        this.zzc = activity;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        zzz.this.zzr.onActivityDestroyed(ObjectWrapper.wrap(this.zzc), this.zzb);
    }
}
