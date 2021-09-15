package com.appsflyer.internal.model.event;

import android.content.Context;
import android.support.annotation.NonNull;
import com.appsflyer.AFEvent;
import com.appsflyer.AFInAppEventType;

public class Validate extends Purchase {
    public Validate(@NonNull Context context) {
        super(AFInAppEventType.PURCHASE, Boolean.TRUE, context);
    }

    public AFEvent urlString(String str) {
        return super.urlString(addChannel(str));
    }
}
