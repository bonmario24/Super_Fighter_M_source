package com.xhuyu.component.utils;

public class MixTool {
    public static byte[] decode(String param) {
        byte[] bytes = param.getBytes();
        int i = 0;
        while (i < bytes.length) {
            bytes[i] = (byte) (bytes[i] - 17);
            System.out.print(bytes[i] + (i == bytes.length + -1 ? "" : ","));
            i++;
        }
        System.out.println();
        return bytes;
    }

    public static byte[] encode(byte[] paramBytes) {
        if (paramBytes == null) {
            return null;
        }
        int length = paramBytes.length;
        for (int i = 0; i < length; i++) {
            paramBytes[i] = (byte) (paramBytes[i] + 17);
        }
        return paramBytes;
    }

    public static byte[] toByte(String[] strs) {
        byte[] bytes = new byte[strs.length];
        for (int i = 0; i < strs.length; i++) {
            bytes[i] = Byte.parseByte(strs[i]);
        }
        return bytes;
    }
}
