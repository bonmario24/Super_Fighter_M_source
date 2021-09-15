package com.eagle.mixsdk.sdk.did.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.eagle.mixsdk.sdk.did.config.DIDConfig;

public class DIDSpUtil {
    private static DIDSpUtil mEagleSharedPreferences;
    private SharedPreferences sPreferences = null;

    private DIDSpUtil() {
    }

    public static DIDSpUtil getInstance() {
        if (mEagleSharedPreferences != null) {
            return mEagleSharedPreferences;
        }
        DIDSpUtil dIDSpUtil = new DIDSpUtil();
        mEagleSharedPreferences = dIDSpUtil;
        return dIDSpUtil;
    }

    private SharedPreferences getsPreferences(Context context) {
        if (this.sPreferences == null) {
            this.sPreferences = context.getSharedPreferences(DIDConfig.SP_TAG_DID, 0);
        }
        return this.sPreferences;
    }

    public void save(Context context, String tag, String value) {
        if (context != null && !TextUtils.isEmpty(tag)) {
            SharedPreferences.Editor mEditor = getsPreferences(context).edit();
            mEditor.putString(tag, value);
            mEditor.apply();
        }
    }

    public void putBoolean(Context context, String tag, boolean value) {
        if (!TextUtils.isEmpty(tag) && context != null) {
            SharedPreferences.Editor mEditor = getsPreferences(context).edit();
            mEditor.putBoolean(tag, value);
            mEditor.apply();
        }
    }

    public boolean getBoolean(Context context, String tag) {
        return !TextUtils.isEmpty(tag) && context != null && getsPreferences(context).getBoolean(tag, false);
    }

    public void putLong(Context context, String tag, long value) {
        if (!TextUtils.isEmpty(tag) && context != null) {
            SharedPreferences.Editor mEditor = getsPreferences(context).edit();
            mEditor.putLong(tag, value);
            mEditor.apply();
        }
    }

    public long getLong(Context context, String tag) {
        if (TextUtils.isEmpty(tag) || context == null) {
            return -1;
        }
        return getsPreferences(context).getLong(tag, -1);
    }

    public String read(Context context) {
        return read(context, DIDConfig.SP_TAG_DID);
    }

    public void save(Context context, String value) {
        save(context, DIDConfig.SP_TAG_DID, value);
    }

    public String read(Context context, String tag) {
        if (TextUtils.isEmpty(tag) || context == null) {
            return "";
        }
        return getsPreferences(context).getString(tag, "");
    }
}
