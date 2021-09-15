package com.eagle.mixsdk.sdk.did.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.p027eg.CommonUtil;
import com.doraemon.util.PermissionUtils;
import com.eagle.mixsdk.sdk.did.config.DIDConfig;
import com.eagle.mixsdk.sdk.utils.ExternalOverManager;
import com.facebook.appevents.AppEventsConstants;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class NewDevDidUtil {
    public static final int ANDROID_ID_TYPE = 2;
    public static final int DISPLAY_TYPE = 6;
    public static final int GAID_TYPE = 4;
    public static final int IMEI_TYPE = 1;
    public static final int MAC_TYPE = 5;
    public static final int OAID_TYPE = 3;
    public static final int RADIO_TYPE = 7;
    public static final int UUID_TYPE = 8;

    private NewDevDidUtil() {
    }

    private static class SingletonHolder {
        public static NewDevDidUtil instance = new NewDevDidUtil();

        private SingletonHolder() {
        }
    }

    public static NewDevDidUtil getInstance() {
        return SingletonHolder.instance;
    }

    public String readDidFromHidden(Context context, String uniqueName) {
        String did = "";
        if (!DIDUtil.isHiddenStorage()) {
            return did;
        }
        try {
            did = ExternalOverManager.getInstance().readWithPath(context, uniqueName, DIDConfig.NEW_CACHEDIR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return did;
    }

    public void writeDidToHidden(Context context, String uniqueName, String did) {
        if (DIDUtil.isHiddenStorage()) {
            try {
                ExternalOverManager.getInstance().writeWithPath(context, did, uniqueName, DIDConfig.NEW_CACHEDIR);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    public String getImei(Context context) {
        String imei = "";
        try {
            if (PermissionUtils.isGranted("android.permission.READ_PHONE_STATE")) {
                imei = CommonUtil.getIMEI(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TextUtils.isEmpty(imei) ? "" : imei;
    }

    public String newDidToV3(Context context) {
        String[] didInfoArr;
        int currentSDKVersion = Build.VERSION.SDK_INT;
        if (currentSDKVersion < 23) {
            didInfoArr = generateNewV3Did(context, 1);
        } else if (currentSDKVersion < 26) {
            didInfoArr = generateNewV3Did(context, 2);
        } else {
            didInfoArr = generateNewV3Did(context, 5);
        }
        if (didInfoArr.length <= 0) {
            return null;
        }
        String did = didInfoArr[0];
        return didInfoArr[1] + DIDMD5Util.MD5(DIDMD5Util.MD5(did) + DIDConfig.SALT).toUpperCase();
    }

    @SuppressLint({"HardwareIds"})
    private String[] generateNewV3Did(Context context, int generateType) {
        String cacheInfo = "";
        String cacheIdentify = "";
        if (generateType == 1) {
            String imei = getImei(context);
            if (!CheckUtils.isNullOrEmpty(imei) && !imei.contains("0000000")) {
                cacheInfo = imei;
                cacheIdentify = AppEventsConstants.EVENT_PARAM_VALUE_YES;
            }
        }
        if (CheckUtils.isNullOrEmpty(cacheInfo) && generateType <= 2) {
            String androidID = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (!CheckUtils.isNullOrEmpty(androidID) && !androidID.equals("9774d56d682e549c")) {
                cacheInfo = androidID;
                cacheIdentify = "4";
            }
        }
        String factoryInfo = Build.MANUFACTURER + Build.BRAND + Build.MODEL + Build.DEVICE + Build.PRODUCT + Build.BOARD;
        if (CheckUtils.isNullOrEmpty(cacheInfo) && generateType <= 5) {
            String macAddress = CommonUtil.getMacAddress();
            if (!CheckUtils.isNullOrEmpty(macAddress) && !"02:00:00:00:00:00".equals(macAddress)) {
                cacheInfo = factoryInfo + macAddress;
                cacheIdentify = "2";
            }
        }
        if (CheckUtils.isNullOrEmpty(cacheInfo) && generateType <= 6) {
            String display = Build.DISPLAY;
            if (!CheckUtils.isNullOrEmpty(display)) {
                cacheInfo = factoryInfo + display;
                cacheIdentify = "5";
            }
        }
        if (CheckUtils.isNullOrEmpty(cacheInfo) && generateType <= 7) {
            String radioVersion = Build.getRadioVersion();
            if (!CheckUtils.isNullOrEmpty(radioVersion)) {
                cacheInfo = factoryInfo + radioVersion;
                cacheIdentify = "6";
            }
        }
        if (CheckUtils.isNullOrEmpty(cacheInfo) && generateType <= 8) {
            cacheInfo = UUID.randomUUID().toString();
            cacheIdentify = "8";
        }
        return new String[]{cacheInfo, cacheIdentify};
    }

    @SuppressLint({"HardwareIds"})
    public String newDidToV2(Context context) {
        String did = null;
        String identify = "";
        if (Build.VERSION.SDK_INT >= 29) {
            String macAddress = CommonUtil.getMacAddress();
            if (!CheckUtils.isNullOrEmpty(macAddress) && !"02:00:00:00:00:00".equals(macAddress)) {
                identify = "2";
                did = macAddress + Build.FINGERPRINT;
            }
        } else {
            did = getImei(context);
            identify = AppEventsConstants.EVENT_PARAM_VALUE_YES;
        }
        if (TextUtils.isEmpty(did) || did.contains("0000000")) {
            String tempDid = Settings.Secure.getString(context.getContentResolver(), "android_id");
            try {
                if (TextUtils.isEmpty(tempDid) || "9774d56d682e549c".equals(tempDid)) {
                    did = UUID.randomUUID().toString();
                    identify = "8";
                } else {
                    identify = "4";
                    if (Build.VERSION.SDK_INT >= 29) {
                        tempDid = tempDid + Build.FINGERPRINT;
                    }
                    did = UUID.nameUUIDFromBytes(tempDid.getBytes("utf8")).toString();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (TextUtils.isEmpty(did)) {
            did = UUID.randomUUID().toString();
            identify = "8";
        }
        return identify + DIDMD5Util.MD5(DIDMD5Util.MD5(did) + DIDConfig.SALT).toUpperCase();
    }
}
