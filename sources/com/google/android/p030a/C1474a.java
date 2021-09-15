package com.google.android.p030a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.a.a */
/* compiled from: BaseProxy */
public class C1474a implements IInterface {

    /* renamed from: a */
    private final IBinder f843a;

    /* renamed from: b */
    private final String f844b;

    protected C1474a(IBinder iBinder, String str) {
        this.f843a = iBinder;
        this.f844b = str;
    }

    public final IBinder asBinder() {
        return this.f843a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Parcel mo17673a() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.f844b);
        return obtain;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Parcel mo17674a(Parcel parcel) throws RemoteException {
        parcel = Parcel.obtain();
        try {
            this.f843a.transact(1, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            parcel.recycle();
        }
    }
}
