package com.thinkfly.plugins.coludladder.p034dk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.thinkfly.plugins.coludladder.config.Config;
import com.thinkfly.plugins.coludladder.log.Log;

@Deprecated
/* renamed from: com.thinkfly.plugins.coludladder.dk.SharedPreferencesUtils */
public class SharedPreferencesUtils {
    private static SharedPreferencesUtils mCloudLadderSharedPreferences;
    private SharedPreferences sPreferences = null;

    private SharedPreferencesUtils() {
    }

    public static SharedPreferencesUtils getInstance(Context context, String sp_name) {
        return getInstance();
    }

    public static SharedPreferencesUtils getInstance() {
        if (mCloudLadderSharedPreferences == null) {
            mCloudLadderSharedPreferences = new SharedPreferencesUtils();
        }
        return mCloudLadderSharedPreferences;
    }

    public void create(Context context, String sp_name) {
        if (sp_name == null || "".equals(sp_name)) {
            sp_name = Config.DBConfig.SP_NAME;
            Log.m662d(Log.TAG, "CREATE SP_NAME : " + sp_name);
        }
        this.sPreferences = context.getSharedPreferences(sp_name, 0);
    }

    public void save(String tag, String value) {
        SharedPreferences.Editor mEditor = this.sPreferences.edit();
        mEditor.putString(tag, value);
        mEditor.apply();
    }

    public String read(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            return this.sPreferences.getString(tag, "");
        }
        Log.m664i(Log.TAG, "if you want to read sharedpreferences,tag must not be    empty...");
        return null;
    }
}
