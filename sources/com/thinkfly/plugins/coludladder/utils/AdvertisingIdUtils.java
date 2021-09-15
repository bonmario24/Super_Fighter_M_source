package com.thinkfly.plugins.coludladder.utils;

import android.util.Log;
import com.doraemon.util.language.Language;
import com.xingfei.doraemon.deviceinfo.AdvertisingIdClient;
import com.xingfei.doraemon.deviceinfo.AdvertisingIdHelper;

public class AdvertisingIdUtils {
    /* access modifiers changed from: private */
    public static String mAId = "";

    public static String getAId() {
        return mAId;
    }

    public static void obtainAdvertisingId() {
        final long currentTimeMillis = System.currentTimeMillis();
        AdvertisingIdHelper.getInstance().getAdvertisingId(new AdvertisingIdHelper.AIdListener() {
            public void onFailed(String str) {
                Log.w(com.thinkfly.plugins.coludladder.log.Log.TAG, "initAid fail " + str);
                Log.w(com.thinkfly.plugins.coludladder.log.Log.TAG, "Displayed getAdvertisingId fai--> +" + (System.currentTimeMillis() - currentTimeMillis) + Language.f814ms);
            }

            public void onSuccess(AdvertisingIdClient.AdInfo adInfo) {
                if (adInfo != null) {
                    String unused = AdvertisingIdUtils.mAId = adInfo.getId();
                }
                Log.w(com.thinkfly.plugins.coludladder.log.Log.TAG, "Displayed getAdvertisingId success--> +" + (System.currentTimeMillis() - currentTimeMillis) + Language.f814ms);
            }
        });
    }
}
