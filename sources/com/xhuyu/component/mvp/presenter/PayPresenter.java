package com.xhuyu.component.mvp.presenter;

import android.app.Activity;
import android.content.Intent;

public interface PayPresenter {
    void doInitBilling(Activity activity);

    void onActivityResult(int i, int i2, Intent intent);

    void onDestroy();

    void onPause();
}
