package com.bun.supplier;

import android.support.annotation.Keep;

@Keep
public interface InnerIdSupplier extends IdSupplier {
    @Keep
    /* renamed from: a */
    void mo14801a(SupplierListener supplierListener);

    @Keep
    /* renamed from: a */
    boolean mo14802a();

    @Keep
    String getUDID();

    @Keep
    void shutDown();
}
