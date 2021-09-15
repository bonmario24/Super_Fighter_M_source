package com.xhuyu.component.widget.ucenter.views;

import android.content.Context;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.util.SizeUtils;
import com.xhuyu.component.mvp.model.CountryModel;
import com.xhuyu.component.mvp.presenter.impl.ChangePhoneVerifyPresenterImpl;
import com.xhuyu.component.mvp.view.PhoneView;
import com.xsdk.doraemon.event.SDKEventBus;
import com.xsdk.doraemon.event.Subscribe;
import org.json.JSONObject;

public class ChangePhoneVerifyView extends PhoneCodeView implements PhoneView {
    private final ChangePhoneVerifyPresenterImpl mPresenter = new ChangePhoneVerifyPresenterImpl(this);

    public ChangePhoneVerifyView(Context context, String phoneNumber, String areaCode, String areaAbbreviation) {
        super(context, phoneNumber);
        SDKEventBus.getDefault().register(this);
        this.titleBar.setTitle(getString("huyu_tip_verify_phone"));
        this.btnConfirm.setText(getString("huyu_next"));
        setPhoneEnable(false);
        if (!CheckUtils.isNullOrEmpty(areaAbbreviation) && !CheckUtils.isNullOrEmpty(areaCode)) {
            setSelectCountryEnable(false);
        }
        if (!CheckUtils.isNullOrEmpty(areaAbbreviation)) {
            this.tvAbv.setText(areaAbbreviation);
        }
        if (!CheckUtils.isNullOrEmpty(areaCode)) {
            this.tvCode.setText("+" + areaCode);
        }
        this.tvCode.setPadding(SizeUtils.dp2px(5.0f), 0, SizeUtils.dp2px(10.0f), 0);
    }

    /* access modifiers changed from: protected */
    public void getCode(String phoneNumber, String areaCode, String areaAbbreviation) {
        this.mPresenter.doGetPhoneCodeInLogin();
    }

    /* access modifiers changed from: protected */
    public void confirmPhone(String phone, String code, String areaCode, String areaAbbreviation) {
        this.mPresenter.doChangePhoneVerify(phone, code, 1, areaCode, areaAbbreviation);
    }

    public void onError(String message, int code) {
    }

    public void onComplete(JSONObject success, String message) {
        showToast(message);
        startView(new ChangePhoneView(getContext(), ""));
    }

    /* access modifiers changed from: protected */
    @Subscribe(event = {16})
    public void onReceiverCountryData(CountryModel data) {
        super.onReceiverCountryData(data);
    }
}
