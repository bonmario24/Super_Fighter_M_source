package com.appsflyer.internal.model.event;

import android.content.Context;
import android.support.annotation.Nullable;

public abstract class Purchase extends BackgroundEvent {
    Purchase() {
        this((String) null, (Boolean) null, (Context) null);
    }

    Purchase(@Nullable String str, @Nullable Boolean bool, @Nullable Context context) {
        super(str, Boolean.FALSE, (Boolean) null, bool, context);
    }
}
