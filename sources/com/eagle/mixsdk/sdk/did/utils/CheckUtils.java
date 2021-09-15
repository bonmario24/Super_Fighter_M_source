package com.eagle.mixsdk.sdk.did.utils;

import android.text.TextUtils;
import java.util.regex.Pattern;

@Deprecated
public class CheckUtils {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNumeric(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("[0-9]*").matcher(str.substring(0, 1)).matches();
    }
}
