package com.xingfei.doraemon.deviceinfo;

import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.doraemon.util.Utils;
import com.xingfei.doraemon.deviceinfo.AdvertisingIdClient;
import java.util.ArrayList;
import java.util.List;

public class AdvertisingIdHelper {
    private static final String PAK_GOOGLE_PLAY_SERVICE = "com.google.android.gms";
    /* access modifiers changed from: private */
    public AdvertisingIdClient.AdInfo mAdInfo;

    public interface AIdListener {
        void onFailed(String str);

        void onSuccess(AdvertisingIdClient.AdInfo adInfo);
    }

    private static class SingletonHolder {
        public static AdvertisingIdHelper instance = new AdvertisingIdHelper();

        private SingletonHolder() {
        }
    }

    private AdvertisingIdHelper() {
    }

    public static boolean checkPackageApp(String str) {
        List<PackageInfo> installedPackages = Utils.getApp().getApplicationContext().getPackageManager().getInstalledPackages(0);
        ArrayList arrayList = new ArrayList();
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                arrayList.add(installedPackages.get(i).packageName);
            }
        }
        return arrayList.contains(str);
    }

    public static AdvertisingIdHelper getInstance() {
        return SingletonHolder.instance;
    }

    public static boolean isExitAid() {
        return checkPackageApp("com.google.android.gms");
    }

    public String getAdvertisingId() {
        return this.mAdInfo != null ? this.mAdInfo.getId() : "";
    }

    public void getAdvertisingId(final AIdListener aIdListener) {
        if (this.mAdInfo == null || TextUtils.isEmpty(this.mAdInfo.getId())) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (AdvertisingIdHelper.checkPackageApp("com.google.android.gms")) {
                            AdvertisingIdClient.AdInfo unused = AdvertisingIdHelper.this.mAdInfo = AdvertisingIdClient.getAdvertisingIdInfo();
                        } else {
                            AdvertisingIdClient.AdInfo unused2 = AdvertisingIdHelper.this.mAdInfo = new AdvertisingIdClient.AdInfo("", false);
                        }
                        if (aIdListener != null) {
                            aIdListener.onSuccess(AdvertisingIdHelper.this.mAdInfo);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (aIdListener != null) {
                            aIdListener.onFailed(e.getMessage());
                        }
                    }
                }
            }).start();
        } else if (aIdListener != null) {
            aIdListener.onSuccess(this.mAdInfo);
        }
    }

    public int hashCode() {
        return super.hashCode();
    }
}
