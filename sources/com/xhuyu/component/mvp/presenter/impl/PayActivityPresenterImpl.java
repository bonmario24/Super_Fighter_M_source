package com.xhuyu.component.mvp.presenter.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.alibaba.fastjson.JSON;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.NativeProtocol;
import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.core.config.TrackEventKey;
import com.xhuyu.component.mvp.model.PayWayBean;
import com.xhuyu.component.mvp.model.PaymentResult;
import com.xhuyu.component.mvp.model.WalletOrderBean;
import com.xhuyu.component.mvp.presenter.PayActivityPresenter;
import com.xhuyu.component.mvp.view.PayView;
import com.xhuyu.component.network.NetPayUtil;
import com.xhuyu.component.utils.JsonUtil;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.utils.UiCalculateUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class PayActivityPresenterImpl implements PayActivityPresenter {
    private static final String PRODUCT_ID = "product_id";
    private GoogleBillingPresenterImpl mGoogleBillingPresenter;
    /* access modifiers changed from: private */
    public HashMap<String, Object> mPayInfo;
    private String mProductID;
    /* access modifiers changed from: private */
    public PayView mView;

    public PayActivityPresenterImpl(PayView view, HashMap<String, Object> payInfo) {
        this.mView = view;
        this.mPayInfo = payInfo;
        if (this.mPayInfo.size() > 0) {
            this.mProductID = this.mPayInfo.get(PRODUCT_ID).toString();
        }
    }

    public void calculateTheLayout(Context context) {
        int width = UiCalculateUtil.calculateTheLayoutWidth(context);
        int height = UiCalculateUtil.calculateTheLayoutHeight(context, 0.8f, 0.45f);
        if (UiCalculateUtil.isLandscape(context)) {
            height = UiCalculateUtil.calculateTheLayoutHeight(context);
        }
        this.mView.autoInflaterUI(width, height);
    }

    public void doGetPaymentList(final Activity activity) {
        calculateTheLayout(activity);
        this.mView.showDialog();
        NetPayUtil.getPayWays(activity, (String) this.mPayInfo.get("game_role_level"), new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                PayActivityPresenterImpl.this.mView.closeLoadingDialog();
                try {
                    List<PayWayBean> payWayList = JSON.parseArray(dataJson.getString("list"), PayWayBean.class);
                    if (!(payWayList == null || payWayList.size() == 0)) {
                        if (payWayList.size() == 1) {
                            PayWayBean wayBean = payWayList.get(0);
                            if (wayBean.getChild() != null && wayBean.getChild().size() == 1 && wayBean.getChild().get(0).getType() == 1009 && activity != null && !activity.isFinishing()) {
                                PayActivityPresenterImpl.this.doGoogleBilling(wayBean.getChild().get(0).getType(), activity);
                                return;
                            }
                        }
                        PayActivityPresenterImpl.this.mView.initPayWaysData(payWayList);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PayActivityPresenterImpl.this.mView.onPayFail("pay_fail", true, new Object[0]);
            }

            public void onFail(String message, int code) {
                PayActivityPresenterImpl.this.mView.closeLoadingDialog();
                PayActivityPresenterImpl.this.mView.showToastMessage(false, message);
                PayActivityPresenterImpl.this.mView.onPayFail(message, false, new Object[0]);
            }
        });
    }

    public void doUniformOrder(PayWayBean payWayBean) {
        this.mView.showDialog();
        this.mPayInfo.put("pay_type_id", Integer.valueOf(payWayBean.getType()));
        if (!CheckUtils.isNullOrEmpty(payWayBean.getCurrency())) {
            this.mPayInfo.put("pay_currency", payWayBean.getCurrency());
        }
        NetPayUtil.uniformOrder(this.mPayInfo, payWayBean, new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                PayActivityPresenterImpl.this.mView.closeLoadingDialog();
                if (dataJson != null) {
                    SDKLoggerUtil.getLogger().mo19501d("下单成功" + dataJson.toString(), new Object[0]);
                    SDKEventPost.postTrack(TrackEventKey.ON_ORDERS_RESULT, 1, JsonUtil.getString(dataJson, "order_id"));
                    if (dataJson.has(NativeProtocol.WEB_DIALOG_PARAMS)) {
                        try {
                            JSONObject params = dataJson.getJSONObject(NativeProtocol.WEB_DIALOG_PARAMS);
                            String url = null;
                            WalletOrderBean orderBean = null;
                            if (params.has("url")) {
                                url = params.getString("url");
                            } else if (params.has("trade_code")) {
                                String tradeCode = params.getString("trade_code");
                                String tradeUrl = params.optString("trade_url");
                                String amount = params.optString("amount", AppEventsConstants.EVENT_PARAM_VALUE_NO);
                                orderBean = new WalletOrderBean();
                                orderBean.setAmount(dataJson.optString("amount", AppEventsConstants.EVENT_PARAM_VALUE_NO));
                                orderBean.setProductName((String) PayActivityPresenterImpl.this.mPayInfo.get("product_name"));
                                orderBean.setTrade_code(tradeCode);
                                orderBean.setTrade_url(tradeUrl);
                                orderBean.setWalletAmount(amount);
                            }
                            if (!(args == null || args[0] == null || !(args[0] instanceof PayWayBean))) {
                                PayWayBean wayBean = args[0];
                                if (!CheckUtils.isNullOrEmpty(url)) {
                                    PayActivityPresenterImpl.this.mView.onCompleteUniformOrder(wayBean, url);
                                    return;
                                } else if (orderBean != null) {
                                    orderBean.setCurrency(wayBean.getCurrency());
                                    PayActivityPresenterImpl.this.mView.onCompleteUniformOrderGoToWallet(orderBean);
                                    return;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                PayActivityPresenterImpl.this.mView.onPayFail("pay_fail", true, new Object[0]);
            }

            public void onFail(String message, int code) {
                PayActivityPresenterImpl.this.mView.closeLoadingDialog();
                PayActivityPresenterImpl.this.mView.showToastMessage(false, message);
                PayActivityPresenterImpl.this.mView.onPayFail(message, false, new Object[0]);
            }
        });
    }

    public void doGoogleBilling(int payType, Activity activity) {
        if (!CheckUtil.checkPackageApp(activity, "com.android.vending")) {
            SDKLoggerUtil.getLogger().mo19502e("Please install Google store first", new Object[0]);
            SDKEventPost.post(5, new PaymentResult(0, "Please install Google store first"));
            this.mView.payFinish();
            return;
        }
        this.mGoogleBillingPresenter = new GoogleBillingPresenterImpl(this.mView, payType, this.mProductID, this.mPayInfo);
        this.mGoogleBillingPresenter.doInitBilling(activity);
    }

    public void onDestroy() {
        if (this.mGoogleBillingPresenter != null) {
            this.mGoogleBillingPresenter.onDestroy();
        }
    }

    public void onPause() {
        if (this.mGoogleBillingPresenter != null) {
            this.mGoogleBillingPresenter.onPause();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.mGoogleBillingPresenter != null) {
            this.mGoogleBillingPresenter.onActivityResult(requestCode, resultCode, data);
        }
    }
}
