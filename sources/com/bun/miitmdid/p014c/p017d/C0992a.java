package com.bun.miitmdid.p014c.p017d;

import android.content.Context;
import android.support.annotation.Keep;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* renamed from: com.bun.miitmdid.c.d.a */
public class C0992a implements InnerIdSupplier {
    @Keep

    /* renamed from: a */
    private Context f740a;
    @Keep

    /* renamed from: b */
    private String f741b = "";
    @Keep

    /* renamed from: c */
    private String f742c = "";
    @Keep

    /* renamed from: d */
    private String f743d = "";
    @Keep

    /* renamed from: e */
    private boolean f744e = false;
    @Keep

    /* renamed from: f */
    private SupplierListener f745f;

    @Keep
    /* renamed from: com.bun.miitmdid.c.d.a$a */
    class C0993a implements Runnable {
        @Keep
        C0993a() {
        }

        @Keep
        public native void run();
    }

    @Keep
    public C0992a(Context context) {
        this.f740a = context;
    }

    @Keep
    /* renamed from: a */
    static native /* synthetic */ Context m489a(C0992a aVar);

    @Keep
    /* renamed from: a */
    static native /* synthetic */ String m490a(C0992a aVar, String str);

    @Keep
    /* renamed from: b */
    private native void m491b();

    @Keep
    /* renamed from: b */
    static native /* synthetic */ void m492b(C0992a aVar);

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
