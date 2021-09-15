package com.google.firebase.analytics;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-measurement-api@@17.4.0 */
final class zza implements Callable<String> {
    private final /* synthetic */ FirebaseAnalytics zza;

    zza(FirebaseAnalytics firebaseAnalytics) {
        this.zza = firebaseAnalytics;
    }

    public final /* synthetic */ Object call() throws Exception {
        String zza2 = this.zza.zzb();
        if (zza2 == null) {
            if (this.zza.zzd) {
                zza2 = this.zza.zzc.zzh();
            } else {
                zza2 = this.zza.zzb.zzh().zzc(120000);
            }
            if (zza2 == null) {
                throw new TimeoutException();
            }
            this.zza.zza(zza2);
        }
        return zza2;
    }
}
