package com.xhuyu.component.widget.ucenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xhuyu.component.utils.ResourceUtil;
import com.xhuyu.component.widget.ucenter.bean.AccountInfoBean;
import java.util.List;

public class UCenterAccountAdapter extends BaseAdapter {
    /* access modifiers changed from: private */
    public List<AccountInfoBean> mDataList;
    /* access modifiers changed from: private */
    public OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int i, AccountInfoBean accountInfoBean);
    }

    public UCenterAccountAdapter(List<AccountInfoBean> dataList) {
        this.mDataList = dataList;
    }

    public int getCount() {
        return this.mDataList.size();
    }

    public AccountInfoBean getItem(int position) {
        return this.mDataList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(ResourceUtil.getLayoutId("adapter_ucenter_account_item"), (ViewGroup) null);
            viewHolder.tvTitleInfo = (TextView) ResourceUtil.getWidgetView(convertView, "tv_title_info");
            viewHolder.rootView = ResourceUtil.getWidgetView(convertView, "root_view");
            viewHolder.tvOtherInfo = (TextView) ResourceUtil.getWidgetView(convertView, "tv_other_info");
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTitleInfo.setText(this.mDataList.get(position).getTitle());
        viewHolder.tvOtherInfo.setText(this.mDataList.get(position).getOtherInfo());
        if (this.mOnItemClickListener != null) {
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    UCenterAccountAdapter.this.mOnItemClickListener.onClick(position, (AccountInfoBean) UCenterAccountAdapter.this.mDataList.get(position));
                }
            });
        }
        return convertView;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public List<AccountInfoBean> getDataList() {
        return this.mDataList;
    }

    private static class ViewHolder {
        View rootView;
        TextView tvOtherInfo;
        TextView tvTitleInfo;

        private ViewHolder() {
        }
    }
}
