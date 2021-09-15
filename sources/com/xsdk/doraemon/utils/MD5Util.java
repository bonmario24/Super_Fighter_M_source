package com.xsdk.doraemon.utils;

import com.facebook.appevents.AppEventsConstants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static void main(String[] args) {
        System.out.println(getMD5("password"));
    }

    public static String getMD5(String message) {
        try {
            return bytesToHex(MessageDigest.getInstance("MD5").digest(message.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String bytesToHex(byte[] r4) {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 0
        L_0x0006:
            int r3 = r4.length
            if (r1 >= r3) goto L_0x0022
            byte r2 = r4[r1]
            if (r2 >= 0) goto L_0x000f
            int r2 = r2 + 256
        L_0x000f:
            r3 = 16
            if (r2 >= r3) goto L_0x0018
            java.lang.String r3 = "0"
            r0.append(r3)
        L_0x0018:
            java.lang.String r3 = java.lang.Integer.toHexString(r2)
            r0.append(r3)
            int r1 = r1 + 1
            goto L_0x0006
        L_0x0022:
            java.lang.String r3 = r0.toString()
            java.lang.String r3 = r3.toUpperCase()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xsdk.doraemon.utils.MD5Util.bytesToHex(byte[]):java.lang.String");
    }

    public static String encode32(String text) {
        try {
            byte[] result = MessageDigest.getInstance("md5").digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                String hex = Integer.toHexString(b & 255);
                if (hex.length() == 1) {
                    sb.append(AppEventsConstants.EVENT_PARAM_VALUE_NO + hex);
                } else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
