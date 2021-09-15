package com.xhuyu.component.utils;

import android.provider.Settings;
import com.xhuyu.component.core.config.Constant;
import com.xsdk.doraemon.utils.ContextUtil;
import java.util.UUID;

public class UniqueIdentifierUtil {
    private static String getGUID() {
        return UUID.randomUUID().toString();
    }

    public static String getAndroidID() {
        return Settings.Secure.getString(ContextUtil.getInstance().getApplicationContext().getContentResolver(), "android_id");
    }

    public static String getCacheUniqueID() {
        return CacheUtils.getCacheString(Constant.HuYuCacheKey.KEY_UNIQUE_ID, "");
    }

    public static void saveUniqueID(String uniqueID) {
        CacheUtils.putCacheString(Constant.HuYuCacheKey.KEY_UNIQUE_ID, uniqueID);
    }

    public static String getCacheGoogleAdvertisingId() {
        return CacheUtils.getCacheString(Constant.HuYuCacheKey.KEY_GOOGLE_ADVERTISING_ID, "");
    }

    public static void saveGoogleAdvertisingId(String googleAdvertisingId) {
        CacheUtils.putCacheString(Constant.HuYuCacheKey.KEY_GOOGLE_ADVERTISING_ID, googleAdvertisingId);
    }

    public static String getCacheGUID() {
        return CacheUtils.getCacheString(Constant.HuYuCacheKey.KEY_GUID, "");
    }

    public static void saveGUID() {
        CacheUtils.putCacheString(Constant.HuYuCacheKey.KEY_GUID, getGUID());
    }
}
