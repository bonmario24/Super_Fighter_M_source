package com.xhuyu.component.utils;

import android.app.Activity;
import android.content.Context;
import android.support.p000v4.app.ActivityCompat;
import com.eagle.mixsdk.sdk.did.DIDV4Proxy;

public class PermissionUtil {
    public static boolean isHaveStoragePermission(Context context) {
        return checkPermissions(context, DIDV4Proxy.PERMISSION_READ_EXTERNAL_STORAGE, DIDV4Proxy.PERMISSION_WRITE_EXTERNAL_STORAGE);
    }

    public static void applyStoragePermission(Activity activity, int requestCode) {
        ActivityCompat.requestPermissions(activity, new String[]{DIDV4Proxy.PERMISSION_READ_EXTERNAL_STORAGE, DIDV4Proxy.PERMISSION_WRITE_EXTERNAL_STORAGE}, requestCode);
    }

    public static boolean checkPermissions(Context context, String... ps) {
        for (String checkSelfPermission : ps) {
            if (ActivityCompat.checkSelfPermission(context, checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }
}
