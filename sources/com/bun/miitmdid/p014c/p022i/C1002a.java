package com.bun.miitmdid.p014c.p022i;

import android.content.Context;
import android.support.annotation.Keep;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;
import com.heytap.openid.sdk.OpenIDSDK;

@Keep
/* renamed from: com.bun.miitmdid.c.i.a */
public class C1002a implements InnerIdSupplier {
    @Keep

    /* renamed from: a */
    private Context f761a;

    @Keep
    /* renamed from: com.bun.miitmdid.c.i.a$a */
    class C1003a implements Runnable {
        @Keep

        /* renamed from: a */
        final /* synthetic */ SupplierListener f762a;

        @Keep
        C1003a(SupplierListener supplierListener) {
            this.f762a = supplierListener;
        }

        @Keep
        public native void run();
    }

    @Keep
    public C1002a(Context context) {
        OpenIDSDK.m29d(context);
        this.f761a = context;
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
