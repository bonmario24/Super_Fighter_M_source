package com.xhuyu.component.widget.ucenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.util.KeyboardUtils;
import com.doraemon.util.language.Language;
import com.doraemon.util.language.LanguageUtil;
import com.xhuyu.component.callback.OnMultiClickListener;
import com.xhuyu.component.widget.LoadingDialog;
import com.xhuyu.component.widget.ucenter.TipDiaglogView;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

public abstract class IViewWrapper {
    public static final int STATUS_CREATE = 1;
    public static final int STATUS_DESTROY = 4;
    public static final int STATUS_START = 2;
    public static final int STATUS_STOP = 3;
    private View.OnClickListener DEFAULT_CLICK = new OnMultiClickListener() {
        public void onMultiClick(View view) {
        }
    };
    private View baseView;
    private Context context;
    private LoadingDialog loadingDialog;
    private int status;
    private Rect viewXY = null;

    public abstract String getLayoutName();

    public abstract void initView();

    public IViewWrapper(Context context2) {
        this.context = context2;
        createBaseView();
    }

    private void createBaseView() {
        this.baseView = ReflectResource.getInstance(this.context).getLayoutView(getLayoutName());
        this.baseView.setOnClickListener(this.DEFAULT_CLICK);
        initView();
        onViewCreate();
    }

    public View getBaseView() {
        if (this.baseView == null) {
            createBaseView();
        }
        return this.baseView;
    }

    public Context getContext() {
        return this.context;
    }

    public void startView(IViewWrapper view) {
        if (this.context == null) {
            SDKLoggerUtil.getLogger().mo19502e("startView fail content is null", new Object[0]);
        } else {
            UserCenterView.getInstance(getContext()).startView(view);
        }
    }

    public int getStatus() {
        return this.status;
    }

    private void setStatus(int status2) {
        this.status = status2;
    }

    public void showDialog(String titleRes, TipDiaglogView.IDialogViewListener listener) {
        UserCenterView.getInstance(getContext()).showDialog(getString(titleRes), listener);
    }

    public View findViewByName(String name) {
        if (this.baseView != null) {
            return ReflectResource.getInstance(this.context).getWidgetView(this.baseView, name);
        }
        SDKLoggerUtil.getLogger().mo19501d("findViewByName baseView is null", new Object[0]);
        return null;
    }

    public String[] getStringArray(String resName) {
        return ReflectResource.getInstance(this.context).getStringArray(resName);
    }

    public String getString(String name) {
        if (this.context == null) {
            return "";
        }
        return ReflectResource.getInstance(this.context).getString(name);
    }

    public void setOnClickListener(String idName, View.OnClickListener listener) {
        findViewByName(idName).setOnClickListener(listener);
    }

    public void showToast(String msg) {
        UserCenterView.getInstance(getContext()).showToast(msg);
    }

    public LoadingDialog showLoading(String msg) {
        if (msg == null) {
            msg = getString("huyu_tip_request");
        }
        Activity activity = getActivity();
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        if (this.loadingDialog == null) {
            this.loadingDialog = new LoadingDialog(activity);
        } else if (this.loadingDialog.isShowing()) {
            this.loadingDialog.dismiss();
        }
        this.loadingDialog.setLoadingMessage(msg);
        try {
            this.loadingDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.loadingDialog;
    }

    public Activity getActivity() {
        return UserCenterView.getInstance(this.context).getActivity();
    }

    public boolean onBackPressed() {
        if (isKeyBoardUp()) {
            KeyboardUtils.hideSoftInput(getActivity());
        } else if (this.loadingDialog == null || !this.loadingDialog.isShowing()) {
            finish();
        } else {
            this.loadingDialog.dismiss();
        }
        return true;
    }

    public boolean isKeyBoardUp() {
        if (this.baseView == null || getActivity() == null) {
            return false;
        }
        Rect rect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        if ((this.viewXY.bottom - this.viewXY.top) - (rect.bottom - rect.top) > 10) {
            return true;
        }
        return false;
    }

    public void finish() {
        if (isKeyBoardUp()) {
            KeyboardUtils.hideSoftInput(getActivity());
        } else {
            UserCenterView.getInstance(getContext()).destroyView(this);
        }
    }

    public boolean isDestroy() {
        return getStatus() == 4;
    }

    public void onViewCreate() {
        setStatus(1);
    }

    public void onViewStart() {
        setStatus(2);
        if (this.viewXY == null) {
            this.viewXY = new Rect();
            if (getActivity() != null) {
                getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(this.viewXY);
            }
        }
    }

    public void onViewStop() {
        setStatus(3);
        KeyboardUtils.hideSoftInput(getActivity());
    }

    public void onViewDestroy() {
        setStatus(4);
        KeyboardUtils.hideSoftInput(getActivity());
    }

    public boolean isVisiable() {
        return getStatus() == 2;
    }

    public void goHome() {
        UserCenterView.getInstance(getContext()).goHome();
    }

    public String getCurrentLanguage() {
        String spConfigLanguage = LanguageUtil.getInstance().getSPConfigLanguage(getContext());
        String systemLan = getString("huyu_lan_sys");
        if (CheckUtils.isNullOrEmpty(spConfigLanguage) && !LanguageUtil.getInstance().isSupportLanguage(spConfigLanguage)) {
            return systemLan;
        }
        if (spConfigLanguage.equals(Language.f807en)) {
            return getString("huyu_lan_en");
        }
        if (spConfigLanguage.equals(Language.zh_hans)) {
            return getString("huyu_lan_jt");
        }
        if (spConfigLanguage.equals(Language.zh_hant)) {
            return getString("huyu_lan_ft");
        }
        return systemLan;
    }
}
