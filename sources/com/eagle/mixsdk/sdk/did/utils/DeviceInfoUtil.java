package com.eagle.mixsdk.sdk.did.utils;

import android.content.Context;
import android.util.Log;
import com.doraemon.devices.DeviceInfo;
import com.doraemon.devices.Identifier;
import com.doraemon.devices.IdentifierObserver;
import com.doraemon.devices.MsaSdkHelper;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.util.LocationUtil;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.Map;

public class DeviceInfoUtil {
    private static final String SP_IS_UPLOAD_DEVICE = "IsUpLoadDevice";
    private static final String SP_LAST_UPLOAD_TIME_KEY = "LastUploadTime";
    private static final String TAG = DeviceInfoUtil.class.getSimpleName();
    private static DeviceInfoUtil mInstance;
    /* access modifiers changed from: private */
    public DeviceInfo deviceInfoBean;
    /* access modifiers changed from: private */
    public Context mContext;
    private IDeviceInfoListener mListener;
    private long needCompareTime;

    public interface IDeviceInfoListener {
        void onResult(Map<String, Object> map);
    }

    private DeviceInfoUtil() {
    }

    public static DeviceInfoUtil getInstance() {
        if (mInstance == null) {
            synchronized (DeviceInfoUtil.class) {
                if (mInstance == null) {
                    mInstance = new DeviceInfoUtil();
                }
            }
        }
        return mInstance;
    }

    public void doGetDeviceInfo(Context context, long compareTime, IDeviceInfoListener deviceInfoListener) {
        this.mContext = context;
        this.needCompareTime = compareTime;
        if (this.mListener == null) {
            this.mListener = deviceInfoListener;
        }
        doCheck(context);
    }

    private void doCheck(Context context) {
        boolean isUpLoad = DIDSpUtil.getInstance().getBoolean(this.mContext, SP_IS_UPLOAD_DEVICE);
        if (!compareSameDay() && isUpLoad) {
            isUpLoad = false;
            DIDSpUtil.getInstance().putBoolean(this.mContext, SP_IS_UPLOAD_DEVICE, false);
        }
        if (!isUpLoad) {
            if (this.deviceInfoBean == null) {
                this.deviceInfoBean = new DeviceInfo.Builder().build(context);
            }
            uploadDeviceInfo();
            doGetMdidSdkOAID();
            DIDSpUtil.getInstance().putBoolean(this.mContext, SP_IS_UPLOAD_DEVICE, true);
        }
    }

    /* access modifiers changed from: private */
    public void uploadDeviceInfo() {
        if (this.mListener != null) {
            this.mListener.onResult(createDeviceInfoMap());
        }
    }

    public Map<String, Object> createDeviceInfoMap() {
        int i;
        int i2;
        int i3;
        int i4 = 1;
        Map<String, Object> params = new HashMap<>();
        params.put("board", this.deviceInfoBean.getBoard());
        params.put("buildid", this.deviceInfoBean.getBuildID());
        params.put("finger", this.deviceInfoBean.getDeviceFingerprint());
        params.put(ServerProtocol.DIALOG_PARAM_DISPLAY, this.deviceInfoBean.getDeviceDisplay());
        params.put("radio", this.deviceInfoBean.getRadioVersion());
        params.put("androidid", this.deviceInfoBean.getAndroidID());
        params.put("simulator", Integer.valueOf(this.deviceInfoBean.isSimulator() ? 1 : 0));
        params.put("ram", Long.valueOf(this.deviceInfoBean.getTotalRamMemory()));
        params.put("free", Long.valueOf(this.deviceInfoBean.getAvailRamMemory()));
        params.put("input", this.deviceInfoBean.getDefaultInput());
        params.put("browser", this.deviceInfoBean.getDefaultBrowser());
        params.put(FirebaseAnalytics.Param.LOCATION, this.deviceInfoBean.getLocation());
        if (this.deviceInfoBean.isSupportMagnetic()) {
            i = 1;
        } else {
            i = 0;
        }
        params.put("magnetic", Integer.valueOf(i));
        if (this.deviceInfoBean.isSupportGyroscope()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        params.put("gyroscope", Integer.valueOf(i2));
        if (this.deviceInfoBean.isSupportMicrophone()) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        params.put("microphone", Integer.valueOf(i3));
        if (!this.deviceInfoBean.isSupportXposed()) {
            i4 = 0;
        }
        params.put("xposed", Integer.valueOf(i4));
        params.put("oaid", this.deviceInfoBean.getMiitmOAID());
        params.put("product", this.deviceInfoBean.getProduct());
        params.put("device", this.deviceInfoBean.getDevice());
        params.put("totaldisk", Long.valueOf(this.deviceInfoBean.getTotalDisk()));
        params.put("freedisk", Long.valueOf(this.deviceInfoBean.getFreeDisk()));
        return params;
    }

    private boolean compareSameDay() {
        long lastUploadTime = DIDSpUtil.getInstance().getLong(this.mContext, SP_LAST_UPLOAD_TIME_KEY);
        if (lastUploadTime < 0) {
            DIDSpUtil.getInstance().putLong(this.mContext, SP_LAST_UPLOAD_TIME_KEY, this.needCompareTime);
            return true;
        }
        boolean sameDay = DateUtil.compareSameDay(lastUploadTime, this.needCompareTime);
        if (sameDay) {
            return sameDay;
        }
        DIDSpUtil.getInstance().putLong(this.mContext, SP_LAST_UPLOAD_TIME_KEY, this.needCompareTime);
        return sameDay;
    }

    private void doGetMdidSdkOAID() {
        try {
            MsaSdkHelper.getInstance().obtainMsnIdentifier(new IdentifierObserver() {
                public void onChange(Identifier identifier) {
                    String oaid = identifier.getOAID();
                    if (!CheckUtils.isNullOrEmpty(oaid)) {
                        DeviceInfoUtil.this.deviceInfoBean.setLocation(LocationUtil.getLocationInfo(DeviceInfoUtil.this.mContext));
                        DeviceInfoUtil.this.deviceInfoBean.setMiitmOAID(oaid);
                        DeviceInfoUtil.this.uploadDeviceInfo();
                    }
                }
            });
        } catch (Exception e) {
            Log.w(TAG, "doGetMdidSdkOAID excepiton " + e.getMessage());
        }
    }

    public DeviceInfo getDeviceInfo() {
        return this.deviceInfoBean;
    }
}
