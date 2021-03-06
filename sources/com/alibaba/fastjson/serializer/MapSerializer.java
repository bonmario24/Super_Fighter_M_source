package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public final class MapSerializer implements ObjectSerializer {
    /* JADX INFO: finally extract failed */
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        Map<?, ?> map = (Map) object;
        Class<?> mapClass = map.getClass();
        boolean containsKey = (mapClass == JSONObject.class || mapClass == HashMap.class || mapClass == LinkedHashMap.class) && map.containsKey(JSON.DEFAULT_TYPE_KEY);
        if ((out.features & SerializerFeature.SortField.mask) != 0 && !(map instanceof SortedMap) && !(map instanceof LinkedHashMap)) {
            try {
                map = new TreeMap<>(map);
            } catch (Exception e) {
            }
        }
        if (serializer.references == null || !serializer.references.containsKey(object)) {
            SerialContext parent = serializer.context;
            serializer.setContext(parent, object, fieldName, 0);
            try {
                out.write(123);
                serializer.incrementIndent();
                Class<?> preClazz = null;
                ObjectSerializer preWriter = null;
                boolean first = true;
                if ((out.features & SerializerFeature.WriteClassName.mask) != 0 && !containsKey) {
                    out.writeFieldName(serializer.config.typeKey, false);
                    out.writeString(object.getClass().getName());
                    first = false;
                }
                for (Map.Entry entry : map.entrySet()) {
                    Object value = entry.getValue();
                    Object entryKey = entry.getKey();
                    if (serializer.applyName(object, entryKey) && serializer.apply(object, entryKey, value)) {
                        Object entryKey2 = serializer.processKey(object, entryKey, value);
                        Object value2 = JSONSerializer.processValue(serializer, object, entryKey2, value);
                        if (value2 != null || (out.features & SerializerFeature.WriteMapNullValue.mask) != 0) {
                            if (entryKey2 instanceof String) {
                                String key = (String) entryKey2;
                                if (!first) {
                                    out.write(44);
                                }
                                if ((out.features & SerializerFeature.PrettyFormat.mask) != 0) {
                                    serializer.println();
                                }
                                out.writeFieldName(key, true);
                            } else {
                                if (!first) {
                                    out.write(44);
                                }
                                if ((out.features & SerializerFeature.BrowserCompatible.mask) == 0 && (out.features & SerializerFeature.WriteNonStringKeyAsString.mask) == 0) {
                                    serializer.write(entryKey2);
                                } else {
                                    serializer.write(JSON.toJSONString(entryKey2));
                                }
                                out.write(58);
                            }
                            first = false;
                            if (value2 == null) {
                                out.writeNull();
                            } else {
                                Class<?> clazz = value2.getClass();
                                if (clazz == preClazz) {
                                    preWriter.write(serializer, value2, entryKey2, (Type) null);
                                } else {
                                    preClazz = clazz;
                                    preWriter = serializer.config.get(clazz);
                                    preWriter.write(serializer, value2, entryKey2, (Type) null);
                                }
                            }
                        }
                    }
                }
                serializer.context = parent;
                serializer.decrementIdent();
                if ((out.features & SerializerFeature.PrettyFormat.mask) != 0 && map.size() > 0) {
                    serializer.println();
                }
                out.write(125);
            } catch (Throwable th) {
                serializer.context = parent;
                throw th;
            }
        } else {
            serializer.writeReference(object);
        }
    }
}
