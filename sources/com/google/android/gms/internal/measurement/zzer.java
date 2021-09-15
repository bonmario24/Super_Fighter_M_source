package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
public class zzer {
    private static volatile boolean zza = false;
    private static boolean zzb = true;
    private static volatile zzer zzc;
    private static volatile zzer zzd;
    private static final zzer zze = new zzer(true);
    private final Map<zza, zzfe.zzd<?, ?>> zzf;

    public static zzer zza() {
        zzer zzer = zzc;
        if (zzer == null) {
            synchronized (zzer.class) {
                zzer = zzc;
                if (zzer == null) {
                    zzer = zze;
                    zzc = zzer;
                }
            }
        }
        return zzer;
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
    static final class zza {
        private final Object zza;
        private final int zzb;

        zza(Object obj, int i) {
            this.zza = obj;
            this.zzb = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.zza) * 65535) + this.zzb;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza2 = (zza) obj;
            if (this.zza == zza2.zza && this.zzb == zza2.zzb) {
                return true;
            }
            return false;
        }
    }

    public static zzer zzb() {
        zzer zzer = zzd;
        if (zzer == null) {
            synchronized (zzer.class) {
                zzer = zzd;
                if (zzer == null) {
                    zzer = zzfd.zza(zzer.class);
                    zzd = zzer;
                }
            }
        }
        return zzer;
    }

    public final <ContainingType extends zzgm> zzfe.zzd<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return this.zzf.get(new zza(containingtype, i));
    }

    zzer() {
        this.zzf = new HashMap();
    }

    private zzer(boolean z) {
        this.zzf = Collections.emptyMap();
    }
}
