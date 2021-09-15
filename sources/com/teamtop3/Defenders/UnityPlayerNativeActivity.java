package com.teamtop3.Defenders;

import android.app.NativeActivity;
import android.os.Bundle;
import android.util.Log;
import com.zero.base.GameInterface;

public abstract class UnityPlayerNativeActivity extends NativeActivity implements GameInterface {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Log.e("Unity", "UnityPlayerNativeActivity has been deprecated, please update your AndroidManifest to use UnityPlayerActivity instead \n old Activity ");
        super.onCreate(bundle);
    }
}
