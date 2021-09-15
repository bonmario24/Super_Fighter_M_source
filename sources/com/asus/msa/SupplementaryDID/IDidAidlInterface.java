package com.asus.msa.SupplementaryDID;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.annotation.Keep;

@Keep
public interface IDidAidlInterface extends IInterface {

    @Keep
    public static abstract class Stub extends Binder implements IDidAidlInterface {

        @Keep
        public static class Proxy implements IDidAidlInterface {
            @Keep

            /* renamed from: a */
            public IBinder f683a;

            @Keep
            public Proxy(IBinder iBinder) {
                this.f683a = iBinder;
            }

            @Keep
            /* renamed from: a */
            public native boolean mo14759a();

            @Keep
            public native IBinder asBinder();

            @Keep
            public native String getAAID();

            @Keep
            public native String getOAID();

            @Keep
            public native String getUDID();

            @Keep
            public native String getVAID();
        }

        @Keep
        public Stub() {
            attachInterface(this, "com.asus.msa.SupplementaryDID.IDidAidlInterface");
        }

        @Keep
        /* renamed from: a */
        public static native IDidAidlInterface m419a(IBinder iBinder);

        @Keep
        public native IBinder asBinder();

        @Keep
        public native boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2);
    }

    @Keep
    /* renamed from: a */
    boolean mo14759a();

    @Keep
    String getAAID();

    @Keep
    String getOAID();

    @Keep
    String getUDID();

    @Keep
    String getVAID();
}
