package com.bun.miitmdid.supplier.msa;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Keep;
import com.bun.lib.C0973c;
import com.bun.miitmdid.p014c.p018e.C0994a;

@Keep
public class MsaClient {
    @Keep
    private static String TAG = "MSA Client library";
    @Keep
    private static String TARGET_PACKAGE = "com.mdid.msa";
    @Keep
    private C0994a _BindService;
    @Keep
    private ServiceConnection mConnection;
    @Keep
    private Context mContext;
    @Keep
    private C0973c mDeviceidInterface;

    @Keep
    /* renamed from: com.bun.miitmdid.supplier.msa.MsaClient$a */
    class C1016a implements ServiceConnection {
        @Keep

        /* renamed from: a */
        final /* synthetic */ C0994a f794a;

        @Keep
        C1016a(C0994a aVar) {
            this.f794a = aVar;
        }

        @Keep
        public native synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder);

        @Keep
        public native void onServiceDisconnected(ComponentName componentName);
    }

    @Keep
    public MsaClient(Context context, C0994a aVar) {
        if (context != null) {
            this.mContext = context;
            this._BindService = aVar;
            this.mConnection = new C1016a(aVar);
            return;
        }
        throw new NullPointerException("Context can not be null.");
    }

    @Keep
    public static native boolean CheckService(Context context);

    @Keep
    public static native void StartMsaKlService(Context context, String str);

    @Keep
    static native /* synthetic */ C0973c access$000(MsaClient msaClient);

    @Keep
    static native /* synthetic */ C0973c access$002(MsaClient msaClient, C0973c cVar);

    @Keep
    static native /* synthetic */ String access$100();

    @Keep
    public native void BindService(String str);

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
