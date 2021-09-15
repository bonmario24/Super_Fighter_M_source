package com.bun.miitmdid.p014c.p015b;

import android.content.Context;
import android.os.IBinder;
import android.support.annotation.Keep;
import com.asus.msa.SupplementaryDID.IDidAidlInterface;
import com.asus.msa.sdid.IDIDBinderStatusListener;
import com.asus.msa.sdid.SupplementaryDIDManager;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* renamed from: com.bun.miitmdid.c.b.a */
public class C0990a implements InnerIdSupplier, IDIDBinderStatusListener {
    @Keep

    /* renamed from: a */
    private SupplierListener f732a;
    @Keep

    /* renamed from: b */
    private String f733b = "";
    @Keep

    /* renamed from: c */
    private String f734c = "";
    @Keep

    /* renamed from: d */
    private String f735d = "";
    @Keep

    /* renamed from: e */
    private String f736e = "";
    @Keep

    /* renamed from: f */
    private SupplementaryDIDManager f737f;
    @Keep

    /* renamed from: g */
    private boolean f738g = false;
    @Keep

    /* renamed from: h */
    private boolean f739h = false;

    @Keep
    public C0990a(Context context, SupplierListener supplierListener) {
        this.f732a = supplierListener;
        this.f737f = new SupplementaryDIDManager(context);
    }

    @Keep
    /* renamed from: a */
    public native void mo14767a(IDidAidlInterface iDidAidlInterface);

    @Keep
    /* renamed from: a */
    public native void mo14801a(SupplierListener supplierListener);

    @Keep
    /* renamed from: a */
    public native boolean mo14802a();

    @Keep
    public native IBinder asBinder();

    @Keep
    /* renamed from: b */
    public native void mo14768b();

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
