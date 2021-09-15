package com.xhuyu.component.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.utils.ContextUtil;

public class ResourceUtil {
    public static String getStringContainFormat(String stringName, Object... formatArgs) {
        Context context = ContextUtil.getInstance().getApplicationContext();
        return ReflectResource.getInstance(context).getProxyContext().getResources().getString(ReflectResource.getInstance(context).getStringId(stringName), formatArgs);
    }

    public static String getString(String resName) {
        return ReflectResource.getInstance(ContextUtil.getInstance().getApplicationContext()).getString(resName);
    }

    public static int getWidgetViewID(String widgetName) {
        return ReflectResource.getInstance(ContextUtil.getInstance().getApplicationContext()).getWidgetViewID(widgetName);
    }

    public static View getWidgetView(View layout, String widgetName) {
        return ReflectResource.getInstance(ContextUtil.getInstance().getApplicationContext()).getWidgetView(layout, widgetName);
    }

    public static int getDrawableId(String imgName) {
        return ReflectResource.getInstance(ContextUtil.getInstance().getApplicationContext()).getDrawableId(imgName);
    }

    public static Drawable getDrawable(String imgName) {
        return ReflectResource.getInstance(ContextUtil.getInstance().getApplicationContext()).getDrawable(imgName);
    }

    public static int getLayoutId(String layoutName) {
        return ReflectResource.getInstance(ContextUtil.getInstance().getApplicationContext()).getLayoutId(layoutName);
    }

    public static View getLayoutView(String layoutName) {
        return ReflectResource.getInstance(ContextUtil.getInstance().getApplicationContext()).getLayoutView(layoutName);
    }

    public static int getColorId(String colorName) {
        return ReflectResource.getInstance(ContextUtil.getInstance().getApplicationContext()).getColorId(colorName);
    }
}
