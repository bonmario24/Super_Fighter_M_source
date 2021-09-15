package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzcn;
import com.google.android.gms.internal.measurement.zzjx;
import com.google.android.gms.internal.measurement.zzx;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public class zzfw implements zzgt {
    private static volatile zzfw zzb;
    @VisibleForTesting
    final long zza;
    private Boolean zzaa;
    private long zzab;
    private volatile Boolean zzac;
    @VisibleForTesting
    private Boolean zzad;
    @VisibleForTesting
    private Boolean zzae;
    private int zzaf;
    private AtomicInteger zzag = new AtomicInteger(0);
    private final Context zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final boolean zzg;
    private final zzx zzh;
    private final zzy zzi;
    private final zzfe zzj;
    private final zzes zzk;
    private final zzft zzl;
    private final zzjm zzm;
    private final zzkm zzn;
    private final zzeq zzo;
    private final Clock zzp;
    private final zzig zzq;
    private final zzhb zzr;
    private final zza zzs;
    private final zzib zzt;
    private zzeo zzu;
    private zzil zzv;
    private zzai zzw;
    private zzep zzx;
    private zzfn zzy;
    private boolean zzz = false;

    private zzfw(zzgy zzgy) {
        long currentTimeMillis;
        boolean z;
        Preconditions.checkNotNull(zzgy);
        this.zzh = new zzx(zzgy.zza);
        zzei.zza = this.zzh;
        this.zzc = zzgy.zza;
        this.zzd = zzgy.zzb;
        this.zze = zzgy.zzc;
        this.zzf = zzgy.zzd;
        this.zzg = zzgy.zzh;
        this.zzac = zzgy.zze;
        zzx zzx2 = zzgy.zzg;
        if (!(zzx2 == null || zzx2.zzg == null)) {
            Object obj = zzx2.zzg.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zzad = (Boolean) obj;
            }
            Object obj2 = zzx2.zzg.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzae = (Boolean) obj2;
            }
        }
        zzcn.zza(this.zzc);
        this.zzp = DefaultClock.getInstance();
        if (zzgy.zzi != null) {
            currentTimeMillis = zzgy.zzi.longValue();
        } else {
            currentTimeMillis = this.zzp.currentTimeMillis();
        }
        this.zza = currentTimeMillis;
        this.zzi = new zzy(this);
        zzfe zzfe = new zzfe(this);
        zzfe.zzab();
        this.zzj = zzfe;
        zzes zzes = new zzes(this);
        zzes.zzab();
        this.zzk = zzes;
        zzkm zzkm = new zzkm(this);
        zzkm.zzab();
        this.zzn = zzkm;
        zzeq zzeq = new zzeq(this);
        zzeq.zzab();
        this.zzo = zzeq;
        this.zzs = new zza(this);
        zzig zzig = new zzig(this);
        zzig.zzx();
        this.zzq = zzig;
        zzhb zzhb = new zzhb(this);
        zzhb.zzx();
        this.zzr = zzhb;
        zzjm zzjm = new zzjm(this);
        zzjm.zzx();
        this.zzm = zzjm;
        zzib zzib = new zzib(this);
        zzib.zzab();
        this.zzt = zzib;
        zzft zzft = new zzft(this);
        zzft.zzab();
        this.zzl = zzft;
        if (!((zzgy.zzg == null || zzgy.zzg.zzb == 0) ? false : true)) {
            z = true;
        } else {
            z = false;
        }
        zzx zzx3 = this.zzh;
        if (this.zzc.getApplicationContext() instanceof Application) {
            zzhb zzh2 = zzh();
            if (zzh2.zzn().getApplicationContext() instanceof Application) {
                Application application = (Application) zzh2.zzn().getApplicationContext();
                if (zzh2.zza == null) {
                    zzh2.zza = new zzhw(zzh2, (zzhc) null);
                }
                if (z) {
                    application.unregisterActivityLifecycleCallbacks(zzh2.zza);
                    application.registerActivityLifecycleCallbacks(zzh2.zza);
                    zzh2.zzr().zzx().zza("Registered activity lifecycle callback");
                }
            }
        } else {
            zzr().zzi().zza("Application context is not an Application");
        }
        this.zzl.zza((Runnable) new zzfy(this, zzgy));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzgy zzgy) {
        zzeu zzv2;
        String concat;
        zzq().zzd();
        zzai zzai = new zzai(this);
        zzai.zzab();
        this.zzw = zzai;
        zzep zzep = new zzep(this, zzgy.zzf);
        zzep.zzx();
        this.zzx = zzep;
        zzeo zzeo = new zzeo(this);
        zzeo.zzx();
        this.zzu = zzeo;
        zzil zzil = new zzil(this);
        zzil.zzx();
        this.zzv = zzil;
        this.zzn.zzac();
        this.zzj.zzac();
        this.zzy = new zzfn(this);
        this.zzx.zzy();
        zzr().zzv().zza("App measurement initialized, version", Long.valueOf(this.zzi.zzf()));
        zzx zzx2 = this.zzh;
        zzr().zzv().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzx zzx3 = this.zzh;
        String zzab2 = zzep.zzab();
        if (TextUtils.isEmpty(this.zzd)) {
            if (zzi().zzf(zzab2)) {
                zzv2 = zzr().zzv();
                concat = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
            } else {
                zzv2 = zzr().zzv();
                String valueOf = String.valueOf(zzab2);
                concat = valueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(valueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ");
            }
            zzv2.zza(concat);
        }
        zzr().zzw().zza("Debug-level message logging enabled");
        if (this.zzaf != this.zzag.get()) {
            zzr().zzf().zza("Not all components initialized", Integer.valueOf(this.zzaf), Integer.valueOf(this.zzag.get()));
        }
        this.zzz = true;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza() {
        boolean z = false;
        zzq().zzd();
        if (zzc().zzc.zza() == 0) {
            zzc().zzc.zza(this.zzp.currentTimeMillis());
        }
        if (Long.valueOf(zzc().zzh.zza()).longValue() == 0) {
            zzr().zzx().zza("Persisting first open", Long.valueOf(this.zza));
            zzc().zzh.zza(this.zza);
        }
        if (this.zzi.zza(zzaq.zzcp)) {
            zzx zzx2 = this.zzh;
            zzh().zzb.zzb();
        }
        if (zzag()) {
            zzx zzx3 = this.zzh;
            if (!TextUtils.isEmpty(zzy().zzac()) || !TextUtils.isEmpty(zzy().zzad())) {
                zzi();
                if (zzkm.zza(zzy().zzac(), zzc().zzh(), zzy().zzad(), zzc().zzi())) {
                    zzr().zzv().zza("Rechecking which service to use due to a GMP App Id change");
                    zzc().zzk();
                    zzk().zzab();
                    this.zzv.zzah();
                    this.zzv.zzaf();
                    zzc().zzh.zza(this.zza);
                    zzc().zzj.zza((String) null);
                }
                zzc().zzc(zzy().zzac());
                zzc().zzd(zzy().zzad());
            }
            zzh().zza(zzc().zzj.zza());
            zzx zzx4 = this.zzh;
            if (zzjx.zzb() && this.zzi.zza(zzaq.zzbv) && !zzi().zzv() && !TextUtils.isEmpty(zzc().zzv.zza())) {
                zzr().zzi().zza("Remote config removed with active feature rollouts");
                zzc().zzv.zza((String) null);
            }
            if (!TextUtils.isEmpty(zzy().zzac()) || !TextUtils.isEmpty(zzy().zzad())) {
                boolean zzab2 = zzab();
                if (!zzc().zzx() && !this.zzi.zzh()) {
                    zzfe zzc2 = zzc();
                    if (!zzab2) {
                        z = true;
                    }
                    zzc2.zzc(z);
                }
                if (zzab2) {
                    zzh().zzai();
                }
                zze().zza.zza();
                zzw().zza((AtomicReference<String>) new AtomicReference());
            }
        } else if (zzab()) {
            if (!zzi().zzd("android.permission.INTERNET")) {
                zzr().zzf().zza("App is missing INTERNET permission");
            }
            if (!zzi().zzd("android.permission.ACCESS_NETWORK_STATE")) {
                zzr().zzf().zza("App is missing ACCESS_NETWORK_STATE permission");
            }
            zzx zzx5 = this.zzh;
            if (!Wrappers.packageManager(this.zzc).isCallerInstantApp() && !this.zzi.zzy()) {
                if (!zzfo.zza(this.zzc)) {
                    zzr().zzf().zza("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzkm.zza(this.zzc, false)) {
                    zzr().zzf().zza("AppMeasurementService not registered/enabled");
                }
            }
            zzr().zzf().zza("Uploading is not possible. App measurement disabled");
        }
        zzc().zzo.zza(this.zzi.zza(zzaq.zzaz));
        zzc().zzp.zza(this.zzi.zza(zzaq.zzba));
    }

    public final zzx zzu() {
        return this.zzh;
    }

    public final zzy zzb() {
        return this.zzi;
    }

    public final zzfe zzc() {
        zza((zzgr) this.zzj);
        return this.zzj;
    }

    public final zzes zzr() {
        zzb((zzgq) this.zzk);
        return this.zzk;
    }

    public final zzes zzd() {
        if (this.zzk == null || !this.zzk.zzz()) {
            return null;
        }
        return this.zzk;
    }

    public final zzft zzq() {
        zzb((zzgq) this.zzl);
        return this.zzl;
    }

    public final zzjm zze() {
        zzb((zzg) this.zzm);
        return this.zzm;
    }

    public final zzfn zzf() {
        return this.zzy;
    }

    /* access modifiers changed from: package-private */
    public final zzft zzg() {
        return this.zzl;
    }

    public final zzhb zzh() {
        zzb((zzg) this.zzr);
        return this.zzr;
    }

    public final zzkm zzi() {
        zza((zzgr) this.zzn);
        return this.zzn;
    }

    public final zzeq zzj() {
        zza((zzgr) this.zzo);
        return this.zzo;
    }

    public final zzeo zzk() {
        zzb((zzg) this.zzu);
        return this.zzu;
    }

    private final zzib zzai() {
        zzb((zzgq) this.zzt);
        return this.zzt;
    }

    public final Context zzn() {
        return this.zzc;
    }

    public final boolean zzl() {
        return TextUtils.isEmpty(this.zzd);
    }

    public final String zzo() {
        return this.zzd;
    }

    public final String zzp() {
        return this.zze;
    }

    public final String zzs() {
        return this.zzf;
    }

    public final boolean zzt() {
        return this.zzg;
    }

    public final Clock zzm() {
        return this.zzp;
    }

    public final zzig zzv() {
        zzb((zzg) this.zzq);
        return this.zzq;
    }

    public final zzil zzw() {
        zzb((zzg) this.zzv);
        return this.zzv;
    }

    public final zzai zzx() {
        zzb((zzgq) this.zzw);
        return this.zzw;
    }

    public final zzep zzy() {
        zzb((zzg) this.zzx);
        return this.zzx;
    }

    public final zza zzz() {
        if (this.zzs != null) {
            return this.zzs;
        }
        throw new IllegalStateException("Component not created");
    }

    @VisibleForTesting
    public static zzfw zza(Context context, String str, String str2, Bundle bundle) {
        return zza(context, new zzx(0, 0, true, (String) null, (String) null, (String) null, bundle), (Long) null);
    }

    public static zzfw zza(Context context, zzx zzx2, Long l) {
        if (zzx2 != null && (zzx2.zze == null || zzx2.zzf == null)) {
            zzx2 = new zzx(zzx2.zza, zzx2.zzb, zzx2.zzc, zzx2.zzd, (String) null, (String) null, zzx2.zzg);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (zzfw.class) {
                if (zzb == null) {
                    zzb = new zzfw(new zzgy(context, zzx2, l));
                }
            }
        } else if (!(zzx2 == null || zzx2.zzg == null || !zzx2.zzg.containsKey("dataCollectionDefaultEnabled"))) {
            zzb.zza(zzx2.zzg.getBoolean("dataCollectionDefaultEnabled"));
        }
        return zzb;
    }

    private static void zzb(zzgq zzgq) {
        if (zzgq == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzgq.zzz()) {
            String valueOf = String.valueOf(zzgq.getClass());
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 27).append("Component not initialized: ").append(valueOf).toString());
        }
    }

    private static void zzb(zzg zzg2) {
        if (zzg2 == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzg2.zzv()) {
            String valueOf = String.valueOf(zzg2.getClass());
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 27).append("Component not initialized: ").append(valueOf).toString());
        }
    }

    private static void zza(zzgr zzgr) {
        if (zzgr == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(boolean z) {
        this.zzac = Boolean.valueOf(z);
    }

    @WorkerThread
    public final boolean zzaa() {
        return this.zzac != null && this.zzac.booleanValue();
    }

    @WorkerThread
    public final boolean zzab() {
        return zzac() == 0;
    }

    @WorkerThread
    public final int zzac() {
        zzq().zzd();
        if (this.zzi.zzh()) {
            return 1;
        }
        if (this.zzae != null && this.zzae.booleanValue()) {
            return 2;
        }
        Boolean zzv2 = zzc().zzv();
        if (zzv2 == null) {
            zzy zzy2 = this.zzi;
            zzy2.zzu();
            Boolean zzd2 = zzy2.zzd("firebase_analytics_collection_enabled");
            if (zzd2 != null) {
                if (!zzd2.booleanValue()) {
                    return 4;
                }
                return 0;
            } else if (this.zzad != null) {
                if (!this.zzad.booleanValue()) {
                    return 5;
                }
                return 0;
            } else if (GoogleServices.isMeasurementExplicitlyDisabled()) {
                return 6;
            } else {
                if (!this.zzi.zza(zzaq.zzas) || this.zzac == null || this.zzac.booleanValue()) {
                    return 0;
                }
                return 7;
            }
        } else if (!zzv2.booleanValue()) {
            return 3;
        } else {
            return 0;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzad() {
        zzx zzx2 = this.zzh;
    }

    /* access modifiers changed from: package-private */
    public final void zzae() {
        zzx zzx2 = this.zzh;
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzgq zzgq) {
        this.zzaf++;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzg zzg2) {
        this.zzaf++;
    }

    /* access modifiers changed from: package-private */
    public final void zzaf() {
        this.zzag.incrementAndGet();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final boolean zzag() {
        boolean z = false;
        if (!this.zzz) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
        zzq().zzd();
        if (this.zzaa == null || this.zzab == 0 || (this.zzaa != null && !this.zzaa.booleanValue() && Math.abs(this.zzp.elapsedRealtime() - this.zzab) > 1000)) {
            this.zzab = this.zzp.elapsedRealtime();
            zzx zzx2 = this.zzh;
            this.zzaa = Boolean.valueOf(zzi().zzd("android.permission.INTERNET") && zzi().zzd("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzc).isCallerInstantApp() || this.zzi.zzy() || (zzfo.zza(this.zzc) && zzkm.zza(this.zzc, false))));
            if (this.zzaa.booleanValue()) {
                if (zzi().zza(zzy().zzac(), zzy().zzad(), zzy().zzae()) || !TextUtils.isEmpty(zzy().zzad())) {
                    z = true;
                }
                this.zzaa = Boolean.valueOf(z);
            }
        }
        return this.zzaa.booleanValue();
    }

    @WorkerThread
    public final void zzah() {
        zzq().zzd();
        zzb((zzgq) zzai());
        String zzab2 = zzy().zzab();
        Pair<String, Boolean> zza2 = zzc().zza(zzab2);
        if (!this.zzi.zzi().booleanValue() || ((Boolean) zza2.second).booleanValue() || TextUtils.isEmpty((CharSequence) zza2.first)) {
            zzr().zzw().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
        } else if (!zzai().zzg()) {
            zzr().zzi().zza("Network is not available for Deferred Deep Link request. Skipping");
        } else {
            URL zza3 = zzi().zza(zzy().zzt().zzf(), zzab2, (String) zza2.first, zzc().zzu.zza() - 1);
            zzib zzai = zzai();
            zzfz zzfz = new zzfz(this);
            zzai.zzd();
            zzai.zzaa();
            Preconditions.checkNotNull(zza3);
            Preconditions.checkNotNull(zzfz);
            zzai.zzq().zzb((Runnable) new zzid(zzai, zzab2, zza3, (byte[]) null, (Map<String, String>) null, zzfz));
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(String str, int i, Throwable th, byte[] bArr, Map map) {
        List<ResolveInfo> queryIntentActivities;
        boolean z = true;
        if (!((i == 200 || i == 204 || i == 304) && th == null)) {
            zzr().zzi().zza("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i), th);
            return;
        }
        zzc().zzt.zza(true);
        if (bArr.length == 0) {
            zzr().zzw().zza("Deferred Deep Link response empty.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            String optString = jSONObject.optString("deeplink", "");
            String optString2 = jSONObject.optString("gclid", "");
            double optDouble = jSONObject.optDouble("timestamp", 0.0d);
            if (TextUtils.isEmpty(optString)) {
                zzr().zzw().zza("Deferred Deep Link is empty.");
                return;
            }
            zzkm zzi2 = zzi();
            zzi2.zzb();
            if (TextUtils.isEmpty(optString) || (queryIntentActivities = zzi2.zzn().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(optString)), 0)) == null || queryIntentActivities.isEmpty()) {
                z = false;
            }
            if (!z) {
                zzr().zzi().zza("Deferred Deep Link validation failed. gclid, deep link", optString2, optString);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("gclid", optString2);
            bundle.putString("_cis", "ddp");
            this.zzr.zza("auto", "_cmp", bundle);
            zzkm zzi3 = zzi();
            if (!TextUtils.isEmpty(optString) && zzi3.zza(optString, optDouble)) {
                zzi3.zzn().sendBroadcast(new Intent("android.google.analytics.action.DEEPLINK_ACTION"));
            }
        } catch (JSONException e) {
            zzr().zzf().zza("Failed to parse the Deferred Deep Link response. exception", e);
        }
    }
}
