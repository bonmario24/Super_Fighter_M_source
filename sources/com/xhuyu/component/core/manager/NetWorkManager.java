package com.xhuyu.component.core.manager;

import android.content.Context;
import com.eagle.mixsdk.sdk.did.ThinkFlyUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.ShareConstants;
import com.star.libtrack.obserable.OkHttpObserable;
import com.xhuyu.component.BuildConfig;
import com.xhuyu.component.core.api.HuYuApi;
import com.xhuyu.component.core.config.Constant;
import com.xhuyu.component.core.config.SDKConfig;
import com.xhuyu.component.network.LoginInterceptor;
import com.xhuyu.component.utils.CacheUtils;
import com.xhuyu.component.utils.GameUtil;
import com.xhuyu.component.utils.JsonUtil;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.okhttp.imp.CallBackListener;
import com.xsdk.doraemon.okhttp.imp.OKHttpCallbackListener;
import com.xsdk.doraemon.okhttp.model.ExtensionInfo;
import com.xsdk.doraemon.okhttp.util.OKHttpUtil;
import com.xsdk.doraemon.utils.ContextUtil;
import com.xsdk.doraemon.utils.NetWorkUtils;
import java.util.HashMap;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import org.json.JSONObject;

public class NetWorkManager {
    private static NetWorkManager mInstance;
    private String localDomainIP;
    private Context mContext;

    private NetWorkManager() {
    }

    public static NetWorkManager getInstance() {
        if (mInstance == null) {
            synchronized (NetWorkManager.class) {
                if (mInstance == null) {
                    mInstance = new NetWorkManager();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context) {
        this.mContext = context;
        OKHttpUtil.getInstance().initOkHttp(context, Constant.LOG_TAG, new LoginInterceptor());
        this.localDomainIP = CacheUtils.getCacheString(Constant.HuYuCacheKey.KEY_IP);
    }

    public void doPostWithoutExtensionInfo(String urlPath, OKHttpCallbackListener callback) {
        doPostAsyncRequest(urlPath, (HashMap<String, Object>) null, (ExtensionInfo) null, callback);
    }

    public void doPostWithoutExtensionInfo(String urlPath, HashMap<String, Object> dataHashMap, OKHttpCallbackListener callback) {
        doPostAsyncRequest(urlPath, dataHashMap, (ExtensionInfo) null, callback);
    }

    public void doPostAsyncRequest(String urlPath, HashMap<String, Object> dataHashMap, ExtensionInfo extensionInfo, final OKHttpCallbackListener callback) {
        if (this.mContext == null) {
            callback.onFailure(-4, "unknown error", extensionInfo);
        } else if (!NetWorkUtils.isNetAvailable(this.mContext)) {
            callback.onFailure(-4, getString("no_netwrok"), extensionInfo);
        } else {
            String jsonStr = JsonUtil.toJson(dataHashMap);
            String currentUrl = urlPath;
            if (!currentUrl.startsWith("http")) {
                currentUrl = this.localDomainIP + urlPath;
            }
            boolean hasPassword = dataHashMap != null && dataHashMap.containsKey("password");
            if (extensionInfo == null) {
                extensionInfo = new ExtensionInfo();
                extensionInfo.setTrackTag(currentUrl);
            }
            OKHttpUtil.getInstance().doPostAsyncRequestWithTag(currentUrl, jsonStr, new Headers.Builder().add(OkHttpObserable.OKHTTP_TAG, extensionInfo.getTrackTag()).add("StarDid", ThinkFlyUtils.getDeviceID(this.mContext)).add("password", hasPassword ? dataHashMap.get("password").toString() : "").add("StarPlatform", AppEventsConstants.EVENT_PARAM_VALUE_YES).add("StarVersion", BuildConfig.VERSION_NAME).add("StarGameId", CacheUtils.getCacheInteger(Constant.HuYuCacheKey.KEY_GAME_ID) + "").add("StarLanguage", SDKConfig.getInstance().getConfigValue(Constant.HuYuConfigKey.CURRENT_LANGUAGE_KEY) + "").add("StarToken", UserManager.getInstance().getStarToken()).add("StarBid", GameUtil.getPackageName(this.mContext)).build(), extensionInfo, new CallBackListener() {
                public void onSuccess(final JSONObject result, final Object args) {
                    HuYuApi.getInstance().getMainHandler().post(new Runnable() {
                        public void run() {
                            ExtensionInfo extInfo = OKHttpUtil.getInstance().convertObjToExtInfo(args);
                            if (result == null) {
                                callback.onFailure(-2, "data execption", extInfo);
                            } else if (JsonUtil.getInt(result, "status", -1) == 1) {
                                JSONObject data = JsonUtil.getJsonObject(result, "data");
                                if (data == null) {
                                    data = new JSONObject();
                                }
                                callback.onSuccess(data, JsonUtil.getString(result, ShareConstants.WEB_DIALOG_PARAM_MESSAGE), extInfo);
                            } else {
                                callback.onFailure(0, JsonUtil.getString(result, ShareConstants.WEB_DIALOG_PARAM_MESSAGE), extInfo);
                            }
                        }
                    });
                }

                public void onFailure(Callback var1, final Call call, final String exceptionMessage) {
                    HuYuApi.getInstance().getMainHandler().post(new Runnable() {
                        public void run() {
                            ExtensionInfo extInfo = OKHttpUtil.getInstance().convertObjToExtInfo(call != null ? call.request().tag() : null);
                            if (exceptionMessage.contains("java.net.ConnectException")) {
                                callback.onFailure(-4, NetWorkManager.this.getString("network_error"), extInfo);
                            } else {
                                callback.onFailure(-1, NetWorkManager.this.getString("service_error"), extInfo);
                            }
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public String getString(String resName) {
        return ReflectResource.getInstance(ContextUtil.getInstance().getApplicationContext()).getString(resName);
    }

    public void cancelRequest(ExtensionInfo extensionInfo) {
        OKHttpUtil.getInstance().cancelTag(extensionInfo);
    }

    public void cancelAllRequest() {
        OKHttpUtil.getInstance().cancelAll();
    }
}
