package com.doraemon.util;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressLint({"ApplySharedPref"})
public final class SPUtils {
    private static final Map<String, SPUtils> SP_UTILS_MAP = new HashMap();

    /* renamed from: sp */
    private SharedPreferences f803sp;

    public static SPUtils getInstance() {
        return getInstance("", 0);
    }

    public static SPUtils getInstance(int mode) {
        return getInstance("", mode);
    }

    public static SPUtils getInstance(String spName) {
        return getInstance(spName, 0);
    }

    public static SPUtils getInstance(String spName, int mode) {
        if (isSpace(spName)) {
            spName = "spUtils";
        }
        SPUtils spUtils = SP_UTILS_MAP.get(spName);
        if (spUtils == null) {
            synchronized (SPUtils.class) {
                try {
                    spUtils = SP_UTILS_MAP.get(spName);
                    if (spUtils == null) {
                        SPUtils spUtils2 = new SPUtils(spName, mode);
                        try {
                            SP_UTILS_MAP.put(spName, spUtils2);
                            spUtils = spUtils2;
                        } catch (Throwable th) {
                            th = th;
                            SPUtils sPUtils = spUtils2;
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
        }
        return spUtils;
    }

    private SPUtils(String spName) {
        this.f803sp = Utils.getApp().getSharedPreferences(spName, 0);
    }

    private SPUtils(String spName, int mode) {
        this.f803sp = Utils.getApp().getSharedPreferences(spName, mode);
    }

    public void put(@NonNull String key, String value) {
        put(key, value, false);
    }

    public void put(@NonNull String key, String value, boolean isCommit) {
        if (isCommit) {
            this.f803sp.edit().putString(key, value).commit();
        } else {
            this.f803sp.edit().putString(key, value).apply();
        }
    }

    public String getString(@NonNull String key) {
        return getString(key, "");
    }

    public String getString(@NonNull String key, String defaultValue) {
        return this.f803sp.getString(key, defaultValue);
    }

    public void put(@NonNull String key, int value) {
        put(key, value, false);
    }

    public void put(@NonNull String key, int value, boolean isCommit) {
        if (isCommit) {
            this.f803sp.edit().putInt(key, value).commit();
        } else {
            this.f803sp.edit().putInt(key, value).apply();
        }
    }

    public int getInt(@NonNull String key) {
        return getInt(key, -1);
    }

    public int getInt(@NonNull String key, int defaultValue) {
        return this.f803sp.getInt(key, defaultValue);
    }

    public void put(@NonNull String key, long value) {
        put(key, value, false);
    }

    public void put(@NonNull String key, long value, boolean isCommit) {
        if (isCommit) {
            this.f803sp.edit().putLong(key, value).commit();
        } else {
            this.f803sp.edit().putLong(key, value).apply();
        }
    }

    public long getLong(@NonNull String key) {
        return getLong(key, -1);
    }

    public long getLong(@NonNull String key, long defaultValue) {
        return this.f803sp.getLong(key, defaultValue);
    }

    public void put(@NonNull String key, float value) {
        put(key, value, false);
    }

    public void put(@NonNull String key, float value, boolean isCommit) {
        if (isCommit) {
            this.f803sp.edit().putFloat(key, value).commit();
        } else {
            this.f803sp.edit().putFloat(key, value).apply();
        }
    }

    public float getFloat(@NonNull String key) {
        return getFloat(key, -1.0f);
    }

    public float getFloat(@NonNull String key, float defaultValue) {
        return this.f803sp.getFloat(key, defaultValue);
    }

    public void put(@NonNull String key, boolean value) {
        put(key, value, false);
    }

    public void put(@NonNull String key, boolean value, boolean isCommit) {
        if (isCommit) {
            this.f803sp.edit().putBoolean(key, value).commit();
        } else {
            this.f803sp.edit().putBoolean(key, value).apply();
        }
    }

    public boolean getBoolean(@NonNull String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return this.f803sp.getBoolean(key, defaultValue);
    }

    public void put(@NonNull String key, Set<String> value) {
        put(key, value, false);
    }

    public void put(@NonNull String key, Set<String> value, boolean isCommit) {
        if (isCommit) {
            this.f803sp.edit().putStringSet(key, value).commit();
        } else {
            this.f803sp.edit().putStringSet(key, value).apply();
        }
    }

    public Set<String> getStringSet(@NonNull String key) {
        return getStringSet(key, Collections.emptySet());
    }

    public Set<String> getStringSet(@NonNull String key, Set<String> defaultValue) {
        return this.f803sp.getStringSet(key, defaultValue);
    }

    public Map<String, ?> getAll() {
        return this.f803sp.getAll();
    }

    public boolean contains(@NonNull String key) {
        return this.f803sp.contains(key);
    }

    public void remove(@NonNull String key) {
        remove(key, false);
    }

    public void remove(@NonNull String key, boolean isCommit) {
        if (isCommit) {
            this.f803sp.edit().remove(key).commit();
        } else {
            this.f803sp.edit().remove(key).apply();
        }
    }

    public void clear() {
        clear(false);
    }

    public void clear(boolean isCommit) {
        if (isCommit) {
            this.f803sp.edit().clear().commit();
        } else {
            this.f803sp.edit().clear().apply();
        }
    }

    private static boolean isSpace(String s) {
        if (s == null) {
            return true;
        }
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
