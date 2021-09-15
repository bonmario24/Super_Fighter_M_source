package com.xhuyu.component.mvp.view;

import com.xhuyu.component.core.base.BaseView;

public interface PhoneBaseView extends BaseView {
    void showToastMessage(boolean z, String str);

    void startTimerToVerificationCode();
}
