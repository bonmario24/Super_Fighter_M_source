package com.eagle.mixsdk.sdk.manger;

import android.app.Activity;
import android.text.TextUtils;
import com.doraemon.okhttp3.Call;
import com.doraemon.okhttp3.Callback;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.dialog.LoadingDialog;
import com.eagle.mixsdk.sdk.okhttp.imp.CallBackListener;
import com.eagle.mixsdk.sdk.okhttp.imp.OKHttpCallbackListener;
import com.eagle.mixsdk.sdk.okhttp.model.ExtensionInfo;
import com.eagle.mixsdk.sdk.okhttp.util.OKHttpUtil;
import com.eagle.mixsdk.sdk.utils.OverseasUtil;
import com.eagle.mixsdk.sdk.utils.ResPluginUtil;
import com.eagle.mixsdk.sdk.utils.domain.DoNetDisposeUtil;
import org.json.JSONObject;

public class EagleLoopDomainManager {
    private static EagleLoopDomainManager instance = new EagleLoopDomainManager();
    private boolean isShowDialog = false;
    /* access modifiers changed from: private */
    public LoadingDialog mProgressDialog;

    private EagleLoopDomainManager() {
    }

    public static EagleLoopDomainManager getInstance() {
        return instance;
    }

    public void doGetAsyncRequest(final String notInHostnamePath, Object args, final OKHttpCallbackListener listener) {
        String currentUseDomainUrl = DoNetDisposeUtil.getCurrentUseDomainUrl();
        if (TextUtils.isEmpty(currentUseDomainUrl)) {
            System.out.println("请求失败！当前的主机域名地址是空的!");
            listener.onFailure(ResPluginUtil.getStringByName("xeagle_network_failure"), (ExtensionInfo) null);
            return;
        }
        ExtensionInfo extensionInfo = new ExtensionInfo();
        extensionInfo.setUrlTag(currentUseDomainUrl);
        extensionInfo.setExtData(args);
        OKHttpUtil.getInstance().doGetAsyncRequestWithTag(currentUseDomainUrl + notInHostnamePath, extensionInfo, new CallBackListener() {
            public void onSuccess(JSONObject jsonObject, Object args) {
                if (listener != null) {
                    listener.onSuccess(jsonObject, OKHttpUtil.getInstance().convertObjToExtInfo(args));
                }
                EagleLoopDomainManager.this.dismissLoadingDialog();
            }

            public void onFailure(Callback callback, Call call, String exceptionMessage) {
                if (OverseasUtil.isOverseasChannel() || (!exceptionMessage.contains("java.net.SocketTimeoutException") && !exceptionMessage.contains("java.net.ConnectException"))) {
                    if (listener != null) {
                        listener.onFailure(exceptionMessage, OKHttpUtil.getInstance().convertObjToExtInfo(call != null ? call.request().tag() : null));
                    }
                    EagleLoopDomainManager.this.dismissLoadingDialog();
                    return;
                }
                DoNetDisposeUtil.disposeReconnection(1, notInHostnamePath, call, callback, listener);
            }
        });
    }

    public void doPostAsyncRequest(final String notInHostnamePath, String params, Object args, final OKHttpCallbackListener listener) {
        if (!TextUtils.isEmpty(params)) {
            String currentUseDomainUrl = DoNetDisposeUtil.getCurrentUseDomainUrl();
            if (TextUtils.isEmpty(currentUseDomainUrl)) {
                System.out.println("请求失败！当前的主机域名地址是空的!");
                listener.onFailure(ResPluginUtil.getStringByName("xeagle_network_failure"), (ExtensionInfo) null);
                return;
            }
            ExtensionInfo extensionInfo = new ExtensionInfo();
            extensionInfo.setUrlTag(currentUseDomainUrl);
            extensionInfo.setExtData(args);
            OKHttpUtil.getInstance().doPostAsyncRequestWithTag(currentUseDomainUrl + notInHostnamePath, params, extensionInfo, new CallBackListener() {
                public void onSuccess(JSONObject jsonObject, Object args) {
                    if (listener != null) {
                        listener.onSuccess(jsonObject, OKHttpUtil.getInstance().convertObjToExtInfo(args));
                    }
                    EagleLoopDomainManager.this.dismissLoadingDialog();
                }

                public void onFailure(Callback callback, Call call, String exceptionMessage) {
                    if (OverseasUtil.isOverseasChannel() || (!exceptionMessage.contains("java.net.SocketTimeoutException") && !exceptionMessage.contains("java.net.ConnectException"))) {
                        if (listener != null) {
                            listener.onFailure(exceptionMessage, OKHttpUtil.getInstance().convertObjToExtInfo(call != null ? call.request().tag() : null));
                        }
                        EagleLoopDomainManager.this.dismissLoadingDialog();
                        return;
                    }
                    DoNetDisposeUtil.disposeReconnection(2, notInHostnamePath, call, callback, listener);
                }
            });
        }
    }

    public void doPostAsyncByFormRequest(final String notInHostnamePath, String params, Object args, final OKHttpCallbackListener listener) {
        if (!TextUtils.isEmpty(params)) {
            String currentUseDomainUrl = DoNetDisposeUtil.getCurrentUseDomainUrl();
            if (TextUtils.isEmpty(currentUseDomainUrl)) {
                System.out.println("请求失败！当前的主机域名地址是空的!");
                listener.onFailure(ResPluginUtil.getStringByName("xeagle_network_failure"), (ExtensionInfo) null);
                return;
            }
            ExtensionInfo extensionInfo = new ExtensionInfo();
            extensionInfo.setUrlTag(currentUseDomainUrl);
            extensionInfo.setExtData(args);
            OKHttpUtil.getInstance().doPostAsyncByFormRequestWithTag(currentUseDomainUrl + notInHostnamePath, params, extensionInfo, new CallBackListener() {
                public void onSuccess(JSONObject jsonObject, Object args) {
                    if (listener != null) {
                        listener.onSuccess(jsonObject, OKHttpUtil.getInstance().convertObjToExtInfo(args));
                    }
                    EagleLoopDomainManager.this.dismissLoadingDialog();
                }

                public void onFailure(Callback callback, Call call, String exceptionMessage) {
                    if (OverseasUtil.isOverseasChannel() || (!exceptionMessage.contains("java.net.SocketTimeoutException") && !exceptionMessage.contains("java.net.ConnectException"))) {
                        if (listener != null) {
                            listener.onFailure(exceptionMessage, OKHttpUtil.getInstance().convertObjToExtInfo(call != null ? call.request().tag() : null));
                        }
                        EagleLoopDomainManager.this.dismissLoadingDialog();
                        return;
                    }
                    DoNetDisposeUtil.disposeReconnection(2, notInHostnamePath, call, callback, listener);
                }
            });
        }
    }

    public boolean isShowDialog() {
        return this.isShowDialog;
    }

    public void showLoadingDialog(final String message) {
        if (!this.isShowDialog) {
            this.isShowDialog = true;
            EagleSDK.getInstance().runOnMainThread(new Runnable() {
                public void run() {
                    Activity activity = EagleSDK.getInstance().getContext();
                    if (activity != null && !activity.isFinishing()) {
                        LoadingDialog unused = EagleLoopDomainManager.this.mProgressDialog = new LoadingDialog(activity);
                        EagleLoopDomainManager.this.mProgressDialog.setCancelable(false);
                        EagleLoopDomainManager.this.mProgressDialog.setCanceledOnTouchOutside(false);
                        EagleLoopDomainManager.this.mProgressDialog.setTitle((CharSequence) message);
                        if (EagleLoopDomainManager.getInstance().isShowDialog()) {
                            EagleLoopDomainManager.this.mProgressDialog.show();
                        }
                    }
                }
            });
        }
    }

    public void dismissLoadingDialog() {
        if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
            this.mProgressDialog = null;
        }
        this.isShowDialog = false;
    }
}
