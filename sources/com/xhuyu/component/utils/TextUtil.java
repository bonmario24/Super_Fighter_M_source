package com.xhuyu.component.utils;

import android.text.TextUtils;
import java.util.Locale;

public class TextUtil {
    public static String hidePhoneText(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(phone);
        sb.replace(3, 7, "****");
        return sb.toString();
    }

    public static String getSortLetterBySortKey(String sortKey) {
        if (sortKey == null || "".equals(sortKey.trim())) {
            return null;
        }
        String sortString = sortKey.trim().substring(0, 1).toUpperCase(Locale.CHINESE);
        if (sortString.matches("[A-Z]")) {
            return sortString.toUpperCase(Locale.CHINESE);
        }
        return "#";
    }
}
