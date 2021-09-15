package com.xsdk.doraemon.utils.notch;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.Window;
import com.xsdk.doraemon.utils.notch.base.BaseNotch;

public class XiaomiNotch extends BaseNotch {
    private String TAG = XiaomiNotch.class.getSimpleName();
    /* access modifiers changed from: private */
    public Activity activity;
    @SuppressLint({"HandlerLeak"})
    private Handler mHandler = new Handler() {
        public void dispatchMessage(Message msg) {
            String cmd = (String) msg.obj;
            Class<Window> cls = Window.class;
            try {
                cls.getMethod(cmd, new Class[]{Integer.TYPE}).invoke(XiaomiNotch.this.activity.getWindow(), new Object[]{1792});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public XiaomiNotch(Activity activity2) {
        this.activity = activity2;
    }

    public boolean isSupportNotch() {
        try {
            Class<?> mClassType = Class.forName("android.os.SystemProperties");
            if (((Integer) mClassType.getDeclaredMethod("getInt", new Class[]{String.class, Integer.TYPE}).invoke(mClassType, new Object[]{"ro.miui.notch", 0})).intValue() == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isHideNotch() {
        if (Build.VERSION.SDK_INT < 17) {
            return false;
        }
        if (Settings.Global.getInt(this.activity.getContentResolver(), "force_black", 0) == 1) {
            return true;
        }
        return false;
    }

    public int getNotchWidth() {
        if (!isSupportNotch()) {
            return 0;
        }
        if (isHideNotch()) {
            Message msg = new Message();
            msg.obj = "clearExtraFlags";
            this.mHandler.sendMessage(msg);
            return 0;
        }
        Message msg2 = new Message();
        msg2.obj = "addExtraFlags";
        this.mHandler.sendMessage(msg2);
        String model = Build.MODEL;
        if (model.contains("MI8Lite")) {
            return 296;
        }
        if (model.contains("Redmi 6 Pro")) {
            return 352;
        }
        if (model.contains("MI 8 SE")) {
            return 540;
        }
        if (model.contains("MI 8") || model.contains("MI 8 Explorer Edition") || model.contains("MI 8 UD")) {
            return 560;
        }
        if (model.contains("POCO F1")) {
            return 588;
        }
        int resourceId = this.activity.getResources().getIdentifier("notch_width", "dimen", "android");
        if (resourceId > 0) {
            return this.activity.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public int getNotchHeight() {
        if (!isSupportNotch()) {
            return 0;
        }
        if (isHideNotch()) {
            Message msg = new Message();
            msg.obj = "clearExtraFlags";
            this.mHandler.sendMessage(msg);
            return 0;
        }
        Message msg2 = new Message();
        msg2.obj = "addExtraFlags";
        this.mHandler.sendMessage(msg2);
        String model = Build.MODEL;
        if (model.contains("MI8Lite")) {
            return 82;
        }
        if (model.contains("MI 8 SE")) {
            return 85;
        }
        if (model.contains("POCO F1")) {
            return 86;
        }
        if (model.contains("MI 8") || model.contains("MI 8 Explorer Edition") || model.contains("MI 8 UD") || model.contains("Redmi 6 Pro")) {
            return 89;
        }
        if (model.contains("MI 9")) {
            return 89;
        }
        int resourceId = this.activity.getResources().getIdentifier("notch_height", "dimen", "android");
        if (resourceId > 0) {
            return this.activity.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
