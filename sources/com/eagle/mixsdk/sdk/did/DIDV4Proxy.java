package com.eagle.mixsdk.sdk.did;

import android.content.Context;
import android.util.Log;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.util.PermissionUtils;
import com.doraemon.util.ThreadUtils;
import com.eagle.mixsdk.sdk.did.config.DIDConfig;
import com.eagle.mixsdk.sdk.did.listener.IDIDObtainListener;
import com.eagle.mixsdk.sdk.did.utils.NewDevDidUtil;

public class DIDV4Proxy {
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    /* access modifiers changed from: private */
    public static volatile String mCacheDid = "";
    /* access modifiers changed from: private */
    public static volatile int mDIDFeatures = 0;
    private volatile boolean isObtaining;
    /* access modifiers changed from: private */
    public IDIDObtainListener obtainListener;

    private static class SingletonHolder {
        public static DIDV4Proxy instance = new DIDV4Proxy();

        private SingletonHolder() {
        }
    }

    public static DIDV4Proxy getInstance() {
        return SingletonHolder.instance;
    }

    private DIDV4Proxy() {
        this.isObtaining = false;
    }

    public static int getFeatures() {
        return mDIDFeatures;
    }

    public String obtainUUID(Context context) {
        if (!CheckUtils.isNullOrEmpty(mCacheDid)) {
            return mCacheDid;
        }
        obtainUUID(context, (IDIDObtainListener) null);
        return "00000000000000000000000000000000";
    }

    public void obtainUUID(Context context, IDIDObtainListener listener) {
        if (listener != null) {
            this.obtainListener = listener;
        }
        if (!CheckUtils.isNullOrEmpty(mCacheDid)) {
            notifyDidChange();
        } else {
            DidV4Helper.doGet(context, new IDIDObtainListener() {
                public void onResult(String did, int from) {
                    String unused = DIDV4Proxy.mCacheDid = did;
                    int unused2 = DIDV4Proxy.mDIDFeatures = from;
                    DIDV4Proxy.this.notifyDidChange();
                    Log.d("UniqueID", "current_v4_id:" + did + " from:" + from);
                }
            });
        }
    }

    public boolean isValidDID(Context context) {
        if (CheckUtils.isNullOrEmpty(mCacheDid)) {
            return false;
        }
        if (!PermissionUtils.isGranted(PERMISSION_READ_EXTERNAL_STORAGE)) {
            return false;
        }
        return mCacheDid.equals(NewDevDidUtil.getInstance().readDidFromHidden(context, DIDConfig.UUID_FILE_TAG));
    }

    /* access modifiers changed from: private */
    public void notifyDidChange() {
        ThreadUtils.runOnUiThread(new Runnable() {
            public void run() {
                if (DIDV4Proxy.this.obtainListener != null) {
                    DIDV4Proxy.this.obtainListener.onResult(DIDV4Proxy.mCacheDid, DIDV4Proxy.getFeatures());
                    IDIDObtainListener unused = DIDV4Proxy.this.obtainListener = null;
                }
            }
        });
    }
}
