package com.eagle.mixsdk.sdk.okhttp.util;

import android.content.Context;
import com.doraemon.okhttp3.Call;
import com.doraemon.okhttp3.Callback;
import com.doraemon.okhttp3.FormBody;
import com.doraemon.okhttp3.MediaType;
import com.doraemon.okhttp3.OkHttpClient;
import com.doraemon.okhttp3.Request;
import com.doraemon.okhttp3.RequestBody;
import com.doraemon.okhttp3.Response;
import com.doraemon.p027eg.CommonUtil;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.okhttp.imp.CallBackListener;
import com.eagle.mixsdk.sdk.okhttp.logger.HttpLoggingInterceptor;
import com.eagle.mixsdk.sdk.okhttp.logger.OkLogger;
import com.eagle.mixsdk.sdk.okhttp.model.ExtensionInfo;
import com.eagle.mixsdk.sdk.utils.ResPluginUtil;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.json.JSONException;
import org.json.JSONObject;

public class OKHttpUtil {
    private static final long CONNECT_MILLISECONDS = 8000;
    private static final long DEFAULT_TIME_OUT_MILLISECONDS = 8000;
    private static final OKHttpUtil instance = new OKHttpUtil();
    private final MediaType MEDIA_TYPE_FORM = MediaType.get("application/x-www-form-urlencoded");
    private final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient okHttpClient = null;

    private OKHttpUtil() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.readTimeout(8000, TimeUnit.MILLISECONDS);
        okBuilder.writeTimeout(8000, TimeUnit.MILLISECONDS);
        okBuilder.connectTimeout(8000, TimeUnit.MILLISECONDS);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("EagleOkHttp");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        OkLogger.debug(true);
        okBuilder.addInterceptor(loggingInterceptor);
        this.okHttpClient = okBuilder.build();
    }

    public static OKHttpUtil getInstance() {
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        return this.okHttpClient;
    }

    public void doGetAsyncRequest(String url, CallBackListener callbackListener) {
        doGetAsyncRequestWithTag(url, (ExtensionInfo) null, callbackListener);
    }

    public void doGetAsyncRequestWithTag(String url, ExtensionInfo extensionInfo, final CallBackListener callbackListener) {
        if (!isOnline(EagleSDK.getInstance().getApplication())) {
            callbackListener.onFailure((Callback) null, (Call) null, ResPluginUtil.getStringByName("xeagle_network_without_links"));
            return;
        }
        Request.Builder url2 = new Request.Builder().url(url);
        Object obj = extensionInfo;
        if (extensionInfo == null) {
            obj = "";
        }
        getOkHttpClient().newCall(url2.tag(obj).get().build()).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    try {
                        if (callbackListener != null) {
                            callbackListener.onSuccess(jsonObject, call.request().tag());
                        }
                        JSONObject jSONObject = jsonObject;
                    } catch (JSONException e) {
                        e = e;
                        JSONObject jSONObject2 = jsonObject;
                        e.printStackTrace();
                        onFailure(call, new IOException("json exception:" + e.toString()));
                    }
                } catch (JSONException e2) {
                    e = e2;
                    e.printStackTrace();
                    onFailure(call, new IOException("json exception:" + e.toString()));
                }
            }

            public void onFailure(Call call, IOException exception) {
                callbackListener.onFailure(this, call, exception.toString());
            }
        });
    }

    public void doPostAsyncRequest(String url, String params, CallBackListener callbackListener) {
        doPostAsyncRequestWithTag(url, params, (ExtensionInfo) null, callbackListener);
    }

    public void doPostAsyncRequestWithTag(String url, String params, ExtensionInfo extensionInfo, final CallBackListener callbackListener) {
        if (!isOnline(EagleSDK.getInstance().getApplication())) {
            callbackListener.onFailure((Callback) null, (Call) null, ResPluginUtil.getStringByName("xeagle_network_without_links"));
            return;
        }
        Request.Builder post = new Request.Builder().url(url).post(RequestBody.create(this.MEDIA_TYPE_JSON, params));
        Object obj = extensionInfo;
        if (extensionInfo == null) {
            obj = "";
        }
        getOkHttpClient().newCall(post.tag(obj).build()).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (callbackListener != null) {
                        callbackListener.onSuccess(jsonObject, call.request().tag());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    onFailure(call, new IOException("Something is exception"));
                }
            }

            public void onFailure(Call call, IOException exception) {
                callbackListener.onFailure(this, call, exception.toString());
            }
        });
    }

    public void doPostAsyncByFormRequest(String url, String params, CallBackListener callbackListener) {
        doPostAsyncByFormRequestWithTag(url, params, (ExtensionInfo) null, callbackListener);
    }

    public void doPostAsyncByFormRequestWithTag(String url, String params, ExtensionInfo extensionInfo, final CallBackListener callbackListener) {
        if (!isOnline(EagleSDK.getInstance().getApplication())) {
            callbackListener.onFailure((Callback) null, (Call) null, ResPluginUtil.getStringByName("xeagle_network_without_links"));
            return;
        }
        Request.Builder post = new Request.Builder().url(url).post(FormBody.create(this.MEDIA_TYPE_FORM, params));
        Object obj = extensionInfo;
        if (extensionInfo == null) {
            obj = "";
        }
        getOkHttpClient().newCall(post.tag(obj).build()).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (callbackListener != null) {
                        callbackListener.onSuccess(jsonObject, call.request().tag());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    onFailure(call, new IOException("Something is exception"));
                }
            }

            public void onFailure(Call call, IOException exception) {
                callbackListener.onFailure(this, call, exception.toString());
            }
        });
    }

    public boolean isOnline(Context context) {
        return CommonUtil.isNetAvailable();
    }

    public ExtensionInfo convertObjToExtInfo(Object tagObj) {
        if (tagObj == null || !(tagObj instanceof ExtensionInfo)) {
            return null;
        }
        return (ExtensionInfo) tagObj;
    }
}
