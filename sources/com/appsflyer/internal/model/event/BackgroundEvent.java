package com.appsflyer.internal.model.event;

import android.content.Context;
import android.support.annotation.Nullable;
import com.appsflyer.AFEvent;
import com.appsflyer.AFHelper;

public abstract class BackgroundEvent extends AFEvent {

    /* renamed from: ɪ */
    private final boolean f617;

    /* renamed from: ɾ */
    private boolean f618;

    /* renamed from: ӏ */
    private final boolean f619;

    BackgroundEvent() {
        this((String) null, (Boolean) null, (Boolean) null, (Boolean) null, (Context) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BackgroundEvent(@Nullable String str, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable Context context) {
        super(str, Boolean.valueOf(bool3 != null ? bool3.booleanValue() : false), context);
        boolean z;
        boolean z2 = true;
        if (bool != null) {
            z = bool.booleanValue();
        } else {
            z = true;
        }
        this.f617 = z;
        this.f619 = bool2 != null ? bool2.booleanValue() : z2;
    }

    public BackgroundEvent trackingStopped(boolean z) {
        this.f618 = z;
        return this;
    }

    public boolean trackingStopped() {
        return this.f618;
    }

    public boolean readResponse() {
        return this.f617;
    }

    public boolean proxyMode() {
        return this.f619;
    }

    public String body() {
        return AFHelper.convertToJsonObject(params()).toString();
    }
}
