package com.doraemon.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Process;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

public class XposedCheckUtil {
    private static String TAG = "Wooo Xposed";
    private static StringBuffer xposedCache = new StringBuffer();

    public static boolean checkXposed(Context ctx) {
        return checkCache() || checkJarClass() || checkJarFile() || checkMaps() || checkPackage(ctx) || checkException();
    }

    private static boolean checkPackage(Context ctx) {
        for (ApplicationInfo applicationInfo : ctx.getPackageManager().getInstalledApplications(128)) {
            if (applicationInfo.packageName.equals("de.robv.android.xposed.installer")) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkException() {
        try {
            throw new Exception("xppp");
        } catch (Exception e) {
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge")) {
                    Log.i(TAG, "found exception of xposed");
                    return true;
                }
            }
            return false;
        }
    }

    private static boolean checkMaps() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/" + Process.myPid() + "/maps"));
            while (true) {
                String str = bufferedReader.readLine();
                if (str == null) {
                    break;
                } else if (str.endsWith("jar") && str.contains("XposedBridge.jar")) {
                    Log.i(TAG, "proc/pid/maps find Xposed.jar -> " + str);
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    private static boolean checkCache() {
        xposedCache.setLength(0);
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Log.i(TAG, "checkCache IN");
        try {
            Object XPHelpers = cl.loadClass("de.robv.android.xposed.XposedHelpers").newInstance();
            if (XPHelpers != null) {
                filterField(XPHelpers, "fieldCache");
                filterField(XPHelpers, "methodCache");
                filterField(XPHelpers, "constructorCache");
                if (xposedCache.length() <= 0) {
                    return false;
                }
                Log.i(TAG, "cache content -> " + xposedCache.length() + " -> " + xposedCache);
                return true;
            }
            Log.i(TAG, "cannot find Xposed framework");
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static void filterField(Object xpHelper, String cacheName) {
        try {
            Field f = xpHelper.getClass().getDeclaredField(cacheName);
            f.setAccessible(true);
            HashMap caMap = (HashMap) f.get(xpHelper);
            if (caMap != null) {
                Set<String> caSet = caMap.keySet();
                if (!caSet.isEmpty()) {
                    Log.i(TAG, "filter -> " + cacheName + " , size -> " + caSet.size());
                    for (String key : caSet) {
                        Log.i(TAG, "filter key -> " + key);
                        if (key != null) {
                            String key2 = key.toLowerCase();
                            if (key2.length() > 0 && !key2.startsWith("android.support") && !key2.startsWith("javax.") && !key2.startsWith("android.webkit") && !key2.startsWith("java.util") && !key2.startsWith("android.widget") && !key2.startsWith("sun.")) {
                                xposedCache.append(key2);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private static boolean checkJarFile() {
        if (new File("/system/framework/XposedBridge.jar").exists()) {
            Log.i(TAG, "system may installed Xposed find jar file");
            return true;
        }
        Log.i(TAG, "system not install Xposed cannot find jar file");
        return false;
    }

    private static boolean checkJarClass() {
        try {
            if (ClassLoader.getSystemClassLoader().loadClass("de.robv.android.xposed.XposedBridge") != null) {
                Log.i(TAG, "system installed Xposed Class");
                return true;
            }
        } catch (Exception e) {
        }
        Log.i(TAG, "system not install Xposed Class");
        return false;
    }

    private static void disableHooks() {
        Log.i(TAG, "disableHooks seted  -> " + getStaticFieldOjbectCL("de.robv.android.xposed.XposedBridge", "disableHooks"));
        Log.i(TAG, "disableHooks seted ");
        setStaticOjbectCL("de.robv.android.xposed.XposedBridge", "disableHooks", true);
        Log.i(TAG, "disableHooks seted  -> " + getStaticFieldOjbectCL("de.robv.android.xposed.XposedBridge", "disableHooks"));
    }

    private static Object getStaticFieldOjbectCL(String className, String fieldName) {
        try {
            Field field = Class.forName(className).getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get((Object) null);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            return null;
        }
    }

    private static void setStaticOjbectCL(String className, String fieldName, Object value) {
        try {
            Field declaredField = Class.forName(className).getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            declaredField.set((Object) null, value);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
        }
    }
}
