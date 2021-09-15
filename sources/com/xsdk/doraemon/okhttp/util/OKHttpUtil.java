package com.xsdk.doraemon.okhttp.util;

import android.content.Context;
import com.xsdk.doraemon.okhttp.imp.CallBackListener;
import com.xsdk.doraemon.okhttp.logger.HttpLoggingInterceptor;
import com.xsdk.doraemon.okhttp.logger.OkLogger;
import com.xsdk.doraemon.okhttp.model.ExtensionInfo;
import com.xsdk.doraemon.utils.NetWorkUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class OKHttpUtil {
    private static final long CONNECT_MILLISECONDS = 8000;
    private static final long DEFAULT_TIME_OUT_MILLISECONDS = 8000;
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String PARAMETER_SEPARATOR = "&";
    private static final OKHttpUtil instance = new OKHttpUtil();
    private final MediaType MEDIA_TYPE_FORM = MediaType.get("application/x-www-form-urlencoded");
    private final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private boolean hasInit;
    private Context mContext;
    private OkHttpClient okHttpClient = null;

    private OKHttpUtil() {
    }

    public static OKHttpUtil getInstance() {
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        return this.okHttpClient;
    }

    public void initOkHttp(Context context, String tagName, Interceptor... argsInterceptor) {
        if (!this.hasInit) {
            this.mContext = context;
            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            okBuilder.readTimeout(8000, TimeUnit.MILLISECONDS);
            okBuilder.writeTimeout(8000, TimeUnit.MILLISECONDS);
            okBuilder.connectTimeout(8000, TimeUnit.MILLISECONDS);
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(tagName + "-Http");
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
            loggingInterceptor.setColorLevel(Level.INFO);
            OkLogger.debug(tagName + "-Http", true);
            okBuilder.addInterceptor(loggingInterceptor);
            if (argsInterceptor != null) {
                for (Interceptor interceptor : argsInterceptor) {
                    okBuilder.addInterceptor(interceptor);
                }
            }
            this.okHttpClient = okBuilder.build();
            this.hasInit = true;
        }
    }

    public void doGetAsyncRequest(String url, CallBackListener callbackListener) {
        doGetAsyncRequestWithTag(url, (ExtensionInfo) null, callbackListener);
    }

    public void doGetAsyncRequestWithTag(String url, ExtensionInfo extensionInfo, final CallBackListener callbackListener) {
        if (!NetWorkUtils.isNetAvailable(this.mContext)) {
            callbackListener.onFailure((Callback) null, (Call) null, ResPluginUtil.getStringByName(this.mContext, "no_netwrok"));
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

    public void doPostAsyncRequest(String url, String params, Headers headers, CallBackListener callbackListener) {
        doPostAsyncRequestWithTag(url, params, headers, (ExtensionInfo) null, callbackListener);
    }

    public void doPostAsyncRequest(String url, String params, ExtensionInfo extensionInfo, CallBackListener callbackListener) {
        doPostAsyncRequestWithTag(url, params, (Headers) null, extensionInfo, callbackListener);
    }

    public void doPostAsyncRequest(String url, String params, CallBackListener callbackListener) {
        doPostAsyncRequestWithTag(url, params, (Headers) null, (ExtensionInfo) null, callbackListener);
    }

    public void doPostAsyncRequestWithTag(String url, String params, Headers headers, ExtensionInfo extensionInfo, final CallBackListener callbackListener) {
        if (!NetWorkUtils.isNetAvailable(this.mContext)) {
            callbackListener.onFailure((Callback) null, (Call) null, ResPluginUtil.getStringByName(this.mContext, "no_netwrok"));
            return;
        }
        Request.Builder post = new Request.Builder().url(url).headers(headers).post(RequestBody.create(this.MEDIA_TYPE_JSON, params));
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
        if (!NetWorkUtils.isNetAvailable(this.mContext)) {
            callbackListener.onFailure((Callback) null, (Call) null, ResPluginUtil.getStringByName(this.mContext, "no_netwrok"));
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

    public ExtensionInfo convertObjToExtInfo(Object tagObj) {
        if (tagObj == null || !(tagObj instanceof ExtensionInfo)) {
            return null;
        }
        return (ExtensionInfo) tagObj;
    }

    public String urlParamsFormat(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            Object val = params.get(key);
            if (val == null) {
                val = "";
            }
            if (sb.length() > 0) {
                sb.append(PARAMETER_SEPARATOR);
            }
            sb.append(key).append(NAME_VALUE_SEPARATOR).append(val);
        }
        return "?" + sb.toString();
    }

    public String urlParamsFormatWithEncodeUrl(Map<String, String> params, String charset) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            String val = params.get(key);
            if (val == null) {
                val = "";
            }
            String encodedName = URLEncoder.encode(key, charset);
            String encodedValue = URLEncoder.encode(val, charset);
            if (sb.length() > 0) {
                sb.append(PARAMETER_SEPARATOR);
            }
            sb.append(encodedName).append(NAME_VALUE_SEPARATOR).append(encodedValue);
        }
        return "?" + sb.toString();
    }

    public void cancelAll() {
        if (this.okHttpClient != null) {
            for (Call call : this.okHttpClient.dispatcher().queuedCalls()) {
                call.cancel();
            }
            for (Call call2 : this.okHttpClient.dispatcher().runningCalls()) {
                call2.cancel();
            }
        }
    }

    public void cancelTag(Object tag) {
        if (tag != null) {
            for (Call call : getOkHttpClient().dispatcher().queuedCalls()) {
                if (tag.equals(call.request().tag())) {
                    call.cancel();
                }
            }
            for (Call call2 : getOkHttpClient().dispatcher().runningCalls()) {
                if (tag.equals(call2.request().tag())) {
                    call2.cancel();
                }
            }
        }
    }
}
