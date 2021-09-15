package com.eagle.mixsdk.sdk.did.utils;

import android.content.Context;
import com.eagle.mixsdk.sdk.did.impl.DIDThreadRunnable;
import com.eagle.mixsdk.sdk.did.listener.IDIDObtainListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeviceIDUtil {
    public static final String V2_VERSION_TAG = "_v2";
    public static final String V3_VERSION_TAG = "_v3";
    public static final String V4_VERSION_TAG = "_v4";
    private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    public static void doGetDeviceID(Context context, String versionTag, IDIDObtainListener obtainListener) {
        singleThreadExecutor.execute(new DIDThreadRunnable(context, versionTag, obtainListener));
    }
}
