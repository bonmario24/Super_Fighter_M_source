package com.xhuyu.component.network;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.core.api.OnDownloadListener;
import com.xhuyu.component.core.manager.NetWorkManager;
import com.xhuyu.component.utils.GameUtil;
import com.xsdk.doraemon.okhttp.imp.OKHttpCallbackListener;
import com.xsdk.doraemon.okhttp.model.ExtensionInfo;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import org.json.JSONObject;

public class NetBasicUtil {
    public static void doRequestDuplicate(final GameSDKListener listener) {
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.DUPLICATE_URL, new HashMap(), new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, new Object[0]);
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    public static void doRequestFacebookShare(Activity activity, final GameSDKListener listener) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("tz", "+8");
        hashMap.put("game_version", GameUtil.getVersionName(activity));
        hashMap.put("game_build_version", GameUtil.getVersionCode(activity));
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.FACEBOOK_SHARE_URL, hashMap, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, extensionInfo.getExtData());
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    public static void doRequestFeedback(String feedbackContent, final GameSDKListener listener) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("feedback_content", feedbackContent);
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.FEEDBACK_URL, hashMap, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, extensionInfo.getExtData());
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    public static void postNotice(final GameSDKListener listener) {
        NetWorkManager.getInstance().doPostWithoutExtensionInfo(Urls.NOTICE_URL, (HashMap<String, Object>) null, new OKHttpCallbackListener() {
            public void onSuccess(JSONObject jsonObject, String message, ExtensionInfo extensionInfo) {
                listener.onSuccess(jsonObject, message, new Object[0]);
            }

            public void onFailure(int errorCode, String errorThrows, ExtensionInfo extensionInfo) {
                listener.onFail(errorThrows, errorCode);
            }
        });
    }

    public static void downloadImage(final String imageUrl, final OnDownloadListener listener) {
        if (CheckUtils.isNullOrEmpty(imageUrl)) {
            listener.onError("url is empty");
        } else {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(imageUrl).openConnection();
                        httpURLConnection.setConnectTimeout(8000);
                        httpURLConnection.setRequestMethod("GET");
                        if (httpURLConnection.getResponseCode() == 200) {
                            Bitmap bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                            if (bitmap != null) {
                                listener.onResult(bitmap);
                            } else {
                                listener.onError("bitmap is empty");
                            }
                        } else {
                            listener.onError(httpURLConnection.getResponseMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        listener.onError(e.toString());
                    }
                }
            }).start();
        }
    }
}
