package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.internal.measurement.zzz;
import com.google.android.gms.measurement.internal.zzgz;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.4.0 */
final class zzaz extends zzz.zzb {
    private final /* synthetic */ zzgz zzc;
    private final /* synthetic */ zzz zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaz(zzz zzz, zzgz zzgz) {
        super(zzz);
        this.zzd = zzz;
        this.zzc = zzgz;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        Pair pair;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.zzd.zzf.size()) {
                pair = null;
                break;
            } else if (this.zzc.equals(((Pair) this.zzd.zzf.get(i2)).first)) {
                pair = (Pair) this.zzd.zzf.get(i2);
                break;
            } else {
                i = i2 + 1;
            }
        }
        if (pair == null) {
            Log.w(this.zzd.zzc, "OnEventListener had not been registered.");
            return;
        }
        this.zzd.zzr.unregisterOnMeasurementEventListener((zzq) pair.second);
        this.zzd.zzf.remove(pair);
    }
}
