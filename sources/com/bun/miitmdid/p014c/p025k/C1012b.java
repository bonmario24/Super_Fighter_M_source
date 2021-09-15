package com.bun.miitmdid.p014c.p025k;

import android.content.Context;
import android.support.annotation.Keep;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* renamed from: com.bun.miitmdid.c.k.b */
public class C1012b implements InnerIdSupplier {
    @Keep

    /* renamed from: a */
    private Context f791a;

    @Keep
    public C1012b(Context context) {
        this.f791a = context;
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
