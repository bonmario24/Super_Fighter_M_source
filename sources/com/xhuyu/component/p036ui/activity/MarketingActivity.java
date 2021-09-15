package com.xhuyu.component.p036ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.xhuyu.component.core.base.BaseSupportActivity;
import com.xhuyu.component.p036ui.fragment.MarketingFragment;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

/* renamed from: com.xhuyu.component.ui.activity.MarketingActivity */
public class MarketingActivity extends BaseSupportActivity {
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
        MarketingFragment marketingFragment = MarketingFragment.getInstance();
        if (marketingFragment != null) {
            loadRootFragment(ReflectResource.getInstance(getApplication()).getWidgetViewID("f_fragment"), marketingFragment);
            return;
        }
        SDKLoggerUtil.getLogger().mo19502e("open marketing view error", new Object[0]);
        finish();
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MarketingActivity.class));
    }
}
