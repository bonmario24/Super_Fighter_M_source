package com.xsdk.doraemon.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.xsdk.doraemon.apkreflect.ReflectResource;

public abstract class BaseDialogFragment extends DialogFragment {
    protected Activity mActivity;

    public void onAttach(Context context) {
        super.onAttach(context);
        if (Build.VERSION.SDK_INT >= 23) {
            onCurrentAttach(context);
        }
    }

    /* access modifiers changed from: protected */
    public void onCurrentAttach(Context mContext) {
        this.mActivity = (Activity) mContext;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < 23) {
            onCurrentAttach(activity);
        }
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setStyle(2, 0);
    }

    /* access modifiers changed from: protected */
    public View getContentView(String strResLayoutID) {
        return ReflectResource.getInstance(getActivity().getBaseContext()).getLayoutView(strResLayoutID);
    }

    /* access modifiers changed from: protected */
    public View getViewByName(View layout, String widgetResID) {
        return ReflectResource.getInstance(getActivity().getBaseContext()).getWidgetView(layout, widgetResID);
    }

    public int getWidgetViewID(String widgetName) {
        return ReflectResource.getInstance(getActivity().getBaseContext()).getWidgetViewID(widgetName);
    }

    public void onStart() {
        super.onStart();
        doInitDialog();
    }

    private void doInitDialog() {
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.alpha = 1.0f;
            lp.dimAmount = 0.5f;
            dialog.getWindow().setAttributes(lp);
            getDialog().getWindow().addFlags(2);
            getDialog().getWindow().clearFlags(131080);
            getDialog().getWindow().setSoftInputMode(4);
            if (getView() != null) {
                Animation animation = new AlphaAnimation(0.0f, 1.0f);
                animation.setStartTime(300);
                getView().startAnimation(animation);
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
    }
}
