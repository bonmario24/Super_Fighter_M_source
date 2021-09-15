package com.xhuyu.component.widget.account.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xhuyu.component.callback.OnMultiClickListener;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;
import java.util.List;

public class AccountListAdapter extends BaseListAdapter<HuYuUser> {
    /* access modifiers changed from: private */
    public AccountListAdapterListener listener;

    public interface AccountListAdapterListener {
        void deleteUser(int i);
    }

    public AccountListAdapter(List<HuYuUser> xUsers) {
        super(xUsers);
    }

    public AccountListAdapter() {
    }

    public View createView(int position, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(ReflectResource.getInstance(parent.getContext()).getLayoutId("x_view_account_list_item"), parent, false);
    }

    public BaseHolder<HuYuUser> createHolder(int postion) {
        return new AccountListHolder();
    }

    public class AccountListHolder extends BaseHolder<HuYuUser> {

        /* renamed from: iv */
        private ImageView f889iv;

        /* renamed from: tv */
        private TextView f890tv;

        public AccountListHolder() {
        }

        public void initView(View view) {
            this.f890tv = (TextView) ReflectResource.getInstance(view.getContext()).getWidgetView(view, "tv_account_list_item");
            this.f889iv = (ImageView) ReflectResource.getInstance(view.getContext()).getWidgetView(view, "iv_close_account_list_item");
        }

        public void bindView(final int position, HuYuUser item) {
            this.f890tv.setText(item.getUsername());
            this.f889iv.setOnClickListener(new OnMultiClickListener() {
                public void onMultiClick(View view) {
                    SDKLoggerUtil.getLogger().mo19501d("click----" + position, new Object[0]);
                    if (AccountListAdapter.this.listener != null) {
                        AccountListAdapter.this.listener.deleteUser(position);
                    }
                }
            });
        }
    }

    public void setListener(AccountListAdapterListener listener2) {
        this.listener = listener2;
    }
}
