package com.eagle.mixsdk.sdk.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import java.lang.reflect.Field;

public class ResourceHelper {
    public static void showTip(Context context, String resID) {
        Toast.makeText(context, getString(context, resID), 0).show();
    }

    public static Drawable getDrawable(Context context, String resID) {
        return context.getResources().getDrawable(getIdentifier(context, resID));
    }

    public static String getString(Context context, String resID) {
        return context.getResources().getString(getIdentifier(context, resID));
    }

    public static View getView(Activity context, String resID) {
        return context.findViewById(getIdentifier(context, resID));
    }

    public static View getViewByParent(View view, String resID) {
        return view.findViewById(getIdentifier(view.getContext(), resID));
    }

    public static View getViewByWindow(Window view, String resID) {
        return view.findViewById(getIdentifier(view.getContext(), resID));
    }

    public static int getIdentifier(Context context, String paramString) {
        if (paramString != null) {
            String[] splits = paramString.split("\\.", 3);
            if (splits.length == 3) {
                return context.getResources().getIdentifier(splits[2], splits[1], context.getPackageName());
            }
        }
        return 0;
    }

    public static int getResource(Context context, String name) {
        Object obj;
        String[] splits = name.split("\\.", 3);
        if (splits.length != 3 || (obj = getResourceId(context, splits[2], splits[1])) == null) {
            return 0;
        }
        return ((Integer) obj).intValue();
    }

    public static int[] getResouceArray(Context context, String name) {
        String[] splits = name.split("\\.", 3);
        if (splits.length != 3) {
            return new int[0];
        }
        Object obj = getResourceId(context, splits[2], splits[1]);
        return obj == null ? new int[0] : (int[]) obj;
    }

    private static Object getResourceId(Context context, String name, String type) {
        try {
            for (Class child : Class.forName(context.getPackageName() + ".R").getClasses()) {
                if (child.getSimpleName().equals(type)) {
                    for (Field f : child.getFields()) {
                        if (f.getName().equals(name)) {
                            return f.get((Object) null);
                        }
                    }
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
