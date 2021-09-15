package com.xhuyu.component.p036ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import com.xhuyu.component.core.base.BaseSupportActivity;
import com.xhuyu.component.core.manager.FloatWindowManager;
import com.xhuyu.component.widget.ucenter.UserCenterView;
import com.xsdk.doraemon.utils.notch.utils.DisplayCutoutUtil;

/* renamed from: com.xhuyu.component.ui.activity.UserCenterActivity */
public class UserCenterActivity extends BaseSupportActivity {
    /* access modifiers changed from: protected */
    public String getLayoutName() {
        return "activity_user_center";
    }

    /* access modifiers changed from: protected */
    public ViewGroup needTrackContainsView() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        DisplayCutoutUtil.openFullScreenModel(this);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(3);
        initView();
    }

    private void initView() {
        UserCenterView userCenterView = UserCenterView.getInstance(getBaseContext());
        userCenterView.onViewEnter(this);
        try {
            View widgetView = getWidgetView("user_center_container");
            if (widgetView != null) {
                ((RelativeLayout) widgetView).removeAllViews();
                ((RelativeLayout) widgetView).addView(userCenterView, new ViewGroup.LayoutParams(-1, -1));
                return;
            }
            showToast(getStringByName("huyu_ucenter_open_fail"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressedSupport() {
        if (!UserCenterView.getInstance(getBaseContext()).onBackPressed()) {
            UserCenterView.getInstance(getBaseContext()).finish(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        UserCenterView.getInstance(this).finish(false);
        super.onDestroy();
        FloatWindowManager.getInstance().show();
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, UserCenterActivity.class));
    }
}
