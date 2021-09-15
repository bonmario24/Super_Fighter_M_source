package com.xhuyu.component.widget.account;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.annotation.Nullable;
import com.doraemon.util.SizeUtils;
import com.xhuyu.component.callback.OnAccountSelectListener;
import com.xhuyu.component.core.manager.UserHistoryDbManager;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xhuyu.component.utils.ViewUtil;
import com.xhuyu.component.widget.account.adapter.AccountListAdapter;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.util.ArrayList;
import java.util.List;

public class AccoutInputLayout extends LinearLayout {
    public static final int TYPE_ACCOUNT = 0;
    public static final int TYPE_PHONE = 1;
    private static final int maxNumber = 4;
    /* access modifiers changed from: private */
    public AccountListAdapter adapter = new AccountListAdapter();
    public EditText etAccout;
    public ImageView ivDown;
    private ListView listView;
    /* access modifiers changed from: private */
    public OnAccountSelectListener listener;
    private int type;
    private PopupWindow window;

    public AccoutInputLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        SDKLoggerUtil.getLogger().mo19501d("----->" + getTag(), new Object[0]);
        inflateByTag();
    }

    public AccoutInputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        SDKLoggerUtil.getLogger().mo19501d("----->" + getTag(), new Object[0]);
        inflateByTag();
    }

    private void inflateByTag() {
        if ("account".equals(getTag())) {
            inflate(getContext(), ReflectResource.getInstance(getContext()).getLayoutId("x_view_account_input"), this);
            this.type = 0;
        } else if ("phone".equals(getTag())) {
            inflate(getContext(), ReflectResource.getInstance(getContext()).getLayoutId("x_view_phone_input"), this);
            this.type = 1;
        }
    }

    private void init() {
        this.etAccout = (EditText) findViewByName("edt_account");
        this.ivDown = (ImageView) findViewByName("iv_down");
        this.ivDown.setVisibility(8);
        creatListView();
        this.ivDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AccoutInputLayout.this.showListData();
            }
        });
        ViewUtil.bindFocusVisiable(this.etAccout, findViewByName("iv_delete"));
    }

    private void initUserList() {
        Context context = getContext();
        new AsyncTask<Void, Void, List<HuYuUser>>() {
            /* access modifiers changed from: protected */
            public List<HuYuUser> doInBackground(Void... params) {
                return AccoutInputLayout.this.getAllUserByTag();
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(List<HuYuUser> users) {
                AccoutInputLayout.this.adapter.resetData(users);
                AccoutInputLayout.this.ivDown.setVisibility(users.size() == 0 ? 8 : 0);
                if (AccoutInputLayout.this.listener != null) {
                    AccoutInputLayout.this.listener.onAccountListInit();
                }
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public List<HuYuUser> getAllUserByTag() {
        if ("account".equals(getTag())) {
            return UserHistoryDbManager.getInstance().getUserTable().getAllUser();
        }
        if ("phone".equals(getTag())) {
            return UserHistoryDbManager.getInstance().getPhoneTable().getAllUser();
        }
        return new ArrayList();
    }

    private void creatListView() {
        this.adapter.setListener(new AccountListAdapter.AccountListAdapterListener() {
            public void deleteUser(int position) {
                AccoutInputLayout.this.onDeleteUser(position);
            }
        });
        this.listView = new ListView(getContext());
        this.listView.setAdapter(this.adapter);
        this.listView.setDividerHeight(0);
        this.window = new PopupWindow(this.listView, -2, SizeUtils.dp2px(40.0f) * 4);
        this.window.setOutsideTouchable(true);
        this.window.setFocusable(true);
        this.window.setBackgroundDrawable(new BitmapDrawable());
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (AccoutInputLayout.this.listener != null) {
                    AccoutInputLayout.this.listener.OnAccountSelect((HuYuUser) AccoutInputLayout.this.adapter.getItem(position));
                }
                AccoutInputLayout.this.dissWindow();
            }
        });
        this.window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                AccoutInputLayout.this.ivDown.setSelected(false);
            }
        });
        initUserList();
    }

    /* access modifiers changed from: private */
    public void onDeleteUser(int position) {
        HuYuUser user = (HuYuUser) this.adapter.getItem(position);
        switch (this.type) {
            case 0:
                UserHistoryDbManager.getInstance().getUserTable().deleteUser(user.getUsername());
                break;
            case 1:
                UserHistoryDbManager.getInstance().getPhoneTable().deleteUser(user.getUsername());
                break;
        }
        this.adapter.deleteData(position);
        if (this.adapter.getCount() == 0) {
            dissWindow();
        }
        this.ivDown.setVisibility(this.adapter.getCount() == 0 ? 8 : 0);
        clearAccout(user, true);
    }

    private void clearAccout(HuYuUser user, boolean fromList) {
        if (this.listener != null) {
            this.listener.OnAccountDelete(user, fromList);
        }
    }

    /* access modifiers changed from: private */
    public void showListData() {
        if (this.listener != null) {
            this.listener.onAccountListShow();
        }
        if (this.adapter.getCount() != 0 && !this.window.isShowing()) {
            int width = getMeasuredWidth();
            if (width > 0 && this.window.getHeight() != width) {
                this.window.setWidth(width);
            }
            if (this.adapter.getCount() <= 4) {
                this.window.setHeight(-2);
            }
            this.window.showAsDropDown(this);
            this.ivDown.setSelected(true);
        }
    }

    /* access modifiers changed from: private */
    public void dissWindow() {
        if (this.window != null && this.window.isShowing()) {
            this.window.dismiss();
        }
    }

    public void setOnAccountSelectListener(OnAccountSelectListener listener2) {
        this.listener = listener2;
        if (this.adapter.getCount() > 0) {
            this.listener.onAccountListInit();
        }
    }

    public HuYuUser getNewXuser() {
        if (this.adapter == null || this.adapter.getCount() == 0) {
            return null;
        }
        return (HuYuUser) this.adapter.getItem(0);
    }

    private View findViewByName(String name) {
        return findViewById(getLayoutId(name));
    }

    private int getLayoutId(String name) {
        return ReflectResource.getInstance(getContext()).getWidgetViewID(name);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        dissWindow();
        super.onDetachedFromWindow();
    }
}
