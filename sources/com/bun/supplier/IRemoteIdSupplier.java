package com.bun.supplier;

import android.support.annotation.Keep;

@Keep
public interface IRemoteIdSupplier extends InnerIdSupplier {
    @Keep
    String getAAID(String str);

    @Keep
    String getVAID(String str);
}
