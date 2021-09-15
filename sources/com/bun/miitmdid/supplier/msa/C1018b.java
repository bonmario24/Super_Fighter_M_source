package com.bun.miitmdid.supplier.msa;

import android.content.Context;
import android.support.annotation.Keep;
import android.text.TextUtils;
import com.bun.lib.sysParamters;
import com.bun.miitmdid.p014c.p018e.C0994a;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* renamed from: com.bun.miitmdid.supplier.msa.b */
public class C1018b implements InnerIdSupplier, C0994a {
    @Keep

    /* renamed from: a */
    public SupplierListener f798a;
    @Keep

    /* renamed from: b */
    private MsaClient f799b;

    @Keep
    public C1018b(Context context) {
        if (MsaClient.CheckService(context)) {
            String g = sysParamters.m443g();
            if (!TextUtils.isEmpty(g)) {
                MsaClient.StartMsaKlService(context, g);
            }
            this.f799b = new MsaClient(context, this);
        }
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
