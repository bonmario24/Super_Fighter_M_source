package com.bun.miitmdid.supplier.sumsung;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Keep;
import com.bun.lib.C0971a;
import com.bun.miitmdid.p014c.p018e.C0994a;
import com.samsung.android.deviceidservice.IDeviceIdService;

@Keep
public class SumsungCore {
    @Keep
    private static boolean DBG = false;
    @Keep
    private static String SAMSUNGTAG = "Samsung_DeviceIdService";
    @Keep
    private static String TAG = "SumsungCore library";
    @Keep
    private C0994a mCallerCallBack = null;
    @Keep
    private ServiceConnection mConnection;
    @Keep
    private Context mContext = null;
    @Keep
    private IDeviceIdService mDeviceidInterface;

    @Keep
    /* renamed from: com.bun.miitmdid.supplier.sumsung.SumsungCore$a */
    class C1019a implements ServiceConnection {
        @Keep
        C1019a() {
        }

        @Keep
        public native synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder);

        @Keep
        public native void onServiceDisconnected(ComponentName componentName);
    }

    @Keep
    public SumsungCore(Context context, C0994a aVar) {
        if (context != null) {
            this.mContext = context;
            this.mCallerCallBack = aVar;
            this.mConnection = new C1019a();
            Intent intent = new Intent();
            intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
            if (this.mContext.bindService(intent, this.mConnection, 1)) {
                C0971a.m430b(TAG, "bindService Successful!");
                return;
            }
            this.mContext.unbindService(this.mConnection);
            C0971a.m430b(TAG, "bindService Failed!");
            C0994a aVar2 = this.mCallerCallBack;
            if (aVar2 != null) {
                aVar2.mo14812b();
                return;
            }
            return;
        }
        throw new NullPointerException("Context can not be null.");
    }

    @Keep
    static native /* synthetic */ IDeviceIdService access$002(SumsungCore sumsungCore, IDeviceIdService iDeviceIdService);

    @Keep
    static native /* synthetic */ C0994a access$100(SumsungCore sumsungCore);

    @Keep
    static native /* synthetic */ String access$200();

    @Keep
    public native String getAAID();

    @Keep
    public native String getOAID();

    @Keep
    public native String getUDID();

    @Keep
    public native String getVAID();

    @Keep
    public native boolean isSupported();

    @Keep
    public native void shutdown();
}
