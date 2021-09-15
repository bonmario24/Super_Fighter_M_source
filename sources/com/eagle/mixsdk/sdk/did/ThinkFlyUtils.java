package com.eagle.mixsdk.sdk.did;

import android.content.Context;

public class ThinkFlyUtils {
    public static final String INVALID_DID = "00000000000000000000000000000000";

    public static String getDeviceID(Context context) {
        return DIDV4Proxy.getInstance().obtainUUID(context);
    }

    public static boolean checkDIDValid(Context context) {
        return DIDV4Proxy.getInstance().isValidDID(context);
    }

    public static String getDIDFeature() {
        return "" + DIDV4Proxy.getFeatures();
    }
}
