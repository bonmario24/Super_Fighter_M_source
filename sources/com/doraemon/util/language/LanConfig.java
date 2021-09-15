package com.doraemon.util.language;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LanConfig {
    private static LanConfig mInstance;
    private Map<String, String> lanConfig = new HashMap();

    private LanConfig() {
    }

    public static LanConfig getInstance() {
        if (mInstance == null) {
            synchronized (LanConfig.class) {
                if (mInstance == null) {
                    mInstance = new LanConfig();
                }
            }
        }
        return mInstance;
    }

    public void loadConfig(Context context, String assetsPropertiesFileName) {
        Properties properties = new Properties();
        try {
            InputStream ins = context.getAssets().open(assetsPropertiesFileName);
            properties.load(ins);
            ins.close();
            for (Object key : properties.keySet()) {
                this.lanConfig.put(key.toString(), properties.getProperty(key.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLanConfig(String key) {
        if (TextUtils.isEmpty(key) || !this.lanConfig.containsKey(key)) {
            return null;
        }
        return this.lanConfig.get(key);
    }

    public void setLanConfig(String key, String value) {
        this.lanConfig.put(key, value);
    }
}
