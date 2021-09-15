package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzig extends zzg {
    @VisibleForTesting
    protected zzih zza;
    private volatile zzih zzb;
    private zzih zzc;
    private final Map<Activity, zzih> zzd = new ConcurrentHashMap();
    private zzih zze;
    private String zzf;

    public zzig(zzfw zzfw) {
        super(zzfw);
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    @WorkerThread
    public final zzih zzab() {
        zzw();
        zzd();
        return this.zza;
    }

    @MainThread
    public final void zza(@NonNull Activity activity, @Size(max = 36, min = 1) @Nullable String str, @Size(max = 36, min = 1) @Nullable String str2) {
        String str3;
        if (!zzt().zzj().booleanValue()) {
            zzr().zzk().zza("setCurrentScreen cannot be called while screen reporting is disabled.");
        } else if (this.zzb == null) {
            zzr().zzk().zza("setCurrentScreen cannot be called while no activity active");
        } else if (this.zzd.get(activity) == null) {
            zzr().zzk().zza("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = zza(activity.getClass().getCanonicalName());
            }
            boolean zzc2 = zzkm.zzc(this.zzb.zzb, str2);
            boolean zzc3 = zzkm.zzc(this.zzb.zza, str);
            if (zzc2 && zzc3) {
                zzr().zzk().zza("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() <= 0 || str.length() > 100)) {
                zzr().zzk().zza("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() > 0 && str2.length() <= 100)) {
                zzeu zzx = zzr().zzx();
                if (str == null) {
                    str3 = "null";
                } else {
                    str3 = str;
                }
                zzx.zza("Setting current screen to name, class", str3, str2);
                zzih zzih = new zzih(str, str2, zzp().zzg());
                this.zzd.put(activity, zzih);
                zza(activity, zzih, true);
            } else {
                zzr().zzk().zza("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    public final zzih zzac() {
        zzb();
        return this.zzb;
    }

    @MainThread
    private final void zza(Activity activity, zzih zzih, boolean z) {
        zzih zzih2;
        zzih zzih3 = this.zzb == null ? this.zzc : this.zzb;
        if (zzih.zzb == null) {
            zzih2 = new zzih(zzih.zza, activity != null ? zza(activity.getClass().getCanonicalName()) : null, zzih.zzc);
        } else {
            zzih2 = zzih;
        }
        this.zzc = this.zzb;
        this.zzb = zzih2;
        zzq().zza((Runnable) new zzij(this, z, zzm().elapsedRealtime(), zzih3, zzih2));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzih zzih, boolean z, long j) {
        boolean z2;
        zze().zza(zzm().elapsedRealtime());
        if (zzih == null || !zzih.zzd) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (zzk().zza(z2, z, j) && zzih != null) {
            zzih.zzd = false;
        }
    }

    public static void zza(zzih zzih, Bundle bundle, boolean z) {
        if (bundle != null && zzih != null && (!bundle.containsKey("_sc") || z)) {
            if (zzih.zza != null) {
                bundle.putString("_sn", zzih.zza);
            } else {
                bundle.remove("_sn");
            }
            if (zzih.zzb != null) {
                bundle.putString("_sc", zzih.zzb);
            } else {
                bundle.remove("_sc");
            }
            bundle.putLong("_si", zzih.zzc);
        } else if (bundle != null && zzih == null && z) {
            bundle.remove("_sn");
            bundle.remove("_sc");
            bundle.remove("_si");
        }
    }

    @WorkerThread
    public final void zza(String str, zzih zzih) {
        zzd();
        synchronized (this) {
            if (this.zzf == null || this.zzf.equals(str) || zzih != null) {
                this.zzf = str;
                this.zze = zzih;
            }
        }
    }

    @VisibleForTesting
    private static String zza(String str) {
        String str2;
        String[] split = str.split("\\.");
        if (split.length > 0) {
            str2 = split[split.length - 1];
        } else {
            str2 = "";
        }
        if (str2.length() > 100) {
            return str2.substring(0, 100);
        }
        return str2;
    }

    @MainThread
    private final zzih zzd(@NonNull Activity activity) {
        Preconditions.checkNotNull(activity);
        zzih zzih = this.zzd.get(activity);
        if (zzih != null) {
            return zzih;
        }
        zzih zzih2 = new zzih((String) null, zza(activity.getClass().getCanonicalName()), zzp().zzg());
        this.zzd.put(activity, zzih2);
        return zzih2;
    }

    @MainThread
    public final void zza(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (zzt().zzj().booleanValue() && bundle != null && (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) != null) {
            this.zzd.put(activity, new zzih(bundle2.getString("name"), bundle2.getString("referrer_name"), bundle2.getLong("id")));
        }
    }

    @MainThread
    public final void zza(Activity activity) {
        if (zzt().zzj().booleanValue()) {
            zza(activity, zzd(activity), false);
            zza zze2 = zze();
            zze2.zzq().zza((Runnable) new zze(zze2, zze2.zzm().elapsedRealtime()));
        }
    }

    @MainThread
    public final void zzb(Activity activity) {
        if (zzt().zzj().booleanValue()) {
            zzih zzd2 = zzd(activity);
            this.zzc = this.zzb;
            this.zzb = null;
            zzq().zza((Runnable) new zzii(this, zzd2, zzm().elapsedRealtime()));
        }
    }

    @MainThread
    public final void zzb(Activity activity, Bundle bundle) {
        zzih zzih;
        if (zzt().zzj().booleanValue() && bundle != null && (zzih = this.zzd.get(activity)) != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putLong("id", zzih.zzc);
            bundle2.putString("name", zzih.zza);
            bundle2.putString("referrer_name", zzih.zzb);
            bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
        }
    }

    @MainThread
    public final void zzc(Activity activity) {
        if (zzt().zzj().booleanValue()) {
            this.zzd.remove(activity);
        }
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

    public final /* bridge */ /* synthetic */ zza zze() {
        return super.zze();
    }

    public final /* bridge */ /* synthetic */ zzhb zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzep zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzil zzh() {
        return super.zzh();
    }

    public final /* bridge */ /* synthetic */ zzig zzi() {
        return super.zzi();
    }

    public final /* bridge */ /* synthetic */ zzeo zzj() {
        return super.zzj();
    }

    public final /* bridge */ /* synthetic */ zzjm zzk() {
        return super.zzk();
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
