package com.xhuyu.component.widget.ucenter.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.doraemon.p027eg.CheckUtils;
import com.xhuyu.component.callback.BaseTextWatcher;
import com.xhuyu.component.callback.OnMultiClickListener;
import com.xhuyu.component.mvp.model.CountryModel;
import com.xhuyu.component.mvp.view.PhoneBaseView;
import com.xhuyu.component.utils.ViewUtil;
import com.xhuyu.component.widget.CountDownTimerButton;
import com.xhuyu.component.widget.LoadingDialog;
import com.xhuyu.component.widget.TitleBar;
import com.xhuyu.component.widget.ucenter.IViewWrapper;
import com.xsdk.doraemon.event.SDKEventBus;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

public abstract class PhoneCodeView extends IViewWrapper implements PhoneBaseView {
    protected Button btnConfirm;
    protected CountDownTimerButton btnSendCode;
    protected EditText editCode;
    protected EditText edtPhone;
    protected boolean isPhoneType;
    protected View ivDown;
    protected String mPhoneNumber;
    protected View rlCountryContains;
    protected LoadingDialog showLoading;
    protected View smsContains;
    protected TitleBar titleBar;
    protected TextView tvAbv;
    protected TextView tvCode;

    /* access modifiers changed from: protected */
    public abstract void confirmPhone(String str, String str2, String str3, String str4);

    /* access modifiers changed from: protected */
    public abstract void getCode(String str, String str2, String str3);

    public PhoneCodeView(Context context, String phoneNumber) {
        this(context, phoneNumber, true);
    }

    public PhoneCodeView(Context context, String phoneNumber, boolean isPhoneType2) {
        super(context);
        this.mPhoneNumber = phoneNumber;
        this.isPhoneType = isPhoneType2;
        layoutForType();
    }

    public void initView() {
    }

    private void layoutForType() {
        this.titleBar = new TitleBar(findViewByName("rl_title_contains"));
        this.titleBar.setBackClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PhoneCodeView.this.finish();
            }
        });
        this.edtPhone = (EditText) findViewByName("edt_phone");
        this.edtPhone.setText(this.mPhoneNumber);
        this.btnConfirm = (Button) findViewByName("btn_confirm");
        this.editCode = (EditText) findViewByName("edt_code");
        this.btnSendCode = (CountDownTimerButton) findViewByName("btn_send_code");
        this.tvAbv = (TextView) findViewByName("tv_abv");
        this.tvCode = (TextView) findViewByName("tv_code");
        this.smsContains = findViewByName("ll_sms_contains");
        this.ivDown = findViewByName("iv_down");
        this.rlCountryContains = findViewByName("rl_country_contains");
        this.rlCountryContains.setOnClickListener(new OnMultiClickListener() {
            public void onMultiClick(View view) {
                PhoneCodeView.this.startView(new SelectCountryView(PhoneCodeView.this.getContext()));
            }
        });
        ViewUtil.bindButtonEnable(this.btnConfirm, new EditText[]{this.edtPhone, this.editCode});
        this.btnSendCode.setOnClickListener(new OnMultiClickListener() {
            public void onMultiClick(View view) {
                PhoneCodeView.this.getCode();
            }
        });
        this.btnConfirm.setOnClickListener(new OnMultiClickListener() {
            public void onMultiClick(View view) {
                PhoneCodeView.this.confirmPhone();
            }
        });
        this.edtPhone.addTextChangedListener(new BaseTextWatcher() {
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    PhoneCodeView.this.editCode.setText("");
                }
            }
        });
        ViewUtil.bindFocusVisiable(this.edtPhone, findViewByName("iv_delete_phone"));
        ViewUtil.bindFocusVisiable(this.editCode, findViewByName("iv_delete_code"));
    }

    /* access modifiers changed from: private */
    public void getCode() {
        String abv = "";
        String countryCode = "";
        if (this.isPhoneType) {
            abv = this.tvAbv.getText().toString().trim();
            countryCode = this.tvCode.getText().toString().trim().replaceAll("\\+", "");
            if (CheckUtils.isNullOrEmpty(countryCode) || CheckUtils.isNullOrEmpty(abv) || abv.equals("huyu_select_country")) {
                showToast(getString("huyu_completion_search_hint"));
                return;
            }
        }
        getCode(getPhone(), countryCode, abv);
    }

    /* access modifiers changed from: protected */
    public String getPhone() {
        if (this.edtPhone.isEnabled()) {
            return this.edtPhone.getText().toString().trim();
        }
        return this.mPhoneNumber;
    }

    /* access modifiers changed from: protected */
    public void setSelectCountryEnable(boolean enable) {
        this.rlCountryContains.setEnabled(enable);
        if (!enable) {
            this.ivDown.setVisibility(8);
        } else {
            this.ivDown.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void setPhoneEnable(boolean enable) {
        this.edtPhone.setEnabled(enable);
        if (!enable) {
            this.mPhoneNumber = this.edtPhone.getText().toString().trim();
            this.edtPhone.setText(this.mPhoneNumber);
        }
    }

    /* access modifiers changed from: private */
    public void confirmPhone() {
        String phone = getPhone();
        String code = this.editCode.getText().toString().trim();
        if (CheckUtils.isNullOrEmpty(code)) {
            showToast(getString("huyu_tip_input_right_code"));
            return;
        }
        String abv = "";
        String countryCode = "";
        if (this.isPhoneType) {
            abv = this.tvAbv.getText().toString().trim();
            countryCode = this.tvCode.getText().toString().trim().replaceAll("\\+", "");
            if (CheckUtils.isNullOrEmpty(countryCode) || CheckUtils.isNullOrEmpty(abv) || abv.equals("huyu_select_country")) {
                showToast(getString("huyu_completion_search_hint"));
                return;
            }
        }
        confirmPhone(phone, code, countryCode, abv);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.mPhoneNumber = phoneNumber;
        if (!TextUtils.isEmpty(phoneNumber)) {
            this.edtPhone.setText(phoneNumber);
        }
    }

    /* access modifiers changed from: protected */
    public void onReceiverCountryData(CountryModel data) {
        try {
            SDKLoggerUtil.getLogger().mo19504i("onReceiverResultData :" + data.toString(), new Object[0]);
            this.tvAbv.setText(data.getAbbreviation());
            this.tvCode.setText("+" + data.getAreaCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startTimerToVerificationCode() {
        this.btnSendCode.startTimer();
    }

    public void showToastMessage(boolean fromRes, String message) {
        String msg = message;
        if (fromRes && !CheckUtils.isNullOrEmpty(msg)) {
            msg = getString(message);
        }
        showToast(msg);
    }

    public void showDialog() {
        this.showLoading = showLoading((String) null);
    }

    public void closeLoadingDialog() {
        if (this.showLoading != null) {
            this.showLoading.dismiss();
        }
    }

    public void onViewDestroy() {
        SDKEventBus.getDefault().unregister(this);
        super.onViewDestroy();
        this.btnSendCode.stopTimer();
    }

    public String getLayoutName() {
        return "view_check_bind_phone";
    }
}
