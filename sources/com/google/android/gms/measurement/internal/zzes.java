package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.GuardedBy;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzes extends zzgq {
    /* access modifiers changed from: private */
    public char zza = 0;
    /* access modifiers changed from: private */
    public long zzb = -1;
    @GuardedBy("this")
    private String zzc;
    private final zzeu zzd = new zzeu(this, 6, false, false);
    private final zzeu zze = new zzeu(this, 6, true, false);
    private final zzeu zzf = new zzeu(this, 6, false, true);
    private final zzeu zzg = new zzeu(this, 5, false, false);
    private final zzeu zzh = new zzeu(this, 5, true, false);
    private final zzeu zzi = new zzeu(this, 5, false, true);
    private final zzeu zzj = new zzeu(this, 4, false, false);
    private final zzeu zzk = new zzeu(this, 3, false, false);
    private final zzeu zzl = new zzeu(this, 2, false, false);

    zzes(zzfw zzfw) {
        super(zzfw);
    }

    public final zzeu zzf() {
        return this.zzd;
    }

    public final zzeu zzg() {
        return this.zze;
    }

    public final zzeu zzh() {
        return this.zzf;
    }

    public final zzeu zzi() {
        return this.zzg;
    }

    public final zzeu zzj() {
        return this.zzh;
    }

    public final zzeu zzk() {
        return this.zzi;
    }

    public final zzeu zzv() {
        return this.zzj;
    }

    public final zzeu zzw() {
        return this.zzk;
    }

    public final zzeu zzx() {
        return this.zzl;
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return false;
    }

    protected static Object zza(String str) {
        if (str == null) {
            return null;
        }
        return new zzex(str);
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        int i2 = 0;
        if (!z && zza(i)) {
            zza(i, zza(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            Preconditions.checkNotNull(str);
            zzft zzg2 = this.zzz.zzg();
            if (zzg2 == null) {
                zza(6, "Scheduler not set. Not logging error/warn");
            } else if (!zzg2.zzz()) {
                zza(6, "Scheduler not initialized. Not logging error/warn");
            } else {
                if (i >= 0) {
                    i2 = i;
                }
                if (i2 >= 9) {
                    i2 = 8;
                }
                zzg2.zza((Runnable) new zzev(this, i2, str, obj, obj2, obj3));
            }
        }
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final boolean zza(int i) {
        return Log.isLoggable(zzad(), i);
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public final void zza(int i, String str) {
        Log.println(i, zzad(), str);
    }

    @VisibleForTesting
    private final String zzad() {
        String str;
        String str2;
        zzes zzes;
        synchronized (this) {
            if (this.zzc == null) {
                if (this.zzz.zzs() != null) {
                    str2 = this.zzz.zzs();
                    zzes = this;
                } else {
                    zzt().zzu();
                    str2 = "FA";
                    zzes = this;
                }
                zzes.zzc = str2;
            }
            str = this.zzc;
        }
        return str;
    }

    static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            str = "";
        }
        String zza2 = zza(z, obj);
        String zza3 = zza(z, obj2);
        String zza4 = zza(z, obj3);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(zza2)) {
            sb.append(str2);
            sb.append(zza2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zza3)) {
            sb.append(str2);
            sb.append(zza3);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zza4)) {
            sb.append(str2);
            sb.append(zza4);
        }
        return sb.toString();
    }

    @VisibleForTesting
    private static String zza(boolean z, Object obj) {
        Object obj2;
        String className;
        if (obj == null) {
            return "";
        }
        if (obj instanceof Integer) {
            obj2 = Long.valueOf((long) ((Integer) obj).intValue());
        } else {
            obj2 = obj;
        }
        if (obj2 instanceof Long) {
            if (!z) {
                return String.valueOf(obj2);
            }
            if (Math.abs(((Long) obj2).longValue()) < 100) {
                return String.valueOf(obj2);
            }
            String str = String.valueOf(obj2).charAt(0) == '-' ? "-" : "";
            String valueOf = String.valueOf(Math.abs(((Long) obj2).longValue()));
            return new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(str).length()).append(str).append(Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1)))).append("...").append(str).append(Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d)).toString();
        } else if (obj2 instanceof Boolean) {
            return String.valueOf(obj2);
        } else {
            if (obj2 instanceof Throwable) {
                Throwable th = (Throwable) obj2;
                StringBuilder sb = new StringBuilder(z ? th.getClass().getName() : th.toString());
                String zzb2 = zzb(zzfw.class.getCanonicalName());
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTrace[i];
                    if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null && zzb(className).equals(zzb2)) {
                        sb.append(": ");
                        sb.append(stackTraceElement);
                        break;
                    }
                    i++;
                }
                return sb.toString();
            } else if (obj2 instanceof zzex) {
                return ((zzex) obj2).zza;
            } else {
                if (z) {
                    return "-";
                }
                return String.valueOf(obj2);
            }
        }
    }

    private static String zzb(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : str;
    }

    public final String zzy() {
        Pair<String, Long> zza2 = zzs().zzb.zza();
        if (zza2 == null || zza2 == zzfe.zza) {
            return null;
        }
        String valueOf = String.valueOf(zza2.second);
        String str = (String) zza2.first;
        return new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length()).append(valueOf).append(":").append(str).toString();
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
