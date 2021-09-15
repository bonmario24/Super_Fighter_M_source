package com.thinkfly.star;

import com.thinkfly.plugins.coludladder.log.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GlobalParams {
    private HashMap<String, Object> _map = new HashMap<>();

    public GlobalParams put(String key, Object value) {
        if (value != null) {
            Log.m665w(Log.TAG, "GlobalParams  put " + key + "  " + value);
            this._map.put(key, value);
        }
        return this;
    }

    public GlobalParams putAll(Map<String, Object> map) {
        if (map != null && !map.isEmpty()) {
            this._map.putAll(map);
        }
        return this;
    }

    public boolean isEmpty() {
        return this._map == null || this._map.isEmpty();
    }

    public Set<String> keySet() {
        return this._map.keySet();
    }

    public Object get(String key) {
        return this._map.get(key);
    }
}
