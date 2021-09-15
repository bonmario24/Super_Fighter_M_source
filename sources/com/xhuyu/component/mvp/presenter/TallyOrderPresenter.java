package com.xhuyu.component.mvp.presenter;

import android.content.Context;

public interface TallyOrderPresenter {
    void calculateTheLayout(Context context);

    void doPayment(String str, String str2);
}
