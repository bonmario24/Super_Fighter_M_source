package com.eagle.mixsdk.sdk.did;

import android.content.Context;
import android.util.Log;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.util.PermissionUtils;
import com.doraemon.util.ThreadUtils;
import com.eagle.mixsdk.sdk.did.listener.IDIDObtainListener;
import com.eagle.mixsdk.sdk.did.utils.DeviceIDUtil;

@Deprecated
public class DIDV3Proxy {
    /* access modifiers changed from: private */
    public static volatile String mCacheDid = "";
    /* access modifiers changed from: private */
    public static volatile int mDIDFeatures = 0;
    /* access modifiers changed from: private */
    public boolean isCreate = false;
    /* access modifiers changed from: private */
    public IDIDObtainListener obtainListener;

    private static class SingletonHolder {
        public static DIDV3Proxy instance = new DIDV3Proxy();

        private SingletonHolder() {
        }
    }

    public static DIDV3Proxy getInstance() {
        return SingletonHolder.instance;
    }

    public String obtainUUID(Context context) {
        if (!CheckUtils.isNullOrEmpty(mCacheDid)) {
            return mCacheDid;
        }
        if (!PermissionUtils.isGranted(DIDV4Proxy.PERMISSION_READ_EXTERNAL_STORAGE)) {
            return "00000000000000000000000000000000";
        }
        if (!this.isCreate && CheckUtils.isNullOrEmpty(mCacheDid)) {
            this.isCreate = true;
            DeviceIDUtil.doGetDeviceID(context, DeviceIDUtil.V3_VERSION_TAG, new IDIDObtainListener() {
                public void onResult(String did, int from) {
                    boolean unused = DIDV3Proxy.this.isCreate = false;
                    String unused2 = DIDV3Proxy.mCacheDid = did;
                    int unused3 = DIDV3Proxy.mDIDFeatures = from;
                    DIDV3Proxy.this.notifyDidChange();
                    Log.d("TAG", "current_v3id:" + DIDV3Proxy.mCacheDid + " from:" + from);
                }
            });
        }
        if (CheckUtils.isNullOrEmpty(mCacheDid)) {
            return "00000000000000000000000000000000";
        }
        return mCacheDid;
    }

    public void obtainUUID(Context context, IDIDObtainListener obtainListener2) {
        if (obtainListener2 != null) {
            this.obtainListener = obtainListener2;
        }
        if (CheckUtils.isNullOrEmpty(mCacheDid)) {
            obtainUUID(context);
        } else {
            notifyDidChange();
        }
    }

    public static int getDidFeatures() {
        return mDIDFeatures;
    }

    public void notifyDidChange() {
        ThreadUtils.runOnUiThread(new Runnable() {
            public void run() {
                if (DIDV3Proxy.this.obtainListener != null) {
                    DIDV3Proxy.this.obtainListener.onResult(DIDV3Proxy.mCacheDid, DIDV3Proxy.getDidFeatures());
                    IDIDObtainListener unused = DIDV3Proxy.this.obtainListener = null;
                }
            }
        });
    }
}
