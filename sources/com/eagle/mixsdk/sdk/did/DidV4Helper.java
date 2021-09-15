package com.eagle.mixsdk.sdk.did;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.doraemon.devices.AdvertisingIdHelper;
import com.doraemon.devices.Identifier;
import com.doraemon.devices.IdentifierObserver;
import com.doraemon.devices.MsaSdkHelper;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.p027eg.CommonUtil;
import com.doraemon.util.PermissionUtils;
import com.eagle.mixsdk.sdk.did.config.DIDConfig;
import com.eagle.mixsdk.sdk.did.listener.IDIDObtainListener;
import com.eagle.mixsdk.sdk.did.utils.DIDMD5Util;
import com.eagle.mixsdk.sdk.did.utils.ExternalDidDisposeUtil;
import com.eagle.mixsdk.sdk.did.utils.NewDevDidSpUtil;
import com.eagle.mixsdk.sdk.did.utils.NewDevDidUtil;
import com.facebook.appevents.AppEventsConstants;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class DidV4Helper {
    private static final String TAG = "UniqueID";
    private static final long TIMEOUT = 5000;
    private static Handler handler;
    private static DidV4Helper instance;
    private static IDIDObtainListener mListener;
    /* access modifiers changed from: private */
    public String cacheDID;
    /* access modifiers changed from: private */
    public String didToAudio;
    /* access modifiers changed from: private */
    public String didToPicture;
    /* access modifiers changed from: private */
    public String didToVideo;
    /* access modifiers changed from: private */
    public String hiddenDID;
    /* access modifiers changed from: private */
    public AtomicBoolean isGetGaiD = new AtomicBoolean();
    /* access modifiers changed from: private */
    public AtomicBoolean isGetOaiD = new AtomicBoolean();
    /* access modifiers changed from: private */
    public volatile String mCacheGAID = "";
    /* access modifiers changed from: private */
    public volatile String mCacheOAID = "";
    /* access modifiers changed from: private */
    public String matcherDID;

    public static DidV4Helper with(Context context) {
        if (instance == null) {
            instance = new DidV4Helper(context);
        }
        return instance;
    }

    private DidV4Helper(final Context context) {
        new Thread(new Runnable() {
            public void run() {
                String unused = DidV4Helper.this.hiddenDID = "";
                String unused2 = DidV4Helper.this.didToPicture = "";
                String unused3 = DidV4Helper.this.didToVideo = "";
                String unused4 = DidV4Helper.this.didToAudio = "";
                String unused5 = DidV4Helper.this.cacheDID = "";
                String unused6 = DidV4Helper.this.matcherDID = "";
                DidV4Helper.this.read(context);
                DidV4Helper.this.check(context);
            }
        }).start();
    }

    public static void doGet(Context context, IDIDObtainListener listener) {
        if (PermissionUtils.isGranted(DIDV4Proxy.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            mListener = listener;
            with(context);
        }
    }

    /* access modifiers changed from: private */
    public void check(Context context) {
        boolean isValid = true;
        String[] didArray = {this.hiddenDID, this.didToAudio, this.didToVideo, this.didToPicture, this.cacheDID};
        int length = didArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String did = didArray[i];
            if (!CheckUtils.isNullOrEmpty(did) && DIDConfig.didPattern.matcher(did).matches()) {
                this.matcherDID = did;
                break;
            }
            i++;
        }
        if (CheckUtils.isNullOrEmpty(this.matcherDID)) {
            isValid = false;
        }
        if (isValid) {
            callback(this.matcherDID, 2);
            rewrite(context);
            return;
        }
        create(context);
    }

    /* access modifiers changed from: private */
    public void read(Context context) {
        this.hiddenDID = NewDevDidUtil.getInstance().readDidFromHidden(context, DIDConfig.UUID_FILE_TAG);
        Log.d("UniqueID-", "didFromHiddenDID:" + this.hiddenDID);
        this.didToPicture = ExternalDidDisposeUtil.readDidToPicture(context, "ocean");
        this.didToVideo = ExternalDidDisposeUtil.readDidToVideo(context, "ocean");
        this.didToAudio = ExternalDidDisposeUtil.readDidToAudio(context, ExternalDidDisposeUtil.AUDIO_FILE_NAME);
        this.cacheDID = NewDevDidSpUtil.getInstance().read(context, DIDConfig.SP_DID_TAG);
        Log.d("UniqueID-", "didFromCacheDID:" + this.cacheDID);
    }

    private void rewrite(Context context) {
        if (PermissionUtils.isGranted(DIDV4Proxy.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            if (CheckUtils.isNullOrEmpty(this.hiddenDID) || !this.matcherDID.equals(this.hiddenDID)) {
                NewDevDidUtil.getInstance().writeDidToHidden(context, DIDConfig.UUID_FILE_TAG, this.matcherDID);
            }
            if (CheckUtils.isNullOrEmpty(this.didToPicture) || !this.matcherDID.equals(this.didToPicture)) {
                ExternalDidDisposeUtil.saveDidToPicture(context, "ocean", this.matcherDID);
            }
            if (CheckUtils.isNullOrEmpty(this.didToVideo) || !this.matcherDID.equals(this.didToVideo)) {
                ExternalDidDisposeUtil.saveDidToVideo(context, "ocean", this.matcherDID);
            }
            if (CheckUtils.isNullOrEmpty(this.didToAudio) || !this.matcherDID.equals(this.didToAudio)) {
                ExternalDidDisposeUtil.saveDidToAudio(context, ExternalDidDisposeUtil.AUDIO_FILE_NAME, this.matcherDID);
            }
            if (CheckUtils.isNullOrEmpty(this.cacheDID) || !this.matcherDID.equals(this.cacheDID)) {
                NewDevDidSpUtil.getInstance().save(context, DIDConfig.SP_DID_TAG, this.matcherDID);
            }
        }
    }

    private void create(Context context) {
        int currentSDKVersion = Build.VERSION.SDK_INT;
        if (currentSDKVersion >= 26) {
            obtainOAIDOrGAID(context);
            return;
        }
        if (currentSDKVersion < 23) {
            this.matcherDID = generateNewDid(context, 1);
        } else {
            this.matcherDID = generateNewDid(context, 2);
        }
        callback(this.matcherDID, 1);
        rewrite(context);
    }

    private void obtainOAIDOrGAID(final Context context) {
        getHandler().postDelayed(new Runnable() {
            public void run() {
                Log.w(DidV4Helper.TAG, "postDelayed running");
                DidV4Helper.this.isGetGaiD.set(true);
                DidV4Helper.this.isGetOaiD.set(true);
                DidV4Helper.this.createWithGAID(context, DidV4Helper.this.mCacheGAID);
            }
        }, TIMEOUT);
        MsaSdkHelper.getInstance().obtainMsnIdentifier(new IdentifierObserver() {
            public void onChange(Identifier identifier) {
                Log.w(DidV4Helper.TAG, "obtainMsnIdentifier");
                try {
                    if (!DidV4Helper.this.isGetOaiD.getAndSet(true)) {
                        if (identifier != null && !CheckUtils.isNullOrEmpty(identifier.getOAID())) {
                            String unused = DidV4Helper.this.mCacheOAID = identifier.getOAID();
                            DidV4Helper.this.getHandler().removeCallbacksAndMessages((Object) null);
                            Log.w(DidV4Helper.TAG, "createWithOAID:" + identifier.getOAID());
                            DidV4Helper.this.createWithOAID(context, identifier.getOAID());
                        } else if (DidV4Helper.this.isGetGaiD.get()) {
                            DidV4Helper.this.getHandler().removeCallbacksAndMessages((Object) null);
                            DidV4Helper.this.createWithGAID(context, DidV4Helper.this.mCacheGAID);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        AdvertisingIdHelper.getInstance().getAdvertisingId(new IdentifierObserver() {
            public void onChange(Identifier identifier) {
                if (!DidV4Helper.this.isGetGaiD.getAndSet(true)) {
                    Log.w(DidV4Helper.TAG, "getAdvertisingId");
                    if (identifier != null && !CheckUtils.isNullOrEmpty(identifier.getGAID())) {
                        String unused = DidV4Helper.this.mCacheGAID = identifier.getGAID();
                    }
                    if (DidV4Helper.this.isGetOaiD.get() && CheckUtils.isNullOrEmpty(DidV4Helper.this.mCacheOAID)) {
                        DidV4Helper.this.getHandler().removeCallbacksAndMessages((Object) null);
                        DidV4Helper.this.createWithGAID(context, DidV4Helper.this.mCacheGAID);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public Handler getHandler() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        return handler;
    }

    /* access modifiers changed from: private */
    public void createWithOAID(Context context, String oaid) {
        if (CheckUtils.isNullOrEmpty(oaid)) {
            throw new NullPointerException("oaid is empty in createWithOAID");
        }
        this.matcherDID = generateNewDid(context, 3);
        callback(this.matcherDID, 1);
        rewrite(context);
    }

    /* access modifiers changed from: private */
    public void createWithGAID(Context context, String gAid) {
        if (CheckUtils.isNullOrEmpty(gAid)) {
            createWithMac(context);
            return;
        }
        Log.w(TAG, "createWithGAID:" + gAid);
        this.matcherDID = generateNewDid(context, 4);
        callback(this.matcherDID, 1);
        rewrite(context);
    }

    private void createWithMac(Context context) {
        this.matcherDID = generateNewDid(context, 5);
        callback(this.matcherDID, 1);
        rewrite(context);
    }

    private void callback(String did, int from) {
        if (mListener != null) {
            mListener.onResult(did, from);
        }
    }

    @SuppressLint({"HardwareIds"})
    public String generateNewDid(Context context, int generateType) {
        String cacheInfo = "";
        String cacheIdentify = "";
        String factoryInfo = Build.MANUFACTURER + Build.BRAND + Build.MODEL + Build.DEVICE + Build.PRODUCT + Build.BOARD;
        switch (generateType) {
            case 1:
                String imei = NewDevDidUtil.getInstance().getImei(context);
                if (!CheckUtils.isNullOrEmpty(imei) && !imei.contains("0000000")) {
                    cacheInfo = imei;
                    cacheIdentify = AppEventsConstants.EVENT_PARAM_VALUE_YES;
                    break;
                }
            case 2:
                String androidID = Settings.Secure.getString(context.getContentResolver(), "android_id");
                if (!CheckUtils.isNullOrEmpty(androidID) && !androidID.equals("9774d56d682e549c")) {
                    cacheInfo = androidID;
                    cacheIdentify = "4";
                    break;
                }
            case 3:
                String oaid = MsaSdkHelper.getInstance().getOAID();
                if (!CheckUtils.isNullOrEmpty(oaid)) {
                    cacheInfo = oaid;
                    cacheIdentify = "3";
                    break;
                }
            case 4:
                String gaid = AdvertisingIdHelper.getInstance().getAdvertisingId();
                if (!CheckUtils.isNullOrEmpty(gaid)) {
                    cacheInfo = gaid;
                    cacheIdentify = "7";
                    break;
                }
            case 5:
                String macAddress = CommonUtil.getMacAddress();
                if (!CheckUtils.isNullOrEmpty(macAddress) && !"02:00:00:00:00:00".equals(macAddress)) {
                    cacheInfo = factoryInfo + macAddress;
                    cacheIdentify = "2";
                    break;
                }
            case 6:
                String display = Build.DISPLAY;
                if (!CheckUtils.isNullOrEmpty(display)) {
                    cacheInfo = factoryInfo + display;
                    cacheIdentify = "5";
                    break;
                }
            case 7:
                String radioVersion = Build.getRadioVersion();
                if (!CheckUtils.isNullOrEmpty(radioVersion)) {
                    cacheInfo = factoryInfo + radioVersion;
                    cacheIdentify = "6";
                    break;
                }
            case 8:
                cacheInfo = UUID.randomUUID().toString();
                cacheIdentify = "8";
                break;
        }
        if (TextUtils.isEmpty(cacheInfo) || TextUtils.isEmpty(cacheIdentify)) {
            throw new NullPointerException("generateNewDid have some exception！！！");
        }
        return cacheIdentify + DIDMD5Util.MD5(DIDMD5Util.MD5(cacheInfo) + DIDConfig.SALT).toUpperCase();
    }
}
