package com.eagle.mixsdk.sdk.did;

import android.content.Context;
import android.util.Log;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.util.PermissionUtils;
import com.eagle.mixsdk.sdk.did.listener.IDIDObtainListener;
import com.eagle.mixsdk.sdk.did.utils.DeviceIDUtil;

@Deprecated
public class DIDV2Proxy {
    /* access modifiers changed from: private */
    public static String mCacheDid = "";
    /* access modifiers changed from: private */
    public boolean isCreate = false;
    private IDIDObtainListener obtainListener = new IDIDObtainListener() {
        public void onResult(String did, int from) {
            boolean unused = DIDV2Proxy.this.isCreate = false;
            String unused2 = DIDV2Proxy.mCacheDid = did;
            Log.d("TAG", "current id:" + DIDV2Proxy.mCacheDid + " from:" + from);
        }
    };

    private static class SingletonHolder {
        public static DIDV2Proxy instance = new DIDV2Proxy();

        private SingletonHolder() {
        }
    }

    public static DIDV2Proxy getInstance() {
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
            DeviceIDUtil.doGetDeviceID(context, DeviceIDUtil.V2_VERSION_TAG, this.obtainListener);
        }
        if (CheckUtils.isNullOrEmpty(mCacheDid)) {
            return "00000000000000000000000000000000";
        }
        return mCacheDid;
    }
}
