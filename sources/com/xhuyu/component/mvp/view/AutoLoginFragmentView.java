package com.xhuyu.component.mvp.view;

import com.xhuyu.component.core.base.BaseView;

public interface AutoLoginFragmentView extends BaseView {
    void onLoginFailed(String str, int i);

    void onLoginSuccess();

    void refreshTick(long j);
}
