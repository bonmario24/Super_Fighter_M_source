package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzx;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzgy {
    final Context zza;
    String zzb;
    String zzc;
    String zzd;
    Boolean zze;
    long zzf;
    zzx zzg;
    boolean zzh = true;
    Long zzi;

    @VisibleForTesting
    public zzgy(Context context, zzx zzx, Long l) {
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zza = applicationContext;
        this.zzi = l;
        if (zzx != null) {
            this.zzg = zzx;
            this.zzb = zzx.zzf;
            this.zzc = zzx.zze;
            this.zzd = zzx.zzd;
            this.zzh = zzx.zzc;
            this.zzf = zzx.zzb;
            if (zzx.zzg != null) {
                this.zze = Boolean.valueOf(zzx.zzg.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
