package com.bun.supplier;

import android.support.annotation.Keep;

@Keep
public interface IIdentifierListener {
    @Keep
    void OnSupport(boolean z, IdSupplier idSupplier);
}
