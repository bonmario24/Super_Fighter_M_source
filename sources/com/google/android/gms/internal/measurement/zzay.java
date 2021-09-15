package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzz;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.4.0 */
final class zzay extends zzz.zzb {
    private final /* synthetic */ Long zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ String zze;
    private final /* synthetic */ Bundle zzf;
    private final /* synthetic */ boolean zzg;
    private final /* synthetic */ boolean zzh;
    private final /* synthetic */ zzz zzi;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzay(zzz zzz, Long l, String str, String str2, Bundle bundle, boolean z, boolean z2) {
        super(zzz);
        this.zzi = zzz;
        this.zzc = l;
        this.zzd = str;
        this.zze = str2;
        this.zzf = bundle;
        this.zzg = z;
        this.zzh = z2;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        this.zzi.zzr.logEvent(this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzc == null ? this.zza : this.zzc.longValue());
    }
}
