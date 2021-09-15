package com.eagle.mixsdk.sdk.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;

public interface IActivityCallback {
    void onActivityResult(int i, int i2, Intent intent);

    void onBackPressed();

    void onConfigurationChanged(Configuration configuration);

    void onCreate();

    void onDestroy();

    void onNewIntent(Intent intent);

    void onPause();

    void onRequestPermissionResult(int i, String[] strArr, int[] iArr);

    void onRestart();

    void onRestoreInstanceState(Bundle bundle);

    void onResume();

    void onSaveInstanceState(Bundle bundle);

    void onStart();

    void onStop();

    void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams);

    void onWindowFocusChanged(boolean z);
}
