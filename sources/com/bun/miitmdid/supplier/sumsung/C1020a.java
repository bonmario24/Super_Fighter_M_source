package com.bun.miitmdid.supplier.sumsung;

import android.content.Context;
import android.support.annotation.Keep;
import com.bun.miitmdid.p014c.p018e.C0994a;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* renamed from: com.bun.miitmdid.supplier.sumsung.a */
public class C1020a implements InnerIdSupplier, C0994a {
    @Keep

    /* renamed from: a */
    public SupplierListener f801a;
    @Keep

    /* renamed from: b */
    private SumsungCore f802b;

    @Keep
    public C1020a(Context context, SupplierListener supplierListener) {
        this.f801a = supplierListener;
        this.f802b = new SumsungCore(context, this);
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
