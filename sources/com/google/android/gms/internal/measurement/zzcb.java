package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzcb extends ContentObserver {
    private final /* synthetic */ zzbz zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcb(zzbz zzbz, Handler handler) {
        super((Handler) null);
        this.zza = zzbz;
    }

    public final void onChange(boolean z) {
        this.zza.zzb();
    }
}
