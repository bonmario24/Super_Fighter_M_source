package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzfe extends zzgq {
    @VisibleForTesting
    static final Pair<String, Long> zza = new Pair<>("", 0L);
    private SharedPreferences zzaa;
    private String zzab;
    private boolean zzac;
    private long zzad;
    public zzfl zzb;
    public final zzfi zzc = new zzfi(this, "last_upload", 0);
    public final zzfi zzd = new zzfi(this, "last_upload_attempt", 0);
    public final zzfi zze = new zzfi(this, "backoff", 0);
    public final zzfi zzf = new zzfi(this, "last_delete_stale", 0);
    public final zzfi zzg = new zzfi(this, "midnight_offset", 0);
    public final zzfi zzh = new zzfi(this, "first_open_time", 0);
    public final zzfi zzi = new zzfi(this, "app_install_time", 0);
    public final zzfk zzj = new zzfk(this, "app_instance_id", (String) null);
    public final zzfi zzk = new zzfi(this, "time_before_start", 10000);
    public final zzfi zzl = new zzfi(this, "session_timeout", 1800000);
    public final zzfg zzm = new zzfg(this, "start_new_session", true);
    public final zzfk zzn = new zzfk(this, "non_personalized_ads", (String) null);
    public final zzfg zzo = new zzfg(this, "use_dynamite_api", false);
    public final zzfg zzp = new zzfg(this, "allow_remote_dynamite", false);
    public final zzfi zzq = new zzfi(this, "last_pause_time", 0);
    public boolean zzr;
    public zzfg zzs = new zzfg(this, "app_backgrounded", false);
    public zzfg zzt = new zzfg(this, "deep_link_retrieval_complete", false);
    public zzfi zzu = new zzfi(this, "deep_link_retrieval_attempts", 0);
    public final zzfk zzv = new zzfk(this, "firebase_feature_rollouts", (String) null);
    public final zzfk zzw = new zzfk(this, "deferred_attribution_cache", (String) null);
    public final zzfi zzx = new zzfi(this, "deferred_attribution_cache_timestamp", 0);
    public final zzfj zzy = new zzfj(this, "default_event_parameters", (Bundle) null);

    /* access modifiers changed from: package-private */
    @WorkerThread
    @NonNull
    public final Pair<String, Boolean> zza(String str) {
        zzd();
        long elapsedRealtime = zzm().elapsedRealtime();
        if (this.zzab != null && elapsedRealtime < this.zzad) {
            return new Pair<>(this.zzab, Boolean.valueOf(this.zzac));
        }
        this.zzad = elapsedRealtime + zzt().zza(str, zzaq.zza);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(zzn());
            if (advertisingIdInfo != null) {
                this.zzab = advertisingIdInfo.getId();
                this.zzac = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzab == null) {
                this.zzab = "";
            }
        } catch (Exception e) {
            zzr().zzw().zza("Unable to get advertising id", e);
            this.zzab = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzab, Boolean.valueOf(this.zzac));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String zzb(String str) {
        zzd();
        String str2 = (String) zza(str).first;
        MessageDigest zzi2 = zzkm.zzi();
        if (zzi2 == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzi2.digest(str2.getBytes()))});
    }

    zzfe(zzfw zzfw) {
        super(zzfw);
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return true;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    /* renamed from: f_ */
    public final void mo24471f_() {
        this.zzaa = zzn().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzr = this.zzaa.getBoolean("has_been_opened", false);
        if (!this.zzr) {
            SharedPreferences.Editor edit = this.zzaa.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.zzb = new zzfl(this, "health_monitor", Math.max(0, zzaq.zzb.zza(null).longValue()));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final SharedPreferences zzg() {
        zzd();
        zzaa();
        return this.zzaa;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(String str) {
        zzd();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String zzh() {
        zzd();
        return zzg().getString("gmp_app_id", (String) null);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzd(String str) {
        zzd();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("admob_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String zzi() {
        zzd();
        return zzg().getString("admob_app_id", (String) null);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final Boolean zzj() {
        zzd();
        if (!zzg().contains("use_service")) {
            return null;
        }
        return Boolean.valueOf(zzg().getBoolean("use_service", false));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(boolean z) {
        zzd();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzk() {
        zzd();
        Boolean zzv2 = zzv();
        SharedPreferences.Editor edit = zzg().edit();
        edit.clear();
        edit.apply();
        if (zzv2 != null) {
            zzb(zzv2.booleanValue());
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(boolean z) {
        zzd();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putBoolean("measurement_enabled", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final Boolean zzv() {
        zzd();
        if (zzg().contains("measurement_enabled")) {
            return Boolean.valueOf(zzg().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final String zzw() {
        zzd();
        String string = zzg().getString("previous_os_version", (String) null);
        zzl().zzaa();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor edit = zzg().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(boolean z) {
        zzd();
        zzr().zzx().zza("App measurement setting deferred collection", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzg().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzx() {
        return this.zzaa.contains("deferred_analytics_collection");
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(long j) {
        return j - this.zzl.zza() > this.zzq.zza();
    }
}
