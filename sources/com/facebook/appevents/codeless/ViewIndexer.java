package com.facebook.appevents.codeless;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.appevents.codeless.internal.UnityReflection;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.InternalSettings;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewIndexer {
    private static final String APP_VERSION_PARAM = "app_version";
    private static final String PLATFORM_PARAM = "platform";
    private static final String REQUEST_TYPE = "request_type";
    private static final String SUCCESS = "success";
    /* access modifiers changed from: private */
    public static final String TAG = ViewIndexer.class.getCanonicalName();
    private static final String TREE_PARAM = "tree";
    private static ViewIndexer instance;
    /* access modifiers changed from: private */
    public WeakReference<Activity> activityReference;
    /* access modifiers changed from: private */
    public Timer indexingTimer;
    /* access modifiers changed from: private */
    public String previousDigest = null;
    /* access modifiers changed from: private */
    public final Handler uiThreadHandler = new Handler(Looper.getMainLooper());

    public ViewIndexer(Activity activity) {
        this.activityReference = new WeakReference<>(activity);
        instance = this;
    }

    public void schedule() {
        final TimerTask indexingTask = new TimerTask() {
            public void run() {
                String screenshot;
                try {
                    Activity activity = (Activity) ViewIndexer.this.activityReference.get();
                    if (activity != null) {
                        String activityName = activity.getClass().getSimpleName();
                        View rootView = activity.getWindow().getDecorView().getRootView();
                        if (!CodelessManager.getIsAppIndexingEnabled()) {
                            return;
                        }
                        if (InternalSettings.isUnityApp()) {
                            UnityReflection.captureViewHierarchy();
                            return;
                        }
                        FutureTask<String> screenshotFuture = new FutureTask<>(new ScreenshotTaker(rootView));
                        ViewIndexer.this.uiThreadHandler.post(screenshotFuture);
                        try {
                            screenshot = screenshotFuture.get(1, TimeUnit.SECONDS);
                        } catch (Exception e) {
                            Log.e(ViewIndexer.TAG, "Failed to take screenshot.", e);
                        }
                        JSONObject viewTree = new JSONObject();
                        try {
                            viewTree.put("screenname", activityName);
                            viewTree.put("screenshot", screenshot);
                            JSONArray viewArray = new JSONArray();
                            viewArray.put(ViewHierarchy.getDictionaryOfView(rootView));
                            viewTree.put("view", viewArray);
                        } catch (JSONException e2) {
                            Log.e(ViewIndexer.TAG, "Failed to create JSONObject");
                        }
                        ViewIndexer.this.sendToServer(viewTree.toString());
                    }
                } catch (Exception e3) {
                    Log.e(ViewIndexer.TAG, "UI Component tree indexing failure!", e3);
                }
            }
        };
        FacebookSdk.getExecutor().execute(new Runnable() {
            public void run() {
                try {
                    if (ViewIndexer.this.indexingTimer != null) {
                        ViewIndexer.this.indexingTimer.cancel();
                    }
                    String unused = ViewIndexer.this.previousDigest = null;
                    Timer unused2 = ViewIndexer.this.indexingTimer = new Timer();
                    ViewIndexer.this.indexingTimer.scheduleAtFixedRate(indexingTask, 0, 1000);
                } catch (Exception e) {
                    Log.e(ViewIndexer.TAG, "Error scheduling indexing job", e);
                }
            }
        });
    }

    public void unschedule() {
        if (((Activity) this.activityReference.get()) != null && this.indexingTimer != null) {
            try {
                this.indexingTimer.cancel();
                this.indexingTimer = null;
            } catch (Exception e) {
                Log.e(TAG, "Error unscheduling indexing job", e);
            }
        }
    }

    public static void sendToServerUnityInstance(String tree) {
        if (instance != null) {
            instance.sendToServerUnity(tree);
        }
    }

    @Deprecated
    public void sendToServerUnity(String tree) {
        instance.sendToServer(tree);
    }

    /* access modifiers changed from: private */
    public void sendToServer(final String tree) {
        FacebookSdk.getExecutor().execute(new Runnable() {
            public void run() {
                GraphRequest request;
                String currentDigest = Utility.md5hash(tree);
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if ((currentDigest == null || !currentDigest.equals(ViewIndexer.this.previousDigest)) && (request = ViewIndexer.buildAppIndexingRequest(tree, accessToken, FacebookSdk.getApplicationId(), Constants.APP_INDEXING)) != null) {
                    GraphResponse res = request.executeAndWait();
                    try {
                        JSONObject jsonRes = res.getJSONObject();
                        if (jsonRes != null) {
                            if ("true".equals(jsonRes.optString("success"))) {
                                Logger.log(LoggingBehavior.APP_EVENTS, ViewIndexer.TAG, "Successfully send UI component tree to server");
                                String unused = ViewIndexer.this.previousDigest = currentDigest;
                            }
                            if (jsonRes.has(Constants.APP_INDEXING_ENABLED)) {
                                CodelessManager.updateAppIndexing(Boolean.valueOf(jsonRes.getBoolean(Constants.APP_INDEXING_ENABLED)));
                                return;
                            }
                            return;
                        }
                        Log.e(ViewIndexer.TAG, "Error sending UI component tree to Facebook: " + res.getError());
                    } catch (JSONException e) {
                        Log.e(ViewIndexer.TAG, "Error decoding server response.", e);
                    }
                }
            }
        });
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static GraphRequest buildAppIndexingRequest(String appIndex, AccessToken accessToken, String appId, String requestType) {
        GraphRequest postRequest = null;
        if (appIndex != null) {
            postRequest = GraphRequest.newPostRequest(accessToken, String.format(Locale.US, "%s/app_indexing", new Object[]{appId}), (JSONObject) null, (GraphRequest.Callback) null);
            Bundle requestParameters = postRequest.getParameters();
            if (requestParameters == null) {
                requestParameters = new Bundle();
            }
            requestParameters.putString(TREE_PARAM, appIndex);
            requestParameters.putString(APP_VERSION_PARAM, AppEventUtility.getAppVersion());
            requestParameters.putString(PLATFORM_PARAM, "android");
            requestParameters.putString(REQUEST_TYPE, requestType);
            if (requestType.equals(Constants.APP_INDEXING)) {
                requestParameters.putString(Constants.DEVICE_SESSION_ID, CodelessManager.getCurrentDeviceSessionID());
            }
            postRequest.setParameters(requestParameters);
            postRequest.setCallback(new GraphRequest.Callback() {
                public void onCompleted(GraphResponse response) {
                    Logger.log(LoggingBehavior.APP_EVENTS, ViewIndexer.TAG, "App index sent to FB!");
                }
            });
        }
        return postRequest;
    }

    private static class ScreenshotTaker implements Callable<String> {
        private WeakReference<View> rootView;

        ScreenshotTaker(View rootView2) {
            this.rootView = new WeakReference<>(rootView2);
        }

        public String call() {
            View view = (View) this.rootView.get();
            if (view == null || view.getWidth() == 0 || view.getHeight() == 0) {
                return "";
            }
            Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
            view.draw(new Canvas(bitmap));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, outputStream);
            return Base64.encodeToString(outputStream.toByteArray(), 2);
        }
    }
}
