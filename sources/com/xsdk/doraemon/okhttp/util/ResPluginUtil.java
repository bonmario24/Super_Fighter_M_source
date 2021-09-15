package com.xsdk.doraemon.okhttp.util;

import android.content.Context;
import com.xsdk.doraemon.apkreflect.ReflectResource;

public class ResPluginUtil {
    public static String getStringByName(Context context, String resName) {
        try {
            return ReflectResource.getInstance(context).getString(resName);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
