package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.collection.ArrayMap;
import com.android.vending.expansion.zipfile.APEZProvider;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzn;
import com.google.android.gms.internal.measurement.zzp;
import com.google.android.gms.internal.measurement.zzq;
import com.google.android.gms.internal.measurement.zzv;
import com.google.android.gms.internal.measurement.zzx;
import java.util.Map;

@DynamiteApi
/* compiled from: com.google.android.gms:play-services-measurement-sdk@@17.4.0 */
public class AppMeasurementDynamiteService extends zzn {
    @VisibleForTesting
    zzfw zza = null;
    private Map<Integer, zzgz> zzb = new ArrayMap();

    /* compiled from: com.google.android.gms:play-services-measurement-sdk@@17.4.0 */
    class zza implements zzgz {
        private zzq zza;

        zza(zzq zzq) {
            this.zza = zzq;
        }

        public final void onEvent(String str, String str2, Bundle bundle, long j) {
            try {
                this.zza.zza(str, str2, bundle, j);
            } catch (RemoteException e) {
                AppMeasurementDynamiteService.this.zza.zzr().zzi().zza("Event listener threw exception", e);
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk@@17.4.0 */
    class zzb implements zzgw {
        private zzq zza;

        zzb(zzq zzq) {
            this.zza = zzq;
        }

        public final void interceptEvent(String str, String str2, Bundle bundle, long j) {
            try {
                this.zza.zza(str, str2, bundle, j);
            } catch (RemoteException e) {
                AppMeasurementDynamiteService.this.zza.zzr().zzi().zza("Event interceptor threw exception", e);
            }
        }
    }

    private final void zza() {
        if (this.zza == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    public void initialize(IObjectWrapper iObjectWrapper, zzx zzx, long j) throws RemoteException {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        if (this.zza == null) {
            this.zza = zzfw.zza(context, zzx, Long.valueOf(j));
        } else {
            this.zza.zzr().zzi().zza("Attempting to initialize multiple times");
        }
    }

    public void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) throws RemoteException {
        zza();
        this.zza.zzh().zza(str, str2, bundle, z, z2, j);
    }

    public void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j) throws RemoteException {
        zza();
        this.zza.zzh().zza(str, str2, ObjectWrapper.unwrap(iObjectWrapper), z, j);
    }

    public void setUserId(String str, long j) throws RemoteException {
        zza();
        this.zza.zzh().zza((String) null, APEZProvider.FILEID, str, true, j);
    }

    public void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j) throws RemoteException {
        zza();
        this.zza.zzv().zza((Activity) ObjectWrapper.unwrap(iObjectWrapper), str, str2);
    }

    public void setMeasurementEnabled(boolean z, long j) throws RemoteException {
        zza();
        this.zza.zzh().zza(z);
    }

    public void resetAnalyticsData(long j) throws RemoteException {
        zza();
        this.zza.zzh().zzd(j);
    }

    public void setMinimumSessionDuration(long j) throws RemoteException {
        zza();
        this.zza.zzh().zza(j);
    }

    public void setSessionTimeoutDuration(long j) throws RemoteException {
        zza();
        this.zza.zzh().zzb(j);
    }

    public void getMaxUserProperties(String str, zzp zzp) throws RemoteException {
        zza();
        this.zza.zzh();
        Preconditions.checkNotEmpty(str);
        this.zza.zzi().zza(zzp, 25);
    }

    public void getCurrentScreenName(zzp zzp) throws RemoteException {
        zza();
        zza(zzp, this.zza.zzh().zzaj());
    }

    public void getCurrentScreenClass(zzp zzp) throws RemoteException {
        zza();
        zza(zzp, this.zza.zzh().zzak());
    }

    public void getCachedAppInstanceId(zzp zzp) throws RemoteException {
        zza();
        zza(zzp, this.zza.zzh().zzah());
    }

    public void getAppInstanceId(zzp zzp) throws RemoteException {
        zza();
        this.zza.zzq().zza((Runnable) new zzh(this, zzp));
    }

    public void getGmpAppId(zzp zzp) throws RemoteException {
        zza();
        zza(zzp, this.zza.zzh().zzal());
    }

    public void generateEventId(zzp zzp) throws RemoteException {
        zza();
        this.zza.zzi().zza(zzp, this.zza.zzi().zzg());
    }

    public void beginAdUnitExposure(String str, long j) throws RemoteException {
        zza();
        this.zza.zzz().zza(str, j);
    }

    public void endAdUnitExposure(String str, long j) throws RemoteException {
        zza();
        this.zza.zzz().zzb(str, j);
    }

    public void initForTests(Map map) throws RemoteException {
        zza();
    }

    public void logEventAndBundle(String str, String str2, Bundle bundle, zzp zzp, long j) throws RemoteException {
        zza();
        Preconditions.checkNotEmpty(str2);
        (bundle != null ? new Bundle(bundle) : new Bundle()).putString("_o", "app");
        this.zza.zzq().zza((Runnable) new zzj(this, zzp, new zzao(str2, new zzan(bundle), "app", j), str));
    }

    public void onActivityStarted(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        zzhw zzhw = this.zza.zzh().zza;
        if (zzhw != null) {
            this.zza.zzh().zzab();
            zzhw.onActivityStarted((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityStopped(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        zzhw zzhw = this.zza.zzh().zza;
        if (zzhw != null) {
            this.zza.zzh().zzab();
            zzhw.onActivityStopped((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j) throws RemoteException {
        zza();
        zzhw zzhw = this.zza.zzh().zza;
        if (zzhw != null) {
            this.zza.zzh().zzab();
            zzhw.onActivityCreated((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
    }

    public void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        zzhw zzhw = this.zza.zzh().zza;
        if (zzhw != null) {
            this.zza.zzh().zzab();
            zzhw.onActivityDestroyed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityPaused(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        zzhw zzhw = this.zza.zzh().zza;
        if (zzhw != null) {
            this.zza.zzh().zzab();
            zzhw.onActivityPaused((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityResumed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        zzhw zzhw = this.zza.zzh().zza;
        if (zzhw != null) {
            this.zza.zzh().zzab();
            zzhw.onActivityResumed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzp zzp, long j) throws RemoteException {
        zza();
        zzhw zzhw = this.zza.zzh().zza;
        Bundle bundle = new Bundle();
        if (zzhw != null) {
            this.zza.zzh().zzab();
            zzhw.onActivitySaveInstanceState((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
        try {
            zzp.zza(bundle);
        } catch (RemoteException e) {
            this.zza.zzr().zzi().zza("Error returning bundle value to wrapper", e);
        }
    }

    public void performAction(Bundle bundle, zzp zzp, long j) throws RemoteException {
        zza();
        zzp.zza((Bundle) null);
    }

    public void getUserProperties(String str, String str2, boolean z, zzp zzp) throws RemoteException {
        zza();
        this.zza.zzq().zza((Runnable) new zzi(this, zzp, str, str2, z));
    }

    public void logHealthData(int i, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Object obj = null;
        zza();
        Object unwrap = iObjectWrapper == null ? null : ObjectWrapper.unwrap(iObjectWrapper);
        Object unwrap2 = iObjectWrapper2 == null ? null : ObjectWrapper.unwrap(iObjectWrapper2);
        if (iObjectWrapper3 != null) {
            obj = ObjectWrapper.unwrap(iObjectWrapper3);
        }
        this.zza.zzr().zza(i, true, false, str, unwrap, unwrap2, obj);
    }

    public void setEventInterceptor(zzq zzq) throws RemoteException {
        zza();
        zzhb zzh = this.zza.zzh();
        zzb zzb2 = new zzb(zzq);
        zzh.zzb();
        zzh.zzw();
        zzh.zzq().zza((Runnable) new zzhl(zzh, zzb2));
    }

    public void registerOnMeasurementEventListener(zzq zzq) throws RemoteException {
        zza();
        zzgz zzgz = this.zzb.get(Integer.valueOf(zzq.zza()));
        if (zzgz == null) {
            zzgz = new zza(zzq);
            this.zzb.put(Integer.valueOf(zzq.zza()), zzgz);
        }
        this.zza.zzh().zza(zzgz);
    }

    public void unregisterOnMeasurementEventListener(zzq zzq) throws RemoteException {
        zza();
        zzgz remove = this.zzb.remove(Integer.valueOf(zzq.zza()));
        if (remove == null) {
            remove = new zza(zzq);
        }
        this.zza.zzh().zzb(remove);
    }

    public void setInstanceIdProvider(zzv zzv) throws RemoteException {
        zza();
    }

    public void setConditionalUserProperty(Bundle bundle, long j) throws RemoteException {
        zza();
        if (bundle == null) {
            this.zza.zzr().zzf().zza("Conditional user property must not be null");
        } else {
            this.zza.zzh().zza(bundle, j);
        }
    }

    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        zza();
        this.zza.zzh().zzc(str, str2, bundle);
    }

    public void getConditionalUserProperties(String str, String str2, zzp zzp) throws RemoteException {
        zza();
        this.zza.zzq().zza((Runnable) new zzl(this, zzp, str, str2));
    }

    public void getTestFlag(zzp zzp, int i) throws RemoteException {
        zza();
        switch (i) {
            case 0:
                this.zza.zzi().zza(zzp, this.zza.zzh().zzad());
                return;
            case 1:
                this.zza.zzi().zza(zzp, this.zza.zzh().zzae().longValue());
                return;
            case 2:
                zzkm zzi = this.zza.zzi();
                double doubleValue = this.zza.zzh().zzag().doubleValue();
                Bundle bundle = new Bundle();
                bundle.putDouble("r", doubleValue);
                try {
                    zzp.zza(bundle);
                    return;
                } catch (RemoteException e) {
                    zzi.zzz.zzr().zzi().zza("Error returning double value to wrapper", e);
                    return;
                }
            case 3:
                this.zza.zzi().zza(zzp, this.zza.zzh().zzaf().intValue());
                return;
            case 4:
                this.zza.zzi().zza(zzp, this.zza.zzh().zzac().booleanValue());
                return;
            default:
                return;
        }
    }

    private final void zza(zzp zzp, String str) {
        this.zza.zzi().zza(zzp, str);
    }

    public void setDataCollectionEnabled(boolean z) throws RemoteException {
        zza();
        this.zza.zzh().zzb(z);
    }

    public void isDataCollectionEnabled(zzp zzp) throws RemoteException {
        zza();
        this.zza.zzq().zza((Runnable) new zzk(this, zzp));
    }

    public void setDefaultEventParameters(Bundle bundle) {
        zza();
        zzhb zzh = this.zza.zzh();
        zzh.zzq().zza((Runnable) new zzha(zzh, bundle == null ? null : new Bundle(bundle)));
    }
}
