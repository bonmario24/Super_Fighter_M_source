package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.MainThread;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzx;
import com.google.android.gms.measurement.internal.zzjn;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
public final class zzjj<T extends Context & zzjn> {
    private final T zza;

    public zzjj(T t) {
        Preconditions.checkNotNull(t);
        this.zza = t;
    }

    @MainThread
    public final void zza() {
        zzfw zza2 = zzfw.zza(this.zza, (zzx) null, (Long) null);
        zzes zzr = zza2.zzr();
        zza2.zzu();
        zzr.zzx().zza("Local AppMeasurementService is starting up");
    }

    @MainThread
    public final void zzb() {
        zzfw zza2 = zzfw.zza(this.zza, (zzx) null, (Long) null);
        zzes zzr = zza2.zzr();
        zza2.zzu();
        zzr.zzx().zza("Local AppMeasurementService is shutting down");
    }

    @MainThread
    public final int zza(Intent intent, int i, int i2) {
        zzfw zza2 = zzfw.zza(this.zza, (zzx) null, (Long) null);
        zzes zzr = zza2.zzr();
        if (intent == null) {
            zzr.zzi().zza("AppMeasurementService started with null intent");
        } else {
            String action = intent.getAction();
            zza2.zzu();
            zzr.zzx().zza("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
            if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
                zza((Runnable) new zzji(this, i2, zzr, intent));
            }
        }
        return 2;
    }

    private final void zza(Runnable runnable) {
        zzka zza2 = zzka.zza((Context) this.zza);
        zza2.zzq().zza((Runnable) new zzjk(this, zza2, runnable));
    }

    @MainThread
    public final IBinder zza(Intent intent) {
        if (intent == null) {
            zzc().zzf().zza("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzgb(zzka.zza((Context) this.zza));
        }
        zzc().zzi().zza("onBind received unknown action", action);
        return null;
    }

    @MainThread
    public final boolean zzb(Intent intent) {
        if (intent == null) {
            zzc().zzf().zza("onUnbind called with null intent");
        } else {
            zzc().zzx().zza("onUnbind called for intent. action", intent.getAction());
        }
        return true;
    }

    @TargetApi(24)
    @MainThread
    public final boolean zza(JobParameters jobParameters) {
        zzfw zza2 = zzfw.zza(this.zza, (zzx) null, (Long) null);
        zzes zzr = zza2.zzr();
        String string = jobParameters.getExtras().getString(NativeProtocol.WEB_DIALOG_ACTION);
        zza2.zzu();
        zzr.zzx().zza("Local AppMeasurementJobService called. action", string);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(string)) {
            return true;
        }
        zza((Runnable) new zzjl(this, zzr, jobParameters));
        return true;
    }

    @MainThread
    public final void zzc(Intent intent) {
        if (intent == null) {
            zzc().zzf().zza("onRebind called with null intent");
            return;
        }
        zzc().zzx().zza("onRebind called. action", intent.getAction());
    }

    private final zzes zzc() {
        return zzfw.zza(this.zza, (zzx) null, (Long) null).zzr();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzes zzes, JobParameters jobParameters) {
        zzes.zzx().zza("AppMeasurementJobService processed last upload request.");
        ((zzjn) this.zza).zza(jobParameters, false);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, zzes zzes, Intent intent) {
        if (((zzjn) this.zza).zza(i)) {
            zzes.zzx().zza("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            zzc().zzx().zza("Completed wakeful intent.");
            ((zzjn) this.zza).zza(intent);
        }
    }
}
