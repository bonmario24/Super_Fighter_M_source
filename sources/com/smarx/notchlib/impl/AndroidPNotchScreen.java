package com.smarx.notchlib.impl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Rect;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.smarx.notchlib.INotchScreen;
import java.util.List;

@TargetApi(28)
public class AndroidPNotchScreen implements INotchScreen {
    public void getNotchRect(Activity activity, final INotchScreen.NotchSizeCallback notchSizeCallback) {
        final View decorView = activity.getWindow().getDecorView();
        decorView.post(new Runnable() {
            public void run() {
                DisplayCutout displayCutout;
                WindowInsets rootWindowInsets = decorView.getRootWindowInsets();
                if (rootWindowInsets == null || (displayCutout = rootWindowInsets.getDisplayCutout()) == null) {
                    notchSizeCallback.onResult((List<Rect>) null);
                    return;
                }
                notchSizeCallback.onResult(displayCutout.getBoundingRects());
            }
        });
    }

    public boolean hasNotch(Activity activity) {
        return true;
    }

    public void setDisplayInNotch(Activity activity) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        window.setAttributes(attributes);
        window.getDecorView().setSystemUiVisibility(1280);
    }
}
