package com.bun.supplier;

import android.support.annotation.Keep;

@Keep
public interface SupplierListener {
    @Keep
    void OnSupport(boolean z, IdSupplier idSupplier);
}
