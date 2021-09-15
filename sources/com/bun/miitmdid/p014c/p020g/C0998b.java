package com.bun.miitmdid.p014c.p020g;

import android.content.Context;
import android.support.annotation.Keep;
import com.bun.miitmdid.p014c.p018e.C0994a;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* renamed from: com.bun.miitmdid.c.g.b */
public class C0998b implements InnerIdSupplier, C0994a {
    @Keep

    /* renamed from: a */
    private C0996a f755a;
    @Keep

    /* renamed from: b */
    private SupplierListener f756b;

    @Keep
    public C0998b(Context context, SupplierListener supplierListener) {
        this.f756b = supplierListener;
        this.f755a = new C0996a(context, this);
    }

    @Keep
    /* renamed from: a */
    public native void mo14801a(SupplierListener supplierListener);

    @Keep
    /* renamed from: a */
    public native void mo14811a(boolean z);

    @Keep
    /* renamed from: a */
    public native boolean mo14802a();

    @Keep
    /* renamed from: b */
    public native void mo14812b();

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
