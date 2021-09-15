package com.xhuyu.component.mvp.view;

import com.xhuyu.component.core.base.BaseView;
import com.xhuyu.component.mvp.model.PayWayBean;
import com.xhuyu.component.mvp.model.WalletOrderBean;
import java.util.List;

public interface PayView extends BaseView {
    void autoInflaterUI(int i, int i2);

    void initPayWaysData(List<PayWayBean> list);

    void onCompleteUniformOrder(PayWayBean payWayBean, String str);

    void onCompleteUniformOrderGoToWallet(WalletOrderBean walletOrderBean);

    void onPayFail(String str, boolean z, Object... objArr);

    void payFinish();

    void showToastMessage(boolean z, String str);
}
