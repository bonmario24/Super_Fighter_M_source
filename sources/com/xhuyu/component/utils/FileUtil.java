package com.xhuyu.component.utils;

import android.content.Context;
import android.graphics.Bitmap;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FileUtil {
    public static boolean writeBytes(byte[] bytes, int length, int offset, String path) {
        File file = new File(path);
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            return false;
        }
        try {
            OutputStream ins = new FileOutputStream(path);
            ins.write(bytes, offset, length);
            ins.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeBytes(byte[] bytes, String path) {
        return writeBytes(bytes, bytes.length, 0, path);
    }

    public static boolean writeString(String content, String path) {
        return writeBytes(content.getBytes(), path);
    }

    public static boolean copyFile(String source, String dst) {
        if (CheckUtil.checkTextEmpty(source, dst)) {
            return false;
        }
        File fileSource = new File(source);
        new File(dst);
        if (!fileSource.exists()) {
            return false;
        }
        byte[] buf = new byte[1024];
        try {
            InputStream ins = new FileInputStream(source);
            OutputStream outs = new FileOutputStream(dst);
            while (true) {
                int len = ins.read(buf);
                if (len > 0) {
                    outs.write(buf, 0, len);
                } else {
                    ins.close();
                    outs.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeBitmap(Bitmap bitmap, Bitmap.CompressFormat format, int quality, String path) {
        try {
            File dir = new File(path).getParentFile();
            if (!dir.exists() && !dir.mkdirs()) {
                return false;
            }
            FileOutputStream outs = new FileOutputStream(path);
            bitmap.compress(format, quality, outs);
            outs.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String readString(String file) {
        return readString(new File(file));
    }

    public static String readString(File file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = reader.readLine();
                if (line != null) {
                    sb.append(line);
                } else {
                    reader.close();
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] readBytes(String file) {
        return readBytes(new File(file));
    }

    public static byte[] readBytes(File file) {
        if (file == null || !file.exists()) {
            return new byte[0];
        }
        ByteArrayOutputStream outs = new ByteArrayOutputStream(1024);
        byte[] buf = new byte[1024];
        try {
            InputStream ins = new FileInputStream(file);
            while (true) {
                int len = ins.read(buf);
                if (len > 0) {
                    outs.write(buf, 0, len);
                } else {
                    ins.close();
                    return outs.toByteArray();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static void assetFile2sd(Context context, String fileAssetPath, String fileSdPath) {
        try {
            new File(new File(fileSdPath).getParent()).mkdirs();
            OutputStream outputStream = new FileOutputStream(fileSdPath);
            InputStream inputStream = context.getAssets().open(fileAssetPath);
            byte[] buffer = new byte[1024];
            for (int length = inputStream.read(buffer); length > 0; length = inputStream.read(buffer)) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            SDKLoggerUtil.getLogger().mo19502e("copy files fail", new Object[0]);
        }
    }
}
