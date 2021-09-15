package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzjg;
import com.google.android.gms.internal.measurement.zzlo;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzy extends zzgr {
    private Boolean zza;
    @NonNull
    private zzaa zzb = zzab.zza;
    private Boolean zzc;

    zzy(zzfw zzfw) {
        super(zzfw);
    }

    /* access modifiers changed from: package-private */
    public final void zza(@NonNull zzaa zzaa) {
        this.zzb = zzaa;
    }

    public final int zze() {
        if (!zzjg.zzb() || !zzt().zzd((String) null, zzaq.zzcl) || zzp().zzj() < 2147483) {
            return 25;
        }
        return 100;
    }

    /* access modifiers changed from: package-private */
    public final int zza(@Size(min = 1) String str) {
        if (!zzjg.zzb() || !zzd((String) null, zzaq.zzck)) {
            return 500;
        }
        return zza(str, zzaq.zzag, 500, CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE);
    }

    @WorkerThread
    public final int zzb(@Size(min = 1) String str) {
        return zzb(str, zzaq.zzn);
    }

    /* access modifiers changed from: package-private */
    public final int zzc(@Size(min = 1) String str) {
        if (!zzjg.zzb() || !zzd((String) null, zzaq.zzck)) {
            return 25;
        }
        return zza(str, zzaq.zzaf, 25, 100);
    }

    public final long zzf() {
        zzu();
        return 26001;
    }

    public final boolean zzg() {
        if (this.zzc == null) {
            synchronized (this) {
                if (this.zzc == null) {
                    ApplicationInfo applicationInfo = zzn().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzc = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if (this.zzc == null) {
                        this.zzc = Boolean.TRUE;
                        zzr().zzf().zza("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzc.booleanValue();
    }

    @WorkerThread
    public final long zza(String str, @NonNull zzel<Long> zzel) {
        if (str == null) {
            return zzel.zza(null).longValue();
        }
        String zza2 = this.zzb.zza(str, zzel.zza());
        if (TextUtils.isEmpty(zza2)) {
            return zzel.zza(null).longValue();
        }
        try {
            return zzel.zza(Long.valueOf(Long.parseLong(zza2))).longValue();
        } catch (NumberFormatException e) {
            return zzel.zza(null).longValue();
        }
    }

    @WorkerThread
    public final int zzb(String str, @NonNull zzel<Integer> zzel) {
        if (str == null) {
            return zzel.zza(null).intValue();
        }
        String zza2 = this.zzb.zza(str, zzel.zza());
        if (TextUtils.isEmpty(zza2)) {
            return zzel.zza(null).intValue();
        }
        try {
            return zzel.zza(Integer.valueOf(Integer.parseInt(zza2))).intValue();
        } catch (NumberFormatException e) {
            return zzel.zza(null).intValue();
        }
    }

    @WorkerThread
    public final int zza(String str, @NonNull zzel<Integer> zzel, int i, int i2) {
        return Math.max(Math.min(zzb(str, zzel), i2), i);
    }

    @WorkerThread
    public final double zzc(String str, @NonNull zzel<Double> zzel) {
        if (str == null) {
            return zzel.zza(null).doubleValue();
        }
        String zza2 = this.zzb.zza(str, zzel.zza());
        if (TextUtils.isEmpty(zza2)) {
            return zzel.zza(null).doubleValue();
        }
        try {
            return zzel.zza(Double.valueOf(Double.parseDouble(zza2))).doubleValue();
        } catch (NumberFormatException e) {
            return zzel.zza(null).doubleValue();
        }
    }

    @WorkerThread
    public final boolean zzd(String str, @NonNull zzel<Boolean> zzel) {
        if (str == null) {
            return zzel.zza(null).booleanValue();
        }
        String zza2 = this.zzb.zza(str, zzel.zza());
        if (TextUtils.isEmpty(zza2)) {
            return zzel.zza(null).booleanValue();
        }
        return zzel.zza(Boolean.valueOf(Boolean.parseBoolean(zza2))).booleanValue();
    }

    public final boolean zze(String str, zzel<Boolean> zzel) {
        return zzd(str, zzel);
    }

    public final boolean zza(zzel<Boolean> zzel) {
        return zzd((String) null, zzel);
    }

    @Nullable
    @VisibleForTesting
    private final Bundle zzz() {
        try {
            if (zzn().getPackageManager() == null) {
                zzr().zzf().zza("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(zzn()).getApplicationInfo(zzn().getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData;
            }
            zzr().zzf().zza("Failed to load metadata: ApplicationInfo is null");
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            zzr().zzf().zza("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @VisibleForTesting
    public final Boolean zzd(@Size(min = 1) String str) {
        Preconditions.checkNotEmpty(str);
        Bundle zzz = zzz();
        if (zzz == null) {
            zzr().zzf().zza("Failed to load metadata: Metadata bundle is null");
            return null;
        } else if (zzz.containsKey(str)) {
            return Boolean.valueOf(zzz.getBoolean(str));
        } else {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @VisibleForTesting
    public final List<String> zze(@Size(min = 1) String str) {
        Integer valueOf;
        Preconditions.checkNotEmpty(str);
        Bundle zzz = zzz();
        if (zzz == null) {
            zzr().zzf().zza("Failed to load metadata: Metadata bundle is null");
            valueOf = null;
        } else if (!zzz.containsKey(str)) {
            valueOf = null;
        } else {
            valueOf = Integer.valueOf(zzz.getInt(str));
        }
        if (valueOf == null) {
            return null;
        }
        try {
            String[] stringArray = zzn().getResources().getStringArray(valueOf.intValue());
            if (stringArray != null) {
                return Arrays.asList(stringArray);
            }
            return null;
        } catch (Resources.NotFoundException e) {
            zzr().zzf().zza("Failed to load string array from metadata: resource not found", e);
            return null;
        }
    }

    public final boolean zzh() {
        zzu();
        Boolean zzd = zzd("firebase_analytics_collection_deactivated");
        return zzd != null && zzd.booleanValue();
    }

    public final Boolean zzi() {
        zzb();
        Boolean zzd = zzd("google_analytics_adid_collection_enabled");
        return Boolean.valueOf(zzd == null || zzd.booleanValue());
    }

    public final Boolean zzj() {
        boolean z = true;
        zzb();
        if (!zzlo.zzb() || !zza(zzaq.zzcc)) {
            return true;
        }
        Boolean zzd = zzd("google_analytics_automatic_screen_reporting_enabled");
        if (zzd != null && !zzd.booleanValue()) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public static long zzk() {
        return zzaq.zzac.zza(null).longValue();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002e, code lost:
        if (android.text.TextUtils.isEmpty(r0) != false) goto L_0x0030;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0089  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza(com.google.android.gms.measurement.internal.zzf r7) {
        /*
            r6 = this;
            r4 = 0
            android.net.Uri$Builder r2 = new android.net.Uri$Builder
            r2.<init>()
            java.lang.String r0 = r7.zze()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x008f
            boolean r0 = com.google.android.gms.internal.measurement.zzlb.zzb()
            if (r0 == 0) goto L_0x0030
            com.google.android.gms.measurement.internal.zzy r0 = r6.zzt()
            java.lang.String r1 = r7.zzc()
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzaq.zzbo
            boolean r0 = r0.zzd(r1, r3)
            if (r0 == 0) goto L_0x0030
            java.lang.String r0 = r7.zzg()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x008f
        L_0x0030:
            java.lang.String r0 = r7.zzf()
            r1 = r0
        L_0x0035:
            com.google.android.gms.measurement.internal.zzel<java.lang.String> r0 = com.google.android.gms.measurement.internal.zzaq.zzd
            java.lang.Object r0 = r0.zza(r4)
            java.lang.String r0 = (java.lang.String) r0
            android.net.Uri$Builder r3 = r2.scheme(r0)
            com.google.android.gms.measurement.internal.zzel<java.lang.String> r0 = com.google.android.gms.measurement.internal.zzaq.zze
            java.lang.Object r0 = r0.zza(r4)
            java.lang.String r0 = (java.lang.String) r0
            android.net.Uri$Builder r3 = r3.encodedAuthority(r0)
            java.lang.String r4 = "config/app/"
            java.lang.String r0 = java.lang.String.valueOf(r1)
            int r1 = r0.length()
            if (r1 == 0) goto L_0x0089
            java.lang.String r0 = r4.concat(r0)
        L_0x005d:
            android.net.Uri$Builder r0 = r3.path(r0)
            java.lang.String r1 = "app_instance_id"
            java.lang.String r3 = r7.zzd()
            android.net.Uri$Builder r0 = r0.appendQueryParameter(r1, r3)
            java.lang.String r1 = "platform"
            java.lang.String r3 = "android"
            android.net.Uri$Builder r0 = r0.appendQueryParameter(r1, r3)
            java.lang.String r1 = "gmp_version"
            long r4 = r6.zzf()
            java.lang.String r3 = java.lang.String.valueOf(r4)
            r0.appendQueryParameter(r1, r3)
            android.net.Uri r0 = r2.build()
            java.lang.String r0 = r0.toString()
            return r0
        L_0x0089:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4)
            goto L_0x005d
        L_0x008f:
            r1 = r0
            goto L_0x0035
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzy.zza(com.google.android.gms.measurement.internal.zzf):java.lang.String");
    }

    public static long zzv() {
        return zzaq.zzc.zza(null).longValue();
    }

    public final String zzw() {
        return zza("debug.firebase.analytics.app", "");
    }

    public final String zzx() {
        return zza("debug.deferred.deeplink", "");
    }

    private final String zza(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke((Object) null, new Object[]{str, str2});
        } catch (ClassNotFoundException e) {
            zzr().zzf().zza("Could not find SystemProperties class", e);
        } catch (NoSuchMethodException e2) {
            zzr().zzf().zza("Could not find SystemProperties.get() method", e2);
        } catch (IllegalAccessException e3) {
            zzr().zzf().zza("Could not access SystemProperties.get()", e3);
        } catch (InvocationTargetException e4) {
            zzr().zzf().zza("SystemProperties.get() threw an exception", e4);
        }
        return str2;
    }

    public final boolean zzf(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(this.zzb.zza(str, "gaia_collection_enabled"));
    }

    public final boolean zzg(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(this.zzb.zza(str, "measurement.event_sampling_enabled"));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzh(String str) {
        return zzd(str, zzaq.zzaj);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String zzi(String str) {
        zzel<String> zzel = zzaq.zzak;
        if (str == null) {
            return zzel.zza(null);
        }
        return zzel.zza(this.zzb.zza(str, zzel.zza()));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzy() {
        if (this.zza == null) {
            this.zza = zzd("app_measurement_lite");
            if (this.zza == null) {
                this.zza = false;
            }
        }
        if (this.zza.booleanValue() || !this.zzz.zzt()) {
            return true;
        }
        return false;
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
