package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zac;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public final class zav implements zabr {
    private final Looper zabl;
    private final GoogleApiManager zabo;
    /* access modifiers changed from: private */
    public final Lock zaer;
    /* access modifiers changed from: private */
    public final Map<Api.AnyClientKey<?>, zaw<?>> zaeu = new HashMap();
    /* access modifiers changed from: private */
    public final Map<Api.AnyClientKey<?>, zaw<?>> zaev = new HashMap();
    private final Map<Api<?>, Boolean> zaew;
    /* access modifiers changed from: private */
    public final zaaw zaex;
    private final GoogleApiAvailabilityLight zaey;
    /* access modifiers changed from: private */
    public final Condition zaez;
    private final ClientSettings zafa;
    private final boolean zafb;
    /* access modifiers changed from: private */
    public final boolean zafc;
    private final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zafd = new LinkedList();
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public boolean zafe;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public Map<ApiKey<?>, ConnectionResult> zaff;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public Map<ApiKey<?>, ConnectionResult> zafg;
    @GuardedBy("mLock")
    private zaaa zafh;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public ConnectionResult zafi;

    public zav(Context context, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, Api.AbstractClientBuilder<? extends zac, SignInOptions> abstractClientBuilder, ArrayList<zap> arrayList, zaaw zaaw, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        this.zaer = lock;
        this.zabl = looper;
        this.zaez = lock.newCondition();
        this.zaey = googleApiAvailabilityLight;
        this.zaex = zaaw;
        this.zaew = map2;
        this.zafa = clientSettings;
        this.zafb = z;
        HashMap hashMap = new HashMap();
        for (Api next : map2.keySet()) {
            hashMap.put(next.getClientKey(), next);
        }
        HashMap hashMap2 = new HashMap();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            zap zap = (zap) obj;
            hashMap2.put(zap.mApi, zap);
        }
        boolean z5 = false;
        boolean z6 = true;
        boolean z7 = false;
        for (Map.Entry next2 : map.entrySet()) {
            Api api = (Api) hashMap.get(next2.getKey());
            Api.Client client = (Api.Client) next2.getValue();
            if (client.requiresGooglePlayServices()) {
                z2 = true;
                if (!this.zaew.get(api).booleanValue()) {
                    z3 = z6;
                    z4 = true;
                } else {
                    z3 = z6;
                    z4 = z7;
                }
            } else {
                z2 = z5;
                z3 = false;
                z4 = z7;
            }
            zaw zaw = new zaw(context, api, looper, client, (zap) hashMap2.get(api), clientSettings, abstractClientBuilder);
            this.zaeu.put((Api.AnyClientKey) next2.getKey(), zaw);
            if (client.requiresSignIn()) {
                this.zaev.put((Api.AnyClientKey) next2.getKey(), zaw);
            }
            z5 = z2;
            z6 = z3;
            z7 = z4;
        }
        this.zafc = z5 && !z6 && !z7;
        this.zabo = GoogleApiManager.zaba();
    }

    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        if (this.zafb && zab(t)) {
            return t;
        }
        if (!isConnected()) {
            this.zafd.add(t);
            return t;
        }
        this.zaex.zahj.zac(t);
        return this.zaeu.get(t.getClientKey()).doRead(t);
    }

    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        Api.AnyClientKey clientKey = t.getClientKey();
        if (this.zafb && zab(t)) {
            return t;
        }
        this.zaex.zahj.zac(t);
        return this.zaeu.get(clientKey).doWrite(t);
    }

    private final <T extends BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>> boolean zab(@NonNull T t) {
        Api.AnyClientKey clientKey = t.getClientKey();
        ConnectionResult zaa = zaa((Api.AnyClientKey<?>) clientKey);
        if (zaa == null || zaa.getErrorCode() != 4) {
            return false;
        }
        t.setFailedResult(new Status(4, (String) null, this.zabo.zaa((ApiKey<?>) this.zaeu.get(clientKey).getApiKey(), System.identityHashCode(this.zaex))));
        return true;
    }

    public final void connect() {
        this.zaer.lock();
        try {
            if (!this.zafe) {
                this.zafe = true;
                this.zaff = null;
                this.zafg = null;
                this.zafh = null;
                this.zafi = null;
                this.zabo.zam();
                this.zabo.zaa((Iterable<? extends HasApiKey<?>>) this.zaeu.values()).addOnCompleteListener((Executor) new HandlerExecutor(this.zabl), new zax(this));
                this.zaer.unlock();
            }
        } finally {
            this.zaer.unlock();
        }
    }

    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zaez.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, (PendingIntent) null);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        if (this.zafi != null) {
            return this.zafi;
        }
        return new ConnectionResult(13, (PendingIntent) null);
    }

    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, (PendingIntent) null);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, (PendingIntent) null);
                }
            } else {
                nanos = this.zaez.awaitNanos(nanos);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        if (this.zafi != null) {
            return this.zafi;
        }
        return new ConnectionResult(13, (PendingIntent) null);
    }

    public final void disconnect() {
        this.zaer.lock();
        try {
            this.zafe = false;
            this.zaff = null;
            this.zafg = null;
            if (this.zafh != null) {
                this.zafh.cancel();
                this.zafh = null;
            }
            this.zafi = null;
            while (!this.zafd.isEmpty()) {
                BaseImplementation.ApiMethodImpl remove = this.zafd.remove();
                remove.zaa((zacq) null);
                remove.cancel();
            }
            this.zaez.signalAll();
        } finally {
            this.zaer.unlock();
        }
    }

    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return zaa(api.getClientKey());
    }

    @Nullable
    private final ConnectionResult zaa(@NonNull Api.AnyClientKey<?> anyClientKey) {
        this.zaer.lock();
        try {
            zaw zaw = this.zaeu.get(anyClientKey);
            if (this.zaff != null && zaw != null) {
                return this.zaff.get(zaw.getApiKey());
            }
            this.zaer.unlock();
            return null;
        } finally {
            this.zaer.unlock();
        }
    }

    public final boolean isConnected() {
        this.zaer.lock();
        try {
            return this.zaff != null && this.zafi == null;
        } finally {
            this.zaer.unlock();
        }
    }

    public final boolean isConnecting() {
        this.zaer.lock();
        try {
            return this.zaff == null && this.zafe;
        } finally {
            this.zaer.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0025 A[Catch:{ all -> 0x0045 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zaz() {
        /*
            r3 = this;
            r1 = 0
            java.util.concurrent.locks.Lock r0 = r3.zaer
            r0.lock()
            boolean r0 = r3.zafe     // Catch:{ all -> 0x0045 }
            if (r0 == 0) goto L_0x000e
            boolean r0 = r3.zafb     // Catch:{ all -> 0x0045 }
            if (r0 != 0) goto L_0x0015
        L_0x000e:
            java.util.concurrent.locks.Lock r0 = r3.zaer
            r0.unlock()
            r0 = r1
        L_0x0014:
            return r0
        L_0x0015:
            java.util.Map<com.google.android.gms.common.api.Api$AnyClientKey<?>, com.google.android.gms.common.api.internal.zaw<?>> r0 = r3.zaev     // Catch:{ all -> 0x0045 }
            java.util.Set r0 = r0.keySet()     // Catch:{ all -> 0x0045 }
            java.util.Iterator r2 = r0.iterator()     // Catch:{ all -> 0x0045 }
        L_0x001f:
            boolean r0 = r2.hasNext()     // Catch:{ all -> 0x0045 }
            if (r0 == 0) goto L_0x003e
            java.lang.Object r0 = r2.next()     // Catch:{ all -> 0x0045 }
            com.google.android.gms.common.api.Api$AnyClientKey r0 = (com.google.android.gms.common.api.Api.AnyClientKey) r0     // Catch:{ all -> 0x0045 }
            com.google.android.gms.common.ConnectionResult r0 = r3.zaa((com.google.android.gms.common.api.Api.AnyClientKey<?>) r0)     // Catch:{ all -> 0x0045 }
            if (r0 == 0) goto L_0x0037
            boolean r0 = r0.isSuccess()     // Catch:{ all -> 0x0045 }
            if (r0 != 0) goto L_0x001f
        L_0x0037:
            java.util.concurrent.locks.Lock r0 = r3.zaer
            r0.unlock()
            r0 = r1
            goto L_0x0014
        L_0x003e:
            java.util.concurrent.locks.Lock r0 = r3.zaer
            r0.unlock()
            r0 = 1
            goto L_0x0014
        L_0x0045:
            r0 = move-exception
            java.util.concurrent.locks.Lock r1 = r3.zaer
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zav.zaz():boolean");
    }

    /* JADX INFO: finally extract failed */
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        this.zaer.lock();
        try {
            if (!this.zafe || zaz()) {
                this.zaer.unlock();
                return false;
            }
            this.zabo.zam();
            this.zafh = new zaaa(this, signInConnectionListener);
            this.zabo.zaa((Iterable<? extends HasApiKey<?>>) this.zaev.values()).addOnCompleteListener((Executor) new HandlerExecutor(this.zabl), this.zafh);
            this.zaer.unlock();
            return true;
        } catch (Throwable th) {
            this.zaer.unlock();
            throw th;
        }
    }

    public final void maybeSignOut() {
        this.zaer.lock();
        try {
            this.zabo.maybeSignOut();
            if (this.zafh != null) {
                this.zafh.cancel();
                this.zafh = null;
            }
            if (this.zafg == null) {
                this.zafg = new ArrayMap(this.zaev.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            for (zaw<?> apiKey : this.zaev.values()) {
                this.zafg.put(apiKey.getApiKey(), connectionResult);
            }
            if (this.zaff != null) {
                this.zaff.putAll(this.zafg);
            }
        } finally {
            this.zaer.unlock();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public final void zau() {
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaaa() {
        if (this.zafa == null) {
            this.zaex.zahe = Collections.emptySet();
            return;
        }
        HashSet hashSet = new HashSet(this.zafa.getRequiredScopes());
        Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = this.zafa.getOptionalApiSettings();
        for (Api next : optionalApiSettings.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(next);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(optionalApiSettings.get(next).mScopes);
            }
        }
        this.zaex.zahe = hashSet;
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaab() {
        while (!this.zafd.isEmpty()) {
            execute(this.zafd.remove());
        }
        this.zaex.zab((Bundle) null);
    }

    /* access modifiers changed from: private */
    public final boolean zaa(zaw<?> zaw, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && this.zaew.get(zaw.getApi()).booleanValue() && zaw.zaad().requiresGooglePlayServices() && this.zaey.isUserResolvableError(connectionResult.getErrorCode());
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    @Nullable
    public final ConnectionResult zaac() {
        int i = 0;
        ConnectionResult connectionResult = null;
        int i2 = 0;
        ConnectionResult connectionResult2 = null;
        for (zaw next : this.zaeu.values()) {
            Api api = next.getApi();
            ConnectionResult connectionResult3 = this.zaff.get(next.getApiKey());
            if (!connectionResult3.isSuccess() && (!this.zaew.get(api).booleanValue() || connectionResult3.hasResolution() || this.zaey.isUserResolvableError(connectionResult3.getErrorCode()))) {
                if (connectionResult3.getErrorCode() != 4 || !this.zafb) {
                    int priority = api.zah().getPriority();
                    if (connectionResult2 != null && i2 <= priority) {
                        priority = i2;
                        connectionResult3 = connectionResult2;
                    }
                    i2 = priority;
                    connectionResult2 = connectionResult3;
                } else {
                    int priority2 = api.zah().getPriority();
                    if (connectionResult == null || i > priority2) {
                        i = priority2;
                        connectionResult = connectionResult3;
                    }
                }
            }
        }
        return (connectionResult2 == null || connectionResult == null || i2 <= i) ? connectionResult2 : connectionResult;
    }
}
