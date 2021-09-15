package com.google.android.finsky.externalreferrer;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.p030a.C1474a;
import com.google.android.p030a.C1475b;
import com.google.android.p030a.C1476c;

public interface IGetInstallReferrerService extends IInterface {

    public static abstract class Stub extends C1475b implements IGetInstallReferrerService {

        public static class Proxy extends C1474a implements IGetInstallReferrerService {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
            }

            /* renamed from: a */
            public final Bundle mo12182a(Bundle bundle) throws RemoteException {
                Parcel a = mo17673a();
                C1476c.m629a(a, (Parcelable) bundle);
                Parcel a2 = mo17674a(a);
                Bundle bundle2 = (Bundle) C1476c.m628a(a2, Bundle.CREATOR);
                a2.recycle();
                return bundle2;
            }
        }

        public Stub() {
            super("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
        }

        /* renamed from: a */
        public static IGetInstallReferrerService m21a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
            if (queryLocalInterface instanceof IGetInstallReferrerService) {
                return (IGetInstallReferrerService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }

    /* renamed from: a */
    Bundle mo12182a(Bundle bundle) throws RemoteException;
}
