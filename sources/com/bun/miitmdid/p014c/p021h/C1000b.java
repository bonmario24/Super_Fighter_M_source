package com.bun.miitmdid.p014c.p021h;

import android.content.Context;
import android.support.annotation.Keep;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* renamed from: com.bun.miitmdid.c.h.b */
public class C1000b implements InnerIdSupplier {
    @Keep

    /* renamed from: a */
    private Context f758a;

    @Keep
    /* renamed from: com.bun.miitmdid.c.h.b$a */
    class C1001a implements Runnable {
        @Keep

        /* renamed from: a */
        final /* synthetic */ SupplierListener f759a;

        @Keep
        C1001a(SupplierListener supplierListener) {
            this.f759a = supplierListener;
        }

        @Keep
        public native void run();
    }

    @Keep
    public C1000b(Context context) {
        this.f758a = context;
    }

    @Keep
    /* renamed from: a */
    public native void mo14801a(SupplierListener supplierListener);

    @Keep
    /* renamed from: a */
    public native boolean mo14802a();

    @Keep
    public native String getAAID();

    @Keep
    public native String getOAID();

    @Keep
    public native String getUDID();

    @Keep
    public native String getVAID();

    @Keep
    public native boolean isSupported();

    @Keep
    public native void shutDown();
}
