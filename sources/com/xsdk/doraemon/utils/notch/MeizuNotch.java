package com.xsdk.doraemon.utils.notch;

import android.app.Activity;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.xsdk.doraemon.utils.notch.base.BaseNotch;

public class MeizuNotch extends BaseNotch {
    private String TAG = MeizuNotch.class.getSimpleName();
    private Activity activity;

    public MeizuNotch(Activity activity2) {
        this.activity = activity2;
    }

    public boolean isSupportNotch() {
        try {
            return ((Boolean) Class.forName("flyme.config.FlymeFeature").getDeclaredField("IS_FRINGE_DEVICE").get((Object) null)).booleanValue();
        } catch (Exception e) {
            Log.e(this.TAG, "isSupportNotch:\n" + e.toString());
            return false;
        }
    }

    public boolean isHideNotch() {
        if (Build.VERSION.SDK_INT < 17) {
            return false;
        }
        if (Settings.Global.getInt(this.activity.getContentResolver(), "mz_fringe_hide", 0) == 1) {
            return true;
        }
        return false;
    }

    public int getNotchHeight() {
        int fhid;
        if (!isSupportNotch() || isHideNotch() || (fhid = this.activity.getResources().getIdentifier("fringe_height", "dimen", "android")) <= 0) {
            return 0;
        }
        return this.activity.getResources().getDimensionPixelSize(fhid);
    }
}
