package com.xhuyu.component.widget.ucenter.views;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.doraemon.p027eg.CheckUtils;
import com.xhuyu.component.callback.OnMultiClickListener;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.core.api.HuYuApi;
import com.xhuyu.component.core.manager.UserManager;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xhuyu.component.mvp.model.LoginResult;
import com.xhuyu.component.network.NetUserUtil;
import com.xhuyu.component.utils.ResourceUtil;
import com.xhuyu.component.widget.LoadingDialog;
import com.xhuyu.component.widget.ucenter.IViewWrapper;
import com.xhuyu.component.widget.ucenter.TipDiaglogView;
import com.xhuyu.component.widget.ucenter.adapter.UCenterAccountAdapter;
import com.xhuyu.component.widget.ucenter.bean.AccountInfoBean;
import com.xsdk.doraemon.event.SDKEventBus;
import com.xsdk.doraemon.event.Subscribe;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class AccountInfoView extends IViewWrapper {
    private UCenterAccountAdapter accountAdapter;
    private List<AccountInfoBean> accountDataList;
    private View llCoinContains;
    /* access modifiers changed from: private */
    public LoadingDialog loadingDialog;
    private ListView lvAccount;
    private TextView tvCoin;
    private TextView tvUserName;

    public AccountInfoView(Context context) {
        super(context);
        SDKEventBus.getDefault().register(this);
    }

    @Subscribe(event = {3})
    public void onLoginResult(LoginResult result) {
        try {
            if (result.getResultCode() == 1) {
                goHome();
            }
        } catch (Exception e) {
        }
    }

    @Subscribe(event = {6})
    public void onLogoutResult() {
        try {
            goHome();
        } catch (Exception e) {
        }
    }

    public void onViewStart() {
        super.onViewStart();
        updateUserInfo();
    }

    public void initView() {
        this.tvUserName = (TextView) findViewByName("tv_user_name");
        this.tvCoin = (TextView) findViewByName("tv_coin");
        this.llCoinContains = findViewByName("ll_coin_contains");
        this.lvAccount = (ListView) findViewByName("lv_account");
        intData();
        this.accountAdapter = new UCenterAccountAdapter(this.accountDataList);
        this.accountAdapter.setOnItemClickListener(new UCenterAccountAdapter.OnItemClickListener() {
            public void onClick(int position, AccountInfoBean info) {
                switch (info.getItemType()) {
                    case 1:
                        AccountInfoView.this.changePassword();
                        return;
                    case 2:
                        AccountInfoView.this.changeEmail();
                        return;
                    case 3:
                        AccountInfoView.this.changePhone();
                        return;
                    case 4:
                        AccountInfoView.this.startView(new SwitchLanguageView(AccountInfoView.this.getContext()));
                        return;
                    default:
                        return;
                }
            }
        });
        this.lvAccount.setAdapter(this.accountAdapter);
        setOnClickListener("btn_logout", new OnMultiClickListener() {
            public void onMultiClick(View view) {
                AccountInfoView.this.changeAccount();
            }
        });
    }

    /* access modifiers changed from: private */
    public void changeEmail() {
        HuYuUser user = checkUser();
        if (user != null) {
            if (!TextUtils.isEmpty(user.getEmail())) {
                startView(new EmailSettingView(getContext(), user.getEmail(), true));
            } else {
                startView(new EmailSettingView(getContext(), "", false));
            }
        }
    }

    private HuYuUser checkUser() {
        HuYuUser user = UserManager.getInstance().getUser();
        if (user != null) {
            return user;
        }
        showToast(getString("huyu_tip_data_error"));
        return null;
    }

    /* access modifiers changed from: private */
    public void changePhone() {
        HuYuUser user = checkUser();
        if (user != null) {
            if (!TextUtils.isEmpty(user.getPhone())) {
                startView(new ChangePhoneVerifyView(getContext(), user.getPhone(), user.getArea_code(), user.getArea_abbreviation()));
            } else {
                startView(new BindPhoneView(getContext(), user.getPhone()));
            }
        }
    }

    /* access modifiers changed from: private */
    public void changePassword() {
        HuYuUser user = checkUser();
        if (user != null) {
            if (user.getPassword_status() == 0) {
                startView(new SetPasswordView(getContext()));
            } else {
                startView(new ChangePasswordView(getContext()));
            }
        }
    }

    /* access modifiers changed from: private */
    public void changeAccount() {
        showDialog("huyu_tip_switch", new TipDiaglogView.IDialogViewListener() {
            public void onCancel(TipDiaglogView dialog) {
                dialog.dismiss();
            }

            public void onComfir(TipDiaglogView dialog) {
                dialog.dismiss();
                Activity activity = AccountInfoView.this.getActivity();
                if (activity != null && !activity.isFinishing()) {
                    HuYuApi.getInstance().doLogout();
                    activity.finish();
                }
            }
        });
    }

    private void updateUserInfo() {
        intData();
        if (this.loadingDialog == null) {
            this.loadingDialog = showLoading((String) null);
        }
        NetUserUtil.postGetUserInfo(new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                if (AccountInfoView.this.loadingDialog != null) {
                    AccountInfoView.this.loadingDialog.dismiss();
                }
                HuYuUser user = (HuYuUser) JSON.parseObject(dataJson.toString(), HuYuUser.class);
                HuYuUser oldUser = UserManager.getInstance().getUser();
                oldUser.setPassword_status(user.getPassword_status());
                oldUser.setUser_wallet_amount(user.getUser_wallet_amount());
                oldUser.setIdentity_status(user.getIdentity_status());
                oldUser.setUsername(user.getUsername());
                oldUser.setPhone(user.getPhone());
                oldUser.setArea_abbreviation(user.getArea_abbreviation());
                oldUser.setEmail(user.getEmail());
                oldUser.setArea_code(user.getArea_code());
                AccountInfoView.this.setUserInfo(oldUser);
            }

            public void onFail(String message, int code) {
                if (AccountInfoView.this.loadingDialog != null) {
                    AccountInfoView.this.loadingDialog.dismiss();
                }
                AccountInfoView.this.showToast(message);
                AccountInfoView.this.setUserInfo(UserManager.getInstance().getUser());
            }
        });
    }

    private void intData() {
        if (this.accountDataList == null) {
            this.accountDataList = new ArrayList();
        }
        if (this.accountDataList.size() == 0) {
            HuYuUser user = UserManager.getInstance().getUser();
            this.accountDataList.add(new AccountInfoBean(user.getPassword_status() == 1 ? getString("huyu_account_change_pwd") : getString("huyu_account_set_pwd"), 1));
            this.accountDataList.add(new AccountInfoBean(getString("huyu_bind_email"), CheckUtils.isNullOrEmpty(user.getEmail()) ? "" : getString("huyu_been_bound_email"), 2));
            this.accountDataList.add(new AccountInfoBean(getString("huyu_change_phone"), CheckUtils.isNullOrEmpty(user.getPhone()) ? "" : user.getPhone(), 3));
            this.accountDataList.add(new AccountInfoBean(getString("huyu_switch_lan"), getCurrentLanguage(), 4));
        }
    }

    /* access modifiers changed from: private */
    public void setUserInfo(HuYuUser user) {
        this.tvUserName.setText(user.getUsername());
        for (AccountInfoBean accountInfoBean : this.accountDataList) {
            switch (accountInfoBean.getItemType()) {
                case 1:
                    accountInfoBean.setTitle(user.getPassword_status() == 1 ? getString("huyu_account_change_pwd") : getString("huyu_account_set_pwd"));
                    break;
                case 2:
                    if (CheckUtils.isNullOrEmpty(user.getEmail())) {
                        accountInfoBean.setOtherInfo("");
                        break;
                    } else {
                        accountInfoBean.setOtherInfo(getString("huyu_been_bound_email"));
                        break;
                    }
                case 3:
                    if (CheckUtils.isNullOrEmpty(user.getPhone())) {
                        accountInfoBean.setTitle(getString("huyu_bind_phone"));
                        accountInfoBean.setOtherInfo("");
                        break;
                    } else {
                        accountInfoBean.setTitle(getString("huyu_change_phone"));
                        accountInfoBean.setOtherInfo(user.getPhone());
                        break;
                    }
            }
        }
        this.accountAdapter.notifyDataSetChanged();
        this.tvCoin.setText(ResourceUtil.getStringContainFormat("huyu_coin", Long.valueOf(user.getUser_wallet_amount())));
        UserManager.getInstance().saveUser(user);
    }

    public String getLayoutName() {
        return "view_account_info";
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onViewDestroy() {
        SDKEventBus.getDefault().unregister(this);
        super.onViewDestroy();
    }
}
