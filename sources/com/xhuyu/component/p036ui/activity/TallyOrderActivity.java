package com.xhuyu.component.p036ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.doraemon.p027eg.CheckUtils;
import com.facebook.internal.AnalyticsEvents;
import com.xhuyu.component.adapter.TallyOrderAdapter;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.core.base.BaseSupportActivity;
import com.xhuyu.component.mvp.model.PaymentResult;
import com.xhuyu.component.mvp.model.TallyOrderBean;
import com.xhuyu.component.mvp.model.WalletOrderBean;
import com.xhuyu.component.mvp.presenter.impl.TallyOrderPresenterImpl;
import com.xhuyu.component.mvp.view.TallyOrderView;
import com.xhuyu.component.utils.ActivityUtil;
import com.xhuyu.component.utils.ResourceUtil;
import com.xsdk.doraemon.utils.CheckUtil;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xhuyu.component.ui.activity.TallyOrderActivity */
public class TallyOrderActivity extends BaseSupportActivity implements View.OnClickListener, TallyOrderView {
    private static final String EXTRA_DATA = "extra_data";
    private View btnConfirmPayment;
    private View ivClose;
    private ListView lvPayment;
    private TallyOrderPresenterImpl presenter;
    private LinearLayout tallyLayout;
    private String tradeCode;
    private String tradeUrl;

    /* access modifiers changed from: protected */
    public String getLayoutName() {
        return "x_activity_tally_order";
    }

    /* access modifiers changed from: protected */
    public ViewGroup needTrackContainsView() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new TallyOrderPresenterImpl(this);
        initView();
        initData();
        this.presenter.calculateTheLayout(this);
    }

    private void initData() {
        WalletOrderBean orderBean;
        if (getIntent() == null || (orderBean = (WalletOrderBean) getIntent().getSerializableExtra(EXTRA_DATA)) == null) {
            finish();
            return;
        }
        this.tradeCode = orderBean.getTrade_code();
        this.tradeUrl = orderBean.getTrade_url();
        List<TallyOrderBean> beanList = new ArrayList<>();
        beanList.add(new TallyOrderBean(getStringByName("huyu_order_name"), orderBean.getProductName()));
        beanList.add(new TallyOrderBean(getStringByName("huyu_order_price"), orderBean.getAmount() + "　" + orderBean.getCurrency()));
        TallyOrderBean tallyOrderBean = new TallyOrderBean(getStringByName("huyu_order_settle_accounts"), ResourceUtil.getStringContainFormat("huyu_order_point", orderBean.getWalletAmount()));
        tallyOrderBean.setTextColorString("#FC425F");
        beanList.add(tallyOrderBean);
        this.lvPayment.setAdapter(new TallyOrderAdapter(beanList));
    }

    private void initView() {
        this.tallyLayout = (LinearLayout) getWidgetView("ll_contains_tally");
        ((TextView) getWidgetView("tv_title")).setText(getStringByName("huyu_title_order_confirm"));
        getWidgetView("iv_back").setVisibility(8);
        this.ivClose = getWidgetView("iv_close");
        this.ivClose.setVisibility(0);
        this.btnConfirmPayment = getWidgetView("btn_confirm_payment");
        this.lvPayment = (ListView) getWidgetView("lv_payment");
        setListener();
    }

    private void setListener() {
        this.ivClose.setOnClickListener(this);
        this.btnConfirmPayment.setOnClickListener(this);
    }

    public static void start(Context context, WalletOrderBean bean) {
        Intent starter = new Intent(context, TallyOrderActivity.class);
        starter.putExtra(EXTRA_DATA, bean);
        context.startActivity(starter);
    }

    private void finishSDKActivity() {
        ActivityUtil.finishAllSDKActivity();
    }

    public void onClick(View v) {
        if (v.getId() == this.ivClose.getId()) {
            finish();
        } else if (v.getId() != this.btnConfirmPayment.getId()) {
        } else {
            if (CheckUtils.isNullOrEmpty(this.tradeCode) || CheckUtils.isNullOrEmpty(this.tradeUrl)) {
                onPayFail("pay_fail", true, new Object[0]);
            } else {
                this.presenter.doPayment(this.tradeCode, this.tradeUrl);
            }
        }
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

    public void payFinish() {
        dismissDialog();
        finish();
        finishSDKActivity();
    }

    public void showToastMessage(final boolean isResMsg, final String message) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (isResMsg) {
                        TallyOrderActivity.this.showToast(TallyOrderActivity.this.getStringByName(message));
                    } else {
                        TallyOrderActivity.this.showToast(message);
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    public void showDialog() {
        showLoadingDialog();
    }

    public void closeLoadingDialog() {
        dismissDialog();
    }

    public void autoInflaterUI(int width, int height) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        layoutParams.addRule(13);
        this.tallyLayout.setLayoutParams(layoutParams);
    }
}
