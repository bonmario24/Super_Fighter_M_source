package com.xhuyu.component.p036ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.doraemon.util.SizeUtils;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.mvp.model.NoticeBean;
import com.xsdk.doraemon.base.BaseDialogFragment;
import com.xsdk.doraemon.utils.ImageUtil;
import com.xsdk.doraemon.utils.UiCalculateUtil;

/* renamed from: com.xhuyu.component.ui.fragment.NoticeExitDialogFragment */
public class NoticeExitDialogFragment extends BaseDialogFragment implements View.OnClickListener {
    private static final String EXTRA_IMAGE = "extra_image";
    private static final String INTENT_EXTRA_KEY = "notice_bean";
    private Button btnBack;
    private Button btnExit;
    private View mContentView;
    /* access modifiers changed from: private */
    public ImageView mIvNotice;
    /* access modifiers changed from: private */
    public RelativeLayout mRelContains;
    /* access modifiers changed from: private */
    public LinearLayout noticeContains;
    private NoticeBean.NoticeData noticeData;

    public static NoticeExitDialogFragment newInstance(NoticeBean.NoticeData noticeData2, Bitmap exitBitmap) {
        Bundle args = new Bundle();
        args.putSerializable(INTENT_EXTRA_KEY, noticeData2);
        args.putParcelable(EXTRA_IMAGE, exitBitmap);
        NoticeExitDialogFragment fragment = new NoticeExitDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public final View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.mContentView = getContentView("layout_notice_exit");
        this.mRelContains = (RelativeLayout) getViewByName(this.mContentView, "rel_contains");
        setupUI();
        try {
            Bundle arguments = getArguments();
            this.noticeData = (NoticeBean.NoticeData) arguments.getSerializable(INTENT_EXTRA_KEY);
            Bitmap image = (Bitmap) arguments.getParcelable(EXTRA_IMAGE);
            if (image != null) {
                this.mIvNotice.setImageBitmap(ImageUtil.getRoundBitmapByShader(image, image.getWidth(), image.getHeight(), SizeUtils.dp2px(8.0f), 0, true, false));
            }
            arguments.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        autoInflaterUI();
        return this.mContentView;
    }

    private void setupUI() {
        this.btnExit = (Button) getViewByName(this.mContentView, "btn_exit");
        this.btnBack = (Button) getViewByName(this.mContentView, "btn_back");
        this.mIvNotice = (ImageView) getViewByName(this.mContentView, "iv_notice");
        this.noticeContains = (LinearLayout) getViewByName(this.mContentView, "ll_notice_contains");
        this.mIvNotice.setScaleType(ImageView.ScaleType.FIT_XY);
        this.mIvNotice.setOnClickListener(this);
        this.btnExit.setOnClickListener(this);
        this.btnBack.setOnClickListener(this);
    }

    private void autoInflaterUI() {
        final int size = UiCalculateUtil.calculateTheLayoutWidth(this.mActivity);
        int height = UiCalculateUtil.calculateTheLayoutHeight(this.mActivity);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mIvNotice.getLayoutParams();
        layoutParams.height = (int) (((double) height) * 0.7d);
        layoutParams.addRule(13);
        this.mIvNotice.setLayoutParams(layoutParams);
        this.mRelContains.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(size, NoticeExitDialogFragment.this.noticeContains.getHeight() + NoticeExitDialogFragment.this.mIvNotice.getHeight());
                rlp.addRule(13);
                NoticeExitDialogFragment.this.mRelContains.setLayoutParams(rlp);
                if (Build.VERSION.SDK_INT >= 16) {
                    NoticeExitDialogFragment.this.mRelContains.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    NoticeExitDialogFragment.this.mRelContains.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    public void onClick(View v) {
        if (v.getId() == this.btnBack.getId()) {
            dismiss();
        } else if (v.getId() == this.btnExit.getId()) {
            SDKEventPost.post(7, new Object[0]);
            UserManager.getInstance().exitGame();
            dismiss();
        } else if (v.getId() == this.mIvNotice.getId() && this.noticeData != null && !CheckUtils.isNullOrEmpty(this.noticeData.getRedirect_url())) {
            if (this.noticeData.getGoto_type() == 2 && !this.noticeData.getRedirect_url().startsWith("http")) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.noticeData.getRedirect_url()));
                    intent.addFlags(268435456);
                    this.mActivity.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (this.noticeData.getGoto_type() == 1 && this.noticeData.getRedirect_url().startsWith("http")) {
                Intent intent2 = new Intent();
                intent2.setAction("android.intent.action.VIEW");
                intent2.setData(Uri.parse(this.noticeData.getRedirect_url()));
                startActivity(intent2);
            }
        }
    }
}
