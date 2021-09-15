package com.xhuyu.component.widget.ucenter.views;

import android.content.Context;
import com.doraemon.util.SizeUtils;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xhuyu.component.mvp.presenter.impl.EmailPresenterImpl;
import com.xhuyu.component.mvp.view.EmailView;
import org.json.JSONObject;

public class EmailSettingView extends PhoneCodeView implements EmailView {
    private final EmailPresenterImpl emailPresenter = new EmailPresenterImpl(this);
    private boolean isBinding;

    public EmailSettingView(Context context, String emailAddress, boolean isBinding2) {
        super(context, emailAddress, false);
        this.isBinding = isBinding2;
        changeLayoutForType();
        this.titleBar.setTitle(getString("huyu_bind_email"));
    }

    private void changeLayoutForType() {
        this.edtPhone.setPadding(SizeUtils.dp2px(12.0f), 0, SizeUtils.dp2px(12.0f), 0);
        this.edtPhone.setInputType(32);
        if (this.isBinding) {
            this.edtPhone.setEnabled(false);
            this.btnConfirm.setVisibility(8);
            this.smsContains.setVisibility(8);
        } else {
            this.edtPhone.setText("");
            this.edtPhone.setHint(getString("huyu_hint_input_email"));
        }
        this.rlCountryContains.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void getCode(String email, String areaCode, String areaAbbreviation) {
        this.emailPresenter.requestVerificationCode(email);
    }

    /* access modifiers changed from: protected */
    public void confirmPhone(String email, String verifyCode, String areaCode, String areaAbbreviation) {
        this.emailPresenter.requestBindEmail(email, verifyCode);
    }

    public void onError(String message, int code) {
    }

    public void onComplete(JSONObject success, String message) {
        HuYuUser user = UserManager.getInstance().getUser();
        user.setEmail(this.edtPhone.getText().toString().trim());
        UserManager.getInstance().saveUser(user);
        goHome();
    }
}
