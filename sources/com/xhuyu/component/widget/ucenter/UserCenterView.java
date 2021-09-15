package com.xhuyu.component.widget.ucenter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.doraemon.util.KeyboardUtils;
import com.doraemon.util.SizeUtils;
import com.xhuyu.component.core.base.BaseAnimationListener;
import com.xhuyu.component.core.config.SDKConfig;
import com.xhuyu.component.core.manager.FloatWindowManager;
import com.xhuyu.component.widget.account.adapter.BaseHolder;
import com.xhuyu.component.widget.account.adapter.BaseListAdapter;
import com.xhuyu.component.widget.ucenter.TipDiaglogView;
import com.xhuyu.component.widget.ucenter.bean.MenuItem;
import com.xhuyu.component.widget.ucenter.views.AccountInfoView;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.utils.ContextUtil;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import com.xsdk.doraemon.utils.notch.utils.DeviceTools;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class UserCenterView extends RelativeLayout {
    private static final int STATUS_EXIT = 1;
    private static final int STATUS_EXITING = 3;
    private static final int STATUS_INIT = 0;
    private static final int STATUS_SHOWING = 2;
    private static UserCenterView instance;
    private BaseListAdapter<MenuItem> adapter;
    private Animation animationIn;
    private Animation animationOut;
    private TipDiaglogView diaglogView;
    private LinearLayout lly;

    /* renamed from: lv */
    private ListView f891lv;
    private WeakReference<Activity> reference;
    private FrameLayout root;
    /* access modifiers changed from: private */
    public ViewStack stack = new ViewStack();
    private int status = -1;

    private UserCenterView(Context context) {
        super(context);
        init();
    }

    public UserCenterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public static UserCenterView getInstance(Context context) {
        if (instance == null) {
            instance = (UserCenterView) ReflectResource.getInstance(context).getLayoutView("view_user_center");
        }
        return instance;
    }

    private void init() {
        initAnimation();
        this.status = 0;
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (UserCenterView.this.stack.size() <= 0 || !UserCenterView.this.stack.top().isKeyBoardUp()) {
                    UserCenterView.this.finish(true);
                } else {
                    KeyboardUtils.hideSoftInput(UserCenterView.this.getActivity());
                }
            }
        });
    }

    public void onViewEnter(Activity activity) {
        if (this.status == 2) {
            SDKLoggerUtil.getLogger().mo19502e("onViewEnter status error " + this.status, new Object[0]);
            return;
        }
        this.lly.startAnimation(this.animationIn);
        changeStatus(2);
        this.reference = new WeakReference<>(activity);
        if (this.stack.size() == 0) {
            enterIndex();
        } else {
            this.stack.top().onViewStart();
        }
    }

    public void finish(boolean animation) {
        if (this.status != 2) {
            SDKLoggerUtil.getLogger().mo19502e("finish status error " + this.status, new Object[0]);
            return;
        }
        changeStatus(3);
        if (this.stack.size() > 0) {
            this.stack.top().onViewStop();
        }
        if (animation) {
            this.lly.startAnimation(this.animationOut);
        } else {
            onExit();
        }
    }

    /* access modifiers changed from: private */
    public void onExit() {
        SDKLoggerUtil.getLogger().mo19501d("onExit", new Object[0]);
        changeStatus(1);
        ((ViewGroup) getParent()).removeView(this);
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
        this.reference = null;
    }

    private void changeStatus(int status2) {
        this.status = status2;
    }

    private void initAnimation() {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int width = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.animationIn = new TranslateAnimation((float) (-width), 0.0f, 0.0f, 0.0f);
        this.animationIn.setDuration(500);
        this.animationOut = new TranslateAnimation(0.0f, (float) (-width), 0.0f, 0.0f);
        this.animationOut.setDuration(500);
        this.animationOut.setAnimationListener(new BaseAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                UserCenterView.this.onExit();
            }
        });
    }

    private void reMeasure() {
        this.f891lv = (ListView) ReflectResource.getInstance(getContext()).getWidgetView(this, "lv_menu");
        this.lly = (LinearLayout) ReflectResource.getInstance(getContext()).getWidgetView(this, "lly");
        int widthPixels = ContextUtil.getInstance().getWidthPixels();
        int size = Math.min(widthPixels, ContextUtil.getInstance().getHeightPixels());
        ViewGroup.LayoutParams lp = this.lly.getLayoutParams();
        lp.width = size;
        if (SDKConfig.getInstance().isLandscape()) {
            lp.width = (int) (((double) widthPixels) * 0.55d);
            if (FloatWindowManager.getInstance().getRectArray().size() > 0) {
                int navigationBarHeight = DeviceTools.getNavigationBarHeight(getContext());
                lp.width = ((int) (((double) widthPixels) * 0.55d)) + navigationBarHeight;
                this.f891lv.setLayoutParams(new LinearLayout.LayoutParams(SizeUtils.dp2px(50.0f) + navigationBarHeight, -1));
            }
        }
        lp.height = size;
        this.lly.requestLayout();
        SDKLoggerUtil.getLogger().mo19501d("size-->" + size, new Object[0]);
    }

    private void initView() {
        this.root = (FrameLayout) ReflectResource.getInstance(getContext()).getWidgetView(this, "fly_content");
        this.adapter = new MenuItemAdapter();
        this.adapter.appendData(new ArrayList<MenuItem>() {
            {
                add(new MenuItem(UserCenterView.this.getStringByName("huyu_account"), "ucenter_tab_user", 0));
            }
        });
        this.f891lv.setAdapter(this.adapter);
        this.f891lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 0) {
                    UserCenterView.this.goHome();
                }
            }
        });
        this.lly.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        this.stack.attach(this.root);
    }

    public void showToast(String msg) {
        try {
            if (!TextUtils.isEmpty(msg)) {
                Activity activity = getActivity();
                if (activity != null) {
                    Toast.makeText(activity, msg, 0).show();
                } else {
                    SDKLoggerUtil.getLogger().mo19501d("showToast fail activity not attach?", new Object[0]);
                }
            }
        } catch (Exception e) {
        }
    }

    public void showDialog(String content, TipDiaglogView.IDialogViewListener listener) {
        if (this.diaglogView == null) {
            this.diaglogView = new TipDiaglogView(getContext());
        }
        if (!this.diaglogView.isShowing()) {
            this.diaglogView.setContentText(content);
            this.diaglogView.setListener(listener);
            this.diaglogView.show(this);
        }
    }

    private boolean checkEnvir() {
        if (this.root != null) {
            return true;
        }
        SDKLoggerUtil.getLogger().mo19501d("UserCenterRootView create fail...", new Object[0]);
        return false;
    }

    private void enterIndex() {
        if (checkEnvir()) {
            this.stack.clearStack();
            startView(new AccountInfoView(getContext()));
        }
    }

    public void startView(IViewWrapper view) {
        if (checkEnvir()) {
            this.stack.push(view);
        }
    }

    public void destroyView(IViewWrapper view) {
        if (checkEnvir()) {
            this.stack.removeView(view);
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        reMeasure();
        initView();
    }

    public boolean onBackPressed() {
        if (this.diaglogView == null || !this.diaglogView.isShowing()) {
            return backPressedDispatcher();
        }
        this.diaglogView.dismiss();
        return true;
    }

    private boolean backPressedDispatcher() {
        if (this.stack.size() < 2) {
            return false;
        }
        return this.stack.top().onBackPressed();
    }

    public void getApplicationContext() {
        getContext().getApplicationContext();
    }

    public Activity getActivity() {
        if (this.reference != null) {
            return (Activity) this.reference.get();
        }
        return null;
    }

    public void goHome() {
        if (this.stack.size() >= 2) {
            for (int i = this.stack.size() - 1; i > 0; i--) {
                this.stack.removeView((IViewWrapper) this.stack.get(i));
            }
        }
    }

    private static class MenuItemAdapter extends BaseListAdapter<MenuItem> {
        private MenuItemAdapter() {
        }

        public View createView(int position, ViewGroup parent) {
            return ReflectResource.getInstance(parent.getContext()).getLayoutView("item_user_menu");
        }

        public BaseHolder<MenuItem> createHolder(int postion) {
            return new MenuItemHolder();
        }
    }

    /* access modifiers changed from: private */
    public String getStringByName(String resName) {
        return ReflectResource.getInstance(getContext()).getString(resName);
    }

    private static class MenuItemHolder extends BaseHolder<MenuItem> {

        /* renamed from: tv */
        private TextView f892tv;

        private MenuItemHolder() {
        }

        public void initView(View view) {
            this.f892tv = (TextView) ReflectResource.getInstance(view.getContext()).getWidgetView(view, "tv_item");
        }

        public void bindView(int position, MenuItem item) {
            if (item.getType() == 0) {
                this.f892tv.setText(item.getName());
            }
        }
    }
}
