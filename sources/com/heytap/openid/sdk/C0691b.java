package com.heytap.openid.sdk;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Keep;

@Keep
/* renamed from: com.heytap.openid.sdk.b */
public class C0691b implements ServiceConnection {
    @Keep

    /* renamed from: a */
    public final /* synthetic */ C0692c f79a;

    @Keep
    public C0691b(C0692c cVar) {
        this.f79a = cVar;
    }

    @Keep
    public native void onServiceConnected(ComponentName componentName, IBinder iBinder);

    @Keep
    public native void onServiceDisconnected(ComponentName componentName);
}
