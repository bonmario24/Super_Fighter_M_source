package com.eagle.mixsdk.sdk.lifecycle;

import android.content.Intent;
import com.eagle.mixsdk.sdk.ActivityCallbackAdapter;
import com.xhuyu.component.core.api.HuYuApi;

public class ActivityLifecycleAdapter extends ActivityCallbackAdapter {
    public void onResume() {
        HuYuApi.getInstance().onResume();
        super.onResume();
    }

    public void onDestroy() {
        HuYuApi.getInstance().onDestroy();
        super.onDestroy();
    }

    public void onPause() {
        HuYuApi.getInstance().onPause();
        super.onPause();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        HuYuApi.getInstance().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
