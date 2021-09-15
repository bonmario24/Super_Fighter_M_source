package com.eagle.mixsdk.sdk.utils;

import com.doraemon.p027eg.DateUtils;
import java.util.Date;

@Deprecated
public class DateUtil {
    public static int getAge(String birth, String pattern) throws Exception {
        return DateUtils.getAge(birth, pattern);
    }

    public static int getAge(Date birthDay) throws Exception {
        return DateUtils.getAge(birthDay);
    }
}
