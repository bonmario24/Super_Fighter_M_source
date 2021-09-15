package com.xhuyu.component.p036ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.xhuyu.component.adapter.PaymentAdapter;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.core.base.BaseSupportActivity;
import com.xhuyu.component.mvp.model.PayWayBean;
import com.xhuyu.component.mvp.model.PaymentResult;
import com.xhuyu.component.mvp.model.WalletOrderBean;
import com.xhuyu.component.mvp.presenter.impl.PayActivityPresenterImpl;
import com.xhuyu.component.mvp.view.PayView;
import com.xhuyu.component.utils.ResourceUtil;
import com.xhuyu.component.utils.appsflyer.AppsFlyerTrackUtil;
import com.xsdk.doraemon.utils.CheckUtil;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.xhuyu.component.ui.activity.PaymentActivity */
public class PaymentActivity extends BaseSupportActivity implements PayView, View.OnClickListener {
    private static final String INTENT_EXTRA_KEY = "paymentInfo";
    private ExpandableListView mListView;
    private HashMap<String, Object> mPayInfo;
    /* access modifiers changed from: private */
    public PaymentAdapter mPaymentAdapter;
    /* access modifiers changed from: private */
    public LinearLayout mPaymentContains;
    /* access modifiers changed from: private */
    public PayActivityPresenterImpl mPresenter;

    /* access modifiers changed from: protected */
    public String getLayoutName() {
        return "x_activity_pay";
    }

    /* access modifiers changed from: protected */
    public ViewGroup needTrackContainsView() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUI();
        initPayData();
    }

    private void setupUI() {
        ((TextView) getWidgetView("tv_title")).setText(getStringByName("huyu_title_pay"));
        getWidgetView("iv_back").setOnClickListener(this);
        this.mPaymentContains = (LinearLayout) getWidgetView("ll_payment_contains");
        this.mPaymentContains.setVisibility(8);
        this.mListView = (ExpandableListView) getWidgetView("list_view");
        this.mListView.setGroupIndicator((Drawable) null);
        this.mListView.setDivider((Drawable) null);
        this.mListView.setChildDivider(ResourceUtil.getDrawable("list_divider"));
    }

    private void initPayData() {
        Intent intent = getIntent();
        this.mPayInfo = new HashMap<>();
        if (intent != null) {
            this.mPayInfo = (HashMap) intent.getSerializableExtra(INTENT_EXTRA_KEY);
            this.mPresenter = new PayActivityPresenterImpl(this, this.mPayInfo);
            this.mPresenter.doGetPaymentList(this);
            return;
        }
        finish();
    }

    public void initPayWaysData(List<PayWayBean> payWayList) {
        this.mPaymentAdapter = new PaymentAdapter(payWayList);
        this.mListView.setAdapter(this.mPaymentAdapter);
        this.mPaymentContains.setVisibility(0);
        this.mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (PaymentActivity.this.mPaymentAdapter.getGroup(groupPosition).isExpanded()) {
                    return false;
                }
                PayWayBean childPayWay = PaymentActivity.this.mPaymentAdapter.getFirstChildPayWay(groupPosition);
                if (childPayWay.getType() == 1009) {
                    PaymentActivity.this.mPaymentContains.setVisibility(8);
                    PaymentActivity.this.mPresenter.doGoogleBilling(childPayWay.getType(), PaymentActivity.this);
                    return false;
                }
                AppsFlyerTrackUtil.trackOrderPayment(PaymentActivity.this, childPayWay.getType() + "");
                PaymentActivity.this.mPresenter.doUniformOrder(childPayWay);
                return false;
            }
        });
        this.mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                PayWayBean wayBean = PaymentActivity.this.mPaymentAdapter.getChild(groupPosition, childPosition);
                if (wayBean == null) {
                    return true;
                }
                AppsFlyerTrackUtil.trackOrderPayment(PaymentActivity.this, wayBean.getType() + "");
                PaymentActivity.this.mPresenter.doUniformOrder(wayBean);
                return true;
            }
        });
    }

    public void autoInflaterUI(int width, int height) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        layoutParams.addRule(13);
        this.mPaymentContains.setLayoutParams(layoutParams);
    }

    public void showDialog() {
        showLoadingDialog();
    }

    public void closeLoadingDialog() {
        dismissDialog();
    }

    public void onPayFail(String messageInfo, boolean isResID, Object... args) {
        int i;
        String message = messageInfo;
        if (isResID && !CheckUtil.isEmpty(messageInfo)) {
            showToastMessage(true, messageInfo);
        }
        if (CheckUtil.isEmpty(message)) {
            message = "pay fail";
        }
        if (args != null) {
            for (int i2 = 0; i2 < args.length; i2++) {
                message = message + ":" + args[i2].toString();
            }
        }
        Object[] objArr = new Object[1];
        if (message.contains(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED)) {
            i = 2;
        } else {
            i = 0;
        }
        objArr[0] = new PaymentResult(i, message);
        SDKEventPost.post(5, objArr);
        dismissDialog();
        finish();
    }

    public void showToastMessage(final boolean isResMsg, final String message) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (isResMsg) {
                        PaymentActivity.this.showToast(PaymentActivity.this.getStringByName(message));
                    } else {
                        PaymentActivity.this.showToast(message);
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    public void onCompleteUniformOrder(PayWayBean payWayBean, String url) {
        WebBillingActivity.start(this, payWayBean.getName(), url);
        finish();
    }

    public void onCompleteUniformOrderGoToWallet(WalletOrderBean bean) {
        TallyOrderActivity.start(this, bean);
    }

    public void onClick(View v) {
        if (v.getId() == getWidgetView("iv_back").getId()) {
            onPayFail(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED, false, new Object[0]);
        }
    }

    public void payFinish() {
        dismissDialog();
        finish();
    }

    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        onPayFail(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED, false, new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    public static void start(Context context, HashMap<String, Object> paymentInfo) {
        Intent starter = new Intent(context, PaymentActivity.class);
        starter.putExtra(INTENT_EXTRA_KEY, paymentInfo);
        context.startActivity(starter);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mPresenter.onDestroy();
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.mPresenter.onPause();
        super.onPause();
    }
}
