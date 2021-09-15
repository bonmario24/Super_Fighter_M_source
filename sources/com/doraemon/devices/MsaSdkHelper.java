package com.doraemon.devices;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.bun.miitmdid.core.ErrorCode;
import com.bun.miitmdid.core.JLibrary;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.supplier.IIdentifierListener;
import com.bun.supplier.IdSupplier;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.util.DeviceEmuCheckUtil;
import com.doraemon.util.Utils;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class MsaSdkHelper {
    private static MsaSdkHelper instance;
    /* access modifiers changed from: private */
    public Identifier mIdentifier;
    private final CopyOnWriteArrayList<IdentifierObserver> observers;

    private static class SingletonHolder {
        public static MsaSdkHelper instance = new MsaSdkHelper();

        private SingletonHolder() {
        }
    }

    public static MsaSdkHelper getInstance() {
        return SingletonHolder.instance;
    }

    private MsaSdkHelper() {
        this.observers = new CopyOnWriteArrayList<>();
        Application context = Utils.getApp();
        boolean isOverSea = false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (info.metaData != null) {
                isOverSea = info.metaData.getBoolean("doraemon_oaid");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (isOverSea) {
            System.out.println("MsaSdkHelper:return default oaid");
            this.mIdentifier = new Identifier();
        } else if (!DeviceEmuCheckUtil.mayOnEmulator(context) || Build.VERSION.SDK_INT > 19) {
            try {
                JLibrary.InitEntry(context);
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
            try {
                String errorMessage = "";
                switch (MdidSdkHelper.InitSdk(context, true, new IIdentifierListener() {
                    public void OnSupport(boolean isSupport, IdSupplier idSupplier) {
                        if (idSupplier == null) {
                            System.out.println("MsaSdkHelper--OnSupport:idSupplier is null");
                            return;
                        }
                        Identifier unused = MsaSdkHelper.this.mIdentifier = new Identifier(idSupplier.getOAID(), "", "", "");
                        MsaSdkHelper.this.notifyChange();
                    }
                })) {
                    case ErrorCode.INIT_ERROR_MANUFACTURER_NOSUPPORT:
                        errorMessage = "不支持的厂商";
                        break;
                    case ErrorCode.INIT_ERROR_DEVICE_NOSUPPORT:
                        errorMessage = "不支持的设备";
                        break;
                    case ErrorCode.INIT_ERROR_LOAD_CONFIGFILE:
                        errorMessage = "加载配置文件失败";
                        break;
                    case ErrorCode.INIT_HELPER_CALL_ERROR:
                        errorMessage = "反射调用失败";
                        break;
                }
                if (!CheckUtils.isNullOrEmpty(errorMessage)) {
                    notifyChange();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            System.out.println("MsaSdkHelper:doGetMdidSdkOAID:current sdk api < 19 and is imulator");
        }
    }

    /* access modifiers changed from: private */
    public void notifyChange() {
        if (this.observers.size() != 0) {
            Iterator<IdentifierObserver> it = this.observers.iterator();
            while (it.hasNext()) {
                IdentifierObserver observer = it.next();
                observer.onChange(getIdentifier());
                this.observers.remove(observer);
            }
        }
    }

    public void obtainMsnIdentifierWithTimer(IdentifierObserver observer, long delay) {
        if (observer != null) {
            if (this.mIdentifier != null) {
                observer.onChange(this.mIdentifier);
            } else if (this.observers.contains(observer)) {
            } else {
                if (delay > 0) {
                    this.observers.add(new TimeoutObserver(observer, delay));
                } else {
                    this.observers.add(observer);
                }
            }
        }
    }

    public void obtainMsnIdentifier(IdentifierObserver observer) {
        obtainMsnIdentifierWithTimer(observer, 0);
    }

    public String getOAID() {
        return getIdentifier().getOAID();
    }

    public String getVAID() {
        return getIdentifier().getVAID();
    }

    public String getAAID() {
        return getIdentifier().getAAID();
    }

    public Identifier getIdentifier() {
        if (this.mIdentifier == null) {
            return new Identifier();
        }
        return this.mIdentifier;
    }

    public void reset() {
        this.mIdentifier = null;
    }
}
