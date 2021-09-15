package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import com.appsflyer.internal.referrer.Payload;

@TargetApi(14)
@MainThread
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final class zzhw implements Application.ActivityLifecycleCallbacks {
    private final /* synthetic */ zzhb zza;

    private zzhw(zzhb zzhb) {
        this.zza = zzhb;
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        String str;
        try {
            this.zza.zzr().zzx().zza("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent != null) {
                Uri data = intent.getData();
                if (data == null || !data.isHierarchical()) {
                    this.zza.zzi().zza(activity, bundle);
                    return;
                }
                this.zza.zzp();
                if (zzkm.zza(intent)) {
                    str = "gs";
                } else {
                    str = "auto";
                }
                this.zza.zzq().zza((Runnable) new zzhz(this, bundle == null, data, str, data.getQueryParameter(Payload.REFERRER)));
                this.zza.zzi().zza(activity, bundle);
            }
        } catch (Exception e) {
            this.zza.zzr().zzf().zza("Throwable caught in onActivityCreated", e);
        } finally {
            this.zza.zzi().zza(activity, bundle);
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(boolean z, Uri uri, String str, String str2) {
        Bundle bundle;
        boolean z2;
        String str3;
        Bundle bundle2 = null;
        this.zza.zzd();
        try {
            if (this.zza.zzt().zza(zzaq.zzbi) || this.zza.zzt().zza(zzaq.zzbk) || this.zza.zzt().zza(zzaq.zzbj)) {
                zzkm zzp = this.zza.zzp();
                if (TextUtils.isEmpty(str2)) {
                    bundle = null;
                } else if (str2.contains("gclid") || str2.contains("utm_campaign") || str2.contains("utm_source") || str2.contains("utm_medium")) {
                    String valueOf = String.valueOf(str2);
                    if (valueOf.length() != 0) {
                        str3 = "https://google.com/search?".concat(valueOf);
                    } else {
                        str3 = new String("https://google.com/search?");
                    }
                    bundle = zzp.zza(Uri.parse(str3));
                    if (bundle != null) {
                        bundle.putString("_cis", Payload.REFERRER);
                    }
                } else {
                    zzp.zzr().zzw().zza("Activity created with data 'referrer' without required params");
                    bundle = null;
                }
            } else {
                bundle = null;
            }
            if (z && (bundle2 = this.zza.zzp().zza(uri)) != null) {
                bundle2.putString("_cis", "intent");
                if (this.zza.zzt().zza(zzaq.zzbi) && !bundle2.containsKey("gclid") && bundle != null && bundle.containsKey("gclid")) {
                    bundle2.putString("_cer", String.format("gclid=%s", new Object[]{bundle.getString("gclid")}));
                }
                this.zza.zza(str, "_cmp", bundle2);
                if (this.zza.zzt().zza(zzaq.zzcp)) {
                    this.zza.zzb.zza(str, bundle2);
                }
            }
            if (this.zza.zzt().zza(zzaq.zzbk) && !this.zza.zzt().zza(zzaq.zzbj) && bundle != null && bundle.containsKey("gclid") && (bundle2 == null || !bundle2.containsKey("gclid"))) {
                this.zza.zza("auto", "_lgclid", (Object) bundle.getString("gclid"), true);
            }
            if (!TextUtils.isEmpty(str2)) {
                this.zza.zzr().zzw().zza("Activity created with referrer", str2);
                if (this.zza.zzt().zza(zzaq.zzbj)) {
                    if (bundle != null) {
                        this.zza.zza(str, "_cmp", bundle);
                        if (this.zza.zzt().zza(zzaq.zzcp)) {
                            this.zza.zzb.zza(str, bundle);
                        }
                    } else {
                        this.zza.zzr().zzw().zza("Referrer does not contain valid parameters", str2);
                    }
                    this.zza.zza("auto", "_ldl", (Object) null, true);
                    return;
                }
                if (!str2.contains("gclid") || (!str2.contains("utm_campaign") && !str2.contains("utm_source") && !str2.contains("utm_medium") && !str2.contains("utm_term") && !str2.contains("utm_content"))) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (!z2) {
                    this.zza.zzr().zzw().zza("Activity created with data 'referrer' without required params");
                } else if (!TextUtils.isEmpty(str2)) {
                    this.zza.zza("auto", "_ldl", (Object) str2, true);
                }
            }
        } catch (Exception e) {
            this.zza.zzr().zzf().zza("Throwable caught in handleReferrerForOnActivityCreated", e);
        }
    }

    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzi().zzc(activity);
    }

    @MainThread
    public final void onActivityPaused(Activity activity) {
        this.zza.zzi().zzb(activity);
        zzjm zzk = this.zza.zzk();
        zzk.zzq().zza((Runnable) new zzjo(zzk, zzk.zzm().elapsedRealtime()));
    }

    @MainThread
    public final void onActivityResumed(Activity activity) {
        zzjm zzk = this.zza.zzk();
        zzk.zzq().zza((Runnable) new zzjp(zzk, zzk.zzm().elapsedRealtime()));
        this.zza.zzi().zza(activity);
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zza.zzi().zzb(activity, bundle);
    }

    /* synthetic */ zzhw(zzhb zzhb, zzhc zzhc) {
        this(zzhb);
    }
}
