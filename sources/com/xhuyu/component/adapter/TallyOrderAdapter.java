package com.xhuyu.component.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xhuyu.component.mvp.model.TallyOrderBean;
import com.xhuyu.component.utils.ResourceUtil;
import java.util.List;

public class TallyOrderAdapter extends BaseAdapter {
    private List<TallyOrderBean> mDataList;

    public TallyOrderAdapter(List<TallyOrderBean> dataList) {
        this.mDataList = dataList;
    }

    public int getCount() {
        return this.mDataList.size();
    }

    public TallyOrderBean getItem(int position) {
        return this.mDataList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(ResourceUtil.getLayoutId("adapter_tally_order_item"), (ViewGroup) null);
            viewHolder.tvLeft = (TextView) ResourceUtil.getWidgetView(convertView, "tv_left");
            viewHolder.tvRight = (TextView) ResourceUtil.getWidgetView(convertView, "tv_right");
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TallyOrderBean orderBean = this.mDataList.get(position);
        viewHolder.tvLeft.setText(orderBean.getLeftInfo());
        viewHolder.tvLeft.setTextColor(Color.parseColor(orderBean.getTextColorString()));
        viewHolder.tvRight.setText(orderBean.getRightDetail());
        viewHolder.tvRight.setTextColor(Color.parseColor(orderBean.getTextColorString()));
        return convertView;
    }

    private static class ViewHolder {
        TextView tvLeft;
        TextView tvRight;

        private ViewHolder() {
        }
    }
}
