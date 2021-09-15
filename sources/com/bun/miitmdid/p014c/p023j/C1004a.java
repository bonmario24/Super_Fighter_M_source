package com.bun.miitmdid.p014c.p023j;

import android.content.Context;
import android.support.annotation.Keep;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* renamed from: com.bun.miitmdid.c.j.a */
public class C1004a implements InnerIdSupplier {
    @Keep

    /* renamed from: a */
    private String f764a = "";
    @Keep

    /* renamed from: b */
    private Context f765b;

    @Keep
    /* renamed from: com.bun.miitmdid.c.j.a$a */
    class C1005a implements Runnable {
        @Keep

        /* renamed from: a */
        final /* synthetic */ SupplierListener f766a;

        @Keep
        C1005a(SupplierListener supplierListener) {
            this.f766a = supplierListener;
        }

        @Keep
        public native void run();
    }

    @Keep
    public C1004a(Context context) {
        this.f765b = context;
    }

    @Keep
    /* renamed from: a */
    public native void mo14801a(SupplierListener supplierListener);

    @Keep
    /* renamed from: a */
    public native void mo14823a(String str);

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
