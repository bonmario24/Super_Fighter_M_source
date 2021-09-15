package com.thinkfly.star.utils;

import com.thinkfly.plugins.coludladder.CloudLadder;
import com.thinkfly.star.CloudLadderSDK;
import com.thinkfly.star.builder.Builder;

@Deprecated
public class SharedPreferencesUtils {
    private static SharedPreferencesUtils mSharedPreferencesUtils = null;
    private CloudLadder mCloudLadder = null;

    private SharedPreferencesUtils(CloudLadder cloudLadder) {
        this.mCloudLadder = cloudLadder;
    }

    public static SharedPreferencesUtils getInstance(CloudLadder cloudLadder) {
        if (mSharedPreferencesUtils != null) {
            return mSharedPreferencesUtils;
        }
        SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils(cloudLadder);
        mSharedPreferencesUtils = sharedPreferencesUtils;
        return sharedPreferencesUtils;
    }

    public String readUID() {
        return this.mCloudLadder.getSharedPreferencesUtils().read(CloudLadderSDK.UID_KEY);
    }

    public void saveUID(Builder builder) {
        if (builder == null || builder.getUid() == null || "".equals(builder.getUid())) {
            this.mCloudLadder.getSharedPreferencesUtils().save(CloudLadderSDK.UID_KEY, "");
        } else {
            this.mCloudLadder.getSharedPreferencesUtils().save(CloudLadderSDK.UID_KEY, builder.getUid());
        }
    }
}
