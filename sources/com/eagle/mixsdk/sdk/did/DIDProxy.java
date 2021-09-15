package com.eagle.mixsdk.sdk.did;

import android.annotation.SuppressLint;
import android.content.Context;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.util.PermissionUtils;
import com.eagle.mixsdk.sdk.did.utils.DIDSpUtil;
import com.eagle.mixsdk.sdk.did.utils.DIDUtil;

@Deprecated
public class DIDProxy {
    private static String mCacheDid = "";

    private static class SingletonHolder {
        public static DIDProxy instance = new DIDProxy();

        private SingletonHolder() {
        }
    }

    public static DIDProxy getInstance() {
        return SingletonHolder.instance;
    }

    @SuppressLint({"HardwareIds"})
    public String obtainUUID(Context context) {
        try {
            DIDV3Proxy.getInstance().obtainUUID(context);
            DIDV4Proxy.getInstance().obtainUUID(context);
        } catch (Exception e) {
        }
        if (!CheckUtils.isNullOrEmpty(mCacheDid)) {
            return mCacheDid;
        }
        if (!PermissionUtils.isGranted(DIDV4Proxy.PERMISSION_READ_EXTERNAL_STORAGE)) {
            return "00000000000000000000000000000000";
        }
        mCacheDid = DIDUtil.getInstance().readDid(context);
        if (!CheckUtils.isNullOrEmpty(mCacheDid)) {
            DIDSpUtil.getInstance().save(context, mCacheDid);
            return mCacheDid;
        }
        mCacheDid = DIDSpUtil.getInstance().read(context);
        if (!CheckUtils.isNullOrEmpty(mCacheDid)) {
            DIDUtil.getInstance().writeDid(context, mCacheDid);
            return mCacheDid;
        }
        if (!PermissionUtils.isGranted(DIDV4Proxy.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            return "00000000000000000000000000000000";
        }
        mCacheDid = DIDUtil.getInstance().newDid(context);
        DIDUtil.getInstance().writeDid(context, mCacheDid);
        DIDSpUtil.getInstance().save(context, mCacheDid);
        return mCacheDid;
    }

    public boolean isValidDID(Context context) {
        if (CheckUtils.isNullOrEmpty(mCacheDid)) {
            return false;
        }
        if (!PermissionUtils.isGranted(DIDV4Proxy.PERMISSION_READ_EXTERNAL_STORAGE)) {
            return false;
        }
        return mCacheDid.equals(DIDUtil.getInstance().readDid(context));
    }
}
