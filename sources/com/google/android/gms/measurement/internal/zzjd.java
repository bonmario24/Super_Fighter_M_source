package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzjd implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    final /* synthetic */ zzil zza;
    /* access modifiers changed from: private */
    public volatile boolean zzb;
    private volatile zzet zzc;

    protected zzjd(zzil zzil) {
        this.zza = zzil;
    }

    @WorkerThread
    public final void zza(Intent intent) {
        this.zza.zzd();
        Context zzn = this.zza.zzn();
        ConnectionTracker instance = ConnectionTracker.getInstance();
        synchronized (this) {
            if (this.zzb) {
                this.zza.zzr().zzx().zza("Connection attempt already in progress");
                return;
            }
            this.zza.zzr().zzx().zza("Using local app measurement service");
            this.zzb = true;
            instance.bindService(zzn, intent, this.zza.zza, 129);
        }
    }

    @WorkerThread
    public final void zza() {
        if (this.zzc != null && (this.zzc.isConnected() || this.zzc.isConnecting())) {
            this.zzc.disconnect();
        }
        this.zzc = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008e A[SYNTHETIC, Splitter:B:39:0x008e] */
    @androidx.annotation.MainThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected(android.content.ComponentName r5, android.os.IBinder r6) {
        /*
            r4 = this;
            r1 = 0
            java.lang.String r0 = "MeasurementServiceConnection.onServiceConnected"
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r0)
            monitor-enter(r4)
            if (r6 != 0) goto L_0x001d
            r0 = 0
            r4.zzb = r0     // Catch:{ all -> 0x0055 }
            com.google.android.gms.measurement.internal.zzil r0 = r4.zza     // Catch:{ all -> 0x0055 }
            com.google.android.gms.measurement.internal.zzes r0 = r0.zzr()     // Catch:{ all -> 0x0055 }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzf()     // Catch:{ all -> 0x0055 }
            java.lang.String r1 = "Service connected with null binder"
            r0.zza(r1)     // Catch:{ all -> 0x0055 }
            monitor-exit(r4)     // Catch:{ all -> 0x0055 }
        L_0x001c:
            return
        L_0x001d:
            java.lang.String r0 = r6.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x006b }
            java.lang.String r2 = "com.google.android.gms.measurement.internal.IMeasurementService"
            boolean r2 = r2.equals(r0)     // Catch:{ RemoteException -> 0x006b }
            if (r2 == 0) goto L_0x007d
            if (r6 != 0) goto L_0x0058
            r0 = r1
        L_0x002c:
            com.google.android.gms.measurement.internal.zzil r1 = r4.zza     // Catch:{ RemoteException -> 0x009f }
            com.google.android.gms.measurement.internal.zzes r1 = r1.zzr()     // Catch:{ RemoteException -> 0x009f }
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzx()     // Catch:{ RemoteException -> 0x009f }
            java.lang.String r2 = "Bound to IMeasurementService interface"
            r1.zza(r2)     // Catch:{ RemoteException -> 0x009f }
        L_0x003b:
            if (r0 != 0) goto L_0x008e
            r0 = 0
            r4.zzb = r0     // Catch:{ all -> 0x0055 }
            com.google.android.gms.common.stats.ConnectionTracker r0 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ IllegalArgumentException -> 0x009d }
            com.google.android.gms.measurement.internal.zzil r1 = r4.zza     // Catch:{ IllegalArgumentException -> 0x009d }
            android.content.Context r1 = r1.zzn()     // Catch:{ IllegalArgumentException -> 0x009d }
            com.google.android.gms.measurement.internal.zzil r2 = r4.zza     // Catch:{ IllegalArgumentException -> 0x009d }
            com.google.android.gms.measurement.internal.zzjd r2 = r2.zza     // Catch:{ IllegalArgumentException -> 0x009d }
            r0.unbindService(r1, r2)     // Catch:{ IllegalArgumentException -> 0x009d }
        L_0x0053:
            monitor-exit(r4)     // Catch:{ all -> 0x0055 }
            goto L_0x001c
        L_0x0055:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0055 }
            throw r0
        L_0x0058:
            java.lang.String r0 = "com.google.android.gms.measurement.internal.IMeasurementService"
            android.os.IInterface r0 = r6.queryLocalInterface(r0)     // Catch:{ RemoteException -> 0x006b }
            boolean r2 = r0 instanceof com.google.android.gms.measurement.internal.zzek     // Catch:{ RemoteException -> 0x006b }
            if (r2 == 0) goto L_0x0065
            com.google.android.gms.measurement.internal.zzek r0 = (com.google.android.gms.measurement.internal.zzek) r0     // Catch:{ RemoteException -> 0x006b }
            goto L_0x002c
        L_0x0065:
            com.google.android.gms.measurement.internal.zzem r0 = new com.google.android.gms.measurement.internal.zzem     // Catch:{ RemoteException -> 0x006b }
            r0.<init>(r6)     // Catch:{ RemoteException -> 0x006b }
            goto L_0x002c
        L_0x006b:
            r0 = move-exception
            r0 = r1
        L_0x006d:
            com.google.android.gms.measurement.internal.zzil r1 = r4.zza     // Catch:{ all -> 0x0055 }
            com.google.android.gms.measurement.internal.zzes r1 = r1.zzr()     // Catch:{ all -> 0x0055 }
            com.google.android.gms.measurement.internal.zzeu r1 = r1.zzf()     // Catch:{ all -> 0x0055 }
            java.lang.String r2 = "Service connect failed to get IMeasurementService"
            r1.zza(r2)     // Catch:{ all -> 0x0055 }
            goto L_0x003b
        L_0x007d:
            com.google.android.gms.measurement.internal.zzil r2 = r4.zza     // Catch:{ RemoteException -> 0x006b }
            com.google.android.gms.measurement.internal.zzes r2 = r2.zzr()     // Catch:{ RemoteException -> 0x006b }
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzf()     // Catch:{ RemoteException -> 0x006b }
            java.lang.String r3 = "Got binder with a wrong descriptor"
            r2.zza(r3, r0)     // Catch:{ RemoteException -> 0x006b }
            r0 = r1
            goto L_0x003b
        L_0x008e:
            com.google.android.gms.measurement.internal.zzil r1 = r4.zza     // Catch:{ all -> 0x0055 }
            com.google.android.gms.measurement.internal.zzft r1 = r1.zzq()     // Catch:{ all -> 0x0055 }
            com.google.android.gms.measurement.internal.zzjc r2 = new com.google.android.gms.measurement.internal.zzjc     // Catch:{ all -> 0x0055 }
            r2.<init>(r4, r0)     // Catch:{ all -> 0x0055 }
            r1.zza((java.lang.Runnable) r2)     // Catch:{ all -> 0x0055 }
            goto L_0x0053
        L_0x009d:
            r0 = move-exception
            goto L_0x0053
        L_0x009f:
            r1 = move-exception
            goto L_0x006d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjd.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.zza.zzr().zzw().zza("Service disconnected");
        this.zza.zzq().zza((Runnable) new zzjf(this, componentName));
    }

    @WorkerThread
    public final void zzb() {
        this.zza.zzd();
        Context zzn = this.zza.zzn();
        synchronized (this) {
            if (this.zzb) {
                this.zza.zzr().zzx().zza("Connection attempt already in progress");
            } else if (this.zzc == null || (!this.zzc.isConnecting() && !this.zzc.isConnected())) {
                this.zzc = new zzet(zzn, Looper.getMainLooper(), this, this);
                this.zza.zzr().zzx().zza("Connecting to remote service");
                this.zzb = true;
                this.zzc.checkAvailabilityAndConnect();
            } else {
                this.zza.zzr().zzx().zza("Already awaiting connection attempt");
            }
        }
    }

    @MainThread
    public final void onConnected(@Nullable Bundle bundle) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                this.zza.zzq().zza((Runnable) new zzje(this, (zzek) this.zzc.getService()));
            } catch (DeadObjectException | IllegalStateException e) {
                this.zzc = null;
                this.zzb = false;
            }
        }
    }

    @MainThread
    public final void onConnectionSuspended(int i) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.zza.zzr().zzw().zza("Service connection suspended");
        this.zza.zzq().zza((Runnable) new zzjh(this));
    }

    @MainThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        zzes zzd = this.zza.zzz.zzd();
        if (zzd != null) {
            zzd.zzi().zza("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzb = false;
            this.zzc = null;
        }
        this.zza.zzq().zza((Runnable) new zzjg(this));
    }
}
