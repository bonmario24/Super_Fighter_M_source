package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzlc;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final /* synthetic */ class zzha implements Runnable {
    private final zzhb zza;
    private final Bundle zzb;

    zzha(zzhb zzhb, Bundle bundle) {
        this.zza = zzhb;
        this.zzb = bundle;
    }

    public final void run() {
        zzhb zzhb = this.zza;
        Bundle bundle = this.zzb;
        if (zzlc.zzb() && zzhb.zzt().zza(zzaq.zzcn)) {
            if (bundle == null) {
                zzhb.zzs().zzy.zza(new Bundle());
                return;
            }
            Bundle zza2 = zzhb.zzs().zzy.zza();
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (obj != null && !(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Double)) {
                    zzhb.zzp();
                    if (zzkm.zza(obj)) {
                        zzhb.zzp().zza(27, (String) null, (String) null, 0);
                    }
                    zzhb.zzr().zzk().zza("Invalid default event parameter type. Name, value", str, obj);
                } else if (zzkm.zze(str)) {
                    zzhb.zzr().zzk().zza("Invalid default event parameter name. Name", str);
                } else if (obj == null) {
                    zza2.remove(str);
                } else if (zzhb.zzp().zza("param", str, 100, obj)) {
                    zzhb.zzp().zza(zza2, str, obj);
                }
            }
            zzhb.zzp();
            if (zzkm.zza(zza2, zzhb.zzt().zze())) {
                zzhb.zzp().zza(26, (String) null, (String) null, 0);
                zzhb.zzr().zzk().zza("Too many default event parameters set. Discarding beyond event parameter limit");
            }
            zzhb.zzs().zzy.zza(zza2);
        }
    }
}
