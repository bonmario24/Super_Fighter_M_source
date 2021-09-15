package com.zero.service;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.teamtop3.Defenders.UnityPlayerNativeActivity;

public class ShowNotificationReceiver extends BroadcastReceiver {
    public static String packgename = "com.zero.defenders";
    Context text;

    /* access modifiers changed from: package-private */
    public boolean isRunningApp() {
        for (ActivityManager.RunningTaskInfo runningTaskInfo : ((ActivityManager) this.text.getSystemService("activity")).getRunningTasks(1000)) {
            if (runningTaskInfo.topActivity.getPackageName().equals(packgename)) {
                return true;
            }
        }
        return false;
    }

    public void onReceive(Context context, Intent intent) {
        this.text = context;
        if (isRunningApp()) {
            Log.e("unity", "点击时开启�?��?��?��??");
            Intent intent2 = new Intent();
            intent2.setClass(this.text, UnityPlayerNativeActivity.class);
            intent2.setFlags(270532608);
            this.text.getApplicationContext().startActivity(intent2);
        } else if (!isRunningApp()) {
            Log.e("unity", "点击是关闭�?��?��?��??");
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(packgename);
            launchIntentForPackage.setFlags(270532608);
            context.startActivity(launchIntentForPackage);
        }
    }
}
