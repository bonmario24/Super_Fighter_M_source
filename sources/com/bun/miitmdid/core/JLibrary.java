package com.bun.miitmdid.core;

import android.content.Context;
import android.support.annotation.Keep;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Keep
public class JLibrary {
    @Keep
    public static String ASSET = "assets/";
    @Keep
    public static String SeriailizationString = "stub_liu_0_dex_so:39285EFA@com/jdog;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;";
    public static final String TAG = JLibrary.class.getSimpleName();
    @Keep
    public static ClassLoader classLoader;
    @Keep
    public static Context context;
    @Keep
    public static String o00o0a0odod;
    @Keep
    public static String o00o0a0odou;
    @Keep
    public static String xdata = ".00000000000";
    @Keep
    public static String ydata = ".11111111111";

    @Keep
    enum ReturnStatus {
        RETURN_OK,
        RETURN_ERR
    }

    @Keep
    public static ReturnStatus InitEntry(Context context2) throws Exception {
        if (context2 == null) {
            throw new ExceptionInInitializerError("pass InitEntry arg(context) is null");
        }
        context = context2;
        classLoader = JLibrary.class.getClassLoader();
        System.loadLibrary(SeriailizationString.substring(SeriailizationString.lastIndexOf(58) + 1, SeriailizationString.indexOf(64)));
        if ("!".length() < 0) {
            return ReturnStatus.RETURN_ERR;
        }
        m551a();
        return ReturnStatus.RETURN_OK;
    }

    @Keep
    public static ByteBuffer ReadByteBuffer(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            ByteBuffer allocate = ByteBuffer.allocate(fileInputStream.available());
            for (int i = 0; i < fileInputStream.available(); i += fileInputStream.read(allocate.array(), i, fileInputStream.available() - i)) {
            }
            fileInputStream.close();
            return allocate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Keep
    /* renamed from: a */
    public static native boolean m551a();

    @Keep
    public static Object[] o0o0o0o0o0(Object obj, String str, String str2, List<IOException> list) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        String[] split = str.split(";");
        ArrayList arrayList = new ArrayList();
        for (String ReadByteBuffer : split) {
            arrayList.add(ReadByteBuffer(ReadByteBuffer));
        }
        return (Object[]) o0o0o0o0o0o0(obj, str2, ByteBuffer[].class, List.class).invoke(obj, new Object[]{arrayList.toArray(new ByteBuffer[arrayList.size()]), list});
    }

    @Keep
    private static Method o0o0o0o0o0o0(Object obj, String str, Class<?>... clsArr) throws NoSuchMethodException {
        Class cls = obj.getClass();
        while (cls != null) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException e) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchMethodException("Method " + str + " with parameters " + Arrays.asList(clsArr) + " not found in " + obj.getClass());
    }

    @Keep
    public static void o0oo0o0(Context context2, String str) throws Exception {
        int i;
        int i2 = 3;
        try {
            InputStream open = context2.getAssets().open(str);
            o00o0a0odod = Utils.getXdataDir(context2, "");
            o00o0a0odou = Utils.getYdataDir(context2, "");
            File file = new File(o00o0a0odod);
            File file2 = new File(o00o0a0odou);
            if (!file.exists()) {
                int i3 = 3;
                while (true) {
                    i = i3 - 1;
                    if (i3 > 0) {
                        if (file.mkdir()) {
                            break;
                        }
                        i3 = i;
                    } else {
                        break;
                    }
                }
            } else {
                i = 3;
            }
            if (i == -1) {
                throw new IllegalStateException("User dir cannot be created: " + file.getAbsolutePath());
            }
            if (!file2.exists()) {
                int i4 = 3;
                while (true) {
                    i2 = i4 - 1;
                    if (i4 <= 0 || file2.mkdir()) {
                        break;
                    }
                    i4 = i2;
                }
            }
            if (i2 == -1) {
                throw new IllegalStateException("User dir cannot be created: " + file2.getAbsolutePath());
            } else if (Utils.update(context2) || !new File(String.valueOf(o00o0a0odod) + str).exists()) {
                Utils.x0xooXdata(open, String.valueOf(o00o0a0odod) + str, context2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
