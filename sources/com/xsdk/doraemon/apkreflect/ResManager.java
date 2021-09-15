package com.xsdk.doraemon.apkreflect;

import android.content.Context;
import android.util.Log;
import com.doraemon.util.EncryptUtils;
import com.doraemon.util.ResourceUtils;
import java.io.File;
import java.util.Properties;

public class ResManager {
    private static ResManager instance;
    private String KP_RES_PACKAGE_NAME = "";
    private String KP_RES_URI = "";
    private String RES_APP_NAME = "";
    private String RES_PATH = "ResApk/";

    public static ResManager getInstance() {
        if (instance == null) {
            instance = new ResManager();
        }
        return instance;
    }

    public void init(Context context, String apkName, String packageName) {
        this.KP_RES_URI = this.RES_PATH + apkName;
        this.KP_RES_PACKAGE_NAME = packageName;
        if (apkName.contains(".")) {
            this.RES_APP_NAME = apkName.substring(0, apkName.indexOf("."));
        } else {
            this.RES_APP_NAME = apkName;
        }
        initResourceApk(context);
    }

    private void initResourceApk(Context context) {
        String path = context.getApplicationContext().getFilesDir().getAbsolutePath() + "/" + this.KP_RES_URI;
        ReflectResource.setKpResDexpath(path);
        ReflectResource.setKpResPackageName(this.KP_RES_PACKAGE_NAME);
        ReflectResource.setSourceDebug(false);
        File file = new File(path);
        if (file.exists()) {
            String oldFileMD5 = EncryptUtils.encryptMD5File2String(file);
            try {
                Properties properties = new Properties();
                properties.load(context.getResources().getAssets().open(this.RES_APP_NAME + ".ini"));
                String newFileMD5 = properties.get("resmd5").toString();
                Log.d("resapk", "file md5 result:" + oldFileMD5 + "; new md5:" + newFileMD5);
                if (!oldFileMD5.equals(newFileMD5)) {
                    file.delete();
                    ResourceUtils.copyFileFromAssets(this.RES_APP_NAME + ".apk", path);
                }
            } catch (Exception e) {
                Log.e("resapk", e.toString());
            }
        } else {
            Log.i("resapk", "no files exist before");
            ResourceUtils.copyFileFromAssets(this.RES_APP_NAME + ".apk", path);
        }
    }
}
