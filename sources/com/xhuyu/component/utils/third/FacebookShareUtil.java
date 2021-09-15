package com.xhuyu.component.utils.third;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.internal.FacebookDialogBase;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.core.config.SuperTool;
import com.xhuyu.component.mvp.model.ShareResult;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

public class FacebookShareUtil {
    public static final int SHARE_AUTO_TYPE = 1;
    public static final int SHARE_WEB_TYPE = 2;
    private static FacebookShareUtil instance = null;
    private Activity mActivity;
    private CallbackManager mCallbackManager = CallbackManager.Factory.create();
    FacebookDialogBase<ShareContent, Sharer.Result> shareDialog;

    private FacebookShareUtil() {
    }

    public static FacebookShareUtil getInstance() {
        if (instance == null) {
            synchronized (FacebookShareUtil.class) {
                if (instance == null) {
                    instance = new FacebookShareUtil();
                }
            }
        }
        return instance;
    }

    private void initShare(Activity activity) {
        this.mActivity = activity;
        this.shareDialog = new ShareDialog(this.mActivity);
        this.shareDialog.registerCallback(this.mCallbackManager, new FacebookCallback<Sharer.Result>() {
            public void onSuccess(Sharer.Result result) {
                SDKLoggerUtil.getLogger().mo19504i("FB分享成功", new Object[0]);
                FacebookShareUtil.this.postResult(1, result != null ? result.getPostId() : "", "");
            }

            public void onCancel() {
                SDKLoggerUtil.getLogger().mo19502e("FB分享取消", new Object[0]);
                FacebookShareUtil.this.postResult(2, "", "Cancel");
            }

            public void onError(FacebookException error) {
                SDKLoggerUtil.getLogger().mo19502e("FB分享Error :" + error.toString(), new Object[0]);
                FacebookShareUtil.this.postResult(0, "", error.toString());
            }
        });
    }

    public void shareLink(Activity activity, String hashTag, String quote, String contentShareUrl, int shareType) {
        SuperTool.getInstance().setActivityResultType(3);
        initShare(activity);
        ShareLinkContent.Builder shareLinkContentBuilder = new ShareLinkContent.Builder();
        shareLinkContentBuilder.setContentUrl(Uri.parse(contentShareUrl));
        shareLinkContentBuilder.setShareHashtag(new ShareHashtag.Builder().setHashtag("#" + hashTag + "").build());
        shareLinkContentBuilder.setQuote(quote);
        show(shareLinkContentBuilder.build(), shareType);
    }

    public void shareLink(Activity activity, String quote, String contentShareUrl, int shareType) {
        SuperTool.getInstance().setActivityResultType(3);
        initShare(activity);
        ShareLinkContent.Builder shareLinkContentBuilder = new ShareLinkContent.Builder();
        shareLinkContentBuilder.setContentUrl(Uri.parse(contentShareUrl));
        shareLinkContentBuilder.setQuote(quote);
        show(shareLinkContentBuilder.build(), shareType);
    }

    public void sharePhoto(Activity activity, Bitmap imageUrl, int shareType) {
        SuperTool.getInstance().setActivityResultType(3);
        initShare(activity);
        show(new SharePhotoContent.Builder().addPhoto(new SharePhoto.Builder().setBitmap(imageUrl).build()).build(), shareType);
    }

    private void show(ShareContent content, int shareType) {
        SDKLoggerUtil.getLogger().mo19502e("mCallbackManager is null=" + (this.mCallbackManager == null ? "true" : "false"), new Object[0]);
        ShareApi.share(content, (FacebookCallback<Sharer.Result>) null);
        if (shareType == 1) {
            this.shareDialog.show(content);
        } else if (shareType == 2 && (this.shareDialog instanceof ShareDialog)) {
            ((ShareDialog) this.shareDialog).show(content, ShareDialog.Mode.WEB);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.mCallbackManager != null) {
            this.mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void postResult(int resultCode, String postId, String failureMsg) {
        SDKEventPost.post(8, new ShareResult(resultCode, postId, failureMsg));
    }
}
