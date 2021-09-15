package com.appsflyer.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.appsflyer.internal.u */
final class C0957u {
    C0957u() {
    }

    /* renamed from: ι */
    static C0958b m398(Context context) throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            C0959d dVar = new C0959d((byte) 0);
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            try {
                if (!context.bindService(intent, dVar, 1)) {
                    if (context != null) {
                        context.unbindService(dVar);
                    }
                    throw new IOException("Google Play connection failed");
                } else if (dVar.f641) {
                    throw new IllegalStateException();
                } else {
                    dVar.f641 = true;
                    C0960e eVar = new C0960e(dVar.f642.take());
                    C0958b bVar = new C0958b(eVar.mo14733(), eVar.mo14732());
                    if (context != null) {
                        context.unbindService(dVar);
                    }
                    return bVar;
                }
            } catch (Exception e) {
                throw e;
            } catch (Throwable th) {
                if (context != null) {
                    context.unbindService(dVar);
                }
                throw th;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    /* renamed from: com.appsflyer.internal.u$b */
    static final class C0958b {

        /* renamed from: ǃ */
        private final boolean f639;

        /* renamed from: ɩ */
        final String f640;

        C0958b(String str, boolean z) {
            this.f640 = str;
            this.f639 = z;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: ɩ */
        public final boolean mo14728() {
            return this.f639;
        }
    }

    /* renamed from: com.appsflyer.internal.u$d */
    static final class C0959d implements ServiceConnection {

        /* renamed from: ı */
        boolean f641;

        /* renamed from: Ι */
        final LinkedBlockingQueue<IBinder> f642;

        private C0959d() {
            this.f642 = new LinkedBlockingQueue<>(1);
            this.f641 = false;
        }

        /* synthetic */ C0959d(byte b) {
            this();
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f642.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public final void onServiceDisconnected(ComponentName componentName) {
        }
    }

    /* renamed from: com.appsflyer.internal.u$e */
    static final class C0960e implements IInterface {

        /* renamed from: ǃ */
        private IBinder f643;

        C0960e(IBinder iBinder) {
            this.f643 = iBinder;
        }

        public final IBinder asBinder() {
            return this.f643;
        }

        /* renamed from: ǃ */
        public final String mo14733() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f643.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: ı */
        public final boolean mo14732() throws RemoteException {
            boolean z = true;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.f643.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                return z;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
