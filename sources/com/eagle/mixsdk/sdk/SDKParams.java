package com.eagle.mixsdk.sdk;

import android.text.TextUtils;
import com.doraemon.util.ShellAdbUtil;
import java.util.HashMap;
import java.util.Map;

public class SDKParams {
    private Map<String, String> configs;

    public SDKParams() {
        this.configs = new HashMap();
    }

    public SDKParams(Map<String, String> configs2) {
        this();
        if (configs2 != null) {
            for (String key : configs2.keySet()) {
                put(key, configs2.get(key));
            }
        }
    }

    public boolean contains(String key) {
        return this.configs.containsKey(key);
    }

    public void put(String key, String value) {
        this.configs.put(key, value);
    }

    public String getString(String key) {
        if (this.configs.containsKey(key)) {
            return this.configs.get(key);
        }
        return null;
    }

    public int getInt(String key) {
        String val = getString(key);
        try {
            if (TextUtils.isEmpty(val)) {
                return 0;
            }
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public Float getFloat(String key) {
        String val = getString(key);
        try {
            return Float.valueOf(TextUtils.isEmpty(val) ? 0.0f : Float.parseFloat(val));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Float.valueOf(0.0f);
        } catch (Exception e2) {
            e2.printStackTrace();
            return Float.valueOf(0.0f);
        }
    }

    public Double getDouble(String key) {
        String val = getString(key);
        try {
            return Double.valueOf(TextUtils.isEmpty(val) ? 0.0d : Double.parseDouble(val));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Double.valueOf(0.0d);
        } catch (Exception e2) {
            e2.printStackTrace();
            return Double.valueOf(0.0d);
        }
    }

    public Long getLong(String key) {
        String val = getString(key);
        try {
            return Long.valueOf(TextUtils.isEmpty(val) ? 0 : Long.parseLong(val));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0L;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public Boolean getBoolean(String key) {
        try {
            return Boolean.valueOf(Boolean.parseBoolean(getString(key)));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Short getShort(String key) {
        String val = getString(key);
        try {
            return Short.valueOf(TextUtils.isEmpty(val) ? 0 : Short.parseShort(val));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public Byte getByte(String key) {
        String val = getString(key);
        try {
            return Byte.valueOf(TextUtils.isEmpty(val) ? 0 : Byte.parseByte(val));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return (byte) 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return (byte) 0;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String key : this.configs.keySet()) {
            sb.append(key).append("=").append(this.configs.get(key)).append(ShellAdbUtil.COMMAND_LINE_END);
        }
        return sb.toString();
    }
}
