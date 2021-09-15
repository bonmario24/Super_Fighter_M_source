package com.xsdk.doraemon.utils.permission;

import com.eagle.mixsdk.sdk.did.DIDV4Proxy;

public class PermissionsList {
    public static final String[] AUDIO_ARR = {"android.permission.RECORD_AUDIO"};
    public static final String[] CALL_ARR = {"android.permission.CALL_PHONE", "android.permission.READ_PHONE_STATE"};
    public static final String[] CAMERA_AND_AUDIO_ARR = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
    public static final String[] CAMERA_ARR = {"android.permission.CAMERA"};
    public static final String[] CONTACT_ARR = {"android.permission.GET_ACCOUNTS", "android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS"};
    public static final String[] LOCATION_ARR = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    public static final int PERMISSION_AUDIO_ARR_CODE = 6666;
    public static final int PERMISSION_CALL_REQUEST_CODE = 5555;
    public static final int PERMISSION_CAMERA_AUDIO_REQUEST_CODE = 888;
    public static final int PERMISSION_CAMERA_REQUEST_CODE = 222;
    public static final int PERMISSION_CONTACT_REQUEST_CODE = 4444;
    public static final int PERMISSION_LOCATION_REQUEST_CODE = 333;
    public static final int PERMISSION_SHARE_REQUEST_CODE = 777;
    public static final int PERMISSION_STORAGE_REQUEST_CODE = 111;
    public static final String[] PERMISS_ARR = {DIDV4Proxy.PERMISSION_WRITE_EXTERNAL_STORAGE, DIDV4Proxy.PERMISSION_READ_EXTERNAL_STORAGE};
    public static String[] UMENG_ARR = {"android.permission.ACCESS_FINE_LOCATION"};
}
