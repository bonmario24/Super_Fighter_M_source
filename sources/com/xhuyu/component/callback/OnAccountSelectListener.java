package com.xhuyu.component.callback;

import com.xhuyu.component.mvp.model.HuYuUser;

public interface OnAccountSelectListener {
    void OnAccountDelete(HuYuUser huYuUser, boolean z);

    void OnAccountSelect(HuYuUser huYuUser);

    void onAccountListInit();

    void onAccountListShow();
}
