package com.xhuyu.component.widget.ucenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.doraemon.util.language.Language;
import com.xhuyu.component.utils.ResourceUtil;
import java.util.List;

public class SwitchLanguageAdapter extends BaseAdapter {
    /* access modifiers changed from: private */
    public String currentLan;
    /* access modifiers changed from: private */
    public List<String> mDataList;
    /* access modifiers changed from: private */
    public OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int i, String str);
    }

    public SwitchLanguageAdapter(List<String> dataList, String currentLan2) {
        this.mDataList = dataList;
        this.currentLan = currentLan2;
    }

    public int getCount() {
        return this.mDataList.size();
    }

    public String getItem(int position) {
        return this.mDataList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(ResourceUtil.getLayoutId("adapter_switch_lan_item"), (ViewGroup) null);
            viewHolder.tvTitleInfo = (TextView) ResourceUtil.getWidgetView(convertView, "tv_lan");
            viewHolder.rootView = ResourceUtil.getWidgetView(convertView, "root_view");
            viewHolder.imgRight = (ImageView) ResourceUtil.getWidgetView(convertView, "img_right");
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTitleInfo.setText(this.mDataList.get(position));
        if (this.currentLan.equals(this.mDataList.get(position))) {
            viewHolder.imgRight.setVisibility(0);
        } else {
            viewHolder.imgRight.setVisibility(8);
        }
        if (this.mOnItemClickListener != null) {
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String unused = SwitchLanguageAdapter.this.currentLan = (String) SwitchLanguageAdapter.this.mDataList.get(position);
                    SwitchLanguageAdapter.this.notifyDataSetChanged();
                    String lan = "sys";
                    switch (position) {
                        case 1:
                            lan = Language.zh_hans;
                            break;
                        case 2:
                            lan = Language.zh_hant;
                            break;
                        case 3:
                            lan = Language.f807en;
                            break;
                    }
                    SwitchLanguageAdapter.this.mOnItemClickListener.onClick(position, lan);
                }
            });
        }
        return convertView;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    private static class ViewHolder {
        ImageView imgRight;
        View rootView;
        TextView tvTitleInfo;

        private ViewHolder() {
        }
    }
}
