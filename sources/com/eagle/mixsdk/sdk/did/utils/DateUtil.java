package com.eagle.mixsdk.sdk.did.utils;

import android.util.Log;
import com.eagle.mixsdk.sdk.base.Constants;
import java.util.Calendar;

public class DateUtil {
    public static boolean compareSameDay(long dateTime, long compareDateTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateTime);
        Calendar compareCalendar = Calendar.getInstance();
        compareCalendar.setTimeInMillis(compareDateTime);
        int year = calendar.get(1);
        int month = calendar.get(2);
        int dayOfYear = calendar.get(6);
        int dayOfMonth = calendar.get(5);
        Log.d(Constants.TAG, "year:" + year + " month:" + month + " dayOfYear:" + dayOfYear + " dayOfMonth:" + dayOfMonth);
        int compareYear = compareCalendar.get(1);
        int compareMonth = compareCalendar.get(2);
        int compareDayOfYear = compareCalendar.get(6);
        int compareDayOfMonth = compareCalendar.get(5);
        Log.d(Constants.TAG, "compareYear:" + compareYear + " compareMonth:" + compareMonth + " compareDayOfYear:" + compareDayOfYear + " compareDayOfMonth:" + compareDayOfMonth);
        if (year == compareYear && month == compareMonth && dayOfYear == compareDayOfYear && dayOfMonth == compareDayOfMonth) {
            return true;
        }
        return false;
    }
}
