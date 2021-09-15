package com.xhuyu.component.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.doraemon.util.PermissionUtils;
import com.facebook.appevents.AppEventsConstants;
import com.xsdk.doraemon.utils.CheckUtil;
import java.lang.reflect.InvocationTargetException;

public class PhoneInfoUtils {
    public static String getDeviceCountryCode(Context context) {
        String countryCode;
        String countryCode2;
        TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
        if (tm != null) {
            String countryCode3 = tm.getSimCountryIso();
            if (countryCode3 != null && countryCode3.length() == 2) {
                return countryCode3.toUpperCase();
            }
            if (tm.getPhoneType() == 2) {
                countryCode2 = getCDMACountryIso();
            } else {
                countryCode2 = tm.getNetworkCountryIso();
            }
            if (countryCode2 != null && countryCode2.length() == 2) {
                return countryCode2.toUpperCase();
            }
        }
        if (Build.VERSION.SDK_INT >= 24) {
            countryCode = context.getResources().getConfiguration().getLocales().get(0).getCountry();
        } else {
            countryCode = context.getResources().getConfiguration().locale.getCountry();
        }
        if (countryCode.length() == 2) {
            return countryCode.toUpperCase();
        }
        return "KR";
    }

    @SuppressLint({"PrivateApi"})
    private static String getCDMACountryIso() {
        try {
            Class<?> systemProperties = Class.forName("android.os.SystemProperties");
            switch (Integer.parseInt(((String) systemProperties.getMethod("get", new Class[]{String.class}).invoke(systemProperties, new Object[]{"ro.cdma.home.operator.numeric"})).substring(0, 3))) {
                case 204:
                    return "NL";
                case 232:
                    return "AT";
                case 247:
                    return "LV";
                case 255:
                    return "UA";
                case 262:
                    return "DE";
                case 283:
                    return "AM";
                case 310:
                    return "US";
                case 311:
                    return "US";
                case 312:
                    return "US";
                case 316:
                    return "US";
                case 330:
                    return "PR";
                case 414:
                    return "MM";
                case 434:
                    return "UZ";
                case 450:
                    return "KR";
                case 455:
                    return "MO";
                case 460:
                    return "CN";
                case 619:
                    return "SL";
                case 634:
                    return "SD";
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | NullPointerException | InvocationTargetException e) {
        }
        return null;
    }

    public static String getLine1Number(Context context) {
        TelephonyManager telephonyManager;
        if (Build.VERSION.SDK_INT >= 29 || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        if (!PermissionUtils.isGranted("android.permission.READ_PHONE_STATE")) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        String line1Number = telephonyManager.getLine1Number();
        if (!CheckUtil.isEmpty(line1Number)) {
            return line1Number;
        }
        return AppEventsConstants.EVENT_PARAM_VALUE_NO;
    }

    public static boolean isPhoneNumber(String number) {
        if (TextUtils.isEmpty(number)) {
            return false;
        }
        return number.matches("^1(3[0-9]|4[0-9]|5[0-9]|6[0-9]|7[0-9]|8[0-9]|9[0-9])\\d{8}");
    }
}
