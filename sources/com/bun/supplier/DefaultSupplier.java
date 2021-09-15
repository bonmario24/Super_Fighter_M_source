package com.bun.supplier;

import android.support.annotation.Keep;

@Keep
public class DefaultSupplier implements IdSupplier {
    @Keep
    public String getAAID() {
        return "";
    }

    @Keep
    public String getOAID() {
        return "";
    }

    @Keep
    public String getVAID() {
        return "";
    }

    @Keep
    public boolean isSupported() {
        return false;
    }
}
