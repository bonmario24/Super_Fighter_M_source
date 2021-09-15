package com.doraemon.util;

import android.util.Base64;
import com.eagle.mixsdk.sdk.utils.RSAUtils;
import com.facebook.appevents.AppEventsConstants;
import java.io.File;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class EncryptUtils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private EncryptUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String encryptMD2ToString(String data) {
        if (data == null || data.length() == 0) {
            return "";
        }
        return encryptMD2ToString(data.getBytes());
    }

    public static String encryptMD2ToString(byte[] data) {
        return bytes2HexString(encryptMD2(data));
    }

    public static byte[] encryptMD2(byte[] data) {
        return hashTemplate(data, "MD2");
    }

    public static String encryptMD5ToString(String data) {
        if (data == null || data.length() == 0) {
            return "";
        }
        return encryptMD5ToString(data.getBytes());
    }

    public static String encryptMD5ToLowerString(String data) {
        if (data == null || data.length() == 0) {
            return "";
        }
        return encryptMD5ToString(data.getBytes()).toLowerCase();
    }

    public static String encryptMD5ToString(String data, String salt) {
        if (data == null && salt == null) {
            return "";
        }
        if (salt == null) {
            return bytes2HexString(encryptMD5(data.getBytes()));
        }
        if (data == null) {
            return bytes2HexString(encryptMD5(salt.getBytes()));
        }
        return bytes2HexString(encryptMD5((data + salt).getBytes()));
    }

    public static String encryptMD5ToString(byte[] data) {
        return bytes2HexString(encryptMD5(data));
    }

    public static String encryptMD5ToString(byte[] data, byte[] salt) {
        if (data == null && salt == null) {
            return "";
        }
        if (salt == null) {
            return bytes2HexString(encryptMD5(data));
        }
        if (data == null) {
            return bytes2HexString(encryptMD5(salt));
        }
        byte[] dataSalt = new byte[(data.length + salt.length)];
        System.arraycopy(data, 0, dataSalt, 0, data.length);
        System.arraycopy(salt, 0, dataSalt, data.length, salt.length);
        return bytes2HexString(encryptMD5(dataSalt));
    }

    public static byte[] encryptMD5(byte[] data) {
        return hashTemplate(data, "MD5");
    }

    public static String encryptMD5File2String(String filePath) {
        return encryptMD5File2String(isSpace(filePath) ? null : new File(filePath));
    }

    public static byte[] encryptMD5File(String filePath) {
        return encryptMD5File(isSpace(filePath) ? null : new File(filePath));
    }

    public static String encryptMD5File2String(File file) {
        return bytes2HexString(encryptMD5File(file));
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0039 A[SYNTHETIC, Splitter:B:22:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0045 A[SYNTHETIC, Splitter:B:28:0x0045] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] encryptMD5File(java.io.File r8) {
        /*
            r6 = 0
            if (r8 != 0) goto L_0x0004
        L_0x0003:
            return r6
        L_0x0004:
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x004e }
            r4.<init>(r8)     // Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x004e }
            java.lang.String r7 = "MD5"
            java.security.MessageDigest r5 = java.security.MessageDigest.getInstance(r7)     // Catch:{ NoSuchAlgorithmException -> 0x0054, IOException -> 0x0057, all -> 0x0051 }
            java.security.DigestInputStream r1 = new java.security.DigestInputStream     // Catch:{ NoSuchAlgorithmException -> 0x0054, IOException -> 0x0057, all -> 0x0051 }
            r1.<init>(r4, r5)     // Catch:{ NoSuchAlgorithmException -> 0x0054, IOException -> 0x0057, all -> 0x0051 }
            r7 = 262144(0x40000, float:3.67342E-40)
            byte[] r0 = new byte[r7]     // Catch:{ NoSuchAlgorithmException -> 0x0054, IOException -> 0x0057, all -> 0x0051 }
        L_0x0019:
            int r7 = r1.read(r0)     // Catch:{ NoSuchAlgorithmException -> 0x0054, IOException -> 0x0057, all -> 0x0051 }
            if (r7 > 0) goto L_0x0019
            java.security.MessageDigest r5 = r1.getMessageDigest()     // Catch:{ NoSuchAlgorithmException -> 0x0054, IOException -> 0x0057, all -> 0x0051 }
            byte[] r6 = r5.digest()     // Catch:{ NoSuchAlgorithmException -> 0x0054, IOException -> 0x0057, all -> 0x0051 }
            if (r4 == 0) goto L_0x0003
            r4.close()     // Catch:{ IOException -> 0x002d }
            goto L_0x0003
        L_0x002d:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0003
        L_0x0032:
            r7 = move-exception
        L_0x0033:
            r2 = r7
        L_0x0034:
            r2.printStackTrace()     // Catch:{ all -> 0x0042 }
            if (r3 == 0) goto L_0x0003
            r3.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x0003
        L_0x003d:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0003
        L_0x0042:
            r6 = move-exception
        L_0x0043:
            if (r3 == 0) goto L_0x0048
            r3.close()     // Catch:{ IOException -> 0x0049 }
        L_0x0048:
            throw r6
        L_0x0049:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0048
        L_0x004e:
            r7 = move-exception
        L_0x004f:
            r2 = r7
            goto L_0x0034
        L_0x0051:
            r6 = move-exception
            r3 = r4
            goto L_0x0043
        L_0x0054:
            r7 = move-exception
            r3 = r4
            goto L_0x0033
        L_0x0057:
            r7 = move-exception
            r3 = r4
            goto L_0x004f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doraemon.util.EncryptUtils.encryptMD5File(java.io.File):byte[]");
    }

    public static String encryptSHA1ToString(String data) {
        if (data == null || data.length() == 0) {
            return "";
        }
        return encryptSHA1ToString(data.getBytes());
    }

    public static String encryptSHA1ToString(byte[] data) {
        return bytes2HexString(encryptSHA1(data));
    }

    public static byte[] encryptSHA1(byte[] data) {
        return hashTemplate(data, "SHA-1");
    }

    public static String encryptSHA224ToString(String data) {
        if (data == null || data.length() == 0) {
            return "";
        }
        return encryptSHA224ToString(data.getBytes());
    }

    public static String encryptSHA224ToString(byte[] data) {
        return bytes2HexString(encryptSHA224(data));
    }

    public static byte[] encryptSHA224(byte[] data) {
        return hashTemplate(data, "SHA224");
    }

    public static String encryptSHA256ToString(String data) {
        if (data == null || data.length() == 0) {
            return "";
        }
        return encryptSHA256ToString(data.getBytes());
    }

    public static String encryptSHA256ToString(byte[] data) {
        return bytes2HexString(encryptSHA256(data));
    }

    public static byte[] encryptSHA256(byte[] data) {
        return hashTemplate(data, "SHA-256");
    }

    public static String encryptSHA384ToString(String data) {
        if (data == null || data.length() == 0) {
            return "";
        }
        return encryptSHA384ToString(data.getBytes());
    }

    public static String encryptSHA384ToString(byte[] data) {
        return bytes2HexString(encryptSHA384(data));
    }

    public static byte[] encryptSHA384(byte[] data) {
        return hashTemplate(data, "SHA-384");
    }

    public static String encryptSHA512ToString(String data) {
        if (data == null || data.length() == 0) {
            return "";
        }
        return encryptSHA512ToString(data.getBytes());
    }

    public static String encryptSHA512ToString(byte[] data) {
        return bytes2HexString(encryptSHA512(data));
    }

    public static byte[] encryptSHA512(byte[] data) {
        return hashTemplate(data, "SHA-512");
    }

    private static byte[] hashTemplate(byte[] data, String algorithm) {
        if (data == null || data.length <= 0) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptHmacMD5ToString(String data, String key) {
        if (data == null || data.length() == 0 || key == null || key.length() == 0) {
            return "";
        }
        return encryptHmacMD5ToString(data.getBytes(), key.getBytes());
    }

    public static String encryptHmacMD5ToString(byte[] data, byte[] key) {
        return bytes2HexString(encryptHmacMD5(data, key));
    }

    public static byte[] encryptHmacMD5(byte[] data, byte[] key) {
        return hmacTemplate(data, key, "HmacMD5");
    }

    public static String encryptHmacSHA1ToString(String data, String key) {
        if (data == null || data.length() == 0 || key == null || key.length() == 0) {
            return "";
        }
        return encryptHmacSHA1ToString(data.getBytes(), key.getBytes());
    }

    public static String encryptHmacSHA1ToString(byte[] data, byte[] key) {
        return bytes2HexString(encryptHmacSHA1(data, key));
    }

    public static byte[] encryptHmacSHA1(byte[] data, byte[] key) {
        return hmacTemplate(data, key, "HmacSHA1");
    }

    public static String encryptHmacSHA224ToString(String data, String key) {
        if (data == null || data.length() == 0 || key == null || key.length() == 0) {
            return "";
        }
        return encryptHmacSHA224ToString(data.getBytes(), key.getBytes());
    }

    public static String encryptHmacSHA224ToString(byte[] data, byte[] key) {
        return bytes2HexString(encryptHmacSHA224(data, key));
    }

    public static byte[] encryptHmacSHA224(byte[] data, byte[] key) {
        return hmacTemplate(data, key, "HmacSHA224");
    }

    public static String encryptHmacSHA256ToString(String data, String key) {
        if (data == null || data.length() == 0 || key == null || key.length() == 0) {
            return "";
        }
        return encryptHmacSHA256ToString(data.getBytes(), key.getBytes());
    }

    public static String encryptHmacSHA256ToString(byte[] data, byte[] key) {
        return bytes2HexString(encryptHmacSHA256(data, key));
    }

    public static byte[] encryptHmacSHA256(byte[] data, byte[] key) {
        return hmacTemplate(data, key, "HmacSHA256");
    }

    public static String encryptHmacSHA384ToString(String data, String key) {
        if (data == null || data.length() == 0 || key == null || key.length() == 0) {
            return "";
        }
        return encryptHmacSHA384ToString(data.getBytes(), key.getBytes());
    }

    public static String encryptHmacSHA384ToString(byte[] data, byte[] key) {
        return bytes2HexString(encryptHmacSHA384(data, key));
    }

    public static byte[] encryptHmacSHA384(byte[] data, byte[] key) {
        return hmacTemplate(data, key, "HmacSHA384");
    }

    public static String encryptHmacSHA512ToString(String data, String key) {
        if (data == null || data.length() == 0 || key == null || key.length() == 0) {
            return "";
        }
        return encryptHmacSHA512ToString(data.getBytes(), key.getBytes());
    }

    public static String encryptHmacSHA512ToString(byte[] data, byte[] key) {
        return bytes2HexString(encryptHmacSHA512(data, key));
    }

    public static byte[] encryptHmacSHA512(byte[] data, byte[] key) {
        return hmacTemplate(data, key, "HmacSHA512");
    }

    private static byte[] hmacTemplate(byte[] data, byte[] key, String algorithm) {
        if (data == null || data.length == 0 || key == null || key.length == 0) {
            return null;
        }
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(secretKey);
            return mac.doFinal(data);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] encryptDES2Base64(byte[] data, byte[] key, String transformation, byte[] iv) {
        return base64Encode(encryptDES(data, key, transformation, iv));
    }

    public static String encryptDES2HexString(byte[] data, byte[] key, String transformation, byte[] iv) {
        return bytes2HexString(encryptDES(data, key, transformation, iv));
    }

    public static byte[] encryptDES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return symmetricTemplate(data, key, "DES", transformation, iv, true);
    }

    public static byte[] decryptBase64DES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return decryptDES(base64Decode(data), key, transformation, iv);
    }

    public static byte[] decryptHexStringDES(String data, byte[] key, String transformation, byte[] iv) {
        return decryptDES(hexString2Bytes(data), key, transformation, iv);
    }

    public static byte[] decryptDES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return symmetricTemplate(data, key, "DES", transformation, iv, false);
    }

    public static byte[] encrypt3DES2Base64(byte[] data, byte[] key, String transformation, byte[] iv) {
        return base64Encode(encrypt3DES(data, key, transformation, iv));
    }

    public static String encrypt3DES2HexString(byte[] data, byte[] key, String transformation, byte[] iv) {
        return bytes2HexString(encrypt3DES(data, key, transformation, iv));
    }

    public static byte[] encrypt3DES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return symmetricTemplate(data, key, "DESede", transformation, iv, true);
    }

    public static byte[] decryptBase64_3DES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return decrypt3DES(base64Decode(data), key, transformation, iv);
    }

    public static byte[] decryptHexString3DES(String data, byte[] key, String transformation, byte[] iv) {
        return decrypt3DES(hexString2Bytes(data), key, transformation, iv);
    }

    public static byte[] decrypt3DES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return symmetricTemplate(data, key, "DESede", transformation, iv, false);
    }

    public static byte[] encryptAES2Base64(byte[] data, byte[] key, String transformation, byte[] iv) {
        return base64Encode(encryptAES(data, key, transformation, iv));
    }

    public static String encryptAES2HexString(byte[] data, byte[] key, String transformation, byte[] iv) {
        return bytes2HexString(encryptAES(data, key, transformation, iv));
    }

    public static byte[] encryptAES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return symmetricTemplate(data, key, "AES", transformation, iv, true);
    }

    public static byte[] decryptBase64AES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return decryptAES(base64Decode(data), key, transformation, iv);
    }

    public static byte[] decryptHexStringAES(String data, byte[] key, String transformation, byte[] iv) {
        return decryptAES(hexString2Bytes(data), key, transformation, iv);
    }

    public static byte[] decryptAES(byte[] data, byte[] key, String transformation, byte[] iv) {
        return symmetricTemplate(data, key, "AES", transformation, iv, false);
    }

    private static byte[] symmetricTemplate(byte[] data, byte[] key, String algorithm, String transformation, byte[] iv, boolean isEncrypt) {
        SecretKey secretKey;
        int i = 1;
        if (data == null || data.length == 0 || key == null || key.length == 0) {
            return null;
        }
        try {
            if ("DES".equals(algorithm)) {
                secretKey = SecretKeyFactory.getInstance(algorithm).generateSecret(new DESKeySpec(key));
            } else {
                secretKey = new SecretKeySpec(key, algorithm);
            }
            Cipher cipher = Cipher.getInstance(transformation);
            if (iv == null || iv.length == 0) {
                if (!isEncrypt) {
                    i = 2;
                }
                cipher.init(i, secretKey);
            } else {
                AlgorithmParameterSpec params = new IvParameterSpec(iv);
                if (!isEncrypt) {
                    i = 2;
                }
                cipher.init(i, secretKey, params);
            }
            return cipher.doFinal(data);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] encryptRSA2Base64(byte[] data, byte[] publicKey, int keySize, String transformation) {
        return base64Encode(encryptRSA(data, publicKey, keySize, transformation));
    }

    public static String encryptRSA2HexString(byte[] data, byte[] publicKey, int keySize, String transformation) {
        return bytes2HexString(encryptRSA(data, publicKey, keySize, transformation));
    }

    public static byte[] encryptRSA(byte[] data, byte[] publicKey, int keySize, String transformation) {
        return rsaTemplate(data, publicKey, keySize, transformation, true);
    }

    public static byte[] decryptBase64RSA(byte[] data, byte[] privateKey, int keySize, String transformation) {
        return decryptRSA(base64Decode(data), privateKey, keySize, transformation);
    }

    public static byte[] decryptHexStringRSA(String data, byte[] privateKey, int keySize, String transformation) {
        return decryptRSA(hexString2Bytes(data), privateKey, keySize, transformation);
    }

    public static byte[] decryptRSA(byte[] data, byte[] privateKey, int keySize, String transformation) {
        return rsaTemplate(data, privateKey, keySize, transformation, false);
    }

    private static byte[] rsaTemplate(byte[] data, byte[] key, int keySize, String transformation, boolean isEncrypt) {
        Key rsaKey;
        if (data == null || data.length == 0 || key == null || key.length == 0) {
            return null;
        }
        if (isEncrypt) {
            try {
                rsaKey = KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(key));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            } catch (NoSuchPaddingException e2) {
                e2.printStackTrace();
                return null;
            } catch (InvalidKeyException e3) {
                e3.printStackTrace();
                return null;
            } catch (BadPaddingException e4) {
                e4.printStackTrace();
                return null;
            } catch (IllegalBlockSizeException e5) {
                e5.printStackTrace();
                return null;
            } catch (InvalidKeySpecException e6) {
                e6.printStackTrace();
                return null;
            }
        } else {
            rsaKey = KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(key));
        }
        if (rsaKey == null) {
            return null;
        }
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(isEncrypt ? 1 : 2, rsaKey);
        int len = data.length;
        int maxLen = keySize / 8;
        if (isEncrypt && transformation.toLowerCase().endsWith("pkcs1padding")) {
            maxLen -= 11;
        }
        int count = len / maxLen;
        if (count <= 0) {
            return cipher.doFinal(data);
        }
        byte[] ret = new byte[0];
        byte[] buff = new byte[maxLen];
        int index = 0;
        for (int i = 0; i < count; i++) {
            System.arraycopy(data, index, buff, 0, maxLen);
            ret = joins(ret, cipher.doFinal(buff));
            index += maxLen;
        }
        if (index == len) {
            return ret;
        }
        int restLen = len - index;
        byte[] buff2 = new byte[restLen];
        System.arraycopy(data, index, buff2, 0, restLen);
        return joins(ret, cipher.doFinal(buff2));
    }

    private static byte[] joins(byte[] prefix, byte[] suffix) {
        byte[] ret = new byte[(prefix.length + suffix.length)];
        System.arraycopy(prefix, 0, ret, 0, prefix.length);
        System.arraycopy(suffix, 0, ret, prefix.length, suffix.length);
        return ret;
    }

    private static String bytes2HexString(byte[] bytes) {
        int len;
        if (bytes == null || (len = bytes.length) <= 0) {
            return "";
        }
        char[] ret = new char[(len << 1)];
        int j = 0;
        for (int i = 0; i < len; i++) {
            int j2 = j + 1;
            ret[j] = HEX_DIGITS[(bytes[i] >> 4) & 15];
            j = j2 + 1;
            ret[j2] = HEX_DIGITS[bytes[i] & 15];
        }
        return new String(ret);
    }

    private static byte[] hexString2Bytes(String hexString) {
        if (isSpace(hexString)) {
            return null;
        }
        int len = hexString.length();
        if (len % 2 != 0) {
            hexString = AppEventsConstants.EVENT_PARAM_VALUE_NO + hexString;
            len++;
        }
        char[] hexBytes = hexString.toUpperCase().toCharArray();
        byte[] ret = new byte[(len >> 1)];
        for (int i = 0; i < len; i += 2) {
            ret[i >> 1] = (byte) ((hex2Dec(hexBytes[i]) << 4) | hex2Dec(hexBytes[i + 1]));
        }
        return ret;
    }

    private static int hex2Dec(char hexChar) {
        if (hexChar >= '0' && hexChar <= '9') {
            return hexChar - '0';
        }
        if (hexChar >= 'A' && hexChar <= 'F') {
            return (hexChar - 'A') + 10;
        }
        throw new IllegalArgumentException();
    }

    private static byte[] base64Encode(byte[] input) {
        return Base64.encode(input, 2);
    }

    private static byte[] base64Decode(byte[] input) {
        return Base64.decode(input, 2);
    }

    private static boolean isSpace(String s) {
        if (s == null) {
            return true;
        }
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
