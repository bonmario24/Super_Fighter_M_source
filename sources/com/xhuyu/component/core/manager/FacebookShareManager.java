package com.xhuyu.component.core.manager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Toast;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.core.api.OnDownloadListener;
import com.xhuyu.component.core.config.Constant;
import com.xhuyu.component.mvp.model.ShareResult;
import com.xhuyu.component.network.NetBasicUtil;
import com.xhuyu.component.utils.ResourceUtil;
import com.xhuyu.component.utils.third.FacebookShareUtil;
import com.xhuyu.component.widget.LoadingDialog;
import com.xsdk.doraemon.event.SDKEventBus;
import com.xsdk.doraemon.event.Subscribe;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.utils.ContextUtil;
import org.json.JSONObject;

public class FacebookShareManager {
    private static final int IMAGE_URL_TYPE = 1;
    private static final int WEB_URL_TYPE = 2;
    private static FacebookShareManager mInstance;
    /* access modifiers changed from: private */
    public Activity mActivity;
    private LoadingDialog mLoadingDialog;

    private FacebookShareManager() {
        SDKEventBus.getDefault().register(this);
    }

    public static FacebookShareManager getInstance() {
        if (mInstance == null) {
            synchronized (FacebookShareManager.class) {
                if (mInstance == null) {
                    mInstance = new FacebookShareManager();
                }
            }
        }
        return mInstance;
    }

    public void share(Activity activity) {
        this.mActivity = activity;
        showLoadingDialog();
        NetBasicUtil.doRequestFacebookShare(activity, new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                if (dataJson != null) {
                    int shareType = dataJson.optInt("share_type", -1);
                    String url = dataJson.optString("url");
                    int urlType = dataJson.optInt("url_type");
                    if (shareType > 0 && !CheckUtils.isNullOrEmpty(url) && urlType > 0) {
                        FacebookShareManager.this.openShare(shareType, urlType, url);
                        return;
                    }
                }
                FacebookShareManager.this.postFail("");
            }

            public void onFail(String message, int code) {
                FacebookShareManager.this.postFail(message);
            }
        });
    }

    /* access modifiers changed from: private */
    public void openShare(int shareType, int urlType, String url) {
        if (!CheckUtil.checkPackageApp(this.mActivity, Constant.PAK_FACEBOOK)) {
            FacebookShareUtil.getInstance().shareLink(this.mActivity, "", url, 2);
        } else if ((shareType == 2 && urlType == 1) || urlType == 2) {
            dismissDialog();
            FacebookShareUtil.getInstance().shareLink(this.mActivity, "", url, shareType);
        } else if (urlType == 1) {
            loadImage(shareType, url);
        }
    }

    private void loadImage(final int shareType, String url) {
        NetBasicUtil.downloadImage(url, new OnDownloadListener() {
            public void onResult(Object object) {
                if (object instanceof Bitmap) {
                    FacebookShareManager.this.dismissDialog();
                    FacebookShareUtil.getInstance().sharePhoto(FacebookShareManager.this.mActivity, (Bitmap) object, shareType);
                    return;
                }
                FacebookShareManager.this.postFail("");
            }

            public void onError(String failMessage) {
                FacebookShareManager.this.postFail("");
            }
        });
    }

    private void showLoadingDialog() {
        if (this.mLoadingDialog == null) {
            this.mLoadingDialog = new LoadingDialog(this.mActivity);
        }
        if (this.mActivity != null && !this.mActivity.isFinishing() && this.mLoadingDialog != null && !this.mLoadingDialog.isShowing()) {
            this.mLoadingDialog.show();
        }
    }

    /* access modifiers changed from: private */
    public void dismissDialog() {
        if (this.mLoadingDialog != null) {
            this.mLoadingDialog.dismiss();
        }
    }

    public void onPause() {
        dismissDialog();
    }

    /* access modifiers changed from: private */
    public void postFail(String error) {
        dismissDialog();
        String msg = error;
        try {
            if (CheckUtils.isNullOrEmpty(msg)) {
                msg = ResourceUtil.getString("huyu_share_fail");
            }
            FacebookShareUtil.getInstance().postResult(0, "", msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        FacebookShareUtil.getInstance().onActivityResult(requestCode, resultCode, data);
    }

    @Subscribe(event = {8})
    public void onShareResultResult(ShareResult shareResult) {
        switch (shareResult.getResultCode()) {
            case 1:
                toast("huyu_share_success");
                return;
            case 2:
                toast("huyu_share_cancel");
                return;
            default:
                toast("huyu_share_fail");
                return;
        }
    }

    private void toast(String resString) {
        try {
            String message = ResourceUtil.getString(resString);
            if (!CheckUtils.isNullOrEmpty(message)) {
                Toast.makeText(ContextUtil.getInstance().getActivity(), message, 0).show();
            }
        } catch (Exception e) {
        }
    }
}
