package com.doraemon.p027eg;

/* renamed from: com.doraemon.eg.FilterUtils */
public class FilterUtils {
    public static String filterString(String str) {
        return filterString(str, "");
    }

    public static String filterString(String str, String def) {
        return CheckUtils.isNullOrEmpty(str) ? def : str;
    }

    public static Object filter(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return filterString((String) obj);
        }
        return obj;
    }
}
