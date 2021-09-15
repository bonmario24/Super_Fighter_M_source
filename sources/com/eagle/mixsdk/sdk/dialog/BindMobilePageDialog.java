package com.eagle.mixsdk.sdk.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.ValueCallback;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.listeners.EagleBindMobileListener;
import com.eagle.mixsdk.sdk.other.p029bm.BindMobileHelper;
import com.eagle.mixsdk.sdk.other.p029bm.BindMobileJSInterface;
import com.eagle.mixsdk.sdk.utils.HttpUtil;
import com.eagle.mixsdk.sdk.view.FullScreenWebView;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class BindMobilePageDialog extends Dialog implements ViewTreeObserver.OnGlobalLayoutListener {
    /* access modifiers changed from: private */
    public EagleBindMobileListener mBMCallback;
    private final Activity mContext;
    private String mRoleData;
    /* access modifiers changed from: private */
    public FullScreenWebView mWebView;
    /* access modifiers changed from: private */
    public int root_height;

    public BindMobilePageDialog(Activity context, String roleData) {
        super(context);
        if (getWindow() != null) {
            getWindow().requestFeature(1);
        }
        this.mContext = context;
        this.mRoleData = roleData;
    }

    public Dialog addListener(EagleBindMobileListener listener) {
        this.mBMCallback = listener;
        return this;
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"AddJavascriptInterface"})
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mWebView = new FullScreenWebView(this.mContext);
        this.mWebView.setTransparent();
        this.mWebView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case 2:
                        return true;
                    default:
                        return false;
                }
            }
        });
        this.mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                return true;
            }
        });
        this.mWebView.addJavascriptInterface(new BindMobileJSInterface(new BindMobileJSInterface.IJsCallBack() {
            public void onResult(int action, int code, String msg) {
                switch (action) {
                    case 12:
                        if (BindMobilePageDialog.this.mBMCallback != null) {
                            BindMobilePageDialog.this.mBMCallback.onResult(code, msg);
                            break;
                        }
                        break;
                    case 13:
                        break;
                }
                if (1 == code) {
                    BindMobilePageDialog.this.dismiss();
                }
                String jsResult = BindMobilePageDialog.this.getJsResult(code, msg);
                if (Build.VERSION.SDK_INT >= 19) {
                    BindMobilePageDialog.this.mWebView.evaluateJavascript("javascript:resultMsg(" + jsResult + ")", new ValueCallback<String>() {
                        public void onReceiveValue(String value) {
                        }
                    });
                } else {
                    BindMobilePageDialog.this.mWebView.loadUrl("javascript:resultMsg(" + jsResult + ")");
                }
            }
        }), "control");
        DisplayMetrics dm = this.mContext.getResources().getDisplayMetrics();
        int widthPixels = dm.widthPixels;
        int heightPixels = dm.heightPixels;
        Log.w(Constants.TAG, "---" + widthPixels + "---" + heightPixels);
        setContentView(this.mWebView, new ViewGroup.LayoutParams(widthPixels, heightPixels));
        HashMap<String, String> commonParams = new HashMap<>();
        if (!TextUtils.isEmpty(this.mRoleData)) {
            try {
                JSONObject roleJson = new JSONObject(this.mRoleData);
                commonParams.put("serverId", roleJson.optString("serverId", ""));
                commonParams.put("serverName", roleJson.optString("serverName", ""));
                commonParams.put("roleId", roleJson.optString("roleId", ""));
                commonParams.put("roleName", roleJson.optString("roleName", ""));
                commonParams.put("roleLevel", roleJson.optString("roleLevel", ""));
                commonParams.put("vipLevel", roleJson.optString("vipLevel", ""));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        HttpUtil.getCommonParams(commonParams);
        commonParams.put("uid", EagleSDK.getInstance().getUToken().getUserID() + "");
        this.mWebView.toLoadUrl(BindMobileHelper.getInstance().getH5Url(commonParams));
        this.mWebView.post(new Runnable() {
            public void run() {
                Rect rect = new Rect();
                BindMobilePageDialog.this.mWebView.getWindowVisibleDisplayFrame(rect);
                int unused = BindMobilePageDialog.this.root_height = rect.bottom - rect.top;
                Log.d("shen", "root--" + BindMobilePageDialog.this.root_height);
            }
        });
        registerKeyBord();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        if (getWindow() != null) {
            getWindow().setSoftInputMode(34);
            getWindow().setBackgroundDrawable(new ColorDrawable(0));
            getWindow().setFlags(1024, 1024);
        }
    }

    private void registerKeyBord() {
        if (getWindow() != null) {
            getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
    }

    private boolean isKeyBroadShowing() {
        Rect rect = new Rect();
        this.mWebView.getWindowVisibleDisplayFrame(rect);
        int diffHeight = this.root_height - (rect.bottom - rect.top);
        Log.d("shen", "isKeyBroadShowing " + (rect.bottom - rect.top));
        return diffHeight > 10;
    }

    public void dismiss() {
        super.dismiss();
        if (Build.VERSION.SDK_INT >= 16 && getWindow() != null) {
            getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    public void onGlobalLayout() {
        if (isKeyBroadShowing()) {
            Log.d("shen", "显示");
            Rect rect = new Rect();
            this.mWebView.getWindowVisibleDisplayFrame(rect);
            this.mWebView.setTranslationY((float) (-Math.min(this.root_height - (rect.bottom - rect.top), getContext().getResources().getDisplayMetrics().heightPixels / 2)));
            return;
        }
        Log.d("shen", "隐藏");
        this.mWebView.setTranslationY(0.0f);
    }

    /* access modifiers changed from: private */
    public String getJsResult(int code, String msg) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", code);
            jsonObject.put("msg", msg);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
