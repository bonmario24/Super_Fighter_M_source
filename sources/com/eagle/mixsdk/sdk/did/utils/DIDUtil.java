package com.eagle.mixsdk.sdk.did.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.doraemon.p027eg.CommonUtil;
import com.doraemon.util.PermissionUtils;
import com.eagle.mixsdk.sdk.did.config.DIDConfig;
import com.eagle.mixsdk.sdk.utils.ExternalOverManager;
import com.facebook.appevents.AppEventsConstants;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class DIDUtil {
    private DIDUtil() {
    }

    private static class SingletonHolder {
        public static DIDUtil instance = new DIDUtil();

        private SingletonHolder() {
        }
    }

    public static DIDUtil getInstance() {
        return SingletonHolder.instance;
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

    public String readDid(Context context) {
        try {
            return ExternalOverManager.getInstance().readWithPath(context, DIDConfig.UUID_FILE_TAG, DIDConfig.NEW_CACHEDIR);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void writeDid(Context context, String did) {
        try {
            ExternalOverManager.getInstance().writeWithPath(context, did, DIDConfig.UUID_FILE_TAG, DIDConfig.NEW_CACHEDIR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"HardwareIds"})
    public String newDid(Context context) {
        String identify = AppEventsConstants.EVENT_PARAM_VALUE_YES;
        String _did = getImei(context);
        if (TextUtils.isEmpty(_did) || _did.contains("0000000")) {
            String tempDid = Settings.Secure.getString(context.getContentResolver(), "android_id");
            try {
                if (TextUtils.isEmpty(tempDid) || "9774d56d682e549c".equals(tempDid)) {
                    _did = UUID.randomUUID().toString();
                    identify = "8";
                } else {
                    identify = "4";
                    _did = UUID.nameUUIDFromBytes(tempDid.getBytes("utf8")).toString();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (TextUtils.isEmpty(_did)) {
            _did = UUID.randomUUID().toString();
            identify = "8";
        }
        return identify + DIDMD5Util.MD5(DIDMD5Util.MD5(_did) + DIDConfig.SALT).toUpperCase();
    }

    public static boolean isHiddenStorage() {
        return checkStorageLevel(1);
    }

    public static boolean isAudioStorage() {
        return checkStorageLevel(2);
    }

    public static boolean isPictureStorage() {
        return checkStorageLevel(3);
    }

    public static boolean isVideoStorage() {
        return checkStorageLevel(4);
    }

    private static boolean checkStorageLevel(int level) {
        return DIDConfig.didStorageLevel() >= level;
    }
}
