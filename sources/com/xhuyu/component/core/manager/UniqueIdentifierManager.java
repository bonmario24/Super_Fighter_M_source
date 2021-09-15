package com.xhuyu.component.core.manager;

import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.utils.UniqueIdentifierUtil;
import com.xingfei.doraemon.deviceinfo.AdvertisingIdClient;
import com.xingfei.doraemon.deviceinfo.AdvertisingIdHelper;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

public class UniqueIdentifierManager {
    private static UniqueIdentifierManager mInstance;
    AdvertisingIdHelper.AIdListener idListener = new AdvertisingIdHelper.AIdListener() {
        public void onSuccess(AdvertisingIdClient.AdInfo adInfo) {
            if (adInfo != null) {
                String googleAdvertisingId = adInfo.getId();
                if (!CheckUtil.isEmpty(googleAdvertisingId)) {
                    UniqueIdentifierUtil.saveGoogleAdvertisingId(googleAdvertisingId);
                    UniqueIdentifierUtil.saveUniqueID(googleAdvertisingId);
                    UniqueIdentifierManager.this.setGoogleAdvertisingId(googleAdvertisingId);
                }
            }
        }

        public void onFailed(String s) {
            SDKLoggerUtil.getLogger().mo19502e("谷歌广告ID获取失败 msg:" + s, new Object[0]);
        }
    };
    private String mGoogleAdvertisingId = "";
    private String mUniqueId;

    private UniqueIdentifierManager() {
    }

    public static UniqueIdentifierManager getInstance() {
        if (mInstance == null) {
            synchronized (UniqueIdentifierManager.class) {
                if (mInstance == null) {
                    mInstance = new UniqueIdentifierManager();
                }
            }
        }
        return mInstance;
    }

    public void initUniqueID() {
        if (CheckUtil.isEmpty(UniqueIdentifierUtil.getCacheGUID())) {
            UniqueIdentifierUtil.saveGUID();
        }
        doGetAdvertisingId();
    }

    public String getGoogleAdvertisingId() {
        if (CheckUtil.isEmpty(this.mGoogleAdvertisingId)) {
            String advertisingId = AdvertisingIdHelper.getInstance().getAdvertisingId();
            if (!CheckUtil.isEmpty(advertisingId)) {
                this.mGoogleAdvertisingId = advertisingId;
                UniqueIdentifierUtil.saveGoogleAdvertisingId(advertisingId);
            } else {
                doGetAdvertisingId();
                return "";
            }
        }
        return this.mGoogleAdvertisingId;
    }

    private void doGetAdvertisingId() {
        AdvertisingIdHelper.getInstance().getAdvertisingId(this.idListener);
    }

    private String getUniqueIdentifier() {
        String cacheUniqueID = UniqueIdentifierUtil.getCacheUniqueID();
        String advertisingId = CheckUtil.isEmpty(this.mGoogleAdvertisingId) ? UniqueIdentifierUtil.getCacheGoogleAdvertisingId() : this.mGoogleAdvertisingId;
        if (CheckUtils.isNullOrEmpty(cacheUniqueID)) {
            String uniqueID = advertisingId;
            if (CheckUtil.isEmpty(uniqueID)) {
                String androidID = UniqueIdentifierUtil.getAndroidID();
                String cacheGUID = UniqueIdentifierUtil.getCacheGUID();
                if (CheckUtil.isEmpty(androidID)) {
                    uniqueID = cacheGUID;
                } else {
                    uniqueID = androidID;
                }
            }
            UniqueIdentifierUtil.saveUniqueID(uniqueID);
            return uniqueID;
        } else if (CheckUtil.isEmpty(advertisingId) || cacheUniqueID.equals(advertisingId)) {
            return cacheUniqueID;
        } else {
            UniqueIdentifierUtil.saveUniqueID(advertisingId);
            return advertisingId;
        }
    }

    public String getUniqueID() {
        if (CheckUtil.isEmpty(this.mUniqueId)) {
            this.mUniqueId = getUniqueIdentifier();
        } else if (!CheckUtil.isEmpty(this.mGoogleAdvertisingId) && !this.mUniqueId.equals(this.mGoogleAdvertisingId)) {
            this.mUniqueId = this.mGoogleAdvertisingId;
        }
        return this.mUniqueId;
    }

    /* access modifiers changed from: private */
    public void setGoogleAdvertisingId(String mGoogleAdvertisingId2) {
        this.mGoogleAdvertisingId = mGoogleAdvertisingId2;
    }
}
