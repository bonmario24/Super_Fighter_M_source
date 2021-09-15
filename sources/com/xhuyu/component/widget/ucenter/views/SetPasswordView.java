package com.xhuyu.component.widget.ucenter.views;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.doraemon.p027eg.CheckUtils;
import com.xhuyu.component.callback.OnMultiClickListener;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xhuyu.component.mvp.presenter.impl.ChangePasswordPresenterImpl;
import com.xhuyu.component.mvp.view.PasswordView;
import com.xhuyu.component.utils.RSAUtil;
import com.xhuyu.component.utils.ViewUtil;
import com.xhuyu.component.widget.LoadingDialog;
import com.xhuyu.component.widget.TitleBar;
import com.xhuyu.component.widget.ucenter.IViewWrapper;

public class SetPasswordView extends IViewWrapper implements PasswordView {
    private Button btncomfir;
    private EditText edtPass;
    private ImageView ivEye;
    private ChangePasswordPresenterImpl mPresenter;
    private LoadingDialog showLoading;

    public SetPasswordView(Context context) {
        super(context);
    }

    public void initView() {
        this.mPresenter = new ChangePasswordPresenterImpl(this);
        this.ivEye = (ImageView) findViewByName("iv_eye");
        this.btncomfir = (Button) findViewByName("btn_next");
        this.edtPass = (EditText) findViewByName("edt_input_pwd");
        TitleBar titleBar = new TitleBar(findViewByName("rl_title_contains"));
        titleBar.setTitle(getString("huyu_account_set_pwd"));
        titleBar.setBackClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SetPasswordView.this.finish();
            }
        });
        this.btncomfir.setOnClickListener(new OnMultiClickListener() {
            public void onMultiClick(View view) {
                SetPasswordView.this.setPassword();
            }
        });
        ViewUtil.bindButtonEnable(this.btncomfir, new EditText[]{this.edtPass});
        ViewUtil.bindPassowrdVisiable(this.ivEye, this.edtPass);
        ViewUtil.bindFocusVisiable(this.edtPass, findViewByName("iv_delete"));
        this.ivEye.performClick();
    }

    /* access modifiers changed from: private */
    public void setPassword() {
        this.mPresenter.doSetPassword(this.edtPass.getText().toString().trim());
    }

    public String getLayoutName() {
        return "view_set_password";
    }

    public void onComplete() {
        HuYuUser user = UserManager.getInstance().getUser();
        user.setPwd_status(1);
        String password = RSAUtil.publicEncrypt(this.edtPass.getText().toString().trim());
        user.setPassword(password);
        UserManager.getInstance().saveUser(user);
        UserManager.getInstance().changePassword(password);
        goHome();
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
}
