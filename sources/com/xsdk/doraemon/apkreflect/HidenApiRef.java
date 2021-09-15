package com.xsdk.doraemon.apkreflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class HidenApiRef {
    public static Object invokHidenApi(String className, String methodName, Object target, Object[] args, Class[] types) {
        try {
            return ((Method) Class.class.getDeclaredMethod("getDeclaredMethod", new Class[]{String.class, Class[].class}).invoke(Class.forName(className), new Object[]{methodName, types})).invoke(target, new Object[]{args});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object newHideObject(String className, Class[] types, Object[] args) {
        try {
            Method getConstructor = Class.class.getDeclaredMethod("getConstructor", new Class[]{Class[].class});
            return Constructor.class.getDeclaredMethod("newInstance", new Class[]{Object[].class}).invoke((Constructor) getConstructor.invoke(Class.forName(className), new Object[]{types}), new Object[]{args});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
