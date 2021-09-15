package com.alibaba.fastjson.util;

import com.alibaba.fastjson.annotation.JSONField;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class FieldInfo implements Comparable<FieldInfo> {
    public final Class<?> declaringClass;
    public final Field field;
    public final boolean fieldAccess;
    private final JSONField fieldAnnotation;
    public final Class<?> fieldClass;
    public final boolean fieldTransient;
    public final Type fieldType;
    public final boolean getOnly;
    public final boolean isEnum;
    public final Method method;
    private final JSONField methodAnnotation;
    public final String name;
    public final char[] name_chars;
    private int ordinal = 0;

    public FieldInfo(String name2, Class<?> declaringClass2, Class<?> fieldClass2, Type fieldType2, Field field2, int ordinal2, int serialzeFeatures) {
        boolean z;
        this.name = name2;
        this.declaringClass = declaringClass2;
        this.fieldClass = fieldClass2;
        this.fieldType = fieldType2;
        this.method = null;
        this.field = field2;
        this.ordinal = ordinal2;
        this.isEnum = fieldClass2.isEnum();
        this.fieldAnnotation = null;
        this.methodAnnotation = null;
        if (field2 != null) {
            int modifiers = field2.getModifiers();
            if ((modifiers & 1) != 0 || this.method == null) {
                z = true;
            } else {
                z = false;
            }
            this.fieldAccess = z;
            this.fieldTransient = Modifier.isTransient(modifiers);
        } else {
            this.fieldAccess = false;
            this.fieldTransient = false;
        }
        this.getOnly = false;
        int nameLen = this.name.length();
        this.name_chars = new char[(nameLen + 3)];
        this.name.getChars(0, this.name.length(), this.name_chars, 1);
        this.name_chars[0] = '\"';
        this.name_chars[nameLen + 1] = '\"';
        this.name_chars[nameLen + 2] = ':';
    }

    public FieldInfo(String name2, Method method2, Field field2, Class<?> clazz, Type type, int ordinal2, int serialzeFeatures, JSONField methodAnnotation2, JSONField fieldAnnotation2, boolean fieldGenericSupport) {
        Class<?> fieldClass2;
        Type fieldType2;
        Type fieldType3;
        Type genericFieldType;
        Type fieldType4;
        this.name = name2;
        this.method = method2;
        this.field = field2;
        this.ordinal = ordinal2;
        this.methodAnnotation = methodAnnotation2;
        this.fieldAnnotation = fieldAnnotation2;
        if (field2 != null) {
            int modifiers = field2.getModifiers();
            this.fieldAccess = method2 == null || ((modifiers & 1) != 0 && method2.getReturnType() == field2.getType());
            this.fieldTransient = (modifiers & 128) != 0;
        } else {
            this.fieldAccess = false;
            this.fieldTransient = false;
        }
        int nameLen = this.name.length();
        this.name_chars = new char[(nameLen + 3)];
        this.name.getChars(0, this.name.length(), this.name_chars, 1);
        this.name_chars[0] = '\"';
        this.name_chars[nameLen + 1] = '\"';
        this.name_chars[nameLen + 2] = ':';
        if (method2 != null) {
            Class<?>[] parameterTypes = method2.getParameterTypes();
            if (parameterTypes.length == 1) {
                fieldClass2 = parameterTypes[0];
                if (fieldClass2 == Class.class || fieldClass2 == String.class || fieldClass2.isPrimitive()) {
                    fieldType2 = fieldClass2;
                } else {
                    fieldType2 = fieldGenericSupport ? method2.getGenericParameterTypes()[0] : fieldClass2;
                }
                this.getOnly = false;
            } else {
                fieldClass2 = method2.getReturnType();
                if (fieldClass2 == Class.class) {
                    fieldType4 = fieldClass2;
                } else {
                    fieldType4 = fieldGenericSupport ? method2.getGenericReturnType() : fieldClass2;
                }
                this.getOnly = true;
            }
            this.declaringClass = method2.getDeclaringClass();
        } else {
            fieldClass2 = field2.getType();
            if (fieldClass2.isPrimitive() || fieldClass2 == String.class || fieldClass2.isEnum()) {
                fieldType3 = fieldClass2;
            } else {
                fieldType3 = fieldGenericSupport ? field2.getGenericType() : fieldClass2;
            }
            this.declaringClass = field2.getDeclaringClass();
            this.getOnly = Modifier.isFinal(field2.getModifiers());
        }
        if (clazz == null || fieldClass2 != Object.class || !(fieldType2 instanceof TypeVariable) || (genericFieldType = getInheritGenericType(clazz, (TypeVariable) fieldType2)) == null) {
            Type genericFieldType2 = fieldType2;
            if (!(fieldType2 instanceof Class)) {
                genericFieldType2 = getFieldType(clazz, type == null ? clazz : type, fieldType2);
                if (genericFieldType2 != fieldType2) {
                    if (genericFieldType2 instanceof ParameterizedType) {
                        fieldClass2 = TypeUtils.getClass(genericFieldType2);
                    } else if (genericFieldType2 instanceof Class) {
                        fieldClass2 = TypeUtils.getClass(genericFieldType2);
                    }
                }
            }
            this.fieldType = genericFieldType2;
            this.fieldClass = fieldClass2;
            this.isEnum = !fieldClass2.isArray() && fieldClass2.isEnum();
            return;
        }
        this.fieldClass = TypeUtils.getClass(genericFieldType);
        this.fieldType = genericFieldType;
        this.isEnum = fieldClass2.isEnum();
    }

    public static Type getFieldType(Class<?> clazz, Type type, Type fieldType2) {
        if (clazz == null || type == null) {
            Type type2 = fieldType2;
            return fieldType2;
        } else if (fieldType2 instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) fieldType2).getGenericComponentType();
            Type componentTypeX = getFieldType(clazz, type, componentType);
            if (componentType != componentTypeX) {
                Type type3 = fieldType2;
                return Array.newInstance(TypeUtils.getClass(componentTypeX), 0).getClass();
            }
            return fieldType2;
        } else if (!TypeUtils.isGenericParamType(type)) {
            Type type4 = fieldType2;
            return fieldType2;
        } else {
            if (fieldType2 instanceof TypeVariable) {
                ParameterizedType paramType = (ParameterizedType) TypeUtils.getGenericParamType(type);
                Class<?> parameterizedClass = TypeUtils.getClass(paramType);
                TypeVariable<?> typeVar = (TypeVariable) fieldType2;
                for (int i = 0; i < parameterizedClass.getTypeParameters().length; i++) {
                    if (parameterizedClass.getTypeParameters()[i].getName().equals(typeVar.getName())) {
                        Type fieldType3 = paramType.getActualTypeArguments()[i];
                        Type type5 = fieldType3;
                        return fieldType3;
                    }
                }
            }
            if (fieldType2 instanceof ParameterizedType) {
                ParameterizedType parameterizedFieldType = (ParameterizedType) fieldType2;
                Type[] arguments = parameterizedFieldType.getActualTypeArguments();
                boolean changed = false;
                TypeVariable<?>[] typeVariables = null;
                Type[] actualTypes = null;
                ParameterizedType paramType2 = null;
                if (type instanceof ParameterizedType) {
                    paramType2 = (ParameterizedType) type;
                    typeVariables = clazz.getTypeParameters();
                } else if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
                    paramType2 = (ParameterizedType) clazz.getGenericSuperclass();
                    typeVariables = clazz.getSuperclass().getTypeParameters();
                }
                for (int i2 = 0; i2 < arguments.length && paramType2 != null; i2++) {
                    Type feildTypeArguement = arguments[i2];
                    if (feildTypeArguement instanceof TypeVariable) {
                        TypeVariable<?> typeVar2 = (TypeVariable) feildTypeArguement;
                        for (int j = 0; j < typeVariables.length; j++) {
                            if (typeVariables[j].getName().equals(typeVar2.getName())) {
                                if (actualTypes == null) {
                                    actualTypes = paramType2.getActualTypeArguments();
                                }
                                arguments[i2] = actualTypes[j];
                                changed = true;
                            }
                        }
                    }
                }
                if (changed) {
                    ParameterizedTypeImpl parameterizedTypeImpl = new ParameterizedTypeImpl(arguments, parameterizedFieldType.getOwnerType(), parameterizedFieldType.getRawType());
                    ParameterizedTypeImpl parameterizedTypeImpl2 = parameterizedTypeImpl;
                    return parameterizedTypeImpl;
                }
            }
            return fieldType2;
        }
    }

    public static Type getInheritGenericType(Class<?> clazz, TypeVariable<?> typeVariable) {
        Type type;
        GenericDeclaration gd = typeVariable.getGenericDeclaration();
        do {
            type = clazz.getGenericSuperclass();
            if (type == null) {
                return null;
            }
            if (type instanceof ParameterizedType) {
                ParameterizedType ptype = (ParameterizedType) type;
                if (ptype.getRawType() == gd) {
                    TypeVariable<?>[] tvs = gd.getTypeParameters();
                    Type[] types = ptype.getActualTypeArguments();
                    for (int i = 0; i < tvs.length; i++) {
                        if (tvs[i] == typeVariable) {
                            return types[i];
                        }
                    }
                    return null;
                }
            }
            clazz = TypeUtils.getClass(type);
        } while (type != null);
        return null;
    }

    public String toString() {
        return this.name;
    }

    public int compareTo(FieldInfo o) {
        if (this.ordinal < o.ordinal) {
            return -1;
        }
        if (this.ordinal > o.ordinal) {
            return 1;
        }
        return this.name.compareTo(o.name);
    }

    public boolean equals(FieldInfo o) {
        if (o == this || compareTo(o) == 0) {
            return true;
        }
        return false;
    }

    public JSONField getAnnotation() {
        if (this.fieldAnnotation != null) {
            return this.fieldAnnotation;
        }
        return this.methodAnnotation;
    }

    public Object get(Object javaObject) throws IllegalAccessException, InvocationTargetException {
        if (this.fieldAccess) {
            return this.field.get(javaObject);
        }
        return this.method.invoke(javaObject, new Object[0]);
    }

    public void set(Object javaObject, Object value) throws IllegalAccessException, InvocationTargetException {
        if (this.method != null) {
            this.method.invoke(javaObject, new Object[]{value});
            return;
        }
        this.field.set(javaObject, value);
    }
}
