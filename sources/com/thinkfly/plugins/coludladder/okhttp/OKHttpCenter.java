package com.thinkfly.plugins.coludladder.okhttp;

import com.doraemon.okhttp3.Call;
import com.doraemon.okhttp3.Callback;
import com.doraemon.okhttp3.FormBody;
import com.doraemon.okhttp3.MediaType;
import com.doraemon.okhttp3.OkHttpClient;
import com.doraemon.okhttp3.Request;
import com.doraemon.okhttp3.RequestBody;
import com.doraemon.okhttp3.Response;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.util.ShellAdbUtil;
import com.thinkfly.plugins.coludladder.log.Log;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class OKHttpCenter {
    private static final MediaType MEDIA_TYPE_FORM = MediaType.get("application/x-www-form-urlencoded");
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    public static int READ_TIME_OUT = 20000;
    public static int TIME_OUT = 20000;
    private static OkHttpClient okHttpClient = null;

    public interface OKHttpCallbackListener {
        void onFailure(String str);

        void onSuccess(JSONObject jSONObject);
    }

    public static JSONObject getSync(String url) {
        Request request = new Request.Builder().url(url).build();
        try {
            Response mResponse = getOkHttpClient().newCall(request).execute();
            if (mResponse.isSuccessful()) {
                return new JSONObject(mResponse.body().string());
            }
            return null;
        } catch (SocketTimeoutException e) {
            Log.m662d(Log.TAG, " SocketTimeoutException : " + e.getLocalizedMessage() + ShellAdbUtil.COMMAND_LINE_END + request.toString());
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (JSONException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static JSONObject postSync(String url, String params) {
        try {
            return new JSONObject(getOkHttpClient().newCall(new Request.Builder().url(url).post(RequestBody.create(MEDIA_TYPE_JSON, params)).build()).execute().body().string());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public static void getAsync(String url, final OKHttpCallbackListener listener) {
        getOkHttpClient().newCall(new Request.Builder().url(url).get().build()).enqueue(new Callback() {
            public void onResponse(Call arg0, Response response) throws IOException {
                String resultString = response.body().string();
                Log.m662d(Log.TAG, "response : " + resultString);
                try {
                    JSONObject jsonObject = new JSONObject(resultString);
                    try {
                        if (listener != null) {
                            listener.onSuccess(jsonObject);
                        }
                        JSONObject jSONObject = jsonObject;
                    } catch (JSONException e) {
                        e = e;
                        JSONObject jSONObject2 = jsonObject;
                        e.printStackTrace();
                        listener.onSuccess((JSONObject) null);
                    }
                } catch (JSONException e2) {
                    e = e2;
                    e.printStackTrace();
                    listener.onSuccess((JSONObject) null);
                }
            }

            public void onFailure(Call arg0, IOException exception) {
                if (listener != null) {
                    listener.onFailure(exception.getLocalizedMessage());
                }
            }
        });
    }

    public static void postAsync(String url, String params, final OKHttpCallbackListener listener) {
        if (!CheckUtils.isNullOrEmpty(params)) {
            getOkHttpClient().newCall(new Request.Builder().url(url).post(RequestBody.create(MEDIA_TYPE_JSON, params)).build()).enqueue(new Callback() {
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String resultString = response.body().string();
                        Log.m662d(Log.TAG, "postAsync onResponse string : " + resultString);
                        JSONObject jsonObject = new JSONObject(resultString);
                        if (listener != null) {
                            listener.onSuccess(jsonObject);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        onFailure(call, new IOException("Something is exception"));
                    }
                }

                public void onFailure(Call call, IOException exception) {
                    if (listener != null) {
                        listener.onFailure(exception.getLocalizedMessage());
                    }
                }
            });
        }
    }

    public static void postAsyncByForm(String url, String params, final OKHttpCallbackListener listener) {
        if (!CheckUtils.isNullOrEmpty(params)) {
            getOkHttpClient().newCall(new Request.Builder().url(url).post(FormBody.create(MEDIA_TYPE_FORM, params)).build()).enqueue(new Callback() {
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String resultString = response.body().string();
                        Log.m662d(Log.TAG, "postAsync onResponse string : " + resultString);
                        JSONObject jsonObject = new JSONObject(resultString);
                        if (listener != null) {
                            listener.onSuccess(jsonObject);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        onFailure(call, new IOException("Something is exception"));
                    }
                }

                public void onFailure(Call call, IOException exception) {
                    if (listener != null) {
                        listener.onFailure(exception.getLocalizedMessage());
                    }
                }
            });
        }
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            createOkHttpClient();
        }
        return okHttpClient;
    }

    public static void createOkHttpClient() {
        okHttpClient = new OkHttpClient.Builder().readTimeout((long) READ_TIME_OUT, TimeUnit.MILLISECONDS).connectTimeout((long) TIME_OUT, TimeUnit.MILLISECONDS).build();
    }
}
