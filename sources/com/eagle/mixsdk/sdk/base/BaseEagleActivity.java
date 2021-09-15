package com.eagle.mixsdk.sdk.base;

import android.app.Activity;
import android.content.Context;
import com.eagle.mixsdk.sdk.platform.EaglePlatform;

public class BaseEagleActivity extends Activity {
    /* access modifiers changed from: protected */
    public void attachBaseContext(Context newBase) {
        super.attachBaseContext(EaglePlatform.getInstance().attachBaseContext(newBase));
    }
}
