package com.xhuyu.component.p036ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.xhuyu.component.callback.BaseTextWatcher;
import com.xhuyu.component.callback.OnAccountSelectListener;
import com.xhuyu.component.core.base.BaseLoginComponentView;
import com.xhuyu.component.core.config.SDKConfig;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xhuyu.component.mvp.presenter.impl.NormalLoginFragmentPresenterImpl;
import com.xhuyu.component.utils.ViewUtil;
import com.xhuyu.component.utils.appsflyer.AFEventType;
import com.xhuyu.component.utils.appsflyer.AppsFlyerTrackUtil;
import com.xhuyu.component.utils.third.FacebookLoginUtil;
import com.xhuyu.component.widget.account.AccoutInputLayout;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import com.xsdk.doraemon.widget.DelEditTextView;
import com.xsdk.doraemon.widget.TInputConnection;
import com.xsdk.doraemon.widget.circular.CircularModel;
import com.xsdk.doraemon.widget.circular.HorizontalScrollCircularView;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xhuyu.component.ui.fragment.LoginByAccountFragment */
public class LoginByAccountFragment extends BaseLoginComponentView implements View.OnClickListener {
    private static final String IC_FACEBOOK = "ic_facebook_login";
    private static final String IC_GOOGLE = "ic_google_login";
    private static final String ID_FACEBOOK = "iv_facebook_login";
    private static final String ID_GOOGLE = "iv_google_login";
    private static final String MIX_PWD = "00000000";
    private Button btnLogin;
    List<CircularModel> circularModelList;
    /* access modifiers changed from: private */
    public EditText edtAccount;
    /* access modifiers changed from: private */
    public DelEditTextView edtPassword;
    private HorizontalScrollCircularView horizontalScrollView;
    /* access modifiers changed from: private */
    public boolean inputing = false;
    /* access modifiers changed from: private */
    public LinearLayout llContains;
    /* access modifiers changed from: private */
    public AccoutInputLayout lyAccount;
    /* access modifiers changed from: private */
    public NormalLoginFragmentPresenterImpl mPresenter;
    private OnAccountSelectListener onAccountSelectListener = new OnAccountSelectListener() {
        public void OnAccountSelect(HuYuUser user) {
            setAccountInfo(user);
        }

        public void OnAccountDelete(HuYuUser user, boolean fromList) {
            HuYuUser newUser;
            if (user.getUsername().equals(LoginByAccountFragment.this.edtAccount.getText().toString())) {
                LoginByAccountFragment.this.edtAccount.setText("");
                LoginByAccountFragment.this.edtPassword.setText("");
                if (fromList && (newUser = LoginByAccountFragment.this.lyAccount.getNewXuser()) != null) {
                    setAccountInfo(newUser);
                }
            }
            if (fromList && UserManager.getInstance().getUser() != null && user.getUsername().equals(UserManager.getInstance().getUser().getUsername())) {
                UserManager.getInstance().deleteUser();
            }
        }

        public void onAccountListShow() {
        }

        public void onAccountListInit() {
            setAccountInfo(LoginByAccountFragment.this.lyAccount.getNewXuser());
        }

        private void setAccountInfo(HuYuUser user) {
            if (user != null) {
                LoginByAccountFragment.this.edtAccount.setText(user.getUsername());
                LoginByAccountFragment.this.setPassword(user.getPassword());
            }
        }
    };
    private LinearLayout separatorContains;
    ScrollView slContains;
    private TextView tvAgreement;
    private TextView tvForget;

    public static LoginByAccountFragment getInstance() {
        return new LoginByAccountFragment();
    }

    /* access modifiers changed from: protected */
    public String getLayoutName() {
        return "x_fragment_normal_login";
    }

    /* access modifiers changed from: protected */
    public void initView(View contentView, Bundle bundle) {
        this.mPresenter = new NormalLoginFragmentPresenterImpl(this);
        this.mPresenter.initThreadSDK(getActivity());
        setupUI();
        setUiListener();
        this.mPresenter.loadHistoryUser();
    }

    private void setupUI() {
        this.circularModelList = new ArrayList();
        this.edtAccount = (EditText) getViewByName("edt_account");
        this.tvAgreement = (TextView) getViewByName("tv_xf_agreement");
        this.edtPassword = (DelEditTextView) getViewByName("edt_pwd");
        this.lyAccount = (AccoutInputLayout) getViewByName("ly_account");
        this.tvForget = (TextView) getViewByName("tv_forget");
        this.slContains = (ScrollView) getViewByName("sl_view_contains");
        this.llContains = (LinearLayout) getViewByName("ll_contains");
        this.separatorContains = (LinearLayout) getViewByName("ll_separator_contains");
        this.btnLogin = (Button) getViewByName("btn_hy_login");
        this.horizontalScrollView = (HorizontalScrollCircularView) getViewByName("horizontal_scroll_view");
        this.tvForget.setVisibility(8);
        createThirdLoginView();
        uiVisibilityToggle();
    }

    private void uiVisibilityToggle() {
        ViewUtil.bindFocusVisiable(this.edtPassword, getViewByName("iv_delete_pass"));
    }

    private void createThirdLoginView() {
        if (SDKConfig.getInstance().isShowGoogleLogin()) {
            addResLoginIcon(IC_GOOGLE);
        }
        if (SDKConfig.getInstance().isShowFacebookLogin()) {
            addResLoginIcon(IC_FACEBOOK);
        }
        if (this.circularModelList != null && this.circularModelList.size() > 0) {
            this.separatorContains.setVisibility(0);
            this.horizontalScrollView.addImages(this.circularModelList).setOnClickListener(new HorizontalScrollCircularView.OnCircularViewItemClick() {
                public void onItemClick(int id) {
                    if (id == LoginByAccountFragment.this.getWidgetViewID(LoginByAccountFragment.ID_GOOGLE)) {
                        AppsFlyerTrackUtil.trackStartLogin(LoginByAccountFragment.this._mActivity, AFEventType.GOOGLE_LOGIN);
                        LoginByAccountFragment.this.mPresenter.doGoogleLogin(LoginByAccountFragment.this._mActivity);
                    } else if (id == LoginByAccountFragment.this.getWidgetViewID(LoginByAccountFragment.ID_FACEBOOK)) {
                        AppsFlyerTrackUtil.trackStartLogin(LoginByAccountFragment.this._mActivity, AFEventType.FACEBOOK_LOGIN);
                        FacebookLoginUtil.getInstance().doFacebookLogin();
                    }
                }
            }).commit();
        }
    }

    private void setUiListener() {
        this.slContains.setVisibility(4);
        this.llContains.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                LoginByAccountFragment.this.mPresenter.calculateTheLayout(LoginByAccountFragment.this.getActivity(), LoginByAccountFragment.this.llContains.getHeight() + LoginByAccountFragment.this.slContains.getPaddingTop() + LoginByAccountFragment.this.slContains.getPaddingBottom());
                if (Build.VERSION.SDK_INT >= 16) {
                    LoginByAccountFragment.this.llContains.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    LoginByAccountFragment.this.llContains.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
        this.tvAgreement.setOnClickListener(this);
        this.btnLogin.setOnClickListener(this);
        this.tvForget.setOnClickListener(this);
        this.lyAccount.setOnAccountSelectListener(this.onAccountSelectListener);
        this.edtPassword.addTextChangedListener(new BaseTextWatcher() {
            public void afterTextChanged(Editable s) {
                if (!LoginByAccountFragment.this.inputing) {
                    LoginByAccountFragment.this.edtPassword.setTag((Object) null);
                    LoginByAccountFragment.this.mPresenter.reset(LoginByAccountFragment.this.edtPassword.getText().toString(), LoginByAccountFragment.this.edtAccount.getText().toString());
                }
            }
        });
        this.edtAccount.addTextChangedListener(new BaseTextWatcher() {
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s) || LoginByAccountFragment.this.edtPassword.getText().toString().equals(LoginByAccountFragment.MIX_PWD)) {
                    LoginByAccountFragment.this.edtPassword.setText("");
                }
                LoginByAccountFragment.this.mPresenter.reset(LoginByAccountFragment.this.edtPassword.getText().toString(), LoginByAccountFragment.this.edtAccount.getText().toString());
            }
        });
        this.edtPassword.setBackSpaceListener(new TInputConnection.BackspaceListener() {
            public boolean onBackspace() {
                Editable editable = LoginByAccountFragment.this.edtPassword.getText();
                if (editable.length() == 0 || !editable.toString().equals(LoginByAccountFragment.MIX_PWD)) {
                    return false;
                }
                LoginByAccountFragment.this.edtPassword.setText("");
                return false;
            }
        });
    }

    private void addResLoginIcon(String imageName) {
        Drawable drawable = ReflectResource.getInstance(this._mActivity).getDrawable(imageName);
        if (drawable != null) {
            int viewID = -1;
            char c = 65535;
            switch (imageName.hashCode()) {
                case 1266505960:
                    if (imageName.equals(IC_GOOGLE)) {
                        c = 0;
                        break;
                    }
                    break;
                case 1549503093:
                    if (imageName.equals(IC_FACEBOOK)) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    viewID = getWidgetViewID(ID_GOOGLE);
                    break;
                case 1:
                    viewID = getWidgetViewID(ID_FACEBOOK);
                    break;
            }
            if (viewID > 0) {
                this.circularModelList.add(new CircularModel(viewID, drawable, 0.6f));
            }
        }
    }

    public void changePwdEditViewType(int inputType) {
        this.edtPassword.setInputType(inputType);
    }

    public void autoInflaterUI(int width, int height) {
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(width, height);
        rlp.addRule(13);
        this.slContains.setLayoutParams(rlp);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.separatorContains.getLayoutParams();
        layoutParams.width = (int) (((double) width) * 0.7d);
        layoutParams.gravity = 17;
        this.separatorContains.setLayoutParams(layoutParams);
        this.slContains.post(new Runnable() {
            public void run() {
                LoginByAccountFragment.this.slContains.setVisibility(0);
            }
        });
    }

    public void showAccountInfo(boolean isNewCreate, String account, String password) {
        if (!TextUtils.isEmpty(account)) {
            this.edtAccount.setText(account);
        }
        setPassword(password);
        this.edtPassword.setInputType(isNewCreate ? 145 : 129);
    }

    /* access modifiers changed from: private */
    public void setPassword(String password) {
        if (password == null) {
            password = "";
        }
        this.inputing = true;
        this.edtPassword.setTag(password);
        if (password.length() >= 32) {
            password = MIX_PWD;
        }
        this.edtPassword.setText(password);
        this.inputing = false;
    }

    private void login() {
        String account = this.edtAccount.getText().toString();
        String password = this.edtPassword.getTag() == null ? "" : this.edtPassword.getTag().toString();
        if (TextUtils.isEmpty(password)) {
            password = this.edtPassword.getText().toString();
        }
        SDKLoggerUtil.getLogger().mo19501d("begin to request login....", new Object[0]);
        this.mPresenter.requestAccountLogin(account, password);
    }

    public void onClick(View v) {
        if (v.getId() == this.tvAgreement.getId()) {
            start(UserAgreementFragment.getInstance());
        } else if (v.getId() == this.btnLogin.getId()) {
            login();
        } else if (v.getId() == this.tvForget.getId()) {
            start(ForgetPasswordFragment.getInstance());
        }
    }
}
