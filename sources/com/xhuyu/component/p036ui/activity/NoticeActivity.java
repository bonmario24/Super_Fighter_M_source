package com.xhuyu.component.p036ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.doraemon.util.SizeUtils;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.base.BaseWebActivity;
import com.xhuyu.component.core.manager.NoticeManager;
import com.xhuyu.component.mvp.model.NoticeBean;
import com.xhuyu.component.mvp.presenter.impl.NoticePresenterImpl;
import com.xhuyu.component.mvp.view.NoticeView;
import com.xsdk.doraemon.utils.UiCalculateUtil;

/* renamed from: com.xhuyu.component.ui.activity.NoticeActivity */
public class NoticeActivity extends BaseWebActivity implements NoticeView {
    private static final String INTENT_EXTRA_KEY = "notice";
    private View ivClose;
    private RelativeLayout jumpContains;
    /* access modifiers changed from: private */
    public ImageView mIvNotice;
    private LinearLayout mNoticeContains;
    private NoticePresenterImpl mNoticePresenter;
    private NoticeBean.NoticeData mParamsBean;
    private TextView tvNoticeTitle;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.mParamsBean = (NoticeBean.NoticeData) intent.getSerializableExtra(INTENT_EXTRA_KEY);
        this.mNoticePresenter = new NoticePresenterImpl(this);
        this.mNoticePresenter.doInitNotice(this.mParamsBean);
    }

    private void initUI() {
        View webLayoutContains = getWidgetView("rl_web_layout");
        int padding = SizeUtils.dp2px(12.0f);
        getWidgetView("ll_contains_user").setPadding(padding, 0, padding, padding);
        webLayoutContains.setVisibility(8);
        this.mNoticeContains = (LinearLayout) getWidgetView("ll_notice_contains");
        this.mIvNotice = (ImageView) getWidgetView("iv_notice");
        this.mIvNotice.setOnClickListener(this);
        this.mIvNotice.setScaleType(ImageView.ScaleType.FIT_XY);
        this.jumpContains = (RelativeLayout) getWidgetView("rl_jump_contains");
        this.tvNoticeTitle = (TextView) getWidgetView("tv_notice_title");
        getWidgetView("iv_back").setVisibility(8);
        this.ivClose = getWidgetView("iv_close");
        this.ivClose.setVisibility(0);
        this.ivClose.setOnClickListener(this);
        int width = UiCalculateUtil.calculateTheLayoutWidth(this, 0.55f, 0.9f);
        int height = UiCalculateUtil.calculateTheLayoutHeight(this, 0.8f, 0.5f);
        if (UiCalculateUtil.isLandscape(this)) {
            height = UiCalculateUtil.calculateTheLayoutHeight(this);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
        layoutParams.gravity = 17;
        webLayoutContains.setLayoutParams(layoutParams);
    }

    public void loadWebView(WebView webView) {
    }

    public void onFinishActivity() {
        setResult(NoticeManager.NOTICE_RESULT_CODE);
        finish();
    }

    public void openImageNotice(final Bitmap imageData) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    NoticeActivity.this.isImageType(true);
                    NoticeActivity.this.mIvNotice.setImageBitmap(imageData);
                } catch (Exception e) {
                }
            }
        });
    }

    public void openTextNotice(String notice) {
        try {
            isImageType(false);
            if (!CheckUtils.isNullOrEmpty(notice)) {
                this.mWebView.loadDataWithBaseURL((String) null, getHtmlData(notice), "text/html", "utf-8", (String) null);
            } else {
                onFinishActivity();
            }
        } catch (Exception e) {
        }
    }

    private String getHtmlData(String bodyHTML) {
        return "<html>" + "<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> <style>img{max-width: 100%; width:auto; height:auto;}</style></head>" + "<body>" + bodyHTML + "</body></html>";
    }

    /* access modifiers changed from: private */
    public void isImageType(boolean isImage) {
        int i;
        int i2 = 8;
        getWidgetView("rl_web_layout").setVisibility(0);
        LinearLayout linearLayout = this.mNoticeContains;
        if (isImage) {
            i = 0;
        } else {
            i = 8;
        }
        linearLayout.setVisibility(i);
        WebView webView = this.mWebView;
        if (!isImage) {
            i2 = 0;
        }
        webView.setVisibility(i2);
    }

    public void showDialog() {
        showLoadingDialog();
        this.mLoadingDialog.setCancelable(false);
    }

    public void closeLoadingDialog() {
        dismissDialog();
    }

    public static void start(Activity activity, int requestCode, String title, NoticeBean.NoticeData paramsBean) {
        Intent starter = new Intent(activity, NoticeActivity.class);
        starter.putExtra(INTENT_EXTRA_KEY, paramsBean);
        starter.putExtra(BaseWebActivity.EXTRA_TITLE, title);
        activity.startActivityForResult(starter, requestCode);
    }

    public void onClick(View v) {
        if (v.getId() == this.mIvNotice.getId()) {
            if (this.mParamsBean != null && !CheckUtils.isNullOrEmpty(this.mParamsBean.getRedirect_url())) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(this.mParamsBean.getRedirect_url()));
                startActivity(intent);
            }
        } else if (v.getId() == this.ivClose.getId()) {
            onFinishActivity();
        }
    }

    public void onBackPressedSupport() {
        onFinishActivity();
    }
}
