package com.xhuyu.component.p036ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.xhuyu.component.core.base.BaseSupportActivity;
import com.xhuyu.component.core.config.SuperTool;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.p036ui.fragment.AutoLoginFragment;
import com.xhuyu.component.p036ui.fragment.LoginByAccountFragment;
import com.xhuyu.component.utils.third.FacebookLoginUtil;
import com.xhuyu.component.utils.third.GoogleLoginUtil;
import com.xsdk.doraemon.apkreflect.ReflectResource;

/* renamed from: com.xhuyu.component.ui.activity.LoginActivity */
public class LoginActivity extends BaseSupportActivity {
    /* access modifiers changed from: protected */
    public String getLayoutName() {
        return null;
    }

    /* access modifiers changed from: protected */
    public ViewGroup needTrackContainsView() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(18);
        openLoginView();
    }

    private void openLoginView() {
        if (UserManager.getInstance().getLoginUser() != null) {
            loadRootFragment(ReflectResource.getInstance(getApplication()).getWidgetViewID("f_fragment"), AutoLoginFragment.getInstance());
        } else {
            loadRootFragment(ReflectResource.getInstance(getApplication()).getWidgetViewID("f_fragment"), LoginByAccountFragment.getInstance());
        }
    }

    public void onBackPressedSupport() {
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (SuperTool.getInstance().getActivityResultType()) {
            case 1:
                FacebookLoginUtil.getInstance().onActivityResult(requestCode, resultCode, data);
                break;
            case 2:
                GoogleLoginUtil.getInstance().onActivityResult(requestCode, resultCode, data);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
