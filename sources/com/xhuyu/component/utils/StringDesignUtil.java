package com.xhuyu.component.utils;

import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

public class StringDesignUtil {
    public static SpannableStringBuilder getSpannableStringBuilder(String text, int color, int startIndex, int entIndex) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        if (startIndex >= 0 && entIndex > startIndex && entIndex <= text.length()) {
            builder.setSpan(new ForegroundColorSpan(color), startIndex, entIndex, 33);
        }
        return builder;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        r3 = r7.indexOf(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.text.SpannableStringBuilder getSpannableStringBuilder(java.lang.String r7, java.lang.String[] r8, int[] r9) {
        /*
            android.text.SpannableStringBuilder r0 = new android.text.SpannableStringBuilder
            r0.<init>(r7)
            if (r8 == 0) goto L_0x003f
            r2 = 0
        L_0x0008:
            int r5 = r8.length
            if (r2 >= r5) goto L_0x003f
            r4 = r8[r2]
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L_0x003c
            boolean r5 = r7.contains(r4)
            if (r5 == 0) goto L_0x003c
            if (r9 == 0) goto L_0x003c
            int r5 = r9.length
            if (r5 <= r2) goto L_0x003c
            int r3 = r7.indexOf(r4)
            int r5 = r4.length()
            int r1 = r3 + r5
            if (r1 <= r3) goto L_0x003c
            int r5 = r7.length()
            if (r1 > r5) goto L_0x003c
            android.text.style.ForegroundColorSpan r5 = new android.text.style.ForegroundColorSpan
            r6 = r9[r2]
            r5.<init>(r6)
            r6 = 33
            r0.setSpan(r5, r3, r1, r6)
        L_0x003c:
            int r2 = r2 + 1
            goto L_0x0008
        L_0x003f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xhuyu.component.utils.StringDesignUtil.getSpannableStringBuilder(java.lang.String, java.lang.String[], int[]):android.text.SpannableStringBuilder");
    }

    public static SpannableStringBuilder getSpannableStringBuilder(String[] text, int[] color) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (text != null) {
            for (int i = 0; i < text.length; i++) {
                int startIndex = builder.length();
                builder.append(text[i]);
                if (color != null && color.length > i) {
                    builder.setSpan(new ForegroundColorSpan(color[i]), startIndex, builder.length(), 33);
                }
            }
        }
        return builder;
    }

    public static Spanned getSpanned(String text, String keyword, String colorValue) {
        return getSpanned(text, keyword, colorValue, false);
    }

    public static Spanned getSpanned(String text, String keyword, String colorValue, boolean isBold) {
        if (isBold) {
            return Html.fromHtml(text.replace(keyword, "<strong><font color=" + colorValue + ">" + keyword + "</font></strong>"));
        }
        return Html.fromHtml(text.replace(keyword, "<font color=" + colorValue + ">" + keyword + "</font>"));
    }

    public static Spanned getSpanned(String text, String[] keyword, String[] colorValue) {
        if (!(keyword == null || colorValue == null)) {
            for (int i = 0; i < keyword.length; i++) {
                if (colorValue.length > i) {
                    text = text.replace(keyword[i], "<font color=" + colorValue[i] + ">" + keyword[i] + "</font>");
                }
            }
        }
        return Html.fromHtml(text);
    }

    public static Spanned getSpanned(String[] keyword, String[] colorValue) {
        StringBuffer buffer = new StringBuffer();
        if (!(keyword == null || colorValue == null)) {
            for (int i = 0; i < keyword.length; i++) {
                if (colorValue.length > i) {
                    buffer.append("<font color=" + colorValue[i] + ">" + keyword[i] + "</font>");
                }
            }
        }
        return Html.fromHtml(buffer.toString());
    }
}
