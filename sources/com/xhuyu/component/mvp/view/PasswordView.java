package com.xhuyu.component.mvp.view;

import com.xhuyu.component.core.base.BaseView;

public interface PasswordView extends BaseView {
    void onComplete();

    void showToastMessage(boolean z, String str);
}
