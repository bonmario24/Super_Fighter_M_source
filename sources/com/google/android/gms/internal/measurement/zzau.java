package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.internal.measurement.zzz;
import com.google.android.gms.measurement.internal.zzgz;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.4.0 */
final class zzau extends zzz.zzb {
    private final /* synthetic */ zzgz zzc;
    private final /* synthetic */ zzz zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzau(zzz zzz, zzgz zzgz) {
        super(zzz);
        this.zzd = zzz;
        this.zzc = zzgz;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.zzd.zzf.size()) {
                zzz.zzd zzd2 = new zzz.zzd(this.zzc);
                this.zzd.zzf.add(new Pair(this.zzc, zzd2));
                this.zzd.zzr.registerOnMeasurementEventListener(zzd2);
                return;
            } else if (this.zzc.equals(((Pair) this.zzd.zzf.get(i2)).first)) {
                Log.w(this.zzd.zzc, "OnEventListener already registered.");
                return;
            } else {
                i = i2 + 1;
            }
        }
    }
}
