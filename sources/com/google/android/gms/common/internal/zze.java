package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.common.zzi;
import java.util.HashMap;
import javax.annotation.concurrent.GuardedBy;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
final class zze extends GmsClientSupervisor implements Handler.Callback {
    /* access modifiers changed from: private */
    public final Handler mHandler;
    /* access modifiers changed from: private */
    @GuardedBy("mConnectionStatus")
    public final HashMap<GmsClientSupervisor.zza, zzg> zzee = new HashMap<>();
    /* access modifiers changed from: private */
    public final Context zzef;
    /* access modifiers changed from: private */
    public final ConnectionTracker zzeg;
    private final long zzeh;
    /* access modifiers changed from: private */
    public final long zzei;

    zze(Context context) {
        this.zzef = context.getApplicationContext();
        this.mHandler = new zzi(context.getMainLooper(), this);
        this.zzeg = ConnectionTracker.getInstance();
        this.zzeh = 5000;
        this.zzei = 300000;
    }

    /* access modifiers changed from: protected */
    public final boolean zza(GmsClientSupervisor.zza zza, ServiceConnection serviceConnection, String str) {
        boolean isBound;
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzee) {
            zzg zzg = this.zzee.get(zza);
            if (zzg != null) {
                this.mHandler.removeMessages(0, zza);
                if (!zzg.zza(serviceConnection)) {
                    zzg.zza(serviceConnection, serviceConnection, str);
                    switch (zzg.getState()) {
                        case 1:
                            serviceConnection.onServiceConnected(zzg.getComponentName(), zzg.getBinder());
                            break;
                        case 2:
                            zzg.zzf(str);
                            break;
                    }
                } else {
                    String valueOf = String.valueOf(zza);
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 81).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(valueOf).toString());
                }
            } else {
                zzg = new zzg(this, zza);
                zzg.zza(serviceConnection, serviceConnection, str);
                zzg.zzf(str);
                this.zzee.put(zza, zzg);
            }
            isBound = zzg.isBound();
        }
        return isBound;
    }

    /* access modifiers changed from: protected */
    public final void zzb(GmsClientSupervisor.zza zza, ServiceConnection serviceConnection, String str) {
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzee) {
            zzg zzg = this.zzee.get(zza);
            if (zzg == null) {
                String valueOf = String.valueOf(zza);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 50).append("Nonexistent connection status for service config: ").append(valueOf).toString());
            } else if (!zzg.zza(serviceConnection)) {
                String valueOf2 = String.valueOf(zza);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf2).length() + 76).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(valueOf2).toString());
            } else {
                zzg.zza(serviceConnection, str);
                if (zzg.zzs()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, zza), this.zzeh);
                }
            }
        }
    }

    public final boolean handleMessage(Message message) {
        ComponentName componentName;
        switch (message.what) {
            case 0:
                synchronized (this.zzee) {
                    GmsClientSupervisor.zza zza = (GmsClientSupervisor.zza) message.obj;
                    zzg zzg = this.zzee.get(zza);
                    if (zzg != null && zzg.zzs()) {
                        if (zzg.isBound()) {
                            zzg.zzg("GmsClientSupervisor");
                        }
                        this.zzee.remove(zza);
                    }
                }
                return true;
            case 1:
                synchronized (this.zzee) {
                    GmsClientSupervisor.zza zza2 = (GmsClientSupervisor.zza) message.obj;
                    zzg zzg2 = this.zzee.get(zza2);
                    if (zzg2 != null && zzg2.getState() == 3) {
                        String valueOf = String.valueOf(zza2);
                        Log.e("GmsClientSupervisor", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Timeout waiting for ServiceConnection callback ").append(valueOf).toString(), new Exception());
                        ComponentName componentName2 = zzg2.getComponentName();
                        if (componentName2 == null) {
                            componentName2 = zza2.getComponentName();
                        }
                        if (componentName2 == null) {
                            componentName = new ComponentName(zza2.getPackage(), "unknown");
                        } else {
                            componentName = componentName2;
                        }
                        zzg2.onServiceDisconnected(componentName);
                    }
                }
                return true;
            default:
                return false;
        }
    }
}
