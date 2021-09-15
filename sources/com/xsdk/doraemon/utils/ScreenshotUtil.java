package com.xsdk.doraemon.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import com.thinkfly.star.utils.CheckUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ScreenshotUtil {
    public static void saveScreenshot(Context context, String sdkDirName, String fileName, InputStream viewInputStream) {
        insertMediaFile(context, sdkDirName, fileName, viewInputStream);
    }

    private static String insertMediaFile(Context context, String sdkDirName, String fileName, InputStream inputStream) {
        Uri fileUri = getUriDispose(context, sdkDirName, fileName);
        ContentResolver cr = context.getContentResolver();
        try {
            byte[] buffer = new byte[1048576];
            ParcelFileDescriptor parcelFileDescriptor = cr.openFileDescriptor(fileUri, "w");
            FileOutputStream fileOutputStream = null;
            if (parcelFileDescriptor != null) {
                fileOutputStream = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
            }
            if (fileOutputStream != null) {
                while (true) {
                    int numRead = inputStream.read(buffer);
                    if (numRead == -1) {
                        break;
                    }
                    fileOutputStream.write(buffer, 0, numRead);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
                context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", fileUri));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "Failed to insert media file" + e);
            if (fileUri != null) {
                cr.delete(fileUri, (String) null, (String[]) null);
                fileUri = null;
            }
        }
        if (fileUri != null) {
            return fileUri.toString();
        }
        return null;
    }

    private static Uri getUriDispose(Context context, String sdkFileName, String fileName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", fileName);
        contentValues.put("mime_type", "image/*");
        Uri fileUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        if (Build.VERSION.SDK_INT >= 29) {
            StringBuilder append = new StringBuilder().append("DCIM/");
            if (CheckUtils.isNullOrEmpty(sdkFileName)) {
                sdkFileName = "";
            }
            contentValues.put("relative_path", append.append(sdkFileName).toString());
        } else {
            contentValues.put("_data", fileMkdirs(fileName, sdkFileName, Environment.DIRECTORY_PICTURES));
        }
        if (fileUri != null) {
            return context.getContentResolver().insert(fileUri, contentValues);
        }
        return fileUri;
    }

    private static String fileMkdirs(String fileName, String sdkFileName, String publicDir) {
        StringBuilder append = new StringBuilder().append(Environment.getExternalStorageDirectory().getPath()).append(File.separator).append(publicDir).append(File.separator);
        if (CheckUtils.isNullOrEmpty(sdkFileName)) {
            sdkFileName = "";
        }
        File dir = new File(append.append(sdkFileName).toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, fileName);
        if (file.exists()) {
            file.delete();
        }
        return file.getPath();
    }
}
