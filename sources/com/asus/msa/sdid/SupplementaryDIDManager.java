package com.asus.msa.sdid;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Keep;
import com.asus.msa.SupplementaryDID.IDidAidlInterface;

@Keep
public class SupplementaryDIDManager {
    @Keep
    public static boolean DEBUG = false;
    @Keep
    public static final String TAG = "SupplementaryDIDManager";
    @Keep
    public boolean isBinded = false;
    @Keep
    public Context mContext;
    @Keep
    public IDidAidlInterface mDidService;
    @Keep
    public IDIDBinderStatusListener mListener;
    @Keep
    public ServiceConnection mServiceConnection = new ServiceConnection() {
        @Keep
        public native void onServiceConnected(ComponentName componentName, IBinder iBinder);

        @Keep
        public native void onServiceDisconnected(ComponentName componentName);
    };

    @Keep
    public SupplementaryDIDManager(Context context) {
        this.mContext = context;
    }

    @Keep
    public static native /* synthetic */ boolean access$000();

    @Keep
    public static native /* synthetic */ IDidAidlInterface access$102(SupplementaryDIDManager supplementaryDIDManager, IDidAidlInterface iDidAidlInterface);

    @Keep
    public static native /* synthetic */ void access$200(SupplementaryDIDManager supplementaryDIDManager, boolean z);

    @Keep
    private native void notifyAllListeners(boolean z);

    @Keep
    public native void deInit();

    @Keep
    public native void init(IDIDBinderStatusListener iDIDBinderStatusListener);

    @Keep
    public native void showLog(boolean z);
}
