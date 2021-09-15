package com.eagle.mixsdk.sdk.did.presenter;

import android.content.Context;
import android.os.Build;
import com.bun.miitmdid.core.ErrorCode;
import com.bun.miitmdid.core.JLibrary;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.supplier.IIdentifierListener;
import com.bun.supplier.IdSupplier;
import com.doraemon.util.DeviceEmuCheckUtil;
import com.eagle.mixsdk.sdk.did.bean.IdentifierBean;
import com.eagle.mixsdk.sdk.did.utils.CheckUtils;

public class MsnIdentifierPresenter {
    private static MsnIdentifierPresenter instance;
    /* access modifiers changed from: private */
    public IdentifierBean mIdentifier;
    /* access modifiers changed from: private */
    public OnGetIdentifierListener mListener;

    public interface OnGetIdentifierListener {
        void onComplete(IdentifierBean identifierBean);

        void onError(int i, String str);
    }

    public static MsnIdentifierPresenter getInstance() {
        if (instance == null) {
            synchronized (MsnIdentifierPresenter.class) {
                if (instance == null) {
                    instance = new MsnIdentifierPresenter();
                }
            }
        }
        return instance;
    }

    public String getAAID() {
        return getIdentifier().getAAID();
    }

    public IdentifierBean getIdentifier() {
        return this.mIdentifier == null ? new IdentifierBean() : this.mIdentifier;
    }

    public IdentifierBean getIdentifier(Context context) {
        if (this.mIdentifier == null) {
            obtainMsnIdentifier(context, this.mListener);
        }
        return this.mIdentifier;
    }

    public String getOAID() {
        return getIdentifier().getOAID();
    }

    public String getVAID() {
        return getIdentifier().getVAID();
    }

    public void obtainMsnIdentifier(Context context, OnGetIdentifierListener onGetIdentifierListener) {
        this.mListener = onGetIdentifierListener;
        if (this.mIdentifier != null) {
            if (this.mListener != null) {
                this.mListener.onComplete(this.mIdentifier);
            }
        } else if (!DeviceEmuCheckUtil.mayOnEmulator(context) || Build.VERSION.SDK_INT > 19) {
            try {
                JLibrary.InitEntry(context);
            } catch (Exception e) {
            }
            try {
                int InitSdk = MdidSdkHelper.InitSdk(context, true, new IIdentifierListener() {
                    public void OnSupport(boolean z, IdSupplier idSupplier) {
                        if (idSupplier == null) {
                            System.out.println("EagleSDK--OnSupport:idSupplier is null");
                            if (MsnIdentifierPresenter.this.mListener != null) {
                                MsnIdentifierPresenter.this.mListener.onError(-1, "idSupplier is null");
                                return;
                            }
                            return;
                        }
                        IdentifierBean unused = MsnIdentifierPresenter.this.mIdentifier = new IdentifierBean(idSupplier.getOAID(), idSupplier.getVAID(), idSupplier.getAAID());
                        if (MsnIdentifierPresenter.this.mListener != null) {
                            MsnIdentifierPresenter.this.mListener.onComplete(MsnIdentifierPresenter.this.mIdentifier);
                        }
                    }
                });
                String str = "";
                switch (InitSdk) {
                    case ErrorCode.INIT_ERROR_MANUFACTURER_NOSUPPORT:
                        str = "不支持的厂商";
                        break;
                    case ErrorCode.INIT_ERROR_DEVICE_NOSUPPORT:
                        str = "不支持的设备";
                        break;
                    case ErrorCode.INIT_ERROR_LOAD_CONFIGFILE:
                        str = "加载配置文件失败";
                        break;
                    case ErrorCode.INIT_HELPER_CALL_ERROR:
                        str = "反射调用失败";
                        break;
                }
                if (!CheckUtils.isNullOrEmpty(str) && this.mListener != null) {
                    this.mListener.onError(InitSdk, str);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (this.mListener != null) {
                    this.mListener.onError(-1, e2.toString());
                }
            }
        } else {
            System.out.println("EagleSDK--doGetMdidSdkOAID:current sdk api < 19 and is imulator");
            if (this.mListener != null) {
                this.mListener.onError(-1, "Emulators smaller than 4.4 are not supported");
            }
        }
    }

    public void reset() {
        this.mIdentifier = null;
    }

    public void setOnGetIdentifierListener(OnGetIdentifierListener onGetIdentifierListener) {
        this.mListener = onGetIdentifierListener;
    }
}
