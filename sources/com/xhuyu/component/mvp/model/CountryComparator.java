package com.xhuyu.component.mvp.model;

import com.doraemon.p027eg.CheckUtils;
import com.xsdk.doraemon.utils.CheckUtil;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryComparator implements Comparator<CountryModel> {
    public int compare(CountryModel o1i, CountryModel o2i) {
        String o1SortLetters = o1i.getSortLetters();
        String o2SortLetters = o2i.getSortLetters();
        if (CheckUtils.isNullOrEmpty(o1SortLetters) || CheckUtils.isNullOrEmpty(o2SortLetters) || o1SortLetters.equals("@") || o2SortLetters.equals("#")) {
            return -1;
        }
        if (o1SortLetters.equals("#") || o2SortLetters.equals("@")) {
            return 1;
        }
        if (!CheckUtil.isInteger(o1SortLetters) && !CheckUtil.isInteger(o2SortLetters)) {
            return o1SortLetters.compareTo(o2SortLetters);
        }
        if (Integer.parseInt(o1SortLetters) == Integer.parseInt(o2SortLetters)) {
            return 0;
        }
        Matcher m = Pattern.compile("[0-9]+").matcher(o1SortLetters);
        if (!m.find()) {
            return -1;
        }
        String num1 = m.toMatchResult().group(0);
        Matcher m1 = Pattern.compile("[0-9]+").matcher(o2SortLetters);
        if (!m1.find()) {
            return -1;
        }
        return Integer.parseInt(num1) - Integer.parseInt(m1.toMatchResult().group(0));
    }
}
