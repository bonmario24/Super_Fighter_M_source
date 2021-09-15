package com.xhuyu.component.widget.ucenter.views;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.doraemon.p027eg.CheckUtils;
import com.xhuyu.component.callback.OnMultiClickListener;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.mvp.presenter.impl.ChangePasswordPresenterImpl;
import com.xhuyu.component.mvp.view.PasswordView;
import com.xhuyu.component.utils.RSAUtil;
import com.xhuyu.component.utils.ViewUtil;
import com.xhuyu.component.widget.LoadingDialog;
import com.xhuyu.component.widget.TitleBar;
import com.xhuyu.component.widget.ucenter.IViewWrapper;

public class ChangePasswordView extends IViewWrapper implements PasswordView {
    private Button btnComfir;
    private EditText edtOld;
    private EditText edtnew;
    private ChangePasswordPresenterImpl mPresenter;
    private LoadingDialog showLoading;

    public ChangePasswordView(Context context) {
        super(context);
    }

    public void initView() {
        this.mPresenter = new ChangePasswordPresenterImpl(this);
        this.btnComfir = (Button) findViewByName("btn_confirm");
        this.edtOld = (EditText) findViewByName("edt_old_password");
        this.edtnew = (EditText) findViewByName("edt_new_password");
        ViewUtil.bindButtonEnable(this.btnComfir, new EditText[]{this.edtOld, this.edtnew});
        ViewUtil.bindFocusVisiable(this.edtOld, findViewByName("iv_delete_old"));
        ViewUtil.bindFocusVisiable(this.edtnew, findViewByName("iv_delete_new"));
        ImageView ivSee = (ImageView) findViewByName("iv_see");
        ViewUtil.bindPassowrdVisiable(ivSee, this.edtnew);
        ivSee.performClick();
        TitleBar titleBar = new TitleBar(findViewByName("rl_title_contains"));
        titleBar.setTitle(getString("huyu_account_change_pwd"));
        titleBar.setBackClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ChangePasswordView.this.finish();
            }
        });
        this.btnComfir.setOnClickListener(new OnMultiClickListener() {
            public void onMultiClick(View view) {
                ChangePasswordView.this.resetPassword();
            }
        });
    }

    /* access modifiers changed from: private */
    public void resetPassword() {
        this.mPresenter.doModifyPassword(this.edtOld.getText().toString().trim(), this.edtnew.getText().toString().trim());
    }

    public String getLayoutName() {
        return "view_change_password";
    }

    public void onComplete() {
        UserManager.getInstance().changePassword(RSAUtil.publicEncrypt(this.edtnew.getText().toString().trim()));
        finish();
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
