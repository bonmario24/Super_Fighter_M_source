package com.huawei.android.hms.pps.p031a;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Keep;
import java.util.concurrent.LinkedBlockingQueue;

@Keep
/* renamed from: com.huawei.android.hms.pps.a.a */
public final class C1477a implements ServiceConnection {
    @Keep

    /* renamed from: a */
    public boolean f845a = false;
    @Keep

    /* renamed from: b */
    public final LinkedBlockingQueue<IBinder> f846b = new LinkedBlockingQueue<>(1);

    @Keep
    public final native void onServiceConnected(ComponentName componentName, IBinder iBinder);

    @Keep
    public final native void onServiceDisconnected(ComponentName componentName);
}
