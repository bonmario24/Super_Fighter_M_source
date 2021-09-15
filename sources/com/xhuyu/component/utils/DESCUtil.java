package com.xhuyu.component.utils;

import android.text.TextUtils;
import android.util.Base64;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESCUtil {
    public static String encryptByKey(String datasource, String key) {
        if (TextUtils.isEmpty(datasource)) {
            return "";
        }
        try {
            SecureRandom random = new SecureRandom();
            SecretKey securekey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(key.getBytes()));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, securekey, random);
            byte[] result = cipher.doFinal(datasource.getBytes());
            if (result.length == 0) {
                return "";
            }
            return new String(Base64.encode(result, 0));
        } catch (Throwable e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String decrypt(String src, String key) throws Exception {
        if (TextUtils.isEmpty(src)) {
            return "";
        }
        SecureRandom random = new SecureRandom();
        SecretKey securekey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(key.getBytes()));
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, securekey, random);
        byte[] data = Base64.decode(src.getBytes(), 0);
        if (data.length == 0) {
            return "";
        }
        return new String(cipher.doFinal(data));
    }
}
