package com.smarx.notchlib;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import com.smarx.notchlib.INotchScreen;
import com.smarx.notchlib.impl.AndroidPNotchScreen;
import com.smarx.notchlib.impl.HuaweiNotchScreen;
import com.smarx.notchlib.impl.MiNotchScreen;
import com.smarx.notchlib.impl.OppoNotchScreen;
import com.smarx.notchlib.utils.RomUtils;
import java.util.List;

public class NotchScreenManager {
    private static final NotchScreenManager instance = new NotchScreenManager();
    private final INotchScreen notchScreen = getNotchScreen();

    private NotchScreenManager() {
    }

    public static NotchScreenManager getInstance() {
        return instance;
    }

    private INotchScreen getNotchScreen() {
        if (Build.VERSION.SDK_INT >= 28) {
            return new AndroidPNotchScreen();
        }
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        if (RomUtils.isHuawei()) {
            return new HuaweiNotchScreen();
        }
        if (RomUtils.isOppo()) {
            return new OppoNotchScreen();
        }
        if (RomUtils.isVivo()) {
            return new HuaweiNotchScreen();
        }
        if (RomUtils.isXiaomi()) {
            return new MiNotchScreen();
        }
        return null;
    }

    public void getNotchInfo(Activity activity, final INotchScreen.NotchScreenCallback notchScreenCallback) {
        final INotchScreen.NotchScreenInfo notchScreenInfo = new INotchScreen.NotchScreenInfo();
        if (this.notchScreen == null || !this.notchScreen.hasNotch(activity)) {
            notchScreenCallback.onResult(notchScreenInfo);
        } else {
            this.notchScreen.getNotchRect(activity, new INotchScreen.NotchSizeCallback() {
                public void onResult(List<Rect> list) {
                    if (list != null && list.size() > 0) {
                        notchScreenInfo.hasNotch = true;
                        notchScreenInfo.notchRects = list;
                    }
                    notchScreenCallback.onResult(notchScreenInfo);
                }
            });
        }
    }

    public void setDisplayInNotch(Activity activity) {
        if (this.notchScreen != null) {
            this.notchScreen.setDisplayInNotch(activity);
        }
    }
}
