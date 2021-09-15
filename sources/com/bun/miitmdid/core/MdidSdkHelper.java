package com.bun.miitmdid.core;

import android.content.Context;
import android.support.annotation.Keep;
import android.util.Log;
import com.bun.supplier.C1021a;
import com.bun.supplier.IIdentifierListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Keep
public class MdidSdkHelper {
    public static String TAG = "MdidSdkHelper";
    @Keep
    public static boolean _OuterIsOk = true;
    @Keep
    private String sdk_date = "2020011018";

    @Keep
    public static int InitSdk(Context context, boolean z, IIdentifierListener iIdentifierListener) {
        try {
            if (!_OuterIsOk) {
                if (iIdentifierListener != null) {
                    iIdentifierListener.OnSupport(false, new C1021a());
                }
                return ErrorCode.INIT_HELPER_CALL_ERROR;
            }
            Class<?> cls = Class.forName("com.bun.miitmdid.core.MdidSdk");
            if (cls == null) {
                logd(z, "not found class:" + "com.bun.miitmdid.core.MdidSdk");
                return ErrorCode.INIT_HELPER_CALL_ERROR;
            }
            Constructor<?> constructor = cls.getConstructor(new Class[]{Boolean.TYPE});
            if (constructor == null) {
                logd(z, "not found MdidSdk Constructor");
                return ErrorCode.INIT_HELPER_CALL_ERROR;
            }
            Object newInstance = constructor.newInstance(new Object[]{Boolean.valueOf(z)});
            if (newInstance == null) {
                logd(z, "Create MdidSdk Instance failed");
                return ErrorCode.INIT_HELPER_CALL_ERROR;
            }
            Method declaredMethod = cls.getDeclaredMethod("InitSdk", new Class[]{Context.class, IIdentifierListener.class});
            if (declaredMethod == null) {
                logd(z, "not found MdidSdk " + "InitSdk" + " function");
                return ErrorCode.INIT_HELPER_CALL_ERROR;
            }
            int intValue = ((Integer) declaredMethod.invoke(newInstance, new Object[]{context, iIdentifierListener})).intValue();
            logd(z, "call and retvalue:" + intValue);
            return intValue;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            loge(z, e);
            logd(z, "exception exit");
            return ErrorCode.INIT_HELPER_CALL_ERROR;
        }
    }

    public static void logd(boolean z, String str) {
        if (z) {
            Log.d(TAG, str);
        }
    }

    public static void loge(boolean z, Exception exc) {
        if (z) {
            Log.e(TAG, exc.getClass().getSimpleName(), exc);
        }
    }
}
