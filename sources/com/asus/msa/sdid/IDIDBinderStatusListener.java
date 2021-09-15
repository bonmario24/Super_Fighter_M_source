package com.asus.msa.sdid;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.annotation.Keep;
import com.asus.msa.SupplementaryDID.IDidAidlInterface;

@Keep
public interface IDIDBinderStatusListener extends IInterface {

    @Keep
    public static abstract class Stub extends Binder implements IDIDBinderStatusListener {

        @Keep
        public static class Proxy implements IDIDBinderStatusListener {
            @Keep

            /* renamed from: a */
            public IBinder f684a;

            @Keep
            /* renamed from: a */
            public native void mo14767a(IDidAidlInterface iDidAidlInterface);

            @Keep
            public native IBinder asBinder();

            @Keep
            /* renamed from: b */
            public native void mo14768b();
        }

        @Keep
        public Stub() {
            attachInterface(this, "com.asus.msa.sdid.IDIDBinderStatusListener");
        }

        @Keep
        public native IBinder asBinder();

        @Keep
        public native boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2);
    }

    @Keep
    /* renamed from: a */
    void mo14767a(IDidAidlInterface iDidAidlInterface);

    @Keep
    /* renamed from: b */
    void mo14768b();
}
