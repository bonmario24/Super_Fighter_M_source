package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzjm;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzeq extends zzgq {
    private static final AtomicReference<String[]> zza = new AtomicReference<>();
    private static final AtomicReference<String[]> zzb = new AtomicReference<>();
    private static final AtomicReference<String[]> zzc = new AtomicReference<>();

    zzeq(zzfw zzfw) {
        super(zzfw);
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return false;
    }

    private final boolean zzg() {
        zzu();
        return this.zzz.zzl() && this.zzz.zzr().zza(3);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zza(String str) {
        if (str == null) {
            return null;
        }
        return zzg() ? zza(str, zzgv.zzb, zzgv.zza, zza) : str;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzb(String str) {
        if (str == null) {
            return null;
        }
        return zzg() ? zza(str, zzgu.zzb, zzgu.zza, zzb) : str;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzc(String str) {
        if (str == null) {
            return null;
        }
        if (!zzg()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return zza(str, zzgx.zzb, zzgx.zza, zzc);
        }
        return "experiment_id" + "(" + str + ")";
    }

    @Nullable
    private static String zza(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        int i = 0;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        while (true) {
            if (i >= strArr.length) {
                break;
            } else if (zzkm.zzc(str, strArr[i])) {
                synchronized (atomicReference) {
                    String[] strArr3 = atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    if (strArr3[i] == null) {
                        strArr3[i] = strArr2[i] + "(" + strArr[i] + ")";
                    }
                    str = strArr3[i];
                }
            } else {
                i++;
            }
        }
        return str;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zza(zzao zzao) {
        String str = null;
        if (zzao == null) {
            return null;
        }
        if (!zzg()) {
            return zzao.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("origin=");
        sb.append(zzao.zzc);
        sb.append(",name=");
        sb.append(zza(zzao.zza));
        sb.append(",params=");
        zzan zzan = zzao.zzb;
        if (zzan != null) {
            if (!zzg()) {
                str = zzan.toString();
            } else {
                str = zza(zzan.zzb());
            }
        }
        sb.append(str);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zza(Bundle bundle) {
        String valueOf;
        if (bundle == null) {
            return null;
        }
        if (!zzg()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Bundle[{");
        for (String str : bundle.keySet()) {
            if (sb.length() != 8) {
                sb.append(", ");
            }
            sb.append(zzb(str));
            sb.append("=");
            if (!zzjm.zzb() || !zzt().zza(zzaq.zzce)) {
                sb.append(bundle.get(str));
            } else {
                Object obj = bundle.get(str);
                if (obj instanceof Bundle) {
                    valueOf = zza(new Object[]{obj});
                } else if (obj instanceof Object[]) {
                    valueOf = zza((Object[]) obj);
                } else if (obj instanceof ArrayList) {
                    valueOf = zza(((ArrayList) obj).toArray());
                } else {
                    valueOf = String.valueOf(obj);
                }
                sb.append(valueOf);
            }
        }
        sb.append("}]");
        return sb.toString();
    }

    @Nullable
    private final String zza(Object[] objArr) {
        String valueOf;
        if (objArr == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Bundle bundle : objArr) {
            if (bundle instanceof Bundle) {
                valueOf = zza(bundle);
            } else {
                valueOf = String.valueOf(bundle);
            }
            if (valueOf != null) {
                if (sb.length() != 1) {
                    sb.append(", ");
                }
                sb.append(valueOf);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    public final /* bridge */ /* synthetic */ zzai zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzeq zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzkm zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzft zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzes zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzfe zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzy zzt() {
        return super.zzt();
    }

    public final /* bridge */ /* synthetic */ zzx zzu() {
        return super.zzu();
    }
}
