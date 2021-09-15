package com.google.firebase.auth.api.internal;

import android.util.Log;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

@VisibleForTesting
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
final class zzap implements zzaq {
    private final int zza;
    private final int zzb;
    private final Map<String, Integer> zzc;

    public zzap(int i, int i2, Map<String, Integer> map) {
        this.zza = zza() ? 0 : i;
        this.zzb = i2;
        this.zzc = (Map) Preconditions.checkNotNull(map);
        zza();
    }

    public final boolean zza(String str) {
        if (this.zza == 0) {
            return true;
        }
        if (this.zzb <= this.zza) {
            return false;
        }
        Integer num = this.zzc.get(str);
        if (num == null) {
            num = 0;
        }
        return num.intValue() > this.zza && this.zzb >= num.intValue();
    }

    private static boolean zza() {
        boolean equals = ImagesContract.LOCAL.equals(zzgc.zza("firebear.preference"));
        if (equals) {
            Log.e("BiChannelGoogleApi", "Found local preference, will always use local service instance");
        }
        return equals;
    }
}
