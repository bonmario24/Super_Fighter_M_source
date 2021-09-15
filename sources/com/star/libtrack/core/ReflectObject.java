package com.star.libtrack.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectObject {
    public static Field getField(Class cla, String name) {
        try {
            Field field = cla.getDeclaredField(name);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Field getField(String cla, String name) {
        try {
            return getField((Class) Class.forName(cla), name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getFieldValue(Class cla, Object target, String name) {
        try {
            return getField(cla, name).get(target);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getFieldValue(String cla, Object target, String name) {
        try {
            return getFieldValue((Class) Class.forName(cla), target, name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean setFieldValue(Class cla, Object target, String name, Object value) {
        try {
            getField(cla, name).set(target, value);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean setFieldValue(String cla, Object target, String name, Object value) {
        try {
            return setFieldValue((Class) Class.forName(cla), target, name, value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Method getMethod(Class cla, String name, Class[] args) {
        try {
            Method method = cla.getDeclaredMethod(name, args);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Method getMethod(String cla, String name, Class[] args) {
        try {
            return getMethod((Class) Class.forName(cla), name, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object invokeMethod(Class cla, Object target, String name, Class[] args, Object[] argv) {
        try {
            return getMethod(cla, name, args).invoke(target, argv);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object invokeMethod(String cla, Object target, String name, Class[] args, Object[] argv) {
        try {
            return getMethod(cla, name, args).invoke(target, argv);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Constructor getConstruct(Class cla, Class[] args) {
        try {
            Constructor constructor = cla.getDeclaredConstructor(args);
            constructor.setAccessible(true);
            return constructor;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Constructor getConstruct(String cla, Class[] args) {
        try {
            return getConstruct((Class) Class.forName(cla), args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getInstance(Class cla, Class[] args, Object[] argv) {
        try {
            return getConstruct(cla, args).newInstance(argv);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getInstance(String cla, Class[] args, Object[] argv) {
        try {
            return getInstance((Class) Class.forName(cla), args, argv);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
