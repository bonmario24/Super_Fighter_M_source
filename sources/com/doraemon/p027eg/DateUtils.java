package com.doraemon.p027eg;

import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: com.doraemon.eg.DateUtils */
public class DateUtils {
    public static int getAge(String birth, String pattern) throws Exception {
        if (!TextUtils.isEmpty(birth) && !TextUtils.isEmpty(pattern)) {
            return getAge(new SimpleDateFormat(pattern, Locale.CHINESE).parse(birth));
        }
        throw new NullPointerException("参数不能为空");
    }

    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(1);
        int monthNow = cal.get(2);
        int dayOfMonthNow = cal.get(5);
        cal.setTime(birthDay);
        int yearBirth = cal.get(1);
        int monthBirth = cal.get(2);
        int dayOfMonthBirth = cal.get(5);
        int age = yearNow - yearBirth;
        if (monthNow > monthBirth) {
            return age;
        }
        if (monthNow != monthBirth) {
            return age - 1;
        }
        if (dayOfMonthNow < dayOfMonthBirth) {
            return age - 1;
        }
        return age;
    }

    public static boolean isDeepNight(long currentTimeMillis) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTimeInMillis(currentTimeMillis);
        int hour = calendar.get(11);
        return hour < 8 || hour >= 22;
    }

    public static boolean isWeekend(long currentTimeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTimeInMillis(currentTimeMillis);
        switch (calendar.get(7)) {
            case 1:
            case 7:
                return true;
            default:
                return false;
        }
    }

    public static boolean isSameDay(long l1, long l2) {
        return getZeroTimeMillis(l1) == getZeroTimeMillis(l2);
    }

    public static long getZeroTimeMillis(long currentTimeMillis) {
        return currentTimeMillis - ((((long) TimeZone.getDefault().getRawOffset()) + currentTimeMillis) % 86400000);
    }
}
