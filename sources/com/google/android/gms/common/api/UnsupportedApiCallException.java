package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature zzbj;

    @KeepForSdk
    public UnsupportedApiCallException(Feature feature) {
        this.zzbj = feature;
    }

    public final String getMessage() {
        String valueOf = String.valueOf(this.zzbj);
        return new StringBuilder(String.valueOf(valueOf).length() + 8).append("Missing ").append(valueOf).toString();
    }
}
