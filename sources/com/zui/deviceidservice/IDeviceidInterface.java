package com.zui.deviceidservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.annotation.Keep;

@Keep
public interface IDeviceidInterface extends IInterface {

    @Keep
    public static abstract class Stub extends Binder implements IDeviceidInterface {

        @Keep
        private static class Proxy implements IDeviceidInterface {
            @Keep

            /* renamed from: a */
            private IBinder f901a;

            @Keep
            Proxy(IBinder iBinder) {
                this.f901a = iBinder;
            }

            @Keep
            /* renamed from: a */
            public native boolean mo19648a();

            @Keep
            public native IBinder asBinder();

            @Keep
            public native String getAAID(String str);

            @Keep
            public native String getOAID();

            @Keep
            public native String getUDID();

            @Keep
            public native String getVAID(String str);
        }

        @Keep
        public Stub() {
            attachInterface(this, "com.zui.deviceidservice.IDeviceidInterface");
        }

        @Keep
        /* renamed from: a */
        public static native IDeviceidInterface m682a(IBinder iBinder);

        @Keep
        public native IBinder asBinder();

        @Keep
        public native boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2);
    }

    @Keep
    /* renamed from: a */
    boolean mo19648a();

    @Keep
    String getAAID(String str);

    @Keep
    String getOAID();

    @Keep
    String getUDID();

    @Keep
    String getVAID(String str);
}
