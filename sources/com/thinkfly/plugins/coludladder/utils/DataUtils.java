package com.thinkfly.plugins.coludladder.utils;

import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import org.json.JSONArray;

public class DataUtils {
    public static byte[] jsonToByteArray(String obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj.getBytes("utf-8");
        } catch (Exception e) {
            return null;
        }
    }

    public static String compress(JSONArray jsonArray) throws IOException {
        ByteArrayOutputStream localByteArrayOutputStream = null;
        try {
            ByteArrayOutputStream localByteArrayOutputStream2 = new ByteArrayOutputStream(jsonArray.toString().getBytes().length);
            try {
                DeflaterOutputStream defla = new DeflaterOutputStream(localByteArrayOutputStream2);
                defla.write(jsonArray.toString().getBytes());
                defla.finish();
                defla.close();
                byte[] arrayOfByte = localByteArrayOutputStream2.toByteArray();
                if (localByteArrayOutputStream2 != null) {
                    localByteArrayOutputStream2.close();
                }
                return base64Encode(arrayOfByte);
            } catch (Throwable th) {
                th = th;
                localByteArrayOutputStream = localByteArrayOutputStream2;
            }
        } catch (Throwable th2) {
            th = th2;
            if (localByteArrayOutputStream != null) {
                localByteArrayOutputStream.close();
            }
            throw th;
        }
    }

    private static String base64Encode(byte[] input) {
        return Base64.encodeToString(input, 2);
    }

    public static String encrypt(String salt, String params) {
        return EncryptUtils.md5(EncryptUtils.md5(params) + salt);
    }
}
