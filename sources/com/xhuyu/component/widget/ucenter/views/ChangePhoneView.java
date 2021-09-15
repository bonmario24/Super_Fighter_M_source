package com.xhuyu.component.widget.ucenter.views;

import android.content.Context;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.mvp.model.CountryModel;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xhuyu.component.mvp.presenter.impl.ChangePhoneVerifyPresenterImpl;
import com.xhuyu.component.mvp.view.PhoneView;
import com.xsdk.doraemon.event.SDKEventBus;
import com.xsdk.doraemon.event.Subscribe;
import org.json.JSONObject;

public class ChangePhoneView extends PhoneCodeView implements PhoneView {
    private final ChangePhoneVerifyPresenterImpl mPresenter = new ChangePhoneVerifyPresenterImpl(this);

    public ChangePhoneView(Context context, String phoneNumber) {
        super(context, phoneNumber);
        SDKEventBus.getDefault().register(this);
        this.tvAbv.setText("TW");
        this.tvCode.setText("+886");
        this.titleBar.setTitle(getString("huyu_tip_change_phone"));
        this.edtPhone.setHint(getString("huyu_hit_input_new_phone"));
    }

    /* access modifiers changed from: protected */
    public void getCode(String phoneNumber, String areaCode, String areaAbbreviation) {
        this.mPresenter.requestVerificationCode(phoneNumber, areaCode, areaAbbreviation);
    }

    /* access modifiers changed from: protected */
    public void confirmPhone(String phone, String code, String areaCode, String areaAbbreviation) {
        this.mPresenter.doChangePhoneVerify(phone, code, 2, areaCode, areaAbbreviation);
    }

    public void onError(String message, int code) {
    }

    public void onComplete(JSONObject success, String message) {
        HuYuUser user = UserManager.getInstance().getUser();
        user.setPhone(this.mPhoneNumber);
        UserManager.getInstance().saveUser(user);
        goHome();
    }

    /* access modifiers changed from: protected */
    @Subscribe(event = {16})
    public void onReceiverCountryData(CountryModel data) {
        super.onReceiverCountryData(data);
    }
}
