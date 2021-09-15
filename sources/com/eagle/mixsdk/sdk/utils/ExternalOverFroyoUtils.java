package com.eagle.mixsdk.sdk.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;

public class ExternalOverFroyoUtils {
    public static File getDiskCacheDir(Context context, String cacheDir) {
        return new File(getExternalCachePath(context, cacheDir));
    }

    @SuppressLint({"NewApi"})
    public static String getExternalCachePath(Context context, String cacheDir) {
        String _cacheDir;
        if (TextUtils.isEmpty(cacheDir)) {
            _cacheDir = "/" + context.getPackageName();
        } else {
            _cacheDir = "/" + cacheDir;
        }
        return Environment.getExternalStorageDirectory().getPath() + _cacheDir;
    }
}
