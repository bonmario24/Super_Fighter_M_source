package com.xhuyu.component.widget.ucenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.doraemon.p027eg.CheckUtils;
import com.xhuyu.component.mvp.model.CountryModel;
import com.xhuyu.component.utils.ResourceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryAreaAdapter extends BaseAdapter implements SectionIndexer {
    private List<CountryModel> mDataList;

    public CountryAreaAdapter(List<CountryModel> dataList) {
        if (dataList == null) {
            this.mDataList = new ArrayList();
        } else {
            this.mDataList = dataList;
        }
    }

    public int getCount() {
        return this.mDataList.size();
    }

    public CountryModel getItem(int position) {
        return this.mDataList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(ResourceUtil.getLayoutId("adapter_search_country_item"), (ViewGroup) null);
            viewHolder.tvCountry = (TextView) ResourceUtil.getWidgetView(convertView, "tv_country");
            viewHolder.tvCode = (TextView) ResourceUtil.getWidgetView(convertView, "tv_code");
            viewHolder.countryCatalog = (TextView) ResourceUtil.getWidgetView(convertView, "country_catalog");
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CountryModel countryModel = this.mDataList.get(position);
        if (position == getPositionForSection(getSectionForPosition(position))) {
            viewHolder.countryCatalog.setVisibility(0);
            String sortLetters = countryModel.getSortLetters();
            if (countryModel.hasStroke()) {
                sortLetters = sortLetters + " 劃";
            }
            viewHolder.countryCatalog.setText(sortLetters);
        } else {
            viewHolder.countryCatalog.setVisibility(8);
        }
        viewHolder.tvCountry.setText(countryModel.getCountryName());
        viewHolder.tvCode.setText("+" + countryModel.getAreaCode());
        return convertView;
    }

    public int getPositionForSection(int section) {
        if (section == 42) {
            return 0;
        }
        for (int i = 0; i < getCount(); i++) {
            String sortStr = this.mDataList.get(i).getSortLetters();
            if (!CheckUtils.isNullOrEmpty(sortStr) && !"unknown".equals(sortStr)) {
                if (this.mDataList.get(i).hasStroke()) {
                    if (Integer.parseInt(this.mDataList.get(i).getSortLetters()) == section) {
                        return i;
                    }
                } else if (sortStr.toUpperCase(Locale.CHINESE).charAt(0) == section) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int getSectionForPosition(int position) {
        String sortLetters = this.mDataList.get(position).getSortLetters();
        if (CheckUtils.isNullOrEmpty(sortLetters) || "unknown".equals(sortLetters)) {
            return -1;
        }
        if (!this.mDataList.get(position).hasStroke()) {
            return this.mDataList.get(position).getSortLetters().charAt(0);
        }
        return Integer.parseInt(this.mDataList.get(position).getSortLetters());
    }

    public Object[] getSections() {
        return null;
    }

    public void updateListView(List<CountryModel> list) {
        if (list == null) {
            this.mDataList = new ArrayList();
        } else {
            this.mDataList = list;
        }
        notifyDataSetChanged();
    }

    public List<CountryModel> getDataList() {
        return this.mDataList;
    }

    private static class ViewHolder {
        TextView countryCatalog;
        TextView tvCode;
        TextView tvCountry;

        private ViewHolder() {
        }
    }
}
