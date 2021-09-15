package com.eagle.mixsdk.sdk.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.eagle.mixsdk.sdk.dialog.CommonDialog;
import com.eagle.mixsdk.sdk.utils.ResPluginUtil;
import java.lang.ref.WeakReference;

public class ExitDialog {
    private final CommonDialog dialog;
    private final Context mContext = ((Context) this.weakReference.get());
    private final WeakReference<Context> weakReference;

    public interface OnClickListener {
        void onClick(int i);
    }

    public ExitDialog(Context context, final OnClickListener listener) {
        this.weakReference = new WeakReference<>(context);
        this.dialog = new CommonDialog.Builder(this.mContext).setTitle((CharSequence) getTitle()).setMessage((CharSequence) getMessage()).setPositiveButton((CharSequence) getSureText(), (View.OnClickListener) new View.OnClickListener() {
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(-1);
                }
            }
        }).setNegativeButton((CharSequence) getCancelText(), (View.OnClickListener) new View.OnClickListener() {
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(-2);
                }
            }
        }).builder();
    }

    public String getSureText() {
        return ResPluginUtil.getStringByName("xeagle_exit");
    }

    public String getCancelText() {
        return ResPluginUtil.getStringByName("xeagle_back");
    }

    public String getTitle() {
        return ResPluginUtil.getStringByName("xeagle_tip_wxtip");
    }

    public String getMessage() {
        return ResPluginUtil.getStringByName("xeagle_tip_exit_game");
    }

    public void show() {
        if (this.dialog != null && this.mContext != null && (this.mContext instanceof Activity) && !((Activity) this.mContext).isFinishing()) {
            this.dialog.show();
        }
    }
}
