package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.BinderThread;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.IGmsCallbacks;
import com.google.android.gms.common.internal.IGmsServiceBroker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.common.zzi;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public abstract class BaseGmsClient<T extends IInterface> {
    @KeepForSdk
    public static final int CONNECT_STATE_CONNECTED = 4;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    @KeepForSdk
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = {"service_esmobile", "service_googleme"};
    @KeepForSdk
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private static final Feature[] zzch = new Feature[0];
    private final Context mContext;
    final Handler mHandler;
    private final Object mLock;
    private int zzci;
    private long zzcj;
    private long zzck;
    private int zzcl;
    private long zzcm;
    @VisibleForTesting
    private zzh zzcn;
    private final Looper zzco;
    private final GmsClientSupervisor zzcp;
    private final GoogleApiAvailabilityLight zzcq;
    /* access modifiers changed from: private */
    public final Object zzcr;
    /* access modifiers changed from: private */
    @GuardedBy("mServiceBrokerLock")
    public IGmsServiceBroker zzcs;
    @VisibleForTesting
    protected ConnectionProgressReportCallbacks zzct;
    @GuardedBy("mLock")
    private T zzcu;
    /* access modifiers changed from: private */
    public final ArrayList<zzb<?>> zzcv;
    @GuardedBy("mLock")
    private zze zzcw;
    @GuardedBy("mLock")
    private int zzcx;
    /* access modifiers changed from: private */
    public final BaseConnectionCallbacks zzcy;
    /* access modifiers changed from: private */
    public final BaseOnConnectionFailedListener zzcz;
    private final int zzda;
    private final String zzdb;
    /* access modifiers changed from: private */
    public ConnectionResult zzdc;
    /* access modifiers changed from: private */
    public boolean zzdd;
    private volatile zza zzde;
    @VisibleForTesting
    protected AtomicInteger zzdf;

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public interface BaseConnectionCallbacks {
        @KeepForSdk
        void onConnected(@Nullable Bundle bundle);

        @KeepForSdk
        void onConnectionSuspended(int i);
    }

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public interface ConnectionProgressReportCallbacks {
        @KeepForSdk
        void onReportServiceBinding(@NonNull ConnectionResult connectionResult);
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        @KeepForSdk
        public LegacyClientCallbackAdapter() {
        }

        public void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                BaseGmsClient.this.getRemoteService((IAccountAccessor) null, BaseGmsClient.this.getScopes());
            } else if (BaseGmsClient.this.zzcz != null) {
                BaseGmsClient.this.zzcz.onConnectionFailed(connectionResult);
            }
        }
    }

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public interface SignOutCallbacks {
        @KeepForSdk
        void onSignOutComplete();
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    @Nullable
    public abstract T createServiceInterface(IBinder iBinder);

    /* access modifiers changed from: protected */
    @NonNull
    @KeepForSdk
    public abstract String getServiceDescriptor();

    /* access modifiers changed from: protected */
    @NonNull
    @KeepForSdk
    public abstract String getStartServiceAction();

    @KeepForSdk
    protected BaseGmsClient(Context context, Looper looper, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailabilityLight.getInstance(), i, (BaseConnectionCallbacks) Preconditions.checkNotNull(baseConnectionCallbacks), (BaseOnConnectionFailedListener) Preconditions.checkNotNull(baseOnConnectionFailedListener), str);
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    final class zzc extends zzi {
        public zzc(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            ConnectionResult connectionResult;
            ConnectionResult connectionResult2;
            PendingIntent pendingIntent = null;
            if (BaseGmsClient.this.zzdf.get() != message.arg1) {
                if (zzb(message)) {
                    zza(message);
                }
            } else if ((message.what == 1 || message.what == 7 || ((message.what == 4 && !BaseGmsClient.this.enableLocalFallback()) || message.what == 5)) && !BaseGmsClient.this.isConnecting()) {
                zza(message);
            } else if (message.what == 4) {
                ConnectionResult unused = BaseGmsClient.this.zzdc = new ConnectionResult(message.arg2);
                if (!BaseGmsClient.this.zzn() || BaseGmsClient.this.zzdd) {
                    if (BaseGmsClient.this.zzdc != null) {
                        connectionResult2 = BaseGmsClient.this.zzdc;
                    } else {
                        connectionResult2 = new ConnectionResult(8);
                    }
                    BaseGmsClient.this.zzct.onReportServiceBinding(connectionResult2);
                    BaseGmsClient.this.onConnectionFailed(connectionResult2);
                    return;
                }
                BaseGmsClient.this.zza(3, null);
            } else if (message.what == 5) {
                if (BaseGmsClient.this.zzdc != null) {
                    connectionResult = BaseGmsClient.this.zzdc;
                } else {
                    connectionResult = new ConnectionResult(8);
                }
                BaseGmsClient.this.zzct.onReportServiceBinding(connectionResult);
                BaseGmsClient.this.onConnectionFailed(connectionResult);
            } else if (message.what == 3) {
                if (message.obj instanceof PendingIntent) {
                    pendingIntent = (PendingIntent) message.obj;
                }
                ConnectionResult connectionResult3 = new ConnectionResult(message.arg2, pendingIntent);
                BaseGmsClient.this.zzct.onReportServiceBinding(connectionResult3);
                BaseGmsClient.this.onConnectionFailed(connectionResult3);
            } else if (message.what == 6) {
                BaseGmsClient.this.zza(5, null);
                if (BaseGmsClient.this.zzcy != null) {
                    BaseGmsClient.this.zzcy.onConnectionSuspended(message.arg2);
                }
                BaseGmsClient.this.onConnectionSuspended(message.arg2);
                boolean unused2 = BaseGmsClient.this.zza(5, 1, null);
            } else if (message.what == 2 && !BaseGmsClient.this.isConnected()) {
                zza(message);
            } else if (zzb(message)) {
                ((zzb) message.obj).zzo();
            } else {
                Log.wtf("GmsClient", new StringBuilder(45).append("Don't know how to handle message: ").append(message.what).toString(), new Exception());
            }
        }

        private static void zza(Message message) {
            zzb zzb = (zzb) message.obj;
            zzb.zzk();
            zzb.unregister();
        }

        private static boolean zzb(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 7;
        }
    }

    @VisibleForTesting
    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public final class zze implements ServiceConnection {
        private final int zzdj;

        public zze(int i) {
            this.zzdj = i;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IGmsServiceBroker zza;
            if (iBinder == null) {
                BaseGmsClient.this.zzb(16);
                return;
            }
            synchronized (BaseGmsClient.this.zzcr) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                if (iBinder == null) {
                    zza = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IGmsServiceBroker)) {
                        zza = new IGmsServiceBroker.Stub.zza(iBinder);
                    } else {
                        zza = (IGmsServiceBroker) queryLocalInterface;
                    }
                }
                IGmsServiceBroker unused = baseGmsClient.zzcs = zza;
            }
            BaseGmsClient.this.zza(0, (Bundle) null, this.zzdj);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            synchronized (BaseGmsClient.this.zzcr) {
                IGmsServiceBroker unused = BaseGmsClient.this.zzcs = null;
            }
            BaseGmsClient.this.mHandler.sendMessage(BaseGmsClient.this.mHandler.obtainMessage(6, this.zzdj, 1));
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    protected final class zzf extends zza {
        @BinderThread
        public zzf(int i, @Nullable Bundle bundle) {
            super(i, (Bundle) null);
        }

        /* access modifiers changed from: protected */
        public final void zza(ConnectionResult connectionResult) {
            if (!BaseGmsClient.this.enableLocalFallback() || !BaseGmsClient.this.zzn()) {
                BaseGmsClient.this.zzct.onReportServiceBinding(connectionResult);
                BaseGmsClient.this.onConnectionFailed(connectionResult);
                return;
            }
            BaseGmsClient.this.zzb(16);
        }

        /* access modifiers changed from: protected */
        public final boolean zzj() {
            BaseGmsClient.this.zzct.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    protected abstract class zzb<TListener> {
        private TListener zzdg;
        private boolean zzdh = false;

        public zzb(TListener tlistener) {
            this.zzdg = tlistener;
        }

        /* access modifiers changed from: protected */
        public abstract void zza(TListener tlistener);

        /* access modifiers changed from: protected */
        public abstract void zzk();

        public final void zzo() {
            TListener tlistener;
            synchronized (this) {
                tlistener = this.zzdg;
                if (this.zzdh) {
                    String valueOf = String.valueOf(this);
                    Log.w("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Callback proxy ").append(valueOf).append(" being reused. This is not safe.").toString());
                }
            }
            if (tlistener != null) {
                try {
                    zza(tlistener);
                } catch (RuntimeException e) {
                    zzk();
                    throw e;
                }
            } else {
                zzk();
            }
            synchronized (this) {
                this.zzdh = true;
            }
            unregister();
        }

        public final void unregister() {
            removeListener();
            synchronized (BaseGmsClient.this.zzcv) {
                BaseGmsClient.this.zzcv.remove(this);
            }
        }

        public final void removeListener() {
            synchronized (this) {
                this.zzdg = null;
            }
        }
    }

    @VisibleForTesting
    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public static final class zzd extends IGmsCallbacks.zza {
        private BaseGmsClient zzdi;
        private final int zzdj;

        public zzd(@NonNull BaseGmsClient baseGmsClient, int i) {
            this.zzdi = baseGmsClient;
            this.zzdj = i;
        }

        @BinderThread
        public final void zza(int i, @Nullable Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        @BinderThread
        public final void onPostInitComplete(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
            Preconditions.checkNotNull(this.zzdi, "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzdi.onPostInitHandler(i, iBinder, bundle, this.zzdj);
            this.zzdi = null;
        }

        @BinderThread
        public final void zza(int i, @NonNull IBinder iBinder, @NonNull zza zza) {
            Preconditions.checkNotNull(this.zzdi, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Preconditions.checkNotNull(zza);
            this.zzdi.zza(zza);
            onPostInitComplete(i, iBinder, zza.zzdm);
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    protected final class zzg extends zza {
        private final IBinder zzdk;

        @BinderThread
        public zzg(int i, IBinder iBinder, Bundle bundle) {
            super(i, bundle);
            this.zzdk = iBinder;
        }

        /* access modifiers changed from: protected */
        public final void zza(ConnectionResult connectionResult) {
            if (BaseGmsClient.this.zzcz != null) {
                BaseGmsClient.this.zzcz.onConnectionFailed(connectionResult);
            }
            BaseGmsClient.this.onConnectionFailed(connectionResult);
        }

        /* access modifiers changed from: protected */
        public final boolean zzj() {
            try {
                String interfaceDescriptor = this.zzdk.getInterfaceDescriptor();
                if (!BaseGmsClient.this.getServiceDescriptor().equals(interfaceDescriptor)) {
                    String serviceDescriptor = BaseGmsClient.this.getServiceDescriptor();
                    Log.e("GmsClient", new StringBuilder(String.valueOf(serviceDescriptor).length() + 34 + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(serviceDescriptor).append(" vs. ").append(interfaceDescriptor).toString());
                    return false;
                }
                IInterface createServiceInterface = BaseGmsClient.this.createServiceInterface(this.zzdk);
                if (createServiceInterface == null) {
                    return false;
                }
                if (!BaseGmsClient.this.zza(2, 4, createServiceInterface) && !BaseGmsClient.this.zza(3, 4, createServiceInterface)) {
                    return false;
                }
                ConnectionResult unused = BaseGmsClient.this.zzdc = null;
                Bundle connectionHint = BaseGmsClient.this.getConnectionHint();
                if (BaseGmsClient.this.zzcy != null) {
                    BaseGmsClient.this.zzcy.onConnected(connectionHint);
                }
                return true;
            } catch (RemoteException e) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    private abstract class zza extends zzb<Boolean> {
        private final int statusCode;
        private final Bundle zzcf;

        @BinderThread
        protected zza(int i, Bundle bundle) {
            super(true);
            this.statusCode = i;
            this.zzcf = bundle;
        }

        /* access modifiers changed from: protected */
        public abstract void zza(ConnectionResult connectionResult);

        /* access modifiers changed from: protected */
        public abstract boolean zzj();

        /* access modifiers changed from: protected */
        public final void zzk() {
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void zza(Object obj) {
            PendingIntent pendingIntent = null;
            if (((Boolean) obj) == null) {
                BaseGmsClient.this.zza(1, null);
                return;
            }
            switch (this.statusCode) {
                case 0:
                    if (!zzj()) {
                        BaseGmsClient.this.zza(1, null);
                        zza(new ConnectionResult(8, (PendingIntent) null));
                        return;
                    }
                    return;
                case 10:
                    BaseGmsClient.this.zza(1, null);
                    throw new IllegalStateException(String.format("A fatal developer error has occurred. Class name: %s. Start service action: %s. Service Descriptor: %s. ", new Object[]{getClass().getSimpleName(), BaseGmsClient.this.getStartServiceAction(), BaseGmsClient.this.getServiceDescriptor()}));
                default:
                    BaseGmsClient.this.zza(1, null);
                    if (this.zzcf != null) {
                        pendingIntent = (PendingIntent) this.zzcf.getParcelable(BaseGmsClient.KEY_PENDING_INTENT);
                    }
                    zza(new ConnectionResult(this.statusCode, pendingIntent));
                    return;
            }
        }
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this.mLock = new Object();
        this.zzcr = new Object();
        this.zzcv = new ArrayList<>();
        this.zzcx = 1;
        this.zzdc = null;
        this.zzdd = false;
        this.zzde = null;
        this.zzdf = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.zzco = (Looper) Preconditions.checkNotNull(looper, "Looper must not be null");
        this.zzcp = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcq = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.mHandler = new zzc(looper);
        this.zzda = i;
        this.zzcy = baseConnectionCallbacks;
        this.zzcz = baseOnConnectionFailedListener;
        this.zzdb = str;
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        this.mLock = new Object();
        this.zzcr = new Object();
        this.zzcv = new ArrayList<>();
        this.zzcx = 1;
        this.zzdc = null;
        this.zzdd = false;
        this.zzde = null;
        this.zzdf = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.mHandler = (Handler) Preconditions.checkNotNull(handler, "Handler must not be null");
        this.zzco = handler.getLooper();
        this.zzcp = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcq = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zzda = i;
        this.zzcy = baseConnectionCallbacks;
        this.zzcz = baseOnConnectionFailedListener;
        this.zzdb = null;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public String getStartServicePackage() {
        return "com.google.android.gms";
    }

    @Nullable
    private final String zzl() {
        return this.zzdb == null ? this.mContext.getClass().getName() : this.zzdb;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    @Nullable
    public String getLocalStartServiceAction() {
        return null;
    }

    /* access modifiers changed from: private */
    public final void zza(zza zza2) {
        this.zzde = zza2;
    }

    @KeepForSdk
    @Nullable
    public final Feature[] getAvailableFeatures() {
        zza zza2 = this.zzde;
        if (zza2 == null) {
            return null;
        }
        return zza2.zzdn;
    }

    /* access modifiers changed from: protected */
    @CallSuper
    @KeepForSdk
    public void onConnectedLocked(@NonNull T t) {
        this.zzck = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @CallSuper
    @KeepForSdk
    public void onConnectionSuspended(int i) {
        this.zzci = i;
        this.zzcj = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @CallSuper
    @KeepForSdk
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzcl = connectionResult.getErrorCode();
        this.zzcm = System.currentTimeMillis();
    }

    /* access modifiers changed from: private */
    public final void zza(int i, T t) {
        zzh zzh;
        boolean z = true;
        if ((i == 4) != (t != null)) {
            z = false;
        }
        Preconditions.checkArgument(z);
        synchronized (this.mLock) {
            this.zzcx = i;
            this.zzcu = t;
            onSetConnectState(i, t);
            switch (i) {
                case 1:
                    if (this.zzcw != null) {
                        this.zzcp.zza(this.zzcn.zzt(), this.zzcn.getPackageName(), this.zzcn.zzq(), this.zzcw, zzl());
                        this.zzcw = null;
                        break;
                    }
                    break;
                case 2:
                case 3:
                    if (!(this.zzcw == null || this.zzcn == null)) {
                        String zzt = this.zzcn.zzt();
                        String packageName = this.zzcn.getPackageName();
                        Log.e("GmsClient", new StringBuilder(String.valueOf(zzt).length() + 70 + String.valueOf(packageName).length()).append("Calling connect() while still connected, missing disconnect() for ").append(zzt).append(" on ").append(packageName).toString());
                        this.zzcp.zza(this.zzcn.zzt(), this.zzcn.getPackageName(), this.zzcn.zzq(), this.zzcw, zzl());
                        this.zzdf.incrementAndGet();
                    }
                    this.zzcw = new zze(this.zzdf.get());
                    if (this.zzcx != 3 || getLocalStartServiceAction() == null) {
                        zzh = new zzh(getStartServicePackage(), getStartServiceAction(), false, 129, getUseDynamicLookup());
                    } else {
                        zzh = new zzh(getContext().getPackageName(), getLocalStartServiceAction(), true, 129, false);
                    }
                    this.zzcn = zzh;
                    if (!this.zzcp.zza(new GmsClientSupervisor.zza(this.zzcn.zzt(), this.zzcn.getPackageName(), this.zzcn.zzq(), this.zzcn.getUseDynamicLookup()), this.zzcw, zzl())) {
                        String zzt2 = this.zzcn.zzt();
                        String packageName2 = this.zzcn.getPackageName();
                        Log.e("GmsClient", new StringBuilder(String.valueOf(zzt2).length() + 34 + String.valueOf(packageName2).length()).append("unable to connect to service: ").append(zzt2).append(" on ").append(packageName2).toString());
                        zza(16, (Bundle) null, this.zzdf.get());
                        break;
                    }
                    break;
                case 4:
                    onConnectedLocked(t);
                    break;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @KeepForSdk
    public void onSetConnectState(int i, T t) {
    }

    /* access modifiers changed from: private */
    public final boolean zza(int i, int i2, T t) {
        boolean z;
        synchronized (this.mLock) {
            if (this.zzcx != i) {
                z = false;
            } else {
                zza(i2, t);
                z = true;
            }
        }
        return z;
    }

    @KeepForSdk
    public void checkAvailabilityAndConnect() {
        int isGooglePlayServicesAvailable = this.zzcq.isGooglePlayServicesAvailable(this.mContext, getMinApkVersion());
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, (IInterface) null);
            triggerNotAvailable(new LegacyClientCallbackAdapter(), isGooglePlayServicesAvailable, (PendingIntent) null);
            return;
        }
        connect(new LegacyClientCallbackAdapter());
    }

    @KeepForSdk
    public void connect(@NonNull ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.zzct = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        zza(2, (IInterface) null);
    }

    @KeepForSdk
    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcx == 4;
        }
        return z;
    }

    @KeepForSdk
    public boolean isConnecting() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcx == 2 || this.zzcx == 3;
        }
        return z;
    }

    private final boolean zzm() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcx == 3;
        }
        return z;
    }

    @KeepForSdk
    public void disconnect() {
        this.zzdf.incrementAndGet();
        synchronized (this.zzcv) {
            int size = this.zzcv.size();
            for (int i = 0; i < size; i++) {
                this.zzcv.get(i).removeListener();
            }
            this.zzcv.clear();
        }
        synchronized (this.zzcr) {
            this.zzcs = null;
        }
        zza(1, (IInterface) null);
    }

    @KeepForSdk
    public void triggerConnectionSuspended(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, this.zzdf.get(), i));
    }

    /* access modifiers changed from: private */
    public final void zzb(int i) {
        int i2;
        if (zzm()) {
            i2 = 5;
            this.zzdd = true;
        } else {
            i2 = 4;
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(i2, this.zzdf.get(), 16));
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    @VisibleForTesting
    public void triggerNotAvailable(@NonNull ConnectionProgressReportCallbacks connectionProgressReportCallbacks, int i, @Nullable PendingIntent pendingIntent) {
        this.zzct = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzdf.get(), i, pendingIntent));
    }

    @KeepForSdk
    public final Context getContext() {
        return this.mContext;
    }

    @KeepForSdk
    public final Looper getLooper() {
        return this.zzco;
    }

    @KeepForSdk
    public Account getAccount() {
        return null;
    }

    @KeepForSdk
    public Feature[] getApiFeatures() {
        return zzch;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, new zzg(i, iBinder, bundle)));
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, @Nullable Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, i2, -1, new zzf(i, (Bundle) null)));
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    @KeepForSdk
    public Bundle getConnectionHint() {
        return null;
    }

    @KeepForSdk
    public final T getService() throws DeadObjectException {
        T t;
        synchronized (this.mLock) {
            if (this.zzcx == 5) {
                throw new DeadObjectException();
            }
            checkConnected();
            Preconditions.checkState(this.zzcu != null, "Client is connected but service is null");
            t = this.zzcu;
        }
        return t;
    }

    @WorkerThread
    @KeepForSdk
    public void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        Bundle getServiceRequestExtraArgs = getGetServiceRequestExtraArgs();
        GetServiceRequest getServiceRequest = new GetServiceRequest(this.zzda);
        getServiceRequest.zzak = this.mContext.getPackageName();
        getServiceRequest.zzdt = getServiceRequestExtraArgs;
        if (set != null) {
            getServiceRequest.zzds = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (requiresSignIn()) {
            getServiceRequest.zzdu = getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
            if (iAccountAccessor != null) {
                getServiceRequest.zzdr = iAccountAccessor.asBinder();
            }
        } else if (requiresAccount()) {
            getServiceRequest.zzdu = getAccount();
        }
        getServiceRequest.zzdv = zzch;
        getServiceRequest.zzdw = getApiFeatures();
        try {
            synchronized (this.zzcr) {
                if (this.zzcs != null) {
                    this.zzcs.getService(new zzd(this, this.zzdf.get()), getServiceRequest);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            triggerConnectionSuspended(1);
        } catch (SecurityException e2) {
            throw e2;
        } catch (RemoteException | RuntimeException e3) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e3);
            onPostInitHandler(8, (IBinder) null, (Bundle) null, this.zzdf.get());
        }
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public boolean enableLocalFallback() {
        return false;
    }

    @KeepForSdk
    public boolean requiresSignIn() {
        return false;
    }

    @KeepForSdk
    public void onUserSignOut(@NonNull SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.onSignOutComplete();
    }

    @KeepForSdk
    public boolean requiresAccount() {
        return false;
    }

    @KeepForSdk
    public boolean requiresGooglePlayServices() {
        return true;
    }

    @KeepForSdk
    public boolean providesSignIn() {
        return false;
    }

    @KeepForSdk
    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Set<Scope> getScopes() {
        return Collections.EMPTY_SET;
    }

    @KeepForSdk
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        T t;
        IGmsServiceBroker iGmsServiceBroker;
        synchronized (this.mLock) {
            i = this.zzcx;
            t = this.zzcu;
        }
        synchronized (this.zzcr) {
            iGmsServiceBroker = this.zzcs;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("REMOTE_CONNECTING");
                break;
            case 3:
                printWriter.print("LOCAL_CONNECTING");
                break;
            case 4:
                printWriter.print("CONNECTED");
                break;
            case 5:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (t == null) {
            printWriter.append("null");
        } else {
            printWriter.append(getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(t.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (iGmsServiceBroker == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzck > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.zzck;
            String format = simpleDateFormat.format(new Date(this.zzck));
            append.println(new StringBuilder(String.valueOf(format).length() + 21).append(j).append(" ").append(format).toString());
        }
        if (this.zzcj > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            switch (this.zzci) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append(String.valueOf(this.zzci));
                    break;
            }
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j2 = this.zzcj;
            String format2 = simpleDateFormat.format(new Date(this.zzcj));
            append2.println(new StringBuilder(String.valueOf(format2).length() + 21).append(j2).append(" ").append(format2).toString());
        }
        if (this.zzcm > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzcl));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j3 = this.zzcm;
            String format3 = simpleDateFormat.format(new Date(this.zzcm));
            append3.println(new StringBuilder(String.valueOf(format3).length() + 21).append(j3).append(" ").append(format3).toString());
        }
    }

    @KeepForSdk
    @Nullable
    public IBinder getServiceBrokerBinder() {
        IBinder asBinder;
        synchronized (this.zzcr) {
            if (this.zzcs == null) {
                asBinder = null;
            } else {
                asBinder = this.zzcs.asBinder();
            }
        }
        return asBinder;
    }

    /* access modifiers changed from: private */
    public final boolean zzn() {
        if (this.zzdd || TextUtils.isEmpty(getServiceDescriptor()) || TextUtils.isEmpty(getLocalStartServiceAction())) {
            return false;
        }
        try {
            Class.forName(getServiceDescriptor());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @KeepForSdk
    public String getEndpointPackageName() {
        if (isConnected() && this.zzcn != null) {
            return this.zzcn.getPackageName();
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    @KeepForSdk
    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public boolean getUseDynamicLookup() {
        return false;
    }
}
