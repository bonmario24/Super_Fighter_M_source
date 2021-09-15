package com.eagle.mixsdk.sdk.did.utils;

import com.facebook.appevents.AppEventsConstants;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DIDMD5Util {
    public static String MD5(String content) {
        try {
            byte[] hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 255) < 16) {
                    hex.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                hex.append(Integer.toHexString(b & 255));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e2);
        }
    }
}
