package com.xhuyu.component.mvp.presenter;

import android.app.Activity;
import android.content.Context;

public interface NormalLoginFragmentPresenter {
    void calculateTheLayout(Context context, int i);

    void doGoogleLogin(Activity activity);

    void doVisitorLogin();

    void initThreadSDK(Activity activity);

    void loadHistoryUser();

    void requestAccountLogin(String str, String str2);
}
