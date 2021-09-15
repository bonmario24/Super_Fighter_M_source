package com.eagle.mixsdk.sdk;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;
import com.eagle.mixsdk.sdk.base.IActivityCallback;

public class ActivityCallbackAdapter implements IActivityCallback {
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onNewIntent(Intent newIntent) {
    }

    public void onStop() {
    }

    public void onDestroy() {
    }

    public void onRestart() {
    }

    public void onBackPressed() {
    }

    public void onCreate() {
    }

    public void onStart() {
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    public void onWindowFocusChanged(boolean hasFocus) {
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
    }
}
