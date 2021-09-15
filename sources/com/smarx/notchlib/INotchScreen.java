package com.smarx.notchlib;

import android.app.Activity;
import android.graphics.Rect;
import java.util.List;

public interface INotchScreen {

    public interface HasNotchCallback {
        void onResult(boolean z);
    }

    public interface NotchScreenCallback {
        void onResult(NotchScreenInfo notchScreenInfo);
    }

    public static class NotchScreenInfo {
        public boolean hasNotch;
        public List<Rect> notchRects;
    }

    public interface NotchSizeCallback {
        void onResult(List<Rect> list);
    }

    void getNotchRect(Activity activity, NotchSizeCallback notchSizeCallback);

    boolean hasNotch(Activity activity);

    void setDisplayInNotch(Activity activity);
}
