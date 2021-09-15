package com.xhuyu.component.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.xhuyu.component.mvp.model.PayWayBean;

public interface PayActivityPresenter {
    void calculateTheLayout(Context context);

    void doGetPaymentList(Activity activity);

    void doUniformOrder(PayWayBean payWayBean);

    void onActivityResult(int i, int i2, Intent intent);

    void onDestroy();

    void onPause();
}
