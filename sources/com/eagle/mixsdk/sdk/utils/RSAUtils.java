package com.eagle.mixsdk.sdk.utils;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtils {
    public static final String KEY_ALGORITHM = "RSA";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    public static final String SIGNATURE_ALGORITHM_MD5 = "MD5withRSA";
    public static final String SIGNATURE_ALGORITHM_SHA = "SHA1WithRSA";

    public static Map<String, Object> generateKeys() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String, Object> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, (RSAPublicKey) keyPair.getPublic());
        keyMap.put(PRIVATE_KEY, (RSAPrivateKey) keyPair.getPrivate());
        return keyMap;
    }

    public static boolean verify(String content, String sign, String publicKey, String input_charset) {
        return verify(content, sign, publicKey, input_charset, SIGNATURE_ALGORITHM_MD5);
    }

    public static boolean verify(String content, String sign, String publicKey, String input_charset, String algorithm) {
        try {
            PublicKey pubKey = KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode(publicKey)));
            Signature signature = Signature.getInstance(algorithm);
            signature.initVerify(pubKey);
            signature.update(content.getBytes(input_charset));
            return signature.verify(Base64.decode(sign));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String sign(String content, String privateKey, String input_charset) {
        return sign(content, privateKey, input_charset, SIGNATURE_ALGORITHM_MD5);
    }

    public static String sign(String content, String privateKey, String input_charset, String algorithm) {
        try {
            PrivateKey priKey = KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(privateKey)));
            Signature signature = Signature.getInstance(algorithm);
            signature.initSign(priKey);
            signature.update(content.getBytes(input_charset));
            return Base64.encode(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        return Base64.encode(((Key) keyMap.get(PRIVATE_KEY)).getEncoded());
    }

    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        return Base64.encode(((Key) keyMap.get(PUBLIC_KEY)).getEncoded());
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> keys = generateKeys();
        String pubKey = getPublicKey(keys);
        String priKey = getPrivateKey(keys);
        System.out.println("The pubKey is ");
        System.out.println(pubKey);
        System.out.println(priKey);
    }
}
