package com.xhuyu.component.mvp.presenter.impl;

import android.content.Context;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.mvp.model.PaymentResult;
import com.xhuyu.component.mvp.presenter.TallyOrderPresenter;
import com.xhuyu.component.mvp.view.TallyOrderView;
import com.xhuyu.component.network.NetPayUtil;
import com.xsdk.doraemon.utils.UiCalculateUtil;
import org.json.JSONObject;

public class TallyOrderPresenterImpl implements TallyOrderPresenter {
    /* access modifiers changed from: private */
    public TallyOrderView mView;

    public TallyOrderPresenterImpl(TallyOrderView view) {
        this.mView = view;
    }

    public void calculateTheLayout(Context context) {
        int width = UiCalculateUtil.calculateTheLayoutWidth(context);
        int height = UiCalculateUtil.calculateTheLayoutHeight(context, 0.8f, 0.45f);
        if (UiCalculateUtil.isLandscape(context)) {
            height = UiCalculateUtil.calculateTheLayoutHeight(context);
        }
        this.mView.autoInflaterUI(width, height);
    }

    public void doPayment(String tradCode, String requestUrl) {
        this.mView.showDialog();
        NetPayUtil.doPaymentToWallet(tradCode, requestUrl, new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                TallyOrderPresenterImpl.this.mView.closeLoadingDialog();
                SDKEventPost.post(5, new PaymentResult(1, message));
                TallyOrderPresenterImpl.this.mView.showToastMessage(true, "pay_succes");
                TallyOrderPresenterImpl.this.mView.payFinish();
            }

            public void onFail(String message, int code) {
                TallyOrderPresenterImpl.this.mView.closeLoadingDialog();
                TallyOrderPresenterImpl.this.mView.showToastMessage(false, message);
                TallyOrderPresenterImpl.this.mView.onPayFail(message, false, new Object[0]);
            }
        });
    }
}
