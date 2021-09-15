package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzfk {
    private final String zza;
    private final String zzb = null;
    private boolean zzc;
    private String zzd;
    private final /* synthetic */ zzfe zze;

    public zzfk(zzfe zzfe, String str, String str2) {
        this.zze = zzfe;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
    }

    @WorkerThread
    public final String zza() {
        if (!this.zzc) {
            this.zzc = true;
            this.zzd = this.zze.zzg().getString(this.zza, (String) null);
        }
        return this.zzd;
    }

    @WorkerThread
    public final void zza(String str) {
        if (this.zze.zzt().zza(zzaq.zzbx) || !zzkm.zzc(str, this.zzd)) {
            SharedPreferences.Editor edit = this.zze.zzg().edit();
            edit.putString(this.zza, str);
            edit.apply();
            this.zzd = str;
        }
    }
}
