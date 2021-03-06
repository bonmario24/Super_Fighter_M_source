package com.google.android.gms.common.internal;

import android.content.Intent;
import androidx.fragment.app.Fragment;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
final class zac extends DialogRedirect {
    private final /* synthetic */ Fragment val$fragment;
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ Intent zaos;

    zac(Intent intent, Fragment fragment, int i) {
        this.zaos = intent;
        this.val$fragment = fragment;
        this.val$requestCode = i;
    }

    public final void redirect() {
        if (this.zaos != null) {
            this.val$fragment.startActivityForResult(this.zaos, this.val$requestCode);
        }
    }
}
