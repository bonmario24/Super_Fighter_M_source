package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.facebook.appevents.AppEventsConstants;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TypeUtils {
    public static boolean compatibleWithJavaBean = false;
    private static ConcurrentMap<String, Class<?>> mappings = new ConcurrentHashMap();
    private static boolean setAccessibleEnable = true;

    static {
        mappings.put("byte", Byte.TYPE);
        mappings.put("short", Short.TYPE);
        mappings.put("int", Integer.TYPE);
        mappings.put("long", Long.TYPE);
        mappings.put("float", Float.TYPE);
        mappings.put("double", Double.TYPE);
        mappings.put("boolean", Boolean.TYPE);
        mappings.put("char", Character.TYPE);
        mappings.put("[byte", byte[].class);
        mappings.put("[short", short[].class);
        mappings.put("[int", int[].class);
        mappings.put("[long", long[].class);
        mappings.put("[float", float[].class);
        mappings.put("[double", double[].class);
        mappings.put("[boolean", boolean[].class);
        mappings.put("[char", char[].class);
        mappings.put(HashMap.class.getName(), HashMap.class);
    }

    public static final String castToString(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    public static final Byte castToByte(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Byte.valueOf(((Number) value).byteValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
            return Byte.valueOf(Byte.parseByte(strVal));
        }
        throw new JSONException("can not cast to byte, value : " + value);
    }

    public static final Character castToChar(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Character) {
            return (Character) value;
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }
            if (strVal.length() == 1) {
                return Character.valueOf(strVal.charAt(0));
            }
            throw new JSONException("can not cast to byte, value : " + value);
        }
        throw new JSONException("can not cast to byte, value : " + value);
    }

    public static final Short castToShort(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Short.valueOf(((Number) value).shortValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
            return Short.valueOf(Short.parseShort(strVal));
        }
        throw new JSONException("can not cast to short, value : " + value);
    }

    public static final BigDecimal castToBigDecimal(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        }
        String strVal = value.toString();
        if (strVal.length() == 0 || "null".equals(strVal)) {
            return null;
        }
        return new BigDecimal(strVal);
    }

    public static final BigInteger castToBigInteger(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigInteger) {
            return (BigInteger) value;
        }
        if ((value instanceof Float) || (value instanceof Double)) {
            return BigInteger.valueOf(((Number) value).longValue());
        }
        String strVal = value.toString();
        if (strVal.length() == 0 || "null".equals(strVal)) {
            return null;
        }
        return new BigInteger(strVal);
    }

    public static final Float castToFloat(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Float.valueOf(((Number) value).floatValue());
        }
        if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
            return Float.valueOf(Float.parseFloat(strVal));
        }
        throw new JSONException("can not cast to float, value : " + value);
    }

    public static final Double castToDouble(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Double.valueOf(((Number) value).doubleValue());
        }
        if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
            return Double.valueOf(Double.parseDouble(strVal));
        }
        throw new JSONException("can not cast to double, value : " + value);
    }

    public static final Date castToDate(Object value) {
        String format;
        if (value == null) {
            return null;
        }
        if (value instanceof Calendar) {
            return ((Calendar) value).getTime();
        }
        if (value instanceof Date) {
            return (Date) value;
        }
        long longValue = -1;
        if (value instanceof Number) {
            longValue = ((Number) value).longValue();
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.indexOf(45) != -1) {
                if (strVal.length() == JSON.DEFFAULT_DATE_FORMAT.length()) {
                    format = JSON.DEFFAULT_DATE_FORMAT;
                } else if (strVal.length() == 10) {
                    format = "yyyy-MM-dd";
                } else if (strVal.length() == "yyyy-MM-dd HH:mm:ss".length()) {
                    format = "yyyy-MM-dd HH:mm:ss";
                } else {
                    format = "yyyy-MM-dd HH:mm:ss.SSS";
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat(format, JSON.defaultLocale);
                dateFormat.setTimeZone(JSON.defaultTimeZone);
                try {
                    return dateFormat.parse(strVal);
                } catch (ParseException e) {
                    throw new JSONException("can not cast to Date, value : " + strVal);
                }
            } else if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            } else {
                longValue = Long.parseLong(strVal);
            }
        }
        if (longValue >= 0) {
            return new Date(longValue);
        }
        throw new JSONException("can not cast to Date, value : " + value);
    }

    public static final Long castToLong(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Long.valueOf(((Number) value).longValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
            try {
                return Long.valueOf(Long.parseLong(strVal));
            } catch (NumberFormatException e) {
                JSONLexer dateParser = new JSONLexer(strVal);
                Calendar calendar = null;
                if (dateParser.scanISO8601DateIfMatch(false)) {
                    calendar = dateParser.getCalendar();
                }
                dateParser.close();
                if (calendar != null) {
                    return Long.valueOf(calendar.getTimeInMillis());
                }
            }
        }
        throw new JSONException("can not cast to long, value : " + value);
    }

    public static final Integer castToInt(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof Number) {
            return Integer.valueOf(((Number) value).intValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
            return Integer.valueOf(Integer.parseInt(strVal));
        }
        throw new JSONException("can not cast to int, value : " + value);
    }

    public static final byte[] castToBytes(Object value) {
        if (value instanceof byte[]) {
            return (byte[]) value;
        }
        if (value instanceof String) {
            return JSONLexer.decodeFast((String) value);
        }
        throw new JSONException("can not cast to int, value : " + value);
    }

    public static final Boolean castToBoolean(Object value) {
        boolean z = true;
        if (value == null) {
            return null;
        }
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof Number) {
            if (((Number) value).intValue() != 1) {
                z = false;
            }
            return Boolean.valueOf(z);
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
            if ("true".equalsIgnoreCase(strVal) || AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(strVal)) {
                return Boolean.TRUE;
            }
            if ("false".equalsIgnoreCase(strVal) || AppEventsConstants.EVENT_PARAM_VALUE_NO.equals(strVal)) {
                return Boolean.FALSE;
            }
        }
        throw new JSONException("can not cast to int, value : " + value);
    }

    public static final <T> T castToJavaBean(Object obj, Class<T> clazz) {
        return cast(obj, clazz, ParserConfig.global);
    }

    public static final <T> T cast(Object obj, Class<T> clazz, ParserConfig mapping) {
        Calendar calendar;
        if (obj == null) {
            return null;
        }
        if (clazz == null) {
            throw new IllegalArgumentException("clazz is null");
        } else if (clazz == obj.getClass()) {
            return obj;
        } else {
            if (!(obj instanceof Map)) {
                if (clazz.isArray()) {
                    if (obj instanceof Collection) {
                        Collection<Object> collection = (Collection) obj;
                        int index = 0;
                        Object array = Array.newInstance(clazz.getComponentType(), collection.size());
                        for (Object item : collection) {
                            Array.set(array, index, cast(item, clazz.getComponentType(), mapping));
                            index++;
                        }
                        return array;
                    } else if (clazz == byte[].class) {
                        return castToBytes(obj);
                    }
                }
                if (clazz.isAssignableFrom(obj.getClass())) {
                    return obj;
                }
                if (clazz == Boolean.TYPE || clazz == Boolean.class) {
                    return castToBoolean(obj);
                }
                if (clazz == Byte.TYPE || clazz == Byte.class) {
                    return castToByte(obj);
                }
                if (clazz == Short.TYPE || clazz == Short.class) {
                    return castToShort(obj);
                }
                if (clazz == Integer.TYPE || clazz == Integer.class) {
                    return castToInt(obj);
                }
                if (clazz == Long.TYPE || clazz == Long.class) {
                    return castToLong(obj);
                }
                if (clazz == Float.TYPE || clazz == Float.class) {
                    return castToFloat(obj);
                }
                if (clazz == Double.TYPE || clazz == Double.class) {
                    return castToDouble(obj);
                }
                if (clazz == String.class) {
                    return castToString(obj);
                }
                if (clazz == BigDecimal.class) {
                    return castToBigDecimal(obj);
                }
                if (clazz == BigInteger.class) {
                    return castToBigInteger(obj);
                }
                if (clazz == Date.class) {
                    return castToDate(obj);
                }
                if (clazz.isEnum()) {
                    return castToEnum(obj, clazz, mapping);
                }
                if (Calendar.class.isAssignableFrom(clazz)) {
                    Date date = castToDate(obj);
                    if (clazz == Calendar.class) {
                        calendar = Calendar.getInstance(JSON.defaultTimeZone, JSON.defaultLocale);
                    } else {
                        try {
                            calendar = clazz.newInstance();
                        } catch (Exception e) {
                            throw new JSONException("can not cast to : " + clazz.getName(), e);
                        }
                    }
                    calendar.setTime(date);
                    return calendar;
                }
                if (obj instanceof String) {
                    String strVal = (String) obj;
                    if (strVal.length() == 0 || "null".equals(strVal)) {
                        return null;
                    }
                    if (clazz == Currency.class) {
                        return Currency.getInstance(strVal);
                    }
                }
                throw new JSONException("can not cast to : " + clazz.getName());
            } else if (clazz == Map.class) {
                return obj;
            } else {
                Map map = (Map) obj;
                if (clazz != Object.class || map.containsKey(JSON.DEFAULT_TYPE_KEY)) {
                    return castToJavaBean((Map) obj, clazz, mapping);
                }
                return obj;
            }
        }
    }

    public static final <T> T castToEnum(Object obj, Class<T> clazz, ParserConfig mapping) {
        try {
            if (obj instanceof String) {
                String name = (String) obj;
                if (name.length() == 0) {
                    return null;
                }
                return Enum.valueOf(clazz, name);
            }
            if (obj instanceof Number) {
                int ordinal = ((Number) obj).intValue();
                Object[] values = clazz.getEnumConstants();
                if (ordinal < values.length) {
                    return values[ordinal];
                }
            }
            throw new JSONException("can not cast to : " + clazz.getName());
        } catch (Exception ex) {
            throw new JSONException("can not cast to : " + clazz.getName(), ex);
        }
    }

    public static final <T> T cast(Object obj, Type type, ParserConfig mapping) {
        if (obj == null) {
            return null;
        }
        if (type instanceof Class) {
            return cast(obj, (Class) type, mapping);
        }
        if (type instanceof ParameterizedType) {
            return cast(obj, (ParameterizedType) type, mapping);
        }
        if (obj instanceof String) {
            String strVal = (String) obj;
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
        }
        if (type instanceof TypeVariable) {
            return obj;
        }
        throw new JSONException("can not cast to : " + type);
    }

    public static final <T> T cast(Object obj, ParameterizedType type, ParserConfig mapping) {
        Collection collection;
        Type rawTye = type.getRawType();
        if (rawTye == Set.class || rawTye == HashSet.class || rawTye == TreeSet.class || rawTye == List.class || rawTye == ArrayList.class) {
            Type itemType = type.getActualTypeArguments()[0];
            if (obj instanceof Iterable) {
                if (rawTye == Set.class || rawTye == HashSet.class) {
                    collection = new HashSet();
                } else if (rawTye == TreeSet.class) {
                    collection = new TreeSet();
                } else {
                    collection = new ArrayList();
                }
                for (Object item : (Iterable) obj) {
                    collection.add(cast(item, itemType, mapping));
                }
                return collection;
            }
        }
        if (rawTye == Map.class || rawTye == HashMap.class) {
            Type keyType = type.getActualTypeArguments()[0];
            Type valueType = type.getActualTypeArguments()[1];
            if (obj instanceof Map) {
                Map map = new HashMap();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    map.put(cast(entry.getKey(), keyType, mapping), cast(entry.getValue(), valueType, mapping));
                }
                return map;
            }
        }
        if (obj instanceof String) {
            String strVal = (String) obj;
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
        }
        if (type.getActualTypeArguments().length == 1 && (type.getActualTypeArguments()[0] instanceof WildcardType)) {
            return cast(obj, rawTye, mapping);
        }
        throw new JSONException("can not cast to : " + type);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.alibaba.fastjson.parser.deserializer.ObjectDeserializer] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> T castToJavaBean(java.util.Map<java.lang.String, java.lang.Object> r17, java.lang.Class<T> r18, com.alibaba.fastjson.parser.ParserConfig r19) {
        /*
            java.lang.Class<java.lang.StackTraceElement> r14 = java.lang.StackTraceElement.class
            r0 = r18
            if (r0 != r14) goto L_0x003c
            java.lang.String r14 = "className"
            r0 = r17
            java.lang.Object r3 = r0.get(r14)     // Catch:{ Exception -> 0x006c }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x006c }
            java.lang.String r14 = "methodName"
            r0 = r17
            java.lang.Object r11 = r0.get(r14)     // Catch:{ Exception -> 0x006c }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ Exception -> 0x006c }
            java.lang.String r14 = "fileName"
            r0 = r17
            java.lang.Object r6 = r0.get(r14)     // Catch:{ Exception -> 0x006c }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x006c }
            java.lang.String r14 = "lineNumber"
            r0 = r17
            java.lang.Object r13 = r0.get(r14)     // Catch:{ Exception -> 0x006c }
            java.lang.Number r13 = (java.lang.Number) r13     // Catch:{ Exception -> 0x006c }
            if (r13 != 0) goto L_0x0037
            r9 = 0
        L_0x0031:
            java.lang.StackTraceElement r14 = new java.lang.StackTraceElement     // Catch:{ Exception -> 0x006c }
            r14.<init>(r3, r11, r6, r9)     // Catch:{ Exception -> 0x006c }
        L_0x0036:
            return r14
        L_0x0037:
            int r9 = r13.intValue()     // Catch:{ Exception -> 0x006c }
            goto L_0x0031
        L_0x003c:
            java.lang.String r14 = "@type"
            r0 = r17
            java.lang.Object r7 = r0.get(r14)     // Catch:{ Exception -> 0x006c }
            boolean r14 = r7 instanceof java.lang.String     // Catch:{ Exception -> 0x006c }
            if (r14 == 0) goto L_0x0088
            r0 = r7
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x006c }
            r2 = r0
            r14 = 0
            java.lang.Class r10 = loadClass(r2, r14)     // Catch:{ Exception -> 0x006c }
            if (r10 != 0) goto L_0x0077
            java.lang.ClassNotFoundException r14 = new java.lang.ClassNotFoundException     // Catch:{ Exception -> 0x006c }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x006c }
            r15.<init>()     // Catch:{ Exception -> 0x006c }
            java.lang.StringBuilder r15 = r15.append(r2)     // Catch:{ Exception -> 0x006c }
            java.lang.String r16 = " not found"
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x006c }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x006c }
            r14.<init>(r15)     // Catch:{ Exception -> 0x006c }
            throw r14     // Catch:{ Exception -> 0x006c }
        L_0x006c:
            r5 = move-exception
            com.alibaba.fastjson.JSONException r14 = new com.alibaba.fastjson.JSONException
            java.lang.String r15 = r5.getMessage()
            r14.<init>(r15, r5)
            throw r14
        L_0x0077:
            r0 = r18
            boolean r14 = r10.equals(r0)     // Catch:{ Exception -> 0x006c }
            if (r14 != 0) goto L_0x0088
            r0 = r17
            r1 = r19
            java.lang.Object r14 = castToJavaBean(r0, r10, r1)     // Catch:{ Exception -> 0x006c }
            goto L_0x0036
        L_0x0088:
            boolean r14 = r18.isInterface()     // Catch:{ Exception -> 0x006c }
            if (r14 == 0) goto L_0x00b5
            r0 = r17
            boolean r14 = r0 instanceof com.alibaba.fastjson.JSONObject     // Catch:{ Exception -> 0x006c }
            if (r14 == 0) goto L_0x00ad
            r0 = r17
            com.alibaba.fastjson.JSONObject r0 = (com.alibaba.fastjson.JSONObject) r0     // Catch:{ Exception -> 0x006c }
            r12 = r0
        L_0x0099:
            java.lang.Thread r14 = java.lang.Thread.currentThread()     // Catch:{ Exception -> 0x006c }
            java.lang.ClassLoader r14 = r14.getContextClassLoader()     // Catch:{ Exception -> 0x006c }
            r15 = 1
            java.lang.Class[] r15 = new java.lang.Class[r15]     // Catch:{ Exception -> 0x006c }
            r16 = 0
            r15[r16] = r18     // Catch:{ Exception -> 0x006c }
            java.lang.Object r14 = java.lang.reflect.Proxy.newProxyInstance(r14, r15, r12)     // Catch:{ Exception -> 0x006c }
            goto L_0x0036
        L_0x00ad:
            com.alibaba.fastjson.JSONObject r12 = new com.alibaba.fastjson.JSONObject     // Catch:{ Exception -> 0x006c }
            r0 = r17
            r12.<init>((java.util.Map<java.lang.String, java.lang.Object>) r0)     // Catch:{ Exception -> 0x006c }
            goto L_0x0099
        L_0x00b5:
            if (r19 != 0) goto L_0x00b9
            com.alibaba.fastjson.parser.ParserConfig r19 = com.alibaba.fastjson.parser.ParserConfig.global     // Catch:{ Exception -> 0x006c }
        L_0x00b9:
            r8 = 0
            r0 = r19
            r1 = r18
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r4 = r0.getDeserializer(r1)     // Catch:{ Exception -> 0x006c }
            boolean r14 = r4 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer     // Catch:{ Exception -> 0x006c }
            if (r14 == 0) goto L_0x00ca
            r0 = r4
            com.alibaba.fastjson.parser.JavaBeanDeserializer r0 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r0     // Catch:{ Exception -> 0x006c }
            r8 = r0
        L_0x00ca:
            if (r8 != 0) goto L_0x00d4
            com.alibaba.fastjson.JSONException r14 = new com.alibaba.fastjson.JSONException     // Catch:{ Exception -> 0x006c }
            java.lang.String r15 = "can not get javaBeanDeserializer"
            r14.<init>(r15)     // Catch:{ Exception -> 0x006c }
            throw r14     // Catch:{ Exception -> 0x006c }
        L_0x00d4:
            r0 = r17
            r1 = r19
            java.lang.Object r14 = r8.createInstance((java.util.Map<java.lang.String, java.lang.Object>) r0, (com.alibaba.fastjson.parser.ParserConfig) r1)     // Catch:{ Exception -> 0x006c }
            goto L_0x0036
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.TypeUtils.castToJavaBean(java.util.Map, java.lang.Class, com.alibaba.fastjson.parser.ParserConfig):java.lang.Object");
    }

    public static Class<?> loadClass(String className, ClassLoader classLoader) {
        if (className == null || className.length() == 0) {
            return null;
        }
        Class<?> clazz = (Class) mappings.get(className);
        if (clazz != null) {
            return clazz;
        }
        if (className.charAt(0) == '[') {
            return Array.newInstance(loadClass(className.substring(1), classLoader), 0).getClass();
        }
        if (className.startsWith("L") && className.endsWith(";")) {
            return loadClass(className.substring(1, className.length() - 1), classLoader);
        }
        if (classLoader != null) {
            try {
                clazz = classLoader.loadClass(className);
                mappings.put(className, clazz);
                return clazz;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null) {
                Class<?> clazz2 = contextClassLoader.loadClass(className);
                mappings.put(className, clazz2);
                return clazz2;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            clazz = Class.forName(className);
            mappings.put(className, clazz);
            return clazz;
        } catch (Exception e3) {
            e3.printStackTrace();
            return clazz;
        }
    }

    /* JADX WARNING: type inference failed for: r39v0, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0201  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.alibaba.fastjson.util.FieldInfo> computeGetters(java.lang.Class<?> r39, int r40, boolean r41, com.alibaba.fastjson.annotation.JSONType r42, java.util.Map<java.lang.String, java.lang.String> r43, boolean r44, boolean r45, boolean r46) {
        /*
            java.util.LinkedHashMap r32 = new java.util.LinkedHashMap
            r32.<init>()
            java.lang.reflect.Field[] r28 = r39.getDeclaredFields()
            if (r41 != 0) goto L_0x02e2
            java.lang.reflect.Method[] r37 = r39.getMethods()
            r0 = r37
            int r0 = r0.length
            r38 = r0
            r3 = 0
            r36 = r3
        L_0x0017:
            r0 = r36
            r1 = r38
            if (r0 >= r1) goto L_0x02e2
            r5 = r37[r36]
            java.lang.String r34 = r5.getName()
            r9 = 0
            r10 = 0
            int r3 = r5.getModifiers()
            r3 = r3 & 8
            if (r3 != 0) goto L_0x006a
            java.lang.Class r3 = r5.getReturnType()
            java.lang.Class r6 = java.lang.Void.TYPE
            boolean r3 = r3.equals(r6)
            if (r3 != 0) goto L_0x006a
            java.lang.Class[] r3 = r5.getParameterTypes()
            int r3 = r3.length
            if (r3 != 0) goto L_0x006a
            java.lang.Class r3 = r5.getReturnType()
            java.lang.Class<java.lang.ClassLoader> r6 = java.lang.ClassLoader.class
            if (r3 == r6) goto L_0x006a
            java.lang.Class r3 = r5.getDeclaringClass()
            java.lang.Class<java.lang.Object> r6 = java.lang.Object.class
            if (r3 == r6) goto L_0x006a
            java.lang.String r3 = "getMetaClass"
            r0 = r34
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x006f
            java.lang.Class r3 = r5.getReturnType()
            java.lang.String r3 = r3.getName()
            java.lang.String r6 = "groovy.lang.MetaClass"
            boolean r3 = r3.equals(r6)
            if (r3 == 0) goto L_0x006f
        L_0x006a:
            int r3 = r36 + 1
            r36 = r3
            goto L_0x0017
        L_0x006f:
            if (r45 == 0) goto L_0x00ca
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r3 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r3 = r5.getAnnotation(r3)
            com.alibaba.fastjson.annotation.JSONField r3 = (com.alibaba.fastjson.annotation.JSONField) r3
            r11 = r3
        L_0x007a:
            if (r11 != 0) goto L_0x0084
            if (r45 == 0) goto L_0x0084
            r0 = r39
            com.alibaba.fastjson.annotation.JSONField r11 = getSupperMethodAnnotation(r0, r5)
        L_0x0084:
            if (r11 == 0) goto L_0x00cc
            boolean r3 = r11.serialize()
            if (r3 == 0) goto L_0x006a
            int r9 = r11.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r3 = r11.serialzeFeatures()
            int r10 = com.alibaba.fastjson.serializer.SerializerFeature.m199of(r3)
            java.lang.String r3 = r11.name()
            int r3 = r3.length()
            if (r3 == 0) goto L_0x00cc
            java.lang.String r4 = r11.name()
            if (r43 == 0) goto L_0x00b2
            r0 = r43
            java.lang.Object r4 = r0.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x006a
        L_0x00b2:
            r0 = r39
            r1 = r40
            setAccessible(r0, r5, r1)
            com.alibaba.fastjson.util.FieldInfo r3 = new com.alibaba.fastjson.util.FieldInfo
            r6 = 0
            r8 = 0
            r12 = 0
            r13 = 1
            r7 = r39
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r0 = r32
            r0.put(r4, r3)
            goto L_0x006a
        L_0x00ca:
            r11 = 0
            goto L_0x007a
        L_0x00cc:
            java.lang.String r3 = "get"
            r0 = r34
            boolean r3 = r0.startsWith(r3)
            if (r3 == 0) goto L_0x017f
            int r3 = r34.length()
            r6 = 4
            if (r3 < r6) goto L_0x006a
            java.lang.String r3 = "getClass"
            r0 = r34
            boolean r3 = r0.equals(r3)
            if (r3 != 0) goto L_0x006a
            r3 = 3
            r0 = r34
            char r25 = r0.charAt(r3)
            boolean r3 = java.lang.Character.isUpperCase(r25)
            if (r3 == 0) goto L_0x0258
            boolean r3 = compatibleWithJavaBean
            if (r3 == 0) goto L_0x0233
            r3 = 3
            r0 = r34
            java.lang.String r3 = r0.substring(r3)
            java.lang.String r4 = decapitalize(r3)
        L_0x0103:
            r0 = r39
            r1 = r42
            boolean r3 = isJSONTypeIgnore(r0, r1, r4)
            if (r3 != 0) goto L_0x006a
            r0 = r39
            r1 = r28
            java.lang.reflect.Field r15 = getField(r0, r4, r1)
            r21 = 0
            if (r15 == 0) goto L_0x0431
            if (r45 == 0) goto L_0x0297
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r3 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r3 = r15.getAnnotation(r3)
            com.alibaba.fastjson.annotation.JSONField r3 = (com.alibaba.fastjson.annotation.JSONField) r3
            r21 = r3
        L_0x0125:
            if (r21 == 0) goto L_0x0431
            boolean r3 = r21.serialize()
            if (r3 == 0) goto L_0x006a
            int r9 = r21.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r3 = r21.serialzeFeatures()
            int r10 = com.alibaba.fastjson.serializer.SerializerFeature.m199of(r3)
            java.lang.String r3 = r21.name()
            int r3 = r3.length()
            if (r3 == 0) goto L_0x0431
            java.lang.String r4 = r21.name()
            if (r43 == 0) goto L_0x0153
            r0 = r43
            java.lang.Object r4 = r0.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x006a
        L_0x0153:
            r13 = r4
        L_0x0154:
            if (r43 == 0) goto L_0x0161
            r0 = r43
            java.lang.Object r4 = r0.get(r13)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x006a
            r13 = r4
        L_0x0161:
            r0 = r39
            r1 = r40
            setAccessible(r0, r5, r1)
            com.alibaba.fastjson.util.FieldInfo r12 = new com.alibaba.fastjson.util.FieldInfo
            r17 = 0
            r14 = r5
            r16 = r39
            r18 = r9
            r19 = r10
            r20 = r11
            r22 = r46
            r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            r0 = r32
            r0.put(r13, r12)
        L_0x017f:
            java.lang.String r3 = "is"
            r0 = r34
            boolean r3 = r0.startsWith(r3)
            if (r3 == 0) goto L_0x006a
            int r3 = r34.length()
            r6 = 3
            if (r3 < r6) goto L_0x006a
            r3 = 2
            r0 = r34
            char r24 = r0.charAt(r3)
            boolean r3 = java.lang.Character.isUpperCase(r24)
            if (r3 == 0) goto L_0x02c0
            boolean r3 = compatibleWithJavaBean
            if (r3 == 0) goto L_0x029b
            r3 = 2
            r0 = r34
            java.lang.String r3 = r0.substring(r3)
            java.lang.String r4 = decapitalize(r3)
        L_0x01ac:
            r0 = r39
            r1 = r28
            java.lang.reflect.Field r15 = getField(r0, r4, r1)
            if (r15 != 0) goto L_0x01c0
            r0 = r39
            r1 = r34
            r2 = r28
            java.lang.reflect.Field r15 = getField(r0, r1, r2)
        L_0x01c0:
            r21 = 0
            if (r15 == 0) goto L_0x042e
            if (r45 == 0) goto L_0x02de
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r3 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r3 = r15.getAnnotation(r3)
            com.alibaba.fastjson.annotation.JSONField r3 = (com.alibaba.fastjson.annotation.JSONField) r3
            r21 = r3
        L_0x01d0:
            if (r21 == 0) goto L_0x042e
            boolean r3 = r21.serialize()
            if (r3 == 0) goto L_0x006a
            int r9 = r21.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r3 = r21.serialzeFeatures()
            int r10 = com.alibaba.fastjson.serializer.SerializerFeature.m199of(r3)
            java.lang.String r3 = r21.name()
            int r3 = r3.length()
            if (r3 == 0) goto L_0x042e
            java.lang.String r4 = r21.name()
            if (r43 == 0) goto L_0x01fe
            r0 = r43
            java.lang.Object r4 = r0.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x006a
        L_0x01fe:
            r13 = r4
        L_0x01ff:
            if (r43 == 0) goto L_0x020c
            r0 = r43
            java.lang.Object r4 = r0.get(r13)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x006a
            r13 = r4
        L_0x020c:
            r0 = r39
            r1 = r40
            setAccessible(r0, r15, r1)
            r0 = r39
            r1 = r40
            setAccessible(r0, r5, r1)
            com.alibaba.fastjson.util.FieldInfo r12 = new com.alibaba.fastjson.util.FieldInfo
            r17 = 0
            r14 = r5
            r16 = r39
            r18 = r9
            r19 = r10
            r20 = r11
            r22 = r46
            r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            r0 = r32
            r0.put(r13, r12)
            goto L_0x006a
        L_0x0233:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r6 = 3
            r0 = r34
            char r6 = r0.charAt(r6)
            char r6 = java.lang.Character.toLowerCase(r6)
            java.lang.StringBuilder r3 = r3.append(r6)
            r6 = 4
            r0 = r34
            java.lang.String r6 = r0.substring(r6)
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r4 = r3.toString()
            goto L_0x0103
        L_0x0258:
            r3 = 95
            r0 = r25
            if (r0 != r3) goto L_0x0267
            r3 = 4
            r0 = r34
            java.lang.String r4 = r0.substring(r3)
            goto L_0x0103
        L_0x0267:
            r3 = 102(0x66, float:1.43E-43)
            r0 = r25
            if (r0 != r3) goto L_0x0276
            r3 = 3
            r0 = r34
            java.lang.String r4 = r0.substring(r3)
            goto L_0x0103
        L_0x0276:
            int r3 = r34.length()
            r6 = 5
            if (r3 < r6) goto L_0x006a
            r3 = 4
            r0 = r34
            char r3 = r0.charAt(r3)
            boolean r3 = java.lang.Character.isUpperCase(r3)
            if (r3 == 0) goto L_0x006a
            r3 = 3
            r0 = r34
            java.lang.String r3 = r0.substring(r3)
            java.lang.String r4 = decapitalize(r3)
            goto L_0x0103
        L_0x0297:
            r21 = 0
            goto L_0x0125
        L_0x029b:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r6 = 2
            r0 = r34
            char r6 = r0.charAt(r6)
            char r6 = java.lang.Character.toLowerCase(r6)
            java.lang.StringBuilder r3 = r3.append(r6)
            r6 = 3
            r0 = r34
            java.lang.String r6 = r0.substring(r6)
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r4 = r3.toString()
            goto L_0x01ac
        L_0x02c0:
            r3 = 95
            r0 = r24
            if (r0 != r3) goto L_0x02cf
            r3 = 3
            r0 = r34
            java.lang.String r4 = r0.substring(r3)
            goto L_0x01ac
        L_0x02cf:
            r3 = 102(0x66, float:1.43E-43)
            r0 = r24
            if (r0 != r3) goto L_0x006a
            r3 = 2
            r0 = r34
            java.lang.String r4 = r0.substring(r3)
            goto L_0x01ac
        L_0x02de:
            r21 = 0
            goto L_0x01d0
        L_0x02e2:
            java.util.ArrayList r26 = new java.util.ArrayList
            r0 = r28
            int r3 = r0.length
            r0 = r26
            r0.<init>(r3)
            r0 = r28
            int r6 = r0.length
            r3 = 0
        L_0x02f0:
            if (r3 >= r6) goto L_0x030f
            r29 = r28[r3]
            int r7 = r29.getModifiers()
            r7 = r7 & 8
            if (r7 == 0) goto L_0x02ff
        L_0x02fc:
            int r3 = r3 + 1
            goto L_0x02f0
        L_0x02ff:
            int r7 = r29.getModifiers()
            r7 = r7 & 1
            if (r7 == 0) goto L_0x02fc
            r0 = r26
            r1 = r29
            r0.add(r1)
            goto L_0x02fc
        L_0x030f:
            java.lang.Class r23 = r39.getSuperclass()
        L_0x0313:
            if (r23 == 0) goto L_0x0345
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            r0 = r23
            if (r0 == r3) goto L_0x0345
            java.lang.reflect.Field[] r6 = r23.getDeclaredFields()
            int r7 = r6.length
            r3 = 0
        L_0x0321:
            if (r3 >= r7) goto L_0x0340
            r29 = r6[r3]
            int r8 = r29.getModifiers()
            r8 = r8 & 8
            if (r8 == 0) goto L_0x0330
        L_0x032d:
            int r3 = r3 + 1
            goto L_0x0321
        L_0x0330:
            int r8 = r29.getModifiers()
            r8 = r8 & 1
            if (r8 == 0) goto L_0x032d
            r0 = r26
            r1 = r29
            r0.add(r1)
            goto L_0x032d
        L_0x0340:
            java.lang.Class r23 = r23.getSuperclass()
            goto L_0x0313
        L_0x0345:
            java.util.Iterator r6 = r26.iterator()
        L_0x0349:
            boolean r3 = r6.hasNext()
            if (r3 == 0) goto L_0x03c0
            java.lang.Object r15 = r6.next()
            java.lang.reflect.Field r15 = (java.lang.reflect.Field) r15
            if (r45 == 0) goto L_0x03bd
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r3 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r3 = r15.getAnnotation(r3)
            com.alibaba.fastjson.annotation.JSONField r3 = (com.alibaba.fastjson.annotation.JSONField) r3
            r21 = r3
        L_0x0361:
            r9 = 0
            r10 = 0
            java.lang.String r4 = r15.getName()
            if (r21 == 0) goto L_0x0389
            boolean r3 = r21.serialize()
            if (r3 == 0) goto L_0x0349
            int r9 = r21.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r3 = r21.serialzeFeatures()
            int r10 = com.alibaba.fastjson.serializer.SerializerFeature.m199of(r3)
            java.lang.String r3 = r21.name()
            int r3 = r3.length()
            if (r3 == 0) goto L_0x0389
            java.lang.String r4 = r21.name()
        L_0x0389:
            if (r43 == 0) goto L_0x0395
            r0 = r43
            java.lang.Object r4 = r0.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x0349
        L_0x0395:
            r0 = r32
            boolean r3 = r0.containsKey(r4)
            if (r3 != 0) goto L_0x0349
            r0 = r39
            r1 = r40
            setAccessible(r0, r15, r1)
            com.alibaba.fastjson.util.FieldInfo r12 = new com.alibaba.fastjson.util.FieldInfo
            r14 = 0
            r17 = 0
            r20 = 0
            r13 = r4
            r16 = r39
            r18 = r9
            r19 = r10
            r22 = r46
            r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            r0 = r32
            r0.put(r4, r12)
            goto L_0x0349
        L_0x03bd:
            r21 = 0
            goto L_0x0361
        L_0x03c0:
            java.util.ArrayList r31 = new java.util.ArrayList
            r31.<init>()
            r27 = 0
            r35 = 0
            if (r42 == 0) goto L_0x03ec
            java.lang.String[] r35 = r42.orders()
            if (r35 == 0) goto L_0x0409
            r0 = r35
            int r3 = r0.length
            int r6 = r32.size()
            if (r3 != r6) goto L_0x0409
            r27 = 1
            r0 = r35
            int r6 = r0.length
            r3 = 0
        L_0x03e0:
            if (r3 >= r6) goto L_0x03ec
            r33 = r35[r3]
            boolean r7 = r32.containsKey(r33)
            if (r7 != 0) goto L_0x0406
            r27 = 0
        L_0x03ec:
            if (r27 == 0) goto L_0x040c
            r0 = r35
            int r6 = r0.length
            r3 = 0
        L_0x03f2:
            if (r3 >= r6) goto L_0x042d
            r33 = r35[r3]
            java.lang.Object r30 = r32.get(r33)
            com.alibaba.fastjson.util.FieldInfo r30 = (com.alibaba.fastjson.util.FieldInfo) r30
            r0 = r31
            r1 = r30
            r0.add(r1)
            int r3 = r3 + 1
            goto L_0x03f2
        L_0x0406:
            int r3 = r3 + 1
            goto L_0x03e0
        L_0x0409:
            r27 = 0
            goto L_0x03ec
        L_0x040c:
            java.util.Collection r3 = r32.values()
            java.util.Iterator r3 = r3.iterator()
        L_0x0414:
            boolean r6 = r3.hasNext()
            if (r6 == 0) goto L_0x0428
            java.lang.Object r30 = r3.next()
            com.alibaba.fastjson.util.FieldInfo r30 = (com.alibaba.fastjson.util.FieldInfo) r30
            r0 = r31
            r1 = r30
            r0.add(r1)
            goto L_0x0414
        L_0x0428:
            if (r44 == 0) goto L_0x042d
            java.util.Collections.sort(r31)
        L_0x042d:
            return r31
        L_0x042e:
            r13 = r4
            goto L_0x01ff
        L_0x0431:
            r13 = r4
            goto L_0x0154
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.TypeUtils.computeGetters(java.lang.Class, int, boolean, com.alibaba.fastjson.annotation.JSONType, java.util.Map, boolean, boolean, boolean):java.util.List");
    }

    public static JSONField getSupperMethodAnnotation(Class<?> clazz, Method method) {
        JSONField annotation;
        for (Class<?> interfaceClass : clazz.getInterfaces()) {
            for (Method interfaceMethod : interfaceClass.getMethods()) {
                if (interfaceMethod.getName().equals(method.getName())) {
                    Class<?>[] interfaceParameterTypes = interfaceMethod.getParameterTypes();
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (interfaceParameterTypes.length == parameterTypes.length) {
                        boolean match = true;
                        int i = 0;
                        while (true) {
                            if (i >= interfaceParameterTypes.length) {
                                break;
                            } else if (!interfaceParameterTypes[i].equals(parameterTypes[i])) {
                                match = false;
                                break;
                            } else {
                                i++;
                            }
                        }
                        if (match && (annotation = (JSONField) interfaceMethod.getAnnotation(JSONField.class)) != null) {
                            return annotation;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    private static boolean isJSONTypeIgnore(Class<?> clazz, JSONType jsonType, String propertyName) {
        if (!(jsonType == null || jsonType.ignores() == null)) {
            for (String item : jsonType.ignores()) {
                if (propertyName.equalsIgnoreCase(item)) {
                    return true;
                }
            }
        }
        Class<? super Object> superclass = clazz.getSuperclass();
        return (superclass == Object.class || superclass == null || !isJSONTypeIgnore(superclass, (JSONType) superclass.getAnnotation(JSONType.class), propertyName)) ? false : true;
    }

    public static boolean isGenericParamType(Type type) {
        if (type instanceof ParameterizedType) {
            return true;
        }
        if (!(type instanceof Class)) {
            return false;
        }
        Type superType = ((Class) type).getGenericSuperclass();
        if (superType == Object.class || !isGenericParamType(superType)) {
            return false;
        }
        return true;
    }

    public static Type getGenericParamType(Type type) {
        return type instanceof Class ? getGenericParamType(((Class) type).getGenericSuperclass()) : type;
    }

    public static Class<?> getClass(Type type) {
        if (type.getClass() == Class.class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        }
        if (type instanceof TypeVariable) {
            return (Class) ((TypeVariable) type).getBounds()[0];
        }
        return Object.class;
    }

    public static String decapitalize(String name) {
        if (name == null || name.length() == 0) {
            return name;
        }
        if (name.length() > 1 && Character.isUpperCase(name.charAt(1)) && Character.isUpperCase(name.charAt(0))) {
            return name;
        }
        char[] chars = name.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }

    public static boolean setAccessible(Class<?> clazz, Member member, int classMofifiers) {
        if (member == null || !setAccessibleEnable) {
            return false;
        }
        Class<? super Object> superclass = clazz.getSuperclass();
        if ((superclass == null || superclass == Object.class) && (member.getModifiers() & 1) != 0 && (classMofifiers & 1) != 0) {
            return false;
        }
        try {
            ((AccessibleObject) member).setAccessible(true);
            return true;
        } catch (AccessControlException e) {
            setAccessibleEnable = false;
            return false;
        }
    }

    public static Field getField(Class<?> clazz, String fieldName, Field[] declaredFields) {
        Field field = getField0(clazz, fieldName, declaredFields);
        if (field == null) {
            field = getField0(clazz, "_" + fieldName, declaredFields);
        }
        if (field == null) {
            field = getField0(clazz, "m_" + fieldName, declaredFields);
        }
        if (field == null) {
            return getField0(clazz, "m" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), declaredFields);
        }
        return field;
    }

    private static Field getField0(Class<?> clazz, String fieldName, Field[] declaredFields) {
        for (Field item : declaredFields) {
            if (fieldName.equals(item.getName())) {
                return item;
            }
        }
        Class<? super Object> superclass = clazz.getSuperclass();
        return (superclass == null || superclass == Object.class) ? null : getField(superclass, fieldName, superclass.getDeclaredFields());
    }
}
