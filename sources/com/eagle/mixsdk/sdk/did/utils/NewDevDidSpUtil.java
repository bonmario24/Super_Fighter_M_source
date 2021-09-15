package com.eagle.mixsdk.sdk.did.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.eagle.mixsdk.sdk.did.config.DIDConfig;

public class NewDevDidSpUtil {
    private static NewDevDidSpUtil mEagleSharedPreferences;
    private SharedPreferences sPreferences = null;

    private NewDevDidSpUtil() {
    }

    public static NewDevDidSpUtil getInstance() {
        if (mEagleSharedPreferences != null) {
            return mEagleSharedPreferences;
        }
        NewDevDidSpUtil newDevDidSpUtil = new NewDevDidSpUtil();
        mEagleSharedPreferences = newDevDidSpUtil;
        return newDevDidSpUtil;
    }

    private SharedPreferences getsPreferences(Context context) {
        if (this.sPreferences == null) {
            this.sPreferences = context.getSharedPreferences(DIDConfig.SP_TAG_DID, 0);
        }
        return this.sPreferences;
    }

    public String read(Context context, String tag) {
        if (TextUtils.isEmpty(tag) || context == null) {
            return "";
        }
        return getsPreferences(context).getString(tag, "");
    }

    public void save(Context context, String tag, String value) {
        if (context != null && !TextUtils.isEmpty(tag)) {
            SharedPreferences.Editor mEditor = getsPreferences(context).edit();
            mEditor.putString(tag, value);
            mEditor.apply();
        }
    }
}
