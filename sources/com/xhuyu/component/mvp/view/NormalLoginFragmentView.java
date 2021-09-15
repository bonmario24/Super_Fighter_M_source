package com.xhuyu.component.mvp.view;

import android.app.Activity;
import android.view.View;
import com.xhuyu.component.core.base.BaseView;

public interface NormalLoginFragmentView extends BaseView {
    void autoInflaterUI(int i, int i2);

    void changePwdEditViewType(int i);

    View getContentView();

    Activity getViewActivity();

    void onLoginFailed(String str, int i);

    void onLoginSuccess();

    void showAccountInfo(boolean z, String str, String str2);

    void showToastMessage(boolean z, String str);
}
