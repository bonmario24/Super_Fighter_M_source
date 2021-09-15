package com.xhuyu.component.utils;

import android.text.TextUtils;

public class ValueUtil {
    public static long strToLong(String str, long defvalue) {
        if (TextUtils.isEmpty(str)) {
            return defvalue;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return defvalue;
        }
    }
}
