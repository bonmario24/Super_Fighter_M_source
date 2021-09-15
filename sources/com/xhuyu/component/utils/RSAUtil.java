package com.xhuyu.component.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.eagle.mixsdk.sdk.utils.RSAUtils;
import com.xhuyu.component.core.config.SDKConfig;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class RSAUtil {
    public static final String TRANSFORMATION = "RSA/None/PKCS1Padding";

    public static String publicEncrypt(String password) {
        return publicEncrypt(password, SDKConfig.getInstance().getPlatformCipher());
    }

    public static String publicEncrypt(String source, String key) {
        byte[] cache;
        if (TextUtils.isEmpty(source)) {
            return "";
        }
        try {
            PublicKey publicKey = KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode(key, 2)));
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(1, publicKey);
            int inputLen = source.getBytes().length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offset = 0;
            int i = 0;
            while (inputLen - offset > 0) {
                if (inputLen - offset > 117) {
                    cache = cipher.doFinal(source.getBytes(), offset, 117);
                } else {
                    cache = cipher.doFinal(source.getBytes(), offset, inputLen - offset);
                }
                out.write(cache, 0, cache.length);
                i++;
                offset = i * 117;
            }
            byte[] result = out.toByteArray();
            out.close();
            return new String(Base64.encode(result, 2));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
