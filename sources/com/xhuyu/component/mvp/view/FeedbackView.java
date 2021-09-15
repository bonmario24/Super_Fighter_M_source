package com.xhuyu.component.mvp.view;

import com.xhuyu.component.core.base.BaseView;

public interface FeedbackView extends BaseView {
    void onFinishActivity();

    void toastMessage(String str, boolean z);
}
