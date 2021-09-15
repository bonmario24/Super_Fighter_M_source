package com.huawei.android.hms.pps;

import android.content.Context;
import android.support.annotation.Keep;

@Keep
public class AdvertisingIdClient {

    @Keep
    public static final class Info {
        @Keep
        private final String advertisingId;
        @Keep
        private final boolean limitAdTrackingEnabled;

        @Keep
        Info(String str, boolean z) {
            this.advertisingId = str;
            this.limitAdTrackingEnabled = z;
        }

        @Keep
        public final native String getId();

        @Keep
        public final native boolean isLimitAdTrackingEnabled();
    }

    @Keep
    public static native Info getAdvertisingIdInfo(Context context);

    @Keep
    private static native String getTag();
}
