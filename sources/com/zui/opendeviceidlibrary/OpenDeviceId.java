package com.zui.opendeviceidlibrary;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Keep;
import com.zui.deviceidservice.IDeviceidInterface;

@Keep
public class OpenDeviceId {
    @Keep

    /* renamed from: c */
    private static String f902c = "OpenDeviceId library";
    @Keep

    /* renamed from: d */
    private static boolean f903d;
    @Keep

    /* renamed from: a */
    private IDeviceidInterface f904a;
    @Keep

    /* renamed from: b */
    private CallBack f905b;

    @Keep
    /* renamed from: com.zui.opendeviceidlibrary.OpenDeviceId$1 */
    class C17531 implements ServiceConnection {
        @Keep

        /* renamed from: a */
        final /* synthetic */ OpenDeviceId f906a;

        @Keep
        public native synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder);

        @Keep
        public native void onServiceDisconnected(ComponentName componentName);
    }

    @Keep
    public interface CallBack {
        @Keep
        /* renamed from: a */
        void mo19658a(OpenDeviceId openDeviceId);
    }

    @Keep
    /* renamed from: a */
    static native /* synthetic */ IDeviceidInterface m684a(OpenDeviceId openDeviceId, IDeviceidInterface iDeviceidInterface);

    @Keep
    /* renamed from: a */
    static native /* synthetic */ CallBack m685a(OpenDeviceId openDeviceId);

    @Keep
    /* renamed from: a */
    static native /* synthetic */ void m686a(OpenDeviceId openDeviceId, String str);

    @Keep
    /* renamed from: a */
    private native void m687a(String str);
}
