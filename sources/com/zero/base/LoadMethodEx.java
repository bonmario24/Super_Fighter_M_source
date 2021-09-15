package com.zero.base;

import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LoadMethodEx {
    private static final String LogTag = "LoadMethodEx ERROR";

    public static Object Load(String str, String str2, Object[] objArr, Object obj) {
        Class cls = null;
        Object obj2 = null;
        if (str != null) {
            try {
                cls = Class.forName(str);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Constructor constructor = null;
            try {
                constructor = cls.getConstructor((Class[]) null);
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            }
            try {
                obj2 = constructor.newInstance((Object[]) null);
            } catch (InstantiationException e3) {
                e3.printStackTrace();
            } catch (IllegalAccessException e4) {
                e4.printStackTrace();
            } catch (IllegalArgumentException e5) {
                e5.printStackTrace();
            } catch (InvocationTargetException e6) {
                e6.printStackTrace();
            }
        } else {
            obj2 = obj;
            cls = obj.getClass();
        }
        try {
            Method method = cls.getMethod(str2, getParamTypes(cls, str2));
            method.setAccessible(true);
            try {
                return method.invoke(obj2, objArr);
            } catch (IllegalAccessException e7) {
                e7.printStackTrace();
                Log.e(LogTag, "���������쳣:" + str2);
                return null;
            } catch (IllegalArgumentException e8) {
                e8.printStackTrace();
                Log.e(LogTag, "���������쳣:" + str2);
                return null;
            } catch (InvocationTargetException e9) {
                e9.printStackTrace();
                Log.e(LogTag, "����Ŀ���쳣:" + str2);
                return null;
            }
        } catch (NoSuchMethodException e10) {
            e10.printStackTrace();
            Log.e(LogTag, "�����˲����ڵķ������߲�����������.:" + str2);
            return null;
        }
    }

    public static Class[] getParamTypes(Class cls, String str) {
        Class[] clsArr = null;
        Method[] methods = cls.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals(str)) {
                clsArr = methods[i].getParameterTypes();
            }
        }
        return clsArr;
    }
}
