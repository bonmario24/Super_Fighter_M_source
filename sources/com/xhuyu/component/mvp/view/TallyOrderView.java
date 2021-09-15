package com.xhuyu.component.mvp.view;

import com.xhuyu.component.core.base.BaseView;

public interface TallyOrderView extends BaseView {
    void autoInflaterUI(int i, int i2);

    void onPayFail(String str, boolean z, Object... objArr);

    void payFinish();

    void showToastMessage(boolean z, String str);
}
