package com.doraemon.constant;

import android.annotation.SuppressLint;
import android.os.Build;
import com.eagle.mixsdk.sdk.did.DIDV4Proxy;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressLint({"InlinedApi"})
public final class PermissionConstants {
    public static final String CALENDAR = "android.permission-group.CALENDAR";
    public static final String CAMERA = "android.permission-group.CAMERA";
    public static final String CONTACTS = "android.permission-group.CONTACTS";
    private static final String[] GROUP_CALENDAR = {"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"};
    private static final String[] GROUP_CAMERA = {"android.permission.CAMERA"};
    private static final String[] GROUP_CONTACTS = {"android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS", "android.permission.GET_ACCOUNTS"};
    private static final String[] GROUP_LOCATION = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private static final String[] GROUP_MICROPHONE = {"android.permission.RECORD_AUDIO"};
    private static final String[] GROUP_PHONE = {"android.permission.READ_PHONE_STATE", "android.permission.READ_PHONE_NUMBERS", "android.permission.CALL_PHONE", "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission.USE_SIP", "android.permission.PROCESS_OUTGOING_CALLS", "android.permission.ANSWER_PHONE_CALLS"};
    private static final String[] GROUP_PHONE_BELOW_O = {"android.permission.READ_PHONE_STATE", "android.permission.READ_PHONE_NUMBERS", "android.permission.CALL_PHONE", "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission.USE_SIP", "android.permission.PROCESS_OUTGOING_CALLS"};
    private static final String[] GROUP_SENSORS = {"android.permission.BODY_SENSORS"};
    private static final String[] GROUP_SMS = {"android.permission.SEND_SMS", "android.permission.RECEIVE_SMS", "android.permission.READ_SMS", "android.permission.RECEIVE_WAP_PUSH", "android.permission.RECEIVE_MMS"};
    private static final String[] GROUP_STORAGE = {DIDV4Proxy.PERMISSION_READ_EXTERNAL_STORAGE, DIDV4Proxy.PERMISSION_WRITE_EXTERNAL_STORAGE};
    public static final String LOCATION = "android.permission-group.LOCATION";
    public static final String MICROPHONE = "android.permission-group.MICROPHONE";
    public static final String PHONE = "android.permission-group.PHONE";
    public static final String SENSORS = "android.permission-group.SENSORS";
    public static final String SMS = "android.permission-group.SMS";
    public static final String STORAGE = "android.permission-group.STORAGE";

    @Retention(RetentionPolicy.SOURCE)
    public @interface Permission {
    }

    public static String[] getPermissions(String permission) {
        char c = 65535;
        switch (permission.hashCode()) {
            case -1639857183:
                if (permission.equals(CONTACTS)) {
                    c = 2;
                    break;
                }
                break;
            case -1410061184:
                if (permission.equals(PHONE)) {
                    c = 5;
                    break;
                }
                break;
            case -1250730292:
                if (permission.equals(CALENDAR)) {
                    c = 0;
                    break;
                }
                break;
            case -1140935117:
                if (permission.equals(CAMERA)) {
                    c = 1;
                    break;
                }
                break;
            case 421761675:
                if (permission.equals(SENSORS)) {
                    c = 6;
                    break;
                }
                break;
            case 828638019:
                if (permission.equals(LOCATION)) {
                    c = 3;
                    break;
                }
                break;
            case 852078861:
                if (permission.equals(STORAGE)) {
                    c = 8;
                    break;
                }
                break;
            case 1581272376:
                if (permission.equals(MICROPHONE)) {
                    c = 4;
                    break;
                }
                break;
            case 1795181803:
                if (permission.equals(SMS)) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return GROUP_CALENDAR;
            case 1:
                return GROUP_CAMERA;
            case 2:
                return GROUP_CONTACTS;
            case 3:
                return GROUP_LOCATION;
            case 4:
                return GROUP_MICROPHONE;
            case 5:
                if (Build.VERSION.SDK_INT < 26) {
                    return GROUP_PHONE_BELOW_O;
                }
                return GROUP_PHONE;
            case 6:
                return GROUP_SENSORS;
            case 7:
                return GROUP_SMS;
            case 8:
                return GROUP_STORAGE;
            default:
                return new String[]{permission};
        }
    }
}
