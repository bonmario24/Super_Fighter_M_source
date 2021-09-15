package com.eagle.mixsdk.sdk.utils.domain;

import android.text.TextUtils;
import com.doraemon.okhttp3.Call;
import com.doraemon.okhttp3.Callback;
import com.doraemon.okhttp3.RequestBody;
import com.doraemon.util.ToastUtils;
import com.eagle.mixsdk.sdk.manger.EagleLoopDomainManager;
import com.eagle.mixsdk.sdk.okhttp.imp.OKHttpCallbackListener;
import com.eagle.mixsdk.sdk.okhttp.model.ExtensionInfo;
import com.eagle.mixsdk.sdk.okhttp.util.NetUtil;
import com.eagle.mixsdk.sdk.okhttp.util.OKHttpUtil;
import com.eagle.mixsdk.sdk.utils.ResPluginUtil;

public class DoNetDisposeUtil {
    public static final int GET_TYPE = 1;
    public static final int POST_TYPE = 2;
    private static int indexToggle = 0;

    public static String getCurrentUseDomainUrl() {
        indexToggle = 0;
        String currentUseDomainUrl = DomainsCacheUtil.getCurrentUseDomainHostname(true);
        System.out.println("┏====================================获取域名STAR====================================┓");
        System.out.println("currentUseDomainUrl:" + currentUseDomainUrl);
        System.out.println("┗====================================获取域名END=====================================┛");
        return currentUseDomainUrl;
    }

    public static void disposeReconnection(int requestMethod, String notInHostnamePath, Call responseCall, Callback callback, OKHttpCallbackListener listener) {
        boolean z;
        EagleLoopDomainManager.getInstance().showLoadingDialog(ResPluginUtil.getStringByName("xeagle_loading"));
        Object tag = responseCall.request().tag();
        if (tag instanceof ExtensionInfo) {
            ExtensionInfo extensionInfo = (ExtensionInfo) tag;
            System.out.println("获取到网络链式附加参数信息:" + extensionInfo.getUrlTag());
            pingHost(extensionInfo.getUrlTag());
            String urlTag = extensionInfo.getUrlTag();
            if (indexToggle == 0) {
                z = true;
            } else {
                z = false;
            }
            DomainsCacheUtil.updateDomainState(urlTag, z);
            indexToggle++;
            System.out.println("┏================================================获取可使用域名STAR================================================┓");
            String currDomainUrl = DomainsCacheUtil.getCurrentUseDomainHostname(false);
            if (!TextUtils.isEmpty(currDomainUrl)) {
                System.out.println("可使用域名:" + currDomainUrl);
                System.out.println("备用域名重新连接请求");
                if (requestMethod == 2) {
                    RequestBody body = responseCall.request().body();
                    extensionInfo.setUrlTag(currDomainUrl);
                    OKHttpUtil.getInstance().getOkHttpClient().newCall(responseCall.request().newBuilder().url(currDomainUrl + notInHostnamePath).post(body).tag(extensionInfo).build()).enqueue(callback);
                } else if (requestMethod == 1) {
                    extensionInfo.setUrlTag(currDomainUrl);
                    OKHttpUtil.getInstance().getOkHttpClient().newCall(responseCall.request().newBuilder().url(currDomainUrl + notInHostnamePath).get().tag(extensionInfo).build()).enqueue(callback);
                }
            } else {
                callbackResult(listener, extensionInfo);
            }
            System.out.println("┗================================================获取可使用域名END================================================┛");
            return;
        }
        callbackResult(listener, (ExtensionInfo) null);
    }

    private static void callbackResult(OKHttpCallbackListener listener, ExtensionInfo extensionInfo) {
        EagleLoopDomainManager.getInstance().dismissLoadingDialog();
        System.out.println(" 没有可使用的域名，结束超时轮循");
        indexToggle = 0;
        String xeagle_network_failure = ResPluginUtil.getStringByName("xeagle_network_failure");
        ToastUtils.showLong((CharSequence) xeagle_network_failure);
        if (listener != null) {
            listener.onFailure(xeagle_network_failure, extensionInfo);
        }
    }

    private static void pingHost(final String targetHost) {
        new Thread(new Runnable() {
            public void run() {
                String host = "";
                if (targetHost.startsWith("http://")) {
                    host = targetHost.replace("http://", "");
                } else if (targetHost.startsWith("https://")) {
                    host = targetHost.replace("https://", "");
                }
                String[] eagleResult = NetUtil.pingHost(4, host);
                String[] checkResult = NetUtil.pingHost(4, "www.baidu.com");
                if (eagleResult != null && checkResult != null) {
                    TrackNetEventUtil.reportEvent(eagleResult, checkResult);
                }
            }
        }).start();
    }
}
