package com.eagle.mixsdk.sdk.active;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CustomAlertDialog {
    private static CustomAlertDialog mCustomAlertDialog;
    private AlertDialog mAlertDialog;
    /* access modifiers changed from: private */
    public ImageView mImageView = null;
    private CustomWebView mWebView = null;

    private CustomAlertDialog() {
    }

    public static CustomAlertDialog getInstance() {
        if (mCustomAlertDialog != null) {
            return mCustomAlertDialog;
        }
        CustomAlertDialog customAlertDialog = new CustomAlertDialog();
        mCustomAlertDialog = customAlertDialog;
        return customAlertDialog;
    }

    public void createDialog(Activity activity, final CustomWebView webView) {
        this.mWebView = webView;
        this.mAlertDialog = new AlertDialog.Builder(activity).create();
        this.mAlertDialog.show();
        WindowManager.LayoutParams layoutParams = this.mAlertDialog.getWindow().getAttributes();
        layoutParams.gravity = 17;
        layoutParams.width = dip2px(activity, 310.0f);
        layoutParams.height = dip2px(activity, 330.0f);
        LinearLayout mLinearLayout = new LinearLayout(activity);
        mLinearLayout.setOrientation(1);
        mLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        LinearLayout titleLayout = new LinearLayout(activity);
        titleLayout.setBackgroundColor(-16777216);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(-1, dip2px(activity, 30.0f));
        titleParams.gravity = 16;
        titleLayout.setLayoutParams(titleParams);
        this.mImageView = new ImageView(activity);
        this.mImageView.setBackgroundResource(17301555);
        this.mImageView.setVisibility(4);
        this.mImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (webView.canGoBack()) {
                    webView.goBack();
                    if (!webView.canGoBack()) {
                        CustomAlertDialog.this.mImageView.setVisibility(4);
                        return;
                    }
                    return;
                }
                CustomAlertDialog.this.mImageView.setVisibility(4);
            }
        });
        titleLayout.addView(this.mImageView);
        LinearLayout webViewLinearLayout = new LinearLayout(activity);
        webViewLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        webViewLinearLayout.addView(webView);
        mLinearLayout.addView(titleLayout);
        mLinearLayout.addView(webViewLinearLayout);
        this.mAlertDialog.setCanceledOnTouchOutside(false);
        this.mAlertDialog.setCancelable(false);
        this.mAlertDialog.getWindow().setBackgroundDrawableResource(17170445);
        this.mAlertDialog.getWindow().setAttributes(layoutParams);
        this.mAlertDialog.getWindow().clearFlags(131072);
        this.mAlertDialog.getWindow().setContentView(mLinearLayout);
        this.mAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                System.out.println("keyCode : " + keyCode);
                if (keyCode != 4 || event.getRepeatCount() != 0) {
                    return true;
                }
                if (webView.canGoBack()) {
                    webView.goBack();
                }
                return false;
            }
        });
    }

    public void gotoBack() {
        if (this.mWebView != null && this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        }
    }

    public void closeDialog() {
        if (this.mAlertDialog != null) {
            this.mAlertDialog.dismiss();
            this.mAlertDialog = null;
        }
    }

    public ImageView getImageView() {
        return this.mImageView;
    }

    public static int dip2px(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getHeight(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
    }
}
