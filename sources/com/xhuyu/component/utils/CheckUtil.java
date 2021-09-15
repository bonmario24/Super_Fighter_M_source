package com.xhuyu.component.utils;

import android.text.TextUtils;

public class CheckUtil {
    public static boolean isPasswordValid(String password) {
        if (TextUtils.isEmpty(password) || password.length() <= 5 || password.length() >= 19) {
            return false;
        }
        return true;
    }
}
