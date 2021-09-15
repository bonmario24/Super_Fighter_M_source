package com.eagle.mixsdk.sdk.dialog;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.eagle.mixsdk.sdk.base.PluginFactory;
import com.eagle.mixsdk.sdk.dialog.CommonDialog;
import com.eagle.mixsdk.sdk.utils.ResPluginUtil;
import java.lang.ref.WeakReference;

public class GameNoticeDialog {
    private CommonDialog dialog;
    private boolean isCanceable;
    private final Context mContext = ((Context) this.weakReference.get());
    /* access modifiers changed from: private */
    public View.OnClickListener mListener;
    private String mNoticeContent;
    private final WeakReference<Context> weakReference;

    public GameNoticeDialog(Context context) {
        this.weakReference = new WeakReference<>(context);
    }

    public String getSureText() {
        return ResPluginUtil.getStringByName("xeagle_confirm_see");
    }

    public String getTitle() {
        return ResPluginUtil.getStringByName("xeagle_tip_wxtip");
    }

    public String getMessage() {
        if (TextUtils.isEmpty(this.mNoticeContent)) {
            return PluginFactory.getInstance().getExtValues(this.mContext, "dialog_game_notice_content");
        }
        return this.mNoticeContent;
    }

    public GameNoticeDialog setNoticeContent(String content) {
        this.mNoticeContent = content;
        return this;
    }

    public GameNoticeDialog setCancelable(boolean cancelable) {
        this.isCanceable = cancelable;
        return this;
    }

    public void show() {
        if (this.dialog == null) {
            this.dialog = new CommonDialog.Builder(this.mContext).setTitle((CharSequence) getTitle()).setMessage((CharSequence) getMessage()).setPositiveButton((CharSequence) getSureText(), (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View v) {
                    if (GameNoticeDialog.this.mListener != null) {
                        GameNoticeDialog.this.mListener.onClick(v);
                    }
                }
            }).setCancelable(this.isCanceable).isSingleBtn(true).builder();
        }
        if (!(this.mContext instanceof Activity) || !((Activity) this.mContext).isFinishing()) {
            this.dialog.show();
        }
    }

    public GameNoticeDialog setPositiveListener(View.OnClickListener listener) {
        this.mListener = listener;
        return this;
    }
}
