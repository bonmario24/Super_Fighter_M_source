package com.doraemon.devices;

import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.doraemon.devices.AdvertisingIdClient;
import com.doraemon.util.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AdvertisingIdHelper {
    private static final String PAK_GOOGLE_PLAY_SERVICE = "com.google.android.gms";
    /* access modifiers changed from: private */
    public AdvertisingIdClient.AdInfo mAdInfo;
    private final CopyOnWriteArrayList<IdentifierObserver> observers;
    private Thread thread;

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
        this.mAdInfo = null;
        this.observers = new CopyOnWriteArrayList<>();
        this.thread = new Thread(new Runnable() {
            public void run() {
                try {
                    if (AdvertisingIdHelper.checkPackageApp("com.google.android.gms")) {
                        AdvertisingIdClient.AdInfo unused = AdvertisingIdHelper.this.mAdInfo = AdvertisingIdClient.getAdvertisingIdInfo();
                    }
                    AdvertisingIdHelper.this.notifyChange();
                } catch (Exception e) {
                    e.printStackTrace();
                    AdvertisingIdClient.AdInfo unused2 = AdvertisingIdHelper.this.mAdInfo = null;
                    AdvertisingIdHelper.this.notifyChange();
                }
            }
        });
        start();
    }

    private void start() {
        if (this.thread != null) {
            this.thread.start();
        }
    }

    public static AdvertisingIdHelper getInstance() {
        return SingletonHolder.instance;
    }

    public String getAdvertisingId() {
        return this.mAdInfo != null ? this.mAdInfo.getId() : "";
    }

    public boolean hasGaid() {
        return this.mAdInfo != null;
    }

    public void getAdvertisingId(IdentifierObserver listener, long delay) {
        if (this.mAdInfo == null || TextUtils.isEmpty(this.mAdInfo.getId())) {
            if (listener != null && !this.observers.contains(listener)) {
                if (delay > 0) {
                    this.observers.add(new TimeoutObserver(listener, delay));
                } else {
                    this.observers.add(listener);
                }
            }
        } else if (listener != null) {
            listener.onChange(new Identifier().setGAID(this.mAdInfo.getId()));
        }
    }

    public void getAdvertisingId(IdentifierObserver listener) {
        getAdvertisingId(listener, 0);
    }

    /* access modifiers changed from: private */
    public void notifyChange() {
        if (this.observers.size() != 0) {
            Iterator<IdentifierObserver> it = this.observers.iterator();
            while (it.hasNext()) {
                IdentifierObserver observer = it.next();
                observer.onChange(new Identifier().setGAID(getAdvertisingId()));
                this.observers.remove(observer);
            }
        }
    }

    public static boolean isExitAid() {
        return checkPackageApp("com.google.android.gms");
    }

    public static boolean checkPackageApp(String packageName) {
        List<PackageInfo> pInfo = Utils.getApp().getApplicationContext().getPackageManager().getInstalledPackages(0);
        List<String> pName = new ArrayList<>();
        if (pInfo != null) {
            for (int i = 0; i < pInfo.size(); i++) {
                pName.add(pInfo.get(i).packageName);
            }
        }
        return pName.contains(packageName);
    }
}
