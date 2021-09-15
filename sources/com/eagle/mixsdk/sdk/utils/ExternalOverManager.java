package com.eagle.mixsdk.sdk.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.doraemon.p027eg.Const;
import com.doraemon.util.PermissionUtils;
import com.eagle.mixsdk.sdk.did.DIDV4Proxy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class ExternalOverManager {
    private static ExternalOverManager mEagleExternalOverFroyo;

    private ExternalOverManager() {
    }

    public static ExternalOverManager getInstance() {
        if (mEagleExternalOverFroyo != null) {
            return mEagleExternalOverFroyo;
        }
        ExternalOverManager externalOverManager = new ExternalOverManager();
        mEagleExternalOverFroyo = externalOverManager;
        return externalOverManager;
    }

    public void createExternalFile(Context context, String uniqueName) {
        createExternalFile(context, uniqueName, "");
    }

    public void createExternalFile(Context context, String uniqueName, String cacheDir) {
        if (TextUtils.isEmpty(uniqueName)) {
            Log.i(Const.TAG, "the uniqueName not allow be null...");
        }
    }

    public void writeDiD(Context context, String content, String uniqueName) {
    }

    public void writeWithPath(Context context, String content, String uniqueName, String cacheDir) {
        if (!PermissionUtils.isGranted(DIDV4Proxy.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            Log.w(Const.TAG, "no permission  write " + cacheDir);
            return;
        }
        File externalFile = ExternalOverFroyoUtils.getDiskCacheDir(context, cacheDir);
        if (externalFile.exists() || externalFile.mkdirs()) {
            File cacheFile = new File(externalFile.getAbsolutePath() + File.separator + uniqueName);
            try {
                if (cacheFile.exists() || cacheFile.createNewFile()) {
                    FileOutputStream stream = new FileOutputStream(cacheFile);
                    stream.write(content.getBytes());
                    stream.close();
                    Log.w(Const.TAG, "write success ： " + content);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String readWithPath(Context context, String uniqueName, String cacheDir) {
        if (!PermissionUtils.isGranted(DIDV4Proxy.PERMISSION_READ_EXTERNAL_STORAGE)) {
            Log.w(Const.TAG, "no permission  read " + uniqueName);
            return "";
        }
        File cacheFile = new File(ExternalOverFroyoUtils.getExternalCachePath(context, cacheDir) + File.separator + uniqueName);
        if (!cacheFile.exists()) {
            return "";
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(cacheFile));
            StringBuffer sb = new StringBuffer();
            while (true) {
                String readLine = br.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    br.close();
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void write(Context context, String content, String uniqueName) {
        writeWithPath(context, content, uniqueName, "");
    }

    public String read(Context context, String uniqueName) {
        return readWithPath(context, uniqueName, "");
    }
}
