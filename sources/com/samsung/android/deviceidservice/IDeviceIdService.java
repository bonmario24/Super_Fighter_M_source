package com.samsung.android.deviceidservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.annotation.Keep;

@Keep
public interface IDeviceIdService extends IInterface {

    @Keep
    public static abstract class Stub extends Binder implements IDeviceIdService {

        @Keep
        private static class Proxy implements IDeviceIdService {
            @Keep

            /* renamed from: a */
            private IBinder f866a;

            @Keep
            Proxy(IBinder iBinder) {
                this.f866a = iBinder;
            }

            @Keep
            public native IBinder asBinder();

            @Keep
            public native String getAAID(String str);

            @Keep
            public native String getOAID();

            @Keep
            public native String getVAID(String str);
        }

        @Keep
        public Stub() {
            attachInterface(this, "com.samsung.android.deviceidservice.IDeviceIdService");
        }

        @Keep
        /* renamed from: a */
        public static native IDeviceIdService m661a(IBinder iBinder);

        @Keep
        public native IBinder asBinder();

        @Keep
        public native boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2);
    }

    @Keep
    String getAAID(String str);

    @Keep
    String getOAID();

    @Keep
    String getVAID(String str);
}
