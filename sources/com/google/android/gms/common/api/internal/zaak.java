package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.zak;
import com.google.android.gms.signin.zac;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public final class zaak implements zabb {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Api.AbstractClientBuilder<? extends zac, SignInOptions> zacf;
    /* access modifiers changed from: private */
    public final Lock zaer;
    private final Map<Api<?>, Boolean> zaew;
    /* access modifiers changed from: private */
    public final GoogleApiAvailabilityLight zaey;
    /* access modifiers changed from: private */
    public final ClientSettings zafa;
    private ConnectionResult zafi;
    /* access modifiers changed from: private */
    public final zabe zafv;
    private int zaga;
    private int zagb = 0;
    private int zagc;
    private final Bundle zagd = new Bundle();
    private final Set<Api.AnyClientKey> zage = new HashSet();
    /* access modifiers changed from: private */
    public zac zagf;
    private boolean zagg;
    /* access modifiers changed from: private */
    public boolean zagh;
    private boolean zagi;
    /* access modifiers changed from: private */
    public IAccountAccessor zagj;
    private boolean zagk;
    private boolean zagl;
    private ArrayList<Future<?>> zagm = new ArrayList<>();

    public zaak(zabe zabe, ClientSettings clientSettings, Map<Api<?>, Boolean> map, GoogleApiAvailabilityLight googleApiAvailabilityLight, Api.AbstractClientBuilder<? extends zac, SignInOptions> abstractClientBuilder, Lock lock, Context context) {
        this.zafv = zabe;
        this.zafa = clientSettings;
        this.zaew = map;
        this.zaey = googleApiAvailabilityLight;
        this.zacf = abstractClientBuilder;
        this.zaer = lock;
        this.mContext = context;
    }

    @GuardedBy("mLock")
    public final void begin() {
        this.zafv.zaht.clear();
        this.zagh = false;
        this.zafi = null;
        this.zagb = 0;
        this.zagg = true;
        this.zagi = false;
        this.zagk = false;
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (Api next : this.zaew.keySet()) {
            Api.Client client = this.zafv.zahd.get(next.getClientKey());
            boolean z2 = (next.zah().getPriority() == 1) | z;
            boolean booleanValue = this.zaew.get(next).booleanValue();
            if (client.requiresSignIn()) {
                this.zagh = true;
                if (booleanValue) {
                    this.zage.add(next.getClientKey());
                } else {
                    this.zagg = false;
                }
            }
            hashMap.put(client, new zaam(this, next, booleanValue));
            z = z2;
        }
        if (z) {
            this.zagh = false;
        }
        if (this.zagh) {
            this.zafa.setClientSessionId(Integer.valueOf(System.identityHashCode(this.zafv.zaeh)));
            zaar zaar = new zaar(this, (zaaj) null);
            this.zagf = (zac) this.zacf.buildClient(this.mContext, this.zafv.zaeh.getLooper(), this.zafa, this.zafa.getSignInOptions(), (GoogleApiClient.ConnectionCallbacks) zaar, (GoogleApiClient.OnConnectionFailedListener) zaar);
        }
        this.zagc = this.zafv.zahd.size();
        this.zagm.add(zabf.zaaz().submit(new zaal(this, hashMap)));
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final boolean zaam() {
        this.zagc--;
        if (this.zagc > 0) {
            return false;
        }
        if (this.zagc < 0) {
            Log.w("GACConnecting", this.zafv.zaeh.zaaw());
            Log.wtf("GACConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zae(new ConnectionResult(8, (PendingIntent) null));
            return false;
        } else if (this.zafi == null) {
            return true;
        } else {
            this.zafv.zahw = this.zaga;
            zae(this.zafi);
            return false;
        }
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaa(zak zak) {
        if (zac(0)) {
            ConnectionResult connectionResult = zak.getConnectionResult();
            if (connectionResult.isSuccess()) {
                ResolveAccountResponse zacv = zak.zacv();
                ConnectionResult connectionResult2 = zacv.getConnectionResult();
                if (!connectionResult2.isSuccess()) {
                    String valueOf = String.valueOf(connectionResult2);
                    Log.wtf("GACConnecting", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                    zae(connectionResult2);
                    return;
                }
                this.zagi = true;
                this.zagj = zacv.getAccountAccessor();
                this.zagk = zacv.getSaveDefaultAccount();
                this.zagl = zacv.isFromCrossClientAuth();
                zaan();
            } else if (zad(connectionResult)) {
                zaap();
                zaan();
            } else {
                zae(connectionResult);
            }
        }
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaan() {
        if (this.zagc == 0) {
            if (!this.zagh || this.zagi) {
                ArrayList arrayList = new ArrayList();
                this.zagb = 1;
                this.zagc = this.zafv.zahd.size();
                for (Api.AnyClientKey next : this.zafv.zahd.keySet()) {
                    if (!this.zafv.zaht.containsKey(next)) {
                        arrayList.add(this.zafv.zahd.get(next));
                    } else if (zaam()) {
                        zaao();
                    }
                }
                if (!arrayList.isEmpty()) {
                    this.zagm.add(zabf.zaaz().submit(new zaaq(this, arrayList)));
                }
            }
        }
    }

    @GuardedBy("mLock")
    public final void onConnected(Bundle bundle) {
        if (zac(1)) {
            if (bundle != null) {
                this.zagd.putAll(bundle);
            }
            if (zaam()) {
                zaao();
            }
        }
    }

    @GuardedBy("mLock")
    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (zac(1)) {
            zab(connectionResult, api, z);
            if (zaam()) {
                zaao();
            }
        }
    }

    @GuardedBy("mLock")
    private final void zaao() {
        this.zafv.zaay();
        zabf.zaaz().execute(new zaaj(this));
        if (this.zagf != null) {
            if (this.zagk) {
                this.zagf.zaa(this.zagj, this.zagl);
            }
            zab(false);
        }
        for (Api.AnyClientKey<?> anyClientKey : this.zafv.zaht.keySet()) {
            this.zafv.zahd.get(anyClientKey).disconnect();
        }
        this.zafv.zahx.zab(this.zagd.isEmpty() ? null : this.zagd);
    }

    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        this.zafv.zaeh.zafd.add(t);
        return t;
    }

    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    public final void connect() {
    }

    @GuardedBy("mLock")
    public final boolean disconnect() {
        zaaq();
        zab(true);
        this.zafv.zaf((ConnectionResult) null);
        return true;
    }

    @GuardedBy("mLock")
    public final void onConnectionSuspended(int i) {
        zae(new ConnectionResult(8, (PendingIntent) null));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        if (r2 != false) goto L_0x0015;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        if (r3 >= r5.zaga) goto L_0x003f;
     */
    @javax.annotation.concurrent.GuardedBy("mLock")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zab(com.google.android.gms.common.ConnectionResult r6, com.google.android.gms.common.api.Api<?> r7, boolean r8) {
        /*
            r5 = this;
            r1 = 0
            r0 = 1
            com.google.android.gms.common.api.Api$BaseClientBuilder r2 = r7.zah()
            int r3 = r2.getPriority()
            if (r8 == 0) goto L_0x0015
            boolean r2 = r6.hasResolution()
            if (r2 == 0) goto L_0x002f
            r2 = r0
        L_0x0013:
            if (r2 == 0) goto L_0x003f
        L_0x0015:
            com.google.android.gms.common.ConnectionResult r2 = r5.zafi
            if (r2 == 0) goto L_0x001d
            int r2 = r5.zaga
            if (r3 >= r2) goto L_0x003f
        L_0x001d:
            if (r0 == 0) goto L_0x0023
            r5.zafi = r6
            r5.zaga = r3
        L_0x0023:
            com.google.android.gms.common.api.internal.zabe r0 = r5.zafv
            java.util.Map<com.google.android.gms.common.api.Api$AnyClientKey<?>, com.google.android.gms.common.ConnectionResult> r0 = r0.zaht
            com.google.android.gms.common.api.Api$AnyClientKey r1 = r7.getClientKey()
            r0.put(r1, r6)
            return
        L_0x002f:
            com.google.android.gms.common.GoogleApiAvailabilityLight r2 = r5.zaey
            int r4 = r6.getErrorCode()
            android.content.Intent r2 = r2.getErrorResolutionIntent(r4)
            if (r2 == 0) goto L_0x003d
            r2 = r0
            goto L_0x0013
        L_0x003d:
            r2 = r1
            goto L_0x0013
        L_0x003f:
            r0 = r1
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zaak.zab(com.google.android.gms.common.ConnectionResult, com.google.android.gms.common.api.Api, boolean):void");
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaap() {
        this.zagh = false;
        this.zafv.zaeh.zahe = Collections.emptySet();
        for (Api.AnyClientKey next : this.zage) {
            if (!this.zafv.zaht.containsKey(next)) {
                this.zafv.zaht.put(next, new ConnectionResult(17, (PendingIntent) null));
            }
        }
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final boolean zad(ConnectionResult connectionResult) {
        return this.zagg && !connectionResult.hasResolution();
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zae(ConnectionResult connectionResult) {
        zaaq();
        zab(!connectionResult.hasResolution());
        this.zafv.zaf(connectionResult);
        this.zafv.zahx.zac(connectionResult);
    }

    @GuardedBy("mLock")
    private final void zab(boolean z) {
        if (this.zagf != null) {
            if (this.zagf.isConnected() && z) {
                this.zagf.zacu();
            }
            this.zagf.disconnect();
            if (this.zafa.isSignInClientDisconnectFixEnabled()) {
                this.zagf = null;
            }
            this.zagj = null;
        }
    }

    private final void zaaq() {
        ArrayList arrayList = this.zagm;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((Future) obj).cancel(true);
        }
        this.zagm.clear();
    }

    /* access modifiers changed from: private */
    public final Set<Scope> zaar() {
        if (this.zafa == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.zafa.getRequiredScopes());
        Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = this.zafa.getOptionalApiSettings();
        for (Api next : optionalApiSettings.keySet()) {
            if (!this.zafv.zaht.containsKey(next.getClientKey())) {
                hashSet.addAll(optionalApiSettings.get(next).mScopes);
            }
        }
        return hashSet;
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final boolean zac(int i) {
        if (this.zagb == i) {
            return true;
        }
        Log.w("GACConnecting", this.zafv.zaeh.zaaw());
        String valueOf = String.valueOf(this);
        Log.w("GACConnecting", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Unexpected callback in ").append(valueOf).toString());
        Log.w("GACConnecting", new StringBuilder(33).append("mRemainingConnections=").append(this.zagc).toString());
        String zad = zad(this.zagb);
        String zad2 = zad(i);
        Log.e("GACConnecting", new StringBuilder(String.valueOf(zad).length() + 70 + String.valueOf(zad2).length()).append("GoogleApiClient connecting is in step ").append(zad).append(" but received callback for step ").append(zad2).toString(), new Exception());
        zae(new ConnectionResult(8, (PendingIntent) null));
        return false;
    }

    private static String zad(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }
}
